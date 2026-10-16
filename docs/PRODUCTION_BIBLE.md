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
