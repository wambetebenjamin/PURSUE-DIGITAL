/**
 * config.js — the config tables.
 *
 * The reference reads its balance from data tables that live in the IL2CPP
 * binary, not in the decompiled code:
 *
 *     farmersworldtoolcfg     tools: type, durability, terrainType, groupId
 *     farmersworldactivitycfg  activity windows and population caps
 *     itemcfg  exchangecfg  seasoncfg  serverinfocfg  newbattlepassbasecfg
 *
 * and from constants that the Lua reads by name:
 *
 *     RECRUITMENT_COST  FARMERS_WORLD_HANDLING_FEE  UNRECRUITED_FARMER
 *     REFINED_SANDS_PRODUCTION_TIME  ..._SPEND  ..._NUM_MAX  ..._BP_SPEED_UP
 *
 * Those tables are not recoverable from the dump (doc 05, section 6), so
 * this file *is* the table. It is written in the reference's shape — same
 * terrain → resource → tool ladder, same timer arithmetic, same
 * `*_S10` season-override pattern — with the numbers taken from the game we
 * already ship (src/core/progression.js, muster.js, skirmish.js) so that the
 * browser client and this server agree to the last provision.
 */

/* ------------------------------------------------------------------ resources */

/** The four stores. Mirrors the reference's Coin / Fish / Wood / Gold. */
export const RESOURCES = Object.freeze([
  { id: 'provisions', name: 'Provisions', ref: 'Fish', note: 'bread, water, oil' },
  { id: 'bronze', name: 'Bronze', ref: 'Coin', note: 'blades, spearheads' },
  { id: 'timber', name: 'Timber', ref: 'Wood', note: 'beams and stakes' },
  { id: 'spoil', name: 'Spoil', ref: 'Gold', note: 'recovered from Amalek' },
]);

export const RESOURCE_IDS = Object.freeze(RESOURCES.map((r) => r.id));

/* ------------------------------------------------- terrain → resource → tool */

/**
 * The reference's table, verbatim in shape:
 *
 *     Home      → Coin  → None
 *     Seaside   → Fish  → FishingRod, FishingNet
 *     Forest    → Wood  → Axe, Chainsaw
 *     GoldMine  → Gold  → Pickaxe, MiningMachine
 *
 * Our yards and their tools. Basic tool, better tool — the tier ladder the
 * reference uses for every production building in the genre.
 */
export const YARDS = Object.freeze([
  {
    id: 'ziklag', ref: 'Home', name: 'The Camp at Ziklag', yields: 'bronze',
    tools: ['none'], tool: 'The Storehouse',
    note: 'Where the spoil is counted and the writs are kept.',
  },
  {
    id: 'shore', ref: 'Seaside', name: 'The Shore', yields: 'provisions',
    tools: ['handline', 'cast_net'], tool: 'The Nets',
    note: 'The brook Besor and the sea beyond it. Bread from the shallows.',
  },
  {
    id: 'woodland', ref: 'Forest', name: 'The Woodland of Judah', yields: 'timber',
    tools: ['axe', 'great_axe'], tool: 'The Woodwright',
    note: 'Beams and stakes cut from the wadi.',
  },
  {
    id: 'spoilfield', ref: 'GoldMine', name: 'The Spoilfield', yields: 'spoil',
    tools: ['pick', 'iron_crew'], tool: 'The Ironworkers',
    note: 'Where the Amalekite treasure was left, and where it is smelted.',
  },
]);

/** Tools, in the reference's two-tier ladder. */
export const TOOLS = Object.freeze([
  { id: 'none', name: 'Bare hands', tier: 0, yard: 'ziklag', durability: 0 },
  { id: 'handline', name: 'Handline', tier: 1, yard: 'shore', durability: 60 },
  { id: 'cast_net', name: 'Cast Net', tier: 2, yard: 'shore', durability: 140 },
  { id: 'axe', name: 'Axe', tier: 1, yard: 'woodland', durability: 60 },
  { id: 'great_axe', name: 'Great Axe', tier: 2, yard: 'woodland', durability: 140 },
  { id: 'pick', name: 'Pick', tier: 1, yard: 'spoilfield', durability: 60 },
  { id: 'iron_crew', name: 'Iron Crew', tier: 2, yard: 'spoilfield', durability: 140 },
]);

