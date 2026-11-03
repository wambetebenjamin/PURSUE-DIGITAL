/**
 * game/session.js — who the camp is talking to, and how it leaves.
 *
 * The scenes are started by Phaser, which does not pass our arguments to
 * them, so the session lives here instead: one client for the whole camp,
 * the way back to the 3D march, and the way to the citadel.
 */

import { CampApi } from './api.js';

let api = null;
let onLeave = null;
let onCitadel = null;

/**
 * Where the server is. The camp is served from the same place as the
 * function, so the path is taken from the page itself — a build served
 * from a sub-path still finds its own server.
 * @returns {string}
 */
export function defaultBase() {
  try {
    if (typeof document !== 'undefined' && document.baseURI) {
      return new URL('api', document.baseURI).pathname.replace(/\/+$/, '');
    }
  } catch (_e) { /* no document: the root path it is */ }
  return '/api';
}

/**
 * Open the session.
 * @param {object} [opts]
 * @param {string} [opts.base]        where the server is
 * @param {Function} [opts.onLeave]   called when the player takes the road
 * @param {Function} [opts.onCitadel] called when the player asks for the citadel
 */
export function openSession(opts = {}) {
  if (!api) api = new CampApi({ base: opts.base || defaultBase() });
  if (opts.onLeave) onLeave = opts.onLeave;
  if (opts.onCitadel) onCitadel = opts.onCitadel;
  return api;
}

/** Close it — the camp is going away. */
export function closeSession() {
  api = null;
  onLeave = null;
  onCitadel = null;
}

/** The client the scenes talk through (may be null). */
export function sessionApi() {
  return api;
}

/** The way back to the march (may be null). */
export function sessionLeave() {
  return onLeave;
}

/** The way to the citadel (may be null). */
export function sessionCitadel() {
  return onCitadel;
}
