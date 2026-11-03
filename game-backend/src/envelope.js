/**
 * envelope.js — the response envelope and the error vocabulary.
 *
 * Recovered from the decompiled SDK (com.bytedance.ttgame.base.BaseResponse
 * and com.bytedance.ttgame.module.gameinfo.GameInfoResponse):
 *
 *     { "code": 0, "message": "ok", "data": { ... } }
 *
 * `code == 0` means success. The error codes below are the SDK's own
 * GameInfoErrorCode numbers, kept verbatim so that a client written against
 * the reference's semantics reads our server without a translation table.
 */

export const CODE = Object.freeze({
  OK: 0,
  PARAM_ERROR: -130001,
  TOKEN_INVALID: -130002,
  SERVER_LIST_EMPTY: -130003,
  OPEN_ID_ERROR: -130004,
  RISK_CONTROL: -130005,
  SERVER_ERROR: -130006,
  NETWORK_ERROR: -133001,
  UNKNOWN_ERROR: -139999,
});

/** Human text for each code, exactly as the SDK words it. */
export const CODE_TEXT = Object.freeze({
  0: 'ok',
  '-130001': 'parameter error',
  '-130002': 'token invalid',
  '-130003': 'server list empty',
  '-130004': 'open id error',
  '-130005': 'risk control',
  '-130006': 'server error',
  '-133001': 'network error',
  '-139999': 'unknown error',
});

/**
 * The envelope. Every route answers with this shape and nothing else.
 * @param {object} [data]
 * @param {number} [code]
 * @param {string} [message]
 */
export function ok(data = {}, code = CODE.OK, message = 'ok') {
  return { code, message, data };
}

/** A failure, in the same shape. */
export function fail(code, message) {
  return {
    code,
    message: message || CODE_TEXT[code] || CODE_TEXT[CODE.UNKNOWN_ERROR],
    data: null,
  };
}

/**
 * The push channel. The reference pushes `SPushFarmersWorldCanGain` and
 * `SPushGetGloryFactoryInfo` down the socket; ours is a pollable queue the
 * client drains on every reconnect (see docs/CLIENT_ARCHITECTURE.md).
 */
export function push(name, payload = {}) {
  return { name, payload, at: new Date().toISOString() };
}
