package com.bytedance.sync.interfaze;

import com.bytedance.sync.user.a;
import com.ss.android.ug.bus.c;
/* compiled from: IDeviceInfoGetter.java */
/* loaded from: classes3.dex */
public interface e extends com.ss.android.ug.bus.a {
    void addAccountLoginOrSwitchEventListener(c.a<Void> aVar);

    void addDeviceInfoChangedListener(c.a<Void> aVar);

    a.C0209a getDeviceInfo();

    void removeAccountLoginOrSwitchEventListener(c.a<Void> aVar);

    void startListenAccountChangeEvent();

    void updateDid(String str);
}
