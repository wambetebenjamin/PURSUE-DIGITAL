/**
 * combat.js — the fight, resolved on the server.
 *
 * The rule the reference teaches: **the client never invents a number.** The
 * client asks what is owed and the server answers. So the server owns the
 * fight too.
 *
 * The engine below is a faithful port of the shipped client's resolver
 * (src/core/skirmish.js) — same RNG, same 0.1-second beats, same mitigation,
 * same skill semantics, same order-of-battle arithmetic. That matters: the
 * client sends the seed and the moments it fired each skill, and the server
 * replays the fight and gets the same answer. The server then applies its
 * result, not the client's.
 */

import { SKILLS } from './config.js';
import { orderOfBattle, tierScale } from './muster.js';

/** xorshift32 — the client's generator, bit for bit. */
export function rng(seed) {
  let x = seed >>> 0 || 1;
  return () => {
    x ^= x << 13; x >>>= 0;
    x ^= x >> 17; x ^= x << 5; x >>>= 0;
    return x / 4294967296;
  };
}

function sum(groups, key) {
  return groups.reduce((n, g) => (g.hp > 0 ? g[key] : 0), 0);
}

/**
 * The fight.
 *
 * @param {object} stage    one of config.SKIRMISHES
 * @param {object} troops   {companies: {...}}
 * @param {object} [opts]   {barracks, seed, heroes, skillTimings}
 * @returns {{won:boolean, log:Array, casualties:object, losses:object, elapsed:number}}
 */
export function simulate(stage, troops, opts = {}) {
  const seed = opts.seed || 0x5eed;
  const random = rng(seed);
  const barracks = opts.barracks || 1;

  const state = {
    stage,
    seed,
    random,
    time: 0,
    wave: 0,
    status: 'fighting',
    log: [],
    line: [],
    heroes: [],
    buffs: { atk: 0, shield: 0, weaken: 0 },
    enemy: [],
    skillTimings: opts.skillTimings || {},
  };

  // --- our line
  state.line = orderOfBattle(troops, barracks).map((c) => ({
    id: c.id,
    name: c.name,
    line: c.line,
    men: c.count,
    maxHp: Math.max(1, c.maxHp),
    hp: Math.max(1, c.maxHp),
    atk: c.atk,
    def: c.def,
    hpPerMan: c.maxHp / Math.max(1, c.count),
  }));

  // --- the heroes and their gauges
  state.heroes = (opts.heroes || []).map((h) => ({
    ...h,
    skill: h.skill || SKILLS[h.id] || { name: 'Stand', kind: 'rally', power: 0.3, charge: 26 },
    gauge: 35,
    ready: false,
  }));

  loadWave(state, 0);
  push(state, `The band closes with ${stage.name.toLowerCase()}.`);

  let guard = 0;
  while (state.status === 'fighting' && guard < 60000) {
    tick(state, 0.2);
    // the band fires whatever is ready
    for (const h of state.heroes) {
      if (!h.ready) continue;
      const scheduled = state.skillTimings[h.id];
      if (scheduled !== undefined && state.time + 1e-9 < scheduled) continue;
      useSkill(state, h.id);
    }
    guard += 1;
  }

  return {
    won: state.status === 'won',
    status: state.status,
    log: state.log,
    casualties: casualties(state),
    losses: losses(state),
    elapsed: Math.round(state.time * 10) / 10,
    seed,
    hp: state.line.map((g) => ({ id: g.id, hp: Math.max(0, Math.round(g.hp)), men: g.men })),
  };
}

function push(state, line) {
  state.log.push({ t: Math.round(state.time), line });
  if (state.log.length > 40) state.log.shift();
}

function loadWave(state, i) {
  const wave = state.stage.waves[i] || [];
  state.enemy = wave.map((g) => ({
    id: g.key,
    name: g.name,
    men: g.count,
    maxHp: Math.max(1, Math.round(g.count * g.hp)),
    hp: Math.max(1, Math.round(g.count * g.hp)),
    atk: Math.round(g.count * g.atk),
    def: Math.round(g.count * g.def),
    hpPerMan: g.hp,
  }));
  state.wave = i;
  if (i > 0) {
    push(state, `A second party comes on: ${wave.map((g) => g.name).join(' and ')}.`);
  }
}

