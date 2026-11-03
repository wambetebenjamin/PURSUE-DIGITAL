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
  if (campUp && campModule) {
    campModule.stopCamp();
    campUp = false;
  }
  engine.resume();
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

/* -------------------------------------------------------------------------
   THE CAMP — the 2D canvas of Ziklag, and the way into the march.

   The camp is the front of the house: the player comes out of the title
   card into the encampment, works the stores there, sends the band out, and
   when they are ready they take the road into the 3D march.

   Phaser is a large library and the march does not need it, so the camp is
   fetched on its own: the title card comes up at once and the encampment
   arrives a moment later.
   ---------------------------------------------------------------------- */

let campModule = null;
let campUp = false;

/** Fetch the camp once, then keep it. */
async function loadCamp() {
  if (!campModule) campModule = await import('./game/index.js');
  return campModule;
}

/** Hand the frame to the 2D camp. */
async function enterCamp() {
  citadel.exit();          // the citadel's own panel steps aside
  engine.suspend();        // the 3D world waits behind the canvas
  const mod = await loadCamp();
  campUp = true;
  const started = await mod.launchCamp({
    onLeave: () => {
      // "Take the road": the camp is torn down and the march begins.
      mod.stopCamp();
      campUp = false;
      engine.resume();
      game.hud.setVisible(true);
      game.start();
      shell.begin();
      if (IS_TOUCH) shell.requestFullscreen();
    },
    onCitadel: () => {
      // "the citadel": back to the city from above.
      mod.stopCamp();
      campUp = false;
      engine.resume();
      enterCitadel();
    },
  });
  if (!started) campUp = false;   // no container: the camp never stood up
}

startButton.addEventListener('click', async () => {
  startScreen.classList.add('hidden');
  audio.init();
  audio.resume();
  await enterCamp();
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

  // and a way back to the camp, which is where the game begins
  const camp = document.createElement('button');
  camp.id = 'pause-camp';
  camp.className = 'secondary-button';
  camp.textContent = 'The Encampment';
  camp.addEventListener('click', async () => {
    game.resetAll();
    await enterCamp();
  });
  pausePanel.appendChild(camp);
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
  const marching = game.started && !game.modal?.isOpen && !game.paused && !game.finished;
  if (marching || campUp) shell.begin();
  else shell.end();
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

// The camp comes up behind the title card: the encampment of Ziklag is the
// first thing on the screen, and the 3D march waits behind it.
enterCamp().then(() => {
  document.body.classList.add('camp-behind');
});

// one warm-up frame so shaders compile before we drop the loading veil
requestAnimationFrame(() => {
  requestAnimationFrame(() => {
    loadingEl.classList.add('done');
    setTimeout(() => loadingEl.classList.add('hidden'), 600);
  });
});

// expose for debugging in the console (handy on deployed builds)
window.PURSUE = { engine, game, citadel, THREE };
