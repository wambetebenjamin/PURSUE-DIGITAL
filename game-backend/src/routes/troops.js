/**
 * routes/troops.js — the band of six hundred.
 *
 *   CFarmersWorldBuyPeople   → CTroopsRecruit   (a press of ten)
 *   CFarmersWorldMakeTool    → CTroopsTrain     (a company up a tier)
 *   CFarmersWorldRecoverHP   → CTroopsHeal      (the wounded back in line)
 */

import { CODE } from '../envelope.js';
import { GameError } from '../auth.js';
import { loadState, saveState } from '../services/state.js';
import {
  recruit, train, heal, healAll, companyCap, companyStats, orderOfBattle,
  musterPower, bandPower, drill, drillCost, isUnlocked, fieldHeroes,
} from '../services/muster.js';
import { COMPANY_IDS, COMPANIES, MAX_TIER, STEP, ROSTER } from '../services/config.js';

function barracksLevel(encampment) {
  return Math.max(1, Math.min(5, (encampment.works && encampment.works.barracks) || 1));
}

export default [
  {
    method: 'get', path: '/api/troops', rpc: 'CTroopsDetail', auth: true,
    handler: async ({ player, store }) => {
      const { encampment, troops } = await loadState(store, player);
      const barracks = barracksLevel(encampment);
      const companies = COMPANY_IDS.map((id) => ({
        ...companyStats(id, troops, barracks),
        step: STEP,
        max_tier: MAX_TIER,
      }));
      await saveState(store, { encampment, troops });
      return {
        companies,
        order_of_battle: orderOfBattle(troops, barracks),
        power: musterPower(troops, barracks),
        band_power: bandPower(encampment.roster || {}, barracks, player.stars || 0),
        wounded: companies.reduce((n, c) => n + (c.wounded || 0), 0),
        barracks_level: barracks,
      };
    },
  },
  {
    method: 'post', path: '/api/troops/recruit', rpc: 'CTroopsRecruit', auth: true,
    handler: async ({ body, player, store }) => {
      const id = body.company_id || body.id;
      if (!COMPANY_IDS.includes(id)) throw new GameError(CODE.PARAM_ERROR, 'unknown company');
      const steps = Math.max(1, Number(body.steps) || 1);
      const { encampment, troops } = await loadState(store, player);
      const done = recruit(troops, encampment, id, steps, barracksLevel(encampment));
      if (!done) throw new GameError(CODE.PARAM_ERROR, 'the company cannot be filled');
      await saveState(store, { encampment, troops });
      return { ...done, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'post', path: '/api/troops/train', rpc: 'CTroopsTrain', auth: true,
    handler: async ({ body, player, store }) => {
      const id = body.company_id || body.id;
      if (!COMPANY_IDS.includes(id)) throw new GameError(CODE.PARAM_ERROR, 'unknown company');
      const { encampment, troops } = await loadState(store, player);
      const done = train(troops, encampment, id, barracksLevel(encampment));
      if (!done) throw new GameError(CODE.PARAM_ERROR, 'the barracks cannot drill that far');
      await saveState(store, { encampment, troops });
      return { ...done, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'post', path: '/api/troops/heal', rpc: 'CTroopsHeal', auth: true,
    handler: async ({ body, player, store }) => {
      const { encampment, troops } = await loadState(store, player);
      const hospital = Math.max(1, Math.min(5, (encampment.works && encampment.works.hospital) || 1));
      const id = body.company_id;
      if (id && !COMPANY_IDS.includes(id)) throw new GameError(CODE.PARAM_ERROR, 'unknown company');
      let healed = 0;
      let cost = { provisions: 0 };
      if (id) {
        const done = heal(troops, encampment, id, Number(body.men) || 1, hospital);
        if (!done) throw new GameError(CODE.PARAM_ERROR, 'nothing to tend');
        healed = done.healed;
        cost = done.cost;
      } else {
        healed = healAll(troops, encampment, hospital);
        if (!healed) throw new GameError(CODE.PARAM_ERROR, 'nothing to tend');
      }
      await saveState(store, { encampment, troops });
      return { healed, cost, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'post', path: '/api/troops/drill', rpc: 'CTroopsDrill', auth: true,
    handler: async ({ body, player, store }) => {
      const id = body.hero_id;
      if (!ROSTER.some((m) => m.id === id)) throw new GameError(CODE.PARAM_ERROR, 'unknown mighty man');
      const { encampment, troops } = await loadState(store, player);
      if (!encampment.roster[id]) encampment.roster[id] = { level: 1, seen: false };
      const done = drill(encampment, encampment.roster, id, player.stars || 0);
      if (!done) throw new GameError(CODE.PARAM_ERROR, 'he cannot be drilled');
      await saveState(store, { encampment, troops });
      return { ...done, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'get', path: '/api/troops/roster', rpc: 'CTroopsRoster', auth: true,
    handler: async ({ player, store }) => {
      const { encampment, troops } = await loadState(store, player);
      const barracks = barracksLevel(encampment);
      await saveState(store, { encampment, troops });
      return {
        mighty_men: ROSTER.map((m) => ({
          ...m,
          level: (encampment.roster[m.id] || {}).level || 1,
          unlocked: isUnlocked(m.id, player.stars || 0, encampment.roster || {}),
          skill: (encampment.roster[m.id] || {}).skill || null,
        })),
        in_the_field: fieldHeroes(player.stars || 0, encampment.roster || {}),
        drill_cost: Object.fromEntries(
          ROSTER.map((m) => [m.id, drillCost(m.id, encampment.roster || {})]),
        ),
        band_power: bandPower(encampment.roster || {}, barracks, player.stars || 0),
      };
    },
  },
];
