/**
 * canvasText.js — tiny canvas-texture sprite labels (well under the 1024² cap).
 */
import * as THREE from 'three';

/**
 * Create a floating text sprite.
 * @param {string} text
 * @param {{fontSize?:number, color?:string, bg?:string, padding?:number}} opts
 */
export function makeTextSprite(text, opts = {}) {
  const fontSize = opts.fontSize ?? 44;
  const padding = opts.padding ?? 24;

  const canvas = document.createElement('canvas');
  const ctx = canvas.getContext('2d');
  ctx.font = `600 ${fontSize}px Georgia, serif`;

  const metrics = ctx.measureText(text);
  canvas.width = THREE.MathUtils.ceilPowerOfTwo(Math.min(512, metrics.width + padding * 2));
  canvas.height = THREE.MathUtils.ceilPowerOfTwo(fontSize + padding * 2);

  const ctx2 = canvas.getContext('2d');
  ctx2.font = `600 ${fontSize}px Georgia, serif`;
  ctx2.textAlign = 'center';
  ctx2.textBaseline = 'middle';

  if (opts.bg) {
    ctx2.fillStyle = opts.bg;
    const r = 18;
    const w = canvas.width;
    const h = canvas.height;
    ctx2.beginPath();
    ctx2.moveTo(r, 0);
    ctx2.arcTo(w, 0, w, h, r);
    ctx2.arcTo(w, h, 0, h, r);
    ctx2.arcTo(0, h, 0, 0, r);
    ctx2.arcTo(0, 0, w, 0, r);
    ctx2.closePath();
    ctx2.fill();
  }

  ctx2.fillStyle = opts.color ?? '#eaf6ff';
  ctx2.shadowColor = 'rgba(0,0,0,0.55)';
  ctx2.shadowBlur = 8;
  ctx2.fillText(text, canvas.width / 2, canvas.height / 2);

  const texture = new THREE.CanvasTexture(canvas);
  texture.colorSpace = THREE.SRGBColorSpace;
  texture.minFilter = THREE.LinearFilter;

  const material = new THREE.SpriteMaterial({
    map: texture,
    transparent: true,
    depthWrite: false,
    depthTest: true,
  });

  const sprite = new THREE.Sprite(material);
  const aspect = canvas.width / canvas.height;
  const worldHeight = 1.5;
  sprite.scale.set(worldHeight * aspect, worldHeight, 1);
  return sprite;
}
