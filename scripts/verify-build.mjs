/**
 * verify-build.mjs — proves the installed-app shell is complete.
 *
 * `npm run build` ends here. The PWA is only real if three things agree:
 * the manifest names icons that exist, the service worker precaches files that
 * exist, and every URL in the emitted HTML works from a *sub-path* (GitHub
 * Pages) as well as from a domain root (Vercel). A broken manifest turns into a
 * silent install failure on a phone, which is exactly the bug nobody sees in CI.
 *
 * Usage: `node scripts/verify-build.mjs`
 */
import { readFileSync, existsSync } from 'node:fs';
import { join } from 'node:path';

const DIST = 'dist';
let failures = 0;

const ok = (cond, label) => {
  if (cond) {
    console.log(`  ✓ ${label}`);
  } else {
    console.error(`  ✗ ${label}`);
    failures++;
  }
};

const read = (p) => readFileSync(join(DIST, p), 'utf8');
const exists = (p) => existsSync(join(DIST, p));

console.log('\nPURSUE build verification\n');

// ------------------------------------------------------------- manifest
const manifestPath = 'manifest.webmanifest';
ok(exists(manifestPath), 'manifest.webmanifest is emitted');
const manifest = JSON.parse(read(manifestPath));
ok(manifest.name && manifest.short_name, 'manifest carries name and short_name');
ok(manifest.display === 'fullscreen', 'manifest launches fullscreen');
ok(manifest.orientation === 'landscape', 'manifest locks landscape');
ok(manifest.start_url && manifest.scope, 'manifest defines start_url and scope');
ok(/^#/.test(manifest.theme_color || ''), 'manifest sets a theme colour');
ok(Array.isArray(manifest.icons) && manifest.icons.length >= 3, 'manifest lists at least three icons');

for (const icon of manifest.icons) {
  ok(exists(icon.src), `icon exists: ${icon.src}`);
  ok(['image/png', 'image/svg+xml'].includes(icon.type), `icon type is installable: ${icon.src}`);
}
ok(manifest.icons.some((i) => i.purpose === 'maskable'), 'a maskable icon is supplied (Android adaptive)');
ok(manifest.icons.some((i) => i.sizes === '512x512'), 'a 512 icon is supplied (install prompt requirement)');

// --------------------------------------------------------- service worker
ok(exists('sw.js'), 'sw.js is emitted');
const sw = read('sw.js');
const precacheMatch = sw.match(/const PRECACHE = (\[[\s\S]*?\]);/);
ok(Boolean(precacheMatch), 'sw.js carries a precache list');
const precache = JSON.parse(precacheMatch[1]);
ok(precache.length >= 8, `sw.js precaches the whole app (${precache.length} files)`);
ok(precache.some((f) => f.endsWith('index.html')), 'the shell is precached');
ok(precache.some((f) => f.includes('three-')), 'the engine chunk is precached');
ok(precache.some((f) => f.endsWith('.css')), 'the stylesheet is precached');
ok(precache.some((f) => f.endsWith('.ttf')), 'the local serif fonts are precached');
ok(precache.every((f) => !f.startsWith('/')), 'precache paths are sub-path safe');

for (const file of precache) {
  ok(exists(file.replace(/^\.\//, '')), `precached file exists: ${file}`);
}
ok(!/__PRECACHE__|__VERSION__/.test(sw), 'sw.js placeholders were replaced');

// ------------------------------------------------------------------ html
const html = read('index.html');
ok(html.includes('rel="manifest"'), 'the page links the manifest');
ok(html.includes('apple-touch-icon'), 'iOS home-screen icon is declared');
ok(html.includes('apple-mobile-web-app-capable'), 'iOS standalone mode is declared');
ok(html.includes('id="install-pill"'), 'the install invitation is in the shell');
ok(!/src="\/|href="\/(?!\/)/.test(html), 'no root-absolute URLs (works on Pages sub-path)');

// ------------------------------------------------------------- mobile UI
ok(html.includes('id="touch-ui"'), 'touch controls are in the build');
for (const id of ['joy-base', 'btn-use', 'btn-run', 'btn-crouch', 'btn-map']) {
  ok(html.includes(`id="${id}"`), `touch control present: ${id}`);
}

console.log(
  failures === 0
    ? `\nThe installable shell is complete.\n`
    : `\n${failures} check(s) failed.\n`,
);
process.exit(failures === 0 ? 0 : 1);
