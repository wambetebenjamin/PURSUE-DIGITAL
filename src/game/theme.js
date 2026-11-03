/**
 * game/theme.js — the look of the 2D camp.
 *
 * The palette is the reference's own, recovered in pass 17 and already used
 * by the 3D build: gold-bronze on deep oxblood, with the earth of Judah for
 * the ground and parchment for the lettering. Nothing here is a texture
 * file — every shape is drawn with Phaser's Graphics, so the camp has no
 * image dependencies at all.
 */

/** The palette, as numbers for Graphics and strings for Text. */
export const COLORS = Object.freeze({
  ink: 0x251116,        // the dark above the camp
  night: 0x1b0c10,      // deeper still, for the sky
  oxblood: 0x412225,    // the tent cloth, the panels
  oxbloodLight: 0x502b26,
  earth: 0x6a4d39,      // the ground
  earthLight: 0x795e46, // the trodden path
  bronze: 0xb48357,
  bronzeLight: 0xdcb490,
  gold: 0xe8b15c,       // the prize, the banners
  parchment: 0xf7eab4,  // the lettering
  highlight: 0xffffde,  // the brightest ink
  ember: 0xad724a,      // firelight, the dust of the marching
  blood: 0x8c2f2a,     // defeat, the losses
});

/** The same palette as CSS colours, for the few DOM bits we need. */
export const CSS = Object.freeze({
  ink: '#251116',
  oxblood: '#412225',
  earth: '#6a4d39',
  earthLight: '#795e46',
  bronze: '#b48357',
  bronzeLight: '#dcb490',
  gold: '#e8b15c',
  parchment: '#f7eab4',
  highlight: '#ffffde',
  ember: '#ad724a',
  blood: '#8c2f2a',
});

/** The type. The page already loads these faces locally. */
export const FONT = Object.freeze({
  serif: '"EB Garamond", Georgia, "Times New Roman", serif',
  sans: 'Inter, "Helvetica Neue", Arial, sans-serif',
});

/** Text styles, kept in one place so the two scenes agree. */
export const TEXT = Object.freeze({
  title: { fontFamily: FONT.serif, fontSize: '44px', color: CSS.parchment, fontStyle: 'bold' },
  subtitle: { fontFamily: FONT.serif, fontSize: '19px', color: CSS.bronzeLight },
  building: { fontFamily: FONT.serif, fontSize: '18px', color: CSS.parchment },
  buildingDim: { fontFamily: FONT.serif, fontSize: '15px', color: CSS.bronze },
  readout: { fontFamily: FONT.sans, fontSize: '20px', color: CSS.parchment },
  readoutLabel: { fontFamily: FONT.sans, fontSize: '11px', color: CSS.bronze },
  button: { fontFamily: FONT.sans, fontSize: '17px', color: CSS.highlight },
  buttonDim: { fontFamily: FONT.sans, fontSize: '14px', color: CSS.bronzeLight },
  body: { fontFamily: FONT.serif, fontSize: '20px', color: CSS.parchment },
  big: { fontFamily: FONT.serif, fontSize: '54px', color: CSS.gold, fontStyle: 'bold' },
  small: { fontFamily: FONT.sans, fontSize: '13px', color: CSS.bronze },
});

/**
 * The base size of each style, in pixels. The scenes multiply these by the
 * camp's scale, so the lettering shrinks with the canvas instead of
 * swallowing a phone.
 */
export const SIZES = Object.freeze({
  title: 44,
  subtitle: 19,
  building: 18,
  buildingDim: 15,
  readout: 20,
  readoutLabel: 11,
  button: 17,
  buttonDim: 14,
  body: 20,
  big: 54,
  small: 13,
});
