/**
 * CitadelScene.js — Ziklag as a city, seen from above.
 *
 * The pursuit game walks through a burned town; a strategy game needs a town
 * that grows. This builds the rebuilt Ziklag as one procedural isometric
 * diorama: a walled plateau, a gate, eight works whose geometry answers to
 * their level in the progression store, roads between them, people on the
 * roads, smoke where there is work and banners where there is a lord.
 *
 * Everything is primitives and the shared stronghold kit — the same public
 * architectural grammar the rest of the world uses — so the city costs one
 * group and a handful of materials, and rebuilding a single site on upgrade
 * disposes only that site.
 *
 * The module knows nothing about DOM or game rules: it is handed levels and
 * hands back a group, a camera rig and a picking list.
 */
import * as THREE from 'three';
import {
  createCurtainWall, createDrumTower, createGatehouse, createPalisade,
  createBanner, createBrazier, createStoneSteps, ashlarMat, ashlarDarkMat,
} from '../props/MedievalKit.js';
import { createPalm, createOliveTree, createCypress, createRock, createRockField } from '../props/PropFactory.js';
import { SkyDome } from '../effects/SkyDome.js';

/* ------------------------------------------------------------- materials */

const cache = new Map();
function cached(key, make) {
  if (!cache.has(key)) cache.set(key, make());
  return cache.get(key);
}

const M = {
  ground: () => cached('ground', () => new THREE.MeshLambertMaterial({ color: 0xb9a583, flatShading: true })),
  plateau: () => cached('plateau', () => new THREE.MeshLambertMaterial({ color: 0x9c8767, flatShading: true })),
  road: () => cached('road', () => new THREE.MeshLambertMaterial({ color: 0x8d7f68, flatShading: true })),
  plaster: () => cached('plaster', () => new THREE.MeshLambertMaterial({ color: 0xe8dcc0, flatShading: true })),
  plasterWarm: () => cached('plasterWarm', () => new THREE.MeshLambertMaterial({ color: 0xd8c29c, flatShading: true })),
  timber: () => cached('timber', () => new THREE.MeshLambertMaterial({ color: 0x6b4a2c, flatShading: true })),
  timberDark: () => cached('timberDark', () => new THREE.MeshLambertMaterial({ color: 0x40301f, flatShading: true })),
  roofTile: () => cached('roofTile', () => new THREE.MeshLambertMaterial({ color: 0x8a4a30, flatShading: true })),
  slate: () => cached('slate', () => new THREE.MeshLambertMaterial({ color: 0x3f4450, flatShading: true })),
  clothRed: () => cached('clothRed', () => new THREE.MeshLambertMaterial({ color: 0x8c2a18, side: THREE.DoubleSide })),
  clothIndigo: () => cached('clothIndigo', () => new THREE.MeshLambertMaterial({ color: 0x2c3358, side: THREE.DoubleSide })),
  gold: () => cached('gold', () => new THREE.MeshStandardMaterial({ color: 0xd9ab52, roughness: 0.35, metalness: 0.85 })),
  water: () => cached('water', () => new THREE.MeshStandardMaterial({
    color: 0x2f6f86, roughness: 0.12, metalness: 0.1, transparent: true, opacity: 0.92,
  })),
  ember: () => cached('ember', () => new THREE.MeshBasicMaterial({ color: 0xff6a2a })),
  wheat: () => cached('wheat', () => new THREE.MeshLambertMaterial({ color: 0xc9a95c, flatShading: true })),
  skin: () => cached('skin', () => new THREE.MeshLambertMaterial({ color: 0xa9794f, flatShading: true })),
  robe: () => cached('robe', () => new THREE.MeshLambertMaterial({ color: 0x7d6a4c, flatShading: true })),
  robeAlt: () => cached('robeAlt', () => new THREE.MeshLambertMaterial({ color: 0x5c5140, flatShading: true })),
  treeLeaf: () => cached('treeLeaf', () => new THREE.MeshLambertMaterial({ color: 0x4c6b2f, flatShading: true })),
};

/* ----------------------------------------------------------------- utils */

function box(mat, w, h, d, x = 0, y = 0, z = 0, ry = 0) {
  const m = new THREE.Mesh(new THREE.BoxGeometry(w, h, d), mat);
  m.position.set(x, y, z);
  m.rotation.y = ry;
  m.castShadow = true;
  m.receiveShadow = true;
  m.userData.owns = true;
  return m;
}

function cyl(mat, rTop, rBot, h, x = 0, y = 0, z = 0, seg = 10) {
  const m = new THREE.Mesh(new THREE.CylinderGeometry(rTop, rBot, h, seg), mat);
  m.position.set(x, y, z);
  m.castShadow = true;
  m.receiveShadow = true;
  m.userData.owns = true;
  return m;
}

