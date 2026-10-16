/**
 * Particles.js — pooled CPU-simulated point particles with a tiny soft-disc
 * shader (round particles + per-particle alpha, zero textures).
 *
 * One THREE.Points draw call per emitter, regardless of particle count.
 */
import * as THREE from 'three';
import { randRange } from '../../utils/math.js';

const VERT = /* glsl */ `
  attribute float aSize;
  attribute float aAlpha;
  attribute vec3 aColor;
  varying float vAlpha;
  varying vec3 vColor;
  void main() {
    vAlpha = aAlpha;
    vColor = aColor;
    vec4 mv = modelViewMatrix * vec4(position, 1.0);
    gl_PointSize = aSize * (240.0 / max(1.0, -mv.z));
    gl_Position = projectionMatrix * mv;
  }
`;

const FRAG = /* glsl */ `
  varying float vAlpha;
  varying vec3 vColor;
  void main() {
    float d = length(gl_PointCoord - vec2(0.5));
    if (d > 0.5) discard;
    float a = vAlpha * smoothstep(0.5, 0.12, d);
    gl_FragColor = vec4(vColor, a);
  }
`;

/**
 * @typedef {Object} EmitterConfig
 * @property {number} count          pool size
 * @property {THREE.Vector3} origin  emit anchor (local to the parent group)
 * @property {number} spread         horizontal spawn radius
 * @property {number} rise           upward velocity base
 * @property {number} riseJitter
 * @property {number} drift          sideways wander
 * @property {number} life           seconds
 * @property {number} lifeJitter
 * @property {number} size           base point size
 * @property {number} sizeGrow       size gained over a lifetime
 * @property {number} alpha          peak opacity
 * @property {number} rate           particles per second (<=0 = all at once)
 * @property {THREE.Color|number} color
 * @property {THREE.Color|number} [colorEnd]
 * @property {boolean} [additive]
 */
export class ParticleEmitter {
  constructor(cfg) {
    this.cfg = cfg;
    this.count = cfg.count;
    this.origin = cfg.origin.clone();
    this._t = 0;

    const positions = new Float32Array(this.count * 3);
    const colors = new Float32Array(this.count * 3);
    const sizes = new Float32Array(this.count);
    const alphas = new Float32Array(this.count);

    this._pos = positions;
    this._col = colors;
    this._size = sizes;
    this._alpha = alphas;

    // per-particle sim state
    this._life = new Float32Array(this.count);
    this._maxLife = new Float32Array(this.count);
    this._vel = new Float32Array(this.count * 3);
    this._alive = new Uint8Array(this.count);

    const geometry = new THREE.BufferGeometry();
    geometry.setAttribute('position', new THREE.BufferAttribute(positions, 3));
    geometry.setAttribute('aColor', new THREE.BufferAttribute(colors, 3));
    geometry.setAttribute('aSize', new THREE.BufferAttribute(sizes, 1));
    geometry.setAttribute('aAlpha', new THREE.BufferAttribute(alphas, 1));

    this.material = new THREE.ShaderMaterial({
      vertexShader: VERT,
      fragmentShader: FRAG,
      transparent: true,
      depthWrite: false,
      blending: cfg.additive ? THREE.AdditiveBlending : THREE.NormalBlending,
    });

    this.points = new THREE.Points(geometry, this.material);
    this.points.frustumCulled = false;

    this._colorA = new THREE.Color(cfg.color);
    this._colorB = new THREE.Color(cfg.colorEnd ?? cfg.color);

    // seed the pool
    for (let i = 0; i < this.count; i++) this._spawn(i, true);
    this._flush();
  }

  setOrigin(x, y, z) {
    this.origin.set(x, y, z);
  }

  _spawn(i, randomAge = false) {
    const c = this.cfg;
    const i3 = i * 3;
    const ang = Math.random() * Math.PI * 2;
    const rad = Math.sqrt(Math.random()) * (c.spread ?? 1);

    this._pos[i3] = this.origin.x + Math.cos(ang) * rad;
    this._pos[i3 + 1] = this.origin.y + randRange(-0.15, 0.35);
    this._pos[i3 + 2] = this.origin.z + Math.sin(ang) * rad;

    this._vel[i3] = randRange(-c.drift, c.drift);
    this._vel[i3 + 1] = c.rise * randRange(1 - c.riseJitter, 1 + c.riseJitter);
    this._vel[i3 + 2] = randRange(-c.drift, c.drift);

    this._maxLife[i] = c.life * randRange(1 - c.lifeJitter, 1 + c.lifeJitter);
    this._life[i] = randomAge ? Math.random() * this._maxLife[i] : 0;
    this._alive[i] = 1;
    this._size[i] = c.size;
  }

  update(dt) {
    const c = this.cfg;
    for (let i = 0; i < this.count; i++) {
      if (!this._alive[i]) {
        this._alpha[i] = 0;
        continue;
      }
      this._life[i] += dt;
      const t = this._life[i] / this._maxLife[i];
      if (t >= 1) {
        this._spawn(i);
        continue;
      }
      const i3 = i * 3;
      this._pos[i3] += this._vel[i3] * dt;
      this._pos[i3 + 1] += this._vel[i3 + 1] * dt;
      this._pos[i3 + 2] += this._vel[i3 + 2] * dt;

      // gentle lateral wander
      this._vel[i3] += Math.sin(this._life[i] * 1.3 + i) * c.drift * 0.35 * dt;
      this._vel[i3 + 2] += Math.cos(this._life[i] * 1.1 + i * 1.7) * c.drift * 0.35 * dt;

      this._size[i] = c.size + c.sizeGrow * t;
      // fade in fast, out slow
      const fade = t < 0.12 ? t / 0.12 : 1 - (t - 0.12) / 0.88;
      this._alpha[i] = Math.max(0, fade) * c.alpha;

      const col = this._col;
      col[i3] = this._colorA.r + (this._colorB.r - this._colorA.r) * t;
      col[i3 + 1] = this._colorA.g + (this._colorB.g - this._colorA.g) * t;
      col[i3 + 2] = this._colorA.b + (this._colorB.b - this._colorA.b) * t;
    }
    this._flush();
  }

  _flush() {
    const g = this.points.geometry;
    g.attributes.position.needsUpdate = true;
    g.attributes.aColor.needsUpdate = true;
    g.attributes.aSize.needsUpdate = true;
    g.attributes.aAlpha.needsUpdate = true;
  }
}
