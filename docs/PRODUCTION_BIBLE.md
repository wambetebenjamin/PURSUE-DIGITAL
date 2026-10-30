# PURSUE — Production Bible
## 1 Samuel 30–31 Master Prompt → Engine Pipeline Translation

This document maps the **Production Master Prompt** (Ziklag in Ruins through the
Fall of Saul on Mount Gilboa) onto PURSUE's low-poly Three.js pipeline. Every
cinematic directive in the master prompt has a concrete home in the code:
lighting color temperatures become hex key/ambient colors, PBR metrics become
Three.js material parameters, and camera compositions become zone staging.

---

## 1. Pipeline overview

| Prompt phase | Scripture | FSM state | Zone | Player beat |
| --- | --- | --- | --- | --- |
| **1. Ziklag in Ruins** | 1 Sam 30:1–6 | `STATE_0_ZIKLAG` | `ZiklagZone.js` | Follow the debris trail to the Desert Edge |
| **2. Pursuit Through the Besor Brook** | 1 Sam 30:9–10 | `STATE_1_DESERT_MARCH` | `DesertZone.js` | Cross the brook past the exhausted 200 |
| **3. Night Ambush at the Amalekite Camp** | 1 Sam 30:16 | `STATE_3_AMALEKITE_RESCUE`* | `CampZone.js` | Infiltrate past vision cones to the captives |
| **4. The 24-Hour War of Attrition** | 1 Sam 30:17–19 | `STATE_3` → battle cards | `CampZone.js` (aftermath staging) | Survive the camp, watch the rout |
| **5. Spoils and Separation at Besor** | 1 Sam 30:21–25 | `STATE_4_SPOILS_DIVISION` | `SpoilsZone.js` | Stand on the outcrop; give the statute |
| **6. Diplomatic Distribution of Spoils** | 1 Sam 30:26–31 | `STATE_5_DISTRIBUTION` | `GateZone.js` | Present the tribute to the elders of Judah |
| **7. The Fall of Saul on Mount Gilboa** | 1 Sam 31:1–6 | `STATE_6_GILBOA_EPILOGUE` | `GilboaZone.js` | Climb the ridge to the dying king |

\* `STATE_2_EGYPTIAN_SERVANT` (1 Sam 30:11–15) sits between the brook and the
camp as the narrative bridge the master prompt's pipeline assumes.

---

## 2. Color-temperature → engine lighting

The prompt's Kelvin values are translated to authored hex colors (Three.js
lights are already linear-ish RGB; a true blackbody curve reads too magenta
at low K and too cyan at high K for the cinematic intent, so these are
*graded* equivalents):

| Prompt spec | Zone | Key light (sun/moon) | Hemisphere ambient | Sky (top → bottom) | Fog |
| --- | --- | --- | --- | --- | --- |
| **5600K golden hour**, low-angle | Ziklag | `0xffd2a0` × 1.05, offset y=18 (raking) | `0x6a5a78 / 0x8a5a38` × 0.9 | `0x4a5578 → 0xd9963e` | `0x3a2a22` @ 0.014 |
| **6500K brutal midday**, overexposed | Besor | `0xf2f7ff` × 1.75, high overhead | `0xbcd4f0 / 0xe0c896` × 1.05 | `0x6aa2e0 → 0xf6ecc9` | `0xe8dcb4` @ 0.0068 |
| **3200K starlight night** + campfires | Camp | moon `0x9db8e8` × 0.55 | `0x2c3a5c / 0x241818` × 0.75 | `0x0b1026 → 0x2c2440` | `0x1c2238` @ 0.012 |
| **5200K direct late afternoon**, backlit dust | Spoils | `0xfff0d8` × 1.55, NW-low (backlight) | `0xbfd4e8 / 0xd9be86` × 1.1 | `0x4d86c8 → 0xf2e0b0` | `0xdbc492` @ 0.0058 |
| **4800K warm clear morning**, long shadows | Gate | `0xffe0b8` × 1.45, low-east | `0xa8c4e0 / 0xd9b98c` × 1.0 | `0x4e86c2 → 0xf0d9a8` | `0xd9c19a` @ 0.005 |
| **7000K oppressive overcast** | Gilboa | `0xc9d4e2` × 0.8 (flat/diffused) | `0x8a97a8 / 0x4a4e54` × 1.05 | `0x39424e → 0x8a94a0` | `0x6b7480` @ 0.016 |

