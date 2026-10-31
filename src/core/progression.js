/**
 * progression.js — the long arc, persisted.
 *
 * The game's run is finite (one pursuit, seven phases). A strategy game's arc
 * is not: it is a campaign you return to. Pass 13 adds that layer, drawn from
 * the structure of the reference strategy game (Land of Empires: Immortal) and
 * rebuilt on our own content:
 *
 *   reference pattern                  →  PURSUE, in 1 Samuel 30
 *   city that survives the run         →  the rebuilding of Ziklag
 *   hero roster with classes           →  David's mighty men (2 Sam 23)
 *   barracks tiers                     →  the band of 600 (1 Sam 30:9–10)
 *   chapter task chain                 →  the charge: what must be done next
 *   stars per stage                    →  a remembered phase (time, silence, stealth)
 *   resources from battles             →  provisions, bronze, timber, spoil
 *
 * Everything lives here, in one persisted object, so the War Table UI, the HUD
 * tracker and the game rules all read the same truth. Storage is optional:
 * without localStorage the campaign simply does not survive the tab.
 */

const KEY = 'pursue.progress.v1';

/* ------------------------------------------------------------------ data */

/** Resources, in the order the War Table lists them. */
export const RESOURCES = Object.freeze([
  { id: 'provisions', name: 'Provisions', glyph: '◍', note: 'bread, water, oil' },
  { id: 'bronze', name: 'Bronze', glyph: '◈', note: 'blades, spearheads' },
  { id: 'timber', name: 'Timber', glyph: '⌶', note: 'beams and stakes' },
  { id: 'spoil', name: 'Spoil', glyph: '✦', note: 'recovered from Amalek' },
]);

/** The settlement. Each level is a real modifier, not decoration. */
export const BUILDINGS = Object.freeze([
  {
    id: 'hall',
    name: 'The Hall of the March',
    note: 'Where the charge is kept. Raises renown from every phase.',
    maxLevel: 5,
    cost: (lv) => ({ timber: 40 + lv * 35, bronze: 20 + lv * 20 }),
    effect: (lv) => `+${lv * 10}% renown`,
  },
  {
    id: 'barracks',
    name: 'The Barracks',
    note: 'Drills the six hundred. Raises the strength of every blow.',
    maxLevel: 5,
    cost: (lv) => ({ bronze: 50 + lv * 40, provisions: 30 + lv * 25 }),
    effect: (lv) => `+${lv * 8}% strength`,
  },
  {
    id: 'cistern',
    name: 'The Cistern',
    note: 'Water for the march. The breath returns sooner.',
    maxLevel: 5,
    cost: (lv) => ({ timber: 30 + lv * 25, provisions: 25 + lv * 20 }),
    effect: (lv) => `+${lv * 9}% breath`,
  },
  {
    id: 'wall',
    name: 'The Wall',
    note: 'Ziklag burned once. Raises the lamp of vitality.',
    maxLevel: 5,
    cost: (lv) => ({ timber: 45 + lv * 30, bronze: 25 + lv * 22 }),
    effect: (lv) => `+${lv * 10}% vitality`,
  },
  {
    id: 'threshing',
    name: 'The Threshing Floor',
    note: 'Barley and wheat, winnowed at evening. Bread while the band is away.',
    maxLevel: 5,
    cost: (lv) => ({ timber: 35 + lv * 28, provisions: 20 + lv * 18 }),
    effect: (lv) => `+${lv * 24} provisions an hour`,
    yield: (lv) => ({ provisions: lv * 24 }),
  },
  {
    id: 'quarry',
    name: 'The Quarry',
    note: 'Beams and stakes cut from the wadi. Nothing is raised without timber.',
    maxLevel: 5,
    cost: (lv) => ({ timber: 30 + lv * 24, provisions: 22 + lv * 16 }),
    effect: (lv) => `+${lv * 18} timber an hour`,
    yield: (lv) => ({ timber: lv * 18 }),
  },
  {
    id: 'smelter',
    name: 'The Smelter',
    note: 'Bronze poured from the spoil of the hills, into spearheads and bosses.',
    maxLevel: 5,
    cost: (lv) => ({ timber: 40 + lv * 30, bronze: 18 + lv * 20 }),
    effect: (lv) => `+${lv * 14} bronze an hour`,
    yield: (lv) => ({ bronze: lv * 14 }),
  },
  {
    id: 'hospital',
    name: 'The Tent of the Wounded',
    note: 'Where the hurt are carried after a skirmish. Tending costs less bread.',
    maxLevel: 5,
    cost: (lv) => ({ provisions: 40 + lv * 30, timber: 25 + lv * 20 }),
    effect: (lv) => `-${(lv - 1) * 8}% to the tending of the wounded`,
  },
]);

