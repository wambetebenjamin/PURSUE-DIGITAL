# 04 — The real game API: protobuf RPC over the socket

## 1. What the protocol is

Recovered from the Lua constant tables (`FarmModel_Service_Proto.lua`,
`FarmModel_Service.lua`), the game speaks **protobuf messages named by
convention** and dispatched by a promise layer:

```
Net/NetPromise/ProtocalHandler      ← dispatch + promise
msg.gs.<module>.C<Action>           ← client → server
msg.gs.<module>.S<Action>           ← server → client
msg.gs.<module>.SPush<Event>        ← server-pushed event
msg.gs.rank.CTypeRank / STypeRank   ← dedicated request kinds
```

Three properties follow immediately, and they are the whole architecture:

1. **It is RPC, not REST.** One message in, one message out, correlated by a
   promise. No resources, no verbs, no status codes.
2. **The message name *is* the endpoint.** `CFarmersWorldBuyField` is both the
   request type and the route.
3. **`C…` / `S…` pairing is mechanical.** Every request has exactly one response
   of the same name with `C`→`S`. That makes code generation trivial and is
   worth copying literally in C#.

## 2. The recovered catalogue — the farm module

This is a complete, real module (3 bundles shipped in the APK, everything else
hot-updated):

| Client request | Server response | Meaning |
| --- | --- | --- |
| `CGetFarmersWorldDetail` | `SGetFarmersWorldDetail` | open the farm, get the whole board |
| `CFarmersWorldBuyField` | `SFarmersWorldBuyField` | buy a plot |
| `CFarmersWorldBuyPeople` | `SFarmersWorldBuyPeople` | hire a worker |
| `CFarmersWorldMakeTool` | `SFarmersWorldMakeTool` | craft a tool |
| `CFarmersWorldUseTool` | `SFarmersWorldUseTool` | equip a tool on an activity |
| `CFarmersWorldUnloadTool` | `SFarmersWorldUnloadTool` | unequip |
| `CFarmersWorldFixTool` | `SFarmersWorldFixTool` | repair (durability) |
| `CFarmersWorldGainQuery` | `SFarmersWorldGainQuery` | *what can I collect right now?* |
| `CFarmersWorldGainOutput` | `SFarmersWorldGainOutput` | collect it |
| `CFarmersWorldExchangeResource` | `SFarmersWorldExchangeResource` | swap one resource for another |
| `CFarmersWorldExchangeCoin` | `SFarmersWorldExchangeCoin` | sell for hard currency |
| `CFarmersWorldRecoverHP` | `SFarmersWorldRecoverHP` | restore worker stamina |
| `CFarmersWorldExchange` | `SFarmersWorldExchange` | shop exchange |
| *(server)* | `SPushFarmersWorldCanGain` | **push**: something is ready to collect |
| `CTypeRank` | `STypeRank` | leaderboard page |

Read that list as a design, not a list:

- **`GainQuery` / `GainOutput` separation** — the client asks *what is due*
  before it takes anything. This is how the server stays authoritative over
  offline accumulation while the UI still shows a live number. It is the single
  most important pattern in the whole dump.
- **`Buy*` for structure, `Make*` for items, `Use*` for equipping** — three
  verbs for three different kinds of ownership.
- **`RecoverHP`, `FixTool`** — the reference models worker exhaustion and tool
  durability. Both are soft time-gates.
- **`SPush…CanGain`** — the server proactively tells the client when production
  has ripened. Drives the red dot.

`RedDotManager.lua` (recovered) is the consumer of those pushes. The red-dot
system is not cosmetic; it is the retention loop.

## 3. The transport

| Layer | Reference | Evidence |
| --- | --- | --- |
| Byte layer | protobuf | message names in the Lua constant tables |
| Framing | length-prefixed message envelope | `ProtocalHandler` naming, `NetPromise` correlation |
| Transport | socket, with a UDP relay agent in the shell | `com.oasis.udp.UDPAgent`, `GameNetExpAgent`, `NetDiagnosisListener` |
| Session | `session_token` from `gsdk/account/exchange_token` | doc 03 |
| Routing | `server_list` gives `address` + `port` | doc 03 |
| Push | `SPush…` messages on the same socket | catalogue above |

There is no websocket for game state. The only websocket in the app is the IM
chat service (`wss://frontier-va.byteoversea.com/ws/v2`).

## 4. What to replicate, and what to replace

**Replicate:**

- `C<Action>` / `S<Action>` naming, mechanically paired.
- One promise-based dispatcher: `Call<TReq, TRes>(TReq) → Task<TRes>`.
- `Query` / `Commit` split for every timer-driven resource.
- Server pushes for expiry and completion.
- A `SPush` channel separate from request/response.

**Replace:**

- The socket. Use **gRPC-Web over HTTPS**, or Nakama's socket, or PlayFab's
  CloudScript. Payload stays protobuf; you lose nothing and gain TLS, retries,
  and load balancers.
- The UDP relay. It exists to survive flaky mobile networks; QUIC/WebTransport
  does that now.

## 5. A concrete C# contract

```csharp
// One request, one response, one route — the whole protocol in 40 lines.
public interface IGameRpc
{
    Task<TRes> Call<TReq, TRes>(TReq req, CancellationToken ct = default)
        where TReq : IMessage where TRes : IMessage, new();
}

public sealed class ProtobufRpc : IGameRpc
{
    private readonly IRpcTransport _transport;   // gRPC-Web, Nakama, whatever

    public async Task<TRes> Call<TReq, TRes>(TReq req, CancellationToken ct = default)
        where TReq : IMessage where TRes : IMessage, new()
    {
        // route name is derived, not hand-written:
        //   CFarmersWorldBuyField -> "gs.farmers_world.BuyField"
        var route = RouteName.From<TReq>();
        var bytes = await _transport.Invoke(route, req.ToByteArray(), ct);
        var res   = new TRes();
        res.MergeFrom(bytes);
        return res;
    }
}
```

House rule for PURSUE: **the client never mutates a resource.** It issues
`C…` and applies the `S…`. Every number on screen is the server's echo. That one
rule removes an entire class of cheating and desync bugs.

## 6. The honest limit

The three shipped Lua bundles are the farm, the glory factory (a crafting
building) and a delete-role dialog. The rest of the catalogue — building
upgrade, troop training, march dispatch, combat resolution — is **not in the
APK**; those modules arrive by hot update. So the *shape* of those endpoints is
known with certainty (they will be `C<Module><Action>` / `S<Module><Action>`
with the same Query/Commit split), but their exact names are not.

If you want them, the path is: on a rootable device or emulator, let the game
download its content, then pull the cached bundles from
`Android/data/com.oasis.immortal/files/` (the exact path is set by
`RocketDownloaderDepend`) and re-run `tools/ufs-extract.py` over them. That
recovers the full catalogue. It is a desktop task, not something this sandbox
can do.
