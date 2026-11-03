/**
 * models/index.js — the Mongoose schemas.
 *
 * Field names follow the decompiled SDK's wire names (snake_case, the
 * `@SerializedName` values recovered by tools/extract-java-schema.py) so a
 * client written against the reference's payload conventions reads these
 * documents without translation.
 *
 * The Player mirrors the SDK's `Role` bean field for field.
 */

import mongoose from 'mongoose';

const { Schema } = mongoose;

/* ------------------------------------------------------------------ Player */

/**
 * Role, from com.bytedance.ttgame.module.gameinfo.api.Role:
 *   role_id · name · level · server_name · real_server_id · login_time
 *   avatar_url · job · extra
 *
 * plus the session the SDK hands out after `gsdk/account/exchange_token`.
 */
export const PlayerSchema = new Schema({
  role_id: { type: String, required: true, unique: true, index: true },
  open_id: { type: String, default: null, index: true },
  name: { type: String, default: 'A captain of Ziklag' },
  level: { type: Number, default: 1 },
  job: { type: String, default: 'captain' },
  avatar_url: { type: String, default: null },
  server_id: { type: Number, default: 1 },
  server_name: { type: String, default: 'Ziklag' },
  session_token: { type: String, default: null, index: true },
  token_expires_at: { type: Date, default: null },
  last_login_time: { type: Date, default: null },
  stars: { type: Number, default: 0 },
  renown: { type: Number, default: 0 },
  season: { type: Number, default: 10 },
  boon_active: { type: Boolean, default: false },
  extra: { type: String, default: null },
}, { timestamps: true, collection: 'pursue_players' });

/* -------------------------------------------------------------- Encampment */

/** The camp at Ziklag: the stores, the works, the production queues. */
export const EncampmentSchema = new Schema({
  player_id: { type: String, required: true, unique: true, index: true },
  resources: {
    provisions: { type: Number, default: 180 },
    bronze: { type: Number, default: 90 },
    timber: { type: Number, default: 60 },
    spoil: { type: Number, default: 0 },
  },
  works: { type: Map, of: Number, default: {} },
  production: { type: Map, of: Schema.Types.Mixed, default: {} },
  roster: { type: Map, of: Schema.Types.Mixed, default: {} },
  renown: { type: Number, default: 0 },
  runs: { type: Number, default: 0 },
  last_collect_at: { type: Date, default: Date.now },
  last_play: { type: Date, default: null },
  boon_active: { type: Boolean, default: false },
  season: { type: Number, default: 10 },
}, { timestamps: true, collection: 'pursue_encampments' });

/* ------------------------------------------------------------------ Troops */

const CompanySchema = new Schema({
  count: { type: Number, default: 0 },
  tier: { type: Number, default: 1 },
  wounded: { type: Number, default: 0 },
}, { _id: false });

/** The band of six hundred. */
export const TroopsSchema = new Schema({
  player_id: { type: String, required: true, unique: true, index: true },
  companies: {
    spears: { type: CompanySchema, default: () => ({}) },
    shields: { type: CompanySchema, default: () => ({}) },
    bows: { type: CompanySchema, default: () => ({}) },
    slings: { type: CompanySchema, default: () => ({}) },
    scouts: { type: CompanySchema, default: () => ({}) },
  },
  wins: { type: Map, of: Number, default: {} },
  battles: { type: Number, default: 0 },
  lost: { type: Number, default: 0 },
}, { timestamps: true, collection: 'pursue_troops' });

/* -------------------------------------------------------------------- Loot */

/** The recovered spoil — a ledger of everything taken and recovered. */
export const LootSchema = new Schema({
  player_id: { type: String, required: true, index: true },
  pursuit_id: { type: String, default: null },
  kind: { type: String, default: 'harvest' },  // harvest | spoil | gift | salvage
  source: { type: String, default: '' },       // the work, or the verse
  items: { type: Map, of: Number, default: {} },
  claimed: { type: Boolean, default: true },
}, { timestamps: true, collection: 'pursue_loot' });

/* ---------------------------------------------------------------- Pursuit */

/** A fought battle, kept as the ledger of the pursuit. */
export const PursuitSchema = new Schema({
  player_id: { type: String, required: true, index: true },
  stage_id: { type: String, default: null },
  phase: { type: String, default: null },
  seed: { type: Number, default: 0 },
  won: { type: Boolean, default: false },
  stars: { type: Number, default: 0 },
  rewards: { type: Map, of: Number, default: {} },
  renown: { type: Number, default: 0 },
  casualties: {
    standing: { type: Number, default: 0 },
    fallen: { type: Number, default: 0 },
  },
  losses: { type: Map, of: Number, default: {} },
  elapsed: { type: Number, default: 0 },
  log: { type: [String], default: [] },
}, { timestamps: true, collection: 'pursuits' });

export const Player = mongoose.models.PursuePlayer || mongoose.model('PursuePlayer', PlayerSchema);
export const Encampment = mongoose.models.PursueEncampment || mongoose.model('PursueEncampment', EncampmentSchema);
export const Troops = mongoose.models.PursueTroops || mongoose.model('PursueTroops', TroopsSchema);
export const Loot = mongoose.models.PursueLoot || mongoose.model('PursueLoot', LootSchema);
export const Pursuit = mongoose.models.PursuePursuit || mongoose.model('PursuePursuit', PursuitSchema);
