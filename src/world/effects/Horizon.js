/**
 * Horizon.js — the distant hills that end the world instead of a fog wall.
 *
 * A ground plane that simply dissolves into fog reads as a stage. Real
 * landscapes end in layered silhouettes: nearer ridges with detail, farther
 * ridges flattened into the haze by the air between. Two unlit rings do that
 * for a fraction of a millisecond:
 *
 *   far ring   radius 385, taller, 60 % dissolved into the horizon colour
 *   near ring  radius 300, lower,  35 % dissolved — enough to read as distance
 *
 * Both are built from a seeded noise profile so every phase has its own
 * skyline that never repeats and never shimmers (the profile is fixed at build
 * time, so the silhouette is stable frame to frame).
 *
 * Unlit on purpose: aerial perspective is not something a directional light
 * should decide. The colours are pre-mixed with the phase's haze.
 */
import * as THREE from 'three';
import { seededRandom } from '../../utils/math.js';

/** Smooth periodic ridge profile: two octaves of value noise around a ring. */
function ridgeProfile(segments, seed, octaves = 2) {
  const out = new Float32Array(segments + 1);
  for (let i = 0; i <= segments; i++) {
    const a = (i / segments) * Math.PI * 2;
    let v = 0;
    let amp = 1;
    let norm = 0;
    for (let o = 0; o < octaves; o++) {
      const f = 2 + o * 3; // frequency in whole cycles → seamless wrap
      v += Math.sin(a * f + seededRandom(seed + o * 17.3) * Math.PI * 2) * amp;
      norm += amp;
      amp *= 0.55;
    }
    v = v / (norm || 1);
    // sharpen the crests a little; valleys stay broad like eroded foothills
    out[i] = Math.pow(Math.max(0, v * 0.5 + 0.5), 1.6);
  }
  return out;
}

function buildRing({
  radius, minHeight, maxHeight, segments, seed, hazeColor, tint, dissolve, yBase,
}) {
  const profile = ridgeProfile(segments, seed, 2);
  const positions = new Float32Array((segments + 1) * 2 * 3);
  const colors = new Float32Array((segments + 1) * 2 * 3);
  const indices = [];

  const haze = new THREE.Color(hazeColor);
  const rock = new THREE.Color(tint);
  // the crest keeps more of the rock tone, the base is pure haze
  const crest = haze.clone().lerp(rock, 1 - dissolve);
  const foot = haze.clone();

  for (let i = 0; i <= segments; i++) {
    const a = (i / segments) * Math.PI * 2;
    const x = Math.cos(a) * radius;
    const z = Math.sin(a) * radius;
    const top = minHeight + profile[i] * (maxHeight - minHeight);

    const vi = i * 2;
    positions.set([x, yBase, z], vi * 3);
    positions.set([x, top, z], (vi + 1) * 3);

    colors.set([foot.r, foot.g, foot.b], vi * 3);
    // slight vertical gradient: haze gathers at the foot of a far ridge
    const c = foot.clone().lerp(crest, 0.55 + profile[i] * 0.45);
    colors.set([c.r, c.g, c.b], (vi + 1) * 3);

    if (i < segments) {
      const a0 = vi;
      const a1 = vi + 1;
      const b0 = vi + 2;
      const b1 = vi + 3;
      indices.push(a0, a1, b0, a1, b1, b0);
    }
  }

  const geo = new THREE.BufferGeometry();
  geo.setAttribute('position', new THREE.BufferAttribute(positions, 3));
  geo.setAttribute('color', new THREE.BufferAttribute(colors, 3));
  geo.setIndex(indices);
  geo.computeBoundingSphere();

  const mat = new THREE.MeshBasicMaterial({
    vertexColors: true,
    side: THREE.DoubleSide, // we stand inside the ring; winding must not matter
    fog: false,
    depthWrite: true,
  });

  const mesh = new THREE.Mesh(geo, mat);
  mesh.name = 'HORIZON_RIDGE';
  mesh.frustumCulled = false;
  mesh.renderOrder = -6; // after the dome, before the world
  return mesh;
}

/**
 * The plain the zone's terrain sits on.
 *
 * Zones are finite planes (130 × 300 m at most). Without something under them
 * you eventually see the world's edge lift away from the sky like a floating
 * island. This is a single 560 m disc, dropped below every zone's lowest point
 * and painted in the phase's own haze colour, so exp² fog dissolves it into the
 * horizon with no seam: terrain, plain, ridges, sky — one continuous distance.
 */
function buildHazePlain(atmo) {
  const geo = new THREE.CircleGeometry(560, 48);
  geo.rotateX(-Math.PI / 2);
  const mat = new THREE.MeshBasicMaterial({
    color: atmo.fog?.color ?? 0x9ea8b2,
    fog: true,
    depthWrite: true,
  });
  const mesh = new THREE.Mesh(geo, mat);
  mesh.position.y = -2.6; // below the lowest wadi bank in any zone
  mesh.name = 'HAZE_PLAIN';
  mesh.renderOrder = -7;
  mesh.frustumCulled = false;
  return mesh;
}

/**
 * @param {object} atmo preset from world/atmospheres.js
 * @param {{seed?: number, tint?: number, farOnly?: boolean}} [opts]
 * @returns {THREE.Group}
 */
export function createHorizon(atmo, opts = {}) {
  const group = new THREE.Group();
  group.name = 'HORIZON';
  const seed = opts.seed ?? 11;
  const tint = opts.tint ?? 0x3d4654;
  const haze = atmo.fog?.color ?? 0x9ea8b2;
  const night = (atmo.sky?.stars ?? 0) > 0.5;

  // the haze plain first: it is drawn behind everything else in the group
  group.add(buildHazePlain(atmo));

  // far range: high, heavily dissolved
  group.add(buildRing({
    radius: 385,
    minHeight: 14,
    maxHeight: 46,
    segments: 84,
    seed: seed + 3.1,
    hazeColor: haze,
    tint,
    dissolve: night ? 0.72 : 0.60,
    yBase: -14,
  }));

  if (!opts.farOnly) {
    // near range: lower, darker, more silhouette
    group.add(buildRing({
      radius: 300,
      minHeight: 6,
      maxHeight: 24,
      segments: 96,
      seed: seed + 9.7,
      hazeColor: haze,
      tint,
      dissolve: night ? 0.55 : 0.40,
      yBase: -12,
    }));
  }

  return group;
}
