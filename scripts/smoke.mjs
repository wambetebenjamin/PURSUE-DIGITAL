/**
 * smoke.mjs — headless logic test for PURSUE.
 *
 * Boots the game modules in Node with a minimal DOM stub, then walks the
 * full state machine: Ziklag → desert → brook → servant dialogue → camp
 * detection → rescue → the battle sequence → the spoil divided at Besor →
 * gifts to Judah → the fall of Saul → victory → restart. Any thrown error
 * fails the run.
 *
 * Usage: `npm run smoke`
 */
import * as THREE from 'three';

// ------------------------------------------------------------- DOM stubs
class FakeClassList {
  constructor() { this._set = new Set(); }
  add(...c) { c.forEach((x) => this._set.add(x)); }
  remove(...c) { c.forEach((x) => this._set.delete(x)); }
  toggle(c, force) {
    const on = force === undefined ? !this._set.has(c) : force;
    if (on) this._set.add(c); else this._set.delete(c);
    return on;
  }
  contains(c) { return this._set.has(c); }
}

class FakeElement {
  constructor(tag = 'div') {
    this.tagName = tag.toUpperCase();
    this.classList = new FakeClassList();
    this.style = {};
    this.children = [];
    this.textContent = '';
    this.offsetWidth = 0;
    this._innerHTML = '';
  }
  set innerHTML(v) { this._innerHTML = v; if (v === '') this.children = []; }
  get innerHTML() { return this._innerHTML; }
  appendChild(c) { this.children.push(c); return c; }
  replaceChildren(...c) { this.children = c; }
  setAttribute(k, v) { this._attrs = this._attrs || {}; this._attrs[k] = String(v); }
  getAttribute(k) { return (this._attrs || {})[k] ?? null; }
  removeAttribute(k) { if (this._attrs) delete this._attrs[k]; }
  addEventListener() {}
  removeEventListener() {}
}

function fake2dContext() {
  return new Proxy({ measureText: () => ({ width: 120 }) }, {
    get: (t, k) => (k in t ? t[k] : () => {}),
    set: () => true,
  });
}

const elements = new Map();
globalThis.document = {
  pointerLockElement: null,
  getElementById(id) {
    if (!elements.has(id)) elements.set(id, new FakeElement());
    return elements.get(id);
  },
  createElement(tag) {
    const el = new FakeElement(tag);
    if (tag === 'canvas') {
      el.getContext = () => fake2dContext();
      el.width = 0;
      el.height = 0;
    }
    return el;
  },
  addEventListener() {},
  exitPointerLock() {},
  body: new FakeElement('body'),
};

// a persistence shim so the settings store can be exercised as it ships
const store = new Map();
globalThis.localStorage = {
  getItem: (k) => (store.has(k) ? store.get(k) : null),
  setItem: (k, v) => store.set(k, String(v)),
  removeItem: (k) => store.delete(k),
};
globalThis.window = globalThis;
globalThis.addEventListener = () => {};
globalThis.requestAnimationFrame = (fn) => setTimeout(fn, 0);

// ------------------------------------------------------------- bootstrap
const { Input } = await import('../src/core/Input.js');
const { postFX } = await import('../src/core/PostFX.js');
const { Game } = await import('../src/core/Game.js');
const { STATES, NARRATIVE, PHASE_GRADES } = await import('../src/constants.js');
const { ATMOSPHERE, ATMOSPHERE_KEYS, atmosphereFor } = await import('../src/world/atmospheres.js');
const { GRADES } = await import('../src/core/PostFX.js');
const { ZIKLAG } = await import('../src/world/zones/ZiklagZone.js');
const { DESERT } = await import('../src/world/zones/DesertZone.js');
const { EGYPT } = await import('../src/world/zones/EgyptianZone.js');
const { CAMP } = await import('../src/world/zones/CampZone.js');
const { SPOILS } = await import('../src/world/zones/SpoilsZone.js');
const { GATE } = await import('../src/world/zones/GateZone.js');
const { GILBOA } = await import('../src/world/zones/GilboaZone.js');

