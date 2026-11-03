/**
 * routes/camp.js — the camp at Ziklag.
 *
 * The reference's farm module, retold. The shape is preserved exactly:
 *
 *   CGetFarmersWorldDetail  → CGetEncampmentDetail   (open it, get it all)
 *   CFarmersWorldGainQuery  → CEncampmentGainQuery   (what is ready?)
 *   CFarmersWorldGainOutput → CEncampmentGainOutput  (take it)
 *   CFarmersWorldExchange   → CEncampmentExchange     (the exchange table)
 *   CGloryFactoryProcess    → CWorkProduce            (queue slots)
 *   CGloryFactoryCollect    → CWorkCollect            (take finished slots)
 *
 * The client never mutates a resource. It asks, and the server answers.
 */

import { CODE } from '../envelope.js';
import { GameError } from '../auth.js';
import { loadState, saveState, campView } from '../services/state.js';
import {
  gainQuery, gainCollect, upgrade, upgradeCost, produce, produceCost,
  collectSlots, finishedSlots, applyExchange, exchange, workState,
  allFinishAt, nextFinishAt, productionRate,
} from '../services/economy.js';
import { slotMinutes, slotCost, CONST, WORKS, WORK_IDS } from '../services/config.js';

export default [
  {
    method: 'get', path: '/api/camp/detail', rpc: 'CGetEncampmentDetail', auth: true,
    handler: async ({ player, store, now }) => {
      const { encampment, troops } = await loadState(store, player);
      const gain = gainQuery(encampment, now);
      await saveState(store, { encampment, troops });
      return {
        camp: campView(encampment, troops, gain),
        rate: productionRate(encampment.works),
        next_finish_at: nextFinishAt(encampment, now),
        all_finish_at: allFinishAt(encampment, now),
      };
    },
  },
  {
    method: 'post', path: '/api/camp/upgrade', rpc: 'CEncampmentUpgrade', auth: true,
    handler: async ({ body, player, store, now }) => {
      const workId = body.work_id || body.building_id;
      if (!WORK_IDS.includes(workId)) throw new GameError(CODE.PARAM_ERROR, 'unknown work');
      const { encampment, troops } = await loadState(store, player);
      const cost = upgradeCost(workId, encampment.works);
      if (!cost) throw new GameError(CODE.PARAM_ERROR, 'that work is at its ceiling');
      const done = upgrade(encampment, workId);
      if (!done) throw new GameError(CODE.PARAM_ERROR, 'not enough in the stores');
      await saveState(store, { encampment, troops });
      return { ...done, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'get', path: '/api/camp/gain/query', rpc: 'CEncampmentGainQuery', auth: true,
    handler: async ({ player, store, now }) => {
      const { encampment, troops } = await loadState(store, player);
      const gain = gainQuery(encampment, now);
      await saveState(store, { encampment, troops });
      return gain;
    },
  },
  {
    method: 'post', path: '/api/camp/gain/collect', rpc: 'CEncampmentGainOutput', auth: true,
    handler: async ({ player, store, now }) => {
      const { encampment, troops } = await loadState(store, player);
      const result = gainCollect(encampment, now);
      if (result.given && Object.keys(result.given).length) {
        await store.addLoot({
          player_id: player.role_id,
          pursuit_id: null,
          kind: 'harvest',
          source: 'the works of Ziklag',
          items: result.given,
          claimed: true,
        });
      }
      await saveState(store, { encampment, troops });
      return { ...result, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'post', path: '/api/camp/produce', rpc: 'CWorkProduce', auth: true,
    handler: async ({ body, player, store, now }) => {
      const workId = body.work_id;
      if (!WORK_IDS.includes(workId)) throw new GameError(CODE.PARAM_ERROR, 'unknown work');
      const slots = Number(body.slots) || 1;
      const { encampment, troops } = await loadState(store, player);
      const done = produce(encampment, workId, slots, now);
      if (!done) throw new GameError(CODE.PARAM_ERROR, 'the order cannot be accepted');
      await saveState(store, { encampment, troops });
      return { ...done, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'post', path: '/api/camp/produce/collect', rpc: 'CWorkCollect', auth: true,
    handler: async ({ body, player, store, now }) => {
      const workId = body.work_id;
      if (!WORK_IDS.includes(workId)) throw new GameError(CODE.PARAM_ERROR, 'unknown work');
      const { encampment, troops } = await loadState(store, player);
      const done = collectSlots(encampment, workId, now);
      if (!done) throw new GameError(CODE.PARAM_ERROR, 'nothing has ripened');
      await store.addLoot({
        player_id: player.role_id,
        pursuit_id: null,
        kind: 'harvest',
        source: workId,
        items: done.given,
        claimed: true,
      });
      await saveState(store, { encampment, troops });
      return { ...done, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'get', path: '/api/camp/work', rpc: 'CGetWorkInfo', auth: true,
    handler: async ({ query, player, store, now }) => {
      const { encampment, troops } = await loadState(store, player);
      const boon = Boolean(encampment.boon_active);
      const works = WORKS.map((w) => {
        const q = workState(encampment, w.id);
        const level = encampment.works[w.id] || 1;
        return {
          work_id: w.id,
          name: w.name,
          level,
          max_level: w.maxLevel,
          effect: w.effect(level),
          yield: w.yield(level) || {},
          slots: q.slots || 0,
          finished: finishedSlots(encampment, w.id, now),
          next_finish_at: q.next_finish_at ? new Date(q.next_finish_at).toISOString() : null,
          minutes_per_slot: slotMinutes(boon),
          cost_per_slot: slotCost(boon),
          cost_raw: CONST.PRODUCTION_SPEND,
          cost_boosted: slotCost(boon),
          speed_up_percent: boon ? 20 : 0,
        };
      });
      await saveState(store, { encampment, troops });
      return {
        works,
        boon: {
          active: boon,
          speed_up_percent: boon ? 20 : 0,
          cost_premium_percent: boon ? 20 : 0,
        },
        next_finish_at: nextFinishAt(encampment, now),
        all_finish_at: allFinishAt(encampment, now),
      };
    },
  },
  {
    method: 'post', path: '/api/camp/exchange', rpc: 'CEncampmentExchange', auth: true,
    handler: async ({ body, player, store }) => {
      const { from, to } = body;
      const amount = Number(body.amount) || 1;
      const { encampment, troops } = await loadState(store, player);
      const plan = applyExchange(encampment, from, to, amount);
      if (!plan) throw new GameError(CODE.PARAM_ERROR, 'that exchange cannot be made');
      await saveState(store, { encampment, troops });
      return { ...plan, resources: { ...encampment.resources } };
    },
  },
  {
    method: 'post', path: '/api/camp/exchange/resource', rpc: 'CEncampmentExchangeResource', auth: true,
    handler: async ({ body, player, store }) => {
      const { from, to } = body;
      const amount = Number(body.amount) || 1;
      const plan = exchange(from, to, amount);
      if (!plan) throw new GameError(CODE.PARAM_ERROR, 'no such exchange');
      const { encampment, troops } = await loadState(store, player);
      const done = applyExchange(encampment, from, to, amount);
      if (!done) throw new GameError(CODE.PARAM_ERROR, 'not enough in the stores');
      await saveState(store, { encampment, troops });
      return { ...done, resources: { ...encampment.resources } };
    },
  },
];
