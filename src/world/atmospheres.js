/**
 * atmospheres.js — the air of each phase, as data.
 *
 * Pass 11 replaced the neon "Vice" sky stops with a physically motivated
 * atmosphere per phase. Every value here is a lighting decision, not a paint
 * colour: the sun's real elevation and azimuth, the temperature of its light,
 * the turbidity of the air (dust, smoke, sea haze), the sky's zenith and
 * horizon radiance, and the fog that carries the horizon into the ground.
 *
 * The seven phases cover one day and a night of 1 Samuel 30–31:
 *
 *   Ziklag    late afternoon, smoke still standing over the ruins
 *   Desert    white noon, high sun, dust-loaded air
 *   Egyptian  mid-afternoon, warm but clear
 *   Camp      night, moon high in the east, thin silver haze
 *   Spoils    sunset, sun on the horizon at Besor
 *   Gate      afternoon at Hebron, pale limestone bounce
 *   Gilboa    overcast before the storm, cloud diffusing every shadow
 *
 * Colours are hex (sRGB) — the sky shader reads them as linear radiance.
 */

/**
 * @typedef {Object} Atmosphere
 * @property {string} name
 * @property {string} label      human readable, used by the bible and tests
 * @property {Object} sun        {dir:[x,y,z], color, intensity, disc, glow}
 * @property {Object} hemi       {sky, ground, intensity}
 * @property {Object} sky        {zenith, horizon, ground, turbidity, rayleigh,
 *                                glow, cloud, cloudTint, stars, moon, moonDir,
 *                                moonColor, moonSize}
 * @property {Object} fog        {color, density}
 * @property {string} grade      key into PostFX.GRADES
 */

/** Normalise a direction tuple and scale it. */
function dir(x, y, z, scale = 1) {
  const l = Math.hypot(x, y, z) || 1;
  return [x / l * scale, y / l * scale, z / l * scale];
}

