/**
 * sw.js — the offline shell.
 *
 * The whole game is a static bundle (a few MB), so it can simply be precached:
 * after the first load the march runs with no network at all, which is what
 * makes the installed app behave like the APK rather than like a web page.
 *
 * Strategy
 *   • install  — precache every emitted file (appended by the build, see
 *                vite.config.js `pursue-pwa`), then take over immediately.
 *   • activate — delete caches from earlier builds.
 *   • navigate — network first, falling back to the cached shell, so a new
 *                deploy is picked up on the next load but a desert camp still
 *                works in a tunnel.
 *   • assets   — cache first (they are content-hashed and immutable).
 *
 * Relative URLs throughout: the same file serves the Vercel root and the
 * GitHub Pages sub-path.
 */
const VERSION = 'mvv4x93c';
const CACHE = `pursue-${VERSION}`;

/** Files appended at build time, relative to the service worker's scope. */
const PRECACHE = [
  "./assets/EBGaramond-BZHnynmT.ttf",
  "./assets/EBGaramond-Italic-C2_346tT.ttf",
  "./assets/FrankRuhlLibre-DH22KwVP.ttf",
  "./assets/Inter-VF2RPR_K.ttf",
  "./assets/index-D7Ab0eKe.css",
  "./assets/index-DqqCMO7d.js",
  "./assets/three-CXh9M2zY.js",
  "./icons/apple-touch-icon.png",
  "./icons/favicon-64.png",
  "./icons/icon-192.png",
  "./icons/icon-512.png",
  "./icons/icon-maskable-512.png",
  "./index.html"
];

const scopeUrl = (rel) => new URL(rel, self.registration.scope).href;

self.addEventListener('install', (event) => {
  event.waitUntil((async () => {
    const cache = await caches.open(CACHE);
    // one failure must not abort the whole install: add what we can
    await Promise.all(PRECACHE.map(async (rel) => {
      try {
        await cache.add(new Request(scopeUrl(rel), { cache: 'reload' }));
      } catch (_e) {
        /* a missing optional file is not fatal */
      }
    }));
    await self.skipWaiting();
  })());
});

self.addEventListener('activate', (event) => {
  event.waitUntil((async () => {
    const keys = await caches.keys();
    await Promise.all(keys.filter((k) => k !== CACHE).map((k) => caches.delete(k)));
    await self.clients.claim();
  })());
});

self.addEventListener('message', (event) => {
  if (event.data === 'skip-waiting') self.skipWaiting();
});

self.addEventListener('fetch', (event) => {
  const req = event.request;
  if (req.method !== 'GET') return;

  const url = new URL(req.url);
  if (url.origin !== self.location.origin) return;
  // never intercept the service worker itself or range requests
  if (url.pathname.endsWith('/sw.js')) return;
  if (req.headers.has('range')) return;

  // --- navigations: fresh shell when possible, cached shell when not
  if (req.mode === 'navigate') {
    event.respondWith((async () => {
      const cache = await caches.open(CACHE);
      const shell = scopeUrl('index.html');
      try {
        const fresh = await fetch(req);
        if (fresh && fresh.ok) cache.put(shell, fresh.clone());
        return fresh;
      } catch (_e) {
        const cached = await cache.match(shell);
        if (cached) return cached;
        return new Response('PURSUE needs one online visit before it can run offline.', {
          status: 503,
          headers: { 'Content-Type': 'text/plain; charset=utf-8' },
        });
      }
    })());
    return;
  }

  // --- everything else: cache first, then network, then cache a copy
  event.respondWith((async () => {
    const cache = await caches.open(CACHE);
    const hit = await cache.match(req, { ignoreSearch: false });
    if (hit) return hit;
    try {
      const res = await fetch(req);
      if (res && res.ok && res.type === 'basic') cache.put(req, res.clone());
      return res;
    } catch (err) {
      const fallback = await cache.match(req, { ignoreSearch: true });
      if (fallback) return fallback;
      throw err;
    }
  })());
});