Firelight (camp, Ziklag ruins) stays at `0xff7b2e`-family point lights — the
prompt's dual-source night: **warm 3200K fire vs. cool blue starlight**.

The prompt's "post-processing volume / LUT" note maps to this per-zone
fog + sky + hemisphere triad; swapping zones swaps the grade instantly
(`World.activate()`).

---

## 3. PBR spec → Three.js materials

Hero materials use `MeshStandardMaterial` so the prompt's exact
**Metallic/Roughness** numbers survive the translation; everything else stays
on the cheap Lambert/Phong shared cache (`src/utils/materials.js`).

| Prompt spec | Material key | Parameter mapping |
| --- | --- | --- |
| Blood-spattered steel (Metallic 1.0, Roughness 0.15) | `Mat.steelBloody()` | `metalness: 1.0, roughness: 0.15` — Saul's sword, spear tips |
| Tarnished bronze scales (Metallic 0.8, Roughness 0.4) | `Mat.royalBronze()` | `metalness: 0.8, roughness: 0.4` — Saul's armor |
| Spilled liquids, zero roughness | `Mat.spill()` | `roughness: 0` + `metalness: 0.15`, translucent — wine pools at the camp, blood pools on Gilboa |
| Pillaged polished silver & brass | `Mat.silverLoot()` / `Mat.goldLoot()` | `metalness: 1.0`, `roughness: 0.12 / 0.22` — treasure piles, open chests, crown |
| Blinding white speculars on moving water | `waterMat` clone in `DesertZone` | Phong `shininess: 160`, `specular: 0xffffff`, brown sediment color `0x5a4a34` |
| Dry porous limestone, zero specularity | `Mat.limestone()` | Lambert flat-shaded, no specular — Besor cliffs, Hebron gate |
| Charred splintered wood + glowing embers | `Mat.charWood()` / `Mat.ember()` | dark Lambert + unlit `0xff6a2a` ember patches |
| Coarse wool / linen / leather | `tunicIndigo`, `tunicEarth`, `tentCloth`, `canvasTarp`… | flat-shaded Lambert; "wet clinging cloth" is staged dark (`tunicBattleWorn`) |
| Fleece micro-material | `Mat.fleece()` | pale Lambert on instanced herd bodies |
| Polished flagstones, decades of traffic | `Mat.flagstone()` | flat Lambert `0x9e937f` + lighter chipped slabs |
| Non-reflective ambush gear | `Mat.ironDark()` + dark tunics | high roughness `0.55`, charcoal palette |

