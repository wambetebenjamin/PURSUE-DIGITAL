/**
 * game/CampScene.js — the Ziklag Encampment.
 *
 * The camp is drawn with Phaser's Graphics: no textures, no images, no
 * external assets. A tent, a barracks, the six works of the settlement and
 * the road east, with the stores and the number of men under arms along the
 * top — every figure fetched from the server.
 *
 * Clicking a building raises it; clicking a company in the strip musters a
 * press of ten. When the server says yes, the building pulses and a line of
 * gold rises from it; when it says no, it says why.
 */

import Phaser from 'phaser';
import { COLORS, CSS, TEXT, SIZES } from './theme.js';
import {
  campLayout, readouts, companyRows, levelOf, upgradeCost, recruitCost,
  costLine, PLACES, STAGES,
} from './layout.js';
import { sessionApi, sessionLeave, sessionCitadel } from './session.js';

export default class CampScene extends Phaser.Scene {
  constructor() {
    super('Camp');
  }

  init(data) {
    this.api = (data && data.api) || sessionApi();
    this.onLeave = (data && data.onLeave) || sessionLeave();
    this.onCitadel = (data && data.onCitadel) || sessionCitadel();
    this.stageId = (data && data.stageId) || STAGES[0].id;
    this.selected = 'spears';
    this.message = '';
    this.busy = false;
    this.sized = [];
  }

  create() {
    this.cameras.main.setBackgroundColor(COLORS.ink);
    this.drawBackdrop();
    this.drawTitle();
    this.drawPlaces();
    this.drawReadouts();
    this.drawCompanyStrip();
    this.drawStages();
    this.drawButtons();
    this.layout();

    this.scale.on('resize', this.layout, this);
    this.events.once('shutdown', () => this.scale.off('resize', this.layout, this));

    this.fetchState();
  }

  /** The name of the camp, over the ridge. */
  drawTitle() {
    this.titleText = this.txt(0, 0, 'ZIKLAG', 'title').setOrigin(0, 0);
    this.titleSub = this.txt(0, 0, 'the encampment — 1 Samuel 30', 'subtitle').setOrigin(0, 0);
  }

  /* ---------------------------------------------------------------- text */

  /** A text whose size follows the camp's scale. */
  txt(x, y, str, style) {
    const t = this.add.text(x, y, str, TEXT[style]);
    this.sized.push({ t, px: SIZES[style] });
    return t;
  }

  fitText() {
    const s = this.s || 1;
    for (const { t, px } of this.sized) t.setFontSize(Math.max(9, Math.round(px * s)));
  }

  /* ------------------------------------------------------------- backdrop */

  drawBackdrop() {
    this.sky = this.add.graphics();
    this.ground = this.add.graphics();
    this.stars = [];
    // a scatter of stars over the dark
    for (let i = 0; i < 44; i += 1) {
      const s = this.add.circle(0, 0, 1 + (i % 3), COLORS.parchment, 0.08 + (i % 5) * 0.05);
      s.setScrollFactor(0);
      this.stars.push(s);
    }
  }

  /* -------------------------------------------------------------- places */

  drawPlaces() {
    this.places = {};
    for (const def of PLACES) {
      const container = this.add.container(0, 0);
      const art = this.add.graphics();
      container.add(art);

      const label = this.txt(0, 0, def.name, 'building').setOrigin(0.5, 0);
      const sub = this.txt(0, 0, def.sub, 'buildingDim').setOrigin(0.5, 0);
      const badge = this.txt(0, 0, '', 'readoutLabel').setOrigin(0.5, 0);
      container.add([label, sub, badge]);

      const hit = this.add.zone(0, 0, 10, 10)
        .setInteractive({ useHandCursor: true })
        .on('pointerdown', () => this.onPlaceTap(def, container));
      container.add(hit);

      this.places[def.id] = { def, container, art, label, sub, badge, hit };
    }
  }

