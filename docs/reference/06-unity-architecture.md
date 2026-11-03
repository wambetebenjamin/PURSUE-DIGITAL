# 06 — Unity architecture for PURSUE (1 Samuel 30)

## 1. The mapping

| Land of Empires | PURSUE | Notes |
| --- | --- | --- |
| Ruined capital, rebuild | **Ziklag**, burned and rebuilt | the encampment on the hill |
| Buildings (farm, factory, barracks) | **the Works** — seven buildings | the whole city is one screen |
| Hero roster (Cleopatra, Caesar…) | **the mighty men of Judah** (2 Sam 23) | named, class-bound, skill-bearing |
| Troop tiers per building | **the companies of the six hundred** (1 Sam 30:9–10) | tier gated by the Works' level |
| Monster PvE stages | **the Amelekite holds** | Ziklag, the Arabah, the brook, etc. |
| Rank / leaderboard | **the watchmen's roll** | social, but cosmetic in v1 |
| Farm minigame | **the field at Gibeon** | optional side content |
| Glory Factory | **the pot of manna** (or the forge at the gate) | the timed production building |
| Battle pass | **the covenant season** | weekly retune, `*_S10`-style const overrides |
| Alliance / gifts | **the tribe** | deferred past v1 |

## 2. Project layout

```
Assets/
├── _Project/
│   ├── Scripts/
│   │   ├── Core/            # the framework, ported from the reference's Core/*
│   │   │   ├── ObservableValue.cs
│   │   │   ├── EventBus.cs
│   │   │   ├── BindGroup.cs
│   │   │   ├── Promise.cs
│   │   │   └── ServiceLocator.cs
│   │   ├── Net/
│   │   │   ├── ProtobufRpc.cs
│   │   │   ├── RouteName.cs
│   │   │   └── PushChannel.cs
│   │   ├── World/           # the city
│   │   │   ├── CityGrid.cs
│   │   │   ├── BuildingSite.cs
│   │   │   ├── BuildingDefinition.cs
│   │   │   ├── ProductionRuntime.cs
│   │   │   └── CityRenderer.cs
│   │   ├── Field/           # the march / skirmish
│   │   │   ├── HeroRuntime.cs
│   │   │   ├── CompanyRuntime.cs
│   │   │   ├── Encounter.cs
│   │   │   └── CombatResolver.cs
│   │   ├── UI/
│   │   │   ├── UIRoot.cs
│   │   │   ├── UIManager.cs
│   │   │   ├── UIConfig.cs
│   │   │   └── Views/…
│   │   └── Meta/            # progression, save, settings
│   ├── Data/                # ScriptableObjects
│   │   ├── Buildings/*.asset
│   │   ├── Heroes/*.asset
│   │   ├── Companies/*.asset
│   │   └── Stages/*.asset
│   ├── Protos/              # .proto files → generated C#
│   └── UI/Prefabs/
├── Scenes/
│   ├── Boot.unity
│   ├── City.unity
│   └── Field.unity
└── Packages/
```

## 3. The framework (ported from `Core/`)

The reference's Lua framework is sound. Port it rather than reinvent it.

```csharp
/// A value with subscribers. The reference's Core/ModelValue.
[Serializable]
public sealed class ObservableValue<T> : IDisposable
{
    private T _value;
    private event Action<T> _changed;

    public T Value
    {
        get => _value;
        set { if (EqualityComparer<T>.Default.Equals(_value, value)) return;
              _value = value; _changed?.Invoke(value); }
    }

    public void Subscribe(Action<T> onChanged, BindGroup group)
    {
        _changed += onChanged;
        group.Add(() => _changed -= onChanged);   // lifetime-bound
    }
}
```

```csharp
/// Binds a set of callbacks and releases them all at once. Core/BindGroup.
public sealed class BindGroup : IDisposable
{
    private readonly List<Action> _disposers = new();

    public void Add(Action dispose) => _disposers.Add(dispose);

    public void Dispose()
    {
        foreach (var d in _disposers) d();
        _disposers.Clear();
    }
}
```

## 4. Data as ScriptableObjects

Every balance number lives in an asset, never in code — that is how the
reference retunes a season without a client update.