function cone(mat, r, h, x = 0, y = 0, z = 0, seg = 10) {
  const m = new THREE.Mesh(new THREE.ConeGeometry(r, h, seg), mat);
  m.position.set(x, y, z);
  m.castShadow = true;
  m.receiveShadow = true;
  m.userData.owns = true;
  return m;
}

/** A ring of stakes — the yard fence of a working. */
function yardFence(group, radius, count, height = 1.5) {
  for (let i = 0; i < count; i++) {
    const a = (i / count) * Math.PI * 2;
    const x = Math.cos(a) * radius;
    const z = Math.sin(a) * radius;
    if (Math.abs(x) < 1.6 && z > 0) continue; // leave a way in
    const stake = cyl(M.timber(), 0.07, 0.1, height, x, height / 2, z, 5);
    stake.rotation.z = (Math.sin(i * 2.1) * 0.5) * 0.12;
    group.add(stake);
  }
  // rail
  for (let i = 0; i < count; i++) {
    const a0 = (i / count) * Math.PI * 2;
    const a1 = ((i + 1) / count) * Math.PI * 2;
    const x0 = Math.cos(a0) * radius;
    const z0 = Math.sin(a0) * radius;
    const x1 = Math.cos(a1) * radius;
    const z1 = Math.sin(a1) * radius;
    const len = Math.hypot(x1 - x0, z1 - z0);
    const rail = box(M.timber(), len, 0.08, 0.1, (x0 + x1) / 2, height * 0.66, (z0 + z1) / 2,
      Math.atan2(z1 - z0, x1 - x0));
    group.add(rail);
  }
}

/** Kit builds bake fresh geometry, so their meshes may be disposed on rebuild. */
function own(node) {
  node.traverse?.((o) => {
    if (o.isMesh && o.geometry) o.userData.owns = true;
  });
  return node;
}

/* ------------------------------------------------------------- the works */

/**
 * Each builder takes a level (1..5) and returns a group standing on y = 0
 * with its footprint centred on the origin.
 */
