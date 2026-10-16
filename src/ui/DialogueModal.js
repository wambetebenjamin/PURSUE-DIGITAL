/**
 * DialogueModal.js — narrative / dialogue overlay.
 *
 * Supports one-shot narrative cards ("Next") and multi-step dialogue
 * sequences with per-step button labels (used for the Egyptian Servant).
 * While open: input is disabled and the pointer is unlocked.
 */
export class DialogueModal {
  constructor(input) {
    this.input = input;
    this.root = document.getElementById('modal-root');
    this.titleEl = document.getElementById('modal-title');
    this.speakerEl = document.getElementById('modal-speaker');
    this.bodyEl = document.getElementById('modal-body');
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

  /**
   * Show a simple narrative card.
   * @param {{title?:string, body:string, button?:string}} opts
   * @param {() => void} onDone
   */
  showNarrative(opts, onDone) {
    this.showSequence(
      [{ title: opts.title, body: opts.body, button: opts.button ?? 'Continue' }],
      onDone,
    );
  }

  /**
   * Show a multi-step dialogue.
   * @param {{title?:string, speaker?:string, body:string, button?:string}[]} steps
   * @param {() => void} [onDone]
   */
  showSequence(steps, onDone) {
    this._steps = steps;
    this._index = 0;
    this._onDone = onDone ?? null;
    this.open = true;
    this.root.classList.remove('hidden');
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
