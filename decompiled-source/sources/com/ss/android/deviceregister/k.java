package com.ss.android.deviceregister;

import com.bytedance.bdinstall.intf.c;
/* compiled from: IAdIdConfig.java */
/* loaded from: classes9.dex */
public interface k {
    boolean enablePrefetchAdId();

    long getAdIdWaitTime();

    /* compiled from: IAdIdConfig.java */
    /* loaded from: classes9.dex */
    public static class a implements k {

        /* renamed from: a  reason: collision with root package name */
        com.bytedance.bdinstall.intf.c f18221a = new c.a();

        @Override // com.ss.android.deviceregister.k
        public long getAdIdWaitTime() {
            return this.f18221a.a();
        }

        @Override // com.ss.android.deviceregister.k
        public boolean enablePrefetchAdId() {
            return this.f18221a.b();
        }
    }
}
