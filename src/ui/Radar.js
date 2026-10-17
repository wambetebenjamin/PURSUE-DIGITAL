/**
 * Radar.js — Vice City style circular radar + the full "Map of Judah" chart.
 *
 * Interface conventions follow the classic GTA III / Vice City radar
 * (CRadar / HUD_RADARDISC) rebuilt as procedural canvas art:
 *   • heading-up rotating map with a fixed player triangle at the centre
 *   • speed-scaled zoom range (walk zoomed-in → sprint zoomed-out)
 *   • mission blips clamped to the rim with an edge chevron when off-range
 *   • a north tick on the ring, land plates drawn under the blips
 *
 * No game assets are used — every stroke is drawn at runtime. The class
 * degrades to a silent no-op when no 2D context exists (headless smoke).
 */
import * as THREE from 'three';
import { STATES } from '../constants.js';
import { ZIKLAG } from '../world/zones/ZiklagZone.js';
import { DESERT } from '../world/zones/DesertZone.js';
import { EGYPT } from '../world/zones/EgyptianZone.js';
import { CAMP } from '../world/zones/CampZone.js';
import { SPOILS } from '../world/zones/SpoilsZone.js';
import { GATE } from '../world/zones/GateZone.js';
import { GILBOA } from '../world/zones/GilboaZone.js';

/** Literary district tags, the zone-name banners of the march. */
export const ZONE_TAGS = Object.freeze({
  [STATES.ZIKLAG]: ['Ziklag', 'a town in the Negev'],
  [STATES.DESERT]: ['The Brook Besor', 'the desert march'],
  [STATES.EGYPTIAN]: ['The Egyptian’s Shade', 'the road south'],
  [STATES.CAMP]: ['The Amalekite Camp', 'the valley of the raiders'],
  [STATES.SPOILS]: ['The Spoils at Besor', 'the dividing line'],
  [STATES.GATE]: ['The Gate of Ziklag', 'tribute to Judah'],
  [STATES.GILBOA]: ['Mount Gilboa', 'the fall of Saul'],
});

const ZONE_CONFIGS = {
  [STATES.ZIKLAG]: ZIKLAG,
  [STATES.DESERT]: DESERT,
  [STATES.EGYPTIAN]: EGYPT,
  [STATES.CAMP]: CAMP,
  [STATES.SPOILS]: SPOILS,
  [STATES.GATE]: GATE,
  [STATES.GILBOA]: GILBOA,
};

// walk a zone config for every {x, z}-style point to bound its footprint
function collectPoints(node, out, depth = 0) {
  if (!node || depth > 4 || out.length > 200) return;
  if (typeof node === 'object' && typeof node.x === 'number' && typeof node.z === 'number') {
    out.push({ x: node.x, z: node.z });
    return;
  }
  if (Array.isArray(node)) {
    for (const item of node) collectPoints(item, out, depth + 1);
    return;
  }
  if (typeof node === 'object') {
    for (const key of Object.keys(node)) {
      // colliders and builders hold hundreds of points — skip bulky leaves
      if (key === 'colliders' || typeof node[key] === 'function') continue;
      collectPoints(node[key], out, depth + 1);
    }
  }
}

function buildPlates() {
  const plates = [];
  for (const [state, cfg] of Object.entries(ZONE_CONFIGS)) {
    const pts = [];
    collectPoints(cfg, pts);
    if (!pts.length) continue;
    let minX = Infinity; let maxX = -Infinity; let minZ = Infinity; let maxZ = -Infinity;
    for (const p of pts) {
      minX = Math.min(minX, p.x); maxX = Math.max(maxX, p.x);
      minZ = Math.min(minZ, p.z); maxZ = Math.max(maxZ, p.z);
    }
    const pad = 10;
    plates.push({
      state,
      cx: (minX + maxX) / 2,
      cz: (minZ + maxZ) / 2,
      w: maxX - minX + pad * 2,
      h: maxZ - minZ + pad * 2,
      pts,
    });
  }
  return plates;
}

