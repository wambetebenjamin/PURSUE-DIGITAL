/**
 * materials.js — shared material & geometry library.
 *
 * PERFORMANCE NOTE: every mesh reuses the handful of materials declared here,
 * so the renderer can batch state changes and keep draw-call cost low.
 * All colors stay well under the 1024² texture budget by using *no* textures
 * at all on world geometry (flat-shaded vertex colors only).
 */
import * as THREE from 'three';

const cache = new Map();

function cached(key, factory) {
  if (!cache.has(key)) cache.set(key, factory());
  return cache.get(key);
}

export const Mat = {
  // --- Ziklag (ash / char) ---
  ashGround: () => cached('ashGround', () => new THREE.MeshLambertMaterial({ color: 0x2e2a27, flatShading: true })),
  charWood: () => cached('charWood', () => new THREE.MeshLambertMaterial({ color: 0x221c18, flatShading: true })),
  charRoof: () => cached('charRoof', () => new THREE.MeshLambertMaterial({ color: 0x17120f, flatShading: true })),
  ember: () => cached('ember', () => new THREE.MeshBasicMaterial({ color: 0xff6a2a })),
  ashDebris: () => cached('ashDebris', () => new THREE.MeshLambertMaterial({ color: 0x3a322c, flatShading: true })),

  // --- Desert / arid ---
  sand: () => cached('sand', () => new THREE.MeshLambertMaterial({ color: 0xc9a86c, flatShading: true })),
  sandLight: () => cached('sandLight', () => new THREE.MeshLambertMaterial({ color: 0xd9be86, flatShading: true })),
  rock: () => cached('rock', () => new THREE.MeshLambertMaterial({ color: 0x8a7358, flatShading: true })),
  rockDark: () => cached('rockDark', () => new THREE.MeshLambertMaterial({ color: 0x6b5842, flatShading: true })),
  water: () => cached('water', () => new THREE.MeshPhongMaterial({
    color: 0x2b7fb8,
    shininess: 90,
    specular: 0x99d4ff,
    transparent: true,
    opacity: 0.88,
  })),

  // --- Vegetation / props ---
  dryBush: () => cached('dryBush', () => new THREE.MeshLambertMaterial({ color: 0x6d6a3c, flatShading: true })),
  palmTrunk: () => cached('palmTrunk', () => new THREE.MeshLambertMaterial({ color: 0x6e4f33, flatShading: true })),
  palmLeaf: () => cached('palmLeaf', () => new THREE.MeshLambertMaterial({ color: 0x4c6b2f, flatShading: true })),

  // --- Tents / camp ---
  tentCloth: () => cached('tentCloth', () => new THREE.MeshLambertMaterial({ color: 0xa8815a, flatShading: true })),
  tentClothAlt: () => cached('tentClothAlt', () => new THREE.MeshLambertMaterial({ color: 0x8d6b47, flatShading: true })),
  rope: () => cached('rope', () => new THREE.MeshLambertMaterial({ color: 0x7a6242 })),
  cageWood: () => cached('cageWood', () => new THREE.MeshLambertMaterial({ color: 0x5c452c, flatShading: true })),

  // --- Fire / glow (unlit, emissive-look) ---
  flame: () => cached('flame', () => new THREE.MeshBasicMaterial({ color: 0xff8c2a })),
  flameCore: () => cached('flameCore', () => new THREE.MeshBasicMaterial({ color: 0xffd25e })),
  beacon: () => cached('beacon', () => new THREE.MeshBasicMaterial({
    color: 0x53e0ff,
    transparent: true,
    opacity: 0.55,
    depthWrite: false,
    blending: THREE.AdditiveBlending,
  })),
  beaconRing: () => cached('beaconRing', () => new THREE.MeshBasicMaterial({
    color: 0x53e0ff,
    transparent: true,
    opacity: 0.8,
    side: THREE.DoubleSide,
    depthWrite: false,
    blending: THREE.AdditiveBlending,
  })),

  // --- Characters ---
  skin: () => cached('skin', () => new THREE.MeshLambertMaterial({ color: 0x9c6b45, flatShading: true })),
  skinDark: () => cached('skinDark', () => new THREE.MeshLambertMaterial({ color: 0x714a2e, flatShading: true })),
  hair: () => cached('hair', () => new THREE.MeshLambertMaterial({ color: 0x241a12, flatShading: true })),
  tunicDavid: () => cached('tunicDavid', () => new THREE.MeshLambertMaterial({ color: 0xb9805c, flatShading: true })),
  sashDavid: () => cached('sashDavid', () => new THREE.MeshLambertMaterial({ color: 0x8c2f24, flatShading: true })),
  tunicServant: () => cached('tunicServant', () => new THREE.MeshLambertMaterial({ color: 0xc7b291, flatShading: true })),
  tunicAmalekite: () => cached('tunicAmalekite', () => new THREE.MeshLambertMaterial({ color: 0x7d3b2e, flatShading: true })),
  tunicAmalekiteAlt: () => cached('tunicAmalekiteAlt', () => new THREE.MeshLambertMaterial({ color: 0x5e4632, flatShading: true })),
  tunicCaptive: () => cached('tunicCaptive', () => new THREE.MeshLambertMaterial({ color: 0x5b6b7a, flatShading: true })),

  // --- PHASE 4-7 PBR accents (prompt-specified Metallic/Roughness values) ---
  // "Blood-spattered steel (Metallic 1.0, Roughness 0.15)"
  steelBloody: () => cached('steelBloody', () => new THREE.MeshStandardMaterial({
    color: 0x6d7076, metalness: 1.0, roughness: 0.15, flatShading: true,
  })),
  // "tarnished, dented bronze scales (Metallic 0.8, Roughness 0.4)"
  royalBronze: () => cached('royalBronze', () => new THREE.MeshStandardMaterial({
    color: 0x8a5c28, metalness: 0.8, roughness: 0.4, flatShading: true,
  })),
  // "spilled liquids with zero roughness"
  spill: () => cached('spill', () => new THREE.MeshStandardMaterial({
    color: 0x2a1408, metalness: 0.15, roughness: 0.0,
    transparent: true, opacity: 0.85,
  })),
  // "micro-reflections on piles of pillaged, polished silver and brass"
  silverLoot: () => cached('silverLoot', () => new THREE.MeshStandardMaterial({
    color: 0xc9ccd4, metalness: 1.0, roughness: 0.12, flatShading: true,
  })),
  goldLoot: () => cached('goldLoot', () => new THREE.MeshStandardMaterial({
    color: 0xd8a63a, metalness: 1.0, roughness: 0.22, flatShading: true,
  })),
  bronzeBand: () => cached('bronzeBand', () => new THREE.MeshStandardMaterial({
    color: 0x7a5c2e, metalness: 0.9, roughness: 0.45, flatShading: true,
  })),
  ironDark: () => cached('ironDark', () => new THREE.MeshStandardMaterial({
    color: 0x2c2c30, metalness: 0.85, roughness: 0.55, flatShading: true,
  })),

  // --- PHASE 5-7 architecture & landscape (limestone / basalt / flagstone) ---
  limestone: () => cached('limestone', () => new THREE.MeshLambertMaterial({ color: 0xcdbb99, flatShading: true })),
  limestonePale: () => cached('limestonePale', () => new THREE.MeshLambertMaterial({ color: 0xd9c9a6, flatShading: true })),
  flagstone: () => cached('flagstone', () => new THREE.MeshLambertMaterial({ color: 0x9e937f, flatShading: true })),
  basalt: () => cached('basalt', () => new THREE.MeshLambertMaterial({ color: 0x3a3d42, flatShading: true })),
  riverMud: () => cached('riverMud', () => new THREE.MeshLambertMaterial({ color: 0x6b543c, flatShading: true })),

  // --- livestock / wagons / tribute (PHASE 5-6) ---
  fleece: () => cached('fleece', () => new THREE.MeshLambertMaterial({ color: 0xe6dccb, flatShading: true })),
  goatHide: () => cached('goatHide', () => new THREE.MeshLambertMaterial({ color: 0x8d7358, flatShading: true })),
  cattleHide: () => cached('cattleHide', () => new THREE.MeshLambertMaterial({ color: 0x6e4b32, flatShading: true })),
  muleHide: () => cached('muleHide', () => new THREE.MeshLambertMaterial({ color: 0x7a6248, flatShading: true })),
  camelHide: () => cached('camelHide', () => new THREE.MeshLambertMaterial({ color: 0xa8875e, flatShading: true })),
  canvasTarp: () => cached('canvasTarp', () => new THREE.MeshLambertMaterial({ color: 0xb09a72, flatShading: true })),
  cedarWood: () => cached('cedarWood', () => new THREE.MeshLambertMaterial({ color: 0x6b3f24, flatShading: true })),

  // --- PHASE 5-6 fine attire (saturated distribution palette) ---
  elderPurple: () => cached('elderPurple', () => new THREE.MeshLambertMaterial({ color: 0x5a2a5e, flatShading: true })),
  elderMadder: () => cached('elderMadder', () => new THREE.MeshLambertMaterial({ color: 0x8c2f24, flatShading: true })),
  tunicIndigo: () => cached('tunicIndigo', () => new THREE.MeshLambertMaterial({ color: 0x4b5568, flatShading: true })),
  tunicEarth: () => cached('tunicEarth', () => new THREE.MeshLambertMaterial({ color: 0x6b543c, flatShading: true })),
  tunicBattleWorn: () => cached('tunicBattleWorn', () => new THREE.MeshLambertMaterial({ color: 0x5c4a3e, flatShading: true })),

  // --- Misc shared ---
  alertCone: () => cached('alertCone', () => new THREE.MeshBasicMaterial({
    color: 0xffd25e,
    transparent: true,
    opacity: 0.16,
    side: THREE.DoubleSide,
    depthWrite: false,
  })),
  arrowGlow: () => cached('arrowGlow', () => new THREE.MeshBasicMaterial({
    color: 0x59f0c8,
    transparent: true,
    opacity: 0.92,
  })),
};

/** Shared low-poly geometries (reused across every instance). */
export const Geo = {
  box: () => cached('g_box', () => new THREE.BoxGeometry(1, 1, 1)),
  sphere: () => cached('g_sphere', () => new THREE.SphereGeometry(0.5, 10, 8)),
  cyl: () => cached('g_cyl', () => new THREE.CylinderGeometry(0.5, 0.5, 1, 8)),
  cone: () => cached('g_cone', () => new THREE.ConeGeometry(0.5, 1, 8)),
  dodeca: () => cached('g_dodeca', () => new THREE.DodecahedronGeometry(0.5, 0)),
  ico: () => cached('g_ico', () => new THREE.IcosahedronGeometry(0.5, 0)),
  plane: () => cached('g_plane', () => new THREE.PlaneGeometry(1, 1)),
  ring: () => cached('g_ring', () => new THREE.RingGeometry(0.42, 0.5, 32)),
  torus: () => cached('g_torus', () => new THREE.TorusGeometry(0.5, 0.08, 6, 20)),
};
