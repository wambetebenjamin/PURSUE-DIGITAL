/**
 * SkirmishBoard.js — the field, drawn as the war table draws everything:
 * vellum, ink, bronze, wax. No sprites, no health bars with gradient candy.
 *
 * The board is a real-time view of `Skirmish` from core/skirmish.js. It owns a
 * requestAnimationFrame loop only while it is open, ticks the engine, and lets
 * the player fire hero skills by hand — the reference game's dungeon stage,
 * built in our register.
 */
import { Skirmish, SKIRMISHES } from '../core/skirmish.js';
import { muster, totalWounded } from '../core/muster.js';
import { progression, modifiers } from '../core/progression.js';
import { audio } from '../audio/AudioEngine.js';

function el(tag, className, text) {
  const node = document.createElement(tag);
  if (className && node.classList) node.classList.add(...className.split(' ').filter(Boolean));
  if (text !== undefined && text !== null) node.textContent = String(text);
  return node;
}

export class SkirmishBoard {
  /** @param {{onClose?: Function}} [opts] */
  constructor(opts = {}) {
    this.onClose = opts.onClose || null;
    this.host = document.getElementById('skirmish-screen');
    this.built = false;
    this.open_ = false;
    this.bout = null;
    this.stage = null;
    this._raf = 0;
    this._last = 0;
    this._ended = false;
    this._nodes = {};
  }

  /* ---------------------------------------------------------------- build */

  build() {
    if (this.built || !this.host) return;
    this.built = true;
    const host = this.host;
    if (host.replaceChildren) host.replaceChildren();

    const card = el('div', 'skirmish-card');
    host.appendChild(card);
    this.card = card;

    // --- head
    const head = el('div', 'skirmish-head');
    this.title = el('h2', '', 'The Field');
    this.where = el('p', 'skirmish-where', '');
    const right = el('div', 'skirmish-clock');
    this.clock = el('span', 'skirmish-time', '0:00');
    this.speedBtn = el('button', 'war-btn skirmish-speed', '×1');
    this.speedBtn.type = 'button';
    this.speedBtn.addEventListener('click', () => {
      if (!this.bout) return;
      this.bout.speed = this.bout.speed === 1 ? 2 : this.bout.speed === 2 ? 0.5 : 1;
      this.speedBtn.textContent = `×${this.bout.speed}`;
    });
    right.appendChild(this.clock);
    right.appendChild(this.speedBtn);
    head.appendChild(this.title);
    head.appendChild(this.where);
    head.appendChild(right);
    card.appendChild(head);

    // --- the two lines
    const field = el('div', 'skirmish-field');
    const ours = el('div', 'skirmish-side ours');
    ours.appendChild(el('h3', '', 'The band'));
    this.ourList = el('div', 'skirmish-line');
    ours.appendChild(this.ourList);
    const theirs = el('div', 'skirmish-side theirs');
    theirs.appendChild(el('h3', '', 'The Amalekites'));
    this.theirList = el('div', 'skirmish-line');
    theirs.appendChild(this.theirList);
    field.appendChild(ours);
    field.appendChild(theirs);
    card.appendChild(field);

    // --- hero gauges
    const heroes = el('div', 'skirmish-heroes');
    this.heroList = heroes;
    card.appendChild(heroes);

    // --- the log
    this.logList = el('ol', 'skirmish-log');
    card.appendChild(this.logList);

    // --- the result
    this.result = el('div', 'skirmish-result hidden');
    card.appendChild(this.result);

    // --- the way out
    const foot = el('div', 'skirmish-foot');
    this.retreatBtn = el('button', 'war-btn', 'Withdraw');
    this.retreatBtn.type = 'button';
    this.retreatBtn.addEventListener('click', () => this.withdraw());
    foot.appendChild(this.retreatBtn);
    card.appendChild(foot);
  }

  /* ----------------------------------------------------------------- open */

  /** @param {object} stage one of SKIRMISHES */
  open(stage) {
    this.build();
    if (!this.host) return false;
    this.stage = stage;
    this._ended = false;
    this.bout = new Skirmish(stage, { barracks: progression.get().buildings.barracks });
    this.title.textContent = stage.name;
    this.where.textContent = stage.where;
    this.speedBtn.textContent = '×1';
    if (this.result) {
      this.result.classList.add('hidden');
      if (this.result.replaceChildren) this.result.replaceChildren();
    }
    this._renderHeroes();
    this._renderField();
    this._renderLog();
    this.host.classList.remove('hidden');
    this.open_ = true;
    this._last = 0;
    this._raf = typeof requestAnimationFrame === 'function'
      ? requestAnimationFrame((t) => this._frame(t)) : 0;
    return true;
  }

  get isOpen() {
    return this.open_;
  }

  close() {
    this.open_ = false;
    this.bout = null;
    if (this._raf && typeof cancelAnimationFrame === 'function') cancelAnimationFrame(this._raf);
    this._raf = 0;
    if (this.host) this.host.classList.add('hidden');
    if (this.onClose) this.onClose();
  }

  /** Break off the fight: the enemy holds the field, the band comes away. */
  withdraw() {
    if (this.bout && this.bout.status === 'fighting') {
      this.bout.status = 'lost';
      this.bout.push('The horn sounds the withdrawal.');
      this._settle();
    } else {
      this.close();
    }
  }

