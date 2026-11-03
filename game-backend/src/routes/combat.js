/**
 * routes/combat.js — the pursuit.
 *
 * Two things happen under this one route, as in the shipped game:
 *
 *   stage_id  → a field skirmish. The server replays the fight from the
 *               seed and the skill timings, and its result is the truth.
 *   phase     → a campaign phase of the pursuit. The band reports how the
 *               run went, and the server awards the stars and the spoil.
 *
 * Either way the client's own report is never trusted: the server replays
 * the skirmish, and the phase stars are computed from the numbers the
 * server receives but the reward is the server's to give.
 */

import { CODE } from '../envelope.js';
import { GameError } from '../auth.js';
import { loadState, saveState } from '../services/state.js';
import {
  simulate, skirmishStars, pursuitStars, phaseReward, preview,
} from '../services/combat.js';
import { applyLosses, fieldHeroes, isUnlocked } from '../services/muster.js';
import { addResources } from '../services/economy.js';
import { SKIRMISHES, SKIRMISH_IDS, SKILLS, PHASE_ORDER, TASKS, ROSTER } from '../services/config.js';

function barracksLevel(encampment) {
  return Math.max(1, Math.min(5, (encampment.works && encampment.works.barracks) || 1));
}

/** The heroes who took the field, with their skills. */
function heroesFor(encampment, player, requested) {
  const stars = player.stars || 0;
  const roster = encampment.roster || {};
  const available = fieldHeroes(stars, roster);
  const wanted = Array.isArray(requested) && requested.length
    ? requested.filter((id) => isUnlocked(id, stars, roster))
    : available.map((h) => h.id);
  return wanted.map((id) => {
    const m = ROSTER.find((x) => x.id === id);
    const skill = SKILLS[id] || { name: 'Stand', kind: 'rally', power: 0.3, charge: 26 };
    const level = (roster[id] || {}).level || 1;
    return {
      id,
      name: m ? m.name : id,
      role: m ? m.role : 'blade',
      power: m ? m.power : 1,
      level,
      skill,
    };
  });
}

export default [
  {
    method: 'post', path: '/api/combat/pursue', rpc: 'CCombatPursue', auth: true,
    handler: async ({ body, player, store, now }) => {
      const { encampment, troops } = await loadState(store, player);
      const barracks = barracksLevel(encampment);

      /* ------------------------------------------------ the campaign phase */
      if (body.phase) {
        if (!PHASE_ORDER.includes(body.phase)) {
          throw new GameError(CODE.PARAM_ERROR, 'unknown phase');
        }
        const stars = pursuitStars({
          time: Number(body.time) || 0,
          alerts: Number(body.alerts) || 0,
          stealth: Boolean(body.stealth),
        });
        const reward = phaseReward(body.phase, stars);
        encampment.resources = addResources(encampment.resources, reward);
        const renownMul = 1 + (encampment.works.hall || 1) * 0.10;
        const renown = Math.round(stars * 10 * renownMul);
        encampment.renown = (encampment.renown || 0) + renown;
        encampment.runs = (encampment.runs || 0) + 1;

        const task = TASKS.find((t) => t.phase === body.phase);
        if (task) {
          encampment.tasks_done = Array.from(new Set([...(encampment.tasks_done || []), task.id]));
        }

        await store.addLoot({
          player_id: player.role_id,
          pursuit_id: `phase:${body.phase}`,
          kind: 'spoil',
          source: task ? task.verse : body.phase,
          items: reward,
          claimed: true,
        });
        await store.addPursuit({
          player_id: player.role_id,
          stage_id: null,
          phase: body.phase,
          seed: Number(body.seed) || 0,
          won: true,
          stars,
          rewards: reward,
          renown,
          casualties: { standing: 0, fallen: 0 },
          losses: {},
          elapsed: Number(body.time) || 0,
          log: [],
        });

        player.stars = (player.stars || 0) + stars;
        player.renown = (encampment.renown || 0);
        await store.putPlayer(player);
        await saveState(store, { encampment, troops });

        return {
          kind: 'phase',
          phase: body.phase,
          stars,
          reward,
          renown,
          resources: { ...encampment.resources },
          task: task ? { id: task.id, title: task.title, verse: task.verse } : null,
        };
      }

      /* ----------------------------------------------------- the field fight */
      const stageId = body.stage_id;
      if (!SKIRMISH_IDS.includes(stageId)) {
        throw new GameError(CODE.PARAM_ERROR, 'unknown stage');
      }
      const stage = SKIRMISHES.find((s) => s.id === stageId);
      const seed = Number(body.seed) || 0x5eed;
      const skillTimings = body.skill_timings || {};
      const heroes = heroesFor(encampment, player, body.hero_ids);

      const result = simulate(stage, troops, {
        barracks,
        seed,
        heroes,
        skillTimings,
      });

      const stars = skirmishStars(result);
      const rewards = result.won ? { ...stage.rewards } : {};
      if (result.won) {
        encampment.resources = addResources(encampment.resources, rewards);
        const renownMul = 1 + (encampment.works.hall || 1) * 0.10;
        const renown = Math.round((stage.renown || 0) * renownMul);
        encampment.renown = (encampment.renown || 0) + renown;
        troops.wins[stageId] = (troops.wins[stageId] || 0) + 1;
        troops.battles = (troops.battles || 0) + 1;
      } else {
        troops.lost = (troops.lost || 0) + 1;
        troops.battles = (troops.battles || 0) + 1;
      }
      const taken = applyLosses(troops, result.losses);

      await store.addPursuit({
        player_id: player.role_id,
        stage_id: stageId,
        phase: null,
        seed,
        won: result.won,
        stars,
        rewards,
        renown: result.won ? Math.round((stage.renown || 0) * (1 + (encampment.works.hall || 1) * 0.10)) : 0,
        casualties: result.casualties,
        losses: taken,
        elapsed: result.elapsed,
        log: (result.log || []).map((l) => l.line),
      });
      if (Object.keys(rewards).length) {
        await store.addLoot({
          player_id: player.role_id,
          pursuit_id: `stage:${stageId}`,
          kind: 'spoil',
          source: stage.name,
          items: rewards,
          claimed: true,
        });
      }

      player.stars = (player.stars || 0) + stars;
      player.renown = encampment.renown || 0;
      await store.putPlayer(player);
      await saveState(store, { encampment, troops });

      const verified = body.client_result
        ? Boolean(body.client_result.won) === result.won
        : null;

      return {
        kind: 'skirmish',
        stage_id: stageId,
        won: result.won,
        stars,
        rewards,
        casualties: result.casualties,
        losses: taken,
        elapsed: result.elapsed,
        seed,
        log: result.log,
        hp: result.hp,
        recommended_power: stage.power,
        client_report_agrees: verified,
        resources: { ...encampment.resources },
      };
    },
  },
  {
    method: 'get', path: '/api/combat/preview', rpc: 'CCombatPreview', auth: true,
    handler: async ({ player, store }) => {
      const { encampment, troops } = await loadState(store, player);
      const barracks = barracksLevel(encampment);
      await saveState(store, { encampment, troops });
      return {
        order_of_battle: preview(troops, barracks),
        barracks_level: barracks,
        heroes: heroesFor(encampment, player, null),
        stages: SKIRMISHES.map((s) => ({
          id: s.id,
          name: s.name,
          power: s.power,
          rewards: s.rewards,
          renown: s.renown,
          waves: s.waves.length,
          won: (troops.wins && troops.wins[s.id]) || 0,
        })),
        phases: PHASE_ORDER,
      };
    },
  },
];
