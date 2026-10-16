/**
 * GateZone.js — PHASE 6: "The Diplomatic Distribution of Spoils"
 * (1 Samuel 30:26–31)
 *
 * Framed by the great limestone arch of a Judean walled city (Hebron):
 * elders in fine woven attire receive iron-bound cedar chests while a
 * train of pack mules unloads textiles, shields, and polished vessels.
 * The palette turns saturated and political — royal purples, madder reds,
 * and the hard metallic glare of gold and silver in direct light.
 *
 * Lighting: warm, clear morning sun at 4800K — long angled shadows across
 * polished flagstones.
 */
import * as THREE from 'three';
import { Mat, Geo } from '../../utils/materials.js';
import {
  createCityGate, createChest, createMule, createHumanoid,
  createBeacon, createTreasurePile, createWheatField, createGrassField,
  createOliveTree, createCypress,
} from '../props/PropFactory.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';

export const GATE = {
  spawn: { x: 0, z: 26, yaw: 0 },
  elders: { x: 0, z: -6 },
  interactRadius: 6.0,
  bounds: { minX: -44, maxX: 44, minZ: -30, maxZ: 34 },
};

export function gateHeight(x, z) {
  // courtyard flagstones: nearly flat, decades of traffic polished level
  return Math.sin(x * 0.04) * 0.25 + Math.cos(z * 0.05) * 0.22;
}

