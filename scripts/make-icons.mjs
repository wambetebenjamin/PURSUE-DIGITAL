/**
 * make-icons.mjs — generates the installed-app icons (PNG) with no dependencies.
 *
 * A phone install needs PNG icons at 192 and 512 (plus a maskable 512 and an
 * iOS 180), and the project has no image assets by design. So the icon is
 * drawn here, from the same art direction as the title card: a low sun over a
 * ridgeline, smoke in the air, and a wheat stalk — the mark of the pursuit
 * through the fields of the Negev.
 *
 * Pure Node: the rasteriser writes an RGBA buffer and the encoder emits a
 * standard PNG (IHDR / IDAT / IEND with zlib deflate and hand-rolled CRCs).
 *
 * Usage: `node scripts/make-icons.mjs`
 */
import { deflateSync } from 'node:zlib';
import { mkdirSync, writeFileSync } from 'node:fs';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';

const here = dirname(fileURLToPath(import.meta.url));
const outDir = join(here, '..', 'public', 'icons');

// ------------------------------------------------------------------ PNG
const CRC_TABLE = (() => {
  const t = new Uint32Array(256);
  for (let n = 0; n < 256; n++) {
    let c = n;
    for (let k = 0; k < 8; k++) c = c & 1 ? 0xedb88320 ^ (c >>> 1) : c >>> 1;
    t[n] = c >>> 0;
  }
  return t;
})();

function crc32(buf) {
  let c = 0xffffffff;
  for (let i = 0; i < buf.length; i++) c = CRC_TABLE[(c ^ buf[i]) & 0xff] ^ (c >>> 8);
  return (c ^ 0xffffffff) >>> 0;
}

function chunk(type, data) {
  const len = Buffer.alloc(4);
  len.writeUInt32BE(data.length, 0);
  const body = Buffer.concat([Buffer.from(type, 'ascii'), data]);
  const crc = Buffer.alloc(4);
  crc.writeUInt32BE(crc32(body), 0);
  return Buffer.concat([len, body, crc]);
}

function encodePNG(size, rgba) {
  const sig = Buffer.from([0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a]);
  const ihdr = Buffer.alloc(13);
  ihdr.writeUInt32BE(size, 0);
  ihdr.writeUInt32BE(size, 4);
  ihdr[8] = 8; // bit depth
  ihdr[9] = 6; // truecolour + alpha
  ihdr[10] = 0;
  ihdr[11] = 0;
  ihdr[12] = 0;

  // filtered scanlines, filter byte 0
  const stride = size * 4;
  const raw = Buffer.alloc((stride + 1) * size);
  for (let y = 0; y < size; y++) {
    raw[y * (stride + 1)] = 0;
    rgba.copy(raw, y * (stride + 1) + 1, y * stride, (y + 1) * stride);
  }
  return Buffer.concat([
    sig,
    chunk('IHDR', ihdr),
    chunk('IDAT', deflateSync(raw, { level: 9 })),
    chunk('IEND', Buffer.alloc(0)),
  ]);
}

// ---------------------------------------------------------------- drawing
const clamp01 = (v) => Math.max(0, Math.min(1, v));
const smooth = (a, b, x) => {
  const t = clamp01((x - a) / (b - a));
  return t * t * (3 - 2 * t);
};
const mix = (a, b, t) => a.map((v, i) => v + (b[i] - v) * t);

/** distance from point to segment, for strokes. */
function segDist(px, py, ax, ay, bx, by) {
  const vx = bx - ax;
  const vy = by - ay;
  const wx = px - ax;
  const wy = py - ay;
  const t = clamp01((wx * vx + wy * vy) / (vx * vx + vy * vy || 1));
  return Math.hypot(wx - vx * t, wy - vy * t);
}

/**
 * One icon, rendered at `size`. Coordinates are authored in a 0..1 unit square
 * and scaled, so every output size is the same drawing.
 */
