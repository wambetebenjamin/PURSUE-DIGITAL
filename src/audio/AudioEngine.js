/**
 * AudioEngine.js — procedural sound design for PURSUE.
 *
 * Everything is synthesized live with the Web Audio API: layered ambience
 * (wind, fire crackle, brook water, crickets, birdsong, rain), thematic
 * tonal pads per phase, and gameplay SFX (footsteps, UI, alert, battle
 * drums, victory). No audio files, no downloads.
 *
 * Every method is a no-op until `init()` runs on a user gesture, and a
 * no-op entirely in environments without AudioContext (headless smoke).
 */
export class AudioEngine {
  constructor() {
    this.ready = false;
    this.muted = false;
    this._ctx = null;
    this._master = null;
    this._layers = {};
    this._pad = null;
    this._timers = { crackle: 0, bird: 0, cricket: 0 };
    this._ambience = 'none';
  }

  // ------------------------------------------------------------- setup
  init() {
    if (this.ready) return;
    const Ctx = typeof window !== 'undefined'
      ? (window.AudioContext || window.webkitAudioContext)
      : null;
    if (!Ctx) return;
    try {
      this._ctx = new Ctx();
    } catch {
      return;
    }
    const ctx = this._ctx;

    this._master = ctx.createGain();
    this._master.gain.value = 0.55;
    this._master.connect(ctx.destination);

    // ---- shared noise buffers
    this._noise = this._makeNoise(2.0, false);
    this._brown = this._makeNoise(2.0, true);

    // ---- ambience layers (each: source loop -> filter -> gain -> master)
    const layer = (name, buffer, type, freq, q, gain) => {
      const src = ctx.createBufferSource();
      src.buffer = buffer;
      src.loop = true;
      const filter = ctx.createBiquadFilter();
      filter.type = type;
      filter.frequency.value = freq;
      filter.Q.value = q;
      const g = ctx.createGain();
      g.gain.value = 0;
      src.connect(filter).connect(g).connect(this._master);
      src.start();
      this._layers[name] = { src, filter, gain: g, base: gain };
    };
    layer('wind', this._brown, 'lowpass', 420, 0.6, 0.16);
    layer('fire', this._brown, 'lowpass', 900, 0.8, 0.10);
    layer('water', this._noise, 'bandpass', 950, 0.8, 0.10);
    layer('rain', this._noise, 'highpass', 1400, 0.5, 0.10);
    layer('murmur', this._noise, 'bandpass', 500, 1.2, 0.05);

    // slow LFO on the wind so it breathes
    const lfo = ctx.createOscillator();
    lfo.frequency.value = 0.08;
    const lfoGain = ctx.createGain();
    lfoGain.gain.value = 140;
    lfo.connect(lfoGain).connect(this._layers.wind.filter.frequency);
    lfo.start();

    // ---- tonal pad bus
    this._padGain = ctx.createGain();
    this._padGain.gain.value = 0;
    const padFilter = ctx.createBiquadFilter();
    padFilter.type = 'lowpass';
    padFilter.frequency.value = 620;
    this._padGain.connect(padFilter).connect(this._master);

    this.ready = true;
  }

  _makeNoise(seconds, brown) {
    const ctx = this._ctx;
    const len = ctx.sampleRate * seconds;
    const buf = ctx.createBuffer(1, len, ctx.sampleRate);
    const data = buf.getChannelData(0);
    let last = 0;
    for (let i = 0; i < len; i++) {
      const w = Math.random() * 2 - 1;
      if (brown) {
        last = (last + 0.02 * w) / 1.02;
        data[i] = last * 3.5;
      } else {
        data[i] = w;
      }
    }
    return buf;
  }

  // ---------------------------------------------------------- control
  toggleMuted() {
    this.muted = !this.muted;
    if (this.ready) {
      const t = this._ctx.currentTime;
      this._master.gain.setTargetAtTime(this.muted ? 0 : 0.55, t, 0.08);
    }
    return this.muted;
  }

  resume() {
    if (this._ctx && this._ctx.state === 'suspended') this._ctx.resume();
  }

  /** Crossfade the ambient bed to a phase's soundscape. */
  setAmbience(name) {
    this._ambience = name;
    if (!this.ready) return;
    const t = this._ctx.currentTime;
    const profile = AMBIENCE_PROFILES[name] ?? {};
    for (const [key, layerBase] of Object.entries(this._layers)) {
      const target = (profile[key] ?? 0) * layerBase.base;
      layerBase.gain.gain.setTargetAtTime(target, t, 1.2);
    }
    // pad chord
    this._setPad(profile.chord ?? []);
    if (this._padGain) {
      this._padGain.gain.setTargetAtTime((profile.pad ?? 0) * 0.075, t, 1.6);
    }
  }

