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

    case 'armsCrossed':
      // closed, aggrieved body language (the greedy 400)
      armL.rotation.x = -1.35;
      armR.rotation.x = -1.35;
      armL.rotation.z = -0.55;
      armR.rotation.z = 0.55;
      head.rotation.x = -0.08;
      break;

    case 'grieving':
      // slumped shoulders, head hung, hands open at the sides
      torso.rotation.x = 0.16;
      head.rotation.x = 0.42;
      armL.rotation.x = -0.25;
      armR.rotation.x = -0.25;
      armL.rotation.z = 0.14;
      armR.rotation.z = -0.14;
      break;

    case 'slumpedWounded':
      // Saul against the chariot wheel: one knee down, head heavy
      g.position.y = -0.32;
      torso.rotation.x = 0.32;
      head.rotation.x = 0.5;
      legL.rotation.x = -1.25;
      legR.rotation.x = -0.35;
      armL.rotation.x = -0.95;
      armL.rotation.z = 0.25;
      armR.rotation.x = -1.5;
      armR.rotation.z = -0.3;
      break;

    case 'reclining':
      // intoxicated Amalekite slumped over rugs
      g.position.y = -0.55;
      g.rotation.x = -0.35;
      torso.rotation.x = 0.45;
      head.rotation.x = 0.55;
      legL.rotation.x = -1.35;
      legR.rotation.x = -1.15;
      armL.rotation.x = -1.7;
      armR.rotation.x = -1.2;
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

// ============================================================ livestock
/**
 * Instanced herd — sheep, goats, and cattle as three draw calls per species
 * (body + head + legs). `groundHeight(x, z)` places each animal on the terrain.
 * @returns {THREE.Group}
 */
export function createHerd(count, area, seed = 1, groundHeight = () => 0) {
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
