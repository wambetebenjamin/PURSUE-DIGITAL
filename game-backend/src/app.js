/**
 * app.js — the express adapter.
 *
 * The routing, the session and the envelope all live in core.js; this file
 * is only the express shell around them.
 */

import express from 'express';
import { createCore } from './core.js';

/**
 * Build the application.
 * @param {object} store the persistence store (see store/index.js)
 */
export function createApp(store) {
  const app = express();
  const core = createCore(store);

  app.use(express.json({ limit: '256kb' }));
  app.use((req, _res, next) => { req.store = store; next(); });

  // permissive CORS: the browser build and the Unity build both talk here
  app.use((req, res, next) => {
    res.set('Access-Control-Allow-Origin', req.get('origin') || '*');
    res.set('Access-Control-Allow-Headers', 'Content-Type, Authorization, x-session-token');
    res.set('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
    if (req.method === 'OPTIONS') { res.status(204).end(); return; }
    next();
  });

  app.all('/api/*', async (req, res) => {
    const token = req.get('authorization')?.replace(/^bearer\s+/i, '')
      || req.get('x-session-token');
    const out = await core.handle({
      method: req.method,
      path: req.path,
      body: req.body,
      query: req.query,
      token,
    });
    res.json(out);
  });

  app.use((req, res) => {
    res.status(404).json({
      code: -130001,
      message: `no such route: ${req.method} ${req.path}`,
      data: null,
    });
  });

  return app;
}
