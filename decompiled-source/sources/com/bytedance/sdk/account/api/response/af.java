package com.bytedance.sdk.account.api.response;

import java.util.Map;
/* compiled from: LoginInfoResponse.java */
/* loaded from: classes2.dex */
public class af extends com.bytedance.sdk.account.api.call.b {
    public Map<Integer, com.bytedance.sdk.account.user.d> loginInfoEntityMap;

    public af(boolean z, int i2) {
        super(z, i2);
    }

    @Override // com.bytedance.sdk.account.api.call.b
    public String toString() {
        return "LoginInfoResponse{loginInfoEntityMap=" + this.loginInfoEntityMap + ", success=" + this.success + ", error=" + this.error + ", errorMsg='" + this.errorMsg + "'}";
    }
}
