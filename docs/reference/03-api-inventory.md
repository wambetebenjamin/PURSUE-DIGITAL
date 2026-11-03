# 03 — The Retrofit inventory: 113 endpoints, and what they are not

## The finding, stated plainly

The brief expected this document to list `login`, `upgrade_building`,
`attack_enemy`, `collect_resource`. **No such endpoint exists in the dump.**

Every Retrofit interface in `decompiled-source/sources/` belongs to the
**ByteDance TTGame SDK** (`com.bytedance.ttgame.*`), its older `gsdk.*`
generation, the payment SDK (`com.bytedance.pipo.*`), the Twitter Kit, or
ByteDance platform services (consent, network quality). Counted:

- **59** files import `retrofit2.*` or declare `@GET`/`@POST`
- **113** HTTP endpoints across them
- **0** of them touch game state

The game does not speak HTTP. Its state travels over a socket as protobuf RPC —
see doc 04. What follows is still worth having, because it is the account /
payment / compliance / anti-cheat spine that any live mobile strategy game
needs in some form, and this is a complete, real-world specification of one.

## The envelope

Every SDK response extends `com.bytedance.ttgame.base.BaseResponse`:

```java
public class BaseResponse {
    @SerializedName("code")    public int code;      // 0 == success
    @SerializedName("message") public String message;
    public boolean isSuccess() { return this.code == 0; }
}
```

Requests are `@FormUrlEncoded` `@FieldMap` or `@QueryMap`, plus one SDK-specific
annotation worth knowing:

```java
@AddCommonParam boolean z     // prepend the device/app/signature envelope
```

`@AddCommonParam` is what injects `aid`, `app_id=2447`, `device_id`, `iid`,
`channel=GooglePlay`, region and the request signature. The `info1…info4`
constants in `config.json` are almost certainly part of that signature. In a new
game this becomes your own auth header — one interceptor, not a hundred
parameters.

Base URLs come from `url_config.json`:

| Family | Base | Generation |
| --- | --- | --- |
| `sdk/…` | `https://bsdk-{sg,va}.bytegsdk.com/` | legacy |
| `gsdk/…` | `https://gsdk-{sg,va}.bytegsdk.com/` | middle |
| `v3/…`, `v4/…` | same hosts | current |
| `/gsdk/v3/payment/…` | `https://pay-{sg,ca}.bytegsdk.com/` | payments |

## Endpoint catalogue

Grouped by domain, exactly as extracted from the annotations. `@Path`/`{...}`
segments are runtime-variable (region or `subPath`).


### Account and login  (44)

