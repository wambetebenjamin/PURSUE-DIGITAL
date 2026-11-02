/**
 * muster.js — the companies of the six hundred, persisted.
 *
 * The reference game runs its fights with troops raised in barracks and drawn
 * up in lines, gated by the barracks' level. Ours are the band of 1 Samuel 30
 * — the men who went down after the Amalekites — so the companies are kept in
 * the wilderness, not in a barracks yard:
 *
 *   reference pattern            →  PURSUE, in 1 Samuel 30
 *   troop tiers raised in barracks → companies trained by the Barracks level
 *   front line / back line       →  spears and shields forward, bows and
 *                                   slings behind, scouts ranging
 *   wounded recovered in hospital → the wounded tended for provisions
 *
 * Every company carries `count` men, a `tier` (1–5), and a record of `wounded`
 * who can be brought back to the line. Nothing here is decoration: the
 * skirmish engine reads these numbers directly.
 */

const KEY = 'pursue.muster.v1';

/* ------------------------------------------------------------------ data */

/**
 * Companies. `line` decides where they stand; `atk`/`def`/`hp` are per man.
 * `max` is the ceiling at barracks level 5 (see `companyCap`).
 */
export const COMPANIES = Object.freeze([
  {
    id: 'spears', name: 'The Iron Spears', glyph: '⌇', line: 'front',
    atk: 9, def: 7, hp: 14, max: 70,
    note: 'The old weapon of the hill country. They hold the first line.',
    cost: (n) => ({ bronze: 26 * n, provisions: 12 * n }),
  },
  {
    id: 'shields', name: 'The Shield Wall', glyph: '▣', line: 'front',
    atk: 5, def: 13, hp: 20, max: 60,
    note: 'Hide over wicker, shoulder to shoulder. They take the blow.',
    cost: (n) => ({ bronze: 30 * n, timber: 14 * n }),
  },
  {
    id: 'bows', name: 'The Bowmen', glyph: '⟋', line: 'back',
    atk: 12, def: 3, hp: 9, max: 60,
    note: 'Judah’s bow. They loose over the wall of shields.',
    cost: (n) => ({ bronze: 22 * n, timber: 18 * n }),
  },
  {
    id: 'slings', name: 'The Slingers', glyph: '∘', line: 'back',
    atk: 8, def: 2, hp: 8, max: 50,
    note: 'A stone and a strip of leather. Cheap, and they never miss twice.',
    cost: (n) => ({ provisions: 16 * n, timber: 9 * n }),
  },
  {
    id: 'scouts', name: 'The Ranging Scouts', glyph: '⌖', line: 'back',
    atk: 6, def: 5, hp: 11, max: 40,
    note: 'They find the track and count the enemy before the horns sound.',
    cost: (n) => ({ provisions: 20 * n, bronze: 12 * n }),
  },
]);

/** Men per purchase step. One press musters ten. */
export const STEP = 10;

/** Tiers are gated by the Barracks — a rabble cannot be drilled into veterans. */
export const MAX_TIER = 5;

/** What it costs to take a company up one tier. */
export function trainCost(id, s = state) {
  const c = s.companies[id];
  if (!c) return null;
  if (c.tier >= MAX_TIER) return null;
  const t = c.tier;
  return { bronze: 60 + t * 45, provisions: 40 + t * 30, timber: 20 + t * 18 };
}

/* ----------------------------------------------------------------- state */

