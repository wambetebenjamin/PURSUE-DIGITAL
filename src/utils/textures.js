/**
 * textures.js — procedural canvas surface maps.
 *
 * Every texture is painted at runtime on a ≤512² canvas (well under the
 * 1024² budget) from seeded noise and stipple passes — no image downloads.
 * Each call returns a cached THREE.CanvasTexture with wrap/repeat set.
 */
import * as THREE from 'three';
import { seededRandom } from './math.js';

const canvasCache = new Map();
const texCache = new Map();

function paint(key, size, painter) {
  if (canvasCache.has(key)) return canvasCache.get(key);
  const canvas = document.createElement('canvas');
  canvas.width = size;
  canvas.height = size;
  const ctx = canvas.getContext('2d');
  painter(ctx, size);
  canvasCache.set(key, canvas);
  return canvas;
}

/** Scatter soft mottled spots over a base color (the workhorse). */
function mottle(ctx, size, base, spots, count, alpha, rMin, rMax, seed = 1) {
  ctx.fillStyle = base;
  ctx.fillRect(0, 0, size, size);
  for (let i = 0; i < count; i++) {
    const r = rMin + seededRandom(seed * 13 + i * 2.7) * (rMax - rMin);
    const x = seededRandom(seed * 7 + i * 3.1) * size;
    const y = seededRandom(seed * 11 + i * 1.9) * size;
    ctx.globalAlpha = alpha * (0.4 + seededRandom(seed + i) * 0.6);
    ctx.fillStyle = spots[Math.floor(seededRandom(seed * 3 + i * 5.3) * spots.length)];
    ctx.beginPath();
    ctx.arc(x, y, r, 0, Math.PI * 2);
    ctx.fill();
  }
  ctx.globalAlpha = 1;
}

function tex(key, size, painter, { repeat = [1, 1], aniso = 4 } = {}) {
  const cacheKey = `${key}@${repeat[0]}x${repeat[1]}`;
  if (texCache.has(cacheKey)) return texCache.get(cacheKey);
  const t = new THREE.CanvasTexture(paint(key, size, painter));
  t.wrapS = t.wrapT = THREE.RepeatWrapping;
  t.repeat.set(repeat[0], repeat[1]);
  t.anisotropy = aniso;
  texCache.set(cacheKey, t);
  return t;
}

