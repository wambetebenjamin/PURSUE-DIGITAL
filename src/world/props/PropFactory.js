/**
 * PropFactory.js — reusable low-poly prop builders.
 *
 * Everything is assembled from shared primitives (see utils/materials.js).
 * Builders return a THREE.Group; the caller is responsible for positioning,
 * rotating and registering colliders.
 */
import * as THREE from 'three';
import { Mat, Geo } from '../../utils/materials.js';
import { randRange, seededRandom } from '../../utils/math.js';

// ------------------------------------------------------------ helpers
function mesh(geo, mat, sx = 1, sy = 1, sz = 1, x = 0, y = 0, z = 0) {
  const m = new THREE.Mesh(geo, mat);
  m.scale.set(sx, sy, sz);
  m.position.set(x, y, z);
  m.castShadow = true;
  m.receiveShadow = true;
  return m;
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

  // slumped roof
  const roof = mesh(Geo.box(), Mat.charRoof(), w + 0.5, 0.16, d + 0.5, 0, h + 0.12, 0);
  roof.rotation.z = (r(6) - 0.5) * 0.34;
  roof.rotation.x = (r(7) - 0.5) * 0.22;
  g.add(roof);

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

  return g;
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
  return g;
}

// --------------------------------------------------------------- fires
/** Bonfire: logs + stylized flame + optional point light. */
export function createBonfire(withLight = true) {
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
 * Generic low-poly humanoid with named pivot parts.
 * @param {{skin:THREE.Material, cloth:THREE.Material, cloth2?:THREE.Material, hair?:THREE.Material}} palette
 * @param {'standing'|'sitting'|'celebrating'|'seatedCaptive'} pose
 */
export function createHumanoid(palette, pose = 'standing') {
  const g = new THREE.Group();
  const skin = palette.skin;
  const cloth = palette.cloth;
  const cloth2 = palette.cloth2 ?? cloth;
  const hair = palette.hair ?? Mat.hair();

  const torso = mesh(Geo.box(), cloth, 0.62, 0.8, 0.36, 0, 1.14, 0);
  const sash = mesh(Geo.box(), cloth2, 0.66, 0.15, 0.4, 0, 0.84, 0);
  const head = mesh(Geo.sphere(), skin, 0.4, 0.42, 0.4, 0, 1.8, 0);
  const hairMesh = mesh(Geo.sphere(), hair, 0.44, 0.3, 0.44, 0, 1.96, 0.02);
  const neck = mesh(Geo.cyl(), skin, 0.14, 0.18, 0.14, 0, 1.58, 0);

  // pivots
  const armL = new THREE.Group();
  const armR = new THREE.Group();
  armL.position.set(-0.42, 1.44, 0);
  armR.position.set(0.42, 1.44, 0);
  armL.add(
    mesh(Geo.box(), cloth, 0.17, 0.36, 0.19, 0, -0.18, 0),
    mesh(Geo.box(), skin, 0.14, 0.38, 0.16, 0, -0.5, 0),
  );
  armR.add(
    mesh(Geo.box(), cloth, 0.17, 0.36, 0.19, 0, -0.18, 0),
    mesh(Geo.box(), skin, 0.14, 0.38, 0.16, 0, -0.5, 0),
  );

  const legL = new THREE.Group();
  const legR = new THREE.Group();
  legL.position.set(-0.17, 0.82, 0);
  legR.position.set(0.17, 0.82, 0);
  legL.add(mesh(Geo.box(), cloth2, 0.25, 0.78, 0.28, 0, -0.39, 0));
  legR.add(mesh(Geo.box(), cloth2, 0.25, 0.78, 0.28, 0, -0.39, 0));

  g.add(torso, sash, head, hairMesh, neck, armL, armR, legL, legR);

  const parts = { armL, armR, legL, legR, head, torso };

  switch (pose) {
    case 'sitting':
      // folded on the ground
      g.position.y = -0.42;
      torso.rotation.x = 0.18;
      head.position.set(0, 1.62, 0.18);
      hairMesh.position.set(0, 1.78, 0.18);
      neck.position.set(0, 1.42, 0.08);
      legL.rotation.x = -1.45;
      legR.rotation.x = -1.45;
      legL.rotation.z = 0.35;
      legR.rotation.z = -0.35;
      armL.rotation.x = -0.75;
      armR.rotation.x = -0.75;
      armL.rotation.z = 0.22;
      armR.rotation.z = -0.22;
      break;

    case 'celebrating':
      armL.rotation.z = 2.45;
      armR.rotation.z = -2.45;
      armL.rotation.x = -0.25;
      armR.rotation.x = -0.25;
      head.rotation.x = -0.16;
      break;

    case 'seatedCaptive':
      g.position.y = -0.5;
      torso.rotation.x = 0.25;
      head.position.set(0, 1.56, 0.22);
      hairMesh.position.set(0, 1.72, 0.22);
      neck.position.set(0, 1.38, 0.1);
      legL.rotation.x = -1.5;
      legR.rotation.x = -1.5;
      armL.rotation.x = -1.15;
      armR.rotation.x = -1.15;
      armL.rotation.z = 0.35;
      armR.rotation.z = -0.35;
      head.rotation.x = 0.3;
      break;

    default:
      break;
  }

  g.traverse((o) => {
    if (o.isMesh) {
      o.castShadow = true;
      o.receiveShadow = false;
    }
  });

  g.userData.parts = parts;
  return g;
}

// ------------------------------------------------------------ beacons
/**
 * Glowing waypoint: pulsing ground ring + light pillar + floating label.
 * `group.userData.update(time)` animates the pulse.
 */
export function createBeacon(label, colorHex = 0x53e0ff) {
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
