/**
 * Citadel.js — the strategy front end: the city you are rebuilding, from above.
 *
 * This is where the game opens now. The pursuit (the walking game) is one
 * button away; the rest of the time you are governing: raising works, mustering
 * companies, sending the band at a stage, reading the chart.
 *
 * It owns three things and nothing else:
 *   • the isometric city scene (world/city/CitadelScene.js) and its camera rig
 *   • the strategy HUD — resources, the dock, the selected-work card
 *   • the sheets that hang off the dock (the War Table's leaves, the chart)
 *
 * Every number it shows comes from the progression/muster stores, so the city
 * on the screen is the city the rules are using.
 */
import * as THREE from 'three';
import { CitadelScene, SITES } from '../world/city/CitadelScene.js';
import { progression, BUILDINGS, upgradeCost, currentTask, TASKS, pendingProduction } from '../core/progression.js';
import { muster, musterPower } from '../core/muster.js';
import { SKIRMISHES } from '../core/skirmish.js';
import { WarTable } from './WarTable.js';
import { SkirmishBoard } from './SkirmishBoard.js';
import { audio } from '../audio/AudioEngine.js';

function el(tag, className, text) {
  const node = document.createElement(tag);
  if (className && node.classList) node.classList.add(...className.split(' ').filter(Boolean));
  if (text !== undefined && text !== null) node.textContent = String(text);
  return node;
}

function costLine(cost) {
  if (!cost) return 'at its height';
  return Object.entries(cost).map(([k, v]) => `${v} ${k}`).join(' · ');
}

const DOCK = [
  { id: 'citadel', label: 'The Citadel', note: 'the works' },
  { id: 'charge', label: 'The Charge', note: 'what is owed' },
  { id: 'band', label: 'The Band', note: 'the mighty men' },
  { id: 'muster', label: 'The Host', note: 'the companies' },
  { id: 'field', label: 'The Field', note: 'stages' },
  { id: 'chart', label: 'The Chart', note: 'the road south' },
];

export class Citadel {
  /**
   * @param {{scene: THREE.Scene, camera: THREE.PerspectiveCamera,
   *          canvas: HTMLCanvasElement, onMarch: Function}} opts
   */
  constructor(opts) {
    this.scene3 = opts.scene;
    this.camera = opts.camera;
    this.canvas = opts.canvas;
    this.onMarch = opts.onMarch || (() => {});
    this.input = opts.input || null;
    this.active = false;
    this.host = document.getElementById('citadel-ui');
    this.built = false;
    this.selected = null;
    this.sheetId = null;
    this._drag = null;
    this._ray = new THREE.Raycaster();
    this._ndc = new THREE.Vector2();

    const levels = progression.get().buildings;
    this.city = new CitadelScene(levels);
    this.city._wallLevel = levels.wall || 1;

    this.board = null; // the field board, built on first fight
    this._onPointerDown = this._onPointerDown.bind(this);
    this._onPointerMove = this._onPointerMove.bind(this);
    this._onPointerUp = this._onPointerUp.bind(this);
    this._onWheel = this._onWheel.bind(this);
  }

  /* ---------------------------------------------------------------- build */

