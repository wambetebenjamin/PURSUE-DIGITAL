/**
 * camp-server.mjs — the camp, talking to the real server.
 *
 * The camp's client and the server are two halves of one contract. This
 * test stands the server up (express + the memory store) and drives it with
 * the very client the canvas uses: log in, read the camp, raise a work,
 * muster a company, send the band out, and read the result.
 *
 * Usage: `npm run camp:server`
 */
import { createApp } from '../game-backend/src/app.js';
import { createStore } from '../game-backend/src/store/index.js';
import { CampApi } from '../src/game/api.js';

let passed = 0;
function ok(cond, label) {
  if (!cond) {
    console.error(`  ✗ FAIL: ${label}`);
    process.exit(1);
  }
  passed += 1;
  console.log(`  ✓ ${label}`);
}

/* ------------------------------------------------------------- the server */
const store = await createStore(null);
const app = createApp(store);
const server = app.listen(0, '127.0.0.1');
await new Promise((r) => server.once('listening', r));
const { port } = server.address();
const base = `http://127.0.0.1:${port}/api`;

/* ------------------------------------------------------------- the client */
const api = new CampApi({ base, deviceId: 'dev-integration' });

const state = await api.refresh();
ok(api.online === true, 'the camp reaches the server');
ok(state.source === 'server', 'and the camp is kept by the server');
ok(typeof state.resources.provisions === 'number', 'the stores come back');
ok(Object.keys(state.works).length === 8, 'all eight works are there');
ok(Object.keys(state.troops.companies).length === 5, 'and all five companies');

const affordable = 'quarry';
const before = state.works[affordable];
const raised = await api.upgradeCamp(affordable);
ok(raised.ok === true, 'a work can be raised');
ok(raised.data.level === before + 1, 'and the server says what level it is now');

const mustered = await api.recruitTroops('spears', 1);
ok(mustered.ok === true, 'a press of ten can be mustered');
ok(mustered.data.count >= 10, 'and the server says how many men there are');

const fought = await api.pursue('besor-watch');
ok(fought.ok === true, 'the band can be sent out');
ok(typeof fought.data.won === 'boolean', 'the server decides the field');
ok(fought.data.log !== undefined, 'and the fight is logged');

const after = await api.refresh();
// the fight is fought with a random seed, so what is certain is the shape
// of the answer, not its numbers
ok(typeof after.troops.companies.spears.count === 'number',
  'the company is still on the books after the fight');
ok(after.resources.provisions >= 0 && after.resources.timber >= 0,
  'and the stores are still whole');

/* ------------------------------------------------- a refusal is reported */
const poor = await api.upgradeCamp('hall');
ok(poor.ok === false, 'the server refuses an order the stores cannot pay for');
ok(typeof poor.message === 'string' && poor.message.length > 0, 'and it says why');
ok(poor.source === 'server', 'the refusal is the server\'s, not the camp\'s');

server.close();
await store.close();
console.log(`\nAll ${passed} checks passed.\n`);
process.exit(0);
