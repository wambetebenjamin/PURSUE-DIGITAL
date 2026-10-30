import { defineConfig } from 'vite';
import { readdirSync, readFileSync, statSync, writeFileSync } from 'node:fs';
import { join, relative } from 'node:path';

/** Every file under `dir`, as paths relative to it (posix separators). */
function walk(dir, base = dir, out = []) {
  for (const name of readdirSync(dir)) {
    const full = join(dir, name);
    if (statSync(full).isDirectory()) walk(full, base, out);
    else out.push(relative(base, full).split('\\').join('/'));
  }
  return out;
}

/**
 * pursue-pwa — installs the app shell into the build.
 *
 * After Vite has emitted `dist/` this plugin:
 *   1. writes `manifest.webmanifest` with the *real* hashed asset names, so an
 *      installed app has its own icon, name, colours and landscape lock;
 *   2. fills the service worker's precache list with every emitted file, so the
 *      whole game is available offline after the first visit.
 *
 * Doing this at build time (instead of hand-writing the files) means the
 * manifest can never point at a stale hashed chunk.
 */
function pursuePWA() {
  return {
    name: 'pursue-pwa',
    apply: 'build',
    closeBundle() {
      const dist = 'dist';
      const files = walk(dist).filter((f) => f !== 'sw.js' && f !== 'manifest.webmanifest');

      // ---------------------------------------------------------- manifest
      const manifest = {
        id: './',
        name: 'PURSUE — 1 Samuel 30',
        short_name: 'PURSUE',
        description:
          'Pursue, and recover all. A cinematic retelling of 1 Samuel 30, played in the browser or installed like an app.',
        start_url: './',
        scope: './',
        display: 'fullscreen',
        display_override: ['fullscreen', 'standalone', 'minimal-ui'],
        orientation: 'landscape',
        background_color: '#0a0806',
        theme_color: '#1a1208',
        categories: ['games', 'entertainment'],
        lang: 'en',
        dir: 'ltr',
        icons: [
          { src: 'icons/icon-192.png', sizes: '192x192', type: 'image/png', purpose: 'any' },
          { src: 'icons/icon-512.png', sizes: '512x512', type: 'image/png', purpose: 'any' },
          { src: 'icons/icon-maskable-512.png', sizes: '512x512', type: 'image/png', purpose: 'maskable' },
        ],
      };
      writeFileSync(join(dist, 'manifest.webmanifest'), `${JSON.stringify(manifest, null, 2)}\n`);

      // ---------------------------------------------------- service worker
      const swSrc = readFileSync('public/sw.js', 'utf8');
      const version = `${Date.now().toString(36)}`;
      const precache = files
        .filter((f) => !f.endsWith('.map'))
        .map((f) => `./${f}`);
      const sw = swSrc
        .replace('__VERSION__', version)
        .replace('__PRECACHE__', JSON.stringify(precache, null, 2));
      writeFileSync(join(dist, 'sw.js'), sw);

      const kb = (precache.length ? 1 : 1);
      console.log(
        `\n  pursue-pwa  manifest + sw.js written · ${precache.length} files precached (${kb ? 'offline play ready' : ''})`,
      );
    },
  };
}

// Static, dependency-free deploy target: `npm run build` emits `dist/`,
// which Vercel serves as-is (Vite framework preset).
export default defineConfig({
  base: './',
  plugins: [pursuePWA()],
  build: {
    target: 'es2020',
    sourcemap: false,
    chunkSizeWarningLimit: 900,
    rollupOptions: {
      output: {
        manualChunks: {
          three: ['three'],
        },
      },
    },
  },
  server: {
    host: '0.0.0.0',
    port: 5173,
    strictPort: true,
    // Allow any host (sandbox previews proxy via *.e2b.app subdomains).
    allowedHosts: true,
  },
  preview: {
    host: '0.0.0.0',
    port: 4173,
    strictPort: true,
    allowedHosts: true,
  },
});
