/**
 * pbr.js — the character / gear material library for the cinematic pass.
 *
 * These are real PBR materials (MeshPhysicalMaterial) rather than the flat
 * Lambert palette the world uses, because characters are read close-up:
 *   • skin   — albedo + pore bump + a wrap/fresnel subsurface term injected
 *              into the lighting (`uSSSColor`), plus a thin sweat clearcoat.
 *   • hair   — anisotropic shading with a strand alpha map (hair cards).
 *   • leather — cracked bump map, dust in the grain, low sheen.
 *   • cloth  — woven bump, sheen lobe, sun-faded albedo.
 *   • metal  — hammered/ scuffed roughness map, dented bump, high env.
 *
 * The subsurface term is an approximation (rim translucency + warm bounce),
 * not screen-space SSS, but at gameplay distance it gives the ears, nose,
 * lips and shadowed jaw the soft waxy bleed that reads as living skin.
 */
import * as THREE from 'three';
import {
  poresMap, skinAlbedoMap, strandAlphaMap, strandColorMap,
  leatherBumpMap, leatherAlbedoMap, metalBumpMap, metalRoughMap,
  weaveBumpMap, clothAlbedoMap,
} from './surfaces.js';

const cache = new Map();
function cached(key, factory) {
  if (!cache.has(key)) cache.set(key, factory());
  return cache.get(key);
}

/**
 * Inject the subsurface rim term into a MeshPhysicalMaterial.
 * Uses `normal` + `vViewPosition` (both in view space) so it works on every
 * primitive in the rig without extra varyings.
 */
function addSubsurface(mat, color, strength, dustColor, dustAmount) {
  mat.onBeforeCompile = (shader) => {
    shader.uniforms.uSSSColor = { value: new THREE.Color(color) };
    shader.uniforms.uSSSStrength = { value: strength };
    shader.uniforms.uDustColor = { value: new THREE.Color(dustColor) };
    shader.uniforms.uDustAmount = { value: dustAmount };

    shader.vertexShader = shader.vertexShader
      .replace('#include <common>', '#include <common>\nvarying vec3 vSssPos;')
      .replace('#include <project_vertex>', `#include <project_vertex>
        vSssPos = -mvPosition.xyz;`);

    shader.fragmentShader = shader.fragmentShader
      .replace('#include <common>', `#include <common>
        uniform vec3 uSSSColor;
        uniform float uSSSStrength;
        uniform vec3 uDustColor;
        uniform float uDustAmount;
        varying vec3 vSssPos;`)
      .replace('#include <tonemapping_fragment>', `
        {
          // NB: opaque_fragment has already written gl_FragColor — the
          // subsurface term must be applied to that, not to outgoingLight.
          vec3 V = normalize(vSssPos);
          vec3 N = normalize(normal);
          float fres = 1.0 - saturate(dot(N, V));
          // waxy bleed at grazing angles (ears, nose, lips, shadowed jaw)
          gl_FragColor.rgb += uSSSColor * pow(fres, 2.6) * uSSSStrength;
          // warm light bleeding through the thin parts
          float thin = saturate(0.5 + 0.5 * dot(N, vec3(0.0, 0.65, 0.75)));
          gl_FragColor.rgb += uSSSColor * thin * uSSSStrength * 0.16;
          // fine desert dust settled on upward-facing skin
          float up = saturate(0.5 + 0.5 * dot(N, vec3(0.0, 0.85, 0.3)));
          gl_FragColor.rgb = mix(gl_FragColor.rgb, uDustColor, up * uDustAmount);
        }
        #include <tonemapping_fragment>`);
  };
  mat.customProgramCacheKey = () => `sss-${strength}-${dustAmount}`;
  return mat;
}

