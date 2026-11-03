package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.platform.base.d;
/* compiled from: AuthorizeAdapter.java */
/* loaded from: classes2.dex */
public abstract class a implements com.bytedance.sdk.account.platform.base.b {

    /* renamed from: a  reason: collision with root package name */
    private String f12712a;

    public abstract void onAuthError(d dVar);

    public abstract void onAuthSuccess(Bundle bundle);

    public a(String str) {
        this.f12712a = str;
    }

    @Override // com.bytedance.sdk.account.platform.base.b
    public void onSuccess(Bundle bundle) {
        com.bytedance.sdk.account.monitor.b.platformAuthEvent(this.f12712a, "auth", 1, null, null, false, null);
        onAuthSuccess(bundle);
    }

    @Override // com.bytedance.sdk.account.platform.base.b
    public void onError(d dVar) {
        com.bytedance.sdk.account.monitor.b.platformAuthEvent(this.f12712a, "auth", 0, dVar.platformErrorCode, dVar.platformErrorMsg, dVar.isCancel, null);
        onAuthError(dVar);
    }
}
