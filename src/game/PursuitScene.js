/**
 * game/PursuitScene.js — the pursuit.
 *
 * The band goes out east while the server settles the fight. The march is
 * drawn with moving shapes; the result is the server's, not the client's.
 * When the answer comes, the field is shown: won or lost, the loot that was
 * recovered, the men still standing, and the way back to Ziklag.
 */

import Phaser from 'phaser';
import { COLORS, CSS, TEXT, SIZES } from './theme.js';
import { campLayout, formatNumber, STAGES } from './layout.js';
import { sessionApi, sessionLeave } from './session.js';

const MARCHERS = 16;

export default class PursuitScene extends Phaser.Scene {
  constructor() {
    super('Pursuit');
  }

  init(data) {
    this.api = (data && data.api) || sessionApi();
    this.onLeave = (data && data.onLeave) || sessionLeave();
    this.stageId = (data && data.stageId) || STAGES[0].id;
    this.result = null;
    this.resolved = false;
    this.sized = [];
  }

  create() {
    this.cameras.main.setBackgroundColor(COLORS.night);
    this.drawScene();
    this.layout();

    this.scale.on('resize', this.layout, this);
    this.events.once('shutdown', () => this.scale.off('resize', this.layout, this));

    this.march();
    this.fight();
  }

  /* ---------------------------------------------------------------- text */

  txt(x, y, str, style) {
    const t = this.add.text(x, y, str, TEXT[style]);
    this.sized.push({ t, px: SIZES[style] });
    return t;
  }

  fitText() {
    const s = this.s || 1;
    for (const { t, px } of this.sized) t.setFontSize(Math.max(9, Math.round(px * s)));
  }

  /* --------------------------------------------------------------- scene */

  drawScene() {
    this.ground = this.add.graphics();
    this.marchers = [];
    for (let i = 0; i < MARCHERS; i += 1) {
      const body = this.add.rectangle(0, 0, 8, 16, COLORS.oxbloodLight, 1);
      const spear = this.add.rectangle(0, 0, 2, 22, COLORS.bronze, 0.9);
      this.marchers.push({ body, spear });
    }

    this.title = this.txt(0, 0, '', 'title').setOrigin(0.5, 0);
    this.status = this.txt(0, 0, 'Troops marching…', 'subtitle').setOrigin(0.5, 0.5);
    this.resultTitle = this.txt(0, 0, '', 'big').setOrigin(0.5, 0.5).setVisible(false);
    this.resultBody = this.txt(0, 0, '', 'body')
      .setOrigin(0.5, 0.5).setAlign('center').setVisible(false);
    this.lootLine = this.txt(0, 0, '', 'subtitle')
      .setOrigin(0.5, 0.5).setVisible(false);

    this.backText = this.txt(0, 0, 'Return to Ziklag', 'button').setOrigin(0.5, 0.5);
    this.backBox = this.add.rectangle(0, 0, 10, 10, COLORS.gold, 0.92)
      .setInteractive({ useHandCursor: true })
      .on('pointerdown', () => this.goBack());
    this.backText.setVisible(false);
    this.backBox.setVisible(false);
  }

  layout() {
    const w = this.scale.width;
    const h = this.scale.height;
    const l = campLayout(w, h);
    this.s = l.scale;
    this.fitText();

    this.ground.clear();
    this.ground.fillStyle(COLORS.night, 1);
    this.ground.fillRect(0, 0, w, l.horizon);
    this.ground.fillStyle(0x1f1013, 1);
    this.ground.fillTriangle(w * 0.02, l.horizon, w * 0.20, l.horizon - 40 * l.scale, w * 0.38, l.horizon);
    this.ground.fillStyle(COLORS.earth, 1);
    this.ground.fillRect(0, l.horizon, w, h - l.horizon);
    this.ground.fillStyle(COLORS.earthLight, 0.3);
    this.ground.fillRect(0, l.horizon, w, 8 * l.scale);
    // the dust of the march, low on the horizon
    this.ground.fillStyle(COLORS.ember, 0.16);
    this.ground.fillEllipse(w * 0.5, l.horizon + 10 * l.scale, w * 0.9, 26 * l.scale);

    const stage = STAGES.find((s) => s.id === this.stageId) || STAGES[0];
    this.title.setPosition(w / 2, l.horizon * 0.26);
    this.title.setText(stage.name);
    this.status.setPosition(w / 2, l.horizon * 0.52);

    this.resultTitle.setPosition(w / 2, l.horizon * 0.44);
    this.resultBody.setPosition(w / 2, l.horizon * 0.66);
    this.lootLine.setPosition(w / 2, l.horizon * 0.82);

    this.backBox.setPosition(w / 2, h - 58 * l.scale);
    this.backBox.setSize(260 * l.scale, 46 * l.scale);
    this.backText.setPosition(w / 2, h - 58 * l.scale);
  }

