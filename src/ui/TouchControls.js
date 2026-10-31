/**
 * TouchControls.js — on-screen controls for phones.
 *
 * Renders the dynamic joystick visuals (fed by Input.onJoystick) and two
 * action buttons that synthesize keyboard events into Input:
 *   • Use  → KeyE   (interact / advance dialogue)
 *   • Run  → ShiftLeft (toggled)
 *   • Low  → KeyC (toggled crouch)
 * Hidden entirely on desktop (body.touch-mode controls visibility).
 */
export class TouchControls {
  constructor(input) {
    this.input = input;

    this.root = document.getElementById('touch-ui');
    this.joyBase = document.getElementById('joy-base');
    this.joyKnob = document.getElementById('joy-knob');
    this.btnRun = document.getElementById('btn-run');
    this.btnUse = document.getElementById('btn-use');
    this.btnMap = document.getElementById('btn-map');
    this.btnCrouch = document.getElementById('btn-crouch');
    this._runOn = false;

    if (!this.root) return;

    // joystick visuals follow the finger
    input.onJoystick = (joy) => {
      if (joy.active) {
        this.joyBase.classList.add('active');
        this.joyBase.style.left = `${joy.ox}px`;
        this.joyBase.style.top = `${joy.oy}px`;
        const dx = Math.max(-52, Math.min(52, joy.x - joy.ox));
        const dy = Math.max(-52, Math.min(52, joy.y - joy.oy));
        this.joyKnob.style.transform = `translate(${dx}px, ${dy}px)`;
      } else {
        this.joyBase.classList.remove('active');
        this.joyKnob.style.transform = 'translate(0, 0)';
      }
    };

    // --- Use button: press = KeyE down + edge, release = up ---
    const useDown = (e) => {
      e.preventDefault();
      e.stopPropagation();
      this.input.pressKey('KeyE');
      this.btnUse.classList.add('held');
    };
    const useUp = (e) => {
      e.preventDefault();
      e.stopPropagation();
      this.input.releaseKey('KeyE');
      this.btnUse.classList.remove('held');
    };
    this.btnUse.addEventListener('pointerdown', useDown);
    this.btnUse.addEventListener('pointerup', useUp);
    this.btnUse.addEventListener('pointercancel', useUp);
    this.btnUse.addEventListener('pointerleave', useUp);

    // --- Run toggle ---
    this.btnRun.addEventListener('pointerdown', (e) => {
      e.preventDefault();
      e.stopPropagation();
      this._runOn = !this._runOn;
      if (this._runOn) this.input.pressKey('ShiftLeft');
      else this.input.releaseKey('ShiftLeft');
      this.btnRun.classList.toggle('on', this._runOn);
    });

    // --- Low button: toggles the crouch key (KeyC) ---
    if (this.btnCrouch) {
      let crouched = false;
      this.btnCrouch.addEventListener('pointerdown', (e) => {
        e.preventDefault();
        e.stopPropagation();
        crouched = !crouched;
        this.input.pressKey('KeyC');
        setTimeout(() => this.input.releaseKey('KeyC'), 0);
        this.btnCrouch.classList.toggle('on', crouched);
      });
    }

    // --- Map button: opens the pause/map screen (KeyM edge) ---
    if (this.btnMap) {
      this.btnMap.addEventListener('pointerdown', (e) => {
        e.preventDefault();
        e.stopPropagation();
        this.input.pressKey('KeyM');
        setTimeout(() => this.input.releaseKey('KeyM'), 0);
      });
    }
  }
}
