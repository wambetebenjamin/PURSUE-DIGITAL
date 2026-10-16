/**
 * constants.js — Global game constants, narrative copy, and tunables.
 * All world-space units are meters-ish; player is ~1.8 units tall.
 */

export const STATES = Object.freeze({
  ZIKLAG: 'STATE_0_ZIKLAG',
  DESERT: 'STATE_1_DESERT_MARCH',
  EGYPTIAN: 'STATE_2_EGYPTIAN_SERVANT',
  CAMP: 'STATE_3_AMALEKITE_RESCUE',
});

export const STATE_ORDER = Object.freeze([
  STATES.ZIKLAG,
  STATES.DESERT,
  STATES.EGYPTIAN,
  STATES.CAMP,
]);

export const CONTROLS = Object.freeze({
  forward: ['KeyW', 'ArrowUp'],
  back: ['KeyS', 'ArrowDown'],
  left: ['KeyA', 'ArrowLeft'],
  right: ['KeyD', 'ArrowRight'],
  run: ['ShiftLeft', 'ShiftRight'],
  interact: ['KeyE', 'Space'],
});

export const PLAYER = Object.freeze({
  walkSpeed: 5.6,
  runSpeed: 11.2,
  acceleration: 26,
  friction: 14,
  radius: 0.75,
  turnSmoothing: 11,
});

export const STAMINA = Object.freeze({
  max: 100,
  drainPerSecond: 15,
  regenPerSecond: 12,
  regenDelay: 0.65,
  exhaustedResetAt: 28,
});

export const CAMERA = Object.freeze({
  distance: 9.5,
  minDistance: 3.5,
  maxDistance: 15,
  height: 2.1,
  pitchMin: -0.42,
  pitchMax: 1.12,
  sensitivity: 0.0021,
  positionSmoothing: 9,
});

export const VISION = Object.freeze({
  range: 10.5,
  halfAngle: 0.66, // radians (~38°) each side of the guard's facing
  alertDuration: 2.4,
});

export const NARRATIVE = Object.freeze({
  ziklagModal: {
    title: 'The Ashes of Ziklag',
    body: 'Ziklag is destroyed, and the families are taken. Pursue the Amalekites.',
    button: 'Pursue',
  },
  brookModal: {
    title: 'The Brook Besor',
    body: '200 men are too exhausted to cross. You must press forward with 400.',
    button: 'Press Forward',
  },
  servantDialogue: [
    {
      speaker: 'Egyptian Servant',
      body: 'I am an Egyptian, servant to an Amalekite. My master left me three days ago because I fell sick.',
      button: 'Listen',
    },
    {
      speaker: 'You',
      body: '[Give Bread and Water]',
      button: 'Give',
    },
    {
      speaker: 'Egyptian Servant',
      body: 'Thank you. I will lead you down to the raiders.',
      button: 'Follow His Directions',
    },
  ],
  detectionAlert: 'Spotted! The raiders drive you back to the edge of the camp.',
  promptServant: 'Press E to give bread and water.',
  promptWaypoint: 'Press E to depart for the wilderness',
});

export const OBJECTIVES = Object.freeze({
  [STATES.ZIKLAG]: 'Follow the debris trail to the Desert Edge',
  [STATES.DESERT]: 'March across the wilderness — cross the Brook Besor',
  [STATES.EGYPTIAN]: 'Find the Egyptian servant in the field',
  SERVANT_LEAD: 'Follow the arrow to the Amalekite camp',
  [STATES.CAMP]: 'Reach the captives at the heart of the camp',
});
