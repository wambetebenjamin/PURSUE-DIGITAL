/**
 * Input.js — keyboard + Pointer Lock mouse input, plus a mobile touch layer.
 *
 * The mouse only drives the camera while the pointer is locked to the canvas.
 * `consumeMouseDelta()` returns and clears the accumulated look delta once per
 * frame so the camera reads it exactly once.
 *
 * TOUCH: touches starting in the left 46% of the screen drive a dynamic
 * virtual joystick (`moveAxis`, normalized −1..1); touches elsewhere drag the
 * camera look. DOM buttons (see ui/TouchControls.js) synthesize KeyE /
 * ShiftLeft presses so every gameplay path is shared with desktop.
 */
export class Input {
  constructor() {
    this.keys = new Set();
    this.pressed = new Set(); // edge-triggered, cleared every frame
    this.mouseDX = 0;
    this.mouseDY = 0;
    this.pointerLocked = false;
    this.enabled = true; // disabled while modals are open

    // --- touch state ---
    this.touchMode = false;
    this.moveAxis = { x: 0, y: 0 };      // −1..1 joystick vector
    this.joystick = { active: false, ox: 0, oy: 0, x: 0, y: 0 }; // for visuals
    this.touchLookActive = false;
    this.onJoystick = null; // (joystick) => void  — visual hook

    this._canvas = null;
    this._onLockChange = null;
    this.onPointerLockChange = null;
  }

  attach(canvas) {
    this._canvas = canvas;

    window.addEventListener('keydown', (e) => {
      // Keep the page from scrolling on game keys.
      if (GAME_KEYS.has(e.code)) e.preventDefault();
      if (!this.keys.has(e.code)) this.pressed.add(e.code);
      this.keys.add(e.code);
    });

    window.addEventListener('keyup', (e) => {
      this.keys.delete(e.code);
    });

    // Lose all held keys when the tab loses focus (prevents "stuck" movement).
    window.addEventListener('blur', () => {
      this.keys.clear();
      this.pressed.clear();
    });

    canvas.addEventListener('click', () => {
      if (this.enabled && !this.pointerLocked && !this.touchMode) this.requestLock();
    });

    this._onLockChange = () => {
      this.pointerLocked = document.pointerLockElement === canvas;
      if (!this.pointerLocked) {
        this.mouseDX = 0;
        this.mouseDY = 0;
      }
      if (this.onPointerLockChange) this.onPointerLockChange(this.pointerLocked);
    };
    document.addEventListener('pointerlockchange', this._onLockChange);

    document.addEventListener('mousemove', (e) => {
      if (!this.pointerLocked || !this.enabled) return;
      this.mouseDX += e.movementX;
      this.mouseDY += e.movementY;
    });

    this._attachTouch(canvas);
  }

  // ------------------------------------------------------------- touch
  _attachTouch(canvas) {
    this.touchMode = IS_TOUCH;
    if (!this.touchMode) return;

    // stop iOS rubber-band scroll / pinch-zoom during play
    document.addEventListener('touchmove', (e) => {
      if (e.target === canvas || e.target === document.body) e.preventDefault();
    }, { passive: false });

    const joyId = { current: null };
    const lookId = { current: null };
    const lookLast = { x: 0, y: 0 };

    const startJoy = (t) => {
      joyId.current = t.identifier;
      this.joystick = { active: true, ox: t.clientX, oy: t.clientY, x: t.clientX, y: t.clientY };
      this._emitJoystick();
    };
    const moveJoy = (t) => {
      this.joystick.x = t.clientX;
      this.joystick.y = t.clientY;
      const dx = t.clientX - this.joystick.ox;
      const dy = t.clientY - this.joystick.oy;
      const R = 64; // px to full deflection
      const len = Math.hypot(dx, dy) || 1;
      const clamped = Math.min(1, len / R);
      this.moveAxis.x = (dx / len) * clamped;
      this.moveAxis.y = (-dy / len) * clamped; // up on screen = forward
      this._emitJoystick();
    };
    const endJoy = () => {
      joyId.current = null;
      this.moveAxis.x = 0;
      this.moveAxis.y = 0;
      this.joystick = { active: false, ox: 0, oy: 0, x: 0, y: 0 };
      this._emitJoystick();
    };

    canvas.addEventListener('touchstart', (e) => {
      for (const t of e.changedTouches) {
        const isLeft = t.clientX < window.innerWidth * 0.46;
        if (isLeft && joyId.current === null) {
          startJoy(t);
        } else if (!isLeft && lookId.current === null) {
          lookId.current = t.identifier;
          lookLast.x = t.clientX;
          lookLast.y = t.clientY;
          this.touchLookActive = true;
        }
      }
      e.preventDefault();
    }, { passive: false });

    canvas.addEventListener('touchmove', (e) => {
      for (const t of e.changedTouches) {
        if (t.identifier === joyId.current) {
          moveJoy(t);
        } else if (t.identifier === lookId.current) {
          // raw pixel drag, tuned like mouse movement
          this.mouseDX += (t.clientX - lookLast.x) * 1.9;
          this.mouseDY += (t.clientY - lookLast.y) * 1.9;
          lookLast.x = t.clientX;
          lookLast.y = t.clientY;
        }
      }
      e.preventDefault();
    }, { passive: false });

    const finish = (e) => {
      for (const t of e.changedTouches) {
        if (t.identifier === joyId.current) endJoy();
        if (t.identifier === lookId.current) {
          lookId.current = null;
          this.touchLookActive = false;
        }
      }
    };
    canvas.addEventListener('touchend', finish);
    canvas.addEventListener('touchcancel', finish);
  }

  _emitJoystick() {
    if (this.onJoystick) this.onJoystick(this.joystick);
  }

  /** Synthesized key press/release from the on-screen buttons. */
  pressKey(code) {
    if (!this.keys.has(code)) this.pressed.add(code);
    this.keys.add(code);
  }

  releaseKey(code) {
    this.keys.delete(code);
  }

  // ---------------------------------------------------------- pointer
  requestLock() {
    if (this.touchMode) return;
    if (this._canvas && !this.pointerLocked) {
      const p = this._canvas.requestPointerLock?.();
      if (p && typeof p.catch === 'function') p.catch(() => {});
    }
  }

  exitLock() {
    if (document.pointerLockElement) document.exitPointerLock();
  }

  /** True while the camera may consume look deltas (locked mouse OR touch drag). */
  get lookActive() {
    return this.pointerLocked || this.touchLookActive;
  }

  isDown(codes) {
    if (!this.enabled) return false;
    const list = Array.isArray(codes) ? codes : [codes];
    return list.some((c) => this.keys.has(c));
  }

  wasPressed(codes) {
    const list = Array.isArray(codes) ? codes : [codes];
    return list.some((c) => this.pressed.has(c));
  }

  consumeMouseDelta() {
    const d = { x: this.mouseDX, y: this.mouseDY };
    this.mouseDX = 0;
    this.mouseDY = 0;
    return d;
  }

  /** Clear edge-triggered state. Call once at the very end of each frame. */
  endFrame() {
    this.pressed.clear();
  }
}

const IS_TOUCH = typeof window !== 'undefined'
  && ((typeof navigator !== 'undefined' && navigator.maxTouchPoints > 0) || 'ontouchstart' in window);

const GAME_KEYS = new Set([
  'KeyW', 'KeyA', 'KeyS', 'KeyD',
  'ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight',
  'Space', 'ShiftLeft', 'ShiftRight', 'KeyE',
]);
