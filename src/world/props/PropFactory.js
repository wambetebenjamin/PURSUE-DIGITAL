/**
 * PropFactory.js — reusable low-poly prop builders.
 *
 * Everything is assembled from shared primitives (see utils/materials.js).
 * Builders return a THREE.Group; the caller is responsible for positioning,
 * rotating and registering colliders.
 */
import * as THREE from 'three';
import { qualityCount } from '../../core/quality.js';
import { Mat, Geo } from '../../utils/materials.js';
import { createDetailedHuman } from '../../entities/CharacterRig.js';
import { randRange, seededRandom } from '../../utils/math.js';
import { mergeGeometries } from 'three/addons/utils/BufferGeometryUtils.js';

// ------------------------------------------------------------ helpers
function mesh(geo, mat, sx = 1, sy = 1, sz = 1, x = 0, y = 0, z = 0) {
  const m = new THREE.Mesh(geo, mat);
  m.scale.set(sx, sy, sz);
  m.position.set(x, y, z);
  m.castShadow = true;
  m.receiveShadow = true;
  return m;
}

/**
 * Bake a static prop into one mesh per material.
 *
 * Props are authored as readable little hierarchies of primitives; at runtime
 * that would be dozens of draw calls per ruin. Everything here is immobile,
 * so we flatten the group's world matrices into merged geometries — a burnt
 * house drops from ~30 draws to ~7, an acacia from 14 to 2.
 */
function bakeStatic(root) {
  root.updateMatrixWorld(true);
  const byMat = new Map();
  root.traverse((o) => {
    if (!o.isMesh || o.isInstancedMesh) return;
    const geo = o.geometry.clone();
    geo.applyMatrix4(o.matrixWorld);
    // normalise attribute sets so mixed primitives can merge
    for (const name of Object.keys(geo.attributes)) {
      if (name !== 'position' && name !== 'normal' && name !== 'uv') {
        geo.deleteAttribute(name);
      }
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

// -------------------------------------------------------------- houses
/** A burnt, partially-collapsed house. */
export function createBurntHouse(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 13.37 + n);

  const w = 3.2 + r(1) * 1.4;
  const d = 3 + r(2) * 1.2;
  const h = 2.1 + r(3) * 0.8;

  // floor slab
  g.add(mesh(Geo.box(), Mat.ashDebris(), w + 0.6, 0.22, d + 0.6, 0, 0.11, 0));

  // walls — one or two sides missing (collapsed)
  const wallMat = Mat.charWood();
  const missing = Math.floor(r(4) * 4);
  const walls = [
    [w, h, 0.18, 0, h / 2, -d / 2],
    [w, h, 0.18, 0, h / 2, d / 2],
    [0.18, h, d, -w / 2, h / 2, 0],
    [0.18, h, d, w / 2, h / 2, 0],
  ];
  walls.forEach((cfg, i) => {
    if (i === missing && r(5) > 0.25) {
      // half-height rubble wall
      g.add(mesh(Geo.box(), wallMat, cfg[0], cfg[1] * 0.35, cfg[2], cfg[3], cfg[1] * 0.17, cfg[5]));
    } else {
      g.add(mesh(Geo.box(), wallMat, cfg[0], cfg[1], cfg[2], cfg[3], cfg[4], cfg[5]));
    }
  });

  // ---------------------------------------------------------- stone base
  // hand-hewn limestone plinth under the burnt mud-brick — the town was
  // built of stone before the fire took the roof and the timber
  for (let side = 0; side < 4; side++) {
    const along = side < 2 ? w + 0.4 : d + 0.4;
    const bx = side === 2 ? -(w / 2 + 0.14) : side === 3 ? w / 2 + 0.14 : 0;
    const bz = side === 0 ? -(d / 2 + 0.14) : side === 1 ? d / 2 + 0.14 : 0;
    for (let k = 0; k < 3; k++) {
      const t = (k - 1) * (along / 3.4);
      const bxk = side < 2 ? bx + t : bx;
      const bzk = side < 2 ? bz : bz + t;
      const block = mesh(Geo.box(), Mat.hewnLimestone(),
        side < 2 ? along / 3.3 : 0.34, 0.3, side < 2 ? 0.34 : along / 3.3,
        bxk, 0.16, bzk);
      block.rotation.y = (r(200 + side * 4 + k) - 0.5) * 0.06;
      g.add(block);
    }
  }
  // mud-brick chunks and fallen dressed blocks
  for (let i = 0; i < 4; i++) {
    const s = 0.3 + r(210 + i) * 0.35;
    const chunk = mesh(Geo.box(), Mat.hewnLimestone(), s * 1.5, s, s,
      (r(220 + i) - 0.5) * (w + 2.6), s * 0.5, (r(230 + i) - 0.5) * (d + 2.6));
    chunk.rotation.set(r(240 + i) * 0.5, r(250 + i) * 3, (r(260 + i) - 0.5) * 0.4);
    g.add(chunk);
  }

  // slumped roof, with charred beams sagging through it
  const roof = mesh(Geo.box(), Mat.charRoof(), w + 0.5, 0.16, d + 0.5, 0, h + 0.12, 0);
  roof.rotation.z = (r(6) - 0.5) * 0.34;
  roof.rotation.x = (r(7) - 0.5) * 0.22;
  g.add(roof);
  for (let i = 0; i < 4; i++) {
    const beam = mesh(Geo.box(), Mat.charWood(), 0.14, 0.14, d + 1.2,
      0, h - 0.05,
      0);
    beam.position.x = (i - 1.5) * (w / 4.4);
    beam.position.y = h - 0.05 + r(270 + i) * 0.12;
    beam.rotation.x = 0.06 + r(280 + i) * 0.18;
    beam.rotation.z = (r(290 + i) - 0.5) * 0.12;
    g.add(beam);
  }
  // soot-scarred wall tops (limestone blackened by the fire)
  for (const [sx, sz, sxx, szz] of [
    [w * 0.5, 0, 0.22, d], [-w * 0.5, 0, 0.22, d],
    [0, d * 0.5, w, 0.22], [0, -d * 0.5, w, 0.22],
  ]) {
    g.add(mesh(Geo.box(), Mat.soot(), sxx * 0.98, 0.5, szz * 0.98, sx, h * 0.72, sz));
  }

  // glowing ember patch (under the roof line)
  const embers = mesh(Geo.box(), Mat.ember(), w * 0.36, 0.06, d * 0.3, (r(8) - 0.5) * 1.2, 0.26, (r(9) - 0.5) * 1.2);
  embers.castShadow = false;
  g.add(embers);

  // scattered debris
  for (let i = 0; i < 5; i++) {
    const s = 0.18 + r(10 + i) * 0.35;
    const deb = mesh(Geo.box(), Mat.ashDebris(), s, s * 0.5, s * 1.6,
      (r(20 + i) - 0.5) * (w + 3), s * 0.25, (r(30 + i) - 0.5) * (d + 3));
    deb.rotation.y = r(40 + i) * Math.PI;
    deb.rotation.z = (r(50 + i) - 0.5) * 0.8;
    g.add(deb);
  }

  return bakeStatic(g);
}

/** Charred tree trunk with a few stubby branches. */
export function createCharredTree(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 7.77 + n);
  const h = 3 + r(1) * 2.2;

  const trunk = mesh(Geo.cyl(), Mat.charWood(), 0.32, h, 0.32, 0, h / 2, 0);
  trunk.rotation.z = (r(2) - 0.5) * 0.18;
  g.add(trunk);

  for (let i = 0; i < 3; i++) {
    const bl = 0.9 + r(3 + i) * 1.1;
    const branch = mesh(Geo.cyl(), Mat.charWood(), 0.12, bl, 0.12, 0, h * (0.5 + i * 0.16), 0);
    branch.rotation.z = 0.7 + r(10 + i) * 0.7;
    branch.rotation.y = r(20 + i) * Math.PI * 2;
    g.add(branch);
  }
  return g;
}

// --------------------------------------------------------------- rocks
export function createRock(seed = 1, dark = false) {
  const r = (n) => seededRandom(seed * 3.31 + n);
  const m = new THREE.Mesh(Geo.dodeca(), dark ? Mat.rockDark() : Mat.rock());
  const s = 0.9 + r(1) * 2.2;
  m.scale.set(s * (0.8 + r(2) * 0.7), s * (0.5 + r(3) * 0.6), s * (0.8 + r(4) * 0.7));
  m.position.y = m.scale.y * 0.32;
  m.rotation.set(r(5) * 0.6, r(6) * Math.PI * 2, r(7) * 0.6);
  m.castShadow = true;
  m.receiveShadow = true;
  return m;
}

/** Batched rocks — one InstancedMesh draw call for the whole field. */
export function createRockField(count, area, seed = 1) {
  count = qualityCount(count, 10);
  const geo = new THREE.DodecahedronGeometry(0.5, 0);
  const inst = new THREE.InstancedMesh(geo, Mat.rock(), count);
  inst.castShadow = true;
  inst.receiveShadow = true;

  const dummy = new THREE.Object3D();
  for (let i = 0; i < count; i++) {
    const r = (n) => seededRandom(seed * 17 + i * 3 + n);
    const x = randRange(area.minX, area.maxX);
    const z = randRange(area.minZ, area.maxZ);
    const s = 0.9 + r(1) * 2.4;
    dummy.position.set(x, s * 0.28, z);
    dummy.scale.set(s * (0.8 + r(2) * 0.7), s * (0.5 + r(3) * 0.65), s * (0.8 + r(4) * 0.7));
    dummy.rotation.set(r(5) * 0.5, r(6) * Math.PI * 2, r(7) * 0.5);
    dummy.updateMatrix();
    inst.setMatrixAt(i, dummy.matrix);
  }
  inst.instanceMatrix.needsUpdate = true;
  return inst;
}

export function createDryBush(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 9.1 + n);
  for (let i = 0; i < 4; i++) {
    const b = mesh(Geo.ico(), Mat.dryBush(),
      0.5 + r(i) * 0.5, 0.35 + r(i + 4) * 0.3, 0.5 + r(i + 8) * 0.5,
      (r(i + 12) - 0.5) * 0.7, 0.2 + r(i + 16) * 0.2, (r(i + 20) - 0.5) * 0.7);
    g.add(b);
  }
  return g;
}

/** Simple palm: trunk + leaf fan. */
export function createPalm(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 4.2 + n);
  const h = 4 + r(1) * 2.5;

  const trunk = mesh(Geo.cyl(), Mat.palmTrunk(), 0.28, h, 0.28, 0, h / 2, 0);
  trunk.rotation.z = (r(2) - 0.5) * 0.16;
  g.add(trunk);

  for (let i = 0; i < 6; i++) {
    const leaf = mesh(Geo.box(), Mat.palmLeaf(), 2.4, 0.1, 0.55, 0, 0, 0);
    const a = (i / 6) * Math.PI * 2;
    leaf.position.set(Math.cos(a) * 1.15, h + 0.15, Math.sin(a) * 1.15);
    leaf.rotation.y = -a;
    leaf.rotation.z = -0.5 - r(10 + i) * 0.35;
    g.add(leaf);
  }
  return g;
}