  /* ---------------------------------------------------------------- frame */

  _frame(t) {
    if (!this.open_ || !this.bout) return;
    const dt = this._last ? Math.min(0.12, (t - this._last) / 1000) : 0;
    this._last = t;
    const before = this.bout.status;
    this.bout.tick(dt);
    this._renderClock();
    this._renderField();
    this._renderHeroes();
    this._renderLog();
    if (before === 'fighting' && this.bout.status !== 'fighting') this._settle();
    this._raf = typeof requestAnimationFrame === 'function'
      ? requestAnimationFrame((n) => this._frame(n)) : 0;
  }

  _renderClock() {
    if (!this.clock || !this.bout) return;
    const s = Math.floor(this.bout.time);
    this.clock.textContent = `${Math.floor(s / 60)}:${String(s % 60).padStart(2, '0')}`;
  }

  _renderField() {
    if (!this.bout) return;
    const draw = (list, groups) => {
      if (!list) return;
      if (list.replaceChildren) list.replaceChildren();
      if (!groups.length) {
        list.appendChild(el('p', 'skirmish-empty', '— none left standing —'));
        return;
      }
      for (const g of groups) {
        const row = el('div', `skirmish-unit${g.hp <= 0 ? ' down' : ''}${g.line === 'front' ? ' front' : ''}`);
        row.appendChild(el('span', 'skirmish-glyph', g.glyph || '·'));
        const main = el('div', 'skirmish-unit-main');
        main.appendChild(el('span', 'skirmish-unit-name', g.name));
        const track = el('div', 'skirmish-track');
        const fill = el('div', 'skirmish-fill');
        const ratio = Math.max(0, Math.min(1, g.hp / g.maxHp));
        fill.style.width = `${(ratio * 100).toFixed(1)}%`;
        track.appendChild(fill);
        main.appendChild(track);
        row.appendChild(main);
        const men = g.hp > 0 ? Math.max(0, Math.round(g.hp / (g.hpPerMan || 1))) : 0;
        row.appendChild(el('span', 'skirmish-men', g.men !== undefined ? `${men}/${g.men}` : ''));
        list.appendChild(row);
      }
    };
    draw(this.ourList, this.bout.line);
    draw(this.theirList, this.bout.enemy);
  }

  _renderHeroes() {
    if (!this.heroList || !this.bout) return;
    if (this.heroList.replaceChildren) this.heroList.replaceChildren();
    for (const h of this.bout.heroes) {
      const btn = el('button', `skirmish-skill${h.ready ? ' ready' : ''}`);
      btn.type = 'button';
      btn.style.setProperty('--gauge', `${Math.round(h.gauge)}%`);
      btn.appendChild(el('span', 'skirmish-skill-name', h.name));
      btn.appendChild(el('span', 'skirmish-skill-move', h.skill.name));
      btn.addEventListener('click', () => {
        if (!this.bout) return;
        if (this.bout.useSkill(h.id)) {
          audio.ui?.('interact');
          this._renderHeroes();
          this._renderField();
          this._renderLog();
        }
      });
      this.heroList.appendChild(btn);
    }
  }

  _renderLog() {
    if (!this.logList || !this.bout) return;
    if (this.logList.replaceChildren) this.logList.replaceChildren();
    const rows = this.bout.log.slice(-6);
    for (const r of rows) {
      const li = el('li', '', r.line);
      this.logList.appendChild(li);
    }
  }

  /* --------------------------------------------------------------- settle */

  _settle() {
    if (this._ended || !this.bout) return;
    this._ended = true;
    const bout = this.bout;
    const won = bout.status === 'won';
    const stage = this.stage;

    if (won) {
      progression.grant(stage.rewards);
      progression.addRenown(stage.renown || 0);
      muster.recordWin(stage.id);
      audio.ui?.('alert');
    } else {
      muster.recordLoss();
    }
    muster.applyLosses(bout.losses());

    if (!this.result) return;
    if (this.result.replaceChildren) this.result.replaceChildren();
    const c = bout.casualties;
    this.result.appendChild(el('span', 'skirmish-outcome', won ? 'The field is ours' : 'The field is theirs'));
    const loot = Object.entries(won ? stage.rewards : {})
      .map(([k, v]) => `${v} ${k}`)
      .join(' · ');
    this.result.appendChild(el('p', 'skirmish-tally',
      `${c.standing} still standing · ${c.fallen} fallen · ${won ? loot || 'nothing taken' : 'nothing taken'}`));
    if (totalWounded() > 0) {
      this.result.appendChild(el('p', 'skirmish-tally dim',
        `${totalWounded()} wounded wait at the tent — tend them at the War Table.`));
    }
    const btn = el('button', 'war-btn seal', won ? 'Gather the spoil' : 'Carry them back');
    btn.type = 'button';
    btn.addEventListener('click', () => this.close());
    this.result.appendChild(btn);
    this.result.classList.remove('hidden');
    if (this.retreatBtn) this.retreatBtn.textContent = 'Close';
  }
}

export { SKIRMISHES };
