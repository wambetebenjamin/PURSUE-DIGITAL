/**
 * index.js — the entry point.
 *
 * Starts the store (Mongo if MONGODB_URI is set, memory otherwise) and the
 * express app. Nothing else is required to run it:
 *
 *     node src/index.js
 *     PORT=8080 node src/index.js
 */

import { createApp } from './app.js';
import { createStore } from './store/index.js';

const PORT = Number(process.env.PORT) || 8080;
const HOST = process.env.HOST || '0.0.0.0';

async function main() {
  const store = await createStore(process.env.MONGODB_URI || null);
  const app = createApp(store);

  const server = app.listen(PORT, HOST, () => {
    const where = store.kind === 'mongo'
      ? `mongodb (${process.env.MONGODB_URI.replace(/\/\/[^@]*@/, '//***@')})`
      : 'memory (no MONGODB_URI set — state is lost on restart)';
    console.log(`PURSUE server listening on http://${HOST}:${PORT}  store: ${where}`);
  });

  const shutdown = async () => {
    server.close();
    await store.close();
    process.exit(0);
  };
  process.on('SIGINT', shutdown);
  process.on('SIGTERM', shutdown);
}

main().catch((err) => {
  console.error('PURSUE server failed to start:', err);
  process.exit(1);
});
