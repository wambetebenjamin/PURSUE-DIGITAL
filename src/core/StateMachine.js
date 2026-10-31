/**
 * StateMachine.js — tiny finite state machine.
 *
 * Each state is `{ enter(payload), update(dt), exit() }`.
 * `set(name)` is safe to call from inside `update` — the transition is
 * applied immediately, and the new state's `update` runs next frame.
 */
export class StateMachine {
  constructor(debugName = 'FSM') {
    this.name = debugName;
    this.states = new Map();
    this.current = null;
    this.currentName = null;
    this._previousName = null;
  }

  add(name, handlers) {
    this.states.set(name, {
      enter: handlers.enter ?? (() => {}),
      update: handlers.update ?? (() => {}),
      exit: handlers.exit ?? (() => {}),
    });
    return this;
  }

  set(name, payload) {
    if (!this.states.has(name)) throw new Error(`[FSM:${this.name}] unknown state "${name}"`);
    if (this.current) this.current.exit();
    this._previousName = this.currentName;
    this.currentName = name;
    this.current = this.states.get(name);
    this.current.enter(payload);
    return this;
  }

  update(dt) {
    this.current?.update(dt);
  }

  previousName() {
    return this._previousName;
  }
}
