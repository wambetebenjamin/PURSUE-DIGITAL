/**
 * MedievalKit.js — the stronghold kit: monumental stone, iron and banner cloth.
 *
 * The environment language of the reference strategy game is not its *art* —
 * it is a construction grammar that every walled city in the genre shares:
 * ashlar curtain walls with crenellations and buttresses, drum towers with
 * conical or battlemented tops, a gatehouse that reads as the strongest thing
 * on screen, banners hanging from shafts, braziers burning at the threshold,
 * and rubble where something used to stand.
 *
 * That grammar is public architectural vocabulary (it is how real castles are
 * built), so it is legitimate to author — and here it is authored from
 * primitives, with our own stone, iron and cloth materials, no third-party
 * geometry or texture. Everything is baked to one mesh per material, so a
 * fifty-metre curtain wall is a handful of draw calls.
 *
 * Used by ZiklagZone (a stronghold already burned), GateZone (Hebron, intact)
 * and GilboaZone (a wall that has been fought over).
 */
import * as THREE from 'three';
import { Mat, Geo } from '../../utils/materials.js';
import { seededRandom } from '../../utils/math.js';
import { mergeGeometries } from 'three/addons/utils/BufferGeometryUtils.js';

/* ---------------------------------------------------------------- stone */

/** Dark ashlar: cool grey-brown, almost slate. The stronghold's body. */
export const StoneMat = {
  _cache: new Map(),
  get(key, factory) {
    if (!this._cache.has(key)) this._cache.set(key, factory());
    return this._cache.get(key);
  },
};

export function ashlarMat() {
  return StoneMat.get('ashlar', () => new THREE.MeshLambertMaterial({
    color: 0x8d8a86, flatShading: true,
  }));
}

export function ashlarDarkMat() {
  return StoneMat.get('ashlarDark', () => new THREE.MeshLambertMaterial({
    color: 0x5c5a58, flatShading: true,
  }));
}

export function sootStoneMat() {
  return StoneMat.get('sootStone', () => new THREE.MeshLambertMaterial({
    color: 0x33302e, flatShading: true,
  }));
}

export function ironMat() {
  return StoneMat.get('iron', () => new THREE.MeshStandardMaterial({
    color: 0x2b2b30, roughness: 0.62, metalness: 0.75, flatShading: true,
  }));
}

export function bannerClothMat(color = 0x7a1c18) {
  return StoneMat.get(`banner${color.toString(16)}`, () => new THREE.MeshLambertMaterial({
    color, flatShading: true, side: THREE.DoubleSide,
  }));
}

export function emberMat() {
  return StoneMat.get('ember', () => new THREE.MeshBasicMaterial({ color: 0xff8a3a }));
}

/* --------------------------------------------------------------- baking */

/** Flatten a prop hierarchy to one mesh per material (the same trick as PropFactory). */
function bake(root) {
  root.updateMatrixWorld(true);
  const byMat = new Map();
  root.traverse((o) => {
    if (!o.isMesh || o.isInstancedMesh) return;
    const geo = o.geometry.index ? o.geometry.toNonIndexed() : o.geometry.clone();
    geo.applyMatrix4(o.matrixWorld);
    for (const name of Object.keys(geo.attributes)) {
      if (name !== 'position' && name !== 'normal' && name !== 'uv') geo.deleteAttribute(name);
    }
    if (!byMat.has(o.material)) byMat.set(o.material, []);
    byMat.get(o.material).push(geo);
  });
  const out = new THREE.Group();
  for (const [mat, geos] of byMat) {
    const merged = mergeGeometries(geos, false);
    if (!merged) continue;
    const m = new THREE.Mesh(merged, mat);
    m.castShadow = true;
    m.receiveShadow = true;
    out.add(m);
  }
  return out;
}

function box(mat, sx, sy, sz, x = 0, y = 0, z = 0, ry = 0) {
  const m = new THREE.Mesh(Geo.box(), mat);
  m.scale.set(sx, sy, sz);
  m.position.set(x, y, z);
  m.rotation.y = ry;
  return m;
}