export function createGateZone() {
  const group = new THREE.Group();
  group.name = 'ZONE_GATE';
  const colliders = [];
  const updaters = [];

  // ------------------------------------------------------------- sky
  // 4800K warm morning, clear
  const sky = new SkyDome(0x4e86c2, 0xf0d9a8, 0.8);
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0xa8c4e0, 0xd9b98c, 1.0);
  group.add(hemi);

  const sun = new THREE.DirectionalLight(0xffe0b8, 1.45);
  sun.position.set(-46, 34, 30); // low-east: long angled shadows on the flagstones
  sun.castShadow = true;
  sun.shadow.mapSize.set(1024, 1024);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 170;
  sun.shadow.camera.left = -42;
  sun.shadow.camera.right = 42;
  sun.shadow.camera.top = 42;
  sun.shadow.camera.bottom = -42;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(-44, 32, 28);
  group.add(sun, sun.target);

  // --------------------------------------------------------- terrain
  const groundGeo = new THREE.PlaneGeometry(110, 90, 30, 26);
  groundGeo.rotateX(-Math.PI / 2);
  const pos = groundGeo.attributes.position;
  for (let i = 0; i < pos.count; i++) {
    pos.setY(i, gateHeight(pos.getX(i), pos.getZ(i)));
  }
  groundGeo.computeVertexNormals();
  const ground = new THREE.Mesh(groundGeo, Mat.flagstone());
  ground.receiveShadow = true;
  group.add(ground);

  // "flat, polished flagstones... chipped edges" — a few lighter slabs
  for (let i = 0; i < 14; i++) {
    const x = (seededRandom(i * 3.3) - 0.5) * 56;
    const z = (seededRandom(i * 7.7) - 0.5) * 40;
    const slab = new THREE.Mesh(Geo.box(), Mat.limestonePale());
    slab.scale.set(3.2, 0.08, 2.4);
    slab.position.set(x, gateHeight(x, z) + 0.06, z);
    slab.rotation.y = seededRandom(i * 2.2) * 0.4 - 0.2;
    slab.receiveShadow = true;
    group.add(slab);
  }

  // ------------------------------------------------- the city gateway
  const gate = createCityGate(2);
  gate.position.set(0, gateHeight(0, -14), -14);
  group.add(gate);
  colliders.push({ x: -4.6, z: -14, r: 2.4 }, { x: 4.6, z: -14, r: 2.4 });

  // terraced limestone hills of Hebron beyond the gate
  for (let t = 0; t < 4; t++) {
    const terrace = new THREE.Mesh(Geo.box(), t % 2 ? Mat.limestone() : Mat.limestonePale());
    terrace.scale.set(90 - t * 12, 2.4, 10);
    terrace.position.set(0, 1.2 + t * 2.2, -30 - t * 8);
    terrace.receiveShadow = true;
    terrace.castShadow = true;
    group.add(terrace);
  }
  // cypress silhouettes + olive trees on the terraces
  for (let i = 0; i < 7; i++) {
    const x = (seededRandom(i * 4.4) - 0.5) * 70;
    const z = -32 - seededRandom(i * 8.8) * 22;
    const t = Math.floor((-z - 30) / 8);
    const y = 2.4 + Math.max(0, Math.min(3, t)) * 2.2;
    const tree = i % 2 === 0 ? createCypress(i + 1) : createOliveTree(i + 1);
    tree.position.set(x, y, z);
    group.add(tree);
    colliders.push({ x, z, r: 1.0 });
  }

  // --------------------------------------------- fields of wheat
  // "terraced limestone hills of Hebron" under cultivation
  for (let t = 0; t < 3; t++) {
    const y = 2.4 + t * 2.2;
    const wheat = createWheatField(700 - t * 120, {
      minX: -34 + t * 5, maxX: 34 - t * 5,
      minZ: -31 - t * 8, maxZ: -23 - t * 8,
    }, 23 + t, () => y);
    group.add(wheat);
    updaters.push((tm) => wheat.userData.update(tm));
  }
  const courtyardGrass = createGrassField(900, { minX: -38, maxX: 38, minZ: -20, maxZ: 18 }, 29,
    gateHeight, { color: 0x8a9a4c, height: 0.55, dryness: 0.4 });
  group.add(courtyardGrass);
  updaters.push((tm) => courtyardGrass.userData.update(tm));

  // ------------------------------------------------- elders of Judah
  const elderPalettes = [
    { skin: Mat.skin(), cloth: Mat.elderPurple(), cloth2: Mat.elderMadder(), hair: Mat.hair() },
    { skin: Mat.skin(), cloth: Mat.elderMadder(), cloth2: Mat.elderPurple(), hair: Mat.hair() },
    { skin: Mat.skinDark(), cloth: Mat.elderPurple(), cloth2: Mat.canvasTarp(), hair: Mat.hair() },
    { skin: Mat.skin(), cloth: Mat.tunicIndigo(), cloth2: Mat.elderMadder(), hair: Mat.hair() },
    { skin: Mat.skinDark(), cloth: Mat.elderMadder(), cloth2: Mat.tunicEarth(), hair: Mat.hair() },
  ];
  elderPalettes.forEach((palette, i) => {
    const a = -0.7 + i * 0.35;
    const x = Math.sin(a) * 5.2;
    const z = GATE.elders.z - 1.2 + Math.cos(a) * 1.4;
    const elder = createHumanoid(palette, 'standing',
      { robe: true, beard: i % 2 === 0 ? 'gray' : 'full', hairStyle: i % 3 === 0 ? 'balding' : 'short', detail: 'full' });
    elder.position.set(x, gateHeight(x, z), z);
    elder.rotation.y = Math.PI - a * 0.7; // turned toward the arriving envoys
    group.add(elder);
    colliders.push({ x, z, r: 0.7 });
    updaters.push((t) => {
      elder.userData.parts.head.rotation.y = Math.sin(t * 0.35 + i * 1.3) * 0.35;
    });
  });

  // --------------------------------- tribute chests + gold & silver
  [[-2.8, -3.2, true], [2.6, -3.0, true], [0, -1.6, false], [-1.2, -0.2, true]].forEach(([x, z, open], i) => {
    const chest = createChest(i + 1, open);
    chest.position.set(x, gateHeight(x, z), z);
    group.add(chest);
    colliders.push({ x, z, r: 0.9 });
  });
  [[4.4, -1.2], [-4.2, -0.4]].forEach(([x, z], i) => {
    const pile = createTreasurePile(i + 1);
    pile.position.set(x, gateHeight(x, z), z);
    group.add(pile);
  });

  // ------------------------------------ the mule train, being unloaded
  for (let i = 0; i < 6; i++) {
    const x = -18 + i * 7.2;
    const z = 6 + (i % 2) * 5;
    const mule = createMule(i + 1);
    mule.position.set(x, gateHeight(x, z), z);
    mule.rotation.y = -Math.PI / 2 + (seededRandom(i * 3.9) - 0.5) * 0.5;
    group.add(mule);
    colliders.push({ x, z, r: 1.1 });
    updaters.push((t) => {
      mule.rotation.z = Math.sin(t * 1.2 + i * 2.2) * 0.02;
    });
  }

  // battle-weary envoys of David (dusty leather & linen against the fine robes)
  const envoyPalette = {
    skin: Mat.skinDark(),
    cloth: Mat.tunicBattleWorn(),
    cloth2: Mat.tunicIndigo(),
    hair: Mat.hair(),
  };
  for (let i = 0; i < 3; i++) {
    const x = -6 + i * 6;
    const z = 2.5 + (i % 2) * 2;
    const envoy = createHumanoid(envoyPalette, 'standing',
      { armor: 'leather', beard: 'full', detail: 'low' });
    envoy.position.set(x, gateHeight(x, z), z);
    envoy.rotation.y = Math.PI; // facing the elders, hands openly at their belts
    group.add(envoy);
    colliders.push({ x, z, r: 0.7 });
  }

  // -------------------------------------------------------- the beacon
  const beacon = createBeacon('Elders of Judah', 0xffc857);
  beacon.position.set(GATE.elders.x, gateHeight(GATE.elders.x, GATE.elders.z), GATE.elders.z);
  const label = makeTextSprite('THE ELDERS OF JUDAH', { color: '#ffe9b8', bg: 'rgba(48,28,8,0.55)' });
  label.position.set(0, 6.4, 0);
  beacon.add(label);
  group.add(beacon);
  updaters.push((t) => beacon.userData.update(t));

  // ------------------------------------------------------- atmosphere
  const motes = new ParticleEmitter({
    count: 70,
    origin: new THREE.Vector3(0, 3.5, -2),
    spread: 30,
    rise: 0.25,
    riseJitter: 0.8,
    drift: 0.7,
    life: 10,
    lifeJitter: 0.4,
    size: 5,
    sizeGrow: 2,
    alpha: 0.14,
    rate: 0,
    color: 0xf0d9a8,
    colorEnd: 0xd9b98c,
    additive: true,
  });
  group.add(motes.points);

  return {
    name: 'STATE_5_DISTRIBUTION',
    group,
    spawn: GATE.spawn,
    bounds: GATE.bounds,
    colliders,
    fog: { color: 0xd9c19a, density: 0.005 },
    sun,
    groundHeight: gateHeight,
    elders: GATE.elders,
    interactRadius: GATE.interactRadius,
    update(dt, time) {
      motes.update(dt);
      for (const u of updaters) u(time);
    },
  };
}