  build() {
    if (this.built || !this.host) return;
    this.built = true;
    const host = this.host;
    if (host.replaceChildren) host.replaceChildren();

    // ---------------- top bar
    const top = el('div', 'cit-top');

    const lord = el('div', 'cit-lord');
    lord.appendChild(el('span', 'cit-lord-sigil', 'ד'));
    const lordText = el('div', 'cit-lord-text');
    lordText.appendChild(el('span', 'cit-lord-name', 'David, son of Jesse'));
    this.lordSub = el('span', 'cit-lord-sub', 'Ziklag, rebuilt from ash');
    lordText.appendChild(this.lordSub);
    lord.appendChild(lordText);
    top.appendChild(lord);

    this.resBar = el('div', 'cit-res');
    top.appendChild(this.resBar);

    const actions = el('div', 'cit-actions');
    this.collectBtn = el('button', 'cit-btn seal', 'Empty the works');
    this.collectBtn.type = 'button';
    this.collectBtn.addEventListener('click', () => {
      progression.collect();
      audio.ui?.('interact');
      this.refresh();
    });
    const march = el('button', 'cit-btn march', 'Take the road');
    march.type = 'button';
    march.addEventListener('click', () => {
      audio.ui?.('interact');
      this.onMarch();
    });
    actions.appendChild(this.collectBtn);
    actions.appendChild(march);
    top.appendChild(actions);
    host.appendChild(top);

    // ---------------- dock
    const dock = el('nav', 'cit-dock');
    this.dockButtons = [];
    for (const item of DOCK) {
      const btn = el('button', 'cit-dock-btn', '');
      btn.type = 'button';
      btn.appendChild(el('span', 'cit-dock-label', item.label));
      btn.appendChild(el('span', 'cit-dock-note', item.note));
      btn.addEventListener('click', () => this.openDock(item.id));
      dock.appendChild(btn);
      this.dockButtons.push({ id: item.id, node: btn });
    }
    host.appendChild(dock);

    // ---------------- the selected work
    this.siteCard = el('section', 'cit-site hidden');
    host.appendChild(this.siteCard);

    // ---------------- hint
    this.hint = el('p', 'cit-hint', 'Drag to turn the city · scroll to draw nearer · tap a work');
    host.appendChild(this.hint);

    // ---------------- the sheet
    this.sheet = el('div', 'cit-sheet hidden');
    const sheetInner = el('div', 'cit-sheet-card');
    this.sheetTitle = el('h2', '', '');
    this.sheetNote = el('p', 'cit-sheet-note', '');
    this.sheetBody = el('div', 'cit-sheet-body');
    const close = el('button', 'cit-btn', 'Close');
    close.type = 'button';
    close.addEventListener('click', () => this.closeSheet());
    this.sheetBack = el('button', 'cit-btn', 'Back to the city');
    this.sheetBack.type = 'button';
    this.sheetBack.addEventListener('click', () => this.closeSheet());
    sheetInner.appendChild(this.sheetTitle);
    sheetInner.appendChild(this.sheetNote);
    sheetInner.appendChild(this.sheetBody);
    const sheetFoot = el('div', 'cit-sheet-foot');
    sheetFoot.appendChild(this.sheetBack);
    sheetFoot.appendChild(close);
    sheetInner.appendChild(sheetFoot);
    this.sheet.appendChild(sheetInner);
    host.appendChild(this.sheet);

    // the war table lives inside the sheet, one instance, six leaves
    this.war = new WarTable({ host: el('div', 'cit-war'), onChange: () => this.refresh() });
    // WarTable caches its host at construction; give it a real parent
    this.sheetBody.appendChild(this.war.host);

    this.chartCanvas = null;
    progression.onChange(() => this.refresh());
    muster.onChange(() => this.refresh());
    this.refresh();
  }

  /* ----------------------------------------------------------------- open */

  enter() {
    this.build();
    this.active = true;
    this.scene3.add(this.city.group);
    this.city.sync(progression.get().buildings);
    if (this.host) this.host.classList.remove('hidden');
    document.body.classList.add('citadel-mode');
    // governing is not walking: the canvas must not grab the pointer lock
    if (this.input) this.input.enabled = false;
    this.canvas.addEventListener('pointerdown', this._onPointerDown);
    this.canvas.addEventListener('pointermove', this._onPointerMove);
    window.addEventListener('pointerup', this._onPointerUp);
    this.canvas.addEventListener('wheel', this._onWheel, { passive: false });
    this.refresh();
  }

  exit() {
    this.active = false;
    this.scene3.remove(this.city.group);
    if (this.host) this.host.classList.add('hidden');
    document.body.classList.remove('citadel-mode');
    if (this.input) this.input.enabled = true;
    this.canvas.removeEventListener('pointerdown', this._onPointerDown);
    this.canvas.removeEventListener('pointermove', this._onPointerMove);
    window.removeEventListener('pointerup', this._onPointerUp);
    this.canvas.removeEventListener('wheel', this._onWheel);
    this.closeSheet();
    if (this.board) this.board.close();
  }

  /* -------------------------------------------------------------- camera */

  update(dt, elapsed) {
    this.city.update(dt, elapsed);
    this.city.applyCamera(this.camera);
    // the sun's shadow frustum follows the view, not the world origin
    if (this.city.sun) {
      const t = this.city.camera.target;
      this.city.sun.position.set(t.x - 52, 78, t.y + 64);
      this.city.sun.target.position.set(t.x, 0, t.y);
      this.city.sun.target.updateMatrixWorld();
    }
    // the sky dome rides with the camera
    if (this.city.sky?.mesh) this.city.sky.mesh.position.copy(this.camera.position);
    this._gather(dt);
  }

  _gather() {
    // the collect button carries its own badge, so the top bar never lies
    if (!this.collectBtn) return;
    const { resources } = pendingProduction(progression.get());
    const total = Object.values(resources).reduce((a, b) => a + b, 0);
    this.collectBtn.textContent = total > 0 ? `Empty the works · ${total}` : 'The works are empty';
    this.collectBtn.disabled = total <= 0;
  }

