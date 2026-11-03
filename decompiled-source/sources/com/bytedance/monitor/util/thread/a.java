package com.bytedance.monitor.util.thread;

import com.bytedance.monitor.util.thread.d;
import com.bytedance.monitor.util.thread.inner.b;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
/* compiled from: AsyncTaskManager.java */
/* loaded from: classes2.dex */
public class a implements d {
    public static final String TAG = "AsyncTaskManager";

    /* renamed from: b  reason: collision with root package name */
    private static final int f9684b = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final int f9685c = 1;

    /* renamed from: d  reason: collision with root package name */
    private static final int f9686d = 1;

    /* renamed from: e  reason: collision with root package name */
    private static final String f9687e = "io-task";

    /* renamed from: f  reason: collision with root package name */
    private static final String f9688f = "light-weight-task";

    /* renamed from: g  reason: collision with root package name */
    private static final String f9689g = "time-sensitive-task";

    /* renamed from: h  reason: collision with root package name */
    private volatile com.bytedance.monitor.util.thread.inner.c f9690h;

    /* renamed from: i  reason: collision with root package name */
    private volatile com.bytedance.monitor.util.thread.inner.c f9691i;

    /* renamed from: j  reason: collision with root package name */
    private volatile com.bytedance.monitor.util.thread.inner.c f9692j;
    private Map<b, Long> k = new ConcurrentHashMap(3);
    private f l;
    private d.a m;

    /* renamed from: a  reason: collision with root package name */
    private static final Object f9683a = new Object();
    private static final Object n = new Object();
    private static final Object o = new Object();

    /* compiled from: AsyncTaskManager.java */
    /* renamed from: com.bytedance.monitor.util.thread.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static final class C0138a {

        /* renamed from: a  reason: collision with root package name */
        static final a f9696a = new a();

