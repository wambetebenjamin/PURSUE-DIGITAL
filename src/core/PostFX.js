/**
 * PostFX.js — the "Vice City" look: bloom + retro-Miami color grade.
 *
 * Chain: RenderPass → UnrealBloomPass → GradePass (custom) → OutputPass.
 * The grade does split-toning (teal/purple shadows, hot highlights),
 * saturation punch, edge chromatic aberration, vignette, and film grain —
 * the ingredients of that early-2000s neon-sunset render.
 *
 * Grades transition smoothly (`setGrade` lerps toward the target every
 * frame), so crossing a phase boundary fades the mood instead of snapping.
 * All methods no-op before `init()` (headless smoke runs fine).
 */
import * as THREE from 'three';
import { EffectComposer } from 'three/addons/postprocessing/EffectComposer.js';
import { RenderPass } from 'three/addons/postprocessing/RenderPass.js';
import { UnrealBloomPass } from 'three/addons/postprocessing/UnrealBloomPass.js';
import { ShaderPass } from 'three/addons/postprocessing/ShaderPass.js';
import { OutputPass } from 'three/addons/postprocessing/OutputPass.js';

/** Per-mood grade presets. */
export const GRADES = Object.freeze({
  sunset: {
    saturation: 1.42, shadowTint: [0.52, 0.36, 0.78], highlightTint: [1.14, 0.94, 0.76],
    vignette: 0.34, grain: 0.035, chroma: 0.6, bloom: 0.72, exposure: 1.14,
  },
  blaze: {
    saturation: 1.34, shadowTint: [0.42, 0.58, 0.8], highlightTint: [1.1, 1.04, 0.86],
    vignette: 0.22, grain: 0.025, chroma: 0.42, bloom: 0.52, exposure: 1.22,
  },
  gold: {
    saturation: 1.4, shadowTint: [0.5, 0.42, 0.72], highlightTint: [1.14, 0.98, 0.76],
    vignette: 0.28, grain: 0.03, chroma: 0.5, bloom: 0.58, exposure: 1.14,
  },
  neonNight: {
    saturation: 1.55, shadowTint: [0.36, 0.32, 0.85], highlightTint: [1.18, 0.82, 0.92],
    vignette: 0.44, grain: 0.05, chroma: 0.75, bloom: 1.0, exposure: 1.06,
  },
  clear: {
    saturation: 1.32, shadowTint: [0.48, 0.52, 0.72], highlightTint: [1.08, 1.02, 0.88],
    vignette: 0.24, grain: 0.025, chroma: 0.42, bloom: 0.48, exposure: 1.14,
  },
  storm: {
    saturation: 1.16, shadowTint: [0.4, 0.52, 0.66], highlightTint: [0.92, 1.0, 1.12],
    vignette: 0.42, grain: 0.045, chroma: 0.55, bloom: 0.34, exposure: 1.02,
  },
});

const GradeShader = {
  uniforms: {
    tDiffuse: { value: null },
    uSaturation: { value: 1.3 },
    uShadowTint: { value: new THREE.Vector3(0.5, 0.4, 0.75) },
    uHighlightTint: { value: new THREE.Vector3(1.1, 0.95, 0.8) },
    uVignette: { value: 0.3 },
    uGrain: { value: 0.03 },
    uChroma: { value: 0.5 },
    uTime: { value: 0 },
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
    varying vec2 vUv;

    void main() {
      vec2 centered = vUv - 0.5;
      float edge = dot(centered, centered);

      // chromatic aberration toward the frame edges
      vec2 ca = centered * edge * uChroma * 0.06;
      vec3 col;
      col.r = texture2D(tDiffuse, vUv + ca).r;
      col.g = texture2D(tDiffuse, vUv).g;
      col.b = texture2D(tDiffuse, vUv - ca).b;

      // split toning: tinted shadows, hot highlights
      float luma = dot(col, vec3(0.299, 0.587, 0.114));
      float shadowW = 1.0 - smoothstep(0.0, 0.55, luma);
      float highW = smoothstep(0.42, 1.0, luma);
      col = mix(col, col * uShadowTint * 1.5, shadowW * 0.5);
      col = mix(col, col * uHighlightTint * 1.15, highW * 0.45);

      // saturation punch (the Vice City signature)
      luma = dot(col, vec3(0.299, 0.587, 0.114));
      col = mix(vec3(luma), col, uSaturation);

      // gentle S-curve
      col = mix(col, smoothstep(0.0, 1.0, col), 0.22);

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
    this._time = 0;
    this._current = { ...GRADES.sunset, shadowTint: [...GRADES.sunset.shadowTint], highlightTint: [...GRADES.sunset.highlightTint] };
    this._target = this._current;
  }

  init(renderer, scene, camera) {
    if (this.composer) return;
    this.renderer = renderer;

    const size = new THREE.Vector2();
    renderer.getSize(size);

    const composer = new EffectComposer(renderer);
    composer.setPixelRatio(Math.min(window.devicePixelRatio, 1.75));
    composer.setSize(size.x, size.y);
    composer.addPass(new RenderPass(scene, camera));

    const bloom = new UnrealBloomPass(size, 0.7, 0.55, 0.72);
    composer.addPass(bloom);

    const grade = new ShaderPass(GradeShader);
    composer.addPass(grade);

    composer.addPass(new OutputPass());

    this.composer = composer;
    this._bloom = bloom;
    this._gradePass = grade;
  }

  /** Crossfade toward a grade preset ('sunset' | 'blaze' | 'gold' | 'neonNight' | 'clear' | 'storm'). */
  setGrade(name) {
    const preset = GRADES[name];
    if (!preset) return;
    this._target = preset;
    if (!this.composer) return; // headless: nothing to grade
    this._bloom.strength = preset.bloom;
    if (this.renderer) this.renderer.toneMappingExposure = preset.exposure;
  }

  setSize(w, h) {
    this.composer?.setSize(w, h);
    this._bloom?.setSize(w, h);
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
    for (let i = 0; i < 3; i++) {
      c.shadowTint[i] += (t.shadowTint[i] - c.shadowTint[i]) * k;
      c.highlightTint[i] += (t.highlightTint[i] - c.highlightTint[i]) * k;
    }

    const u = this._gradePass.uniforms;
    u.uSaturation.value = c.saturation;
    u.uShadowTint.value.set(c.shadowTint[0], c.shadowTint[1], c.shadowTint[2]);
    u.uHighlightTint.value.set(c.highlightTint[0], c.highlightTint[1], c.highlightTint[2]);
    u.uVignette.value = c.vignette;
    u.uGrain.value = c.grain;
    u.uChroma.value = c.chroma;
    u.uTime.value = this._time;

    this.composer.render(dt);
  }
}

export const postFX = new PostFX();
