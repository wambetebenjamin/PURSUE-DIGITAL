# PURSUE — the server for Ziklag

The backend for **PURSUE**, a strategy game on 1 Samuel 30:8. It is the
server the reference *should* have had: same mechanics, same protocol shape,
same "the client never invents a number" discipline — different theme, our
own code, our own content.

```
npm install
npm test          # 44 checks
npm start         # http://0.0.0.0:8080
```

No database is required: without `MONGODB_URI` the server keeps its state in
memory and says so in the log.

---

## What it is

| | |
| --- | --- |
| **Runtime** | Node 18+ , ES modules |
| **Framework** | Express 4 (the core itself is framework-free — see `src/core.js`) |
| **Database** | MongoDB via Mongoose 8, or in-memory |
| **Tests** | `node:test` — 44 checks over the mechanics and the HTTP surface |
| **Protocol** | `C…`/`S…` message pairs, the SDK's `{code, message, data}` envelope |

The last row is the point. The reference speaks protobuf RPC over a socket,
with one message in and one message out and the message *name* being the
endpoint (`CFarmersWorldBuyField` → `SFarmersWorldBuyField`). That naming is
worth copying, so every route here declares a request and a response name,
and the same handlers are reachable at `/api/rpc/<module>/<action>`.

`GET /api/meta/rpc` returns the whole catalogue, with the reference's own
names beside ours.

---

## The routes

| Method | Route | Message | What it does |
| --- | --- | --- | --- |
| POST | `/api/auth/login` | `CLogin` | Establish a role; returns a session token |
| POST | `/api/auth/token` | `CExchangeToken` | Trade a session for a fresh one |
| POST | `/api/auth/role` | `CDeleteRole` | Wipe the role |
| GET | `/api/server/list` | `CServerList` | The shards, with address and port |
| GET | `/api/camp/detail` | `CGetEncampmentDetail` | The whole camp in one reply |
| POST | `/api/camp/upgrade` | `CEncampmentUpgrade` | Raise a work one level |
| GET | `/api/camp/gain/query` | `CEncampmentGainQuery` | **What is ready to be taken?** |
| POST | `/api/camp/gain/collect` | `CEncampmentGainOutput` | Take it |
| POST | `/api/camp/exchange` | `CEncampmentExchange` | The exchange table |
| POST | `/api/camp/exchange/resource` | `CEncampmentExchangeResource` | One resource for another |
| POST | `/api/camp/produce` | `CWorkProduce` | Queue production slots |
| POST | `/api/camp/produce/collect` | `CWorkCollect` | Take the finished slots |
| GET | `/api/camp/work` | `CGetWorkInfo` | The state of every work |
| GET | `/api/troops` | `CTroopsDetail` | The band, and the order of battle |
| POST | `/api/troops/recruit` | `CTroopsRecruit` | Muster a press of ten |
| POST | `/api/troops/train` | `CTroopsTrain` | Raise a company's tier |
| POST | `/api/troops/heal` | `CTroopsHeal` | Bring the wounded back to the line |
| POST | `/api/troops/drill` | `CTroopsDrill` | Drill a mighty man |
| GET | `/api/troops/roster` | `CTroopsRoster` | The mighty men, and their skills |
| POST | `/api/combat/pursue` | `CCombatPursue` | Fight a skirmish, or finish a phase |
| GET | `/api/combat/preview` | `CCombatPreview` | The order of battle and the stages |
| GET | `/api/loot` | `CLootList` | The ledger of recovered spoil |
| GET | `/api/rank/:type` | `CTypeRank` | A leaderboard page |
| GET | `/api/meta/rpc` | — | The message catalogue |
| GET | `/api/meta/config` | — | The config tables |
| GET | `/api/meta/pushes` | — | The push channel |
| GET | `/api/health` | — | Liveness, and which store is in use |

Every reply is the SDK's envelope:

```json
{ "code": 0, "message": "ok", "data": { } }
```

`code` 0 is success; the failures use the SDK's own error numbers
(`-130001` parameter, `-130002` token invalid, `-130006` server error).

---

## The mechanics, and where they come from

Nothing here is invented. Every rule is the reference's, recovered from the
decompiled dump (see `docs/reference/05-game-logic.md`) and re-expressed in
our theme:

| Rule | Where | The reference's name |
| --- | --- | --- |
| the client asks what is owed before it takes | `services/economy.js` | `CFarmersWorldGainQuery` → `CFarmersWorldGainOutput` |
| offline accrual is capped at 8 hours | `services/economy.js` | the offline cap |
| a slot makes the work finish sooner and cost more | `services/economy.js` | `REFINED_SANDS_PRODUCTION_BP_SPEED_UP` |
| the barracks gates the size of a company | `services/muster.js` | `populationCap` |
| the barracks gates a company's tier | `services/muster.js` | the tier ladder |
| seven in ten of the fallen are wounded | `services/muster.js` | the hospital |
| the fight is resolved by one deterministic engine | `services/combat.js` | the field battle |
| the server's clock decides when a slot ripens | `ctx.now` | `TimeModel.GetTimeNow` |
| the season can retune the tables without a client build | `services/config.js` | `*_S10` const overrides |

The theme mapping — every original name beside ours — is in
[`../docs/THEME_MAPPING.md`](../docs/THEME_MAPPING.md).

---

## The models

`src/models/index.js`, Mongoose:

| Model | What it holds |
| --- | --- |
| `Player` | the role (`role_id`, `name`, `level`, `server_id`, …) and the session token — the SDK's `Role` bean, field for field |
| `Encampment` | the camp: the stores, the works, the production queues, the roster, renown |
| `Troops` | the band: five companies, their tiers and their wounded, and the ledger of wins |
| `Loot` | the recovered spoil — every harvest, every prize, with its source |
| `Pursuit` | a fought battle: the seed, the result, the stars, the casualties, the log |

---

## Layout

```
src/
  core.js        the server, with no framework in it (both adapters use it)
  app.js         the express adapter
  index.js       the entry point
  envelope.js    {code, message, data} and the SDK's error numbers
  auth.js        the session: login, token, the guard
  rpc.js         the message catalogue
  routes/        auth, camp, troops, combat, meta
  services/      config, economy, muster, combat, state
  store/         memory, mongo
  models/        the Mongoose schemas
test/            economy, muster, combat, api
```

`src/core.js` is deliberately free of express and mongoose, so the same code
runs as a Vercel serverless function (`api/[...path].js` in the repository
root) without pulling a web framework or a database driver into the build.

---

## Configuration

| Variable | Default | Meaning |
| --- | --- | --- |
| `PORT` | `8080` | the port to listen on |
| `HOST` | `0.0.0.0` | the interface to bind |
| `MONGODB_URI` | *(unset)* | a MongoDB URI. Without it the state is in memory and is lost on restart |

```bash
cp .env.example .env   # then edit it
MONGODB_URI="mongodb+srv://user:pass@cluster.mongodb.net/pursue" npm start
```

---

## The browser client

`src/net/pursueServer.js` in the repository root talks to this server:

```js
import { PursueServer } from '../src/net/pursueServer.js';

const net = new PursueServer({ base: '/api' });
await net.connect('David');
await net.upgradeWork('threshing');
```

It keeps a local mirror, holds what it cannot send, and replays it in order
when the server returns. The full contract is in
[`../docs/CLIENT_ARCHITECTURE.md`](../docs/CLIENT_ARCHITECTURE.md).
