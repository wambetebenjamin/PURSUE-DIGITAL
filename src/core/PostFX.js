/**
 * PostFX.js — the cinematic chain: depth of field → bloom → film grade.
 *
 * Grade pass additions over the previous Miami grade:
 *   • god rays  — screen-space radial scattering from the sun's projected
 *                 position, occluded by bright-pass so smoke columns carve
 *                 shadow shafts through the light.
 *   • heat haze — a slow refractive wobble in the lower third of the frame
 *                 (ground-level shimmer), stronger at noon.
 *   • motion blur — 5-tap smear along the camera's measured screen-space
 *                 flow, so sprinting and whip-pans drag like a real lens.
 *   • dust veil — warm suspended particulate tint that lifts with the haze.
 *
 * Depth of field uses three's BokehPass (disabled on phones, where the
 * depth prepass is too expensive). Focus tracks the player each frame.
 *
 * All methods no-op before `init()` (headless smoke runs fine).
 */
import * as THREE from 'three';
import { EffectComposer } from 'three/addons/postprocessing/EffectComposer.js';
import { RenderPass } from 'three/addons/postprocessing/RenderPass.js';
import { UnrealBloomPass } from 'three/addons/postprocessing/UnrealBloomPass.js';
import { ShaderPass } from 'three/addons/postprocessing/ShaderPass.js';
import { OutputPass } from 'three/addons/postprocessing/OutputPass.js';
import { BokehPass } from 'three/addons/postprocessing/BokehPass.js';
import { IS_MOBILE } from './quality.js';

/** Per-mood grade presets — named for their biblical moment. */
export const GRADES = Object.freeze({
  /** Ocean Drive sunset over burning Ziklag: magenta sky, violet shadows. */
  sunset: {
    saturation: 1.50, shadowTint: [0.60, 0.34, 0.90], highlightTint: [1.20, 0.92, 0.74],
    vignette: 0.34, grain: 0.030, chroma: 0.56, bloom: 0.92, exposure: 1.14,
    rays: 0.85, haze: 0.50, dust: 0.34,
  },
  /** Blinding noon on the march: bleached sky, turquoise heat. */
  blaze: {
    saturation: 1.46, shadowTint: [0.40, 0.62, 0.92], highlightTint: [1.14, 1.10, 0.92],
    vignette: 0.22, grain: 0.022, chroma: 0.40, bloom: 0.66, exposure: 1.22,
    rays: 1.00, haze: 1.00, dust: 0.62,
  },
  /** Golden hour in the Egyptian's field. */
  gold: {
    saturation: 1.52, shadowTint: [0.52, 0.42, 0.86], highlightTint: [1.20, 1.00, 0.74],
    vignette: 0.26, grain: 0.027, chroma: 0.46, bloom: 0.78, exposure: 1.14,
    rays: 0.80, haze: 0.55, dust: 0.40,
  },
  /** Moonlit camp — cold indigo, firelight pockets, thin silver haze. */
  neonNight: {
    saturation: 1.62, shadowTint: [0.42, 0.36, 1.00], highlightTint: [1.24, 0.78, 0.94],
    vignette: 0.42, grain: 0.046, chroma: 0.62, bloom: 1.15, exposure: 1.10,
    rays: 0.35, haze: 0.22, dust: 0.24,
  },
  /** South Beach afternoon at the gate: clean, pale limestone. */
  clear: {
    saturation: 1.42, shadowTint: [0.44, 0.56, 0.90], highlightTint: [1.12, 1.08, 0.90],
    vignette: 0.22, grain: 0.022, chroma: 0.40, bloom: 0.62, exposure: 1.16,
    rays: 0.70, haze: 0.50, dust: 0.34,
  },
  /** The storm on Gilboa: bruised purple, steel blue, cold highlights. */
  storm: {
    saturation: 1.30, shadowTint: [0.48, 0.42, 0.92], highlightTint: [0.96, 1.00, 1.14],
    vignette: 0.42, grain: 0.044, chroma: 0.50, bloom: 0.46, exposure: 1.02,
    rays: 0.20, haze: 0.38, dust: 0.54,
  },
});

