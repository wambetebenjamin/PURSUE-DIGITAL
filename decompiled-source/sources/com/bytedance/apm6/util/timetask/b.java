package com.bytedance.apm6.util.timetask;

import com.bytedance.apm6.util.l;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: AsyncTaskManager.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static Map<c, b> f2416a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private ScheduledThreadPoolExecutor f2417b;

    /* renamed from: c  reason: collision with root package name */
    private ConcurrentHashMap<com.bytedance.apm6.util.timetask.a, ScheduledFuture> f2418c = new ConcurrentHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private ConcurrentHashMap<com.bytedance.apm6.util.timetask.a, Runnable> f2419d = new ConcurrentHashMap<>();

    private b(String str) {
        this.f2417b = new ScheduledThreadPoolExecutor(1, new e(str));
    }

    public static synchronized b getInstance(c cVar) {
        b bVar;
        synchronized (b.class) {
            if (cVar == null) {
                throw new IllegalArgumentException("AsyncTaskManagerType must be not null!");
            }
            bVar = f2416a.get(cVar);
            if (bVar == null) {
                bVar = new b(cVar.name());
                f2416a.put(cVar, bVar);
            }
        }
        return bVar;
    }

    public void sendTask(com.bytedance.apm6.util.timetask.a aVar) {
        ScheduledFuture<?> schedule;
        if (aVar == null) {
            return;
        }
        try {
            a aVar2 = new a(aVar);
            if (aVar.isLoop()) {
                schedule = this.f2417b.scheduleWithFixedDelay(aVar2, aVar.getInitialDelay(), aVar.getLoopInterval(), TimeUnit.MILLISECONDS);
            } else {
                schedule = this.f2417b.schedule(aVar2, aVar.getInitialDelay(), TimeUnit.MILLISECONDS);
            }
            this.f2419d.put(aVar, aVar2);
            this.f2418c.put(aVar, schedule);
        } catch (Throwable th) {
            com.bytedance.apm6.util.log.b.e(d.TAG, "sendTask failed.", th);
        }
    }

    public void removeTask(com.bytedance.apm6.util.timetask.a aVar) {
        try {
            Runnable remove = this.f2419d.remove(aVar);
            if (remove != null) {
                this.f2417b.remove(remove);
            }
            ScheduledFuture remove2 = this.f2418c.remove(aVar);
            if (remove2 != null) {
                remove2.cancel(true);
            }
        } catch (Throwable th) {
            com.bytedance.apm6.util.log.b.e(d.TAG, "removeTask failed", th);
        }
    }

    /* compiled from: AsyncTaskManager.java */
    /* loaded from: classes.dex */
    private class a implements Runnable {

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.apm6.util.timetask.a f2421b;

        private a(com.bytedance.apm6.util.timetask.a aVar) {
            this.f2421b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.beginSection("APM-Task");
            try {
                this.f2421b.run();
            } catch (Throwable th) {
                com.bytedance.apm6.util.log.b.e(d.TAG, "thread " + Thread.currentThread().getName() + " exception", th);
            }
            l.endSection();
        }
    }
}
