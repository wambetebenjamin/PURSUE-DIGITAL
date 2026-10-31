/**
 * CharacterRig.js — detailed low-poly human builder.
 *
 * Anatomy is built from tapered cylinders, sculpted sphere masses (skull,
 * jaw, brow, cheeks, deltoids, calves), layered garments, lamellar armor
 * plates and strapped sandals, then merged per material inside each animated
 * pivot so a full character costs ~8 draw calls.
 *
 * Pivot contract (used by zones + Player):
 *   parts = { armL, armR, legL, legR, head, torso }
 * `torso` is a group at y=1.14 (breathing offset target), `head` is its
 * child so the head follows the chest.
 */
import * as THREE from 'three';
import { mergeGeometries } from 'three/addons/utils/BufferGeometryUtils.js';
import { seededRandom } from '../utils/math.js';

const sphere = () => new THREE.SphereGeometry(0.5, 10, 8);
const box = () => new THREE.BoxGeometry(1, 1, 1);
const cyl = (rt = 0.5, rb = 0.5, h = 1, seg = 8) => new THREE.CylinderGeometry(rt, rb, h, seg);
const cone = (r = 0.5, h = 1, seg = 8) => new THREE.ConeGeometry(r, h, seg);
/** Hair card: a single quad, UV-mapped for the strand alpha map. */
const card = () => new THREE.PlaneGeometry(1, 1);

const _dummy = new THREE.Object3D();

/** Bake a transform into a fresh geometry and pair it with a material. */
function piece(list, mat, geo, x = 0, y = 0, z = 0, rx = 0, ry = 0, rz = 0, sx = 1, sy = 1, sz = 1) {
  _dummy.position.set(x, y, z);
  _dummy.rotation.set(rx, ry, rz);
  _dummy.scale.set(sx, sy, sz);
  _dummy.updateMatrix();
  geo.applyMatrix4(_dummy.matrix);
  list.push({ geo, mat });
}

/** Merge all pieces of one pivot, one mesh per material. */
function buildPivot(entries) {
  const byMat = new Map();
  for (const e of entries) {
    if (!byMat.has(e.mat)) byMat.set(e.mat, []);
    byMat.get(e.mat).push(e.geo);
  }
  const meshes = [];
  for (const [mat, geos] of byMat) {
    const merged = mergeGeometries(geos, false);
    const mesh = new THREE.Mesh(merged, mat);
    mesh.castShadow = true;
    mesh.receiveShadow = false;
    meshes.push(mesh);
  }
  return meshes;
}

/**
 * @param {Object} opts
 * @param {{skin:THREE.Material, cloth:THREE.Material, cloth2?:THREE.Material,
 *          hair?:THREE.Material, leather?:THREE.Material, metal?:THREE.Material,
 *          accent?:THREE.Material}} opts.palette
 * @param {'standing'|'sitting'|'celebrating'|'seatedCaptive'|'armsCrossed'|
 *         'grieving'|'slumpedWounded'|'reclining'} [opts.pose]
 * @param {'full'|'gray'|'none'} [opts.beard]
 * @param {'short'|'long'|'balding'|'covered'} [opts.hairStyle]
 * @param {'none'|'leather'|'bronze'|'iron'} [opts.armor]
 * @param {boolean} [opts.robe] long elder robe covering the legs
 * @param {'full'|'low'} [opts.detail] 'low' skips eyes/rivets (crowd figures)
 */
