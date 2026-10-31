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

/**
 * Per-mood grade presets — photographic, not painted.
 *
 * Pass 11 rebuilt these from measurements instead of vibes. Each preset is what
 * a cinematographer would set for that hour: a colour temperature for the
 * shadows and the highlights (never a filter over the whole frame), a contrast
 * curve, how much dust is suspended in the air, and the exposure the sun asks
 * for. Saturation stays near 1.0 — photoreal is not the same as vivid.
 */
export const GRADES = Object.freeze({
  /** Ziklag at golden hour: warm highlights, cool shade, smoke in the air. */
  sunset: {
    saturation: 1.05, contrast: 0.34,
    shadowTint: [0.95, 0.97, 1.06], highlightTint: [1.06, 1.01, 0.94],
    vignette: 0.30, grain: 0.022, chroma: 0.20, bloom: 0.55, exposure: 1.02,
    rays: 0.75, haze: 0.55, dust: 0.42, dustColor: [0.86, 0.76, 0.62],
  },
  /** White noon on the march: glare, bleached rock, dust in the light. */
  blaze: {
    saturation: 1.02, contrast: 0.29,
    shadowTint: [0.97, 0.99, 1.04], highlightTint: [1.04, 1.03, 1.00],
    vignette: 0.19, grain: 0.016, chroma: 0.13, bloom: 0.42, exposure: 1.04,
    rays: 0.92, haze: 1.00, dust: 0.55, dustColor: [0.90, 0.84, 0.72],
  },
  /** Mid-afternoon in the Egyptian's field: warm, gentle, dry grass. */
  gold: {
    saturation: 1.05, contrast: 0.31,
    shadowTint: [0.96, 0.98, 1.05], highlightTint: [1.06, 1.02, 0.94],
    vignette: 0.24, grain: 0.020, chroma: 0.17, bloom: 0.50, exposure: 1.03,
    rays: 0.70, haze: 0.50, dust: 0.35, dustColor: [0.88, 0.80, 0.66],
  },
  /** The camp at night: silver key, fire pockets, deep blue shade. */
  night: {
    saturation: 0.96, contrast: 0.30,
    shadowTint: [0.90, 0.95, 1.16], highlightTint: [1.05, 1.00, 0.97],
    vignette: 0.46, grain: 0.038, chroma: 0.16, bloom: 0.92, exposure: 1.12,
    rays: 0.16, haze: 0.18, dust: 0.15, dustColor: [0.72, 0.78, 0.92],
  },
  /** Sunset at Besor: the last warm light, long shadows, silty air. */
  ember: {
    saturation: 1.07, contrast: 0.34,
    shadowTint: [0.94, 0.96, 1.09], highlightTint: [1.08, 1.00, 0.90],
    vignette: 0.30, grain: 0.022, chroma: 0.19, bloom: 0.62, exposure: 1.02,
    rays: 0.85, haze: 0.60, dust: 0.45, dustColor: [0.92, 0.78, 0.62],
  },
  /** Hebron in the afternoon: pale stone, clean air, neutral balance. */
  clear: {
    saturation: 1.03, contrast: 0.28,
    shadowTint: [0.96, 0.98, 1.05], highlightTint: [1.04, 1.02, 0.96],
    vignette: 0.20, grain: 0.016, chroma: 0.14, bloom: 0.40, exposure: 1.02,
    rays: 0.66, haze: 0.45, dust: 0.30, dustColor: [0.88, 0.82, 0.70],
  },
  /** Gilboa under cloud: desaturated, flat light, rain not yet fallen. */
  storm: {
    saturation: 0.91, contrast: 0.26,
    shadowTint: [0.96, 0.98, 1.06], highlightTint: [0.99, 1.00, 1.02],
    vignette: 0.34, grain: 0.032, chroma: 0.14, bloom: 0.28, exposure: 0.99,
    rays: 0.10, haze: 0.35, dust: 0.30, dustColor: [0.78, 0.80, 0.84],
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
    uContrast: { value: 0.3 },
    uDustColor: { value: new THREE.Vector3(0.88, 0.80, 0.68) },
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
    uniform float uContrast;
    uniform vec3 uDustColor;
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
      vec2 ca = centered * edge * uChroma * 0.042;
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

      // split toning: the colour of the shade vs. the colour of the light
      float l = luma(col);
      float shadowW = 1.0 - smoothstep(0.0, 0.55, l);
      float highW = smoothstep(0.40, 1.0, l);
      col = mix(col, col * uShadowTint * 1.30, shadowW * 0.40);
      col = mix(col, col * uHighlightTint * 1.10, highW * 0.38);

      // a little vibrance — enough to keep Mie-washed noon from going grey
      float mx = max(col.r, max(col.g, col.b));
      float mn = min(col.r, min(col.g, col.b));
      col *= 1.0 + (1.0 - (mx - mn)) * 0.10;

      // saturation: nearly neutral by design
      l = luma(col);
      col = mix(vec3(l), col, uSaturation);

      // filmic S-curve — contrast with the colour still alive
      col = mix(col, smoothstep(0.0, 1.0, col), uContrast);

      // suspended dust: the phase's own particulate, strongest low in frame
      col = mix(col, col * uDustColor + uDustColor * 0.04, uDust * (0.18 + band * 0.32));

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
      dustColor: [...GRADES.sunset.dustColor],
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

  /** Render-resolution governor (see Engine._adapt). */
  setPixelRatio(r) {
    if (!this.composer || !Number.isFinite(r)) return;
    this.composer.setPixelRatio(Math.max(0.5, r));
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
    c.contrast += (t.contrast - c.contrast) * k;
    for (let i = 0; i < 3; i++) {
      c.shadowTint[i] += (t.shadowTint[i] - c.shadowTint[i]) * k;
      c.highlightTint[i] += (t.highlightTint[i] - c.highlightTint[i]) * k;
      c.dustColor[i] += (t.dustColor[i] - c.dustColor[i]) * k;
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
    u.uContrast.value = c.contrast;
    u.uDustColor.value.set(c.dustColor[0], c.dustColor[1], c.dustColor[2]);
    u.uGrain.value = feat.filmGrain === false ? 0 : c.grain;
    u.uTime.value = this._time;

    this._updateCameraFx(dt);

    this.composer.render(dt);
  }
}

export const postFX = new PostFX();