  /* -------------------------------------------------------------- march */

  march() {
    const l = campLayout(this.scale.width, this.scale.height);
    const groundY = l.horizon + 18 * l.scale;
    const spread = this.scale.width;
    this.marchers.forEach((m, i) => {
      m.body.setPosition((i * spread) / MARCHERS, groundY);
      m.spear.setPosition((i * spread) / MARCHERS, groundY - 16 * l.scale);
      m.body.setAlpha(0.92);
    });
    // the men walk east, over and over, until the server answers
    this.marchTween = this.tweens.add({
      targets: this.marchers,
      x: (i) => (i * spread) / MARCHERS + spread * 0.22,
      duration: 5200,
      repeat: -1,
      ease: 'Linear',
      onUpdate: () => {
        for (const m of this.marchers) {
          const x = ((m.body.x % spread) + spread) % spread;
          m.body.x = x;
          m.spear.x = x;
        }
      },
    });
    // the bob of men on the march
    this.bob = this.add.tween({
      targets: this.marchers.map((m) => m.body),
      y: '+=4',
      duration: 320,
      yoyo: true,
      repeat: -1,
      ease: 'Sine.easeInOut',
    });
  }

  stopMarch() {
    if (this.marchTween) this.marchTween.stop();
    if (this.bob) this.bob.stop();
    this.marchers.forEach((m) => { m.body.setAlpha(0); m.spear.setAlpha(0); });
  }

  /* -------------------------------------------------------------- fight */

  async fight() {
    if (!this.api) {
      this.status.setText('there is no band to send');
      return;
    }
    const result = await this.api.pursue(this.stageId);
    if (!result || !result.ok) {
      this.stopMarch();
      this.status.setText(result ? result.message : 'the band could not be sent out');
      return;
    }
    this.result = result.data;
    this.resolved = true;
    this.showResult(result.data);
  }

  showResult(data) {
    this.stopMarch();
    const won = Boolean(data.won);
    this.status.setVisible(false);
    this.resultTitle.setVisible(true);
    this.resultBody.setVisible(true);
    this.lootLine.setVisible(true);
    this.backText.setVisible(true);
    this.backBox.setVisible(true);

    this.resultTitle.setText(won ? 'THE FIELD IS OURS' : 'THE LINE BREAKS');
    this.resultTitle.setColor(won ? CSS.gold : CSS.blood);

    const c = data.casualties || { standing: 0, fallen: 0 };
    const stars = data.stars || 0;
    const starLine = won ? '★'.repeat(Math.max(0, stars)) + '☆'.repeat(Math.max(0, 3 - stars)) : '';
    this.resultBody.setText([
      won ? 'Amalek is taken, and the spoil is ours.' : 'The band was beaten back, and none followed.',
      `${c.standing} still standing · ${c.fallen} fallen`,
      starLine,
    ].filter(Boolean).join('\n'));

    const rewards = data.rewards || {};
    const loot = Object.entries(rewards)
      .map(([k, v]) => `${k} ${formatNumber(v)}`)
      .join('   ·   ');
    this.lootLine.setText(won && loot ? `LOOT RECOVERED   ${loot}` : 'nothing was recovered');
    this.lootLine.setColor(won ? CSS.gold : CSS.bronze);

    // a flash of the result
    const flash = this.add.rectangle(this.scale.width / 2, this.scale.height / 2,
      this.scale.width, this.scale.height, won ? COLORS.gold : COLORS.blood, 0.35);
    this.tweens.add({ targets: flash, alpha: 0, duration: 700, onComplete: () => flash.destroy() });
  }

  goBack() {
    this.scene.start('Camp', {
      api: this.api,
      onLeave: this.onLeave,
      stageId: this.stageId,
    });
  }
}
