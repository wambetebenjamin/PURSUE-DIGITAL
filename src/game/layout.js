/**
 * game/layout.js — where things stand in the camp.
 *
 * Pure arithmetic, no Phaser: the scenes ask this module where to put
 * things, and the smoke suite asks it where things *should* be. That keeps
 * the responsive behaviour testable — the canvas can be any size, and the
 * camp still stands in the same proportion.
 *
 * The design space is 1280 × 720. Everything scales from there.
 */

/** The camp's places, in the order they are drawn back to front. */
export const PLACES = Object.freeze([
  { id: 'hall', name: 'The Main Tent', sub: 'the hall of the march', kind: 'main' },
  { id: 'barracks', name: 'The Barracks', sub: 'the six hundred', kind: 'barracks' },
  { id: 'threshing', name: 'The Threshing Floor', sub: 'barley and wheat', kind: 'work' },
  { id: 'quarry', name: 'The Quarry', sub: 'beams and stakes', kind: 'work' },
  { id: 'smelter', name: 'The Smelter', sub: 'bronze and spearheads', kind: 'work' },
  { id: 'hospital', name: 'The Tent of the Wounded', sub: 'the tended', kind: 'work' },
  { id: 'cistern', name: 'The Cistern', sub: 'water in the wilderness', kind: 'cistern' },
  { id: 'wall', name: 'The Wall', sub: 'stone and timber', kind: 'wall' },
]);

/** The companies of the band, in the order the strip shows them. */
export const COMPANIES = Object.freeze([
  { id: 'spears', name: 'Iron Spears', line: 'front' },
  { id: 'shields', name: 'Shield Wall', line: 'front' },
  { id: 'bows', name: 'Bowmen', line: 'back' },
  { id: 'slings', name: 'Slingers', line: 'back' },
  { id: 'scouts', name: 'Scouts', line: 'back' },
]);

/** The three skirmishes the camp can send the band into. */
export const STAGES = Object.freeze([
  { id: 'besor-watch', name: 'The Watch at Besor', short: 'Besor Watch' },
  { id: 'ford', name: 'The Ford by Night', short: 'The Ford' },
  { id: 'outpost', name: 'The Outpost on the Escarpment', short: 'The Outpost' },
]);

/** The design space the layout is written against. */
export const DESIGN = Object.freeze({ w: 1280, h: 720 });

/**
 * Lay the camp out for a canvas of this size.
 *
 * The camp is seen from the side: the sky and the ridge at the back, the
 * Main Tent and the Barracks standing on that ridge, the four works nearer
 * the viewer along the edge of the camp, the stores across the top, the
 * choice of engagement at the top right, and the companies along the
 * bottom with the two orders beside them.
 *
 * @param {number} w canvas width
 * @param {number} h canvas height
 * @returns {object} every position the scenes need
 */
export function campLayout(w, h) {
  const scale = Math.min(w / DESIGN.w, h / DESIGN.h);
  const horizon = h * 0.58;          // the earth meets the dark here
  const ground = horizon;            // the tents stand on that line
  const band = h * 0.80;             // the row of works, nearer the viewer

  const mainW = 210 * scale;
  const mainH = 132 * scale;
  const barrW = 184 * scale;
  const barrH = 108 * scale;
  const workW = 132 * scale;
  const workH = 80 * scale;

  const works = {};
  const workList = PLACES.filter((p) => p.kind !== 'main' && p.kind !== 'barracks');
  const gap = w * 0.142;
  const startX = w * 0.5 - (gap * (workList.length - 1)) / 2 - workW / 2;
  workList.forEach((p, i) => {
    works[p.id] = { x: startX + gap * i, y: band, w: workW, h: workH };
  });

  const stageStep = 148 * scale;
  const stageW = 430 * scale;

  return {
    scale,
    horizon,
    ground,
    band,
    title: { x: 28 * scale, y: 20 * scale },
    readout: { x: 24 * scale, y: 74 * scale, w: Math.min(w - 48 * scale, 690 * scale), h: 60 * scale },
    stages: {
      x: w - 24 * scale - stageW,
      y: 74 * scale,
      w: stageW,
      step: stageStep,
      h: 44 * scale,
    },
    tent: { x: w * 0.17, y: ground, w: mainW, h: mainH },
    barracks: { x: w * 0.355, y: ground, w: barrW, h: barrH },
    works,
    gate: { x: w * 0.62, y: ground, w: 200 * scale, h: 120 * scale },
    strip: { x: w * 0.055, y: h - 92 * scale, w: Math.min(w * 0.56, 700 * scale), h: 44 * scale },
    buttons: { x: w * 0.5, y: h - 34 * scale, w: 226 * scale, gap: 14 * scale },
  };
}