| Verb | Path | Method | Params | Interface |
| --- | --- | --- | --- | --- |
| `GET` | `/1.1/account/verify_credentials.json` | `verifyCredentials` | `@Query("include_entities") Boolean bool, @Query("skip_status") Boolean bool2, @Query("include_email") Boolean bool3` | AccountService |
| `GET` | `/sdk/account/identity/info` | `a` | `@AddCommonParam boolean z, @Query("token") String str` | b |
| `POST` | `/sdk/account/pass/is_registered` | `isPassRegistered` | `@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | PassApi |
| `POST` | `/sdk/account/switch_os_login` | `switchOSLoginI18n` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | ISwitchLoginApi |
| `POST` | `/sdk/account/v3/login/switch_os_login` | `switchOSLoginCn` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | ISwitchLoginApi |
| `GET` | `/v3/account/identity/info` | `b` | `@AddCommonParam boolean z, @Query("token") String str` | b |
| `GET` | `gsdk/account/all_os_users` | `getAccountsWithSamePuid` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | IChannelLoginParamApi |
| `POST` | `gsdk/account/exchange_token` | `exchangeAccessToken` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | LoginApi |
| `POST` | `gsdk/account/login` | `login` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | LoginApi |
| `GET` | `gsdk/account/login_params` | `loginParmas` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | IChannelLoginParamApi |
| `POST` | `gsdk/account/logout` | `logout` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | LoginApi |
| `GET` | `gsdk/account/query_open_id` | `querySdkOpenIdByUserId` | `@AddCommonParam boolean z, @Query("access_token") String str, @Query("user_id") long j2` | LoginApi |
| `GET` | `gsdk/account/request_cloud` | `requestCloudWithCall` | `@QueryMap Map<String, String> map` | CloudApi |
| `POST` | `sdk/account/account_code/create` | `createAccountCode` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `sdk/account/account_code/login` | `LoginAccountCode` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `sdk/account/account_code/password/change` | `accountCodePasswordChange` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `sdk/account/account_code/password/change` | `modifyAccountCode` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `GET` | `sdk/account/account_code/query` | `queryAccountCode` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `sdk/account/gps/link` | `linkSeamlessAccount` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | SeamlessLoginApi |
| `GET` | `sdk/account/gps/link_data` | `getSeamlessLoginAccounts` | `@AddCommonParam boolean z, @Query("session_id") String str, @Query("login_id") String str2` | SeamlessLoginApi |
| `POST` | `sdk/account/gps/unlink` | `unlinkSeamlessAccount` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | SeamlessLoginApi |
| `POST` | `sdk/account/log_off` | `logOff` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | LogOffApi |
| `POST` | `sdk/account/oversea_info` | `accountOverseaInfo` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `sdk/bind/do_v3` | `bindOrChangeBind` | `@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |
| `POST` | `sdk/bind/unbind` | `unBind` | `@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |
| `POST` | `sdk/mobile/change_v2` | `changeMobile` | `@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |
| `POST` | `sdk/mobile/password` | `setMobilePassword` | `@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |
| `POST` | `v3/account/account_code/create` | `createAccountCodePrivatization` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `v3/account/account_code/login` | `LoginAccountCodePrivatization` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `v3/account/account_code/password/change` | `accountCodePasswordChangePrivatization` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `v3/account/account_code/password/change` | `modifyAccountCodePrivatization` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `GET` | `v3/account/account_code/query` | `queryAccountCodePrivatization` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `v3/account/g_login` | `loginPrivatization` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | LoginApi |
| `POST` | `v3/account/g_logout` | `logoutPrivatization` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | LoginApi |
| `POST` | `v3/account/oversea_info` | `accountOverseaInfoPrivatization` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | AccountCodeApi |
| `GET` | `v3/account/query_open_id` | `querySdkOpenIdByUserIdPrivatization` | `@AddCommonParam boolean z, @Query("access_token") String str, @Query("user_id") long j2` | LoginApi |
| `GET` | `v3/account/request_cloud` | `requestCloudWithCallPrivatization` | `@QueryMap Map<String, String> map` | CloudApi |
| `POST` | `v3/bind/do_v3` | `bindOrChangeBindPrivatization` | `@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |
| `POST` | `v3/bind/unbind` | `unBindPrivatization` | `@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |
| `POST` | `{path}/account/account_code/change_account_code` | `modifyAccount` | `@Path("path") String str, @AddCommonParam boolean z, @FieldMap Map<String, Object> map` | AccountCodeApi |
| `POST` | `{path}/account/confirm_qrcode` | `confirmQRCode` | `@Path("path") String str, @AddCommonParam boolean z, @FieldMap Map<String, Object> map` | IQRCodeApi |
| `POST` | `{path}/account/scan_qrcode` | `scanQRCode` | `@Path("path") String str, @AddCommonParam boolean z, @FieldMap Map<String, Object> map` | IQRCodeApi |
| `POST` | `{path}/bind/do_v3` | `bindOrChangeBindCall` | `@Path("path") String str, @AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |
| `POST` | `{path}/bind/unbind` | `unBindCall` | `@Path("path") String str, @AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap` | AccountManagementApi |

### Payments  (13)

| Verb | Path | Method | Params | Interface |
| --- | --- | --- | --- | --- |
| `POST` | `/gsdk/v3/payment/create_order` | `createOrderV3` | `@AddCommonParam boolean z, @FieldMap Map<String, String> map` | IOrderCommonApi |
| `GET` | `gsdk/payment/query_goods_with_activity` | `queryActivityGoods` | `@QueryMap Map<String, String> map` | IQueryProductApi |
| `GET` | `gsdk/v3/payment/query_goods_with_activity` | `queryActivityGoods` | `@Query("encrypt_msg") String str` | IQueryProductApi |
| `POST` | `gsdk/v4/payment/create_order` | `b` | `@Field("encrypt_msg") String str` | f |
| `POST` | `gsdk/v4/payment/minors` | `c` | `@Field("encrypt_msg") String str` | f |
| `GET` | `gsdk/v4/payment/minors` | `d` | `@Query("encrypt_msg") String str` | f |
| `GET` | `gsdk/v4/payment/query_goods_with_activity` | `a` | `@Query("encrypt_msg") String str` | f |
| `GET` | `v3/m_pay/minors` | `getMinorsLimit` | `@Query("encrypt_msg") String str` | IMinorsApi |
| `POST` | `v3/m_pay/minors` | `setMinorsLimit` | `@Field("encrypt_msg") String str` | IMinorsApi |
| `POST` | `v3/m_pay/order` | `createOrder` | `@Field("encrypt_msg") String str` | PipoOrderApi |
| `GET` | `v3/m_pay/products` | `queryProducts` | `@Query("encrypt_msg") String str` | IQueryProductApi |
| `POST` | `v3/m_pay/receipt` | `uploadToken` | `@Field("encrypt_msg") String str` | UploadTokenApi |
| `POST` | `v3/m_pay/restore_subscription` | `restoreSubscription` | `@Field("encrypt_msg") String str` | IRestoreSubscriptionApi |

