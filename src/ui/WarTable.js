/**
 * WarTable.js — the campaign screen: the long arc, as a written page.
 *
 * Four leaves, in the order a governor would consult them:
 *
 *   The Charge   the task chain (claim rewards; the next thing to do)
 *   The Band     David's mighty men — power, role, drill
 *   Ziklag       the settlement he rebuilds — four buildings, five levels each
 *   The Campaign the seven phases and the stars remembered in each
 *
 * Built entirely from the progression store, so every number on the page is the
 * number the game rules use. Vellum, bronze and wax — the same material
 * language as the pause codex, the satchel and the settings leaf.
 */
import {
  progression, RESOURCES, BUILDINGS, ROSTER, TASKS, PHASE_ORDER, PHASE_NAMES,
  bandPower, isUnlocked, upgradeCost, drillCost, currentTask, totalStars,
} from '../core/progression.js';

const TABS = [
  { id: 'charge', label: 'The Charge' },
  { id: 'band', label: 'The Band' },
  { id: 'ziklag', label: 'Ziklag' },
  { id: 'campaign', label: 'The Campaign' },
];

function el(tag, className, text) {
  const node = document.createElement(tag);
  if (className && node.classList) node.classList.add(...className.split(' ').filter(Boolean));
  if (text !== undefined && text !== null) node.textContent = String(text);
  return node;
}

function costLine(cost) {
  if (!cost) return 'at its height';
  return Object.entries(cost)
    .map(([k, v]) => `${v} ${k}`)
    .join(' · ');
}

export class WarTable {
  /** @param {{host?: HTMLElement, onChange?: Function}} [opts] */
  constructor(opts = {}) {
    this.host = opts.host || document.getElementById('war-table');
    this.onChange = opts.onChange || null;
    this.tab = 'charge';
    this.built = false;
    this.tabsBar = null;
    this.body = null;
    this.strip = null;
    this.root = null;
  }

  build() {
    if (this.built || !this.host) return;
    this.built = true;
    if (this.host.replaceChildren) this.host.replaceChildren();

    // ---- header: title, band power, stars
    const head = el('div', 'war-head');
    this.powerLine = el('span', 'war-power', 'The band: 0 strong');
    this.starLine = el('span', 'war-stars', '✦ 0 of 21');
    head.appendChild(this.powerLine);
    head.appendChild(this.starLine);
    this.host.appendChild(head);

    // ---- resources
    this.strip = el('div', 'war-resources');
    this.host.appendChild(this.strip);

    // ---- tabs
    this.tabsBar = el('div', 'war-tabs');
    this.tabButtons = [];
    for (const t of TABS) {
      const btn = el('button', 'war-tab', t.label);
      btn.type = 'button';
      btn.addEventListener('click', () => this.show(t.id));
      this.tabsBar.appendChild(btn);
      this.tabButtons.push({ id: t.id, node: btn });
    }
    this.host.appendChild(this.tabsBar);

    // ---- body
    this.body = el('div', 'war-body');
    this.host.appendChild(this.body);

    // ---- footer ledger
    this.ledger = el('p', 'war-ledger', '');
    this.host.appendChild(this.ledger);

    progression.onChange(() => this.render());
    this.render();
  }

  show(tab) {
    this.tab = tab;
    this.render();
  }

  /** Open the page on a given leaf (default: wherever it was). */
  open(tab) {
    this.build();
    if (tab) this.tab = tab;
    this.render();
  }

  render() {
    if (!this.built) return;
    const s = progression.get();

    // header numbers
    if (this.powerLine) this.powerLine.textContent = `The band: ${bandPower(s)} strong`;
    if (this.starLine) this.starLine.textContent = `✦ ${totalStars(s)} of ${PHASE_ORDER.length * 3}`;

    // resources
    if (this.strip) {
      if (this.strip.replaceChildren) this.strip.replaceChildren();
      for (const r of RESOURCES) {
        const cell = el('div', 'war-res');
        cell.appendChild(el('span', 'war-res-glyph', r.glyph));
        cell.appendChild(el('span', 'war-res-amount', s.resources[r.id] || 0));
        cell.appendChild(el('span', 'war-res-name', r.name));
        this.strip.appendChild(cell);
      }
    }

    // tabs active state
    if (this.tabButtons) {
      for (const t of this.tabButtons) {
        if (t.node.classList) t.node.classList.toggle('active', t.id === this.tab);
      }
    }

    if (this.body) {
      if (this.body.replaceChildren) this.body.replaceChildren();
      if (this.tab === 'charge') this._renderCharge(s);
      else if (this.tab === 'band') this._renderBand(s);
      else if (this.tab === 'ziklag') this._renderZiklag(s);
      else this._renderCampaign(s);
    }

    if (this.ledger) {
      const next = currentTask(s);
      this.ledger.textContent = next
        ? `Next in the charge: ${next.title}.`
        : 'The charge is kept. What was taken has been recovered.';
    }
  }

