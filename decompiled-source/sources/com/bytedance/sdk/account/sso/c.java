package com.bytedance.sdk.account.sso;

import org.json.JSONObject;
/* compiled from: BindConflictUser.java */
/* loaded from: classes2.dex */
public class c {
    public String avatarUrl;
    public String conflictPlatformScreenName;
    public String currentPlatformScreenName;
    public String lastLoginTime;
    public String mobile;
    public String screenName;

    public static void extract(c cVar, JSONObject jSONObject) {
        if (jSONObject != null) {
            cVar.screenName = jSONObject.optString("screen_name");
            cVar.avatarUrl = jSONObject.optString(com.bytedance.sdk.account.save.database.a.FIELD_AVATAR_URL);
            cVar.lastLoginTime = jSONObject.optString("last_login_time");
            cVar.mobile = jSONObject.optString("mobile");
            cVar.currentPlatformScreenName = jSONObject.optString("platform_screen_name_current");
            cVar.conflictPlatformScreenName = jSONObject.optString("platform_screen_name_conflict");
        }
    }
}
