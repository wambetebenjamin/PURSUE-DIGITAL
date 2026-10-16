/**
 * quality.js — device detection and performance profile.
 *
 * Phones get a lighter render budget: lower pixel ratio, 1024² shadows,
 * no MSAA (post-processing already smooths edges), and ~45% particle /
 * grass / creature density. Desktop keeps the full experience.
 * Safe in headless Node (smoke test) — all probes are guarded.
 */

const hasWindow = typeof window !== 'undefined';
const nav = typeof navigator !== 'undefined' ? navigator : null;

export const IS_TOUCH = Boolean(
  hasWindow && (nav?.maxTouchPoints > 0 || 'ontouchstart' in window),
);

export const IS_SMALL_SCREEN = hasWindow
  ? Math.min(window.innerWidth || 2200, window.innerHeight || 1400) < 820
  : false;

export const IS_MOBILE = IS_TOUCH && IS_SMALL_SCREEN;

export const QUALITY = Object.freeze({
  /** Cap on devicePixelRatio. */
  pixelRatio: IS_MOBILE ? 1.3 : 1.75,
  /** Sun shadow-map resolution (per side). */
  shadowMap: IS_MOBILE ? 1024 : 2048,
  /** Multiplier for particles, grass, herds, butterflies, birds. */
  density: IS_MOBILE ? 0.45 : 1,
  /** MSAA on the base canvas (mobile relies on post-processing instead). */
  antialias: !IS_MOBILE,
});

/** Scale a requested count by the device density, with a sane floor. */
export function qualityCount(count, floor = 24) {
  return Math.max(floor, Math.round(count * QUALITY.density));
}
