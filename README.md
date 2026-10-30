# PURSUE — 1 Samuel 30

A cinematic **3D action-adventure web game** for desktop browsers, retelling the
pursuit of the Amalekites from **1 Samuel 30–31**. Built with **Three.js + Vite** (pure WebGL,
no game engine runtime): detailed procedural characters with sculpted anatomy, painted
canvas surface textures, wind-swept fields, fully synthesized audio, and themed narrative
cards — all generated at runtime, zero asset downloads. Deployable to **Vercel** as a static site.

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

Headless logic test (walks the entire seven-phase state machine in Node — rigs,
textures, and cards included):

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
| **Shift** | Run — drains the stamina bar (15 units/sec) |
| **C / Ctrl** | Sneak low — half speed, half silhouette, guards see you at half range |
| **Mouse wheel** | Camera distance (3.5 – 15 units) |
| **E / Space** | Interact, pick up, advance dialogue |
| **Esc** | Pause / release the mouse pointer |
| **M** | Map of Judah (full chart of the march) |

### The Vice City look

> *Superseded in Pass 8 by the cinematic grade (§The cinematic overhaul), which
> keeps the same palette logic — saturated skies, split-toned shadows, bloom —
> but aims it at a photoreal desert rather than neon Miami. The history below is
> kept because the grade architecture is unchanged.*

The Pass 7 graphics were graded to 1986 Miami rather than desert realism:

- **Neutral tone mapping** (Khronos PBR Neutral) so saturated pinks and cyans survive to the grade
- **Three-stop skies** — saturated zenith, electric mid band, hot pastel horizon haze, fat sun disc with wide halo
- Each phase carries its Miami moment: Ocean Drive sunset, Malibu noon, Little Havana gold hour, Starfish Island night, South Beach afternoon, the Vice storm
- **Miami grade** — violet shadows, cream-pink highlights, vibrance + saturation punch, S-curve, chromatic edges, bloom
- **Palm avenues** flank the travel corridors; the brook runs Vice City turquoise

## The Vice City interface

> *Pass 8 replaced this layer with the diegetic interface described under “The
> cinematic overhaul”: the radar is now a sheepskin scroll, the stars are wax
> seals, and the pause screen is a vellum codex. The interaction contracts
> (radar zoom, district banners, timer, pursuit) are unchanged.*

