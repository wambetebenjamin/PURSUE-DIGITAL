/**
 * World.js — owns every zone, applies per-phase atmosphere (fog + sky),
 * keeps the shadow-casting sun anchored on the player, and forwards updates.
 *
 * Zones are fully self-contained (own lights, sky, geometry). Only the active
 * zone is visible, so inactive phases cost zero GPU work — lights inside an
 * invisible group are culled by the renderer automatically.
 */
import * as THREE from 'three';
import { createZiklagZone } from './zones/ZiklagZone.js';
import { createDesertZone } from './zones/DesertZone.js';
import { createEgyptianZone } from './zones/EgyptianZone.js';
import { createCampZone } from './zones/CampZone.js';
import { createSpoilsZone } from './zones/SpoilsZone.js';
import { createGateZone } from './zones/GateZone.js';
import { createGilboaZone } from './zones/GilboaZone.js';
import { STATES } from '../constants.js';

export class World {
  constructor(scene) {
    this.scene = scene;
    this.zones = new Map();
    this.activeZone = null;

    this.scene.fog = new THREE.FogExp2(0x241c18, 0.016);

    // Build all seven phases up-front (cheap: low-poly primitives only).
    this.zones.set(STATES.ZIKLAG, createZiklagZone());
    this.zones.set(STATES.DESERT, createDesertZone());
    this.zones.set(STATES.EGYPTIAN, createEgyptianZone());
    this.zones.set(STATES.CAMP, createCampZone());
    this.zones.set(STATES.SPOILS, createSpoilsZone());
    this.zones.set(STATES.GATE, createGateZone());
    this.zones.set(STATES.GILBOA, createGilboaZone());

    for (const zone of this.zones.values()) {
      zone.group.visible = false;
      this.scene.add(zone.group);
    }
  }

  /** Activate a phase: toggles visibility + applies fog. */
  activate(stateName) {
    const zone = this.zones.get(stateName);
    if (!zone) throw new Error(`[World] unknown zone "${stateName}"`);

    if (this.activeZone) this.activeZone.group.visible = false;
    zone.group.visible = true;
    this.activeZone = zone;

    this.scene.fog.color.set(zone.fog.color);
    this.scene.fog.density = zone.fog.density;

    return zone;
  }

  /**
   * @param {number} dt
   * @param {number} time
   * @param {THREE.Vector3} playerPos keeps the shadow frustum on the action
   */
  update(dt, time, playerPos) {
    const zone = this.activeZone;
    if (!zone) return;

    zone.update(dt, time);
    zone.sky?.update?.(time);

    if (zone.sun) {
      zone.sun.position.copy(playerPos).add(zone.sun.userData.offset);
      zone.sun.target.position.copy(playerPos);
      zone.sun.target.updateMatrixWorld();
    }
  }

  get groundHeight() {
    return this.activeZone ? this.activeZone.groundHeight : () => 0;
  }
}
