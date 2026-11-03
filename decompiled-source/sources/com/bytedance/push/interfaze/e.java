package com.bytedance.push.interfaze;

import com.ss.android.ug.bus.c;
import java.util.List;
/* compiled from: IAccountService.java */
/* loaded from: classes2.dex */
public interface e extends com.ss.android.ug.bus.a {
    List<String> getLoginHistoryUids();

    String getSecUid();

    void registerOnAccountSwitchListener(c.a<com.ss.android.ug.bus.account.event.c> aVar);

    void registerOnLoginListener(c.a<com.ss.android.ug.bus.account.event.a> aVar);

    void registerOnLogoutListener(c.a<com.ss.android.ug.bus.account.event.b> aVar);
}