function ours(state) {
  return state.line.filter((g) => g.hp > 0);
}

function theirs(state) {
  return state.enemy.filter((g) => g.hp > 0);
}

function casualties(state) {
  let standing = 0;
  let fallen = 0;
  for (const g of state.line) {
    const left = g.hp > 0 ? Math.max(0, Math.round(g.hp / g.hpPerMan)) : 0;
    standing += left;
    fallen += Math.max(0, g.men - left);
  }
  return { standing, fallen };
}

function losses(state) {
  const out = {};
  for (const g of state.line) {
    const left = g.hp > 0 ? Math.max(0, Math.round(g.hp / g.hpPerMan)) : 0;
    const dead = Math.max(0, g.men - left);
    if (dead > 0) out[g.id] = dead;
  }
  return out;
}

/** Advance the fight. Small steps so a long frame does not skip a beat. */
function tick(state, dt) {
  if (state.status !== 'fighting') return state.status;
  const step = Math.min(0.2, Math.max(0, dt));
  state._since = (state._since || 0) + step;
  while (state._since >= 0.1) {
    beat(state, 0.1);
    state._since -= 0.1;
    if (state.status !== 'fighting') break;
  }
  return state.status;
}

function beat(state, dt) {
  state.time += dt;

  // hero gauges fill whether or not the player is looking
  for (const h of state.heroes) {
    if (h.gauge < 100) {
      h.gauge = Math.min(100, h.gauge + (100 / h.skill.charge) * dt);
    }
    h.ready = h.gauge >= 100;
  }
  // buffs run down
  for (const k of Object.keys(state.buffs)) {
    if (state.buffs[k] > 0) state.buffs[k] = Math.max(0, state.buffs[k] - dt);
  }

  const myLine = ours(state);
  const theirLine = theirs(state);
  if (myLine.length === 0) return end(state, false);
  if (theirLine.length === 0) {
    if (state.wave + 1 < state.stage.waves.length) {
      loadWave(state, state.wave + 1);
      return state.status;
    }
    return end(state, true);
  }

  // our blow
  const front = myLine.filter((g) => g.line === 'front');
  const back = myLine.filter((g) => g.line === 'back');
  const heroBonus = 1 + state.heroes.reduce((n, h) => n + h.power * 0.02, 0);
  const rally = state.buffs.atk > 0 ? 1.55 : 1;
  const ourAtk = (sum(front, 'atk') + sum(back, 'atk') * 0.6) * heroBonus * rally;
  strike(state, state.enemy, ourAtk * dt, true);

  // their blow: the wall takes it, unless the wall is down
  const weaken = state.buffs.weaken > 0 ? 0.72 : 1;
  const theirAtk = (sum(theirLine, 'atk') * weaken) * (1 - state.buffs.shield * 0.35);
  if (front.length > 0) {
    strike(state, front, theirAtk * dt * 0.72, false);
    strike(state, back, theirAtk * dt * 0.18, false);
  } else {
    strike(state, myLine, theirAtk * dt, false);
  }
}

/** Apply damage to a side, front groups first. */
function strike(state, groups, amount, isOurs) {
  if (!(amount > 0)) return;
  const targets = groups
    .filter((g) => g.hp > 0)
    .sort((a, b) => (a.line === 'front' ? -1 : 1) - (b.line === 'front' ? -1 : 1));
  const size = targets.length;
  if (!size) return;
  for (const g of targets) {
    const mitigation = g.def / (g.def + 90);
    const share = amount / size;
    const dealt = share * (1 - mitigation) * (0.85 + state.random() * 0.3);
    g.hp = Math.max(0, g.hp - dealt);
    // front groups eat more of the blow
    if (isOurs && g.line === 'front' && size > 1) g.hp = Math.max(0, g.hp - dealt * 0.35);
  }
}

