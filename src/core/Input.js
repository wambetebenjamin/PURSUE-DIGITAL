/**
 * Input.js — keyboard + Pointer Lock mouse input.
 *
 * The mouse only drives the camera while the pointer is locked to the canvas.
 * `consumeMouseDelta()` returns and clears the accumulated look delta once per
 * frame so the camera reads it exactly once.
 */
export class Input {
  constructor() {
    this.keys = new Set();
    this.pressed = new Set(); // edge-triggered, cleared every frame
    this.mouseDX = 0;
    this.mouseDY = 0;
    this.pointerLocked = false;
    this.enabled = true; // disabled while modals are open
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
      if (this.enabled && !this.pointerLocked) this.requestLock();
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
  }

  requestLock() {
    if (this._canvas && !this.pointerLocked) {
      const p = this._canvas.requestPointerLock?.();
      if (p && typeof p.catch === 'function') p.catch(() => {});
    }
  }

  exitLock() {
    if (document.pointerLockElement) document.exitPointerLock();
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

const GAME_KEYS = new Set([
  'KeyW', 'KeyA', 'KeyS', 'KeyD',
  'ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight',
  'Space', 'ShiftLeft', 'ShiftRight', 'KeyE',
]);