export const ATMOSPHERE = Object.freeze({
  ziklag: Object.freeze({
    name: 'ziklag',
    label: 'Late afternoon over the ashes — smoke still in the air',
    // Sun 16° above the west-south-west horizon: long raking shadows.
    sun: Object.freeze({
      dir: Object.freeze(dir(-0.78, 0.29, 0.55, 62)),
      color: 0xffb877,
      intensity: 2.05,
      disc: 1.0,
      glow: 0.9,
    }),
    hemi: Object.freeze({ sky: 0x8ea6c4, ground: 0xa8774e, intensity: 0.92 }),
    sky: Object.freeze({
      zenith: 0x44709f,
      horizon: 0xe8b183,
      ground: 0x7a5f4a,
      turbidity: 9.5, // burning city: heavy scattering
      rayleigh: 1.15,
      glow: 1.0,
      cloud: 0.34,
      cloudTint: 0xf6d2ab,
      stars: 0.0,
      moon: 0.0,
      moonDir: Object.freeze(dir(-0.4, 0.5, 0.6)),
      moonColor: 0xd8e4ff,
      moonSize: 0.9996,
    }),
    fog: Object.freeze({ color: 0xdcb493, density: 0.0105 }),
    grade: 'sunset',
  }),

  desert: Object.freeze({
    name: 'desert',
    label: 'White noon on the march — dust and glare',
    sun: Object.freeze({
      dir: Object.freeze(dir(0.42, 0.93, 0.30, 62)), // sun almost overhead
      color: 0xfff4dd,
      intensity: 3.15,
      disc: 1.0,
      glow: 0.55,
    }),
    hemi: Object.freeze({ sky: 0xc4d8ea, ground: 0xd8bd8e, intensity: 1.28 }),
    sky: Object.freeze({
      zenith: 0x2f76b8,
      horizon: 0xe4ddca,
      ground: 0xcdbfa4,
      turbidity: 7.0,
      rayleigh: 1.35,
      glow: 0.5,
      cloud: 0.12,
      cloudTint: 0xfbf6ec,
      stars: 0.0,
      moon: 0.0,
      moonDir: Object.freeze(dir(0.3, 0.4, -0.8)),
      moonColor: 0xd8e4ff,
      moonSize: 0.9996,
    }),
    fog: Object.freeze({ color: 0xe0d8c6, density: 0.0062 }),
    grade: 'blaze',
  }),

  egyptian: Object.freeze({
    name: 'egyptian',
    label: 'Mid-afternoon in the Egyptian’s field — warm and clear',
    sun: Object.freeze({
      dir: Object.freeze(dir(-0.62, 0.56, 0.55, 62)),
      color: 0xffdcab,
      intensity: 2.35,
      disc: 1.0,
      glow: 0.75,
    }),
    hemi: Object.freeze({ sky: 0xa8c4e0, ground: 0xc9a06a, intensity: 1.05 }),
    sky: Object.freeze({
      zenith: 0x4080bd,
      horizon: 0xe8d2b0,
      ground: 0xb59a7a,
      turbidity: 5.5,
      rayleigh: 1.2,
      glow: 0.8,
      cloud: 0.22,
      cloudTint: 0xf8e6cc,
      stars: 0.0,
      moon: 0.0,
      moonDir: Object.freeze(dir(-0.4, 0.5, 0.6)),
      moonColor: 0xd8e4ff,
      moonSize: 0.9996,
    }),
    fog: Object.freeze({ color: 0xe2cfae, density: 0.0068 }),
    grade: 'gold',
  }),

  camp: Object.freeze({
    name: 'camp',
    label: 'Night in the Amalekite camp — moon in the east, fires below',
    // The key light is the moon: 34° up in the east, cool and weak.
    sun: Object.freeze({
      dir: Object.freeze(dir(-0.34, 0.56, 0.76, 62)),
      color: 0xa9bfe8,
      intensity: 0.62,
      disc: 0.0, // the moon is drawn by the sky, not the sun disc
      glow: 0.35,
    }),
    hemi: Object.freeze({ sky: 0x24324e, ground: 0x2a1f1a, intensity: 0.55 }),
    sky: Object.freeze({
      zenith: 0x0a1224,
      horizon: 0x2a3550,
      ground: 0x141a26,
      turbidity: 2.2,
      rayleigh: 0.5,
      glow: 0.25,
      cloud: 0.18,
      cloudTint: 0x4a5670,
      stars: 1.0,
      moon: 0.85,
      moonDir: Object.freeze(dir(-0.34, 0.56, 0.76)),
      moonColor: 0xdfe8ff,
      moonSize: 0.99935,
    }),
    fog: Object.freeze({ color: 0x1d2740, density: 0.0125 }),
    grade: 'night',
  }),

  spoils: Object.freeze({
    name: 'spoils',
    label: 'Sunset at the brook Besor — the sun touching the horizon',
    sun: Object.freeze({
      dir: Object.freeze(dir(0.30, 0.10, -0.95, 62)), // 6° above the horizon
      color: 0xffc48a,
      intensity: 2.6,
      disc: 1.0,
      glow: 1.0,
    }),
    hemi: Object.freeze({ sky: 0x8fa2c8, ground: 0xc08a5c, intensity: 0.86 }),
    sky: Object.freeze({
      zenith: 0x3c5f96,
      horizon: 0xf3b177,
      ground: 0x6d5138,
      turbidity: 10.5,
      rayleigh: 1.3,
      glow: 1.0,
      cloud: 0.4,
      cloudTint: 0xf7c39a,
      stars: 0.06,
      moon: 0.0,
      moonDir: Object.freeze(dir(0.4, 0.5, 0.6)),
      moonColor: 0xd8e4ff,
      moonSize: 0.9996,
    }),
    fog: Object.freeze({ color: 0xe3ab84, density: 0.0072 }),
    grade: 'ember',
  }),

  gate: Object.freeze({
    name: 'gate',
    label: 'Afternoon at the gate of Hebron — pale limestone light',
    sun: Object.freeze({
      dir: Object.freeze(dir(-0.55, 0.66, 0.52, 62)),
      color: 0xffe7bd,
      intensity: 2.5,
      disc: 1.0,
      glow: 0.7,
    }),
    hemi: Object.freeze({ sky: 0xb4cbe4, ground: 0xd2b68c, intensity: 1.1 }),
    sky: Object.freeze({
      zenith: 0x4382ba,
      horizon: 0xe6dac2,
      ground: 0xc0ab8e,
      turbidity: 6.0,
      rayleigh: 1.25,
      glow: 0.75,
      cloud: 0.26,
      cloudTint: 0xfaf0da,
      stars: 0.0,
      moon: 0.0,
      moonDir: Object.freeze(dir(-0.4, 0.5, 0.6)),
      moonColor: 0xd8e4ff,
      moonSize: 0.9996,
    }),
    fog: Object.freeze({ color: 0xdfd2b6, density: 0.0060 }),
    grade: 'clear',
  }),

  gilboa: Object.freeze({
    name: 'gilboa',
    label: 'Overcast on the ridge of Gilboa — the storm not yet broken',
    sun: Object.freeze({
      dir: Object.freeze(dir(0.15, 0.74, 0.30, 62)),
      color: 0xdae2ee,
      intensity: 0.85, // diffused by cloud: shadows nearly gone
      disc: 0.0,
      glow: 0.2,
    }),
    hemi: Object.freeze({ sky: 0x8d99a6, ground: 0x4e565e, intensity: 1.15 }),
    sky: Object.freeze({
      zenith: 0x505c68,
      horizon: 0x9ea8b2,
      ground: 0x5c6470,
      turbidity: 4.0,
      rayleigh: 0.55,
      glow: 0.3,
      cloud: 0.92,
      cloudTint: 0xc2ccd6,
      stars: 0.0,
      moon: 0.0,
      moonDir: Object.freeze(dir(0.3, 0.4, 0.8)),
      moonColor: 0xd8e4ff,
      moonSize: 0.9996,
    }),
    fog: Object.freeze({ color: 0x9ea8b2, density: 0.0165 }),
    grade: 'storm',
  }),
});

/** Ordered list, useful for docs and tests. */
export const ATMOSPHERE_KEYS = Object.freeze(Object.keys(ATMOSPHERE));

/** Look up by zone state name (`STATE_0_ZIKLAG` → `ziklag`). */
export function atmosphereFor(stateName) {
  const short = String(stateName).toLowerCase().replace(/^state_\d+_/, '').split('_')[0];
  return ATMOSPHERE[short] ?? ATMOSPHERE.ziklag;
}
