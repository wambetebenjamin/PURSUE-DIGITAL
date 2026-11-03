/**
 * muster.js — the band of six hundred.
 *
 * The reference trains soldiers behind a barracks level gate; we train
 * companies behind ours. The arithmetic is the shipped game's (src/core/
 * muster.js), so the browser client and this server never disagree about
 * how many men a company may hold or what a press of ten costs.
 */

import { COMPANIES, COMPANY_IDS, ROSTER, STEP, MAX_TIER } from './config.js';
import { canAfford, spend } from './economy.js';

/** The ceiling on a company at a given barracks level. */
export function companyCap(id, barracksLevel = 1) {
  const c = COMPANIES.find((x) => x.id === id);
  if (!c) return 0;
  const share = 0.2 + 0.2 * Math.max(1, Math.min(5, barracksLevel));
  return Math.min(c.max, Math.round(c.max * share));
}

/** Per-man effectiveness of a company at its tier. */
export function tierScale(tier) {
  return 1 + (Math.max(1, tier) - 1) * 0.35;
}

/** Aggregate combat figures for one company — what the fight reads. */
export function companyStats(id, troops, barracksLevel = 1) {
  const def = COMPANIES.find((x) => x.id === id);
  const row = troops.companies[id];
  if (!def || !row) return null;
  const scale = tierScale(row.tier);
  return {
    id,
    name: def.name,
    line: def.line,
    count: row.count,
    tier: row.tier,
    wounded: row.wounded,
    maxHp: Math.round(row.count * def.hp * scale),
    atk: Math.round(row.count * def.atk * scale),
    def: Math.round(row.count * def.def * scale),
    cap: companyCap(id, barracksLevel),
  };
}

/** The whole order of battle, front line first. */
export function orderOfBattle(troops, barracksLevel = 1) {
  const all = COMPANY_IDS
    .map((id) => companyStats(id, troops, barracksLevel))
    .filter((c) => c && c.count > 0);
  return all.sort((a, b) => (a.line === b.line ? 0 : a.line === 'front' ? -1 : 1));
}

/** Men under arms, and the strength the enemy reads from the ridge. */
export function musterPower(troops, barracksLevel = 1) {
  let power = 0;
  let men = 0;
  for (const c of COMPANY_IDS) {
    const row = troops.companies[c];
    if (!row || row.count <= 0) continue;
    const def = COMPANIES.find((x) => x.id === c);
    men += row.count;
    power += row.count * (def.atk + def.def) * tierScale(row.tier);
  }
  const drill = 1 + (Math.max(1, Math.min(5, barracksLevel)) - 1) * 0.06;
  return { power: Math.round((power / 10) * drill), men };
}

/** What it costs to take a company up one tier. */
export function trainCost(id, troops) {
  const row = troops.companies[id];
  if (!row) return null;
  if (row.tier >= MAX_TIER) return null;
  const t = row.tier;
  return { bronze: 60 + t * 45, provisions: 40 + t * 30, timber: 20 + t * 18 };
}

/**
 * Muster men into a company — `CTroopsRecruit`.
 *
 * @returns {{count:number, cost:object}|null} null when it cannot be done
 */
export function recruit(troops, encampment, id, steps = 1, barracksLevel = 1) {
  const def = COMPANIES.find((c) => c.id === id);
  const row = troops.companies[id];
  if (!def || !row) return null;
  const cap = companyCap(id, barracksLevel);
  if (row.count >= cap) return null;
  const n = Math.max(1, Math.min(steps, Math.ceil((cap - row.count) / STEP)));
  const cost = def.cost(n);
  if (!canAfford(encampment.resources, cost)) return null;

  encampment.resources = spend(encampment.resources, cost);
  row.count = Math.min(cap, row.count + n * STEP);
  return { count: row.count, cost, steps: n, cap };
}

/** Take a company up one tier — `CTroopsTrain`. The barracks gates the tier. */
export function train(troops, encampment, id, barracksLevel = 1) {
  const row = troops.companies[id];
  const cost = trainCost(id, troops);
  if (!row || !cost) return null;
  if (row.tier >= Math.min(MAX_TIER, Math.max(1, barracksLevel) + 1)) return null;
  if (!canAfford(encampment.resources, cost)) return null;
  encampment.resources = spend(encampment.resources, cost);
  row.tier += 1;
  return { tier: row.tier, cost };
}