const BUILD = {
  hall(level) {
    const g = new THREE.Group();
    const storeys = Math.min(5, level);
    const w = 10 + level * 0.4;
    // plinth
    g.add(box(ashlarDarkMat(), w + 2.4, 0.7, w * 0.8 + 2, 0, 0.35, 0));
    let y = 0.7;
    for (let s = 0; s < storeys; s++) {
      const sw = w * (1 - s * 0.09);
      const sh = 2.5;
      g.add(box(M.plaster(), sw, sh, sw * 0.78, 0, y + sh / 2, 0));
      // timber banding between storeys
      g.add(box(M.timber(), sw + 0.16, 0.22, sw * 0.78 + 0.16, 0, y + sh - 0.1, 0));
      // windows, lit at the top storey
      const windows = 3;
      for (let i = 0; i < windows; i++) {
        const wx = (i - (windows - 1) / 2) * (sw / (windows + 0.4));
        g.add(box(s >= storeys - 1 ? M.gold() : M.timberDark(), 0.6, 1.05, 0.12,
          wx, y + sh * 0.55, sw * 0.39 + 0.02));
        g.add(box(M.timberDark(), 0.6, 1.05, 0.12, wx, y + sh * 0.55, -sw * 0.39 - 0.02));
      }
      y += sh;
    }
    // the flat roof with a parapet, and a tower at one corner
    g.add(box(ashlarDarkMat(), w * (1 - (storeys - 1) * 0.09) + 0.5, 0.35, w * 0.62, 0, y + 0.17, 0));
    const towerX = -w * 0.36;
    const towerH = 3.2 + level * 0.5;
    g.add(cyl(ashlarMat(), 1.5, 1.8, towerH, towerX, towerH / 2, -w * 0.2, 10));
    g.add(cone(M.slate(), 2.0, 1.7, towerX, towerH + 0.85, -w * 0.2, 10));
    // banners of the house — more as the hall grows
    const flags = Math.min(4, Math.max(1, level - 1));
    for (let i = 0; i < flags; i++) {
      const banner = own(createBanner({ color: 0x8c2a18, height: 5.2, width: 1.5 }));
      banner.position.set(w * 0.36 - i * 1.9, 0, w * 0.34);
      g.add(banner);
    }
    if (level >= 5) g.add(cyl(M.gold(), 0.22, 0.3, 1.1, towerX, towerH + 1.9, -w * 0.2, 8));
    // steps up to the door
    g.add(own(createStoneSteps({ width: 5.4, steps: 4 })));
    const steps = g.children[g.children.length - 1];
    steps.position.set(0, 0, w * 0.42);
    // a brazier of the guard at either side of the door
    for (const sx of [-3.6, 3.6]) {
      const brazier = own(createBrazier({ withLight: level >= 3 }));
      brazier.position.set(sx, 0.2, w * 0.44);
      g.add(brazier);
    }
    return g;
  },

  barracks(level) {
    const g = new THREE.Group();
    // drill yard
    g.add(box(M.road(), 16, 0.14, 13, 0, 0.07, 0));
    yardFence(g, 8.6, 26, 1.4);
    // the long hall
    const w = 11 + level * 0.5;
    g.add(box(M.plasterWarm(), w, 3.1, 5.6, 0, 1.55, -4.2));
    const roof = cone(M.roofTile(), w * 0.72, 1.9, 0, 3.9, -4.2, 4);
    roof.rotation.y = Math.PI / 4;
    g.add(roof);
    // weapon racks along the wall
    for (let i = 0; i < Math.min(6, level + 1); i++) {
      const rack = new THREE.Group();
      for (let s = 0; s < 5; s++) {
        const spear = cyl(M.timber(), 0.045, 0.045, 2.1, s * 0.3 - 0.6, 1.05, 0, 4);
        spear.rotation.z = 0.12;
        rack.add(spear);
        rack.add(box(ashlarDarkMat(), 0.1, 0.22, 0.06, s * 0.3 - 0.6, 2.15, 0));
      }
      rack.position.set(-w / 2 + 1.2 + i * 2.0, 0, -0.6);
      g.add(rack);
    }
    // training posts
    for (let i = 0; i < Math.min(5, level + 1); i++) {
      g.add(cyl(M.timber(), 0.16, 0.2, 1.9, -5 + i * 2.4, 0.95, 3.4, 6));
      g.add(box(M.timberDark(), 1.0, 0.16, 0.16, -5 + i * 2.4, 1.7, 3.4));
    }
    // the muster banner at the yard mouth
    const flag = own(createBanner({ color: 0x2c3358, height: 4.6, width: 1.4 }));
    flag.position.set(5.4, 0, 5.2);
    g.add(flag);
    // an armoury lean-to that appears as the barracks grow
    if (level >= 3) {
      g.add(box(M.timber(), 3.4, 2.2, 3.0, -6.4, 1.1, 4.6));
      g.add(box(M.roofTile(), 4.0, 0.2, 3.6, -6.4, 2.3, 4.6));
    }
    if (level >= 4) {
      const brazier = own(createBrazier({ withLight: true }));
      brazier.position.set(-2.4, 0, 5.6);
      g.add(brazier);
    }
    return g;
  },

  cistern(level) {
    const g = new THREE.Group();
    const r = 5.4 + level * 0.35;
    // the stone kerb and the water in it
    g.add(cyl(ashlarDarkMat(), r, r + 0.3, 1.5, 0, 0.75, 0, 16));
    const waterH = 0.5 + level * 0.12;
    const water = cyl(M.water(), r - 0.7, r - 0.7, 0.12, 0, 1.5 + 0.02, 0, 16);
    water.position.y = 1.2 + waterH * 0.1;
    g.add(water);
    // coping
    for (let i = 0; i < 16; i++) {
      const a = (i / 16) * Math.PI * 2;
      g.add(box(ashlarMat(), 1.1, 0.28, 0.6, Math.cos(a) * (r + 0.16), 1.55, Math.sin(a) * (r + 0.16), -a));
    }
    // a dome once it is a proper reservoir
    if (level >= 3) {
      const dome = new THREE.Mesh(new THREE.SphereGeometry(r - 1.2, 18, 10, 0, Math.PI * 2, 0, Math.PI / 2), M.plaster());
      dome.position.y = 1.6;
      dome.castShadow = true;
      g.add(dome);
      g.add(cyl(M.gold(), 0.16, 0.16, 0.8, 0, r * 0.55 + 2.0, 0, 8));
    }
    // steps down to the water and the jars that carry it
    const steps = own(createStoneSteps({ width: 3.4, steps: 3 }));
    steps.position.set(0, 0, r + 0.6);
    g.add(steps);
    for (let i = 0; i < Math.min(6, level + 1); i++) {
      const a = -0.7 + i * 0.28;
      g.add(cyl(M.plaster(), 0.32, 0.24, 0.62, Math.cos(a) * (r + 1.6), 0.31, Math.sin(a) * (r + 1.6), 8));
    }
    return g;
  },

  threshing(level) {
    const g = new THREE.Group();
    // the threshing floor: a worn circle of stone
    g.add(cyl(M.road(), 7.4, 7.6, 0.16, 0, 0.08, 0, 20));
    g.add(cyl(ashlarDarkMat(), 8.0, 8.1, 0.12, 0, 0.05, 0, 20));
    // sheaves stacked around the edge
    const sheaves = Math.min(18, level * 3 + 2);
    for (let i = 0; i < sheaves; i++) {
      const a = (i / sheaves) * Math.PI * 2 + 0.3;
      const rad = 5.4 + (i % 2) * 0.9;
      const h = 1.2 + (i % 3) * 0.22;
      g.add(cone(M.wheat(), 0.5, h, Math.cos(a) * rad, h / 2, Math.sin(a) * rad, 7));
    }
    // a mill, once the floor can afford one
    if (level >= 3) {
      g.add(cyl(ashlarDarkMat(), 1.5, 1.8, 2.2, -4.4, 1.1, -4.4, 10));
      const blade = new THREE.Group();
      for (let i = 0; i < 4; i++) {
        const b = box(M.timber(), 0.3, 3.0, 0.12, 0, 1.3, 0);
        b.rotation.z = (i / 4) * Math.PI * 2;
        blade.add(b);
      }
      blade.position.set(-4.4, 2.6, -3.6);
      blade.name = 'MILL_BLADES';
      g.add(blade);
    }
    // the winnowing baskets and a cart
    for (let i = 0; i < Math.min(4, level); i++) {
      g.add(cyl(M.timber(), 0.5, 0.4, 0.28, 3.0 - i * 0.8, 0.2, -5.4, 10));
    }
    if (level >= 2) g.add(box(M.timber(), 2.6, 0.8, 1.5, 4.6, 0.5, 3.4));
    return g;
  },

  quarry(level) {
    const g = new THREE.Group();
    // a stepped pit, cut into the plateau
    const pits = Math.min(4, level);
    for (let i = 0; i < pits; i++) {
      const r = 6.4 - i * 1.25;
      const y = -0.3 - i * 0.45;
      g.add(cyl(ashlarDarkMat(), r, r + 0.4, 0.5, 0, y, 0, 14));
    }
    // cut blocks stacked at the lip
    const blocks = Math.min(20, level * 4);
    for (let i = 0; i < blocks; i++) {
      const a = (i / blocks) * Math.PI * 2;
      const rad = 5.8 + (i % 3) * 0.7;
      g.add(box(ashlarMat(), 1.1, 0.62, 0.8, Math.cos(a) * rad, 0.31 + (i % 2) * 0.62, Math.sin(a) * rad,
        a * 1.3));
    }
    // a crane of beams, and a chisel bench
    if (level >= 3) {
      g.add(box(M.timber(), 0.35, 5.4, 0.35, -4.6, 2.7, 3.8));
      g.add(box(M.timber(), 0.35, 5.4, 0.35, -1.6, 2.7, 3.8));
      g.add(box(M.timber(), 3.4, 0.32, 0.32, -3.1, 5.3, 3.8));
      g.add(box(M.timber(), 0.28, 2.6, 0.28, -0.4, 4.0, 3.8));
      g.add(box(ashlarDarkMat(), 1.2, 0.9, 1.0, -0.4, 2.5, 3.8));
    }
    // dust of the working
    g.add(createRockField(10, 10, 3));
    return g;
  },

  smelter(level) {
    const g = new THREE.Group();
    // the furnace: a squat stone stack, hotter as it grows
    g.add(box(ashlarDarkMat(), 6.4, 2.4, 6.0, 0, 1.2, 0));
    g.add(cyl(ashlarDarkMat(), 1.6, 2.1, 2.2, 0, 3.4, 0, 10));
    const chimneyH = 3.0 + level * 1.15;
    g.add(cyl(M.timberDark(), 1.0, 1.35, chimneyH, 0, 4.4 + chimneyH / 2, 0, 10));
    // the mouth of the furnace
    g.add(box(M.ember(), 1.5, 0.9, 0.3, 0, 1.1, 3.02));
    const light = new THREE.PointLight(0xff7a30, 1.1 + level * 0.25, 18, 2);
    light.position.set(0, 1.6, 3.2);
    g.add(light);
    // bellows and an anvil, and ingots stacked on the yard
    g.add(box(M.timber(), 2.4, 0.5, 1.2, -4.4, 0.9, 2.4));
    g.add(box(M.timber(), 0.5, 1.4, 0.9, -4.4, 0.45, 2.4));
    g.add(box(M.timberDark(), 1.1, 0.7, 0.7, 4.0, 0.35, 2.6));
    for (let i = 0; i < Math.min(10, level * 2); i++) {
      g.add(box(M.gold(), 0.7, 0.16, 0.5, 3.0 + (i % 3) * 0.75, 0.1 + Math.floor(i / 3) * 0.17, -2.4,
        i * 0.4));
    }
    // slag heaps
    for (let i = 0; i < level; i++) {
      g.add(cone(M.timberDark(), 0.9 + (i % 2) * 0.3, 0.18, -5 + i * 1.5, 0.09, -3.4, 7));
    }
    return g;
  },

  hospital(level) {
    const g = new THREE.Group();
    const tents = Math.min(7, level + 2);
    for (let i = 0; i < tents; i++) {
      const a = (i / tents) * Math.PI * 2;
      const rad = 3.0 + (i % 2) * 3.4;
      const t = new THREE.Group();
      t.add(box(M.plaster(), 3.2, 1.5, 3.0, 0, 0.75, 0));
      t.add(cone(i % 2 ? M.clothIndigo() : M.plasterWarm(), 2.6, 1.5, 0, 2.1, 0, 4));
      t.rotation.y = a + 0.4;
      t.position.set(Math.cos(a) * rad, 0, Math.sin(a) * rad);
      g.add(t);
    }
    // the well of the sick, a brazier, and the herb plot
    g.add(cyl(ashlarDarkMat(), 1.1, 1.2, 0.9, 0, 0.45, 0, 12));
    const brazier = own(createBrazier({ withLight: true }));
    brazier.position.set(0, 0.2, 2.4);
    g.add(brazier);
    if (level >= 2) {
      for (let i = 0; i < Math.min(8, level * 2); i++) {
        g.add(box(M.treeLeaf(), 1.2, 0.3, 0.9, -4.4 + (i % 4) * 1.4, 0.16, -4.0 + Math.floor(i / 4) * 1.4));
      }
    }
    if (level >= 4) g.add(own(createPalisade({ length: 12 })));
    return g;
  },
};

