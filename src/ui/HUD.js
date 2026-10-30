/**
 * HUD.js — persistent HTML/CSS overlay: objective, stamina meter, compass,
 * interaction prompt, transient alert banners, plus the Vice City interface
 * layer: district banner (CHud::SetZoneName), mission timer and the
 * pursuit stars (the wanted-stars analog of the Amalekite raid).
 */
import { clamp, compassLabel } from '../utils/math.js';
import { IS_TOUCH } from '../core/quality.js';

export class HUD {
  constructor() {
    this.objectiveText = document.getElementById('objective-text');
    this.staminaFill = document.getElementById('stamina-fill');
    this.compassText = document.getElementById('compass-text');
    this.compassNeedle = document.getElementById('compass-needle');
    this.lamp = document.getElementById('lamp-wrap');
    this.flame = document.getElementById('lamp-flame');
    this.stanceChip = document.getElementById('stance-chip');
    this.prompt = document.getElementById('prompt');
    this.promptText = document.getElementById('prompt-text');
    this.alertBanner = document.getElementById('alert-banner');
    this.alertText = document.getElementById('alert-text');
    this.hud = document.getElementById('hud');

    // --- Vice City interface layer ---
    this.zoneBanner = document.getElementById('zone-banner');
    this.zoneName = document.getElementById('zone-name');
    this.zoneTag = document.getElementById('zone-tag');
    this.timerText = document.getElementById('hud-timer');
    this.pursuitStars = document.getElementById('pursuit-stars');

    this._alertTimer = 0;
    this._promptVisible = false;
    this._staminaWarning = false;
    this._health = 1;
    this._crouched = false;
    this._zoneTimer = 0;
    this._zoneFade = 0;
    this._pursuit = -1;
  }

  /** District banner — GTA's zone name card with its fade in / hold / fade out. */
  showZone(name, tagline = '') {
    if (this.zoneName) this.zoneName.textContent = name;
    if (this.zoneTag) this.zoneTag.textContent = tagline;
    if (this.zoneBanner) this.zoneBanner.classList.remove('hidden');
    this._zoneTimer = 3.6;
    this._zoneFade = 1;
    if (this.zoneBanner) this.zoneBanner.style.opacity = '1';
  }

  /** Mission timer, mm:ss — the GTA mission-clock analog. */
  setTimer(seconds) {
    if (!this.timerText) return;
    const s = Math.max(0, Math.floor(seconds));
    const mm = Math.floor(s / 60);
    const ss = String(s % 60).padStart(2, '0');
    this.timerText.textContent = `${mm}:${ss}`;
  }

  /** Pursuit stars 0–5 — the wanted-level analog (the raiders are hunting). */
  setPursuit(level) {
    const n = clamp(Math.round(level), 0, 5);
    if (n === this._pursuit || !this.pursuitStars) return;
    this._pursuit = n;
    const stars = this.pursuitStars.children;
    for (let i = 0; i < stars.length; i++) {
      stars[i].classList.toggle('lit', i < n);
    }
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

  /** Stance — the sneak chip appears only while hunched. */
  setStance(crouched) {
    if (crouched === this._crouched) return;
    this._crouched = crouched;
    if (this.stanceChip && this.stanceChip.classList) {
      this.stanceChip.classList.toggle('hidden', !crouched);
    }
    if (this.hud && this.hud.classList) this.hud.classList.toggle('sneaking', crouched);
  }

  setCompass(headingDegrees) {
    const deg = Math.round(headingDegrees) % 360;
    this.compassText.textContent = `${compassLabel(deg)} ${deg}°`;
    // the astrolabe needle swings under the fixed ring
    if (this.compassNeedle && this.compassNeedle.style) {
      this.compassNeedle.style.transform = `rotate(${-deg}deg)`;
    }
  }

  /**
   * Vitality — the oil in the lamp. The flame shrinks and gutters as health
   * falls; at a quarter it turns from gold to a smoky red.
   */
  setHealth(ratio) {
    const r = clamp(ratio, 0, 1);
    if (Math.abs(r - this._health) < 0.004) return;
    this._health = r;
    if (this.flame && this.flame.style) {
      this.flame.style.transform = `scale(${0.5 + r * 0.5}, ${0.42 + r * 0.58})`;
      this.flame.style.opacity = String(0.35 + r * 0.65);
      this.flame.classList.toggle('low', r < 0.3);
    }
    if (this.lamp && this.lamp.style) {
      this.lamp.classList.toggle('guttering', r < 0.3);
      if (this.lamp.style.setProperty) this.lamp.style.setProperty('--oil', String(r));
    }
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
    if (this._zoneTimer > 0) {
      this._zoneTimer -= dt;
      if (this._zoneTimer <= 1) {
        this._zoneFade = Math.max(0, this._zoneTimer);
        if (this.zoneBanner) this.zoneBanner.style.opacity = String(this._zoneFade);
      }
      if (this._zoneTimer <= 0 && this.zoneBanner) this.zoneBanner.classList.add('hidden');
    }
  }
}