/** Fire a hero's skill by hand. */
function useSkill(state, id) {
  const h = state.heroes.find((x) => x.id === id);
  if (!h || h.gauge < 100 || state.status !== 'fighting') return false;
  h.gauge = 0;
  h.ready = false;
  const s = h.skill;
  const myLine = ours(state);
  const total = myLine.reduce((n, g) => n + g.hp, 0) || 0;
  const cap = myLine.reduce((n, g) => n + g.maxHp, 0) || 1;

  if (s.kind === 'heal') {
    for (const g of myLine) g.hp = Math.min(g.maxHp, g.hp + g.maxHp * s.power);
    push(state, `${h.name}: ${s.name} — the wounded rise.`);
  } else if (s.kind === 'rally') {
    state.buffs.atk = 8;
    push(state, `${h.name}: ${s.name} — the line presses forward.`);
  } else if (s.kind === 'shield') {
    state.buffs.shield = 8;
    for (const g of myLine) g.hp = Math.min(g.maxHp, g.hp + total * s.power * 0.1);
    push(state, `${h.name}: ${s.name} — shields lock.`);
  } else if (s.kind === 'burst') {
    const blow = cap * s.power * 0.06;
    strike(state, state.enemy, blow, true);
    push(state, `${h.name}: ${s.name} — ${Math.round(blow)} struck home.`);
  } else if (s.kind === 'weaken') {
    state.buffs.weaken = 8;
    push(state, `${h.name}: ${s.name} — the enemy foot slows.`);
  }
  return true;
}

function end(state, won) {
  state.status = won ? 'won' : 'lost';
  const c = casualties(state);
  push(state, won
    ? `The field is ours. ${c.standing} still standing, ${c.fallen} fallen.`
    : `The line breaks. ${c.fallen} fallen, the rest scattered.`);
  return state.status;
}

/**
 * Stars for a won skirmish: swift, silent and unseen, as in the campaign.
 * @param {object} result the value returned by simulate()
 */
export function skirmishStars(result) {
  if (!result.won) return 0;
  let stars = 1;
  if (!Object.keys(result.losses).length) stars += 1;   // untouched
  if (result.elapsed <= 60) stars += 1;                 // swift
  return Math.max(1, Math.min(3, stars));
}

/**
 * Stars for a campaign phase — the seven-phase pursuit.
 * Mirrors progression.recordPhase: swift (≤150s), silent (no alerts) or unseen.
 */
export function pursuitStars(run = {}) {
  const fast = (run.time ?? 999) <= 150;
  const silent = (run.alerts ?? 99) === 0;
  const stealth = Boolean(run.stealth);
  let stars = 1 + (fast ? 1 : 0) + ((silent || stealth) ? 1 : 0);
  return Math.max(1, Math.min(3, stars));
}

/** The reward a phase is worth at a given star rating. */
export function phaseReward(phase, stars) {
  const base = {
    STATE_0_ZIKLAG: { provisions: 40, timber: 30 },
    STATE_1_DESERT_MARCH: { provisions: 45, bronze: 20 },
    STATE_2_EGYPTIAN_SERVANT: { provisions: 40, bronze: 25 },
    STATE_3_AMALEKITE_RESCUE: { spoil: 70, bronze: 35 },
    STATE_4_SPOILS_DIVISION: { spoil: 55, timber: 30 },
    STATE_5_DISTRIBUTION: { spoil: 65, provisions: 35 },
    STATE_6_GILBOA_EPILOGUE: { spoil: 80, bronze: 45 },
  }[phase] || {};
  const mul = 1 + (Math.max(1, stars) - 1) * 0.5;
  return Object.fromEntries(Object.entries(base).map(([k, v]) => [k, Math.round(v * mul)]));
}

/** The company figures a fight reads, for the client to draw the order of battle. */
export function preview(troops, barracks = 1) {
  return orderOfBattle(troops, barracks).map((c) => ({
    ...c,
    scale: tierScale(c.tier),
  }));
}
