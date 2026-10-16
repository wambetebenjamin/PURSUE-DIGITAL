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

/** Card theme per phase (drives the modal/score styling). */
export const PHASE_THEMES = Object.freeze({
  [STATES.ZIKLAG]: 'ziklag',
  [STATES.DESERT]: 'desert',
  [STATES.EGYPTIAN]: 'egyptian',
  [STATES.CAMP]: 'camp',
  [STATES.SPOILS]: 'spoils',
  [STATES.GATE]: 'gate',
  [STATES.GILBOA]: 'gilboa',
});

/** Ambient soundscape per phase (see audio/AudioEngine.js). */
export const PHASE_AMBIENCE = Object.freeze({
  [STATES.ZIKLAG]: 'ziklag',
  [STATES.DESERT]: 'desert',
  [STATES.EGYPTIAN]: 'egyptian',
  [STATES.CAMP]: 'camp',
  [STATES.SPOILS]: 'spoils',
  [STATES.GATE]: 'gate',
  [STATES.GILBOA]: 'gilboa',
});

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
    body: 'The Amalekites have burned Ziklag and carried off every woman and child — your wives among them. The men weep until they have no strength left to weep. You are the one who must hold them together.',
    button: 'Take Up the Pursuit',
    cite: '1 Samuel 30:1–6',
  },
  brookModal: {
    title: 'The Brook Besor',
    body: 'Two hundred of the men can go no farther. They sit on the bank with their hands on their knees, and they will not cross. Four hundred of us press on through the torrent — and those two hundred will guard the road behind us.',
    button: 'Cross the Brook',
    cite: '1 Samuel 30:9–10',
  },
  servantDialogue: [
    {
      speaker: 'Egyptian Servant',
      body: 'Three days ago my master left me here. I fell sick on the road, and the Amalekite would not wait for me. I have had nothing to eat since.',
      button: 'Listen',
    },
    {
      speaker: 'You',
      body: '[You break bread and pour water for him]',
      button: 'Give',
    },
    {
      speaker: 'Egyptian Servant',
      body: 'Bless you. I was with them when they raided the Negev of the Cherethites and the land of Judah and Caleb. They are spread across the plain even now — eating, drinking, and dancing over their plunder. I will take you down to them.',
      button: 'Follow His Lead',
      cite: '1 Samuel 30:11–15',
    },
  ],

  // ---- PHASE 4: the 24-hour war of attrition (1 Sam 30:17–19) ----
  battleSequence: [
    {
      title: 'From Twilight to Twilight',
      body: 'You fall upon them at dusk, and the sword does not rest until the evening of the next day. Camp by camp the plunderers are broken, until not one of them is left standing.',
      button: 'Press the Attack',
      cite: '1 Samuel 30:17',
    },
    {
      title: 'Nothing Missing',
      body: 'Wives, sons, daughters, herds, and every last possession — all of it recovered. Not one thing is missing, small or great.',
      button: 'Return to Besor',
      cite: '1 Samuel 30:18–19',
    },
  ],

  // ---- PHASE 5: the spoil divided at Besor (1 Sam 30:21–25) ----
  spoilsDialogue: [
    {
      speaker: 'Some of the Four Hundred',
      body: 'These men stayed behind the brook while we bled. They drew no sword last night. Send them home to their families — the spoil belongs to us.',
      button: 'Hear Them Out',
    },
    {
      speaker: 'You',
      body: 'You will not do this, my brothers. What the LORD has given us, he has given to all of us — those who went down to the battle and those who stayed with the baggage. They will share alike.',
      button: 'Speak the Statute',
      cite: '1 Samuel 30:23–24',
    },
    {
      title: 'A Statute for Israel',
      body: 'From that day on, this became the rule in Israel — a statute and an ordinance, and it stands to this day.',
      button: 'Send Portions to Judah',
      cite: '1 Samuel 30:25',
    },
  ],

  // ---- PHASE 6: the distribution to Judah (1 Sam 30:26–31) ----
  gateDialogue: [
    {
      speaker: 'Elders of Judah',
      body: 'Your messengers arrive with mules heavy-laden. What is all this wealth that you send to your friends?',
      button: 'Receive Them',
    },
    {
      speaker: 'You',
      body: 'A portion of the spoil taken from the enemies of the LORD. For my brothers in every town that sheltered us — to those in Hebron, and in every place where we wandered — send of it.',
      button: 'Present the Tribute',
      cite: '1 Samuel 30:26–28',
    },
    {
      title: 'Gifts from the Spoil',
      body: 'To the elders of Judah, and to every town that stood by David in his wandering, he sends gifts. The fugitive of yesterday stands before his people as a leader.',
      button: 'Meanwhile, on Gilboa…',
      cite: '1 Samuel 30:29–31',
    },
  ],

  // ---- PHASE 7: the fall of Saul (1 Sam 31:1–6) ----
  gilboaDialogue: [
    {
      title: 'Mount Gilboa',
      body: 'Far to the north, on that same day, the battle turns against Israel. The Philistines press up the slopes of Gilboa, and the army of Saul breaks before them.',
      button: 'Ascend the Ridge',
      cite: '1 Samuel 31:1',
    },
    {
      speaker: 'Saul',
      body: 'Draw your sword and run me through — I will not fall into the hands of these uncircumcised. But his armor-bearer would not, for he feared greatly. So the king leans on his own sword…',
      button: 'Stand with the King',
      cite: '1 Samuel 31:3–4',
    },
    {
      title: 'That Same Day',
      body: 'Saul and his sons die on Gilboa, and all his men with them. The crown is heavy — and the LORD has given the kingdom to a man after his own heart. From the ashes of Ziklag, David stands: with all recovered.',
      button: 'Recovered All',
      cite: '1 Samuel 31:6',
    },
  ],

  detectionAlert: 'Spotted! They drive you back to the edge of the camp.',
  promptServant: 'Press E to give him bread and water',
  promptWaypoint: 'Press E to depart for the wilderness',
  promptOutcrop: 'Press E to divide the spoil',
  promptElders: 'Press E to present the tribute',
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
