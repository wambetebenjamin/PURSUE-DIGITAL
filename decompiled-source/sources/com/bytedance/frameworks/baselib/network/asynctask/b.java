package com.bytedance.frameworks.baselib.network.asynctask;

import com.bytedance.common.utility.Logger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
/* compiled from: NetworkAsyncTaskManager.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7409a = "b";

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentHashMap<c, b> f7410b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private final ConcurrentHashMap<com.bytedance.frameworks.baselib.network.asynctask.a, ScheduledFuture> f7411c = new ConcurrentHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private final ConcurrentHashMap<com.bytedance.frameworks.baselib.network.asynctask.a, Runnable> f7412d = new ConcurrentHashMap<>();

    /* renamed from: e  reason: collision with root package name */
    private final c f7413e;

    /* renamed from: f  reason: collision with root package name */
    private final ScheduledThreadPoolExecutor f7414f;

    public static b a(c cVar) {
        if (cVar == null) {
            throw new IllegalArgumentException("unsupported async task type");
        }
        ConcurrentHashMap<c, b> concurrentHashMap = f7410b;
        if (concurrentHashMap.get(cVar) == null) {
            synchronized (b.class) {
                if (concurrentHashMap.get(cVar) == null) {
                    concurrentHashMap.put(cVar, new b(cVar));
                }
            }
        }
        return concurrentHashMap.get(cVar);
    }

    private b(c cVar) {
        this.f7413e = cVar;
        this.f7414f = new ScheduledThreadPoolExecutor(1, new d(cVar.name()));
    }

    public ConcurrentHashMap<?, ?> a() {
        return this.f7412d;
    }

    public ConcurrentHashMap<?, ?> b() {
        return this.f7411c;
    }

    public void a(com.bytedance.frameworks.baselib.network.asynctask.a aVar) {
        ScheduledFuture<?> schedule;
        if (aVar == null) {
            return;
        }
        aVar.setTaskType(this.f7413e);
        try {
            a aVar2 = new a(aVar);
            if (aVar.isLoop()) {
                schedule = this.f7414f.scheduleWithFixedDelay(aVar2, aVar.getInitialDelay(), aVar.getLoopInterval(), aVar.getTimeUnit());
            } else {
                schedule = this.f7414f.schedule(aVar2, aVar.getInitialDelay(), aVar.getTimeUnit());
            }
            this.f7412d.put(aVar, aVar2);
            this.f7411c.put(aVar, schedule);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b(com.bytedance.frameworks.baselib.network.asynctask.a aVar) {
        try {
            Runnable remove = this.f7412d.remove(aVar);
            if (remove != null) {
                this.f7414f.remove(remove);
            }
            ScheduledFuture remove2 = this.f7411c.remove(aVar);
            if (remove2 != null) {
                remove2.cancel(true);
            }
        } catch (Throwable th) {
            Logger.e(f7409a, "removeTask failed", th);
        }
    }

    /* compiled from: NetworkAsyncTaskManager.java */
    /* loaded from: classes2.dex */
    private static class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final com.bytedance.frameworks.baselib.network.asynctask.a f7415a;

        private a(com.bytedance.frameworks.baselib.network.asynctask.a aVar) {
            this.f7415a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.f7415a.run();
                if (Logger.debug()) {
                    Logger.d(b.f7409a, "Task execute time: " + (System.currentTimeMillis() - currentTimeMillis));
                }
            } catch (Throwable th) {
                try {
                    Logger.e(b.f7409a, "thread " + Thread.currentThread().getName() + " exception", th);
                    if (this.f7415a.isLoop()) {
                    }
                } finally {
                    if (!this.f7415a.isLoop()) {
                        b.a(this.f7415a.getTaskType()).a().remove(this.f7415a);
                        b.a(this.f7415a.getTaskType()).b().remove(this.f7415a);
                    }
                }
            }
        }
    }
}