  /* ------------------------------------------------------------- charge */
  _renderCharge(s) {
    const now = currentTask(s);
    const list = el('div', 'war-list');
    for (const t of TASKS) {
      const done = s.tasksDone.includes(t.id);
      const claimed = s.tasksClaimed.includes(t.id);
      const row = el('div', `war-row task${done ? ' done' : ''}${claimed ? ' claimed' : ''}`);

      const left = el('div', 'war-row-main');
      left.appendChild(el('h4', '', t.title));
      left.appendChild(el('p', 'war-row-note', t.detail));
      left.appendChild(el('span', 'war-cite', t.verse));
      if (now && now.id === t.id && !done) left.appendChild(el('span', 'war-now', 'current'));
      row.appendChild(left);

      const right = el('div', 'war-row-side');
      if (claimed) {
        right.appendChild(el('span', 'war-stamp', 'kept'));
      } else if (done) {
        const btn = el('button', 'war-btn seal', 'Claim');
        btn.type = 'button';

        btn.addEventListener('click', () => {
          progression.claimTask(t.id);
          this.render();
          if (this.onChange) this.onChange();
        });
        right.appendChild(btn);
        right.appendChild(el('span', 'war-reward', costLine(t.reward)));
      } else {
        right.appendChild(el('span', 'war-pending', 'unfulfilled'));
        right.appendChild(el('span', 'war-reward', costLine(t.reward)));
      }
      row.appendChild(right);
      list.appendChild(row);
    }
    this.body.appendChild(list);
  }

  /* --------------------------------------------------------------- band */
  _renderBand(s) {
    const list = el('div', 'war-list band');
    for (const m of ROSTER) {
      const unlocked = isUnlocked(m.id, s);
      const r = s.roster[m.id] || { level: 1 };
      const row = el('div', `war-row man role-${m.role}${unlocked ? '' : ' locked'}`);

      const sign = el('div', 'war-sigil', m.name.slice(0, 1));
      row.appendChild(sign);

      const main = el('div', 'war-row-main');
      main.appendChild(el('h4', '', `${m.name} · ${m.title}`));
      main.appendChild(el('span', 'war-role', m.role === 'blade' ? 'blade' : m.role === 'bow' ? 'bow' : 'spear'));
      main.appendChild(el('p', 'war-row-note', m.verse));
      row.appendChild(main);

      const side = el('div', 'war-row-side');
      if (unlocked) {
        side.appendChild(el('span', 'war-level', `drill ${r.level}`));
        side.appendChild(el('span', 'war-reward', `power ${Math.round(m.power * (1 + (r.level - 1) * 0.18))}`));
        const btn = el('button', 'war-btn', 'Drill');
        btn.type = 'button';

        if (!drillCost(m.id, s)) btn.disabled = true;
        btn.addEventListener('click', () => {
          progression.drill(m.id);
          this.render();
          if (this.onChange) this.onChange();
        });
        side.appendChild(btn);
      } else {
        side.appendChild(el('span', 'war-pending', `joins at ${m.unlockAt} stars`));
      }
      row.appendChild(side);
      list.appendChild(row);
    }
    this.body.appendChild(list);
  }

  /* ------------------------------------------------------------- ziklag */
  _renderZiklag(s) {
    const intro = el('p', 'war-intro',
      'Ziklag was burned with fire. What is rebuilt there is not decoration: every level is a margin on the march.');
    this.body.appendChild(intro);

    const list = el('div', 'war-list build');
    for (const b of BUILDINGS) {
      const lv = s.buildings[b.id] || 1;
      const cost = upgradeCost(b.id, s);
      const row = el('div', 'war-row building');

      const main = el('div', 'war-row-main');
      main.appendChild(el('h4', '', b.name));
      main.appendChild(el('p', 'war-row-note', b.note));
      main.appendChild(el('span', 'war-effect', b.effect(lv)));
      row.appendChild(main);

      const side = el('div', 'war-row-side');
      side.appendChild(el('span', 'war-level', `level ${lv} of ${b.maxLevel}`));
      if (cost) {
        const btn = el('button', 'war-btn', 'Raise');
        btn.type = 'button';

        btn.addEventListener('click', () => {
          progression.upgrade(b.id);
          this.render();
          if (this.onChange) this.onChange();
        });
        side.appendChild(btn);
        side.appendChild(el('span', 'war-reward', costLine(cost)));
      } else {
        side.appendChild(el('span', 'war-stamp', 'at its height'));
      }
      row.appendChild(side);

      const pips = el('div', 'war-pips');
      for (let i = 0; i < b.maxLevel; i++) {
        pips.appendChild(el('span', `war-pip${i < lv ? ' on' : ''}`));
      }
      row.appendChild(pips);

      list.appendChild(row);
    }
    this.body.appendChild(list);
  }

  /* ----------------------------------------------------------- campaign */
  _renderCampaign(s) {
    const list = el('div', 'war-list campaign');
    PHASE_ORDER.forEach((phase, i) => {
      const stars = s.stars[phase] || 0;
      const row = el('div', `war-row phase${stars ? ' remembered' : ''}`);
      row.appendChild(el('span', 'war-index', i + 1));

      const main = el('div', 'war-row-main');
      main.appendChild(el('h4', '', PHASE_NAMES[phase]));
      main.appendChild(el('p', 'war-row-note',
        stars === 3 ? 'Swift, silent and unseen.'
          : stars === 2 ? 'Swift and quiet.'
            : stars === 1 ? 'Endured.'
              : 'Not yet walked.'));
      row.appendChild(main);

      const side = el('div', 'war-row-side');
      const pips = el('div', 'war-pips inline');
      for (let k = 0; k < 3; k++) pips.appendChild(el('span', `war-pip${k < stars ? ' on' : ''}`));
      side.appendChild(pips);
      row.appendChild(side);

      list.appendChild(row);
    });
    this.body.appendChild(list);
  }
}
