/**
 * Player.js — David: third-person character rig, movement & stamina.
 *
 * The rig is a tiny low-poly humanoid built from shared primitives:
 * box torso, sphere head, pivot-grouped arms/legs that swing while walking.
 * No skinned meshes — transform animation only, so it costs almost nothing.
 */
import * as THREE from 'three';
import { Mat, Geo } from '../utils/materials.js';
import { CONTROLS, PLAYER } from '../constants.js';
import { Stamina } from './Stamina.js';
import { dampFactor, lerpAngle } from '../utils/math.js';
import { createDetailedHuman } from '../entities/CharacterRig.js';
import { audio } from '../audio/AudioEngine.js';

const _dir = new THREE.Vector3();
const _forward = new THREE.Vector3();
const _right = new THREE.Vector3();
const _push = new THREE.Vector3();

export class Player {
  constructor(input) {
    this.input = input;
    this.stamina = new Stamina();

    this.velocity = new THREE.Vector3();
    this.groundHeight = 0; // set by the active zone each frame
    this.health = 1; // vitality — the oil in the lamp (HUD), regenerates slowly
    this.running = false;
    this.moving = false;
    this.interactRequested = false;

    this.group = new THREE.Group();
    this._buildRig();
  }

  // ---------------------------------------------------------------- rig
  _buildRig() {
    // David: hardened Levantine warrior — sun-bronzed, bearded, lamellar
    // leather over a faded earth-tone tunic, red sash, sandals caked in dust.
    const rig = createDetailedHuman({
      palette: {
        skin: Mat.skinPbr(),
        cloth: Mat.clothPbr(),
        cloth2: Mat.leatherPbrWarm(),
        hair: Mat.hairPbr(),
        hairCard: Mat.hairCard(),
        leather: Mat.leatherPbr(),
        metal: Mat.bronzePbr(),
        accent: Mat.clothPbrMadder(),
      },
      pose: 'standing',
      beard: 'full',
      hairStyle: 'short',
      armor: 'leather',
      detail: 'full',
      seed: 7,
    });
    this.rig = rig;
    this.group.add(rig);

    const parts = rig.userData.parts;
    this.armL = parts.armL;
    this.armR = parts.armR;
    this.legL = parts.legL;
    this.legR = parts.legR;
    this.head = parts.head;
    this.torso = parts.torso;

    // shepherd's bag at the hip
    const bag = new THREE.Mesh(Geo.box(), Mat.sashDavid());
    bag.scale.set(0.22, 0.3, 0.14);
    bag.position.set(-0.36, 1.0, -0.24);
    bag.rotation.z = 0.18;
    bag.castShadow = true;

    // the king's sword at his thigh
    const scabbard = new THREE.Mesh(Geo.cyl(), Mat.leatherDark());
    scabbard.scale.set(0.075, 0.85, 0.14);
    scabbard.position.set(0.34, 0.72, -0.1);
    scabbard.rotation.x = 0.28;
    scabbard.rotation.z = -0.12;
    scabbard.castShadow = true;
    const hilt = new THREE.Mesh(Geo.box(), Mat.brass());
    hilt.scale.set(0.05, 0.22, 0.05);
    hilt.position.set(0.38, 1.18, -0.14);
    hilt.rotation.x = 0.28;
    hilt.castShadow = true;

    this.group.add(bag, scabbard, hilt);
    this.group.traverse((o) => {
      if (o.isMesh) {
        o.castShadow = true;
        o.receiveShadow = false;
      }
    });
  }

  // ------------------------------------------------------------- state
  spawn(position, yaw = 0) {
    this.group.position.copy(position);
    this.group.rotation.y = yaw;
    this.velocity.set(0, 0, 0);
    this.stamina.reset();
    this.health = 1;
    this.running = false;
    this.moving = false;
    this._gait = 0;
    this._armIdle = 0;
  }

  get position() {
    return this.group.position;
  }