const GradeShader = {
  uniforms: {
    tDiffuse: { value: null },
    uSaturation: { value: 1.5 },
    uShadowTint: { value: new THREE.Vector3(0.55, 0.32, 0.9) },
    uHighlightTint: { value: new THREE.Vector3(1.2, 0.9, 0.75) },
    uVignette: { value: 0.3 },
    uGrain: { value: 0.03 },
    uChroma: { value: 0.5 },
    uTime: { value: 0 },
    uSun: { value: new THREE.Vector2(0.5, 0.7) },
    uSunOn: { value: 1 },
    uRays: { value: 0.6 },
    uHaze: { value: 0.4 },
    uDust: { value: 0.3 },
    uMotion: { value: new THREE.Vector2(0, 0) },
    uMotionBlur: { value: 0 },
  },
  vertexShader: /* glsl */ `
    varying vec2 vUv;
    void main() {
      vUv = uv;
      gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
    }
  `,
  fragmentShader: /* glsl */ `
    uniform sampler2D tDiffuse;
    uniform float uSaturation;
    uniform vec3 uShadowTint;
    uniform vec3 uHighlightTint;
    uniform float uVignette;
    uniform float uGrain;
    uniform float uChroma;
    uniform float uTime;
    uniform vec2 uSun;
    uniform float uSunOn;
    uniform float uRays;
    uniform float uHaze;
    uniform float uDust;
    uniform vec2 uMotion;
    uniform float uMotionBlur;
    varying vec2 vUv;

    float luma(vec3 c) { return dot(c, vec3(0.299, 0.587, 0.114)); }

    void main() {
      // ---------------- heat haze: refractive shimmer near the ground
      vec2 uv = vUv;
      float band = smoothstep(0.52, 0.02, vUv.y);
      float wob = sin(vUv.y * 130.0 + uTime * 2.6) * 0.0016
                + sin(vUv.y * 57.0 - uTime * 1.7 + vUv.x * 9.0) * 0.0011;
      uv.x += wob * band * uHaze;
      uv.y += cos(vUv.x * 40.0 + uTime * 2.1) * 0.0006 * band * uHaze;

      vec2 centered = uv - 0.5;
      float edge = dot(centered, centered);

      // chromatic aberration toward the frame edges
      vec2 ca = centered * edge * uChroma * 0.06;
      vec3 col;
      col.r = texture2D(tDiffuse, uv + ca).r;
      col.g = texture2D(tDiffuse, uv).g;
      col.b = texture2D(tDiffuse, uv - ca).b;

      // ---------------- god rays: march toward the sun, add bright shafts
      if (uRays > 0.001 && uSunOn > 0.5) {
        float d = distance(uv, uSun);
        float falloff = 1.0 - smoothstep(0.0, 0.85, d);
        vec2 dir = (uSun - uv) * (0.55 / 14.0);
        vec2 p = uv;
        float acc = 0.0;
        for (int i = 0; i < 14; i++) {
          p += dir;
          vec3 s = texture2D(tDiffuse, p).rgb;
          acc += smoothstep(0.62, 1.5, luma(s));
        }
        acc /= 14.0;
        vec3 rayTint = vec3(1.0, 0.80, 0.56);
        col += rayTint * acc * falloff * uRays * 0.85;
      }

      // ---------------- motion blur: smear along measured camera flow
      if (uMotionBlur > 0.02) {
        vec3 blurred = col;
        for (int i = 1; i <= 4; i++) {
          vec2 off = uMotion * (float(i) / 4.0) * uMotionBlur;
          blurred += texture2D(tDiffuse, uv - off).rgb;
        }
        col = blurred / 5.0;
      }

      // Miami split toning: violet shadows, hot cream-pink highlights
      float l = luma(col);
      float shadowW = 1.0 - smoothstep(0.0, 0.55, l);
      float highW = smoothstep(0.40, 1.0, l);
      col = mix(col, col * uShadowTint * 1.55, shadowW * 0.55);
      col = mix(col, col * uHighlightTint * 1.14, highW * 0.50);

      // vibrance: push muted colors harder than already-vivid ones
      float mx = max(col.r, max(col.g, col.b));
      float mn = min(col.r, min(col.g, col.b));
      float vib = 1.0 + (1.0 - (mx - mn)) * 0.34;
      col *= vib;

      // saturation punch
      l = luma(col);
      col = mix(vec3(l), col, uSaturation);

      // filmic S-curve — contrast with the color still alive
      col = mix(col, smoothstep(0.0, 1.0, col), 0.26);

      // suspended dust: warm veil strongest low in the frame
      vec3 dustCol = vec3(0.86, 0.72, 0.52);
      col = mix(col, col * dustCol + dustCol * 0.04, uDust * (0.18 + band * 0.32));

      // gentle highlight rolloff
      col = col / (1.0 + max(0.0, mx - 1.0) * 0.25);

      // vignette + grain
      col *= 1.0 - edge * uVignette;
      float g = fract(sin(dot(vUv + fract(uTime * 0.37), vec2(12.9898, 78.233))) * 43758.5453);
      col += (g - 0.5) * uGrain;

      gl_FragColor = vec4(col, 1.0);
    }
  `,
};

