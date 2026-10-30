/**
 * Engine.js — WebGL renderer bootstrap + the single main loop.
 *
 * Owns: renderer, scene container, clock, resize handling.
 * Game logic lives in Game.js; the engine just drives `update(dt)`.
 */
import * as THREE from 'three';
import { postFX } from './PostFX.js';
import { RoomEnvironment } from 'three/addons/environments/RoomEnvironment.js';
import { QUALITY, IS_MOBILE } from './quality.js';

export class Engine {
  /**
   * @param {HTMLElement} mountEl container for the <canvas>
   * @param {{update: (dt:number, elapsed:number)=>void}} game
   */
  constructor(mountEl, game) {
    this.mountEl = mountEl;
    this.game = game;

    this.renderer = new THREE.WebGLRenderer({
      antialias: QUALITY.antialias,
      powerPreference: 'high-performance',
      stencil: false,
    });
    // Cap pixel ratio: keeps 60 FPS on high-DPI laptops without visible loss.
    this.renderer.setPixelRatio(Math.min(window.devicePixelRatio, QUALITY.pixelRatio));
    this.renderer.setSize(window.innerWidth, window.innerHeight);
    this.renderer.outputColorSpace = THREE.SRGBColorSpace;
    // Khronos PBR Neutral: keeps saturated Miami pinks/cyans alive
    // (ACES greys out exactly the colors the Vice City grade lives on)
    this.renderer.toneMapping = THREE.NeutralToneMapping;
    this.renderer.toneMappingExposure = 1.12;
    this.renderer.shadowMap.enabled = true;
    this.renderer.shadowMap.type = THREE.PCFSoftShadowMap;
    this.renderer.domElement.id = 'game-canvas';
    mountEl.appendChild(this.renderer.domElement);

    this.scene = new THREE.Scene();

    // image-based fill light: soft studio env keeps skin, metal and cloth
    // from reading as plastic under pure Lambert shading
    const pmrem = new THREE.PMREMGenerator(this.renderer);
    this.scene.environment = pmrem.fromScene(new RoomEnvironment(), 0.06).texture;
    this.scene.environmentIntensity = 0.52;
    pmrem.dispose();

    this.camera = new THREE.PerspectiveCamera(
      62,
      window.innerWidth / window.innerHeight,
      0.1,
      600,
    );

    // Vice City-style post chain: bloom + retro-Miami grade
    postFX.init(this.renderer, this.scene, this.camera);

    // ---- adaptive resolution governor (phones are a lottery of GPUs)
    this._basePixelRatio = Math.min(window.devicePixelRatio, QUALITY.pixelRatio);
    this._perfSteps = IS_MOBILE ? [1, 0.82, 0.68, 0.56] : [1, 0.85];
    this._perfStep = 0;
    this._perfAccum = 0;
    this._perfFrames = 0;
    this._perfQuiet = 0;

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
    // FOV (portrait widening + sprint kick) is owned by ThirdPersonCamera
    this.camera.updateProjectionMatrix();
    this.renderer.setSize(w, h);
    postFX.setSize(w, h);
    if (this.onResize) this.onResize();
  }

  _loop() {
    this._raf = requestAnimationFrame(this._loop);

    // Clamp dt so tab-switches never produce physics explosions.
    const dt = Math.min(this.clock.getDelta(), 0.05);
    this.elapsed += dt;

    this.game.update(dt, this.elapsed);
    postFX.render(dt);
    this._adapt(dt);
  }

  /**
   * Keep the frame rate honest on unknown hardware.
   *
   * An APK is built for a device tier; a web build is opened on everything from
   * a flagship to a five-year-old budget phone. This governor watches the real
   * frame time and steps the render resolution down (never the world's content)
   * when the device cannot hold ~40 fps. It only ever steps *down*, and only
   * after two seconds of sustained trouble, so a loading hitch can never lock
   * a good device into a blurry build.
   */
  _adapt(dt) {
    if (dt <= 0) return;
    this._perfAccum += dt;
    this._perfFrames++;
    if (this._perfAccum < 2) return;

    const avg = this._perfAccum / this._perfFrames;
    this._perfAccum = 0;
    this._perfFrames = 0;

    const last = this._perfStep >= this._perfSteps.length - 1;
    if (avg > 1 / 40 && !last) {
      this._perfQuiet++;
      // two slow windows in a row before spending quality
      if (this._perfQuiet >= 2) {
        this._perfStep++;
        this._perfQuiet = 0;
        this._applyPixelRatio();
      }
    } else {
      this._perfQuiet = 0;
    }
  }

  _applyPixelRatio() {
    const scale = this._perfSteps[this._perfStep];
    const ratio = Math.max(0.6, this._basePixelRatio * scale);
    this.renderer.setPixelRatio(ratio);
    postFX.setPixelRatio(Math.min(ratio, IS_MOBILE ? 1.25 : 1.6));
    this._onResize();
  }
}
