/**
 * SkyDome.js — an analytic sky, not a painted gradient.
 *
 * Pass 11 replaced the neon three-stop Miami gradient with a physically
 * motivated dome. The shader sums the two lobes that decide what daylight
 * actually looks like:
 *
 *   • Rayleigh scattering — the λ⁻⁴ term that turns the sky blue overhead and
 *     bleaches it toward the horizon, strong when the air is clean.
 *   • Mie scattering — the forward-lobed aerosol term that puts the white haze
 *     around a low sun and washes a dusty noon toward paper.
 *
 * On top of that sit the four things a camera actually records:
 *   – a limb-darkened solar disc (or none at all under thick cloud),
 *   – a horizon haze band so the ground and the sky meet in one colour,
 *   – a two-scale cirrus deck, lit from below when the sun is low,
 *   – a colour-graded star field and a moon after dark.
 *
 * The dome is a single inverted sphere at radius 420 with depth writes off and
 * renderOrder −10, so it costs one draw call and never occludes anything.
 *
 * Colours are authored per phase in `world/atmospheres.js`; this file only
 * knows how to light them.
 */
import * as THREE from 'three';

const VERT = /* glsl */ `
  varying vec3 vDir;
  void main() {
    vDir = normalize(position);
    // keep the dome centred on the camera so it can never be walked out of
    vec4 world = modelMatrix * vec4(position, 1.0);
    gl_Position = projectionMatrix * viewMatrix * world;
  }
`;

