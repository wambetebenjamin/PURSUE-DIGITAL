/**
 * net/pursueServer.js — the browser's half of the conversation with the
 * server (game-backend/).
 *
 * The rule the reference teaches, and the rule this module keeps:
 *
 *     the client never invents a number.
 *
 * So the client asks what is owed (`CEncampmentGainQuery`), asks to be paid
 * (`CEncampmentGainOutput`), and shows the server's answer. Everything the
 * player sees about the camp comes from an `S…` reply.
 *
 * Offline behaviour follows the same source. The reference does not pretend
 * to be online: it keeps the last server state, lets the player keep
 * working, and reconciles when the socket comes back. Here the queue of
 * uncommitted actions plays that role — actions taken while the server is
 * unreachable are replayed, in order, the moment it returns.
 *
 * The module takes its `fetch` and its `storage` by injection, so it can be
 * tested headlessly and so it works in a browser, a worker or Unity's
 * WebView without change.
 */

/** The storage key. Separate from the campaign's own keys on purpose. */
const KEY = 'pursue.net.v1';

/** Actions that may be taken offline and replayed later, in order. */
const QUEUEABLE = new Set([
  'encampment/Upgrade',
  'encampment/GainOutput',
  'encampment/Exchange',
  'work/Produce',
  'work/Collect',
  'troops/Recruit',
  'troops/Train',
  'troops/Heal',
  'troops/Drill',
]);

/** A code that means "the session is no good" — the SDK's TOKEN_INVALID. */
const TOKEN_INVALID = -130002;

function defaultStorage() {
  try {
    if (typeof localStorage !== 'undefined' && localStorage) return localStorage;
  } catch (_e) { /* private mode */ }
  const bag = new Map();
  return {
    getItem: (k) => (bag.has(k) ? bag.get(k) : null),
    setItem: (k, v) => bag.set(k, String(v)),
    removeItem: (k) => bag.delete(k),
  };
}

export class PursueServer {
  /**
   * @param {object} [opts]
   * @param {string} [opts.base]        where the server lives (default: same origin)
   * @param {Function} [opts.fetchImpl] the fetch to use
   * @param {object} [opts.storage]      localStorage-like
   * @param {string} [opts.deviceId]     a stable id for this device
   */
  constructor(opts = {}) {
    this.base = (opts.base ?? '/api').replace(/\/$/, '');
    this.fetchImpl = opts.fetchImpl || ((...a) => globalThis.fetch(...a));
    this.storage = opts.storage || defaultStorage();
    this.deviceId = opts.deviceId || this._deviceId();
    this.state = this._read();
    this.online = false;
    this.lastError = null;
  }

  /* ------------------------------------------------------------- storage */

  _read() {
    try {
      const raw = this.storage.getItem(KEY);
      if (raw) return { ...emptyState(), ...JSON.parse(raw) };
    } catch (_e) { /* a corrupt record is simply ignored */ }
    return emptyState();
  }

  _write() {
    try {
      this.storage.setItem(KEY, JSON.stringify(this.state));
    } catch (_e) { /* private mode: the tab keeps the state */ }
  }

  _deviceId() {
    const KEY2 = 'pursue.device.v1';
    try {
      const found = this.storage.getItem(KEY2);
      if (found) return found;
      const made = `dev_${Math.random().toString(36).slice(2)}${Date.now().toString(36)}`;
      this.storage.setItem(KEY2, made);
      return made;
    } catch (_e) {
      return 'dev_anonymous';
    }
  }

  /* --------------------------------------------------------------- calls */

  /**
   * One message. This is the C/S pair the reference speaks.
   * @param {string} module  e.g. 'encampment'
   * @param {string} action   e.g. 'GainQuery'
   * @param {object} [msg]    the message body
   * @returns {Promise<object>} the server's `data`
   */
  async call(module, action, msg = {}) {
    const key = `${module}/${action}`;
    try {
      const res = await this.fetchImpl(`${this.base}/rpc/${module}/${action}`, {
        method: 'POST',
        headers: this._headers(),
        body: JSON.stringify({ msg }),
      });
      const body = await res.json();
      if (body.code !== 0) {
        if (body.code === TOKEN_INVALID) this.state.token = null;
        const err = new Error(body.message || 'server error');
        err.code = body.code;
        throw err;
      }
      this.online = true;
      this.lastError = null;
      return body.data;
    } catch (err) {
      // The network failed, not the server. Queue the action if it can wait.
      if (err && typeof err.code === 'number') {
        this.lastError = err;
        throw err;
      }
      this.online = false;
      this.lastError = err;
      if (QUEUEABLE.has(key)) this.enqueue(module, action, msg);
      return null;
    }
  }