class PostFX {
  constructor() {
    this.composer = null;
    this.renderer = null;
    this._gradePass = null;
    this._bloom = null;
    this._bokeh = null;
    this._time = 0;
    this._focus = 6;
    this._focusTarget = 6;
    this._sunDir = new THREE.Vector3(0.4, 0.5, -0.6).normalize();
    this._sunUv = new THREE.Vector2(0.5, 0.7);
    this._prevVP = new THREE.Matrix4();
    this._prevVPReady = false;
    this._motion = new THREE.Vector2(0, 0);
    this._current = {
      ...GRADES.sunset,
      shadowTint: [...GRADES.sunset.shadowTint],
      highlightTint: [...GRADES.sunset.highlightTint],
    };
    this._target = this._current;
  }

  init(renderer, scene, camera) {
    if (this.composer) return;
    this.renderer = renderer;
    this.scene = scene;
    this.camera = camera;

    const size = new THREE.Vector2();
    renderer.getSize(size);

    const composer = new EffectComposer(renderer);
    // a lighter buffer on phones keeps the extra passes affordable
    composer.setPixelRatio(Math.min(window.devicePixelRatio, IS_MOBILE ? 1.25 : 1.6));
    composer.setSize(size.x, size.y);
    composer.addPass(new RenderPass(scene, camera));

    // --- depth of field: focus rides the player, aperture opens with sun
    if (!IS_MOBILE) {
      const bokeh = new BokehPass(scene, camera, {
        focus: this._focus,
        aperture: 0.0016,
        maxblur: 0.008,
      });
      composer.addPass(bokeh);
      this._bokeh = bokeh;
    }

    const bloom = new UnrealBloomPass(size, 0.85, 0.65, 0.62);
    composer.addPass(bloom);

    const grade = new ShaderPass(GradeShader);
    composer.addPass(grade);

    composer.addPass(new OutputPass());

    this.composer = composer;
    this._bloom = bloom;
    this._gradePass = grade;
  }

  /** Crossfade toward a grade preset. */
  setGrade(name) {
    const preset = GRADES[name];
    if (!preset) return;
    this._target = preset;
    if (!this.composer) return; // headless: nothing to grade
    this._bloom.strength = preset.bloom;
    if (this.renderer) this.renderer.toneMappingExposure = preset.exposure;
  }

  /** Direction *toward* the sun in world space (drives the god rays). */
  setSunDirection(v) {
    if (!v || (v.lengthSq && v.lengthSq() < 1e-6)) return;
    this._sunDir.copy(v).normalize();
  }

  /**
   * Quality features, driven by the settings leaf.
   * @param {{godRays?:boolean, depthOfField?:boolean, filmGrain?:boolean}} f
   */
  setFeatures(f = {}) {
    this._features = { ...(this._features || {}), ...f };
    if (this._bokeh) this._bokeh.enabled = this._features.depthOfField !== false;
  }

  /** Depth-of-field focus distance in world units (camera → player). */
  setFocus(distance) {
    if (Number.isFinite(distance)) this._focusTarget = Math.max(1.2, distance);
  }

