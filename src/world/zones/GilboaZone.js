/**
 * GilboaZone.js — PHASE 7: "The Fall of Saul on Mount Gilboa"
 * (1 Samuel 31:1–6)
 *
 * The tragic northern front, simultaneous with David's victory: a steep,
 * unforgiving ridge under a heavy overcast at 7000K. Saul is isolated
 * against a jagged outcrop beside his broken chariot wheel; below him the
 * shattered shield-wall flees; up the slope press the dark ranks of the
 * Philistine vanguard. Low mist, wind-whipped drizzle, and dark pools of
 * blood soak into the volcanic stone.
 *
 * This phase is a cinematic epilogue — the player walks the ridge to the
 * dying king and closes the story.
 */
import * as THREE from 'three';
import { QUALITY } from '../../core/quality.js';
import { Mat, Geo } from '../../utils/materials.js';
import {
  createRockField, createRock, createChariotWreck, createShieldDebris,
  createHumanoid, createCrown, createBeacon, createMountainRidge,
} from '../props/PropFactory.js';
import {
  createCurtainWall, createPalisade, createSiegeLadder, createRubbleHeap, createBrazier,
} from '../props/MedievalKit.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { ATMOSPHERE } from '../atmospheres.js';
import { createLightingRig, attachRig } from '../effects/LightingRig.js';
import { createHorizon } from '../effects/Horizon.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';
import { applyTerrainColors } from '../../utils/terrain.js';

export const GILBOA = {
  spawn: { x: 0, z: 30, yaw: 0 },
  saul: { x: -1.4, z: 4.6 },
  interactRadius: 5.5,
  bounds: { minX: -46, maxX: 46, minZ: -52, maxZ: 38 },
};

export function gilboaHeight(x, z) {
  // steep ridge: high in the south (player), dropping away north toward the valley
  return (
    9 + z * 0.24 +
    Math.sin(x * 0.07) * 1.3 +
    Math.cos(z * 0.05) * 1.5 +
    Math.sin((x - z) * 0.028) * 1.9
  );
}

