/**
 * main.js — bootstrap: engine + game + UI wiring.
 *
 * Flow: loading → title screen → (click) pointer lock + STATE_0 → … → victory.
 */
import './ui/styles.css';
import * as THREE from 'three';
import { Engine } from './core/Engine.js';
import { audio } from './audio/AudioEngine.js';
import { TouchControls } from './ui/TouchControls.js';
import { IS_TOUCH } from './core/quality.js';
import { Input } from './core/Input.js';
import { Game } from './core/Game.js';
import { settings } from './core/settings.js';
import { postFX } from './core/PostFX.js';
import { initInstallShell } from './core/install.js';
import { Citadel } from './ui/Citadel.js';

// ---------------------------------------------------------------- engine
const mount = document.getElementById('app');
const input = new Input();

// Game is constructed before Engine so Engine.update has something to call;
// the scene/camera come from the engine, so we wire them through a holder.
const gameHolder = { current: null };

const engine = new Engine(mount, {
  update: (dt, elapsed) => {
    if (citadel.active) citadel.update(dt, elapsed);
    else gameHolder.current?.update(dt, elapsed);
  },
});

input.attach(engine.canvas);

// phone play: on-screen controls + touch styling
if (IS_TOUCH) {
  document.body.classList.add('touch-mode');
  // eslint-disable-next-line no-new
  new TouchControls(input);
}

const game = new Game({
  scene: engine.scene,
  camera: engine.camera,
  input,
});
gameHolder.current = game;

// ------------------------------------------------ the strategy front end
// The game opens on the city from above — the works, the host, the charge —
// and the pursuit is one button away. Everything the citadel shows is read
// from the same stores the march uses.
const citadel = new Citadel({
  scene: engine.scene,
  camera: engine.camera,
  canvas: engine.canvas,
  input,
  onMarch: () => {
    citadel.exit();
    game.hud.setVisible(true);
    game.start();
    shell.begin();
    if (IS_TOUCH) shell.requestFullscreen();
  },
});

/** Put the camera back over Ziklag, with every other zone dark. */
function enterCitadel() {
  game.pause.close(false);
  game.started = false;
  game.hud.setVisible(false);
  for (const zone of game.world.zones.values()) zone.group.visible = false;
  engine.scene.fog.color.set(0xd6c2a0);
  engine.scene.fog.density = 0.0032;
  postFX.setGrade('clear');
  citadel.enter();
}
window.PURSUE_CITADEL = { citadel, enterCitadel };

// ------------------------------------------------------------- audio
const audioToggle = document.getElementById('audio-toggle');
audioToggle.addEventListener('click', () => {
  audio.init();
  audio.resume();
  const on = !settings.get('sound');
  // a silenced master that is switched back on must have somewhere to go
  settings.set(on && settings.get('volume') === 0 ? { sound: true, volume: 0.55 } : { sound: on });
});

// ---------------------------------------------------- settings → systems
const applySettings = (snap) => {
  // audio — muted state and master gain are independent leaves
  audio.setVolume(snap.volume);
  audio.setMuted(!snap.sound);
  const audible = snap.sound && snap.volume > 0;
  audioToggle.classList.toggle('muted', !audible);
  audioToggle.textContent = audible ? '♪' : '✕';
  audioToggle.setAttribute('aria-label', audible ? 'Mute sound' : 'Unmute sound');
  audioToggle.setAttribute('title', audible ? 'Mute sound' : 'Unmute sound');
  // camera
  game.cameraRig.sensitivity = snap.sensitivity;
  game.cameraRig.invertY = snap.invertY;
  game.cameraRig.handheld = snap.handheld ? 1 : 0;
  if (snap.cameraDistance) game.cameraRig.baseDistance = snap.cameraDistance;
  // post-processing
  postFX.setFeatures({
    godRays: snap.godRays,
    depthOfField: snap.depthOfField,
    filmGrain: snap.filmGrain,
  });
};
applySettings(settings.get());
settings.onChange(applySettings);

// -------------------------------------------------------------- UI wiring
const loadingEl = document.getElementById('loading');
const startScreen = document.getElementById('start-screen');
const startButton = document.getElementById('start-button');
const resumeHint = document.getElementById('resume-hint');

// pointer-lock hint follows lock state (only once the game has begun)
input.onPointerLockChange = (locked) => {
  const showModal = game.modal.isOpen;
  const showHint = !IS_TOUCH && game.started && !game.finished && !locked && !showModal;
  resumeHint.classList.toggle('hidden', !showHint);
};

const shell = initInstallShell();

startButton.addEventListener('click', () => {
  startScreen.classList.add('hidden');
  audio.init();
  audio.resume();
  enterCitadel();
  shell.begin();
});

// a way back to the city from the march's own pause page
const pausePanel = document.getElementById('pause-menu-panel');
if (pausePanel && pausePanel.appendChild) {
  const home = document.createElement('button');
  home.id = 'pause-citadel';
  home.className = 'secondary-button';
  home.textContent = 'The Citadel';
  home.addEventListener('click', () => {
    game.resetAll();
    enterCitadel();
  });
  pausePanel.appendChild(home);
}

// the screen may sleep over a paused march, and must not over a running one
document.addEventListener('visibilitychange', () => {
  if (document.hidden) {
    shell.end();
    // an app that loses focus must stop the march, not run it in a pocket
    if (game.started && !game.finished && !game.pause.isOpen) game.pause.open('menu');
  } else if (game.started && !game.paused && !game.finished) {
    shell.begin();
  }
});

// pause overlay, dialogue and victory all stop the clock: no wake lock there
const shellSync = () => {
  if (!game.started || game.modal?.isOpen || game.paused || game.finished) shell.end();
  else shell.begin();
};
// cheap and complete: polls the game's own state rather than instrumenting
// every overlay that can stop the clock
setInterval(shellSync, 700);

// click anywhere on the canvas re-acquires the pointer (Input handles it),
// but also hide the hint immediately for crisp feedback
engine.canvas.addEventListener('click', () => {
  resumeHint.classList.add('hidden');
});

// unlock audio on the very first touch (mobile autoplay policies)
window.addEventListener('touchstart', () => {
  audio.init();
  audio.resume();
}, { once: true, passive: true });

// --------------------------------------------------------------- startup
// --------------------------------------------------- cinematic mode (P)
let cinematic = false;
const letterbox = document.getElementById('letterbox');
const setCinematic = (on) => {
  cinematic = on;
  document.body.classList.toggle('cinematic', on);
  if (letterbox) letterbox.classList.toggle('on', on);
  game.hud.setVisible(!on && game.started && !game.finished);
};
window.addEventListener('keydown', (e) => {
  if (e.code === 'KeyP' && settings.get('cinematicKey') && game.started && !game.modal.isOpen) {
    setCinematic(!cinematic);
  }
  if (e.code === 'Enter' && !startScreen.classList.contains('hidden')) {
    startButton.click();
  }
});

engine.start();

// one warm-up frame so shaders compile before we drop the loading veil
requestAnimationFrame(() => {
  requestAnimationFrame(() => {
    loadingEl.classList.add('done');
    setTimeout(() => loadingEl.classList.add('hidden'), 600);
  });
});

// expose for debugging in the console (handy on deployed builds)
window.PURSUE = { engine, game, citadel, THREE };
