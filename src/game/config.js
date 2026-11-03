/**
 * game/config.js — the Phaser configuration.
 *
 * The canvas attaches to `#game-container` and scales with the window: the
 * scenes are laid out in a design space of 1280 × 720 and stretched to
 * whatever the canvas actually is, so the camp stands on a phone in
 * landscape as well as on a wide monitor.
 *
 * Phaser owns no audio here — the 3D build already has an audio engine, and
 * two of them fighting over the sound would be worse than one.
 */

import Phaser from 'phaser';
import CampScene from './CampScene.js';
import PursuitScene from './PursuitScene.js';

/** The id of the container the canvas is injected into. */
export const PARENT_ID = 'game-container';

/**
 * Build the configuration.
 * @param {string} [parent] the container's id
 */
export function createConfig(parent = PARENT_ID) {
  const size = (typeof window !== 'undefined' && window.innerWidth)
    ? { width: window.innerWidth, height: window.innerHeight }
    : { width: 1280, height: 720 };
  return {
    type: Phaser.AUTO,
    parent,
    backgroundColor: '#251116',
    scale: {
      mode: Phaser.Scale.RESIZE,
      autoCenter: Phaser.Scale.CENTER_BOTH,
      width: size.width,
      height: size.height,
    },
    render: {
      antialias: true,
      roundPixels: false,
      powerPreference: 'low-power',
    },
    // the 3D build owns the sound
    audio: { noAudio: true },
    banner: false,
    scene: [CampScene, PursuitScene],
  };
}

export default createConfig;