const _v = new THREE.Vector2();

export class Radar {
  /**
   * @param {HTMLElement} host container div; a canvas is created inside it
   */
  constructor(host) {
    this.host = host;
    this.canvas = document.createElement('canvas');
    this.ctx = (typeof this.canvas.getContext === 'function')
      ? this.canvas.getContext('2d')
      : null;
    this.enabled = Boolean(this.ctx);
    if (this.host && this.host.appendChild) this.host.appendChild(this.canvas);

    this.plates = buildPlates();
    this.player = { x: 0, z: 0 };
    this.heading = 0; // radians, 0 = north = −Z
    this.speedRatio = 0;
    this.range = 40; // world units visible from centre to rim edge
    this._objective = null;
    this._pulse = 0;

    if (this.enabled) this.resize();
  }

  /** Mission blip, world XZ (the m_Message / BLIP_COORD analog). */
  setObjective(x, z) {
    this._objective = { x, z };
  }

  /** Called each frame while the game is running. */
  update(dt, playerPos, heading, speedRatio = 0) {
    if (!this.enabled) return;
    this.player.x = playerPos.x;
    this.player.z = playerPos.z;
    this.heading = heading;
    this.speedRatio = Math.min(1, Math.max(0, speedRatio));
    this._pulse += dt;

    // CRadar speed-zoom: range grows as the player runs (120→350 in GTA units)
    const targetRange = 26 + this.speedRatio * 44;
    this.range += (targetRange - this.range) * Math.min(1, dt * 2.2);

    this.renderDisc();
  }

  resize() {
    if (!this.enabled || !this.host) return;
    const size = this.host.clientWidth || 150;
    const dpr = Math.min(window.devicePixelRatio || 1, 2);
    this.canvas.width = Math.round(size * dpr);
    this.canvas.height = Math.round(size * dpr);
    this.canvas.style.width = `${size}px`;
    this.canvas.style.height = `${size}px`;
    this.renderDisc();
  }

  // ------------------------------------------------------------ drawing
  /** world XZ → canvas XY inside the disc (heading-up, player at centre). */
  _toCanvas(wx, wz, cx, cy, radius) {
    const dx = wx - this.player.x;
    const dz = wz - this.player.z;
    const s = Math.sin(-this.heading);
    const c = Math.cos(-this.heading);
    const rx = dx * c - dz * s;
    const rz = dx * s + dz * c;
    const k = radius / (this.range * Math.SQRT2);
    _v.set(cx + rx * k, cy + rz * k);
    return _v;
  }