/* ------------------------------------------------------------------- the works */

/**
 * The Works of Ziklag — the eight buildings of the shipped city. Each level
 * is a real modifier, not decoration. `cost(lv)` buys the *next* level.
 */
export const WORKS = Object.freeze([
  {
    id: 'hall', name: 'The Hall of the March', maxLevel: 5,
    cost: (lv) => ({ timber: 40 + lv * 35, bronze: 20 + lv * 20 }),
    yield: () => null, effect: (lv) => `+${lv * 10}% renown`,
  },
  {
    id: 'barracks', name: 'The Barracks', maxLevel: 5,
    cost: (lv) => ({ bronze: 50 + lv * 40, provisions: 30 + lv * 25 }),
    yield: () => null, effect: (lv) => `+${lv * 8}% strength`,
  },
  {
    id: 'cistern', name: 'The Cistern', maxLevel: 5,
    cost: (lv) => ({ timber: 30 + lv * 25, provisions: 25 + lv * 20 }),
    yield: () => null, effect: (lv) => `+${lv * 9}% breath`,
  },
  {
    id: 'wall', name: 'The Wall', maxLevel: 5,
    cost: (lv) => ({ timber: 45 + lv * 30, bronze: 25 + lv * 22 }),
    yield: () => null, effect: (lv) => `+${lv * 10}% vitality`,
  },
  {
    id: 'threshing', name: 'The Threshing Floor', maxLevel: 5,
    cost: (lv) => ({ timber: 35 + lv * 28, provisions: 20 + lv * 18 }),
    yield: (lv) => ({ provisions: lv * 24 }), effect: (lv) => `+${lv * 24} provisions an hour`,
  },
  {
    id: 'quarry', name: 'The Quarry', maxLevel: 5,
    cost: (lv) => ({ timber: 30 + lv * 24, provisions: 22 + lv * 16 }),
    yield: (lv) => ({ timber: lv * 18 }), effect: (lv) => `+${lv * 18} timber an hour`,
  },
  {
    id: 'smelter', name: 'The Smelter', maxLevel: 5,
    cost: (lv) => ({ timber: 40 + lv * 30, bronze: 18 + lv * 20 }),
    yield: (lv) => ({ bronze: lv * 14 }), effect: (lv) => `+${lv * 14} bronze an hour`,
  },
  {
    id: 'hospital', name: 'The Tent of the Wounded', maxLevel: 5,
    cost: (lv) => ({ provisions: 40 + lv * 30, timber: 25 + lv * 20 }),
    yield: () => null, effect: (lv) => `-${(lv - 1) * 8}% to the tending of the wounded`,
  },
]);

export const WORK_IDS = Object.freeze(WORKS.map((w) => w.id));

/* ------------------------------------------------------------------- the band */

/** David's mighty men (2 Samuel 23). */
export const ROSTER = Object.freeze([
  { id: 'david', name: 'David', title: 'the son of Jesse', role: 'blade', power: 12, unlocked: true },
  { id: 'abishai', name: 'Abishai', title: 'brother of Joab', role: 'blade', power: 9, unlocked: true },
  { id: 'eleazar', name: 'Eleazar', title: 'son of Dodo', role: 'spear', power: 8, unlockAt: 2 },
  { id: 'shammah', name: 'Shammah', title: 'son of Agee', role: 'bow', power: 7, unlockAt: 3 },
  { id: 'benaiah', name: 'Benaiah', title: 'son of Jehoiada', role: 'spear', power: 10, unlockAt: 5 },
  { id: 'ittai', name: 'Ittai', title: 'the Gittite', role: 'bow', power: 8, unlockAt: 7 },
]);

