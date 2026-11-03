# 05 — Game logic: what the recovered code actually does

## 1. The architecture the Lua reveals

The gameplay layer is **Lua 5.3 bytecode** driving Unity view objects. The
recovered module map:

```
Core/          ModelValue · Event · BindGroup · Promise · Window · U3DObject
               BaseClass · SimpleClass · Logger · UI/UIManager · UI/UIConfig
Net/           NetPromise/ProtocalHandler
Tool/          NumberChangeTimer · TableExt
Game/Model/    ItemModel · PlayerInfoModel
Game/Ctrl/     TimeCtrl · NoticeCtrl · WwiseCtrl
Game/Farm/     Model/FarmModel · Model/FarmModel_Service · Model/FarmModel_Service_Proto
               Model/RedDotManager · View/* (13 views)
Game/GloryFactory/  GloryFactoryModel · GloryFactoryCtrl · UIGloryFactoryView
```

Four framework ideas worth stealing outright:

### `Core/ModelValue` — a typed observable

A model field is not a variable, it is a value with subscribers. `FarmModel`
exposes `resources`, `plots`, `farmersDict`, `tools`, `stamina`, `bonusItem`,
`isVip`, `shopItems`, `canGain`. When the server pushes an update, the model
re-raises `OnItemUpdate`, the view binds, and the UI redraws. **No view ever
polls.**

### `Core/Event` + `Core/BindGroup` — the event bus and its lifetime

`FarmModel` raises `OnFarmerChangedTool`, `OnToolChangedFarmer`,
`OnFarmerRecruited`, `OnToolDuraChanged`, `OnItemExchanged`. `BindGroup` binds
a callback to a model for as long as a view lives, and unbinds everything when
the view closes. This is the leak-free pattern; every modern Unity codebase
rediscover it painfully.

### `Core/ModelValue` + `Core/Promise` + `Net/NetPromise/ProtocalHandler`

Every server call returns a promise. The `ProtocalHandler` registry maps
message name → handler; a handler registers `Send`, `Init`, and a `Private`
error branch keyed on `msg.gs.common.ErrorCode`.

### `Core/UI/UIManager` + `UIConfig`

A declarative config: `UIConfig_Farm.lua` names each view, its layer, its
prefab and its bundle:

```lua
FarmMainView   = { Layer = "Layer", AssetBundle = "ui/views/farm",
                   AssetName = "FarmMainView.prefab" }
FarmerListView = { Layer = "AddictivePop", … }
CraftToolsView = { Layer = "MutexPop", … }
```

Layers: `Layer` (base), `MutexPop` (one pop at a time), `AddictivePop` (stacks).
**Copy this.** The three-layer rule is the whole of the reference's UI
architecture and it maps directly onto a canvas stack.

## 2. The production loop, reconstructed

The farm module is the complete worked example in the APK. Its data model,
recovered from the constant tables:

| Field | Type | Meaning |
| --- | --- | --- |
| `resources` | map | `Coin`, `Wood`, `Gold`, `Fish` by `ResourceEnum` |
| `plots` | list | `PlotEnum`: `None`, `Coin`, `Package`, `Home`, `Seaside`, `Forest`, `GoldMine` |
| `farmersDict` | map | workers, each on a plot with a tool |
| `tools` | list | `ToolEnum`: `None`, `FishingRod`, `FishingNet`, `Axe`, `Chainsaw`, `Pickaxe`, `MiningMachine` |
| `stamina` | number | worker energy, consumed by work |
| `bonusItem` | id | VIP/rate boost |
| `isVip` | bool | unlocks `VIPunlock` and extra stamina |
| `shopItems` | list | the exchange shop |
| `canGain` | bool | "something is ready to collect" |
| `pushProtos` | list | server-push messages the client listens for |

### The terrain → resource → tool table (recovered verbatim)

| `TerrainEnum` | `ResourceEnum` | `ToolEnum` |
| --- | --- | --- |
| `Home` | `Coin` | `None` |
| `Seaside` | `Fish` | `FishingRod`, `FishingNet` |
| `Forest` | `Wood` | `Axe`, `Chainsaw` |
| `GoldMine` | `Gold` | `Pickaxe`, `MiningMachine` |

And the tool tiers are literal: `FishingRod` → `FishingNet`,
`Axe` → `Chainsaw`, `Pickaxe` → `MiningMachine`. **Basic tool, better tool.**
That is the tier ladder every production building in this genre uses.

### The rules the code exposes

| Function | Rule |
| --- | --- |
| `GetMaxFarmersOnPlot` | plot type caps workers (`farmersworldactivitycfg`) |
| `GetMaxFarmers` | global cap, from `populationCap` |
| `GetRecruitmentCost` | `RECRUITMENT_COST` const, per `peopleCost` |
| `GetStaminaSpeed` | stamina drain rate, modified by `isVip` |
| `GetStamina` | `stamina` vs `staminaLimit`; `HPlimit` caps it |
| `GetToolCounts` | counts tools by `ToolEnum` |
| `GetResourceTypeByTerrain` | the table above |
| `GetBonusItemId` | VIP bonus item |
| `GetActivityConst` | `populationCap`, `peopleCost`, `land` |