export function createGilboaZone() {
  const group = new THREE.Group();
  group.name = 'ZONE_GILBOA';
  const colliders = [];
  const updaters = [];

  // ------------------------------------------------------------- sky
  // overcast on the ridge of Gilboa — the storm not yet broken
  const atmo = ATMOSPHERE.gilboa;
  const rig = createLightingRig(atmo, { shadowExtent: 44, shadowFar: 200 });
  attachRig(group, rig);
  const { sky, hemi, sun } = rig;
  // layered ranges dissolve into this phase's haze — no fog wall at the edge
  group.add(createHorizon(atmo));

  // --------------------------------------------------------- terrain
  const groundGeo = new THREE.PlaneGeometry(120, 130, 48, 52);
  groundGeo.rotateX(-Math.PI / 2);
  const pos = groundGeo.attributes.position;
  for (let i = 0; i < pos.count; i++) {
    pos.setY(i, gilboaHeight(pos.getX(i), pos.getZ(i)));
  }
  groundGeo.computeVertexNormals();
  applyTerrainColors(groundGeo, {
    colorA: new THREE.Color(0x8d8c86), colorB: new THREE.Color(0x5f6058),
    colorC: new THREE.Color(0x3a3c38), scale: 0.05, strength: 0.5, seed: 37,
  });
  const groundMat = Mat.basalt().clone();
  groundMat.vertexColors = true;
  const ground = new THREE.Mesh(groundGeo, groundMat);
  ground.receiveShadow = true;
  group.add(ground);

  // storm ridges enclosing the valley
  const far1 = createMountainRidge(61, { width: 520, height: 74, depth: 36, color: 0x4a525c });
  far1.position.set(0, -14, -180);
  const far2 = createMountainRidge(67, { width: 420, height: 52, depth: 30, color: 0x565e68 });
  far2.position.set(-140, -8, -120);
  far2.rotation.y = 0.55;
  const far3 = createMountainRidge(71, { width: 420, height: 56, depth: 30, color: 0x565e68 });
  far3.position.set(150, -8, -110);
  far3.rotation.y = -0.55;
  group.add(far1, far2, far3);

  // sharp volcanic outcrops — the ridge line
  const rim = createRockField(34, { minX: -70, maxX: 70, minZ: -72, maxZ: 50 }, 81);
  rim.material = Mat.basalt();
  group.add(rim);

  for (let i = 0; i < 10; i++) {
    const x = (seededRandom(i * 2.6) - 0.5) * 70;
    const z = 32 - i * 8 + (seededRandom(i * 6.4) - 0.5) * 6;
    const rock = createRock(900 + i, true);
    rock.scale.multiplyScalar(1.6);
    rock.position.set(x, gilboaHeight(x, z), z);
    group.add(rock);
    colliders.push({ x, z, r: 1.7 });
  }

  // dark blood pools soaking into the stone (zero-roughness fluid spec)
  for (let i = 0; i < 7; i++) {
    const x = (seededRandom(i * 8.7) - 0.5) * 40;
    const z = -2 + (seededRandom(i * 3.3) - 0.5) * 34;
    const pool = new THREE.Mesh(Geo.plane(), Mat.spill());
    pool.rotation.x = -Math.PI / 2;
    pool.position.set(x, gilboaHeight(x, z) + 0.05, z);
    pool.scale.set(1.6 + seededRandom(i * 5.5) * 2.2, 1.2 + seededRandom(i * 9.9) * 1.6, 1);
    group.add(pool);
  }

  // ------------------------------------------- Saul + the broken wheel
  // ---------------------------------------- the rampart on the ridge
  // Gilboa has been fought over for a generation: a stone rampart, a palisade
  // thrown up in front of it, ladders still against the wall, and the rubble
  // of the last assault.
  const rampart = createCurtainWall({ length: 34, height: 5.6, thickness: 1.8, ruined: true, seed: 71, soot: false });
  rampart.position.set(0, gilboaHeight(0, -34), -34);
  group.add(rampart);
  for (let t = -1; t <= 1; t++) colliders.push({ x: t * 13, z: -34, r: 3.2 });

  const palisade = createPalisade({ length: 26, height: 2.6, seed: 73 });
  palisade.position.set(0, gilboaHeight(0, -27), -27);
  group.add(palisade);
  for (let t = -1; t <= 1; t++) colliders.push({ x: t * 10, z: -27, r: 2.2 });

  [[-9, -30, 0.3], [11, -29, -0.25]].forEach(([x, z, ry], i) => {
    const ladder = createSiegeLadder({ height: 7.2 });
    ladder.position.set(x, gilboaHeight(x, z), z);
    ladder.rotation.y = ry;
    group.add(ladder);
  });
  [[-6, -24], [8, -25], [2, -31]].forEach(([x, z], i) => {
    const heap = createRubbleHeap({ radius: 1.6 + seededRandom(i * 2.1) * 1.1, count: 9, seed: 80 + i });
    heap.position.set(x, gilboaHeight(x, z), z);
    group.add(heap);
  });

  // one brazier still lit on the rampart: somebody is still up there
  const watchFire = createBrazier({ withLight: true, height: 1.7 });
  watchFire.position.set(4, gilboaHeight(4, -33), -33);
  group.add(watchFire);
  updaters.push((t) => watchFire.userData.update?.(t));

  const wreck = createChariotWreck(2);
  wreck.position.set(2.4, gilboaHeight(2.4, 4.2), 4.2);
  wreck.rotation.y = -0.7;
  group.add(wreck);
  colliders.push({ x: 2.4, z: 4.2, r: 2.2 });

  const saulPalette = {
    skin: Mat.skin(),
    cloth: Mat.royalBronze(),
    cloth2: Mat.elderPurple(), // the royal purple beneath the dented scales
    hair: Mat.hair(),
  };
  const saul = createHumanoid(saulPalette, 'slumpedWounded',
    { armor: 'bronze', beard: 'full', hairStyle: 'long', detail: 'full' });
  saul.position.set(GILBOA.saul.x, gilboaHeight(GILBOA.saul.x, GILBOA.saul.z), GILBOA.saul.z);
  saul.rotation.y = 0.6; // facing down the slope at the advancing enemy
  const crown = createCrown();
  crown.position.set(0, 2.08, 0.02);
  crown.rotation.x = 0.5; // heavy on the bowed head
  saul.add(crown);
  // his own sword, gripped low
  const sword = new THREE.Mesh(Geo.box(), Mat.steelBloody());
  sword.scale.set(0.12, 1.6, 0.05);
  sword.position.set(0.55, 0.75, 0.35);
  sword.rotation.x = -0.9;
  saul.add(sword);
  group.add(saul);
  colliders.push({ x: GILBOA.saul.x, z: GILBOA.saul.z, r: 0.9 });
  updaters.push((t) => {
    // shallow, erratic breathing
    saul.userData.parts.torso.position.y = 1.14 + Math.sin(t * 1.7) * 0.035 + Math.sin(t * 4.3) * 0.012;
    saul.rotation.z = Math.sin(t * 0.6) * 0.02;
  });

  // jagged outcrop behind the king
  const outcrop = createRock(950, true);
  outcrop.scale.multiplyScalar(2.8);
  outcrop.position.set(-4.5, gilboaHeight(-4.5, 6.5), 6.5);
  group.add(outcrop);
  colliders.push({ x: -4.5, z: 6.5, r: 2.6 });

  // ------------------------------ the shattered shield-wall (midground)
  for (let i = 0; i < 4; i++) {
    const x = -14 + i * 9;
    const z = -8 - (i % 2) * 7;
    const debris = createShieldDebris(i + 1);
    debris.position.set(x, gilboaHeight(x, z), z);
    debris.rotation.y = seededRandom(i * 4.4) * Math.PI;
    group.add(debris);
  }

  // fleeing Israelite remnants (running north, away)
  const israelitePalette = {
    skin: Mat.skin(),
    cloth: Mat.tunicBattleWorn(),
    cloth2: Mat.tunicIndigo(),
    hair: Mat.hair(),
  };
  for (let i = 0; i < 3; i++) {
    const x = -10 + i * 11;
    const z = -12 - i * 5;
    const man = createHumanoid(israelitePalette, 'grieving',
      { armor: 'leather', beard: 'full', detail: 'low' });
    man.position.set(x, gilboaHeight(x, z), z);
    man.rotation.y = Math.PI + (seededRandom(i * 7.1) - 0.5); // fleeing down the slope
    group.add(man);
    updaters.push((t) => {
      man.rotation.x = 0.12 + Math.sin(t * 3.1 + i * 1.2) * 0.06;
    });
  }

  // ------------------------- Philistine vanguard pressing up the ridge
  const philistinePalette = {
    skin: Mat.skinDark(),
    cloth: Mat.ironDark(),
    cloth2: Mat.basalt(),
    hair: Mat.hair(),
  };
  for (let i = 0; i < 9; i++) {
    const row = Math.floor(i / 3);
    const col = i % 3;
    const x = -9 + col * 9 + (seededRandom(i * 3.7) - 0.5) * 2;
    const z = -26 - row * 7 + (seededRandom(i * 8.2) - 0.5) * 2;
    const soldier = createHumanoid(philistinePalette, 'standing',
      { armor: 'iron', hairStyle: 'covered', detail: 'low' });
    soldier.position.set(x, gilboaHeight(x, z), z);
    soldier.rotation.y = (seededRandom(i * 2.4) - 0.5) * 0.3; // facing up-slope
    // dark iron spear
    const spear = new THREE.Mesh(Geo.cyl(), Mat.ironDark());
    spear.scale.set(0.06, 3.4, 0.06);
    spear.position.set(0.55, 1.6, 0.1);
    soldier.add(spear);
    group.add(soldier);
    colliders.push({ x, z, r: 0.7 });
    updaters.push((t) => {
      soldier.rotation.x = Math.sin(t * 1.4 + i * 0.9) * 0.03; // rhythmic advance
    });
  }

  // -------------------------------------------------------- the beacon
  const beacon = createBeacon('King Saul', 0xff5c4a);
  beacon.position.set(GILBOA.saul.x, gilboaHeight(GILBOA.saul.x, GILBOA.saul.z), GILBOA.saul.z);
  const label = makeTextSprite('KING SAUL', { color: '#ffd2c8', bg: 'rgba(40,10,8,0.55)' });
  label.position.set(0, 6.2, 0);
  beacon.add(label);
  group.add(beacon);
  updaters.push((t) => beacon.userData.update(t));

  // ------------------------------------------- mist + wind-whipped rain
  const mist = new ParticleEmitter({
    count: 110,
    origin: new THREE.Vector3(0, 8, -6),
    spread: 42,
    rise: 0.4,
    riseJitter: 0.9,
    drift: 1.6,
    life: 11,
    lifeJitter: 0.4,
    size: 22,
    sizeGrow: 16,
    alpha: 0.14,
    rate: 0,
    color: 0x9aa4b0,
    colorEnd: 0x6b7480,
  });
  const drizzle = new ParticleEmitter({
    count: 130,
    origin: new THREE.Vector3(0, 16, -4),
    spread: 38,
    rise: -16, // falling
    riseJitter: 0.15,
    drift: 3.2, // wind-whipped sideways
    life: 1.6,
    lifeJitter: 0.3,
    size: 3,
    sizeGrow: 0,
    alpha: 0.32,
    rate: 0,
    color: 0xaab6c2,
    colorEnd: 0x8a94a0,
    additive: true,
  });
  group.add(mist.points, drizzle.points);

  return {
    name: 'STATE_6_GILBOA_EPILOGUE',
    group,
    spawn: GILBOA.spawn,
    bounds: GILBOA.bounds,
    colliders,
    fog: { color: atmo.fog.color, density: atmo.fog.density },
    atmosphere: atmo,
    sun,
    sky,
    groundHeight: gilboaHeight,
    saul: GILBOA.saul,
    interactRadius: GILBOA.interactRadius,
    update(dt, time) {
      mist.update(dt);
      drizzle.update(dt);
      for (const u of updaters) u(time);
    },
  };
}
