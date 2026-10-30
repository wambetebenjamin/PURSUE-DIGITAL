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
  createSootPatch, createCollapsedTimber, createPotteryCluster, createWeaponRack,
  createCrackedPatch,
} from '../props/PropFactory.js';
import { smokeSpriteMap } from '../../utils/surfaces.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { ATMOSPHERE } from '../atmospheres.js';
import { createLightingRig, attachRig } from '../effects/LightingRig.js';
import { createHorizon } from '../effects/Horizon.js';
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
  // late afternoon over the burning town — smoke still in the air
  const atmo = ATMOSPHERE.ziklag;
  const rig = createLightingRig(atmo, { shadowExtent: 44, shadowFar: 180 });
  attachRig(group, rig);
  const { sky, hemi, sun } = rig;
  // layered ranges dissolve into this phase's haze — no fog wall at the edge
  group.add(createHorizon(atmo));

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
    colorA: new THREE.Color(0xbfa084), colorB: new THREE.Color(0x87695a),
    colorC: new THREE.Color(0x453528), scale: 0.045, strength: 0.5, seed: 3,
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

  // soot scars and cracked earth around every burnt shell
  housePositions.forEach(([x, z], i) => {
    const soot = createSootPatch(i + 1, 4.2 + seededRandom(i * 2.7) * 2.4);
    soot.position.set(x + (seededRandom(i * 3.1) - 0.5) * 2, ziklagHeight(x, z) + 0.02,
      z + (seededRandom(i * 4.3) - 0.5) * 2);
    group.add(soot);
    if (i % 3 === 0) {
      const crack = createCrackedPatch(i + 20, 5 + seededRandom(i) * 3);
      crack.position.set(x + 4, ziklagHeight(x + 4, z) + 0.02, z - 3);
      group.add(crack);
    }
  });

  // collapsed roof timbers and the raiders' abandoned gear
  const timberSpots = [[-7, 10], [13, -8], [-12, -24], [18, 6], [-18, -4], [6, 20]];
  timberSpots.forEach(([x, z], i) => {
    const timber = createCollapsedTimber(i + 1);
    timber.position.set(x, ziklagHeight(x, z), z);
    timber.rotation.y = seededRandom(i * 6.3) * Math.PI * 2;
    group.add(timber);
  });
  for (let i = 0; i < 5; i++) {
    const x = (seededRandom(i * 5.3) - 0.5) * 54;
    const z = (seededRandom(i * 8.1) - 0.5) * 44;
    const pottery = createPotteryCluster(i + 3);
    pottery.position.set(x, ziklagHeight(x, z), z);
    pottery.rotation.y = seededRandom(i * 2.9) * Math.PI * 2;
    group.add(pottery);
  }
  const rack = createWeaponRack(4);
  rack.position.set(-17, ziklagHeight(-17, 14), 14);
  rack.rotation.y = 0.8;
  group.add(rack);
  colliders.push({ x: -17, z: 14, r: 1.3 });

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
  const beacon = createBeacon('Desert Edge', 0xffc36a);
  beacon.position.set(ZIKLAG.waypoint.x, ziklagHeight(ZIKLAG.waypoint.x, ZIKLAG.waypoint.z), ZIKLAG.waypoint.z);
  const label = makeTextSprite('DESERT EDGE', { color: '#ffeabd', bg: 'rgba(48,32,8,0.55)' });
  label.position.set(0, 6.4, 0);
  beacon.add(label);
  group.add(beacon);
  updaters.push((t) => beacon.userData.update(t));

  // ------------------------------------------------------- particles
  const smokeMap = smokeSpriteMap();
  const smokeA = new ParticleEmitter({
    map: smokeMap,
    count: 90,
    origin: new THREE.Vector3(-9, 2.2, 16),
    spread: 2.4,
    rise: 1.35,
    riseJitter: 0.5,
    drift: 0.5,
    life: 6.5,
    lifeJitter: 0.35,
    size: 34,
    sizeGrow: 64,
    alpha: 0.5,
    rate: 0,
    color: 0x4a4038,
    colorEnd: 0x241f1c,
  });
  const smokeB = new ParticleEmitter({
    map: smokeMap,
    count: 70,
    origin: new THREE.Vector3(12, 2.2, -2),
    spread: 2.0,
    rise: 1.15,
    riseJitter: 0.5,
    drift: 0.45,
    life: 7,
    lifeJitter: 0.35,
    size: 32,
    sizeGrow: 58,
    alpha: 0.44,
    rate: 0,
    color: 0x52453c,
    colorEnd: 0x2a221e,
  });
  const smokeC = new ParticleEmitter({
    map: smokeMap,
    count: 70,
    origin: new THREE.Vector3(11, 2.2, -16),
    spread: 2.2,
    rise: 1.25,
    riseJitter: 0.5,
    drift: 0.5,
    life: 6.8,
    lifeJitter: 0.35,
    size: 33,
    sizeGrow: 60,
    alpha: 0.46,
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
    map: smokeMap,
    count: 110,
    origin: new THREE.Vector3(20, 3.4, -28),
    spread: 2.8,
    rise: 2.1,
    riseJitter: 0.4,
    drift: 1.0,
    life: 9.5,
    lifeJitter: 0.3,
    size: 42,
    sizeGrow: 88,
    alpha: 0.58,
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
  // falling ash — the town is still settling, ember-grey against the sun
  const ashFall = new ParticleEmitter({
    count: 150,
    origin: new THREE.Vector3(0, 7, -6),
    spread: 30,
    rise: -0.32,
    riseJitter: 0.7,
    drift: 0.55,
    life: 12,
    lifeJitter: 0.4,
    size: 3,
    sizeGrow: 1,
    alpha: 0.5,
    rate: 0,
    color: 0x9a8e84,
    colorEnd: 0x4a423c,
  });
  group.add(smokeA.points, smokeB.points, smokeC.points, embers.points, smokeColumn.points, dustStreams.points, heatShimmer.points, sandDrift.points, ashFall.points);

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
    fog: { color: atmo.fog.color, density: atmo.fog.density },
    atmosphere: atmo,
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
      ashFall.update(dt);
      fireA.intensity = 26 + Math.sin(time * 11) * 8;
      fireB.intensity = 22 + Math.sin(time * 9 + 2) * 7;
      for (const u of updaters) u(time);
    },
  };
}
