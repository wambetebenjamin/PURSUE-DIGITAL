/**
 * PauseMenu.js — the pause + "Map of Judah" chart screen.
 *
 * Follows the classic GTA frontend page flow (Frontend.cpp): Escape pauses
 * into a small menu; the MAP page draws the whole march on a canvas — the
 * FrontEnd::MAPS analog — with the player arrow, objective blip and the
 * seven districts. "Begin again" restarts the run like NEW GAME.
 *
 * DOM ids are created when missing so the module is headless-safe (smoke).
 */
import { renderSatchel } from './Inventory.js';
export class PauseMenu {
  /**
   * @param {{ onResume: () => void, onRestart: () => void, radar: import('./Radar.js').Radar }} opts
   */
  constructor({ onResume, onRestart, radar }) {
    this.onResume = onResume;
    this.onRestart = onRestart;
    this.radar = radar;
    this.isOpen = false;
    this.page = 'menu'; // 'menu' | 'map' | 'satchel'

    this.screen = document.getElementById('pause-screen');
    this.btnResume = document.getElementById('pause-resume');
    this.btnResume2 = document.getElementById('pause-resume-2');
    this.btnMap = document.getElementById('pause-map');
    this.btnRestart = document.getElementById('pause-restart');
    this.menuPanel = document.getElementById('pause-menu-panel');
    this.mapPanel = document.getElementById('pause-map-panel');
    this.satchelPanel = document.getElementById('pause-satchel-panel');
    this.btnSatchel = document.getElementById('pause-satchel');
    this.satchelHost = document.getElementById('satchel-grid');
    // headless fakes may lack getContext — renderFullMap guards and no-ops
    this.mapCanvas = document.getElementById('map-canvas');

    if (this.btnResume) {
      this.btnResume.addEventListener('click', () => this.close());
    }
    if (this.btnResume2) {
      this.btnResume2.addEventListener('click', () => this.close());
    }
    if (this.btnMap) {
      this.btnMap.addEventListener('click', () => this.showPage('map'));
    }
    if (this.btnSatchel) {
      this.btnSatchel.addEventListener('click', () => this.showPage('satchel'));
    }
    const back3 = document.getElementById('pause-resume-3');
    if (back3) back3.addEventListener('click', () => this.close());
    if (this.btnRestart) {
      this.btnRestart.addEventListener('click', () => {
        this.close();
        if (this.onRestart) this.onRestart();
      });
    }
  }

  _sizeMap() {
    const canvas = this.mapCanvas;
    if (!canvas || typeof canvas.getContext !== 'function') return;
    const rect = canvas.getBoundingClientRect?.();
    const dpr = Math.min(window.devicePixelRatio || 1, 2);
    const w = Math.max(320, Math.round((rect?.width || 720) * dpr));
    const h = Math.max(220, Math.round((rect?.height || 460) * dpr));
    if (canvas.width !== w || canvas.height !== h) {
      canvas.width = w;
      canvas.height = h;
    }
  }

  /** Open with an optional page ('menu' | 'map'). */
  open(page = 'menu') {
    this.isOpen = true;
    this.showPage(page);
    if (this.screen) this.screen.classList.remove('hidden');
  }

  showPage(page) {
    this.page = page;
    if (this.menuPanel) this.menuPanel.classList.toggle('hidden', page !== 'menu');
    if (this.mapPanel) this.mapPanel.classList.toggle('hidden', page !== 'map');
    if (this.satchelPanel) this.satchelPanel.classList.toggle('hidden', page !== 'satchel');
    if (page === 'map') {
      this._sizeMap();
      this.render();
    }
    if (page === 'satchel') renderSatchel(this.satchelHost);
  }

  close(notify = true) {
    this.isOpen = false;
    this.page = 'menu';
    if (this.screen) this.screen.classList.add('hidden');
    if (notify && this.onResume) this.onResume();
  }

  /** Toggle open/closed; passing 'map' opens straight onto the chart. */
  toggle(page) {
    if (this.isOpen && (!page || page === this.page)) this.close();
    else this.open(page || 'menu');
  }

  /** Redraw the chart while open (player may not be moving, but the pulse is). */
  render(playerPos, heading) {
    if (!this.isOpen || this.page !== 'map' || !this.radar) return;
    this._sizeMap();
    this.radar.renderFullMap(this.mapCanvas, playerPos || { x: 0, z: 0 }, heading || 0);
  }

  update(_dt, playerPos, heading) {
    if (!this.isOpen) return;
    if (this.page === 'map') this.render(playerPos, heading);
  }
}
