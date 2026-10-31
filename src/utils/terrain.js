/**
 * terrain.js — large-scale color variation for ground meshes.
 *
 * Painting vertex colors over the tiled canvas textures breaks the
 * "one flat plane" look: drier ridges, greener hollows, damp patches.
 */
import * as THREE from 'three';
import { seededRandom } from './math.js';

/** Cheap 2-D value noise from the seeded RNG (0..1). */
function vnoise(x, y, seed) {
  const x0 = Math.floor(x); const y0 = Math.floor(y);
  const fx = x - x0; const fy = y - y0;
  const s = (a, b) => seededRandom(seed + a * 127.1 + b * 311.7);
  const u = fx * fx * (3 - 2 * fx);
  const v = fy * fy * (3 - 2 * fy);
  const n00 = s(x0, y0);
  const n10 = s(x0 + 1, y0);
  const n01 = s(x0, y0 + 1);
  const n11 = s(x0 + 1, y0 + 1);
  return (n00 * (1 - u) + n10 * u) * (1 - v) + (n01 * (1 - u) + n11 * u) * v;
}

/**
 * Write a `color` attribute onto a ground geometry (expects XZ plane).
 * Blends three tones by fractal noise and slope.
 */
export function applyTerrainColors(geo, {
  colorA = new THREE.Color(0xffffff), // highs / dry
  colorB = new THREE.Color(0xd0c0a0), // lows / green
  colorC = new THREE.Color(0x8a7860), // damp / dark
  scale = 0.06,
  strength = 0.55,
  seed = 7,
} = {}) {
  const pos = geo.attributes.position;
  const colors = new Float32Array(pos.count * 3);
  const c = new THREE.Color();
  for (let i = 0; i < pos.count; i++) {
    const x = pos.getX(i);
    const z = pos.getZ(i);
    const h = pos.getY(i);
    const n = vnoise(x * scale, z * scale, seed) * 0.65
      + vnoise(x * scale * 2.7 + 13, z * scale * 2.7 + 41, seed + 9) * 0.35;
    const damp = vnoise(x * scale * 1.4 + 71, z * scale * 1.4 + 19, seed + 31);
    c.copy(colorA).lerp(colorB, Math.min(1, Math.max(0, (n - 0.28) * 1.9)));
    if (damp > 0.62) c.lerp(colorC, Math.min(1, (damp - 0.62) * 2.2));
    // subtle high-altitude dryness
    c.multiplyScalar(1 + h * 0.012 * strength);
    colors[i * 3] = c.r;
    colors[i * 3 + 1] = c.g;
    colors[i * 3 + 2] = c.b;
  }
  geo.setAttribute('color', new THREE.BufferAttribute(colors, 3));
  return geo;
}