// ------------------------------------------------------------- grounds
export function sandTexture(repeat = [10, 10]) {
  return tex('sand', 512, (ctx, s) => {
    mottle(ctx, s, '#c9a86c', ['#b8975c', '#d9be86', '#a8874f', '#e0c896'], 2600, 0.35, 1, 7, 2);
    // fine grain
    for (let i = 0; i < 2200; i++) {
      ctx.globalAlpha = 0.12 + seededRandom(i * 1.7) * 0.2;
      ctx.fillStyle = seededRandom(i * 2.3) > 0.5 ? '#8a7358' : '#efe0b8';
      ctx.fillRect(seededRandom(i * 3.7) * s, seededRandom(i * 4.1) * s, 1.6, 1.6);
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function grassTexture(repeat = [12, 12]) {
  return tex('grass', 512, (ctx, s) => {
    mottle(ctx, s, '#6d7c3e', ['#55662f', '#7d8f48', '#8a9a52', '#4c5c28'], 2800, 0.4, 1, 8, 5);
    for (let i = 0; i < 1600; i++) {
      const x = seededRandom(i * 2.9) * s;
      const y = seededRandom(i * 3.3) * s;
      ctx.globalAlpha = 0.25;
      ctx.strokeStyle = seededRandom(i * 1.3) > 0.5 ? '#95a65c' : '#5a6b32';
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.lineTo(x + (seededRandom(i * 5.1) - 0.5) * 4, y - 6 - seededRandom(i * 6.7) * 8);
      ctx.stroke();
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function dirtTexture(repeat = [10, 10]) {
  return tex('dirt', 512, (ctx, s) => {
    mottle(ctx, s, '#6b543c', ['#59452f', '#7d6446', '#8a6f4e', '#4a3826'], 2600, 0.4, 1, 8, 8);
  }, { repeat });
}

export function ashTexture(repeat = [10, 10]) {
  return tex('ash', 512, (ctx, s) => {
    mottle(ctx, s, '#322c28', ['#241f1c', '#453a31', '#3a322c', '#1c1714'], 2800, 0.45, 1, 8, 11);
    // ember flecks
    for (let i = 0; i < 60; i++) {
      ctx.globalAlpha = 0.5;
      ctx.fillStyle = seededRandom(i * 9.1) > 0.5 ? '#7a3c1c' : '#5a2c14';
      ctx.fillRect(seededRandom(i * 3.1) * s, seededRandom(i * 5.7) * s, 2.5, 2.5);
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function stoneTexture(repeat = [6, 6], base = '#cdbb99', dark = '#a8946e', light = '#e2d2ac') {
  return tex(`stone-${base}`, 512, (ctx, s) => {
    mottle(ctx, s, base, [dark, light, base], 1800, 0.35, 2, 14, 17);
    // chisel pocks
    for (let i = 0; i < 380; i++) {
      ctx.globalAlpha = 0.18;
      ctx.fillStyle = dark;
      ctx.fillRect(seededRandom(i * 4.3) * s, seededRandom(i * 6.1) * s,
        2 + seededRandom(i * 2.2) * 5, 1 + seededRandom(i * 3.9) * 3);
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function basaltTexture(repeat = [8, 8]) {
  return tex('basalt', 512, (ctx, s) => {
    mottle(ctx, s, '#3a3d42', ['#2c2f34', '#4a4e54', '#24262a', '#565b61'], 2200, 0.4, 1, 9, 23);
    for (let i = 0; i < 200; i++) {
      ctx.globalAlpha = 0.22;
      ctx.strokeStyle = '#22252a';
      const x = seededRandom(i * 2.7) * s;
      const y = seededRandom(i * 3.1) * s;
      ctx.beginPath();
      ctx.moveTo(x, y);
      ctx.lineTo(x + (seededRandom(i * 7.7) - 0.5) * 40, y + (seededRandom(i * 8.3) - 0.5) * 40);
      ctx.stroke();
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

// -------------------------------------------------------------- fabric
export function clothTexture(colorHex, repeat = [3, 3]) {
  const base = `#${colorHex.toString(16).padStart(6, '0')}`;
  return tex(`cloth-${base}`, 256, (ctx, s) => {
    mottle(ctx, s, base, [base, base], 500, 0.25, 3, 10, 29);
    // weave crosshatch
    for (let y = 0; y < s; y += 5) {
      ctx.globalAlpha = 0.16;
      ctx.fillStyle = '#000000';
      ctx.fillRect(0, y, s, 1.4);
    }
    for (let x = 0; x < s; x += 5) {
      ctx.globalAlpha = 0.1;
      ctx.fillStyle = '#ffffff';
      ctx.fillRect(x, 0, 1.2, s);
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

export function leatherTexture(colorHex, repeat = [3, 3]) {
  const base = `#${colorHex.toString(16).padStart(6, '0')}`;
  return tex(`leather-${base}`, 256, (ctx, s) => {
    mottle(ctx, s, base, ['#000000', '#ffffff'], 900, 0.12, 1, 6, 31);
  }, { repeat });
}

export function woodTexture(colorHex, repeat = [2, 2]) {
  const base = `#${colorHex.toString(16).padStart(6, '0')}`;
  return tex(`wood-${base}`, 256, (ctx, s) => {
    ctx.fillStyle = base;
    ctx.fillRect(0, 0, s, s);
    for (let i = 0; i < 44; i++) {
      const y = (i / 44) * s + (seededRandom(i * 3.3) - 0.5) * 6;
      ctx.globalAlpha = 0.18 + seededRandom(i * 1.7) * 0.22;
      ctx.strokeStyle = i % 3 === 0 ? '#000000' : '#ffffff';
      ctx.beginPath();
      ctx.moveTo(0, y);
      for (let x = 0; x <= s; x += 32) {
        ctx.lineTo(x, y + Math.sin(x * 0.03 + i) * 4);
      }
      ctx.stroke();
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}

// ---------------------------------------------------------------- skin
export function fleshTexture(hexBase = '#a06b45', repeat = [2, 2]) {
  return tex(`flesh-${hexBase}`, 256, (ctx, s) => {
    mottle(ctx, s, hexBase, ['#8a5a3c', '#b57e54', '#c28b60', '#7a4c32'], 1300, 0.28, 1, 6, 37);
    // pores / dust
    for (let i = 0; i < 700; i++) {
      ctx.globalAlpha = 0.1;
      ctx.fillStyle = seededRandom(i * 2.1) > 0.5 ? '#5a3a26' : '#d9a878';
      ctx.fillRect(seededRandom(i * 3.3) * s, seededRandom(i * 4.7) * s, 1.8, 1.8);
    }
    ctx.globalAlpha = 1;
  }, { repeat });
}
