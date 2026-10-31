/**
 * skirmish.js — the field fight, simulated.
 *
 * The reference game's stages come in a few shapes; two of them shape this
 * file. Its *field* stage runs itself: lines close, troops trade, and the
 * player mostly watches. Its *dungeon* stage hands the player three heroes and
 * a skill gauge to fire by hand. This is both at once — a real-time skirmish
 * where the companies fight on their own and the player's hand is on the
 * heroes' skills.
 *
 * Everything is plain arithmetic on plain objects: no DOM, no THREE, no
 * timers. `tick(dt)` advances the fight, so the smoke suite can run a whole
 * battle in a loop without a renderer.
 *
 * Twist on the reference: no mana potions, no pay-to-win multipliers. The
 * gauge fills with time, and the skills are the ones 2 Samuel 23 remembers.
 */
import { ROSTER, isUnlocked, progression } from './progression.js';
import { COMPANIES, orderOfBattle, musterPower, muster } from './muster.js';

/* ------------------------------------------------------------- the stages */

/**
 * Skirmishes. Each is a series of waves — the reference's stage waves — drawn
 * from the towns and roads of 1 Samuel 30.
 * `power` is the muster strength the fight is tuned for; below it you will be
 * beaten, above it you will win with fewer wounded.
 */
export const SKIRMISHES = Object.freeze([
  {
    id: 'besor-watch',
    name: 'The Watch at Besor',
    where: 'The brook, where the two hundred stayed',
    brief: 'A raiding party is feeling for the baggage. Six of them, and no more.',
    power: 60,
    rewards: { provisions: 45, timber: 20 },
    renown: 8,
    waves: [
      [{ key: 'riders', name: 'Amalekite riders', count: 6, hp: 13, atk: 5, def: 2 }],
    ],
  },
  {
    id: 'ford',
    name: 'The Ford by Night',
    where: 'South of the Besor',
    brief: 'They cross in the dark, in two parties, hoping to be quiet.',
    power: 120,
    rewards: { provisions: 55, bronze: 35 },
    renown: 12,
    waves: [
      [{ key: 'raiders', name: 'Amalekite raiders', count: 8, hp: 14, atk: 6, def: 3 }],
      [{ key: 'raiders', name: 'Amalekite raiders', count: 6, hp: 16, atk: 7, def: 3 },
        { key: 'archers', name: 'Desert archers', count: 4, hp: 10, atk: 9, def: 1 }],
    ],
  },
  {
    id: 'outpost',
    name: 'The Outpost on the Escarpment',
    where: 'The ridge above the camp',
    brief: 'A watch of spears and a captain who has done this before.',
    power: 200,
    rewards: { timber: 60, bronze: 50 },
    renown: 16,
    waves: [
      [{ key: 'spears', name: 'Amalekite spears', count: 10, hp: 16, atk: 7, def: 5 },
        { key: 'archers', name: 'Desert archers', count: 5, hp: 11, atk: 9, def: 2 }],
      [{ key: 'captain', name: 'The captain of the watch', count: 1, hp: 140, atk: 18, def: 10 },
        { key: 'spears', name: 'Amalekite spears', count: 8, hp: 16, atk: 7, def: 5 }],
    ],
  },
  {
    id: 'perimeter',
    name: "The Camp Perimeter",
    where: 'The Amalekite camp, scattered and eating',
    brief: 'They are feasting on what they took. Break the perimeter and they break.',
    power: 300,
    rewards: { spoil: 90, bronze: 55 },
    renown: 22,
    waves: [
      [{ key: 'revellers', name: 'Revellers with blades', count: 14, hp: 14, atk: 6, def: 2 }],
      [{ key: 'guards', name: 'Camp guards', count: 12, hp: 18, atk: 8, def: 5 },
        { key: 'archers', name: 'Desert archers', count: 6, hp: 12, atk: 10, def: 2 }],
      [{ key: 'champion', name: 'The champion of the camp', count: 1, hp: 240, atk: 24, def: 14 },
        { key: 'guards', name: 'Camp guards', count: 10, hp: 18, atk: 8, def: 5 }],
    ],
  },
  {
    id: 'long-night',
    name: 'The Long Night',
    where: 'Twilight to twilight, as the scripture has it',
    brief: 'The fight that lasts a day and a night. Only a strong band comes out of it.',
    power: 430,
    rewards: { spoil: 130, bronze: 80 },
    renown: 30,
    waves: [
      [{ key: 'raiders', name: 'Amalekite raiders', count: 16, hp: 18, atk: 9, def: 4 }],
      [{ key: 'spears', name: 'Amalekite spears', count: 14, hp: 20, atk: 10, def: 6 },
        { key: 'archers', name: 'Desert archers', count: 8, hp: 14, atk: 12, def: 3 }],
      [{ key: 'champion', name: 'The prince of Amalek', count: 1, hp: 320, atk: 28, def: 18 },
        { key: 'guards', name: 'Sworn guards', count: 14, hp: 22, atk: 11, def: 7 }],
    ],
  },
  {
    id: 'gilboa-slope',
    name: 'The Slope of Gilboa',
    where: 'Where the kingdom fell',
    brief: 'Philistine chariotry on the open slope. The last fight of the pursuit.',
    power: 560,
    rewards: { spoil: 160, bronze: 100, timber: 40 },
    renown: 40,
    waves: [
      [{ key: 'chariots', name: 'Chariot runners', count: 12, hp: 26, atk: 13, def: 6 },
        { key: 'archers', name: 'Philistine archers', count: 10, hp: 15, atk: 13, def: 3 }],
      [{ key: 'chariots', name: 'Chariot runners', count: 14, hp: 28, atk: 14, def: 7 },
        { key: 'spears', name: 'Philistine spears', count: 12, hp: 24, atk: 12, def: 8 }],
      [{ key: 'prince', name: 'A Philistine prince', count: 1, hp: 420, atk: 32, def: 22 },
        { key: 'guards', name: 'Sworn guards', count: 16, hp: 24, atk: 12, def: 8 }],
    ],
  },
]);