  setSize(w, h) {
    this.composer?.setSize(w, h);
    this._bloom?.setSize(w, h);
  }

  _updateCameraFx(dt) {
    const cam = this.camera;
    if (!cam) return;
    cam.updateMatrixWorld();
    const vp = new THREE.Matrix4().multiplyMatrices(cam.projectionMatrix, cam.matrixWorldInverse);

    // ---- projected sun position + visibility
    const sunWorld = this._sunDir.clone().multiplyScalar(400).add(cam.position);
    const p = sunWorld.clone().project(cam);
    const on = p.z < 1 && Math.abs(p.x) < 1.35 && Math.abs(p.y) < 1.35;
    this._sunUv.set(p.x * 0.5 + 0.5, p.y * 0.5 + 0.5);
    if (!on) this._sunUv.set(0.5, -0.5);

    // ---- screen-space flow: how far a point ahead moved in screen space
    if (this._prevVPReady) {
      const ahead = cam.getWorldDirection(new THREE.Vector3()).multiplyScalar(12).add(cam.position);
      const now = ahead.clone().applyMatrix4(vp);
      const prev = ahead.clone().applyMatrix4(this._prevVP);
      const dx = (now.x - prev.x) * 0.5;
      const dy = (now.y - prev.y) * 0.5;
      // clamp + smooth so a tab hitch never produces a full-frame smear
      this._motion.x += (Math.max(-0.08, Math.min(0.08, dx)) - this._motion.x) * Math.min(1, dt * 12);
      this._motion.y += (Math.max(-0.08, Math.min(0.08, dy)) - this._motion.y) * Math.min(1, dt * 12);
    }
    this._prevVP.copy(vp);
    this._prevVPReady = true;

    const u = this._gradePass.uniforms;
    u.uSun.value.copy(this._sunUv);
    u.uSunOn.value = on ? 1 : 0;
    u.uMotion.value.copy(this._motion);
    const flow = Math.hypot(this._motion.x, this._motion.y);
    u.uMotionBlur.value += (Math.min(1, flow / 0.06) - u.uMotionBlur.value) * Math.min(1, dt * 8);
  }

  render(dt) {
    if (!this.composer) return;
    this._time += dt;

    // ease the grade toward its target for smooth phase transitions
    const k = Math.min(1, dt * 2.4);
    const c = this._current;
    const t = this._target;
    c.saturation += (t.saturation - c.saturation) * k;
    c.vignette += (t.vignette - c.vignette) * k;
    c.grain += (t.grain - c.grain) * k;
    c.chroma += (t.chroma - c.chroma) * k;
    c.rays += (t.rays - c.rays) * k;
    c.haze += (t.haze - c.haze) * k;
    c.dust += (t.dust - c.dust) * k;
    for (let i = 0; i < 3; i++) {
      c.shadowTint[i] += (t.shadowTint[i] - c.shadowTint[i]) * k;
      c.highlightTint[i] += (t.highlightTint[i] - c.highlightTint[i]) * k;
    }

    // focus pull toward the player
    this._focus += (this._focusTarget - this._focus) * Math.min(1, dt * 3.5);
    if (this._bokeh) {
      const b = this._bokeh.uniforms;
      b.focus.value = this._focus;
      b.aperture.value = 0.008 + (1 - Math.min(1, c.haze)) * 0.006;
    }

    const u = this._gradePass.uniforms;
    u.uSaturation.value = c.saturation;
    u.uShadowTint.value.set(c.shadowTint[0], c.shadowTint[1], c.shadowTint[2]);
    u.uHighlightTint.value.set(c.highlightTint[0], c.highlightTint[1], c.highlightTint[2]);
    u.uVignette.value = c.vignette;
    u.uGrain.value = c.grain;
    u.uChroma.value = c.chroma;
    const feat = this._features || {};
    u.uRays.value = feat.godRays === false ? 0 : c.rays;
    u.uHaze.value = c.haze;
    u.uDust.value = c.dust;
    u.uGrain.value = feat.filmGrain === false ? 0 : c.grain;
    u.uTime.value = this._time;

    this._updateCameraFx(dt);

    this.composer.render(dt);
  }
}

export const postFX = new PostFX();
