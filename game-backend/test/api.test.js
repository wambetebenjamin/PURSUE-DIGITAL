/**
 * test/api.test.js — the server, end to end.
 *
 * The envelope, the session, the Query/Commit split and the RPC dispatcher,
 * exercised over real HTTP against the memory store.
 */

import test from 'node:test';
import assert from 'node:assert/strict';

import { createApp } from '../src/app.js';
import { MemoryStore } from '../src/store/memory.js';

async function withServer(fn) {
  const store = new MemoryStore();
  await store.connect();
  const app = createApp(store);
  const server = app.listen(0, '127.0.0.1');
  await new Promise((resolve) => server.once('listening', resolve));
  const base = `http://127.0.0.1:${server.address().port}`;
  try {
    await fn({ base, store });
  } finally {
    await new Promise((resolve) => server.close(resolve));
  }
}

async function post(base, path, body, token) {
  const res = await fetch(`${base}${path}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
    },
    body: JSON.stringify(body || {}),
  });
  return res.json();
}

async function get(base, path, token) {
  const res = await fetch(`${base}${path}`, {
    headers: token ? { Authorization: `Bearer ${token}` } : {},
  });
  return res.json();
}

test('health answers in the SDK envelope', async () => {
  await withServer(async ({ base }) => {
    const body = await get(base, '/api/health');
    assert.equal(body.code, 0);
    assert.equal(body.message, 'ok');
    assert.equal(body.data.status, 'ok');
    assert.equal(body.data.store, 'memory');
  });
});

test('a state route without a token is refused with TOKEN_INVALID', async () => {
  await withServer(async ({ base }) => {
    const body = await get(base, '/api/camp/detail');
    assert.equal(body.code, -130002);
    assert.equal(body.message, 'token invalid');
  });
});

test('login returns a role and a session token, and the token opens the camp', async () => {
  await withServer(async ({ base }) => {
    const login = await post(base, '/api/auth/login', { device_id: 'test-device-1', name: 'David' });
    assert.equal(login.code, 0);
    assert.ok(login.data.session_token);
    assert.ok(login.data.player.role_id);
    assert.equal(login.data.player.name, 'David');
    assert.equal(login.data.servers.length, 3);

    const camp = await get(base, '/api/camp/detail', login.data.session_token);
    assert.equal(camp.code, 0);
    assert.equal(camp.data.camp.resources.provisions, 180);
    assert.equal(camp.data.camp.works.threshing, 1);
  });
});

test('the same device finds its role again', async () => {
  await withServer(async ({ base }) => {
    const first = await post(base, '/api/auth/login', { device_id: 'stable-device' });
    const second = await post(base, '/api/auth/login', { device_id: 'stable-device' });
    assert.equal(first.data.player.role_id, second.data.player.role_id);
    assert.notEqual(first.data.session_token, second.data.session_token, 'a fresh token each time');
  });
});

test('the gain query does not pay, and the collect does', async () => {
  await withServer(async ({ base }) => {
    const login = await post(base, '/api/auth/login', { device_id: 'dev-gain' });
    const token = login.data.session_token;

    const query = await get(base, '/api/camp/gain/query', token);
    assert.equal(query.code, 0);
    assert.equal(query.data.can_gain, false);

    const collect = await post(base, '/api/camp/gain/collect', {}, token);
    assert.equal(collect.code, 0);
    assert.deepEqual(collect.data.given, {});
  });
});

test('upgrading a work costs from the stores and raises the level', async () => {
  await withServer(async ({ base }) => {
    const login = await post(base, '/api/auth/login', { device_id: 'dev-upgrade' });
    const token = login.data.session_token;

    const before = await get(base, '/api/camp/detail', token);
    const timber = before.data.camp.resources.timber;

    // The Cistern costs 55 timber and 45 provisions at level 1; the camp
    // starts with 60 timber and 180 provisions, so this one can be paid.
    const up = await post(base, '/api/camp/upgrade', { work_id: 'cistern' }, token);
    assert.equal(up.code, 0);
    assert.equal(up.data.level, 2);
    assert.equal(up.data.resources.timber, timber - 55);

    // The Threshing Floor wants 63 timber — more than the camp holds.
    const dear = await post(base, '/api/camp/upgrade', { work_id: 'threshing' }, token);
    assert.equal(dear.code, -130001);

    const bad = await post(base, '/api/camp/upgrade', { work_id: 'nowhere' }, token);
    assert.equal(bad.code, -130001);
  });
});

test('recruiting a company costs and raises the count', async () => {
  await withServer(async ({ base }) => {
    const login = await post(base, '/api/auth/login', { device_id: 'dev-recruit' });
    const token = login.data.session_token;

    const done = await post(base, '/api/troops/recruit', { company_id: 'spears', steps: 1 }, token);
    assert.equal(done.code, 0);
    assert.equal(done.data.count, 10);

    const detail = await get(base, '/api/troops', token);
    assert.equal(detail.code, 0);
    assert.equal(detail.data.companies.find((c) => c.id === 'spears').count, 10);
    assert.ok(detail.data.power.power > 0);
  });
});

test('the pursuit replays a fight on the server and settles it', async () => {
  await withServer(async ({ base }) => {
    const login = await post(base, '/api/auth/login', { device_id: 'dev-fight' });
    const token = login.data.session_token;

    // give the camp a real band
    for (const id of ['spears', 'shields', 'bows']) {
      await post(base, '/api/troops/recruit', { company_id: id, steps: 2 }, token);
    }
    await post(base, '/api/camp/upgrade', { work_id: 'barracks' }, token);

    const fight = await post(base, '/api/combat/pursue', {
      stage_id: 'besor-watch', seed: 20240,
    }, token);
    assert.equal(fight.code, 0);
    assert.equal(fight.data.kind, 'skirmish');
    assert.equal(typeof fight.data.won, 'boolean');
    assert.ok(fight.data.log.length > 0);
    assert.ok(fight.data.elapsed >= 0);

    const again = await post(base, '/api/combat/pursue', {
      stage_id: 'besor-watch', seed: 20240,
    }, token);
    assert.deepEqual(again.data.losses, fight.data.losses, 'the replay must be identical');
  });
});

test('a campaign phase is scored and pays its spoil', async () => {
  await withServer(async ({ base }) => {
    const login = await post(base, '/api/auth/login', { device_id: 'dev-phase' });
    const token = login.data.session_token;

    const done = await post(base, '/api/combat/pursue', {
      phase: 'STATE_0_ZIKLAG', time: 100, alerts: 0, stealth: false,
    }, token);
    assert.equal(done.code, 0);
    assert.equal(done.data.stars, 3);
    assert.equal(done.data.reward.provisions, 80);
    assert.equal(done.data.task.id, 'ashes');
  });
});

test('the RPC dispatcher addresses the same handlers by message name', async () => {
  await withServer(async ({ base }) => {
    const login = await post(base, '/api/auth/login', { device_id: 'dev-rpc' });
    const token = login.data.session_token;

    const body = await post(base, '/api/rpc/encampment/upgrade', {
      msg: { work_id: 'cistern' },
    }, token);
    assert.equal(body.code, 0);
    assert.equal(body.data.level, 2);

    const unknown = await post(base, '/api/rpc/encampment/nothing', {}, token);
    assert.equal(unknown.code, -130001);
  });
});

test('the catalogue is published, with the reference names beside ours', async () => {
  await withServer(async ({ base }) => {
    const body = await get(base, '/api/meta/rpc');
    assert.equal(body.code, 0);
    assert.ok(body.data.count >= 20);
    const gain = body.data.messages.find((m) => m.action === 'GainQuery');
    assert.equal(gain.request, 'CEncampmentGainQuery');
    assert.match(gain.reference, /CFarmersWorldGainQuery/);
    assert.ok(body.data.messages.every((m) => m.request.startsWith('C')));
    assert.ok(body.data.messages.every((m) => m.response.startsWith('S')));
  });
});

test('the config tables are served so the client can be retuned', async () => {
  await withServer(async ({ base }) => {
    const body = await get(base, '/api/meta/config');
    assert.equal(body.code, 0);
    assert.equal(body.data.works.length, 8);
    assert.equal(body.data.companies.length, 5);
    assert.equal(body.data.skirmishes.length, 6);
    assert.equal(body.data.tasks.length, 7);
    assert.equal(body.data.resources.length, 4);
  });
});

test('an unknown route answers with the envelope, not a stack trace', async () => {
  await withServer(async ({ base }) => {
    const body = await get(base, '/api/nothing/here');
    assert.equal(body.code, -130001);
    assert.match(body.message, /no such route/);
  });
});
