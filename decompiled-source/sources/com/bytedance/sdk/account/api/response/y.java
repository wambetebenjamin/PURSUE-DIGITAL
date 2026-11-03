package com.bytedance.sdk.account.api.response;
/* compiled from: GetAccountInfoResponse.java */
/* loaded from: classes2.dex */
public class y extends com.bytedance.sdk.account.api.call.h implements com.bytedance.sdk.account.mobile.query.j {
    public String errorName;

    public y(boolean z, int i2) {
        super(z, i2);
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.userInfo;
    }
}