The game plays with the interface of a classic open-world epic, rebuilt in our
own procedural style after studying the reversed GTA III / Vice City engine
([daynz/GTAviceCity](https://github.com/daynz/GTAviceCity)) — design patterns
only, no copyrighted assets:

- A circular **radar** bottom-left: rotating map, mission blip, north tick,
  speed-scaled zoom (walk close, sprint wide)
- **District banners** as you enter each phase ("Ziklag — a town in the Negev")
- A **mission timer** and **pursuit stars** — the raiders of Amalek flare when
  they spot you
- **Esc** pauses into the march menu; **M** charts the **Map of Judah** with
  all seven districts and your objective
- A "**MISSION PASSED**" flourish when the story completes

## The cinematic overhaul

Pass 8 replaced the stylised look end to end: **anatomical, asymmetric
characters** with subsurface-skinned faces, strand-card hair and beards and
physically based gear; **lit environments** with limestone plinths, soot and
ember beds, collapsing timbers, acacia groves, gravel micro-debris, wet stones
and a flowing ripple-shaded brook; **atmosphere** with volumetric smoke, dust,
god rays, heat haze, camera-motion blur, depth of field and handheld camera;
and a **fully diegetic interface** — vellum charge strip, clay oil-lamp
vitality, stone-and-wine breath gauge, bronze astrolabe, sheepskin scroll map,
clay tablet clock, wax pursuit seals, a vellum codex pause screen and a
satchel of procedurally rendered goods.

The whole thing is still procedural and real-time: no image assets, no
downloaded models, one static build. See `docs/PRODUCTION_BIBLE.md` §9 for the
full material, geometry and shader notes.

## The third-person grammar

Two external references shaped pass 9, both used as design sources rather than
asset sources:

- **[conradosaud/ThirdPersonPack](https://github.com/conradosaud/ThirdPersonPack)**
  (a Unity third-person sample) gave us the controller *grammar*: crouch as a
  toggle with halved velocity, an animator that resolves `run / sprint / crouch
  / air` parameters into blended locomotion, additive sprint, wheel zoom
  clamped to the rig's limits, and a camera that probes for obstruction. Our
  port lives in `src/player/Locomotion.js` (a state machine driven by published
  parameters), `ThirdPersonCamera._clearDistance()` and `Player.js`. The C#
  scripts, `.anim` clips and the pack's licensed character model are **not**
  used — the rig would not survive the port and the model is not ours.
- **[Fantasy RPG Icons](https://www.freegameassets.com/fantasy-rpg-icons)**
  (2044 icons) gave us the inventory *system*: an eight-slot equipment
  taxonomy, category grouping, rarity carried by the frame, and one shared art
  grammar. All 18 satchel icons are painted procedurally in `src/ui/icons.js`;
  no PNG from that pack is redistributed. Each item accepts a `file` property,
  so a licensed pack can be dropped in later without layout changes.

Crouch also changed the stealth design: in the Amalekite camp a crouched player
is spotted at 52 % of the guard's range and only inside the cone.

## Playing on a phone

The game detects touch devices and adapts automatically:

- **Left thumb** — a dynamic virtual joystick appears where you touch to move (analog speed)
- **Right thumb** — drag anywhere else to look around
- **Run / Use / Map** — on-screen buttons; *Use* talks to people and advances dialogue
- Portrait play widens the camera field of view and shows a gentle "turn your phone" hint
- The desktop prompt "Press E" reads "Tap Use"
- A lighter performance profile kicks in (pixel ratio capped at 1.3, 1024² shadows, 45% grass/particle/creature density, no MSAA) so mid-range phones stay smooth
- Safe-area insets keep the HUD clear of notches; the layout folds down to small screens and short landscape

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
plus interaction prompts, alert banners, a sound toggle, and themed narrative cards with
speaker chips and scripture citations.

---

## Characters, sound & themes

- **Character rigs** (`src/entities/CharacterRig.js`) — 7.5-head anatomically-proportioned
  figures with smooth-shaded sculpted skull/jaw/brow/cheek masses, eyes, noses, layered hair
  and beards, tunics, belts, sashes, lamellar armor plates with rivets, and strapped sandals;
  working poses (hand-mill grinding, jar-carrying) bring the villages to life. Geometry is merged
  per material inside each animated pivot (~8 draw calls per figure). David wears leather
  lamellar and carries a sheathed sword; Saul wears dented royal bronze under a crown.
- **Surface textures** (`src/utils/textures.js`) — every ground, wall, cloth, wood and skin
  material is painted at runtime on ≤512² canvases (mottled sand, grass blades, weave
  crosshatch, wood grain, stone pocks, flesh pores).
- **Living landscapes** — wind-swept instanced grass seas (10k+ blades per vista), wheat
  with grain heads, wildflowers, reed beds, olive groves, layered mountain ridgelines that
  fog into the sky, drifting cloud banks, circling bird flocks, butterflies over the meadows,
  and grazing sheep that lift their heads. Ground meshes carry vertex-color terrain
  variation (dry ridges, green hollows, damp patches) beneath painted surface textures.
- **Real type** — EB Garamond and Inter (OFL, embedded at build time): literary serif for
  scripture and titles, humanist sans for the interface. Natural sentence-case labels —
  no letter-spaced template styling.
- **Procedural audio** (`src/audio/AudioEngine.js`) — the Web Audio API synthesizes every
  sound: layered ambience (wind, fire crackle, brook water, crickets, birdsong, rain),
  tonal pads per phase, war drums under the battle cards, and SFX (stride-synced footsteps,
  UI, alerts, victory chords). Mute with the ♪ button; nothing is downloaded.
- **Themed cards** — each act's dialogue card carries its own palette (ember, sandstone,
  night-fire, gold, marble, slate), an act badge, speaker chips, and scripture citations.
- **Vice City grade** (`src/core/PostFX.js`) — a retro-Miami post chain: Unreal bloom,
  split-toned shadows/highlights, saturation punch, edge chromatic aberration, vignette
  and film grain, crossfading between per-phase moods (sunset · blaze · gold · neon-night ·
  storm). Skies carry a hard sun disc with streaky sunset bands; the title screen is pure
  80s — sun stripes, palm silhouettes, and a chrome-gradient logo under a pink/cyan HUD.

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
    │   ├── PostFX.js           # Vice City post chain: bloom + grade presets
    │   ├── Game.js             # orchestrator: FSM, phase logic, per-frame update order
    │   ├── StateMachine.js     # generic FSM (enter/update/exit)
    │   ├── Input.js            # keyboard + Pointer-Lock mouse, edge-triggered actions
    │   └── EventBus.js         # decoupled pub/sub
    ├── audio/
    │   └── AudioEngine.js      # procedural Web Audio: ambience, pads, footsteps, SFX
    ├── camera/
    │   └── ThirdPersonCamera.js# orbit camera: mouse look, smoothing, compass bearing
    ├── player/
    │   ├── Player.js           # David rig (primitives), movement, collisions, gait animation
    │   └── Stamina.js          # 0–100 run stamina, forced-walk on exhaustion
    ├── entities/
    │   ├── CharacterRig.js     # detailed merged-geometry humanoid builder
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
        ├── textures.js         # procedural canvas surfaces (skin, sand, grass, cloth…)
        └── canvasText.js       # tiny canvas-texture label sprites (< 1024²)
```

---

## Rendering & performance notes

- **Procedural assets only** — no external models, textures, or audio downloads. The world
  uses Lambert/Phong/Standard materials from a **shared material cache** (minimal state changes).
- **Texture budget**: all textures are runtime-painted canvases (≤512²), far under the 1024² cap.
- **Character budget**: figures merge to ~8 draw calls each (per-pivot, per-material);
  fields, herds, and rocks are instanced (one draw call per system).
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
