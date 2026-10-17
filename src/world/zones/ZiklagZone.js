/**
 * ZiklagZone.js — PHASE 1: "The Ashes of Ziklag"
 *
 * Dark ash ground, burnt low-poly houses along a debris trail, rising smoke
 * and ember particles. A glowing beacon marks the "Desert Edge" waypoint.
 */
import * as THREE from 'three';
import { QUALITY } from '../../core/quality.js';
import { Mat, Geo } from '../../utils/materials.js';
import {
  createBurntHouse, createCharredTree, createBeacon, createGateWithDrifts,
} from '../props/PropFactory.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';
import { applyTerrainColors } from '../../utils/terrain.js';
import { createBirdFlock } from '../props/PropFactory.js';

export const ZIKLAG = {
  spawn: { x: 0, z: 26, yaw: 0 },
  waypoint: { x: 0, z: -36 },
  bounds: { minX: -55, maxX: 55, minZ: -58, maxZ: 40 },
};

export function ziklagHeight(x, z) {
  return Math.sin(x * 0.08) * 0.35 + Math.cos(z * 0.07) * 0.3;
}

export function createZiklagZone() {
  const group = new THREE.Group();
  group.name = 'ZONE_ZIKLAG';
  const colliders = [];
  const updaters = [];

  // ------------------------------------------------------------- sky
  // PHASE 1 spec: late-afternoon golden hour, 5600K, low-angle sun
  const sky = new SkyDome(0x3b1a8e, 0xff5e4d, 0.55, 0xd94a8c); // Ocean Drive sunset
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0x6a5a78, 0x8a5a38, 0.9);
  group.add(hemi);

  // 5600K golden-hour key light held LOW on the horizon — long raking shadows
  const sun = new THREE.DirectionalLight(0xffb060, 1.1);
  sun.position.set(-38, 20, 26);
  sun.castShadow = true;
  sun.shadow.mapSize.set(QUALITY.shadowMap, QUALITY.shadowMap);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 160;
  sun.shadow.camera.left = -42;
  sun.shadow.camera.right = 42;
  sun.shadow.camera.top = 42;
  sun.shadow.camera.bottom = -42;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(-36, 18, 24);
  group.add(sun, sun.target);
  sky.setSun(sun.userData.offset, 0xffb060, 0.85);

  // flickering fires at two ruined houses
  const fireA = new THREE.PointLight(0xff6a2a, 30, 24, 2);
  fireA.position.set(-12, 1.6, -6);
  const fireB = new THREE.PointLight(0xff7b3a, 26, 22, 2);
  fireB.position.set(14, 1.6, -18);
  group.add(fireA, fireB);

  // --------------------------------------------------------- terrain
  const groundGeo = new THREE.PlaneGeometry(130, 120, 48, 42);
  groundGeo.rotateX(-Math.PI / 2);
  const pos = groundGeo.attributes.position;
  for (let i = 0; i < pos.count; i++) {
    const x = pos.getX(i);
    const z = pos.getZ(i);
    pos.setY(i, ziklagHeight(x, z));
  }
  groundGeo.computeVertexNormals();
  applyTerrainColors(groundGeo, {
    colorA: new THREE.Color(0xc9987a), colorB: new THREE.Color(0x8a5c52),
    colorC: new THREE.Color(0x4a2c3c), scale: 0.045, strength: 0.5, seed: 3,
  });
  const groundMat = Mat.ashGround().clone();
  groundMat.vertexColors = true;
  const ground = new THREE.Mesh(groundGeo, groundMat);
  ground.receiveShadow = true;
  group.add(ground);

  // scorched path (slightly lighter strip toward the waypoint)
  const path = new THREE.Mesh(Geo.plane(), new THREE.MeshLambertMaterial({ color: 0x453a31 }));
  path.rotation.x = -Math.PI / 2;
  path.scale.set(7, 82, 1);
  path.position.set(0, 0.05, -8);
  path.receiveShadow = true;
  group.add(path);

  // ------------------------------------------------------- burnt huts
  // houses flank the debris trail from the spawn to the Desert Edge
  const housePositions = [
    [-9, 16], [10, 12], [-13, 4], [12, -2], [-8, -12], [11, -16],
    [-16, -22], [15, -26], [-11, -30], [9, -34], [-20, 18], [19, 2],
  ];
  housePositions.forEach(([x, z], i) => {
    const house = createBurntHouse(i + 1);
    house.position.set(x, ziklagHeight(x, z), z);
    house.rotation.y = seededRandom(i * 3.7) * Math.PI * 2;
    group.add(house);
    colliders.push({ x, z, r: 2.6 });
    updaters.push((t) => house.userData.update?.(t));
  });

  // charred trees
  for (let i = 0; i < 9; i++) {
    const a = seededRandom(i * 5.1) * Math.PI * 2;
    const rad = 14 + seededRandom(i * 9.2) * 30;
    const x = Math.cos(a) * rad * 1.15;
    const z = -8 + Math.sin(a) * rad;
    if (Math.abs(x) < 4) continue; // keep the trail clear
    const tree = createCharredTree(i + 1);
    tree.position.set(x, ziklagHeight(x, z), z);
    group.add(tree);
    colliders.push({ x, z, r: 0.55 });
  }

  // scattered debris rocks
  for (let i = 0; i < 14; i++) {
    const x = (seededRandom(i * 2.3) - 0.5) * 90;
    const z = (seededRandom(i * 6.1) - 0.5) * 80;
    const s = 0.4 + seededRandom(i * 8.8) * 1.1;
    const lump = new THREE.Mesh(Geo.dodeca(), Mat.ashDebris());
    lump.position.set(x, s * 0.25, z);
    lump.scale.setScalar(s);
    lump.rotation.set(seededRandom(i) * 2, seededRandom(i * 2) * 2, 0);
    lump.castShadow = true;
    lump.receiveShadow = true;
    group.add(lump);
  }

  // ----------------------------- collapsed granary + black smoke column
  const granary = createBurntHouse(99);
  granary.scale.setScalar(1.6);
  granary.position.set(20, ziklagHeight(20, -28), -28);
  granary.rotation.y = 1.2;
  group.add(granary);
  colliders.push({ x: 20, z: -28, r: 4.2 });

  // heavy wooden gate banked by wind-blown sand drifts
  const gate = createGateWithDrifts(5);
  gate.position.set(-24, ziklagHeight(-24, 8), 8);
  gate.rotation.y = 0.9;
  group.add(gate);
  colliders.push({ x: -24, z: 8, r: 2.2 });

  // ---------------------------------------------------------- beacon
  const beacon = createBeacon('Desert Edge', 0x53e0ff);
  beacon.position.set(ZIKLAG.waypoint.x, ziklagHeight(ZIKLAG.waypoint.x, ZIKLAG.waypoint.z), ZIKLAG.waypoint.z);
  const label = makeTextSprite('DESERT EDGE', { color: '#c9f2ff', bg: 'rgba(8,20,32,0.55)' });
  label.position.set(0, 6.4, 0);
  beacon.add(label);
  group.add(beacon);
  updaters.push((t) => beacon.userData.update(t));

  // ------------------------------------------------------- particles
  const smokeA = new ParticleEmitter({
    count: 90,
    origin: new THREE.Vector3(-9, 2.2, 16),
    spread: 2.4,
    rise: 1.35,
    riseJitter: 0.5,
    drift: 0.5,
    life: 6.5,
    lifeJitter: 0.35,
    size: 26,
    sizeGrow: 42,
    alpha: 0.34,
    rate: 0,
    color: 0x4a4038,
    colorEnd: 0x241f1c,
  });
  const smokeB = new ParticleEmitter({
    count: 70,
    origin: new THREE.Vector3(12, 2.2, -2),
    spread: 2.0,
    rise: 1.15,
    riseJitter: 0.5,
    drift: 0.45,
    life: 7,
    lifeJitter: 0.35,
    size: 24,
    sizeGrow: 40,
    alpha: 0.3,
    rate: 0,
    color: 0x52453c,
    colorEnd: 0x2a221e,
  });
  const smokeC = new ParticleEmitter({
    count: 70,
    origin: new THREE.Vector3(11, 2.2, -16),
    spread: 2.2,
    rise: 1.25,
    riseJitter: 0.5,
    drift: 0.5,
    life: 6.8,
    lifeJitter: 0.35,
    size: 25,
    sizeGrow: 40,
    alpha: 0.32,
    rate: 0,
    color: 0x4e4238,
    colorEnd: 0x281f1a,
  });
  const embers = new ParticleEmitter({
    count: 120,
    origin: new THREE.Vector3(0, 0.6, -4),
    spread: 22,
    rise: 1.6,
    riseJitter: 0.65,
    drift: 0.85,
    life: 5,
    lifeJitter: 0.5,
    size: 5,
    sizeGrow: 1.5,
    alpha: 0.85,
    rate: 0,
    color: 0xff9a3c,
    colorEnd: 0xff3c14,
    additive: true,
  });
  // dense, lingering black column from the collapsed granary
  const smokeColumn = new ParticleEmitter({
    count: 110,
    origin: new THREE.Vector3(20, 3.4, -28),
    spread: 2.8,
    rise: 2.1,
    riseJitter: 0.4,
    drift: 1.0,
    life: 9.5,
    lifeJitter: 0.3,
    size: 30,
    sizeGrow: 58,
    alpha: 0.42,
    rate: 0,
    color: 0x2c2622,
    colorEnd: 0x120e0c,
  });
  // volumetric dust streams + ground-level heat shimmer, raked by the low sun
  const dustStreams = new ParticleEmitter({
    count: 90,
    origin: new THREE.Vector3(-4, 2.2, -6),
    spread: 16,
    rise: 0.35,
    riseJitter: 1.1,
    drift: 1.5,
    life: 7.5,
    lifeJitter: 0.45,
    size: 12,
    sizeGrow: 7,
    alpha: 0.12,
    rate: 0,
    color: 0xffd9a8,
    colorEnd: 0xc89666,
    additive: true,
  });
  const heatShimmer = new ParticleEmitter({
    count: 70,
    origin: new THREE.Vector3(0, 0.8, 2),
    spread: 26,
    rise: 0.55,
    riseJitter: 0.6,
    drift: 0.7,
    life: 4,
    lifeJitter: 0.5,
    size: 16,
    sizeGrow: 9,
    alpha: 0.08,
    rate: 0,
    color: 0xe8c396,
    colorEnd: 0xb8863c,
    additive: true,
  });
  // wind-blown sand gathering against the gate
  const sandDrift = new ParticleEmitter({
    count: 60,
    origin: new THREE.Vector3(-24, 0.7, 8),
    spread: 5.5,
    rise: 0.5,
    riseJitter: 1.2,
    drift: 1.8,
    life: 4.5,
    lifeJitter: 0.5,
    size: 7,
    sizeGrow: 4,
    alpha: 0.2,
    rate: 0,
    color: 0xd9be86,
    colorEnd: 0xb89a66,
  });
  group.add(smokeA.points, smokeB.points, smokeC.points, embers.points, smokeColumn.points, dustStreams.points, heatShimmer.points, sandDrift.points);

  // scavenger birds circling the smoke column
  const birds = createBirdFlock(8, new THREE.Vector3(10, 0, -10), 34, 26, 5);
  group.add(birds.points ?? birds);
  updaters.push((t) => birds.userData.update(t));

  return {
    name: 'STATE_0_ZIKLAG',
    group,
    spawn: ZIKLAG.spawn,
    bounds: ZIKLAG.bounds,
    colliders,
    fog: { color: 0xff6a52, density: 0.012 },
    sun,
    sky,
    groundHeight: ziklagHeight,
    update(dt, time) {
      smokeA.update(dt);
      smokeB.update(dt);
      smokeC.update(dt);
      embers.update(dt);
      smokeColumn.update(dt);
      dustStreams.update(dt);
      heatShimmer.update(dt);
      sandDrift.update(dt);
      fireA.intensity = 26 + Math.sin(time * 11) * 8;
      fireB.intensity = 22 + Math.sin(time * 9 + 2) * 7;
      for (const u of updaters) u(time);
    },
  };
}
