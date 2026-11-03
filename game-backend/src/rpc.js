/**
 * rpc.js — the message catalogue.
 *
 * The reference does not speak REST. It speaks RPC: one message in, one
 * message out, the message *name* being the endpoint, and every request
 * paired mechanically with a response of the same name (`C…` → `S…`).
 * That naming is the one thing about the reference's network layer worth
 * copying exactly, because it makes the protocol readable without a schema
 * file and it makes code generation trivial.
 *
 * So every route in this server declares its message names, and the server
 * also exposes the whole table at /api/meta/rpc. `ref` is the reference's
 * own name for the same idea, kept so the correspondence is checkable.
 *
 * See docs/reference/04-game-protocol.md for the recovery, and
 * docs/THEME_MAPPING.md for the full mapping table.
 */

export const CATALOGUE = [
  /* --- session: the SDK's account spine -------------------------------- */
  { module: 'login', action: 'Login', request: 'CLogin', response: 'SLogin',
    method: 'POST', path: '/api/auth/login', auth: false,
    ref: 'gsdk/account/login' },
  { module: 'login', action: 'ExchangeToken', request: 'CExchangeToken', response: 'SExchangeToken',
    method: 'POST', path: '/api/auth/token', auth: true,
    ref: 'gsdk/account/exchange_token' },
  { module: 'login', action: 'DeleteRole', request: 'CDeleteRole', response: 'SDeleteRole',
    method: 'POST', path: '/api/auth/role', auth: true,
    ref: 'msg.gs.login.CDeleteRole' },
  { module: 'login', action: 'ServerList', request: 'CServerList', response: 'SServerList',
    method: 'GET', path: '/api/server/list', auth: false,
    ref: 'gsdk/game/server_list' },

  /* --- the camp: the reference's farm module, retold ------------------- */
  { module: 'encampment', action: 'GetDetail', request: 'CGetEncampmentDetail', response: 'SGetEncampmentDetail',
    method: 'GET', path: '/api/camp/detail', auth: true,
    ref: 'msg.gs.festival_activity.CGetFarmersWorldDetail' },
  { module: 'encampment', action: 'Upgrade', request: 'CEncampmentUpgrade', response: 'SEncampmentUpgrade',
    method: 'POST', path: '/api/camp/upgrade', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldBuyField (building tier)' },
  { module: 'encampment', action: 'GainQuery', request: 'CEncampmentGainQuery', response: 'SEncampmentGainQuery',
    method: 'GET', path: '/api/camp/gain/query', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldGainQuery' },
  { module: 'encampment', action: 'GainOutput', request: 'CEncampmentGainOutput', response: 'SEncampmentGainOutput',
    method: 'POST', path: '/api/camp/gain/collect', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldGainOutput' },
  { module: 'encampment', action: 'Exchange', request: 'CEncampmentExchange', response: 'SEncampmentExchange',
    method: 'POST', path: '/api/camp/exchange', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldExchange' },
  { module: 'encampment', action: 'ExchangeResource', request: 'CEncampmentExchangeResource', response: 'SEncampmentExchangeResource',
    method: 'POST', path: '/api/camp/exchange/resource', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldExchangeResource' },
  { module: 'work', action: 'Produce', request: 'CWorkProduce', response: 'SWorkProduce',
    method: 'POST', path: '/api/camp/produce', auth: true,
    ref: 'msg.gs.gloryfactory.CGloryFactoryProcess' },
  { module: 'work', action: 'Collect', request: 'CWorkCollect', response: 'SWorkCollect',
    method: 'POST', path: '/api/camp/produce/collect', auth: true,
    ref: 'msg.gs.gloryfactory.CGloryFactoryCollect' },
  { module: 'work', action: 'GetInfo', request: 'CGetWorkInfo', response: 'SGetWorkInfo',
    method: 'GET', path: '/api/camp/work', auth: true,
    ref: 'msg.gs.gloryfactory.CGetGloryFactoryInfo' },

  /* --- the band --------------------------------------------------------- */
  { module: 'troops', action: 'Recruit', request: 'CTroopsRecruit', response: 'STroopsRecruit',
    method: 'POST', path: '/api/troops/recruit', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldBuyPeople' },
  { module: 'troops', action: 'Train', request: 'CTroopsTrain', response: 'STroopsTrain',
    method: 'POST', path: '/api/troops/train', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldMakeTool (tier upgrade)' },
  { module: 'troops', action: 'Heal', request: 'CTroopsHeal', response: 'STroopsHeal',
    method: 'POST', path: '/api/troops/heal', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldRecoverHP' },
  { module: 'troops', action: 'Detail', request: 'CTroopsDetail', response: 'STroopsDetail',
    method: 'GET', path: '/api/troops', auth: true,
    ref: 'msg.gs.festival_activity.CGetFarmersWorldDetail (army part)' },

  /* --- the fight -------------------------------------------------------- */
  { module: 'combat', action: 'Pursue', request: 'CCombatPursue', response: 'SCombatPursue',
    method: 'POST', path: '/api/combat/pursue', auth: true,
    ref: 'msg.gs.combat.* (hot-updated module)' },
  { module: 'combat', action: 'Preview', request: 'CCombatPreview', response: 'SCombatPreview',
    method: 'GET', path: '/api/combat/preview', auth: true,
    ref: 'msg.gs.combat.* (hot-updated module)' },

  /* --- the spoil -------------------------------------------------------- */
  { module: 'loot', action: 'List', request: 'CLootList', response: 'SLootList',
    method: 'GET', path: '/api/loot', auth: true,
    ref: 'msg.gs.festival_activity.CFarmersWorldGainOutput (ledger)' },
  { module: 'rank', action: 'TypeRank', request: 'CTypeRank', response: 'STypeRank',
    method: 'GET', path: '/api/rank/:type', auth: false,
    ref: 'msg.gs.rank.CTypeRank' },
];

/** The catalogue as the client sees it. */
export function catalogue() {
  return CATALOGUE.map((e) => ({
    module: e.module,
    action: e.action,
    request: e.request,
    response: e.response,
    method: e.method,
    path: e.path,
    auth: e.auth,
    reference: e.ref,
  }));
}

/** Find a message by its request name. */
export function byRequest(name) {
  return CATALOGUE.find((e) => e.request === name) || null;
}

/** Find a message by module and action — the dispatcher's key. */
export function byAction(module, action) {
  const want = String(action || '').toLowerCase();
  return CATALOGUE.find((e) => e.module === module
    && e.action.toLowerCase() === want) || null;
}

/**
 * The push channel. The reference pushes `SPushFarmersWorldCanGain` and
 * `SPushGetGloryFactoryInfo` down the socket; we queue them per player and
 * the client drains the queue on every reconnect.
 */
export const PUSHES = [
  { name: 'SPushEncampmentCanGain', ref: 'msg.gs.festival_activity.SPushFarmersWorldCanGain',
    when: 'the works have ripened' },
  { name: 'SPushWorkInfo', ref: 'msg.gs.gloryfactory.SPushGetGloryFactoryInfo',
    when: 'a production slot finishes' },
  { name: 'SPushTroopsReady', ref: 'msg.gs.festival_activity.SPushFarmersWorldCanGain (army)',
    when: 'wounded are fit to return to the line' },
];
