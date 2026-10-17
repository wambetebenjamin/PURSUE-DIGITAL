/**
 * PostFX.js — the "Vice City" look: bloom + retro-Miami color grade.
 *
 * Chain: RenderPass → UnrealBloomPass → GradePass (custom) → OutputPass.
 * The grade does Miami split-toning (violet shadows, cream/pink highlights),
 * vibrance + saturation punch, an S-curve, edge chromatic aberration,
 * vignette and fine grain — the ingredients of that neon-sunset render.
 * Tone mapping is Khronos PBR Neutral (in Engine) so saturated pinks and
 * cyans survive to the grade instead of being greyed out by ACES.
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

/** Per-mood grade presets — named for their Vice City moment. */
export const GRADES = Object.freeze({
  /** Ocean Drive sunset: the icon. Magenta sky, violet shadows, hot sun. */
  sunset: {
    saturation: 1.62, shadowTint: [0.58, 0.30, 0.92], highlightTint: [1.20, 0.90, 0.72],
    vignette: 0.32, grain: 0.032, chroma: 0.66, bloom: 0.92, exposure: 1.16,
  },
  /** Malibu noon: cyan sky, turquoise haze, white glare. */
  blaze: {
    saturation: 1.52, shadowTint: [0.34, 0.64, 0.92], highlightTint: [1.12, 1.08, 0.88],
    vignette: 0.20, grain: 0.022, chroma: 0.46, bloom: 0.68, exposure: 1.24,
  },
  /** Little Havana golden hour. */
  gold: {
    saturation: 1.56, shadowTint: [0.54, 0.38, 0.88], highlightTint: [1.18, 0.98, 0.72],
    vignette: 0.26, grain: 0.028, chroma: 0.54, bloom: 0.76, exposure: 1.16,
  },
  /** Starfish Island night: electric indigo shadows, pink neon highlights. */
  neonNight: {
    saturation: 1.78, shadowTint: [0.36, 0.26, 1.00], highlightTint: [1.26, 0.68, 0.98],
    vignette: 0.42, grain: 0.048, chroma: 0.82, bloom: 1.22, exposure: 1.10,
  },
  /** South Beach afternoon: bright, clean, pastel. */
  clear: {
    saturation: 1.50, shadowTint: [0.40, 0.56, 0.92], highlightTint: [1.12, 1.06, 0.86],
    vignette: 0.22, grain: 0.022, chroma: 0.46, bloom: 0.64, exposure: 1.18,
  },
  /** Vice storm: bruised purple, steel blue, lightning-cold highlights. */
  storm: {
    saturation: 1.38, shadowTint: [0.46, 0.36, 0.92], highlightTint: [0.96, 0.98, 1.16],
    vignette: 0.42, grain: 0.045, chroma: 0.58, bloom: 0.48, exposure: 1.04,
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

      // Miami split toning: violet shadows, hot cream-pink highlights
      float luma = dot(col, vec3(0.299, 0.587, 0.114));
      float shadowW = 1.0 - smoothstep(0.0, 0.55, luma);
      float highW = smoothstep(0.40, 1.0, luma);
      col = mix(col, col * uShadowTint * 1.65, shadowW * 0.58);
      col = mix(col, col * uHighlightTint * 1.18, highW * 0.52);

      // vibrance: push muted colors harder than already-vivid ones
      float mx = max(col.r, max(col.g, col.b));
      float mn = min(col.r, min(col.g, col.b));
      float satLevel = mx - mn;
      float vib = 1.0 + (1.0 - satLevel) * 0.38;
      col *= vib;

      // saturation punch (the Vice City signature)
      luma = dot(col, vec3(0.299, 0.587, 0.114));
      col = mix(vec3(luma), col, uSaturation);

      // Miami S-curve — deeper contrast, colors stay alive
      col = mix(col, smoothstep(0.0, 1.0, col), 0.30);

      // gentle highlight bloom-bleed: soften anything clipping
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

    const bloom = new UnrealBloomPass(size, 0.85, 0.65, 0.62);
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
