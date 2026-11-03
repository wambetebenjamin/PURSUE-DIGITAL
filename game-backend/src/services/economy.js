/**
 * economy.js — the production loop, server-side and authoritative.
 *
 * The reference's farm module is the complete worked example in the APK, and
 * its shape is what we copy (doc 04, doc 05):
 *
 *   server push  SPushFarmersWorldCanGain
 *         ↓
 *   client        CFarmersWorldGainQuery   → "what exactly is ready?"
 *         ↓ server
 *   client        CFarmersWorldGainOutput  → "take it"
 *         ↓ server
 *   client        SFarmersWorldGainOutput  → authoritative new totals
 *
 * The client never invents a number. Offline accumulation is entirely
 * server-side, and the offline cap is a server constant.
 */

import {
  CONST, WORKS, WORK_IDS, EXCHANGE, RESOURCE_IDS,
  slotMinutes, slotCost, freshResources,
} from './config.js';

/* ------------------------------------------------------------------ helpers */

export function emptyResources() {
  return { provisions: 0, bronze: 0, timber: 0, spoil: 0 };
}

export function addResources(target, delta) {
  const out = { ...target };
  for (const [k, v] of Object.entries(delta || {})) {
    if (!RESOURCE_IDS.includes(k)) continue;
    out[k] = (out[k] || 0) + Math.round(v);
  }
  return out;
}

export function canAfford(resources, cost) {
  for (const [k, v] of Object.entries(cost || {})) {
    if ((resources[k] || 0) < v) return false;
  }
  return true;
}

export function spend(resources, cost) {
  if (!canAfford(resources, cost)) return null;
  const out = { ...resources };
  for (const [k, v] of Object.entries(cost || {})) out[k] -= v;
  return out;
}

/* ------------------------------------------------------------- the works */

/** The hourly output of one work at its current level ({} if it makes nothing). */
export function hourlyYield(workId, level = 1) {
  const def = WORKS.find((w) => w.id === workId);
  if (!def) return {};
  const y = def.yield(level);
  return y || {};
}

/** Everything the settlement makes in an hour, summed. */
export function productionRate(works = {}) {
  const out = {};
  for (const def of WORKS) {
    const y = def.yield(works[def.id] || 1);
    if (!y) continue;
    for (const [k, v] of Object.entries(y)) out[k] = (out[k] || 0) + v;
  }
  return out;
}

/**
 * What has been made since the works were last emptied.
 * Off the clock while the tab is closed — eight hours is the most that can
 * be gathered (CONST.OFFLINE_CAP_HOURS).
 *
 * @param {object} works  {work_id: level}
 * @param {number} lastCollectAt epoch millis
 * @param {number} now epoch millis
 */
export function pendingGain(works = {}, lastCollectAt = Date.now(), now = Date.now()) {
  const capMs = CONST.OFFLINE_CAP_HOURS * 3600 * 1000;
  const since = Math.max(0, Math.min(capMs, now - (lastCollectAt || now)));
  const hours = since / 3600000;
  const out = {};
  for (const [k, v] of Object.entries(productionRate(works))) {
    const made = Math.floor(v * hours);
    if (made > 0) out[k] = made;
  }
  return { resources: out, hours, capped: since >= capMs, since_ms: since };
}

/**
 * The gain query — the read half of the Query/Commit split.
 * The client shows this number before it asks to be paid.
 */
export function gainQuery(encampment, now = Date.now()) {
  const pending = pendingGain(encampment.works, encampment.last_collect_at, now);
  return {
    ...pending,
    can_gain: Object.keys(pending.resources).length > 0,
    next_finish_at: nextFinishAt(encampment, now),
  };
}

/**
 * The gain output — the commit half. Takes what is owed, and only that.
 * The server recomputes; it never trusts a number the client sent.
 */
export function gainCollect(encampment, now = Date.now()) {
  const pending = pendingGain(encampment.works, encampment.last_collect_at, now);
  const given = { ...pending.resources };
  encampment.resources = addResources(encampment.resources, given);
  encampment.last_collect_at = now;
  return { given, hours: pending.hours, capped: pending.capped };
}

/* ------------------------------------------------- the timed work (slots) */

/**
 * The reference's production building arithmetic — the Glory Factory:
 *
 *     processCount     slots currently running
 *     finishCount      slots completed
 *     nextFinishTime   when the current slot finishes
 *     allFinishTime    when the whole queue finishes
 *     costMinuteRaw    the plain cost of a slot
 *     costMinuteBP     the cost of a slot with the season boon applied
 *
 * We keep the queue, the two costs and the two finish times. The boon makes
 * the work finish sooner and costs more per slot; the duration saved is what
 * the player actually buys.
 */
export function workState(encampment, workId) {
  const q = (encampment.production && encampment.production[workId]) || {
    slots: 0, finished: 0, next_finish_at: null, queued_at: null,
  };
  return q;
}

/** When the next slot finishes, or null when nothing is queued. */
export function nextFinishAt(encampment, now = Date.now()) {
  let soonest = null;
  for (const id of WORK_IDS) {
    const q = workState(encampment, id);
    if (!q.slots) continue;
    const start = q.next_finish_at || now;
    if (soonest === null || start < soonest) soonest = start;
  }
  return soonest;
}

/** When the whole queue finishes, or null. */
export function allFinishAt(encampment, now = Date.now()) {
  let last = null;
  for (const id of WORK_IDS) {
    const q = workState(encampment, id);
    if (!q.slots) continue;
    const minutes = slotMinutes(encampment.boon_active);
    const start = q.next_finish_at || now;
    const end = start + minutes * 60 * 1000 * Math.max(0, q.slots - 1);
    if (last === null || end > last) last = end;
  }
  return last;
}

