/**
 * install.js — makes the build behave like an installed app on a phone.
 *
 * The APK's advantage over a web page is not the package: it is that the app
 * owns the screen. This module closes that gap with the platform APIs:
 *
 *   service worker   every file precached, so the march runs with no network
 *   install prompt   `beforeinstallprompt` captured and surfaced as a wax-sealed
 *                    slip instead of the browser's own banner; on iOS, where
 *                    that event does not exist, the slip explains Add to Home
 *                    Screen instead
 *   standalone mode  `body.standalone` once launched from the home screen, so
 *                    the layout can drop browser-only affordances
 *   landscape lock   attempted when fullscreen is available (Android), ignored
 *                    where it is not (iOS)
 *   wake lock        the screen stays on while David is marching, released the
 *                    moment the game is paused or hidden
 *
 * Every API is optional and every call is guarded: an unsupported browser gets
 * the plain web page, never an error.
 */

const SW_URL = './sw.js';

function isStandalone() {
  try {
    if (window.matchMedia?.('(display-mode: fullscreen)')?.matches) return true;
    if (window.matchMedia?.('(display-mode: standalone)')?.matches) return true;
  } catch (_e) { /* older engines */ }
  // iOS home-screen launches
  return navigator.standalone === true;
}

function isIOS() {
  if (typeof navigator === 'undefined') return false;
  const ua = navigator.userAgent || '';
  const iOS = /iPad|iPhone|iPod/.test(ua) || (ua.includes('Macintosh') && navigator.maxTouchPoints > 1);
  const safari = /Safari/.test(ua) && !/CriOS|FxiOS|EdgiOS|OPiOS/.test(ua);
  return iOS && safari;
}

/* ------------------------------------------------------------------ wake */

let wakeSentinel = null;

async function acquireWakeLock() {
  if (wakeSentinel || !('wakeLock' in navigator)) return;
  try {
    wakeSentinel = await navigator.wakeLock.request('screen');
    wakeSentinel.addEventListener?.('release', () => { wakeSentinel = null; });
  } catch (_e) {
    wakeSentinel = null; // denied, low battery, or not visible
  }
}

async function releaseWakeLock() {
  try {
    await wakeSentinel?.release?.();
  } catch (_e) { /* already gone */ }
  wakeSentinel = null;
}

/* ------------------------------------------------------------------ boot */

export function initInstallShell() {
  const body = document.body;
  if (!body) return;

  if (isStandalone()) body.classList.add('standalone');

  // ------------------------------------------------------ service worker
  // Registered in production builds only: a dev server must never serve a
  // cached bundle to the next edit.
  if ('serviceWorker' in navigator && import.meta.env?.PROD) {
    window.addEventListener('load', () => {
      navigator.serviceWorker.register(SW_URL, { scope: './' }).catch(() => {
        /* offline play simply stays unavailable */
      });
    });
  }

  // ---------------------------------------------------------- install UI
  const pill = document.getElementById('install-pill');
  const pillText = document.getElementById('install-text');
  let deferredPrompt = null;

  const showPill = (text) => {
    if (!pill) return;
    if (text && pillText) pillText.textContent = text;
    pill.classList.remove('hidden');
  };
  const hidePill = () => pill?.classList.add('hidden');

  window.addEventListener('beforeinstallprompt', (e) => {
    e.preventDefault();
    deferredPrompt = e;
    showPill('Carry it in your pocket');
  });

  window.addEventListener('appinstalled', () => {
    deferredPrompt = null;
    hidePill();
    body.classList.add('installed');
  });

  if (pill) {
    pill.addEventListener('click', async () => {
      if (deferredPrompt) {
        try {
          deferredPrompt.prompt();
          const { outcome } = await deferredPrompt.userChoice;
          if (outcome === 'accepted') hidePill();
        } catch (_e) { /* dismissed */ }
        deferredPrompt = null;
        return;
      }
      // iOS and desktop Safari: no programmatic prompt exists
      if (pillText) {
        pillText.textContent = isIOS()
          ? 'Share ▸ Add to Home Screen'
          : 'Use your browser’s Install option';
      }
      setTimeout(hidePill, 5200);
    });
  }

  // iOS gets the hint after a few seconds of play, when intent is real
  if (isIOS() && !isStandalone()) {
    window.setTimeout(() => {
      if (!document.getElementById('start-screen')?.classList.contains('hidden')) return;
      showPill('Share ▸ Add to Home Screen');
    }, 24000);
  }

  // ------------------------------------------------------ fullscreen/lock
  const lockLandscape = async () => {
    try {
      await screen.orientation?.lock?.('landscape');
    } catch (_e) {
      /* iOS Safari refuses; the CSS layout already handles portrait */
    }
  };

  return {
    /** Call when the march begins. */
    async begin() {
      body.classList.add('playing');
      await acquireWakeLock();
      if (isStandalone()) lockLandscape();
    },
    /** Call on pause / overlay / victory / tab hidden. */
    async end() {
      body.classList.remove('playing');
      await releaseWakeLock();
    },
    requestFullscreen() {
      const el = typeof document !== 'undefined' ? document.documentElement : null;
      if (!el) return;
      const request = el.requestFullscreen || el.webkitRequestFullscreen;
      try {
        request?.call(el, { navigationUI: 'hide' });
      } catch (_e) { /* user gesture rules */ }
      lockLandscape();
    },
    isStandalone,
    isIOS,
  };
}
