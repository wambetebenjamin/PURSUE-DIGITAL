/**
 * core.js — the server's core, with no framework in it.
 *
 * Everything the game does lives here: the route table, the session check,
 * the envelope. Two adapters sit on top of it:
 *
 *   app.js          express, for `node src/index.js`
 *   api/[[...path]] a Vercel serverless function, for the deployed game
 *
 * Keeping the core free of express and mongoose is what lets the deployed
 * site run the same code as the local server without pulling a web
 * framework into the static build.
 */

import { ok, fail, CODE } from './envelope.js';
import { resolvePlayer } from './auth.js';
import { byAction, catalogue } from './rpc.js';

import authRoutes from './routes/auth.js';
import campRoutes from './routes/camp.js';
import troopsRoutes from './routes/troops.js';
import combatRoutes from './routes/combat.js';
import metaRoutes from './routes/meta.js';

/** Every route the server answers. */
export const ROUTES = [
  ...authRoutes,
  ...campRoutes,
  ...troopsRoutes,
  ...combatRoutes,
  ...metaRoutes,
];

/**
 * The RPC surface — the reference's own addressing, where the message name
 * is the endpoint. It resolves to the very same handler a REST route uses.
 */
function resolveRpc(path, routeByRpc) {
  const seg = path.split('/').filter(Boolean);   // api, rpc, module, action
  if (seg.length < 4) return null;
  const entry = byAction(seg[2], seg[3]);
  if (!entry) return { error: `no such message: ${seg[2]}.${seg[3]}` };
  const def = routeByRpc.get(entry.request);
  if (!def) return { error: `${entry.request} is not served yet` };
  return { def, entry, params: { module: seg[2], action: seg[3] } };
}

/** Match a path against a route pattern. */
function match(pattern, path) {
  const p = pattern.split('/').filter(Boolean);
  const s = path.split('/').filter(Boolean);
  if (p.length !== s.length) return null;
  const params = {};
  for (let i = 0; i < p.length; i += 1) {
    if (p[i].startsWith(':')) params[p[i].slice(1)] = decodeURIComponent(s[i]);
    else if (p[i] !== s[i]) return null;
  }
  return params;
}

/**
 * Build the core.
 * @param {object} store the persistence store
 */
export function createCore(store) {
  const routeByRpc = new Map(ROUTES.filter((r) => r.rpc).map((r) => [r.rpc, r]));
  const all = [...ROUTES];

  /**
   * Handle one request.
   * @param {{method:string, path:string, body?:object, query?:object, token?:string}} req
   * @returns {Promise<{code:number, message:string, data:object|null}>} the envelope
   */
  async function handle(req) {
    const method = String(req.method || 'GET').toUpperCase();
    const path = String(req.path || '/');
    const query = req.query || {};
    let body = req.body || {};

    // The token may arrive as a header or already-stripped.
    const rawToken = req.token
      || headerOf(req, 'authorization')
      || headerOf(req, 'x-session-token');
    const bearer = rawToken ? String(rawToken).replace(/^bearer\s+/i, '').trim() : null;

    let def = null;
    let params = null;

    if (method === 'POST' && path.startsWith('/api/rpc/')) {
      const found = resolveRpc(path, routeByRpc);
      if (!found) return fail(CODE.PARAM_ERROR, 'no such message');
      if (found.error) return fail(CODE.PARAM_ERROR, found.error);
      def = found.def;
      params = found.params;
      body = (body && body.msg) || body || {};
    } else {
      for (const candidate of all) {
        if (String(candidate.method).toUpperCase() !== method) continue;
        const matched = match(candidate.path, path);
        if (matched) { def = candidate; params = matched; break; }
      }
    }

    if (!def) {
      return fail(CODE.PARAM_ERROR, `no such route: ${method} ${path}`);
    }

    const ctx = {
      body,
      query,
      params,
      player: null,
      store,
      now: Date.now(),
      routeByRpc,
    };

    if (def.auth) {
      const player = await resolvePlayer(store, {
        get: (h) => {
          const key = String(h).toLowerCase();
          if (key === 'authorization') return bearer ? `Bearer ${bearer}` : null;
          if (key === 'x-session-token') return bearer;
          return headerOf(req, h);
        },
      });
      if (!player) return fail(CODE.TOKEN_INVALID);
      ctx.player = player;
    }

    try {
      const data = await def.handler(ctx);
      return ok(data);
    } catch (err) {
      if (err && typeof err.code === 'number') return fail(err.code, err.message);
      if (err && err.name === 'ValidationError') return fail(CODE.PARAM_ERROR, err.message);
      return fail(CODE.SERVER_ERROR, err && err.message ? err.message : 'server error');
    }
  }

  return {
    handle,
    routes: ROUTES,
    catalogue,
    store,
  };
}

/** Read a header from a plain request object. */
function headerOf(req, name) {
  const headers = req.headers || {};
  const key = String(name).toLowerCase();
  return headers[key] || headers[key.toUpperCase()] || null;
}