  _headers() {
    const h = { 'Content-Type': 'application/json' };
    if (this.state.token) h.Authorization = `Bearer ${this.state.token}`;
    return h;
  }

  /* ------------------------------------------------------------ session */

  /** Log in (or log in again) and remember the session. */
  async login(name) {
    try {
      const res = await this.fetchImpl(`${this.base}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ device_id: this.deviceId, name }),
      });
      const body = await res.json();
      if (body.code !== 0) throw new Error(body.message || 'login failed');
      this.state.token = body.data.session_token;
      this.state.role_id = body.data.player.role_id;
      this.state.player = body.data.player;
      this.online = true;
      this._write();
      return body.data.player;
    } catch (err) {
      this.online = false;
      this.lastError = err;
      return null;
    }
  }

  /** Open the conversation: log in if needed, then reconcile. */
  async connect(name) {
    if (!this.state.token) await this.login(name);
    else if (!this.online) await this.login(name);
    return this.sync();
  }

  /**
   * Reconcile with the server.
   *
   * The order matters, and it is the reference's order:
   *   1. ask what the server thinks (the query half),
   *   2. replay what was done while we were away,
   *   3. adopt the server's answer as the truth.
   */
  async sync() {
    const detail = await this.call('encampment', 'GetDetail', {});
    if (detail && detail.camp) {
      // The server has a camp for this role: its numbers are the truth.
      this.state.camp = detail.camp;
      this.state.serverHasState = true;
    } else if (this.state.camp) {
      // The server knows nothing yet (a fresh role): keep the local camp
      // and let the queue carry the work up.
      this.state.serverHasState = false;
    }
    await this.flush();
    this._write();
    return this.state.camp;
  }

  /* -------------------------------------------------------------- queue */

  /** Hold an action until the server is back. */
  enqueue(module, action, msg = {}) {
    this.state.queue = this.state.queue || [];
    this.state.queue.push({ module, action, msg, at: Date.now() });
    this._write();
    return this.state.queue.length;
  }

  /** Replay the held actions, in the order they were taken. */
  async flush() {
    const queue = this.state.queue || [];
    if (!queue.length) return 0;
    let sent = 0;
    const remaining = [];
    for (const item of queue) {
      try {
        const res = await this.fetchImpl(`${this.base}/rpc/${item.module}/${item.action}`, {
          method: 'POST',
          headers: this._headers(),
          body: JSON.stringify({ msg: item.msg }),
        });
        const body = await res.json();
        if (body.code !== 0) {
          remaining.push(item);
          continue;
        }
        sent += 1;
      } catch (_e) {
        remaining.push(item);
        break;              // the network is still down; keep the rest in order
      }
    }
    this.state.queue = remaining;
    this._write();
    return sent;
  }

  /* ------------------------------------------------------------- verbs */

  /** What is ready to be gathered? */
  queryGain() { return this.call('encampment', 'GainQuery', {}); }

  /** Take it. */
  collectGain() { return this.call('encampment', 'GainOutput', {}); }

  /** Raise a work. */
  upgradeWork(workId) { return this.call('encampment', 'Upgrade', { work_id: workId }); }

  /** Muster a company. */
  recruit(companyId, steps = 1) {
    return this.call('troops', 'Recruit', { company_id: companyId, steps });
  }

  /** Fight. The server replays it and its result is the truth. */
  pursue(stageId, opts = {}) {
    return this.call('combat', 'Pursue', { stage_id: stageId, ...opts });
  }

  /** The whole camp, as the server has it. */
  detail() { return this.call('encampment', 'GetDetail', {}); }

  /** The message catalogue, so a client can discover the protocol. */
  catalogue() {
    return this.fetchImpl(`${this.base}/meta/rpc`).then((r) => r.json()).catch(() => null);
  }

  /** Forget everything — a new role. */
  reset() {
    this.state = emptyState();
    this._write();
  }
}

function emptyState() {
  return {
    token: null,
    role_id: null,
    player: null,
    camp: null,
    queue: [],
    serverHasState: false,
    lastSync: null,
  };
}

export default PursueServer;
