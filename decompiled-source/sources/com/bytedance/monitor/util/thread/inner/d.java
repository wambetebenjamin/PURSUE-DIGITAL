package com.bytedance.monitor.util.thread.inner;

import com.bytedance.monitor.util.thread.e;
import com.bytedance.monitor.util.thread.f;
/* compiled from: IAsyncTaskHandler.java */
/* loaded from: classes2.dex */
public interface d {
    boolean isPending(e eVar);

    void post(e eVar);

    void postDelayed(e eVar, long j2);

    void release();

    void removeTask(e eVar);

    void scheduleWithFixedDelay(e eVar, long j2, long j3);

    void setThreadLogListener(f fVar);
}
