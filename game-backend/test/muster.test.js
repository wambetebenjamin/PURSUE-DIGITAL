/**
 * test/muster.test.js — the band of six hundred.
 */

import test from 'node:test';
import assert from 'node:assert/strict';

import {
  recruit, train, heal, applyLosses, companyCap, companyStats, orderOfBattle,
  musterPower, bandPower, drill, isUnlocked,
} from '../src/services/muster.js';
import { STEP, MAX_TIER, COMPANIES } from '../src/services/config.js';

function camp(resources = {}) {
  return {
    resources: { provisions: 10000, bronze: 10000, timber: 10000, spoil: 0, ...resources },
    works: { barracks: 1, hospital: 1 },
    roster: { david: { level: 1 } },
  };
}

function band() {
  return {
    companies: Object.fromEntries(COMPANIES.map((c) => [c.id, { count: 0, tier: 1, wounded: 0 }])),
  };
}

test('the barracks gates how large a company may grow', () => {
  assert.equal(companyCap('spears', 1), Math.round(70 * 0.4));
  assert.equal(companyCap('spears', 5), 70);
  assert.equal(companyCap('scouts', 1), Math.round(40 * 0.4));
});

test('recruiting buys whole presses of ten and never exceeds the cap', () => {
  const troops = band();
  const enc = camp();
  const done = recruit(troops, enc, 'spears', 1, 1);
  assert.equal(done.count, STEP);
  assert.equal(enc.resources.bronze, 10000 - 26);
  assert.equal(enc.resources.provisions, 10000 - 12);

  // the cap is 28 at barracks 1: two presses fit, the third does not
  recruit(troops, enc, 'spears', 1, 1);
  assert.equal(troops.companies.spears.count, 20);
  recruit(troops, enc, 'spears', 5, 1);
  assert.equal(troops.companies.spears.count, 28);
  assert.equal(recruit(troops, enc, 'spears', 1, 1), null);
});

test('recruitment the stores cannot pay for is refused', () => {
  const troops = band();
  const enc = camp({ bronze: 0, provisions: 0 });
  assert.equal(recruit(troops, enc, 'spears', 1, 1), null);
  assert.equal(troops.companies.spears.count, 0);
});

test('training raises the tier only as far as the barracks allows', () => {
  const troops = band();
  const enc = camp();
  // The gate is (barracks + 1): a barracks of 1 drills a company to tier 2.
  const done = train(troops, enc, 'spears', 1);
  assert.equal(done.tier, 2);
  assert.equal(enc.resources.bronze, 10000 - 105);
  assert.equal(train(troops, enc, 'spears', 1), null, 'and no further at that barracks');
  assert.equal(train(troops, enc, 'spears', 3).tier, 3, 'a higher barracks drills further');
});

test('a tier makes the company harder to kill', () => {
  const troops = band();
  troops.companies.spears.count = 20;
  const before = companyStats('spears', troops, 5);
  train(troops, camp(), 'spears', 3);
  const after = companyStats('spears', troops, 5);
  assert.ok(after.atk > before.atk);
  assert.ok(after.maxHp > before.maxHp);
});

test('the wounded come back to the line for provisions, at a discount', () => {
  const troops = band();
  troops.companies.spears.count = 10;
  troops.companies.spears.wounded = 5;
  const enc = camp();
  const done = heal(troops, enc, 'spears', 3, 1);
  assert.equal(done.healed, 3);
  assert.equal(troops.companies.spears.wounded, 2);
  assert.equal(troops.companies.spears.count, 13);
  assert.equal(enc.resources.provisions, 10000 - 9);
});

test('the tent of the wounded discounts the tending', () => {
  const troops = band();
  troops.companies.bows.wounded = 10;
  const plain = camp();
  heal(troops, plain, 'bows', 10, 1);
  assert.equal(plain.resources.provisions, 10000 - 30);

  const troops2 = band();
  troops2.companies.bows.wounded = 10;
  const tended = camp();
  heal(troops2, tended, 'bows', 10, 5);
  assert.ok(tended.resources.provisions > 10000 - 30, 'the hospital must cost less');
});

test('seven in ten of the fallen are wounded, not dead', () => {
  const troops = band();
  troops.companies.spears.count = 20;
  const taken = applyLosses(troops, { spears: 10 });
  assert.equal(taken.spears, 10);
  assert.equal(troops.companies.spears.count, 10);
  assert.equal(troops.companies.spears.wounded, 7);
});

test('the order of battle puts the front line first', () => {
  const troops = band();
  troops.companies.bows.count = 20;
  troops.companies.spears.count = 20;
  const oob = orderOfBattle(troops, 5);
  assert.equal(oob[0].id, 'spears');
  assert.equal(oob[oob.length - 1].id, 'bows');
});

test('muster power grows with the men and with the drill', () => {
  const troops = band();
  const empty = musterPower(troops, 1);
  troops.companies.spears.count = 30;
  const some = musterPower(troops, 1);
  const drilled = musterPower(troops, 5);
  assert.ok(some.power > empty.power);
  assert.ok(drilled.power > some.power);
  assert.equal(some.men, 30);
});

test('the mighty men unlock with the stars, and can be drilled', () => {
  assert.equal(isUnlocked('david', 0), true);
  assert.equal(isUnlocked('benaiah', 0), false);
  assert.equal(isUnlocked('benaiah', 5), true);

  const enc = camp();
  enc.roster = { benaiah: { level: 1 } };
  const done = drill(enc, enc.roster, 'benaiah', 5);
  assert.equal(done.level, 2);
  assert.equal(drill(enc, enc.roster, 'eleazar', 0), null, 'locked men cannot be drilled');
});

test('band power counts the men and the level of the barracks', () => {
  const roster = { david: { level: 1 }, abishai: { level: 1 } };
  const low = bandPower(roster, 1, 0);
  const high = bandPower(roster, 5, 0);
  assert.ok(high > low);
});