/* -------------------------------------------------------------- pieces */

/**
 * A crenellated curtain wall.
 * @param {{length?: number, height?: number, thickness?: number, ruined?: boolean,
 *          seed?: number, soot?: boolean}} [opts]
 */
export function createCurtainWall(opts = {}) {
  const {
    length = 24, height = 7, thickness = 1.9, ruined = false, seed = 1, soot = false,
  } = opts;
  const root = new THREE.Group();
  const body = soot || ruined ? sootStoneMat() : ashlarMat();
  const cap = soot || ruined ? sootStoneMat() : ashlarDarkMat();

  // ---- the wall body, in courses so it reads as built rather than extruded
  const courses = Math.max(3, Math.round(height / 1.15));
  const ch = height / courses;
  for (let i = 0; i < courses; i++) {
    const inset = i === 0 ? 0.22 : 0; // a battered footing
    const y = ch * (i + 0.5);
    const h = ruined && i > courses * 0.62 ? ch * seededRandom(seed + i * 3.1) : ch;
    if (h < 0.18) continue;
    root.add(box(body, thickness + inset, h * 0.96, length, 0, y, 0));
  }

  // ---- merlons, with the ragged gaps of a wall that has been fought over
  const merlonW = 0.95;
  const gap = 0.85;
  const step = merlonW + gap;
  const count = Math.floor(length / step);
  for (let i = 0; i < count; i++) {
    const z = -length / 2 + step * (i + 0.5);
    if (ruined && seededRandom(seed + i * 7.3) > 0.58) continue;
    const h = 0.85 + seededRandom(seed + i * 2.7) * 0.35;
    root.add(box(cap, thickness * 0.92, h, merlonW, 0, height + h / 2, z));
  }
  // walkway floor between the merlons
  root.add(box(cap, thickness * 0.86, 0.16, length, 0, height + 0.08, 0));

  // ---- buttresses on the outer face
  const buttressEvery = 6;
  for (let z = -length / 2 + 2.6; z < length / 2 - 1; z += buttressEvery) {
    root.add(box(body, thickness * 0.42, height * 0.78, 1.15, thickness * 0.62, height * 0.39, z));
    root.add(box(cap, thickness * 0.5, 0.28, 1.3, thickness * 0.6, height * 0.78, z));
  }

  // ---- arrow slits: dark boxes recessed into the inner face
  for (let z = -length / 2 + 3.4; z < length / 2 - 1; z += 5.2) {
    root.add(box(sootStoneMat(), 0.12, 1.15, 0.22, -thickness * 0.52, height * 0.62, z + 0.6));
  }

  return bake(root).children.length ? finish(bake(root)) : new THREE.Group();
}

function finish(baked) {
  const g = new THREE.Group();
  g.name = 'MEDIEVAL_WALL';
  g.add(baked);
  return g;
}

/**
 * A drum tower. `top` is 'crenel' (battlemented) or 'cone' (a roof of slate).
 */
