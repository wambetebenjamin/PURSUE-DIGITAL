/**
 * Locomotion.js — the player's animation state machine.
 *
 * Modelled on the parameter/state design of a Unity third-person animator
 * (EasyStart Third Person Controller's `PlayerAnimator.controller`): the
 * controller never plays clips directly — it publishes a handful of booleans
 * and floats (`run`, `sprint`, `crouch`, `air`, plus the controller velocity)
 * and lets a blend tree resolve the pose. We mirror that idea exactly, but
 * resolve to *procedural* pose weights instead of animation clips, because the
 * rig is built from merged primitives with no skinned mesh:
 *
 *   params   →  blend weights  →  stride amplitude / cadence / lean / hip drop
 *
 * The result is a continuous idle → walk → run → sprint → crouch-walk
 * progression with no snapping, and a `state` field that other systems (audio,
 * HUD, stealth) can read.
 */

const STATES = Object.freeze({
  IDLE: 'idle',
  WALK: 'walk',
  RUN: 'run',
  SPRINT: 'sprint',
  CROUCH_IDLE: 'crouchIdle',
  CROUCH_WALK: 'crouchWalk',
  AIR: 'air',
});

/** Target pose per state: stride amplitude, cadence, torso lean, hip drop. */
const POSES = Object.freeze({
  [STATES.IDLE]: { stride: 0, cadence: 0, lean: 0, drop: 0, armBias: 0 },
  [STATES.WALK]: { stride: 0.52, cadence: 7.4, lean: 0.03, drop: 0, armBias: 0 },
  [STATES.RUN]: { stride: 0.86, cadence: 12.4, lean: 0.07, drop: 0, armBias: 0 },
  [STATES.SPRINT]: { stride: 1.02, cadence: 14.6, lean: 0.13, drop: 0.02, armBias: 0.08 },
  [STATES.CROUCH_IDLE]: { stride: 0, cadence: 0, lean: 0.2, drop: 0.3, armBias: 0.1 },
  [STATES.CROUCH_WALK]: { stride: 0.36, cadence: 5.6, lean: 0.24, drop: 0.3, armBias: 0.1 },
  [STATES.AIR]: { stride: 0.2, cadence: 3, lean: 0.1, drop: 0.06, armBias: 0 },
});

export class Locomotion {
  constructor() {
    this.params = { run: false, sprint: false, crouch: false, air: false, speed: 0, speed01: 0 };
    this.state = STATES.IDLE;
    this.pose = { ...POSES[STATES.IDLE] };
    this.phase = 0;      // gait phase in radians
    this.lastStrideSign = 0;
    this.strideEvents = 0; // incremented on each foot plant (audio hook)
  }

  /**
   * Feed the controller one frame.
   * @param {number} dt
   * @param {{speed:number, walkSpeed:number, runSpeed:number, sprinting:boolean,
   *          crouched:boolean, grounded?:boolean}} input
   */
  update(dt, { speed, walkSpeed, runSpeed, sprinting, crouched, grounded = true }) {
    // --- publish the animator parameters -----------------------------------
    const p = this.params;
    p.speed = speed;
    p.run = speed > walkSpeed * 0.16;
    p.sprint = sprinting && p.run;
    p.crouch = Boolean(crouched);
    p.air = !grounded;
    const top = p.crouch ? walkSpeed * 0.55 : runSpeed;
    p.speed01 = Math.min(1, speed / Math.max(0.001, top));

    // --- state selection (first match wins, like an animator's transitions) --
    if (p.air) this.state = STATES.AIR;
    else if (p.crouch) this.state = p.run ? STATES.CROUCH_WALK : STATES.CROUCH_IDLE;
    else if (p.sprint) this.state = STATES.SPRINT;
    else if (p.run) {
      // walk and run share the locomotion blend: pick by actual speed
      this.state = speed > walkSpeed * 0.92 ? STATES.RUN : STATES.WALK;
    } else this.state = STATES.IDLE;

    // --- blend toward the state's pose (critically damped, frame-rate free) --
    const target = POSES[this.state];
    const k = Math.min(1, dt * (this.state === STATES.IDLE ? 6 : 9));
    const pose = this.pose;
    pose.stride += (target.stride - pose.stride) * k;
    pose.cadence += (target.cadence - pose.cadence) * k;
    pose.lean += (target.lean - pose.lean) * k;
    pose.drop += (target.drop - pose.drop) * k;
    pose.armBias += (target.armBias - pose.armBias) * k;

    // --- advance the gait phase by cadence × speed --------------------------
    if (pose.stride > 0.01 && p.speed01 > 0.02) {
      this.phase += dt * pose.cadence * (0.45 + p.speed01 * 0.55);
      const swing = Math.sin(this.phase);
      const sign = swing > 0.05 ? 1 : swing < -0.05 ? -1 : 0;
      if (sign !== 0 && sign !== this.lastStrideSign) this.strideEvents++;
      this.lastStrideSign = sign;
    } else {
      // settle the swing back to neutral when standing still
      this.phase += dt * 0.6;
      this.lastStrideSign = 0;
    }

    return this.pose;
  }

  /** Current swing in −1..1 (sine of the gait phase). */
  get swing() {
    return Math.sin(this.phase);
  }
}

export { STATES as LOCOMOTION_STATES };