/**
 * Bring wounded back to the line — `CTroopsHeal`.
 * The Tent of the Wounded discounts the tending.
 */
export function heal(troops, encampment, id, men = 1, hospitalLevel = 1) {
  const row = troops.companies[id];
  if (!row || row.wounded <= 0) return null;
  const n = Math.max(1, Math.min(men, row.wounded));
  const discount = 1 - 0.08 * Math.max(0, hospitalLevel - 1);
  const cost = { provisions: Math.max(1, Math.round(n * 3 * discount)) };
  if (!canAfford(encampment.resources, cost)) return null;
  encampment.resources = spend(encampment.resources, cost);
  row.wounded -= n;
  row.count += n;
  return { count: row.count, healed: n, cost };
}

/** Bring every wounded man back in one order. */
export function healAll(troops, encampment, hospitalLevel = 1) {
  let healed = 0;
  for (const c of COMPANY_IDS) {
    const row = troops.companies[c];
    if (!row || row.wounded <= 0) continue;
    const discount = 1 - 0.08 * Math.max(0, hospitalLevel - 1);
    const cost = { provisions: Math.max(1, Math.round(row.wounded * 3 * discount)) };
    if (!canAfford(encampment.resources, cost)) continue;
    encampment.resources = spend(encampment.resources, cost);
    healed += row.wounded;
    row.count += row.wounded;
    row.wounded = 0;
  }
  return healed;
}

/**
 * Write the butcher's bill after a fight.
 * Seven in ten of the fallen are wounded, not dead — the hospital's work.
 */
export function applyLosses(troops, losses = {}) {
  const taken = {};
  for (const [id, dead] of Object.entries(losses || {})) {
    const row = troops.companies[id];
    const def = COMPANIES.find((c) => c.id === id);
    if (!row || !def || !(dead > 0)) continue;
    const n = Math.min(row.count, Math.round(dead));
    if (n <= 0) continue;
    row.count -= n;
    row.wounded = Math.min(def.max, row.wounded + Math.round(n * 0.7));
    taken[id] = n;
  }
  return taken;
}

/* ---------------------------------------------------------------- the mighty */

/** Is this mighty man with the band yet? */
export function isUnlocked(id, stars = 0, roster = {}) {
  const m = ROSTER.find((x) => x.id === id);
  if (!m) return false;
  if (m.unlocked) return true;
  if (roster[id] && roster[id].unlocked) return true;
  return stars >= (m.unlockAt || 99);
}

/** Which mighty men are with the band, in roster order. */
export function fieldHeroes(stars = 0, roster = {}, limit = 4) {
  return ROSTER.filter((m) => isUnlocked(m.id, stars, roster)).slice(0, limit);
}

/** Cost to drill a mighty man one level. */
export function drillCost(id, roster = {}) {
  const row = roster[id];
  if (!row) return null;
  return { bronze: 25 + row.level * 18, provisions: 15 + row.level * 12 };
}

/** Drill a mighty man — raise his level. */
export function drill(encampment, roster, id, stars = 0) {
  if (!isUnlocked(id, stars, roster)) return null;
  const cost = drillCost(id, roster);
  if (!cost || !canAfford(encampment.resources, cost)) return null;
  encampment.resources = spend(encampment.resources, cost);
  roster[id].level = Math.min(20, (roster[id].level || 1) + 1);
  return { level: roster[id].level, cost };
}

/** Total band strength: heroes, their levels and the barracks that drilled them. */
export function bandPower(roster = {}, barracksLevel = 1, stars = 0) {
  let p = 0;
  for (const m of ROSTER) {
    if (!isUnlocked(m.id, stars, roster)) continue;
    const row = roster[m.id];
    const level = row ? row.level : 1;
    p += m.power * (1 + (level - 1) * 0.18);
  }
  return Math.round(p * (1 + barracksLevel * 0.08));
}
