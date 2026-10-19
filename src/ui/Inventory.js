/**
 * Inventory.js — "The Satchel": the pause-screen goods page.
 *
 * There are no bitmap icons in this project, so each item is a small
 * procedurally *shaded* still-life: layered radial/linear gradients, rim
 * light and contact shadow on a 160² canvas. They read as little studio
 * renders of clay, iron, linen and leather rather than flat pictograms.
 *
 * The page is rebuilt once and then reused; everything guards for the
 * headless DOM (smoke test), where appendChild exists but canvas painting
 * is a no-op.
 */

const ITEMS = [
  {
    name: 'The Oil Lamp',
    note: 'Clay, thumb-smoothed, bronze-rimmed. The flame is the measure of a man.',
    draw(ctx, S) {
      // contact shadow
      const sh = ctx.createRadialGradient(S * 0.5, S * 0.78, 2, S * 0.5, S * 0.78, S * 0.32);
      sh.addColorStop(0, 'rgba(40,22,8,0.45)');
      sh.addColorStop(1, 'rgba(40,22,8,0)');
      ctx.fillStyle = sh;
      ctx.beginPath(); ctx.ellipse(S * 0.5, S * 0.78, S * 0.33, S * 0.09, 0, 0, Math.PI * 2); ctx.fill();

      // handle
      ctx.strokeStyle = '#5a3a1a';
      ctx.lineWidth = S * 0.055;
      ctx.lineCap = 'round';
      ctx.beginPath();
      ctx.arc(S * 0.30, S * 0.54, S * 0.11, Math.PI * 0.55, Math.PI * 1.75);
      ctx.stroke();

      // body
      const body = ctx.createLinearGradient(S * 0.3, S * 0.46, S * 0.62, S * 0.8);
      body.addColorStop(0, '#c08462');
      body.addColorStop(0.35, '#9a5a3c');
      body.addColorStop(0.75, '#6a3624');
      body.addColorStop(1, '#3e1c12');
      ctx.fillStyle = body;
      ctx.beginPath();
      ctx.moveTo(S * 0.26, S * 0.62);
      ctx.bezierCurveTo(S * 0.32, S * 0.82, S * 0.76, S * 0.83, S * 0.83, S * 0.61);
      ctx.bezierCurveTo(S * 0.85, S * 0.54, S * 0.8, S * 0.5, S * 0.71, S * 0.5);
      ctx.lineTo(S * 0.38, S * 0.5);
      ctx.bezierCurveTo(S * 0.28, S * 0.5, S * 0.24, S * 0.55, S * 0.26, S * 0.62);
      ctx.closePath();
      ctx.fill();

      // bronze rim + spout
      const metal = ctx.createLinearGradient(0, S * 0.44, 0, S * 0.56);
      metal.addColorStop(0, '#e2c07c');
      metal.addColorStop(0.5, '#a8823c');
      metal.addColorStop(1, '#5a3c18');
      ctx.fillStyle = metal;
      ctx.beginPath();
      ctx.moveTo(S * 0.44, S * 0.5); ctx.lineTo(S * 0.69, S * 0.5);
      ctx.lineTo(S * 0.67, S * 0.43); ctx.lineTo(S * 0.46, S * 0.43);
      ctx.closePath(); ctx.fill();
      ctx.beginPath();
      ctx.moveTo(S * 0.79, S * 0.59); ctx.lineTo(S * 0.97, S * 0.51);
      ctx.lineTo(S * 0.92, S * 0.64); ctx.closePath(); ctx.fill();

      // incised band
      ctx.strokeStyle = 'rgba(30,14,6,0.5)';
      ctx.lineWidth = S * 0.012;
      ctx.beginPath();
      ctx.moveTo(S * 0.33, S * 0.63);
      for (let i = 0; i < 4; i++) {
        ctx.quadraticCurveTo(S * (0.37 + i * 0.11), S * 0.59, S * (0.415 + i * 0.11), S * 0.63);
      }
      ctx.stroke();

      // flame
      const fg = ctx.createRadialGradient(S * 0.84, S * 0.4, 1, S * 0.84, S * 0.42, S * 0.13);
      fg.addColorStop(0, 'rgba(255,248,214,1)');
      fg.addColorStop(0.35, 'rgba(255,196,84,0.95)');
      fg.addColorStop(0.75, 'rgba(240,110,30,0.55)');
      fg.addColorStop(1, 'rgba(200,60,10,0)');
      ctx.fillStyle = fg;
      ctx.beginPath();
      ctx.moveTo(S * 0.84, S * 0.22);
      ctx.quadraticCurveTo(S * 0.93, S * 0.38, S * 0.84, S * 0.47);
      ctx.quadraticCurveTo(S * 0.75, S * 0.38, S * 0.84, S * 0.22);
      ctx.fill();
    },
  },
  {
    name: 'Iron Dagger',
    note: 'A raider’s blade, notched twice. The grip is wrapped in cracked hide.',
    draw(ctx, S) {
      const sh = ctx.createRadialGradient(S * 0.55, S * 0.82, 2, S * 0.55, S * 0.82, S * 0.3);
      sh.addColorStop(0, 'rgba(30,20,10,0.4)');
      sh.addColorStop(1, 'rgba(30,20,10,0)');
      ctx.fillStyle = sh;
      ctx.beginPath(); ctx.ellipse(S * 0.55, S * 0.83, S * 0.3, S * 0.06, -0.25, 0, Math.PI * 2); ctx.fill();

      ctx.save();
      ctx.translate(S * 0.5, S * 0.5);
      ctx.rotate(-0.72);
      // blade
      const bl = ctx.createLinearGradient(-S * 0.05, 0, S * 0.05, 0);
      bl.addColorStop(0, '#7a7f86');
      bl.addColorStop(0.45, '#d8dde2');
      bl.addColorStop(0.55, '#aeb4ba');
      bl.addColorStop(1, '#4a4e54');
      ctx.fillStyle = bl;
      ctx.beginPath();
      ctx.moveTo(0, -S * 0.34);
      ctx.lineTo(S * 0.045, -S * 0.2);
      ctx.lineTo(S * 0.035, S * 0.05);
      ctx.lineTo(-S * 0.035, S * 0.05);
      ctx.lineTo(-S * 0.045, -S * 0.2);
      ctx.closePath();
      ctx.fill();
      // fuller
      ctx.strokeStyle = 'rgba(60,64,70,0.65)';
      ctx.lineWidth = S * 0.012;
      ctx.beginPath(); ctx.moveTo(0, -S * 0.29); ctx.lineTo(0, S * 0.02); ctx.stroke();
      // notches
      ctx.fillStyle = '#3c4046';
      ctx.beginPath(); ctx.arc(-S * 0.032, -S * 0.09, S * 0.015, 0, Math.PI * 2); ctx.fill();
      ctx.beginPath(); ctx.arc(S * 0.03, -S * 0.15, S * 0.012, 0, Math.PI * 2); ctx.fill();
      // guard
      const gd = ctx.createLinearGradient(-S * 0.1, 0, S * 0.1, 0);
      gd.addColorStop(0, '#6a4a1e');
      gd.addColorStop(0.5, '#d0a458');
      gd.addColorStop(1, '#503616');
      ctx.fillStyle = gd;
      ctx.fillRect(-S * 0.09, S * 0.05, S * 0.18, S * 0.028);
      // grip
      const gr = ctx.createLinearGradient(-S * 0.03, 0, S * 0.03, 0);
      gr.addColorStop(0, '#3a2616');
      gr.addColorStop(0.5, '#6a4a2c');
      gr.addColorStop(1, '#24160c');
      ctx.fillStyle = gr;
      ctx.fillRect(-S * 0.028, S * 0.078, S * 0.056, S * 0.17);
      for (let i = 0; i < 6; i++) {
        ctx.strokeStyle = 'rgba(20,12,6,0.5)';
        ctx.lineWidth = S * 0.007;
        ctx.beginPath();
        ctx.moveTo(-S * 0.028, S * 0.09 + i * S * 0.026);
        ctx.lineTo(S * 0.028, S * 0.1 + i * S * 0.026);
        ctx.stroke();
      }
      // pommel
      ctx.fillStyle = '#8a6a34';
      ctx.beginPath(); ctx.arc(0, S * 0.265, S * 0.032, 0, Math.PI * 2); ctx.fill();
      ctx.restore();
    },
  },
  {
    name: 'Worn Tunic',
    note: 'Faded madder and undyed linen. Dust lives permanently in the weave.',
    draw(ctx, S) {
      // folded cloth, three planes
      const a = ctx.createLinearGradient(S * 0.15, S * 0.3, S * 0.85, S * 0.6);
      a.addColorStop(0, '#b07a56');
      a.addColorStop(0.4, '#96603f');
      a.addColorStop(1, '#5e3624');
      ctx.fillStyle = a;
      ctx.beginPath();
      ctx.moveTo(S * 0.18, S * 0.44);
      ctx.quadraticCurveTo(S * 0.36, S * 0.3, S * 0.58, S * 0.36);
      ctx.quadraticCurveTo(S * 0.9, S * 0.44, S * 0.84, S * 0.6);
      ctx.quadraticCurveTo(S * 0.62, S * 0.7, S * 0.42, S * 0.66);
      ctx.quadraticCurveTo(S * 0.2, S * 0.62, S * 0.18, S * 0.44);
      ctx.closePath(); ctx.fill();

      // sleeve fold
      const b = ctx.createLinearGradient(S * 0.2, S * 0.5, S * 0.6, S * 0.8);
      b.addColorStop(0, '#a06a48');
      b.addColorStop(1, '#4e2a1a');
      ctx.fillStyle = b;
      ctx.beginPath();
      ctx.moveTo(S * 0.3, S * 0.6);
      ctx.quadraticCurveTo(S * 0.5, S * 0.58, S * 0.74, S * 0.66);
      ctx.quadraticCurveTo(S * 0.66, S * 0.82, S * 0.4, S * 0.8);
      ctx.quadraticCurveTo(S * 0.26, S * 0.76, S * 0.3, S * 0.6);
      ctx.closePath(); ctx.fill();

      // madder stripe
      ctx.fillStyle = 'rgba(122, 30, 24, 0.75)';
      ctx.beginPath();
      ctx.moveTo(S * 0.2, S * 0.47);
      ctx.quadraticCurveTo(S * 0.5, S * 0.38, S * 0.82, S * 0.49);
      ctx.lineTo(S * 0.82, S * 0.53);
      ctx.quadraticCurveTo(S * 0.5, S * 0.42, S * 0.2, S * 0.51);
      ctx.closePath(); ctx.fill();

      // weave lines
      ctx.strokeStyle = 'rgba(40,22,12,0.18)';
      ctx.lineWidth = 1;
      for (let i = 0; i < 12; i++) {
        ctx.beginPath();
        ctx.moveTo(S * 0.22 + i * S * 0.005, S * 0.4 + i * S * 0.02);
        ctx.lineTo(S * 0.8, S * 0.52 + i * S * 0.018);
        ctx.stroke();
      }
      // frayed hem
      ctx.strokeStyle = 'rgba(70, 44, 26, 0.8)';
      for (let i = 0; i < 9; i++) {
        ctx.beginPath();
        ctx.moveTo(S * (0.36 + i * 0.045), S * 0.72);
        ctx.lineTo(S * (0.36 + i * 0.045) + 2, S * 0.79);
        ctx.stroke();
      }
    },
  },
  {
    name: 'Water Skin',
    note: 'Goat hide, sewn with sinew. It still smells of the wadi.',
    draw(ctx, S) {
      const sh = ctx.createRadialGradient(S * 0.5, S * 0.8, 2, S * 0.5, S * 0.8, S * 0.3);
      sh.addColorStop(0, 'rgba(30,20,10,0.35)');
      sh.addColorStop(1, 'rgba(30,20,10,0)');
      ctx.fillStyle = sh;
      ctx.beginPath(); ctx.ellipse(S * 0.5, S * 0.8, S * 0.28, S * 0.05, 0, 0, Math.PI * 2); ctx.fill();

      const g = ctx.createRadialGradient(S * 0.42, S * 0.42, S * 0.05, S * 0.5, S * 0.54, S * 0.36);
      g.addColorStop(0, '#a88860');
      g.addColorStop(0.55, '#7a5a38');
      g.addColorStop(1, '#3c2a16');
      ctx.fillStyle = g;
      ctx.beginPath();
      ctx.moveTo(S * 0.5, S * 0.26);
      ctx.bezierCurveTo(S * 0.74, S * 0.3, S * 0.8, S * 0.58, S * 0.66, S * 0.73);
      ctx.bezierCurveTo(S * 0.56, S * 0.83, S * 0.44, S * 0.83, S * 0.34, S * 0.73);
      ctx.bezierCurveTo(S * 0.2, S * 0.58, S * 0.26, S * 0.3, S * 0.5, S * 0.26);
      ctx.closePath(); ctx.fill();

      // neck + stopper
      ctx.fillStyle = '#6a4a2a';
      ctx.fillRect(S * 0.45, S * 0.16, S * 0.1, S * 0.13);
      ctx.fillStyle = '#b8945c';
      ctx.beginPath(); ctx.arc(S * 0.5, S * 0.15, S * 0.035, 0, Math.PI * 2); ctx.fill();
      // sinew stitching
      ctx.strokeStyle = 'rgba(224,204,160,0.6)';
      ctx.lineWidth = 1.6;
      for (let i = 0; i < 8; i++) {
        ctx.beginPath();
        ctx.moveTo(S * 0.5 + (i - 4) * S * 0.012, S * 0.3);
        ctx.lineTo(S * 0.5 + (i - 4) * S * 0.02, S * 0.36);
        ctx.stroke();
      }
      // highlight
      const h = ctx.createRadialGradient(S * 0.4, S * 0.4, 2, S * 0.4, S * 0.4, S * 0.14);
      h.addColorStop(0, 'rgba(255,236,200,0.4)');
      h.addColorStop(1, 'rgba(255,236,200,0)');
      ctx.fillStyle = h;
      ctx.beginPath(); ctx.ellipse(S * 0.4, S * 0.42, S * 0.14, S * 0.1, -0.5, 0, Math.PI * 2); ctx.fill();
    },
  },
  {
    name: 'Sling & Stones',
    note: 'Five smooth stones from the brook. The shepherd’s whole armoury.',
    draw(ctx, S) {
      // leather sling, curled
      const g = ctx.createLinearGradient(S * 0.2, S * 0.4, S * 0.8, S * 0.7);
      g.addColorStop(0, '#8a6a44');
      g.addColorStop(1, '#3e2a16');
      ctx.strokeStyle = g;
      ctx.lineWidth = S * 0.04;
      ctx.lineCap = 'round';
      ctx.beginPath();
      ctx.moveTo(S * 0.24, S * 0.34);
      ctx.bezierCurveTo(S * 0.5, S * 0.52, S * 0.68, S * 0.62, S * 0.78, S * 0.86);
      ctx.stroke();
      ctx.lineWidth = S * 0.028;
      ctx.beginPath();
      ctx.moveTo(S * 0.36, S * 0.5);
      ctx.bezierCurveTo(S * 0.46, S * 0.66, S * 0.5, S * 0.76, S * 0.52, S * 0.9);
      ctx.stroke();
      // pouch
      ctx.fillStyle = '#5a3e22';
      ctx.beginPath();
      ctx.ellipse(S * 0.44, S * 0.62, S * 0.09, S * 0.07, 0.6, 0, Math.PI * 2);
      ctx.fill();

      // five water-worn stones
      const stones = [
        [0.3, 0.68, 0.055], [0.42, 0.76, 0.048], [0.56, 0.72, 0.05],
        [0.64, 0.82, 0.042], [0.26, 0.8, 0.04],
      ];
      for (let i = 0; i < stones.length; i++) {
        const [x, y, r] = stones[i];
        const sg = ctx.createRadialGradient(
          S * (x - r * 0.4), S * (y - r * 0.5), 1,
          S * x, S * y, S * r,
        );
        sg.addColorStop(0, '#d8d2c2');
        sg.addColorStop(0.6, '#9a9484');
        sg.addColorStop(1, '#4a463e');
        ctx.fillStyle = sg;
        ctx.beginPath();
        ctx.ellipse(S * x, S * y, S * r * 1.15, S * r, i * 0.5, 0, Math.PI * 2);
        ctx.fill();
      }
    },
  },
  {
    name: 'The Scroll',
    note: 'The charge, written by a scribe’s hand: “Pursue, and recover all.”',
    draw(ctx, S) {
      // rolled scroll at an angle
      ctx.save();
      ctx.translate(S * 0.5, S * 0.52);
      ctx.rotate(-0.28);
      const w = S * 0.5;
      const h = S * 0.32;
      const g = ctx.createLinearGradient(-w / 2, 0, w / 2, 0);
      g.addColorStop(0, '#c9ad7c');
      g.addColorStop(0.35, '#f0e2c0');
      g.addColorStop(0.7, '#e2cfa6');
      g.addColorStop(1, '#b89864');
      ctx.fillStyle = g;
      ctx.fillRect(-w / 2, -h / 2, w, h);

      // rolled ends
      for (const sx of [-1, 1]) {
        const rg = ctx.createLinearGradient(sx * w / 2 - 12, 0, sx * w / 2 + 12, 0);
        rg.addColorStop(0, '#a8865a');
        rg.addColorStop(0.5, '#f4e8cc');
        rg.addColorStop(1, '#8a6a42');
        ctx.fillStyle = rg;
        ctx.beginPath();
        ctx.ellipse(sx * w / 2, 0, S * 0.045, h / 2 + S * 0.03, 0, 0, Math.PI * 2);
        ctx.fill();
      }
      // ink lines
      ctx.strokeStyle = 'rgba(60, 38, 16, 0.55)';
      ctx.lineWidth = 1.8;
      for (let i = 0; i < 5; i++) {
        ctx.beginPath();
        ctx.moveTo(-w * 0.38, -h * 0.3 + i * h * 0.15);
        ctx.lineTo(w * (0.2 + (i % 3) * 0.06), -h * 0.3 + i * h * 0.15);
        ctx.stroke();
      }
      // wax seal
      ctx.fillStyle = '#8a2a18';
      ctx.beginPath(); ctx.arc(w * 0.32, h * 0.34, S * 0.045, 0, Math.PI * 2); ctx.fill();
      ctx.fillStyle = 'rgba(240,214,168,0.85)';
      ctx.font = `${Math.round(S * 0.06)}px "Frank Ruhl Libre", Georgia, serif`;
      ctx.textAlign = 'center';
      ctx.textBaseline = 'middle';
      ctx.fillText('\u05D0', w * 0.32, h * 0.35);
      ctx.restore();
    },
  },
];

/** Build the satchel grid once, into `host`. */
export function renderSatchel(host) {
  if (!host || !host.appendChild) return;
  if (host.children && host.children.length > 0) return;

  for (const item of ITEMS) {
    const card = document.createElement('figure');
    if (card.classList) card.classList.add('satchel-item');

    const canvas = document.createElement('canvas');
    canvas.width = 160;
    canvas.height = 160;
    if (canvas.style) {
      canvas.style.width = '100%';
      canvas.style.height = 'auto';
      canvas.style.display = 'block';
    }
    const ctx = typeof canvas.getContext === 'function' ? canvas.getContext('2d') : null;
    if (ctx) {
      try {
        ctx.save();
        item.draw(ctx, 160);
        ctx.restore();
      } catch (_e) {
        /* headless / unsupported canvas: keep the card, skip the art */
      }
    }
    card.appendChild(canvas);

    const cap = document.createElement('figcaption');
    const name = document.createElement('b');
    name.textContent = item.name;
    const note = document.createElement('span');
    note.textContent = item.note;
    cap.appendChild(name);
    cap.appendChild(note);
    card.appendChild(cap);
    host.appendChild(card);
  }
}

export const SATCHEL_ITEMS = ITEMS.map((i) => i.name);
