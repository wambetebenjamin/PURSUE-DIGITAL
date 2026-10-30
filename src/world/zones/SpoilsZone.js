/**
 * SpoilsZone.js — PHASE 5: "The Spoils and Separation at Besor"
 * (1 Samuel 30:21–25)
 *
 * Symmetric staging at the ravine mouth: on the left the 400 vanguard,
 * blood-stained and closed off; on the right the 200 guards beside the
 * baggage train; between them a surging herd of recovered livestock driven
 * by freed captives. David's stone outcrop holds the center — the dividing
 * line where the statute of equal share is given.
 *
 * Lighting: clear, direct late-afternoon sun at 5200K, strong backlighting
 * through the herd dust into a hazy golden veil between the factions.
 */
import * as THREE from 'three';
import { QUALITY } from '../../core/quality.js';
import { Mat } from '../../utils/materials.js';
import {
  createRockField, createRock, createHerd, createBaggageWagon,
  createOutcrop, createBeacon, createHumanoid, createDryBush,
  createGrassField, createOliveTree, createWildflowers, createPalm,
  createMountainRidge, createBirdFlock, createButterflies, createGrazingSheep,
  createJar,
} from '../props/PropFactory.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';
import { applyTerrainColors } from '../../utils/terrain.js';

export const SPOILS = {
  spawn: { x: 0, z: 34, yaw: 0 },
  outcrop: { x: 0, z: -6 },
  interactRadius: 6.2,
  bounds: { minX: -54, maxX: 54, minZ: -50, maxZ: 44 },
};

export function spoilsHeight(x, z) {
  return (
    Math.sin(x * 0.05) * 0.8 +
    Math.cos(z * 0.045) * 1.0 +
    Math.sin((x + z) * 0.02) * 1.1
  );
}