Roughness contrast doctrine (from the prompt's shader note) is preserved
between **high-roughness dust/sand/fleece** and **low-roughness sweat, tears,
wet cloth, spilled liquid** — the low-roughness set is exactly the `roughness
0–0.22` standard materials plus high-`shininess` Phong water.

---

## 4. Camera & composition → zone staging

The engine uses a third-person orbit camera, so each prompt "camera angle" is
translated into **level staging and spawn framing**:

| Prompt shot | Staging translation |
| --- | --- |
| Phase 1: medium-wide, smoke column, debris trail | Trail of burnt houses leads spawn → waypoint; the collapsed granary's black column anchors the skyline; gate with sand drifts frames the west |
| Phase 2: low-tracking riverbed shot | The brook cuts the playable corridor; the 200 slump on the **left** sandstone ledge; waders fight the current mid-stream — the camera naturally reads low along the wadi |
| Phase 3: high-angle panorama over the camp | The camp valley rim reads from the high southern spawn; fires, rugs, spilled amphorae, treasure piles and the moonlit dunes behind |
| Phase 4: ground-level tracking push | Aftermath staged in-zone: overturned amphorae, spilled liquid, treasure underfoot; the 400 escape as camel silhouettes + dust at the north edge; the battle card sequence carries the 24-hour clock |
| Phase 5: symmetric wide, dividing line | Outcrop dead-center; the 400 (arms crossed) on the left; the 200 (open, grieving) + baggage train on the right; the instanced herd fills the geometric middle barrier |
| Phase 6: fixed medium-wide through a city gateway | The limestone gate arch frames every approach to the elders; chests, treasure, and mule train occupy the midground; terraced Hebron hills close the background |
| Phase 7: descending crane on a steep slope | Gilboa heightfield descends north: the player walks *down* the ridge to Saul and the broken wheel; the shattered shield-wall mid-slope; Philistine ranks press up from the valley floor |

---

## 5. FX / particle translation

`ParticleEmitter` (one `THREE.Points` draw call per emitter, soft-disc shader,
zero textures) carries every atmospheric effect:

| Prompt FX | Emitter (zone) |
| --- | --- |
| Dense black smoke column from the granary | `smokeColumn` (Ziklag), 9.5 s life, `0x2c2622 → 0x120e0c` |
| Volumetric dust streams, heat shimmer | `dustStreams`, `heatShimmer` (Ziklag, additive golden) |
| Wind-blown sand drifts against the gate | `sandDrift` (Ziklag) + `createGateWithDrifts()` |
| Ember particles with glowing heat values | `embers` (Ziklag, additive `0xff9a3c → 0xff3c14`) |
| Complex foam along the rapids | `foam` (Besor, additive white) |
| Severe dust wind-haze opacity filter | `haze` (Besor) |
| Flickering campfire light + sparks | `createBonfire()` point-light flicker + spark emitters (Camp) |
| Hazy golden veil between factions | `veil` + `herdDust` (Spoils, backlit) |
| Wind-whipped rain, low mountain mist | `drizzle` (negative `rise` = falling) + `mist` (Gilboa) |
| Churned dust of the fleeing camels | `camelDust` (Camp north edge) |

---

## 6. Virtualized geometry note

The prompt's Nanite guidance maps to this project's instancing discipline:
rocks (`createRockField`, `InstancedMesh` — one draw call per field) and the
**entire livestock herd** (`createHerd` — 6 draw calls for 64 animals: body +
head + legs per species) preserve the micro-detail silhouette cost the prompt
asks for. If hero meshes are ever imported as glTF with displacement, keep the
same rule: detail lives in the normal/displacement maps, silhouettes stay
instanced.

---

## 7a. Character construction (CharacterRig.js)

The master prompt's anatomy directives are realized in the merged-geometry rig:
sculpted skull + jaw + brow + cheek masses, nose, ears, eyes with irises,
clumped hair and full/gray beards, tapered limbs with elbow/knee masses,
layered tunics with skirts and belts, diagonal sashes, lamellar armor plates
with brass rivets, and strapped sandals. `detail: 'full'` (heroes) adds eyes,
brows, lips, thumbs, and rivets; `detail: 'low'` keeps crowds cheap. Pivots
(`armL/R`, `legL/R`, `head`, `torso`) preserve every animation hook the zones use.

Surface realism is carried by `utils/textures.js` — mottled sand, grass, dirt,
ash, stone, basalt, woven cloth, wood grain, and flesh pore maps painted on
≤512² canvases at boot.

## 7. Character directives → rig palette & poses

| Prompt directive | Implementation |
| --- | --- |
| Sun-bronzed, weathered Levantine warriors | `Mat.skin()/skinDark()` with soot-stained tunics (`tunicBattleWorn`, `tunicIndigo`) |
| Grief: slumped shoulders, tear-tracks | `'grieving'` and `'seatedCaptive'` poses (200 at the brook, guards at Besor) |
| Aggressive closed body language (the 400) | `'armsCrossed'` pose, left-faction staging at the dividing line |
| Open, unarmed hands (the 200) | `'grieving'` pose, no weapon meshes, right-faction staging |
| Intoxicated, caught off-guard | `'reclining'` pose over `createRug()` at the camp |
| Saul: shallow breathing, heavy crown, trembling grip | `'slumpedWounded'` pose + `createCrown()` + torso breathing loop in `GilboaZone` |
| Non-reflective ambush gear, charcoal-smudged faces | dark Lambert palettes for Israelite infiltration figures |

---

## 7aa. The Vice City grade (core/PostFX.js)

The look is built in three layers tuned to 1986 Miami, not desert realism:

1. **Tone mapping** — Khronos PBR Neutral (`THREE.NeutralToneMapping`),
   chosen over ACES: ACES greys out saturated pinks/cyans, exactly the
   colors the Vice City grade lives on. Neutral keeps them alive.
2. **Sky** — `SkyDome` draws a three-stop gradient (zenith → electric mid
   band → hot pastel horizon) with a fat sun disc, wide halo, sunset band
   streaks, drifting cloud layers, and a bright horizon-haze bleed. Each
   zone carries its Miami moment:
   Ziklag = Ocean Drive sunset (violet/magenta/coral), Desert = Malibu noon
   (cyan/turquoise), Egyptian = Little Havana gold hour, Camp = Starfish
   Island night (indigo/violet/magenta), Spoils = ocean sunset, Gate =
   South Beach afternoon, Gilboa = the Vice storm (bruised purple).
3. **Grade pass** — Miami split-toning (violet shadows `×1.65`, cream-pink
   highlights), vibrance that pushes muted colors harder, saturation up to
   1.78, a 0.30 S-curve, edge chromatic aberration, vignette and fine grain.
   `PHASE_GRADES` maps each FSM state to a preset (`sunset | blaze | gold |
   neonNight | clear | storm`) and grades crossfade at boundaries.

Terrain vertex colors follow the skies: pink-gold sands, sunset mauves,
moonlit violet ground at camp, storm grey-purple at Gilboa. Palm avenues
(density-scaled) flank the travel corridors in the open zones — the Ocean
Drive silhouette against the gradient. Fog hues match the horizon haze.


## 7b. Sound design (audio/AudioEngine.js)

Every sound is synthesized live — no audio files:

| Prompt atmosphere | Synthesis |
| --- | --- |
| Wind-blown sand, dusty haze | brown-noise bed, lowpass 420 Hz, slow LFO |
| Smoldering ruins, campfires | brown-noise bed + sparse high-passed crackle pops |
| Churning brook | bandpassed white noise (950 Hz) |
| Night camp | cricket trills (4.2 kHz pulses) + fire |
| Severe midday glare | dry wind + hollow pad |
| Fields & gate | birdsong motifs + crowd murmur (bandpass noise) |
| Gilboa rain & mist | high-passed rain noise + mournful minor pad |
| 24-hour battle | sine war drums (92→46 Hz) on a 0.8 s pulse |
| Victory / grief | triangle-wave chord swells |

Per-phase tonal pads (detuned triangles through a lowpass) crossfade with the
zone ambience; footsteps sync to the gait; UI clicks, alerts, and interact
blips cover feedback. The ♪ HUD button mutes the master bus.

## 7c. UI themes & cards

Each act's card is themed (`theme-ziklag` … `theme-gilboa`) with its own
accent/glow palette — ember orange, sandstone gold, night-fire, dividing-line
gold, tribute bronze, slate blue. Cards carry an act badge, serif display
type, speaker chips, and scripture citations (`cite` fields in
`constants.js`). Copy is written in a grounded, scriptural register rather
than generic filler.

## 7d. Mobile play & quality profile

The game is playable on phones (pass 5). The desktop WASD/mouse/pointer-lock
path is untouched; touch is a parallel input layer.

**Detection — `src/core/quality.js`**: guarded probes (`navigator.maxTouchPoints`,
`ontouchstart`, screen size) produce `IS_TOUCH`, `IS_MOBILE`, and a frozen
`QUALITY` profile:

| Knob | Desktop | Phone |
|---|---|---|
| pixel ratio cap | 1.75 | 1.3 |
| shadow map | 2048² | 1024² |
| scene density (grass, wheat, reeds, herds, butterflies, birds, particles) | ×1 | ×0.45 |
| canvas MSAA | on | off (post FX smooths edges) |

`qualityCount(n, floor)` scales instance/particle counts in `PropFactory`
field/creature builders and `world/effects/Particles.js`; each zone sun
takes `QUALITY.shadowMap`. Headless Node (smoke) resolves to the desktop
profile.

**Touch input — `src/core/Input.js`**: touches starting in the left 46% of
the screen spawn a dynamic virtual joystick (`moveAxis`, −1..1, up =
forward) with knob visuals rendered by `ui/TouchControls.js`; touches
elsewhere drag the camera look (deltas feed the same `consumeMouseDelta()`
pipeline, gated by `input.lookActive` = pointer-locked OR touch-dragging).
The on-screen **Use** and **Run** buttons synthesize `KeyE` / `ShiftLeft`
into the key sets, so interact, dialogue advance, sprint and stamina logic
are shared unchanged with keyboard. `Player.js` reads `moveAxis` for analog
speed and falls back to WASD. Pointer-lock requests no-op in touch mode.

**Responsive layout** (`index.html`, `styles.css`): `viewport-fit=cover` +
`user-scalable=no`, `touch-action: none`, safe-area insets on HUD and cards,
large touch targets, the start-screen controls legend swaps to touch
wording, prompts read “Tap Use”, and portrait mode widens the camera FOV
(74°) with a landscape-only rotate hint.

## 7e. The Vice City interface layer (radar & HUD)

Interface *design patterns* were studied from the reversed GTA III / Vice City
engine source (re3/reVC — `github.com/daynz/GTAviceCity`, cloned locally for
reference). Nothing is copied: no game assets, text, fonts, or code — every
stroke is procedural canvas art and every layout decision is re-implemented to
fit PURSUE's march through 1 Samuel 30.

| GTA convention (source) | PURSUE implementation |
|---|---|
| Circular radardisc, bottom-left (`HUD_RADARDISC`, `CRadar`) | `ui/Radar.js` — heading-up disc, land plates, neon rim |
| Speed-scaled radar zoom (`m_radarRange` 120→350) | range 26→70 world units, lerped by run speed |
| Coord blips + edge clamp (`BLIP_COORD`, trace colours) | pulsing magenta mission blip, chevron when off-range |
| North marker (`RADAR_SPRITE_NORTH`) | "N" tick riding the rim |
| Player triangle fixed at centre | white triangle, map rotates beneath it |
| Zone-name card (`CHud::SetZoneName`) | `#zone-banner` district banners ("Ziklag — a town in the Negev") |
| Mission timer | `#hud-timer` mm:ss run clock |
| Wanted stars | `#pursuit-stars` — the Amalekite pursuit (spikes on detection, decays) |
| Frontend MAPS page (`Frontend.cpp`) | pause screen + "Map of Judah" chart (`ui/PauseMenu.js`) |
| Follow-ped speed FOV | sprint FOV kick + distance push in `ThirdPersonCamera` |
| `MISSION PASSED` big message (`SetBigMessage`) | `#mission-passed` banner on the victory card |

Controls: **Esc** pauses (GTA pause key), **M** opens the map directly; on
touch a **Map** button synthesizes `KeyM` like the other touch buttons.

## 8. Phase-flow state machine

```
STATE_0_ZIKLAG ─waypoint→ STATE_1_DESERT_MARCH ─brook exit→ STATE_2_EGYPTIAN_SERVANT
      ─served + exit→ STATE_3_AMALEKITE_RESCUE ─reach cage→ [battle cards 30:17–19]
      ─→ STATE_4_SPOILS_DIVISION ─statute cards 30:21–25→ STATE_5_DISTRIBUTION
      ─gift cards 30:26–31→ STATE_6_GILBOA_EPILOGUE ─Saul cards 31:1–6→ VICTORY
```

All narrative copy lives in `src/constants.js` (`NARRATIVE`); all objectives in
`OBJECTIVES`. Adding an eighth phase means: one zone file, one `fsm.add` block,
one row in `World`, and new copy — the loop itself never changes.

---

*Built for the PURSUE-DIGITAL pipeline (Three.js + Vite). The master prompt's
Unreal-specific notes (Nanite, post-process LUT volumes) are honored in spirit:
virtualized micro-detail → instancing; the 5600K/6500K/3200K grading → per-zone
lighting triads; roughness/SSS doctrine → the material table in §3.*

## 9. The cinematic overhaul (Pass 8)

The brief for this pass was a full replacement — characters, world, light,
camera and interface — moving from "stylised low-poly" to "ultra-realistic,
immersive cinematic". Everything below is real-time, procedural, and runs in
the same static Vite build (no downloaded art, no texture atlases, no
Unreal-only features). The translation of each Unreal concept is given in
brackets.

### 9a. Characters — anatomy, skin, hair, gear

* **Anatomy.** `entities/CharacterRig.js` keeps the ~8-draw-call merged rig but
  the head is now sculpted from asymmetric masses: split brow ridge with a
  permanent squint, one cheekbone set higher, a nose broken off-centre with
  separate alar wings and nostril shadows, a hard mouth line, sun creases at
  the eye corners, a scar through one brow, square wind-burned jaw corners and
  forehead furrows. A per-figure `seed` drives the asymmetry, so no two named
  characters read as the same face.
* **Skin.** `utils/pbr.js → skinMaterial()` is a `MeshPhysicalMaterial` with
  pore/wrinkle bump (`surfaces.poresMap`), a sun-baked mottled albedo with
  wind-burn blotches and settled dust, a thin sweat clearcoat, and an injected
  **subsurface term** (wrapped thin-part transmission + Fresnel rim in
  `uSSSColor`, plus a warm dust lift on upward-facing skin). [Screen-space SSS
  → analytic wrap/rim approximation.]
* **Hair & beards.** Real **hair cards**: layered quads wearing a strand alpha
  map (`strandAlphaMap`) with anisotropic shading, sun-bleached tips, grey
  flecks and dust in the sheen. Ten to thirteen cards crown each skull, seven
  build the nape, eight more fall for long hair, and nineteen beard/moustache
  cards hang from the jaw. Cards are merged per material, so hair still costs
  one extra draw call per figure. [Strand-based groom → alpha-tested cards.]
* **Gear.** Lamellar rows gained a bandolier with a bronze buckle, hip pouch and
  amulet; bronze scale armor gained battle dents and a sweat-darkened strap;
  every armored arm has laced bracers with bronze studs; sandals have a second
  cracked strap and a toe thong. Materials are physically based: cracked leather
  bump, hammered/scuffed metal roughness maps, woven linen sheen, high
  environment reflections on bronze and iron.

### 9b. World — Ziklag, the wilderness, the camp

* **Ziklag.** Every burnt shell now sits on a hand-hewn limestone plinth of
  individual dressed blocks, with fallen blocks, mud-brick chunks, four charred
  roof beams sagging through the roof, soot-scarred wall tops, soot decals and
  cracked-earth scars on the ground. Six collapsed-timber piles still glow at
  the joints; five pottery clusters and an abandoned weapon rack dress the
  ruins. Smoke is now a billowing **sprite volume** (soft smoke texture on the
  particle shader) at nearly twice the size, plus a dense granary column and
  falling ash.
* **Besor brook.** Turquoise water with a procedural **ripple height field**
  sampled as a bump map and scrolled on two axes every frame, wet glossy flints
  half-sunk in the shallows, wet stones along the banks, acacia groves, dry
  scrub, instanced gravel/flint micro-debris across the path and sun-cracked
  earth patches. [Photogrammetry/8K → seeded procedural maps at 256–512²,
  instanced micro-debris.]
* **Raider camp.** Tents gained patched, sun-bleached canvas panels, a stitched
  ridge seam and a dust-darkened hem; weapon racks, pottery clusters, scorched
  ground and glowing ash beds under every fire. The first campfire casts real
  point-light shadows; the rest flicker. [Dynamic campfire GI → one shadowed
  hero light + baked/faked falloff.]
* **Dust & heat.** Dust streams, ground-hugging haze and heat shimmer are
  billboard volumes; the post pass adds a refractive shimmer band and a warm
  particulate veil.

### 9c. Light, atmosphere & camera

* **Desert GI.** Strong hemisphere bounce (pale sand as the ground colour)
  throws warm light into shadowed alcoves, an IBL environment at 0.52 intensity
  gives metal and skin something to reflect, and the low sun still rakes long
  shadows across the ruins. [Lumen → hemisphere + IBL + per-zone light triads.]
* **God rays.** Screen-space radial scattering in the grade shader, aimed at the
  projected sun each frame and occluded by a bright-pass, so the smoke columns
  carve visible shafts.
* **Heat haze.** A lower-frame refraction wobble (two frequencies) that rises
  with the noon grade.
* **Motion blur.** A 5-tap smear along the camera's *measured* screen-space
  flow (point 12 m ahead projected through this and last frame's view-
  projection), so sprinting and whip-pans drag like a real lens.
* **Depth of field.** `BokehPass` (desktop) with focus tracking the player
  distance and aperture widening in clear air. [Physical camera DOF → bokeh
  depth pass.]
* **Handheld camera.** Layered breathing sway plus a slow weight shift,
  amplified while sprinting, and a decaying trauma shake kicked by detection.
  Camera roll is included, so it reads as an operator, not a rig.

### 9d. Diegetic interface — no modern UI remains

| Was | Now |
|---|---|
| Neon bar HUD | Torn **vellum** charge strip with a wax seal; the objective is written in ink |
| Green stamina bar | **Stone relief channel** holding deep wine; the wine surges when exhausted |
| (new) vitality | **Clay oil lamp** on a bronze rim: the flame shrinks, dims and gutters with health |
| Digital compass text | **Hammered-bronze astrolabe** with a swinging wax-red needle and etched ticks |
| Neon radar disc | **Sheepskin scroll chart** with bronze rollers, torn deckle edges, ink plates, charcoal crosses, a wax seal and an inked player arrow |
| Mission clock | **Clay tablet** with incised numerals |
| Wanted stars | Five **branded wax seals** (aleph brand) that light as the pursuit rises |
| Glass pause menu | **Vellum codex** with gilded rules, page marks, bronze-clasp corners and bronze-leaf buttons |
| — | **The Satchel** page: procedurally shaded still-life icons (oil lamp, iron dagger, worn tunic, water skin, sling & stones, the scroll) |
| Purple title card | Vellum title leaf over a **desert dawn**, date-palm silhouettes, gilded rule |

Typography is **Frank Ruhl Libre** (a Hebrew-rooted serif with a full Latin
alphabet, OFL) for display, numerals and seals, over **EB Garamond** for body
copy. No tracked all-caps template labels; the only capitals are in scripture
citations.

### 9e. Performance & verification

The whole pass keeps the shared-material and merged-geometry discipline: hair
cards add one draw call per named figure; smoke sprites use one draw call per
emitter; micro-debris is instanced; decals share two materials. DOF is disabled
on phones, shadows drop to 1024², particle density to 45%. Headless smoke passes
**57/57** and the production build is clean.

## 10. Retrievals from the two reference sources (Pass 9)

Two references were mined for this pass, both treated as **design sources, not
asset sources**: we take structure and technique, and we re-implement
everything in our own procedural pipeline.

### 10a. `conradosaud/ThirdPersonPack` (Unity, MIT-style sample pack)

The pack is a beginner-friendly third-person controller for Unity: two C#
scripts (`ThirdPersonController.cs`, `CameraController.cs`), an animator
controller with `run` / `sprint` / `crouch` / `air` parameters, locomotion
clips, and a demo scene. The C# and the model (a CGTrader "Futurist Soldier"
under its own licence) cannot be dropped into a Three.js build, so what was
retrieved is the **controller grammar**:

