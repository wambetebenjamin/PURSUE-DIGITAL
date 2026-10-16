/**
 * ThirdPersonCamera.js — pointer-lock orbit camera following the player.
 *
 * Yaw/pitch come from accumulated Pointer-Lock mouse deltas. The rig lerps
 * toward its ideal offset every frame (frame-rate independent), keeps the
 * look target at chest height, and never dips below the ground.
 */
import * as THREE from 'three';
import { CAMERA } from '../constants.js';
import { clamp, dampFactor } from '../utils/math.js';

const _desired = new THREE.Vector3();
const _lookAt = new THREE.Vector3();

export class ThirdPersonCamera {
  constructor(camera, input) {
    this.camera = camera;
    this.input = input;
    this.yaw = 0;
    this.pitch = 0.34;
    this.distance = CAMERA.distance;
    this._initialized = false;
  }

  reset(targetPos, yaw = 0) {
    this.yaw = yaw;
    this.pitch = 0.34;
    this.distance = CAMERA.distance;
    this._applyImmediate(targetPos);
    this._initialized = true;
  }

  _applyImmediate(targetPos) {
    const cp = Math.cos(this.pitch);
    this.camera.position.set(
      targetPos.x + Math.sin(this.yaw) * cp * this.distance,
      targetPos.y + Math.sin(this.pitch) * this.distance + CAMERA.height,
      targetPos.z + Math.cos(this.yaw) * cp * this.distance,
    );
    _lookAt.set(targetPos.x, targetPos.y + 1.55, targetPos.z);
    this.camera.lookAt(_lookAt);
  }

  update(dt, targetPos) {
    if (!this._initialized) this.reset(targetPos, this.yaw);

    // --- mouse / touch look ---
    const md = this.input.consumeMouseDelta();
    if (this.input.lookActive && this.input.enabled) {
      this.yaw -= md.x * CAMERA.sensitivity;
      this.pitch = clamp(this.pitch + md.y * CAMERA.sensitivity, CAMERA.pitchMin, CAMERA.pitchMax);
    }

    // --- ideal camera position on a sphere around the player ---
    const cp = Math.cos(this.pitch);
    _desired.set(
      targetPos.x + Math.sin(this.yaw) * cp * this.distance,
      targetPos.y + Math.sin(this.pitch) * this.distance + CAMERA.height,
      targetPos.z + Math.cos(this.yaw) * cp * this.distance,
    );

    // never clip below the ground
    _desired.y = Math.max(_desired.y, targetPos.y + 0.85);

    const k = dampFactor(CAMERA.positionSmoothing, dt);
    this.camera.position.lerp(_desired, k);

    _lookAt.set(targetPos.x, targetPos.y + 1.55, targetPos.z);
    this.camera.lookAt(_lookAt);
  }

  /** Compass bearing of the camera's forward direction, in degrees (0 = North = -Z). */
  headingDegrees() {
    const fx = -Math.sin(this.yaw);
    const fz = -Math.cos(this.yaw);
    return ((Math.atan2(fx, -fz) * 180) / Math.PI + 360) % 360;
  }
}
