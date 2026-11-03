package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.C0392r;
/* compiled from: AuthCodeAccessTokenResponse.java */
/* loaded from: classes2.dex */
public class as extends com.bytedance.sdk.account.api.call.b {

    /* renamed from: a  reason: collision with root package name */
    public String f12275a;

    /* renamed from: b  reason: collision with root package name */
    public long f12276b;

    /* renamed from: c  reason: collision with root package name */
    public String f12277c;

    /* renamed from: d  reason: collision with root package name */
    public long f12278d;

    /* renamed from: e  reason: collision with root package name */
    public String f12279e;

    /* renamed from: f  reason: collision with root package name */
    public String f12280f;

    public as(boolean z) {
        super(z, C0392r.r);
    }

    @Override // com.bytedance.sdk.account.api.call.b
    public String toString() {
        return "AuthCodeAccessTokenResponse{accessToken='" + this.f12275a + "', expiresIn=" + this.f12276b + ", refreshToken='" + this.f12277c + "', refreshExpiresIn=" + this.f12278d + ", openId='" + this.f12279e + "', scopes='" + this.f12280f + "'}";
    }
}
