# Reference teardown — Land of Empires: Immortal 0.1.136

**Status of the artifact:** the `.apks` you linked
(`LandofEmpires-0.1.136.apks`, 427 MB) could not be downloaded into this
sandbox. GitHub serves release assets from `objects.githubusercontent.com` /
`release-assets.githubusercontent.com`, and this environment's egress allowlist
only admits `github.com`, `codeload.github.com`, `api.github.com`,
`registry.npmjs.org`, `pypi.org` and `files.pythonhosted.org`. Both asset hosts
were tried directly and through `gh release download`; both fail the TLS
handshake at the proxy (`EOF`), so there is no partial file to inspect.

What follows is therefore a **teardown of the game's systems from its public
documentation, store listing, patch notes and beginner guides** — not a byte-level
inspection of the APK. Two consequences, both stated plainly:

1. Any claim below about *how it plays* is sourced, not extracted.
2. **Nothing from the package is reused.** No art, no UI atlas, no audio, no
   code, no data tables. What we take is the *shape* of the loop, rebuilt on
   1 Samuel 30 with our own procedural content. That is also why the rebuild
   could proceed at all: the parts worth copying are not the files.

If you want a true binary teardown, the file has to reach the workspace another
way — attach it to the chat directly, or commit it to the repo (Git LFS or a
release upload from your machine does not help; it is the same blocked CDN).

---

## 1. What the reference is

A city-building strategy RPG published by Nuverse (v0.1.136 era, still in
season-driven live service). Loop, in the order the store listing and guides
describe it:

- rebuild a ruined capital; quarries, farms, mills and barracks feed the war
- recruit historical and mythic heroes (Cleopatra, Caesar, Achilles, Merlin…);
  heroes carry classes and skills
- train troops in four arms — infantry, archers, cavalry, siege/auxiliary
- fight in three stage flavours: **Stealth**, **Dungeon** (three heroes, no
  troops, skills on a gauge), **Field** (heroes + troops, formation, largely
  automatic)
- explore a fogged overworld; rescue refugees, hit demon lairs, take resources
- a **chapter task chain** is the spine: it is what unlocks everything else
- alliances, rallies, seasonal resets, VIP, gacha summoning, paid packs

## 2. The seven patterns worth porting

| # | Pattern in the reference | Why it works | What PURSUE built |
|---|---|---|---|
| 1 | Chapter task chain as the spine | one always-visible next step, rewarded on completion | `TASKS` in `src/core/progression.js` — seven charges, one per phase, each with a verse and a reward; the HUD tracker shows the current one and `n of 7` |
| 2 | Stage star ratings (swift / clean / no losses) | turns a linear level into a replayable score | a phase is a stage: **swift** (≤150 s), **silent** (no detection) and **unseen** (12 s+ crouched) each carry a star — `progression.recordPhase()` |
| 3 | Persistent city that outlives a run | gives the numbers somewhere to go | Ziklag rebuilt: four buildings, five levels each, each level a real modifier (vitality, breath, strength, renown) applied at `Game.start()` |
| 4 | Hero roster with classes and power | collection + a readable number | six of David's mighty men (2 Sam 23), three roles — blade / spear / bow — drill levels, unlock gated on campaign stars |
| 5 | Troop tiers behind a barracks | long-tail progression without new content | the barracks tier scales **band power** and blow strength; the 600 of 1 Sam 30:9 are the fiction |
| 6 | Resource economy that funds the city | every fight pays something forward | provisions, bronze, timber, spoil — phases pay out by star rating, buildings and drills spend them |
| 7 | A "governor's" screen, never a settings screen | the meta layer is a place, not a menu | **the War Table**: four vellum leaves (the Charge, the Band, Ziklag, the Campaign), reachable from every pause page via the new codex nav |

## 3. What we deliberately did **not** port

| Reference feature | Why not |
|---|---|
| Gacha hero summoning | It is a monetisation loop, not a design. Heroes are earned by walking the campaign. |
| VIP levels, packs, timers for sale | Same. Nothing in PURSUE is purchasable and nothing gates on time. |
| Alliance / PvP / rallies | Needs a server and a population. The band is the 600, and they are yours. |
| Seasonal resets of power | The story is one pursuit; the campaign is what persists. |
| Auto-battle with a skip button | Our combat is the third-person controller. The reference's "field stage" automation would delete the game we already built. |
| Its art, icons, fonts, audio, data tables | Not ours. Everything here is procedural, as it has been since pass 1. |

## 4. Where the reference pushed us to be better

- **Reachability.** Its screen flow never strands you: every panel has a way
  back and a way onward. Ours had a hole — on a phone, the pause overlay opened
  onto the Chart and the only exit was "back to the march", so the satchel, the
  settings and the new War Table were unreachable without a keyboard. Pass 13
  adds the **codex nav**: five leaves, one tap each, on every page.
- **A single next step.** The HUD now carries the charge, not just the phase
  objective. Two different "what do I do now" lines were a real source of
  confusion.
- **A number that grows.** Before this pass, a finished run left nothing behind
  but a memory. Now stars, stores and the rebuilt town persist across runs, so
  the second playthrough starts different from the first.

## 5. Implementation map

```
src/core/progression.js   the campaign record: resources, buildings, roster,
                          stars, charges; persisted at pursue.progress.v1
src/ui/WarTable.js        four-leaf vellum page, built from the record
src/ui/HUD.js             setTask() — the charge chip
src/ui/PauseMenu.js       the codex nav + the 'war' page
src/core/Game.js          phase scoring (_closePhase), modifiers, task chain
src/player/Stamina.js     regenScale (the cistern)
src/player/Player.js      maxHealth (the wall)
scripts/smoke.mjs         203 checks, incl. 32 for the campaign
```