  /* ------------------------------------------------------------- picking */

  _pointerToNdc(ev) {
    const rect = this.canvas.getBoundingClientRect?.() || { left: 0, top: 0, width: 1, height: 1 };
    this._ndc.x = ((ev.clientX - rect.left) / rect.width) * 2 - 1;
    this._ndc.y = -((ev.clientY - rect.top) / rect.height) * 2 + 1;
  }

  pick(ev) {
    this._pointerToNdc(ev);
    this._ray.setFromCamera(this._ndc, this.camera);
    const hits = this._ray.intersectObjects(this.city.pickables, false);
    return hits.length ? hits[0].object.userData.site : null;
  }

  _onPointerDown(ev) {
    if (this.sheetId) return;
    this._drag = { x: ev.clientX, y: ev.clientY, moved: 0, id: ev.pointerId, touch: ev.pointerType === 'touch' };
    // the first tap also wakes the audio graph (browsers require a gesture)
    audio.init?.();
  }

  _onPointerMove(ev) {
    if (!this._drag) return;
    const dx = ev.clientX - this._drag.x;
    const dy = ev.clientY - this._drag.y;
    this._drag.x = ev.clientX;
    this._drag.y = ev.clientY;
    this._drag.moved += Math.abs(dx) + Math.abs(dy);
    if (this._drag.moved < 6) return;
    if (ev.shiftKey) {
      const k = this.city.camera.dist * 0.0018;
      this.city.pan(-dx * k, -dy * k);
    } else {
      this.city.orbit(dx, dy);
    }
  }

  _onPointerUp(ev) {
    const drag = this._drag;
    this._drag = null;
    if (!drag || drag.moved > 14 || !ev) return;
    const site = this.pick(ev);
    this.select(site || null);
  }

  _onWheel(ev) {
    ev.preventDefault();
    this.city.zoom(1 + Math.sign(ev.deltaY) * 0.09);
  }

  /* ------------------------------------------------------------ site card */

  select(id) {
    this.selected = id;
    if (!this.siteCard) return;
    if (!id) {
      this.siteCard.classList.add('hidden');
      return;
    }
    this.renderSiteCard();
    this.siteCard.classList.remove('hidden');
  }

  renderSiteCard() {
    const id = this.selected;
    if (!id || !this.siteCard) return;
    const site = SITES.find((s) => s.id === id);
    const def = BUILDINGS.find((b) => b.id === id);
    if (!site || !def) return;
    const s = progression.get();
    const level = s.buildings[id] || 1;
    const cost = upgradeCost(id, s);

    if (this.siteCard.replaceChildren) this.siteCard.replaceChildren();
    const head = el('div', 'cit-site-head');
    head.appendChild(el('h3', '', def.name));
    head.appendChild(el('span', 'cit-site-level', `level ${level} of ${def.maxLevel}`));
    this.siteCard.appendChild(head);
    this.siteCard.appendChild(el('p', 'cit-site-note', def.note));

    const pips = el('div', 'cit-pips');
    for (let i = 0; i < def.maxLevel; i++) pips.appendChild(el('span', `cit-pip${i < level ? ' on' : ''}`));
    this.siteCard.appendChild(pips);

    this.siteCard.appendChild(el('p', 'cit-site-effect', def.effect(level)));

    const foot = el('div', 'cit-site-foot');
    if (cost) {
      const raise = el('button', 'cit-btn seal', 'Raise the work');
      raise.type = 'button';
      raise.addEventListener('click', () => {
        const next = progression.upgrade(id);
        if (next) {
          this.city.setLevel(id, next);
          audio.ui?.('build');
          this.renderSiteCard();
        } else {
          raise.textContent = 'The stores will not bear it';
          setTimeout(() => { raise.textContent = 'Raise the work'; }, 1400);
        }
      });
      foot.appendChild(raise);
      foot.appendChild(el('span', 'cit-cost', costLine(cost)));
    } else {
      foot.appendChild(el('span', 'cit-stamp', 'at its height'));
    }
    this.siteCard.appendChild(foot);
  }

  /* --------------------------------------------------------------- sheets */