/* -------------------------------------------------------------- the skills */

/**
 * One skill per mighty man, in the register of 2 Samuel 23.
 * `kind` is what the engine does with it; `power` how hard it bites.
 */
export const SKILLS = Object.freeze({
  david: { name: 'Strengthen the band', kind: 'heal', power: 0.28, charge: 20, note: 'He strengthened himself in the LORD, and the band with him.' },
  abishai: { name: 'Rally the line', kind: 'rally', power: 0.55, charge: 24, note: 'Chief among the thirty — the line answers him.' },
  eleazar: { name: 'Stand fast', kind: 'shield', power: 0.30, charge: 26, note: 'He arose and smote until his hand was weary.' },
  shammah: { name: 'Arrow storm', kind: 'burst', power: 2.6, charge: 22, note: 'He stood in the midst of the ground and defended it.' },
  benaiah: { name: 'The lion’s blow', kind: 'burst', power: 3.4, charge: 28, note: 'He slew two lionlike men of Moab.' },
  ittai: { name: 'Hold the ford', kind: 'weaken', power: 0.3, charge: 26, note: 'Come and pass over with me — and he held.' },
});

/** At most this many mighty men stand in a single fight (the reference's three, plus one). */
export const FIELD_HEROES = 4;

/** Which mighty men are with the band, in roster order. */
export function fieldHeroes(s = progression.get()) {
  return ROSTER.filter((m) => isUnlocked(m.id, s)).slice(0, FIELD_HEROES).map((m) => ({
    id: m.id,
    name: m.name,
    role: m.role,
    power: m.power,
    skill: SKILLS[m.id] || { name: 'Stand', kind: 'rally', power: 0.3, charge: 26, note: '' },
  }));
}

/* ------------------------------------------------------------- the engine */

function rng(seed) {
  let x = seed >>> 0 || 1;
  return () => {
    x ^= x << 13; x >>>= 0;
    x ^= x >> 17;
    x ^= x << 5; x >>>= 0;
    return x / 4294967296;
  };
}

function sum(groups, key) {
  return groups.reduce((n, g) => n + (g.hp > 0 ? g[key] : 0), 0);
}

