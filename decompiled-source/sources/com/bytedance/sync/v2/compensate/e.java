package com.bytedance.sync.v2.compensate;

import com.bytedance.sync.v2.net.g;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ICompensator.java */
/* loaded from: classes3.dex */
public interface e extends g.d {
    void destroy();

    void onSettingsUpdate(com.bytedance.sync.settings.b bVar);

    void resetPollingInterval(com.bytedance.sync.v2.protocal.e eVar);

    void start(com.bytedance.sync.settings.b bVar, boolean z);

    void switchToPoll();
}
