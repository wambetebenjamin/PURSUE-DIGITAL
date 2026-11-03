/**
 * routes/auth.js — the session and the shard list.
 *
 * Mirrors the SDK's account spine:
 *   gsdk/account/login → gsdk/account/exchange_token → gsdk/game/server_list
 */

import { login, exchangeToken, playerView } from '../auth.js';
import { CODE, fail } from '../envelope.js';

/** The shards. The SDK's `server_list` gives an address and a port. */
export const SERVERS = [
  { server_id: 1, server_name: 'Ziklag', region: 'Judea', address: 'ziklag.pursue.game', port: 7001, status: 'online' },
  { server_id: 2, server_name: 'The Brook Besor', region: 'Judea', address: 'besor.pursue.game', port: 7002, status: 'online' },
  { server_id: 3, server_name: 'The Slope of Gilboa', region: 'Judea', address: 'gilboa.pursue.game', port: 7003, status: 'maintenance' },
];

export default [
  {
    method: 'post', path: '/api/auth/login', rpc: 'CLogin', auth: false,
    handler: async ({ body, store }) => {
      const result = await login(store, {
        open_id: body.open_id,
        name: body.name,
        device_id: body.device_id,
        server_id: body.server_id,
      });
      return {
        player: playerView(result.player),
        session_token: result.token,
        expires_at: result.expires_at,
        servers: SERVERS,
      };
    },
  },
  {
    method: 'post', path: '/api/auth/token', rpc: 'CExchangeToken', auth: true,
    handler: async ({ player, store }) => {
      const result = await exchangeToken(store, player);
      return { session_token: result.token, expires_at: result.expires_at };
    },
  },
  {
    method: 'post', path: '/api/auth/role', rpc: 'CDeleteRole', auth: true,
    handler: async ({ player, store }) => {
      // The reference's SDeleteRole wipes the role. Ours wipes the world too.
      await store.deleteRole(player.role_id);
      return { deleted: true, role_id: player.role_id };
    },
  },
  {
    method: 'get', path: '/api/server/list', rpc: 'CServerList', auth: false,
    handler: async () => ({ zone_list: SERVERS, servers: SERVERS }),
  },
];
