/**
 * icons.js — the item icon library.
 *
 * The reference point is a commercial fantasy-RPG icon *system* (squared,
 * centred, rim-lit objects on transparency, one material language shared by
 * every tile, rarity expressed by the frame rather than the art). We do not
 * ship that pack — its licence and its 512² PNG previews are not ours to
 * redistribute — so every icon here is painted procedurally at runtime in the
 * same visual grammar: a single centred object, three-quarter view, one warm
 * key light at the upper left, a cool bounce at the lower right, a contact
 * shadow, and an ink outline that keeps the silhouette readable at 56 px.
 *
 * Drop-in path for a licensed pack: give an item a `file` (see items.js) and
 * the satchel will render `<img src>` instead of calling the painter.
 *
 * All painters are `(ctx, S) => void` with S = canvas size; they assume the
 * canvas is square and transparent.
 */

// --------------------------------------------------------------- palette
const INK = 'rgba(28, 16, 8, 0.85)';
const WARM = 'rgba(255, 232, 186, 0.85)';
const COOL = 'rgba(60, 70, 90, 0.35)';

const BRONZE = ['#f0d29a', '#c39a52', '#8a6628', '#4a3212'];
const IRON = ['#e2e6ea', '#a8b0b8', '#6a727c', '#333a42'];
const CLAY = ['#d9a077', '#b87850', '#8a4c30', '#4c2416'];
const LEATHER = ['#b98c5a', '#8a6038', '#5a3c20', '#2e1e0e'];
const LINEN = ['#f4e8cc', '#ddc9a2', '#b9a077', '#7a6540'];
const GOLD = ['#ffeeb0', '#e0bc5c', '#b0862c', '#5a420e'];
const SILVER = ['#f2f6fa', '#c8d2dc', '#8a97a4', '#454e58'];

// --------------------------------------------------------------- helpers
function lin(ctx, x0, y0, x1, y1, stops) {
  const g = typeof ctx['createLinearGradient'] === 'function'
    ? ctx['createLinearGradient'](x0, y0, x1, y1)
    : null;
  const grad = g && typeof g.addColorStop === 'function' ? g : { addColorStop() {} };
  const n = stops.length;
  for (let i = 0; i < n; i++) grad.addColorStop(i / (n - 1), stops[i]);
  return grad;
}

function rad(ctx, x, y, r0, r1, stops) {
  const g = typeof ctx['createRadialGradient'] === 'function'
    ? ctx['createRadialGradient'](x, y, r0, x, y, r1)
    : null;
  const grad = g && typeof g.addColorStop === 'function' ? g : { addColorStop() {} };
  const n = stops.length;
  for (let i = 0; i < n; i++) grad.addColorStop(i / (n - 1), stops[i]);
  return grad;
}

/** Contact shadow under the object. */
function ground(ctx, S, y = 0.84, w = 0.3, a = 0.35) {
  ctx.fillStyle = rad(ctx, S * 0.5, S * y, 1, S * w, [
    `rgba(30,16,6,${a})`, 'rgba(30,16,6,0)',
  ]);
  ctx.beginPath();
  ctx.ellipse(S * 0.5, S * y, S * w, S * w * 0.26, 0, 0, Math.PI * 2);
  ctx.fill();
}

/** Warm key light from the upper left. */
function rim(ctx, S, x = 0.36, y = 0.3, r = 0.22) {
  ctx.fillStyle = rad(ctx, S * x, S * y, 1, S * r, [
    'rgba(255,244,214,0.42)', 'rgba(255,244,214,0)',
  ]);
  ctx.beginPath();
  ctx.ellipse(S * x, S * y, S * r, S * r * 0.75, -0.4, 0, Math.PI * 2);
  ctx.fill();
}

function outline(ctx, w = 0.008) {
  ctx.strokeStyle = INK;
  ctx.lineWidth = Math.max(1, w);
  ctx.stroke();
}

