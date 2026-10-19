/**
 * materials.js — shared material & geometry library.
 *
 * PERFORMANCE NOTE: every mesh reuses the handful of materials declared here,
 * so the renderer can batch state changes and keep draw-call cost low.
 * All colors stay well under the 1024² texture budget by using *no* textures
 * at all on world geometry (flat-shaded vertex colors only).
 */
import * as THREE from 'three';
import {
  sandTexture, grassTexture, dirtTexture, ashTexture, stoneTexture,
  basaltTexture, clothTexture, leatherTexture, woodTexture, fleshTexture,
} from './textures.js';
import {
  skinMaterial, hairMaterial, hairCardMaterial,
  leatherMaterial, clothMaterial, metalMaterial,
} from './pbr.js';
import {
  sootAlbedoMap, crackedEarthBump, limestoneBumpMap,
} from './surfaces.js';

const cache = new Map();

function cached(key, factory) {
  if (!cache.has(key)) cache.set(key, factory());
  return cache.get(key);
}

export const Mat = {
  // --- Ziklag (ash / char) ---
  ashGround: () => cached('ashGround', () => new THREE.MeshLambertMaterial({ color: 0x8a8078, map: ashTexture([14, 12]), flatShading: true })),
  charWood: () => cached('charWood', () => new THREE.MeshLambertMaterial({ color: 0x221c18, flatShading: true })),
  charRoof: () => cached('charRoof', () => new THREE.MeshLambertMaterial({ color: 0x17120f, flatShading: true })),
  ember: () => cached('ember', () => new THREE.MeshBasicMaterial({ color: 0xff6a2a })),
  ashDebris: () => cached('ashDebris', () => new THREE.MeshLambertMaterial({ color: 0x3a322c, flatShading: true })),

  // --- Desert / arid ---
  sand: () => cached('sand', () => new THREE.MeshLambertMaterial({ color: 0xffffff, map: sandTexture([16, 30]), flatShading: true })),
  sandLight: () => cached('sandLight', () => new THREE.MeshLambertMaterial({ color: 0xf2e6c8, map: sandTexture([12, 11]), flatShading: true })),
  rock: () => cached('rock', () => new THREE.MeshLambertMaterial({ color: 0xcbb894, map: stoneTexture([3, 3], '#cdbb99', '#a8946e', '#e2d2ac'), flatShading: true })),
  rockDark: () => cached('rockDark', () => new THREE.MeshLambertMaterial({ color: 0xa8927a, map: stoneTexture([3, 3], '#6b5842', '#4a3c2c', '#8a7358'), flatShading: true })),
  water: () => cached('water', () => new THREE.MeshPhongMaterial({
    color: 0x2b7fb8,
    shininess: 90,
    specular: 0x99d4ff,
    transparent: true,
    opacity: 0.88,
  })),

  // --- Vegetation / props ---
  pottery: () => cached('pottery', () => new THREE.MeshStandardMaterial({
    color: 0xa8643e, roughness: 0.85, metalness: 0, flatShading: true,
  })),
  potteryDark: () => cached('potteryDark', () => new THREE.MeshStandardMaterial({
    color: 0x6e3e28, roughness: 0.9, metalness: 0, flatShading: true,
  })),
  acaciaBark: () => cached('acaciaBark', () => new THREE.MeshLambertMaterial({ color: 0x5a4632, flatShading: true })),
  acaciaLeaf: () => cached('acaciaLeaf', () => new THREE.MeshLambertMaterial({ color: 0x3f5a2a, flatShading: true })),
  desertScrub: () => cached('desertScrub', () => new THREE.MeshLambertMaterial({ color: 0x8a8050, flatShading: true })),
  wetStone: () => cached('wetStone', () => new THREE.MeshStandardMaterial({
    color: 0x8a8378, roughness: 0.16, metalness: 0.04,
    map: stoneTexture([3, 3], '#7d786e', '#5a564e', '#a09a8e'), envMapIntensity: 1.1,
  })),
  soot: () => cached('soot', () => new THREE.MeshStandardMaterial({
    color: 0xffffff, map: sootAlbedoMap([3, 3]), roughness: 1, metalness: 0,
    transparent: true, opacity: 0.92, depthWrite: false,
    polygonOffset: true, polygonOffsetFactor: -2,
  })),
  crackedEarth: () => cached('crackedEarth', () => new THREE.MeshStandardMaterial({
    color: 0xc9b490, roughness: 1, metalness: 0,
    map: dirtTexture([4, 4]), bumpMap: crackedEarthBump([4, 4]), bumpScale: 0.12,
    transparent: true, opacity: 0.9, depthWrite: false,
    polygonOffset: true, polygonOffsetFactor: -2,
  })),
  hewnLimestone: () => cached('hewnLimestone', () => new THREE.MeshStandardMaterial({
    color: 0xffffff, map: stoneTexture([4, 4], '#c8b596', '#a48f6e', '#e2d2b0'),
    bumpMap: limestoneBumpMap([4, 4]), bumpScale: 0.03, roughness: 0.9, metalness: 0,
  })),
  emberBed: () => cached('emberBed', () => new THREE.MeshBasicMaterial({
    color: 0xff5a1e, transparent: true, opacity: 0.5,
    blending: THREE.AdditiveBlending, depthWrite: false,
  })),
  dryBush: () => cached('dryBush', () => new THREE.MeshLambertMaterial({ color: 0x6d6a3c, flatShading: true })),
  palmTrunk: () => cached('palmTrunk', () => new THREE.MeshLambertMaterial({ color: 0x6e4f33, flatShading: true })),
  palmLeaf: () => cached('palmLeaf', () => new THREE.MeshLambertMaterial({ color: 0x4c6b2f, flatShading: true })),

  // --- Tents / camp ---
  tentCloth: () => cached('tentCloth', () => new THREE.MeshLambertMaterial({ color: 0xd8c0a0, map: clothTexture(0x8c6a44, [3, 3]), flatShading: true })),
  tentClothAlt: () => cached('tentClothAlt', () => new THREE.MeshLambertMaterial({ color: 0xc0a888, map: clothTexture(0x6e5236, [3, 3]), flatShading: true })),
  rope: () => cached('rope', () => new THREE.MeshLambertMaterial({ color: 0x7a6242 })),
  cageWood: () => cached('cageWood', () => new THREE.MeshLambertMaterial({ color: 0xcdb494, map: woodTexture(0x5c452c, [2, 2]), flatShading: true })),

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
  skin: () => cached('skin', () => new THREE.MeshLambertMaterial({ color: 0xd8b898, map: fleshTexture('#a06b45', [2, 2]) })),
  skinDark: () => cached('skinDark', () => new THREE.MeshLambertMaterial({ color: 0xb89878, map: fleshTexture('#7a5236', [2, 2]) })),
  hair: () => cached('hair', () => new THREE.MeshLambertMaterial({ color: 0x241a12 })),
  tunicDavid: () => cached('tunicDavid', () => new THREE.MeshLambertMaterial({ color: 0xd8b898, map: clothTexture(0x8c5a38, [3, 3]) })),
  sashDavid: () => cached('sashDavid', () => new THREE.MeshLambertMaterial({ color: 0x8c2f24 })),
  tunicServant: () => cached('tunicServant', () => new THREE.MeshLambertMaterial({ color: 0xc7b291 })),
  tunicAmalekite: () => cached('tunicAmalekite', () => new THREE.MeshLambertMaterial({ color: 0x7d3b2e })),
  tunicAmalekiteAlt: () => cached('tunicAmalekiteAlt', () => new THREE.MeshLambertMaterial({ color: 0x5e4632 })),
  tunicCaptive: () => cached('tunicCaptive', () => new THREE.MeshLambertMaterial({ color: 0x5b6b7a })),

  // --- PASS 8: cinematic character materials (MeshPhysical + SSS / hair cards)
  // David & named figures get these; crowd extras keep the cheap Lambert set.
  skinPbr: () => cached('skinPbr', () => skinMaterial({ albedo: '#a06b45', sssStrength: 0.34, dust: 0.06 })),
  skinPbrDark: () => cached('skinPbrDark', () => skinMaterial({ albedo: '#7a5236', sssStrength: 0.3, dust: 0.08 })),
  skinPbrWeathered: () => cached('skinPbrWeathered', () => skinMaterial({ albedo: '#8a5c3e', sssStrength: 0.26, dust: 0.14, roughness: 0.72 })),
  hairCard: () => cached('hairCard', () => hairCardMaterial({ color: '#241a12' })),
  hairCardGray: () => cached('hairCardGray', () => hairCardMaterial({ color: '#5a5048' })),
  hairPbr: () => cached('hairPbr', () => hairMaterial({ color: '#241a12' })),
  leatherPbr: () => cached('leatherPbr', () => leatherMaterial({ color: 0x3a2c20, rough: 0.76 })),
  leatherPbrWarm: () => cached('leatherPbrWarm', () => leatherMaterial({ color: 0x5a3a22, rough: 0.82 })),
  clothPbr: () => cached('clothPbr', () => clothMaterial({ color: 0x8c5a38, sheen: 0.5 })),
  clothPbrIndigo: () => cached('clothPbrIndigo', () => clothMaterial({ color: 0x4b5568, sheen: 0.55 })),
  clothPbrCream: () => cached('clothPbrCream', () => clothMaterial({ color: 0xc7b291, sheen: 0.45 })),
  clothPbrMadder: () => cached('clothPbrMadder', () => clothMaterial({ color: 0x8c2f24, sheen: 0.5 })),
  bronzePbr: () => cached('bronzePbr', () => metalMaterial({ color: 0x9a6a2e, metalness: 0.95, rough: 0.38, bump: 0.016 })),
  ironPbr: () => cached('ironPbr', () => metalMaterial({ color: 0x5a5f66, metalness: 1.0, rough: 0.34, bump: 0.014 })),
  ironPbrDark: () => cached('ironPbrDark', () => metalMaterial({ color: 0x30343a, metalness: 0.9, rough: 0.52, bump: 0.018, patina: 0.25 })),
  goldPbr: () => cached('goldPbr', () => metalMaterial({ color: 0xc99a3a, metalness: 1.0, rough: 0.28, bump: 0.01 })),

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
  limestone: () => cached('limestone', () => new THREE.MeshLambertMaterial({ color: 0xffffff, map: stoneTexture([4, 4], '#cdbb99', '#a8946e', '#e8d8b4'), flatShading: true })),
  limestonePale: () => cached('limestonePale', () => new THREE.MeshLambertMaterial({ color: 0xfff4dc, map: stoneTexture([4, 4], '#d9c9a6', '#c0ac88', '#efe2c2'), flatShading: true })),
  flagstone: () => cached('flagstone', () => new THREE.MeshLambertMaterial({ color: 0xd8d0c0, map: stoneTexture([10, 8], '#9e937f', '#7d745f', '#bdb39c'), flatShading: true })),
  basalt: () => cached('basalt', () => new THREE.MeshLambertMaterial({ color: 0x9aa0a8, map: basaltTexture([10, 10]), flatShading: true })),
  riverMud: () => cached('riverMud', () => new THREE.MeshLambertMaterial({ color: 0xffffff, map: dirtTexture([6, 6]), flatShading: true })),

  grassTurf: () => cached('grassTurf', () => new THREE.MeshLambertMaterial({ color: 0xffffff, map: grassTexture([18, 18]), flatShading: true })),

  // --- livestock / wagons / tribute (PHASE 5-6) ---
  fleece: () => cached('fleece', () => new THREE.MeshLambertMaterial({ color: 0xe6dccb })),
  goatHide: () => cached('goatHide', () => new THREE.MeshLambertMaterial({ color: 0x8d7358 })),
  cattleHide: () => cached('cattleHide', () => new THREE.MeshLambertMaterial({ color: 0x6e4b32 })),
  muleHide: () => cached('muleHide', () => new THREE.MeshLambertMaterial({ color: 0x7a6248 })),
  camelHide: () => cached('camelHide', () => new THREE.MeshLambertMaterial({ color: 0xa8875e })),
  canvasTarp: () => cached('canvasTarp', () => new THREE.MeshLambertMaterial({ color: 0xb09a72, flatShading: true })),
  cedarWood: () => cached('cedarWood', () => new THREE.MeshLambertMaterial({ color: 0xd9b494, map: woodTexture(0x6b3f24, [2, 2]), flatShading: true })),

  // --- PHASE 5-6 fine attire (saturated distribution palette) ---
  elderPurple: () => cached('elderPurple', () => new THREE.MeshLambertMaterial({ color: 0x5a2a5e })),
  elderMadder: () => cached('elderMadder', () => new THREE.MeshLambertMaterial({ color: 0x8c2f24 })),
  tunicIndigo: () => cached('tunicIndigo', () => new THREE.MeshLambertMaterial({ color: 0x4b5568 })),
  tunicEarth: () => cached('tunicEarth', () => new THREE.MeshLambertMaterial({ color: 0x6b543c })),
  tunicBattleWorn: () => cached('tunicBattleWorn', () => new THREE.MeshLambertMaterial({ color: 0x5c4a3e })),

  leatherDark: () => cached('leatherDark', () => new THREE.MeshLambertMaterial({ color: 0xb8a894, map: leatherTexture(0x3a2c20, [3, 3]) })),
  brass: () => cached('brass', () => new THREE.MeshStandardMaterial({ color: 0xb08d3e, metalness: 0.9, roughness: 0.42, flatShading: true })),

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