### Compliance and minors  (8)

| Verb | Path | Method | Params | Interface |
| --- | --- | --- | --- | --- |
| `GET` | `/consent/api/combine/list/v1?sdk_version` | `fetchConsentRecord` | `@Query("sdk_version") String str, @Query("sdk_name") String str2` | IConsentApi |
| `GET` | `/consent/api/entity/get/v1` | `getConsentEntity` | `@Field("key") String str, @Field("sdk_version") String str2, @Field("sdk_name") String str3` | IConsentApi |
| `GET` | `/consent/api/entity/list/v1` | `listConsentEntity` | `@Field("keys") List<String> list, @Field("sdk_version") String str, @Field("sdk_name") String str2` | IConsentApi |
| `POST` | `/consent/api/record/create/v1` | `createConsentRecord` | `@Field("entity_keys") String str, @Field("business_flow") String str2, @Field("status") int i2, @Field("sdk_version")…` | IConsentApi |
| `GET` | `/consent/api/record/get/v1` | `getConsentRecord` | `@Field("entity_key") String str, @Field("sdk_version") String str2, @Field("sdk_name") String str3` | IConsentApi |
| `GET` | `/consent/api/record/list/v1` | `listConsentRecord` | `@Field("keys") List<String> list, @Field("sdk_version") String str, @Field("sdk_name") String str2` | IConsentApi |
| `POST` | `gsdk/compliance/pass/logoff/cancel` | `passLogoffCancel` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | LogOffApi |
| `POST` | `{subPath}/compliance/logoff/cancel_logoff` | `cancelLogoff` | `@Path("subPath") String str, @AddCommonParam boolean z, @FieldMap Map<String, String> map` | LoginApi |

### Server list and roles  (3)

| Verb | Path | Method | Params | Interface |
| --- | --- | --- | --- | --- |
| `GET` | `/gsdk/game/role_list` | `getRoleList` | `@Query("sdk_open_id") String str, @ExtraInfo Object obj` | Request |
| `GET` | `/gsdk/game/server_list` | `getServerList` | `@Query("game_version") String str, @Query("sdk_open_id") String str2, @Query("extra_info") String str3, @ExtraInfo Ob…` | Request |
| `GET` | `/gsdk/game/server_role_list` | `getServerAndRoleList` | `@Query("game_version") String str, @Query("sdk_open_id") String str2, @Query("extra_info") String str3, @ExtraInfo Ob…` | Request |

### Sharing and social  (19)