  renderDisc() {
    const ctx = this.ctx;
    const W = this.canvas.width;
    const H = this.canvas.height;
    const cx = W / 2;
    const cy = H / 2;
    const radius = Math.min(W, H) / 2 - 6;

    ctx.clearRect(0, 0, W, H);

    // --- disc body (radardisc sprite) ---
    ctx.save();
    ctx.beginPath();
    ctx.arc(cx, cy, radius, 0, Math.PI * 2);
    ctx.clip();

    ctx.fillStyle = '#141022';
    ctx.fillRect(0, 0, W, H);

    // land plates under the blips
    for (const plate of this.plates) {
      const p = this._toCanvas(plate.cx, plate.cz, cx, cy, radius);
      const px = (plate.w / (this.range * Math.SQRT2 * 2)) * radius;
      const pz = (plate.h / (this.range * Math.SQRT2 * 2)) * radius;
      ctx.fillStyle = 'rgba(196, 154, 92, 0.28)';
      ctx.strokeStyle = 'rgba(255, 214, 150, 0.35)';
      ctx.lineWidth = Math.max(1, W / 150);
      ctx.beginPath();
      ctx.roundRect(p.x - px, p.y - pz, px * 2, pz * 2, radius * 0.12);
      ctx.fill();
      ctx.stroke();
    }

    // heading grid — faint cross-rotating with the map
    ctx.strokeStyle = 'rgba(255, 255, 255, 0.07)';
    ctx.lineWidth = 1;
    for (let i = -2; i <= 2; i++) {
      const a = this._toCanvas(this.player.x + i * this.range * 0.5, this.player.z - this.range, cx, cy, radius);
      const b = this._toCanvas(this.player.x + i * this.range * 0.5, this.player.z + this.range, cx, cy, radius);
      ctx.beginPath(); ctx.moveTo(a.x, a.y); ctx.lineTo(b.x, b.y); ctx.stroke();
      const c = this._toCanvas(this.player.x - this.range, this.player.z + i * this.range * 0.5, cx, cy, radius);
      const d = this._toCanvas(this.player.x + this.range, this.player.z + i * this.range * 0.5, cx, cy, radius);
      ctx.beginPath(); ctx.moveTo(c.x, c.y); ctx.lineTo(d.x, d.y); ctx.stroke();
    }

    // --- objective blip (square coord blip, pulsing) ---
    if (this._objective) {
      const o = this._toCanvas(this._objective.x, this._objective.z, cx, cy, radius);
      const dist = Math.hypot(o.x - cx, o.y - cy);
      const clamped = dist > radius * 0.86;
      const pulse = 1 + Math.sin(this._pulse * 4) * 0.12;
      const size = (W / 22) * pulse;

      let bx = o.x;
      let by = o.y;
      if (clamped) {
        const k = (radius * 0.86) / dist;
        bx = cx + (o.x - cx) * k;
        by = cy + (o.y - cy) * k;
      }
      ctx.fillStyle = '#ff2e88';
      ctx.strokeStyle = 'rgba(255, 255, 255, 0.85)';
      ctx.lineWidth = Math.max(1, W / 130);
      if (clamped) {
        // edge chevron pointing toward the blip
        const ang = Math.atan2(o.y - cy, o.x - cx);
        ctx.save();
        ctx.translate(bx, by);
        ctx.rotate(ang);
        ctx.beginPath();
        ctx.moveTo(size * 0.7, 0);
        ctx.lineTo(-size * 0.45, -size * 0.6);
        ctx.lineTo(-size * 0.45, size * 0.6);
        ctx.closePath();
        ctx.fill();
        ctx.stroke();
        ctx.restore();
      } else {
        ctx.beginPath();
        ctx.rect(bx - size / 2, by - size / 2, size, size);
        ctx.fill();
        ctx.stroke();
      }
    }

    ctx.restore();

    // --- rim: neon ring (radardisc border) ---
    ctx.beginPath();
    ctx.arc(cx, cy, radius, 0, Math.PI * 2);
    ctx.lineWidth = Math.max(2, W / 42);
    ctx.strokeStyle = 'rgba(47, 224, 255, 0.55)';
    ctx.stroke();
    ctx.beginPath();
    ctx.arc(cx, cy, radius - Math.max(1, W / 90), 0, Math.PI * 2);
    ctx.lineWidth = Math.max(1.5, W / 70);
    ctx.strokeStyle = 'rgba(255, 46, 136, 0.5)';
    ctx.stroke();

    // --- north tick on the ring (RADAR_SPRITE_NORTH) ---
    const nAng = this.heading; // north (−Z) rotates opposite the map
    const nx = cx + Math.sin(nAng) * (radius - 2);
    const ny = cy - Math.cos(nAng) * (radius - 2);
    ctx.fillStyle = '#ffd264';
    ctx.font = `bold ${Math.round(W / 8)}px Inter, sans-serif`;
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillText('N', nx, ny);

    // --- player triangle, fixed at centre pointing up ---
    const pSize = W / 13;
    ctx.save();
    ctx.translate(cx, cy);
    ctx.fillStyle = '#f5efe2';
    ctx.strokeStyle = 'rgba(20, 12, 32, 0.9)';
    ctx.lineWidth = Math.max(1, W / 120);
    ctx.beginPath();
    ctx.moveTo(0, -pSize);
    ctx.lineTo(pSize * 0.62, pSize * 0.72);
    ctx.lineTo(0, pSize * 0.3);
    ctx.lineTo(-pSize * 0.62, pSize * 0.72);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();
    ctx.restore();
  }

