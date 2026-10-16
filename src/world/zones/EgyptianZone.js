/**
 * EgyptianZone.js — PHASE 3: "The Egyptian Servant in the Field"
 *
 * An open arid plain with a single seated NPC. After the dialogue the world's
 * directional arrow (see entities/DirectionalArrow.js) points toward the
 * canyon mouth at the north edge, which leads into the Amalekite camp.
 */
import * as THREE from 'three';
import { Mat } from '../../utils/materials.js';
import {
  createRockField, createDryBush, createPalm, createBeacon, createRock,
  createOliveTree, createGrassField, createWildflowers,
} from '../props/PropFactory.js';
import { createServant } from '../../entities/EgyptianServant.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';

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
  const sky = new SkyDome(0x3a7fd0, 0xffc27a, 0.8);
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0x8fc0f0, 0xc9a86c, 0.9);
  group.add(hemi);

  const sun = new THREE.DirectionalLight(0xffd89a, 1.4);
  sun.position.set(-34, 62, 30);
  sun.castShadow = true;
  sun.shadow.mapSize.set(1024, 1024);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 170;
  sun.shadow.camera.left = -40;
  sun.shadow.camera.right = 40;
  sun.shadow.camera.top = 40;
  sun.shadow.camera.bottom = -40;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(-32, 60, 28);
  group.add(sun, sun.target);
  sky.setSun(sun.userData.offset, 0xffd089, 0.5);

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
  const ground = new THREE.Mesh(groundGeo, Mat.sandLight());
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

  const grass = createGrassField(2600, { minX: -44, maxX: 44, minZ: -40, maxZ: 38 }, 13, egyptHeight,
    { color: 0x95a05a, height: 0.7, dryness: 0.45 });
  group.add(grass);
  updaters.push((t) => grass.userData.update(t));

  const flowers = createWildflowers(240, { minX: -38, maxX: 38, minZ: -34, maxZ: 32 }, 17, egyptHeight);
  group.add(flowers);

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
    fog: { color: 0xd9c49a, density: 0.006 },
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
