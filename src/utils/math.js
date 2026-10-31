/**
 * math.js — small math helpers shared across the game.
 */

/** Wrap an angle to (-PI, PI]. */
export function wrapAngle(a) {
  a = a % (Math.PI * 2);
  if (a > Math.PI) a -= Math.PI * 2;
  if (a < -Math.PI) a += Math.PI * 2;
  return a;
}

/** Exponential smoothing factor for frame-rate independent lerps. */
export function dampFactor(smoothing, dt) {
  return 1 - Math.exp(-smoothing * dt);
}

/** Smoothly rotate `current` toward `target` by a damped step. */
export function lerpAngle(current, target, factor) {
  return current + wrapAngle(target - current) * factor;
}

export function clamp(v, min, max) {
  return Math.max(min, Math.min(max, v));
}

export function lerp(a, b, t) {
  return a + (b - a) * t;
}

/** Deterministic pseudo-random in [0,1) from an integer seed (stable worlds). */
export function seededRandom(seed) {
  const x = Math.sin(seed * 127.1 + 311.7) * 43758.5453;
  return x - Math.floor(x);
}

export function randRange(min, max) {
  return min + Math.random() * (max - min);
}

/** Horizontal distance between two THREE.Vector3-compatible points. */
export function distanceXZ(a, b) {
  const dx = a.x - b.x;
  const dz = a.z - b.z;
  return Math.sqrt(dx * dx + dz * dz);
}

/** Compass label from a bearing in degrees (0 = North = -Z). */
export function compassLabel(degrees) {
  const dirs = ['N', 'NE', 'E', 'SE', 'S', 'SW', 'W', 'NW'];
  const idx = Math.round(((degrees % 360) + 360) % 360 / 45) % 8;
  return dirs[idx];
}