export function createSpoilsZone() {
  const group = new THREE.Group();
  group.name = 'ZONE_SPOILS';
  const colliders = [];
  const updaters = [];

  // ------------------------------------------------------------- sky
  // 5200K clear late afternoon — bright, direct, revealing
  const sky = new SkyDome(0x4a2aa0, 0xff8a5c, 0.82, 0xb85aa8); // ocean sunset
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0xbfd4e8, 0xd9be86, 1.1);
  group.add(hemi);

  const sun = new THREE.DirectionalLight(0xffe2a8, 1.6);
  sun.position.set(30, 52, -38); // low-north-west: backlights the herd dust
  sun.castShadow = true;
  sun.shadow.mapSize.set(QUALITY.shadowMap, QUALITY.shadowMap);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 180;
  sun.shadow.camera.left = -46;
  sun.shadow.camera.right = 46;
  sun.shadow.camera.top = 46;
  sun.shadow.camera.bottom = -46;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(28, 50, -36);
  group.add(sun, sun.target);
  sky.setSun(sun.userData.offset, 0xffd08a, 0.8);

  // --------------------------------------------------------- terrain
  const groundGeo = new THREE.PlaneGeometry(130, 120, 48, 44);
  groundGeo.rotateX(-Math.PI / 2);
  const pos = groundGeo.attributes.position;
  for (let i = 0; i < pos.count; i++) {
    pos.setY(i, spoilsHeight(pos.getX(i), pos.getZ(i)));
  }
  groundGeo.computeVertexNormals();
  applyTerrainColors(groundGeo, {
    colorA: new THREE.Color(0xffc9a0), colorB: new THREE.Color(0xe09a70),
    colorC: new THREE.Color(0xa86858), scale: 0.055, strength: 0.6, seed: 23,
  });
  const groundMat = Mat.sandLight().clone();
  groundMat.vertexColors = true;
  const ground = new THREE.Mesh(groundGeo, groundMat);
  ground.receiveShadow = true;
  group.add(ground);

  // dry white river mud crust along the old waterline
  const mud = new THREE.Mesh(new THREE.PlaneGeometry(120, 9), Mat.riverMud());
  mud.rotation.x = -Math.PI / 2;
  mud.position.set(0, 0.06, -30);
  mud.receiveShadow = true;
  group.add(mud);

  // ravine mouth walls (limestone, "dry, porous, zero specularity")
  const wallL = createRockField(22, { minX: -80, maxX: -46, minZ: -70, maxZ: -18 }, 61);
  const wallR = createRockField(22, { minX: 46, maxX: 80, minZ: -70, maxZ: -18 }, 62);
  wallL.material = Mat.limestone();
  wallR.material = Mat.limestone();
  group.add(wallL, wallR);

  for (let i = 0; i < 8; i++) {
    const x = (seededRandom(i * 3.7) - 0.5) * 90;
    const z = -36 - seededRandom(i * 8.3) * 10;
    const rock = createRock(500 + i, true);
    rock.scale.multiplyScalar(1.9);
    rock.position.set(x, spoilsHeight(x, z), z);
    group.add(rock);
    colliders.push({ x, z, r: 2.0 });
  }

  // ------------------------------------------------------- the herd
  const herd = createHerd(64, { minX: -26, maxX: 26, minZ: -24, maxZ: 10 }, 7, spoilsHeight);
  group.add(herd);

  // freed captives driving the herd (small, weary, alive)
  const captivePalette = {
    skin: Mat.skin(),
    cloth: Mat.tunicCaptive(),
    cloth2: Mat.tunicServant(),
    hair: Mat.hair(),
  };
  for (let i = 0; i < 5; i++) {
    const x = -18 + i * 9;
    const z = 12 + (i % 2) * 4;
    const kid = createHumanoid(captivePalette, 'standing', { detail: 'low', beard: 'none' });
    kid.position.set(x, spoilsHeight(x, z), z);
    kid.scale.setScalar(0.72);
    kid.rotation.y = Math.PI + (seededRandom(i * 4.2) - 0.5);
    group.add(kid);
    updaters.push((t) => {
      kid.rotation.z = Math.sin(t * 1.1 + i * 1.7) * 0.04;
    });
  }

  // ------------------------------------------- the 400 (left faction)
  const vanguardPalette = {
    skin: Mat.skinDark(),
    cloth: Mat.tunicIndigo(),
    cloth2: Mat.tunicBattleWorn(),
    hair: Mat.hair(),
  };
  for (let i = 0; i < 8; i++) {
    const col = Math.floor(i / 2);
    const row = i % 2;
    const x = -22 - col * 3.2;
    const z = -2 + row * 4.5 + (seededRandom(i * 2.9) - 0.5);
    const man = createHumanoid(vanguardPalette, i % 3 === 0 ? 'armsCrossed' : 'standing',
      { armor: 'leather', beard: i % 2 === 0 ? 'full' : 'gray', detail: 'low' });
    man.position.set(x, spoilsHeight(x, z), z);
    man.rotation.y = Math.PI / 2; // facing the herd / the 200
    group.add(man);
    colliders.push({ x, z, r: 0.7 });
    updaters.push((t) => {
      man.rotation.z = Math.sin(t * 0.9 + i * 2.1) * 0.025;
    });
  }

  // ------------------------------------------- the 200 (right faction)
  const guardPalette = {
    skin: Mat.skin(),
    cloth: Mat.tunicServant(),
    cloth2: Mat.tunicEarth(),
    hair: Mat.hair(),
  };
  for (let i = 0; i < 8; i++) {
    const col = Math.floor(i / 2);
    const row = i % 2;
    const x = 22 + col * 3.2;
    const z = -2 + row * 4.5 + (seededRandom(i * 6.7) - 0.5);
    const man = createHumanoid(guardPalette, 'grieving',
      { beard: 'full', hairStyle: 'long', detail: 'low' });
    man.position.set(x, spoilsHeight(x, z), z);
    man.rotation.y = -Math.PI / 2;
    group.add(man);
    colliders.push({ x, z, r: 0.7 });
    updaters.push((t) => {
      man.rotation.z = Math.sin(t * 0.8 + i * 1.4) * 0.03;
    });
  }

  // ------------------------------------------- the baggage train
  [[26, 16], [33, 12], [30, 23]].forEach(([x, z], i) => {
    const wagon = createBaggageWagon(i + 1);
    wagon.position.set(x, spoilsHeight(x, z), z);
    wagon.rotation.y = -0.5 + i * 0.35;
    group.add(wagon);
    colliders.push({ x, z, r: 2.6 });
  });

  // ------------------------------------------- the outcrop (center)
  const outcrop = createOutcrop(3);
  outcrop.position.set(SPOILS.outcrop.x, spoilsHeight(SPOILS.outcrop.x, SPOILS.outcrop.z), SPOILS.outcrop.z);
  group.add(outcrop);
  colliders.push({ x: SPOILS.outcrop.x, z: SPOILS.outcrop.z, r: 2.2 });

  const beacon = createBeacon('The Dividing Line', 0xffd25e);
  beacon.position.set(SPOILS.outcrop.x, spoilsHeight(SPOILS.outcrop.x, SPOILS.outcrop.z) + 2.2, SPOILS.outcrop.z);
  const label = makeTextSprite('THE DIVIDING LINE', { color: '#ffe9b8', bg: 'rgba(48,32,8,0.55)' });
  label.position.set(0, 6.2, 0);
  beacon.add(label);
  group.add(beacon);
  updaters.push((t) => beacon.userData.update(t));

  // sparse scrub
  for (let i = 0; i < 10; i++) {
    const x = (seededRandom(i * 5.3) - 0.5) * 96;
    const z = (seededRandom(i * 9.1) - 0.5) * 80;
    const bush = createDryBush(700 + i);
    bush.position.set(x, spoilsHeight(x, z), z);
    group.add(bush);
  }

  // ------------------------------------- green river-meadow at the ford
  const meadow = createGrassField(4400, { minX: -46, maxX: 46, minZ: -30, maxZ: 26 }, 41,
    spoilsHeight, { color: 0x7d8f48, height: 0.6, dryness: 0.35 });
  const farMeadow = createGrassField(3200, { minX: -130, maxX: 130, minZ: -120, maxZ: -52 }, 42,
    spoilsHeight, { color: 0x95a058, height: 0.85, dryness: 0.45 });
  group.add(meadow, farMeadow);
  updaters.push((t) => { meadow.userData.update(t); farMeadow.userData.update(t); });
  const blooms = createWildflowers(340, { minX: -36, maxX: 36, minZ: -20, maxZ: 18 }, 43, spoilsHeight);
  group.add(blooms);

  // live grazers keeping the meadow honest
  [[-12, 18], [10, 20], [-20, 14], [18, 12], [0, 24], [-30, 18], [28, 16]].forEach(([x, z], i) => {
    const sheep = createGrazingSheep(i + 1);
    sheep.position.set(x, spoilsHeight(x, z), z);
    group.add(sheep);
    updaters.push((t) => sheep.userData.update(t));
  });
  const flies = createButterflies(16, { minX: -34, maxX: 34, minZ: -18, maxZ: 20 }, spoilsHeight, 31);
  group.add(flies);
  updaters.push((t) => flies.userData.update(t));

  // freed families bringing water jars across the meadow
  const jarPalette = {
    skin: Mat.skin(), cloth: Mat.tunicCaptive(), cloth2: Mat.tunicServant(), hair: Mat.hair(),
  };
  [[-14, 22, 0.4], [8, 26, -2.2]].forEach(([x, z, ry], i) => {
    const carrier = createHumanoid(jarPalette, 'carryingJar',
      { detail: 'low', beard: 'none', hairStyle: 'long' });
    carrier.position.set(x, spoilsHeight(x, z), z);
    carrier.rotation.y = ry;
    carrier.scale.setScalar(0.92);
    const jar = createJar(10 + i);
    jar.scale.setScalar(0.5);
    jar.position.set(0, 1.92, 0);
    carrier.add(jar);
    group.add(carrier);
    updaters.push((t) => {
      carrier.position.y = spoilsHeight(x, z) + Math.abs(Math.sin(t * 1.5 + i)) * 0.035;
    });
  });

  // horizon ridges + birds
  const ridge = createMountainRidge(47, { width: 560, height: 64, depth: 30, color: 0xc9a878 });
  ridge.position.set(0, -10, -165);
  group.add(ridge);
  const birds = createBirdFlock(8, new THREE.Vector3(0, 0, -20), 40, 32, 47);
  group.add(birds);
  updaters.push((t) => birds.userData.update(t));
  [[-40, 20], [38, 18], [-44, -8], [42, -12]].forEach(([x, z], i) => {
    const tree = createOliveTree(30 + i);
    tree.position.set(x, spoilsHeight(x, z), z);
    group.add(tree);
    colliders.push({ x, z, r: 1.1 });
  });
  // palms — the Miami skyline of the Negev
  [[-34, 30], [34, 28], [-46, 6], [46, 4], [0, -40]].forEach(([x, z], i) => {
    const palm = createPalm(60 + i);
    palm.position.set(x, spoilsHeight(x, z), z);
    group.add(palm);
    colliders.push({ x, z, r: 0.5 });
  });

  // twin palm rows down the valley of the dividing line
  const avenueCount = Math.max(6, Math.round(12 * QUALITY.density));
  for (let i = 0; i < avenueCount; i++) {
    const side = i % 2 === 0 ? -1 : 1;
    const x = side * (38 + (i % 3) * 3.2);
    const z = 42 - Math.floor(i / 2) * 17;
    if (z < -36) break;
    const palm = createPalm(260 + i);
    palm.position.set(x, spoilsHeight(x, z), z);
    palm.scale.setScalar(1.22 + (i % 3) * 0.2);
    palm.rotation.y = i * 1.3;
    group.add(palm);
    colliders.push({ x, z, r: 0.5 });
  }

  // ------------------------------------------- golden dust veil (FX)
  // "strong backlighting catches the dust clouds kicked up by the livestock,
  //  creating a hazy golden veil between the two disputing groups"
  const veil = new ParticleEmitter({
    count: 120,
    origin: new THREE.Vector3(0, 3.2, 0),
    spread: 30,
    rise: 0.5,
    riseJitter: 0.9,
    drift: 1.4,
    life: 9,
    lifeJitter: 0.4,
    size: 16,
    sizeGrow: 10,
    alpha: 0.16,
    rate: 0,
    color: 0xf2e0b0,
    colorEnd: 0xd9be86,
    additive: true,
  });
  const herdDust = new ParticleEmitter({
    count: 80,
    origin: new THREE.Vector3(0, 1.6, -6),
    spread: 24,
    rise: 0.9,
    riseJitter: 0.7,
    drift: 1.1,
    life: 5,
    lifeJitter: 0.45,
    size: 10,
    sizeGrow: 8,
    alpha: 0.22,
    rate: 0,
    color: 0xe8d5a8,
    colorEnd: 0xc9ae7c,
  });
  group.add(veil.points, herdDust.points);

  return {
    name: 'STATE_4_SPOILS_DIVISION',
    group,
    spawn: SPOILS.spawn,
    bounds: SPOILS.bounds,
    colliders,
    fog: { color: 0xe8906a, density: 0.006 },
    sun,
    sky,
    groundHeight: spoilsHeight,
    outcrop: SPOILS.outcrop,
    interactRadius: SPOILS.interactRadius,
    update(dt, time) {
      veil.update(dt);
      herdDust.update(dt);
      for (const u of updaters) u(time);
    },
  };
}