Constants and config tables the code actually reads:

```
RECRUITMENT_COST · FARMERS_WORLD_HANDLING_FEE · UNRECRUITED_FARMER
farmersworldtoolcfg      — tools: type, durability, terrainType, groupId
farmersworldactivitycfg   — activity windows and population caps
itemcfg · exchangecfg · seasoncfg · serverinfocfg · newbattlepassbasecfg
```

### The reward flow — the important one

```
server push  SPushFarmersWorldCanGain
      ↓
client        CFarmersWorldGainQuery   → "what exactly is ready?"
      ↓ server
client        CFarmersWorldGainOutput  → "take it"
      ↓ server
client        SFarmersWorldGainOutput  → authoritative new totals
```

**The client never invents a number.** It asks what is owed, it asks to be
paid, and the server's reply is the truth. Offline accumulation is entirely
server-side. That is why the game survives being closed for a week.

## 3. The production-timer loop (Glory Factory)

The second shipped module is a **timed production building** with a battle-pass
accelerator, and it is the clearest statement of the genre's timer arithmetic:

| Symbol | Meaning |
| --- | --- |
| `processCount` | slots currently running |
| `GetProcessLimit()` | slots available (`REFINED_SANDS_PRODUCTION_NUM_MAX`) |
| `finishCount` | slots completed |
| `nextFinishTime` | when the current slot finishes |
| `allFinishTime` | when the whole queue finishes |
| `GetTimeToProcess()` | per-slot duration (`REFINED_SANDS_PRODUCTION_TIME`) |
| `GetResourceCost()` | per-slot cost (`REFINED_SANDS_PRODUCTION_SPEND`) |
| `GetCostMinuteInfo()` | `costMinuteRaw` vs `costMinuteBP` |
| `GetBPInfo()` | `REFINED_SANDS_PRODUCTION_BP_SPEED_UP` — a percentage |
| `GetMaxTimeToProcess()` | the cap when appending to a full queue |
| `CanCollect()` | a finished slot is waiting |

**The formulas, as the code implies them:**

```csharp
// raw time for one slot
int minutesRaw = Config.REFINED_SANDS_PRODUCTION_TIME;

// with a battle-pass accelerator active, expressed as a percentage
float speedUpPct = battlePass.speedUp;          // e.g. 20 means +20%
int minutesWithBp = Mathf.Max(1, Mathf.RoundToInt(minutesRaw / (1f + speedUpPct / 100f)));

// queue completion
DateTime nextFinish = now + minutesWithBp;
DateTime allFinish  = now + minutesWithBp * remainingSlots;

// cost
int costRaw = Config.REFINED_SANDS_PRODUCTION_SPEND;
int costBp  = Mathf.RoundToInt(costRaw * (1f + speedUpPct / 100f));  // faster costs more
```

Note the two halves of the accelerator: **speed costs more per minute**, but
the *duration* saved is what the player actually buys. The reference shows both
the raw cost and the BP-adjusted cost on screen at once (`costMinuteRawG`,
`costMinuteBPG`) — honest, and a good UI decision to copy.

The slider (`OnSliderValueChanged`) is the interaction: the player drags a
0..limit slider of slots to start, the cost is computed live, and `BtnProduce`
commits `CGloryFactoryProcess`. `BtnAppend` extends a running queue up to
`GetMaxTimeToProcess()`.

## 4. The other loops the reference runs

| Loop | Evidence | Rule |
| --- | --- | --- |
| **Exchange** | `CFarmersWorldExchangeResource`, `CFarmersWorldExchangeCoin`, `CFarmersWorldExchange`, `exchangecfg` | resource→resource at a rate, resource→premium coin, shop items with `limit`, `price`, `exchanged` |
| **Repair** | `CFarmersWorldFixTool`, `durability`, `dura` | tools wear out; `hpCost` is the stamina price of a repair |
| **Energy** | `CFarmersWorldRecoverHP`, `stamina`, `staminaLimit`, `HPlimit` | workers have a stamina pool; work drains it; it refills on a timer |
| **Red dots** | `RedDotManager.lua` — a tree of nodes with `value`, `parent`, `children`, `dots`, `Register`/`Unregister`/`Clear`, `GetAttentionedIdLists` | a notification system where a parent is lit if any child is; the tree is rebuilt on model clear |
| **Rankings** | `msg.gs.rank.CTypeRank` / `STypeRank`, `FarmRankView` | leaderboards are a separate protocol family, not a building feature |
| **Season/battle pass** | `seasoncfg`, `newbattlepassbasecfg`, `UINewBPModel`, `SeasonModel.primarySeason`, `ENewBPEffect` | a live season rotates weekly, with its own const overrides (`*_S10` variants) |
| **Time** | `Game/Ctrl/TimeCtrl`, `TimeModel.GetTimeNow`, `FormatTime2` | server time is authoritative; the client never trusts its own clock |

