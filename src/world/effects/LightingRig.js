/**
 * LightingRig.js — one call that builds the light of a phase.
 *
 * Every zone used to hand-tune its own hemisphere and directional light, which
 * is how six different keys ended up with six different ideas of what "4000 K"
 * looks like. The rig now reads the phase's atmosphere and derives the rig from
 * it, so the visible sun in the dome and the light that casts the shadows are
 * the same light:
 *
 *   sky    — the analytic dome (see effects/SkyDome.js)
 *   hemi   — sky-coloured fill from above, ground bounce from below
 *   sun    — the key, warm when low and cool overcast, aimed from the preset
 *
 * The rig returns `offset`: the sun's position relative to the player, which
 * World.update re-applies every frame to keep the shadow frustum on the action.
 */
import * as THREE from 'three';
import { QUALITY } from '../../core/quality.js';
import { SkyDome } from './SkyDome.js';

/**
 * @param {object} atmo preset from world/atmospheres.js
 * @param {{shadowExtent?: number, shadowFar?: number, distance?: number}} [opts]
 */
export function createLightingRig(atmo, opts = {}) {
  const extent = opts.shadowExtent ?? 44;
  const far = opts.shadowFar ?? 180;
  const distance = opts.distance ?? 62;

  const sky = new SkyDome(atmo);

  const hemi = new THREE.HemisphereLight(atmo.hemi.sky, atmo.hemi.ground, atmo.hemi.intensity);
  hemi.name = 'PHASE_SKY_FILL';

  const dir = new THREE.Vector3().fromArray(atmo.sun.dir).normalize();
  const offset = dir.clone().multiplyScalar(distance);

  const sun = new THREE.DirectionalLight(atmo.sun.color, atmo.sun.intensity);
  sun.name = 'PHASE_SUN';
  sun.position.copy(offset);
  sun.castShadow = true;
  sun.shadow.mapSize.set(QUALITY.shadowMap, QUALITY.shadowMap);
  sun.shadow.camera.near = 1;
  sun.shadow.camera.far = far;
  sun.shadow.camera.left = -extent;
  sun.shadow.camera.right = extent;
  sun.shadow.camera.top = extent;
  sun.shadow.camera.bottom = -extent;
  // A low sun rakes across the ground and needs a touch more bias; a high sun
  // can take a tighter one, which keeps contact shadows crisp.
  sun.shadow.bias = atmo.sun.dir[1] < 0.35 ? -0.0014 : -0.0009;
  sun.shadow.normalBias = 0.022;
  sun.userData.offset = offset;

  // the dome's disc is aimed with the same vector the light uses
  sky.setSun(offset, atmo.sun.color, atmo.sun.disc > 0 ? atmo.sun.glow ?? 0.7 : 0.06);

  return { sky, hemi, sun, offset, atmosphere: atmo };
}

/**
 * Attach a rig to a zone group. Keeps the plumbing identical in all seven
 * zones: dome mesh, fill light, key light and its target.
 */
export function attachRig(group, rig) {
  group.add(rig.sky.mesh, rig.hemi, rig.sun, rig.sun.target);
  return rig;
}