/** The band — David's mighty men, each with a role the fight understands. */
export const ROSTER = Object.freeze([
  {
    id: 'david', name: 'David', title: 'the son of Jesse', role: 'blade',
    power: 12, verse: '1 Samuel 30:6 — “David strengthened himself in the LORD his God.”',
    unlocked: true,
  },
  {
    id: 'abishai', name: 'Abishai', title: 'brother of Joab', role: 'blade',
    power: 9, verse: '2 Samuel 23:18 — “chief among the thirty.”',
    unlocked: true,
  },
  {
    id: 'eleazar', name: 'Eleazar', title: 'son of Dodo', role: 'spear',
    power: 8, verse: '2 Samuel 23:10 — “he arose and smote the Philistines until his hand was weary.”',
    unlocked: false, unlockAt: 2,
  },
  {
    id: 'shammah', name: 'Shammah', title: 'son of Agee', role: 'bow',
    power: 7, verse: '2 Samuel 23:11 — “he stood in the midst of the ground and defended it.”',
    unlocked: false, unlockAt: 3,
  },
  {
    id: 'benaiah', name: 'Benaiah', title: 'son of Jehoiada', role: 'spear',
    power: 10, verse: '2 Samuel 23:20 — “he slew two lionlike men of Moab.”',
    unlocked: false, unlockAt: 5,
  },
  {
    id: 'ittai', name: 'Ittai', title: 'the Gittite', role: 'bow',
    power: 8, verse: '2 Samuel 15:22 — “Come and pass over with me.”',
    unlocked: false, unlockAt: 7,
  },
]);

/**
 * The charge: one task at a time, in the order the scripture gives them.
 * `phase` is where it can be completed; `reward` is what the band carries away.
 */
export const TASKS = Object.freeze([
  {
    id: 'ashes', title: 'Walk the ashes of Ziklag', phase: 'STATE_0_ZIKLAG',
    detail: 'Cross the ruined town to the Desert Edge.',
    verse: '1 Samuel 30:3', reward: { provisions: 40, timber: 25 },
  },
  {
    id: 'besor', title: 'Cross the brook Besor', phase: 'STATE_1_DESERT_MARCH',
    detail: 'The two hundred who were too faint stay with the baggage.',
    verse: '1 Samuel 30:10', reward: { provisions: 35, bronze: 20 },
  },
  {
    id: 'servant', title: 'Give the Egyptian bread and water', phase: 'STATE_2_EGYPTIAN_SERVANT',
    detail: 'Three days without food or drink, and he will lead you down.',
    verse: '1 Samuel 30:11–15', reward: { provisions: 50, bronze: 25 },
  },
  {
    id: 'rescue', title: 'Recover all that was taken', phase: 'STATE_3_AMALEKITE_RESCUE',
    detail: 'Wives, sons, daughters, flocks and herds — nothing missing.',
    verse: '1 Samuel 30:18–19', reward: { spoil: 60, bronze: 40 },
  },
  {
    id: 'divide', title: 'Divide the spoil at the brook', phase: 'STATE_4_SPOILS_DIVISION',
    detail: 'As his part is that goes down to battle, so is his part that stays by the stuff.',
    verse: '1 Samuel 30:24', reward: { spoil: 45, timber: 30 },
  },
  {
    id: 'judah', title: 'Send gifts to the elders of Judah', phase: 'STATE_5_DISTRIBUTION',
    detail: 'To Hebron, and to every place where David and his men had walked.',
    verse: '1 Samuel 30:26–31', reward: { spoil: 70, provisions: 40 },
  },
  {
    id: 'gilboa', title: 'Stand at Gilboa', phase: 'STATE_6_GILBOA_EPILOGUE',
    detail: 'The pursuit ends where the kingdom falls.',
    verse: '1 Samuel 31', reward: { spoil: 90, bronze: 50 },
  },
]);

