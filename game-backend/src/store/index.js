/**
 * store/index.js — persistence, with two faces.
 *
 * `MongoStore` is the production one (Mongoose). `MemoryStore` is the same
 * interface over plain Maps, so the whole server runs — and the whole test
 * suite runs — without a database.
 *
 * The Mongo store lives in its own module and is loaded only when a
 * database is actually configured. That is what keeps mongoose out of the
 * serverless bundle.
 */

import { MemoryStore } from './memory.js';

export { MemoryStore };

/**
 * Build the store. Falls back to memory when there is no database, so the
 * server always starts.
 * @param {string} [uri]
 */
export async function createStore(uri = process.env.MONGODB_URI || null) {
  if (!uri) {
    const store = new MemoryStore();
    await store.connect();
    return store;
  }
  const { MongoStore } = await import('./mongo.js');
  const store = new MongoStore(uri);
  await store.connect();
  return store;
}
