# 07 — The backend: PlayFab, Nakama, or AWS

## 1. What the reference's backend looks like, and why you should not copy it

The reference's server is a proprietary ByteDance stack:

```
Android app  →  HTTP to bsdk/gsdk/v3  →  session_token
             →  socket to the game server (address from /gsdk/game/server_list)
             →  protobuf RPC, msg.gs.<module>.C<Action> / S<Action>
             →  pushes over the same socket
```

You cannot use any of it. There is no free tier, no self-host option, and the
protocol is undocumented. What you *can* copy is the **shape**, and the shape
is good:

1. Account and session over HTTPS, in a boring JSON envelope.
2. The game itself over a single authenticated channel carrying protobuf.
3. Server-authoritative state; the client asks and is told.
4. Push events on the same channel, so expiry and completion are noticed
   immediately.

That is exactly what PlayFab, Nakama and a hand-rolled AWS stack all give you.
Pick on three axes.

## 2. The comparison

| | **PlayFab** | **Nakama** | **AWS (Lambda + DynamoDB)** |
| --- | --- | --- | --- |
| Setup time | 1 hour | 1 day | 1–2 weeks |
| Player identity | email/Google/Steam/Facebook | username or third-party | you build it |
| Realtime | PlayFab Multiplayer (Azure Relay) | **built in, purpose-made** | you build it (WebSocket API + Lambda) |
| Server-authoritative functions | CloudScript (JS/C#) | Go or TypeScript server modules | Lambda |
| Leaderboards | built in | built in | DynamoDB + Lambda |
| Storage | Player Data (size-limited) | built-in KV | S3 |
| Matchmaking | built in | built in | you build it |
| Groups / clans | built in | built in | you build it |
| Cost at launch | free to 5k MAU | self-hosted = fixed server cost; Hero Cloud = managed | pay-per-request, can be near zero |
| Protoc support | custom, awkward | **native, gRPC and JSON-RPC** | native with API Gateway |
| Unity SDK | good | excellent | none (you write it) |
| Hosting burden | none | none (or you run it) | none |
| Best for | fast ship, no backend team | **a game like this one** | max control, dedicated team |

### The recommendation

**Nakama**, and here is the specific reasoning for *this* game:

- The reference's protocol is **protobuf RPC over a socket with pushes**.
  Nakama's realtime API is exactly that shape: match loops, RPC handlers,
  and state pushed to clients. Mapping `C…`/`S…` onto Nakama RPC is close to
  one-to-one.
- A base-building strategy game is **authoritative-server plus a leaderboard
  plus a clan list plus a chat channel**. Nakama has all four built in.
- The reference's live-service retuning (`*_S10` const overrides) maps onto
  Nakama's **runtime config**, which is a KV store you can change without a
  redeploy.
- Self-hosted Nakama is a single Docker Compose file and one Postgres.

PlayFab is a fine second choice and the fastest route to "something live in a
week"; it is a poor fit if you want protobuf framing to match the reference
exactly, and CloudScript's execution model makes heavy per-tick simulation
awkward.

Pure AWS is only worth it with a dedicated backend engineer. Its real cost is
not the lambdas, it is the auth, the session, the push, the leaderboards, the
clans and the anti-cheat you have to write.

## 3. The Nakama plan, step by step

### Step 1 — Stand it up

```bash
docker run --name nakama -p 7350:7350 -p 7351:7351 -p 7352:7352 \
  -d nakama/nakama:3.17.0

docker run --name cockroach \
  -p 26257:26257 -d cockroachdb/cockroach:v23.1.14 \
  single-node-insecure --store=type=mem,size=2GiB --listen-addr=0.0.0.0:26257
```

Point the console at the database, create a project `pursue`, and add the Unity
client.

### Step 2 — Map the reference's messages onto Nakama

| Reference | Nakama |
| --- | --- |
| `gsdk/account/login` (HTTP) | Nakama authenticate (device id or Google) |
| `gsdk/account/exchange_token` | Nakama session token — same idea, same seam |
| `msg.gs.city.CBuildUpgrade` | `rpc("city_build_upgrade", payload)` |
| `msg.gs.city.SBuildUpgrade` | the RPC return value |
| `msg.gs.field.CEncounterStart` | `rpc("field_encounter_start", payload)` |
| `SPushCityReady` | match state / notifications, or a `match_data` update |
| `msg.gs.rank.CTypeRank` | leaderboard read (`nakama.lists.read`) |
| `msg.gs.social.*` | groups + chat, both built in |

### Step 3 — Write the server module (Go)

This is the whole authoritative core of the game. Note how little of it there
is, because the design keeps the client out of the numbers.

```go
package main

import (
    "context"
    "time"

    "github.com/heroiclabs/nakama-common/rt"
    "github.com/heroiclabs/nakama-common/messages"
)

// CityState is the authoritative city. The client never writes here.
type CityState struct {
    Sites      map[string]SiteState `json:"sites"`
    Resources  map[string]int64      `json:"resources"`
    LastCollect map[string]int64     `json:"last_collect"` // unix seconds
    Version    int64                `json:"version"`
}

type SiteState struct {
    BuildingID string `json:"building_id"`
    Level      int    `json:"level"`
    UpgradeEnds int64 `json:"upgrade_ends"`
}

func upgradeCost(level int) map[string]int64 {
    // Mirrors BuildingDefinition.levels[] — server side, always.
    table := []map[string]int64{
        {"wood": 120, "food": 80, "silver": 0},
        {"wood": 260, "food": 170, "silver": 40},
        {"wood": 520, "food": 330, "silver": 120},
        {"wood": 900, "food": 600, "silver": 300},
        {"wood": 1500, "food": 1000, "silver": 650},
    }
    if level < len(table) { return table[level] }
    return table[len(table)-1]
}

func buildUpgrade(ctx context.Context, logger *runtime.Logger, db *db.Database,
                 nk *nakama.Nakama, dispatcher runtime.Dispatcher,
                 runtime *runtime.Runtime) (string, error) {
    userID, _ := nakama.UserIDFromContext(ctx)
    var state CityState
    if err := db.Load(ctx, userID, "city", &state); err != nil {
        return "", err
    }

    siteID := stringField(messages.GetString(runtime, "site_id"))
    level  := intField(messages.GetInt(runtime, "level"))

    site, ok := state.Sites[siteID]
    if !ok { return "", errors.New("no such site") }
    if site.Level >= 5 { return "", errors.New("already at its highest") }

    cost := upgradeCost(site.Level)
    for kind, amount := range cost {
        if state.Resources[kind] < amount {
            return "", errors.New("not enough " + kind)
        }
        state.Resources[kind] -= amount
    }

    site.Level = level + 1
    site.UpgradeEnds = time.Now().Add(upgradeDuration(level)).Unix()
    state.Sites[siteID] = site
    state.Version++

    if err := db.Save(ctx, userID, "city", &state); err != nil { return "", err }
    return jsonString(state), nil
}
```

### Step 4 — The two rules that make it cheat-proof

**Rule 1 — offline production is computed on the server.**

```go
func collect(ctx context.Context, ..., userID string) (string, error) {
    var state CityState
    db.Load(ctx, userID, "city", &state)
    now := time.Now().Unix()
    var total map[string]int64
    for siteID, site := range state.Sites {
        last := state.LastCollect[siteID]
        hours := (now - last) / 3600
        if hours > 8 { hours = 8 }                 // offline cap
        yield := yieldPerHour(site.BuildingID, site.Level) * int(hours)
        for kind, amount := range yield { total[kind] += amount }
    }
    // state.LastCollect[siteID] = now
    // merge totals into state.Resources
    // save, return the new state
}
```

**Rule 2 — combat resolves on the server.**

The client sends *what it intends*, never *what happened*:

```go
func encounterStart(ctx context.Context, ...) (string, error) {
    stageID := stringField(messages.GetString(runtime, "stage_id"))
    seed := time.Now().UnixNano()                 // server picks the seed
    result := resolve(stageID, seed)              // deterministic, server-side
    db.Save(ctx, userID, "stage_"+stageID, result)
    return jsonString(result), nil
}
```

Because the seed comes from the server, a client that lies about the outcome
is trivially caught, and a client that replays a good result gets a different
seed and a different fight.

### Step 5 — Matchmaking and the field

Nakama's **matchmaker** handles the world map: a player searching for a stage
is matched into a 1-player "match" whose metadata carries the stage id and the
seed. The match loop relays `SPush` events (a hold has fallen, a company is
ready) to everyone in the match. For a single-player pursuit game you can
skip matches entirely and use plain RPC + `notifications` — but the
architecture is already there if you later add the tribe/rallies feature.

### Step 6 — Accounts, purchases, and the shell

| Reference | Yours |
| --- | --- |
| `gsdk/account/login` (Google/Facebook/VK) | Google Play Games / Apple / Steam sign-in, or device id for a frictionless first run |
| `v3/m_pay/order` + RSA receipt check | Google Play Billing / App Store, **receipt validated server-side** |
| `gsdk/announcement/list` | a Nakama KV read or a config file |
| `gsdk/game/server_list` | not needed — one world |
| IM websocket | Nakama chat |
| `pay_host`, `geas_host`, `asr_*` | none of it |

## 4. Data model

One KV record per player, one collection per world:

```
player:{userId}            → profile (name, faction, tutorial flags)
city:{userId}              → CityState
stage:{userId}:{stageId}   → stars, attempts, last result
hero:{userId}              → roster + levels + bonds
company:{userId}           → companies per tier
ledger:{userId}            → transaction log for the economy
config:season              → the live-service const overrides (the `*_S10` pattern)
```

The `config:season` record is the reference's most transferable idea: a
single KV row holds the current season's balance overrides, so retuning the
economy is a database update, not a release. Purse Digital should have the
same lever.

## 5. Deployment and cost

| Stage | Setup | Monthly |
| --- | --- | --- |
| Prototype | Nakama Console free tier | $0 |
| Closed test | self-hosted Nakama on a $12 VPS, Postgres on the same box | ~$15 |
| Launch | Nakama Hero Cloud, or a $40 VPS + managed Postgres | $40–200 |
| Growth | 2 app nodes, managed Postgres, CDN for asset bundles | $200–600 |

Assets are the bigger cost than the server: bundle them with Addressables
into a versioned, hashed manifest (doc 02) and serve from Cloudflare R2 or
Backblaze — pennies per gigabyte, and no egress fee.

## 6. A phased plan that never strands you

| Phase | Backend | Why |
| --- | --- | --- |
| 0 (now) | **none** — local save, offline | the city plays today with `localStorage`; ship the game while the server is designed |
| 1 | Nakama auth + `city` KV | accounts and cloud saves; one RPC pair |
| 2 | `collect` / `build_upgrade` on the server | the economy becomes authoritative |
| 3 | `field` encounters server-seeded | combat stops being client-trusted |
| 4 | leaderboards + the tribe | social, once the loop is proven |
| 5 | the covenant season config | live-service retuning |

Phase 0 is not a throwaway: it is the same model code with a local adapter
behind the same interface, so every later phase is a swap rather than a
rewrite.

```csharp
public interface IGameBackend
{
    Task<CityState> City();
    Task<CityState> Raise(string siteId, int level);
    Task<Harvest> EmptyTheWorks();
    Task<EncounterResult> BeginEncounter(string stageId);
    Task<IReadOnlyList<LeaderboardRow>> Roll(string rankType);
}
```

`LocalBackend` implements it against a save file today; `NakamaBackend`
implements it against the server later. The game code never changes.