export function createDrumTower(opts = {}) {
  const {
    radius = 3.2, height = 12, top = 'crenel', ruined = false, seed = 1, soot = false,
  } = opts;
  const root = new THREE.Group();
  const body = soot || ruined ? sootStoneMat() : ashlarMat();
  const trim = soot || ruined ? sootStoneMat() : ashlarDarkMat();

  // ---- the drum, in slight bands so the silhouette is not a clean cylinder
  const bands = Math.max(4, Math.round(height / 2.2));
  const bh = height / bands;
  for (let i = 0; i < bands; i++) {
    const r = radius + (i === 0 ? 0.34 : 0) - (i % 3 === 0 ? 0.05 : 0);
    const h = ruined && i > bands * 0.55 ? bh * seededRandom(seed + i * 5.1) : bh;
    if (h < 0.2) continue;
    const drum = new THREE.Mesh(new THREE.CylinderGeometry(r, r, h * 0.97, 14, 1, true), body);
    drum.position.y = bh * (i + 0.5);
    root.add(drum);
  }
  // a solid base so you cannot see through the open cylinder
  const base = new THREE.Mesh(new THREE.CylinderGeometry(radius + 0.34, radius + 0.34, 1.2, 14), body);
  base.position.y = 0.6;
  root.add(base);

  if (top === 'cone') {
    const roof = new THREE.Mesh(new THREE.ConeGeometry(radius + 0.75, radius * 1.55, 14), trim);
    roof.position.y = height + radius * 0.72;
    root.add(roof);
    const finial = new THREE.Mesh(Geo.cone(), ironMat());
    finial.scale.set(0.16, 1.1, 0.16);
    finial.position.y = height + radius * 1.55 + 0.5;
    root.add(finial);
  } else {
    // battlement ring
    const ring = new THREE.Mesh(new THREE.CylinderGeometry(radius + 0.55, radius + 0.55, 0.34, 14), trim);
    ring.position.y = height + 0.17;
    root.add(ring);
    const n = 14;
    for (let i = 0; i < n; i++) {
      if (ruined && seededRandom(seed + i * 3.7) > 0.6) continue;
      const a = (i / n) * Math.PI * 2;
      const h = 0.8 + seededRandom(seed + i) * 0.4;
      root.add(box(trim, 0.75, h, 0.68,
        Math.cos(a) * (radius + 0.4), height + 0.34 + h / 2, Math.sin(a) * (radius + 0.4), -a));
    }
  }

  // a doorway on the inner side
  root.add(box(sootStoneMat(), 0.16, 2.0, 1.15, -(radius - 0.1), 1.0, 0));

  return finish(bake(root));
}

/**
 * A gatehouse: the strongest thing on screen. Two flanking towers, a segmental
 * arch, a portcullis, and a wall-walk over the passage.
 */
export function createGatehouse(opts = {}) {
  const { span = 9, height = 11, towerRadius = 3.4, ruined = false, seed = 2 } = opts;
  const root = new THREE.Group();
  const body = ruined ? sootStoneMat() : ashlarMat();
  const trim = ruined ? sootStoneMat() : ashlarDarkMat();

  // ---- the wall either side of the passage
  const sideW = (span * 1.9 - span) / 2;
  root.add(box(body, 2.6, height, sideW, -(span / 2 + sideW / 2), height / 2, 0));
  root.add(box(body, 2.6, height, sideW, (span / 2 + sideW / 2), height / 2, 0));

  // ---- the arch: a lintel plus stepped corbels, so it reads as masonry
  const archH = 5.4;
  root.add(box(body, span + 2.4, height - archH, 2.6, 0, archH + (height - archH) / 2, 0));
  for (let i = 0; i < 4; i++) {
    const w = 1.1;
    const x = -(span / 2) + 0.55 + i * (span / 4.2);
    root.add(box(trim, w, 0.34, 2.7, x, archH - 0.17 - i * 0.02, 0));
    root.add(box(trim, w, 0.34, 2.7, -x, archH - 0.17 - i * 0.02, 0));
  }

  // ---- merlons along the top
  const stepM = 1.75;
  for (let x = -(span * 0.95); x <= span * 0.95; x += stepM) {
    if (ruined && seededRandom(seed + x) > 0.55) continue;
    root.add(box(trim, 0.95, 0.95, 2.7, x, height + 0.48, 0));
  }

  // ---- the portcullis: raised, its teeth hanging in the arch
  for (let i = -3; i <= 3; i++) {
    root.add(box(ironMat(), 0.12, 2.4, 0.12, i * 0.62, archH - 1.2, 1.15));
  }
  root.add(box(ironMat(), span * 0.8, 0.14, 0.14, 0, archH - 0.1, 1.15));

  // ---- flanking towers
  const tL = createDrumTower({ radius: towerRadius, height: height + 3, top: 'crenel', ruined, seed: seed + 1 });
  tL.position.set(-(span / 2 + 1.6), 0, 0);
  root.add(tL);
  const tR = createDrumTower({ radius: towerRadius, height: height + 3, top: 'crenel', ruined, seed: seed + 2 });
  tR.position.set(span / 2 + 1.6, 0, 0);
  root.add(tR);

  return finish(bake(root));
}

