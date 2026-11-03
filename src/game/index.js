/**
 * game/index.js — starting and stopping the 2D camp.
 *
 * The camp is a canvas that lives inside the page, beside the 3D march.
 * It is started when the player chooses Ziklag and torn down when they
 * take the road, so the two never fight over the pointer or the frame
 * budget.
 */

import Phaser from 'phaser';
import { createConfig, PARENT_ID } from './config.js';
import { openSession, closeSession, sessionApi, sessionLeave } from './session.js';

let game = null;

/** The fonts are loaded by the page; wait for them before the first paint. */
async function fontsReady() {
  try {
    if (typeof document !== 'undefined' && document.fonts && document.fonts.ready) {
      await document.fonts.ready;
    }
  } catch (_e) { /* no font loading API: the serif falls back */ }
}

/**
 * Start the camp.
 * @param {object} [opts]
 * @param {Function} [opts.onLeave]   called when the player takes the road
 * @param {Function} [opts.onCitadel] called when the player asks for the citadel
 * @param {string} [opts.parent]      the container's id
 * @returns {Promise<object>} the Phaser game
 */
export async function launchCamp(opts = {}) {
  if (game) return game;
  await fontsReady();

  const parent = opts.parent || PARENT_ID;
  const container = typeof document !== 'undefined' ? document.getElementById(parent) : null;
  if (!container) return null;

  openSession({
    base: opts.base || '/api',
    onLeave: opts.onLeave || null,
    onCitadel: opts.onCitadel || null,
  });

  container.classList.remove('hidden');
  if (typeof document !== 'undefined') document.body.classList.add('phaser-mode');

  const config = createConfig(parent);
  game = new Phaser.Game(config);
  return game;
}

/** Stop the camp and give the page back to the 3D march. */
export function stopCamp() {
  if (game) {
    game.destroy(true);
    game = null;
  }
  closeSession();
  if (typeof document !== 'undefined') {
    const container = document.getElementById(PARENT_ID);
    if (container) container.classList.add('hidden');
    document.body.classList.remove('phaser-mode');
  }
}

/** Is the camp on screen? */
export function campRunning() {
  return Boolean(game);
}

export { sessionApi, sessionLeave };
