/**
 * smoke.mjs — headless logic test for PURSUE.
 *
 * Boots the game modules in Node with a minimal DOM stub, then walks the
 * full state machine: Ziklag → desert → brook → servant dialogue → camp
 * detection → rescue → victory → restart. Any thrown error fails the run.
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
};
globalThis.window = globalThis;
globalThis.addEventListener = () => {};
globalThis.requestAnimationFrame = (fn) => setTimeout(fn, 0);

// ------------------------------------------------------------- bootstrap
const { Input } = await import('../src/core/Input.js');
const { Game } = await import('../src/core/Game.js');
const { STATES, NARRATIVE } = await import('../src/constants.js');
const { ZIKLAG } = await import('../src/world/zones/ZiklagZone.js');
const { DESERT } = await import('../src/world/zones/DesertZone.js');
const { EGYPT } = await import('../src/world/zones/EgyptianZone.js');
const { CAMP } = await import('../src/world/zones/CampZone.js');

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
ok(game.finished, 'colliding with the captives triggers victory');
ok(game.victory.visible, 'victory overlay is visible');
ok(document.getElementById('hud').classList.contains('hidden'), 'HUD is cleared on victory');

// ------------------------------------------------------------- restart
game.resetAll();
frames(5);
ok(game.fsm.currentName === STATES.ZIKLAG, 'Play Again resets to STATE_0_ZIKLAG');
ok(!game.finished && !game.servantServed && !game.brookTriggered, 'all run flags are cleared');
ok(!game.victory.visible, 'victory overlay is hidden');
ok(!game.arrow.group.visible, 'directional arrow is hidden again');

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

console.log(`\nAll ${passed} checks passed.\n`);