// --------------------------------------------------------------- tents
/** Low-poly campaign tent (cone roof over a box base). */
export function createTent(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 6.6 + n);
  const alt = r(1) > 0.5;

  const base = mesh(Geo.box(), alt ? Mat.tentClothAlt() : Mat.tentCloth(), 3.4, 1.35, 3.0, 0, 0.68, 0);
  g.add(base);

  const roof = mesh(Geo.cone(), alt ? Mat.tentCloth() : Mat.tentClothAlt(), 3.6, 1.5, 3.2, 0, 2.05, 0);
  roof.rotation.y = r(2) * 0.4;
  g.add(roof);

  // door flap
  const flap = mesh(Geo.box(), Mat.tentClothAlt(), 0.9, 1.0, 0.08, 0, 0.55, 1.53);
  flap.rotation.x = -0.25;
  g.add(flap);

  // pegged ropes
  for (let i = 0; i < 2; i++) {
    const rope = mesh(Geo.cyl(), Mat.rope(), 0.05, 1.6, 0.05,
      i === 0 ? -1.6 : 1.6, 0.85, 1.45);
    rope.rotation.x = 0.7;
    g.add(rope);
  }

  // canvas wear: sun-bleached patches, a stitched seam, a sagging hem
  for (let i = 0; i < 4; i++) {
    const patch = mesh(Geo.box(), i % 2 ? Mat.tentCloth() : Mat.tentClothAlt(),
      0.5 + r(20 + i) * 0.6, 0.4 + r(30 + i) * 0.5, 0.03,
      (r(40 + i) - 0.5) * 2.8, 0.5 + r(50 + i) * 1.1, 1.52 + r(60 + i) * 0.02);
    patch.rotation.z = (r(70 + i) - 0.5) * 0.5;
    patch.position.y = 0.5 + r(50 + i) * 0.9;
    g.add(patch);
  }
  // stitched ridge seam down the roof
  g.add(mesh(Geo.box(), Mat.rope(), 0.06, 1.3, 0.06, 0, 2.55, 0.05));
  // sagging hem, darker where the dew and dust collect
  const hem = mesh(Geo.box(), Mat.tentClothAlt(), 3.5, 0.16, 3.1, 0, 0.06, 0);
  hem.rotation.z = (r(80) - 0.5) * 0.05;
  g.add(hem);
  return g;
}

// --------------------------------------------------------------- fires
/** Bonfire: logs + stylized flame + optional point light. */
export function createBonfire(withLight = true, castShadow = false) {
  const g = new THREE.Group();

  for (let i = 0; i < 5; i++) {
    const log = mesh(Geo.cyl(), Mat.cageWood(), 0.16, 1.5, 0.16, 0, 0.18, 0);
    log.rotation.z = Math.PI / 2 - 0.5;
    log.rotation.y = (i / 5) * Math.PI * 2;
    g.add(log);
  }

  const flameOuter = mesh(Geo.cone(), Mat.flame(), 1.15, 1.7, 1.15, 0, 1.0, 0);
  flameOuter.castShadow = false;
  const flameInner = mesh(Geo.cone(), Mat.flameCore(), 0.62, 1.15, 0.62, 0, 0.85, 0);
  flameInner.castShadow = false;
  g.add(flameOuter, flameInner);

  let light = null;
  if (withLight) {
    light = new THREE.PointLight(0xff7b2e, 42, 26, 2);
    light.position.set(0, 2.1, 0);
    if (castShadow) {
      // one hero fire throws real shadows; the rest fake it with flicker
      light.castShadow = true;
      light.shadow.mapSize.set(512, 512);
      light.shadow.camera.near = 0.4;
      light.shadow.camera.far = 24;
      light.shadow.bias = -0.004;
    }
    g.add(light);
  }

  // cheap flame flicker
  g.userData.update = (t) => {
    const f = 1 + Math.sin(t * 9 + g.id) * 0.09 + Math.sin(t * 23 + g.id * 2) * 0.05;
    flameOuter.scale.set(1.15 * f, 1.7 * (2 - f) * 0.72, 1.15 * f);
    flameInner.scale.set(0.62 * (2 - f), 1.15 * f * 0.9, 0.62 * (2 - f));
    if (light) light.intensity = 38 + Math.sin(t * 13 + g.id) * 9;
  };

  return g;
}

// --------------------------------------------------------------- cage
/** Wooden cage holding the captives (the rescue target). */
export function createCage() {
  const g = new THREE.Group();
  const R = 2.6;

  // base ring
  const ring = new THREE.Mesh(new THREE.TorusGeometry(R, 0.12, 6, 22), Mat.cageWood());
  ring.rotation.x = Math.PI / 2;
  ring.position.y = 0.12;
  ring.castShadow = true;
  g.add(ring);

  // top ring
  const top = new THREE.Mesh(new THREE.TorusGeometry(R, 0.11, 6, 22), Mat.cageWood());
  top.rotation.x = Math.PI / 2;
  top.position.y = 2.9;
  top.castShadow = true;
  g.add(top);

  // bars
  const bars = 12;
  for (let i = 0; i < bars; i++) {
    const a = (i / bars) * Math.PI * 2;
    const bar = mesh(Geo.cyl(), Mat.cageWood(), 0.11, 2.9, 0.11,
      Math.cos(a) * R, 1.5, Math.sin(a) * R);
    g.add(bar);
  }

  // cross planks
  for (let i = 0; i < 4; i++) {
    const a = (i / 4) * Math.PI * 2 + 0.4;
    const plank = mesh(Geo.box(), Mat.cageWood(), R * 2, 0.16, 0.12,
      0, 1.1 + (i % 2) * 1.1, 0);
    plank.rotation.y = a;
    g.add(plank);
  }

  return g;
}

// ------------------------------------------------------------- humans
/**
 * Generic detailed humanoid with named pivot parts (see CharacterRig.js).
 * Kept as the zone-facing API: palette + pose, with optional gear overrides.
 * @param {{skin:THREE.Material, cloth:THREE.Material, cloth2?:THREE.Material,
 *          hair?:THREE.Material, leather?:THREE.Material, metal?:THREE.Material,
 *          accent?:THREE.Material}} palette
 * @param {'standing'|'sitting'|'celebrating'|'seatedCaptive'|'armsCrossed'|
 *         'grieving'|'slumpedWounded'|'reclining'} pose
 * @param {{beard?:string, hairStyle?:string, armor?:string, robe?:boolean,
 *          detail?:string}} [opts]
 */
let _pbrSwap = null;
/** Lambert palette → physical palette for the named figures. */
function pbrSwapMap() {
  if (_pbrSwap) return _pbrSwap;
  _pbrSwap = new Map([
    [Mat.skin(), Mat.skinPbr()],
    [Mat.skinDark(), Mat.skinPbrDark()],
    [Mat.hair(), Mat.hairPbr()],
    [Mat.leatherDark(), Mat.leatherPbr()],
    [Mat.brass(), Mat.bronzePbr()],
    [Mat.steelBloody(), Mat.ironPbr()],
    [Mat.royalBronze(), Mat.bronzePbr()],
  ]);
  return _pbrSwap;
}

