/**
 * surfaces.js — procedural PBR surface maps for the cinematic pass.
 *
 * Everything on screen is still generated at runtime (no downloads, no
 * image assets): pores, leather grain, hammered metal, woven linen, hair
 * strands, limestone tooling, cracked earth and soot are painted on ≤512²
 * canvases from seeded noise and then reused as bump / roughness / alpha
 * maps. The maps are what make the low-poly forms read as *materials*:
 * a bump map gives a bronze pauldron its dents, a strand alpha map turns
 * a flat card into coarse hair, a cracked bump map turns a plane into
 * sun-baked Judean ground.
 *
 * All maps are cached by key, so the whole world shares a handful of
 * GPU textures (well inside the 1024² texture budget).
 */
import * as THREE from 'three';
import { seededRandom } from './math.js';

const cache = new Map();

function makeCanvas(key, size, paint) {
  if (cache.has(key)) return cache.get(key);
  const canvas = document.createElement('canvas');
  canvas.width = size;
  canvas.height = size;
  const ctx = canvas.getContext('2d');
  paint(ctx, size);
  cache.set(key, canvas);
  return canvas;
}

function toTexture(key, size, paint, { repeat = [1, 1], srgb = false, aniso = 4 } = {}) {
  const ck = `${key}|${repeat[0]}x${repeat[1]}|${srgb ? 's' : 'l'}`;
  if (cache.has(ck)) return cache.get(ck);
  const t = new THREE.CanvasTexture(makeCanvas(key, size, paint));
  t.wrapS = t.wrapT = THREE.RepeatWrapping;
  t.repeat.set(repeat[0], repeat[1]);
  t.anisotropy = aniso;
  if (srgb) t.colorSpace = THREE.SRGBColorSpace;
  cache.set(ck, t);
  return t;
}

// ------------------------------------------------------------ noise helpers
function rnd(seed) {
  return seededRandom(seed);
}

/** Gradient factory that survives the headless stub (returns undefined). */
function gradient(ctx, type, ...args) {
  const g = type === 'radial'
    ? ctx['createRadialGradient'](...args)
    : ctx['createLinearGradient'](...args);
  return g && typeof g.addColorStop === 'function' ? g : { addColorStop() {} };
}

/** Soft mottled field — the base of most organic maps. */
function mottle(ctx, s, base, spots, count, alpha, rMin, rMax, seed = 1) {
  ctx.fillStyle = base;
  ctx.fillRect(0, 0, s, s);
  for (let i = 0; i < count; i++) {
    const r = rMin + rnd(seed * 13 + i * 2.7) * (rMax - rMin);
    const x = rnd(seed * 7 + i * 3.1) * s;
    const y = rnd(seed * 11 + i * 1.9) * s;
    ctx.globalAlpha = alpha * (0.35 + rnd(seed + i) * 0.65);
    ctx.fillStyle = spots[(rnd(seed * 3 + i * 5.3) * spots.length) | 0];
    ctx.beginPath();
    ctx.arc(x, y, r, 0, Math.PI * 2);
    ctx.fill();
  }
  ctx.globalAlpha = 1;
}

/** Fine grayscale speckle (grain / grit). */
function speckle(ctx, s, count, seed, light = '#ffffff', dark = '#000000', size = 1.5) {
  for (let i = 0; i < count; i++) {
    ctx.globalAlpha = 0.08 + rnd(i * 1.37 + seed) * 0.22;
    ctx.fillStyle = rnd(i * 2.11 + seed) > 0.5 ? light : dark;
    ctx.fillRect(rnd(i * 3.17 + seed) * s, rnd(i * 4.71 + seed) * s, size, size);
  }
  ctx.globalAlpha = 1;
}