**The `*_S10` const pattern is worth a note.** `REFINED_SANDS_PRODUCTION_TIME`
and `REFINED_SANDS_PRODUCTION_TIME_S10` both exist — a base value and a
season-10 override, selected by `SeasonModel.primarySeason` / `curSeason`.
That is how a live service retunes economy without shipping a client update.

## 5. The C# translation

Everything above becomes plain C#. Here is the production building, which is
the piece worth getting right:

```csharp
public enum ResourceKind { Coin, Wood, Gold, Fish }
public enum TerrainKind  { None, Home, Seaside, Forest, GoldMine }
public enum ToolKind     { None, FishingRod, FishingNet, Axe, Chainsaw, Pickaxe, MiningMachine }
public enum PlotKind     { None, Coin, Package, Home, Seaside, Forest, GoldMine }

[CreateAssetMenu(menuName = "PURSUE/Production Building")]
public sealed class ProductionBuilding : ScriptableObject
{
    [Header("Timing")]
    [Min(1)] public int minutesPerSlot = 60;
    [Min(1)] public int slotLimit = 5;

    [Header("Cost")]
    public int resourceCost = 100;
    public ResourceKind costKind = ResourceKind.Coin;

    [Header("Territory")]
    public TerrainKind terrain;
    public ResourceKind yields;
    public ToolKind[] toolTiers;          // e.g. { FishingRod, FishingNet }

    [Header("Acceleration")]
    [Range(0f, 100f)] public float passSpeedUpPercent = 0f;
    [Range(0f, 100f)] public float passCostPremiumPercent = 0f;
}

/// The arithmetic, in one place, testable without Unity.
public static class ProductionMath
{
    public static int SlotDurationMinutes(ProductionBuilding b, bool passActive)
        => passActive
            ? Mathf.Max(1, Mathf.RoundToInt(b.minutesPerSlot /
                  (1f + b.passSpeedUpPercent / 100f)))
            : b.minutesPerSlot;

    public static int SlotCost(ProductionBuilding b, bool passActive)
        => passActive
            ? Mathf.RoundToInt(b.resourceCost *
                  (1f + b.passCostPremiumPercent / 100f))
            : b.resourceCost;

    public static DateTime QueueFinish(DateTime now, ProductionBuilding b,
                                       int slots, bool passActive)
        => now.AddMinutes((long)SlotDurationMinutes(b, passActive) * slots);

    /// Yield per slot, scaled by tool tier.
    public static int YieldFor(ProductionBuilding b, ToolKind equipped)
    {
        int tier = System.Array.IndexOf(b.toolTiers, equipped);
        if (tier < 0) return 0;                       // wrong tool: no yield
        return b.baseYield * (tier + 1);              // better tool, better yield
    }
}
```

And the authoritative client — the "never invent a number" rule, enforced:

```csharp
public sealed class ProductionController : MonoBehaviour
{
    [SerializeField] ProductionBuilding building;
    private readonly ProductionClient _rpc;      // the C…/S… pair
    private ProductionView _view;

    private void OnEnable()
    {
        _rpc = new ProductionClient();
        _rpc.OnInfoUpdated += ApplyServerInfo;   // SProductionInfo
        _rpc.OnPushReady    += OnServerSaysReady; // SProductionReady
    }

    /// The player asks the server what it owes, then asks to be paid.
    public async void Collect()
    {
        var pending = await _rpc.GainQuery();    // CProductionGainQuery
        if (pending.amount <= 0) { _view.ShowNothingToTake(); return; }

        var result = await _rpc.GainOutput();    // CProductionGainOutput
        ApplyServerInfo(result.info);            // SProductionGainOutput
        _view.PlayCollectAnimation(result.amount);
    }

    private void ApplyServerInfo(ProductionInfo info)
    {
        // Every number on screen comes from here. Never from a local timer.
        _view.SetSlots(info.processCount, building.slotLimit);
        _view.SetFinishTimes(info.nextFinishTime, info.allFinishTime);
        _view.SetStored(info.amount);
    }
}
```

## 6. What could not be recovered, precisely

| Missing | Where it lives | How to get it |
| --- | --- | --- |
| Numeric balance values | `farmersworldtoolcfg` etc. — data tables in the IL2CPP binary, not the Lua | Il2CppDumper on a PC → `ScriptableObject`/`BeanTableUtil` readers |
| Combat resolution | IL2CPP C# | same |
| The rest of the `msg.gs.*` catalogue | modules not in the APK's three Lua bundles | pull the downloaded bundles off a device (`Android/data/com.oasis.immortal/files/`) and re-run `tools/ufs-extract.py` |
| Protobuf field layouts | the `.proto` files, not shipped | reconstruct from the message names + your own schema; the names alone are enough to design the routes |

The Lua constant tables give you the *shape* and the *rules*. The numbers come
from the config tables, which is where you want them anyway — those are the
numbers you retune for your own theme.