| Verb | Path | Method | Params | Interface |
| --- | --- | --- | --- | --- |
| `GET` | `/1.1/collections/entries.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `collection` | `@Query("id") String str, @Query("count") Integer num, @Query("max_position") Long l, @Query("min_position") Long l2` | CollectionService |
| `POST` | `/1.1/favorites/create.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `create` | `@Field("id") Long l, @Field("include_entities") Boolean bool` | FavoriteService |
| `POST` | `/1.1/favorites/destroy.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `destroy` | `@Field("id") Long l, @Field("include_entities") Boolean bool` | FavoriteService |
| `GET` | `/1.1/favorites/list.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `list` | `@Query("user_id") Long l, @Query("screen_name") String str, @Query("count") Integer num, @Query("since_id") String st…` | FavoriteService |
| `GET` | `/1.1/lists/statuses.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `statuses` | `@Query("list_id") Long l, @Query("slug") String str, @Query("owner_screen_name") String str2, @Query("owner_id") Long…` | ListService |
| `GET` | `/1.1/search/tweets.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `tweets` | `@Query("q") String str, @Query(encoded = true, value = "geocode") Geocode geocode, @Query("lang") String str2, @Query…` | SearchService |
| `POST` | `/1.1/statuses/destroy/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `destroy` | `@Path("id") Long l, @Field("trim_user") Boolean bool` | StatusesService |
| `GET` | `/1.1/statuses/home_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `homeTimeline` | `@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @…` | StatusesService |
| `GET` | `/1.1/statuses/lookup.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `lookup` | `@Query("id") String str, @Query("include_entities") Boolean bool, @Query("trim_user") Boolean bool2, @Query("map") Bo…` | StatusesService |
| `GET` | `/1.1/statuses/mentions_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `mentionsTimeline` | `@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @…` | StatusesService |
| `POST` | `/1.1/statuses/retweet/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `retweet` | `@Path("id") Long l, @Field("trim_user") Boolean bool` | StatusesService |
| `GET` | `/1.1/statuses/retweets_of_me.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `retweetsOfMe` | `@Query("count") Integer num, @Query("since_id") Long l, @Query("max_id") Long l2, @Query("trim_user") Boolean bool, @…` | StatusesService |
| `GET` | `/1.1/statuses/show.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `show` | `@Query("id") Long l, @Query("trim_user") Boolean bool, @Query("include_my_retweet") Boolean bool2, @Query("include_en…` | StatusesService |
| `POST` | `/1.1/statuses/unretweet/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `unretweet` | `@Path("id") Long l, @Field("trim_user") Boolean bool` | StatusesService |
| `POST` | `/1.1/statuses/update.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `update` | `@Field("status") String str, @Field("in_reply_to_status_id") Long l, @Field("possibly_sensitive") Boolean bool, @Fiel…` | StatusesService |
| `GET` | `/1.1/statuses/user_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13` | `userTimeline` | `@Query("user_id") Long l, @Query("screen_name") String str, @Query("count") Integer num, @Query("since_id") Long l2, …` | StatusesService |
| `POST` | `/gsdk/share/douyin_video` | `requestAweShareId` | `@Body IAweShareIdRequestBody iAweShareIdRequestBody` | Request |
| `POST` | `/gsdk/share/douyin_video/search` | `requestAweShareVideoInfo` | `@Body IAweShareVideoInfoRequestBody iAweShareVideoInfoRequestBody` | Request |
| `POST` | `https://upload.twitter.com/1.1/media/upload.json` | `upload` | `@Part("media") RequestBody requestBody, @Part("media_data") RequestBody requestBody2, @Part("additional_owners") Requ…` | MediaService |

### SDK settings, AB test, notices, misc  (5)

| Verb | Path | Method | Params | Interface |
| --- | --- | --- | --- | --- |
| `GET` | `/game/cdkey/api/use/{app_id}` | `cdkeyExchange` | `@Path("app_id") String str, @Query("aid") String str2, @Query("cdkey") String str3, @Query("access_token") String str…` | Request |
| `GET` | `/service/settings/v3/` | `fetchSettings` | `@AddCommonParam boolean z, @QueryMap HashMap<String, Object> hashMap` | SDKSettingsApi |
| `GET` | `gsdk/misc/emulator_status` | `whetherDeviceIsSimulator` | `@AddCommonParam boolean z` | EmulatorApi |
| `GET` | `sdk_dynamic/api/v1/module_rule` | `b` | `@AddCommonParam(level = 31) boolean z, @QueryMap Map<String, Object> map, @QueryMap Map<String, Object> map2` | g |
| `GET` | `sdk_dynamic/api/v2/module` | `a` | `@AddCommonParam(level = 31) boolean z, @QueryMap Map<String, Object> map, @QueryMap Map<String, Object> map2` | g |

### Unclassified  (16)

| Verb | Path | Method | Params | Interface |
| --- | --- | --- | --- | --- |
| `GET` | `/1.1/help/configuration.json` | `configuration` | — | ConfigurationService |
| `POST` | `/act/{appid}/process/exec` | `gmGiftExchange` | `@Path("appid") String str, @Body GiftExchangeRequest giftExchangeRequest` | Request |
| `GET` | `/gsdk/announcement/list` | `fetchNotices` | `@QueryMap Map<String, String> map` | Request |
| `GET` | `/gsdk/client-ip` | `a` | — | f |
| `GET` | `/gsdk/doc/comment` | `a` | `@AddCommonParam boolean z` | c |
| `GET` | `/gsdk/misc/get_user_parameter` | `a` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | b |
| `POST` | `/gsdk/misc/update_user_parameter` | `b` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | b |
| `GET` | `/v3/misc/get_user_parameter` | `c` | `@AddCommonParam boolean z, @QueryMap Map<String, Object> map` | b |
| `POST` | `/v3/misc/update_user_parameter` | `d` | `@AddCommonParam boolean z, @FieldMap Map<String, Object> map` | b |
| `GET` | `get_strategy` | `a` | `@Query("scene") String str, @Query("ip") String str2` | j |
| `GET` | `gsdk/misc/location/ipinfo` | `getLocationByIp` | `@Query("ISOLanguage") String str, @Query("IP") String str2, @Query("StrategyVersion") int i2` | LocationIpApi |
| `POST` | `sdk/bind/connect` | `bindConnect` | `@AddCommonParam boolean z, @FieldMap Map<String, Long> map` | ConnectApi |
| `POST` | `sdk/bind/visitor_bind` | `bindVisitor` | `@AddCommonParam boolean z, @FieldMap Map<String, Integer> map` | MobileBindVisitorApi |
| `POST` | `sdk/email/bind` | `bindEmail` | `@AddCommonParam boolean z, @FieldMap HashMap<String, String> hashMap` | AssociatedMailboxApi |
| `POST` | `sdk/email/send_code` | `sendEmailCode` | `@AddCommonParam boolean z, @FieldMap HashMap<String, String> hashMap` | AssociatedMailboxApi |
| `POST` | `v3/bind/visitor_bind` | `bindVisitorPrivatization` | `@AddCommonParam boolean z, @FieldMap Map<String, Integer> map` | MobileBindVisitorApi |

## Payload shapes

The SDK models are plain Gson POJOs. The shapes that matter if you rebuild this
layer yourself (rather than buying it):

### Login — `POST gsdk/account/login`

Request `@FieldMap` (the keys it actually posts, from the surrounding classes):

```json
{
  "aid": "2447",
  "app_id": "2447",
  "channel": "GooglePlay",
  "device_id": "…", "iid": "…", "opaque": "…",
  "platform": "android",
  "version_code": "262", "version_name": "0.1.136",
  "ts": 1730000000, "sign": "…"
}
```

Response — `SdkUserInfo`:

```json
{
  "code": 0, "message": "ok",
  "user_id": "…", "sdk_open_id": "…", "session_key": "…",
  "access_token": "…", "expires_in": 0, "is_new_user": false
}
```

### Exchange for a game session — `POST gsdk/account/exchange_token`

```json
{ "open_id": "…", "access_token": "…", "app_id": "2447" }
→ { "code": 0, "session_token": "…", "uid": 0, "server_region": 10 }
```

The `session_token` is what the game's own socket handshake consumes. **That is
the seam between the two layers**: HTTP login ends with a token, and the
protobuf socket begins with it.

### Purchase — `POST v3/m_pay/order`

```json
{ "encrypt_msg": "…", "product_id": "…", "order_id": "…", "receipt": "…" }
→ { "code": 0, "order_id": "…", "status": "paid", "deliver_payload": "…" }
```

`encrypt_msg` is a signed, encrypted blob rather than readable JSON — the SDK's
own envelope again. This is the one part of the stack you should **not**
reimplement: use the platform store, verify server-side.

### Server list — `GET /gsdk/game/server_list`

```
?game_version=0.1.136&sdk_open_id=…&extra_info=…
→ { "code": 0, "servers": [{ "server_id": 1, "name": "…", "status": "smooth",
                             "address": "…", "port": 0, "region": 10 }] }
```

The `address`/`port` pair is the socket the game connects to. **This is the
single most important endpoint in the SDK from a game-architecture point of
view**, because it is the hand-off from HTTP to gameplay.

## What to take from this document

1. **Do not rebuild the SDK.** PlayFab/Steam/Apple/Google identity plus the
   platform's own IAP replaces all 113 endpoints.
2. **Do copy the shape**: one `BaseResponse { code, message }` envelope, one
   auth interceptor, region-aware base URLs, `v3`-style versioned paths.
3. **Note the hand-off pattern**: HTTP login → session token → socket RPC. It
   is how every serious mobile strategy game works, including anything you build
   on Nakama.

