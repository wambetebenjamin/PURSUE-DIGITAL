# 09 — Roadmap

Ten sprints. Each ends with something playable in a browser and a phone, and
a gate that must pass before the next begins.

## Sprint 0 — the reference teardown *(done)*

- Pull the decompiled source, unpack the UnityFS bundles, recover the Lua
  module map and the `msg.gs.*` catalogue.
- **Deliverable:** this pack — nine documents, and `tools/ufs-extract.py`.
- **Gate:** every claim in the pack traces to a file in the repository.

## Sprint 1 — the Unity skeleton

- Unity 2022.3 project, URP, IL2CPP/ARM64, landscape.
- Port the framework: `ObservableValue`, `EventBus`, `BindGroup`, `Promise`.
- Scene shell: Boot → City → Field, with the `UIManager` three-layer rule.
- **Gate:** the city scene renders on a phone, 60fps, with a placeholder Works.

## Sprint 2 — the city, for real

- `CityGrid`, `BuildingSite`, `BuildingDefinition` assets for the seven Works.
- The walled plateau, procedurally built: wall, towers, gatehouse, roads,
  plaza, works, people.
- Camera rig: orbit, pan, zoom, with the framing from the shipped build
  (pitch 0.80, distance 78).
- **Gate:** you can look at a city that is yours, at three zoom levels, and it
  holds together.

## Sprint 3 — the Works actually work

- Production with the offline cap, the site card, the upgrade cost, the
  visible change per level.
- Resource ledger, spend, and the "Empty the works" action.
- The red-dot system, ported from `RedDotManager`.
- **Gate:** leave the game for an hour, come back, and the works have produced
  exactly what the server-free model says they should.

## Sprint 4 — the march

- Third-person traversal of real ground, the road, the wadi.
- Company units, formations, the encounter resolver (deterministic, seeded).
- The three flavours: Stealth, Dungeon, Field.
- **Gate:** a hold can be taken three ways, and the outcome differs.

## Sprint 5 — the Chart and the campaign

- The vellum map, holds, states (held / known / rumoured), contiguity.
- The seven phases of the charge, each opening a system.
- Stars: swift, silent, unseen.
- **Gate:** the campaign can be played from the first phase to the seventh.

## Sprint 6 — the heroes

- Twelve heroes, four classes, skills at level 5 and 10.
- Bonds, and the company-assembly screen.
- Manual skill moments in a Dungeon encounter.
- **Gate:** a hold marked *silent* cannot be taken without a Silent hero, and
  the game says so.

## Sprint 7 — the live service, phase 1

- Nakama: authenticate, `city` KV, leaderboards.
- `LocalBackend` → `NakamaBackend` swap behind one interface.
- Receipt validation for the store purchase.
- **Gate:** two devices, one account, one city.

## Sprint 8 — the live service, phase 2

- Server-authoritative production and combat.
- The covenant season config (the `*_S10` override pattern).
- Push events for expiry and completion.
- **Gate:** editing the server's yield numbers changes the economy for every
  player without a release.

## Sprint 9 — the tribe and the polish

- Groups, rallies, the watchmen's roll.
- Addressables content groups; a versioned, hashed manifest.
- Performance pass, a11y, device testing on real hardware.
- **Gate:** a first-run install is under 200 MB and plays on a three-year-old
  phone.

## Sprint 10 — the launch

- Store listing, screenshots, privacy policy, age rating.
- Crash reporting, analytics, a rollback plan.
- **Gate:** the build is signed, staged, and installable by a stranger.

---

## The gates, in one table

| Sprint | The gate |
| --- | --- |
| 0 | every claim traces to a file |
| 1 | the city renders at 60fps on a phone |
| 2 | the city holds together at three zoom levels |
| 3 | offline production is exactly right |
| 4 | a hold can be taken three ways, differently |
| 5 | phases one to seven are playable |
| 6 | a *silent* hold needs a Silent hero |
| 7 | two devices, one city |
| 8 | server numbers retune the economy with no release |
| 9 | under 200 MB, playable on old hardware |
| 10 | a stranger can install it |

## Known risks

| Risk | Mitigation |
| --- | --- |
| Unity toolchain unavailable in this environment | the docs and assets are the deliverable; Unity work needs a machine with the editor |
| The remaining `msg.gs.*` catalogue is not in the APK | pull the downloaded bundles off a device and re-run `tools/ufs-extract.py` |
| Balance values are in IL2CPP, not the Lua | the config tables are ours to design; the *shape* is what we needed |
| Live-service scope creep | the tribe is Sprint 9, and it is optional; nothing before it depends on it |
| Scope: the reference has features we are not replicating | research/talents, fog expeditions and alliances are explicitly deferred, not half-built |

## What we deliberately are not building

From the reference's full feature list, three things are **out of scope** for
the launch, and this is a decision, not an oversight:

1. **Research / talent trees** — they add a currency sink, not a decision.
   The Works' levels are the progression.
2. **World-map fog expeditions** — the Chart's *rumoured* holds give the same
   feeling for a fraction of the code.
3. **Alliances and rallies** — one tribe with 30 people in it is a chat
   feature; the tactical value is low for a single-player pursuit.

If they come back, the architecture already has room: `msg.gs.social.*` on the
wire, `Game/Pack` in the Lua, `relationship` in the shell.
