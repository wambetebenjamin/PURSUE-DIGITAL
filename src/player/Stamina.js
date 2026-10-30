/**
 * Stamina.js — run-stamina resource.
 *
 * Walking keeps stamina stable. Running drains `drainPerSecond` units/sec.
 * At 0 the player is forced to walk until stamina regenerates past a
 * hysteresis threshold (so it doesn't flicker at the boundary).
 */
import { STAMINA } from '../constants.js';

export class Stamina {
  constructor() {
    this.reset();
    this._sinceDrain = 999;
  }

  reset() {
    this.value = STAMINA.max;
    this.exhausted = false;
  }

  /**
   * @param {number} dt
   * @param {boolean} wantsRun player is holding the run key
   * @param {boolean} isMoving player has meaningful velocity
   * @returns {boolean} true if the player is allowed to run this frame
   */
  update(dt, wantsRun, isMoving) {
    const canRun = wantsRun && isMoving && !this.exhausted && this.value > 0;

    if (canRun) {
      this.value = Math.max(0, this.value - STAMINA.drainPerSecond * dt);
      this._sinceDrain = 0;
      if (this.value <= 0) this.exhausted = true;
    } else {
      this._sinceDrain += dt;
      if (this._sinceDrain > STAMINA.regenDelay) {
        this.value = Math.min(STAMINA.max, this.value + STAMINA.regenPerSecond * dt * (this.regenScale || 1));
      }
      if (this.exhausted && this.value >= STAMINA.exhaustedResetAt) {
        this.exhausted = false;
      }
    }

    return canRun;
  }

  get ratio() {
    return this.value / STAMINA.max;
  }
}
