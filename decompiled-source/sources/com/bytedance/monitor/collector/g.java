package com.bytedance.monitor.collector;

import android.content.Context;
/* compiled from: IProfilerMonitor.java */
/* loaded from: classes2.dex */
public interface g {
    String dumpStack(long j2, long j3);

    void enableEvilMethodCollect(boolean z);

    void init(Context context, long j2);

    void start();

    void stop();
}
