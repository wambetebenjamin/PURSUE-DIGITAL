/**
 * store/memory.js — the store that needs nothing.
 *
 * The same interface as the Mongo store, over plain Maps. It is what the
 * test suite runs against, and what the serverless function runs against
 * when no database is configured.
 */

export class MemoryStore {
  constructor() {
    this.players = new Map();
    this.encampments = new Map();
    this.troops = new Map();
    this.loot = [];
    this.pursuits = [];
    this.connected = false;
    this.kind = 'memory';
  }

  async connect() { this.connected = true; return this; }
  async close() { this.connected = false; }

  async getPlayer(roleId) { return this.players.get(roleId) || null; }
  async findPlayerByOpenId(openId) {
    for (const p of this.players.values()) if (p.open_id === openId) return p;
    return null;
  }
  async findPlayerByToken(token) {
    if (!token) return null;
    for (const p of this.players.values()) if (p.session_token === token) return p;
    return null;
  }
  async putPlayer(doc) { this.players.set(doc.role_id, doc); return doc; }

  async getEncampment(playerId) { return this.encampments.get(playerId) || null; }
  async putEncampment(doc) { this.encampments.set(doc.player_id, doc); return doc; }

  async getTroops(playerId) { return this.troops.get(playerId) || null; }
  async putTroops(doc) { this.troops.set(doc.player_id, doc); return doc; }

  async addLoot(doc) { this.loot.push(doc); return doc; }
  async listLoot(playerId) { return this.loot.filter((l) => l.player_id === playerId); }

  async addPursuit(doc) { this.pursuits.push(doc); return doc; }
  async listPursuits(playerId) { return this.pursuits.filter((p) => p.player_id === playerId); }

  /** The reference's SDeleteRole: the role and its world go together. */
  async deleteRole(roleId) {
    this.players.delete(roleId);
    this.encampments.delete(roleId);
    this.troops.delete(roleId);
    this.loot = this.loot.filter((l) => l.player_id !== roleId);
    this.pursuits = this.pursuits.filter((p) => p.player_id !== roleId);
    return true;
  }
}

export default MemoryStore;