```csharp
[CreateAssetMenu(menuName = "PURSUE/Building", fileName = "building_")]
public sealed class BuildingDefinition : ScriptableObject
{
    [Header("Identity")]
    public string id;                  // "barracks", "granary", "smithy"
    [TextArea] public string displayName;   // "The Companies"
    [TextArea] public string scripture;     // 1 Sam 30:9-10

    [Header("Levels (1..5)")]
    public BuildingLevel[] levels;

    [Header("Production")]
    public ResourceKind yields;
    public int yieldPerHour = 40;
    public int offlineCapHours = 8;

    [Header("Unlocks")]
    public CompanyTier highestTier;     // which troop tier this building gates
}

[Serializable]
public struct BuildingLevel
{
    public int level;
    public int costWood, costFood, costSilver;
    public int buildSeconds;
    public int yieldPerHour;
    public int capacity;
    public string note;      // shown on the site card, in the game's voice
}
```

```csharp
[CreateAssetMenu(menuName = "PURSUE/Hero", fileName = "hero_")]
public sealed class HeroDefinition : ScriptableObject
{
    public string id;                 // "ishbi-benob"
    public string displayName;        // "Ishbi-benob"
    public string epithet;            // "the Habezakiah"
    public HeroClass cls;             // Mighty, Swift, Silent, Unseen
    public int might, speed, guile, resolve;
    public SkillDefinition[] skills;
    public string[] bonds;            // other hero ids that boost him
}
```

```csharp
[CreateAssetMenu(menuName = "PURSUE/Stage", fileName = "stage_")]
public sealed class StageDefinition : ScriptableObject
{
    public string id;
    public string displayName;        // "The Arabah"
    public string scripture;          // 1 Sam 30:17-18
    public StageFlavour flavour;      // Stealth, Dungeon, Field
    public int[] waveCompanyIds;      // formation slots
    public HeroDefinition[] heroes;
    public int[] stars;               // three thresholds
    public EncounterTable encounters;
}
```

## 5. The city

```csharp
/// The grid. A base-building game needs an explicit placement model,
/// even if the presentation is isometric.
public sealed class CityGrid
{
    private readonly int _w, _h;
    private readonly BuildingSite[] _sites;

    public CityGrid(int w, int h, int siteCount)
    {
        _w = w; _h = h;
        _sites = new BuildingSite[siteCount];
        for (int i = 0; i < siteCount; i++)
            _sites[i] = new BuildingSite(i, i % w, i / w);
    }

    public BuildingSite this[int id] => _sites[id];

    public bool CanPlace(BuildingDefinition def, Vector2Int cell)
    {
        foreach (var s in _sites)
            if (s.building == def && s.cell == cell) return false;
        return true;
    }

    public void Place(int siteId, BuildingDefinition def, int level)
    {
        _sites[siteId].Set(def, level);
    }
}

[Serializable]
public sealed class BuildingSite
{
    public readonly int id;
    public readonly Vector2Int cell;
    public BuildingDefinition building;
    public int level;
    public DateTime upgradeFinishesAt;

    public bool IsUpgrading => upgradeFinishesAt > DateTime.UtcNow;

    public void Set(BuildingDefinition def, int level)
    {
        building = def; this.level = level;
    }
}
```

```csharp
/// Production arithmetic. Server-authoritative: this class is the
/// *client's preview*, and the server's reply always wins.
public static class ProductionMath
{
    public static int PendingYield(BuildingDefinition def, int level,
                                    DateTime lastCollected, DateTime now,
                                    int offlineCapHours)
    {
        var hours = (int)(now - lastCollected).TotalHours;
        hours = Mathf.Min(hours, offlineCapHours);
        var perHour = def.levels[level - 1].yieldPerHour;
        return hours * perHour;
    }

    public static int UpgradeCost(BuildingDefinition def, int level, ResourceKind kind)
    {
        var next = def.levels[Mathf.Min(level, def.levels.Length - 1)];
        return kind switch
        {
            ResourceKind.Wood  => next.costWood,
            ResourceKind.Food  => next.costFood,
            ResourceKind.Silver=> next.costSilver,
            _ => 0
        };
    }
}
```

## 6. The march

```csharp
/// A company of the six hundred.
[Serializable]
public struct CompanyRuntime
{
    public CompanyTier tier;      // Levies, Retainers, Champions
    public int count;
    public int morale;
    public int fatigue;
}

public enum CompanyTier { Levies, Retainers, Champions }
```

