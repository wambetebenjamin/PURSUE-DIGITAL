package com.bytedance.apm.block.trace;

import com.bytedance.services.apm.api.IActivityLifeObserver;
/* compiled from: ITracer.java */
/* loaded from: classes.dex */
public interface d extends IActivityLifeObserver {
    boolean isAlive();

    void onCloseTrace();

    void onStartTrace();
}