/** A symmetrical pointed blade (dagger / spearhead). */
function blade(ctx, S, cx, cy, w, h, palette) {
  ctx.fillStyle = lin(ctx, S * (cx - w), 0, S * (cx + w), 0, palette);
  ctx.beginPath();
  ctx.moveTo(S * cx, S * (cy - h));
  ctx.lineTo(S * (cx + w), S * (cy - h * 0.28));
  ctx.lineTo(S * (cx + w * 0.55), S * (cy + h * 0.8));
  ctx.lineTo(S * (cx - w * 0.55), S * (cy + h * 0.8));
  ctx.lineTo(S * (cx - w), S * (cy - h * 0.28));
  ctx.closePath();
  ctx.fill();
  outline(ctx, S * 0.008);
  // fuller
  ctx.strokeStyle = COOL;
  ctx.lineWidth = S * 0.012;
  ctx.beginPath();
  ctx.moveTo(S * cx, S * (cy - h * 0.85));
  ctx.lineTo(S * cx, S * (cy + h * 0.7));
  ctx.stroke();
}

function rivets(ctx, S, pts) {
  ctx.fillStyle = WARM;
  for (const [x, y] of pts) {
    ctx.beginPath();
    ctx.arc(S * x, S * y, S * 0.012, 0, Math.PI * 2);
    ctx.fill();
  }
}

