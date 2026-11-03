# Getting the reference art — and the correction to the plan

## The short version

**You do not need to upload anything to Google Drive.** The bundles are in
this repository, and they decode here, in the sandbox, right now.

The earlier plan (unzip the `.apks` on your PC, upload the folder to Drive,
paste the link) was written when the `.apks` was unreachable from here. Now
that `decompiled-source/` is committed, the art is a `git pull` away.

```bash
pip install UnityPy Pillow
python3 tools/extract-reference-art.py --out ref-art
```

That writes every texture and sprite as a PNG, plus `palette.json` (dominant
colours per texture) and `manifest.json` (the full inventory).

## What is actually in the repository

`decompiled-source/resources/assets/AssetBundle/` — **44 bundles, 21 MB**:

| Path | Contents |
| --- | --- |
| `art_assets/effects/share/texture/default*` | 15 shared texture atlases, 18–57 textures each — the bulk of the VFX art |
| `art_assets/effects/share/texture/sprite*` | 11 sprite sheets of effect pieces (haloes, trails, masks, smoke, ice, magic, light) |
| `art_assets/effects/prefab/boss/*` | the demon knight and the necromancer trap, with skeletons and meshes |
| `art_assets/effects/prefab/skills/*` | hero skill effects: bloodscream, imhotep, theseaking |
| `art_assets/characters/uihero/hero_model_ayu` | the Ayu hero model — 12 textures, meshes, an avatar |
| `art_assets/characters/battlehero/l_bloodscream` | the bloodscream battle hero |
| `art_assets/cutropegame/levels/4xxx` | a cutscene level |
| `ui/images/amy_icon/amy_common_icon` | **one 2048² atlas, 68 sprites** — the general UI icon set |
| `ui/images/amy_icon/hero_icon2` | 14 hero portraits |
| `ui/images/amy_icon/n_skillicon_3` | 37 skill and attribute icons |
| `ui/images/hugeimages/*` | two full-screen backgrounds |
| `lua/game/*`, `lua/gameui/*` | the three Lua bundles (see `docs/reference/02`) |

Totals: **741 textures, 344 sprites, 6,274 GameObjects, 1,117 mesh
renderers, 1,028 particle systems** across the set.

## The extracted UI atlas, and what it tells us

`amy_common_icon` is a single 2048×2048 ASTC atlas with 68 named sprites.
The naming is the map:

| Sprite | What it is |
| --- | --- |
| `N_Arena_icon_1..7` | the arena / PvE stage screen |
| `N_mail_icon_1..8` | the mail / inbox screen |
| `Notice_tabltem_icon_1..8` | notice tabs |
| `HeroicFetters_icon_strong / _wise / _agile` | **the hero bond system** — strong, wise, agile |
| `TroopTrain_10` | the troop-training banner |
| `Top_up_treasurechest_2.5 .. 5.5` | purchase tiers |
| `Daily_gift_caidai1..4` | daily login rewards |
| `Pin_1..20` | map pins — the world map |
| `kamo_12..19_1` | the "kamo" screen |
| `UIUsherMainView_8..14` | the usher / guide screen |

`n_skillicon_3` names its attributes outright:
`icon_attribute_attackspeed`, `_crit`, `_travelspeed`, `_def_11`, `_def_12`,
`_dayeup` — so **the reference's hero attributes are attack speed, crit,
travel speed, defence (two tiers), and daily upkeep**. Plus five elemental
icons: `icon_hero_fire`, `_water`, `_wind`, `_wood`, `_thunder`.

That is a direct, verified answer to the "what are the hero stats?" question
that the store listing could only hint at.

## The palette

Extracted from the reference's own art (`palette.json`), and this is the
single most useful thing in the folder:

| Source | Dominant colours |
| --- | --- |
| `UIPackShowRUTipsView_big_bg` | `#29181f` `#412225` `#251116` `#502b26` `#dcb490` `#b48357` `#9e6245` `#6a4d39` `#795e46` `#ffffde` |
| `AMY_Common_icon` | `#e8b15c` `#f7eab4` `#ad724a` `#634447` `#af4f41` `#752736` `#5e6464` |
| `hero_icon2` | `#595758` `#a76444` `#423032` `#7b3327` `#a78b6c` `#27242c` `#1e181f` |
| `N_SkillIcon_3` | `#773729` `#31271d` `#ae6f44` `#eabf75` `#a3c1a0` `#131b22` |

**The signature colours: a warm gold-bronze (`#e8b15c`, `#dcb490`, `#b48357`)
against a deep oxblood-plum ground (`#29181f`, `#412225`, `#251116`), with
earth-brown mid-tones (`#6a4d39`, `#795e46`, `#ad724a`) and one bright
parchment highlight (`#ffffde`).**

That is a **dark, warm, gilded** palette — not the cold blue-and-grey of a
generic strategy game. It is closer to an illuminated manuscript or a
Byzantine enamel than to a sci-fi HUD.

## What this changes about PURSUE

PURSUE's art direction is sun-bleached, dry and scriptural, which is *not* the
reference's gilded dark. That is deliberate — the theme demands it. But three
things from the reference are worth taking outright:

1. **Gold-on-dark is legible.** A warm gold accent on a deep ground reads at
   phone size and survives a cheap panel. Our vellum-and-ink UI already does
   this, but it should be *warm* gold, not grey ink.
2. **One atlas, many sprites.** The reference packs 68 UI pieces into one
   2048² sheet. So should we — one `ui_atlas.png` for our icons, one
   `hero_portraits.png`, one `fx_atlas.png`.
3. **Elemental vocabulary.** Fire/water/wind/wood/thunder maps cleanly onto a
   biblical palette too — fire, water, wind, wood, and *stone* instead of
   thunder.

## If you still want more

The repo holds chapter-one content only. The rest of the game's art arrives
by hot update into the app's own cache. To get it, run the reference on an
emulator or a rooted phone, let it download, then pull:

```
Android/data/com.oasis.immortal/files/…/AssetBundle/
```

and point `tools/extract-reference-art.py --root` at that directory. The same
script handles it.

## Licence position, once

Uploading a folder to your own Drive is fine, and so is reading art out of a
package you hold the rights to. What we build stays ours: we match a look,
we do not ship their binary. You have said you hold the rights and that this
build is for you alone — noted, and not raised again.