/** A palisade of sharpened stakes: the field fortification of a raiding camp. */
export function createPalisade(opts = {}) {
  const { length = 14, height = 2.4, seed = 3 } = opts;
  const root = new THREE.Group();
  const wood = Mat.cageWood();
  const count = Math.floor(length / 0.42);
  for (let i = 0; i < count; i++) {
    const x = -length / 2 + i * 0.42;
    const h = height * (0.82 + seededRandom(seed + i * 1.7) * 0.3);
    const stake = new THREE.Mesh(new THREE.CylinderGeometry(0.11, 0.14, h, 5), wood);
    stake.position.set(x, h / 2, seededRandom(seed + i * 4.1) * 0.1);
    stake.rotation.z = (seededRandom(seed + i * 2.3) - 0.5) * 0.09;
    root.add(stake);
    const tip = new THREE.Mesh(new THREE.ConeGeometry(0.13, 0.34, 5), wood);
    tip.position.set(stake.position.x, h + 0.14, stake.position.z);
    root.add(tip);
  }
  // two rails binding the stakes
  root.add(box(wood, length, 0.14, 0.14, 0, height * 0.42, 0.16));
  root.add(box(wood, length, 0.14, 0.14, 0, height * 0.74, 0.16));
  return finish(bake(root));
}

/** A hanging banner: shaft, crossbar, and a long tongue of dyed cloth. */
export function createBanner(opts = {}) {
  const { color = 0x7a1c18, height = 7, width = 1.05, seed = 4 } = opts;
  const root = new THREE.Group();
  const wood = Mat.acaciaBark();
  const shaft = new THREE.Mesh(new THREE.CylinderGeometry(0.07, 0.09, height, 6), wood);
  shaft.position.y = height / 2;
  root.add(shaft);
  const bar = new THREE.Mesh(new THREE.CylinderGeometry(0.05, 0.05, width + 0.5, 6), wood);
  bar.rotation.z = Math.PI / 2;
  bar.position.y = height - 0.32;
  root.add(bar);

  const cloth = new THREE.Mesh(
    new THREE.PlaneGeometry(width, height * 0.52, 1, 6),
    bannerClothMat(color),
  );
  cloth.position.set(0, height - 0.4 - height * 0.26, 0.02);
  cloth.castShadow = true;
  root.add(cloth);

  // a torn notch, so the banner has been in weather
  const tear = new THREE.Mesh(
    new THREE.PlaneGeometry(width * 0.55, height * 0.1),
    bannerClothMat(0x2a1410),
  );
  tear.position.set(width * 0.2, height - 0.4 - height * 0.52 + 0.04, 0.025);
  root.add(tear);

  const g = finish(bake(root));
  g.userData.sway = (t) => {
    const m = g.children[0];
    if (m) m.rotation.z = Math.sin(t * 1.1 + seededRandom(seed) * 6) * 0.035;
  };
  return g;
}

