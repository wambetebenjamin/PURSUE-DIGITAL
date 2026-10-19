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
    this._sprint01 = 0; // damped sprint blend for FOV / distance push
    this._initialized = false;

    // --- physical-camera feel
    this._t = 0;
    this._shake = 0;      // decaying combat/impact trauma
    this._shakeTime = 0;
    this.focusDistance = CAMERA.distance;
    this.handheld = 1;    // 0 = locked-off, 1 = organic handheld
  }

  /** Kick the camera — impacts, detection, the rout. Trauma decays fast. */
  shake(strength = 0.4) {
    this._shake = Math.min(1, this._shake + strength);
    this._shakeTime = 0;
  }

  reset(targetPos, yaw = 0) {
    this.yaw = yaw;
    this.pitch = 0.34;
    this.distance = CAMERA.distance;
    this._sprint01 = 0;
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

  update(dt, targetPos, sprinting = false) {
    if (!this._initialized) this.reset(targetPos, this.yaw);

    // --- mouse / touch look ---
    const md = this.input.consumeMouseDelta();
    if (this.input.lookActive && this.input.enabled) {
      this.yaw -= md.x * CAMERA.sensitivity;
      this.pitch = clamp(this.pitch + md.y * CAMERA.sensitivity, CAMERA.pitchMin, CAMERA.pitchMax);
    }

    // --- sprint feel (CCamera speed zoom analog): FOV kick + distance push ---
    this._sprint01 += ((sprinting ? 1 : 0) - this._sprint01) * Math.min(1, dt * 4);
    const aspect = this.camera.aspect || 1.6;
    const baseFov = aspect < 1 ? CAMERA.portraitFov : CAMERA.fov;
    const targetFov = baseFov + this._sprint01 * CAMERA.sprintFovKick;
    if (Math.abs(this.camera.fov - targetFov) > 0.02) {
      this.camera.fov += (targetFov - this.camera.fov) * Math.min(1, dt * 5);
      this.camera.updateProjectionMatrix();
    }
    this.distance = CAMERA.distance + this._sprint01 * CAMERA.sprintPush;

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

    // ---------------- handheld organic motion
    // A physical operator never holds still: breathing sway plus a slower
    // weight shift, both amplified while sprinting. Layered sines read as
    // hand-held rather than as a mechanical sine wave.
    this._t += dt;
    const t = this._t;
    const amp = this.handheld * (0.0016 + this._sprint01 * 0.006);
    const sway = Math.sin(t * 0.73) * 0.6 + Math.sin(t * 1.61 + 1.3) * 0.4;
    const bob = Math.sin(t * 1.13 + 0.7) * 0.6 + Math.sin(t * 2.37) * 0.4;
    this.camera.rotateY(sway * amp);
    this.camera.rotateX(bob * amp * 0.8);
    this.camera.rotateZ(Math.sin(t * 0.51 + 2.1) * amp * 0.6);

    // ---------------- trauma shake (decays over ~0.7s)
    if (this._shake > 0.001) {
      this._shakeTime += dt;
      this._shake = Math.max(0, this._shake - dt * 1.5);
      const s = this._shake * this._shake;
      const f = this._shakeTime * 34;
      this.camera.rotateX(Math.sin(f * 1.7) * s * 0.02);
      this.camera.rotateY(Math.cos(f * 1.3) * s * 0.018);
      this.camera.rotateZ(Math.sin(f * 2.1 + 1) * s * 0.012);
    }

    // focus target for the depth-of-field pass
    this.focusDistance = this.camera.position.distanceTo(_lookAt);
  }

  /** Compass bearing of the camera's forward direction, in degrees (0 = North = -Z). */
  headingDegrees() {
    const fx = -Math.sin(this.yaw);
    const fz = -Math.cos(this.yaw);
    return ((Math.atan2(fx, -fz) * 180) / Math.PI + 360) % 360;
  }
}
