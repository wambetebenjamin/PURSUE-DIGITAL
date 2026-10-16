/**
 * HUD.js — persistent HTML/CSS overlay: objective, stamina meter, compass,
 * interaction prompt, and transient alert banners.
 */
import { clamp, compassLabel } from '../utils/math.js';
import { IS_TOUCH } from '../core/quality.js';

export class HUD {
  constructor() {
    this.objectiveText = document.getElementById('objective-text');
    this.staminaFill = document.getElementById('stamina-fill');
    this.compassText = document.getElementById('compass-text');
    this.prompt = document.getElementById('prompt');
    this.promptText = document.getElementById('prompt-text');
    this.alertBanner = document.getElementById('alert-banner');
    this.alertText = document.getElementById('alert-text');
    this.hud = document.getElementById('hud');

    this._alertTimer = 0;
    this._promptVisible = false;
    this._staminaWarning = false;
  }

  setObjective(text) {
    this.objectiveText.textContent = text;
    // brief highlight so the player notices the change
    this.objectiveText.classList.remove('pulse');
    void this.objectiveText.offsetWidth; // restart CSS animation
    this.objectiveText.classList.add('pulse');
  }

  setStamina(ratio, exhausted) {
    const pct = clamp(ratio, 0, 1) * 100;
    this.staminaFill.style.width = `${pct}%`;
    if (exhausted !== this._staminaWarning) {
      this._staminaWarning = exhausted;
      this.staminaFill.classList.toggle('exhausted', exhausted);
    }
  }

  setCompass(headingDegrees) {
    const deg = Math.round(headingDegrees) % 360;
    this.compassText.textContent = `${compassLabel(deg)} ${deg}°`;
  }

  showPrompt(text) {
    const display = IS_TOUCH ? text.replace('Press E', 'Tap Use') : text;
    if (!this._promptVisible || this.promptText.textContent !== display) {
      this.promptText.textContent = display;
      this.prompt.classList.remove('hidden');
      this._promptVisible = true;
    }
  }

  hidePrompt() {
    if (this._promptVisible) {
      this.prompt.classList.add('hidden');
      this._promptVisible = false;
    }
  }

  showAlert(text, duration = 2.4) {
    this.alertText.textContent = text;
    this.alertBanner.classList.remove('hidden');
    this.alertBanner.classList.remove('pop');
    void this.alertBanner.offsetWidth;
    this.alertBanner.classList.add('pop');
    this._alertTimer = duration;
  }

  setVisible(visible) {
    this.hud.classList.toggle('hidden', !visible);
  }

  update(dt) {
    if (this._alertTimer > 0) {
      this._alertTimer -= dt;
      if (this._alertTimer <= 0) this.alertBanner.classList.add('hidden');
    }
  }
}
