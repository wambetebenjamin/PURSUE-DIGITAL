/**
 * constants.js — Global game constants, narrative copy, and tunables.
 * All world-space units are meters-ish; player is ~1.8 units tall.
 *
 * The seven phases follow the production master prompt pipeline for
 * 1 Samuel 30–31 (see docs/PRODUCTION_BIBLE.md for the full mapping).
 */

export const STATES = Object.freeze({
  ZIKLAG: 'STATE_0_ZIKLAG',
  DESERT: 'STATE_1_DESERT_MARCH',
  EGYPTIAN: 'STATE_2_EGYPTIAN_SERVANT',
  CAMP: 'STATE_3_AMALEKITE_RESCUE',
  SPOILS: 'STATE_4_SPOILS_DIVISION',
  GATE: 'STATE_5_DISTRIBUTION',
  GILBOA: 'STATE_6_GILBOA_EPILOGUE',
});

export const STATE_ORDER = Object.freeze([
  STATES.ZIKLAG,
  STATES.DESERT,
  STATES.EGYPTIAN,
  STATES.CAMP,
  STATES.SPOILS,
  STATES.GATE,
  STATES.GILBOA,
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
    title: 'Ziklag in Ruins',
    body: 'The outpost smolders. The families are gone, carried off by the Amalekites. Pursue them — recover all.',
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

  // ---- PHASE 4: the 24-hour war of attrition (1 Sam 30:17–19) ----
  battleSequence: [
    {
      title: 'From Twilight to Twilight',
      body: 'David struck the raiders from twilight until the evening of the next day. Not a man of them escaped, except four hundred young men who fled on camels.',
      button: 'Press the Attack',
    },
    {
      title: 'All Recovered',
      body: 'David recovered all that the Amalekites had taken — and rescued his two wives. Nothing was missing, small or great.',
      button: 'Return to Besor',
    },
  ],

  // ---- PHASE 5: the spoil divided at Besor (1 Sam 30:21–25) ----
  spoilsDialogue: [
    {
      speaker: 'Some of the 400',
      body: 'They did not cross the brook with us! They shall not share the spoil — send them away with their families.',
      button: 'Listen',
    },
    {
      speaker: 'You',
      body: 'My brothers, you shall not do so with what the LORD has given us. As his share that goes down to the battle, so shall his share that stays by the baggage — they shall share alike.',
      button: 'Deliver the Statute',
    },
    {
      title: 'A Statute for Israel',
      body: 'And from that day forward it became a statute and a rule for Israel.',
      button: 'Send Portions to Judah',
    },
  ],

  // ---- PHASE 6: the distribution to Judah (1 Sam 30:26–31) ----
  gateDialogue: [
    {
      speaker: 'Elders of Judah',
      body: 'What wealth is this that you send to your friends?',
      button: 'Receive Them',
    },
    {
      speaker: 'You',
      body: 'A portion of the spoil of the enemies of the LORD — for our brothers in every town where I and my men found refuge.',
      button: 'Present the Tribute',
    },
    {
      title: 'Gifts from the Spoil',
      body: 'To those in Hebron and in every place where David and his men had roamed, he sent gifts.',
      button: 'Meanwhile, on Gilboa…',
    },
  ],

  // ---- PHASE 7: the fall of Saul (1 Sam 31:1–6) ----
  gilboaDialogue: [
    {
      title: 'Mount Gilboa',
      body: 'The Philistines fought against Israel, and the men of Israel fled before them and fell slain on Mount Gilboa.',
      button: 'Ascend the Ridge',
    },
    {
      speaker: 'Saul',
      body: 'Draw your sword and thrust me through, lest these uncircumcised come upon me and abuse me.',
      button: 'Stand with the King',
    },
    {
      title: 'The Same Day',
      body: 'So Saul died, and his three sons, and his armor-bearer, and all his men, that same day together.',
      button: 'Recovered All',
    },
  ],

  detectionAlert: 'Spotted! The raiders drive you back to the edge of the camp.',
  promptServant: 'Press E to give bread and water.',
  promptWaypoint: 'Press E to depart for the wilderness',
  promptOutcrop: 'Press E to divide the spoil with the 200',
  promptElders: 'Press E to present the tribute to the elders of Judah',
  promptSaul: 'Press E to approach King Saul',
});

export const OBJECTIVES = Object.freeze({
  [STATES.ZIKLAG]: 'Follow the debris trail to the Desert Edge',
  [STATES.DESERT]: 'March across the wilderness — cross the Brook Besor',
  [STATES.EGYPTIAN]: 'Find the Egyptian servant in the field',
  SERVANT_LEAD: 'Follow the arrow to the Amalekite camp',
  [STATES.CAMP]: 'Reach the captives at the heart of the camp',
  [STATES.SPOILS]: 'Stand on the outcrop and divide the spoil at Besor',
  [STATES.GATE]: 'Deliver portions of the spoil to the elders of Judah',
  [STATES.GILBOA]: 'Climb the ridge to King Saul on Mount Gilboa',
});
