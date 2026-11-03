package com.bytedance.sync.v2.intf;

import com.bytedance.sync.v2.net.j;
/* compiled from: IWsStatusService.java */
/* loaded from: classes3.dex */
public interface k extends com.ss.android.ug.bus.a {
    void addWsStatusChangedListener(j.a aVar);

    com.bytedance.common.wschannel.event.b getCurrentStatus();

    boolean isConnect();

    void removeWsStatusChangedListener(j.a aVar);
}
