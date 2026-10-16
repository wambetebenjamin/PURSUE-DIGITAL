/**
 * DesertZone.js — PHASE 2: "The Pursuit & The Brook"
 *
 * Sun-bleached dunes, rocky valley walls (instanced), and the blue ribbon of
 * the Brook Besor carved through a wadi. A beacon marks the arid field beyond.
 * The brook position is exported so Game can fire the narrative trigger.
 */
import * as THREE from 'three';
import { Mat } from '../../utils/materials.js';
import {
  createRockField, createPalm, createDryBush, createBeacon, createRock, createHumanoid,
  createReeds,
} from '../props/PropFactory.js';
import { ParticleEmitter } from '../effects/Particles.js';
import { SkyDome } from '../effects/SkyDome.js';
import { makeTextSprite } from '../../utils/canvasText.js';
import { seededRandom } from '../../utils/math.js';

export const DESERT = {
  spawn: { x: 0, z: 112, yaw: 0 },
  brookZ: 0,
  brookTrigger: 7.5,
  exit: { x: 0, z: -108 },
  bounds: { minX: -52, maxX: 52, minZ: -118, maxZ: 120 },
};

/** Base rolling dunes (also used for player terrain-follow). */
export function desertHeight(x, z) {
  const base =
    Math.sin(x * 0.045) * 1.8 +
    Math.cos(z * 0.032) * 2.2 +
    Math.sin((x + z) * 0.017) * 2.6;

  // wadi carve for the brook: flatten toward banks, dig a channel at z=0
  const wadi = Math.exp(-(z * z) / 300);
  const bank = 1.15 + Math.sin(x * 0.02) * 0.3;
  const channel = -2.45 * Math.exp(-(z * z) / 42);
  return base * (1 - wadi) + (bank + channel) * wadi;
}