  // ------------------------------------------------- full map (MAPS page)
  /** The pause-screen chart of the whole march. */
  renderFullMap(canvas, playerPos, heading) {
    if (!canvas || typeof canvas.getContext !== 'function') return;
    const ctx = canvas.getContext('2d');
    const W = canvas.width;
    const H = canvas.height;

    // world bounds across all plates
    let minX = Infinity; let maxX = -Infinity; let minZ = Infinity; let maxZ = -Infinity;
    for (const plate of this.plates) {
      minX = Math.min(minX, plate.cx - plate.w / 2);
      maxX = Math.max(maxX, plate.cx + plate.w / 2);
      minZ = Math.min(minZ, plate.cz - plate.h / 2);
      maxZ = Math.max(maxZ, plate.cz + plate.h / 2);
    }
    const spanX = maxX - minX;
    const spanZ = maxZ - minZ;
    const k = Math.min(W / (spanX + 30), H / (spanZ + 30));
    const ox = W / 2 - ((minX + maxX) / 2) * k;
    const oy = H / 2 - ((minZ + maxZ) / 2) * k;

    ctx.clearRect(0, 0, W, H);
    ctx.fillStyle = '#141022';
    ctx.fillRect(0, 0, W, H);

    for (const plate of this.plates) {
      const x = plate.cx * k + ox;
      const y = plate.cz * k + oy;
      const w = plate.w * k;
      const h = plate.h * k;
      ctx.fillStyle = 'rgba(196, 154, 92, 0.32)';
      ctx.strokeStyle = 'rgba(255, 214, 150, 0.45)';
      ctx.lineWidth = 2;
      ctx.beginPath();
      ctx.roundRect(x - w / 2, y - h / 2, w, h, 12);
      ctx.fill();
      ctx.stroke();

      const tag = ZONE_TAGS[plate.state];
      if (tag) {
        ctx.fillStyle = 'rgba(255, 236, 200, 0.95)';
        ctx.font = '600 13px Inter, sans-serif';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(tag[0], x, y - h / 2 - 12);
      }

      // waypoints as small dots
      ctx.fillStyle = 'rgba(255, 214, 150, 0.5)';
      for (const p of plate.pts) {
        ctx.beginPath();
        ctx.arc(p.x * k + ox, p.y !== undefined ? p.z * k + oy : p.z * k + oy, 2.5, 0, Math.PI * 2);
        ctx.fill();
      }
    }

    // objective blip
    if (this._objective) {
      const x = this._objective.x * k + ox;
      const y = this._objective.z * k + oy;
      ctx.fillStyle = '#ff2e88';
      ctx.strokeStyle = '#fff';
      ctx.lineWidth = 2;
      ctx.beginPath();
      ctx.rect(x - 7, y - 7, 14, 14);
      ctx.fill();
      ctx.stroke();
    }

    // player arrow (rotated with heading)
    const px = playerPos.x * k + ox;
    const py = playerPos.z * k + oy;
    ctx.save();
    ctx.translate(px, py);
    ctx.rotate(heading);
    ctx.fillStyle = '#f5efe2';
    ctx.strokeStyle = '#141022';
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.moveTo(0, -12);
    ctx.lineTo(8, 9);
    ctx.lineTo(0, 4);
    ctx.lineTo(-8, 9);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();
    ctx.restore();

    // legend
    ctx.fillStyle = 'rgba(255, 236, 200, 0.8)';
    ctx.font = '11px Inter, sans-serif';
    ctx.textAlign = 'left';
    ctx.textBaseline = 'bottom';
    ctx.fillText('✦ your path  ·  ■ objective  ·  N is up', 14, H - 12);
  }
}