  /** The shape of each kind of place, drawn in local space (0,0 at its base). */
  paintPlace(entry) {
    const { art, def } = entry;
    const { w, h } = this.sizeOf(def.id);
    art.clear();

    if (def.kind === 'main') {
      // the tent: a ridge pole over hanging cloth
      art.fillStyle(COLORS.oxblood, 1);
      art.fillTriangle(-w / 2, 0, 0, -h, w / 2, 0);
      art.fillStyle(COLORS.oxbloodLight, 1);
      art.fillTriangle(-w / 2, 0, 0, -h * 0.42, w / 2, 0);
      art.fillStyle(COLORS.ink, 1);
      art.fillTriangle(-w * 0.16, 0, 0, -h * 0.78, w * 0.16, 0);
      // the pole and its pennant
      art.fillStyle(COLORS.bronze, 1);
      art.fillRect(-2 * this.s, -h - 18 * this.s, 4 * this.s, 20 * this.s);
      art.fillStyle(COLORS.gold, 1);
      art.fillTriangle(2 * this.s, -h - 18 * this.s, 34 * this.s, -h - 10 * this.s, 2 * this.s, -h - 2 * this.s);
      art.lineStyle(2, COLORS.bronze, 0.8);
      art.strokeTriangle(-w / 2, 0, 0, -h, w / 2, 0);
    } else if (def.kind === 'barracks') {
      // the barracks: a long hall with a rank of spears
      art.fillStyle(COLORS.earth, 1);
      art.fillRect(-w / 2, -h * 0.62, w, h * 0.62);
      art.fillStyle(COLORS.oxblood, 1);
      art.fillTriangle(-w / 2, -h * 0.62, 0, -h, w / 2, -h * 0.62);
      art.lineStyle(2, COLORS.bronze, 0.75);
      art.strokeRect(-w / 2, -h * 0.62, w, h * 0.62);
      for (let i = 0; i < 7; i += 1) {
        const x = -w / 2 + 12 * this.s + (i * (w - 24 * this.s)) / 6;
        art.fillStyle(COLORS.bronzeLight, 0.9);
        art.fillRect(x, -h * 0.46, 2 * this.s, h * 0.4);
        art.fillStyle(COLORS.bronze, 1);
        art.fillTriangle(x - 3 * this.s, -h * 0.46, x + 1 * this.s, -h * 0.54, x + 5 * this.s, -h * 0.46);
      }
    } else if (def.kind === 'cistern') {
      // the cistern: a round stone tank, brimmed with water
      art.fillStyle(COLORS.earth, 1);
      art.fillRect(-w * 0.3, -h * 0.5, w * 0.6, h * 0.5);
      art.fillStyle(COLORS.bronze, 0.55);
      art.fillEllipse(0, -h * 0.5, w * 0.62, h * 0.18);
      art.fillStyle(COLORS.ink, 0.85);
      art.fillEllipse(0, -h * 0.5, w * 0.5, h * 0.12);
      art.lineStyle(2, COLORS.bronze, 0.7);
      art.strokeRect(-w * 0.3, -h * 0.5, w * 0.6, h * 0.5);
      // the winch over it
      art.fillStyle(COLORS.bronzeLight, 0.8);
      art.fillRect(-w * 0.36, -h * 0.86, w * 0.72, 3 * this.s);
      art.fillRect(-w * 0.34, -h * 0.86, 3 * this.s, h * 0.36);
    } else if (def.kind === 'wall') {
      // the wall: courses of stone, a gate in the middle
      art.fillStyle(COLORS.earth, 1);
      art.fillRect(-w / 2, -h * 0.7, w, h * 0.7);
      art.fillStyle(COLORS.earthLight, 0.5);
      for (let row = 0; row < 3; row += 1) {
        for (let i = 0; i < 5; i += 1) {
          const x = -w / 2 + 6 * this.s + i * ((w - 12 * this.s) / 5) + (row % 2) * 6 * this.s;
          art.fillRect(x, -h * 0.7 + row * (h * 0.22), (w - 12 * this.s) / 5 - 3 * this.s, h * 0.16);
        }
      }
      art.fillStyle(COLORS.ink, 1);
      art.fillRect(-w * 0.1, -h * 0.34, w * 0.2, h * 0.34);
      art.lineStyle(2, COLORS.bronze, 0.6);
      art.strokeRect(-w / 2, -h * 0.7, w, h * 0.7);
    } else if (def.id === 'quarry') {
      art.fillStyle(COLORS.earthLight, 1);
      art.fillTriangle(-w / 2, 0, -w * 0.18, -h, w / 2, 0);
      art.fillStyle(COLORS.earth, 1);
      art.fillTriangle(w / 2, 0, w * 0.18, -h * 0.8, w * 0.46, 0);
      art.lineStyle(2, COLORS.bronze, 0.6);
      art.strokeTriangle(-w / 2, 0, -w * 0.18, -h, w / 2, 0);
    } else if (def.id === 'smelter') {
      art.fillStyle(COLORS.earth, 1);
      art.fillRect(-w / 2, -h * 0.7, w, h * 0.7);
      art.fillStyle(COLORS.oxblood, 1);
      art.fillRect(w * 0.18, -h, w * 0.18, h * 0.4);
      art.fillStyle(COLORS.ember, 0.85);
      art.fillCircle(-w * 0.12, -h * 0.35, w * 0.14);
      art.fillStyle(COLORS.gold, 0.9);
      art.fillCircle(-w * 0.12, -h * 0.35, w * 0.07);
    } else if (def.id === 'hospital') {
      art.fillStyle(COLORS.parchment, 0.92);
      art.fillTriangle(-w / 2, 0, 0, -h * 0.9, w / 2, 0);
      art.fillStyle(COLORS.blood, 0.85);
      art.fillRect(-3 * this.s, -h * 0.62, 6 * this.s, h * 0.3);
      art.fillRect(-w * 0.1, -h * 0.55, w * 0.2, 6 * this.s);
    } else {
      // the threshing floor: a beaten circle of straw
      art.fillStyle(COLORS.earthLight, 0.85);
      art.fillEllipse(0, -h * 0.16, w * 0.92, h * 0.34);
      art.lineStyle(2, COLORS.bronze, 0.5);
      art.strokeEllipse(0, -h * 0.16, w * 0.92, h * 0.34);
      for (let i = 0; i < 6; i += 1) {
        const x = -w * 0.32 + i * (w * 0.13);
        art.lineStyle(1, COLORS.ember, 0.5);
        art.lineBetween(x, -h * 0.3, x + 6 * this.s, -h * 0.02);
      }
    }
  }

