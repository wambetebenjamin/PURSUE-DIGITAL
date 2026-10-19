/**
 * EgyptianZone.js — PHASE 3: "The Egyptian Servant in the Field"
 *
 * An open arid plain with a single seated NPC. After the dialogue the world's
 * directional arrow (see entities/DirectionalArrow.js) points toward the
 * canyon mouth at the north edge, which leads into the Amalekite camp.
 */
import * as THREE from 'three';
import { QUALITY } from '../../core/quality.js';
import { Mat, Geo } from '../../utils/materials.js';
import {
  createRockField, createDryBush, createPalm, createBeacon, createRock,
  createOliveTree, createGrassField, createWildflowers, createMountainRidge,
  createBirdFlock, createButterflies, createGrazingSheep, createHumanoid, createJar,
} from '../props/PropFactory.js';
import { createServant } from '../../entities/EgyptianServant.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';
import { applyTerrainColors } from '../../utils/terrain.js';

export const EGYPT = {
  spawn: { x: -2, z: 30, yaw: 0 },
  servant: { x: 8, z: -6 },
  exit: { x: 0, z: -38 },
  bounds: { minX: -50, maxX: 50, minZ: -46, maxZ: 42 },
};

export function egyptHeight(x, z) {
  return Math.sin(x * 0.05) * 0.9 + Math.cos(z * 0.045) * 1.1 + Math.sin((x - z) * 0.02) * 1.2;
}

