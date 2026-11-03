/**
 * store/mongo.js — the store that remembers.
 *
 * The production face: Mongoose over MongoDB. It is a separate module so
 * that the serverless bundle — which has no database — never has to carry
 * mongoose with it.
 */

import mongoose from 'mongoose';
import { Player, Encampment, Troops, Loot, Pursuit } from '../models/index.js';

/** Map-shaped documents come back as Maps from Mongoose; flatten them. */
function flatten(doc) {
  if (!doc) return null;
  const obj = typeof doc.toObject === 'function' ? doc.toObject() : { ...doc };
  if (obj.works instanceof Map) obj.works = Object.fromEntries(obj.works);
  if (obj.production instanceof Map) obj.production = Object.fromEntries(obj.production);
  if (obj.roster instanceof Map) obj.roster = Object.fromEntries(obj.roster);
  if (obj.items instanceof Map) obj.items = Object.fromEntries(obj.items);
  if (obj.rewards instanceof Map) obj.rewards = Object.fromEntries(obj.rewards);
  if (obj.losses instanceof Map) obj.losses = Object.fromEntries(obj.losses);
  if (obj.wins instanceof Map) obj.wins = Object.fromEntries(obj.wins);
  return obj;
}

export class MongoStore {
  constructor(uri) {
    this.uri = uri;
    this.connected = false;
    this.kind = 'mongo';
  }

  async connect() {
    await mongoose.connect(this.uri, { serverSelectionTimeoutMS: 8000 });
    this.connected = true;
    return this;
  }

  async close() {
    await mongoose.disconnect();
    this.connected = false;
  }

  async getPlayer(roleId) {
    return flatten(await Player.findOne({ role_id: roleId }).lean());
  }
  async findPlayerByOpenId(openId) {
    return flatten(await Player.findOne({ open_id: openId }).lean());
  }
  async findPlayerByToken(token) {
    if (!token) return null;
    return flatten(await Player.findOne({ session_token: token }).lean());
  }
  async putPlayer(doc) {
    const { _id, ...rest } = doc;
    return flatten(await Player.findOneAndUpdate(
      { role_id: doc.role_id }, { $set: rest }, { upsert: true, new: true },
    ).lean());
  }

  async getEncampment(playerId) {
    return flatten(await Encampment.findOne({ player_id: playerId }).lean());
  }
  async putEncampment(doc) {
    const { _id, ...rest } = doc;
    return flatten(await Encampment.findOneAndUpdate(
      { player_id: doc.player_id }, { $set: rest }, { upsert: true, new: true },
    ).lean());
  }

  async getTroops(playerId) {
    return flatten(await Troops.findOne({ player_id: playerId }).lean());
  }
  async putTroops(doc) {
    const { _id, ...rest } = doc;
    return flatten(await Troops.findOneAndUpdate(
      { player_id: doc.player_id }, { $set: rest }, { upsert: true, new: true },
    ).lean());
  }

  async addLoot(doc) {
    const created = await Loot.create(doc);
    return flatten(created.toObject());
  }
  async listLoot(playerId) {
    const rows = await Loot.find({ player_id: playerId }).sort({ createdAt: -1 }).limit(200).lean();
    return rows.map(flatten);
  }

  async addPursuit(doc) {
    const created = await Pursuit.create(doc);
    return flatten(created.toObject());
  }
  async listPursuits(playerId) {
    const rows = await Pursuit.find({ player_id: playerId }).sort({ createdAt: -1 }).limit(200).lean();
    return rows.map(flatten);
  }

  /** The reference's SDeleteRole: the role and its world go together. */
  async deleteRole(roleId) {
    await Promise.all([
      Player.deleteOne({ role_id: roleId }),
      Encampment.deleteOne({ player_id: roleId }),
      Troops.deleteOne({ player_id: roleId }),
      Loot.deleteMany({ player_id: roleId }),
      Pursuit.deleteMany({ player_id: roleId }),
    ]);
    return true;
  }
}

export default MongoStore;