/** One skill per mighty man, in the register of 2 Samuel 23. */
export const SKILLS = Object.freeze({
  david: { name: 'Strengthen the band', kind: 'heal', power: 0.28, charge: 20 },
  abishai: { name: 'Rally the line', kind: 'rally', power: 0.55, charge: 24 },
  eleazar: { name: 'Stand fast', kind: 'shield', power: 0.3, charge: 26 },
  shammah: { name: 'Arrow storm', kind: 'burst', power: 2.6, charge: 22 },
  benaiah: { name: "The lion's blow", kind: 'burst', power: 3.4, charge: 28 },
  ittai: { name: 'Hold the ford', kind: 'weaken', power: 0.3, charge: 26 },
});

/** At most this many mighty men stand in a single fight. */
export const FIELD_HEROES = 4;

/* ---------------------------------------------------------------- the companies */

/** The companies of the six hundred (1 Samuel 30:9–10). */
export const COMPANIES = Object.freeze([
  {
    id: 'spears', name: 'The Iron Spears', line: 'front',
    atk: 9, def: 7, hp: 14, max: 70,
    cost: (n) => ({ bronze: 26 * n, provisions: 12 * n }),
    note: 'The old weapon of the hill country. They hold the first line.',
  },
  {
    id: 'shields', name: 'The Shield Wall', line: 'front',
    atk: 5, def: 13, hp: 20, max: 60,
    cost: (n) => ({ bronze: 30 * n, timber: 14 * n }),
    note: 'Hide over wicker, shoulder to shoulder. They take the blow.',
  },
  {
    id: 'bows', name: 'The Bowmen', line: 'back',
    atk: 12, def: 3, hp: 9, max: 60,
    cost: (n) => ({ bronze: 22 * n, timber: 18 * n }),
    note: "Judah's bow. They loose over the wall of shields.",
  },
  {
    id: 'slings', name: 'The Slingers', line: 'back',
    atk: 8, def: 2, hp: 8, max: 50,
    cost: (n) => ({ provisions: 16 * n, timber: 9 * n }),
    note: 'A stone and a strip of leather. Cheap, and they never miss twice.',
  },
  {
    id: 'scouts', name: 'The Ranging Scouts', line: 'back',
    atk: 6, def: 5, hp: 11, max: 40,
    cost: (n) => ({ provisions: 20 * n, bronze: 12 * n }),
    note: 'They find the track and count the enemy before the horns sound.',
  },
]);

export const COMPANY_IDS = Object.freeze(COMPANIES.map((c) => c.id));

/** Men per purchase step. One press musters ten. */
export const STEP = 10;

/** Tiers are gated by the Barracks. */
export const MAX_TIER = 5;

/* ------------------------------------------------------------------- the fight */

