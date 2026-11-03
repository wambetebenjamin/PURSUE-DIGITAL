# 01 — Runtime, manifests and configuration

## 1. What the app is

| | |
| --- | --- |
| Package | `com.oasis.immortal` |
| Version | 0.1.136 (versionCode 262) |
| Engine | **Unity 2022.3.62f2**, IL2CPP, ARM64 + ARMv7 |
| Host activity | `com.oasis.unity.OasisPlayerActivity` |
| Orientation | `sensorLandscape` (locked landscape — a strategy game convention) |
| Install | `preferExternal`, bundletool split APKs (`requiredSplitTypes="base__abi"`) |
| Compile SDK | 36 (Android 16 preview), min SDK in the 24–26 range |

Two layers, and keeping them apart is the whole of this analysis:

1. **The Android shell** (`com.oasis.*`, `com.bytedance.ttgame.*`, `gsdk.*`) —
   account, payment, push, sharing, consent, anti-cheat, hot-update download.
   This is the Java you can read.
2. **The game** (`libil2cpp.so` + `assets/bin/Data` + the bundles) — a Unity
   IL2CPP build with a **Lua 5.3 gameplay layer** on top. This is where the game
   lives, and this is what is compiled.

`com.oasis.*` is the bridge: `achievement`, `analytics`, `channel`, `download`,
`gift`, `location`, `notice`, `pk`, `purchase`, `push`, `relationship`, `rtc`,
`share`, `udp`, `voice`, `webview`, `upgrade` — each an agent taking calls from
Unity's C# and forwarding to the platform SDK.

### Permissions that tell you the feature set

`INTERNET`, `ACCESS_NETWORK_STATE`, `WAKE_LOCK`, `VIBRATE`, `POST_NOTIFICATIONS`,
`FOREGROUND_SERVICE` (+`DATA_SYNC`), `BLUETOOTH`/`ACCESS_WIFI_STATE` (peer
diagnostics), `WRITE/READ_EXTERNAL_STORAGE` (the bundle cache), launcher
badge permissions (Samsung/HTC/Sony), `RECORD_AUDIO` (voice chat / ASR).

> For PURSUE: the interesting ones are `INTERNET` + `WAKE_LOCK` +
> `POST_NOTIFICATIONS` + `VIBRATE`. Everything else is live-service machinery
> you do not need in v1.

---

## 2. `resources/assets/config.json` — every field, explained

This file is the boot configuration. It is read before Unity starts, and every
value in it is either an ID you must obtain yourself or a host you must
replace.

### `app`

| Key | Value | Meaning |
| --- | --- | --- |
| `is_debug` | `false` | release build |
| `app_id` | `"2447"` | the ByteDance Game SDK application id |
| `channel` | `"GooglePlay"` | distribution channel string |
| `app_name` | `"immortal"` | internal project name (Land of Empires = *Immortal*) |
| `adjust_app_token` | `iwlv9uaarpxc` | Adjust attribution token |
| `screen_orientation` | `sensorLandscape` | landscape, both ways round |
| `main_activity` | `com.oasis.unity.OasisPlayerActivity` | Unity host activity |
| `secret_id` | `1` | SDK secret index |
| `info1`…`info4` | `1102924926`, `1536907735`, `2075601064`, `1250112469` | SDK signing/obfuscation words — 32-bit constants XORed with request params by `@AddCommonParam` |
| `server_region` | `10` | region shard (10 ≈ Singapore/Asia in the ByteDance numbering) |
| `update_version_code` | `100` | hot-update version counter |
| `device_register_optimization` | `false` | device risk control |

### `app.push`

Push channel `landofglory`, host `https://i.isnssdk.com`, red-dot badge cap of
4 with `default_strategy`. Purely retention machinery.

### `app.im`

ByteDance IM (the in-game world chat):

```json
"fpid": 89, "aid": 2447, "appKey": "e0f82475ab9dbf5717d18b4a9c0d7fd0",
"wsHost": "wss://frontier-va.byteoversea.com/ws/v2",
"service": 2, "method": 1,
"httpHost": "https://imapi-va-oth.isnssdk.com/"
```

A WebSocket chat service. **This is the one websocket in the app** — the game
state itself does not use it.