/** Where each work stands in the city, and how it is named in the HUD. */
export const SITES = Object.freeze([
  { id: 'hall', x: 0, z: -15, yaw: Math.PI, radius: 8.5, name: 'The Hall of the March' },
  { id: 'barracks', x: 20, z: 3, yaw: -Math.PI / 2, radius: 9.5, name: 'The Barracks' },
  { id: 'cistern', x: -20, z: 6, yaw: Math.PI / 2, radius: 8.0, name: 'The Cistern' },
  { id: 'threshing', x: -20, z: -16, yaw: 0.6, radius: 8.6, name: 'The Threshing Floor' },
  { id: 'quarry', x: 21, z: -15, yaw: -0.5, radius: 7.6, name: 'The Quarry' },
  { id: 'smelter', x: 15, z: 19, yaw: -2.2, radius: 6.4, name: 'The Smelter' },
  { id: 'hospital', x: -15, z: 19, yaw: 2.3, radius: 7.2, name: 'The Tent of the Wounded' },
]);

/* --------------------------------------------------------------- the city */

export class CitadelScene {
  /** @param {object} [levels] map of building id → level (1..5) */
  constructor(levels = {}) {
    this.levels = { ...levels };
    this.group = new THREE.Group();
    this.group.name = 'CITADEL';

    this.sites = new Map();      // id → { root, group, level }
    this.pickables = [];         // meshes carrying userData.site
    this.smoke = [];             // rising puffs
    this.people = [];            // walkers
    this.time = 0;

    this._buildGround();
    this._buildSky();
    this._buildLights();
    this._buildWall(this.levels.wall || 1);
    this._buildRoads();
    this._buildSites();
    this._buildScatter();
    this._buildPeople();

    this.camera = { yaw: 0, pitch: 0.8, dist: 78, target: new THREE.Vector2(0, 3) };
  }