/** The skirmishes. Waves are fought in order; the last wave decides it. */
export const SKIRMISHES = Object.freeze([
  {
    id: 'besor-watch', name: 'The Watch at Besor', power: 60,
    rewards: { provisions: 45, timber: 20 }, renown: 8,
    waves: [[{ key: 'riders', name: 'Amalekite riders', count: 6, hp: 13, atk: 5, def: 2 }]],
  },
  {
    id: 'ford', name: 'The Ford by Night', power: 120,
    rewards: { provisions: 55, bronze: 35 }, renown: 12,
    waves: [
      [{ key: 'raiders', name: 'Amalekite raiders', count: 8, hp: 14, atk: 6, def: 3 }],
      [{ key: 'raiders', name: 'Amalekite raiders', count: 6, hp: 16, atk: 7, def: 3 },
        { key: 'archers', name: 'Desert archers', count: 4, hp: 10, atk: 9, def: 1 }],
    ],
  },
  {
    id: 'outpost', name: 'The Outpost on the Escarpment', power: 200,
    rewards: { timber: 60, bronze: 50 }, renown: 16,
    waves: [
      [{ key: 'spears', name: 'Amalekite spears', count: 10, hp: 16, atk: 7, def: 5 },
        { key: 'archers', name: 'Desert archers', count: 5, hp: 11, atk: 9, def: 2 }],
      [{ key: 'captain', name: 'The captain of the watch', count: 1, hp: 140, atk: 18, def: 10 },
        { key: 'spears', name: 'Amalekite spears', count: 8, hp: 16, atk: 7, def: 5 }],
    ],
  },
  {
    id: 'perimeter', name: 'The Camp Perimeter', power: 300,
    rewards: { spoil: 90, bronze: 55 }, renown: 22,
    waves: [
      [{ key: 'revellers', name: 'Revellers with blades', count: 14, hp: 14, atk: 6, def: 2 }],
      [{ key: 'guards', name: 'Camp guards', count: 12, hp: 18, atk: 8, def: 5 },
        { key: 'archers', name: 'Desert archers', count: 6, hp: 12, atk: 10, def: 2 }],
      [{ key: 'champion', name: 'The champion of the camp', count: 1, hp: 240, atk: 24, def: 14 },
        { key: 'guards', name: 'Camp guards', count: 10, hp: 18, atk: 8, def: 5 }],
    ],
  },
  {
    id: 'long-night', name: 'The Long Night', power: 430,
    rewards: { spoil: 130, bronze: 80 }, renown: 30,
    waves: [
      [{ key: 'raiders', name: 'Amalekite raiders', count: 16, hp: 18, atk: 9, def: 4 }],
      [{ key: 'spears', name: 'Amalekite spears', count: 14, hp: 20, atk: 10, def: 6 },
        { key: 'archers', name: 'Desert archers', count: 8, hp: 14, atk: 12, def: 3 }],
      [{ key: 'champion', name: 'The prince of Amalek', count: 1, hp: 320, atk: 28, def: 18 },
        { key: 'guards', name: 'Sworn guards', count: 14, hp: 22, atk: 11, def: 7 }],
    ],
  },
  {
    id: 'gilboa-slope', name: 'The Slope of Gilboa', power: 560,
    rewards: { spoil: 160, bronze: 100, timber: 40 }, renown: 40,
    waves: [
      [{ key: 'chariots', name: 'Chariot runners', count: 12, hp: 26, atk: 13, def: 6 },
        { key: 'archers', name: 'Philistine archers', count: 10, hp: 15, atk: 13, def: 3 }],
      [{ key: 'chariots', name: 'Chariot runners', count: 14, hp: 28, atk: 14, def: 7 },
        { key: 'spears', name: 'Philistine spears', count: 12, hp: 24, atk: 12, def: 8 }],
      [{ key: 'prince', name: 'A Philistine prince', count: 1, hp: 420, atk: 32, def: 22 },
        { key: 'guards', name: 'Sworn guards', count: 16, hp: 24, atk: 12, def: 8 }],
    ],
  },
]);

export const SKIRMISH_IDS = Object.freeze(SKIRMISHES.map((s) => s.id));

/* --------------------------------------------------------------- the charge */

/** The charge: one task at a time, in the order the scripture gives them. */
export const TASKS = Object.freeze([
  { id: 'ashes', title: 'Walk the ashes of Ziklag', phase: 'STATE_0_ZIKLAG', verse: '1 Samuel 30:3',
    reward: { provisions: 40, timber: 25 } },
  { id: 'besor', title: 'Cross the brook Besor', phase: 'STATE_1_DESERT_MARCH', verse: '1 Samuel 30:10',
    reward: { provisions: 35, bronze: 20 } },
  { id: 'servant', title: 'Give the Egyptian bread and water', phase: 'STATE_2_EGYPTIAN_SERVANT', verse: '1 Samuel 30:11–15',
    reward: { provisions: 50, bronze: 25 } },
  { id: 'rescue', title: 'Recover all that was taken', phase: 'STATE_3_AMALEKITE_RESCUE', verse: '1 Samuel 30:18–19',
    reward: { spoil: 60, bronze: 40 } },
  { id: 'divide', title: 'Divide the spoil at the brook', phase: 'STATE_4_SPOILS_DIVISION', verse: '1 Samuel 30:24',
    reward: { spoil: 45, timber: 30 } },
  { id: 'judah', title: 'Send gifts to the elders of Judah', phase: 'STATE_5_DISTRIBUTION', verse: '1 Samuel 30:26–31',
    reward: { spoil: 70, provisions: 40 } },
  { id: 'gilboa', title: 'Stand at Gilboa', phase: 'STATE_6_GILBOA_EPILOGUE', verse: '1 Samuel 31',
    reward: { spoil: 90, bronze: 50 } },
]);