### `app.share`

A panel id (`2447_text_image_1`) and a Twitter consumer key/secret pair,
used for the share-a-screenshot loop.

### `channel.account`

Sign-in providers and their platform ids — Google, Google Play Games, Facebook,
VK:

```json
"google_id":       "...apps.googleusercontent.com",
"google_web_id":   "...apps.googleusercontent.com",
"google_platForm_id": 597, "facebook_platform_id": 598,
"vk_platfrom_id": 1684, "google_play_platform_id": 1845,
"sort": ["vk","facebook","google","google_play"]
```

### `channel.pay`

`iap_key` — an RSA-2048 **public** key used to verify purchase receipts. Note
this is public key material; it is not a secret, and it is not usable against
their store.

> **For PURSUE:** none of this transfers. You need your own app id, your own
> store listing, your own attribution (or none), your own IM (or none).

---

## 3. `url_config.json` — the complete host map

Every host in the file, with what it is for:

| Key | Host | Purpose |
| --- | --- | --- |
| `log_url` | `https://log.byteoversea.net/` | log sink |
| `passport_host` | `bsdk-sg.bytegsdk.com` | account (Singapore) |
| `passport_host_va` | `bsdk-va.bytegsdk.com` | account (Virginia) |
| `passport_host_merge` | `gsdk-sg.bytegsdk.com` | merged game+account SDK |
| `passport_host_merge_va` | `gsdk-va.bytegsdk.com` | merged, US region |
| `passport_host_merge_sandbox` | `https://gsdk-sg-sandbox.bytegsdk.com` | staging |
| `account_privatization` | `https://login-sg.bytegsdk.com/` | white-label login |
| `bsdk_server_url` / `gsdk_server_url` | `https://bsdk-sg.bytegsdk.com/`, `https://gsdk-sg.bytegsdk.com/` | base URLs for the two API families (`bsdk/…` and `gsdk/…`, plus `v3/…`) |
| `*_boe` | `*.boe-gateway.byted.org` | ByteDance internal test gateway — dead in production, and a useful reminder that the SDK has three API generations coexisting (`sdk/`, `gsdk/`, `v3/`, `v4/`) |
| `pay_host` | `https://pay-sg.bytegsdk.com/` | payments |
| `pay_host_va` | `https://pay-ca.bytegsdk.com/` | payments, Americas |
| `geas_host_sg` / `_va` | `https://gsdk-{sg,va}.bytegsdk.com/` | GEAS: geo/device risk scoring |
| `realtime_event_report_url` | `https://dis-sg.bytegsdk.com/put_log_shared_1` | streaming analytics |
| `asr_config_url` / `asr_report_url` | `https://mon.isnssdk.com/…` | APM (crash, ANR, network quality) |
| `asr_address` | `wss://speech-alisg.bytedance.com` | speech recognition (voice input) |
| `common_token_host` | `["bytegsdk.com","sgsnssdk.com"]` | domains that may hold the session token |

**Reading:** two regions (SG, VA), three auth generations, one payment host per
region, and telemetry on two separate hosts. That is a live-service spine, and
rebuilding it is what doc 07 is for.

---

## 4. `hhld_info.json` — 232 bytes, encrypted

Not JSON in spite of the name. `file` reports data; entropy is high; there is no
`{` anywhere. It is a small encrypted blob — most plausibly the client's
per-install "hotfix"/holdings descriptor (the name suggests *household*), read
by the same routine that decrypts `AssetManifest`.

- Size: **232 bytes** — too small for a key list, about right for a few
  integers/a short string per region.
- Decryption key lives in IL2CPP. Not recoverable in this environment.

**Action:** ignore it. There is nothing in 232 encrypted bytes worth the
Il2CppDumper detour, and nothing in it that a new game needs.

---

## 5. Other files in `resources/assets/`

| File | Notes |
| --- | --- |
| `AssetManifest-A46D6781` | 686 KB, encrypted/compressed — see doc 02 §4 |
| `config.json` | above |
| `url_config.json` | above |
| `hhld_info.json` | above |

Anything else at this level is Unity's own `bin/Data` payload, which the JADX
dump does not include (it belongs to the APK's native assets, not `res/`).