export const PHASE_ORDER = Object.freeze([
  'STATE_0_ZIKLAG', 'STATE_1_DESERT_MARCH', 'STATE_2_EGYPTIAN_SERVANT',
  'STATE_3_AMALEKITE_RESCUE', 'STATE_4_SPOILS_DIVISION', 'STATE_5_DISTRIBUTION',
  'STATE_6_GILBOA_EPILOGUE',
]);

export const PHASE_NAMES = Object.freeze({
  STATE_0_ZIKLAG: 'The Ashes of Ziklag',
  STATE_1_DESERT_MARCH: 'The Brook Besor',
  STATE_2_EGYPTIAN_SERVANT: 'The Egyptian Servant',
  STATE_3_AMALEKITE_RESCUE: 'The Camp at Night',
  STATE_4_SPOILS_DIVISION: 'The Dividing of the Spoil',
  STATE_5_DISTRIBUTION: 'The Gifts to Judah',
  STATE_6_GILBOA_EPILOGUE: 'The Fall of Saul',
});

/* ----------------------------------------------------------------- state */

function fresh() {
  return {
    version: 1,
    resources: { provisions: 180, bronze: 90, timber: 60, spoil: 0 },
    buildings: Object.fromEntries(BUILDINGS.map((b) => [b.id, 1])),
    roster: Object.fromEntries(ROSTER.map((m) => [m.id, { level: 1, seen: false }])),
    stars: {},          // phase → 0..3 (best)
    tasksDone: [],      // ids completed at least once
    tasksClaimed: [],   // ids whose reward was taken
    renown: 0,
    runs: 0,
    lastPlayed: null,
    lastCollect: Date.now(),
  };
}

let state = fresh();
const listeners = new Set();

function canStore() {
  try {
    return typeof localStorage !== 'undefined' && localStorage !== null;
  } catch (_e) {
    return false;
  }
}

function merge(saved) {
  const base = fresh();
  if (!saved || typeof saved !== 'object') return base;
  // merge shallow-safe: unknown keys are dropped, missing keys keep defaults
  base.resources = { ...base.resources, ...(saved.resources || {}) };
  base.buildings = { ...base.buildings, ...(saved.buildings || {}) };
  for (const m of ROSTER) {
    const s = saved.roster?.[m.id];
    if (s) base.roster[m.id] = { level: Math.max(1, Math.min(20, Number(s.level) || 1)), seen: Boolean(s.seen) };
  }
  if (saved.stars && typeof saved.stars === 'object') {
    for (const [k, v] of Object.entries(saved.stars)) {
      if (PHASE_ORDER.includes(k)) base.stars[k] = Math.max(0, Math.min(3, Number(v) || 0));
    }
  }
  base.tasksDone = Array.isArray(saved.tasksDone)
    ? saved.tasksDone.filter((id) => TASKS.some((t) => t.id === id)) : [];
  base.tasksClaimed = Array.isArray(saved.tasksClaimed)
    ? saved.tasksClaimed.filter((id) => TASKS.some((t) => t.id === id)) : [];
  base.renown = Math.max(0, Number(saved.renown) || 0);
  base.runs = Math.max(0, Number(saved.runs) || 0);
  base.lastPlayed = typeof saved.lastPlayed === 'string' ? saved.lastPlayed : null;
  base.lastCollect = Number.isFinite(Number(saved.lastCollect))
    ? Number(saved.lastCollect) : Date.now();
  return base;
}

function load() {
  if (!canStore()) return;
  try {
    const raw = localStorage.getItem(KEY);
    if (raw) state = merge(JSON.parse(raw));
  } catch (_e) {
    /* corrupt record: start the campaign again rather than crash */
  }
}

function persist() {
  if (!canStore()) return;
  try {
    state.lastPlayed = new Date().toISOString().slice(0, 10);
    localStorage.setItem(KEY, JSON.stringify(state));
  } catch (_e) { /* private mode: the campaign lives for one tab */ }
}