export const PHASE_ORDER = Object.freeze([
  'STATE_0_ZIKLAG', 'STATE_1_DESERT_MARCH', 'STATE_2_EGYPTIAN_SERVANT',
  'STATE_3_AMALEKITE_RESCUE', 'STATE_4_SPOILS_DIVISION', 'STATE_5_DISTRIBUTION',
  'STATE_6_GILBOA_EPILOGUE',
]);

/* ------------------------------------------------------------------ constants */

/**
 * The reference's constants, renamed and given our numbers. The names are
 * kept in the comment because the decompiled code refers to them by name.
 */
export const CONST = Object.freeze({
  /** RECRUITMENT_COST — the price of a press of ten men, per company. */
  RECRUITMENT_COST: 'per company, per press of ten',
  /** FARMERS_WORLD_HANDLING_FEE — the cut taken when the stores are emptied. */
  HANDLING_FEE: 0,
  /** UNRECRUITED_FARMER — the part of a company that is not yet under arms. */
  UNMUSTERED_MAN: 0,
  /** RECRUITMENT_STEP — men per press. */
  RECRUITMENT_STEP: STEP,
  /** OFFLINE_CAP_HOURS — the most that can be gathered while away. */
  OFFLINE_CAP_HOURS: 8,
  /** PRODUCTION_MINUTES — minutes for one slot of a work. */
  PRODUCTION_MINUTES: 60,
  /** PRODUCTION_NUM_MAX — slots that can be queued at once. */
  PRODUCTION_NUM_MAX: 8,
  /** PRODUCTION_SPEND — the cost of one slot, in provisions. */
  PRODUCTION_SPEND: 10,
});

/**
 * The season boon — our stand-in for the reference's battle-pass accelerator
 * (`REFINED_SANDS_PRODUCTION_BP_SPEED_UP`).
 *
 * The reference's arithmetic, which we copy exactly:
 *   - a boon makes the work finish sooner, and
 *   - it costs more per slot for the privilege.
 * The duration saved is what the player actually buys.
 */
export const BOON = Object.freeze({
  id: 'covenant',
  name: 'The Covenant Boon',
  speedUpPercent: 20,
  costPremiumPercent: 20,
  season: 10,
});

/** The `*_S10` const-override pattern: season 10 retunes the tables. */
export const SEASON_OVERRIDES = Object.freeze({
  10: { PRODUCTION_MINUTES: 60, PRODUCTION_SPEND: 10 },
});

/** The exchange table — the reference's `exchangecfg`. */
export const EXCHANGE = Object.freeze([
  { from: 'provisions', to: 'bronze', rate: 1, note: 'Bread for coin, at the gate.' },
  { from: 'bronze', to: 'provisions', rate: 2, note: 'Coin for bread, at the gate.' },
  { from: 'timber', to: 'bronze', rate: 1, note: 'Beams for spearheads.' },
  { from: 'spoil', to: 'provisions', rate: 3, note: 'The prince’s portion, spent on bread.' },
]);

/* ------------------------------------------------------------------- helpers */

/** A fresh set of stores, in the reference's starting position. */
export function freshResources() {
  return { provisions: 180, bronze: 90, timber: 60, spoil: 0 };
}

/** Fresh levels for every work. */
export function freshWorks() {
  return Object.fromEntries(WORKS.map((w) => [w.id, 1]));
}

/** Fresh companies — an empty camp until men are mustered. */
export function freshCompanies() {
  return Object.fromEntries(COMPANIES.map((c) => [c.id, { count: 0, tier: 1, wounded: 0 }]));
}

/** The minutes for one slot, with the season boon applied. */
export function slotMinutes(boonActive = false) {
  const base = CONST.PRODUCTION_MINUTES;
  if (!boonActive) return base;
  return Math.max(1, Math.round(base / (1 + BOON.speedUpPercent / 100)));
}

/** The cost of one slot, with the season boon applied. */
export function slotCost(boonActive = false) {
  const base = CONST.PRODUCTION_SPEND;
  if (!boonActive) return base;
  return Math.round(base * (1 + BOON.costPremiumPercent / 100));
}