export class Skirmish {
  /**
   * @param {object} stage one of SKIRMISHES
   * @param {{barracks?: number, seed?: number, heroes?: Array}} [opts]
   */
  constructor(stage, opts = {}) {
    this.stage = stage;
    this.seed = opts.seed || 0x5eed;
    this.random = rng(this.seed);
    this.barracks = opts.barracks || 1;
    this.time = 0;
    this.wave = 0;
    this.status = 'fighting';
    this.log = [];
    this.speed = 1;
    this._since = 0;
    this._rng = this.random;

    // --- our line
    this.line = orderOfBattle(muster.get(), this.barracks).map((c) => {
      const def = COMPANIES.find((x) => x.id === c.id);
      return {
        id: c.id,
        name: c.name,
        glyph: c.glyph,
        line: c.line,
        men: c.count,
        maxHp: Math.max(1, c.maxHp),
        hp: Math.max(1, c.maxHp),
        atk: c.atk,
        def: c.def,
      };
    });
    for (const g of this.line) g.hpPerMan = g.maxHp / Math.max(1, g.men);

    // --- the heroes and their gauges
    this.heroes = (opts.heroes || fieldHeroes()).map((h) => ({
      ...h,
      gauge: 35,
      ready: false,
    }));
    this.buffs = { atk: 0, shield: 0, weaken: 0 };

    // --- the enemy
    this.enemy = [];
    this._loadWave(0);
    this.push(`The band closes with ${stage.name.toLowerCase()}.`);
  }

  /* ------------------------------------------------------------- helpers */

  push(line) {
    this.log.push({ t: Math.round(this.time), line });
    if (this.log.length > 40) this.log.shift();
  }

  _loadWave(i) {
    const wave = this.stage.waves[i] || [];
    this.enemy = wave.map((g) => ({
      id: g.key,
      name: g.name,
      men: g.count,
      maxHp: Math.max(1, Math.round(g.count * g.hp)),
      hp: Math.max(1, Math.round(g.count * g.hp)),
      atk: Math.round(g.count * g.atk),
      def: Math.round(g.count * g.def),
      hpPerMan: g.hp,
    }));
    this.wave = i;
    if (i > 0) this.push(`A second party comes on: ${wave.map((g) => g.name).join(' and ')}.`);
  }

  get ours() {
    return this.line.filter((g) => g.hp > 0);
  }

  get theirs() {
    return this.enemy.filter((g) => g.hp > 0);
  }

  /** Men still standing, and how many fell. */
  get casualties() {
    let standing = 0;
    let fallen = 0;
    for (const g of this.line) {
      const left = g.hp > 0 ? Math.max(0, Math.round(g.hp / g.hpPerMan)) : 0;
      standing += left;
      fallen += Math.max(0, g.men - left);
    }
    return { standing, fallen };
  }

  /** Per-company losses, in men — what `muster.applyLosses` wants. */
  losses() {
    const out = {};
    for (const g of this.line) {
      const left = g.hp > 0 ? Math.max(0, Math.round(g.hp / g.hpPerMan)) : 0;
      const dead = Math.max(0, g.men - left);
      if (dead > 0) out[g.id] = dead;
    }
    return out;
  }

  /* ---------------------------------------------------------------- wire */

  /**
   * Give the side orders. `dt` is real seconds; `speed` scales the fight.
   * Small steps are accumulated so a 5-second frame does not skip a beat.
   */
  tick(dt) {
    if (this.status !== 'fighting') return this.status;
    const step = Math.min(0.2, Math.max(0, dt) * (this.speed || 1));
    this._since += step;
    while (this._since >= 0.1) {
      this._beat(0.1);
      this._since -= 0.1;
      if (this.status !== 'fighting') break;
    }
    return this.status;
  }

  _beat(dt) {
    this.time += dt;

    // --- hero gauges fill whether or not the player is looking
    for (const h of this.heroes) {
      if (h.gauge < 100) {
        h.gauge = Math.min(100, h.gauge + (100 / h.skill.charge) * dt);
      }
      h.ready = h.gauge >= 100;
    }
    // --- buffs run down
    for (const k of Object.keys(this.buffs)) {
      if (this.buffs[k] > 0) this.buffs[k] = Math.max(0, this.buffs[k] - dt);
    }
    if (this.buffs.atk === 0) this._rallyMul = 1;

    const ours = this.ours;
    const theirs = this.theirs;
    if (ours.length === 0) return this._end(false);
    if (theirs.length === 0) {
      // clear the wave: bring the next one on, or win
      if (this.wave + 1 < this.stage.waves.length) {
        this._loadWave(this.wave + 1);
        return this.status;
      }
      return this._end(true);
    }

    // --- our blow
    const front = ours.filter((g) => g.line === 'front');
    const back = ours.filter((g) => g.line === 'back');
    const heroBonus = 1 + this.heroes.reduce((n, h) => n + h.power * 0.02, 0);
    const rally = this.buffs.atk > 0 ? 1.55 : 1;
    const ourAtk = (sum(front, 'atk') + sum(back, 'atk') * 0.6) * heroBonus * rally;
    this._strike(this.enemy, ourAtk * dt, true);

    // --- their blow: the wall takes it, unless the wall is down
    const weaken = this.buffs.weaken > 0 ? 0.72 : 1;
    const theirAtk = (sum(theirs, 'atk') * weaken) * (1 - this.buffs.shield * 0.35);
    if (front.length > 0) {
      this._strike(front, theirAtk * dt * 0.72, false);
      this._strike(back, theirAtk * dt * 0.18, false);
    } else {
      this._strike(ours, theirAtk * dt, false);
    }
  }