export function createHumanoid(palette, pose = 'standing', opts = {}) {
  // named figures (detail: 'full') get PBR skin/gear + strand-card hair;
  // crowd extras keep the cheap Lambert palettes
  const full = (opts.detail ?? 'full') === 'full';
  let upgraded = palette;
  if (full) {
    const swap = pbrSwapMap();
    const next = {};
    for (const [k, v] of Object.entries(palette)) next[k] = swap.get(v) ?? v;
    if (!next.hairCard) next.hairCard = opts.grayHair ? Mat.hairCardGray() : Mat.hairCard();
    upgraded = next;
  }
  return createDetailedHuman({ palette: upgraded, pose, ...opts });
}

// ------------------------------------------------------------ beacons
/**
 * Glowing waypoint: pulsing ground ring + light pillar + floating label.
 * `group.userData.update(time)` animates the pulse.
 */
export function createBeacon(label, colorHex = 0xffc36a) {
  const g = new THREE.Group();

  const ring = new THREE.Mesh(Geo.ring(), Mat.beaconRing().clone());
  ring.material.color.set(colorHex);
  ring.rotation.x = -Math.PI / 2;
  ring.position.y = 0.06;
  ring.scale.setScalar(4.2);

  const pillar = new THREE.Mesh(Geo.cyl(), Mat.beacon().clone());
  pillar.material.color.set(colorHex);
  pillar.scale.set(0.8, 7, 0.8);
  pillar.position.y = 3.5;

  g.add(ring, pillar);
  g.userData.update = (t) => {
    const pulse = 1 + Math.sin(t * 2.2) * 0.12;
    ring.scale.setScalar(4.2 * pulse);
    pillar.material.opacity = 0.32 + Math.sin(t * 2.2) * 0.14;
    pillar.scale.y = 7 * (1 + Math.sin(t * 1.7) * 0.06);
  };
  g.userData.ring = ring;

  if (label) {
    // lazy import avoidance: label sprite is added by the zone
    g.userData.labelText = label;
  }
  return g;
}

// ============================================================ livestock
/**
 * Instanced herd — sheep, goats, and cattle as three draw calls per species
 * (body + head + legs). `groundHeight(x, z)` places each animal on the terrain.
 * @returns {THREE.Group}
 */
export function createHerd(count, area, seed = 1, groundHeight = () => 0) {
  count = qualityCount(count, 4);
  const g = new THREE.Group();
  const dummy = new THREE.Object3D();

  const sheepCount = Math.floor(count * 0.7);
  const cattleCount = count - sheepCount;

  // ---- sheep / goats ----
  const sheepBody = new THREE.InstancedMesh(Geo.ico(), Mat.fleece(), sheepCount);
  const sheepHead = new THREE.InstancedMesh(Geo.box(), Mat.goatHide(), sheepCount);
  const sheepLegs = new THREE.InstancedMesh(Geo.box(), Mat.hair(), sheepCount * 4);
  for (let i = 0; i < sheepCount; i++) {
    const r = (n) => seededRandom(seed * 21 + i * 3.7 + n);
    const x = randRange(area.minX, area.maxX);
    const z = randRange(area.minZ, area.maxZ);
    const y = groundHeight(x, z);
    const yaw = r(1) * Math.PI * 2;

    dummy.position.set(x, y + 0.55, z);
    dummy.scale.set(1.15, 0.85, 1.55);
    dummy.rotation.set(0, yaw, 0);
    dummy.updateMatrix();
    sheepBody.setMatrixAt(i, dummy.matrix);

    dummy.position.set(x + Math.sin(yaw) * 0.95, y + 0.62, z + Math.cos(yaw) * 0.95);
    dummy.scale.set(0.42, 0.4, 0.55);
    dummy.rotation.set(0.12, yaw, 0);
    dummy.updateMatrix();
    sheepHead.setMatrixAt(i, dummy.matrix);

    for (let l = 0; l < 4; l++) {
      const lx = (l % 2 === 0 ? -1 : 1) * 0.32;
      const lz = (l < 2 ? -1 : 1) * 0.5;
      dummy.position.set(
        x + Math.cos(yaw) * lx + Math.sin(yaw) * lz,
        y + 0.17,
        z - Math.sin(yaw) * lx + Math.cos(yaw) * lz,
      );
      dummy.scale.set(0.14, 0.36, 0.14);
      dummy.rotation.set(0, yaw, 0);
      dummy.updateMatrix();
      sheepLegs.setMatrixAt(i * 4 + l, dummy.matrix);
    }
  }

  // ---- cattle ----
  const cowBody = new THREE.InstancedMesh(Geo.ico(), Mat.cattleHide(), cattleCount);
  const cowHead = new THREE.InstancedMesh(Geo.box(), Mat.cattleHide(), cattleCount);
  const cowLegs = new THREE.InstancedMesh(Geo.box(), Mat.hair(), cattleCount * 4);
  for (let i = 0; i < cattleCount; i++) {
    const r = (n) => seededRandom(seed * 33 + i * 5.1 + n);
    const x = randRange(area.minX, area.maxX);
    const z = randRange(area.minZ, area.maxZ);
    const y = groundHeight(x, z);
    const yaw = r(1) * Math.PI * 2;

    dummy.position.set(x, y + 0.85, z);
    dummy.scale.set(1.5, 1.15, 2.3);
    dummy.rotation.set(0, yaw, 0);
    dummy.updateMatrix();
    cowBody.setMatrixAt(i, dummy.matrix);

    dummy.position.set(x + Math.sin(yaw) * 1.45, y + 1.0, z + Math.cos(yaw) * 1.45);
    dummy.scale.set(0.55, 0.55, 0.75);
    dummy.rotation.set(0.15, yaw, 0);
    dummy.updateMatrix();
    cowHead.setMatrixAt(i, dummy.matrix);

    for (let l = 0; l < 4; l++) {
      const lx = (l % 2 === 0 ? -1 : 1) * 0.45;
      const lz = (l < 2 ? -1 : 1) * 0.85;
      dummy.position.set(
        x + Math.cos(yaw) * lx + Math.sin(yaw) * lz,
        y + 0.32,
        z - Math.sin(yaw) * lx + Math.cos(yaw) * lz,
      );
      dummy.scale.set(0.2, 0.66, 0.2);
      dummy.rotation.set(0, yaw, 0);
      dummy.updateMatrix();
      cowLegs.setMatrixAt(i * 4 + l, dummy.matrix);
    }
  }

  for (const inst of [sheepBody, sheepHead, sheepLegs, cowBody, cowHead, cowLegs]) {
    inst.instanceMatrix.needsUpdate = true;
    inst.castShadow = true;
    inst.receiveShadow = true;
    g.add(inst);
  }
  return g;
}

/** A single low-poly sheep (for intimate staging near a campfire etc.). */
export function createSheep(seed = 1) {
  const r = (n) => seededRandom(seed * 4.7 + n);
  const g = new THREE.Group();
  g.add(mesh(Geo.ico(), Mat.fleece(), 1.15, 0.85, 1.55, 0, 0.55, 0));
  g.add(mesh(Geo.box(), Mat.goatHide(), 0.42, 0.4, 0.55, 0, 0.62, 0.95));
  for (let l = 0; l < 4; l++) {
    const lx = (l % 2 === 0 ? -1 : 1) * 0.32;
    const lz = (l < 2 ? -1 : 1) * 0.5;
    g.add(mesh(Geo.box(), Mat.hair(), 0.14, 0.36, 0.14, lx, 0.17, lz));
  }
  g.rotation.y = r(1) * Math.PI * 2;
  return g;
}

// ==================================================== baggage & tribute
/** Heavy supply wagon: iron-rimmed wheels, canvas tarp, hemp lashings. */
export function createBaggageWagon(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 8.9 + n);

  // deck + sideboards
  g.add(mesh(Geo.box(), Mat.cedarWood(), 3.6, 0.28, 1.9, 0, 1.0, 0));
  g.add(mesh(Geo.box(), Mat.cedarWood(), 3.6, 0.85, 0.14, 0, 1.5, -0.95));
  g.add(mesh(Geo.box(), Mat.cedarWood(), 3.6, 0.85, 0.14, 0, 1.5, 0.95));

  // canvas tarp over the load (slumped)
  const tarp = mesh(Geo.box(), Mat.canvasTarp(), 3.2, 1.15, 1.75, 0, 2.05, 0);
  tarp.rotation.z = (r(1) - 0.5) * 0.12;
  tarp.rotation.x = (r(2) - 0.5) * 0.1;
  g.add(tarp);

  // hemp lashings
  for (let i = 0; i < 3; i++) {
    const rope = mesh(Geo.box(), Mat.rope(), 0.08, 2.5, 1.9, -1.1 + i * 1.1, 1.9, 0);
    rope.rotation.x = Math.PI / 2;
    rope.scale.y = 0.06;
    g.add(rope);
  }

  // iron-rimmed wheels ("caked in dry, white river mud")
  for (const wx of [-1.25, 1.25]) {
    for (const wz of [-1.05, 1.05]) {
      const rim = new THREE.Mesh(Geo.torus(), Mat.ironDark());
      rim.position.set(wx, 0.85, wz);
      rim.rotation.y = Math.PI / 2;
      rim.scale.setScalar(1.7);
      rim.castShadow = true;
      g.add(rim);
      const hub = mesh(Geo.cyl(), Mat.riverMud(), 0.32, 0.22, 0.32, wx, 0.85, wz);
      hub.rotation.x = Math.PI / 2;
      g.add(hub);
      for (let s = 0; s < 4; s++) {
        const spoke = mesh(Geo.box(), Mat.cedarWood(), 0.1, 1.5, 0.1, wx, 0.85, wz);
        spoke.rotation.x = Math.PI / 2;
        spoke.rotation.y = Math.PI / 2;
        spoke.rotation.z = (s / 4) * Math.PI;
        g.add(spoke);
      }
    }
  }

  // tow pole
  const pole = mesh(Geo.cyl(), Mat.cedarWood(), 0.12, 2.6, 0.12, 2.7, 1.0, 0);
  pole.rotation.z = Math.PI / 2;
  g.add(pole);
  return g;
}