function render(size, { maskable = false } = {}) {
  const buf = Buffer.alloc(size * size * 4);

  // maskable icons must survive an aggressive circular crop: pull the art in
  const inset = maskable ? 0.16 : 0.04;
  const art = 1 - inset * 2;
  const toArt = (v, offset) => (v - (offset ?? 0)) / art;

  for (let py = 0; py < size; py++) {
    for (let px = 0; px < size; px++) {
      const u = (px + 0.5) / size;
      const v = (py + 0.5) / size;
      // art space
      const x = toArt(u, inset);
      const y = toArt(v, inset);
      const insideArt = x > -0.15 && x < 1.15 && y > -0.15 && y < 1.15;

      // ---------------------------------------------------- sky
      const horizon = 0.62;
      const skyT = clamp01(y / horizon);
      let col = mix([0x2a, 0x3a, 0x58], [0xd9, 0x9a, 0x64], Math.pow(skyT, 0.85));

      // smoke band above the horizon: warm, turbulent, low contrast
      const smoke = Math.exp(-Math.pow((y - horizon + 0.02) / 0.12, 2))
        * (0.55 + 0.45 * Math.sin(x * 17.0 + y * 9.0));
      col = mix(col, [0xe8, 0xbe, 0x96], smoke * 0.35);

      // ---------------------------------------------------- sun
      const sx = 0.635;
      const sy = 0.505;
      const sr = 0.105;
      const d = Math.hypot((x - sx), (y - sy) * 1.0);
      const disc = 1 - smooth(sr - 0.006, sr + 0.006, d);
      const halo = Math.exp(-Math.pow(d / (sr * 3.1), 2)) * 0.62;
      col = mix(col, [0xff, 0x8f, 0x42], clamp01(halo));
      col = mix(col, [0xff, 0xe9, 0xc0], disc);

      // ---------------------------------------------------- ridges
      if (y > horizon - 0.04 && insideArt) {
        const ridgeFar = horizon - 0.055
          + 0.020 * Math.sin(x * 6.1 + 1.2)
          + 0.012 * Math.sin(x * 13.7 + 4.1);
        if (y > ridgeFar) {
          const depth = smooth(ridgeFar, ridgeFar + 0.30, y);
          col = mix(col, mix([0x9c, 0x74, 0x62], [0x6b, 0x4c, 0x42], depth), 0.88);
        }
        const ridgeNear = horizon + 0.02
          + 0.030 * Math.sin(x * 3.4 + 0.6)
          + 0.014 * Math.sin(x * 8.3 + 2.2);
        if (y > ridgeNear) {
          col = mix(col, [0x24, 0x18, 0x14], 0.94);
        }
      }

      // ---------------------------------------------------- wheat stalk
      // one stalk rising from the soil, five grains sweeping up each side
      const bx = 0.285;
      const baseY = 0.985;
      const tipY = 0.145;
      const lean = 0.055;
      const t = clamp01((baseY - y) / (baseY - tipY));
      const stemX = bx + lean * t * t;
      const stemW = 0.0045 + 0.0045 * (1 - t); // thicker at the foot
      const stem = (1 - smooth(stemW, stemW + 0.004, Math.abs(x - stemX)))
        * (y < baseY && y > tipY + 0.055 ? 1 : 0);
      if (stem > 0.01) {
        const gold = mix([0x7a, 0x52, 0x1e], [0xe4, 0xc0, 0x80], clamp01(t * 1.1));
        col = mix(col, gold, stem * 0.96);
      }

      // grains: teardrops swept upward from the rachis
      for (let i = 0; i < 5; i++) {
        const gt = 0.10 + i * 0.135;              // from the tip downward
        const gy = tipY + gt * (baseY - tipY);
        const gx = bx + lean * (1 - gt) * (1 - gt);
        const scale = 1 - i * 0.085;
        for (const side of [-1, 1]) {
          const ex = gx + side * 0.046 * scale;
          const ey = gy - 0.042 * scale;          // swept up
          const gd = segDist(x, y, gx, gy, ex, ey);
          const w = (0.0128 - 0.0040 * gt) * scale;
          const grain = 1 - smooth(w, w + 0.006, gd);
          if (grain > 0.01) {
            const shade = mix([0xb0, 0x86, 0x42], [0xf4, 0xe2, 0xb4], clamp01(gt * 1.4));
            col = mix(col, shade, grain * 0.97);
          }
        }
      }

      // awns: three fine hairs off the tip
      for (let i = 0; i < 3; i++) {
        const hx = bx + lean + (i - 1) * 0.045;
        const hy = tipY - 0.075 - (1 - Math.abs(i - 1)) * 0.012;
        const hd = segDist(x, y, bx + lean, tipY - 0.004, hx, hy);
        const hair = 1 - smooth(0.0022, 0.0045, hd);
        if (hair > 0.01) col = mix(col, [0xd6, 0xb2, 0x72], hair * 0.85);
      }

      // ---------------------------------------------------- frame & vignette
      const vig = Math.hypot(u - 0.5, v - 0.5) * 1.42;
      col = col.map((c) => c * (1 - 0.42 * Math.pow(vig, 2.4)));

      // ink border inside the tile (not on maskable, which is cropped)
      if (!maskable) {
        const edge = Math.min(u, v, 1 - u, 1 - v);
        const border = 1 - smooth(0.006, 0.012, edge);
        col = mix(col, [0x1a, 0x12, 0x08], border * 0.85);
      }

      const i = (py * size + px) * 4;
      buf[i] = Math.round(clamp01(col[0] / 255) * 255);
      buf[i + 1] = Math.round(clamp01(col[1] / 255) * 255);
      buf[i + 2] = Math.round(clamp01(col[2] / 255) * 255);
      buf[i + 3] = 255;
    }
  }
  return encodePNG(size, buf);
}

mkdirSync(outDir, { recursive: true });

const jobs = [
  ['icon-192.png', 192, {}],
  ['icon-512.png', 512, {}],
  ['icon-maskable-512.png', 512, { maskable: true }],
  ['apple-touch-icon.png', 180, {}],
  ['favicon-64.png', 64, {}],
];

for (const [name, size, opts] of jobs) {
  const png = render(size, opts);
  writeFileSync(join(outDir, name), png);
  console.log(`  ${name}  ${size}×${size}  ${(png.length / 1024).toFixed(1)} kB`);
}
console.log(`\nWrote ${jobs.length} icons to public/icons/`);