// ================================================================ skin
export function skinMaterial({
  albedo = '#a06b45', sss = 0xff7c52, sssStrength = 0.32,
  dust = 0.05, roughness = 0.66, clearcoat = 0.18,
} = {}) {
  return cached(`skin-${albedo}-${sssStrength}`, () => {
    const mat = new THREE.MeshPhysicalMaterial({
      color: 0xffffff,
      map: skinAlbedoMap(albedo),
      bumpMap: poresMap([2, 2]),
      bumpScale: 0.0035,
      roughness,
      metalness: 0,
      clearcoat,
      clearcoatRoughness: 0.38,
      sheen: 0.22,
      sheenColor: new THREE.Color(0x8a5a44),
      sheenRoughness: 0.9,
      envMapIntensity: 0.85,
    });
    return addSubsurface(mat, sss, sssStrength, 0xc8ae86, dust);
  });
}

// ================================================================ hair
export function hairMaterial({ color = '#241a12', anisotropic = 0.75, rough = 0.52 } = {}) {
  return cached(`hair-${color}`, () => new THREE.MeshPhysicalMaterial({
    color: 0xffffff,
    map: strandColorMap(color),
    bumpMap: strandAlphaMap(),
    bumpScale: 0.0025,
    roughness: rough,
    metalness: 0,
    anisotropy: anisotropic,
    anisotropyRotation: Math.PI * 0.5,
    sheen: 0.6,
    sheenColor: new THREE.Color(color),
    sheenRoughness: 0.6,
    envMapIntensity: 0.7,
  }));
}

/** Card material: strand alpha cut-out, double sided, dust in the tips. */
export function hairCardMaterial({ color = '#241a12', rough = 0.56 } = {}) {
  return cached(`hairCard-${color}`, () => {
    const mat = new THREE.MeshPhysicalMaterial({
      color: 0xffffff,
      map: strandColorMap(color),
      alphaMap: strandAlphaMap(),
      alphaTest: 0.34,
      transparent: false,
      side: THREE.DoubleSide,
      bumpMap: strandAlphaMap(),
      bumpScale: 0.0022,
      roughness: rough,
      metalness: 0,
      anisotropy: 0.6,
      sheen: 0.75,
      sheenColor: new THREE.Color(0xc4a882),
      sheenRoughness: 0.5,
      envMapIntensity: 0.65,
    });
    return addSubsurface(mat, 0x2a1a10, 0.06, 0xa08a6a, 0.18);
  });
}

// ================================================================ leather
export function leatherMaterial({ color = 0x3a2c20, rough = 0.78, sheen = 0.2 } = {}) {
  return cached(`leather-${color}-${rough}`, () => new THREE.MeshPhysicalMaterial({
    color: 0xffffff,
    map: leatherAlbedoMap(color),
    bumpMap: leatherBumpMap([3, 3]),
    bumpScale: 0.007,
    roughness: rough,
    metalness: 0,
    sheen,
    sheenColor: new THREE.Color(0x8a7258),
    sheenRoughness: 0.75,
    envMapIntensity: 0.8,
  }));
}

// ================================================================ cloth
export function clothMaterial({ color = 0x8c5a38, rough = 0.92, sheen = 0.55, weave = 6 } = {}) {
  return cached(`cloth-${color}-${sheen}`, () => new THREE.MeshPhysicalMaterial({
    color: 0xffffff,
    map: clothAlbedoMap(color),
    bumpMap: weaveBumpMap([weave, weave]),
    bumpScale: 0.005,
    roughness: rough,
    metalness: 0,
    sheen,
    sheenColor: new THREE.Color(0xd8c8a8),
    sheenRoughness: 0.7,
    envMapIntensity: 0.65,
  }));
}

// ================================================================ metal
export function metalMaterial({
  color = 0x8a5c28, metalness = 0.92, rough = 0.42, bump = 0.02, patina = 0.15,
} = {}) {
  return cached(`metal-${color}-${rough}-${bump}`, () => {
    const mat = new THREE.MeshPhysicalMaterial({
      color,
      metalness,
      roughness: rough,
      roughnessMap: metalRoughMap([2, 2]),
      bumpMap: metalBumpMap([2, 2]),
      bumpScale: bump * 0.35,
      envMapIntensity: 1.25,
      sheen: patina,
      sheenColor: new THREE.Color(0x4a6a58),
      sheenRoughness: 0.85,
    });
    return mat;
  });
}