// ============================================================ skin
/** Pores, fine wrinkles and a dusting of grit — used as a bump map. */
export function poresMap(repeat = [3, 3]) {
  return toTexture('pores', 512, (ctx, s) => {
    mottle(ctx, s, '#808080', ['#6a6a6a', '#969696', '#5e5e5e', '#a0a0a0'], 3200, 0.35, 0.6, 3.4, 41);
    // follicles
    for (let i = 0; i < 2400; i++) {
      const x = rnd(i * 2.3) * s;
      const y = rnd(i * 3.7) * s;
      const r = 0.6 + rnd(i * 1.9) * 1.5;
      ctx.globalAlpha = 0.5;
      ctx.fillStyle = '#4c4c4c';
      ctx.beginPath(); ctx.arc(x, y, r, 0, Math.PI * 2); ctx.fill();
      ctx.globalAlpha = 0.35;
      ctx.fillStyle = '#b4b4b4';
      ctx.beginPath(); ctx.arc(x - r * 0.5, y - r * 0.5, r * 0.7, 0, Math.PI * 2); ctx.fill();
    }
    // creases (brow, crow's feet, wind-burn lines)
    ctx.strokeStyle = '#4a4a4a';
    for (let i = 0; i < 60; i++) {
      ctx.globalAlpha = 0.18 + rnd(i * 2.9) * 0.2;
      ctx.lineWidth = 0.8 + rnd(i * 4.1) * 1.6;
      const x = rnd(i * 5.3) * s;
      const y = rnd(i * 6.7) * s;
      const len = 8 + rnd(i * 7.1) * 34;
      const a = rnd(i * 8.9) * Math.PI;
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.quadraticCurveTo(
        x + Math.cos(a) * len * 0.5 + (rnd(i * 2.2) - 0.5) * 8,
        y + Math.sin(a) * len * 0.5,
        x + Math.cos(a) * len,
        y + Math.sin(a) * len,
      );
      ctx.stroke();
    }
    ctx.globalAlpha = 1;
    speckle(ctx, s, 1600, 7, '#c8c8c8', '#3a3a3a', 1.1);
  }, { repeat });
}

/** Skin albedo: sun-baked, mottled, with dust settling into the grain. */
export function skinAlbedoMap(base = '#a06b45', repeat = [2, 2]) {
  return toTexture(`skin-${base}`, 512, (ctx, s) => {
    mottle(ctx, s, base, ['#8a5a3c', '#b57e54', '#c28b60', '#7a4c32', '#c99a72'], 2600, 0.3, 1, 7, 37);
    // wind-burn blotches on the cheekbones / shoulders
    for (let i = 0; i < 40; i++) {
      ctx.globalAlpha = 0.10 + rnd(i * 1.7) * 0.12;
      ctx.fillStyle = '#7a3d28';
      ctx.beginPath();
      ctx.ellipse(rnd(i * 3.3) * s, rnd(i * 4.9) * s, 6 + rnd(i) * 26, 4 + rnd(i * 2.5) * 16, rnd(i) * 3, 0, Math.PI * 2);
      ctx.fill();
    }
    // pale dust film
    ctx.globalAlpha = 0.07;
    ctx.fillStyle = '#e6cfae';
    ctx.fillRect(0, 0, s, s);
    ctx.globalAlpha = 1;
    speckle(ctx, s, 900, 13, '#e8d4b4', '#6a4230', 1.2);
  }, { repeat, srgb: true });
}

// ============================================================ hair / beard
/**
 * Hair card alpha — vertical tapering strands. White = opaque, black = cut
 * away. Used with `alphaMap` + alphaTest so the card silhouette frays
 * instead of reading as a flat ribbon.
 */
export function strandAlphaMap() {
  return toTexture('strandAlpha', 256, (ctx, s) => {
    ctx.fillStyle = '#000000';
    ctx.fillRect(0, 0, s, s);
    for (let i = 0; i < 150; i++) {
      const x = (i / 150) * s + (rnd(i * 3.7) - 0.5) * 7;
      const w = 0.7 + rnd(i * 2.3) * 2.1;
      const bend = (rnd(i * 5.1) - 0.5) * 22;
      const alpha = 0.45 + rnd(i * 7.9) * 0.55;
      const grad = gradient(ctx, 'linear', 0, 0, 0, s);
      grad.addColorStop(0, `rgba(255,255,255,${alpha})`);
      grad.addColorStop(0.75, `rgba(235,235,235,${alpha * 0.85})`);
      grad.addColorStop(1, 'rgba(0,0,0,0)');
      ctx.strokeStyle = grad;
      ctx.lineWidth = w;
      ctx.beginPath();
      ctx.moveTo(x, 4);
      ctx.quadraticCurveTo(x + bend * 0.4, s * 0.5, x + bend, s - 2);
      ctx.stroke();
    }
  }, { repeat: [1, 1] });
}