/** The cost of queuing `slots` more slots on a work. */
export function produceCost(boonActive = false, slots = 1) {
  return { provisions: slotCost(boonActive) * slots };
}

/**
 * Start slots on a work. Mirrors `CGloryFactoryProcess`.
 * Returns the new queue state, or null when the order cannot be accepted.
 */
export function produce(encampment, workId, slots = 1, now = Date.now()) {
  if (!WORK_IDS.includes(workId)) return null;
  const n = Math.max(1, Math.min(CONST.PRODUCTION_NUM_MAX, Math.floor(slots)));
  const boon = Boolean(encampment.boon_active);
  const cost = produceCost(boon, n);
  if (!canAfford(encampment.resources, cost)) return null;

  const q = { ...workState(encampment, workId) };
  if (q.slots + n > CONST.PRODUCTION_NUM_MAX) return null;

  const minutes = slotMinutes(boon);
  const nowMs = now;
  // A slot already finished (the queue ran while the client was away) is
  // settled first; the new slots start from the next free moment.
  const base = q.next_finish_at && q.next_finish_at > nowMs ? q.next_finish_at : nowMs;
  q.slots += n;
  q.next_finish_at = q.next_finish_at && q.next_finish_at > nowMs
    ? q.next_finish_at + minutes * 60 * 1000 * n
    : base + minutes * 60 * 1000 * n;
  if (!q.queued_at) q.queued_at = nowMs;

  encampment.resources = spend(encampment.resources, cost);
  encampment.production = encampment.production || {};
  encampment.production[workId] = q;
  return { work_id: workId, slots: q.slots, cost, minutes_per_slot: minutes,
    next_finish_at: new Date(q.next_finish_at).toISOString(),
    all_finish_at: allFinishAt(encampment, now) ? new Date(allFinishAt(encampment, now)).toISOString() : null };
}

/**
 * How many slots of a work have ripened. The reference's `CanCollect()`.
 */
export function finishedSlots(encampment, workId, now = Date.now()) {
  const q = workState(encampment, workId);
  if (!q.slots || !q.next_finish_at) return 0;
  const minutes = slotMinutes(encampment.boon_active);
  const span = minutes * 60 * 1000;
  const elapsed = now - (q.next_finish_at - span * q.slots);
  if (elapsed < 0) return 0;
  return Math.min(q.slots, Math.floor(elapsed / span));
}

/**
 * Collect the finished slots of a work — `CGloryFactoryCollect`.
 * The yield is the work's hourly rate prorated to the slot length.
 */
export function collectSlots(encampment, workId, now = Date.now()) {
  if (!WORK_IDS.includes(workId)) return null;
  const done = finishedSlots(encampment, workId, now);
  if (!done) return null;

  const q = { ...workState(encampment, workId) };
  const minutes = slotMinutes(encampment.boon_active);
  const level = encampment.works[workId] || 1;
  const rate = hourlyYield(workId, level);
  const perSlot = {};
  for (const [k, v] of Object.entries(rate)) {
    const amount = Math.round(v * (minutes / 60));
    if (amount > 0) perSlot[k] = amount;
  }
  const given = {};
  for (const [k, v] of Object.entries(perSlot)) given[k] = v * done;

  q.slots -= done;
  q.finished += done;
  q.next_finish_at = q.next_finish_at ? q.next_finish_at - minutes * 60 * 1000 * done : null;
  if (!q.slots) {
    q.slots = 0;
    q.next_finish_at = null;
  }
  encampment.production = encampment.production || {};
  encampment.production[workId] = q;
  encampment.resources = addResources(encampment.resources, given);
  return { work_id: workId, slots_taken: done, given, slots_left: q.slots };
}

/* ----------------------------------------------------------------- exchange */

/** The exchange table — the reference's `exchangecfg` and its shop. */
export function exchange(from, to, amount = 1) {
  const rule = EXCHANGE.find((e) => e.from === from && e.to === to);
  if (!rule) return null;
  const give = amount;
  const get = Math.floor(amount * rule.rate);
  if (get <= 0) return null;
  return { from, to, give, get, rate: rule.rate, note: rule.note };
}

/** Apply an exchange to the stores, if they can pay. */
export function applyExchange(encampment, from, to, amount = 1) {
  const plan = exchange(from, to, amount);
  if (!plan) return null;
  if ((encampment.resources[from] || 0) < plan.give) return null;
  const next = { ...encampment.resources };
  next[from] -= plan.give;
  next[to] = (next[to] || 0) + plan.get;
  encampment.resources = next;
  return plan;
}

/* ------------------------------------------------------------------ upgrades */

/** Cost to raise a work one level, or null at its ceiling. */
export function upgradeCost(workId, works = {}) {
  const def = WORKS.find((w) => w.id === workId);
  if (!def) return null;
  const lv = works[workId] || 1;
  if (lv >= def.maxLevel) return null;
  return def.cost(lv);
}

/** Raise a work one level, if the stores can pay. */
export function upgrade(encampment, workId) {
  const cost = upgradeCost(workId, encampment.works);
  if (!cost) return null;
  if (!canAfford(encampment.resources, cost)) return null;
  encampment.resources = spend(encampment.resources, cost);
  encampment.works = { ...encampment.works, [workId]: (encampment.works[workId] || 1) + 1 };
  return { work_id: workId, level: encampment.works[workId], cost };
}

/* --------------------------------------------------------------- the start */

/** A brand new camp. Mirrors the reference's starting position. */
export function freshEncampment(playerId) {
  return {
    player_id: playerId,
    resources: freshResources(),
    works: Object.fromEntries(WORKS.map((w) => [w.id, 1])),
    production: {},
    roster: {},
    renown: 0,
    runs: 0,
    last_collect_at: Date.now(),
    last_play: null,
    boon_active: false,
    season: 10,
  };
}