const input = new Input();
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(60, 1.6, 0.1, 600);
const game = new Game({ scene, camera, input });

let passed = 0;
function ok(cond, label) {
  if (!cond) {
    console.error(`  ✗ FAIL: ${label}`);
    process.exit(1);
  }
  passed += 1;
  console.log(`  ✓ ${label}`);
}

function frames(n, dt = 1 / 60) {
  for (let i = 0; i < n; i++) game.update(dt, i * dt);
}

function press(code) {
  input.pressed.add(code);
  input.keys.add(code);
}

function release(code) {
  input.keys.delete(code);
}

console.log('\nPURSUE smoke test — 1 Samuel 30 game loop\n');

// ---------------------------------------------------------- boot
game.start();
frames(10);
ok(game.fsm.currentName === STATES.ZIKLAG, 'boots into STATE_0_ZIKLAG');
ok(game.world.activeZone.name === STATES.ZIKLAG, 'ziklag zone is active');

// --- Vice City interface layer ---
ok(game.radar && game.radar.enabled, 'radar canvas initialised');
ok(game.radar._objective !== null, 'radar carries the mission blip');
ok(document.getElementById('zone-name').textContent === 'Ziklag', 'zone banner names the district');
ok(/^\d+:\d\d$/.test(document.getElementById('hud-timer').textContent), 'mission timer is formatted');
ok(game._runTime > 0, 'run clock is ticking');

// ------------------------------------------- STATE 0: reach waypoint
game.player.group.position.set(ZIKLAG.waypoint.x, 0, ZIKLAG.waypoint.z + 2);
frames(2);
ok(game.modal.isOpen, 'approaching the Desert Edge opens the narrative modal');
ok(game.modal.titleEl.textContent === NARRATIVE.ziklagModal.title, 'modal shows the Ziklag title');

// advance the modal with E
press('KeyE');
frames(1);
release('KeyE');
frames(2);
ok(game.fsm.currentName === STATES.DESERT, 'Next button advances to STATE_1_DESERT_MARCH');

// ------------------------------------------- STATE 1: brook + exit
game.player.group.position.set(0, 0, DESERT.brookZ + 2);
frames(2);
ok(game.modal.isOpen, 'reaching the Brook Besor opens the narrative modal');
press('Space');
frames(1);
release('Space');
frames(2);
ok(!game.modal.isOpen, 'brook modal dismisses');
ok(game.brookTriggered, 'brook trigger is latched');

game.player.group.position.set(DESERT.exit.x, 0, DESERT.exit.z);
frames(2);
ok(game.fsm.currentName === STATES.EGYPTIAN, 'crossing the march advances to STATE_2_EGYPTIAN_SERVANT');

// ------------------------------------------- STATE 2: servant dialogue
game.player.group.position.set(EGYPT.servant.x - 1.5, 0, EGYPT.servant.z);
frames(2);
ok(game._prompt === NARRATIVE.promptServant, 'proximity shows "Press E to give bread and water"');
press('KeyE');
frames(1);
release('KeyE');
ok(game.modal.isOpen, 'interaction opens the servant dialogue');
ok(game.modal._steps.length === NARRATIVE.servantDialogue.length, 'dialogue has 3 scripted steps');

// walk the whole dialogue
for (let i = 0; i < NARRATIVE.servantDialogue.length; i++) {
  press('KeyE');
  frames(1);
  release('KeyE');
  frames(1);
}
ok(!game.modal.isOpen, 'dialogue completes');
ok(game.servantServed, 'servant is marked as served');
ok(game.arrow.group.visible, 'directional arrow is enabled');

// arrow should track the player and face the camp
game.player.group.position.set(0, 0, 10);
game.arrow.update(1 / 60, game.player.position);
ok(Math.abs(game.arrow.group.position.x) < 0.001, 'arrow hovers over the player');

// exit is gated until the servant is served — verify after serving it opens
game.player.group.position.set(EGYPT.exit.x, 0, EGYPT.exit.z);
frames(2);
ok(game.fsm.currentName === STATES.CAMP, 'reaching the canyon advances to STATE_3_AMALEKITE_RESCUE');

