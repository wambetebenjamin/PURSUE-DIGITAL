# Getting the reference art out of the bundle, and into this repo

The game's art is in Unity asset bundles. The **code** is IL2CPP (`Unknown`
scripting backend in AssetRipper's log) and cannot be decompiled without
Il2CppDumper on a desktop — but the code is not what we want. The textures,
sprites, meshes and UI atlases all export without it.

Nothing here ships their binary. We read the art for palette, proportion and
material language, and rebuild in our own geometry.

## 1. What came out of the import

Those bundle names are a map of the game. Read them as a contents list:

| Bundle | What it holds | Worth exporting |
| --- | --- | --- |
| `newmarch`, `march` | the city, the world map, the march | **most valuable** — ground, buildings, roads |
| `newgamer`, `player` | the player character and the opening | characters, camera-era UI |
| `uihero` | hero portraits and frames | portraits, card chrome |
| `troop` | troop icons and unit art | small icons, silhouettes |
| `tech` | the research tree | icon grid, panel style |
| `radar` | the minimap | map frame, blip shapes |
| `uicityentryframeview`, `uieffectmask`, `noticetip` | UI chrome | **panels, buttons, borders** |
| `policypreset`, `policyscene`, `misc` | consent screens and odds and ends | ignore |

`Dependency 'archive:/CAB-…' wasn't found` is expected and harmless: those are
shared bundles the ripper could not pair. Everything self-contained still
exported.

## 2. Export it (AssetRipper, Windows)

1. **File → Export** (or *Export Project*). Choose an export path outside the
   repository — e.g. `C:\Users\USER\Documents\le-export`.
2. In **Settings**, before exporting:
   - **Script Content → Skip** (or *None*). The backend is unknown; asking for
     decompiled scripts only produces errors and thousands of stubs.
   - **Image Export Format → PNG**.
   - **Audio → WAV** (optional, we are not shipping their audio).
   - Leave *Mesh* and *Animation* on if the option exists — meshes are useful
     for silhouette reference.
3. Export. It takes a few minutes and writes:

   ```
   le-export/ExportedProject/Assets/
   ├── Texture2D/     ← the PNGs that matter
   ├── Sprite/        ← UI pieces already cut out
   ├── Mesh/          ← .obj files
   └── AnimationClip/
   ```

## 3. What to send

Not the whole export — it will be several GB. Send this:

```
PURSUE-ref/
├── ui/          ← from Sprite/ and the uicity*/noticetip textures
├── buildings/   ← from newmarch/march: roofs, walls, ground
├── characters/  ← from player/ and uihero/
├── terrain/     ← ground tiles, rock, skybox
└── screens/     ← 5–10 phone screenshots of the real game
```

- **20–40 images**, each under ~5 MB. Sort the export folders by size and take
  the biggest ones first: the big PNGs are the atlases and the hero art.
- **Screenshots are worth more than any single texture.** They show layout,
  spacing, palette and the state of the HUD all at once.
- Do not resize or re-compress anything. Cut-out detail is the point.

## 4. How to hand them over

1. Put `PURSUE-ref/` in Google Drive.
2. Share → **Anyone with the link** → copy the link.
3. Paste the link here.

I will list the folder first and tell you exactly which files I can actually
pull before you upload anything large — Drive links work, but very large
archives are better sent as one zip per folder.

## 5. What happens next (pass 17)

With the reference in hand:

1. **Palette extraction** — dominant colours per material class (stone, timber,
   cloth, ground, UI panels), pulled off the PNGs.
2. **Material rebuild** — the citadel's plaster, roof tile, slate, cloth and
   stone get their hues, value range and roughness matched.
3. **Proportion pass** — building footprints and heights measured against the
   screenshots, so the city reads at the same scale.
4. **UI chrome** — our vellum/ink panels corrected against theirs: corner
   radius, border weight, inner shadow, icon size relative to the frame.
5. **A comparison sheet** published to `docs/`, ours beside theirs per class,
   so the match is visible rather than asserted.