  openDock(id) {
    if (id === 'citadel') {
      this.closeSheet();
      this.city.camera.dist = 64;
      return;
    }
    this.sheetId = id;
    if (!this.sheet) return;
    this.sheet.classList.remove('hidden');

    if (id === 'chart') {
      this.sheetTitle.textContent = 'The Chart';
      this.sheetNote.textContent = 'The road south from Ziklag, and every field on it.';
      this.sheetBody.replaceChildren();
      this.renderChart();
      return;
    }

    const titles = {
      charge: ['The Charge', 'What must be done, in the order the scripture gives it.'],
      band: ['The Band', 'The mighty men, their craft, and the drills that sharpen it.'],
      muster: ['The Host', 'The companies of the six hundred, their tiers and their wounded.'],
      field: ['The Field', 'The stages: take the band out and win the ground.'],
    };
    const [title, note] = titles[id] || ['The War Table', ''];
    this.sheetTitle.textContent = title;
    this.sheetNote.textContent = note;
    this.sheetBody.replaceChildren();
    this.sheetBody.appendChild(this.war.host);
    this.war.build();
    this.war.show(id);
  }

  closeSheet() {
    this.sheetId = null;
    if (this.sheet) this.sheet.classList.add('hidden');
  }

  /* ---------------------------------------------------------------- chart */

  renderChart() {
    const canvas = document.createElement('canvas');
    canvas.className = 'cit-chart';
    const dpr = Math.min(2, (typeof window !== 'undefined' && window.devicePixelRatio) || 1);
    const w = 760;
    const h = 460;
    canvas.width = w * dpr;
    canvas.height = h * dpr;
    const ctx = canvas.getContext?.('2d');
    this.sheetBody.appendChild(canvas);

    // the ledger under the map
    const ledger = el('p', 'cit-chart-ledger', '');
    this.chartLedger = ledger;
    this.sheetBody.appendChild(ledger);

    const nodes = [
      { id: 'ziklag', name: 'Ziklag', x: 0.5, y: 0.14, kind: 'home' },
      { id: 'besor', name: 'the Brook Besor', x: 0.34, y: 0.3, kind: 'water' },
      { id: 'besor-watch', name: 'the watch', x: 0.24, y: 0.38, kind: 'stage' },
      { id: 'ford', name: 'the ford by night', x: 0.42, y: 0.44, kind: 'stage' },
      { id: 'outpost', name: 'the escarpment', x: 0.6, y: 0.52, kind: 'stage' },
      { id: 'perimeter', name: 'the camp perimeter', x: 0.46, y: 0.66, kind: 'stage' },
      { id: 'long-night', name: 'the long night', x: 0.66, y: 0.74, kind: 'stage' },
      { id: 'gilboa-slope', name: 'Gilboa', x: 0.8, y: 0.88, kind: 'stage' },
    ];
    if (!ctx) {
      ledger.textContent = 'The chart needs a canvas this browser will draw on.';
      return;
    }
    ctx.scale(dpr, dpr);

    // vellum ground
    const g = ctx.createLinearGradient(0, 0, 0, h);
    g.addColorStop(0, '#e6d3a8');
    g.addColorStop(1, '#cdb383');
    ctx.fillStyle = g;
    ctx.fillRect(0, 0, w, h);

    // the wadi and the road
    ctx.strokeStyle = 'rgba(90, 66, 34, 0.55)';
    ctx.lineWidth = 2;
    ctx.beginPath();
    nodes.forEach((n, i) => {
      const x = n.x * w;
      const y = n.y * h;
      if (i === 0) ctx.moveTo(x, y);
      else ctx.lineTo(x, y);
    });
    ctx.stroke();

    // the sea, east
    ctx.fillStyle = 'rgba(70, 108, 120, 0.35)';
    ctx.beginPath();
    ctx.moveTo(w * 0.9, 0);
    ctx.lineTo(w, 0);
    ctx.lineTo(w, h);
    ctx.lineTo(w * 0.94, h);
    ctx.closePath();
    ctx.fill();

    // the hills
    ctx.strokeStyle = 'rgba(90, 66, 34, 0.28)';
    ctx.lineWidth = 1;
    for (let i = 0; i < 7; i++) {
      ctx.beginPath();
      ctx.arc(w * (0.1 + i * 0.13), h * (0.2 + (i % 3) * 0.17), 16 + (i % 4) * 9, Math.PI, 0);
      ctx.stroke();
    }

    const won = muster.get().wins;
    for (const n of nodes) {
      const x = n.x * w;
      const y = n.y * h;
      const held = n.kind === 'stage' && won[n.id];
      ctx.beginPath();
      ctx.arc(x, y, n.kind === 'home' ? 9 : 6, 0, Math.PI * 2);
      ctx.fillStyle = n.kind === 'home' ? '#7a2418'
        : held ? '#8a6a2c' : n.kind === 'water' ? '#3f6c7a' : '#4a3520';
      ctx.fill();
      ctx.strokeStyle = 'rgba(40, 26, 12, 0.8)';
      ctx.lineWidth = 1.4;
      ctx.stroke();

      ctx.fillStyle = 'rgba(43, 29, 16, 0.92)';
      ctx.font = '13px "EB Garamond", Georgia, serif';
      ctx.fillText(n.name, x + 12, y + 4);
      if (held) {
        ctx.font = '11px Inter, sans-serif';
        ctx.fillStyle = 'rgba(43, 29, 16, 0.6)';
        ctx.fillText(`held ×${held}`, x + 12, y + 19);
      }
    }

    // a compass rose, north-east of the map
    ctx.strokeStyle = 'rgba(43, 29, 16, 0.5)';
    ctx.beginPath();
    ctx.arc(w - 46, 46, 20, 0, Math.PI * 2);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(w - 46, 22);
    ctx.lineTo(w - 46, 70);
    ctx.moveTo(w - 70, 46);
    ctx.lineTo(w - 22, 46);
    ctx.stroke();
    ctx.fillStyle = 'rgba(43, 29, 16, 0.7)';
    ctx.font = '11px Inter, sans-serif';
    ctx.fillText('N', w - 50, 18);

    const held = Object.keys(won).length;
    ledger.textContent = `${held} of ${SKIRMISHES.length} fields held · ${musterPower(muster.get(), progression.get().buildings.barracks).men} men under arms`;

    // tapping a field node starts that skirmish
    canvas.addEventListener('click', (ev) => {
      const rect = canvas.getBoundingClientRect();
      const mx = ((ev.clientX - rect.left) / rect.width) * w;
      const my = ((ev.clientY - rect.top) / rect.height) * h;
      let best = null;
      let bestD = 34;
      for (const n of nodes) {
        if (n.kind !== 'stage') continue;
        const d = Math.hypot(n.x * w - mx, n.y * h - my);
        if (d < bestD) { bestD = d; best = n; }
      }
      if (!best) return;
      const stage = SKIRMISHES.find((s) => s.id === best.id);
      if (stage) this.fight(stage);
    });
  }