        private C0138a() {
        }
    }

    public static a getInstance() {
        return C0138a.f9696a;
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void post(e eVar) {
        if (eVar == null) {
            return;
        }
        try {
            a(eVar).post(eVar);
        } catch (Throwable th) {
            directReportError(th, "Apm-Async-task-post");
        }
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void postDelayed(e eVar, long j2) {
        if (eVar == null) {
            return;
        }
        try {
            a(eVar).postDelayed(eVar, j2);
        } catch (Throwable th) {
            directReportError(th, "Apm-Async-task-postDelayed");
        }
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void removeTask(e eVar) {
        if (eVar == null) {
            return;
        }
        try {
            a(eVar).removeTask(eVar);
        } catch (Throwable th) {
            directReportError(th, "Apm-Async-task-removeTask");
        }
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void scheduleWithFixedDelay(e eVar, long j2, long j3) {
        if (eVar == null) {
            return;
        }
        try {
            a(eVar).scheduleWithFixedDelay(eVar, j2, j3);
        } catch (Throwable th) {
            directReportError(th, "Apm-Async-task-removeTask");
        }
    }

    @Override // com.bytedance.monitor.util.thread.d
    public ExecutorService getIOExecutor() {
        return getIOTaskExecutor();
    }

    @Override // com.bytedance.monitor.util.thread.d
    public f getThreadLogListener() {
        return this.l;
    }

    @Override // com.bytedance.monitor.util.thread.d
    public long getWorkThreadId(b bVar) {
        Long l = this.k.get(bVar);
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }

    @Override // com.bytedance.monitor.util.thread.d
    public void setTaskOverTime(b bVar, int i2) {
        if (bVar == b.IO) {
            getIOTaskExecutor().setTaskOverTime(i2);
        } else if (bVar == b.TIME_SENSITIVE) {
            getTimeSensitiveHandlerThread().setTaskOverTime(i2);
        } else {
            getLightWeightHandlerThread().setTaskOverTime(i2);
        }
    }

    @Override // com.bytedance.monitor.util.thread.d
    public void directReportError(Throwable th, String str) {
        d.a aVar = this.m;
        if (aVar != null) {
            aVar.directReportError(th, str);
        }
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void release() {
        try {
            getIOTaskExecutor().release();
            getLightWeightHandlerThread().release();
            getTimeSensitiveHandlerThread().release();
        } catch (Throwable th) {
            directReportError(th, "Apm-Async-task-release");
        }
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public void setThreadLogListener(f fVar) {
        this.l = fVar;
        getIOTaskExecutor().setThreadLogListener(fVar);
        getLightWeightHandlerThread().setThreadLogListener(fVar);
        getTimeSensitiveHandlerThread().setThreadLogListener(fVar);
    }

    @Override // com.bytedance.monitor.util.thread.inner.d
    public boolean isPending(e eVar) {
        try {
            return a(eVar).isPending(eVar);
        } catch (Throwable th) {
            directReportError(th, "Apm-Async-task-isPending");
            return false;
        }
    }

    @Override // com.bytedance.monitor.util.thread.d
    public void setIOExecutor(ExecutorService executorService) {
        getIOTaskExecutor().setOuterExecutor(executorService);
    }

    private com.bytedance.monitor.util.thread.inner.d a(e eVar) {
        b taskType = eVar.getTaskType();
        if (taskType == b.IO) {
            return getIOTaskExecutor();
        }
        if (taskType == b.TIME_SENSITIVE) {
            return getTimeSensitiveHandlerThread();
        }
        return getLightWeightHandlerThread();
    }

    public com.bytedance.monitor.util.thread.inner.c getLightWeightHandlerThread() {
        if (this.f9691i == null) {
            synchronized (n) {
                if (this.f9691i == null) {
                    b(this.l);
                }
            }
        }
        return this.f9691i;
    }

    public com.bytedance.monitor.util.thread.inner.c getIOTaskExecutor() {
        if (this.f9690h == null) {
            synchronized (f9683a) {
                if (this.f9690h == null) {
                    a(this.l);
                }
            }
        }
        return this.f9690h;
    }

    public com.bytedance.monitor.util.thread.inner.c getTimeSensitiveHandlerThread() {
        if (this.f9692j == null) {
            synchronized (o) {
                if (this.f9692j == null) {
                    c(this.l);
                }
            }
        }
        return this.f9692j;
    }

    private void a(f fVar) {
        synchronized (f9683a) {
            if (this.f9690h == null) {
                com.bytedance.monitor.util.thread.inner.b bVar = new com.bytedance.monitor.util.thread.inner.b(f9687e);
                bVar.setLogListener(fVar);
                bVar.setThreadIdCallback(new b.a() { // from class: com.bytedance.monitor.util.thread.a.1
                    @Override // com.bytedance.monitor.util.thread.inner.b.a
                    public void onTid(long j2) {
                        a.this.k.put(b.IO, Long.valueOf(j2));
                    }
                });
                com.bytedance.monitor.util.thread.inner.c cVar = new com.bytedance.monitor.util.thread.inner.c(1, bVar);
                cVar.setThreadLogListener(fVar);
                this.f9690h = cVar;
            }
        }
    }

    private void b(f fVar) {
        synchronized (n) {
            if (this.f9691i == null) {
                com.bytedance.monitor.util.thread.inner.b bVar = new com.bytedance.monitor.util.thread.inner.b(f9688f);
                bVar.setLogListener(fVar);
                bVar.setThreadIdCallback(new b.a() { // from class: com.bytedance.monitor.util.thread.a.2
                    @Override // com.bytedance.monitor.util.thread.inner.b.a
                    public void onTid(long j2) {
                        a.this.k.put(b.LIGHT_WEIGHT, Long.valueOf(j2));
                    }
                });
                com.bytedance.monitor.util.thread.inner.c cVar = new com.bytedance.monitor.util.thread.inner.c(1, bVar);
                cVar.setThreadLogListener(fVar);
                this.f9691i = cVar;
            }
        }
    }

    private void c(f fVar) {
        synchronized (o) {
            if (this.f9692j == null) {
                com.bytedance.monitor.util.thread.inner.b bVar = new com.bytedance.monitor.util.thread.inner.b(f9689g);
                bVar.setLogListener(fVar);
                bVar.setThreadIdCallback(new b.a() { // from class: com.bytedance.monitor.util.thread.a.3
                    @Override // com.bytedance.monitor.util.thread.inner.b.a
                    public void onTid(long j2) {
                        a.this.k.put(b.TIME_SENSITIVE, Long.valueOf(j2));
                    }
                });
                com.bytedance.monitor.util.thread.inner.c cVar = new com.bytedance.monitor.util.thread.inner.c(1, bVar);
                cVar.setThreadLogListener(fVar);
                this.f9692j = cVar;
            }
        }
    }

    @Override // com.bytedance.monitor.util.thread.d
    public void setExceptionListener(d.a aVar) {
        this.m = aVar;
    }
}