export function createDetailedHuman(opts = {}) {
  const {
    palette = {},
    pose = 'standing',
    beard = 'none',
    hairStyle = 'short',
    armor = 'none',
    robe = false,
    detail = 'full',
    seed = 1,
  } = opts;

  const skin = palette.skin;
  const cloth = palette.cloth;
  const cloth2 = palette.cloth2 ?? cloth;
  const hair = palette.hair;
  const leather = palette.leather ?? cloth2;
  const metal = palette.metal ?? leather;
  const accent = palette.accent ?? cloth2;
  const hairCard = palette.hairCard ?? null;
  const full = detail === 'full';
  // a stable per-figure seed drives the natural asymmetry of the face
  const r = (n) => seededRandom(seed * 7.13 + n);

  // per-pivot accumulators
  const P = {
    torso: [], head: [], armL: [], armR: [], legL: [], legR: [],
  };

  // =============================================================== torso
  piece(P.torso, skin, cyl(0.17, 0.135, 0.34, 10), 0, 0.12, 0, 0, 0, 0, 1.35, 1, 0.78);
  piece(P.torso, skin, sphere(), 0, 0.27, 0.015, 0, 0, 0, 0.235, 0.16, 0.145);
  piece(P.torso, skin, sphere(), 0, -0.16, 0, 0, 0, 0, 0.155, 0.16, 0.115);
  piece(P.torso, cloth, box(), 0, -0.36, 0, 0, 0, 0, 0.30, 0.17, 0.20);
  // tunic skirt — the silhouette anchor
  piece(P.torso, cloth, cyl(0.185, robe ? 0.315 : 0.27, robe ? 0.82 : 0.46, 12),
    0, robe ? -0.72 : -0.55, 0, 0, 0, 0, 1.22, 1, 0.85);
  // belt
  piece(P.torso, leather, cyl(0.198, 0.198, 0.075, 12), 0, -0.30, 0, 0, 0, 0, 1.26, 1, 0.85);
  // diagonal sash
  piece(P.torso, accent, box(), 0.03, 0.08, 0.145, 0, 0, 0.72, 0.105, 0.66, 0.032);
  // neck
  piece(P.torso, skin, cyl(0.082, 0.098, 0.17, 8), 0, 0.365, 0.01);
  // deltoids
  piece(P.torso, cloth, sphere(), -0.315, 0.27, 0, 0, 0, 0, 0.135, 0.105, 0.12);
  piece(P.torso, cloth, sphere(), 0.315, 0.27, 0, 0, 0, 0, 0.135, 0.105, 0.12);

  // ------------------------------------------------------ armor overlay
  if (armor === 'leather') {
    // lamellar rows + brass rivets
    for (let row = 0; row < 3; row++) {
      for (let col = -1; col <= 1; col++) {
        const x = col * 0.155;
        const y = 0.19 - row * 0.125;
        const z = 0.155 - Math.abs(col) * 0.035;
        piece(P.torso, leather, box(), x, y, z, -0.12 + row * 0.05, col * 0.18, 0, 0.125, 0.115, 0.05);
        if (full) {
          piece(P.torso, metal, sphere(), x - 0.04, y + 0.035, z + 0.03, 0, 0, 0, 0.016, 0.016, 0.012);
          piece(P.torso, metal, sphere(), x + 0.04, y + 0.035, z + 0.03, 0, 0, 0, 0.016, 0.016, 0.012);
        }
      }
    }
    piece(P.torso, leather, sphere(), -0.345, 0.30, 0, 0, 0, 0.25, 0.15, 0.085, 0.135);
    piece(P.torso, leather, sphere(), 0.345, 0.30, 0, 0, 0, -0.25, 0.15, 0.085, 0.135);
    // bandolier + bronze buckle, belt pouch, hanging amulet
    piece(P.torso, leather, box(), 0.04, 0.10, 0.155, 0, 0, 0.66, 0.085, 0.62, 0.028);
    piece(P.torso, metal, box(), 0.015, -0.12, 0.16, 0, 0, 0.66, 0.07, 0.07, 0.026);
    piece(P.torso, leather, box(), -0.19, -0.27, 0.12, 0, 0.4, 0, 0.12, 0.15, 0.075);
    piece(P.torso, metal, sphere(), 0, 0.17, 0.19, 0, 0, 0, 0.028, 0.028, 0.012);
    // arm bracers + knee straps are added in the limb loops below
  } else if (armor === 'bronze') {
    // royal scale cuirass — "tarnished, dented bronze scales"
    for (let row = 0; row < 4; row++) {
      for (let col = -1; col <= 1; col++) {
        const x = col * 0.15;
        const y = 0.24 - row * 0.115;
        const z = 0.16 - Math.abs(col) * 0.03;
        piece(P.torso, metal, box(), x, y, z, -0.1 + row * 0.04, col * 0.15, 0, 0.115, 0.11, 0.045);
      }
    }
    piece(P.torso, metal, sphere(), -0.35, 0.305, 0, 0, 0, 0.3, 0.155, 0.09, 0.14);
    piece(P.torso, metal, sphere(), 0.35, 0.305, 0, 0, 0, -0.3, 0.155, 0.09, 0.14);
    piece(P.torso, accent, box(), 0, 0.335, 0.1, 0.25, 0, 0, 0.30, 0.075, 0.14); // purple collar
    // battle dents and a sweat-darkened buckle strap
    piece(P.torso, leather, box(), 0, 0.02, 0.185, 0, 0, 0, 0.34, 0.055, 0.02);
    for (let i = 0; i < 3; i++) {
      piece(P.torso, metal, sphere(), -0.06 + i * 0.08, 0.10 - i * 0.05, 0.183, 0, 0, 0, 0.028, 0.022, 0.008);
    }
  } else if (armor === 'iron') {
    piece(P.torso, metal, box(), 0, 0.13, 0, 0, 0, 0, 0.315, 0.42, 0.215);
    piece(P.torso, metal, box(), 0, -0.05, 0.125, 0, 0, 0, 0.30, 0.10, 0.05);
  }

  // ================================================================ head
  const head = P.head;
  piece(head, skin, sphere(), 0, 0.19, -0.01, 0, 0, 0, 0.205, 0.235, 0.215);
  piece(head, skin, sphere(), 0, 0.10, 0.035, 0, 0, 0, 0.165, 0.155, 0.16);
  piece(head, skin, sphere(), 0, 0.025, 0.115, 0, 0, 0, 0.068, 0.058, 0.065);
  // ears
  piece(head, skin, sphere(), -0.198, 0.13, -0.015, 0, 0, 0, 0.038, 0.058, 0.024);
  piece(head, skin, sphere(), 0.198, 0.13, -0.015, 0, 0, 0, 0.038, 0.058, 0.024);
  if (full) {
    // --- brow: two halves, never quite level after a life of squinting
    const browTilt = (r(1) - 0.5) * 0.16;
    const browLift = (r(2) - 0.5) * 0.012;
    piece(head, skin, box(), -0.068, 0.206 + browLift, 0.148, 0, 0.1, -0.08 + browTilt, 0.115, 0.034, 0.055);
    piece(head, skin, box(), 0.068, 0.203 - browLift, 0.148, 0, -0.1, 0.08 + browTilt, 0.115, 0.03, 0.055);
    // glabella / frown knot
    piece(head, skin, sphere(), 0, 0.19, 0.165, 0, 0, 0, 0.045, 0.045, 0.035);
    // forehead furrows
    piece(head, skin, box(), 0, 0.262, 0.148, 0.2, 0, 0, 0.15, 0.012, 0.03);
    piece(head, skin, box(), 0, 0.283, 0.132, 0.3, 0, 0, 0.13, 0.010, 0.026);

    // --- nose: broken once, set a little off-centre
    const noseSkew = (r(3) - 0.5) * 0.014;
    piece(head, skin, box(), noseSkew, 0.15, 0.175, 0.12, 0, 0, 0.04, 0.07, 0.055);
    piece(head, skin, cone(), noseSkew + 0.004, 0.123, 0.196, Math.PI / 2 + 0.32, 0, 0.06, 0.066, 1, 0.07);
    piece(head, skin, sphere(), noseSkew - 0.028, 0.103, 0.196, 0, 0, 0, 0.036, 0.03, 0.026);
    piece(head, skin, sphere(), noseSkew + 0.032, 0.100, 0.190, 0, 0, 0, 0.032, 0.028, 0.024);
    // nostril shadow
    piece(head, hair, sphere(), noseSkew - 0.026, 0.092, 0.203, 0, 0, 0, 0.017, 0.012, 0.010);
    piece(head, hair, sphere(), noseSkew + 0.030, 0.090, 0.199, 0, 0, 0, 0.016, 0.011, 0.010);

    // --- cheekbones / hollows: one side leans harder
    const cheekL = 0.112 + (r(4) - 0.5) * 0.012;
    const cheekR = 0.112 - (r(4) - 0.5) * 0.012;
    piece(head, skin, sphere(), -cheekL, 0.108, 0.112, 0, 0, 0, 0.118, 0.098, 0.092);
    piece(head, skin, sphere(), cheekR, 0.098, 0.115, 0, 0, 0, 0.112, 0.09, 0.088);
    piece(head, skin, sphere(), -0.086, 0.038, 0.132, 0, 0, 0, 0.072, 0.062, 0.05);
    piece(head, skin, sphere(), 0.090, 0.033, 0.128, 0, 0, 0, 0.066, 0.058, 0.048);
    // jaw corners — square, wind-burned
    piece(head, skin, sphere(), -0.128, 0.028, 0.075, 0, 0, 0, 0.075, 0.082, 0.075);
    piece(head, skin, sphere(), 0.128, 0.024, 0.072, 0, 0, 0, 0.072, 0.078, 0.072);

    // --- mouth: a hard line, lower lip fuller
    piece(head, skin, box(), noseSkew, 0.058, 0.163, 0, 0, 0, 0.078, 0.017, 0.028);
    piece(head, hair, box(), noseSkew, 0.049, 0.170, 0, 0, 0, 0.064, 0.007, 0.012);
    piece(head, skin, box(), noseSkew, 0.036, 0.161, 0, 0, 0, 0.058, 0.016, 0.026);
    piece(head, skin, box(), noseSkew, 0.046, 0.186, 0, 0, 0, 0.018, 0.032, 0.012);

    // --- sun creases at the eye corners, a scar through one brow
    piece(head, hair, box(), -0.132, 0.176, 0.145, 0, 0, 0.42, 0.036, 0.004, 0.012);
    piece(head, hair, box(), 0.132, 0.176, 0.145, 0, 0, -0.42, 0.036, 0.004, 0.012);
    piece(head, hair, box(), 0.118, 0.232, 0.150, 0, 0, -0.42, 0.052, 0.006, 0.008);
    piece(head, hair, box(), 0.126, 0.205, 0.156, -0.1, 0, -0.3, 0.044, 0.005, 0.008);
    const white = eyeWhite(palette);
    piece(head, white, sphere(), -0.082, 0.175, 0.148, 0, 0, 0, 0.072, 0.055, 0.035);
    piece(head, white, sphere(), 0.082, 0.175, 0.148, 0, 0, 0, 0.072, 0.055, 0.035);
    piece(head, hair, sphere(), -0.082, 0.175, 0.172, 0, 0, 0, 0.032, 0.032, 0.018);
    piece(head, hair, sphere(), 0.082, 0.175, 0.172, 0, 0, 0, 0.032, 0.032, 0.018);
    piece(head, hair, box(), -0.082, 0.222, 0.163, 0, 0, 0.12, 0.062, 0.016, 0.016);
    piece(head, hair, box(), 0.082, 0.222, 0.163, 0, 0, -0.12, 0.062, 0.016, 0.016);
  }

  // ------------------------------------------------------------ hair
  if (hairStyle !== 'covered') {
    if (hairStyle === 'balding') {
      piece(head, hair, sphere(), -0.185, 0.16, -0.03, 0, 0, 0, 0.075, 0.13, 0.16);
      piece(head, hair, sphere(), 0.185, 0.16, -0.03, 0, 0, 0, 0.075, 0.13, 0.16);
      piece(head, hair, sphere(), 0, 0.16, -0.175, 0, 0, 0, 0.16, 0.12, 0.09);
    } else {
      piece(head, hair, sphere(), 0, 0.255, -0.02, 0, 0, 0, 0.215, 0.185, 0.225);
      // clumps break the sphere silhouette
      piece(head, hair, sphere(), -0.155, 0.22, -0.115, 0, 0, 0, 0.10, 0.11, 0.11);
      piece(head, hair, sphere(), 0.155, 0.22, -0.115, 0, 0, 0, 0.10, 0.11, 0.11);
      piece(head, hair, sphere(), 0, 0.29, -0.12, 0, 0, 0, 0.13, 0.10, 0.11);
      if (hairStyle === 'long') {
        piece(head, hair, sphere(), -0.13, 0.06, -0.175, 0, 0, 0, 0.09, 0.19, 0.09);
        piece(head, hair, sphere(), 0.13, 0.06, -0.175, 0, 0, 0, 0.09, 0.19, 0.09);
        piece(head, hair, sphere(), 0, -0.02, -0.185, 0, 0, 0, 0.115, 0.17, 0.08);
      }
    }
    // layered strand cards fracture the silhouette into real, coarse hair
    if (full && hairCard) addHairCards(head, hairCard, hairStyle, r);
  }

  // ----------------------------------------------------------- beard
  if (beard !== 'none') {
    const jaw = [
      [-0.135, 0.055, 0.10], [0.135, 0.055, 0.10],
      [-0.095, 0.015, 0.135], [0.095, 0.015, 0.135],
      [-0.05, -0.015, 0.15], [0.05, -0.015, 0.15],
      [0, -0.03, 0.155],
      [-0.16, 0.10, 0.04], [0.16, 0.10, 0.04],
    ];
    for (const [x, y, z] of jaw) {
      piece(head, hair, sphere(), x, y, z, 0, 0, 0, 0.085, 0.085, 0.085);
    }
    piece(head, hair, box(), 0, 0.075, 0.155, 0, 0, 0, 0.115, 0.028, 0.03);
    if (beard === 'gray') {
      // salt flecks
      for (let i = 0; i < 5; i++) {
        piece(head, skin, sphere(),
          (i - 2) * 0.045, -0.01 + (i % 2) * 0.05, 0.16, 0, 0, 0, 0.03, 0.03, 0.02);
      }
    }
    // thick clumped beard cards — the look of an unshorn desert fighter
    if (full && hairCard) addBeardCards(head, hairCard, r);
  }

  // ---------------------------------------------------------- helmet
  if (hairStyle === 'covered' || armor === 'iron') {
    piece(head, metal, sphere(), 0, 0.245, -0.015, 0, 0, 0, 0.235, 0.215, 0.245);
    piece(head, metal, box(), 0, 0.155, 0.195, 0, 0, 0, 0.035, 0.115, 0.03);
    piece(head, metal, box(), -0.195, 0.135, 0.0, 0, 0, 0.15, 0.028, 0.16, 0.11);
    piece(head, metal, box(), 0.195, 0.135, 0.0, 0, 0, -0.15, 0.028, 0.16, 0.11);
    if (full) piece(head, accent, box(), 0, 0.445, -0.02, 0, 0, 0, 0.035, 0.10, 0.30);
  }

  // ================================================== arms (pivots)
  for (const side of [-1, 1]) {
    const A = side < 0 ? P.armL : P.armR;
    piece(A, cloth, sphere(), 0, -0.01, 0, 0, 0, 0, 0.105, 0.10, 0.10);
    piece(A, cloth, cyl(0.082, 0.068, 0.28), 0, -0.16, 0);
    piece(A, skin, sphere(), 0, -0.315, 0, 0, 0, 0, 0.125, 0.115, 0.115);
    piece(A, skin, cyl(0.058, 0.05, 0.25), 0, -0.455, 0);
    if (armor !== 'none') {
      piece(A, leather, cyl(0.070, 0.064, 0.17), 0, -0.49, 0);
      // bracer lacing and a bronze stud
      piece(A, leather, box(), 0, -0.46, 0.055, 0, 0, 0, 0.075, 0.018, 0.02);
      piece(A, leather, box(), 0, -0.53, 0.058, 0, 0, 0, 0.07, 0.018, 0.02);
      if (full) piece(A, metal, sphere(), 0, -0.495, 0.065, 0, 0, 0, 0.018, 0.018, 0.01);
      // shoulder strap across the deltoid
      piece(A, leather, box(), 0, -0.045, 0.055, 0, 0, 0, 0.11, 0.035, 0.035);
    }
    piece(A, skin, box(), 0, -0.635, 0.008, 0, 0, 0, 0.082, 0.105, 0.05);
    if (full) piece(A, skin, box(), side * 0.042, -0.615, 0.028, 0, 0, side * 0.55, 0.028, 0.055, 0.03);
  }

  // ================================================== legs (pivots)
  for (const side of [-1, 1]) {
    const L = side < 0 ? P.legL : P.legR;
    const wrap = robe ? cloth : cloth2;
    piece(L, wrap, cyl(0.118, 0.092, 0.36), 0, -0.20, 0);
    piece(L, skin, sphere(), 0, -0.395, 0, 0, 0, 0, 0.155, 0.135, 0.15);
    piece(L, skin, cyl(0.075, 0.055, 0.33), 0, -0.585, 0);
    piece(L, skin, sphere(), 0, -0.52, -0.048, 0, 0, 0, 0.135, 0.19, 0.11);
    piece(L, skin, box(), 0, -0.805, 0.055, 0, 0, 0, 0.095, 0.05, 0.225);
    // strapped sandal, caked in trail dust
    piece(L, leather, box(), 0, -0.835, 0.06, 0, 0, 0, 0.11, 0.03, 0.25);
    piece(L, leather, box(), 0, -0.785, 0.125, -0.5, 0, 0, 0.105, 0.022, 0.035);
    piece(L, leather, box(), 0, -0.775, -0.02, 0.35, 0, 0, 0.105, 0.022, 0.035);
    piece(L, leather, cyl(0.062, 0.062, 0.035), 0, -0.765, 0.02);
    // second cracked strap + toe thong (desert wear)
    piece(L, leather, box(), 0, -0.792, 0.075, 0.15, 0, 0, 0.10, 0.016, 0.03);
    piece(L, leather, box(), 0, -0.80, 0.148, 0.6, 0, 0, 0.028, 0.014, 0.07);
    if (armor !== 'none') piece(L, leather, cyl(0.088, 0.082, 0.075), 0, -0.33, 0);
  }

  // ========================================================== assemble
  const g = new THREE.Group();

  const torsoGroup = new THREE.Group();
  torsoGroup.position.set(0, 1.14, 0);
  for (const mesh of buildPivot(P.torso)) torsoGroup.add(mesh);

  const headGroup = new THREE.Group();
  // 7.5-head figure: the head reads ~0.26 units against a 1.95 body
  headGroup.position.set(0, 0.475, 0.012);
  headGroup.scale.setScalar(0.78);
  for (const mesh of buildPivot(P.head)) headGroup.add(mesh);
  torsoGroup.add(headGroup);

  const armL = new THREE.Group();
  armL.position.set(-0.435, 0.28, 0);
  for (const mesh of buildPivot(P.armL)) armL.add(mesh);
  const armR = new THREE.Group();
  armR.position.set(0.435, 0.28, 0);
  for (const mesh of buildPivot(P.armR)) armR.add(mesh);
  torsoGroup.add(armL, armR);

  const legL = new THREE.Group();
  legL.position.set(-0.175, 0.88, 0);
  for (const mesh of buildPivot(P.legL)) legL.add(mesh);
  const legR = new THREE.Group();
  legR.position.set(0.175, 0.88, 0);
  for (const mesh of buildPivot(P.legR)) legR.add(mesh);

  g.add(torsoGroup, legL, legR);
  g.userData.parts = {
    armL, armR, legL, legR, head: headGroup, torso: torsoGroup,
  };

  applyPose(pose, g, g.userData.parts);
  return g;
}

