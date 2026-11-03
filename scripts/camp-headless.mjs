/**
 * camp-headless.mjs — the 2D camp, booted for real without a screen.
 *
 * The camp is a canvas, and a canvas cannot be looked at from a terminal.
 * But it can be *run*: Phaser boots headless inside jsdom, the scenes draw
 * themselves into a canvas that is never shown, and every order the player
 * can give is given here — raise a work, muster a company, send the band
 * out, come home again.
 *
 * This is the only way to know that the camp works without a browser to
 * look at it in.
 *
 * Usage: `npm run camp:test`
 */
import { JSDOM } from 'jsdom';

/* ------------------------------------------------------------- the browser */
const dom = new JSDOM(
  '<!doctype html><html><body><div id="game-container"></div></body></html>',
  { pretendToBeVisual: true, url: 'http://localhost/' },
);
const { window } = dom;

// A 2D context good enough for Phaser's text measuring. Nothing is drawn.
const ctx = new Proxy({
  measureText: (t) => ({
    width: String(t).length * 8,
    actualBoundingBoxAscent: 8,
    actualBoundingBoxDescent: 2,
  }),
  getImageData: () => ({ data: new Uint8ClampedArray(4) }),
  createImageData: (w = 1, h = 1) => ({ data: new Uint8ClampedArray(w * h * 4) }),
  canvas: { width: 1280, height: 720 },
}, {
  get: (t, k) => (k in t ? t[k] : () => {}),
  set: () => true,
});
window.HTMLCanvasElement.prototype.getContext = function getContext() { return ctx; };

globalThis.window = window;
globalThis.document = window.document;
Object.defineProperty(globalThis, 'navigator', { value: window.navigator, configurable: true });
globalThis.self = window;
for (const key of Object.getOwnPropertyNames(window)) {
  if (key in globalThis) continue;
  try { globalThis[key] = window[key]; } catch (_e) { /* read-only */ }
}
globalThis.requestAnimationFrame = window.requestAnimationFrame.bind(window);
globalThis.cancelAnimationFrame = window.cancelAnimationFrame.bind(window);

/* ------------------------------------------------------------------ Phaser */
const phaserMod = await import('phaser');
const Phaser = phaserMod.default || phaserMod;

const { default: CampScene } = await import('../src/game/CampScene.js');
const { default: PursuitScene } = await import('../src/game/PursuitScene.js');

/* ------------------------------------------------------------- the checks */
let passed = 0;
function ok(cond, label) {
  if (!cond) {
    console.error(`  ✗ FAIL: ${label}`);
    process.exit(1);
  }
  passed += 1;
  console.log(`  ✓ ${label}`);
}
const sleep = (ms) => new Promise((r) => setTimeout(r, ms));

/* ------------------------------------------------------- the server's camp */
const CALLS = [];
const STATE = {
  resources: { provisions: 180, bronze: 90, timber: 60, spoil: 0 },
  works: { hall: 1, barracks: 1, threshing: 1, quarry: 1, smelter: 1, hospital: 1, cistern: 1, wall: 1 },
  roster: {},
  renown: 0,
  gain: null,
  rate: {},
  troops: {
    companies: {
      spears: { id: 'spears', name: 'The Iron Spears', count: 40, tier: 2, cap: 70 },
      shields: { id: 'shields', name: 'The Shield Wall', count: 20, tier: 1, cap: 60 },
      bows: { id: 'bows', name: 'The Bowmen', count: 0, tier: 1, cap: 60 },
      slings: { id: 'slings', name: 'The Slingers', count: 0, tier: 1, cap: 50 },
      scouts: { id: 'scouts', name: 'The Ranging Scouts', count: 0, tier: 1, cap: 40 },
    },
    order_of_battle: [],
    power: { power: 300, men: 60 },
    wounded: 3,
  },
  source: 'server',
};

const api = {
  online: true,
  async refresh() { CALLS.push(['refresh']); return STATE; },
  async upgradeCamp(id) {
    CALLS.push(['upgrade', id]);
    STATE.works[id] = (STATE.works[id] || 1) + 1;
    return { ok: true, message: 'raised', data: { work_id: id, level: STATE.works[id] } };
  },
  async recruitTroops(id, steps) {
    CALLS.push(['recruit', id, steps]);
    return { ok: true, message: 'mustered', data: { count: 10 } };
  },
  async pursue(stage) {
    CALLS.push(['pursue', stage]);
    return {
      ok: true,
      message: 'fought',
      source: 'server',
      data: {
        won: true,
        stars: 2,
        rewards: { provisions: 45, timber: 20 },
        casualties: { standing: 55, fallen: 5 },
        elapsed: 42,
        log: [],
      },
    };
  },
};

/* --------------------------------------------------------------- the game */
const game = new Phaser.Game({
  type: Phaser.HEADLESS,
  parent: 'game-container',
  width: 1280,
  height: 720,
  scene: [CampScene, PursuitScene],
  audio: { noAudio: true },
  banner: false,
  defaultImage: null,
  missingImage: null,
  whiteImage: null,
});