  /** Apply damage to a side, front groups first. */
  _strike(groups, amount, isOurs) {
    if (!(amount > 0)) return;
    const targets = groups.filter((g) => g.hp > 0).sort((a, b) => (a.line === 'front' ? -1 : 1) - (b.line === 'front' ? -1 : 1));
    const size = targets.length;
    if (!size) return;
    for (const g of targets) {
      const mitigation = g.def / (g.def + 90);
      const share = amount / size;
      const dealt = share * (1 - mitigation) * (0.85 + this._rng() * 0.3);
      g.hp = Math.max(0, g.hp - dealt);
      // front groups eat more of the blow
      if (isOurs && g.line === 'front' && size > 1) g.hp = Math.max(0, g.hp - dealt * 0.35);
    }
  }

  /** Fire a hero's skill by hand. Returns true if it took. */
  useSkill(id) {
    const h = this.heroes.find((x) => x.id === id);
    if (!h || h.gauge < 100 || this.status !== 'fighting') return false;
    h.gauge = 0;
    h.ready = false;
    const s = h.skill;
    const ours = this.ours;
    const total = ours.reduce((n, g) => n + g.hp, 0) || 0;
    const cap = ours.reduce((n, g) => n + g.maxHp, 0) || 1;

    if (s.kind === 'heal') {
      for (const g of ours) g.hp = Math.min(g.maxHp, g.hp + g.maxHp * s.power);
      this.push(`${h.name}: ${s.name} — the wounded rise.`);
    } else if (s.kind === 'rally') {
      this.buffs.atk = 8;
      this.push(`${h.name}: ${s.name} — the line presses forward.`);
    } else if (s.kind === 'shield') {
      this.buffs.shield = 8;
      for (const g of ours) g.hp = Math.min(g.maxHp, g.hp + total * s.power * 0.1);
      this.push(`${h.name}: ${s.name} — shields lock.`);
    } else if (s.kind === 'burst') {
      const blow = cap * s.power * 0.06;
      this._strike(this.enemy, blow, true);
      this.push(`${h.name}: ${s.name} — ${Math.round(blow)} struck home.`);
    } else if (s.kind === 'weaken') {
      this.buffs.weaken = 8;
      this.push(`${h.name}: ${s.name} — the enemy foot slows.`);
    }
    return true;
  }

  _end(won) {
    this.status = won ? 'won' : 'lost';
    const c = this.casualties;
    this.push(won
      ? `The field is ours. ${c.standing} still standing, ${c.fallen} fallen.`
      : `The line breaks. ${c.fallen} fallen, the rest scattered.`);
    return this.status;
  }
}

/* ------------------------------------------------------------- the payout */

/**
 * Run a skirmish to its end and settle it into the stores.
 * Used by the UI's "fight it out" and by the smoke suite.
 */
export function settleSkirmish(stage, opts = {}) {
  const bout = new Skirmish(stage, opts);
  let guard = 0;
  while (bout.status === 'fighting' && guard < 60000) {
    bout.tick(0.2);
    // the band fires whatever is ready — this is the auto-battle mode
    for (const h of bout.heroes) if (h.ready) bout.useSkill(h.id);
    guard += 1;
  }
  const won = bout.status === 'won';
  const rewards = won ? { ...stage.rewards } : {};
  if (won) {
    progression.grant(rewards);
    progression.addRenown(stage.renown || 0);
    muster.recordWin(stage.id);
  } else {
    muster.recordLoss();
  }
  muster.applyLosses(bout.losses());
  return { won, rewards, bout, power: musterPower(muster.get(), opts.barracks || 1).power };
}