/**
 * Hair cards — layered quads wearing the strand alpha map. Each card is a
 * slightly different width/length/tilt so the silhouette frays into coarse,
 * dust-dulled biblical hair instead of a smooth helmet.
 *
 * Cards live in the head pivot's local space, where the sculpted face mass
 * occupies roughly ±0.20 in x, 0.30 in y and ±0.20 in z.
 */
function addHairCards(head, mat, style, r) {
  const ring = style === 'long' ? 13 : 10;
  for (let i = 0; i < ring; i++) {
    const a = (i / ring) * Math.PI * 2 + (r(70) - 0.5) * 0.22;
    const rad = 0.152 + r(71 + i) * 0.032;
    const y = 0.283 + Math.sin(a * 2 + 0.4) * 0.022 + (r(72 + i) - 0.5) * 0.018;
    piece(head, mat, card(),
      Math.sin(a) * rad, y, Math.cos(a) * rad - 0.018,
      -0.62 - r(73 + i) * 0.28, a, (r(74 + i) - 0.5) * 0.34,
      0.115 + r(75 + i) * 0.045, 0.19 + r(76 + i) * 0.06, 1);
  }
  // back mass — sits over the nape, longer on the long style
  for (let i = 0; i < 7; i++) {
    const x = (i / 6 - 0.5) * 0.26;
    piece(head, mat, card(),
      x, 0.165 - Math.abs(x) * 0.18, -0.152 + Math.abs(x) * 0.19,
      0.22 + r(80 + i) * 0.16, (x > 0 ? -1 : 1) * 0.22, (r(81 + i) - 0.5) * 0.2,
      0.085, 0.21 + r(82 + i) * 0.05, 1);
  }
  if (style === 'long') {
    for (let i = 0; i < 8; i++) {
      const x = (i / 7 - 0.5) * 0.30;
      piece(head, mat, card(),
        x, 0.02 - Math.abs(x) * 0.2, -0.17 + Math.abs(x) * 0.2,
        0.1 + r(90 + i) * 0.12, (x > 0 ? -1 : 1) * 0.3, (r(91 + i) - 0.5) * 0.18,
        0.075 + r(92 + i) * 0.03, 0.24 + r(93 + i) * 0.08, 1);
    }
  }
  // temple wisps — the loose strands that catch the low sun
  for (const side of [-1, 1]) {
    piece(head, mat, card(),
      side * 0.176, 0.228, 0.055,
      0.05, side * 0.9, side * (0.22 + r(95) * 0.2),
      0.045, 0.15, 1);
    piece(head, mat, card(),
      side * 0.163, 0.205, 0.09,
      0.02, side * 0.6, side * (0.12 + r(96) * 0.2),
      0.036, 0.11, 1);
  }
}