/** Hair albedo — coarse strand color variation, greying at the roots. */
export function strandColorMap(base = '#241a12', repeat = [1, 1]) {
  return toTexture(`hair-${base}`, 256, (ctx, s) => {
    ctx.fillStyle = base;
    ctx.fillRect(0, 0, s, s);
    for (let i = 0; i < 240; i++) {
      const x = rnd(i * 4.1) * s;
      ctx.globalAlpha = 0.16 + rnd(i * 2.7) * 0.3;
      ctx.strokeStyle = rnd(i * 3.3) > 0.55 ? '#6a5038' : '#0f0b08';
      ctx.lineWidth = 0.8 + rnd(i * 1.3) * 1.8;
      ctx.beginPath();
      ctx.moveTo(x, 0);
      ctx.quadraticCurveTo(x + (rnd(i * 6.1) - 0.5) * 16, s * 0.5, x + (rnd(i * 8.3) - 0.5) * 22, s);
      ctx.stroke();
    }
    // sun-bleached tips and grey flecks
    for (let i = 0; i < 90; i++) {
      ctx.globalAlpha = 0.25;
      ctx.fillStyle = '#b9a68c';
      ctx.fillRect(rnd(i * 2.9) * s, rnd(i * 5.7) * s, 1.6, 3.4);
    }
    ctx.globalAlpha = 1;
  }, { repeat, srgb: true });
}