/** Iron-bound cedar tribute chest (optionally open, showing gold). */
export function createChest(seed = 1, open = false) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 6.1 + n);

  g.add(mesh(Geo.box(), Mat.cedarWood(), 1.35, 0.72, 0.85, 0, 0.36, 0));
  for (const bx of [-0.52, 0, 0.52]) {
    g.add(mesh(Geo.box(), Mat.bronzeBand(), 0.1, 0.76, 0.89, bx, 0.36, 0));
  }
  g.add(mesh(Geo.box(), Mat.bronzeBand(), 1.39, 0.1, 0.89, 0, 0.36, 0));

  const lid = mesh(Geo.box(), Mat.cedarWood(), 1.35, 0.16, 0.85, 0, 0.82, open ? -0.36 : 0);
  if (open) lid.rotation.x = -1.15;
  g.add(lid);

  if (open) {
    const gold = mesh(Geo.ico(), Mat.goldLoot(), 0.9, 0.35, 0.55, 0, 0.72, 0.05);
    gold.castShadow = true;
    g.add(gold);
    for (let i = 0; i < 4; i++) {
      g.add(mesh(Geo.box(), r(i) > 0.5 ? Mat.silverLoot() : Mat.goldLoot(),
        0.18, 0.05, 0.18,
        (r(10 + i) - 0.5) * 0.9, 0.76, (r(20 + i) - 0.5) * 0.5));
    }
  }
  g.rotation.y = r(3) * 0.6 - 0.3;
  return g;
}

/** Heap of pillaged gold, silver, and spilled wine (PHASE 3 material spec). */
export function createTreasurePile(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 12.3 + n);
  for (let i = 0; i < 7; i++) {
    const m = r(i) > 0.45 ? Mat.goldLoot() : Mat.silverLoot();
    const s = 0.22 + r(10 + i) * 0.3;
    const lump = mesh(Geo.ico(), m, s, s * 0.7, s, (r(20 + i) - 0.5) * 1.4, s * 0.35, (r(30 + i) - 0.5) * 1.4);
    g.add(lump);
  }
  // polished vessels
  const bowl = new THREE.Mesh(new THREE.TorusGeometry(0.34, 0.12, 6, 12), Mat.silverLoot());
  bowl.position.set(0.5, 0.18, -0.3);
  bowl.rotation.x = Math.PI / 2.2;
  bowl.castShadow = true;
  g.add(bowl);
  return g;
}

/** Overturned wine amphora + spilled pool (zero-roughness liquid). */
export function createSpilledAmphora(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 7.9 + n);

  const spill = new THREE.Mesh(Geo.plane(), Mat.spill());
  spill.rotation.x = -Math.PI / 2;
  spill.position.y = 0.045;
  spill.scale.set(1.6 + r(1), 1.2 + r(2), 1);
  g.add(spill);

  const body = mesh(Geo.cone(), Mat.rockDark(), 0.75, 1.25, 0.75, 0.35, 0.55, 0.1);
  body.rotation.z = Math.PI / 2 + (r(3) - 0.5) * 0.5;
  body.rotation.y = r(4) * Math.PI;
  g.add(body);
  const neck = mesh(Geo.cyl(), Mat.rockDark(), 0.16, 0.5, 0.16, -0.25, 0.42, 0.1);
  neck.rotation.z = Math.PI / 2;
  g.add(neck);
  return g;
}

/** Flat woven rug (stolen goods underfoot). */
export function createRug(seed = 1) {
  const r = (n) => seededRandom(seed * 5.5 + n);
  const colors = [Mat.elderMadder(), Mat.elderPurple(), Mat.tunicIndigo(), Mat.canvasTarp()];
  const rug = mesh(Geo.box(), colors[Math.floor(r(1) * colors.length)], 2.6, 0.06, 1.7, 0, 0.05, 0);
  rug.rotation.y = r(2) * Math.PI;
  return rug;
}

// ================================================= animals of the road
/** Pack mule with canvas panniers. */
export function createMule(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 9.3 + n);

  g.add(mesh(Geo.box(), Mat.muleHide(), 0.85, 0.85, 1.85, 0, 1.05, 0)); // body
  g.add(mesh(Geo.box(), Mat.muleHide(), 0.5, 0.55, 0.75, 0, 1.35, 1.15)); // head
  g.add(mesh(Geo.box(), Mat.muleHide(), 0.12, 0.42, 0.1, -0.16, 1.85, 1.05)); // ears
  g.add(mesh(Geo.box(), Mat.muleHide(), 0.12, 0.42, 0.1, 0.16, 1.85, 1.05));
  for (const lx of [-0.3, 0.3]) {
    for (const lz of [-0.65, 0.65]) {
      g.add(mesh(Geo.box(), Mat.muleHide(), 0.18, 0.85, 0.18, lx, 0.42, lz));
    }
  }
  // panniers
  g.add(mesh(Geo.box(), Mat.canvasTarp(), 0.42, 0.62, 1.05, -0.62, 1.28, -0.1));
  g.add(mesh(Geo.box(), Mat.canvasTarp(), 0.42, 0.62, 1.05, 0.62, 1.28, -0.1));
  g.add(mesh(Geo.box(), Mat.rope(), 0.95, 0.1, 1.15, 0, 1.62, -0.1));

  g.rotation.y = r(1) * Math.PI * 2;
  return g;
}

/** Fleeing camel silhouette (the 400 who escaped on camels). */
export function createCamel(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 15.7 + n);

  g.add(mesh(Geo.box(), Mat.camelHide(), 1.05, 1.0, 2.3, 0, 1.55, 0)); // body
  g.add(mesh(Geo.ico(), Mat.camelHide(), 1.05, 0.95, 1.25, 0, 2.35, -0.15)); // hump
  const neck = mesh(Geo.box(), Mat.camelHide(), 0.42, 1.55, 0.42, 0, 2.35, 1.15);
  neck.rotation.x = -0.5;
  g.add(neck);
  g.add(mesh(Geo.box(), Mat.camelHide(), 0.38, 0.4, 0.85, 0, 3.15, 1.62)); // head
  for (const lx of [-0.38, 0.38]) {
    for (const lz of [-0.85, 0.85]) {
      const leg = mesh(Geo.box(), Mat.camelHide(), 0.2, 1.45, 0.2, lx, 0.72, lz);
      leg.rotation.x = (r(lx * 3 + lz) - 0.5) * 0.5; // mid-gallop
      g.add(leg);
    }
  }
  g.rotation.y = r(1) * Math.PI * 2;
  return g;
}

// ==================================================== ruins & war debris
/** Heavy wooden gate with wind-blown sand drifts piling against it. */
export function createGateWithDrifts(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 11.1 + n);

  // posts + lintel
  g.add(mesh(Geo.box(), Mat.cageWood(), 0.55, 3.6, 0.55, -1.9, 1.8, 0));
  g.add(mesh(Geo.box(), Mat.cageWood(), 0.55, 3.6, 0.55, 1.9, 1.8, 0));
  g.add(mesh(Geo.box(), Mat.cageWood(), 4.6, 0.5, 0.7, 0, 3.75, 0));

  // planked door, slightly ajar
  for (let i = 0; i < 5; i++) {
    const plank = mesh(Geo.box(), Mat.cageWood(), 0.62, 3.1, 0.16, -1.35 + i * 0.68, 1.58, 0);
    plank.rotation.y = -0.18;
    g.add(plank);
    // iron studs
    g.add(mesh(Geo.box(), Mat.ironDark(), 0.12, 0.12, 0.2, -1.35 + i * 0.68, 2.6, 0.08));
    g.add(mesh(Geo.box(), Mat.ironDark(), 0.12, 0.12, 0.2, -1.35 + i * 0.68, 0.7, 0.08));
  }

  // sand drifts (flattened lumps banked against both sides)
  for (let i = 0; i < 6; i++) {
    const side = i % 2 === 0 ? -1 : 1;
    const drift = mesh(Geo.ico(), Mat.sand(), 1.6 + r(i) * 1.2, 0.55 + r(i + 6) * 0.3, 1.1,
      side * (1.6 + r(i + 12) * 1.4), 0.12, (r(i + 18) - 0.5) * 1.6);
    drift.rotation.y = r(i + 24) * Math.PI;
    g.add(drift);
  }
  return g;
}

