/**
 * game/api.js — the camp's half of the conversation with the server.
 *
 * The server is the authority: it holds the stores, the works and the band,
 * and it decides what a fight was worth. This module asks it, and shows what
 * it says.
 *
 * It also knows how to be alone. If the server cannot be reached — no
 * network, no server deployed, the function asleep — the camp falls back to
 * the campaign record the browser already keeps (`progression`, `muster`,
 * `skirmish`), so the game is still playable. The rule is the reference's:
 * **the client never invents a number.** The fallback numbers are the
 * player's own, computed by the same code that runs the 3D game.
 */

import { progression } from '../core/progression.js';
import { muster } from '../core/muster.js';
import { settleSkirmish, SKIRMISHES } from '../core/skirmish.js';

/** The device key: the same device finds the same role again. */
const DEVICE_KEY = 'pursue.device.v1';

/** SDK error numbers, as the backend returns them. */
export const CODE = Object.freeze({
  OK: 0,
  PARAM_ERROR: -130001,
  TOKEN_INVALID: -130002,
  SERVER_ERROR: -130006,
  NETWORK_ERROR: -133001,
});

function deviceId(storage) {
  try {
    const found = storage && storage.getItem(DEVICE_KEY);
    if (found) return found;
    const made = `dev_${Math.random().toString(36).slice(2)}${Date.now().toString(36)}`;
    if (storage) storage.setItem(DEVICE_KEY, made);
    return made;
  } catch (_e) {
    return 'dev_anonymous';
  }
}

function safeStorage() {
  try {
    if (typeof localStorage !== 'undefined' && localStorage) return localStorage;
  } catch (_e) { /* private mode */ }
  return null;
}

/**
 * The server sends the companies as a list; the camp wants them by id.
 * @param {Array|object} list
 * @returns {object} keyed by company id
 */
export function byCompany(list) {
  const out = {};
  if (Array.isArray(list)) {
    for (const c of list) if (c && c.id) out[c.id] = c;
    return out;
  }
  for (const [id, c] of Object.entries(list || {})) {
    if (c) out[id] = { id, ...c };
  }
  return out;
}

export class CampApi {
  /**
   * @param {object} [opts]
   * @param {string} [opts.base]        where the server is (default: same origin)
   * @param {Function} [opts.fetchImpl] the fetch to use
   * @param {object} [opts.storage]      localStorage-like
   * @param {string} [opts.name]         the captain's name on the role
   */
  constructor(opts = {}) {
    this.base = (opts.base ?? '/api').replace(/\/$/, '');
    this.fetchImpl = opts.fetchImpl
      || (typeof fetch === 'function' ? fetch.bind(globalThis) : null);
    this.storage = opts.storage === undefined ? safeStorage() : opts.storage;
    this.name = opts.name || 'David';
    this.deviceId = opts.deviceId || deviceId(this.storage);
    this.token = null;
    this.player = null;
    this.online = false;
    this.lastError = null;
    this.state = null;
  }

  /* ------------------------------------------------------------ transport */

  /** One request. Returns the envelope, or null when the server is away. */
  async request(method, path, body) {
    if (!this.fetchImpl) return null;
    try {
      const res = await this.fetchImpl(`${this.base}${path}`, {
        method,
        headers: {
          'Content-Type': 'application/json',
          ...(this.token ? { Authorization: `Bearer ${this.token}` } : {}),
        },
        ...(body === undefined ? {} : { body: JSON.stringify(body) }),
      });
      const envelope = await res.json();
      if (envelope && typeof envelope.code === 'number') {
        this.online = true;
        this.lastError = null;
        return envelope;
      }
      this.online = false;
      return null;
    } catch (_e) {
      this.online = false;
      this.lastError = _e;
      return null;
    }
  }

  /** Log in, or log in again. */
  async ensureSession() {
    if (this.token) return true;
    const envelope = await this.request('POST', '/auth/login', {
      device_id: this.deviceId,
      name: this.name,
    });
    if (!envelope || envelope.code !== CODE.OK) return false;
    this.token = envelope.data.session_token;
    this.player = envelope.data.player;
    return true;
  }

  /** A request that needs a session, with one retry on an expired token. */
  async authed(method, path, body) {
    if (!(await this.ensureSession())) return null;
    let envelope = await this.request(method, path, body);
    if (envelope && envelope.code === CODE.TOKEN_INVALID) {
      this.token = null;
      if (await this.ensureSession()) envelope = await this.request(method, path, body);
    }
    return envelope;
  }

  /* --------------------------------------------------------------- state */

  /** Read the whole camp: the stores, the works, the band. */
  async refresh() {
    const camp = await this.authed('GET', '/camp/detail');
    const troops = await this.authed('GET', '/troops');
    if (!camp || camp.code !== CODE.OK || !troops || troops.code !== CODE.OK) {
      this.state = this.localState();
      return this.state;
    }
    this.state = {
      resources: { ...(camp.data.camp.resources || {}) },
      works: { ...(camp.data.camp.works || {}) },
      roster: camp.data.camp.roster || {},
      renown: camp.data.camp.renown || 0,
      gain: camp.data.camp.gain || null,
      rate: camp.data.rate || {},
      troops: {
        companies: byCompany(troops.data.companies),
        order_of_battle: troops.data.order_of_battle || [],
        power: troops.data.power || { power: 0, men: 0 },
        wounded: troops.data.wounded || 0,
      },
      source: 'server',
    };
    return this.state;
  }