// ------------------------------------------- STATE 3: detection & rescue
const guard = game.world.activeZone.guards[0];
// pin the guard's scan so its cone is deterministic for the test
guard.scan.amplitude = 0;
guard.scan.base = 0;
const gpos = guard.group.position;
game.player.group.position.set(gpos.x, 0, gpos.z + 4); // straight down the cone (+Z)
frames(2);
ok(
  Math.abs(game.player.group.position.z - CAMP.spawn.z) < 0.001,
  'walking into a vision cone pushes the player back to the camp entrance',
);

// sneak in from an angle the guard cannot see, then reach the cage
game._detectionCooldown = 3;
game.player.group.position.set(CAMP.captives.x + 1, 0, CAMP.captives.z + 1);
frames(2);
ok(game.modal.isOpen, 'reaching the captives opens the 24-hour battle sequence');
ok(game.battleShown, 'battle sequence is latched');
ok(!game.finished, 'the game is not over yet — the story continues');
ok(game.modal._steps.length === NARRATIVE.battleSequence.length, 'battle sequence has 2 scripted cards');
ok(game.modal.citeEl.textContent.includes('30:17'), 'cards carry scripture citations');

for (let i = 0; i < NARRATIVE.battleSequence.length; i++) {
  press('KeyE');
  frames(1);
  release('KeyE');
  frames(1);
}
ok(game.fsm.currentName === STATES.SPOILS, 'battle cards advance to STATE_4_SPOILS_DIVISION');

// ------------------------------------------- STATE 4: the spoil divided
game.player.group.position.set(SPOILS.outcrop.x + 1.5, 0, SPOILS.outcrop.z);
frames(2);
ok(game._prompt === NARRATIVE.promptOutcrop, 'the outcrop offers the dividing-line prompt');
press('KeyE');
frames(1);
release('KeyE');
ok(game.modal.isOpen, 'interaction opens the spoil-dispute dialogue');
ok(game.modal.card.classList.contains('theme-spoils'), 'spoils card carries the spoils theme');
ok(game.modal._steps.length === NARRATIVE.spoilsDialogue.length, 'dispute dialogue has 3 scripted steps');
for (let i = 0; i < NARRATIVE.spoilsDialogue.length; i++) {
  press('KeyE');
  frames(1);
  release('KeyE');
  frames(1);
}
ok(game.fsm.currentName === STATES.GATE, 'the statute advances to STATE_5_DISTRIBUTION');

// ------------------------------------------- STATE 5: gifts to Judah
game.player.group.position.set(GATE.elders.x + 1.5, 0, GATE.elders.z);
frames(2);
ok(game._prompt === NARRATIVE.promptElders, 'the elders offer the tribute prompt');
press('KeyE');
frames(1);
release('KeyE');
ok(game.modal.isOpen, 'interaction opens the distribution dialogue');
ok(game.modal._steps.length === NARRATIVE.gateDialogue.length, 'distribution dialogue has 3 scripted steps');
for (let i = 0; i < NARRATIVE.gateDialogue.length; i++) {
  press('KeyE');
  frames(1);
  release('KeyE');
  frames(1);
}
ok(game.fsm.currentName === STATES.GILBOA, 'the gifts advance to STATE_6_GILBOA_EPILOGUE');

// ------------------------------------------- STATE 6: the fall of Saul
game.player.group.position.set(GILBOA.saul.x + 1.5, 0, GILBOA.saul.z);
frames(2);
ok(game._prompt === NARRATIVE.promptSaul, 'Saul offers the final approach prompt');
press('KeyE');
frames(1);
release('KeyE');
ok(game.modal.isOpen, 'interaction opens the Gilboa epilogue');
ok(game.modal.card.classList.contains('theme-gilboa'), 'epilogue card carries the gilboa theme');
ok(game.modal._steps.length === NARRATIVE.gilboaDialogue.length, 'epilogue has 3 scripted steps');
for (let i = 0; i < NARRATIVE.gilboaDialogue.length; i++) {
  press('KeyE');
  frames(1);
  release('KeyE');
  frames(1);
}
ok(game.finished, 'the epilogue completes the run');
ok(game.victory.visible, 'victory overlay is visible');
ok(document.getElementById('hud').classList.contains('hidden'), 'HUD is cleared on victory');

