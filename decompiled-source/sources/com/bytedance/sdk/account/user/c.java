package com.bytedance.sdk.account.user;

import android.text.TextUtils;
import com.bytedance.bdinstall.e;
import com.bytedance.sdk.account.constants.a;
import com.bytedance.sdk.account.impl.h;
import com.bytedance.sdk.account.save.c;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: IBDAccountUserEntity.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    protected String f13065a;

    /* renamed from: b  reason: collision with root package name */
    protected JSONObject f13066b;

    /* renamed from: c  reason: collision with root package name */
    protected JSONObject f13067c;
    public int countryCode;

    /* renamed from: d  reason: collision with root package name */
    protected JSONObject f13068d;
    public String email;

    /* renamed from: g  reason: collision with root package name */
    public String f13070g;

    /* renamed from: h  reason: collision with root package name */
    public int f13071h;
    public boolean hasPassword;
    public boolean isKidsMode;
    public boolean isNewUser;
    public boolean isVisitorAccount;
    public String mobile;
    public int odinUserType;
    public String secUserId;
    public String sessionKey;
    public long userId;
    public final Map<String, com.ss.android.account.model2.a> bindMap = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    public final Map<String, Map<String, com.ss.android.account.model2.a>> f13069e = new HashMap();

    public c() {
    }

    public c(JSONObject jSONObject) {
        this.f13067c = jSONObject;
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        this.f13066b = optJSONObject;
        this.f13068d = optJSONObject;
    }

    public c(JSONObject jSONObject, JSONObject jSONObject2) {
        this.f13067c = jSONObject;
        this.f13066b = jSONObject.optJSONObject("data");
        this.f13068d = jSONObject2;
    }

    public void a(JSONObject jSONObject) {
        this.f13067c = jSONObject;
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        this.f13066b = optJSONObject;
        this.f13068d = optJSONObject;
    }

    public JSONObject getRawJson() {
        return this.f13067c;
    }

    public JSONObject getRawData() {
        return this.f13066b;
    }

    public JSONObject getUserData() {
        return this.f13068d;
    }

    public void extract() {
        extract(this, this.f13067c, this.f13068d);
    }

    public static void extract(c cVar, JSONObject jSONObject, JSONObject jSONObject2) {
        cVar.userId = jSONObject2.optLong("user_id", 0L);
        cVar.odinUserType = jSONObject2.optInt("odin_user_type", 0);
        cVar.f13065a = jSONObject2.optString(a.InterfaceC0185a.SEC_USER_ID, "");
        cVar.sessionKey = jSONObject2.optString("session_key", "");
        cVar.isNewUser = jSONObject2.optInt(e.aq) != 0;
        cVar.mobile = jSONObject2.optString("mobile", "");
        cVar.hasPassword = jSONObject2.optInt("has_password") != 0;
        cVar.secUserId = jSONObject2.optString(a.InterfaceC0185a.SEC_USER_ID, "");
        cVar.isVisitorAccount = jSONObject2.optBoolean("is_visitor_account", false);
        cVar.email = jSONObject2.optString("email", "");
        com.ss.android.account.model2.a create = com.ss.android.account.model2.a.create("mobile");
        com.ss.android.account.model2.a create2 = com.ss.android.account.model2.a.create("email");
        create2.mNickname = cVar.email;
        if (!TextUtils.isEmpty(cVar.email)) {
            cVar.getBindMap().put(create2.mName, create2);
        }
        create.mNickname = cVar.mobile;
        if (!TextUtils.isEmpty(cVar.mobile)) {
            cVar.getBindMap().put(create.mName, create);
        }
        a(cVar, jSONObject2);
        cVar.countryCode = jSONObject2.optInt(c.b.COUNTRY_CODE, -1);
        cVar.isKidsMode = jSONObject2.optInt("is_kids_mode") == 1;
        cVar.f13070g = jSONObject2.optString("lite_user_info_string", "");
        cVar.f13071h = jSONObject2.optInt("lite_user_info_demotion", 0);
    }

    public static void a(c cVar, JSONObject jSONObject) {
        Map<String, com.ss.android.account.model2.a> map;
        com.ss.android.account.model2.a aVar;
        JSONArray optJSONArray = jSONObject.optJSONArray("connects");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            System.currentTimeMillis();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                String string = jSONObject2.getString("platform");
                if (string != null && string.length() != 0) {
                    com.ss.android.account.model2.a create = com.ss.android.account.model2.a.create(string);
                    create.mUserId = jSONObject.optLong("user_id", 0L);
                    if (jSONObject2.has("screen_name")) {
                        create.mNickname = jSONObject2.optString("screen_name");
                    } else if (jSONObject2.has(com.bytedance.sdk.account.save.database.a.FIELD_PLATFORM_SCREEN_NAME)) {
                        create.mNickname = jSONObject2.optString(com.bytedance.sdk.account.save.database.a.FIELD_PLATFORM_SCREEN_NAME);
                    }
                    create.mAvatar = jSONObject2.optString("profile_image_url");
                    create.mPlatformUid = jSONObject2.optString("platform_uid");
                    create.mSecPlatformUid = jSONObject2.optString("sec_platform_uid");
                    create.mModifyTime = jSONObject2.optLong("modify_time");
                    create.mCreateTIme = jSONObject2.optString("create_time");
                    create.f17911a = jSONObject2.optInt("platform_app_id", 0);
                    create.mLogin = true;
                    create.f17915e = jSONObject2.optString("extra");
                    c userInfo = h.a().getUserInfo();
                    if (userInfo != null && userInfo.userId == cVar.userId && (map = userInfo.f13069e.get(string)) != null && (aVar = map.get(String.valueOf(create.f17911a))) != null) {
                        create.f17912b = aVar.f17912b;
                        create.f17913c = aVar.f17913c;
                        create.f17914d = aVar.f17914d;
                        create.mExpireIn = aVar.mExpireIn;
                        create.mExpire = aVar.mExpire;
                        create.f17917h = aVar.f17917h;
                        create.f17916g = aVar.f17916g;
                    }
                    Map<String, com.ss.android.account.model2.a> map2 = cVar.f13069e.get(string);
                    if (map2 == null) {
                        map2 = new HashMap<>();
                        cVar.f13069e.put(string, map2);
                    }
                    com.ss.android.account.model2.a aVar2 = map2.get(String.valueOf(create.f17911a));
                    if (aVar2 == null || aVar2.mModifyTime <= 0 || aVar2.mModifyTime <= create.mModifyTime) {
                        cVar.getBindMap().put(string, create.clone());
                        map2.put(String.valueOf(create.f17911a), create);
                    }
                }
            }
        }
    }

    public long getUserId() {
        return this.userId;
    }

    public int getOdinUserType() {
        return this.odinUserType;
    }

    public String getSecUserId() {
        return this.secUserId;
    }

    public boolean isVisitorAccount() {
        return this.isVisitorAccount;
    }

    public Map<String, com.ss.android.account.model2.a> getBindMap() {
        return this.bindMap;
    }

    public Map<String, Map<String, com.ss.android.account.model2.a>> a() {
        return this.f13069e;
    }

    public int getCountryCode() {
        return this.countryCode;
    }

    public boolean isNewUser() {
        return this.isNewUser;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSecUid() {
        return this.f13065a;
    }
}