  /* ------------------------------------------------------------- ground */

  _buildGround() {
    // the plain the city sits on
    const plain = new THREE.Mesh(new THREE.CircleGeometry(240, 48), M.ground());
    plain.rotation.x = -Math.PI / 2;
    plain.position.y = -2.2;
    plain.receiveShadow = true;
    this.group.add(plain);

    // the plateau: a low cylinder with a slight batter
    const plateau = cyl(M.plateau(), 47, 50, 4.2, 0, -2.1, 0, 40);
    plateau.receiveShadow = true;
    this.group.add(plateau);

    // a skirt of rock at the base, so the plateau does not read as a cake
    for (let i = 0; i < 34; i++) {
      const a = (i / 34) * Math.PI * 2;
      const r = 46 + (i % 4) * 1.4;
      const rock = createRock(i + 3, true);
      rock.scale.setScalar(1.2 + (i % 3) * 0.5);
      rock.position.set(Math.cos(a) * r, -1.6, Math.sin(a) * r);
      this.group.add(rock);
    }

    // the ground of the city itself, slightly lighter where it is trodden
    const floor = new THREE.Mesh(new THREE.CircleGeometry(45, 40), M.ground());
    floor.rotation.x = -Math.PI / 2;
    floor.position.y = 0.01;
    floor.receiveShadow = true;
    this.group.add(floor);
  }