  /* -------------------------------------------------------------- actions */

  /**
   * Raise a place one level.
   * @param {string} placeId 'hall' | 'barracks' | 'threshing' | …
   * @returns {Promise<{ok:boolean, message:string, data:object|null, source:string}>}
   */
  async upgradeCamp(placeId) {
    const envelope = await this.authed('POST', '/camp/upgrade', { work_id: placeId });
    if (envelope && envelope.code === CODE.OK) {
      await this.refresh();
      return { ok: true, message: 'raised', data: envelope.data, source: 'server' };
    }
    if (envelope) {
      // the server refused, and said why
      return { ok: false, message: envelope.message || 'refused', data: null, source: 'server' };
    }
    return this.localUpgrade(placeId);
  }

  /**
   * Muster a press of ten men into a company.
   * @param {string} companyId
   * @param {number} [steps]
   */
  async recruitTroops(companyId, steps = 1) {
    const envelope = await this.authed('POST', '/troops/recruit', {
      company_id: companyId,
      steps,
    });
    if (envelope && envelope.code === CODE.OK) {
      await this.refresh();
      return { ok: true, message: 'mustered', data: envelope.data, source: 'server' };
    }
    if (envelope) {
      return { ok: false, message: envelope.message || 'refused', data: null, source: 'server' };
    }
    return this.localRecruit(companyId, steps);
  }

  /**
   * Send the band out. The server replays the fight and its result is the
   * truth; the client only reports what it did.
   */
  async pursue(stageId, opts = {}) {
    const envelope = await this.authed('POST', '/combat/pursue', {
      stage_id: stageId,
      seed: opts.seed || Math.floor(Math.random() * 0xffffff),
      hero_ids: opts.heroIds,
      skill_timings: opts.skillTimings,
    });
    if (envelope && envelope.code === CODE.OK) {
      await this.refresh();
      return {
        ok: true,
        message: 'fought',
        source: 'server',
        data: {
          won: envelope.data.won,
          stars: envelope.data.stars,
          rewards: envelope.data.rewards || {},
          casualties: envelope.data.casualties || { standing: 0, fallen: 0 },
          elapsed: envelope.data.elapsed,
          log: envelope.data.log || [],
        },
      };
    }
    if (envelope) {
      return { ok: false, message: envelope.message || 'refused', data: null, source: 'server' };
    }
    return this.localPursue(stageId);
  }

  /* --------------------------------------------------------------- local */

  /** The camp as the browser's own record has it. */
  localState() {
    const p = progression.get();
    const m = muster.get();
    const companies = {};
    let men = 0;
    for (const [id, row] of Object.entries(m.companies || {})) {
      companies[id] = { ...row };
      men += row.count || 0;
    }
    return {
      resources: { ...p.resources },
      works: { ...p.buildings },
      roster: { ...p.roster },
      renown: p.renown || 0,
      gain: null,
      rate: {},
      troops: {
        companies,
        order_of_battle: [],
        power: { power: 0, men },
        wounded: Object.values(companies).reduce((n, c) => n + (c.wounded || 0), 0),
      },
      source: 'local',
    };
  }

  /** Raise a place, in the local record. */
  localUpgrade(placeId) {
    const level = progression.upgrade(placeId);
    if (!level) {
      return { ok: false, message: 'the stores cannot pay for that', data: null, source: 'local' };
    }
    this.state = this.localState();
    return { ok: true, message: 'raised', data: { work_id: placeId, level }, source: 'local' };
  }

  /** Muster men, in the local record. */
  localRecruit(companyId, steps = 1) {
    const barracks = progression.get().buildings.barracks || 1;
    const count = muster.recruit(companyId, steps, {
      barracksLevel: barracks,
      spend: (cost) => progression.spend(cost),
    });
    if (!count) {
      return { ok: false, message: 'the stores cannot pay for that', data: null, source: 'local' };
    }
    this.state = this.localState();
    return { ok: true, message: 'mustered', data: { count }, source: 'local' };
  }

  /** Fight it out, in the local record. */
  localPursue(stageId) {
    const stage = SKIRMISHES.find((s) => s.id === stageId) || SKIRMISHES[0];
    const barracks = progression.get().buildings.barracks || 1;
    const result = settleSkirmish(stage, { barracks });
    this.state = this.localState();
    return {
      ok: true,
      message: 'fought',
      source: 'local',
      data: {
        won: result.won,
        stars: result.won ? 1 : 0,
        rewards: result.rewards || {},
        casualties: result.bout ? result.bout.casualties : { standing: 0, fallen: 0 },
        elapsed: result.bout ? result.bout.elapsed : 0,
        log: [],
      },
    };
  }
}

export default CampApi;
