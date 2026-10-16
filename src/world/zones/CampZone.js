/**
 * CampZone.js — PHASE 4: "The Camp and Rescue"
 *
 * A dusk-lit valley of tents and bonfires with celebrating Amalekites.
 * Each guard carries a ground-projected vision cone (distance + facing check).
 * Reaching the central cage triggers the victory sequence.
 */
import * as THREE from 'three';
import { Mat } from '../../utils/materials.js';
import {
  createTent, createBonfire, createCage, createHumanoid,
  createRockField, createBeacon,
} from '../props/PropFactory.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { VISION } from '../../constants.js';
import { seededRandom, randRange, wrapAngle } from '../../utils/math.js';

export const CAMP = {
  spawn: { x: 0, z: 56, yaw: 0 },
  captives: { x: 0, z: -14 },
  rescueRadius: 3.4,
  bounds: { minX: -58, maxX: 58, minZ: -62, maxZ: 64 },
};

export function campHeight(x, z) {
  return (
    Math.sin(x * 0.035) * 1.5 +
    Math.cos(z * 0.03) * 1.8 +
    Math.sin((x + z) * 0.014) * 2.2 -
    1.2
  );
}

// ------------------------------------------------------ vision cones
function createVisionCone() {
  // flat fan arc lying on the ground, opening along +Z
  const segments = 18;
  const positions = [];
  const R = VISION.range;
  for (let i = 0; i < segments; i++) {
    const a0 = -VISION.halfAngle + (2 * VISION.halfAngle * i) / segments;
    const a1 = -VISION.halfAngle + (2 * VISION.halfAngle * (i + 1)) / segments;
    positions.push(0, 0, 0);
    positions.push(Math.sin(a0) * R, 0, Math.cos(a0) * R);
    positions.push(Math.sin(a1) * R, 0, Math.cos(a1) * R);
  }
  const geo = new THREE.BufferGeometry();
  geo.setAttribute('position', new THREE.Float32BufferAttribute(positions, 3));
  const mat = Mat.alertCone().clone();
  const mesh = new THREE.Mesh(geo, mat);
  mesh.position.y = 0.12;
  mesh.renderOrder = 2;
  return mesh;
}

function createGuard(seed) {
  const group = new THREE.Group();
  const palette = {
    skin: Mat.skinDark(),
    cloth: seededRandom(seed) > 0.5 ? Mat.tunicAmalekite() : Mat.tunicAmalekiteAlt(),
    cloth2: Mat.tunicAmalekiteAlt(),
    hair: Mat.hair(),
  };
  const human = createHumanoid(palette, 'celebrating');
  group.add(human);

  const cone = createVisionCone();
  group.add(cone);

  return {
    group,
    cone,
    human,
    facing: 0,
    scan: {
      base: 0,
      amplitude: randRange(0.35, 0.85),
      speed: randRange(0.35, 0.8),
      phase: randRange(0, Math.PI * 2),
    },
  };
}