  /* ------------------------------------------------------------ readouts */

  drawReadouts() {
    this.readoutPanel = this.add.graphics();
    this.readoutTexts = [];
  }

  paintReadouts() {
    const rows = readouts(this.state);
    const box = this.layout_ ? this.layout_.readout : { x: 0, y: 0, w: 0, h: 0 };
    this.readoutPanel.clear();
    this.readoutPanel.fillStyle(COLORS.oxblood, 0.72);
    this.readoutPanel.fillRoundedRect(box.x, box.y, box.w, box.h, 6);
    this.readoutPanel.lineStyle(1, COLORS.bronze, 0.55);
    this.readoutPanel.strokeRoundedRect(box.x, box.y, box.w, box.h, 6);

    for (const t of this.readoutTexts) {
      t.destroy();
      const i = this.sized.findIndex((s) => s.t === t);
      if (i >= 0) this.sized.splice(i, 1);
    }
    this.readoutTexts = [];

    const step = box.w / rows.length;
    rows.forEach((row, i) => {
      const x = box.x + 18 * this.s + i * step;
      const label = this.txt(x, box.y + 12 * this.s, row.label, 'readoutLabel');
      const value = this.txt(x, box.y + 28 * this.s, row.value, 'readout');
      this.readoutTexts.push(label, value);
    });
  }

  /* ------------------------------------------------------- company strip */

  drawCompanyStrip() {
    this.stripPanel = this.add.graphics();
    this.companyButtons = [];
    for (const c of companyRows({ troops: { companies: {} } })) {
      const label = this.txt(0, 0, '', 'buttonDim').setOrigin(0.5, 0.5);
      const box = this.add.rectangle(0, 0, 10, 10, COLORS.oxblood, 0.8)
        .setInteractive({ useHandCursor: true })
        .on('pointerdown', () => this.onCompanyTap(c.id));
      this.companyButtons.push({ id: c.id, label, box });
    }
  }

  paintCompanyStrip() {
    const rows = companyRows(this.state);
    const strip = this.layout_ ? this.layout_.strip : { x: 0, y: 0, w: 0, h: 0 };
    this.stripPanel.clear();
    this.stripPanel.fillStyle(COLORS.ink, 0.55);
    this.stripPanel.fillRoundedRect(strip.x - 12 * this.s, strip.y - 22 * this.s,
      strip.w + 24 * this.s, 44 * this.s, 6);

    const step = strip.w / rows.length;
    rows.forEach((row, i) => {
      const b = this.companyButtons.find((x) => x.id === row.id);
      const x = strip.x + step * i + step / 2;
      const chosen = this.selected === row.id;
      b.box.setPosition(x, strip.y);
      b.box.setSize(step - 8 * this.s, 38 * this.s);
      b.box.setFillStyle(chosen ? COLORS.oxbloodLight : COLORS.oxblood, chosen ? 0.98 : 0.7);
      b.box.setStrokeStyle(chosen ? 2 : 1, chosen ? COLORS.gold : COLORS.bronze, chosen ? 0.9 : 0.4);
      b.label.setPosition(x, strip.y);
      b.label.setText(`${row.name}\n${row.count}`);
      b.label.setColor(chosen ? CSS.highlight : CSS.bronzeLight);
    });
  }

  /* ----------------------------------------------------------- the stages */

