/**
 * test/economy.test.js — the production loop.
 *
 * The rules under test are the reference's (doc 05):
 *   - the offline cap is a server constant
 *   - the client never invents a number: query, then commit
 *   - the boon makes a slot finish sooner and cost more
 */

import test from 'node:test';
import assert from 'node:assert/strict';

import {
  pendingGain, gainQuery, gainCollect, produce, collectSlots, finishedSlots,
  upgrade, upgradeCost, applyExchange, exchange, productionRate,
} from '../src/services/economy.js';
import { CONST, slotMinutes, slotCost, BOON } from '../src/services/config.js';

const works = { hall: 1, barracks: 1, cistern: 1, wall: 1, threshing: 1, quarry: 1, smelter: 1, hospital: 1 };
const HOUR = 3600000;

test('production rate is the sum of the works that make something', () => {
  const rate = productionRate(works);
  assert.equal(rate.provisions, 24);
  assert.equal(rate.timber, 18);
  assert.equal(rate.bronze, 14);
  assert.equal(rate.spoil, undefined);
});

test('pending gain accrues by the hour and stops at the offline cap', () => {
  const now = 1_700_000_000_000;
  const twoHours = pendingGain(works, now - 2 * HOUR, now);
  assert.equal(twoHours.resources.provisions, 48);
  assert.equal(twoHours.resources.timber, 36);
  assert.equal(twoHours.capped, false);

  const week = pendingGain(works, now - 7 * 24 * HOUR, now);
  assert.equal(week.capped, true);
  assert.equal(week.hours, CONST.OFFLINE_CAP_HOURS);
  assert.equal(week.resources.provisions, 24 * CONST.OFFLINE_CAP_HOURS);
});

test('the gain query tells the player what is owed without paying it', () => {
  const now = 1_700_000_000_000;
  const encampment = {
    resources: { provisions: 0, bronze: 0, timber: 0, spoil: 0 },
    works, production: {}, last_collect_at: now - HOUR,
  };
  const q = gainQuery(encampment, now);
  assert.equal(q.can_gain, true);
  assert.equal(q.resources.provisions, 24);
  assert.equal(encampment.resources.provisions, 0, 'a query must not pay');

  const c = gainCollect(encampment, now);
  assert.equal(c.given.provisions, 24);
  assert.equal(encampment.resources.provisions, 24, 'a commit must pay');
  assert.equal(encampment.last_collect_at, now);
});

test('collecting twice in a row does not pay twice', () => {
  const now = 1_700_000_000_000;
  const encampment = {
    resources: { provisions: 0, bronze: 0, timber: 0, spoil: 0 },
    works, production: {}, last_collect_at: now - HOUR,
  };
  gainCollect(encampment, now);
  const second = gainCollect(encampment, now + 1000);
  assert.deepEqual(second.given, {});
});

test('the boon shortens the slot and raises its price', () => {
  const plain = slotMinutes(false);
  const boosted = slotMinutes(true);
  assert.equal(plain, CONST.PRODUCTION_MINUTES);
  assert.equal(boosted, Math.max(1, Math.round(plain / (1 + BOON.speedUpPercent / 100))));
  assert.ok(boosted < plain, 'the boon must save time');
  assert.ok(slotCost(true) > slotCost(false), 'the boon must cost more');
});

test('a queued work ripens on the server clock, not the client clock', () => {
  const now = 1_700_000_000_000;
  const encampment = {
    resources: { provisions: 1000, bronze: 0, timber: 0, spoil: 0 },
    works, production: {}, last_collect_at: now, boon_active: false,
  };
  const order = produce(encampment, 'threshing', 2, now);
  assert.ok(order, 'the order must be accepted');
  assert.equal(order.slots, 2);
  assert.equal(encampment.resources.provisions, 1000 - slotCost(false) * 2);

  assert.equal(finishedSlots(encampment, 'threshing', now), 0);
  const later = now + 61 * 60 * 1000;
  assert.equal(finishedSlots(encampment, 'threshing', later), 1);
  assert.equal(finishedSlots(encampment, 'threshing', later + 61 * 60 * 1000), 2);

  const take = collectSlots(encampment, 'threshing', later + 61 * 60 * 1000);
  assert.equal(take.slots_taken, 2);
  assert.equal(take.given.provisions, 48);   // two slots of 24 provisions
  assert.equal(encampment.resources.provisions, 1000 - slotCost(false) * 2 + 48);
});

test('a work cannot be queued past the slot ceiling', () => {
  const now = 1_700_000_000_000;
  const encampment = {
    resources: { provisions: 100000, bronze: 0, timber: 0, spoil: 0 },
    works, production: {}, last_collect_at: now, boon_active: false,
  };
  assert.ok(produce(encampment, 'threshing', CONST.PRODUCTION_NUM_MAX, now));
  assert.equal(produce(encampment, 'threshing', 1, now), null);
});

test('an upgrade costs what the table says, and stops at the ceiling', () => {
  const encampment = {
    resources: { provisions: 1000, bronze: 1000, timber: 1000, spoil: 0 },
    works: { ...works }, production: {}, last_collect_at: Date.now(),
  };
  const cost = upgradeCost('threshing', encampment.works);
  assert.deepEqual(cost, { timber: 35 + 28, provisions: 20 + 18 });
  const done = upgrade(encampment, 'threshing');
  assert.equal(done.level, 2);
  assert.equal(encampment.resources.timber, 1000 - cost.timber);

  encampment.works.threshing = 5;
  assert.equal(upgradeCost('threshing', encampment.works), null);
  assert.equal(upgrade(encampment, 'threshing'), null);
});

test('an upgrade the stores cannot pay for is refused', () => {
  const encampment = {
    resources: { provisions: 0, bronze: 0, timber: 0, spoil: 0 },
    works: { ...works }, production: {}, last_collect_at: Date.now(),
  };
  assert.equal(upgrade(encampment, 'threshing'), null);
  assert.equal(encampment.works.threshing, 1);
});

test('the exchange table follows the reference rates', () => {
  const plan = exchange('provisions', 'bronze', 10);
  assert.equal(plan.get, 10);
  assert.equal(plan.rate, 1);
  assert.equal(exchange('spoil', 'provisions', 3).get, 9);
  assert.equal(exchange('timber', 'gold', 1), null);
});

test('an exchange the stores cannot pay for is refused', () => {
  const encampment = { resources: { provisions: 2, bronze: 0, timber: 0, spoil: 0 } };
  assert.equal(applyExchange(encampment, 'provisions', 'bronze', 10), null);
  assert.equal(encampment.resources.provisions, 2);
  const done = applyExchange(encampment, 'provisions', 'bronze', 2);
  assert.equal(done.get, 2);
  assert.equal(encampment.resources.bronze, 2);
  assert.equal(encampment.resources.provisions, 0);
});
