package com.bytedance.sync.user;

import com.ss.android.ug.bus.c;
/* compiled from: AccountSDKImpl.java */
/* loaded from: classes3.dex */
public class b implements com.bytedance.sync.interfaze.b {
    @Override // com.bytedance.sync.interfaze.b
    public void registerOnLoginListener(c.a<com.ss.android.ug.bus.account.event.a> aVar) {
        c.register(aVar);
    }

    @Override // com.bytedance.sync.interfaze.b
    public void registerOnLogoutListener(c.a<com.ss.android.ug.bus.account.event.b> aVar) {
        c.register(aVar);
    }

    @Override // com.bytedance.sync.interfaze.b
    public void registerOnAccountSwitchListener(c.a<com.ss.android.ug.bus.account.event.c> aVar) {
        c.register(aVar);
    }

    @Override // com.bytedance.sync.interfaze.b
    public String getSecUid() {
        com.ss.android.ug.bus.account.a aVar = (com.ss.android.ug.bus.account.a) com.ss.android.ug.bus.b.getService(com.ss.android.ug.bus.account.a.class);
        if (aVar == null) {
            return null;
        }
        return aVar.getSecUid();
    }
}