| In the pack | What we took | Where it lives now |
|---|---|---|
| `inputCrouch` toggle; −50 % velocity; crouch animator bool | Crouch/sneak: `KeyC` / `Ctrl`, half speed, knee bend + hip drop + torso lean, and stealth value | `constants.CONTROLS.crouch`, `PLAYER.crouchSpeed`, `Player.js`, `Locomotion.js` |
| `animator.SetBool("run"/"sprint"/"air")` + `cc.velocity.magnitude` | A real locomotion **state machine** with published parameters, blending idle → walk → run → sprint → crouch-idle → crouch-walk | `src/player/Locomotion.js` (new) |
| `sprintAdittion` added to base velocity | Sprint as an additive on the walk speed, gated by stamina | `Player.js` |
| `canZoom` + `sensitivity` + `cameraLimit` | Wheel zoom clamped to 3.5 – 15 units, plus the existing pitch limits and look sensitivity | `Input.consumeWheel()`, `ThirdPersonCamera` |
| Camera rig as a separate object that follows the player | Camera obstruction probe: march from the look target toward the ideal position and stop at the last clear sample, so rubble never swallows the shot | `ThirdPersonCamera._clearDistance()` |
| `HeadHittingDetect()` raycast | Not adopted — the march has no jump, and Space is the interact key | — |
| `PlayerAnimator.controller` / `.anim` clips / `.fbx` | Not adopted — clip data is bound to a Unity rig and the model has its own licence | — |