/** The stone outcrop of the dividing line, with a broken spear planted on it. */
export function createOutcrop(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 18.3 + n);

  for (let i = 0; i < 5; i++) {
    const rock = mesh(Geo.dodeca(), Mat.rock(), 1.6 + r(i) * 1.3, 1.2 + r(i + 5) * 1.5, 1.5 + r(i + 10),
      (r(i + 15) - 0.5) * 1.8, 0.7 + r(i + 20) * 1.1, (r(i + 25) - 0.5) * 1.8);
    rock.rotation.set(r(i + 30) * 0.5, r(i + 35) * Math.PI, r(i + 40) * 0.5);
    g.add(rock);
  }
  const top = mesh(Geo.dodeca(), Mat.rock(), 2.2, 0.85, 2.0, 0, 2.35, 0);
  g.add(top);

  // broken spear — the staff of office
  const shaft = mesh(Geo.cyl(), Mat.cedarWood(), 0.07, 2.3, 0.07, 0.55, 3.6, -0.3);
  shaft.rotation.z = 0.12;
  shaft.rotation.x = -0.1;
  g.add(shaft);
  const tip = mesh(Geo.cone(), Mat.steelBloody(), 0.14, 0.55, 0.14, 0.62, 4.85, -0.38);
  tip.rotation.z = 0.12;
  g.add(tip);
  return g;
}

/** Saul's broken chariot wheel on Gilboa. */
export function createChariotWreck(seed = 1) {
  const g = new THREE.Group();

  const rim = new THREE.Mesh(Geo.torus(), Mat.cedarWood());
  rim.position.y = 1.35;
  rim.rotation.y = 0.5;
  rim.scale.setScalar(2.9);
  rim.castShadow = true;
  g.add(rim);
  const ironRim = new THREE.Mesh(Geo.torus(), Mat.ironDark());
  ironRim.position.copy(rim.position);
  ironRim.rotation.copy(rim.rotation);
  ironRim.scale.setScalar(2.95);
  ironRim.scale.y = 0.55;
  g.add(ironRim);

  for (let s = 0; s < 5; s++) {
    if (s === 2) continue; // snapped spoke
    const spoke = mesh(Geo.box(), Mat.cedarWood(), 0.16, 2.6, 0.16, 0, 1.35, 0);
    spoke.rotation.z = (s / 6) * Math.PI * 2;
    spoke.rotation.y = 0.5;
    g.add(spoke);
  }
  // axle stub + splintered deck
  g.add(mesh(Geo.box(), Mat.cedarWood(), 0.35, 0.35, 2.2, 1.4, 1.1, -0.6));
  const deck = mesh(Geo.box(), Mat.cedarWood(), 2.4, 0.16, 1.5, -1.6, 0.35, 0.4);
  deck.rotation.set(0.25, 0.7, -0.35);
  g.add(deck);
  return g;
}

/** Scattered shield + spear debris along a broken shield-wall. */
export function createShieldDebris(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 22.7 + n);

  for (let i = 0; i < 5; i++) {
    const shield = new THREE.Mesh(Geo.cyl(), r(i) > 0.5 ? Mat.cedarWood() : Mat.ironDark());
    shield.scale.set(1.1, 0.12, 1.1);
    shield.position.set((r(i * 3) - 0.5) * 4.5, 0.18, (r(i * 3 + 1) - 0.5) * 3.5);
    shield.rotation.set(Math.PI / 2 + (r(i * 3 + 2) - 0.5) * 0.7, 0, r(i * 3 + 3) * Math.PI);
    shield.castShadow = true;
    shield.receiveShadow = true;
    g.add(shield);
  }
  for (let i = 0; i < 4; i++) {
    const spear = mesh(Geo.cyl(), Mat.cedarWood(), 0.05, 2.8, 0.05,
      (r(i * 5 + 30) - 0.5) * 4.5, 0.16, (r(i * 5 + 31) - 0.5) * 3.5);
    spear.rotation.z = Math.PI / 2;
    spear.rotation.y = r(i * 5 + 32) * Math.PI;
    g.add(spear);
    const head = mesh(Geo.cone(), Mat.ironDark(), 0.1, 0.4, 0.1,
      spear.position.x + 1.5, 0.16, spear.position.z);
    head.rotation.z = -Math.PI / 2;
    g.add(head);
  }
  return g;
}

/**
 * The great limestone city gate (PHASE 6): two block pillars, lintel,
 * and flanking walls with visible masonry seams.
 */
export function createCityGate(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 27.1 + n);

  // pillars built from stacked blocks
  for (const side of [-1, 1]) {
    for (let row = 0; row < 5; row++) {
      const block = mesh(Geo.box(), row % 2 ? Mat.limestone() : Mat.limestonePale(),
        2.6, 1.35, 2.4,
        side * 4.6 + (r(row + side * 7) - 0.5) * 0.12,
        0.68 + row * 1.33,
        (r(row + 20 + side) - 0.5) * 0.12);
      g.add(block);
    }
  }
  // lintel + battlement
  g.add(mesh(Geo.box(), Mat.limestone(), 11.8, 1.5, 2.6, 0, 7.4, 0));
  for (let i = 0; i < 6; i++) {
    g.add(mesh(Geo.box(), Mat.limestonePale(), 1.1, 0.85, 2.2, -5.2 + i * 2.1, 8.55, 0));
  }

  // flanking walls with block seams
  for (const side of [-1, 1]) {
    for (let col = 0; col < 3; col++) {
      for (let row = 0; row < 3; row++) {
        g.add(mesh(Geo.box(), (col + row) % 2 ? Mat.limestone() : Mat.limestonePale(),
          2.4, 1.3, 1.8,
          side * (7.2 + col * 2.35),
          0.65 + row * 1.28,
          (r(col * 7 + row + side * 3) - 0.5) * 0.1));
      }
    }
  }

  // wooden doors, opened inward
  for (const side of [-1, 1]) {
    const door = mesh(Geo.box(), Mat.cageWood(), 3.3, 5.6, 0.28, side * 2.1, 2.8, -0.9);
    door.rotation.y = side * 0.55;
    g.add(door);
    for (let stud = 0; stud < 6; stud++) {
      g.add(mesh(Geo.box(), Mat.ironDark(), 0.16, 0.16, 0.34,
        side * 2.1 + (r(stud + side * 40) - 0.5) * 2.2, 1.1 + stud * 0.85, -0.9));
    }
  }
  return g;
}

/** Royal crown for Saul (torus + points). */
export function createCrown() {
  const g = new THREE.Group();
  const band = new THREE.Mesh(new THREE.TorusGeometry(0.26, 0.055, 6, 16), Mat.goldLoot());
  band.rotation.x = Math.PI / 2;
  band.castShadow = true;
  g.add(band);
  for (let i = 0; i < 5; i++) {
    const pt = mesh(Geo.cone(), Mat.goldLoot(), 0.07, 0.16, 0.07,
      Math.cos((i / 5) * Math.PI * 2) * 0.26, 0.1, Math.sin((i / 5) * Math.PI * 2) * 0.26);
    g.add(pt);
  }
  return g;
}

// ======================================================= fields & flora
/** Wind-swayed instanced grass. One draw call for the whole field. */
function windMaterial(baseMaterial) {
  const mat = baseMaterial;
  mat.onBeforeCompile = (shader) => {
    shader.uniforms.uTime = { value: 0 };
    shader.vertexShader = `uniform float uTime;\n${shader.vertexShader}`.replace(
      '#include <begin_vertex>',
      `#include <begin_vertex>
       #ifdef USE_INSTANCING
         float sway = sin(uTime * 1.6 + instanceMatrix[3][0] * 0.9 + instanceMatrix[3][2] * 0.7);
         float bend = pow(max(position.y, 0.0), 1.5);
         transformed.x += sway * bend * 0.16;
         transformed.z += cos(uTime * 1.2 + instanceMatrix[3][0]) * bend * 0.09;
       #endif`,
    );
    mat.userData.shader = shader;
  };
  return mat;
}

/**
 * Grass field: thousands of tapered blades, instanced, wind-swayed.
 * group.userData.update(time) advances the wind.
 */