const FRAG = /* glsl */ `
  uniform vec3 uZenith;
  uniform vec3 uHorizon;
  uniform vec3 uGround;
  uniform vec3 uSunDir;
  uniform vec3 uSunColor;
  uniform vec3 uCloudTint;
  uniform vec3 uMoonDir;
  uniform vec3 uMoonColor;
  uniform float uTurbidity;   // aerosol load (Mie)
  uniform float uRayleigh;    // clean-air blue
  uniform float uExponent;    // how fast the sky's blue returns overhead
  uniform float uGlow;        // halo strength around the disc
  uniform float uSunDisc;     // 0 hides the disc (overcast, night)
  uniform float uCloud;       // 0..1 deck coverage
  uniform float uStars;       // 0..1 star field
  uniform float uMoon;        // 0..1 moon
  uniform float uMoonSize;    // cos(angular radius) of the moon disc
  uniform float uTime;
  varying vec3 vDir;

  float hash13(vec3 p3) {
    p3 = fract(p3 * 0.1031);
    p3 += dot(p3, p3.yzx + 33.33);
    return fract((p3.x + p3.y) * p3.z);
  }

  float vnoise(vec3 x) {
    vec3 i = floor(x);
    vec3 f = fract(x);
    f = f * f * (3.0 - 2.0 * f);
    float n000 = hash13(i + vec3(0.0, 0.0, 0.0));
    float n100 = hash13(i + vec3(1.0, 0.0, 0.0));
    float n010 = hash13(i + vec3(0.0, 1.0, 0.0));
    float n110 = hash13(i + vec3(1.0, 1.0, 0.0));
    float n001 = hash13(i + vec3(0.0, 0.0, 1.0));
    float n101 = hash13(i + vec3(1.0, 0.0, 1.0));
    float n011 = hash13(i + vec3(0.0, 1.0, 1.0));
    float n111 = hash13(i + vec3(1.0, 1.0, 1.0));
    float nx00 = mix(n000, n100, f.x);
    float nx10 = mix(n010, n110, f.x);
    float nx01 = mix(n001, n101, f.x);
    float nx11 = mix(n011, n111, f.x);
    return mix(mix(nx00, nx10, f.y), mix(nx01, nx11, f.y), f.z);
  }

  float fbm(vec3 p) {
    float sum = 0.0;
    float amp = 0.55;
    for (int i = 0; i < 4; i++) {
      sum += amp * vnoise(p);
      p *= 2.03;
      amp *= 0.5;
    }
    return sum;
  }

  void main() {
    vec3 d = normalize(vDir);
    float h = clamp(d.y, -1.0, 1.0);
    float up = clamp(h, 0.0, 1.0);
    float mu = max(dot(d, uSunDir), 0.0);

    // ------------------------------------------------ sky radiance gradient
    float t = pow(up, uExponent);
    vec3 col = mix(uHorizon, uZenith, smoothstep(0.0, 1.0, t));

    // ------------------------------------------------ scattering lobes
    float rayPhase = 0.75 * (1.0 + mu * mu);
    const float g = 0.78; // Mie anisotropy
    float miePhase = (1.0 - g * g) / pow(1.0 + g * g - 2.0 * g * mu, 1.5);
    float lowAir = pow(1.0 - up, 4.0); // thicker air near the horizon
    col += uSunColor * (rayPhase * 0.10 * uRayleigh + miePhase * 0.042 * uTurbidity)
         * (0.28 + lowAir * 1.5);

    // ------------------------------------------------ horizon haze band
    col = mix(col, uHorizon, pow(1.0 - up, 9.0) * 0.90);

    // ------------------------------------------------ cirrus / storm deck
    vec3 sp = d / max(up, 0.075);
    vec2 drift = vec2(uTime * 0.0042, uTime * 0.0019);
    float n1 = fbm(vec3(sp.xz * 1.55 + drift, 12.0));
    float n2 = fbm(vec3(sp.xz * 5.10 - drift * 1.7, 31.0));
    float cover = smoothstep(0.74 - uCloud * 0.34, 1.00 - uCloud * 0.26, n1 * 0.72 + n2 * 0.28);
    float cloudMask = smoothstep(0.01, 0.11, up);
    vec3 cloudCol = mix(uCloudTint, uSunColor, 0.34 * mu);
    cloudCol *= 0.84 + 0.45 * lowAir; // clouds are lit from beneath near sunset
    col = mix(col, cloudCol, cover * cloudMask * 0.94);

    // ------------------------------------------------ the sun
    float disc = smoothstep(0.99955, 0.99986, mu);
    float limb = 1.0 - 0.28 * smoothstep(0.99958, 0.99996, mu); // limb darkening
    col += uSunColor * disc * uSunDisc * 6.0 * limb;
    col += uSunColor * pow(mu, 900.0) * uGlow * 0.55;
    col += uSunColor * pow(mu, 11.0) * uGlow * 0.11;

    // ------------------------------------------------ the moon
    float md = max(dot(d, uMoonDir), 0.0);
    float moonDisc = smoothstep(uMoonSize - 0.00010, uMoonSize + 0.00006, md);
    float moonLimb = 1.0 - 0.30 * smoothstep(uMoonSize, 1.0, md);
    col += uMoonColor * moonDisc * uMoon * 1.7 * moonLimb;
    col += uMoonColor * pow(md, 2400.0) * uMoon * 0.55;
    col += uMoonColor * pow(md, 40.0) * uMoon * 0.05; // silver halo

    // ------------------------------------------------ stars & the milky band
    if (uStars > 0.001) {
      vec3 cell = fract(d * 260.0) - 0.5;
      float seed = hash13(floor(d * 260.0));
      float point = smoothstep(0.5, 0.0, length(cell)) * smoothstep(0.9955, 1.0, seed);
      float twinkle = 0.62 + 0.38 * sin(uTime * 1.6 + seed * 61.0);
      float band = smoothstep(0.62, 0.0, abs(dot(d, normalize(vec3(0.42, 0.30, 0.86)))));
      float atmosphere = smoothstep(0.0, 0.20, up);
      col += vec3(0.82, 0.88, 1.00) * (point * twinkle * 1.7 + band * 0.030) * uStars * atmosphere;
    }

    // ------------------------------------------------ below the horizon
    col = mix(col, uGround, (1.0 - smoothstep(-0.22, 0.0, h)) * 0.92);

    gl_FragColor = vec4(col, 1.0);
  }
`;

