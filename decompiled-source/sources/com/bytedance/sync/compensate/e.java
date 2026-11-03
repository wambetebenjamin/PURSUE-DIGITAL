package com.bytedance.sync.compensate;
/* compiled from: ICompensator.java */
/* loaded from: classes3.dex */
interface e {
    void destroy();

    void onSettingsUpdate(com.bytedance.sync.settings.a aVar);

    void start(com.bytedance.sync.settings.a aVar, boolean z);

    void switchToPoll();
}
