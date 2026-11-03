package com.bytedance.sdk.account.user;

import org.json.JSONObject;
/* compiled from: LoginInfoEntity.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private Integer f13072a;

    /* renamed from: b  reason: collision with root package name */
    private String f13073b;

    /* renamed from: c  reason: collision with root package name */
    private String f13074c;

    /* renamed from: d  reason: collision with root package name */
    private String f13075d;

    /* renamed from: e  reason: collision with root package name */
    private com.ss.android.account.model2.b f13076e;

    /* renamed from: f  reason: collision with root package name */
    private String f13077f;

    /* renamed from: g  reason: collision with root package name */
    private String f13078g;

    /* renamed from: h  reason: collision with root package name */
    private String f13079h;

    /* renamed from: i  reason: collision with root package name */
    private Long f13080i;

    /* renamed from: j  reason: collision with root package name */
    private JSONObject f13081j;
    private String k;
    private String l;
    private String m;

    public d(JSONObject jSONObject) {
        this.f13081j = jSONObject;
    }

    public void extract() {
        extract(this, this.f13081j);
    }

    public static void extract(d dVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("login_type")) {
            dVar.f13072a = Integer.valueOf(jSONObject.optInt("login_type"));
        }
        if (jSONObject.has("mobile")) {
            dVar.f13073b = jSONObject.optString("mobile");
        }
        if (jSONObject.has("login_name")) {
            dVar.f13074c = jSONObject.optString("login_name");
        }
        if (jSONObject.has("email")) {
            dVar.f13075d = jSONObject.optString("email");
        }
        if (jSONObject.has("screen_name")) {
            dVar.f13077f = jSONObject.optString("screen_name");
        }
        if (jSONObject.has(com.bytedance.sdk.account.save.database.a.FIELD_AVATAR_URL)) {
            dVar.f13078g = jSONObject.optString(com.bytedance.sdk.account.save.database.a.FIELD_AVATAR_URL);
        }
        if (jSONObject.has("login_ticket")) {
            dVar.f13079h = jSONObject.optString("login_ticket");
        }
        if (jSONObject.has("expire_time")) {
            dVar.f13080i = Long.valueOf(jSONObject.optLong("expire_time"));
        }
        if (jSONObject.has("connect")) {
            JSONObject optJSONObject = jSONObject.optJSONObject("connect");
            dVar.f13076e = new com.ss.android.account.model2.b(optJSONObject.has("platform") ? optJSONObject.optString("platform") : null, optJSONObject.has(com.bytedance.sdk.account.save.database.a.FIELD_PLATFORM_SCREEN_NAME) ? optJSONObject.optString(com.bytedance.sdk.account.save.database.a.FIELD_PLATFORM_SCREEN_NAME) : null, optJSONObject.has("profile_image_url") ? optJSONObject.optString("profile_image_url") : null);
        }
    }

    public Integer getLoginType() {
        return this.f13072a;
    }

    public String getMaskMobile() {
        return this.f13073b;
    }

    public String getLoginName() {
        return this.f13074c;
    }

    public String getMaskEmail() {
        return this.f13075d;
    }

    public com.ss.android.account.model2.b getPlatformEntity() {
        return this.f13076e;
    }

    public String getScreenName() {
        return this.f13077f;
    }

    public String getAvatarUrl() {
        return this.f13078g;
    }

    public String getLoginTicket() {
        return this.f13079h;
    }

    public Long getExpireTime() {
        return this.f13080i;
    }

    public JSONObject getRawData() {
        return this.f13081j;
    }

    public String getMobile() {
        return this.k;
    }

    public void setMobile(String str) {
        this.k = str;
    }

    public String getEmail() {
        return this.l;
    }

    public void setEmail(String str) {
        this.l = str;
    }

    public String getCc() {
        return this.m;
    }

    public void setCc(String str) {
        this.m = str;
    }

    public String toString() {
        return "LoginInfoEntity{loginType=" + this.f13072a + ", maskMobile='" + this.f13073b + "', loginName='" + this.f13074c + "', maskEmail='" + this.f13075d + "', platformEntity=" + this.f13076e + ", screenName='" + this.f13077f + "', avatarUrl='" + this.f13078g + "', loginTicket='" + this.f13079h + "', expireTime=" + this.f13080i + ", rawData=" + this.f13081j + ", mobile='" + this.k + "', email='" + this.l + "', cc='" + this.m + "'}";
    }
}
