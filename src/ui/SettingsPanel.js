/**
 * SettingsPanel.js — the "Settings" leaf of the pause codex.
 *
 * A physical page: sliders are incised grooves with a bronze hilt, toggles are
 * pressed wax seals that fill when set. Everything writes straight through
 * `core/settings.js`, which persists to localStorage and notifies the systems
 * that care (camera sensitivity, post-processing, audio, handheld motion).
 *
 * Headless-safe: every node is looked up and guarded, so the smoke test can
 * build and drive the page with the stub DOM.
 */
import { settings, DEFAULTS } from '../core/settings.js';

const ROWS = [
  {
    key: 'sensitivity', type: 'range', label: 'Look sensitivity',
    min: 0.4, max: 2, step: 0.05, format: (v) => `${Math.round(v * 100)}%`,
  },
  {
    key: 'cameraDistance', type: 'range', label: 'Camera distance',
    min: 3.5, max: 15, step: 0.5, format: (v) => `${v.toFixed(1)} m`,
  },
  {
    key: 'volume', type: 'range', label: 'Sound volume',
    min: 0, max: 1, step: 0.05, format: (v) => (v === 0 ? 'silent' : `${Math.round(v * 100)}%`),
  },
  { key: 'invertY', type: 'toggle', label: 'Invert vertical look' },
  { key: 'handheld', type: 'toggle', label: 'Handheld camera motion' },
  { key: 'godRays', type: 'toggle', label: 'God rays through the smoke' },
  { key: 'depthOfField', type: 'toggle', label: 'Depth of field (desktop)' },
  { key: 'filmGrain', type: 'toggle', label: 'Film grain' },
  { key: 'sound', type: 'toggle', label: 'Ambient sound' },
  { key: 'cinematicKey', type: 'toggle', label: 'Clean frame on P' },
];

function el(tag, className, text) {
  const node = document.createElement(tag);
  if (className && node.classList) node.classList.add(...className.split(' '));
  if (text !== undefined) node.textContent = text;
  return node;
}

export class SettingsPanel {
  /** @param {{host?: HTMLElement, onChange?: (snapshot)=>void}} [opts] */
  constructor(opts = {}) {
    this.host = opts.host || document.getElementById('settings-list');
    this.onChange = opts.onChange || null;
    this.controls = new Map();
    this.built = false;
  }

  build() {
    if (this.built || !this.host || !this.host.appendChild) return;
    this.built = true;
    if (this.host.replaceChildren) this.host.replaceChildren();

    for (const row of ROWS) {
      const line = el('div', `setting-row ${row.type}`);
      line.appendChild(el('label', 'setting-label', row.label));

      if (row.type === 'range') {
        const wrap = el('div', 'setting-control');
        const input = document.createElement('input');
        input.type = 'range';
        input.min = String(row.min);
        input.max = String(row.max);
        input.step = String(row.step);
        input.value = String(settings.get(row.key));
        input.className = 'setting-range';
        const readout = el('span', 'setting-value', row.format(settings.get(row.key)));
        input.addEventListener('input', () => {
          const v = Number(input.value);
          readout.textContent = row.format(v);
          settings.set({ [row.key]: v });
          if (this.onChange) this.onChange(settings.get());
        });
        wrap.appendChild(input);
        wrap.appendChild(readout);
        line.appendChild(wrap);
        this.controls.set(row.key, { input, readout, row });
      } else {
        const seal = el('button', 'setting-seal');
        seal.type = 'button';
        seal.setAttribute('role', 'switch');
        seal.setAttribute('aria-checked', settings.get(row.key) ? 'true' : 'false');
        if (seal.classList) seal.classList.toggle('on', Boolean(settings.get(row.key)));
        seal.addEventListener('click', () => {
          const next = !settings.get(row.key);
          settings.set({ [row.key]: next });
          if (seal.classList) seal.classList.toggle('on', next);
          seal.setAttribute('aria-checked', next ? 'true' : 'false');
          if (this.onChange) this.onChange(settings.get());
        });
        line.appendChild(seal);
        this.controls.set(row.key, { input: seal, row });
      }
      this.host.appendChild(line);
    }

    // reset line
    const foot = el('div', 'setting-row reset');
    foot.appendChild(el('span', 'setting-hint', 'Written in pencil, forgotten gladly.'));
    const reset = el('button', 'setting-reset', 'Restore defaults');
    reset.type = 'button';
    reset.addEventListener('click', () => {
      settings.reset();
      this.sync();
      if (this.onChange) this.onChange(settings.get());
    });
    foot.appendChild(reset);
    this.host.appendChild(foot);
  }

  /** Push the stored values back into the widgets (after reset / load). */
  sync() {
    for (const [key, ctl] of this.controls) {
      const v = settings.get(key);
      if (ctl.row.type === 'range') {
        ctl.input.value = String(v);
        if (ctl.readout) ctl.readout.textContent = ctl.row.format(v);
      } else if (ctl.input.classList) {
        ctl.input.classList.toggle('on', Boolean(v));
        ctl.input.setAttribute('aria-checked', v ? 'true' : 'false');
      }
    }
  }

  open() {
    this.build();
    this.sync();
  }
}

export const SETTING_DEFAULTS = DEFAULTS;
