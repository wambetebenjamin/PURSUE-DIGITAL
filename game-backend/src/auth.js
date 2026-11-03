/**
 * auth.js — the session.
 *
 * The reference's hand-off, recovered from the decompiled SDK:
 *
 *   gsdk/account/login           → the account is established
 *   gsdk/account/exchange_token  → a session_token
 *   gsdk/game/server_list        → the address and port of a shard
 *   then the game socket opens with that token
 *
 * We keep the shape: a login yields a role, a role yields a session token,
 * and every state route demands that token. The token travels in the
 * `Authorization: Bearer` header, or in `x-session-token` for browsers that
 * cannot set headers on a websocket handshake.
 */

import { randomBytes, createHash } from 'node:crypto';
import { CODE, fail } from './envelope.js';

const TOKEN_TTL_MS = 7 * 24 * 3600 * 1000;

/** An error that carries an SDK error code. */
export class GameError extends Error {
  constructor(code, message) {
    super(message);
    this.code = code;
  }
}

export function newRoleId() {
  return `role_${randomBytes(8).toString('hex')}`;
}

export function newToken() {
  return randomBytes(24).toString('hex');
}

/** A stable fingerprint of a device, so the same device finds its role again. */
export function fingerprint(seed) {
  return createHash('sha256').update(String(seed || 'anonymous')).digest('hex').slice(0, 32);
}

/**
 * Log in. Creates the role on first sight, returns it forever after.
 * The open id is the device fingerprint — the SDK's `sdk_open_id`.
 */
export async function login(store, { open_id, name, device_id, server_id } = {}) {
  const key = open_id || fingerprint(device_id || 'anonymous');
  let player = await store.findPlayerByOpenId(key);
  const now = new Date();

  if (!player) {
    player = {
      role_id: newRoleId(),
      open_id: key,
      name: name || 'A captain of Ziklag',
      level: 1,
      job: 'captain',
      avatar_url: null,
      server_id: Number(server_id) || 1,
      server_name: 'Ziklag',
      session_token: newToken(),
      token_expires_at: new Date(now.getTime() + TOKEN_TTL_MS),
      last_login_time: now,
      stars: 0,
      renown: 0,
      season: 10,
      boon_active: false,
      extra: null,
    };
  } else {
    player.session_token = newToken();
    player.token_expires_at = new Date(now.getTime() + TOKEN_TTL_MS);
    player.last_login_time = now;
    if (name) player.name = name;
  }

  await store.putPlayer(player);
  return { player, token: player.session_token, expires_at: player.token_expires_at };
}

/** Exchange a session for a fresh one — `gsdk/account/exchange_token`. */
export async function exchangeToken(store, player) {
  player.session_token = newToken();
  player.token_expires_at = new Date(Date.now() + TOKEN_TTL_MS);
  await store.putPlayer(player);
  return { token: player.session_token, expires_at: player.token_expires_at };
}

/** The player view — the SDK's `Role` bean, plus what our game adds. */
export function playerView(player) {
  return {
    role_id: player.role_id,
    name: player.name,
    level: player.level,
    job: player.job,
    avatar_url: player.avatar_url,
    server_id: player.server_id,
    server_name: player.server_name,
    last_login_time: player.last_login_time,
    stars: player.stars,
    renown: player.renown,
    season: player.season,
    boon_active: player.boon_active,
  };
}

/** Read the token off a request. */
export function readToken(req) {
  const header = req.get('authorization') || req.get('Authorization') || '';
  if (header.toLowerCase().startsWith('bearer ')) return header.slice(7).trim();
  return req.get('x-session-token') || null;
}

/** Resolve the player behind a request, or null. */
export async function resolvePlayer(store, req) {
  const token = readToken(req);
  if (!token) return null;
  const player = await store.findPlayerByToken(token);
  if (!player) return null;
  if (player.token_expires_at && new Date(player.token_expires_at).getTime() < Date.now()) {
    return null;
  }
  return player;
}

/**
 * The guard. Returns a middleware that puts the player on the request, or
 * answers with the SDK's TOKEN_INVALID code.
 */
export function requireAuth(store) {
  return async (req, res, next) => {
    const player = await resolvePlayer(store, req);
    if (!player) {
      res.status(401).json(fail(CODE.TOKEN_INVALID));
      return;
    }
    req.player = player;
    next();
  };
}