export function createGrassField(count, area, seed = 1, groundHeight = () => 0, opts = {}) {
  count = qualityCount(count, 60);
  const { color = 0x7d8f48, height = 0.85, dryness = 0 } = opts;
  const blade = new THREE.ConeGeometry(0.055, 1, 3);
  blade.translate(0, 0.5, 0);

  const mat = windMaterial(new THREE.MeshLambertMaterial({ color, flatShading: true }));
  const inst = new THREE.InstancedMesh(blade, mat, count);
  inst.castShadow = false;
  inst.receiveShadow = true;

  const dummy = new THREE.Object3D();
  const c = new THREE.Color();
  for (let i = 0; i < count; i++) {
    const r = (n) => seededRandom(seed * 31 + i * 2.3 + n);
    const x = randRange(area.minX, area.maxX);
    const z = randRange(area.minZ, area.maxZ);
    const y = groundHeight(x, z);
    const s = 0.55 + r(1) * 0.9;
    dummy.position.set(x, y, z);
    dummy.scale.set(0.8 + r(2) * 0.5, s * height, 0.8 + r(3) * 0.5);
    dummy.rotation.set((r(4) - 0.5) * 0.3, r(5) * Math.PI * 2, (r(6) - 0.5) * 0.3);
    dummy.updateMatrix();
    inst.setMatrixAt(i, dummy.matrix);
    const dry = r(7) < dryness;
    c.setHSL(0.22 - r(8) * 0.06 - (dry ? 0.09 : 0), 0.38 + r(9) * 0.14, 0.32 + r(10) * 0.16);
    inst.setColorAt(i, c);
  }
  inst.instanceMatrix.needsUpdate = true;
  if (inst.instanceColor) inst.instanceColor.needsUpdate = true;

  const g = new THREE.Group();
  g.add(inst);
  g.userData.update = (t) => {
    if (mat.userData.shader) mat.userData.shader.uniforms.uTime.value = t;
  };
  return g;
}

/** Golden wheat — taller blades with a grain head, wind-swept. */
export function createWheatField(count, area, seed = 1, groundHeight = () => 0) {
  count = qualityCount(count, 40);
  // blade + grain head merged into one instance geometry
  const blade = new THREE.ConeGeometry(0.045, 1, 3);
  blade.translate(0, 0.55, 0);
  const head = new THREE.CylinderGeometry(0.035, 0.055, 0.22, 4);
  head.translate(0, 1.12, 0);
  const geo = mergeGeometries([blade, head], false);

  const mat = windMaterial(new THREE.MeshLambertMaterial({ color: 0xd9b45c, flatShading: true }));
  const inst = new THREE.InstancedMesh(geo, mat, count);

  const dummy = new THREE.Object3D();
  const c = new THREE.Color();
  for (let i = 0; i < count; i++) {
    const r = (n) => seededRandom(seed * 47 + i * 3.7 + n);
    const x = randRange(area.minX, area.maxX);
    const z = randRange(area.minZ, area.maxZ);
    dummy.position.set(x, groundHeight(x, z), z);
    dummy.scale.setScalar(0.8 + r(1) * 0.5);
    dummy.rotation.set((r(2) - 0.5) * 0.22, r(3) * Math.PI * 2, (r(4) - 0.5) * 0.22);
    dummy.updateMatrix();
    inst.setMatrixAt(i, dummy.matrix);
    c.setHSL(0.12 + r(5) * 0.035, 0.5 + r(6) * 0.16, 0.42 + r(7) * 0.12);
    inst.setColorAt(i, c);
  }
  inst.instanceMatrix.needsUpdate = true;
  if (inst.instanceColor) inst.instanceColor.needsUpdate = true;

  const g = new THREE.Group();
  g.add(inst);
  g.userData.update = (t) => {
    if (mat.userData.shader) mat.userData.shader.uniforms.uTime.value = t;
  };
  return g;
}

/** Wildflower sprinkle — colored bloom + stem, instanced. */
export function createWildflowers(count, area, seed = 1, groundHeight = () => 0) {
  count = qualityCount(count, 18);
  const stem = new THREE.CylinderGeometry(0.012, 0.018, 0.34, 3);
  stem.translate(0, 0.17, 0);
  const bloom = new THREE.IcosahedronGeometry(0.05, 0);
  bloom.translate(0, 0.38, 0);
  const geo = mergeGeometries([stem, bloom], false);

  const mat = new THREE.MeshLambertMaterial({ color: 0xffffff, flatShading: true });
  const inst = new THREE.InstancedMesh(geo, mat, count);
  const dummy = new THREE.Object3D();
  const c = new THREE.Color();
  const palette = [0xc85a4a, 0xd9a24a, 0x8a6ab0, 0xd8d0e8, 0xb04a6a];
  for (let i = 0; i < count; i++) {
    const r = (n) => seededRandom(seed * 59 + i * 4.1 + n);
    const x = randRange(area.minX, area.maxX);
    const z = randRange(area.minZ, area.maxZ);
    dummy.position.set(x, groundHeight(x, z), z);
    dummy.scale.setScalar(0.8 + r(1) * 0.6);
    dummy.rotation.set((r(2) - 0.5) * 0.35, r(3) * Math.PI * 2, (r(3.5) - 0.5) * 0.35);
    dummy.updateMatrix();
    inst.setMatrixAt(i, dummy.matrix);
    c.setHex(palette[Math.floor(r(4) * palette.length)]);
    inst.setColorAt(i, c);
  }
  inst.instanceMatrix.needsUpdate = true;
  if (inst.instanceColor) inst.instanceColor.needsUpdate = true;

  const g = new THREE.Group();
  g.add(inst);
  return g;
}

/** Gnarled olive tree — twisted trunk segments + silver-green canopy clumps. */
export function createOliveTree(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 6.13 + n);
  const h = 2.2 + r(1) * 1.4;

  // twisted trunk from angled segments
  let x = 0; let z = 0; let y = 0;
  for (let i = 0; i < 3; i++) {
    const seg = mesh(Geo.cyl(), Mat.palmTrunk(), 0.3 - i * 0.07, h / 2.4, 0.3 - i * 0.07,
      x, y + h / 4.8, z);
    seg.rotation.z = (r(10 + i) - 0.5) * 0.55;
    seg.rotation.x = (r(20 + i) - 0.5) * 0.45;
    g.add(seg);
    x += (r(30 + i) - 0.5) * 0.65;
    z += (r(40 + i) - 0.5) * 0.65;
    y += h / 2.7;
  }

  // silver-green canopy: overlapping clumps (olive tones)
  const leaf = new THREE.MeshLambertMaterial({ color: 0x8a9a6a, flatShading: true });
  const leaf2 = new THREE.MeshLambertMaterial({ color: 0x6d7c52, flatShading: true });
  for (let i = 0; i < 7; i++) {
    const clump = mesh(Geo.ico(), i % 2 ? leaf : leaf2,
      1.5 + r(50 + i) * 1.3, 0.95 + r(60 + i) * 0.8, 1.5 + r(70 + i) * 1.3,
      x + (r(80 + i) - 0.5) * 3.2, y + 0.6 + r(90 + i) * 1.5, z + (r(100 + i) - 0.5) * 3.2);
    clump.castShadow = true;
    g.add(clump);
  }
  return g;
}

/** Tall dark cypress — the silhouette of Judean hillsides. */
export function createCypress(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 7.3 + n);
  const h = 3.2 + r(1) * 1.8;
  const trunk = mesh(Geo.cyl(), Mat.palmTrunk(), 0.14, h * 0.9, 0.14, 0, h * 0.45, 0);
  g.add(trunk);
  const dark = new THREE.MeshLambertMaterial({ color: 0x2e4228, flatShading: true });
  for (let i = 0; i < 4; i++) {
    const clump = mesh(Geo.cone(), dark,
      0.85 - i * 0.13, 1.6 - i * 0.2, 0.85 - i * 0.13,
      0, h * (0.42 + i * 0.17), 0);
    clump.rotation.y = r(10 + i) * Math.PI;
    g.add(clump);
  }
  return g;
}

/** Reed bed for brook banks — tall thin clusters. */
export function createReeds(count, area, seed = 1, groundHeight = () => 0) {
  count = qualityCount(count, 24);
  return createGrassField(count, area, seed, groundHeight, { color: 0x7a8a3c, height: 1.5 });
}

// ==================================================== landscape & life
/**
 * Distant mountain ridge — a jagged silhouette strip that reads as
 * landscape depth once fog blends it into the sky.
 */
export function createMountainRidge(seed = 1, {
  width = 420, height = 46, depth = 30, segments = 72, color = 0x6a7488,
} = {}) {
  const positions = [];
  const r = (n) => seededRandom(seed * 41 + n);
  const hAt = (i) => {
    const t = i / segments;
    return height * (
      0.35
      + 0.4 * Math.abs(Math.sin(t * 7 + seed))
      + 0.25 * Math.abs(Math.sin(t * 17 + seed * 2.3))
      + 0.15 * r(i * 1.7)
    );
  };
  for (let i = 0; i < segments; i++) {
    const x0 = -width / 2 + (i / segments) * width;
    const x1 = -width / 2 + ((i + 1) / segments) * width;
    const h0 = hAt(i);
    const h1 = hAt(i + 1);
    // front-facing fan triangle (mountains as an infinite wall)
    positions.push(x0, 0, 0, x1, 0, 0, x0, h0, 0);
    positions.push(x1, 0, 0, x1, h1, 0, x0, h0, 0);
    // thickness skirt
    positions.push(x0, 0, 0, x0, h0, 0, x0, 0, -depth);
    positions.push(x0, h0, 0, x0, h0 * 0.2, -depth, x0, 0, -depth);
    positions.push(x1, 0, 0, x1, 0, -depth, x1, h1, 0);
    positions.push(x1, h1, 0, x1, 0, -depth, x1, h1 * 0.2, -depth);
  }
  const geo = new THREE.BufferGeometry();
  geo.setAttribute('position', new THREE.Float32BufferAttribute(positions, 3));
  geo.computeVertexNormals();
  const mat = new THREE.MeshLambertMaterial({ color, flatShading: true });
  const mesh = new THREE.Mesh(geo, mat);
  mesh.receiveShadow = false;
  mesh.castShadow = false;
  return mesh;
}

