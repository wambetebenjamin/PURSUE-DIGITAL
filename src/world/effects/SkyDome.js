/**
 * SkyDome.js — cheap gradient sky (one inverted sphere, two-color shader).
 * Each zone owns its own dome so atmosphere swaps instantly with the FSM.
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
  uniform float uExponent;
  uniform vec3 uSunDir;
  uniform vec3 uSunColor;
  uniform float uSunGlow;
  uniform float uTime;
  varying vec3 vDir;
  void main() {
    vec3 d = normalize(vDir);
    float h = clamp(d.y * 0.5 + 0.5, 0.0, 1.0);
    vec3 col = mix(uBottom, uTop, pow(h, uExponent));

    // the big Miami sun: hard disc + wide halo
    float sd = max(dot(d, normalize(uSunDir)), 0.0);
    col += uSunColor * (pow(sd, 900.0) * 2.4 + pow(sd, 22.0) * uSunGlow);

    // streaky sunset cloud bands
    float bands = sin(d.y * 24.0 + sin(d.x * 5.0 + uTime * 0.015) * 2.6 + d.z * 3.2);
    float mask = smoothstep(0.02, 0.22, d.y) * (1.0 - smoothstep(0.5, 0.95, d.y));
    col += uSunColor * smoothstep(0.84, 1.0, bands) * mask * 0.14;

    gl_FragColor = vec4(col, 1.0);
  }
`;

export class SkyDome {
  constructor(topColor, bottomColor, exponent = 0.75) {
    this.material = new THREE.ShaderMaterial({
      uniforms: {
        uTop: { value: new THREE.Color(topColor) },
        uBottom: { value: new THREE.Color(bottomColor) },
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

  setColors(topColor, bottomColor) {
    this.material.uniforms.uTop.value.set(topColor);
    this.material.uniforms.uBottom.value.set(bottomColor);
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