export function createDesertZone() {
  const group = new THREE.Group();
  group.name = 'ZONE_DESERT';
  const colliders = [];
  const updaters = [];

  // ------------------------------------------------------------- sky
  // PHASE 2 spec: brutal, overexposed midday desert sun at 6500K
  const sky = new SkyDome(0x2f8fe8, 0xffd9a0, 0.85);
  group.add(sky.mesh);

  // ---------------------------------------------------------- lights
  const hemi = new THREE.HemisphereLight(0xbcd4f0, 0xe0c896, 1.05);
  group.add(hemi);

  const sun = new THREE.DirectionalLight(0xfff2d0, 1.8); // blazing Miami noon
  sun.position.set(46, 70, 26);
  sun.castShadow = true;
  sun.shadow.mapSize.set(1024, 1024);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = 190;
  sun.shadow.camera.left = -44;
  sun.shadow.camera.right = 44;
  sun.shadow.camera.top = 44;
  sun.shadow.camera.bottom = -44;
  sun.shadow.bias = -0.0012;
  sun.userData.offset = new THREE.Vector3(42, 68, 24);
  group.add(sun, sun.target);
  sky.setSun(sun.userData.offset, 0xfff0c0, 0.55);

  // --------------------------------------------------------- terrain
  const groundGeo = new THREE.PlaneGeometry(150, 300, 64, 110);
  groundGeo.rotateX(-Math.PI / 2);
  const pos = groundGeo.attributes.position;
  for (let i = 0; i < pos.count; i++) {
    const x = pos.getX(i);
    const z = pos.getZ(i);
    pos.setY(i, desertHeight(x, z));
  }
  groundGeo.computeVertexNormals();
  const ground = new THREE.Mesh(groundGeo, Mat.sand());
  ground.receiveShadow = true;
  group.add(ground);

  // ---------------------------------------------------- Besor Brook
  // "thick brown sediment water" with blinding white speculars at 6500K
  const waterMat = Mat.water().clone();
  waterMat.color.set(0x1fa8b8); // glossy tropical teal
  waterMat.specular.set(0xffffff);
  waterMat.shininess = 180;
  waterMat.opacity = 0.9;
  waterMat.onBeforeCompile = (shader) => {
    shader.uniforms.uTime = { value: 0 };
    shader.vertexShader = `uniform float uTime;\n${shader.vertexShader}`.replace(
      '#include <begin_vertex>',
      `#include <begin_vertex>
       transformed.y += sin(position.x * 0.55 + uTime * 1.4) * 0.06
                      + cos(position.y * 0.4 + uTime * 1.1) * 0.05;`,
    );
    waterMat.userData.shader = shader;
  };
  const water = new THREE.Mesh(new THREE.PlaneGeometry(150, 15), waterMat);
  water.rotation.x = -Math.PI / 2;
  water.position.set(0, -0.32, 0);
  water.receiveShadow = false;
  group.add(water);

  // a couple of dark stones in the stream for readability
  for (let i = 0; i < 8; i++) {
    const x = (seededRandom(i * 4.4) - 0.5) * 90;
    const z = (seededRandom(i * 7.7) - 0.5) * 7;
    const s = 0.5 + seededRandom(i * 2.2) * 1.1;
    const stone = createRock(100 + i, true);
    stone.position.set(x, -0.25, z);
    stone.scale.setScalar(s);
    group.add(stone);
  }

  // ---------------------------------------------------- valley walls
  // big instanced rock fields hug the playable corridor — dry, porous,
  // crumbly limestone shale ("sharp jagged geometry and zero specularity")
  const rocksL = createRockField(46, { minX: -88, maxX: -50, minZ: -135, maxZ: 135 }, 11);
  const rocksR = createRockField(46, { minX: 50, maxX: 88, minZ: -135, maxZ: 135 }, 22);
  rocksL.material = Mat.limestone();
  rocksR.material = Mat.limestone();
  group.add(rocksL, rocksR);

  // walkable-area boulders (with colliders)
  for (let i = 0; i < 12; i++) {
    const x = (seededRandom(i * 3.1) - 0.5) * 62 * (seededRandom(i * 8.2) > 0.5 ? 1 : -1);
    const z = 100 - i * 18 + (seededRandom(i * 5.5) - 0.5) * 10;
    if (Math.abs(z) < 12) continue; // keep the brook ford open
    const rock = createRock(200 + i);
    const s = 0.8 + seededRandom(i * 6.6) * 1.4;
    rock.scale.multiplyScalar(s);
    rock.position.set(x, desertHeight(x, z), z);
    group.add(rock);
    colliders.push({ x, z, r: 1.1 * s });
  }

  // reed beds along both banks
  const reedsL = createReeds(420, { minX: -40, maxX: -8, minZ: -9, maxZ: 9 }, 61, desertHeight);
  const reedsR = createReeds(420, { minX: 8, maxX: 40, minZ: -9, maxZ: 9 }, 62, desertHeight);
  group.add(reedsL, reedsR);
  updaters.push((t) => { reedsL.userData.update(t); reedsR.userData.update(t); });

  // ------------------------------------ the 200 who could not cross
  // exhausted Israelites collapsing onto the left sandstone ledge (v. 9-10)
  const spentPalette = {
    skin: Mat.skin(),
    cloth: Mat.tunicCaptive(),
    cloth2: Mat.tunicEarth(),
    hair: Mat.hair(),
  };
  for (let i = 0; i < 7; i++) {
    const x = -17 - (i % 3) * 3.4 + (seededRandom(i * 3.1) - 0.5);
    const z = -7 + i * 2.6;
    const man = createHumanoid(spentPalette, i % 2 === 0 ? 'seatedCaptive' : 'grieving',
      { beard: 'full', detail: 'low' });
    man.position.set(x, desertHeight(x, z), z);
    man.rotation.y = Math.PI / 2 + (seededRandom(i * 5.9) - 0.5) * 0.7;
    group.add(man);
  }
  // a few of the 400 already wading the torrent
  for (let i = 0; i < 4; i++) {
    const x = -6 + i * 4.5;
    const z = -1.5 + (i % 2) * 3;
    const wader = createHumanoid(spentPalette, 'standing',
      { armor: 'leather', beard: 'full', detail: 'low' });
    wader.position.set(x, desertHeight(x, z) - 0.55, z);
    wader.rotation.y = Math.PI; // pushing north across the brook
    group.add(wader);
    updaters.push((t) => {
      wader.rotation.z = Math.sin(t * 1.8 + i * 1.3) * 0.08; // fighting the current
    });
  }

  // ------------------------------------------------- palms & shrubs
  const palmSpots = [
    [-14, 14], [16, 9], [-20, -12], [12, -16], [-30, 40], [28, -46],
  ];
  palmSpots.forEach(([x, z], i) => {
    const palm = createPalm(i + 1);
    palm.position.set(x, desertHeight(x, z), z);
    group.add(palm);
    colliders.push({ x, z, r: 0.5 });
  });

  for (let i = 0; i < 16; i++) {
    const x = (seededRandom(i * 7.3) - 0.5) * 84;
    const z = (seededRandom(i * 9.9) - 0.5) * 220;
    if (Math.abs(z) < 10) continue;
    const bush = createDryBush(i + 1);
    bush.position.set(x, desertHeight(x, z), z);
    group.add(bush);
  }

  // ---------------------------------------------------------- beacon
  const beacon = createBeacon('Arid Field', 0xffc857);
  beacon.position.set(DESERT.exit.x, desertHeight(DESERT.exit.x, DESERT.exit.z), DESERT.exit.z);
  const label = makeTextSprite('THE ARID FIELD', { color: '#ffe9b8', bg: 'rgba(48,32,8,0.5)' });
  label.position.set(0, 6.4, 0);
  beacon.add(label);
  group.add(beacon);
  updaters.push((t) => beacon.userData.update(t));

  // ------------------------------------------------------- particles
  const dust = new ParticleEmitter({
    count: 110,
    origin: new THREE.Vector3(0, 3, 0),
    spread: 55,
    rise: 0.35,
    riseJitter: 0.8,
    drift: 1.2,
    life: 8,
    lifeJitter: 0.4,
    size: 7,
    sizeGrow: 3,
    alpha: 0.16,
    rate: 0,
    color: 0xf2e2b8,
    colorEnd: 0xd9be86,
    additive: true,
  });
  // foam simulation along the churning rapids
  const foam = new ParticleEmitter({
    count: 90,
    origin: new THREE.Vector3(0, -0.15, 0),
    spread: 40,
    rise: 0.45,
    riseJitter: 1.2,
    drift: 1.6,
    life: 2.2,
    lifeJitter: 0.5,
    size: 9,
    sizeGrow: 5,
    alpha: 0.35,
    rate: 0,
    color: 0xf6f2e8,
    colorEnd: 0xd9d2c2,
    additive: true,
  });
  // severe dust wind-haze (opacity filter) under the overexposed sun
  const haze = new ParticleEmitter({
    count: 80,
    origin: new THREE.Vector3(0, 4.5, 0),
    spread: 48,
    rise: 0.25,
    riseJitter: 0.7,
    drift: 1.8,
    life: 10,
    lifeJitter: 0.35,
    size: 22,
    sizeGrow: 12,
    alpha: 0.1,
    rate: 0,
    color: 0xf6ecc9,
    colorEnd: 0xe0d0a8,
    additive: true,
  });
  group.add(dust.points, foam.points, haze.points);

  return {
    name: 'STATE_1_DESERT_MARCH',
    group,
    spawn: DESERT.spawn,
    bounds: DESERT.bounds,
    colliders,
    fog: { color: 0xe8dcb4, density: 0.0068 },
    sun,
    sky,
    groundHeight: desertHeight,
    waterLevel: -0.32,
    update(dt, time) {
      if (waterMat.userData.shader) waterMat.userData.shader.uniforms.uTime.value = time;
      dust.update(dt);
      foam.update(dt);
      haze.update(dt);
      for (const u of updaters) u(time);
    },
  };
}
