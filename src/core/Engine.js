/**
 * Engine.js — WebGL renderer bootstrap + the single main loop.
 *
 * Owns: renderer, scene container, clock, resize handling.
 * Game logic lives in Game.js; the engine just drives `update(dt)`.
 */
import * as THREE from 'three';

export class Engine {
  /**
   * @param {HTMLElement} mountEl container for the <canvas>
   * @param {{update: (dt:number, elapsed:number)=>void}} game
   */
  constructor(mountEl, game) {
    this.mountEl = mountEl;
    this.game = game;

    this.renderer = new THREE.WebGLRenderer({
      antialias: true,
      powerPreference: 'high-performance',
      stencil: false,
    });
    // Cap pixel ratio: keeps 60 FPS on high-DPI laptops without visible loss.
    this.renderer.setPixelRatio(Math.min(window.devicePixelRatio, 1.75));
    this.renderer.setSize(window.innerWidth, window.innerHeight);
    this.renderer.outputColorSpace = THREE.SRGBColorSpace;
    this.renderer.toneMapping = THREE.ACESFilmicToneMapping;
    this.renderer.toneMappingExposure = 1.12;
    this.renderer.shadowMap.enabled = true;
    this.renderer.shadowMap.type = THREE.PCFSoftShadowMap;
    this.renderer.domElement.id = 'game-canvas';
    mountEl.appendChild(this.renderer.domElement);

    this.scene = new THREE.Scene();
    this.camera = new THREE.PerspectiveCamera(
      62,
      window.innerWidth / window.innerHeight,
      0.1,
      600,
    );

    this.clock = new THREE.Clock();
    this.elapsed = 0;
    this._raf = 0;
    this._onResize = this._onResize.bind(this);
    this._loop = this._loop.bind(this);
    window.addEventListener('resize', this._onResize);
  }

  get canvas() {
    return this.renderer.domElement;
  }

  start() {
    this.clock.start();
    this._raf = requestAnimationFrame(this._loop);
  }

  stop() {
    cancelAnimationFrame(this._raf);
  }

  _onResize() {
    const w = window.innerWidth;
    const h = window.innerHeight;
    this.camera.aspect = w / h;
    this.camera.updateProjectionMatrix();
    this.renderer.setSize(w, h);
  }

  _loop() {
    this._raf = requestAnimationFrame(this._loop);

    // Clamp dt so tab-switches never produce physics explosions.
    const dt = Math.min(this.clock.getDelta(), 0.05);
    this.elapsed += dt;

    this.game.update(dt, this.elapsed);
    this.renderer.render(this.scene, this.camera);
  }
}