// ============================================================ leather / hide
export function leatherBumpMap(repeat = [3, 3]) {
  return toTexture('leatherBump', 512, (ctx, s) => {
    mottle(ctx, s, '#7a7a7a', ['#5e5e5e', '#909090', '#6e6e6e'], 2200, 0.4, 1, 9, 53);
    // pebbled grain
    for (let i = 0; i < 1800; i++) {
      const x = rnd(i * 3.1) * s;
      const y = rnd(i * 5.3) * s;
      const r = 1.4 + rnd(i * 7.7) * 3.4;
      ctx.globalAlpha = 0.25;
      ctx.fillStyle = '#a8a8a8';
      ctx.beginPath(); ctx.arc(x, y, r, 0, Math.PI * 2); ctx.fill();
      ctx.globalAlpha = 0.22;
      ctx.fillStyle = '#3c3c3c';
      ctx.beginPath(); ctx.arc(x + r * 0.5, y + r * 0.5, r * 0.8, 0, Math.PI * 2); ctx.fill();
    }
    // cracks — dry leather splits along the grain
    for (let i = 0; i < 46; i++) {
      ctx.globalAlpha = 0.55;
      ctx.strokeStyle = '#2e2e2e';
      ctx.lineWidth = 1 + rnd(i * 2.2) * 2;
      let x = rnd(i * 3.9) * s;
      let y = rnd(i * 4.3) * s;
      ctx.beginPath();
      ctx.moveTo(x, y);
      for (let k = 0; k < 6; k++) {
        x += (rnd(i * 7 + k) - 0.5) * 26;
        y += 6 + rnd(i * 9 + k) * 14;
        ctx.lineTo(x, y);
      }
      ctx.stroke();
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function leatherAlbedoMap(base = 0x3a2c20, repeat = [3, 3]) {
  const hex = `#${base.toString(16).padStart(6, '0')}`;
  return toTexture(`leatherAlb-${hex}`, 256, (ctx, s) => {
    mottle(ctx, s, hex, ['#ffffff', '#000000', '#8a6a48'], 900, 0.1, 1, 7, 61);
    // dust along the creases
    ctx.globalAlpha = 0.08;
    ctx.fillStyle = '#c8b08a';
    ctx.fillRect(0, 0, s, s);
    ctx.globalAlpha = 1;
  }, { repeat, srgb: true });
}

// ============================================================ metal
export function metalBumpMap(repeat = [2, 2]) {
  return toTexture('metalBump', 512, (ctx, s) => {
    ctx.fillStyle = '#808080';
    ctx.fillRect(0, 0, s, s);
    // hammer facets
    for (let i = 0; i < 520; i++) {
      const x = rnd(i * 2.7) * s;
      const y = rnd(i * 4.3) * s;
      const r = 3 + rnd(i * 6.1) * 13;
      const g = gradient(ctx, 'radial', x, y, 0, x, y, r);
      g.addColorStop(0, 'rgba(210,210,210,0.35)');
      g.addColorStop(0.7, 'rgba(120,120,120,0.18)');
      g.addColorStop(1, 'rgba(60,60,60,0)');
      ctx.fillStyle = g;
      ctx.beginPath(); ctx.arc(x, y, r, 0, Math.PI * 2); ctx.fill();
    }
    // battle scuffs — directional scratches
    for (let i = 0; i < 220; i++) {
      ctx.globalAlpha = 0.25 + rnd(i * 3.1) * 0.3;
      ctx.strokeStyle = rnd(i * 1.9) > 0.5 ? '#dedede' : '#2a2a2a';
      ctx.lineWidth = 0.7 + rnd(i * 4.7) * 1.4;
      const x = rnd(i * 5.9) * s;
      const y = rnd(i * 7.3) * s;
      const a = rnd(i * 8.1) * Math.PI;
      const len = 8 + rnd(i * 9.7) * 40;
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.lineTo(x + Math.cos(a) * len, y + Math.sin(a) * len);
      ctx.stroke();
    }
    // patina pitting
    speckle(ctx, s, 1400, 67, '#c8c8c8', '#282828', 1.6);
    ctx.globalAlpha = 1;
  }, { repeat });
}

/** Roughness map: worn high-traffic areas are smoother, pits stay matte. */
export function metalRoughMap(repeat = [2, 2]) {
  return toTexture('metalRough', 512, (ctx, s) => {
    mottle(ctx, s, '#8a8a8a', ['#ffffff', '#5a5a5a', '#bebebe'], 1600, 0.3, 3, 22, 71);
    for (let i = 0; i < 200; i++) {
      ctx.globalAlpha = 0.5;
      ctx.strokeStyle = '#3a3a3a';
      ctx.lineWidth = 1;
      const x = rnd(i * 2.3) * s;
      const y = rnd(i * 3.9) * s;
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.lineTo(x + (rnd(i * 4.9) - 0.5) * 60, y + (rnd(i * 6.7) - 0.5) * 60);
      ctx.stroke();
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

// ============================================================ cloth
export function weaveBumpMap(repeat = [6, 6]) {
  return toTexture('weave', 256, (ctx, s) => {
    ctx.fillStyle = '#808080';
    ctx.fillRect(0, 0, s, s);
    const step = 6;
    for (let y = 0; y < s; y += step) {
      for (let x = 0; x < s; x += step) {
        const over = ((x / step + y / step) | 0) % 2 === 0;
        ctx.fillStyle = over ? '#a4a4a4' : '#5c5c5c';
        ctx.fillRect(x, y, step, step);
      }
    }
    // slubs — irregular handspun thread
    for (let i = 0; i < 260; i++) {
      ctx.globalAlpha = 0.35;
      ctx.fillStyle = rnd(i * 2.7) > 0.5 ? '#d0d0d0' : '#3a3a3a';
      ctx.fillRect(rnd(i * 3.3) * s, rnd(i * 5.1) * s, 2 + rnd(i * 6.3) * 7, 1.5);
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function clothAlbedoMap(base = 0x8c5a38, repeat = [3, 3]) {
  const hex = `#${base.toString(16).padStart(6, '0')}`;
  return toTexture(`clothAlb-${hex}`, 256, (ctx, s) => {
    mottle(ctx, s, hex, ['#ffffff', '#000000', '#a8a090'], 700, 0.12, 2, 12, 79);
    // sun-faded patches
    for (let i = 0; i < 26; i++) {
      ctx.globalAlpha = 0.07;
      ctx.fillStyle = '#f0e0c0';
      ctx.beginPath();
      ctx.ellipse(rnd(i * 2.1) * s, rnd(i * 4.7) * s, 8 + rnd(i) * 30, 6 + rnd(i * 3) * 20, rnd(i * 5) * 3, 0, Math.PI * 2);
      ctx.fill();
    }
    // dust settled in the weave
    speckle(ctx, s, 800, 83, '#d8c8a8', '#3a2c20', 1.1);
    ctx.globalAlpha = 1;
  }, { repeat, srgb: true });
}

// ============================================================ ground / stone
export function crackedEarthBump(repeat = [8, 8]) {
  return toTexture('cracked', 512, (ctx, s) => {
    ctx.fillStyle = '#8c8c8c';
    ctx.fillRect(0, 0, s, s);
    mottle(ctx, s, '#8c8c8c', ['#7a7a7a', '#9c9c9c'], 900, 0.3, 4, 18, 89);
    // polygonal mud-crack network
    ctx.strokeStyle = '#333333';
    for (let i = 0; i < 130; i++) {
      let x = rnd(i * 2.3) * s;
      let y = rnd(i * 4.1) * s;
      ctx.globalAlpha = 0.5 + rnd(i) * 0.4;
      ctx.lineWidth = 1 + rnd(i * 5.3) * 1.8;
      ctx.beginPath();
      ctx.moveTo(x, y);
      const steps = 3 + ((rnd(i * 6.1) * 4) | 0);
      for (let k = 0; k < steps; k++) {
        x += (rnd(i * 7.7 + k) - 0.5) * 40;
        y += (rnd(i * 9.1 + k) - 0.5) * 40;
        ctx.lineTo(x, y);
      }
      ctx.stroke();
    }
    speckle(ctx, s, 1200, 97, '#c0c0c0', '#404040', 1.4);
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function limestoneBumpMap(repeat = [4, 4]) {
  return toTexture('limestoneBump', 512, (ctx, s) => {
    mottle(ctx, s, '#8a8a8a', ['#767676', '#9e9e9e', '#6a6a6a'], 1600, 0.35, 3, 14, 101);
    // chisel marks — short parallel gouges
    for (let i = 0; i < 340; i++) {
      ctx.globalAlpha = 0.3;
      ctx.strokeStyle = rnd(i * 2.9) > 0.5 ? '#c4c4c4' : '#4a4a4a';
      ctx.lineWidth = 1 + rnd(i * 4.3) * 2;
      const x = rnd(i * 6.1) * s;
      const y = rnd(i * 7.9) * s;
      const a = rnd(i * 8.7) * Math.PI;
      const len = 4 + rnd(i * 9.3) * 14;
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.lineTo(x + Math.cos(a) * len, y + Math.sin(a) * len);
      ctx.stroke();
    }
    // quarry pitting
    speckle(ctx, s, 1000, 103, '#d0d0d0', '#303030', 1.8);
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function sootAlbedoMap(repeat = [3, 3]) {
  return toTexture('soot', 256, (ctx, s) => {
    mottle(ctx, s, '#1a1512', ['#2a201a', '#0d0a08', '#3a2a1e'], 1400, 0.4, 2, 16, 107);
    // greasy smoke streaks
    for (let i = 0; i < 60; i++) {
      ctx.globalAlpha = 0.14;
      ctx.fillStyle = '#4a3a2a';
      ctx.beginPath();
      ctx.ellipse(rnd(i * 2.7) * s, rnd(i * 3.9) * s, 4 + rnd(i) * 18, 20 + rnd(i * 5.1) * 60, rnd(i) * 1.5, 0, Math.PI * 2);
      ctx.fill();
    }
    ctx.globalAlpha = 1;
  }, { repeat, srgb: true });
}

// ============================================================ effects
/** Soft radial sprite used for smoke / dust billboards (alpha in .a). */
export function smokeSpriteMap() {
  return toTexture('smokeSprite', 256, (ctx, s) => {
    const g = gradient(ctx, 'radial', s / 2, s / 2, 0, s / 2, s / 2, s / 2);
    g.addColorStop(0, 'rgba(255,255,255,0.85)');
    g.addColorStop(0.35, 'rgba(255,255,255,0.45)');
    g.addColorStop(0.72, 'rgba(255,255,255,0.12)');
    g.addColorStop(1, 'rgba(255,255,255,0)');
    ctx.fillStyle = g;
    ctx.fillRect(0, 0, s, s);
    // billow lumps so the sprite is not a perfect ball
    for (let i = 0; i < 26; i++) {
      const x = s / 2 + (rnd(i * 3.1) - 0.5) * s * 0.6;
      const y = s / 2 + (rnd(i * 5.7) - 0.5) * s * 0.6;
      const r = s * (0.08 + rnd(i * 7.3) * 0.16);
      const b = gradient(ctx, 'radial', x, y, 0, x, y, r);
      b.addColorStop(0, 'rgba(255,255,255,0.16)');
      b.addColorStop(1, 'rgba(255,255,255,0)');
      ctx.fillStyle = b;
      ctx.beginPath(); ctx.arc(x, y, r, 0, Math.PI * 2); ctx.fill();
    }
  }, { repeat: [1, 1] });
}

/** A twig / thorn silhouette for acacia canopies (alpha). */
export function thornSpriteMap() {
  return toTexture('thorn', 128, (ctx, s) => {
    ctx.clearRect(0, 0, s, s);
    ctx.strokeStyle = '#2c2418';
    ctx.lineCap = 'round';
    for (let i = 0; i < 26; i++) {
      ctx.globalAlpha = 0.55;
      ctx.lineWidth = 0.8 + rnd(i * 2.3) * 1.5;
      const x = s * 0.5 + (rnd(i * 3.7) - 0.5) * s * 0.6;
      const y = s * 0.5 + (rnd(i * 5.1) - 0.5) * s * 0.6;
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.lineTo(x + (rnd(i * 6.7) - 0.5) * 26, y + (rnd(i * 8.1) - 0.5) * 26);
      ctx.stroke();
    }
    ctx.globalAlpha = 1;
  }, { repeat: [1, 1] });
}

// ============================================================ parchment UI
/**
 * The parchment sheet behind the HUD / scroll map. Painted once and drawn
 * into the radar canvas each frame (cheap: one drawImage).
 */
export function parchmentCanvas() {
  return makeCanvas('parchment', 512, (ctx, s) => {
    const g = gradient(ctx, 'radial', s * 0.5, s * 0.45, s * 0.1, s * 0.5, s * 0.5, s * 0.75);
    g.addColorStop(0, '#efe0bd');
    g.addColorStop(0.55, '#e3cfa6');
    g.addColorStop(1, '#c9ad7c');
    ctx.fillStyle = g;
    ctx.fillRect(0, 0, s, s);
    // fibres
    for (let i = 0; i < 900; i++) {
      ctx.globalAlpha = 0.05 + rnd(i * 1.3) * 0.08;
      ctx.strokeStyle = rnd(i * 2.9) > 0.5 ? '#8a6a42' : '#fff4d8';
      ctx.lineWidth = 0.7;
      const x = rnd(i * 3.1) * s;
      const y = rnd(i * 4.7) * s;
      const len = 4 + rnd(i * 5.3) * 18;
      const a = rnd(i * 6.1) * Math.PI;
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.lineTo(x + Math.cos(a) * len, y + Math.sin(a) * len);
      ctx.stroke();
    }
    // age stains
    for (let i = 0; i < 34; i++) {
      ctx.globalAlpha = 0.05 + rnd(i * 2.1) * 0.07;
      ctx.fillStyle = '#7a4c22';
      ctx.beginPath();
      ctx.arc(rnd(i * 3.3) * s, rnd(i * 4.1) * s, 8 + rnd(i * 5.7) * 48, 0, Math.PI * 2);
      ctx.fill();
    }
    // burnt deckle edge
    ctx.globalAlpha = 0.35;
    ctx.strokeStyle = '#6a4a26';
    ctx.lineWidth = 10;
    ctx.strokeRect(4, 4, s - 8, s - 8);
    ctx.globalAlpha = 1;
  });
}

// ============================================================ water
/**
 * Tiling ripple height field for the Brook Besor. Two scales of sine
 * interference plus small chop; scrolled in opposite directions in the
 * zone's update loop so the surface keeps moving without a normal map.
 */
export function rippleBumpMap(repeat = [6, 2]) {
  return toTexture('ripple', 256, (ctx, s) => {
    // headless stubs have no ImageData — fall back to a painted field
    const img = typeof ctx.createImageData === 'function' ? ctx.createImageData(s, s) : null;
    if (!img || !img.data) {
      mottle(ctx, s, '#808080', ['#6a6a6a', '#969696'], 900, 0.4, 2, 12, 131);
      return;
    }
    for (let y = 0; y < s; y++) {
      for (let x = 0; x < s; x++) {
        const u = (x / s) * Math.PI * 2;
        const v = (y / s) * Math.PI * 2;
        let h = Math.sin(u * 3 + Math.sin(v * 2) * 1.4) * 0.5;
        h += Math.sin(v * 4 - u * 1.5) * 0.3;
        h += Math.sin((u + v) * 7) * 0.12;
        h += Math.sin(u * 11 - v * 5) * 0.06;
        const val = Math.max(0, Math.min(255, 128 + h * 96));
        const i = (y * s + x) * 4;
        img.data[i] = val;
        img.data[i + 1] = val;
        img.data[i + 2] = val;
        img.data[i + 3] = 255;
      }
    }
    ctx.putImageData(img, 0, 0);
    // foam flecks
    for (let i = 0; i < 220; i++) {
      ctx.globalAlpha = 0.3;
      ctx.fillStyle = '#ffffff';
      ctx.fillRect(rnd(i * 3.1) * s, rnd(i * 4.7) * s, 2 + rnd(i * 6.3) * 6, 1);
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}