/** Beard cards: a hang of clumped strands from the jaw to the chest. */
function addBeardCards(head, mat, r) {
  // jaw line, upper row
  for (let i = 0; i < 9; i++) {
    const t = (i / 8) * 2 - 1;
    const x = t * 0.148;
    const z = 0.152 - Math.abs(t) * 0.028;
    const y = 0.012 - (1 - Math.abs(t)) * 0.012;
    piece(head, mat, card(),
      x, y, z,
      0.16 + r(100 + i) * 0.1, -t * 0.55, (r(101 + i) - 0.5) * 0.14,
      0.075, 0.135 + r(102 + i) * 0.04, 1);
  }
  // chin mass, lower and fuller
  for (let i = 0; i < 7; i++) {
    const t = (i / 6) * 2 - 1;
    const x = t * 0.105;
    piece(head, mat, card(),
      x, -0.062 - (1 - Math.abs(t)) * 0.022, 0.172 - Math.abs(t) * 0.03,
      0.1 + r(110 + i) * 0.14, -t * 0.42, (r(111 + i) - 0.5) * 0.2,
      0.07 + r(112 + i) * 0.025, 0.16 + r(113 + i) * 0.05, 1);
  }
  // moustache over the lip
  piece(head, mat, card(), -0.042, 0.048, 0.176, 0.35, 0.2, 0.32, 0.075, 0.05, 1);
  piece(head, mat, card(), 0.042, 0.048, 0.176, 0.35, -0.2, -0.32, 0.075, 0.05, 1);
}