  _setPad(freqs) {
    const ctx = this._ctx;
    if (this._pad) {
      for (const o of this._pad.oscs) {
        try { o.stop(ctx.currentTime + 0.5); } catch { /* already stopped */ }
      }
      this._pad = null;
    }
    if (!freqs.length) return;
    const oscs = [];
    for (const f of freqs) {
      for (const detune of [-4, 4]) {
        const o = ctx.createOscillator();
        o.type = 'triangle';
        o.frequency.value = f;
        o.detune.value = detune;
        o.connect(this._padGain);
        o.start();
        oscs.push(o);
      }
    }
    this._pad = { oscs };
  }

  // ------------------------------------------------- per-frame events
  update(dt) {
    if (!this.ready || this.muted) return;
    const layers = this._layers;
    const profile = AMBIENCE_PROFILES[this._ambience] ?? {};

    // fire crackle: sparse pops
    if ((profile.fire ?? 0) > 0) {
      this._timers.crackle -= dt;
      if (this._timers.crackle <= 0) {
        this._timers.crackle = 0.06 + Math.random() * 0.5;
        this._pop(800 + Math.random() * 1800, 0.02 + Math.random() * 0.04);
      }
    }
    // birdsong by day
    if ((profile.birds ?? 0) > 0) {
      this._timers.bird -= dt;
      if (this._timers.bird <= 0) {
        this._timers.bird = 1.6 + Math.random() * 5.5;
        this._chirp(2200 + Math.random() * 1400);
      }
    }
    // crickets at night
    if ((profile.crickets ?? 0) > 0) {
      this._timers.cricket -= dt;
      if (this._timers.cricket <= 0) {
        this._timers.cricket = 0.5 + Math.random() * 2.2;
        this._trill();
      }
    }
  }

  // -------------------------------------------------------------- SFX
  step(running = false) {
    if (!this.ready || this.muted) return;
    const ctx = this._ctx;
    const t = ctx.currentTime;
    const src = ctx.createBufferSource();
    src.buffer = this._noise;
    src.playbackRate.value = 0.7 + Math.random() * 0.5;
    const filter = ctx.createBiquadFilter();
    filter.type = 'lowpass';
    filter.frequency.value = running ? 500 : 780;
    const g = ctx.createGain();
    g.gain.setValueAtTime(running ? 0.16 : 0.09, t);
    g.gain.exponentialRampToValueAtTime(0.001, t + (running ? 0.16 : 0.11));
    src.connect(filter).connect(g).connect(this._master);
    src.start(t, Math.random());
    src.stop(t + 0.2);
  }

  ui(kind = 'advance') {
    if (!this.ready || this.muted) return;
    const notes = {
      advance: [[660, 0], [880, 0.05]],
      interact: [[523, 0], [784, 0.07]],
      alert: [[220, 0], [233, 0.05]],
      deny: [[311, 0], [233, 0.09]],
    }[kind] ?? [[660, 0]];
    const ctx = this._ctx;
    for (const [freq, delay] of notes) {
      const t = ctx.currentTime + delay;
      const o = ctx.createOscillator();
      o.type = kind === 'alert' ? 'sawtooth' : 'sine';
      o.frequency.value = freq;
      const g = ctx.createGain();
      g.gain.setValueAtTime(0, t);
      g.gain.linearRampToValueAtTime(kind === 'alert' ? 0.10 : 0.07, t + 0.01);
      g.gain.exponentialRampToValueAtTime(0.001, t + (kind === 'alert' ? 0.28 : 0.12));
      o.connect(g).connect(this._master);
      o.start(t);
      o.stop(t + 0.35);
    }
  }

  /** Low war drums under the battle cards (runs ~9 s). */
  battle() {
    if (!this.ready || this.muted) return;
    const ctx = this._ctx;
    for (let i = 0; i < 10; i++) {
      const t = ctx.currentTime + i * 0.82;
      const o = ctx.createOscillator();
      o.type = 'sine';
      o.frequency.setValueAtTime(92, t);
      o.frequency.exponentialRampToValueAtTime(46, t + 0.22);
      const g = ctx.createGain();
      g.gain.setValueAtTime(0.34, t);
      g.gain.exponentialRampToValueAtTime(0.001, t + 0.42);
      o.connect(g).connect(this._master);
      o.start(t);
      o.stop(t + 0.5);
      if (i % 2 === 1) this._pop(1200, 0.05, t);
    }
  }