/**
 * Circling bird flock — tiny chevrons with flapping wings.
 * group.userData.update(time) animates the flight.
 */
export function createBirdFlock(count, center = new THREE.Vector3(), radius = 40, height = 30, seed = 1) {
  count = qualityCount(count, 6);
  const g = new THREE.Group();
  const birds = [];
  const bodyMat = new THREE.MeshBasicMaterial({ color: 0x241f26 });

  for (let i = 0; i < count; i++) {
    const r = (n) => seededRandom(seed * 17 + i * 3.1 + n);
    const bird = new THREE.Group();
    const body = new THREE.Mesh(Geo.box(), bodyMat);
    body.scale.set(0.16, 0.1, 0.5);
    const wingL = new THREE.Mesh(Geo.box(), bodyMat);
    wingL.scale.set(0.9, 0.05, 0.3);
    wingL.position.set(-0.5, 0.05, 0);
    const wingR = new THREE.Mesh(Geo.box(), bodyMat);
    wingR.scale.set(0.9, 0.05, 0.3);
    wingR.position.set(0.5, 0.05, 0);
    bird.add(body, wingL, wingR);
    g.add(bird);
    birds.push({
      bird, wingL, wingR,
      phase: r(1) * Math.PI * 2,
      speed: 0.14 + r(2) * 0.12,
      radius: radius * (0.55 + r(3) * 0.55),
      height: height * (0.7 + r(4) * 0.6),
      flap: 6 + r(5) * 5,
    });
  }

  g.userData.update = (t) => {
    for (const b of birds) {
      const a = b.phase + t * b.speed;
      b.bird.position.set(
        center.x + Math.cos(a) * b.radius,
        b.height + Math.sin(t * 0.4 + b.phase) * 2.2,
        center.z + Math.sin(a) * b.radius,
      );
      b.bird.rotation.y = -a - Math.PI / 2;
      const flap = Math.sin(t * b.flap + b.phase * 3) * 0.65;
      b.wingL.rotation.z = flap;
      b.wingR.rotation.z = -flap;
    }
  };
  return g;
}

/**
 * Butterflies — bright two-wing flakes drifting over the meadow.
 * group.userData.update(time) wanders them along slow lissajous paths.
 */
export function createButterflies(count, area, groundHeight = () => 0, seed = 1) {
  count = qualityCount(count, 8);
  const g = new THREE.Group();
  const palette = [0xffd24a, 0xff8ac2, 0x8ad8ff, 0xfff4e0, 0xff6a4a];
  const items = [];
  for (let i = 0; i < count; i++) {
    const r = (n) => seededRandom(seed * 53 + i * 2.9 + n);
    const mat = new THREE.MeshBasicMaterial({
      color: palette[Math.floor(r(1) * palette.length)],
      side: THREE.DoubleSide,
    });
    const fly = new THREE.Group();
    const wingL = new THREE.Mesh(Geo.plane(), mat);
    wingL.scale.setScalar(0.16);
    wingL.position.x = -0.09;
    const wingR = new THREE.Mesh(Geo.plane(), mat);
    wingR.scale.setScalar(0.16);
    wingR.position.x = 0.09;
    fly.add(wingL, wingR);
    g.add(fly);
    items.push({
      fly, wingL, wingR,
      cx: randRange(area.minX, area.maxX),
      cz: randRange(area.minZ, area.maxZ),
      phase: r(2) * Math.PI * 2,
      speed: 0.35 + r(3) * 0.4,
      span: 1.6 + r(4) * 2.2,
    });
  }
  g.userData.update = (t) => {
    for (const b of items) {
      const x = b.cx + Math.sin(t * b.speed + b.phase) * b.span;
      const z = b.cz + Math.cos(t * b.speed * 0.7 + b.phase * 1.7) * b.span;
      const y = groundHeight(x, z) + 0.7 + Math.sin(t * 1.3 + b.phase) * 0.35;
      b.fly.position.set(x, y, z);
      b.fly.rotation.y = t * b.speed + b.phase;
      const flap = Math.sin(t * 14 + b.phase) * 0.95;
      b.wingL.rotation.y = flap;
      b.wingR.rotation.y = -flap;
    }
  };
  return g;
}

/** A live grazing sheep — head dips to the grass and lifts, ears twitch. */
export function createGrazingSheep(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 4.7 + n);
  g.add(mesh(Geo.ico(), Mat.fleece(), 1.15, 0.85, 1.55, 0, 0.55, 0));
  for (let l = 0; l < 4; l++) {
    const lx = (l % 2 === 0 ? -1 : 1) * 0.32;
    const lz = (l < 2 ? -1 : 1) * 0.5;
    g.add(mesh(Geo.box(), Mat.hair(), 0.14, 0.36, 0.14, lx, 0.17, lz));
  }
  const head = new THREE.Group();
  head.position.set(0, 0.68, 0.85);
  head.add(mesh(Geo.box(), Mat.goatHide(), 0.42, 0.4, 0.55, 0, 0, 0.28));
  head.add(mesh(Geo.box(), Mat.goatHide(), 0.3, 0.16, 0.2, 0, 0.1, 0.62));
  g.add(head);
  g.rotation.y = r(1) * Math.PI * 2;
  g.userData.update = (t) => {
    const graze = (Math.sin(t * 0.5 + seed) + 1) / 2; // 0..1 slow cycle
    head.rotation.x = graze * 0.9 - 0.25;
    head.position.y = 0.68 - graze * 0.34;
  };
  return g;
}

/** A water jar — for carrying poses and courtyard dressing. */
export function createJar(seed = 1, mat = null) {
  const r = (n) => seededRandom(seed * 8.8 + n);
  const m = mat ?? Mat.rockDark();
  const g = new THREE.Group();
  g.add(mesh(Geo.sphere(), m, 0.5, 0.62, 0.5, 0, 0.3, 0));
  g.add(mesh(Geo.cyl(), m, 0.16, 0.32, 0.16, 0, 0.72, 0));
  g.add(mesh(Geo.torus(), m, 0.18, 0.045, 0.18, 0, 0.86, 0));
  g.rotation.y = r(1) * Math.PI;
  return g;
}


// ============================================================ PASS 8 props
/**
 * Acacia — the flat-topped thorn tree of the wadi. A leaning trunk, a few
 * forked limbs and two or three umbrella canopies made of flattened, spiky
 * clusters. Cheap (one material) but instantly reads "Judean wilderness".
 */
export function createAcacia(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 5.7 + n);
  const h = 3.4 + r(1) * 2.2;

  const trunk = mesh(Geo.cyl(), Mat.acaciaBark(), 0.24, h, 0.24, 0, h / 2, 0);
  trunk.rotation.z = (r(2) - 0.5) * 0.24;
  g.add(trunk);

  // forked limbs
  for (let i = 0; i < 4; i++) {
    const a = (i / 4) * Math.PI * 2 + r(3) * 0.5;
    const limb = mesh(Geo.cyl(), Mat.acaciaBark(), 0.10, 1.5, 0.10,
      Math.cos(a) * 0.55, h * 0.82, Math.sin(a) * 0.55);
    limb.rotation.z = Math.cos(a) * 0.75;
    limb.rotation.x = -Math.sin(a) * 0.75;
    g.add(limb);
  }

  // umbrella canopy — flattened clusters, deliberately uneven
  const pads = 3 + ((r(4) * 3) | 0);
  for (let i = 0; i < pads; i++) {
    const a = (i / pads) * Math.PI * 2 + r(10 + i) * 0.8;
    const rad = 0.7 + r(20 + i) * 1.5;
    const pad = mesh(Geo.sphere(), Mat.acaciaLeaf(),
      2.6 + r(30 + i) * 1.4, 0.5 + r(40 + i) * 0.3, 2.2 + r(50 + i) * 1.2,
      Math.cos(a) * rad, h + 0.25 + r(60 + i) * 0.35, Math.sin(a) * rad);
    pad.rotation.y = r(70 + i) * Math.PI;
    g.add(pad);
  }
  // a few bare twigs poking through the canopy
  for (let i = 0; i < 5; i++) {
    const a = r(80 + i) * Math.PI * 2;
    const twig = mesh(Geo.cyl(), Mat.acaciaBark(), 0.035, 1.1, 0.035,
      Math.cos(a) * (1.2 + r(90 + i)), h + 0.5, Math.sin(a) * (1.2 + r(100 + i)));
    twig.rotation.z = (r(110 + i) - 0.5) * 1.1;
    twig.rotation.x = (r(120 + i) - 0.5) * 1.1;
    g.add(twig);
  }
  return bakeStatic(g);
}