// ------------------------------------------------------------- restart
game.resetAll();
frames(5);
ok(game.fsm.currentName === STATES.ZIKLAG, 'Play Again resets to STATE_0_ZIKLAG');
ok(
  !game.finished && !game.servantServed && !game.brookTriggered
  && !game.battleShown && !game.spoilsDone && !game.gateDone,
  'all run flags are cleared',
);
ok(!game.victory.visible, 'victory overlay is hidden');
ok(!game.arrow.group.visible, 'directional arrow is hidden again');

// ------------------------------------------------------ pause & map screen
press('Escape');
frames(1);
ok(game.paused && game.pause.isOpen, 'Escape pauses the march');
ok(!document.getElementById('pause-screen').classList.contains('hidden'), 'pause overlay is shown');
press('Escape');
frames(1);
ok(!game.paused, 'Escape resumes the march');
press('KeyM');
frames(1);
ok(game.paused && game.pause.page === 'map', 'M opens the Map of Judah');
press('Escape');
frames(1);
ok(!game.paused, 'the map closes back to play');

// stamina sanity: sprinting drains, forced walk at zero
const st = game.player.stamina;
game.player.group.position.set(0, 0, 10);
press('ShiftLeft');
press('KeyW');
// face -z and push many frames
for (let i = 0; i < 400; i++) {
  game.update(1 / 60, i / 60);
}
release('KeyW');
release('ShiftLeft');
ok(st.value < 100, 'running drains stamina');
ok(st.exhausted || st.value < 50, 'sustained sprint exhausts the player');
frames(240); // walk idle → regen
ok(st.value > 20, 'stamina regenerates while resting');

// ---------------------------------------------------- pass 9: stance & zoom
// crouch (the sneak parameter of a third-person controller) halves the speed
// and the silhouette, and it must survive a zone change.
press('KeyC');
frames(2);
ok(game.player.crouched === true, 'C drops the player into a crouch');
ok(game.cameraRig.eyeDrop > 0, 'the camera ducks with the crouch');

const beforeZoom = game.cameraRig.baseDistance;
game.input.wheelDelta = 2;
frames(1);
ok(game.cameraRig.baseDistance > beforeZoom, 'the wheel pulls the camera back');
game.input.wheelDelta = -20;
frames(1);
ok(game.cameraRig.baseDistance <= 3.6, 'the wheel clamps at the near limit');

press('KeyC');
frames(2);
ok(game.player.crouched === false, 'C stands the player back up');

// camera obstruction probe: obstacles are registered on every zone enter
ok(Array.isArray(game.cameraRig.obstacles) && game.cameraRig.obstacles.length > 0,
  'the camera probes the zone rubble');

// locomotion state machine: the animator parameters resolve to a real state
game.player.group.position.set(0, 0, 10);
press('ShiftLeft');
press('KeyW');
frames(60);
ok(['run', 'sprint'].includes(game.player.locomotion.state),
  'the locomotion machine reports a moving state');
release('KeyW');
release('ShiftLeft');
frames(60);
ok(game.player.locomotion.state === 'idle' || game.player.locomotion.state === 'walk',
  'and settles back toward idle');

// ------------------------------------------------------- pass 9: the satchel
game.pause.showPage('satchel');
const satchel = document.getElementById('satchel-grid');
ok(satchel.children.length >= 4, 'the satchel builds its sections');
const tileCount = (() => {
  let n = 0;
  const walk = (node) => {
    for (const c of node.children) {
      if (c.classList && c.classList.contains && c.classList.contains('satchel-tile')) n++;
      walk(c);
    }
  };
  walk(satchel);
  return n;
})();
ok(tileCount >= 18, `the satchel lays out every item (${tileCount} tiles)`);
game.pause.close(false);

