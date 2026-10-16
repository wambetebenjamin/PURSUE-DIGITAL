/**
 * ZiklagZone.js — PHASE 1: "The Ashes of Ziklag"
 *
 * Dark ash ground, burnt low-poly houses along a debris trail, rising smoke
 * and ember particles. A glowing beacon marks the "Desert Edge" waypoint.
 */
import * as THREE from 'three';
import { Mat, Geo } from '../../utils/materials.js';
import { createBurntHouse, createCharredTree, createBeacon } from '../props/PropFactory.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';

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
  const sky = new SkyDome(0x0d0b14, 0x4a2415, 0.62);
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0x2c2440, 0x201812, 0.85);
  group.add(hemi);

  const sun = new THREE.DirectionalLight(0xff8a4a, 0.85);
  sun.position.set(-30, 40, 25);
  sun.castShadow = true;
  sun.shadow.mapSize.set(1024, 1024);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 160;
  sun.shadow.camera.left = -42;
  sun.shadow.camera.right = 42;
  sun.shadow.camera.top = 42;
  sun.shadow.camera.bottom = -42;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(-28, 42, 22);
  group.add(sun, sun.target);

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
  const ground = new THREE.Mesh(groundGeo, Mat.ashGround());
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
  group.add(smokeA.points, smokeB.points, smokeC.points, embers.points);

  return {
    name: 'STATE_0_ZIKLAG',
    group,
    spawn: ZIKLAG.spawn,
    bounds: ZIKLAG.bounds,
    colliders,
    fog: { color: 0x241c18, density: 0.016 },
    sun,
    groundHeight: ziklagHeight,
    update(dt, time) {
      smokeA.update(dt);
      smokeB.update(dt);
      smokeC.update(dt);
      embers.update(dt);
      fireA.intensity = 26 + Math.sin(time * 11) * 8;
      fireB.intensity = 22 + Math.sin(time * 9 + 2) * 7;
      for (const u of updaters) u(time);
    },
  };
}