// --------------------------------------------------------------- painters
export const ICON_PAINTERS = {
  /** Linen headcloth, knotted at the nape. */
  headcloth(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = lin(ctx, S * 0.25, S * 0.25, S * 0.8, S * 0.85, LINEN);
    ctx.beginPath();
    ctx.moveTo(S * 0.5, S * 0.17);
    ctx.quadraticCurveTo(S * 0.84, S * 0.27, S * 0.79, S * 0.6);
    ctx.quadraticCurveTo(S * 0.72, S * 0.79, S * 0.5, S * 0.8);
    ctx.quadraticCurveTo(S * 0.28, S * 0.79, S * 0.21, S * 0.6);
    ctx.quadraticCurveTo(S * 0.16, S * 0.27, S * 0.5, S * 0.17);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // folds
    ctx.strokeStyle = 'rgba(120,96,58,0.45)';
    ctx.lineWidth = S * 0.01;
    for (let i = 0; i < 4; i++) {
      ctx.beginPath();
      ctx.moveTo(S * (0.3 + i * 0.13), S * 0.26);
      ctx.quadraticCurveTo(S * (0.34 + i * 0.12), S * 0.52, S * (0.28 + i * 0.14), S * 0.76);
      ctx.stroke();
    }
    // knot
    ctx.fillStyle = lin(ctx, S * 0.42, S * 0.7, S * 0.62, S * 0.9, LINEN);
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.82, S * 0.09, S * 0.055, 0.1, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.007);
    rim(ctx, S, 0.38, 0.3, 0.26);
  },

  /** Leather lamellar vest, scale rows laced to a backing. */
  lamellar(ctx, S) {
    ground(ctx, S);
    // torso shell
    ctx.fillStyle = lin(ctx, S * 0.22, S * 0.24, S * 0.8, S * 0.82, LEATHER);
    ctx.beginPath();
    ctx.moveTo(S * 0.32, S * 0.2);
    ctx.quadraticCurveTo(S * 0.5, S * 0.26, S * 0.68, S * 0.2);
    ctx.lineTo(S * 0.78, S * 0.5);
    ctx.quadraticCurveTo(S * 0.76, S * 0.82, S * 0.5, S * 0.86);
    ctx.quadraticCurveTo(S * 0.24, S * 0.82, S * 0.22, S * 0.5);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // scale rows
    for (let row = 0; row < 4; row++) {
      const y = 0.32 + row * 0.135;
      for (let col = 0; col < 3; col++) {
        const x = 0.34 + col * 0.16;
        ctx.fillStyle = lin(ctx, S * x, S * y, S * (x + 0.13), S * (y + 0.1), BRONZE);
        ctx.beginPath();
        ctx.moveTo(S * (x - 0.07), S * y);
        ctx.lineTo(S * (x + 0.07), S * y);
        ctx.lineTo(S * (x + 0.055), S * (y + 0.1));
        ctx.quadraticCurveTo(S * x, S * (y + 0.135), S * (x - 0.055), S * (y + 0.1));
        ctx.closePath();
        ctx.fill();
        ctx.strokeStyle = 'rgba(40,24,8,0.55)';
        ctx.lineWidth = S * 0.006;
        ctx.stroke();
      }
    }
    rivets(ctx, S, [[0.36, 0.3], [0.64, 0.3], [0.5, 0.62]]);
    rim(ctx, S, 0.36, 0.34, 0.24);
  },

  /** Laced leather bracer. */
  bracers(ctx, S) {
    ground(ctx, S);
    ctx.save();
    ctx.translate(S * 0.5, S * 0.52);
    ctx.rotate(-0.16);
    ctx.fillStyle = lin(ctx, -S * 0.2, 0, S * 0.2, 0, LEATHER);
    ctx.beginPath();
    ctx.roundRect(-S * 0.19, -S * 0.3, S * 0.38, S * 0.6, S * 0.06);
    ctx.fill();
    outline(ctx, S * 0.009);
    // lacing
    ctx.strokeStyle = 'rgba(232,208,160,0.75)';
    ctx.lineWidth = S * 0.011;
    for (let i = 0; i < 4; i++) {
      const y = -S * 0.22 + i * S * 0.14;
      ctx.beginPath();
      ctx.moveTo(-S * 0.15, y);
      ctx.lineTo(S * 0.15, y + S * 0.05);
      ctx.stroke();
      ctx.beginPath();
      ctx.moveTo(-S * 0.15, y + S * 0.05);
      ctx.lineTo(S * 0.15, y);
      ctx.stroke();
    }
    // bronze edge band
    ctx.fillStyle = lin(ctx, -S * 0.2, 0, S * 0.2, 0, BRONZE);
    ctx.fillRect(-S * 0.2, -S * 0.33, S * 0.4, S * 0.06);
    ctx.fillRect(-S * 0.2, S * 0.27, S * 0.4, S * 0.06);
    ctx.restore();
    rim(ctx, S, 0.38, 0.34, 0.22);
  },

  /** Wide war belt with a bronze plate. */
  warbelt(ctx, S) {
    ground(ctx, S, 0.78, 0.32);
    ctx.fillStyle = lin(ctx, 0, S * 0.36, 0, S * 0.58, LEATHER);
    ctx.beginPath();
    ctx.moveTo(S * 0.1, S * 0.4);
    ctx.quadraticCurveTo(S * 0.5, S * 0.52, S * 0.9, S * 0.4);
    ctx.lineTo(S * 0.9, S * 0.56);
    ctx.quadraticCurveTo(S * 0.5, S * 0.68, S * 0.1, S * 0.56);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // buckle
    ctx.fillStyle = lin(ctx, S * 0.42, S * 0.44, S * 0.62, S * 0.66, BRONZE);
    ctx.beginPath();
    ctx.roundRect(S * 0.42, S * 0.44, S * 0.17, S * 0.16, S * 0.02);
    ctx.fill();
    outline(ctx, S * 0.008);
    ctx.strokeStyle = 'rgba(30,18,6,0.7)';
    ctx.lineWidth = S * 0.014;
    ctx.beginPath();
    ctx.moveTo(S * 0.505, S * 0.46);
    ctx.lineTo(S * 0.505, S * 0.58);
    ctx.stroke();
    rim(ctx, S, 0.36, 0.46, 0.26);
  },

  /** Strapped sandals, dust-caked. */
  sandals(ctx, S) {
    ground(ctx, S);
    for (let i = 0; i < 2; i++) {
      ctx.save();
      ctx.translate(S * (0.36 + i * 0.28), S * (0.6 - i * 0.06));
      ctx.rotate(-0.12 + i * 0.1);
      ctx.fillStyle = lin(ctx, -S * 0.18, -S * 0.1, S * 0.18, S * 0.2, LEATHER);
      ctx.beginPath();
      ctx.ellipse(0, 0, S * 0.18, S * 0.27, 0, 0, Math.PI * 2);
      ctx.fill();
      outline(ctx, S * 0.008);
      // straps
      ctx.strokeStyle = 'rgba(58,36,16,0.9)';
      ctx.lineWidth = S * 0.022;
      for (let k = 0; k < 3; k++) {
        ctx.beginPath();
        ctx.moveTo(-S * 0.14, -S * 0.12 + k * S * 0.1);
        ctx.quadraticCurveTo(0, -S * 0.18 + k * S * 0.11, S * 0.14, -S * 0.1 + k * S * 0.1);
        ctx.stroke();
      }
      // toe thong
      ctx.beginPath();
      ctx.moveTo(0, -S * 0.24);
      ctx.lineTo(0, -S * 0.02);
      ctx.stroke();
      ctx.restore();
    }
    rim(ctx, S, 0.4, 0.42, 0.24);
  },

  /** Coarse shepherd's cloak, hood folded back. */
  cloak(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = lin(ctx, S * 0.2, S * 0.2, S * 0.82, S * 0.86, ['#8a7050', '#5e4a30', '#3a2c1a', '#20160c']);
    ctx.beginPath();
    ctx.moveTo(S * 0.5, S * 0.14);
    ctx.quadraticCurveTo(S * 0.72, S * 0.2, S * 0.74, S * 0.34);
    ctx.lineTo(S * 0.9, S * 0.84);
    ctx.quadraticCurveTo(S * 0.5, S * 0.72, S * 0.1, S * 0.84);
    ctx.lineTo(S * 0.26, S * 0.34);
    ctx.quadraticCurveTo(S * 0.28, S * 0.2, S * 0.5, S * 0.14);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // hood roll
    ctx.fillStyle = lin(ctx, S * 0.32, S * 0.16, S * 0.68, S * 0.3, ['#9c8260', '#6a5636']);
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.22, S * 0.17, S * 0.07, 0, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.007);
    // weave
    ctx.strokeStyle = 'rgba(28,18,8,0.22)';
    ctx.lineWidth = 1;
    for (let i = 0; i < 6; i++) {
      ctx.beginPath();
      ctx.moveTo(S * (0.24 + i * 0.02), S * (0.38 + i * 0.08));
      ctx.lineTo(S * (0.78 - i * 0.02), S * (0.42 + i * 0.08));
      ctx.stroke();
    }
    rim(ctx, S, 0.38, 0.3, 0.24);
  },

  /** The sling: two cords and a stone cradle. */
  sling(ctx, S) {
    ground(ctx, S, 0.86, 0.26);
    ctx.lineCap = 'round';
    ctx.strokeStyle = lin(ctx, S * 0.2, S * 0.2, S * 0.8, S * 0.8, LEATHER);
    ctx.lineWidth = S * 0.035;
    ctx.beginPath();
    ctx.moveTo(S * 0.26, S * 0.2);
    ctx.bezierCurveTo(S * 0.52, S * 0.36, S * 0.66, S * 0.5, S * 0.76, S * 0.76);
    ctx.stroke();
    ctx.lineWidth = S * 0.026;
    ctx.beginPath();
    ctx.moveTo(S * 0.4, S * 0.34);
    ctx.bezierCurveTo(S * 0.5, S * 0.56, S * 0.54, S * 0.68, S * 0.56, S * 0.8);
    ctx.stroke();
    // cradle
    ctx.fillStyle = lin(ctx, S * 0.36, S * 0.44, S * 0.6, S * 0.66, LEATHER);
    ctx.beginPath();
    ctx.ellipse(S * 0.45, S * 0.56, S * 0.1, S * 0.075, 0.6, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.007);
    // stones
    for (let i = 0; i < 3; i++) {
      const x = 0.32 + i * 0.13;
      const y = 0.72 + (i % 2) * 0.06;
      ctx.fillStyle = rad(ctx, S * (x - 0.01), S * (y - 0.01), 1, S * 0.06, ['#e2ddcf', '#a8a292', '#4e4a42']);
      ctx.beginPath();
      ctx.ellipse(S * x, S * y, S * 0.058, S * 0.05, i, 0, Math.PI * 2);
      ctx.fill();
      outline(ctx, S * 0.006);
    }
    rim(ctx, S, 0.32, 0.26, 0.2);
  },

  /** Round hide shield with a bronze boss. */
  shield(ctx, S) {
    ground(ctx, S, 0.88, 0.26);
    ctx.fillStyle = rad(ctx, S * 0.42, S * 0.4, S * 0.05, S * 0.36, ['#c39a68', '#8a6038', '#4c3018']);
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.5, S * 0.33, S * 0.34, 0, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.01);
    // stitched hide segments
    ctx.strokeStyle = 'rgba(40,24,10,0.5)';
    ctx.lineWidth = S * 0.01;
    for (let i = 0; i < 4; i++) {
      ctx.beginPath();
      ctx.moveTo(S * 0.5, S * 0.17);
      ctx.quadraticCurveTo(S * (0.4 + i * 0.07), S * 0.5, S * 0.5, S * 0.83);
      ctx.stroke();
    }
    // bronze rim
    ctx.strokeStyle = lin(ctx, S * 0.15, 0, S * 0.85, 0, BRONZE);
    ctx.lineWidth = S * 0.045;
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.5, S * 0.315, S * 0.325, 0, 0, Math.PI * 2);
    ctx.stroke();
    // boss
    ctx.fillStyle = rad(ctx, S * 0.46, S * 0.46, 1, S * 0.12, ['#fff0c0', '#c39a52', '#5a3f14']);
    ctx.beginPath();
    ctx.arc(S * 0.5, S * 0.5, S * 0.11, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.007);
    rim(ctx, S, 0.4, 0.36, 0.26);
  },

  /** Goat-hide water skin. */
  waterskin(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = rad(ctx, S * 0.42, S * 0.42, S * 0.04, S * 0.36, ['#c9a878', '#8a6a44', '#3c2a16']);
    ctx.beginPath();
    ctx.moveTo(S * 0.5, S * 0.22);
    ctx.bezierCurveTo(S * 0.76, S * 0.28, S * 0.8, S * 0.62, S * 0.64, S * 0.78);
    ctx.bezierCurveTo(S * 0.54, S * 0.87, S * 0.46, S * 0.87, S * 0.36, S * 0.78);
    ctx.bezierCurveTo(S * 0.2, S * 0.62, S * 0.24, S * 0.28, S * 0.5, S * 0.22);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // neck + stopper
    ctx.fillStyle = lin(ctx, S * 0.44, 0, S * 0.56, 0, LEATHER);
    ctx.fillRect(S * 0.45, S * 0.13, S * 0.1, S * 0.12);
    ctx.fillStyle = lin(ctx, S * 0.44, 0, S * 0.56, 0, BRONZE);
    ctx.beginPath();
    ctx.arc(S * 0.5, S * 0.12, S * 0.035, 0, Math.PI * 2);
    ctx.fill();
    // sinew stitches
    ctx.strokeStyle = 'rgba(240,222,180,0.6)';
    ctx.lineWidth = S * 0.008;
    for (let i = 0; i < 7; i++) {
      ctx.beginPath();
      ctx.moveTo(S * 0.5 + (i - 3) * S * 0.014, S * 0.26);
      ctx.lineTo(S * 0.5 + (i - 3) * S * 0.024, S * 0.33);
      ctx.stroke();
    }
    rim(ctx, S, 0.4, 0.36, 0.22);
  },

  /** Barley loaf. */
  bread(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = rad(ctx, S * 0.42, S * 0.42, S * 0.05, S * 0.32, ['#e0b878', '#b8873f', '#6a4a1c']);
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.52, S * 0.28, S * 0.2, -0.1, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.009);
    // scoring
    ctx.strokeStyle = 'rgba(84,52,18,0.7)';
    ctx.lineWidth = S * 0.016;
    for (let i = 0; i < 3; i++) {
      ctx.beginPath();
      ctx.moveTo(S * (0.34 + i * 0.1), S * 0.4);
      ctx.quadraticCurveTo(S * (0.38 + i * 0.1), S * 0.52, S * (0.34 + i * 0.1), S * 0.63);
      ctx.stroke();
    }
    // crumb
    ctx.fillStyle = 'rgba(60,36,10,0.35)';
    for (let i = 0; i < 9; i++) {
      ctx.beginPath();
      ctx.arc(S * (0.32 + (i * 0.37) % 0.36), S * (0.46 + (i * 0.53) % 0.16), S * 0.008, 0, Math.PI * 2);
      ctx.fill();
    }
    rim(ctx, S, 0.4, 0.42, 0.22);
  },

  /** Oil flask. */
  oilflask(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = lin(ctx, S * 0.36, S * 0.34, S * 0.66, S * 0.84, ['#e8d6a8', '#c0a068', '#7a5a2c', '#3c2a10']);
    ctx.beginPath();
    ctx.moveTo(S * 0.5, S * 0.2);
    ctx.quadraticCurveTo(S * 0.6, S * 0.3, S * 0.7, S * 0.5);
    ctx.quadraticCurveTo(S * 0.74, S * 0.76, S * 0.5, S * 0.8);
    ctx.quadraticCurveTo(S * 0.26, S * 0.76, S * 0.3, S * 0.5);
    ctx.quadraticCurveTo(S * 0.4, S * 0.3, S * 0.5, S * 0.2);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // oil level
    ctx.fillStyle = 'rgba(214,168,58,0.55)';
    ctx.beginPath();
    ctx.moveTo(S * 0.32, S * 0.54);
    ctx.quadraticCurveTo(S * 0.5, S * 0.5, S * 0.68, S * 0.54);
    ctx.quadraticCurveTo(S * 0.72, S * 0.76, S * 0.5, S * 0.79);
    ctx.quadraticCurveTo(S * 0.28, S * 0.76, S * 0.32, S * 0.54);
    ctx.closePath();
    ctx.fill();
    // stopper
    ctx.fillStyle = lin(ctx, S * 0.44, 0, S * 0.56, 0, LEATHER);
    ctx.fillRect(S * 0.455, S * 0.13, S * 0.09, S * 0.09);
    rim(ctx, S, 0.42, 0.4, 0.22);
  },

  /** A talent of gold (the spoil weighed out). */
  goldtalent(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = rad(ctx, S * 0.4, S * 0.42, S * 0.04, S * 0.34, ['#fff4c4', '#e0bc5c', '#a87c24', '#4e3608']);
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.54, S * 0.3, S * 0.22, 0, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.009);
    // stacked rings
    for (let i = 0; i < 3; i++) {
      ctx.strokeStyle = 'rgba(120,84,12,0.55)';
      ctx.lineWidth = S * 0.012;
      ctx.beginPath();
      ctx.ellipse(S * 0.5, S * 0.46 + i * S * 0.06, S * (0.26 - i * 0.04), S * (0.09 - i * 0.012), 0, 0, Math.PI * 2);
      ctx.stroke();
    }
    // ingot on top
    ctx.fillStyle = lin(ctx, S * 0.4, S * 0.28, S * 0.62, S * 0.44, GOLD);
    ctx.beginPath();
    ctx.moveTo(S * 0.4, S * 0.36);
    ctx.lineTo(S * 0.6, S * 0.34);
    ctx.lineTo(S * 0.64, S * 0.46);
    ctx.lineTo(S * 0.36, S * 0.48);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.007);
    rim(ctx, S, 0.42, 0.36, 0.24);
  },

  /** Silver cup of the raiders' table. */
  silvercup(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = lin(ctx, S * 0.36, S * 0.28, S * 0.66, S * 0.8, SILVER);
    ctx.beginPath();
    ctx.moveTo(S * 0.32, S * 0.26);
    ctx.quadraticCurveTo(S * 0.5, S * 0.34, S * 0.68, S * 0.26);
    ctx.quadraticCurveTo(S * 0.64, S * 0.6, S * 0.5, S * 0.66);
    ctx.quadraticCurveTo(S * 0.36, S * 0.6, S * 0.32, S * 0.26);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // lip band
    ctx.strokeStyle = lin(ctx, S * 0.3, 0, S * 0.7, 0, BRONZE);
    ctx.lineWidth = S * 0.03;
    ctx.beginPath();
    ctx.moveTo(S * 0.32, S * 0.27);
    ctx.quadraticCurveTo(S * 0.5, S * 0.35, S * 0.68, S * 0.27);
    ctx.stroke();
    // stem + foot
    ctx.fillStyle = lin(ctx, S * 0.46, 0, S * 0.54, 0, SILVER);
    ctx.fillRect(S * 0.47, S * 0.64, S * 0.06, S * 0.12);
    ctx.fillStyle = rad(ctx, S * 0.46, S * 0.78, 1, S * 0.16, ['#eef4fa', '#8a97a4', '#3c444e']);
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.79, S * 0.16, S * 0.05, 0, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.006);
    rim(ctx, S, 0.4, 0.34, 0.2);
  },

  /** Raider jar. */
  jar(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = rad(ctx, S * 0.42, S * 0.44, S * 0.05, S * 0.36, CLAY);
    ctx.beginPath();
    ctx.moveTo(S * 0.38, S * 0.24);
    ctx.quadraticCurveTo(S * 0.2, S * 0.5, S * 0.28, S * 0.72);
    ctx.quadraticCurveTo(S * 0.38, S * 0.84, S * 0.5, S * 0.84);
    ctx.quadraticCurveTo(S * 0.62, S * 0.84, S * 0.72, S * 0.72);
    ctx.quadraticCurveTo(S * 0.8, S * 0.5, S * 0.62, S * 0.24);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // neck + rim
    ctx.fillStyle = lin(ctx, S * 0.4, 0, S * 0.6, 0, CLAY);
    ctx.fillRect(S * 0.4, S * 0.16, S * 0.2, S * 0.09);
    ctx.fillStyle = lin(ctx, S * 0.34, 0, S * 0.66, 0, ['#e0ab80', '#9c5e3c']);
    ctx.beginPath();
    ctx.ellipse(S * 0.5, S * 0.16, S * 0.16, S * 0.045, 0, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.006);
    // painted band
    ctx.strokeStyle = 'rgba(60,28,14,0.55)';
    ctx.lineWidth = S * 0.02;
    ctx.beginPath();
    ctx.moveTo(S * 0.26, S * 0.55);
    ctx.quadraticCurveTo(S * 0.5, S * 0.62, S * 0.74, S * 0.55);
    ctx.stroke();
    rim(ctx, S, 0.4, 0.42, 0.24);
  },

  /** Bolt of dyed cloth. */
  dyedcloth(ctx, S) {
    ground(ctx, S);
    ctx.save();
    ctx.translate(S * 0.5, S * 0.52);
    ctx.rotate(-0.12);
    ctx.fillStyle = lin(ctx, -S * 0.3, -S * 0.2, S * 0.3, S * 0.2, ['#a8496a', '#7a2e50', '#4c1a34', '#2a0e1e']);
    ctx.beginPath();
    ctx.roundRect(-S * 0.3, -S * 0.2, S * 0.6, S * 0.4, S * 0.05);
    ctx.fill();
    outline(ctx, S * 0.009);
    // folded layers
    for (let i = 0; i < 3; i++) {
      ctx.strokeStyle = 'rgba(24,10,20,0.45)';
      ctx.lineWidth = S * 0.012;
      ctx.beginPath();
      ctx.moveTo(-S * 0.3, -S * 0.08 + i * S * 0.13);
      ctx.lineTo(S * 0.3, -S * 0.1 + i * S * 0.13);
      ctx.stroke();
    }
    // rolled ends
    for (const sx of [-1, 1]) {
      ctx.fillStyle = rad(ctx, sx * S * 0.28, 0, 1, S * 0.09, ['#d0708e', '#8a3a5c', '#4a1630']);
      ctx.beginPath();
      ctx.ellipse(sx * S * 0.3, 0, S * 0.06, S * 0.2, 0, 0, Math.PI * 2);
      ctx.fill();
      outline(ctx, S * 0.006);
    }
    ctx.restore();
    rim(ctx, S, 0.4, 0.4, 0.22);
  },

  /** The scroll of the charge. */
  scroll(ctx, S) {
    ground(ctx, S);
    ctx.save();
    ctx.translate(S * 0.5, S * 0.5);
    ctx.rotate(-0.22);
    const w = S * 0.54;
    const h = S * 0.34;
    ctx.fillStyle = lin(ctx, -w / 2, 0, w / 2, 0, LINEN);
    ctx.fillRect(-w / 2, -h / 2, w, h);
    outline(ctx, S * 0.009);
    // rolled ends
    for (const sx of [-1, 1]) {
      ctx.fillStyle = lin(ctx, sx * w / 2 - S * 0.05, 0, sx * w / 2 + S * 0.05, 0,
        ['#a8865a', '#f4e8cc', '#8a6a42']);
      ctx.beginPath();
      ctx.ellipse(sx * w / 2, 0, S * 0.045, h / 2 + S * 0.03, 0, 0, Math.PI * 2);
      ctx.fill();
      outline(ctx, S * 0.007);
    }
    // ink lines
    ctx.strokeStyle = 'rgba(60,38,16,0.5)';
    ctx.lineWidth = Math.max(1, S * 0.012);
    for (let i = 0; i < 5; i++) {
      ctx.beginPath();
      ctx.moveTo(-w * 0.38, -h * 0.3 + i * h * 0.15);
      ctx.lineTo(w * (0.2 + (i % 3) * 0.06), -h * 0.3 + i * h * 0.15);
      ctx.stroke();
    }
    // wax seal
    ctx.fillStyle = rad(ctx, w * 0.3, h * 0.3, 1, S * 0.06, ['#b0442c', '#8c2a18', '#4e120a']);
    ctx.beginPath();
    ctx.arc(w * 0.32, h * 0.32, S * 0.045, 0, Math.PI * 2);
    ctx.fill();
    ctx.restore();
    rim(ctx, S, 0.4, 0.38, 0.2);
  },

  /** The linen ephod — brought to David before the pursuit. */
  ephod(ctx, S) {
    ground(ctx, S);
    ctx.fillStyle = lin(ctx, S * 0.26, S * 0.2, S * 0.74, S * 0.84, LINEN);
    ctx.beginPath();
    ctx.moveTo(S * 0.34, S * 0.18);
    ctx.lineTo(S * 0.66, S * 0.18);
    ctx.lineTo(S * 0.74, S * 0.5);
    ctx.lineTo(S * 0.68, S * 0.84);
    ctx.lineTo(S * 0.32, S * 0.84);
    ctx.lineTo(S * 0.26, S * 0.5);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.009);
    // shoulder straps
    ctx.fillStyle = lin(ctx, S * 0.3, S * 0.14, S * 0.7, S * 0.24, LINEN);
    ctx.fillRect(S * 0.34, S * 0.14, S * 0.1, S * 0.08);
    ctx.fillRect(S * 0.56, S * 0.14, S * 0.1, S * 0.08);
    // woven band
    ctx.fillStyle = lin(ctx, S * 0.28, 0, S * 0.72, 0, BRONZE);
    ctx.fillRect(S * 0.28, S * 0.44, S * 0.44, S * 0.07);
    // twelve stones
    const stones = ['#8a2a3a', '#2c5a8a', '#4a8a5a', '#b0862c', '#7a3a8a', '#c0642c'];
    for (let i = 0; i < 6; i++) {
      ctx.fillStyle = stones[i];
      ctx.beginPath();
      ctx.arc(S * (0.34 + (i % 3) * 0.16), S * (0.58 + Math.floor(i / 3) * 0.12), S * 0.028, 0, Math.PI * 2);
      ctx.fill();
      ctx.strokeStyle = INK;
      ctx.lineWidth = S * 0.005;
      ctx.stroke();
    }
    rim(ctx, S, 0.38, 0.3, 0.24);
  },

  /** Horn of oil. */
  horn(ctx, S) {
    ground(ctx, S);
    ctx.save();
    ctx.translate(S * 0.5, S * 0.52);
    ctx.rotate(0.5);
    ctx.fillStyle = lin(ctx, -S * 0.26, -S * 0.1, S * 0.26, S * 0.1, ['#3a2a18', '#6a5236', '#c8a878', '#f0e2c0']);
    ctx.beginPath();
    ctx.moveTo(-S * 0.3, S * 0.02);
    ctx.quadraticCurveTo(-S * 0.1, -S * 0.2, S * 0.16, -S * 0.16);
    ctx.quadraticCurveTo(S * 0.32, -S * 0.12, S * 0.3, S * 0.02);
    ctx.quadraticCurveTo(S * 0.16, S * 0.16, -S * 0.06, S * 0.16);
    ctx.quadraticCurveTo(-S * 0.26, S * 0.14, -S * 0.3, S * 0.02);
    ctx.closePath();
    ctx.fill();
    outline(ctx, S * 0.008);
    // rings
    ctx.strokeStyle = lin(ctx, 0, -S * 0.18, 0, S * 0.18, BRONZE);
    ctx.lineWidth = S * 0.022;
    for (let i = 0; i < 2; i++) {
      ctx.beginPath();
      ctx.ellipse(S * (-0.12 + i * 0.22), S * (0.0 + i * 0.01), S * 0.03, S * 0.16, 0, 0, Math.PI * 2);
      ctx.stroke();
    }
    // spout lip
    ctx.fillStyle = rad(ctx, -S * 0.28, 0, 1, S * 0.07, ['#f6ecd4', '#a88a60', '#4a3820']);
    ctx.beginPath();
    ctx.ellipse(-S * 0.3, S * 0.02, S * 0.05, S * 0.11, 0, 0, Math.PI * 2);
    ctx.fill();
    outline(ctx, S * 0.006);
    ctx.restore();
    rim(ctx, S, 0.4, 0.36, 0.22);
  },
};

/** Draw an icon by id into a 2D context. Returns false when unknown. */
export function drawIcon(id, ctx, S = 128) {
  const painter = ICON_PAINTERS[id];
  if (!painter || !ctx) return false;
  ctx.clearRect(0, 0, S, S);
  painter(ctx, S);
  return true;
}

export const ICON_IDS = Object.keys(ICON_PAINTERS);
