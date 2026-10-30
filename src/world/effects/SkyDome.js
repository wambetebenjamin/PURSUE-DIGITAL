/**
 * SkyDome.js — the Vice City sky: one inverted sphere, three-stop gradient.
 *
 * The classic Miami look is the sky: deep saturated zenith → electric mid
 * band → hot pastel horizon haze, with a huge sun disc hanging in it.
 * Each zone owns its dome so the mood swaps instantly with the FSM.
 */
import * as THREE from 'three';

const VERT = /* glsl */ `
  varying vec3 vDir;
  void main() {
    vDir = normalize(position);
    gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
  }
`;

const FRAG = /* glsl */ `
  uniform vec3 uTop;
  uniform vec3 uBottom;
  uniform vec3 uMid;
  uniform float uExponent;
  uniform vec3 uSunDir;
  uniform vec3 uSunColor;
  uniform float uSunGlow;
  uniform float uTime;
  varying vec3 vDir;
  void main() {
    vec3 d = normalize(vDir);
    float h = clamp(d.y * 0.5 + 0.5, 0.0, 1.0);

    // three-stop gradient: horizon → mid band → zenith (the Miami sky)
    float t = pow(h, uExponent);
    vec3 col = mix(uBottom, uMid, smoothstep(0.0, 0.55, t));
    col = mix(col, uTop, smoothstep(0.45, 1.0, t));

    // hot horizon haze band — the light bleeds into everything
    float haze = smoothstep(0.30, 0.0, d.y) * smoothstep(-0.25, 0.02, d.y);
    col = mix(col, uBottom * 1.18 + uSunColor * 0.10, haze * 0.55);

    // the big Miami sun: fat disc + wide halo
    float sd = max(dot(d, normalize(uSunDir)), 0.0);
    col += uSunColor * (pow(sd, 220.0) * 3.2 + pow(sd, 9.0) * uSunGlow * 1.6);

    // streaky sunset bands near the horizon
    float bands = sin(d.y * 24.0 + sin(d.x * 5.0 + uTime * 0.015) * 2.6 + d.z * 3.2);
    float bandMask = smoothstep(0.02, 0.2, d.y) * (1.0 - smoothstep(0.42, 0.8, d.y));
    col += uSunColor * smoothstep(0.84, 1.0, bands) * bandMask * 0.16;

    // drifting soft cloud layers (slow, layered sine noise)
    float drift = uTime * 0.008;
    float n = sin(d.x * 5.5 + drift * 3.0) * 0.5
            + sin(d.x * 11.0 + d.z * 6.5 + drift * 5.0) * 0.3
            + sin(d.y * 18.0 + d.z * 9.0 + drift * 2.0) * 0.2;
    float cloud = smoothstep(0.18, 0.72, n * 0.5 + 0.5);
    float cloudMask = smoothstep(0.08, 0.3, d.y) * (1.0 - smoothstep(0.6, 1.0, d.y));
    vec3 cloudCol = mix(vec3(1.0), uSunColor, 0.45);
    col = mix(col, cloudCol, cloud * cloudMask * 0.42);

    gl_FragColor = vec4(col, 1.0);
  }
`;

export class SkyDome {
  constructor(topColor, bottomColor, exponent = 0.75, midColor = null) {
    // mid band defaults to the halfway mix of the two stops
    const mid = midColor !== null
      ? midColor
      : new THREE.Color(topColor).lerp(new THREE.Color(bottomColor), 0.5).getHex();

    this.material = new THREE.ShaderMaterial({
      uniforms: {
        uTop: { value: new THREE.Color(topColor) },
        uBottom: { value: new THREE.Color(bottomColor) },
        uMid: { value: new THREE.Color(mid) },
        uExponent: { value: exponent },
        uSunDir: { value: new THREE.Vector3(0, 1, 0) },
        uSunColor: { value: new THREE.Color(0xffc890) },
        uSunGlow: { value: 0.35 },
        uTime: { value: 0 },
      },
      vertexShader: VERT,
      fragmentShader: FRAG,
      side: THREE.BackSide,
      depthWrite: false,
    });
    this.mesh = new THREE.Mesh(new THREE.SphereGeometry(420, 24, 14), this.material);
    this.mesh.frustumCulled = false;
    this.mesh.renderOrder = -10;
  }

  setColors(topColor, bottomColor, midColor) {
    this.material.uniforms.uTop.value.set(topColor);
    this.material.uniforms.uBottom.value.set(bottomColor);
    if (midColor !== undefined) {
      this.material.uniforms.uMid.value.set(midColor);
    } else {
      this.material.uniforms.uMid.value
        .set(topColor)
        .lerp(new THREE.Color(bottomColor), 0.5);
    }
  }

  /** Place the sun disc in the dome (pass the directional-light offset). */
  setSun(dir, colorHex, glow = 0.4) {
    this.material.uniforms.uSunDir.value.copy(dir).normalize();
    this.material.uniforms.uSunColor.value.set(colorHex);
    this.material.uniforms.uSunGlow.value = glow;
  }

  update(time) {
    this.material.uniforms.uTime.value = time;
  }
}
