package com.ss.android;

import com.bytedance.sdk.account.n;
/* compiled from: AbsTTAccountConfig.java */
/* loaded from: classes9.dex */
public abstract class a implements f {

    /* renamed from: a  reason: collision with root package name */
    private n f17880a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.sdk.account.utils.d f17881b;

    @Override // com.ss.android.f
    public boolean isSecureCaptchaEnabled() {
        return false;
    }

    @Override // com.ss.android.f
    public n getNetwork() {
        n nVar = this.f17880a;
        if (nVar != null) {
            return nVar;
        }
        e.b("AbsTTAccountConfig", "call getNetwork");
        try {
            n nVar2 = (n) Class.forName("com.ss.android.account.adapter.NetworkAdapter").newInstance();
            this.f17880a = nVar2;
            return nVar2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.ss.android.f
    public com.bytedance.sdk.account.utils.d getMonitor() {
        com.bytedance.sdk.account.utils.d dVar = this.f17881b;
        if (dVar != null) {
            return dVar;
        }
        e.b("AbsTTAccountConfig", "call getMonitor");
        try {
            com.bytedance.sdk.account.utils.d dVar2 = (com.bytedance.sdk.account.utils.d) Class.forName("com.ss.android.account.adapter.MonitorAdapter").newInstance();
            this.f17881b = dVar2;
            return dVar2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