  _buildSky() {
    this.sky = new SkyDome({
      sky: {
        zenith: 0x4a7bb0, horizon: 0xe8c9a0, ground: 0x8a7355,
        turbidity: 7.5, rayleigh: 1.35, glow: 0.85, cloud: 0.34,
        cloudTint: 0xf6e2c8, stars: 0, moon: 0,
      },
      sun: { dir: [-0.45, 0.62, 0.64], color: 0xffe1b8, disc: 1.0, glow: 0.9 },
    });
    this.group.add(this.sky.mesh);
  }

  _buildLights() {
    const hemi = new THREE.HemisphereLight(0xcfe0f5, 0x9a8464, 1.05);
    this.group.add(hemi);

    const sun = new THREE.DirectionalLight(0xffe6c0, 2.1);
    sun.position.set(-52, 78, 64);
    sun.castShadow = true;
    sun.shadow.mapSize.set(2048, 2048);
    const c = sun.shadow.camera;
    c.left = -70; c.right = 70; c.top = 70; c.bottom = -70; c.near = 1; c.far = 260;
    sun.shadow.bias = -0.0006;
    sun.target.position.set(0, 0, 0);
    this.group.add(sun, sun.target);
    this.sun = sun;

    // a warm bounce from the sand, low and wide
    const fill = new THREE.DirectionalLight(0xffd9a8, 0.35);
    fill.position.set(40, 18, -50);
    this.group.add(fill);
  }

  /* --------------------------------------------------------------- walls */

  _buildWall(level) {
    if (this.wallGroup) {
      this.group.remove(this.wallGroup);
      dispose(this.wallGroup);
    }
    const wall = new THREE.Group();
    wall.name = 'CITADEL_WALL';
    // an octagon of curtain walls, with a tower at every corner
    const sides = 8;
    const R = 42;
    const height = 5.4 + level * 0.62;
    for (let i = 0; i < sides; i++) {
      const a0 = (i / sides) * Math.PI * 2 + Math.PI / 8;
      const a1 = ((i + 1) / sides) * Math.PI * 2 + Math.PI / 8;
      const x0 = Math.cos(a0) * R;
      const z0 = Math.sin(a0) * R;
      const x1 = Math.cos(a1) * R;
      const z1 = Math.sin(a1) * R;
      const len = Math.hypot(x1 - x0, z1 - z0);
      const mx = (x0 + x1) / 2;
      const mz = (z0 + z1) / 2;
      const ry = -Math.atan2(z1 - z0, x1 - x0) + Math.PI / 2;
      // the south face carries the gate instead of a plain run
      if (i === 1) {
        const gate = own(createGatehouse({ span: 9, height: height + 1.6, towerRadius: 3.0 }));
        gate.position.set(mx, 0, mz);
        gate.rotation.y = ry;
        wall.add(gate);
        continue;
      }
      const run = own(createCurtainWall({ length: len + 1.2, height, thickness: 2.0 }));
      run.position.set(mx, 0, mz);
      run.rotation.y = ry;
      wall.add(run);
      // corner towers
      const tower = own(createDrumTower({
        radius: 2.9 + (i % 2) * 0.3,
        height: height + 2.4,
        top: i % 3 === 0 ? 'cone' : 'crenel',
      }));
      tower.position.set(x0, 0, z0);
      wall.add(tower);
    }
    if (level >= 3) {
      // braziers on the gate towers once the wall is worth lighting
      for (const sx of [-4.4, 4.4]) {
        const brazier = own(createBrazier({ withLight: true }));
        brazier.position.set(sx * 0.9, 0, 40);
        wall.add(brazier);
      }
    }
    this.wallGroup = wall;
    this.group.add(wall);
  }

  _buildRoads() {
    const road = new THREE.Group();
    // the great way from the gate to the hall
    road.add(box(M.road(), 7, 0.08, 42, 0, 0.05, 20));
    // the cross way
    road.add(box(M.road(), 46, 0.08, 6, 0, 0.05, 6));
    // the ring the works are strung on
    const ring = new THREE.Mesh(new THREE.RingGeometry(26, 30, 40), M.road());
    ring.rotation.x = -Math.PI / 2;
    ring.position.y = 0.04;
    ring.receiveShadow = true;
    road.add(ring);
    // the plaza before the hall
    const plaza = new THREE.Mesh(new THREE.CircleGeometry(11, 28), M.road());
    plaza.rotation.x = -Math.PI / 2;
    plaza.position.set(0, 0.06, -6);
    plaza.receiveShadow = true;
    road.add(plaza);
    // a well and the market awnings in the plaza
    road.add(cyl(ashlarDarkMat(), 1.6, 1.9, 1.3, 5.2, 0.65, -2.4, 12));
    for (let i = 0; i < 5; i++) {
      const a = -2.4 + i * 0.5;
      const awning = box(M.clothRed(), 2.2, 0.14, 1.6, Math.cos(a) * 8.4, 2.1, -6 + Math.sin(a) * 7.0, a);
      awning.rotation.z = 0.08;
      road.add(awning);
      road.add(box(M.timber(), 0.12, 2.1, 0.12, Math.cos(a) * 8.4 - 0.9, 1.05, -6 + Math.sin(a) * 7.0));
      road.add(box(M.timber(), 0.12, 2.1, 0.12, Math.cos(a) * 8.4 + 0.9, 1.05, -6 + Math.sin(a) * 7.0));
    }
    this.group.add(road);
  }

