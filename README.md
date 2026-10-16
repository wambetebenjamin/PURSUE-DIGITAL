# PURSUE — 1 Samuel 30

A complete, low-poly **3D action-adventure web game** for desktop browsers, retelling the
pursuit of the Amalekites from **1 Samuel 30**. Built with **Three.js + Vite** (pure WebGL,
no game engine runtime), fully modular, and deployable to **Vercel** as a static site.

> Ziklag is burning. The families are taken. Two hundred men are too exhausted to cross the
> brook — you press on with four hundred. Find the Egyptian servant. Descend upon the camp.
> Divide the spoil. Send portions to Judah. **Recover all.**

---

## Quick start (local)

```bash
npm install
npm run dev        # → http://localhost:5173
```

Production build & local preview:

```bash
npm run build      # emits static site into dist/
npm run preview    # serves dist/ at http://localhost:4173
```

Headless logic test (walks the entire seven-phase state machine in Node):

```bash
npm run smoke
```

---

## Deploy to Vercel

The project is a standard Vite static build — Vercel auto-detects it:

1. Push this repository to GitHub.
2. On [vercel.com](https://vercel.com) → **Add New Project** → import the repo.
3. Vercel presets: **Framework = Vite**, **Build = `npm run build`**, **Output = `dist`** (auto-detected).
4. **Deploy** — the game is playable at `https://<project>.vercel.app` in any desktop browser.

Or from the CLI:

```bash
npm i -g vercel
vercel            # follow the prompts; defaults are correct
vercel --prod     # production deployment
```

No server, database, or environment variables are required. Everything is client-side.

---

## Controls

| Input | Action |
| --- | --- |
| **WASD / Arrow keys** | Move (camera-relative) |
| **Mouse** | Orbit the third-person camera (click canvas to capture the pointer) |
| **Mouse wheel** | *(reserved)* |
| **Shift** | Run — drains the stamina bar (15 units/sec) |
| **E / Space** | Interact, pick up, advance dialogue |
| **Esc** | Release the mouse pointer |

---

## The seven phases (finite state machine)

Mapped 1:1 to the production master-prompt pipeline — see
[`docs/PRODUCTION_BIBLE.md`](docs/PRODUCTION_BIBLE.md) for the full
lighting / PBR / composition translation.

| State | Phase | Core content |
| --- | --- | --- |
| `STATE_0_ZIKLAG` | **Ziklag in Ruins** (5600K golden hour) | Burnt mud-brick houses, black granary smoke column, ember particles, sand drifts at the gates, volumetric dust → debris trail to the **Desert Edge** waypoint |
| `STATE_1_DESERT_MARCH` | **The Pursuit Through Besor** (6500K midday) | Sun-bleached dunes, chalky limestone walls (instanced), churning brown **Besor Brook** with foam & blinding speculars, the exhausted **200** collapsed on the left ledge, stamina-gated running |
| `STATE_2_EGYPTIAN_SERVANT` | **The Egyptian Servant** | Seated NPC, proximity prompt *"Press E to give bread and water"*, 3-node dialogue tree, then a **3D directional arrow** unlocks toward the camp |
| `STATE_3_AMALEKITE_RESCUE` | **Night Ambush at the Camp** (3200K starlight) | Tents, bonfires, intoxicated Amalekites on rugs, spilled wine (zero-roughness), piles of pillaged gold & silver, **vision cones** (distance + facing checks), central cage of captives → the 24-hour battle cards (1 Sam 30:17–19) |
| `STATE_4_SPOILS_DIVISION` | **Spoils & Separation at Besor** (5200K) | Symmetric staging: the blood-stained 400 vs. the 200 with the baggage train, an instanced herd of recovered sheep/goats/cattle as the central barrier, David's outcrop → the statute of equal share (1 Sam 30:21–25) |
| `STATE_5_DISTRIBUTION` | **Diplomatic Distribution** (4800K) | Hebron's limestone gate arch, elders in purple & madder receiving cedar tribute chests, unloading mule trains, gold & silver glare → gifts to Judah (1 Sam 30:26–31) |
| `STATE_6_GILBOA_EPILOGUE` | **The Fall of Saul** (7000K overcast) | Steep basalt ridge, low mist & wind-whipped drizzle, the broken chariot wheel, dented bronze Saul with heavy crown, shattered shield-wall, Philistine ranks pressing up the slope → the epilogue and victory (1 Sam 31) |

Persistent HUD (HTML/CSS above the canvas): **objective text · stamina meter · compass heading**,
plus interaction prompts, alert banners, and responsive modals.

---

## File architecture

```
PURSUE-DIGITAL/
├── index.html                  # canvas mount + full HTML UI overlay (HUD, modals, screens)
├── package.json
├── vite.config.js              # static build config (chunked vendor bundle)
├── scripts/
│   └── smoke.mjs               # headless Node test: boots the FSM and plays through all 7 phases
└── src/
    ├── main.js                 # bootstrap: engine + game + title/victory wiring
    ├── constants.js            # FSM names, tunables (speed/stamina/vision), narrative copy
    ├── core/
    │   ├── Engine.js           # WebGL renderer, clock, resize, the single RAF loop
    │   ├── Game.js             # orchestrator: FSM, phase logic, per-frame update order
    │   ├── StateMachine.js     # generic FSM (enter/update/exit)
    │   ├── Input.js            # keyboard + Pointer-Lock mouse, edge-triggered actions
    │   └── EventBus.js         # decoupled pub/sub
    ├── camera/
    │   └── ThirdPersonCamera.js# orbit camera: mouse look, smoothing, compass bearing
    ├── player/
    │   ├── Player.js           # David rig (primitives), movement, collisions, gait animation
    │   └── Stamina.js          # 0–100 run stamina, forced-walk on exhaustion
    ├── entities/
    │   ├── EgyptianServant.js  # seated NPC + idle animation
    │   └── DirectionalArrow.js # 3D chevron pointing at the active target zone
    ├── world/
    │   ├── World.js            # zone manager: visibility, fog/sky per phase, sun follow
    │   ├── zones/
    │   │   ├── ZiklagZone.js   # PHASE 1 environment + waypoint
    │   │   ├── DesertZone.js   # PHASE 2 terrain (dunes/wadi/brook) + march beacon
    │   │   ├── EgyptianZone.js # PHASE 3 plain + servant + canyon mouth
    │   │   ├── CampZone.js     # PHASE 4 tents/fires/guards + vision-cone detection
    │   │   ├── SpoilsZone.js   # PHASE 5 factions + instanced herd + baggage train
    │   │   ├── GateZone.js     # PHASE 6 city gate, elders, tribute, mule train
    │   │   └── GilboaZone.js   # PHASE 7 ridge, Saul, chariot wreck, philistines
    │   ├── props/
    │   │   └── PropFactory.js  # houses, tents, fires, cage, rocks, beacons, humanoids,
    │   │                       # herds (instanced), wagons, chests, mules, camels, gate
    │   └── effects/
    │       ├── Particles.js    # pooled soft-disc particle emitters (smoke, embers, dust, sparks)
    │       └── SkyDome.js      # two-color gradient sky shader
    ├── ui/
    │   ├── HUD.js              # objective, stamina bar, compass, prompts, alerts
    │   ├── DialogueModal.js    # narrative cards + multi-step dialogue sequences
    │   ├── VictoryScreen.js    # 1 Sam 30:18-19 overlay + Play Again
    │   └── styles.css          # full responsive overlay styling
    └── utils/
        ├── math.js             # angle/damping helpers, seeded RNG, compass labels
        ├── materials.js        # shared material & geometry cache (draw-call friendly)
        └── canvasText.js       # tiny canvas-texture label sprites (< 1024²)
```

---

## Rendering & performance notes

- **Low-poly primitives only** — no external models or texture downloads. The world uses
  flat-shaded Lambert/Phong materials from a **shared material cache** (minimal state changes).
- **Texture budget**: the only textures are runtime canvas labels (~512²), far under the 1024² cap.
- **Draw-call discipline**: rocks use `InstancedMesh` (one draw call per field); particle systems
  are one `THREE.Points` call per emitter with a custom soft-disc shader (per-particle alpha,
  zero textures).
- **Phase culling**: only the active zone is visible. Lights inside hidden groups are skipped by
  the renderer, so inactive phases cost zero GPU time.
- **Shadows**: one PCF-soft shadow-casting sun per zone with a 1024² map and a follow-player
  frustum (no giant static shadow maps).
- **Pixel ratio capped at 1.75** and `dt` clamped to 50 ms for stable 60 FPS on laptops.
- **Point lights** capped at 3 in the camp (bonfires) + 2 in Ziklag.

Target: Chrome, Firefox, Edge, Safari — desktop only (keyboard + mouse required).

---

## Expansion seams

- `StateMachine.add(name, …)` — drop in new phases without touching the loop (used for Phases 5–7).
- `PropFactory` / zone builders return plain groups — swap primitives for glTF meshes later.
- `EventBus` is wired for decoupled gameplay events (audio, analytics) if you grow the build.

## Scripture references

- Ziklag destroyed & the pursuit — 1 Samuel 30:1–6
- The 200 at the Brook Besor — 1 Samuel 30:9–10, 21–25
- The Egyptian servant — 1 Samuel 30:11–15
- The 24-hour battle & full recovery — 1 Samuel 30:17–20
- The spoil divided & the statute — 1 Samuel 30:21–25
- Gifts to the elders of Judah — 1 Samuel 30:26–31
- The fall of Saul on Gilboa — 1 Samuel 31:1–6