  drawStages() {
    this.stageButtons = [];
    for (const s of STAGES) {
      const label = this.txt(0, 0, s.short || s.name, 'small').setOrigin(0.5, 0.5);
      const box = this.add.rectangle(0, 0, 10, 10, COLORS.ink, 0.6)
        .setInteractive({ useHandCursor: true })
        .on('pointerdown', () => {
          this.stageId = s.id;
          this.paintStages();
        });
      this.stageButtons.push({ id: s.id, label, box });
    }
    this.stageCaption = this.txt(0, 0, 'CHOOSE THE GROUND', 'readoutLabel').setOrigin(0.5, 0);
  }

  paintStages() {
    const box = this.layout_ ? this.layout_.stages : { x: 0, y: 0, step: 100, h: 40 };
    const step = box.step || 100;
    this.stageButtons.forEach((b, i) => {
      const x = box.x + step * i + step / 2;
      const chosen = this.stageId === b.id;
      b.box.setPosition(x, box.y + box.h / 2);
      b.box.setSize(step - 10 * this.s, box.h);
      b.box.setFillStyle(chosen ? COLORS.oxbloodLight : COLORS.ink, chosen ? 0.95 : 0.5);
      b.box.setStrokeStyle(1, chosen ? COLORS.gold : COLORS.bronze, chosen ? 0.8 : 0.35);
      b.label.setPosition(x, box.y + box.h / 2);
      b.label.setColor(chosen ? CSS.highlight : CSS.bronze);
    });
    if (this.stageCaption) {
      this.stageCaption.setPosition(box.x + step * 1.5, box.y - 14 * this.s);
    }
  }

  /* ------------------------------------------------------------- buttons */

  drawButtons() {
    this.pursueButton = this.makeButton('Pursue the Amalekites', () => this.onPursue());
    this.roadButton = this.makeButton('Take the road', () => {
      if (this.onLeave) this.onLeave();
    }, true);
    this.citadelLink = this.txt(0, 0, 'the citadel ›', 'small')
      .setOrigin(1, 0)
      .setInteractive({ useHandCursor: true })
      .on('pointerdown', () => {
        if (this.onCitadel) this.onCitadel();
      });
    this.hint = this.txt(0, 0, '', 'small').setOrigin(0, 0);
  }

  makeButton(label, onClick, secondary = false) {
    const text = this.txt(0, 0, label, 'button').setOrigin(0.5, 0.5);
    const box = this.add.rectangle(0, 0, 10, 10, secondary ? COLORS.oxblood : COLORS.gold, 0.92)
      .setInteractive({ useHandCursor: true })
      .on('pointerdown', onClick);
    return { text, box, secondary };
  }

  /* -------------------------------------------------------------- layout */

  sizeOf(id) {
    const l = this.layout_ || campLayout(this.scale.width, this.scale.height);
    if (id === 'hall') return l.tent;
    if (id === 'barracks') return l.barracks;
    return l.works[id] || { x: 0, y: 0, w: 100, h: 60 };
  }

