/**
 * DialogueModal.js — themed narrative / dialogue overlay.
 *
 * Cards render with a per-phase theme (ember, sandstone, night, gold,
 * marble, slate…), speaker chips, scripture citations, and soft synthesized
 * UI sound. Supports one-shot narrative cards and multi-step sequences.
 * While open: input is disabled and the pointer is unlocked.
 */
import { audio } from '../audio/AudioEngine.js';

/** Cinematic act badge shown above each card title. */
const THEME_LABELS = {
  ziklag: 'Act I · Ziklag in Ruins',
  desert: 'Act II · Through the Besor',
  egyptian: 'Act III · The Servant',
  camp: 'Act IV · The Night Ambush',
  spoils: 'Act V · The Dividing Line',
  gate: 'Act VI · The Tribute',
  gilboa: 'Act VII · Gilboa',
};

export class DialogueModal {
  constructor(input) {
    this.input = input;
    this.root = document.getElementById('modal-root');
    this.card = document.getElementById('modal-card');
    this.phaseEl = document.getElementById('modal-phase');
    this.titleEl = document.getElementById('modal-title');
    this.speakerEl = document.getElementById('modal-speaker');
    this.bodyEl = document.getElementById('modal-body');
    this.citeEl = document.getElementById('modal-cite');
    this.buttonsEl = document.getElementById('modal-buttons');
    this.open = false;
    this._steps = [];
    this._index = 0;
    this._onDone = null;
    this._advanceOpen = false;
  }

  get isOpen() {
    return this.open;
  }

  /** Apply a card theme: 'ziklag' | 'desert' | 'egyptian' | 'camp' | 'spoils' | 'gate' | 'gilboa'. */
  setTheme(theme = 'ziklag') {
    for (const t of ['ziklag', 'desert', 'egyptian', 'camp', 'spoils', 'gate', 'gilboa']) {
      this.card?.classList.toggle(`theme-${t}`, t === theme);
    }
    if (this.phaseEl) {
      this.phaseEl.textContent = THEME_LABELS[theme] ?? '';
    }
  }

  /**
   * Show a simple narrative card.
   * @param {{title?:string, body:string, button?:string, cite?:string}} opts
   * @param {() => void} [onDone]
   * @param {string} [theme]
   */
  showNarrative(opts, onDone, theme) {
    this.showSequence(
      [{
        title: opts.title,
        body: opts.body,
        button: opts.button ?? 'Continue',
        cite: opts.cite,
      }],
      onDone,
      theme,
    );
  }

  /**
   * Show a multi-step dialogue.
   * @param {{title?:string, speaker?:string, body:string, button?:string, cite?:string}[]} steps
   * @param {() => void} [onDone]
   * @param {string} [theme]
   */
  showSequence(steps, onDone, theme) {
    this._steps = steps;
    this._index = 0;
    this._onDone = onDone ?? null;
    this.open = true;
    if (theme) this.setTheme(theme);
    this.root.classList.remove('hidden');
    this.root.classList.remove('card-in');
    void this.root.offsetWidth;
    this.root.classList.add('card-in');
    this.input.enabled = false;
    this.input.exitLock();
    this._renderStep();
  }

  _renderStep() {
    const step = this._steps[this._index];

    this.titleEl.textContent = step.title ?? '';
    this.titleEl.classList.toggle('hidden', !step.title);

    if (step.speaker) {
      this.speakerEl.textContent = step.speaker;
      this.speakerEl.classList.remove('hidden');
    } else {
      this.speakerEl.classList.add('hidden');
    }

    this.bodyEl.textContent = step.body;

    if (this.citeEl) {
      this.citeEl.textContent = step.cite ? `— ${step.cite}` : '';
      this.citeEl.classList.toggle('hidden', !step.cite);
    }

    this.buttonsEl.innerHTML = '';

    const btn = document.createElement('button');
    btn.className = 'primary-button';
    btn.textContent = step.button ?? 'Continue';
    btn.addEventListener('click', () => this._advance());
    this.buttonsEl.appendChild(btn);
    this._advanceOpen = true;
  }

  /** Keyboard shortcut: E / Space advances exactly like the button. */
  tryAdvanceFromKeyboard() {
    if (this.open && this._advanceOpen) this._advance();
  }

  _advance() {
    audio.ui('advance');
    this._index += 1;
    if (this._index < this._steps.length) {
      this._renderStep();
      return;
    }
    this.close();
  }

  close() {
    this.open = false;
    this._advanceOpen = false;
    this.root.classList.add('hidden');
    this.input.enabled = true;
    this.input.requestLock();
    const done = this._onDone;
    this._onDone = null;
    if (done) done();
  }
}
