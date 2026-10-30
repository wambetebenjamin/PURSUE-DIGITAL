/**
 * settings.js — the player's preferences, stored like a written marginalia.
 *
 * Small, dependency-free store over localStorage with safe defaults, so the
 * game boots identically on a locked-down browser (private mode, file://) and
 * on a normal one. Every system reads through `settings.get()`; the settings
 * leaf in the pause codex writes through `settings.set()` and fires listeners.
 */

const KEY = 'pursue.settings.v1';

export const DEFAULTS = Object.freeze({
  /** Look sensitivity multiplier (0.4 – 2.0). */
  sensitivity: 1.0,
  /** Invert the vertical look axis. */
  invertY: false,
  /** Camera pull-back in metres (3.5 – 15). */
  cameraDistance: 9.5,
  /** Screen-space god rays. */
  godRays: true,
  /** Depth of field (bokeh) — desktop only. */
  depthOfField: true,
  /** Film grain and vignette. */
  filmGrain: true,
  /** Handheld camera sway and trauma shake. */
  handheld: true,
  /** Procedural audio. */
  sound: true,
  /** Master volume 0 – 1. */
  volume: 0.55,
  /** Hide the HUD and letterbox the frame for a clean shot. */
  cinematicKey: true,
});

let current = { ...DEFAULTS };
const listeners = new Set();

function canStore() {
  try {
    return typeof localStorage !== 'undefined' && localStorage !== null;
  } catch (_e) {
    return false;
  }
}

function load() {
  if (!canStore()) return;
  try {
    const raw = localStorage.getItem(KEY);
    if (!raw) return;
    const parsed = JSON.parse(raw);
    for (const k of Object.keys(DEFAULTS)) {
      if (parsed[k] !== undefined && typeof parsed[k] === typeof DEFAULTS[k]) {
        current[k] = parsed[k];
      }
    }
  } catch (_e) {
    /* corrupt or blocked storage: defaults stand */
  }
}

function persist() {
  if (!canStore()) return;
  try {
    localStorage.setItem(KEY, JSON.stringify(current));
  } catch (_e) {
    /* quota or private mode: preferences simply do not survive the tab */
  }
}

load();

export const settings = {
  /** Read one key, or the whole snapshot. */
  get(key) {
    return key === undefined ? { ...current } : current[key];
  },

  /** Write one key (or several) and notify listeners. */
  set(patch) {
    let changed = false;
    for (const [k, v] of Object.entries(patch)) {
      if (!(k in DEFAULTS)) continue;
      if (current[k] === v) continue;
      current[k] = v;
      changed = true;
    }
    if (!changed) return current;
    persist();
    for (const fn of listeners) {
      try {
        fn(current);
      } catch (_e) {
        /* a broken listener must not break the settings leaf */
      }
    }
    return { ...current };
  },

  /** Subscribe to changes; returns an unsubscribe function. */
  onChange(fn) {
    listeners.add(fn);
    return () => listeners.delete(fn);
  },

  reset() {
    current = { ...DEFAULTS };
    persist();
    for (const fn of listeners) fn(current);
    return { ...current };
  },
};