// -------------------------------------------------------- the zone
export function createCampZone() {
  const group = new THREE.Group();
  group.name = 'ZONE_CAMP';
  const colliders = [];
  const timeUpdaters = []; // fn(time)
  const dtUpdaters = []; // fn(dt)
  const guards = [];

  // ------------------------------------------------------------- sky
  const sky = new SkyDome(0x241a3a, 0xc86a3a, 0.7);
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0x53406b, 0x7a4a30, 0.95);
  group.add(hemi);

  const sun = new THREE.DirectionalLight(0xffb070, 1.05);
  sun.position.set(-30, 52, -18);
  sun.castShadow = true;
  sun.shadow.mapSize.set(1024, 1024);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 170;
  sun.shadow.camera.left = -44;
  sun.shadow.camera.right = 44;
  sun.shadow.camera.top = 44;
  sun.shadow.camera.bottom = -44;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(-28, 50, -16);
  group.add(sun, sun.target);

  // --------------------------------------------------------- terrain
  const groundGeo = new THREE.PlaneGeometry(150, 160, 52, 56);
  groundGeo.rotateX(-Math.PI / 2);
  const pos = groundGeo.attributes.position;
  for (let i = 0; i < pos.count; i++) {
    const x = pos.getX(i);
    const z = pos.getZ(i);
    pos.setY(i, campHeight(x, z));
  }
  groundGeo.computeVertexNormals();
  const groundMat = Mat.rockDark().clone();
  groundMat.color.set(0x6e5238);
  const ground = new THREE.Mesh(groundGeo, groundMat);
  ground.receiveShadow = true;
  group.add(ground);

  // valley rim rocks
  const rim = createRockField(40, { minX: -80, maxX: 80, minZ: -85, maxZ: 88 }, 55);
  group.add(rim);

  // ----------------------------------------------------------- tents
  const tentSpots = [
    [-18, 30], [16, 26], [-28, 12], [26, 8], [-12, -2], [14, -6],
    [-30, -22], [24, -26], [-14, -36], [12, -40], [-36, 40], [32, 38],
  ];
  tentSpots.forEach(([x, z], i) => {
    const tent = createTent(i + 1);
    tent.position.set(x, campHeight(x, z), z);
    tent.rotation.y = seededRandom(i * 4.4) * Math.PI * 2;
    group.add(tent);
    colliders.push({ x, z, r: 2.5 });
  });

  // ------------------------------------------------------- bonfires
  const fireSpots = [[-8, 18], [20, 18], [-22, -12], [10, -22]];
  fireSpots.forEach(([x, z], i) => {
    const fire = createBonfire(i < 3); // max 3 dynamic point lights
    fire.position.set(x, campHeight(x, z), z);
    group.add(fire);
    colliders.push({ x, z, r: 1.1 });
    timeUpdaters.push((t) => fire.userData.update(t));
  });

  // fire particles on two of them
  fireSpots.slice(0, 2).forEach(([x, z]) => {
    const sparks = new ParticleEmitter({
      count: 60,
      origin: new THREE.Vector3(x, campHeight(x, z) + 1.1, z),
      spread: 0.7,
      rise: 2.2,
      riseJitter: 0.55,
      drift: 0.6,
      life: 2.2,
      lifeJitter: 0.45,
      size: 5,
      sizeGrow: 1,
      alpha: 0.9,
      rate: 0,
      color: 0xffb246,
      colorEnd: 0xff4a14,
      additive: true,
    });
    group.add(sparks.points);
    dtUpdaters.push((dt) => sparks.update(dt));
  });

  // --------------------------------------------------------- guards
  const guardSpots = [
    { x: -6, z: 34, facing: Math.PI },        // near entrance
    { x: 10, z: 26, facing: Math.PI * 0.85 },
    { x: -16, z: 12, facing: Math.PI * 0.6 },
    { x: 18, z: 4, facing: -Math.PI * 0.55 },
    { x: -10, z: -4, facing: Math.PI * 0.4 },
    { x: 8, z: -16, facing: -Math.PI * 0.4 },
    { x: -20, z: -24, facing: Math.PI * 0.2 },
    { x: 16, z: -30, facing: -Math.PI * 0.15 },
    { x: -2, z: -40, facing: 0 },
  ];
  guardSpots.forEach((spot, i) => {
    const guard = createGuard(i + 1);
    guard.group.position.set(spot.x, campHeight(spot.x, spot.z), spot.z);
    guard.scan.base = spot.facing;
    guard.facing = spot.facing;
    guard.group.rotation.y = spot.facing;
    group.add(guard.group);
    colliders.push({ x: spot.x, z: spot.z, r: 0.7 });
    guards.push(guard);
  });

  // -------------------------------------------------- captives / cage
  const cage = createCage();
  cage.position.set(CAMP.captives.x, campHeight(CAMP.captives.x, CAMP.captives.z), CAMP.captives.z);
  group.add(cage);

  // seated captive family inside the cage
  const captivePalette = {
    skin: Mat.skin(),
    cloth: Mat.tunicCaptive(),
    cloth2: Mat.tunicServant(),
    hair: Mat.hair(),
  };
  const captivePositions = [[-1.1, 0.2], [1.0, -0.5], [0.1, 1.1], [-0.4, -1.2]];
  captivePositions.forEach(([dx, dz], i) => {
    const c = createHumanoid(captivePalette, 'seatedCaptive');
    c.position.set(
      CAMP.captives.x + dx,
      campHeight(CAMP.captives.x + dx, CAMP.captives.z + dz) - 0.05,
      CAMP.captives.z + dz,
    );
    c.rotation.y = seededRandom(i * 7.7) * Math.PI * 2;
    c.scale.setScalar(0.86);
    group.add(c);
    timeUpdaters.push((t) => {
      // faint weary sway
      c.rotation.z = Math.sin(t * 0.7 + i * 1.9) * 0.03;
    });
  });

  // soft goal marker above the cage (helps navigation without a minimap)
  const goalBeacon = createBeacon('Captives', 0x7dffb8);
  goalBeacon.position.set(CAMP.captives.x, campHeight(CAMP.captives.x, CAMP.captives.z), CAMP.captives.z);
  goalBeacon.scale.setScalar(0.75);
  const label = makeTextSprite('THE CAPTIVES', { color: '#c8ffe4', bg: 'rgba(8,32,20,0.5)' });
  label.position.set(0, 6.8, 0);
  goalBeacon.add(label);
  group.add(goalBeacon);
  timeUpdaters.push((t) => goalBeacon.userData.update(t));

  // --------------------------------------------------- campfire smoke
  const smoke = new ParticleEmitter({
    count: 70,
    origin: new THREE.Vector3(-8, campHeight(-8, 18) + 2, 18),
    spread: 1.2,
    rise: 1.5,
    riseJitter: 0.4,
    drift: 0.8,
    life: 5.5,
    lifeJitter: 0.4,
    size: 18,
    sizeGrow: 28,
    alpha: 0.22,
    rate: 0,
    color: 0x5a4a42,
    colorEnd: 0x2e2622,
  });
  group.add(smoke.points);

  return {
    name: 'STATE_3_AMALEKITE_RESCUE',
    group,
    spawn: CAMP.spawn,
    bounds: CAMP.bounds,
    colliders,
    fog: { color: 0x6b4a3a, density: 0.0095 },
    sun,
    groundHeight: campHeight,
    guards,
    captives: CAMP.captives,
    rescueRadius: CAMP.rescueRadius,

    /**
     * Vision check: distance < range AND player within the guard's facing cone.
     * Guards slowly scan, so the cone breathes like an attention arc.
     * @returns {{detected:boolean, alert:number}} alert = 0..1 glow intensity
     */
    checkDetection(playerPos) {
      let detected = false;
      let maxProx = 0;

      for (const guard of guards) {
        const gx = guard.group.position.x;
        const gz = guard.group.position.z;
        const dx = playerPos.x - gx;
        const dz = playerPos.z - gz;
        const dist = Math.sqrt(dx * dx + dz * dz);

        const prox = 1 - Math.min(1, dist / VISION.range);
        if (dist < VISION.range) {
          const angleToPlayer = Math.atan2(dx, dz);
          const diff = Math.abs(wrapAngle(angleToPlayer - guard.facing));
          const inCone = diff < VISION.halfAngle;
          if (inCone) {
            detected = true;
            maxProx = Math.max(maxProx, prox * (1 - diff / VISION.halfAngle) + 0.35);
          }
        }

        // visual feedback on the cone
        const mat = guard.cone.material;
        const alertness = dist < VISION.range
          ? Math.max(0, 1 - Math.abs(wrapAngle(Math.atan2(dx, dz) - guard.facing)) / VISION.halfAngle)
          : 0;
        mat.color.setHex(alertness > 0.55 ? 0xff4444 : 0xffd25e);
        mat.opacity = 0.12 + alertness * 0.28;
      }
      return { detected, alert: Math.min(1, maxProx) };
    },

    update(dt, time) {
      // guards scan + celebrate
      for (const guard of guards) {
        guard.facing = guard.scan.base + Math.sin(time * guard.scan.speed + guard.scan.phase) * guard.scan.amplitude;
        guard.group.rotation.y = guard.facing;
        const parts = guard.human.userData.parts;
        const bounce = Math.sin(time * 2.6 + guard.scan.phase) * 0.12;
        parts.armL.rotation.z = 2.45 + bounce;
        parts.armR.rotation.z = -2.45 - bounce;
        guard.human.position.y = Math.abs(Math.sin(time * 2.6 + guard.scan.phase)) * 0.06;
      }
      smoke.update(dt);
      for (const u of timeUpdaters) u(time);
      for (const u of dtUpdaters) u(dt);
    },
  };
}