  /* --------------------------------------------------------------- sites */

  _buildSites() {
    for (const site of SITES) {
      const root = new THREE.Group();
      root.position.set(site.x, 0, site.z);
      root.rotation.y = site.yaw;
      root.name = `SITE_${site.id.toUpperCase()}`;
      this.group.add(root);

      // the pick proxy — invisible, generous, and always facing the camera
      const proxy = new THREE.Mesh(
        new THREE.CylinderGeometry(site.radius, site.radius, 9, 10),
        new THREE.MeshBasicMaterial({ visible: false }),
      );
      proxy.position.set(site.x, 4, site.z);
      proxy.userData.site = site.id;
      this.group.add(proxy);
      this.pickables.push(proxy);

      this.sites.set(site.id, { root, level: 0, proxy });
      this.setLevel(site.id, this.levels[site.id] || 1);
    }
  }

  /** Rebuild one work at a level. Cheap: only that site's geometry is replaced. */
  setLevel(id, level) {
    const site = this.sites.get(id);
    const build = BUILD[id];
    if (!site || !build) return false;
    const lv = Math.max(1, Math.min(5, Math.round(level || 1)));
    if (site.level === lv) return false;
    while (site.root.children.length) {
      const child = site.root.children.pop();
      dispose(child);
    }
    const mesh = build(lv);
    site.root.add(mesh);
    site.level = lv;
    site.group = mesh;
    return true;
  }

  /** Apply a whole snapshot of levels (after an upgrade, after a load). */
  sync(levels = {}) {
    let changed = false;
    for (const site of SITES) {
      const next = levels[site.id] || 1;
      if (next !== this.sites.get(site.id)?.level && this.setLevel(site.id, next)) changed = true;
    }
    const wallLevel = levels.wall || 1;
    if (this._wallLevel !== wallLevel) {
      this._wallLevel = wallLevel;
      this._buildWall(wallLevel);
      changed = true;
    }
    return changed;
  }

  /* ------------------------------------------------------------- scatter */

  _buildScatter() {
    const rnd = (() => {
      let s = 12345;
      return () => ((s = (s * 1103515245 + 12345) & 0x7fffffff) / 0x7fffffff);
    })();

    for (let i = 0; i < 26; i++) {
      const a = rnd() * Math.PI * 2;
      const r = 12 + rnd() * 26;
      const x = Math.cos(a) * r;
      const z = Math.sin(a) * r;
      // keep the avenues clear
      if (Math.abs(x) < 6 && z > -20) continue;
      if (Math.abs(z - 6) < 5) continue;
      const pick = rnd();
      let tree;
      if (pick < 0.34) tree = createPalm(i + 2);
      else if (pick < 0.68) tree = createOliveTree(i + 5);
      else tree = createCypress(i + 9);
      tree.position.set(x, 0, z);
      tree.scale.setScalar(0.85 + rnd() * 0.4);
      tree.traverse((o) => { if (o.isMesh) o.castShadow = true; });
      this.group.add(tree);
    }

    // a few flocks and a cart, so the city is inhabited rather than modelled
    for (let i = 0; i < 6; i++) {
      const a = rnd() * Math.PI * 2;
      const r = 16 + rnd() * 18;
      const sheep = cyl(M.plaster(), 0.42, 0.5, 0.9, Math.cos(a) * r, 0.45, Math.sin(a) * r, 7);
      const head = box(M.timberDark(), 0.3, 0.3, 0.45, Math.cos(a) * r + 0.35, 0.75, Math.sin(a) * r);
      this.group.add(sheep, head);
    }
  }

  /* --------------------------------------------------------------- people */