export class SkyDome {
  /**
   * @param {object|number} preset an atmosphere from `world/atmospheres.js`,
   *        or a legacy top colour (kept so old call sites never throw)
   */
  constructor(preset = 0x44709f, bottomColor = 0xe8b183, exponent = 0.42, midColor = null) {
    this.material = new THREE.ShaderMaterial({
      uniforms: {
        uZenith: { value: new THREE.Color(0x44709f) },
        uHorizon: { value: new THREE.Color(0xe8b183) },
        uGround: { value: new THREE.Color(0x7a5f4a) },
        uSunDir: { value: new THREE.Vector3(0.35, 0.6, 0.72).normalize() },
        uSunColor: { value: new THREE.Color(0xffd9a8) },
        uCloudTint: { value: new THREE.Color(0xf6dcc0) },
        uMoonDir: { value: new THREE.Vector3(-0.34, 0.56, 0.76).normalize() },
        uMoonColor: { value: new THREE.Color(0xdfe8ff) },
        uTurbidity: { value: 6.0 },
        uRayleigh: { value: 1.2 },
        uExponent: { value: exponent },
        uGlow: { value: 0.7 },
        uSunDisc: { value: 1.0 },
        uCloud: { value: 0.25 },
        uStars: { value: 0.0 },
        uMoon: { value: 0.0 },
        uMoonSize: { value: 0.99985 },
        uTime: { value: 0 },
      },
      vertexShader: VERT,
      fragmentShader: FRAG,
      side: THREE.BackSide,
      depthWrite: false,
      toneMapped: true,
      fog: false,
    });

    this.mesh = new THREE.Mesh(new THREE.SphereGeometry(420, 32, 20), this.material);
    this.mesh.name = 'SKY_DOME';
    this.mesh.frustumCulled = false;
    this.mesh.renderOrder = -10;

    this.preset = null;
    if (typeof preset === 'object' && preset !== null) {
      this.setAtmosphere(preset);
    } else {
      // legacy three-stop call: top, bottom, exponent, mid
      this.setColors(preset, bottomColor, midColor ?? undefined);
      this.material.uniforms.uExponent.value = exponent ?? 0.42;
    }
  }

  /** Adopt a full atmosphere preset (see `world/atmospheres.js`). */
  setAtmosphere(preset) {
    this.preset = preset;
    const u = this.material.uniforms;
    const sky = preset.sky ?? {};
    if (sky.zenith !== undefined) u.uZenith.value.set(sky.zenith);
    if (sky.horizon !== undefined) u.uHorizon.value.set(sky.horizon);
    if (sky.ground !== undefined) u.uGround.value.set(sky.ground);
    if (sky.turbidity !== undefined) u.uTurbidity.value = sky.turbidity;
    if (sky.rayleigh !== undefined) u.uRayleigh.value = sky.rayleigh;
    if (sky.glow !== undefined) u.uGlow.value = sky.glow;
    if (sky.cloud !== undefined) u.uCloud.value = sky.cloud;
    if (sky.cloudTint !== undefined) u.uCloudTint.value.set(sky.cloudTint);
    if (sky.stars !== undefined) u.uStars.value = sky.stars;
    if (sky.moon !== undefined) u.uMoon.value = sky.moon;
    if (sky.moonColor !== undefined) u.uMoonColor.value.set(sky.moonColor);
    if (sky.moonSize !== undefined) u.uMoonSize.value = sky.moonSize;
    if (sky.moonDir) u.uMoonDir.value.fromArray(sky.moonDir).normalize();

    // The disc is a property of the weather, not of the sun's position: an
    // overcast sky still has a sun, it just cannot be seen through the deck.
    const sun = preset.sun ?? {};
    if (sun.color !== undefined) u.uSunColor.value.set(sun.color);
    if (sun.disc !== undefined) u.uSunDisc.value = sun.disc;
    if (sun.glow !== undefined) u.uGlow.value = Math.max(sun.glow, sky.glow ?? 0);
    if (sun.dir) u.uSunDir.value.fromArray(sun.dir).normalize();
    return this;
  }

  /** Legacy gradient setter — still used by a couple of prop previews. */
  setColors(topColor, bottomColor, midColor) {
    const u = this.material.uniforms;
    u.uZenith.value.set(topColor);
    u.uHorizon.value.set(bottomColor);
    if (midColor !== undefined) {
      u.uGround.value.set(midColor);
    } else {
      u.uGround.value.copy(u.uHorizon.value).multiplyScalar(0.72);
    }
    return this;
  }

  /**
   * Aim the sun. Called by the zones with the same offset vector that drives
   * the directional light, so the disc and the shadows can never disagree.
   */
  setSun(dir, colorHex, glow = 0.7) {
    this.material.uniforms.uSunDir.value.copy(dir).normalize();
    if (colorHex !== undefined) this.material.uniforms.uSunColor.value.set(colorHex);
    this.material.uniforms.uGlow.value = glow;
    return this;
  }

  /** Keep the dome locked to the camera so it is never walked out of. */
  follow(camera) {
    if (camera) this.mesh.position.copy(camera.position);
    return this;
  }

  update(time) {
    this.material.uniforms.uTime.value = time;
  }
}
