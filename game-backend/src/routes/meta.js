/**
 * routes/meta.js — what the client needs before it plays.
 *
 * The config tables are served, not compiled into the client, so the balance
 * can be retuned (the reference's `*_S10` season-override pattern) without
 * shipping a new build.
 */

import { catalogue, PUSHES } from '../rpc.js';
import {
  RESOURCES, YARDS, TOOLS, WORKS, ROSTER, SKILLS, COMPANIES, SKIRMISHES,
  TASKS, PHASE_ORDER, CONST, BOON, EXCHANGE, SEASON_OVERRIDES, slotMinutes, slotCost,
} from '../services/config.js';

export default [
  {
    method: 'get', path: '/api/meta/rpc', rpc: 'CMetaRpc', auth: false,
    handler: async () => ({ messages: catalogue(), count: catalogue().length }),
  },
  {
    method: 'get', path: '/api/meta/pushes', rpc: 'CMetaPushes', auth: false,
    handler: async () => ({ pushes: PUSHES }),
  },
  {
    method: 'get', path: '/api/meta/config', rpc: 'CMetaConfig', auth: false,
    handler: async () => ({
      version: '1.0.0',
      season: 10,
      resources: RESOURCES,
      yards: YARDS,
      tools: TOOLS,
      works: WORKS.map((w) => ({
        id: w.id, name: w.name, maxLevel: w.maxLevel,
        cost: w.cost(1), effect: w.effect(1),
      })),
      roster: ROSTER,
      skills: SKILLS,
      companies: COMPANIES.map((c) => ({
        id: c.id, name: c.name, line: c.line, max: c.max, cost: c.cost(1),
      })),
      skirmishes: SKIRMISHES.map((s) => ({
        id: s.id, name: s.name, power: s.power, rewards: s.rewards, renown: s.renown,
      })),
      tasks: TASKS,
      phases: PHASE_ORDER,
      exchange: EXCHANGE,
      constants: CONST,
      boon: BOON,
      season_overrides: SEASON_OVERRIDES,
      production: {
        minutes_per_slot: slotMinutes(false),
        minutes_per_slot_boosted: slotMinutes(true),
        cost_per_slot: slotCost(false),
        cost_per_slot_boosted: slotCost(true),
      },
    }),
  },
  {
    method: 'get', path: '/api/health', rpc: 'CHealth', auth: false,
    handler: async ({ store }) => ({
      status: 'ok',
      store: store.kind,
      time: new Date().toISOString(),
      version: '1.0.0',
    }),
  },
];