/** A dry, sun-resistant desert shrub — a fist of pale thorny stems. */
export function createDesertScrub(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 3.9 + n);
  for (let i = 0; i < 9; i++) {
    const a = r(i * 2.1) * Math.PI * 2;
    const h = 0.3 + r(i * 3.7) * 0.55;
    const stem = mesh(Geo.cyl(), Mat.desertScrub(), 0.035, h, 0.035,
      Math.cos(a) * 0.16, h / 2, Math.sin(a) * 0.16);
    stem.rotation.z = Math.cos(a) * (0.2 + r(i * 5.3) * 0.65);
    stem.rotation.x = -Math.sin(a) * (0.2 + r(i * 6.1) * 0.65);
    g.add(stem);
  }
  // seed heads / dry flower tufts
  for (let i = 0; i < 4; i++) {
    g.add(mesh(Geo.sphere(), Mat.desertScrub(), 0.14, 0.1, 0.14,
      (r(i) - 0.5) * 0.4, 0.34 + r(i * 2) * 0.3, (r(i * 3) - 0.5) * 0.4));
  }
  return bakeStatic(g);
}

/** Instanced micro-debris: the gravel and cracked flint of the Judean path. */
export function createPebbleField(count, area, seed = 1, groundHeight = () => 0) {
  const n = qualityCount(count, 12);
  const geo = Geo.dodeca();
  const inst = new THREE.InstancedMesh(geo, Mat.rockDark(), n);
  inst.castShadow = false;
  inst.receiveShadow = true;
  const dummy = new THREE.Object3D();
  for (let i = 0; i < n; i++) {
    const x = (seededRandom(seed * 2.3 + i * 1.7) - 0.5) * area;
    const z = (seededRandom(seed * 4.1 + i * 2.9) - 0.5) * area;
    const s = 0.05 + seededRandom(seed * 6.7 + i) * 0.22;
    dummy.position.set(x, groundHeight(x, z) + s * 0.22, z);
    dummy.rotation.set(seededRandom(i * 3.3) * 3, seededRandom(i * 5.1) * 3, seededRandom(i * 7.7) * 3);
    dummy.scale.set(s, s * 0.7, s * 1.2);
    dummy.updateMatrix();
    inst.setMatrixAt(i, dummy.matrix);
  }
  inst.instanceMatrix.needsUpdate = true;
  return inst;
}

/** Scattered raider pottery: whole jars, a tipped amphora, fired shards. */
export function createPotteryCluster(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 8.3 + n);
  for (let i = 0; i < 5; i++) {
    const jar = new THREE.Group();
    const body = mesh(Geo.sphere(), r(i) > 0.4 ? Mat.pottery() : Mat.potteryDark(),
      0.42 + r(i * 2) * 0.2, 0.5 + r(i * 3) * 0.24, 0.42 + r(i * 4) * 0.2, 0, 0.3, 0);
    const neck = mesh(Geo.cyl(), Mat.pottery(), 0.15, 0.22, 0.15, 0, 0.62, 0);
    const rim = mesh(Geo.cyl(), Mat.potteryDark(), 0.21, 0.05, 0.21, 0, 0.74, 0);
    jar.add(body, neck, rim);
    jar.position.set((r(i * 5) - 0.5) * 1.8, 0, (r(i * 6) - 0.5) * 1.8);
    jar.rotation.set(r(i * 7) > 0.7 ? 1.3 : 0, r(i * 8) * 6, r(i * 9) > 0.7 ? 0.4 : 0);
    g.add(jar);
  }
  // shards
  for (let i = 0; i < 6; i++) {
    const shard = mesh(Geo.box(), Mat.pottery(), 0.16 + r(i) * 0.12, 0.02, 0.2,
      (r(i * 2) - 0.5) * 2.4, 0.02, (r(i * 3) - 0.5) * 2.4);
    shard.rotation.set(0, r(i * 4) * 6, (r(i * 5) - 0.5) * 0.5);
    g.add(shard);
  }
  return bakeStatic(g);
}

/** A makeshift rack of spears and round hide shields. */
export function createWeaponRack(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 6.1 + n);
  for (let side = -1; side <= 1; side += 2) {
    const post = mesh(Geo.cyl(), Mat.cageWood(), 0.08, 1.9, 0.08, side * 1.1, 0.95, 0);
    post.rotation.z = side * 0.16;
    g.add(post);
    const foot = mesh(Geo.cyl(), Mat.cageWood(), 0.07, 1.3, 0.07, side * 0.85, 0.4, 0.45);
    foot.rotation.x = -0.9;
    g.add(foot);
  }
  g.add(mesh(Geo.cyl(), Mat.cageWood(), 0.06, 2.5, 0.06, 0, 1.55, 0).rotateZ(Math.PI / 2));
  // spears leaning on the rack
  for (let i = 0; i < 4; i++) {
    const x = -0.8 + i * 0.55;
    const shaft = mesh(Geo.cyl(), Mat.cedarWood(), 0.045, 2.5, 0.045, x, 1.25, 0.18);
    shaft.rotation.z = (r(i) - 0.5) * 0.18;
    const head = mesh(Geo.cone(), Mat.ironPbr(), 0.11, 0.42, 0.11, x, 2.56, 0.18);
    head.rotation.z = -Math.PI;
    g.add(shaft, head);
  }
  // shields
  for (let i = 0; i < 2; i++) {
    const shield = mesh(Geo.cyl(), Mat.leatherPbr(), 0.62, 0.08, 0.62, -0.45 + i * 0.9, 0.72, 0.42);
    shield.rotation.x = Math.PI / 2 - 0.18;
    const boss = mesh(Geo.sphere(), Mat.bronzePbr(), 0.18, 0.09, 0.18, -0.45 + i * 0.9, 0.62, 0.55);
    g.add(shield, boss);
  }
  return bakeStatic(g);
}

/** Collapsed roof timbers, still smouldering at the joints. */
export function createCollapsedTimber(seed = 1) {
  const g = new THREE.Group();
  const r = (n) => seededRandom(seed * 4.7 + n);
  for (let i = 0; i < 7; i++) {
    const len = 1.8 + r(i) * 2.4;
    const beam = mesh(Geo.box(), Mat.charWood(), 0.18 + r(i * 2) * 0.12, len, 0.2, 0, 0, 0);
    beam.position.set((r(i * 3) - 0.5) * 1.6, len * 0.35, (r(i * 4) - 0.5) * 1.6);
    beam.rotation.set((r(i * 5) - 0.5) * 1.2, r(i * 6) * 3, (r(i * 7) - 0.5) * 1.4);
    g.add(beam);
  }
  // glowing joints
  for (let i = 0; i < 4; i++) {
    const ember = new THREE.Mesh(Geo.sphere(), Mat.emberBed());
    ember.scale.set(0.16 + r(i) * 0.12, 0.08, 0.16 + r(i * 2) * 0.12);
    ember.position.set((r(i * 3) - 0.5) * 1.4, 0.07, (r(i * 4) - 0.5) * 1.4);
    g.add(ember);
  }
  return bakeStatic(g);
}

/** A ground decal of soot and char around a burnt structure. */
export function createSootPatch(seed = 1, radius = 3.2) {
  const r = (n) => seededRandom(seed * 9.1 + n);
  const patch = new THREE.Mesh(new THREE.CircleGeometry(radius, 18), Mat.soot());
  patch.rotation.x = -Math.PI / 2;
  patch.rotation.z = r(1) * Math.PI * 2;
  patch.scale.set(1, 0.7 + r(2) * 0.5, 1);
  patch.position.y = 0.02;
  patch.renderOrder = 1;
  return patch;
}

/** A decal of sun-cracked, flaking earth. */
export function createCrackedPatch(seed = 1, radius = 4) {
  const r = (n) => seededRandom(seed * 8.7 + n);
  const patch = new THREE.Mesh(new THREE.CircleGeometry(radius, 16), Mat.crackedEarth());
  patch.rotation.x = -Math.PI / 2;
  patch.rotation.z = r(1) * Math.PI * 2;
  patch.scale.set(1, 0.65 + r(2) * 0.5, 1);
  patch.position.y = 0.015;
  patch.renderOrder = 1;
  return patch;
}

/** Wet river stones — dark, glossy, half-sunk in the Besor shallows. */
export function createWetStones(count, area, seed = 1, groundHeight = () => 0) {
  const n = qualityCount(count, 10);
  const inst = new THREE.InstancedMesh(Geo.dodeca(), Mat.wetStone(), n);
  inst.receiveShadow = true;
  const dummy = new THREE.Object3D();
  for (let i = 0; i < n; i++) {
    const x = (seededRandom(seed * 3.1 + i * 1.9) - 0.5) * area;
    const z = (seededRandom(seed * 5.3 + i * 2.3) - 0.5) * 8;
    const s = 0.25 + seededRandom(seed * 7.7 + i) * 0.65;
    dummy.position.set(x, groundHeight(x, z) + s * 0.18, z);
    dummy.rotation.set(seededRandom(i * 2.7) * 3, seededRandom(i * 4.3) * 3, seededRandom(i * 6.1) * 3);
    dummy.scale.set(s, s * 0.6, s * 1.1);
    dummy.updateMatrix();
    inst.setMatrixAt(i, dummy.matrix);
  }
  inst.instanceMatrix.needsUpdate = true;
  return inst;
}