/** Eye whites need their own material when the palette supplies one. */
const eyeMats = new WeakMap();
function eyeWhite(palette) {
  if (!eyeMats.has(palette)) {
    eyeMats.set(palette, new THREE.MeshLambertMaterial({ color: 0xe8e2d8 }));
  }
  return eyeMats.get(palette);
}

function applyPose(pose, g, p) {
  switch (pose) {
    case 'sitting':
      g.position.y = -0.42;
      p.torso.rotation.x = 0.18;
      p.head.rotation.x = 0.35;
      p.legL.rotation.x = -1.45;
      p.legR.rotation.x = -1.45;
      p.legL.rotation.z = 0.35;
      p.legR.rotation.z = -0.35;
      p.armL.rotation.x = -0.75;
      p.armR.rotation.x = -0.75;
      p.armL.rotation.z = 0.22;
      p.armR.rotation.z = -0.22;
      break;

    case 'celebrating':
      p.armL.rotation.z = 2.45;
      p.armR.rotation.z = -2.45;
      p.armL.rotation.x = -0.25;
      p.armR.rotation.x = -0.25;
      p.head.rotation.x = -0.16;
      break;

    case 'seatedCaptive':
      g.position.y = -0.5;
      p.torso.rotation.x = 0.25;
      p.head.rotation.x = 0.3;
      p.legL.rotation.x = -1.5;
      p.legR.rotation.x = -1.5;
      p.armL.rotation.x = -1.15;
      p.armR.rotation.x = -1.15;
      p.armL.rotation.z = 0.35;
      p.armR.rotation.z = -0.35;
      break;

    case 'armsCrossed':
      p.armL.rotation.x = -1.35;
      p.armR.rotation.x = -1.35;
      p.armL.rotation.z = -0.55;
      p.armR.rotation.z = 0.55;
      p.head.rotation.x = -0.08;
      break;

    case 'grieving':
      p.torso.rotation.x = 0.16;
      p.head.rotation.x = 0.42;
      p.armL.rotation.x = -0.25;
      p.armR.rotation.x = -0.25;
      p.armL.rotation.z = 0.14;
      p.armR.rotation.z = -0.14;
      break;

    case 'slumpedWounded':
      g.position.y = -0.32;
      p.torso.rotation.x = 0.32;
      p.head.rotation.x = 0.5;
      p.legL.rotation.x = -1.25;
      p.legR.rotation.x = -0.35;
      p.armL.rotation.x = -0.95;
      p.armL.rotation.z = 0.25;
      p.armR.rotation.x = -1.5;
      p.armR.rotation.z = -0.3;
      break;

    case 'grinding':
      // kneeling at the hand-mill, both arms working the stone
      g.position.y = -0.46;
      p.torso.rotation.x = 0.22;
      p.head.rotation.x = 0.34;
      p.legL.rotation.x = -1.5;
      p.legR.rotation.x = -1.5;
      p.legL.rotation.z = 0.42;
      p.legR.rotation.z = -0.42;
      p.armL.rotation.x = -1.25;
      p.armR.rotation.x = -1.05;
      p.armL.rotation.z = 0.35;
      p.armR.rotation.z = -0.35;
      break;

    case 'carryingJar':
      // both arms raised to steady a jar on the crown of the head
      p.armL.rotation.z = 2.95;
      p.armR.rotation.z = -2.95;
      p.armL.rotation.x = -0.45;
      p.armR.rotation.x = -0.45;
      p.head.rotation.x = -0.06;
      break;

    case 'reclining':
      g.position.y = -0.55;
      g.rotation.x = -0.35;
      p.torso.rotation.x = 0.45;
      p.head.rotation.x = 0.55;
      p.legL.rotation.x = -1.35;
      p.legR.rotation.x = -1.15;
      p.armL.rotation.x = -1.7;
      p.armR.rotation.x = -1.2;
      break;

    default:
      break;
  }
}
