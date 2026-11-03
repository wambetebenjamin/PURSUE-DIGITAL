/**
 * test/combat.test.js — the fight.
 *
 * The engine must be deterministic: the same seed, the same order of
 * battle and the same skill timings must always produce the same battle.
 * That is what lets the server replay a client's fight and own the result.
 */

import test from 'node:test';
import assert from 'node:assert/strict';

import { simulate, skirmishStars, pursuitStars, phaseReward } from '../src/services/combat.js';
import { SKIRMISHES } from '../src/services/config.js';
import { COMPANIES } from '../src/services/config.js';

function band(counts = {}) {
  return {
    companies: Object.fromEntries(COMPANIES.map((c) => [
      c.id, { count: counts[c.id] || 0, tier: counts[`${c.id}_tier`] || 1, wounded: 0 },
    ])),
  };
}

const heroes = [
  { id: 'david', name: 'David', role: 'blade', power: 12, skill: { name: 'Strengthen the band', kind: 'heal', power: 0.28, charge: 20 } },
  { id: 'abishai', name: 'Abishai', role: 'blade', power: 9, skill: { name: 'Rally the line', kind: 'rally', power: 0.55, charge: 24 } },
];

test('the same seed always gives the same battle', () => {
  const stage = SKIRMISHES[1];
  const troops = band({ spears: 30, shields: 20, bows: 20 });
  const a = simulate(stage, troops, { barracks: 3, seed: 4242, heroes });
  const b = simulate(stage, troops, { barracks: 3, seed: 4242, heroes });
  assert.deepEqual(a.losses, b.losses);
  assert.deepEqual(a.casualties, b.casualties);
  assert.equal(a.elapsed, b.elapsed);
  assert.equal(a.won, b.won);
});

test('a different seed gives a different battle', () => {
  const stage = SKIRMISHES[1];
  // A thin band, so the fight lasts long enough for the dice to matter.
  const troops = band({ spears: 6 });
  const a = simulate(stage, troops, { barracks: 1, seed: 1, heroes });
  const b = simulate(stage, troops, { barracks: 1, seed: 2, heroes });
  assert.notDeepEqual(a.log, b.log);
});

test('an empty camp loses', () => {
  const stage = SKIRMISHES[0];
  const result = simulate(stage, band(), { seed: 7, heroes });
  assert.equal(result.won, false);
  assert.equal(result.stars, undefined);
});

test('a strong band wins and the fallen are counted', () => {
  const stage = SKIRMISHES[0];
  const troops = band({ spears: 60, shields: 60, bows: 60, slings: 40, scouts: 40 });
  const result = simulate(stage, troops, { barracks: 5, seed: 99, heroes });
  assert.equal(result.won, true);
  assert.ok(result.casualties.standing > 0);
  assert.ok(result.log.length > 0);
});

test('more men win fights that fewer men lose', () => {
  const stage = SKIRMISHES[3];
  const few = simulate(stage, band({ spears: 10 }), { barracks: 1, seed: 77, heroes });
  const many = simulate(stage, band({ spears: 60, shields: 60, bows: 60 }), { barracks: 5, seed: 77, heroes });
  assert.equal(many.won, true);
  assert.ok(many.casualties.standing > few.casualties.standing,
    `${many.casualties.standing} should exceed ${few.casualties.standing}`);
  assert.ok(many.elapsed <= few.elapsed + 60);
});

test('stars reward a swift and untouched win', () => {
  assert.equal(skirmishStars({ won: false, losses: {}, elapsed: 10 }), 0);
  assert.equal(skirmishStars({ won: true, losses: { spears: 1 }, elapsed: 10 }), 2);
  assert.equal(skirmishStars({ won: true, losses: {}, elapsed: 10 }), 3);
  assert.equal(skirmishStars({ won: true, losses: {}, elapsed: 600 }), 2);
});

test('a phase is swift, silent and unseen', () => {
  assert.equal(pursuitStars({ time: 100, alerts: 0, stealth: false }), 3);
  assert.equal(pursuitStars({ time: 100, alerts: 2, stealth: false }), 2);
  assert.equal(pursuitStars({ time: 400, alerts: 2, stealth: false }), 1);
  assert.equal(pursuitStars({ time: 400, alerts: 5, stealth: true }), 2);
});

test('the phase reward grows with the stars', () => {
  const one = phaseReward('STATE_3_AMALEKITE_RESCUE', 1);
  const three = phaseReward('STATE_3_AMALEKITE_RESCUE', 3);
  assert.equal(one.spoil, 70);
  assert.equal(three.spoil, 140);
});
