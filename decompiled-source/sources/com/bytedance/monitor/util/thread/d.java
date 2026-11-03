package com.bytedance.monitor.util.thread;

import java.util.concurrent.ExecutorService;
/* compiled from: IAsyncTaskManager.java */
/* loaded from: classes2.dex */
public interface d extends com.bytedance.monitor.util.thread.inner.d {

    /* compiled from: IAsyncTaskManager.java */
    /* loaded from: classes2.dex */
    public interface a {
        void directReportError(Throwable th, String str);
    }

    void directReportError(Throwable th, String str);

    ExecutorService getIOExecutor();

    f getThreadLogListener();

    long getWorkThreadId(b bVar);

    void setExceptionListener(a aVar);

    void setIOExecutor(ExecutorService executorService);

    void setTaskOverTime(b bVar, int i2);
}
