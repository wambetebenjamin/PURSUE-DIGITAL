package com.bytedance.sdk.account.api.call;

import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import org.json.JSONObject;
/* compiled from: ThirdTokenResponse.java */
/* loaded from: classes2.dex */
public class j extends b {

    /* renamed from: a  reason: collision with root package name */
    public final String f12251a;

    /* renamed from: b  reason: collision with root package name */
    public final int f12252b;

    /* renamed from: c  reason: collision with root package name */
    public String f12253c;

    /* renamed from: d  reason: collision with root package name */
    public long f12254d;

    /* renamed from: e  reason: collision with root package name */
    public String f12255e;
    @Deprecated

    /* renamed from: f  reason: collision with root package name */
    public String f12256f;
    @Deprecated

    /* renamed from: g  reason: collision with root package name */
    public long f12257g;

    /* renamed from: h  reason: collision with root package name */
    public String f12258h;

    /* renamed from: i  reason: collision with root package name */
    public String f12259i;
    public String k;
    public long n;

    public j(boolean z, int i2, String str, int i3) {
        super(z, i2);
        this.f12251a = str;
        this.f12252b = i3;
    }

    public j(boolean z, int i2, String str, String str2) {
        super(z, i2);
        int i3;
        try {
            i3 = Integer.parseInt(str2);
        } catch (Exception unused) {
            i3 = -1;
        }
        this.f12251a = str;
        this.f12252b = i3;
    }

    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        this.f12253c = jSONObject2.optString("access_token");
        this.f12254d = jSONObject2.optLong("expires_in");
        this.f12255e = jSONObject2.optString("open_id");
        this.f12256f = jSONObject2.optString("refresh_token");
        this.f12257g = jSONObject2.optLong("refresh_expires_in");
        this.f12258h = jSONObject2.optString(ParamKeyConstants.WebViewConstants.REDIRECT_QUERY_SCOPE);
        this.n = jSONObject2.optLong("refresh_time", this.f12254d);
        this.result = jSONObject;
    }

    public void b(JSONObject jSONObject, JSONObject jSONObject2) {
        this.f12259i = jSONObject2.optString("captcha");
        this.k = jSONObject2.optString("desc_url");
        this.result = jSONObject;
    }

    @Override // com.bytedance.sdk.account.api.call.b
    public String toString() {
        return "ThirdTokenResponse{platformName='" + this.f12251a + "', platformAppId=" + this.f12252b + ", accessToken='" + this.f12253c + "', expiresIn=" + this.f12254d + ", openId='" + this.f12255e + "', refreshToken='" + this.f12256f + "', refreshExpiresIn=" + this.f12257g + ", scopes='" + this.f12258h + "', refreshTime=" + this.n + ", captcha='" + this.f12259i + "', descUrl='" + this.k + "'}";
    }

    public String a() {
        return "ThirdTokenResponse{platformName='" + this.f12251a + "', platformAppId=" + this.f12252b + ", accessToken='" + com.ss.android.account.model2.a.a(this.f12253c) + "', expiresIn=" + this.f12254d + ", openId='" + com.ss.android.account.model2.a.a(this.f12255e) + "', scopes='" + this.f12258h + "'}";
    }
}
