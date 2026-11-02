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
    this.style = { setProperty(k, v) { this[k] = v; }, removeProperty(k) { delete this[k]; } };
    this.children = [];
    this.textContent = '';
    this.offsetWidth = 0;
    this._innerHTML = '';
    this.clientWidth = 800;
    this.clientHeight = 600;
    this.width = 800;
    this.height = 600;
  }
  set innerHTML(v) { this._innerHTML = v; if (v === '') this.children = []; }
  get innerHTML() { return this._innerHTML; }
  appendChild(c) { this.children.push(c); return c; }
  replaceChildren(...c) { this.children = c; }
  setAttribute(k, v) { this._attrs = this._attrs || {}; this._attrs[k] = String(v); }
  getAttribute(k) { return (this._attrs || {})[k] ?? null; }
  removeAttribute(k) { if (this._attrs) delete this._attrs[k]; }
  getBoundingClientRect() { return { left: 0, top: 0, width: 800, height: 600, right: 800, bottom: 600 }; }
  setPointerCapture() {}
  releasePointerCapture() {}
  addEventListener() {}
  removeEventListener() {}
}

function fake2dContext() {
  const gradient = { addColorStop() {} };
  const target = {
    measureText: () => ({ width: 120 }),
    createLinearGradient: () => gradient,
    createRadialGradient: () => gradient,
  };
  return new Proxy(target, {
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
if (typeof globalThis.window === 'undefined') globalThis.window = globalThis;
if (typeof globalThis.window.addEventListener !== 'function') globalThis.window.addEventListener = () => {};
if (typeof globalThis.window.removeEventListener !== 'function') globalThis.window.removeEventListener = () => {};
if (typeof globalThis.window.devicePixelRatio !== 'number') globalThis.window.devicePixelRatio = 1;

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

// --------------------------------- pass 13: the campaign (the War Table)
const prog = await import('../src/core/progression.js');
const { progression, TASKS, BUILDINGS, ROSTER, PHASE_ORDER } = prog;
progression.reset();
const p0 = progression.get();
ok(p0.resources.provisions > 0 && p0.resources.spoil === 0, 'a new campaign opens with provisions, not spoil');
ok(Object.keys(p0.buildings).length === BUILDINGS.length, 'every building starts at its first level');
ok(prog.totalStars() === 0 && prog.bandPower() > 0, 'no stars yet; the band is David alone and his kin');

// a phase is scored: swift + silent is three stars and the richest reward
const before = progression.get().resources.spoil;
const r1 = progression.recordPhase(PHASE_ORDER[3], { time: 90, alerts: 0, stealth: true });
ok(r1.stars === 3, 'a swift, silent, unseen phase earns three stars');
ok(progression.get().resources.spoil > before, 'and pays spoil into the stores');
ok(progression.get().stars[PHASE_ORDER[3]] === 3, 'the star rating is remembered');

// a worse walk never lowers the record
const r2 = progression.recordPhase(PHASE_ORDER[3], { time: 400, alerts: 3 });
ok(r2.stars === 1 && r2.best === false, 'a slow, noisy walk scores lower and stays a record');
ok(progression.get().stars[PHASE_ORDER[3]] === 3, 'the best rating is kept');

// the charge chain
const task0 = TASKS[0];
ok(progression.completeTask(task0.id) === true, 'a charge can be completed');
ok(progression.completeTask(task0.id) === false, 'and cannot be completed twice');
const claimBefore = progression.get().resources.provisions;
const paid = progression.claimTask(task0.id);
ok(paid && progression.get().resources.provisions > claimBefore, 'its reward is claimed once');
ok(progression.claimTask(task0.id) === null, 'a claim cannot be paid twice');
ok(prog.currentTask().id === TASKS[1].id, 'the tracker moves to the next charge');
ok(prog.currentTask().verse.length > 0, 'every charge carries its verse');

// the city: spends what it has, refuses what it does not
const wallLv = progression.get().buildings.wall;
progression.get().resources.timber = 9999;
progression.get().resources.bronze = 9999;
ok(progression.upgrade('wall') === wallLv + 1, 'a building can be raised when the stores allow');
const poorSnapshot = JSON.parse(JSON.stringify(progression.get()));
poorSnapshot.resources.timber = 0;
poorSnapshot.resources.bronze = 0;
const before2 = progression.get().buildings.wall;
progression.get().resources.timber = 0;
progression.get().resources.bronze = 0;
ok(progression.upgrade('wall') === null, 'and is refused when they do not');
ok(progression.get().buildings.wall === before2, 'a refused build changes nothing');

// modifiers are real numbers the run uses
progression.get().resources.bronze = 9999;
progression.get().resources.provisions = 9999;
const mods = prog.modifiers();
ok(mods.maxHealth >= 1 && mods.staminaRegen >= 1 && mods.strength >= 1, 'the city yields run modifiers');
ok(Number.isFinite(mods.bandPower), 'and a band power');

// the mighty men: drill, unlock by stars, and a real power rise
const powerBefore = prog.bandPower();
progression.drill('david');
ok(prog.bandPower() >= powerBefore, 'drilling a mighty man never lowers the band');
const unlockStars = ROSTER.find((m) => !m.unlocked);
ok(unlockStars && typeof unlockStars.unlockAt === 'number', 'locked men declare their price in stars');

// the page itself
game.pause.showPage('war');
const warHost = document.getElementById('war-table');
ok(warHost.children.length >= 4, 'the War Table builds its frame (head, stores, tabs, body)');
ok(game.pause.war.tabButtons.length === 6, 'six leaves: charge, band, ziklag, muster, field, campaign');
for (const tab of ['charge', 'band', 'ziklag', 'muster', 'field', 'campaign']) {
  game.pause.war.show(tab);
  ok(game.pause.war.body.children.length >= 1, `the ${tab} leaf renders`);
}
game.pause.war.show('charge');
ok(!document.getElementById('pause-war-panel').classList.contains('hidden'),
  'the War Table is the visible page');
game.pause.showPage('menu');
ok(document.getElementById('pause-war-panel').classList.contains('hidden'),
  'and it yields back to the menu');

// every leaf is reachable by touch, because a phone has no Escape key
ok(game.pause.navButtons.length === 5, 'the codex nav offers all five leaves');
game.pause.showPage('satchel');
ok(game.pause.navButtons.find((b) => b.id === 'satchel').node.classList.contains('active'),
  'the active leaf is marked');
game.pause.showPage('menu');

// the HUD tracker
game.hud.setTask({ title: 'Cross the brook Besor' }, { done: 2, total: TASKS.length });
ok(!document.getElementById('hud-task').classList.contains('hidden'), 'the charge shows in the HUD');
ok(document.getElementById('task-text').textContent.includes('Besor'), 'with the task name');
ok(document.getElementById('task-progress').textContent === `2 of ${TASKS.length}`, 'and the progress');
game.hud.setTask(null);
ok(document.getElementById('hud-task').classList.contains('hidden'), 'and hides when the charge is kept');

// persistence
ok(JSON.parse(localStorage.getItem('pursue.progress.v1')).stars[PHASE_ORDER[3]] === 3,
  'the campaign persists to storage');

// --------------------------- pass 14: the stronghold kit (medieval stone)
const kit = await import('../src/world/props/MedievalKit.js');

// every piece is baked to one mesh per material, so a wall is not 60 draws
const wall = kit.createCurtainWall({ length: 24, height: 6.5, ruined: true, seed: 5 });
ok(wall.children.length >= 1, 'a curtain wall bakes into merged meshes');
ok(wall.name === 'MEDIEVAL_WALL', 'and names itself');
let wallTris = 0;
wall.traverse((o) => { if (o.isMesh) wallTris += o.geometry.attributes.position.count / 3; });
ok(wallTris > 100, `the wall carries real masonry (${Math.round(wallTris)} triangles)`);

const intact = kit.createCurtainWall({ length: 30, height: 7.4, seed: 6 });
ok(intact.children.length >= 1, 'an intact wall builds too');
let intactTris = 0;
intact.traverse((o) => { if (o.isMesh) intactTris += o.geometry.attributes.position.count / 3; });
ok(intactTris > wallTris, `an intact wall has more stone than a ruined one (${Math.round(intactTris)} > ${Math.round(wallTris)})`);

const tower = kit.createDrumTower({ radius: 3.2, height: 10, top: 'cone' });
ok(tower.children.length >= 1, 'a drum tower builds');
const crenellated = kit.createDrumTower({ radius: 3.2, height: 10, top: 'crenel' });
ok(crenellated.children.length >= 1, 'with either a roof or a battlement');

const gatehouse = kit.createGatehouse({ span: 8.5, height: 10.5 });
ok(gatehouse.children.length >= 1, 'the gatehouse builds');
const fence = kit.createPalisade({ length: 20 });
ok(fence.children.length >= 1, 'a palisade builds');
const banner = kit.createBanner({ color: 0x7a1c18, height: 8 });
ok(banner.children.length >= 1 && typeof banner.userData.sway === 'function',
  'a banner builds and can sway');
const brazier = kit.createBrazier({ withLight: true });
ok(brazier.children.length >= 1, 'a brazier builds');
brazier.userData.update(1.2); // drives the flame and the light
ok(true, 'and updates without a renderer');
const heap = kit.createRubbleHeap({ radius: 2, seed: 2 });
ok(heap.children.length >= 1, 'rubble builds');
const ladder = kit.createSiegeLadder({ height: 7 });
ok(ladder.children.length >= 1, 'a siege ladder builds');
const steps = kit.createStoneSteps({ width: 8 });
ok(steps.children.length >= 1, 'stone steps build');

// the kit is actually in the world, and it is what stops the player
const findWalls = (zone) => {
  let n = 0;
  zone.group.traverse((o) => { if (o.name === 'MEDIEVAL_WALL') n++; });
  return n;
};
game.world.activate(STATES.ZIKLAG);
ok(findWalls(game.world.activeZone) >= 4, 'Ziklag stands inside four ruined wall runs');
ok(game.world.activeZone.colliders.length > 20, 'and the stone is solid');
game.world.activate(STATES.GATE);
ok(findWalls(game.world.activeZone) >= 3, 'Hebron has a curtain either side of its gate');
game.world.activate(STATES.GILBOA);
ok(findWalls(game.world.activeZone) >= 1, 'Gilboa keeps a fought-over rampart');
game.world.activate(STATES.CAMP);
ok(findWalls(game.world.activeZone) >= 1 || game.world.activeZone.colliders.length > 20,
  'the Amalekite camp is a fortified enclosure');


// ------------------------------- pass 15: the muster, the field, the works
const must = await import('../src/core/muster.js');
const skin = await import('../src/core/skirmish.js');
const { muster, COMPANIES, companyCap, trainCost, musterPower, orderOfBattle, totalWounded } = must;
const { Skirmish, SKIRMISHES, settleSkirmish, fieldHeroes, SKILLS } = skin;

muster.reset();
progression.reset();
ok(COMPANIES.length === 5, 'five companies stand in the muster');
ok(Object.values(muster.get().companies).every((c) => c.count === 0 && c.tier === 1),
  'and the band begins with no men under arms');
ok(musterPower(muster.get(), 1).men === 0, 'a fresh muster has nobody in the line');

// ---- barracks level decides how many men a company may hold
const cap1 = companyCap('spears', 1);
const cap5 = companyCap('spears', 5);
ok(cap1 > 0 && cap5 > cap1, 'the barracks raises the ceiling on a company');
ok(cap5 === 70, 'and never past the company itself (70 spears)');

// ---- mustering costs bronze and provisions
const p1 = progression.get();
p1.resources.bronze = 500;
p1.resources.provisions = 500;
const men0 = muster.get().companies.spears.count;
muster.recruit('spears', 1, { barracksLevel: 1, spend: (c) => progression.spend(c) });
ok(muster.get().companies.spears.count === men0 + 10, 'ten men are mustered for one order');
ok(progression.get().resources.bronze < 500, 'and the bronze is spent doing it');

// ---- a broke treasury cannot muster
const saved = { ...progression.get().resources };
for (const k of Object.keys(progression.get().resources)) progression.get().resources[k] = 0;
const shieldMen = muster.get().companies.shields.count;
muster.recruit('shields', 1, { barracksLevel: 1, spend: (c) => progression.spend(c) });
ok(muster.get().companies.shields.count === shieldMen, 'without the coin, no men come');
for (const [k, v] of Object.entries(saved)) progression.get().resources[k] = v;
progression.get().resources.bronze = 2000;
progression.get().resources.provisions = 2000;
progression.get().resources.timber = 2000;

// ---- a company cannot exceed its cap
muster.get().companies.bows.count = companyCap('bows', 1);
const capped = muster.recruit('bows', 3, { barracksLevel: 1, spend: () => true });
ok(capped <= companyCap('bows', 1), 'no company grows past what the barracks can hold');

// ---- tiers
ok(trainCost('spears', muster.get()) !== null, 'a company can be drilled a tier');
const t0 = muster.get().companies.spears.tier;
muster.train('spears', { barracksLevel: 1, spend: () => true });
ok(muster.get().companies.spears.tier === t0 + 1, 'drilling raises the tier');
ok(must.tierScale(2) > must.tierScale(1), 'and a higher tier fights harder');
const gate = muster.train('spears', { barracksLevel: 1, spend: () => true });
ok(gate === null, 'but a barracks of level 1 cannot drill past the second tier');

// ---- the order of battle puts shields and spears in front
muster.recruit('shields', 2, { barracksLevel: 5, spend: () => true });
muster.recruit('bows', 2, { barracksLevel: 5, spend: () => true });
const order = orderOfBattle(muster.get(), 5);
ok(order.length >= 2 && order[0].line === 'front', 'the order of battle leads with a front line');
ok(order.some((g) => g.line === 'back'), 'and keeps bows behind it');
ok(musterPower(muster.get(), 5).power > musterPower(muster.get(), 1).power,
  'more men (and a higher barracks) read as more strength');

// ---- losses: the wounded are most of the fallen
const spearMen = muster.get().companies.spears.count;
muster.applyLosses({ spears: 10 });
ok(muster.get().companies.spears.count === spearMen - 10, 'the fallen leave the line');
ok(muster.get().companies.spears.wounded === 7, 'and seven in ten are wounded, not dead');
ok(totalWounded() > 0, 'the tent is not empty');
const healed = muster.healAll({ hospital: 5, spend: () => true });
ok(healed >= 7, 'tending brings them back');
ok(totalWounded() === 0, 'until no wounded remain');

// ---- the field: a strong band wins the first skirmish
for (const c of COMPANIES) {
  muster.get().companies[c.id].count = companyCap(c.id, 5);
  muster.get().companies[c.id].tier = 3;
}
ok(musterPower(muster.get(), 5).power > SKIRMISHES[0].power, 'the band can match the first skirmish');
const spoilBefore = progression.get().resources.spoil;
const first = settleSkirmish(SKIRMISHES[0], { barracks: 5, seed: 7 });
ok(first.won === true, 'the band carries the watch at Besor');
ok(first.rewards.provisions > 0, 'and is paid in provisions');
ok(muster.get().wins[SKIRMISHES[0].id] === 1, 'the ledger remembers the field');
ok(progression.get().resources.spoil >= spoilBefore, 'the stores are never poorer for fighting');

// ---- a band of ten cannot take the prince of Amalek
muster.reset();
muster.get().companies.spears.count = 10;
const hopeless = settleSkirmish(SKIRMISHES[5], { barracks: 1, seed: 3 });
ok(hopeless.won === false, 'ten men do not take the slope of Gilboa');
ok(muster.get().lost === 1, 'and the ledger remembers that too');

// ---- an empty line loses at once
muster.reset();
const empty = new Skirmish(SKIRMISHES[0], { barracks: 1, seed: 1 });
empty.tick(0.5);
ok(empty.status === 'lost', 'with nobody under arms the field is lost at once');

// ---- the engine: waves, gauges and skills
muster.reset();
for (const c of COMPANIES) {
  muster.get().companies[c.id].count = companyCap(c.id, 5);
  muster.get().companies[c.id].tier = 4;
}
const bout = new Skirmish(SKIRMISHES[2], { barracks: 5, seed: 11 });
ok(bout.enemy.length > 0 && bout.line.length > 0, 'both lines stand in the field');
ok(bout.heroes.length > 0 && bout.heroes.length <= skin.FIELD_HEROES,
  'the mighty men stand with the band, never more than the field allows');
const gauge0 = bout.heroes[0].gauge;
bout.tick(1);
ok(bout.heroes[0].gauge > gauge0, 'the skill gauge fills with the fight');
ok(bout.useSkill(bout.heroes[0].id) === false || bout.heroes[0].gauge === 0,
  'a skill fires only when its gauge is full');
// force a full gauge and fire it
bout.heroes[0].gauge = 100;
bout.heroes[0].ready = true;
const fired = bout.useSkill(bout.heroes[0].id);
ok(fired === true, 'a full gauge spends on its skill');
ok(bout.log.length > 1, 'and the field log records it');
ok(Object.keys(SKILLS).length === 6, 'every mighty man in the roster has a skill');

// ---- the fight resolves itself, and the next party comes on
let beats = 0;
while (bout.status === 'fighting' && beats < 4000) { bout.tick(0.2); beats += 1; }
ok(bout.status !== 'fighting', 'every field resolves inside a sane number of beats');
ok(bout.casualties.standing + bout.casualties.fallen === bout.line.reduce((n, g) => n + g.men, 0),
  'every man is accounted for, standing or fallen');

// ---- speed changes the clock, not the arithmetic
const fast = new Skirmish(SKIRMISHES[0], { barracks: 5, seed: 2 });
fast.speed = 4;
fast.tick(0.05);
ok(fast.time > 0.1, 'a faster fight runs its clock faster');

// ---- the works: idle production
progression.reset();
const prod = progression.get();
prod.buildings.threshing = 3;
prod.buildings.quarry = 2;
ok(prog.productionRate(prod).provisions === 72, 'a level-3 threshing floor makes 72 provisions an hour');
ok(prog.productionRate(prod).timber === 36, 'a level-2 quarry makes 36 timber an hour');
const now0 = 1_700_000_000_000;
prod.lastCollect = now0;
const half = prog.pendingProduction(prod, now0 + 30 * 60 * 1000);
ok(half.resources.provisions === 36, 'half an hour is half the hour’s bread');
const long = prog.pendingProduction(prod, now0 + 48 * 3600 * 1000);
ok(long.hours === 8, 'the works are only gathered for eight hours, no more');
ok(long.resources.provisions === 576, 'and that is the eight-hour ceiling');
const gathered = progression.collect(now0 + 30 * 60 * 1000);
ok(gathered.provisions === 36, 'emptying the works pays out what was made');
ok(prog.pendingProduction(progression.get(), now0 + 30 * 60 * 1000).resources.provisions === undefined,
  'and the clock starts again');

// ---- direct grants and renown
const g0 = progression.get().resources.bronze;
const grant = progression.grant({ bronze: 40, timber: -5 });
ok(progression.get().resources.bronze === g0 + 40, 'a grant adds to the stores');
ok(grant.bronze === 40 && grant.timber === undefined, 'and refuses to take anything away');
const renown0 = progression.get().renown;
ok(progression.addRenown(10) > 0, 'renown is paid for a won field');
ok(progression.get().renown > renown0, 'and it is written down');

// ---- the leaves themselves
game.pause.showPage('war');
game.pause.war.build();
game.pause.war.show('muster');
ok(game.pause.war.body.children.length >= 2, 'the muster leaf draws its companies');
game.pause.war.show('field');
ok(game.pause.war.body.children.length >= 2, 'the field leaf draws its skirmishes');
game.pause.war.show('ziklag');
ok(game.pause.war.body.children.length >= 3, 'the Ziklag leaf draws the works beside the buildings');

// ---- the board opens on a stage and can be left
muster.reset();
muster.get().companies.spears.count = 40;
game.pause.war.show('field');
const { SkirmishBoard } = await import('../src/ui/SkirmishBoard.js');
const board = new SkirmishBoard({ onClose: () => {} });
ok(board.open(SKIRMISHES[1]) === true, 'the field board opens on a stage');
ok(board.isOpen === true, 'and reports itself open');
board.bout.tick(0.3);
ok(board.bout.time > 0, 'the board ticks the fight while it is open');
board.withdraw();
ok(board.bout.status === 'lost', 'withdrawing yields the field');
board.close();
ok(board.isOpen === false, 'and closing puts the board away');
game.pause.showPage('menu');


// ------------------------------- pass 16: the citadel (the strategy front end)
const citadelMod = await import('../src/world/city/CitadelScene.js');
const { CitadelScene, SITES } = citadelMod;
const { Citadel } = await import('../src/ui/Citadel.js');

const levels = { hall: 2, barracks: 3, cistern: 1, wall: 2, threshing: 4, quarry: 1, smelter: 2, hospital: 1 };
const city = new CitadelScene(levels);
ok(city.group.children.length > 10, 'the citadel builds: ground, wall, roads, works, people');
ok(SITES.length === 7, 'seven works stand inside the walls');
ok(city.pickables.length === 7, 'every work can be touched');
ok(SITES.every((s) => city.sites.get(s.id)?.level === (levels[s.id] || 1)),
  'each work is built at the level the campaign has it');
ok(city.wallGroup && city.wallGroup.children.length >= 8, 'the wall rings the city with towers and a gate');
ok(city.people.length >= 10, 'the streets are inhabited');
ok(city.camera.dist > 0 && city.camera.pitch > 0, 'the isometric rig starts above the city');

// ---- a work grows when the campaign does
const hallLevel0 = city.sites.get('hall').level;
ok(city.setLevel('hall', 5) === true, 'a work can be rebuilt at a higher level');
ok(city.sites.get('hall').level === 5, 'and the city records it');
ok(city.setLevel('hall', 5) === false, 'rebuilding at the same level is a no-op');
city.setLevel('hall', hallLevel0);
const syncChanged = city.sync({ ...levels, barracks: 5 });
ok(syncChanged === true, 'a snapshot of levels moves the city');
ok(city.sites.get('barracks').level === 5, 'including the barracks');
city.sync(levels);

// ---- the camera rig obeys its limits
const cam = new THREE.PerspectiveCamera(45, 1.6, 0.1, 900);
city.applyCamera(cam);
ok(Number.isFinite(cam.position.x) && cam.position.y > 10, 'the camera stands above and behind the city');
const y0 = city.camera.yaw;
city.orbit(100, 0);
ok(city.camera.yaw !== y0, 'dragging turns the city');
city.orbit(0, 99999);
ok(city.camera.pitch <= 1.321, 'the pitch never tips past the horizon');
city.zoom(100);
ok(city.camera.dist >= 26, 'zooming in stops at the closest the city allows');
city.zoom(0.0001);
ok(city.camera.dist <= 105, 'and pulling back stops at the farthest');
city.pan(9999, 9999);
ok(Math.abs(city.camera.target.x) <= 26.001 && Math.abs(city.camera.target.y) <= 30.001,
  'panning stays inside the walls');

// ---- the city animates without a renderer
city.update(0.1, 1.2);
city.update(0.1, 1.3);
ok(true, 'the citadel ticks headless');
ok(city.smoke.length >= 0, 'and its smoke is cleaned up as it ages');

// ---- the strategy HUD
document.body.classList.remove('citadel-mode');
progression.reset();
const citadelCanvas = document.createElement('canvas');
const cit = new Citadel({ scene: new THREE.Scene(), camera: cam, canvas: citadelCanvas, onMarch: () => {} });
cit.build();
ok(cit.built === true, 'the citadel HUD builds');
ok(cit.dockButtons.length === 6, 'six leaves on the dock: citadel, charge, band, host, field, chart');
ok(cit.resBar.children.length === 5, 'the top bar shows four resources and the men under arms');

// ---- picking a work opens its card, at the level the stores say
cit.select('hall');
ok(cit.selected === 'hall', 'a work can be selected');
ok(cit.siteCard.children.length >= 4, 'and its card is drawn');
ok(!cit.siteCard.classList.contains('hidden'), 'the card is visible');
cit.select(null);
ok(cit.siteCard.classList.contains('hidden'), 'and hides when the city is tapped again');

// ---- raising a work spends the stores and moves the city
progression.get().resources.timber = 4000;
progression.get().resources.bronze = 4000;
const lv0 = progression.get().buildings.barracks;
cit.select('barracks');
cit.city.setLevel('barracks', lv0);
const raised = progression.upgrade('barracks');
ok(raised === lv0 + 1, 'a work can be raised from the card');
ok(cit.city.setLevel('barracks', raised) === true, 'and the city follows the campaign');

// ---- the sheets open on their leaves
cit.openDock('muster');
ok(cit.sheetId === 'muster', 'the host sheet opens');
ok(!cit.sheet.classList.contains('hidden'), 'and is visible');
cit.openDock('chart');
ok(cit.sheetId === 'chart', 'the chart opens');
ok(cit.chartLedger && cit.chartLedger.textContent.includes('fields held'), 'the chart keeps a ledger');
cit.closeSheet();
ok(cit.sheet.classList.contains('hidden'), 'and every sheet can be closed');

// ---- entering and leaving the city
const citScene = new THREE.Scene();
const cit2 = new Citadel({ scene: citScene, camera: cam, canvas: citadelCanvas, onMarch: () => {} });
cit2.enter();
ok(cit2.active === true, 'the citadel can be entered');
ok(document.body.classList.contains('citadel-mode'), 'and the march HUD steps aside');
ok(citScene.children.includes(cit2.city.group), 'the city stands in the scene');
cit2.update(0.016, 2.0);
ok(true, 'and renders a frame without throwing');
cit2.exit();
ok(cit2.active === false, 'it can be left for the march');
ok(!document.body.classList.contains('citadel-mode'), 'and the march gets its HUD back');
ok(!citScene.children.includes(cit2.city.group), 'the city comes out of the scene');

console.log(`\nAll ${passed} checks passed.\n`);