Crouch also became a **gameplay** verb rather than a cosmetic one: inside the
Amalekite camp a crouched man is detected at 52 % of the guard's range and only
when he is squarely inside the cone (`VISION.crouchRange`,
`VISION.crouchPeripheral` in `constants.js`), which makes the night camp an
actual stealth approach instead of a sprint down the cone.

### 10b. `freegameassets.com/fantasy-rpg-icons` (2044-icon catalogue)

The catalogue is a **taxonomy + visual grammar** reference: armour split into
head / chest / arms / belt / feet / cloak / weapon / off-hand, plus provisions,
spoil and class items, each rendered as a centred, rim-lit object with its
rarity carried by the frame rather than the art. We cannot ship those PNGs —
the sandbox has no access to the asset CDN, and the pack's licence is the
publisher's, not ours — so the *system* was rebuilt on our own procedural art:

| From the catalogue | What we built |
|---|---|
| Slot taxonomy | `ui/items.js` — `SLOTS` (head, chest, arms, belt, feet, cloak, hand, off-hand) with one item each |
| Category grouping | Four sections in the satchel: *Upon him*, *In the satchel*, *Spoil recovered*, *Relics of the pursuit* |
| Rarity tiers | `RARITY` = worn / fine / royal, expressed as ink, bronze-ringed and gilded-plus-wax-seal frames in CSS |
| Consistent art direction | `ui/icons.js` — 18 procedural painters sharing one grammar: single centred object, three-quarter view, warm key light upper-left, cool bounce lower-right, contact shadow, ink outline readable at 56 px |
| Item metadata | Every item carries a name, a one-line account and the verse it comes from (1 Samuel 16–30) |
| Item detail view | `#satchel-detail` — name, rarity, description, citation, and a four-cell tally (600 / 200 / 400 / 0) |

**Drop-in path for a licensed pack.** Every item in `ui/items.js` may carry a
`file` property. When it is set, `ui/Inventory.js` renders an `<img>` and skips
the painter entirely, so a purchased or CC0 icon pack can be adopted without
touching any layout code:

```js
// ui/items.js
{ slot: 'chest', icon: 'lamellar', file: './assets/icons/armor/lamellar.png', … }
```

Our icons stay in place as the fallback, so a partially-supplied pack degrades
gracefully instead of leaving holes.
