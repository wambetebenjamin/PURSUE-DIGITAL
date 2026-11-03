/**
 * api/[...path].js — the deployed server.
 *
 * The same core the local server runs (game-backend/src/core.js), exposed as
 * a Vercel serverless function. The static game is served from `dist/`; this
 * answers `/api/*`.
 *
 * Persistence: with no MONGODB_URI set the function keeps its state in the
 * warm instance, which means a player's camp survives as long as the
 * instance does and is lost when it is recycled. Set MONGODB_URI (MongoDB
 * Atlas or any Mongo) and the same code persists to the database. The
 * browser client is written to survive that difference — it never uploads
 * over a camp it has not confirmed.
 */

import { createCore } from '../game-backend/src/core.js';
import { MemoryStore } from '../game-backend/src/store/memory.js';

let core = null;

async function getCore() {
  if (core) return core;
  const store = new MemoryStore();
  await store.connect();
  core = createCore(store);
  return core;
}

export default async function handler(req, res) {
  try {
    const c = await getCore();
    const url = String(req.url || '/');
    const path = url.split('?')[0];
    const headers = req.headers || {};
    const token = headers.authorization
      ? String(headers.authorization).replace(/^bearer\s+/i, '')
      : headers['x-session-token'];

    const out = await c.handle({
      method: req.method || 'GET',
      path,
      body: req.body,
      query: req.query,
      token,
    });

    res.set('Access-Control-Allow-Origin', headers.origin || '*');
    res.set('Access-Control-Allow-Headers', 'Content-Type, Authorization, x-session-token');
    res.set('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
    res.status(200).json(out);
  } catch (err) {
    res.status(200).json({
      code: -130006,
      message: err && err.message ? err.message : 'server error',
      data: null,
    });
  }
}