  layout() {
    const w = this.scale.width;
    const h = this.scale.height;
    const l = campLayout(w, h);
    this.layout_ = l;
    this.s = l.scale;

    // the sky and the earth
    this.sky.clear();
    this.sky.fillStyle(COLORS.night, 1);
    this.sky.fillRect(0, 0, w, l.horizon);
    this.sky.fillStyle(COLORS.ink, 0.55);
    this.sky.fillRect(0, l.horizon * 0.55, w, l.horizon * 0.45);
    // low hills on the ridge
    this.sky.fillStyle(0x1f1013, 1);
    this.sky.fillTriangle(w * 0.04, l.horizon, w * 0.22, l.horizon - 46 * l.scale, w * 0.42, l.horizon);
    this.sky.fillTriangle(w * 0.52, l.horizon, w * 0.70, l.horizon - 34 * l.scale, w * 0.92, l.horizon);

    this.ground.clear();
    this.ground.fillStyle(COLORS.earth, 1);
    this.ground.fillRect(0, l.horizon, w, h - l.horizon);
    this.ground.fillStyle(COLORS.earthLight, 0.35);
    this.ground.fillRect(0, l.horizon, w, 10 * l.scale);
    // the road east, toward the brook Besor
    this.ground.fillStyle(COLORS.earthLight, 0.45);
    this.ground.fillTriangle(l.gate.x, l.horizon, w, l.horizon - 6 * l.scale, w, l.horizon + 26 * l.scale);
    this.ground.fillStyle(COLORS.earthLight, 0.25);
    this.ground.fillTriangle(l.gate.x, l.horizon, l.gate.x + 60 * l.scale, l.horizon - 4 * l.scale,
      l.gate.x + 16 * l.scale, l.horizon + 26 * l.scale);

    this.stars.forEach((s, i) => {
      s.setPosition((i * 137) % Math.max(1, w), 16 + ((i * 71) % Math.max(1, Math.floor(l.horizon * 0.8))));
    });

    // the title
    this.titleText.setPosition(l.title.x, l.title.y);
    this.titleSub.setPosition(l.title.x, l.title.y + 46 * l.scale);

    // the places
    for (const id of Object.keys(this.places)) {
      const entry = this.places[id];
      const box = this.sizeOf(id);
      entry.container.setPosition(box.x, box.y);
      this.paintPlace(entry);
      entry.label.setPosition(0, 8 * l.scale);
      entry.sub.setPosition(0, 30 * l.scale);
      entry.badge.setPosition(0, -box.h - 26 * l.scale);
      entry.badge.setText(`LEVEL ${levelOf(this.state, id)}`);
      entry.hit.setPosition(0, -box.h / 2);
      entry.hit.setSize(box.w * 1.15, box.h * 1.15);
    }

    this.paintReadouts();
    this.paintCompanyStrip();
    this.paintStages();

    // the buttons
    const bw = l.buttons.w;
    const bh = 44 * l.scale;
    const by = l.buttons.y;
    const gap = l.buttons.gap;
    this.pursueButton.box.setPosition(l.buttons.x - bw - gap * 0.5, by);
    this.pursueButton.box.setSize(bw, bh);
    this.pursueButton.text.setPosition(l.buttons.x - bw - gap * 0.5, by);
    this.roadButton.box.setPosition(l.buttons.x + bw + gap * 0.5, by);
    this.roadButton.box.setSize(bw, bh);
    this.roadButton.text.setPosition(l.buttons.x + bw + gap * 0.5, by);
    this.citadelLink.setPosition(w - 24 * l.scale, 22 * l.scale);
    this.hint.setPosition(l.readout.x, l.readout.y + l.readout.h + 6 * l.scale);
    this.hint.setText(this.message || '');

    // the lettering follows the canvas: it is set last, when every word of
    // the camp is on the board
    this.fitText();
  }

  /* --------------------------------------------------------------- state */

  async fetchState() {
    if (!this.api) {
      this.state = null;
      this.layout();
      return;
    }
    this.state = await this.api.refresh();
    this.message = this.api.online
      ? 'the camp is kept by the server'
      : 'the camp keeps its own ledger — the server is away';
    this.layout();
  }

  /* ------------------------------------------------------------ actions */

  async onPlaceTap(def, container) {
    if (this.busy || !this.api) return;
    this.busy = true;
    const level = levelOf(this.state, def.id);
    const cost = upgradeCost(def.id, level);
    const result = await this.api.upgradeCamp(def.id);
    this.busy = false;
    if (result.ok) {
      this.message = `${def.name} raised to level ${result.data.level} · ${costLine(cost)}`;
      this.pulse(container);
      this.popup(container, `+1  ${def.name}`, CSS.gold);
    } else {
      this.message = result.message;
      this.pulse(container, true);
      this.popup(container, result.message, CSS.blood);
    }
    this.layout();
  }

  async onCompanyTap(companyId) {
    if (this.busy || !this.api) return;
    this.selected = companyId;
    this.busy = true;
    const result = await this.api.recruitTroops(companyId, 1);
    this.busy = false;
    const cost = recruitCost(companyId, 1);
    if (result.ok) {
      this.message = `a press of ten mustered · ${costLine(cost)}`;
    } else {
      this.message = result.message;
    }
    this.layout();
  }

  onPursue() {
    if (!this.api) return;
    this.scene.start('Pursuit', {
      api: this.api,
      stageId: this.stageId,
      onLeave: this.onLeave,
    });
  }

  /* ------------------------------------------------------------ effects */

  pulse(container, bad = false) {
    this.tweens.add({
      targets: container,
      scale: bad ? 0.94 : 1.06,
      duration: 140,
      yoyo: true,
      ease: 'Quad.easeOut',
    });
  }

  popup(container, text, color) {
    const t = this.add.text(container.x, container.y - 30, text, {
      fontFamily: '"EB Garamond", Georgia, serif',
      fontSize: `${Math.max(11, Math.round(20 * this.s))}px`,
      color,
    }).setOrigin(0.5, 1);
    this.tweens.add({
      targets: t,
      y: container.y - 96 * this.s,
      alpha: 0,
      duration: 1100,
      ease: 'Quad.easeOut',
      onComplete: () => t.destroy(),
    });
  }
}