// jsdom never decodes the data-URI textures, so the three defaults are made here
for (const key of ['__DEFAULT', '__MISSING', '__WHITE']) {
  const c = window.document.createElement('canvas');
  c.width = 32;
  c.height = 32;
  game.textures.addCanvas(key, c);
}
for (let i = 0; i < 3; i += 1) game.textures.updatePending();

await new Promise((resolve) => {
  game.events.once('ready', resolve);
  setTimeout(resolve, 3000);
});

// the camp comes up with the server's numbers
game.scene.start('Camp', { api });
await sleep(300);

const camp = game.scene.getScene('Camp');
ok(camp && camp.sys.settings.status === 5, 'the camp scene stands up');
ok(CALLS.some((c) => c[0] === 'refresh'), 'the camp asks the server for its state');
ok(camp.state === STATE, 'and keeps what the server said');

/* --------------------------------------------------------- what is on screen */
const texts = () => camp.children.list.filter((o) => o.type === 'Text');
const textOf = (needle) => texts().some((t) => String(t.text).includes(needle));

ok(Object.keys(camp.places).length === 8, 'the camp has eight places');
ok(textOf('ZIKLAG'), 'the camp names itself');
ok(textOf('PROVISIONS'), 'the stores are shown');
ok(textOf('180'), 'with the server\'s provisions');
ok(textOf('MEN UNDER ARMS'), 'the men under arms are counted');
ok(textOf('60'), 'and the number is the server\'s');
ok(textOf('The Iron Spears'), 'the companies are named by the server');
ok(textOf('40'), 'with the men each holds');
ok(textOf('Besor Watch'), 'the ground of the engagement is chosen');
ok(textOf('Pursue the Amalekites'), 'the order to pursue is given');
ok(textOf('Take the road'), 'and the order to march');

const tent = camp.places.hall;
ok(tent.container.y === camp.layout_.ground, 'the main tent stands on the ridge');
ok(tent.art.commandBuffer.length > 0, 'the tent is drawn, not a picture of one');
ok(camp.places.threshing.art.commandBuffer.length > 0, 'the threshing floor is drawn');
ok(camp.places.cistern.art.commandBuffer.length > 0, 'the cistern is drawn');
ok(camp.places.wall.art.commandBuffer.length > 0, 'the wall is drawn');
ok(camp.places.barracks.art.commandBuffer.length > 0, 'the barracks are drawn');

/* ------------------------------------------------------------- the orders */
const before = CALLS.length;
camp.places.quarry.hit.emit('pointerdown', { x: 0, y: 0 });
await sleep(200);
ok(CALLS.slice(before).some((c) => c[0] === 'upgrade' && c[1] === 'quarry'),
  'clicking the quarry asks for it to be raised');
ok(textOf('raised to level 2'), 'and the camp says what happened');

const beforeRecruit = CALLS.length;
camp.companyButtons.find((b) => b.id === 'spears').box.emit('pointerdown', { x: 0, y: 0 });
await sleep(200);
ok(CALLS.slice(beforeRecruit).some((c) => c[0] === 'recruit' && c[1] === 'spears'),
  'clicking a company musters a press of ten');
ok(textOf('press of ten'), 'and the camp says so');

/* --------------------------------------------------------------- the fight */
const beforeFight = CALLS.length;
camp.pursueButton.box.emit('pointerdown', { x: 0, y: 0 });
await sleep(400);
ok(CALLS.slice(beforeFight).some((c) => c[0] === 'pursue' && c[1] === 'besor-watch'),
  'the order to pursue sends the band to the ground that was chosen');

const pursuit = game.scene.getScene('Pursuit');
ok(pursuit && pursuit.sys.settings.status === 5, 'the pursuit scene stands up');
ok(pursuit.resolved === true, 'and waits for the server\'s answer');
ok(pursuit.resultTitle.visible === true, 'the result is shown');
ok(String(pursuit.resultTitle.text).includes('THE FIELD IS OURS'), 'and it is the field that was won');
ok(String(pursuit.lootLine.text).includes('LOOT RECOVERED'), 'the loot recovered is written out');
ok(String(pursuit.lootLine.text).includes('provisions 45'), 'with the server\'s numbers');
ok(String(pursuit.resultBody.text).includes('55 still standing'), 'and the men who stood');

/* ------------------------------------------------------------- the way home */
pursuit.backBox.emit('pointerdown', { x: 0, y: 0 });
await sleep(300);
ok(game.scene.getScene('Camp').sys.settings.status === 5, 'the camp is there again');

/* --------------------------------------------------------- another canvas */
const campAgain = game.scene.getScene('Camp');
const bigTitle = parseInt(campAgain.titleText.style.fontSize, 10);
game.scale.resize(667, 375);
await sleep(200);
ok(campAgain.layout_.scale < 1, 'the camp lays itself out again for a smaller canvas');
ok(parseInt(campAgain.titleText.style.fontSize, 10) < bigTitle, 'and the lettering shrinks with it');
ok(campAgain.places.hall.container.y === campAgain.layout_.ground,
  'the tent still stands on the ridge');
game.scale.resize(1280, 720);
await sleep(150);

/* ---------------------------------------------------------------- the end */
game.destroy(true);
console.log(`\nAll ${passed} checks passed.\n`);
process.exit(0);
