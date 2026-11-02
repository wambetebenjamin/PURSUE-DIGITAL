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
  pendingProduction, productionRate,
} from '../core/progression.js';
import {
  muster, COMPANIES, STEP, companyCap, trainCost, musterPower, totalWounded,
  orderOfBattle,
} from '../core/muster.js';
import { SKIRMISHES } from '../core/skirmish.js';
import { SkirmishBoard } from './SkirmishBoard.js';

const TABS = [
  { id: 'charge', label: 'The Charge' },
  { id: 'band', label: 'The Band' },
  { id: 'ziklag', label: 'Ziklag' },
  { id: 'muster', label: 'The Muster' },
  { id: 'field', label: 'The Field' },
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
    this.board = null; // the skirmish overlay, built on first use
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
    muster.onChange(() => this.render());
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
    if (this.powerLine) {
      const mp = musterPower(muster.get(), s.buildings.barracks);
      this.powerLine.textContent = `The band: ${bandPower(s)} strong · ${mp.men} under arms`;
    }
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
      else if (this.tab === 'muster') this._renderMuster(s);
      else if (this.tab === 'field') this._renderField(s);
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

    // --- the works: what the settlement has made while the band was away
    const pending = pendingProduction(s);
    const rate = productionRate(s);
    const works = el('div', 'war-row works');
    const worksMain = el('div', 'war-row-main');
    worksMain.appendChild(el('h4', '', 'The Works'));
    const rateLine = Object.entries(rate).map(([k, v]) => `${v} ${k}`).join(' · ') || 'nothing yet';
    worksMain.appendChild(el('p', 'war-row-note', `In an hour: ${rateLine}`));
    const waiting = Object.entries(pending.resources).map(([k, v]) => `${v} ${k}`).join(' · ');
    worksMain.appendChild(el('span', 'war-effect',
      waiting ? `waiting since last emptied — ${waiting}` : 'the stores are empty and the works begin again'));
    works.appendChild(worksMain);
    const worksSide = el('div', 'war-row-side');
    const gather = el('button', 'war-btn seal', 'Empty the works');
    gather.type = 'button';
    if (!waiting) gather.disabled = true;
    gather.addEventListener('click', () => {
      progression.collect();
      this.render();
      if (this.onChange) this.onChange();
    });
    worksSide.appendChild(gather);
    worksSide.appendChild(el('span', 'war-reward',
      `${pending.hours.toFixed(1)} hour${pending.hours === 1 ? '' : 's'} of work kept`));
    works.appendChild(worksSide);
    this.body.appendChild(works);

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

  /* -------------------------------------------------------------- muster */
  _renderMuster(s) {
    const barracks = s.buildings.barracks;
    const mp = musterPower(muster.get(), barracks);
    const intro = el('p', 'war-intro',
      `The six hundred go down after the Amalekites. ${mp.men} stand under arms; the ridge reads them as ${mp.power} strong.`
      + (totalWounded() ? ` ${totalWounded()} wounded wait to be brought back to the line.` : ''));
    this.body.appendChild(intro);

    if (totalWounded() > 0) {
      const healRow = el('div', 'war-row works');
      const main = el('div', 'war-row-main');
      main.appendChild(el('h4', '', 'Tend the wounded'));
      main.appendChild(el('p', 'war-row-note',
        `Provisions bring them back. The tent of the wounded is level ${s.buildings.hospital || 1}.`));
      healRow.appendChild(main);
      const side = el('div', 'war-row-side');
      const btn = el('button', 'war-btn seal', 'Tend them all');
      btn.type = 'button';
      btn.addEventListener('click', () => {
        muster.healAll({ hospital: s.buildings.hospital || 1, spend: (c) => progression.spend(c) });
        this.render();
        if (this.onChange) this.onChange();
      });
      side.appendChild(btn);
      healRow.appendChild(side);
      this.body.appendChild(healRow);
    }

    const list = el('div', 'war-list muster');
    for (const c of COMPANIES) {
      const row = muster.get().companies[c.id];
      const cap = companyCap(c.id, barracks);
      const line = el('div', 'war-row company');
      line.appendChild(el('span', 'war-sigil', c.glyph));

      const main = el('div', 'war-row-main');
      main.appendChild(el('h4', '', c.name));
      main.appendChild(el('span', 'war-role', c.line === 'front' ? 'front line' : 'back line'));
      main.appendChild(el('p', 'war-row-note', c.note));
      const pips = el('div', 'war-pips');
      for (let i = 0; i < 5; i++) pips.appendChild(el('span', `war-pip${i < row.tier ? ' on' : ''}`));
      main.appendChild(pips);
      line.appendChild(main);

      const side = el('div', 'war-row-side');
      side.appendChild(el('span', 'war-level', `${row.count} of ${cap} men`));
      side.appendChild(el('span', 'war-reward', `tier ${row.tier} of 5`));

      const stepCost = c.cost(1);
      const mustered = el('button', 'war-btn', `Muster ${STEP}`);
      mustered.type = 'button';
      if (row.count >= cap) mustered.disabled = true;
      mustered.addEventListener('click', () => {
        muster.recruit(c.id, 1, { barracksLevel: barracks, spend: (x) => progression.spend(x) });
        this.render();
        if (this.onChange) this.onChange();
      });
      side.appendChild(mustered);
      side.appendChild(el('span', 'war-reward', costLine(stepCost)));

      const cost = trainCost(c.id, muster.get());
      if (cost) {
        const drill = el('button', 'war-btn', 'Drill a tier');
        drill.type = 'button';
        if (row.tier >= Math.min(5, barracks + 1)) drill.disabled = true;
        drill.addEventListener('click', () => {
          muster.train(c.id, { barracksLevel: barracks, spend: (x) => progression.spend(x) });
          this.render();
          if (this.onChange) this.onChange();
        });
        side.appendChild(drill);
        side.appendChild(el('span', 'war-reward', costLine(cost)));
      } else {
        side.appendChild(el('span', 'war-stamp', 'veterans'));
      }

      if (row.wounded > 0) {
        const heal = el('button', 'war-btn seal', `Tend ${row.wounded}`);
        heal.type = 'button';
        heal.addEventListener('click', () => {
          muster.heal(c.id, row.wounded, { hospital: s.buildings.hospital || 1, spend: (x) => progression.spend(x) });
          this.render();
          if (this.onChange) this.onChange();
        });
        side.appendChild(heal);
      }

      line.appendChild(side);
      list.appendChild(line);
    }
    this.body.appendChild(list);

    const order = orderOfBattle(muster.get(), barracks);
    if (order.length) {
      const drawn = el('p', 'war-intro dim',
        `Order of battle: ${order.filter((g) => g.line === 'front').map((g) => `${g.name} (${g.count})`).join(', ') || 'no front line'} `
        + `forward; ${order.filter((g) => g.line === 'back').map((g) => `${g.name} (${g.count})`).join(', ') || 'nothing behind'}.`);
      this.body.appendChild(drawn);
    }
  }

  /* --------------------------------------------------------------- field */
  _renderField(s) {
    const barracks = s.buildings.barracks;
    const mp = musterPower(muster.get(), barracks);
    const lede = el('p', 'war-intro',
      mp.men
        ? 'The band takes the field. The companies fight on their own; your hand is on the mighty men and their skills.'
        : 'No one stands under arms. Muster the companies before you take the field.');
    this.body.appendChild(lede);

    const list = el('div', 'war-list field');
    for (const stage of SKIRMISHES) {
      const won = muster.get().wins[stage.id] || 0;
      const row = el('div', `war-row skirmish${won ? ' done' : ''}`);

      const main = el('div', 'war-row-main');
      main.appendChild(el('h4', '', stage.name));
      main.appendChild(el('p', 'war-row-note', stage.brief));
      main.appendChild(el('span', 'war-cite', stage.where));
      row.appendChild(main);

      const side = el('div', 'war-row-side');
      side.appendChild(el('span', 'war-level',
        mp.power >= stage.power ? `${stage.power} strong — you match it` : `${stage.power} strong — you do not`));
      side.appendChild(el('span', 'war-reward', costLine(stage.rewards)));
      const btn = el('button', 'war-btn seal', mp.men ? 'Take the field' : 'No men');
      btn.type = 'button';
      if (!mp.men) btn.disabled = true;
      btn.addEventListener('click', () => {
        if (!this.board) {
          this.board = new SkirmishBoard({ onClose: () => this.render() });
        }
        this.board.open(stage);
      });
      side.appendChild(btn);
      if (won) side.appendChild(el('span', 'war-stamp', `held ×${won}`));
      row.appendChild(side);
      list.appendChild(row);
    }
    this.body.appendChild(list);

    const ledger = el('p', 'war-intro dim',
      `${muster.get().battles} fights fought · ${muster.get().lost} lost.`);
    this.body.appendChild(ledger);
  }
}