function notify(reason) {
  for (const fn of listeners) {
    try {
      fn(state, reason);
    } catch (_e) { /* a broken listener must not break the campaign */ }
  }
}

load();

/* ------------------------------------------------------------- derived */

/** Total band strength: heroes, their levels and the barracks that drilled them. */
export function bandPower(s = state) {
  let p = 0;
  for (const m of ROSTER) {
    const r = s.roster[m.id];
    if (!r || !isUnlocked(m.id, s)) continue;
    p += m.power * (1 + (r.level - 1) * 0.18);
  }
  return Math.round(p * (1 + s.buildings.barracks * 0.08));
}

/** Is a mighty man with the band yet? */
export function isUnlocked(id, s = state) {
  const m = ROSTER.find((x) => x.id === id);
  if (!m) return false;
  if (m.unlocked) return true;
  const stars = Object.values(s.stars).reduce((a, b) => a + b, 0);
  return stars >= (m.unlockAt || 99);
}

/**
 * Run modifiers, read once when the march begins.
 * These are the only numbers the city is allowed to change: nothing here
 * rewrites the phase design, it only tilts the player's margins.
 */
export function modifiers(s = state) {
  const b = s.buildings;
  return {
    maxHealth: 1 + b.wall * 0.10,
    staminaRegen: 1 + b.cistern * 0.09,
    strength: 1 + b.barracks * 0.08,
    renown: 1 + b.hall * 0.10,
    bandPower: bandPower(s),
  };
}

/** The hourly output of one work at its current level ({} if it makes nothing). */
export function hourlyYield(id, s = state) {
  const def = BUILDINGS.find((b) => b.id === id);
  if (!def || typeof def.yield !== 'function') return {};
  return def.yield(s.buildings[id] || 1);
}

/** Everything the settlement makes in an hour, summed. */
export function productionRate(s = state) {
  const out = {};
  for (const b of BUILDINGS) {
    if (typeof b.yield !== 'function') continue;
    for (const [k, v] of Object.entries(b.yield(s.buildings[b.id] || 1))) {
      out[k] = (out[k] || 0) + v;
    }
  }
  return out;
}

/**
 * What has been made since the last time the works were emptied.
 * Off the clock while the tab is closed — this is the reference game's idle
 * production, kept honest: eight hours is the most that can be gathered.
 * @param {number} [now] epoch millis (injected by the smoke suite)
 */
export function pendingProduction(s = state, now = Date.now()) {
  const CAP_MS = 8 * 3600 * 1000;
  const since = Math.max(0, Math.min(CAP_MS, now - (s.lastCollect || now)));
  const hours = since / 3600000;
  const out = {};
  for (const [k, v] of Object.entries(productionRate(s))) {
    const made = Math.floor(v * hours);
    if (made > 0) out[k] = made;
  }
  return { resources: out, hours, full: since >= CAP_MS };
}

/** Cost to raise a building one level, or null at its ceiling. */
export function upgradeCost(id, s = state) {
  const def = BUILDINGS.find((b) => b.id === id);
  if (!def) return null;
  const lv = s.buildings[id] || 1;
  if (lv >= def.maxLevel) return null;
  return def.cost(lv);
}

/** Cost to drill a mighty man one level. */
export function drillCost(id, s = state) {
  const r = s.roster[id];
  if (!r) return null;
  return { bronze: 25 + r.level * 18, provisions: 15 + r.level * 12 };
}

/** The task the player is on: first unclaimed, in scripture order. */
export function currentTask(s = state) {
  return TASKS.find((t) => !s.tasksDone.includes(t.id))
    || TASKS.find((t) => !s.tasksClaimed.includes(t.id))
    || null;
}

/** Stars earned across the campaign (max 21). */
export function totalStars(s = state) {
  return Object.values(s.stars).reduce((a, b) => a + b, 0);
}

/** Reward a phase is worth at a given star rating (1..3). */
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

/* ------------------------------------------------------------------ api */