// ------------------------------------------------- pass 10: the settings leaf
const { settings, DEFAULTS } = await import('../src/core/settings.js');
ok(DEFAULTS.sensitivity === 1 && DEFAULTS.volume === 0.55, 'settings defaults are sane');
settings.set({ sensitivity: 1.4, godRays: false, cameraDistance: 6 });
ok(settings.get('sensitivity') === 1.4, 'settings take a written value');
ok(settings.get('godRays') === false, 'and a wax seal can be pressed off');
const persisted = JSON.parse(localStorage.getItem('pursue.settings.v1'));
ok(persisted && persisted.sensitivity === 1.4, 'preferences persist to storage');
let notified = null;
const off = settings.onChange((snap) => { notified = snap; });
settings.set({ filmGrain: false });
ok(notified && notified.filmGrain === false, 'listeners hear the change');
off();
settings.reset();
ok(settings.get('sensitivity') === 1 && settings.get('filmGrain') === true,
  'restore defaults returns every leaf to the factory setting');

// the settings page of the codex is built from real rows, all wired
game.pause.showPage('settings');
const list = document.getElementById('settings-list');
ok(list.children.length >= 11, `the settings leaf lays out every row (${list.children.length})`);
ok(!document.getElementById('pause-settings-panel').classList.contains('hidden'),
  'the settings leaf is the visible page');
ok(game.pause.settings.controls.size === 10, 'ten preferences are reachable from the UI, including the clean frame');
game.pause.showPage('menu');
ok(document.getElementById('pause-settings-panel').classList.contains('hidden'),
  'and it yields back to the menu');
game.pause.close(false);

// post-processing leaves can be switched without a restart
const fx = postFX;
fx.setFeatures({ godRays: false, filmGrain: false, depthOfField: false });
ok(fx._features.godRays === false && fx._features.filmGrain === false,
  'post-processing leaves switch off at runtime');
fx.setFeatures({ godRays: true, filmGrain: true, depthOfField: true });
ok(fx._features.godRays === true, 'and switch back on');

// the frame closes in when the lamp runs low
game.hud.setHealth(0.1);
ok(document.body.classList.contains('critical'), 'a failing lamp marks the frame critical');
game.hud.setHealth(1);
ok(!document.body.classList.contains('critical'), 'and recovers when the lamp is fed');

// ------------------------------------------- pass 11: the air of each phase
ok(ATMOSPHERE_KEYS.length === 7, `seven phases, seven atmospheres (${ATMOSPHERE_KEYS.length})`);
ok(atmosphereFor(STATES.ZIKLAG) === ATMOSPHERE.ziklag, 'a state name resolves to its atmosphere');

const sunElevation = (a) => {
  const [x, y, z] = a.sun.dir;
  return y / Math.hypot(x, y, z);
};
const daylight = ATMOSPHERE_KEYS.filter((k) => sunElevation(ATMOSPHERE[k]) > 0.2);
ok(daylight.length === 6, `six phases stand in daylight (${daylight.join(', ')})`);
ok(ATMOSPHERE.camp.sky.stars > 0.5, 'the camp is the one phase under stars');
ok(ATMOSPHERE.gilboa.sky.cloud > 0.8 && ATMOSPHERE.gilboa.sun.disc === 0,
  'the storm has a cloud deck and no visible disc');
ok(ATMOSPHERE.desert.fog.density < ATMOSPHERE.ziklag.fog.density,
  'noon air is clearer than smoke-choked air');
ok(ATMOSPHERE.ziklag.sky.turbidity > ATMOSPHERE.desert.sky.turbidity * 0.9,
  'smoke loads the air with aerosols');

// each zone carries its phase's sky, haze and ridge lines
const zoneStates = [STATES.ZIKLAG, STATES.DESERT, STATES.EGYPTIAN, STATES.CAMP,
  STATES.SPOILS, STATES.GATE, STATES.GILBOA];
