# Client architecture — talking to the server

**What this answers.** The brief's fourth phase: how we build the front-end
(Unity or browser), how it connects to `game-backend/`, and how it handles
offline and online synchronisation *the way the decompiled game handled it*.

**The short answer.** The browser build ships first, because it is the build
that already exists and the one the player can open in a link. The Unity port
follows the same protocol with no server changes — the protocol is a list of
message names, and both clients speak it. Synchronisation is
**server-authoritative with a local mirror**: the client never invents a
number, and the server is the only thing that decides what the player owns.

---

## 1. The decision: browser first, Unity second

| | Browser (Three.js + Vite) | Unity 2022.3 (the reference's engine) |
| --- | --- | --- |
| Ships today | **yes** — `src/`, deployed to Vercel | no — source ready, needs the editor |
| Server fit | `fetch` + `localStorage`, works offline | `HttpClient` + PlayerPrefs, works offline |
| Cost | zero | needs a licence and a machine |
| Fidelity | the citadel is 3D; the march is 3D | higher, but the same content |

The reference runs Unity **2022.3.62f2** (recovered from the bundle header),
Lua 5.3 for gameplay, IL2CPP for the binary. Its own architecture is
recovered in `docs/reference/06-unity-architecture.md` — `ObservableValue`,
`BindGroup`, `Promise`, `ProtocalHandler`, and the three-layer UI rule
(`Layer` / `MutexPop` / `AddictivePop`).

**The Unity port does not need a second server.** The protocol is a catalogue
of message names; `docs/reference/04-game-protocol.md` §5 already shows the
C# dispatcher, and `game-backend/src/rpc.js` is the same catalogue in
JavaScript. A C# `IGameRpc` that derives the route from the type name
(`CFarmersWorldBuyField` → `gs.farmers_world.BuyField`) speaks to our
`POST /api/rpc/<module>/<action>` unchanged.

---

## 2. The shape of the conversation

```
        browser                                   server
           │                                        │
           │  POST /api/auth/login                  │
           │  {device_id, name}                     │
           │───────────────────────────────────────►│  role + session_token
           │◄───────────────────────────────────────│
           │                                        │
           │  GET /api/camp/detail                  │   ← the query half
           │───────────────────────────────────────►│  what the server thinks
           │◄───────────────────────────────────────│  {camp, rate, next_finish_at}
           │                                        │
           │  POST /api/camp/gain/collect           │   ← the commit half
           │───────────────────────────────────────►│  the server pays
           │◄───────────────────────────────────────│  {given, resources}
           │                                        │
           │  POST /api/combat/pursue               │
           │  {stage_id, seed, skill_timings}       │
           │───────────────────────────────────────►│  the server replays the fight
           │◄───────────────────────────────────────│  {won, stars, losses, log}
```

Every answer is the SDK's envelope: `{code, message, data}`.

**The one rule, copied from the reference:** *the client never invents a
number.* It issues a `C…` and applies the `S…`. Every quantity on screen is
the server's echo. That single rule removes an entire class of cheating and
desync bugs, and it is the reason the fight is resolved server-side.

---

## 3. The sync model

### 3.1 What the reference does

Recovered from the Lua (docs 04 and 05):

1. The server pushes `SPushFarmersWorldCanGain` when something ripens.
2. The client asks `CFarmersWorldGainQuery` — *what exactly is ready?*
3. The client asks `CFarmersWorldGainOutput` — *take it*.
4. The server replies `SFarmersWorldGainOutput` with **authoritative totals**.
5. Offline accumulation is entirely server-side, and capped.

`TimeCtrl` / `TimeModel.GetTimeNow` make the point sharper: **the server's
clock is authoritative**. The client never trusts its own.

### 3.2 What we do

Same model, one substitution: **pushes become a poll**, because a browser
cannot hold a socket cheaply and a serverless function cannot push. The
reference's `SPush…` messages are served as a queue the client drains on
every reconnect — the message names survive, the transport does not.

| Reference | Ours |
| --- | --- |
| `SPushFarmersWorldCanGain` down the socket | the red dot is computed from `CEncampmentGainQuery`; the game already polls its red-dot tree on every model change |
| `CFarmersWorldGainQuery` | `CEncampmentGainQuery` — `GET /api/camp/gain/query` |
| `CFarmersWorldGainOutput` | `CEncampmentGainOutput` — `POST /api/camp/gain/collect` |
| server-side offline accrual, capped | same: `OFFLINE_CAP_HOURS = 8`, computed on the server clock |
| `TimeModel.GetTimeNow` | `ctx.now` on the server; the client never computes accrual |

### 3.3 The reconciliation order

`src/net/pursueServer.js` does this, and the smoke suite proves it:

1. **Log in** if there is no session (or the session was refused).
2. **Ask** the server what it has (`CGetEncampmentDetail`).
3. **Adopt** the server's camp as the truth — *if the server has one*.
4. **Replay** the actions taken while the server was unreachable, in order.
5. **Adopt** the server's answer again.

Step 3 has a guard that matters: if the server has no camp for this role
(a fresh role on a recycled instance), the client **keeps its local camp** and
lets the queue carry the work up. The server can never wipe a player's
progress by being empty.

### 3.4 The offline queue

| Situation | What the client does |
| --- | --- |
| The server is unreachable and the action is queueable (`Upgrade`, `GainOutput`, `Exchange`, `Produce`, `Collect`, `Recruit`, `Train`, `Heal`, `Drill`) | hold it in `state.queue`, in order, and report "offline" |
| The server is unreachable and the action is *not* queueable (a query) | return nothing; the client shows its local mirror |
| The server is reachable but **refuses** (a non-zero `code`) | raise the error — a refusal is a decision, not an outage, and must not be retried blindly |
| The network comes back | replay the queue in order, then re-read the server's answer |

The distinction between *refused* and *unreachable* is the important one. It
is why the client checks `err.code` before deciding to queue: a network error
has no code, a server refusal always does.

---

## 4. The browser build, as it stands

```
src/
  core/      progression.js  muster.js  skirmish.js  Game.js  settings.js …
  world/     citadel/  zones/  props/  atmospheres.js
  ui/        Citadel.js  WarTable.js  SkirmishBoard.js  HUD.js  PauseMenu.js
  net/       pursueServer.js      ← the client for game-backend/
```

`src/net/pursueServer.js` is written and tested (14 smoke checks) but **not
yet wired into the UI**. That is deliberate, and here is why: the deployed
server function keeps its state in a warm instance, so a player's camp would
be lost when the instance is recycled. Wiring it in before the server has a
database would trade a local game that remembers everything for a networked
one that sometimes forgets. The wiring is a one-page change once
`MONGODB_URI` is set — see §7.

### How it will slot in

| The game today | The networked version |
| --- | --- |
| `progression.get()` / `progression.upgrade(id)` | `progression` stays the **mirror**; `upgrade()` calls `net.upgradeWork(id)` and applies the server's answer |
| `progression.collect(now)` | `net.queryGain()` for the number on screen, `net.collectGain()` to take it |
| `muster.recruit(id, n)` | `net.recruit(id, n)`, then apply the server's count |
| `settleSkirmish(stage)` | the fight is **replayed on the server** (`net.pursue(stage.id, {seed, skill_timings})`); the client animates the server's log rather than its own simulation |
| `localStorage` (the campaign) | the campaign stays as the local mirror; the server holds the truth |

The client's own simulator (`src/core/skirmish.js`) and the server's
(`game-backend/src/services/combat.js`) are **the same engine**: the same
xorshift RNG, the same 0.1-second beats, the same mitigation, the same skill
semantics. Given the same seed and the same skill timings, they produce the
same battle. That is deliberate — it means the server can verify a client's
fight instead of merely replacing it, and the animation the player watches is
the fight that was actually settled.

---

## 5. The Unity build

The Unity client is the reference's architecture with our content. The
mapping is in `docs/reference/06-unity-architecture.md`; the parts that touch
the network are here.

### 5.1 The dispatcher

```csharp
public interface IGameRpc
{
    Task<TRes> Call<TReq, TRes>(TReq req, CancellationToken ct = default)
        where TReq : IMessage where TRes : IMessage, new();
}

public sealed class HttpGameRpc : IGameRpc
{
    private readonly string _base;      // "https://pursue-digital.vercel.app/api"

    public async Task<TRes> Call<TReq, TRes>(TReq req, CancellationToken ct = default)
    {
        var route = RouteName.From<TReq>();          // CEncampmentUpgrade → encampment/Upgrade
        var body = JsonSerializer.Serialize(new { msg = req });
        using var http = _http.CreateRequest($"rpc/{route}", HttpMethod.Post);
        http.Headers.Authorization = new AuthenticationHeaderValue("Bearer", _token);
        http.Content = new StringContent(body, Encoding.UTF8, "application/json");

        using var res = await http.SendAsync(ct);
        var env = await res.Content.ReadFromJsonAsync<Envelope>(cancellationToken: ct);
        if (env.Code != 0) throw new GameException(env.Code, env.Message);

        var out_ = new TRes();
        out_.MergeFromJson(env.Data);
        return out_;
    }
}
```

### 5.2 The observable model

The reference's `Core/ModelValue` + `Core/BindGroup` + `Core/Promise` map
onto ours without change:

```csharp
public sealed class EncampmentModel : ObservableValue<EncampmentState>
{
    public void Apply(SEncampmentDetail reply) => Value = reply.Camp;   // the server's echo
}

public sealed class CitadelView : MonoBehaviour
{
    private readonly BindGroup _binds = new();

    private void OnEnable()
    {
        _binds.Bind(_model.OnChanged, Apply);   // unbound when the view closes
    }
    private void OnDisable() => _binds.UnbindAll();
}
```

The rule that carries over from the reference: **the view never polls.** The
model raises, the view binds, the view redraws. The server's reply is what
raises it.

### 5.3 Offline in Unity

The same three rules, with Unity's vocabulary:

| Rule | Browser | Unity |
| --- | --- | --- |
| keep the last server state | `localStorage['pursue.net.v1']` | `PlayerPrefs` (JSON) |
| hold what could not be sent | `state.queue` | a JSON queue in `PlayerPrefs` |
| the server's clock is authoritative | `ctx.now` | the server's `now` in every reply |

Unity's `Application.internetReachability` is a hint, not a fact — treat a
failed request as the only reliable signal of being offline, exactly as the
browser client does.

### 5.4 The Unity project layout

```
unity/PURSUE/
  Assets/
    Scripts/
      Core/      ObservableValue.cs  BindGroup.cs  Promise.cs  Logger.cs
      Net/       IGameRpc.cs  HttpGameRpc.cs  Envelope.cs  ProtocolRoute.cs
      Model/     EncampmentModel.cs  TroopsModel.cs  PursuitModel.cs
      Game/      Camp/  Troops/  Pursuit/            (Model + View pairs)
      UI/        UIManager.cs  UIConfig.cs  Layer/  MutexPop/  AddictivePop/
    Data/       config tables as ScriptableObjects
  Packages/     manifest.json  (addressables, uGUI, TextMeshPro)
```

---

## 6. Rules the server enforces, so the client does not have to

| Rule | Where it lives | Why |
| --- | --- | --- |
| the client cannot grant itself resources | every mutation is a server-side recomputation | the reference's core rule |
| offline accrual is capped at 8 hours | `economy.js`, `OFFLINE_CAP_HOURS` | the reference caps it too |
| a company cannot exceed the barracks cap | `muster.js`, `companyCap` | the reference's `populationCap` |
| a tier cannot exceed the barracks gate | `muster.js`, `train` | the reference's tier ladder |
| the fight is resolved on the server | `combat.js` | the client reports a fight; the server settles it |
| the server's clock decides when a slot ripens | `ctx.now` | `TimeModel.GetTimeNow` |
| the season overrides the economy without a client build | `SEASON_OVERRIDES`, served at `/api/meta/config` | the reference's `*_S10` pattern |

---

## 7. Turning the network on — the order that works

1. **Set a database.** `MONGODB_URI` on the server; the Mongoose store is
   already written (`game-backend/src/store/mongo.js`) and needs no other
   change. Then the state survives cold starts and the queue can be trusted.
2. **Point the client at the server.** `new PursueServer({ base: '/api' })` —
   the same origin as the game, so no CORS and no configuration.
3. **Make the mirror authoritative-on-connect.** The client already asks
   before it uploads; step 3 of the reconciliation order (§3.3) is the guard.
4. **Move one button at a time.** Start with `Upgrade` (cheap, idempotent,
   observable), then `Recruit`, then `collect`. Leave the fight until last —
   it is the only one whose client-side simulation has to be replaced by the
   server's replay.
5. **Keep the local campaign as the fallback.** If the server is unreachable
   at start-up, the player plays the local game. Nothing is lost, and the
   queue catches up later.

---

## 8. What is built, and what is not

| Piece | State |
| --- | --- |
| `game-backend/` — express + mongoose + 44 passing tests | **done** |
| the message catalogue, served at `/api/meta/rpc` | **done** |
| the browser client, `src/net/pursueServer.js` | **done** (14 smoke checks) |
| the client wired into the UI | **not done** — waiting on §7.1 |
| the Vercel function at `api/[...path].js` | **done**, but with in-memory state |
| a persistent database | **not configured** — `MONGODB_URI` unset |
| the Unity project | **not built** — the architecture is in doc 06 and §5 above |