  grief() {
    if (!this.ready || this.muted) return;
    const ctx = this._ctx;
    for (const f of [110, 130.8, 164.8]) {
      const t = ctx.currentTime;
      const o = ctx.createOscillator();
      o.type = 'sine';
      o.frequency.value = f;
      const g = ctx.createGain();
      g.gain.setValueAtTime(0, t);
      g.gain.linearRampToValueAtTime(0.06, t + 1.2);
      g.gain.exponentialRampToValueAtTime(0.001, t + 5.5);
      o.connect(g).connect(this._master);
      o.start(t);
      o.stop(t + 6);
    }
  }

  victory() {
    if (!this.ready || this.muted) return;
    const ctx = this._ctx;
    const chord = [261.6, 329.6, 392, 523.2];
    chord.forEach((f, i) => {
      const t = ctx.currentTime + i * 0.16;
      const o = ctx.createOscillator();
      o.type = 'triangle';
      o.frequency.value = f;
      const g = ctx.createGain();
      g.gain.setValueAtTime(0, t);
      g.gain.linearRampToValueAtTime(0.09, t + 0.06);
      g.gain.exponentialRampToValueAtTime(0.001, t + 2.2);
      o.connect(g).connect(this._master);
      o.start(t);
      o.stop(t + 2.4);
    });
  }

  // ------------------------------------------------------ tiny helpers
  _pop(freq, amp = 0.03, when = null) {
    const ctx = this._ctx;
    const t = when ?? ctx.currentTime;
    const src = ctx.createBufferSource();
    src.buffer = this._noise;
    src.playbackRate.value = 1.4;
    const filter = ctx.createBiquadFilter();
    filter.type = 'highpass';
    filter.frequency.value = freq;
    const g = ctx.createGain();
    g.gain.setValueAtTime(amp, t);
    g.gain.exponentialRampToValueAtTime(0.001, t + 0.07);
    src.connect(filter).connect(g).connect(this._master);
    src.start(t, Math.random());
    src.stop(t + 0.1);
  }

  _chirp(freq) {
    const ctx = this._ctx;
    for (let i = 0; i < 3; i++) {
      const t = ctx.currentTime + i * 0.14;
      const o = ctx.createOscillator();
      o.type = 'sine';
      o.frequency.setValueAtTime(freq * (1 + i * 0.06), t);
      o.frequency.exponentialRampToValueAtTime(freq * 1.18, t + 0.08);
      const g = ctx.createGain();
      g.gain.setValueAtTime(0, t);
      g.gain.linearRampToValueAtTime(0.035, t + 0.015);
      g.gain.exponentialRampToValueAtTime(0.001, t + 0.1);
      o.connect(g).connect(this._master);
      o.start(t);
      o.stop(t + 0.12);
    }
  }

  _trill() {
    const ctx = this._ctx;
    const t = ctx.currentTime;
    const o = ctx.createOscillator();
    o.type = 'square';
    o.frequency.value = 4200;
    const g = ctx.createGain();
    g.gain.setValueAtTime(0, t);
    g.gain.linearRampToValueAtTime(0.014, t + 0.01);
    g.gain.linearRampToValueAtTime(0, t + 0.09);
    o.connect(g).connect(this._master);
    o.start(t);
    o.stop(t + 0.1);
  }
}

/** Per-phase ambient mix (0..1 layer targets + pad chord in Hz). */
const AMBIENCE_PROFILES = {
  ziklag: { wind: 0.55, fire: 0.5, birds: 0.05, pad: 0.6, chord: [110, 155.6, 196] },
  desert: { wind: 0.9, water: 0.25, pad: 0.45, chord: [123.5, 164.8, 246.9] },
  egyptian: { wind: 0.5, birds: 0.35, pad: 0.5, chord: [130.8, 196, 261.6] },
  camp: { wind: 0.2, fire: 0.65, crickets: 0.4, pad: 0.55, chord: [87.3, 116.5, 138.6] },
  spoils: { wind: 0.4, birds: 0.3, murmur: 0.35, pad: 0.6, chord: [146.8, 185, 220] },
  gate: { birds: 0.4, murmur: 0.5, wind: 0.15, pad: 0.65, chord: [164.8, 207.7, 246.9] },
  gilboa: { wind: 0.65, rain: 0.55, pad: 0.7, chord: [82.4, 98, 116.5] },
  none: {},
};

export const audio = new AudioEngine();
