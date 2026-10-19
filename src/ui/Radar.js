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
import { parchmentCanvas } from '../utils/surfaces.js';

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

/** Radial gradient that survives the headless canvas stub. */
function inkGradient(ctx, ...args) {
  const g = typeof ctx['createRadialGradient'] === 'function'
    ? ctx['createRadialGradient'](...args)
    : null;
  return g && typeof g.addColorStop === 'function' ? g : { addColorStop() {} };
}

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
  /** world XZ → canvas XY inside the sheet (heading-up, player at centre). */
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

  /** Ragged sheepskin sheet path — hand-torn edges, never a clean circle. */
  _sheetPath(ctx, x, y, w, h, seed = 3) {
    const jag = 0.9 + (seed % 5) * 0.16;
    ctx.beginPath();
    const steps = 18;
    for (let i = 0; i <= steps; i++) {
      const t = i / steps;
      const px = x + t * w;
      const py = y + Math.sin(t * 11 + seed) * 1.2;
      if (i === 0) ctx.moveTo(px, py); else ctx.lineTo(px, py);
    }
    for (let i = 0; i <= steps; i++) {
      const t = i / steps;
      ctx.lineTo(x + w + Math.sin(t * 9 + seed * 2) * 1.2, y + t * h);
    }
    for (let i = steps; i >= 0; i--) {
      const t = i / steps;
      ctx.lineTo(x + t * w, y + h + Math.sin(t * 12 + seed * 3) * 1.2 * jag);
    }
    for (let i = steps; i >= 0; i--) {
      const t = i / steps;
      ctx.lineTo(x + Math.sin(t * 10 + seed * 4) * 1.2, y + t * h);
    }
    ctx.closePath();
  }

  renderDisc() {
    const ctx = this.ctx;
    const W = this.canvas.width;
    const H = this.canvas.height;
    const cx = W / 2;
    const cy = H / 2;
    const pad = W * 0.055;
    const radius = Math.min(W, H) / 2 - pad;

    ctx.clearRect(0, 0, W, H);

    // --- the sheet itself: aged sheepskin, clipped to a torn edge
    ctx.save();
    this._sheetPath(ctx, pad, pad, W - pad * 2, H - pad * 2, 5);
    ctx.clip();

    try {
      ctx.drawImage(parchmentCanvas(), 0, 0, W, H);
    } catch (_e) {
      ctx.fillStyle = '#e3cfa6';
      ctx.fillRect(0, 0, W, H);
    }

    // ink wash vignette at the edges of the vellum
    const vg = inkGradient(ctx, cx, cy, radius * 0.55, cx, cy, radius * 1.15);
    vg.addColorStop(0, 'rgba(90, 62, 30, 0)');
    vg.addColorStop(1, 'rgba(70, 44, 18, 0.34)');
    ctx.fillStyle = vg;
    ctx.fillRect(0, 0, W, H);

    // --- hand-drawn district plates (ink outline, hatched interior)
    for (const plate of this.plates) {
      const p = this._toCanvas(plate.cx, plate.cz, cx, cy, radius);
      const px = (plate.w / (this.range * Math.SQRT2 * 2)) * radius;
      const pz = (plate.h / (this.range * Math.SQRT2 * 2)) * radius;
      ctx.fillStyle = 'rgba(122, 82, 34, 0.16)';
      ctx.strokeStyle = 'rgba(58, 38, 18, 0.6)';
      ctx.lineWidth = Math.max(1, W / 150);
      ctx.setLineDash([Math.max(3, W / 40), Math.max(2, W / 70)]);
      ctx.beginPath();
      ctx.roundRect(p.x - px, p.y - pz, px * 2, pz * 2, radius * 0.05);
      ctx.fill();
      ctx.stroke();
      ctx.setLineDash([]);
    }

    // --- latitude / longitude ink rules, rotating with the map
    ctx.strokeStyle = 'rgba(72, 46, 20, 0.14)';
    ctx.lineWidth = 1;
    for (let i = -2; i <= 2; i++) {
      const a = this._toCanvas(this.player.x + i * this.range * 0.5, this.player.z - this.range, cx, cy, radius);
      const b = this._toCanvas(this.player.x + i * this.range * 0.5, this.player.z + this.range, cx, cy, radius);
      ctx.beginPath(); ctx.moveTo(a.x, a.y); ctx.lineTo(b.x, b.y); ctx.stroke();
      const c = this._toCanvas(this.player.x - this.range, this.player.z + i * this.range * 0.5, cx, cy, radius);
      const d = this._toCanvas(this.player.x + this.range, this.player.z + i * this.range * 0.5, cx, cy, radius);
      ctx.beginPath(); ctx.moveTo(c.x, c.y); ctx.lineTo(d.x, d.y); ctx.stroke();
    }

    // --- objective: a charcoal-ringed wax seal
    if (this._objective) {
      const o = this._toCanvas(this._objective.x, this._objective.z, cx, cy, radius);
      const dist = Math.hypot(o.x - cx, o.y - cy);
      const clamped = dist > radius * 0.82;
      const pulse = 1 + Math.sin(this._pulse * 3.2) * 0.08;
      const size = (W / 20) * pulse;

      let bx = o.x;
      let by = o.y;
      if (clamped) {
        const k = (radius * 0.82) / dist;
        bx = cx + (o.x - cx) * k;
        by = cy + (o.y - cy) * k;
        const ang = Math.atan2(o.y - cy, o.x - cx);
        ctx.save();
        ctx.translate(bx, by);
        ctx.rotate(ang);
        ctx.fillStyle = '#7a2418';
        ctx.strokeStyle = 'rgba(42, 24, 10, 0.85)';
        ctx.lineWidth = Math.max(1, W / 140);
        ctx.beginPath();
        ctx.moveTo(size * 0.8, 0);
        ctx.lineTo(-size * 0.4, -size * 0.6);
        ctx.lineTo(-size * 0.4, size * 0.6);
        ctx.closePath();
        ctx.fill();
        ctx.stroke();
        ctx.restore();
      } else {
        // wax seal body
        ctx.save();
        ctx.translate(bx, by);
        const g = inkGradient(ctx, -size * 0.2, -size * 0.25, size * 0.1, 0, 0, size * 0.85);
        g.addColorStop(0, '#a8402a');
        g.addColorStop(0.65, '#7a2418');
        g.addColorStop(1, '#4a140c');
        ctx.fillStyle = g;
        ctx.beginPath();
        for (let i = 0; i < 9; i++) {
          const a = (i / 9) * Math.PI * 2;
          const rr = size * (0.62 + (i % 2) * 0.12);
          const x = Math.cos(a) * rr;
          const y = Math.sin(a) * rr;
          if (i === 0) ctx.moveTo(x, y); else ctx.lineTo(x, y);
        }
        ctx.closePath();
        ctx.fill();
        ctx.strokeStyle = 'rgba(40, 18, 8, 0.75)';
        ctx.lineWidth = Math.max(1, W / 160);
        ctx.stroke();
        // the brand: a palm-script mark
        ctx.fillStyle = 'rgba(240, 214, 168, 0.85)';
        ctx.font = `${Math.round(size * 0.9)}px "Frank Ruhl Libre", Georgia, serif`;
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText('\u05D0', 0, size * 0.06);
        ctx.restore();
      }
    }

    ctx.restore();

    // --- deckle edge: burnt ink line just inside the torn border
    ctx.save();
    this._sheetPath(ctx, pad, pad, W - pad * 2, H - pad * 2, 5);
    ctx.strokeStyle = 'rgba(74, 46, 18, 0.55)';
    ctx.lineWidth = Math.max(1.4, W / 90);
    ctx.stroke();
    ctx.restore();

    // --- north tick, set in a blackletter-feeling serif
    const nAng = this.heading;
    const nx = cx + Math.sin(nAng) * (radius * 0.87);
    const ny = cy - Math.cos(nAng) * (radius * 0.87);
    ctx.fillStyle = 'rgba(52, 32, 14, 0.9)';
    ctx.font = `700 ${Math.round(W / 15)}px "Frank Ruhl Libre", Georgia, serif`;
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillText('N', nx, ny);

    // --- player: an inked arrow with a red wax pin
    const pSize = W / 15;
    ctx.save();
    ctx.translate(cx, cy);
    ctx.fillStyle = '#2e2010';
    ctx.strokeStyle = 'rgba(244, 228, 194, 0.9)';
    ctx.lineWidth = Math.max(1, W / 150);
    ctx.beginPath();
    ctx.moveTo(0, -pSize);
    ctx.lineTo(pSize * 0.6, pSize * 0.7);
    ctx.lineTo(0, pSize * 0.28);
    ctx.lineTo(-pSize * 0.6, pSize * 0.7);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();
    ctx.fillStyle = '#8c2a18';
    ctx.beginPath();
    ctx.arc(0, pSize * 0.12, pSize * 0.16, 0, Math.PI * 2);
    ctx.fill();
    ctx.restore();
  }

  // ------------------------------------------------- full map (MAPS page)
  /** The pause-screen chart of the whole march, drawn on vellum. */
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

    // sheet
    ctx.save();
    this._sheetPath(ctx, 6, 6, W - 12, H - 12, 9);
    ctx.clip();
    try {
      ctx.drawImage(parchmentCanvas(), 0, 0, W, H);
    } catch (_e) {
      ctx.fillStyle = '#e3cfa6';
      ctx.fillRect(0, 0, W, H);
    }

    // ink graticule
    ctx.strokeStyle = 'rgba(90, 62, 30, 0.10)';
    ctx.lineWidth = 1;
    for (let x = 0; x < W; x += 48) {
      ctx.beginPath(); ctx.moveTo(x, 0); ctx.lineTo(x, H); ctx.stroke();
    }
    for (let y = 0; y < H; y += 48) {
      ctx.beginPath(); ctx.moveTo(0, y); ctx.lineTo(W, y); ctx.stroke();
    }

    for (const plate of this.plates) {
      const x = plate.cx * k + ox;
      const y = plate.cz * k + oy;
      const w = plate.w * k;
      const h = plate.h * k;
      ctx.fillStyle = 'rgba(122, 82, 34, 0.13)';
      ctx.strokeStyle = 'rgba(58, 38, 18, 0.62)';
      ctx.lineWidth = 2;
      ctx.setLineDash([8, 5]);
      ctx.beginPath();
      ctx.roundRect(x - w / 2, y - h / 2, w, h, 8);
      ctx.fill();
      ctx.stroke();
      ctx.setLineDash([]);

      const tag = ZONE_TAGS[plate.state];
      if (tag) {
        ctx.fillStyle = 'rgba(48, 30, 12, 0.92)';
        ctx.font = `italic 16px "EB Garamond", Georgia, serif`;
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(tag[0], x, y - h / 2 - 12);
      }

      // waypoints as ink crosses
      ctx.strokeStyle = 'rgba(76, 48, 20, 0.55)';
      ctx.lineWidth = 1.4;
      for (const p of plate.pts) {
        const wx = p.x * k + ox;
        const wy = p.z * k + oy;
        ctx.beginPath();
        ctx.moveTo(wx - 3, wy); ctx.lineTo(wx + 3, wy);
        ctx.moveTo(wx, wy - 3); ctx.lineTo(wx, wy + 3);
        ctx.stroke();
      }
    }

    // objective: wax seal with the aleph brand
    if (this._objective) {
      const x = this._objective.x * k + ox;
      const y = this._objective.z * k + oy;
      const g = inkGradient(ctx, x - 3, y - 3, 2, x, y, 13);
      g.addColorStop(0, '#a8402a');
      g.addColorStop(0.7, '#7a2418');
      g.addColorStop(1, '#4a140c');
      ctx.fillStyle = g;
      ctx.beginPath();
      for (let i = 0; i < 9; i++) {
        const a = (i / 9) * Math.PI * 2;
        const rr = 12 * (0.68 + (i % 2) * 0.12);
        const px = x + Math.cos(a) * rr;
        const py = y + Math.sin(a) * rr;
        if (i === 0) ctx.moveTo(px, py); else ctx.lineTo(px, py);
      }
      ctx.closePath();
      ctx.fill();
      ctx.fillStyle = 'rgba(240, 214, 168, 0.9)';
      ctx.font = '700 14px "Frank Ruhl Libre", Georgia, serif';
      ctx.textAlign = 'center';
      ctx.textBaseline = 'middle';
      ctx.fillText('\u05D0', x, y + 1);
    }

    // player arrow
    const px = playerPos.x * k + ox;
    const py = playerPos.z * k + oy;
    ctx.save();
    ctx.translate(px, py);
    ctx.rotate(heading);
    ctx.fillStyle = '#2e2010';
    ctx.strokeStyle = 'rgba(244, 228, 194, 0.9)';
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

    // cartouche + legend
    ctx.fillStyle = 'rgba(58, 36, 14, 0.9)';
    ctx.font = 'italic 15px "EB Garamond", Georgia, serif';
    ctx.textAlign = 'left';
    ctx.textBaseline = 'bottom';
    ctx.fillText('the march of David, from Ziklag to Gilboa', 20, H - 20);
    ctx.font = '13px "EB Garamond", Georgia, serif';
    ctx.fillStyle = 'rgba(70, 44, 18, 0.75)';
    ctx.fillText('N is up   ·   seal = the charge   ·   crosses = waypoints', 20, H - 40);
    ctx.restore();
  }
}
