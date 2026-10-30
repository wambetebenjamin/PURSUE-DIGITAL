/**
 * items.js — what David is carrying, as data.
 *
 * The data model follows the category/slot/rarity structure used by the
 * fantasy-RPG icon packs (armour broken into head / chest / arms / belt /
 * feet / cloak plus weapon and off-hand, then provisions, spoil and relics,
 * each item stamped with a rarity that the frame expresses). Keeping it as
 * plain data means the whole satchel — slots, tooltips, rarity frames — is
 * rebuilt from this file alone.
 *
 * `icon` names a painter in ui/icons.js. To use a licensed PNG pack instead,
 * set `file` to a URL and the satchel will render an <img> and skip the
 * painter (see docs/PRODUCTION_BIBLE.md §10).
 */

/** Rarity ladder — the frame, not the art, carries it. */
export const RARITY = Object.freeze({
  worn: { label: 'worn', note: 'carried through the Negev' },
  fine: { label: 'fine', note: 'taken up when the pursuit began' },
  royal: { label: 'royal', note: 'the weight of a kingdom' },
});

/** The eight things a man wears into the wilderness. */
export const SLOTS = Object.freeze([
  { key: 'head', label: 'Head' },
  { key: 'chest', label: 'Chest' },
  { key: 'arms', label: 'Arms' },
  { key: 'belt', label: 'Belt' },
  { key: 'feet', label: 'Feet' },
  { key: 'cloak', label: 'Cloak' },
  { key: 'weapon', label: 'Hand' },
  { key: 'offhand', label: 'Off-hand' },
]);

export const EQUIPPED = Object.freeze([
  {
    slot: 'head', icon: 'headcloth', name: 'Linen Headcloth', rarity: 'worn',
    blurb: 'Sun-bleached linen, knotted at the nape. It smells of smoke from a town that no longer stands.',
    verse: '1 Samuel 30:1',
  },
  {
    slot: 'chest', icon: 'lamellar', name: 'Lamellar Vest', rarity: 'fine',
    blurb: 'Bronze scales laced to a leather backing, three rows missing where a spear found them.',
    verse: '1 Samuel 30:7',
  },
  {
    slot: 'arms', icon: 'bracers', name: 'Laced Bracers', rarity: 'fine',
    blurb: 'Hide bound with sinew. The lacing is new; the leather under it is not.',
    verse: '1 Samuel 30:9',
  },
  {
    slot: 'belt', icon: 'warbelt', name: 'War Belt', rarity: 'worn',
    blurb: 'Wide enough to carry a sword and a pouch of stones. The buckle has been repaired twice.',
    verse: '1 Samuel 30:11',
  },
  {
    slot: 'feet', icon: 'sandals', name: 'Dust Sandals', rarity: 'worn',
    blurb: 'Caked to the ankle. Two days and a hundred miles of wadi have not been kind to them.',
    verse: '1 Samuel 30:10',
  },
  {
    slot: 'cloak', icon: 'cloak', name: 'Shepherd’s Cloak', rarity: 'worn',
    blurb: 'Coarse wool from the years with the flock. It is the only thing he still owns from before.',
    verse: '1 Samuel 16:11',
  },
  {
    slot: 'weapon', icon: 'sling', name: 'The Sling', rarity: 'fine',
    blurb: 'Two cords and a cradle of hide. It has already brought down more than a stone was meant to.',
    verse: '1 Samuel 17:49',
  },
  {
    slot: 'offhand', icon: 'shield', name: 'Round Hide Shield', rarity: 'fine',
    blurb: 'Three layers of bull hide over a bronze boss, still dented from the last valley.',
    verse: '1 Samuel 30:24',
  },
]);

export const PROVISIONS = Object.freeze([
  {
    icon: 'waterskin', name: 'Water Skin', rarity: 'worn',
    blurb: 'Goat hide, sewn with sinew. Filled at the Besor before the crossing.',
    verse: '1 Samuel 30:11',
  },
  {
    icon: 'bread', name: 'Barley Loaf', rarity: 'worn',
    blurb: 'Hard as a roof tile. The Egyptian servant was given figs and two clusters of raisins instead.',
    verse: '1 Samuel 30:12',
  },
  {
    icon: 'oilflask', name: 'Flask of Oil', rarity: 'fine',
    blurb: 'Enough to keep a lamp burning through one more night of watching.',
    verse: '1 Samuel 30:12',
  },
]);

export const SPOIL = Object.freeze([
  {
    icon: 'goldtalent', name: 'Talent of Gold', rarity: 'royal',
    blurb: 'Weighed out of the raiders’ tents. David sent it south to the elders of Judah.',
    verse: '1 Samuel 30:26',
  },
  {
    icon: 'silvercup', name: 'Silver Cup', rarity: 'fine',
    blurb: 'Still sweet with Amalekite wine. It was in a tent that had been drunk for three days.',
    verse: '1 Samuel 30:16',
  },
  {
    icon: 'jar', name: 'Raider Jar', rarity: 'worn',
    blurb: 'Thumb-marked clay, painted with a band of red ochre. Grain still rattles inside.',
    verse: '1 Samuel 30:19',
  },
  {
    icon: 'dyedcloth', name: 'Bolt of Dyed Cloth', rarity: 'fine',
    blurb: 'Madder and purple, taken from a caravan that never reached its market.',
    verse: '1 Samuel 30:19',
  },
]);

export const RELICS = Object.freeze([
  {
    icon: 'scroll', name: 'The Weight of the Charge', rarity: 'royal',
    blurb: '“Pursue: for thou shalt surely overtake them, and without fail recover all.”',
    verse: '1 Samuel 30:8',
  },
  {
    icon: 'ephod', name: 'The Ephod', rarity: 'royal',
    blurb: 'Linen, with the twelve stones. Brought by Abiathar, and asked before six hundred men went down.',
    verse: '1 Samuel 30:7',
  },
  {
    icon: 'horn', name: 'Horn of Oil', rarity: 'royal',
    blurb: 'Anointed once in Bethlehem among the sheep; Judah will anoint again in Hebron.',
    verse: '1 Samuel 16:13',
  },
]);

/** The tally line under the grid — the numbers of 1 Samuel 30. */
export const TALLY = Object.freeze([
  ['600', 'who went down'],
  ['200', 'too spent for the brook'],
  ['400', 'who fell upon the camp'],
  ['0', 'missing when it was over'],
]);

export const ALL_ITEMS = Object.freeze([...EQUIPPED, ...PROVISIONS, ...SPOIL, ...RELICS]);
