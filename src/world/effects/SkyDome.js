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
  varying vec3 vDir;
  void main() {
    float h = clamp(vDir.y * 0.5 + 0.5, 0.0, 1.0);
    vec3 col = mix(uBottom, uTop, pow(h, uExponent));
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
}