export function createEgyptianZone() {
  const group = new THREE.Group();
  group.name = 'ZONE_EGYPTIAN';
  const colliders = [];
  const updaters = [];

  // ------------------------------------------------------------- sky
  const sky = new SkyDome(0x2f7ae8, 0xffb86a, 0.8, 0x64a0f0); // Little Havana gold hour
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0x8fc0f0, 0xc9a86c, 0.9);
  group.add(hemi);

  const sun = new THREE.DirectionalLight(0xffd89a, 1.4);
  sun.position.set(-34, 62, 30);
  sun.castShadow = true;
  sun.shadow.mapSize.set(QUALITY.shadowMap, QUALITY.shadowMap);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 170;
  sun.shadow.camera.left = -40;
  sun.shadow.camera.right = 40;
  sun.shadow.camera.top = 40;
  sun.shadow.camera.bottom = -40;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(-32, 60, 28);
  group.add(sun, sun.target);
  sky.setSun(sun.userData.offset, 0xffe0a0, 0.75);

  // --------------------------------------------------------- terrain
  const groundGeo = new THREE.PlaneGeometry(120, 110, 46, 42);
  groundGeo.rotateX(-Math.PI / 2);
  const pos = groundGeo.attributes.position;
  for (let i = 0; i < pos.count; i++) {
    const x = pos.getX(i);
    const z = pos.getZ(i);
    pos.setY(i, egyptHeight(x, z));
  }
  groundGeo.computeVertexNormals();
  applyTerrainColors(groundGeo, {
    colorA: new THREE.Color(0xffe9b0), colorB: new THREE.Color(0xd8c070),
    colorC: new THREE.Color(0x88b060), scale: 0.05, strength: 0.55, seed: 19,
  });
  const groundMat = Mat.sandLight().clone();
  groundMat.vertexColors = true;
  const ground = new THREE.Mesh(groundGeo, groundMat);
  ground.receiveShadow = true;
  group.add(ground);

  // ------------------------------------------------------- scatter
  const rocks = createRockField(30, { minX: -48, maxX: 48, minZ: -44, maxZ: 40 }, 31);
  group.add(rocks);

  for (let i = 0; i < 7; i++) {
    const x = (seededRandom(i * 3.9) - 0.5) * 76;
    const z = (seededRandom(i * 8.1) - 0.5) * 70;
    if (Math.abs(x - EGYPT.servant.x) < 8 && Math.abs(z - EGYPT.servant.z) < 8) continue;
    const rock = createRock(300 + i);
    rock.position.set(x, egyptHeight(x, z), z);
    group.add(rock);
    colliders.push({ x, z, r: 1.0 });
  }

  for (let i = 0; i < 12; i++) {
    const x = (seededRandom(i * 6.3) - 0.5) * 84;
    const z = (seededRandom(i * 2.7) - 0.5) * 72;
    const bush = createDryBush(50 + i);
    bush.position.set(x, egyptHeight(x, z), z);
    group.add(bush);
  }

  [[-26, 18], [30, 10], [-34, -18], [24, -26]].forEach(([x, z], i) => {
    const palm = createPalm(80 + i);
    palm.position.set(x, egyptHeight(x, z), z);
    group.add(palm);
    colliders.push({ x, z, r: 0.5 });
  });

  // palm avenue down the field toward the canyon mouth
  const avenueCount = Math.max(6, Math.round(12 * QUALITY.density));
  for (let i = 0; i < avenueCount; i++) {
    const side = i % 2 === 0 ? -1 : 1;
    const x = side * (30 + (i % 3) * 3);
    const z = 34 - Math.floor(i / 2) * 16;
    if (z < -34) break;
    const palm = createPalm(220 + i);
    palm.position.set(x, egyptHeight(x, z), z);
    palm.scale.setScalar(1.2 + (i % 3) * 0.22);
    palm.rotation.y = i * 1.1;
    group.add(palm);
    colliders.push({ x, z, r: 0.5 });
  }

  // canyon mouth walls near the exit
  [[-10, -42], [11, -42], [-16, -36], [17, -36]].forEach(([x, z], i) => {
    const wall = createRock(400 + i, true);
    wall.scale.multiplyScalar(2.6);
    wall.position.set(x, egyptHeight(x, z), z);
    group.add(wall);
    colliders.push({ x, z, r: 2.4 });
  });

  // ---------------------------------------------------- the servant
  const servant = createServant();
  servant.group.position.set(EGYPT.servant.x, egyptHeight(EGYPT.servant.x, EGYPT.servant.z), EGYPT.servant.z);
  servant.group.rotation.y = Math.PI * 0.62; // angled toward the player spawn path
  group.add(servant.group);
  colliders.push({ x: EGYPT.servant.x, z: EGYPT.servant.z, r: 0.9 });
  updaters.push((t) => servant.update(t));

  // --------------------------------------------- the fields (grove)
  // "find the Egyptian servant in the field" — olive trees and wild growth
  const groveSpots = [[-18, 22], [22, 16], [-28, -6], [18, -12], [-12, -24], [32, -22], [-34, 12]];
  groveSpots.forEach(([x, z], i) => {
    const tree = createOliveTree(i + 1);
    tree.position.set(x, egyptHeight(x, z), z);
    group.add(tree);
    colliders.push({ x, z, r: 1.1 });
  });

  const grass = createGrassField(5200, { minX: -44, maxX: 44, minZ: -40, maxZ: 38 }, 13, egyptHeight,
    { color: 0x95a05a, height: 0.7, dryness: 0.45 });
  const farField = createGrassField(3400, { minX: -120, maxX: 120, minZ: -140, maxZ: -50 }, 14, egyptHeight,
    { color: 0xa8a862, height: 0.9, dryness: 0.5 });
  group.add(grass, farField);
  updaters.push((t) => { grass.userData.update(t); farField.userData.update(t); });

  const flowers = createWildflowers(420, { minX: -38, maxX: 38, minZ: -34, maxZ: 32 }, 17, egyptHeight);
  group.add(flowers);

  // grazing sheep + butterflies over the fields
  [[-12, 24], [16, 20], [-22, 2], [26, -4], [4, -20], [-30, -14]].forEach(([x, z], i) => {
    const sheep = createGrazingSheep(i + 1);
    sheep.position.set(x, egyptHeight(x, z), z);
    group.add(sheep);
    updaters.push((t) => sheep.userData.update(t));
  });
  const flies = createButterflies(18, { minX: -36, maxX: 36, minZ: -30, maxZ: 30 }, egyptHeight, 21);
  group.add(flies);
  updaters.push((t) => flies.userData.update(t));

  // villagers at work: a woman at the hand-mill, a girl carrying a water jar
  const workerPalette = {
    skin: Mat.skin(),
    cloth: Mat.tunicServant(),
    cloth2: Mat.tunicEarth(),
    hair: Mat.hair(),
  };
  const miller = createHumanoid(workerPalette, 'grinding', { detail: 'full', beard: 'none', hairStyle: 'long' });
  const mx = -4; const mz = 12;
  miller.position.set(mx, egyptHeight(mx, mz), mz);
  miller.rotation.y = 2.2;
  group.add(miller);
  const millBase = new THREE.Mesh(Geo.cyl(), Mat.rockDark());
  millBase.scale.set(1.15, 0.22, 1.15);
  millBase.position.set(mx, egyptHeight(mx, mz) + 0.11, mz);
  const millStone = new THREE.Mesh(Geo.cyl(), Mat.rock());
  millStone.scale.set(0.75, 0.16, 0.75);
  millStone.position.set(mx, egyptHeight(mx, mz) + 0.32, mz);
  group.add(millBase, millStone);
  updaters.push((t) => {
    const p = miller.userData.parts;
    p.armL.rotation.x = -1.25 + Math.sin(t * 2.1) * 0.32;
    p.armR.rotation.x = -1.05 + Math.sin(t * 2.1 + 0.8) * 0.28;
    p.torso.rotation.z = Math.sin(t * 2.1) * 0.05;
  });

  const carrier = createHumanoid(workerPalette, 'carryingJar', { detail: 'full', beard: 'none', hairStyle: 'long' });
  const cx0 = 10; const cz0 = 16;
  carrier.position.set(cx0, egyptHeight(cx0, cz0), cz0);
  carrier.rotation.y = -0.6;
  const jar = createJar(5);
  jar.scale.setScalar(0.55);
  jar.position.set(0, 1.92, 0);
  carrier.add(jar);
  group.add(carrier);
  updaters.push((t) => {
    carrier.position.y = egyptHeight(cx0, cz0) + Math.abs(Math.sin(t * 1.6)) * 0.035;
    carrier.rotation.z = Math.sin(t * 1.6) * 0.025;
  });

  // horizon ridges + birds
  const ridge = createMountainRidge(17, { width: 520, height: 60, depth: 30, color: 0xb8a074 });
  ridge.position.set(0, -8, -170);
  group.add(ridge);
  const birds = createBirdFlock(8, new THREE.Vector3(0, 0, -30), 42, 30, 27);
  group.add(birds);
  updaters.push((t) => birds.userData.update(t));

  // ------------------------------------------------- camp road beacon
  const beacon = createBeacon('Amalekite Camp', 0xff8c4a);
  beacon.position.set(EGYPT.exit.x, egyptHeight(EGYPT.exit.x, EGYPT.exit.z), EGYPT.exit.z);
  const label = makeTextSprite('TO THE CAMP', { color: '#ffd9b8', bg: 'rgba(48,20,8,0.5)' });
  label.position.set(0, 6.4, 0);
  beacon.add(label);
  group.add(beacon);
  updaters.push((t) => beacon.userData.update(t));

  // ------------------------------------------------------- particles
  const dust = new ParticleEmitter({
    count: 80,
    origin: new THREE.Vector3(0, 2.5, 0),
    spread: 42,
    rise: 0.3,
    riseJitter: 0.8,
    drift: 1,
    life: 7,
    lifeJitter: 0.4,
    size: 6,
    sizeGrow: 2.5,
    alpha: 0.14,
    rate: 0,
    color: 0xecd9ae,
    colorEnd: 0xd9be86,
    additive: true,
  });
  group.add(dust.points);

  return {
    name: 'STATE_2_EGYPTIAN_SERVANT',
    group,
    spawn: EGYPT.spawn,
    bounds: EGYPT.bounds,
    colliders,
    fog: { color: 0xffc98e, density: 0.0065 },
    sun,
    sky,
    groundHeight: egyptHeight,
    servant,
    update(dt, time) {
      dust.update(dt);
      for (const u of updaters) u(time);
    },
  };
}
