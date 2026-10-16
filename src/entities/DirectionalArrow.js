/**
 * DirectionalArrow.js — floating 3D chevron that points at the current
 * target coordinate zone. Enabled after the Egyptian servant dialogue.
 */
import * as THREE from 'three';
import { Mat, Geo } from '../utils/materials.js';

export class DirectionalArrow {
  constructor() {
    this.group = new THREE.Group();
    this.group.visible = false;

    // shaft + head, pointing along +Z of the group
    const shaft = new THREE.Mesh(Geo.box(), Mat.arrowGlow());
    shaft.scale.set(0.22, 0.16, 1.5);
    shaft.position.z = 0.2;
    shaft.castShadow = false;

    const head = new THREE.Mesh(Geo.cone(), Mat.arrowGlow());
    head.scale.set(0.62, 0.95, 0.62);
    head.rotation.x = Math.PI / 2;
    head.position.z = 1.35;
    head.castShadow = false;

    // glow ring under the arrow
    const halo = new THREE.Mesh(Geo.ring(), Mat.beaconRing());
    halo.rotation.x = -Math.PI / 2;
    halo.position.y = -0.55;
    halo.scale.setScalar(1.6);

    this.group.add(shaft, head, halo);
    this._target = new THREE.Vector3();
    this._hasTarget = false;
    this._t = 0;
  }

  /** Point the arrow at a world-space target (y is ignored). */
  setTarget(x, z) {
    this._target.set(x, 0, z);
    this._hasTarget = true;
  }

  show() {
    this.group.visible = true;
  }

  hide() {
    this.group.visible = false;
  }

  /**
   * @param {number} dt
   * @param {THREE.Vector3} playerPos
   */
  update(dt, playerPos) {
    if (!this.group.visible || !this._hasTarget) return;
    this._t += dt;

    // hover above & slightly behind the player
    const bob = Math.sin(this._t * 2.1) * 0.22;
    this.group.position.set(playerPos.x, playerPos.y + 4.1 + bob, playerPos.z);

    // yaw toward the target
    const dx = this._target.x - playerPos.x;
    const dz = this._target.z - playerPos.z;
    const desired = Math.atan2(dx, dz);
    // short-path rotation
    let diff = desired - this.group.rotation.y;
    while (diff > Math.PI) diff -= Math.PI * 2;
    while (diff < -Math.PI) diff += Math.PI * 2;
    this.group.rotation.y += diff * Math.min(1, dt * 6);
  }
}