```csharp
/// Deterministic combat. The same inputs always produce the same result,
/// so the client and server can disagree about the winner only if one of
/// them is cheating.
public static class CombatResolver
{
    public static EncounterResult Resolve(EncounterState state, ulong seed)
    {
        var rng = new XorShift(seed);
        // power = men × quality × morale, modified by terrain and surprise
        int attackerPower = CompanyPower(state.attacker) + HeroPower(state.attackHeroes);
        int defenderPower = CompanyPower(state.defender) + EncounterPower(state.defenders);

        float terrainMod = state.terrain switch
        {
            TerrainKind.River => 0.85f,   // crossing costs you
            TerrainKind.Ridge => 1.15f,   // high ground
            TerrainKind.Village => 0.95f,
            _ => 1f
        };
        float surpriseMod = state.attackersSurprised ? 1.25f : 1f;
        float ratio = (attackerPower * terrainMod * surpriseMod) / (float)defenderPower;

        int rounds = Mathf.Clamp(Mathf.RoundToInt(6f / ratio), 1, 12);
        int losses = Mathf.Max(1, state.attacker.Count / (rounds + 2));
        return new EncounterResult(ratio > 1f, losses, rounds);
    }

    static int CompanyPower(CompanyRuntime c) => c.count * (10 + (int)c.tier * 5) * (c.morale / 100);
}
```

## 7. Networking

```csharp
/// The route name is derived from the message type, exactly as the
/// reference's ProtocalHandler does it.
public static class RouteName
{
    public static string From<TReq>() where TReq : IMessage
    {
        var name = typeof(TReq).Name;              // CGetFarmersWorldDetail
        var module = ModuleOf(typeof(TReq));        // festival_activity
        var action = name.StartsWith("C") ? name[1..] : name;
        return $"msg.gs.{module}.{action}";
    }
}
```

The message catalogue for PURSUE (mirroring the reference's naming exactly):

| Module | Requests | Responses |
| --- | --- | --- |
| `city` | `CCityInfo`, `CBuildUpgrade`, `CCollect`, `CRecruitCompany` | `SCityInfo`, `SBuildUpgrade`, `SCollect`, `SRecruitCompany` |
| `field` | `CStageInfo`, `CEncounterStart`, `CEncounterResolve` | `SStageInfo`, `SEncounterStart`, `SEncounterResolve` |
| `rank` | `CTypeRank` | `STypeRank` |
| `login` | `CDeleteRole` | `SDeleteRole` |
| `social` | `CRally`, `CAllianceInfo` | `SRally`, `SAllianceInfo` |

Pushes: `SPushCityReady`, `SPushStageAvailable`, `SPushRallyIncoming`.

## 8. UI

Port the reference's three-layer rule exactly:

```csharp
public enum UILayer { Base, MutexPop, AdditivePop }

public sealed class UIManager : MonoBehaviour
{
    private readonly Dictionary<Type, Stack<UIView>> _stacks = new();
    public event Action<Type> OnClosed;

    public void Open<T>(T view) where T : UIView
    {
        var layer = view.Layer;
        if (layer == UILayer.MutexPop)
            foreach (var v in _stacks.Values)
                foreach (var open in v) if (open.Layer == UILayer.MutexPop) open.Close();

        if (!_stacks.TryGetValue(typeof(T), out var stack)) _stacks[typeof(T)] = stack = new();
        stack.Push(view);
        view.Show();
    }

    public void Close<T>() where T : UIView
    {
        if (!_stacks.TryGetValue(typeof(T), out var stack) || stack.Count == 0) return;
        var view = stack.Pop();
        view.Bind.Dispose();          // release every binding at once
        view.Hide();
        OnClosed?.Invoke(typeof(T));
    }
}
```

## 9. Build and platform targets

| Target | Setting |
| --- | --- |
| Unity | 2022.3 LTS (matching the reference's engine version exactly) |
| Scripting backend | IL2CPP, ARM64, same as the reference |
| Orientation | landscape, same |
| Rendering | URP, or built-in if you want the smallest APK |
| Addressables | content groups per chapter; one manifest per version, hashed |
| Backend | Nakama (or PlayFab) — see doc 07 |

Matching Unity 2022.3 means your serialized assets and the reference's are
interchangeable at the format level, and that your build pipeline behaves the
same on the same CI image.
