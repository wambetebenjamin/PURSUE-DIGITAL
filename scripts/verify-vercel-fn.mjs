/**
 * verify-vercel-fn.mjs — check the deployed serverless entry point.
 *
 * The Vercel function is the same core the local server runs, but it is
 * bundled separately and cannot pull in mongoose or express. This exercises
 * the handler the way Vercel would, so a broken import is caught here rather
 * than in production.
 *
 * Usage: node scripts/verify-vercel-fn.mjs
 */
const mod = await import('../api/[...path].js');
const handler = mod.default;

function mockRes() {
  const r = { code: null, body: null, headers: {} };
  r.set = (k, v) => { r.headers[k] = v; };
  r.status = (c) => { r.code = c; return r; };
  r.json = (b) => { r.body = b; return r; };
  return r;
}

let passed = 0;
function ok(cond, label) {
  if (!cond) {
    console.error(`  ✗ ${label}`);
    process.exit(1);
  }
  passed += 1;
  console.log(`  ✓ ${label}`);
}

const health = mockRes();
await handler({ method: 'GET', url: '/api/health', headers: {}, body: null, query: {} }, health);
ok(health.body && health.body.code === 0, 'the function answers the health check');
ok(health.body.data && health.body.data.store === 'memory', 'and reports its store');

const login = mockRes();
await handler({
  method: 'POST', url: '/api/auth/login', headers: {}, body: { device_id: 'verify' }, query: {},
}, login);
ok(login.body && login.body.code === 0, 'the function opens a session');
ok(login.body.data && login.body.data.session_token, 'and hands out a token');

const guarded = mockRes();
await handler({ method: 'GET', url: '/api/camp/detail', headers: {}, body: null, query: {} }, guarded);
ok(guarded.body && guarded.body.code === -130002, 'a state route without a token is refused');

console.log(`\n${passed} checks passed.\n`);