export const progression = {
  get() {
    return state;
  },

  onChange(fn) {
    listeners.add(fn);
    return () => listeners.delete(fn);
  },

  /**
   * Remember a finished phase.
   * @param {string} phase
   * @param {{time?: number, alerts?: number, stealth?: boolean}} run
   * @returns {{stars: number, reward: object, best: boolean}}
   */
  recordPhase(phase, run = {}) {
    if (!PHASE_ORDER.includes(phase)) {
      return { stars: 0, reward: {}, best: false };
    }
    // A phase is a stage: swift, silent and unseen each carry a star.
    const fast = (run.time ?? 999) <= 150;
    const silent = (run.alerts ?? 99) === 0;
    const stealth = Boolean(run.stealth);
    let stars = 1 + (fast ? 1 : 0) + ((silent || stealth) ? 1 : 0);
    stars = Math.max(1, Math.min(3, stars));

    const best = stars > (state.stars[phase] || 0);
    if (best) state.stars[phase] = stars;

    const reward = phaseReward(phase, stars);
    for (const [k, v] of Object.entries(reward)) {
      state.resources[k] = (state.resources[k] || 0) + v;
    }
    state.renown += Math.round(stars * 10 * modifiers().renown);
    persist();
    notify('phase');
    return { stars, reward, best };
  },

  /** Mark a charge complete (once). Returns true if this call completed it. */
  completeTask(id) {
    const task = TASKS.find((t) => t.id === id);
    if (!task || state.tasksDone.includes(id)) return false;
    state.tasksDone.push(id);
    persist();
    notify('task');
    return true;
  },

  /** Take a completed task's reward. */
  claimTask(id) {
    if (!state.tasksDone.includes(id) || state.tasksClaimed.includes(id)) return null;
    const task = TASKS.find((t) => t.id === id);
    if (!task) return null;
    for (const [k, v] of Object.entries(task.reward || {})) {
      state.resources[k] = (state.resources[k] || 0) + v;
    }
    state.tasksClaimed.push(id);
    persist();
    notify('claim');
    return task.reward;
  },

  /** Add to the stores directly (skirmish pay, gifts, found spoil). */
  grant(res) {
    const given = {};
    for (const [k, v] of Object.entries(res || {})) {
      if (!(v > 0)) continue;
      state.resources[k] = (state.resources[k] || 0) + Math.round(v);
      given[k] = Math.round(v);
    }
    persist();
    if (Object.keys(given).length) notify('grant');
    return given;
  },

  /** Renown from a won skirmish. */
  addRenown(n) {
    const gained = Math.round((Number(n) || 0) * modifiers().renown);
    if (gained) {
      state.renown += gained;
      persist();
      notify('renown');
    }
    return gained;
  },

  /** Empty the works. Returns what was gathered. */
  collect(now = Date.now()) {
    const { resources } = pendingProduction(state, now);
    const given = {};
    for (const [k, v] of Object.entries(resources)) {
      if (!(v > 0)) continue;
      state.resources[k] = (state.resources[k] || 0) + v;
      given[k] = v;
    }
    state.lastCollect = now;
    persist();
    notify('collect');
    return given;
  },

  /** Spend resources if the band can afford it. */
  spend(cost) {
    for (const [k, v] of Object.entries(cost || {})) {
      if ((state.resources[k] || 0) < v) return false;
    }
    for (const [k, v] of Object.entries(cost || {})) {
      state.resources[k] -= v;
    }
    return true;
  },

  /** Raise a building. Returns the new level, or null if it cannot be done. */
  upgrade(id) {
    const cost = upgradeCost(id);
    if (!cost || !this.spend(cost)) return null;
    state.buildings[id] = (state.buildings[id] || 1) + 1;
    persist();
    notify('build');
    return state.buildings[id];
  },

  /** Drill a mighty man. Returns the new level, or null. */
  drill(id) {
    if (!isUnlocked(id)) return null;
    const cost = drillCost(id);
    if (!cost || !this.spend(cost)) return null;
    state.roster[id].level = Math.min(20, (state.roster[id].level || 1) + 1);
    persist();
    notify('drill');
    return state.roster[id].level;
  },

  /** Count a run begun (used by the War Table's ledger). */
  beginRun() {
    state.runs += 1;
    persist();
    notify('run');
  },

  reset() {
    state = fresh();
    persist();
    notify('reset');
    return state;
  },
};

export const PROGRESS_DEFAULTS = fresh();
