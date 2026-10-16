/**
 * main.js — bootstrap: engine + game + UI wiring.
 *
 * Flow: loading → title screen → (click) pointer lock + STATE_0 → … → victory.
 */
import './ui/styles.css';
import * as THREE from 'three';
import { Engine } from './core/Engine.js';
import { audio } from './audio/AudioEngine.js';
import { Input } from './core/Input.js';
import { Game } from './core/Game.js';

// ---------------------------------------------------------------- engine
const mount = document.getElementById('app');
const input = new Input();

// Game is constructed before Engine so Engine.update has something to call;
// the scene/camera come from the engine, so we wire them through a holder.
const gameHolder = { current: null };

const engine = new Engine(mount, {
  update: (dt, elapsed) => gameHolder.current?.update(dt, elapsed),
});

input.attach(engine.canvas);
const game = new Game({
  scene: engine.scene,
  camera: engine.camera,
  input,
});
gameHolder.current = game;

// -------------------------------------------------------------- UI wiring
const loadingEl = document.getElementById('loading');
const startScreen = document.getElementById('start-screen');
const startButton = document.getElementById('start-button');
const resumeHint = document.getElementById('resume-hint');

// pointer-lock hint follows lock state (only once the game has begun)
input.onPointerLockChange = (locked) => {
  const showModal = game.modal.isOpen;
  const showHint = game.started && !game.finished && !locked && !showModal;
  resumeHint.classList.toggle('hidden', !showHint);
};

startButton.addEventListener('click', () => {
  startScreen.classList.add('hidden');
  audio.init();
  audio.resume();
  game.start();
});

// ------------------------------------------------------------- audio
const audioToggle = document.getElementById('audio-toggle');
audioToggle.addEventListener('click', () => {
  audio.init();
  audio.resume();
  const muted = audio.toggleMuted();
  audioToggle.classList.toggle('muted', muted);
  audioToggle.textContent = muted ? '✕' : '♪';
});

// click anywhere on the canvas re-acquires the pointer (Input handles it),
// but also hide the hint immediately for crisp feedback
engine.canvas.addEventListener('click', () => {
  resumeHint.classList.add('hidden');
});

// --------------------------------------------------------------- startup
engine.start();

// one warm-up frame so shaders compile before we drop the loading veil
requestAnimationFrame(() => {
  requestAnimationFrame(() => {
    loadingEl.classList.add('done');
    setTimeout(() => loadingEl.classList.add('hidden'), 600);
  });
});

// expose for debugging in the console (handy on deployed builds)
window.PURSUE = { engine, game, THREE };