  // ----------------------------------------------------------- update
  /**
   * @param {number} dt
   * @param {number} cameraYaw yaw of the orbit camera (drives move space)
   * @param {number} elapsed
   * @param {{minX:number,maxX:number,minZ:number,maxZ:number}} bounds
   * @param {{x:number,z:number,r:number}[]} colliders
   */
  update(dt, cameraYaw, elapsed, bounds, colliders) {
    // --- movement input in camera space (keyboard or touch joystick) ---
    let ix = 0;
    let iz = 0;
    const joy = this.input.moveAxis;
    const joyMagnitude = joy ? Math.hypot(joy.x, joy.y) : 0;
    if (joyMagnitude > 0.08) {
      ix = joy.x;
      iz = joy.y;
    } else {
      if (this.input.isDown(CONTROLS.forward)) iz += 1;
      if (this.input.isDown(CONTROLS.back)) iz -= 1;
      if (this.input.isDown(CONTROLS.right)) ix += 1;
      if (this.input.isDown(CONTROLS.left)) ix -= 1;
    }

    _forward.set(-Math.sin(cameraYaw), 0, -Math.cos(cameraYaw));
    _right.set(Math.cos(cameraYaw), 0, -Math.sin(cameraYaw));

    _dir.set(0, 0, 0)
      .addScaledVector(_forward, iz)
      .addScaledVector(_right, ix);

    const hasInput = _dir.lengthSq() > 0.0001;
    const analogScale = Math.min(1, joyMagnitude > 0.08 ? joyMagnitude : 1);
    if (hasInput) _dir.normalize();

    // --- stamina gate ---
    const wantsRun = this.input.isDown(CONTROLS.run);
    this.running = this.stamina.update(dt, wantsRun, hasInput);
    const targetSpeed = (this.running ? PLAYER.runSpeed : PLAYER.walkSpeed) * analogScale;

    // --- accelerate / friction ---
    const targetVX = _dir.x * targetSpeed;
    const targetVZ = _dir.z * targetSpeed;
    const accel = hasInput ? PLAYER.acceleration : PLAYER.friction;
    const k = dampFactor(accel, dt);
    this.velocity.x += (targetVX - this.velocity.x) * k;
    this.velocity.z += (targetVZ - this.velocity.z) * k;

    // --- integrate ---
    const pos = this.group.position;
    pos.x += this.velocity.x * dt;
    pos.z += this.velocity.z * dt;

    // --- collide with world props (circular push-out) ---
    const r = PLAYER.radius;
    for (let i = 0; i < colliders.length; i++) {
      const c = colliders[i];
      const dx = pos.x - c.x;
      const dz = pos.z - c.z;
      const min = c.r + r;
      const d2 = dx * dx + dz * dz;
      if (d2 < min * min && d2 > 1e-6) {
        const d = Math.sqrt(d2);
        const overlap = min - d;
        pos.x += (dx / d) * overlap;
        pos.z += (dz / d) * overlap;
      }
    }

    // --- clamp to zone bounds ---
    pos.x = Math.max(bounds.minX, Math.min(bounds.maxX, pos.x));
    pos.z = Math.max(bounds.minZ, Math.min(bounds.maxZ, pos.z));

    // --- terrain follow ---
    pos.y = this.groundHeight;

    // --- facing ---
    const speed2 = this.velocity.x * this.velocity.x + this.velocity.z * this.velocity.z;
    this.moving = speed2 > 0.4;
    if (this.moving) {
      const targetYaw = Math.atan2(this.velocity.x, this.velocity.z);
      this.group.rotation.y = lerpAngle(
        this.group.rotation.y,
        targetYaw,
        dampFactor(PLAYER.turnSmoothing, dt),
      );
    }

    // --- gait animation ---
    const speed = Math.sqrt(speed2);
    const gaitSpeed = this.running ? 12.5 : 7.2;
    if (this.moving) this._gait += dt * gaitSpeed * Math.min(1, speed / PLAYER.walkSpeed);
    const swing = Math.sin(this._gait) * (this.running ? 0.85 : 0.55) * (this.moving ? 1 : 0);
    this.legL.rotation.x = swing;
    this.legR.rotation.x = -swing;
    this.armL.rotation.x = -swing * 0.8;
    this.armR.rotation.x = swing * 0.6 - 0.35;

    // footsteps: one per stride plant
    const sign = swing > 0.05 ? 1 : swing < -0.05 ? -1 : 0;
    if (sign !== 0 && sign !== this._lastStrideSign && this.moving) {
      audio.step(this.running);
    }
    this._lastStrideSign = sign;

    // subtle idle breathing (chest rises, shoulders follow)
    this._armIdle = (this._armIdle ?? 0) + dt;
    const breathe = Math.sin(this._armIdle * 1.6) * 0.035;
    this.torso.position.y = 1.14 + Math.sin(this._armIdle * 1.5) * 0.014;
    if (!this.moving) {
      this.armL.rotation.z = 0.08 + breathe;
      this.armR.rotation.z = -0.08 - breathe;
    } else {
      this.armL.rotation.z = 0.08;
      this.armR.rotation.z = -0.08;
    }

    // --- interact edge ---
    this.interactRequested = this.input.wasPressed(CONTROLS.interact);
  }

  /** Facing direction of the character as a normalized vector. */
  facing(out = _push) {
    const a = this.group.rotation.y;
    return out.set(Math.sin(a), 0, Math.cos(a));
  }
}

// ------------------------------------------------------- rig factories
function pivotLimb(w, len, matTop, matBottom, xOffset) {
  const pivot = new THREE.Group();
  pivot.position.set(xOffset, 0.82, 0);

  const thigh = new THREE.Mesh(Geo.box(), matTop);
  thigh.scale.set(w * 1.6, len * 0.55, w * 1.7);
  thigh.position.y = -len * 0.28;
  thigh.castShadow = true;

  const shin = new THREE.Mesh(Geo.box(), matBottom);
  shin.scale.set(w * 1.35, len * 0.5, w * 1.45);
  shin.position.y = -len * 0.72;
  shin.castShadow = true;

  const foot = new THREE.Mesh(Geo.box(), Mat.hair());
  foot.scale.set(w * 1.5, 0.1, 0.32);
  foot.position.set(0, -len * 0.98, 0.08);
  foot.castShadow = true;

  pivot.add(thigh, shin, foot);
  return pivot;
}

function pivotArm(sleeveMat, skinMat, xOffset) {
  const pivot = new THREE.Group();
  pivot.position.set(xOffset, 1.42, 0);

  const sleeve = new THREE.Mesh(Geo.box(), sleeveMat);
  sleeve.scale.set(0.17, 0.34, 0.19);
  sleeve.position.y = -0.16;
  sleeve.castShadow = true;

  const forearm = new THREE.Mesh(Geo.box(), skinMat);
  forearm.scale.set(0.14, 0.36, 0.16);
  forearm.position.y = -0.48;
  forearm.castShadow = true;

  const hand = new THREE.Mesh(Geo.sphere(), skinMat);
  hand.scale.setScalar(0.17);
  hand.position.y = -0.7;

  pivot.add(sleeve, forearm, hand);
  return pivot;
}