/** An iron brazier with coals: the threshold light of a stronghold. */
export function createBrazier(opts = {}) {
  const { withLight = false, height = 1.5 } = opts;
  const root = new THREE.Group();
  const iron = ironMat();

  // three legs
  for (let i = 0; i < 3; i++) {
    const a = (i / 3) * Math.PI * 2;
    const leg = new THREE.Mesh(new THREE.CylinderGeometry(0.05, 0.07, height, 5), iron);
    leg.position.set(Math.cos(a) * 0.26, height / 2, Math.sin(a) * 0.26);
    leg.rotation.z = Math.cos(a) * 0.16;
    leg.rotation.x = -Math.sin(a) * 0.16;
    root.add(leg);
  }
  // the basket
  const basket = new THREE.Mesh(new THREE.CylinderGeometry(0.42, 0.3, 0.4, 10, 1, true), iron);
  basket.position.y = height + 0.12;
  root.add(basket);
  const rim = new THREE.Mesh(new THREE.TorusGeometry(0.42, 0.035, 5, 12), iron);
  rim.rotation.x = Math.PI / 2;
  rim.position.y = height + 0.32;
  root.add(rim);
  // coals
  const coals = new THREE.Mesh(new THREE.IcosahedronGeometry(0.34, 0), emberMat());
  coals.scale.y = 0.5;
  coals.position.y = height + 0.2;
  root.add(coals);
  const flame = new THREE.Mesh(new THREE.ConeGeometry(0.22, 0.62, 7), Mat.flame());
  flame.position.y = height + 0.52;
  root.add(flame);

  const g = finish(bake(root));
  g.userData.update = (t) => {
    flame.scale.setScalar(0.88 + Math.sin(t * 7.3) * 0.09 + Math.sin(t * 3.1) * 0.05);
    if (withLight && !g.userData.light) {
      const light = new THREE.PointLight(0xff7a30, 12, 14, 2);
      light.position.set(0, height + 0.5, 0);
      g.add(light);
      g.userData.light = light;
    }
    if (g.userData.light) {
      g.userData.light.intensity = 10 + Math.sin(t * 5.7) * 2.2 + Math.sin(t * 11.3) * 1.1;
    }
  };
  if (withLight) g.userData.update(0);
  return g;
}

/** A heap of broken masonry: what a wall becomes after a siege. */
export function createRubbleHeap(opts = {}) {
  const { radius = 2.4, count = 14, seed = 5, soot = true } = opts;
  const root = new THREE.Group();
  const mat = soot ? sootStoneMat() : ashlarMat();
  for (let i = 0; i < count; i++) {
    const a = seededRandom(seed + i * 1.3) * Math.PI * 2;
    const r = Math.sqrt(seededRandom(seed + i * 2.9)) * radius;
    const s = 0.28 + seededRandom(seed + i * 4.7) * 0.85;
    const b = box(mat, s * (1 + seededRandom(seed + i) * 0.7), s * 0.7, s, Math.cos(a) * r, s * 0.3, Math.sin(a) * r);
    b.rotation.set(seededRandom(seed + i * 6.1) * 1.2, seededRandom(seed + i * 3.3) * 3.1, seededRandom(seed + i * 8.7) * 1.2);
    root.add(b);
  }
  return finish(bake(root));
}

/** A siege ladder leaning against a wall. */
export function createSiegeLadder(opts = {}) {
  const { height = 9, lean = 0.22 } = opts;
  const root = new THREE.Group();
  const wood = Mat.cageWood();
  for (const side of [-0.36, 0.36]) {
    const rail = new THREE.Mesh(new THREE.CylinderGeometry(0.07, 0.08, height, 6), wood);
    rail.position.set(side, height / 2, 0);
    rail.rotation.x = lean;
    root.add(rail);
  }
  const rungs = Math.floor(height / 0.55);
  for (let i = 1; i < rungs; i++) {
    const y = i * 0.55;
    const z = Math.sin(lean) * y * 0.5;
    const rung = new THREE.Mesh(new THREE.CylinderGeometry(0.045, 0.045, 0.76, 5), wood);
    rung.rotation.z = Math.PI / 2;
    rung.position.set(0, y, z);
    root.add(rung);
  }
  return finish(bake(root));
}

/** A short flight of stone steps up to a gate. */
export function createStoneSteps(opts = {}) {
  const { width = 6, steps = 3, rise = 0.22 } = opts;
  const root = new THREE.Group();
  const mat = ashlarDarkMat();
  for (let i = 0; i < steps; i++) {
    root.add(box(mat, width, rise, 0.5 + i * 0.18, 0, rise * (i + 0.5), -i * -0.1));
  }
  return finish(bake(root));
}
