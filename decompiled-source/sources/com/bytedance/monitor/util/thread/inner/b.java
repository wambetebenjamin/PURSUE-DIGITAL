package com.bytedance.monitor.util.thread.inner;

import android.os.Process;
import com.bytedance.monitor.util.thread.f;
import java.util.concurrent.ThreadFactory;
/* compiled from: ApmInnerThreadFactory.java */
/* loaded from: classes2.dex */
public class b implements ThreadFactory {
    public final String TAG = getClass().getSimpleName();

    /* renamed from: a  reason: collision with root package name */
    private final String f9701a;

    /* renamed from: b  reason: collision with root package name */
    private f f9702b;

    /* renamed from: c  reason: collision with root package name */
    private a f9703c;

    /* compiled from: ApmInnerThreadFactory.java */
    /* loaded from: classes2.dex */
    public interface a {
        void onTid(long j2);
    }

    public b(String str) {
        this.f9701a = "APM_" + str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        return new Thread(new Runnable() { // from class: com.bytedance.monitor.util.thread.inner.b.1
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                if (b.this.f9703c != null) {
                    b.this.f9703c.onTid(Thread.currentThread().getId());
                }
                try {
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                } catch (Throwable th) {
                    com.bytedance.monitor.util.thread.c.getAsyncTaskManagerInstance().directReportError(th, "APM_INNER_ERROR_async_task");
                    if (b.this.f9702b != null) {
                        b.this.f9702b.onError(b.this.TAG, th.getMessage());
                    }
                }
            }
        }, this.f9701a);
    }

    public void setLogListener(f fVar) {
        this.f9702b = fVar;
    }

    public void setThreadIdCallback(a aVar) {
        this.f9703c = aVar;
    }
}
