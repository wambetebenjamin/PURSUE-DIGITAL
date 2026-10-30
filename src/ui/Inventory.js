/**
 * Inventory.js — "The Satchel": the pause-screen goods page.
 *
 * Built as an equipment page rather than a flat gallery: eight worn slots
 * around the body (head, chest, arms, belt, feet, cloak, hand, off-hand), then
 * provisions, spoil and relics. Every tile is a rendered icon (see
 * ui/icons.js) with a rarity frame, and selecting a tile opens its account
 * beneath the grid — name, rarity, what it is, and the verse it comes from.
 *
 * Data lives in ui/items.js, art in ui/icons.js; this module is only layout
 * and interaction. It is headless-safe (the smoke test runs with a stub DOM).
 */
import { drawIcon } from './icons.js';
import {
  EQUIPPED, PROVISIONS, SPOIL, RELICS, SLOTS, RARITY, TALLY, ALL_ITEMS,
} from './items.js';

const ICON_SIZE = 128;

/** Draw one item into a canvas (or an <img> when a licensed PNG is supplied). */
function makeTileArt(item) {
  if (item.file) {
    const img = document.createElement('img');
    img.src = item.file;
    img.alt = item.name;
    img.loading = 'lazy';
    img.className = 'icon-img';
    return img;
  }
  const canvas = document.createElement('canvas');
  canvas.width = ICON_SIZE;
  canvas.height = ICON_SIZE;
  canvas.className = 'icon-canvas';
  if (canvas.style) {
    canvas.style.width = '100%';
    canvas.style.height = 'auto';
    canvas.style.display = 'block';
  }
  const ctx = typeof canvas.getContext === 'function' ? canvas.getContext('2d') : null;
  if (ctx) {
    try {
      drawIcon(item.icon, ctx, ICON_SIZE);
    } catch (_e) {
      /* headless / unsupported canvas: keep the tile, skip the art */
    }
  }
  return canvas;
}

function el(tag, className, text) {
  const node = document.createElement(tag);
  if (className && node.classList) node.classList.add(...className.split(' '));
  if (text !== undefined) node.textContent = text;
  return node;
}

/**
 * @param {HTMLElement} host  container (#satchel-grid)
 * @param {{onSelect?: (item:Object)=>void}} [opts]
 */
export function renderSatchel(host, opts = {}) {
  if (!host || !host.appendChild) return;
  if (host.children && host.children.length > 0) return; // build once

  let detail = document.getElementById('satchel-detail');
  const bySlot = new Map(EQUIPPED.map((i) => [i.slot, i]));

  const select = (item, tile) => {
    // gilding of the selected tile
    if (host.querySelectorAll) {
      for (const t of host.querySelectorAll('.satchel-tile.selected')) {
        t.classList.remove('selected');
      }
    }
    if (tile && tile.classList) tile.classList.add('selected');
    if (!detail || !detail.replaceChildren) return;
    detail.replaceChildren();
    detail.appendChild(el('b', 'detail-name', item.name));
    detail.appendChild(el('span', `detail-rarity ${item.rarity}`, RARITY[item.rarity]?.label ?? item.rarity));
    detail.appendChild(el('p', 'detail-blurb', item.blurb));
    detail.appendChild(el('cite', 'detail-verse', item.verse));
    if (opts.onSelect) opts.onSelect(item);
  };

  const tile = (item) => {
    const t = el('button', `satchel-tile ${item.rarity}`);
    t.type = 'button';
    if (item.name) {
      t.appendChild(makeTileArt(item));
      t.appendChild(el('span', 'tile-name', item.name));
      if (t.addEventListener) t.addEventListener('click', () => select(item, t));
      if (t.addEventListener) {
        t.addEventListener('pointerenter', () => { if (detail) select(item, t); });
      }
    } else {
      t.classList.add('empty');
      if (t.setAttribute) t.setAttribute('disabled', 'disabled');
      t.appendChild(el('span', 'tile-empty-mark', item.label));
    }
    return t;
  };

  // ------------------------------------------------------- equipment
  const equipSection = el('section', 'satchel-section');
  equipSection.appendChild(el('h3', 'satchel-head', 'Upon him'));
  const slotGrid = el('div', 'slot-grid');
  for (const slot of SLOTS) {
    const item = bySlot.get(slot.key);
    const node = tile(item ?? { label: slot.label });
    if (item) node.appendChild(el('span', 'tile-slot', slot.label));
    slotGrid.appendChild(node);
  }
  equipSection.appendChild(slotGrid);
  host.appendChild(equipSection);

  // ------------------------------------------- provisions / spoil / relics
  const collection = (title, list) => {
    const section = el('section', 'satchel-section');
    section.appendChild(el('h3', 'satchel-head', title));
    const grid = el('div', 'item-grid');
    for (const item of list) grid.appendChild(tile(item));
    section.appendChild(grid);
    host.appendChild(section);
    return grid;
  };

  collection('In the satchel', PROVISIONS);
  collection('Spoil recovered', SPOIL);
  collection('Relics of the pursuit', RELICS);

  // --------------------------------------------------------------- tally
  const tally = el('div', 'satchel-tally');
  for (const [n, label] of TALLY) {
    const cell = el('div', 'tally-cell');
    cell.appendChild(el('b', 'tally-n', n));
    cell.appendChild(el('span', 'tally-label', label));
    tally.appendChild(cell);
  }
  host.appendChild(tally);

  // open on the first item so the panel is never blank
  if (ALL_ITEMS.length) select(ALL_ITEMS[0], null);
}

export const SATCHEL_ITEMS = ALL_ITEMS.map((i) => i.name);