/**
 * The readouts along the top: provisions, bronze, timber, spoil, and the
 * number of men under arms.
 * @param {object} state the merged camp state
 * @returns {Array<{id:string,label:string,value:string}>}
 */
export function readouts(state) {
  const res = (state && state.resources) || {};
  const troops = (state && state.troops) || {};
  const companies = troops.companies || {};
  const counted = Object.values(companies).reduce((n, c) => n + ((c && c.count) || 0), 0);
  const men = (troops.power && troops.power.men) || counted;
  return [
    { id: 'provisions', label: 'PROVISIONS', value: String(res.provisions || 0) },
    { id: 'bronze', label: 'BRONZE', value: String(res.bronze || 0) },
    { id: 'timber', label: 'TIMBER', value: String(res.timber || 0) },
    { id: 'spoil', label: 'SPOIL', value: String(res.spoil || 0) },
    { id: 'troops', label: 'MEN UNDER ARMS', value: String(men) },
  ];
}

/** The five companies, with the count each holds. */
export function companyRows(state) {
  const troops = (state && state.troops) || {};
  const companies = troops.companies || {};
  return COMPANIES.map((c) => {
    const row = companies[c.id] || {};
    return {
      ...c,
      name: row.name || c.name,
      count: row.count || 0,
      tier: row.tier || 1,
      cap: row.cap || 0,
      wounded: row.wounded || 0,
    };
  });
}

/** The level of a place, from the merged state. */
export function levelOf(state, placeId) {
  const works = (state && state.works) || {};
  return works[placeId] || 1;
}

/** A number with a thousands mark, so a big spoil reads at a glance. */
export function formatNumber(n) {
  const v = Math.round(Number(n) || 0);
  return v.toLocaleString('en-US');
}

/** The cost of raising a place one level, from the shipped game's table. */
export function upgradeCost(placeId, level) {
  const def = PLACES.find((p) => p.id === placeId);
  if (!def) return null;
  // the shipped game's cost table, read from src/core/progression.js
  const table = {
    hall: (lv) => ({ timber: 40 + lv * 35, bronze: 20 + lv * 20 }),
    barracks: (lv) => ({ bronze: 50 + lv * 40, provisions: 30 + lv * 25 }),
    cistern: (lv) => ({ timber: 30 + lv * 25, provisions: 25 + lv * 20 }),
    wall: (lv) => ({ timber: 45 + lv * 30, bronze: 25 + lv * 22 }),
    threshing: (lv) => ({ timber: 35 + lv * 28, provisions: 20 + lv * 18 }),
    quarry: (lv) => ({ timber: 30 + lv * 24, provisions: 22 + lv * 16 }),
    smelter: (lv) => ({ timber: 40 + lv * 30, bronze: 18 + lv * 20 }),
    hospital: (lv) => ({ provisions: 40 + lv * 30, timber: 25 + lv * 20 }),
  }[placeId];
  return table ? table(level) : null;
}

/** The cost of one press of ten men, for a company. */
export function recruitCost(companyId, steps = 1) {
  const table = {
    spears: (n) => ({ bronze: 26 * n, provisions: 12 * n }),
    shields: (n) => ({ bronze: 30 * n, timber: 14 * n }),
    bows: (n) => ({ bronze: 22 * n, timber: 18 * n }),
    slings: (n) => ({ provisions: 16 * n, timber: 9 * n }),
    scouts: (n) => ({ provisions: 20 * n, bronze: 12 * n }),
  }[companyId];
  return table ? table(steps) : null;
}

/** A short line: "timber 55 · provisions 45". */
export function costLine(cost) {
  if (!cost) return '';
  return Object.entries(cost)
    .map(([k, v]) => `${k} ${v}`)
    .join(' · ');
}