function fresh() {
  return {
    version: 1,
    companies: Object.fromEntries(COMPANIES.map((c) => [c.id, { count: 0, tier: 1, wounded: 0 }])),
    wins: {},        // skirmish id → times won
    battles: 0,
    lost: 0,
    lastAction: null,
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
  for (const c of COMPANIES) {
    const row = saved.companies?.[c.id];
    if (!row) continue;
    base.companies[c.id] = {
      count: Math.max(0, Math.min(c.max, Number(row.count) || 0)),
      tier: Math.max(1, Math.min(MAX_TIER, Number(row.tier) || 1)),
      wounded: Math.max(0, Math.min(c.max, Number(row.wounded) || 0)),
    };
  }
  if (saved.wins && typeof saved.wins === 'object') {
    for (const [k, v] of Object.entries(saved.wins)) {
      if (Number(v) > 0) base.wins[k] = Math.floor(Number(v));
    }
  }
  base.battles = Math.max(0, Number(saved.battles) || 0);
  base.lost = Math.max(0, Number(saved.lost) || 0);
  return base;
}

function load() {
  if (!canStore()) return;
  try {
    const raw = localStorage.getItem(KEY);
    if (raw) state = merge(JSON.parse(raw));
  } catch (_e) { /* a corrupt ledger starts the muster again */ }
}

function persist() {
  if (!canStore()) return;
  try {
    localStorage.setItem(KEY, JSON.stringify(state));
  } catch (_e) { /* private mode: the muster lives for one tab */ }
}

function notify(reason) {
  for (const fn of listeners) {
    try {
      fn(state, reason);
    } catch (_e) { /* one broken listener must not stop the muster */ }
  }
}

load();

/* --------------------------------------------------------------- derived */

/**
 * The ceiling on a company at a given barracks level.
 * Barracks 1 keeps a tenth of the company; barracks 5 keeps it full.
 */
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

/** Aggregate combat figures for one company — what the skirmish reads. */
export function companyStats(id, s = state, barracksLevel = 1) {
  const def = COMPANIES.find((x) => x.id === id);
  const row = s.companies[id];
  if (!def || !row) return null;
  const scale = tierScale(row.tier);
  return {
    id,
    name: def.name,
    glyph: def.glyph,
    line: def.line,
    count: row.count,
    tier: row.tier,
    maxHp: Math.round(row.count * def.hp * scale),
    atk: Math.round(row.count * def.atk * scale),
    def: Math.round(row.count * def.def * scale),
    cap: companyCap(id, barracksLevel),
  };
}

/** The whole order of battle, front line first. */
export function orderOfBattle(s = state, barracksLevel = 1) {
  const all = COMPANIES.map((c) => companyStats(c.id, s, barracksLevel)).filter((c) => c && c.count > 0);
  return all.sort((a, b) => (a.line === b.line ? 0 : a.line === 'front' ? -1 : 1));
}

/** Men under arms, and the strength the enemy reads from the ridge. */
export function musterPower(s = state, barracksLevel = 1) {
  let power = 0;
  let men = 0;
  for (const c of COMPANIES) {
    const row = s.companies[c.id];
    if (!row || row.count <= 0) continue;
    men += row.count;
    power += row.count * (c.atk + c.def) * tierScale(row.tier);
  }
  // the barracks drills them: every level past the first adds to the line
  const drill = 1 + (Math.max(1, Math.min(5, barracksLevel)) - 1) * 0.06;
  return { power: Math.round((power / 10) * drill), men };
}

/** Wounded waiting to be brought back to the line. */
export function totalWounded(s = state) {
  return COMPANIES.reduce((n, c) => n + (s.companies[c.id]?.wounded || 0), 0);
}

/* ------------------------------------------------------------------ api */

export const muster = {
  get() {
    return state;
  },

  onChange(fn) {
    listeners.add(fn);
    return () => listeners.delete(fn);
  },

  /**
   * Muster men into a company.
   * @param {string} id company
   * @param {number} n how many steps (default 1 = ten men)
   * @param {{barracksLevel?: number, spend?: (cost:object)=>boolean}} opts
   *   `spend` is the treasury (the progression store); without it the muster
   *   cannot pay and the call is a no-op.
   * @returns {number} the new count, or 0 if it could not be done
   */
  recruit(id, n = 1, opts = {}) {
    const def = COMPANIES.find((c) => c.id === id);
    const row = state.companies[id];
    if (!def || !row) return 0;
    const cap = companyCap(id, opts.barracksLevel || 1);
    if (row.count >= cap) return 0;
    const steps = Math.max(1, Math.min(n, Math.ceil((cap - row.count) / STEP)));
    const cost = def.cost(steps);
    if (opts.spend && !opts.spend(cost)) return 0;
    row.count = Math.min(cap, row.count + steps * STEP);
    state.lastAction = 'recruit';
    persist();
    notify('recruit');
    return row.count;
  },

  /** Take a company up one tier. Barracks level gates the ceiling. */
  train(id, opts = {}) {
    const row = state.companies[id];
    const cost = trainCost(id, state);
    if (!row || !cost) return null;
    if (row.tier >= Math.min(MAX_TIER, Math.max(1, opts.barracksLevel || 1) + 1)) return null;
    if (opts.spend && !opts.spend(cost)) return null;
    row.tier += 1;
    state.lastAction = 'train';
    persist();
    notify('train');
    return row.tier;
  },

  /** Bring wounded back to the line, at the cost of provisions. */
  heal(id, n, opts = {}) {
    const row = state.companies[id];
    if (!row || row.wounded <= 0) return 0;
    const men = Math.max(1, Math.min(n, row.wounded));
    const discount = 1 - 0.08 * Math.max(0, (opts.hospital || 1) - 1);
    const cost = { provisions: Math.max(1, Math.round(men * 3 * discount)) };
    if (opts.spend && !opts.spend(cost)) return 0;
    row.wounded -= men;
    row.count += men;
    state.lastAction = 'heal';
    persist();
    notify('heal');
    return row.count;
  },

  /** Bring every wounded man back in one order (where the treasury allows). */
  healAll(opts = {}) {
    let healed = 0;
    for (const c of COMPANIES) {
      const row = state.companies[c.id];
      if (!row || row.wounded <= 0) continue;
      const discount = 1 - 0.08 * Math.max(0, (opts.hospital || 1) - 1);
      const cost = { provisions: Math.max(1, Math.round(row.wounded * 3 * discount)) };
      if (opts.spend && !opts.spend(cost)) continue;
      healed += row.wounded;
      row.count += row.wounded;
      row.wounded = 0;
    }
    if (healed) {
      state.lastAction = 'heal';
      persist();
      notify('heal');
    }
    return healed;
  },

  /**
   * Write the butcher's bill after a skirmish.
   * Seven in ten of the fallen are wounded, not dead — the hospital's work.
   */
  applyLosses(losses = {}) {
    for (const [id, dead] of Object.entries(losses)) {
      const row = state.companies[id];
      const def = COMPANIES.find((c) => c.id === id);
      if (!row || !def || !(dead > 0)) continue;
      const taken = Math.min(row.count, Math.round(dead));
      row.count -= taken;
      row.wounded = Math.min(def.max, row.wounded + Math.round(taken * 0.7));
    }
    persist();
    notify('losses');
  },

  /** Record a won skirmish; the ledger on the War Table reads it. */
  recordWin(id) {
    state.wins[id] = (state.wins[id] || 0) + 1;
    state.battles += 1;
    persist();
    notify('win');
  },

  /** Record a defeat. */
  recordLoss() {
    state.lost += 1;
    state.battles += 1;
    persist();
    notify('loss');
  },

  reset() {
    state = fresh();
    persist();
    notify('reset');
    return state;
  },
};

export const MUSTER_DEFAULTS = fresh();