  fight(stage) {
    if (!this.board) this.board = new SkirmishBoard({ onClose: () => this.refresh() });
    this.closeSheet();
    this.board.open(stage);
  }

  /* --------------------------------------------------------------- refresh */

  refresh() {
    if (!this.built) return;
    const s = progression.get();

    if (this.resBar) {
      this.resBar.replaceChildren();
      const res = [
        ['provisions', '◍'], ['bronze', '◈'], ['timber', '⌶'], ['spoil', '✦'],
      ];
      for (const [id, glyph] of res) {
        const chip = el('div', 'cit-res-chip');
        chip.appendChild(el('span', 'cit-res-glyph', glyph));
        chip.appendChild(el('span', 'cit-res-amount', s.resources[id] || 0));
        this.resBar.appendChild(chip);
      }
      const power = musterPower(muster.get(), s.buildings.barracks);
      const pchip = el('div', 'cit-res-chip power');
      pchip.appendChild(el('span', 'cit-res-glyph', '⚔'));
      pchip.appendChild(el('span', 'cit-res-amount', power.men));
      this.resBar.appendChild(pchip);
    }

    if (this.lordSub) {
      const task = currentTask(s);
      this.lordSub.textContent = task
        ? `Next: ${task.title.toLowerCase()} · ${s.tasksDone.length} of ${TASKS.length} done`
        : 'The charge is kept. So begins the kingdom.';
    }

    if (this.dockButtons) {
      const pending = pendingProduction(s);
      for (const b of this.dockButtons) {
        const dot = b.id === 'charge' || b.id === 'field';
        b.node.classList.toggle('alert', dot && this._hasSomethingWaiting(b.id, s, pending));
      }
    }

    this._gather();
    if (this.selected) this.renderSiteCard();
    if (this.sheetId === 'chart' && this.chartLedger) {
      const held = Object.keys(muster.get().wins).length;
      this.chartLedger.textContent = `${held} of ${SKIRMISHES.length} fields held · `
        + `${musterPower(muster.get(), s.buildings.barracks).men} men under arms`;
    }
  }

  _hasSomethingWaiting(id, s) {
    if (id === 'charge') return s.tasksDone.some((t) => !s.tasksClaimed.includes(t));
    return false;
  }
}
