# PURSUE — reference teardown, the replication pack

This folder is the analysis of `decompiled-source/` (the JADX dump of
`LandofEmpires-0.1.136.apks`) turned into a build plan for **PURSUE**, a Unity
project on 1 Samuel 30:8.

Read in this order:

| # | File | What it answers |
| --- | --- | --- |
| 01 | [`01-runtime-and-config.md`](01-runtime-and-config.md) | What the app actually is, and every value in `config.json` / `url_config.json` / `hhld_info.json` |
| 02 | [`02-asset-pipeline.md`](02-asset-pipeline.md) | How content ships: bundles, manifests, naming, hot update, and what `AssetManifest` is |
| 03 | [`03-api-inventory.md`](03-api-inventory.md) | All 113 Retrofit endpoints, the response envelope, and **what they are not** |
| 04 | [`04-game-protocol.md`](04-game-protocol.md) | The real game API: protobuf RPC over the socket, with the message catalogue recovered from the Lua |
| 05 | [`05-game-logic.md`](05-game-logic.md) | Core loops, the recovered module map, formulas, and C# |
| 06 | [`06-unity-architecture.md`](06-unity-architecture.md) | The Unity class architecture for the 1 Samuel 30 build |
| 07 | [`07-backend.md`](07-backend.md) | PlayFab vs Nakama vs AWS, and the step-by-step server plan |
| 08 | [`08-gdd-pursue.md`](08-gdd-pursue.md) | The game design document |
| 09 | [`09-roadmap.md`](09-roadmap.md) | Sprints, with acceptance gates |

## The one correction that matters

The brief assumed the game's backend is Retrofit2 and that the Retrofit files
would reveal login, upgrade, attack and collect. **They do not.** Every one of
the 113 Retrofit endpoints belongs to the ByteDance **TTGame SDK** — accounts,
payments, compliance, sharing, notices, server lists. The game's own state
never travels over HTTP.

The real protocol is protobuf RPC over a socket, and it is visible in the Lua:

```
msg.gs.festival_activity.CGetFarmersWorldDetail   ← client request
msg.gs.festival_activity.SGetFarmersWorldDetail   ← server response
```

`C…` = client→server, `S…` = server→client, grouped by module. That is the API
to replicate, and it is documented in 04. Everything in 03 is still worth
knowing — it is the whole account/payment/anti-cheat spine you have to rebuild
or buy — but it is not the game.

## What was verifiable, and what was not

Verified directly from the files in this repository:

- `config.json`, `url_config.json` (full contents), `AndroidManifest.xml`
- `hhld_info.json` and `AssetManifest-A46D6781` — **both encrypted**, 232 B and
  686 KB; the client key is inside IL2CPP and is not recoverable here
- 379 asset bundles, unpacked and inspected (UnityFS v8, Unity 2022.3.62f2)
- 3 Lua bundles, decompressed; the Lua is **Lua 5.3 bytecode**, not source —
  names, module paths and protobuf message names were recovered from its
  constant tables
- 113 Retrofit endpoints across 59 interfaces, extracted with annotations
- the Java package map (`com.oasis.*`, `com.bytedance.*`, `gsdk.*`)

Not recoverable without a desktop toolchain:

- **game logic in C#** — IL2CPP (`libil2cpp.so`); needs Il2CppDumper on a PC
- **`AssetManifest` contents** — encrypted; needs the key from IL2CPP
- **Lua source** — only bytecode ships; a 5.3 decompiler on a PC recovers it

Nothing in this pack is guesswork dressed as fact: where a number is inferred
from the reference's genre conventions rather than read from the files, it says
so.

## Reproducing the extraction

`tools/ufs-extract.py` (new) unpacks UnityFS bundles and dumps the Lua constant
tables without any external tooling:

```bash
python3 tools/ufs-extract.py decompiled-source/resources/assets/AssetBundle/lua/game/<bundle> out/
```

It needs `lz4` (`pip install lz4`). It handles the two alignment flags that
trip most extractors (`0x40` blocks-info padding, `0x200` data padding).