const zoneKeys = ['ziklag', 'desert', 'egyptian', 'camp', 'spoils', 'gate', 'gilboa'];
let ridgeRings = 0;
for (let i = 0; i < zoneStates.length; i++) {
  const zone = game.world.zones.get(zoneStates[i]);
  const atmo = ATMOSPHERE[zoneKeys[i]];
  ok(zone.atmosphere === atmo, `${zoneKeys[i]} carries its atmosphere`);
  ok(zone.fog.color === atmo.fog.color && zone.fog.density === atmo.fog.density,
    `${zoneKeys[i]} fogs to its own horizon`);
  ok(zone.sky && zone.sky.material.uniforms.uSunDisc.value === atmo.sun.disc,
    `${zoneKeys[i]} sky draws (or hides) the disc`);
  const horizon = zone.group.children.find((c) => c.name === 'HORIZON');
  const ridges = horizon ? horizon.children.filter((m) => m.name === 'HORIZON_RIDGE') : [];
  ok(ridges.length === 2, `${zoneKeys[i]} ends in two ridge lines`);
  ok(ridges.every((m) => m.geometry.attributes.color),
    `${zoneKeys[i]} ridges are pre-hazed, not fogged`);
  ok(horizon.children.some((m) => m.name === 'HAZE_PLAIN'),
    `${zoneKeys[i]} has a haze plain under its terrain`);
  ridgeRings += ridges.length;
}
ok(ridgeRings === 14, `fourteen hazed ridges across the march (${ridgeRings})`);

// the dome rides with the player, so it can never be walked out of
game.world.activate(STATES.DESERT);
game.player.group.position.set(12, 0, -20);
game.world.update(1 / 60, 1, game.player.group.position);
const domePos = game.world.activeZone.sky.mesh.position;
ok(Math.abs(domePos.x - 12) < 0.001 && Math.abs(domePos.z + 20) < 0.001,
  'the sky follows the player');
ok(scene.fog.color.getHex() === ATMOSPHERE.desert.fog.color,
  'the scene fogs to the desert horizon');

// photographic grades: every phase has one, and none is a neon filter
for (const state of zoneStates) {
  const name = PHASE_GRADES[state];
  ok(GRADES[name], `${state} has a grade (${name})`);
  ok(GRADES[name].saturation <= 1.10 && GRADES[name].saturation >= 0.85,
    `${name} grades photoreal, not vivid (${GRADES[name].saturation})`);
  ok(GRADES[name].contrast > 0.15 && GRADES[name].contrast < 0.5,
    `${name} has a sane contrast curve`);
}

// ------------------------------------- pass 12: the installed-app shell
const { initInstallShell } = await import('../src/core/install.js');
const shell = initInstallShell();
ok(shell && typeof shell.begin === 'function' && typeof shell.end === 'function',
  'the install shell boots headless and exposes begin/end');
ok(shell.isStandalone() === false, 'a browser tab is not standalone');
ok(typeof shell.isIOS() === 'boolean', 'the iOS probe answers on any engine');
// no service worker, no wake lock, no fullscreen in this environment: none may throw
await shell.begin();
await shell.end();
ok(true, 'wake lock and fullscreen are optional, never fatal');
shell.requestFullscreen();
ok(true, 'a refused fullscreen request is swallowed');

// the shell's own assets are the ones an install needs
const { readFileSync: readFs, existsSync } = await import('node:fs');
ok(existsSync('public/sw.js'), 'the service worker template ships');
ok(/__PRECACHE__/.test(readFs('public/sw.js', 'utf8')), 'its precache list is injected at build time');
for (const icon of ['icon-192.png', 'icon-512.png', 'icon-maskable-512.png', 'apple-touch-icon.png']) {
  ok(existsSync(`public/icons/${icon}`), `install icon present: ${icon}`);
}
const png = readFs('public/icons/icon-512.png');
ok(png[0] === 0x89 && png[1] === 0x50 && png.slice(12, 16).toString() === 'IHDR',
  'the icon is a real PNG');
ok(png.readUInt32BE(16) === 512 && png.readUInt32BE(20) === 512, 'the 512 icon is 512 square');

console.log(`\nAll ${passed} checks passed.\n`);
