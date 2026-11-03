/**
 * state.js — load and save a player's world.
 *
 * The reference keeps a role, a farm and an army as separate server-side
 * documents. So do we: an Encampment (the camp and its stores) and a Troops
 * document (the band), both keyed by the player.
 */

import { freshEncampment } from './economy.js';
import { freshCompanies, COMPANIES, ROSTER } from './config.js';

/** A fresh band — five empty companies. */
export function freshTroops(playerId) {
  return {
    player_id: playerId,
    companies: freshCompanies(),
    wins: {},
    battles: 0,
    lost: 0,
  };
}

/** A fresh roster of mighty men. */
export function freshRoster() {
  return Object.fromEntries(ROSTER.map((m) => [m.id, { level: 1, seen: Boolean(m.unlocked) }]));
}

/**
 * Load the player's world, creating it on first sight.
 * @returns {{encampment: object, troops: object, created: boolean}}
 */
export async function loadState(store, player) {
  let created = false;

  let encampment = await store.getEncampment(player.role_id);
  if (!encampment) {
    encampment = freshEncampment(player.role_id);
    encampment.roster = freshRoster();
    created = true;
  }
  // repair documents written by an older schema
  if (!encampment.works) encampment.works = {};
  if (!encampment.production) encampment.production = {};
  if (!encampment.roster) encampment.roster = freshRoster();
  if (!encampment.resources) encampment.resources = { provisions: 0, bronze: 0, timber: 0, spoil: 0 };

  let troops = await store.getTroops(player.role_id);
  if (!troops) {
    troops = freshTroops(player.role_id);
    created = true;
  }
  if (!troops.companies) troops.companies = freshCompanies();
  for (const c of COMPANIES) {
    if (!troops.companies[c.id]) troops.companies[c.id] = { count: 0, tier: 1, wounded: 0 };
  }
  if (!troops.wins) troops.wins = {};

  return { encampment, troops, created };
}

/** Save the world back. */
export async function saveState(store, { encampment, troops }) {
  if (encampment) await store.putEncampment(encampment);
  if (troops) await store.putTroops(troops);
}

/** The camp, as the client sees it. */
export function campView(encampment, troops, gain) {
  return {
    resources: { ...encampment.resources },
    works: { ...encampment.works },
    production: JSON.parse(JSON.stringify(encampment.production || {})),
    roster: JSON.parse(JSON.stringify(encampment.roster || {})),
    renown: encampment.renown || 0,
    runs: encampment.runs || 0,
    season: encampment.season || 10,
    boon_active: Boolean(encampment.boon_active),
    gain: gain || null,
    wounded: troops
      ? Object.values(troops.companies || {}).reduce((n, c) => n + (c.wounded || 0), 0)
      : 0,
  };
}