  _buildPeople() {
    const rnd = (() => {
      let s = 7331;
      return () => ((s = (s * 1103515245 + 12345) & 0x7fffffff) / 0x7fffffff);
    })();
    for (let i = 0; i < 14; i++) {
      const person = new THREE.Group();
      const robe = i % 2 ? M.robe() : M.robeAlt();
      const body = cyl(robe, 0.26, 0.36, 1.05, 0, 0.62, 0, 7);
      const head = new THREE.Mesh(new THREE.SphereGeometry(0.19, 8, 6), M.skin());
      head.position.y = 1.3;
      head.castShadow = true;
      const legs = box(M.timberDark(), 0.3, 0.42, 0.22, 0, 0.21, 0);
      person.add(body, head, legs);
      person.traverse((o) => { if (o.isMesh) o.castShadow = true; });
      this.group.add(person);
      this.people.push({
        node: person,
        angle: rnd() * Math.PI * 2,
        radius: 14 + rnd() * 14,
        speed: (0.06 + rnd() * 0.1) * (rnd() > 0.5 ? 1 : -1),
        bob: rnd() * Math.PI * 2,
        lane: rnd() > 0.5 ? 1 : -1,
      });
    }
  }

  /* --------------------------------------------------------------- update */

  update(dt, elapsed) {
    this.time = elapsed;
    this.sky.update(elapsed);

    // the mill turns, if the floor has one
    const mill = this.sites.get('threshing')?.group;
    if (mill) {
      const blades = mill.getObjectByName?.('MILL_BLADES');
      if (blades) blades.rotation.z = elapsed * 0.8;
    }

    // people walk the ring road
    for (const p of this.people) {
      p.angle += p.speed * dt;
      const x = Math.cos(p.angle) * p.radius;
      const z = Math.sin(p.angle) * p.radius * 0.8 + 4;
      p.node.position.set(x, 0.02, z);
      p.node.rotation.y = -p.angle + (p.speed > 0 ? 0 : Math.PI);
      p.bob += dt * 7;
      p.node.position.y = 0.02 + Math.abs(Math.sin(p.bob)) * 0.05;
    }

    // smoke rises from the smelter and the hospital fire
    const smelter = this.sites.get('smelter');
    if (smelter) {
      const lv = smelter.level || 1;
      if (this.smoke.length < lv + 2 && Math.random() < dt * 1.6) {
        const puff = new THREE.Mesh(
          new THREE.SphereGeometry(0.9, 7, 6),
          new THREE.MeshBasicMaterial({ color: 0x9a8f82, transparent: true, opacity: 0.34 }),
        );
        const s = smelter.root.getWorldPosition(new THREE.Vector3());
        puff.position.set(s.x + (Math.random() - 0.5) * 0.8, 8.4, s.z + (Math.random() - 0.5) * 0.8);
        puff.userData.life = 0;
        this.group.add(puff);
        this.smoke.push(puff);
      }
    }
    for (let i = this.smoke.length - 1; i >= 0; i--) {
      const puff = this.smoke[i];
      puff.userData.life += dt;
      puff.position.y += dt * 1.7;
      puff.position.x += dt * 0.5;
      puff.scale.multiplyScalar(1 + dt * 0.35);
      puff.material.opacity = Math.max(0, 0.34 * (1 - puff.userData.life / 9));
      if (puff.userData.life > 9) {
        this.group.remove(puff);
        dispose(puff);
        this.smoke.splice(i, 1);
      }
    }
  }

  /* --------------------------------------------------------------- camera */

  /** Place a camera on the rig. `state` = {yaw,pitch,dist,target:{x,z}}. */
  applyCamera(camera) {
    const c = this.camera;
    const tx = c.target.x;
    const tz = c.target.y;
    const cp = Math.cos(c.pitch);
    const x = tx + Math.sin(c.yaw) * cp * c.dist;
    const z = tz + Math.cos(c.yaw) * cp * c.dist;
    const y = Math.sin(c.pitch) * c.dist;
    camera.position.set(x, y + 2, z);
    camera.lookAt(tx, 1.6, tz);
    camera.updateProjectionMatrix();
  }

  orbit(dx, dy) {
    this.camera.yaw -= dx * 0.005;
    this.camera.pitch = clamp(this.camera.pitch + dy * 0.004, 0.35, 1.32);
  }

  pan(dx, dz) {
    this.camera.target.x = clamp(this.camera.target.x + dx, -26, 26);
    this.camera.target.y = clamp(this.camera.target.y + dz, -26, 30);
  }

  zoom(factor) {
    this.camera.dist = clamp(this.camera.dist * factor, 26, 105);
  }
}

/* ------------------------------------------------------------------ util */

function clamp(v, lo, hi) {
  return Math.max(lo, Math.min(hi, v));
}

/**
 * Free a rebuilt work's own geometry.
 *
 * Only geometry this module (or a kit builder) created is disposed: the
 * shared `Geo` cache and the material cache belong to the whole game, and
 * disposing one of those would break every other prop using it.
 */
function dispose(node) {
  node.traverse?.((o) => {
    if (o.isMesh && o.geometry && o.userData.owns) o.geometry.dispose();
    // materials are shared and cached — never disposed here
  });
}
