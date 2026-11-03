# 02 — The content pipeline: bundles, manifests, hot update

## 1. What ships inside the APK

```
resources/assets/
├── config.json                  boot config                        (doc 01)
├── url_config.json              host map                           (doc 01)
├── hhld_info.json               232 B encrypted
├── AssetManifest-A46D6781       686 KB encrypted bundle index
└── AssetBundle/
    ├── art_assets/    35 bundles   characters, effects, cutscenes
    ├── ui/             5 bundles   images, icons
    └── lua/            3 bundles   Lua 5.3 bytecode
```

**379 bundles total** were extracted; the layout above is their category split
as it appears in the dump. The naming is the map:

```
art_assets/effects/prefab/boss/monster_model_demon_knight-96aacda3270b4d451f1c341f14648617
art_assets/characters/uihero/hero_model_ayu-aaa9474d6605a5e0df8378aabab05b03
art_assets/effects/prefab/skills/h_imhotep-8819373fea9f8f4477fd4dfbe4e8ee74
ui/images/amy_icon/amy_common_icon-0b5c88c2e4f5e0db0e7ddab3856561f9
lua/game/farm-b23e62ba0483d2bc3887a11d47205545
```

Rules that hold across all of them:

| Rule | Meaning |
| --- | --- |
| `…/<name>-<32 hex>` | asset path + MD5 of the build inputs |
| first segment | **category**: `art_assets`, `ui`, `lua` |
| inner segments | mirror the Unity project's `Assets/…` folders |
| prefix `h_` | hero skill effect |
| prefix `l_` | low-tier hero variant |
| prefix `monster_model_`, `trap_` | boss model, boss trap |
| prefix `hero_model_`, `uihero` | the 3D hero, the 2D portrait atlas |

Worth copying as-is: **one bundle per feature folder, named after its path,
hashed**. The CDN cache becomes self-describing, and "which bundle holds this
asset?" is answerable from the filename.

## 2. The bundle format (verified)

All bundles are **UnityFS v8, Unity 2022.3.62f2**. Header of a representative
Lua bundle:

```
UnityFS\0   ver=8   unity=5.x.x/2022.3.62f2
size=56950  blocksInfo=74  uncompressed=101  flags=0x243
blocks: [(131072, 44440, LZ4HC), (30752, 12366, LZ4HC)]
nodes:  [("CAB-236d028f0816555c9283e5f66b871939", 161824)]
```

`flags = 0x243` decomposes as: compression `0x03` (LZ4HC), `0x40`
(blocks-info padded to a 16-byte boundary), `0x200` (data blocks padded to a
16-byte boundary). Most off-the-shelf extractors fail on this game precisely
because they miss those two alignments. `tools/ufs-extract.py` handles both.

## 3. How the gameplay is packaged

It is **Lua 5.3 bytecode** in Unity `TextAsset`s. Verified header bytes:

```
1b 4c 75 61 53 01 19 93 0d 0a 1a 0a     →  \x1bLua, version 0x53, LUAC_DATA
```

Not encrypted — just compiled. The constant tables stay readable, which is
where all the structure in docs 04 and 05 came from: module paths, service
names, protobuf message names, UI class names.

Recovered module layout (from the three shipped bundles):

```
Assets/Game/Lua/Game/<Feature>/
├── Model/
│   ├── <Feature>Model.lua
│   ├── <Feature>Model_Service.lua          RPC calls
│   ├── <Feature>Model_Service_Proto.lua    protobuf message names
│   └── RedDotManager.lua
└── View/
    ├── <Feature>MainView/
    │   ├── <Feature>MainView.lua
    │   ├── <Feature>MainView_plots.lua     repeatable cells
    │   └── PlotCell.lua
    ├── OverviewView/OverviewView.lua
    ├── RankView/<Feature>RankView.lua
    ├── ShopView/ShopView.lua
    └── GainView/GainView.lua, ConsumeCoin.lua, GetCoinView.lua
```

Plus a shared net layer (`Net/NetPromise/ProtocalHandler`) and a UI framework
(`UIManager`, `UIConfig`).

> **This Model / View / Service / Proto split is the single most valuable thing
> in the dump.** It is an MVVM-flavoured module contract that maps onto Unity
> almost one-to-one (doc 06).

## 4. `AssetManifest-A46D6781` — what it is, and why it stays closed

686 KB, high entropy, no readable structure. It is the client's index of every
downloadable bundle: name, hash, size, dependencies, and probably the CDN
version. The client decrypts it with a key inside IL2CPP.

What is safe to say (inference from the surrounding architecture, not from
decrypted bytes):

- it is **one manifest per content version**, fetched before any art;
- the filename carries a hash (`A46D6781`), so the manifest is itself
  content-addressed and cacheable;
- the bundle names inside it almost certainly match the on-disk paths
  (`art_assets/effects/prefab/boss/…`), because those already look like
  manifest keys.

**Do not spend time on it.** You are not shipping their content; a new project
needs only the *pattern*: one signed, hashed index per version, addressed by
content hash, listing path → hash → size → deps.

## 5. Hot update, reconstructed

The shell contains `com.oasis.upgrade.{UpgradeAgent, DefaultUpgradeAgent,
CustomUIUpgradeListener, IUpgradeForOwnRuleCallback, QueryUpgradeInfoListener}`
and `com.oasis.download.{RocketDownloaderDepend, RocketTGRPBatchDownloadParams,
RocketTGRPDownloadType, RocketHadesDownloaderDepend}` — a batch downloader with
resumable, parallel connections.

```
launch
 └─ UpgradeAgent.query()               → app update? forced? custom dialog?
 └─ fetch AssetManifest (hashed name)
     ├─ diff against the local cache index
     ├─ RocketTGRPBatchDownloadParams: [{path, hash, size, priority}, …]
     └─ parallel + resumable download into external storage
 └─ verify hashes → build the AssetBundle dependency graph
 └─ Unity boots; the Lua layer loads by module
```

**Keep:** a versioned manifest, hash verification, resumable batches.
**Drop:** the Rocket stack — Addressables + any object store does this.

## 6. Sizing reality check

- 427 MB as a `.apks`; roughly 1.2–1.5 GB installed.
- Only **three Lua bundles** ship in the APK. The rest arrives by hot update —
  which is why a clean install of the reference cannot play offline, and why the
  code you can read is a slice rather than the whole.
- 379 bundles in the APK is chapter-one content.

For PURSUE: one Player build with the first two regions baked in (≈120 MB
budget), Addressables remote groups added later. Do not ship a bootstrapper that
cannot play alone.
