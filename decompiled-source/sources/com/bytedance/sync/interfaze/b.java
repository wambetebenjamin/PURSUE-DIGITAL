package com.bytedance.sync.interfaze;

import com.ss.android.ug.bus.c;
/* compiled from: IAccountService.java */
/* loaded from: classes3.dex */
public interface b {
    String getSecUid();

    void registerOnAccountSwitchListener(c.a<com.ss.android.ug.bus.account.event.c> aVar);

    void registerOnLoginListener(c.a<com.ss.android.ug.bus.account.event.a> aVar);

    void registerOnLogoutListener(c.a<com.ss.android.ug.bus.account.event.b> aVar);
}
