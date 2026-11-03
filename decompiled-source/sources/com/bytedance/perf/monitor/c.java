package com.bytedance.perf.monitor;

import android.os.Looper;
import android.os.Message;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: AsyncEventManager.java */
/* loaded from: classes2.dex */
public class c {
    public static final String ASYNC_EVENT_MANAGER_THREAD = "AsyncEventManager-Thread";
    public static long WAIT_INTERVAL_MS = 30000;

    /* renamed from: c  reason: collision with root package name */
    private static final long f9966c = 5000;

    /* renamed from: g  reason: collision with root package name */
    private static long f9967g = 30000;

    /* renamed from: a  reason: collision with root package name */
    CopyOnWriteArraySet<b> f9968a;

    /* renamed from: b  reason: collision with root package name */
    CopyOnWriteArraySet<b> f9969b;

    /* renamed from: d  reason: collision with root package name */
    private volatile ExecutorService f9970d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.apm.thread.d f9971e;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f9972f;

    /* renamed from: h  reason: collision with root package name */
    private final Runnable f9973h;

    /* renamed from: i  reason: collision with root package name */
    private final Runnable f9974i;

    /* compiled from: AsyncEventManager.java */
    /* loaded from: classes2.dex */
    public interface b {
        void onTimeEvent(long j2);
    }

    private c() {
        this.f9972f = true;
        this.f9973h = new Runnable() { // from class: com.bytedance.perf.monitor.c.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<b> it = c.this.f9968a.iterator();
                while (it.hasNext()) {
                    it.next().onTimeEvent(System.currentTimeMillis());
                }
                if (c.this.f9972f) {
                    c.this.f9971e.postDelayed(this, c.WAIT_INTERVAL_MS);
                }
            }
        };
        this.f9974i = new Runnable() { // from class: com.bytedance.perf.monitor.c.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<b> it = c.this.f9969b.iterator();
                while (it.hasNext()) {
                    it.next().onTimeEvent(System.currentTimeMillis());
                }
                if (c.this.f9972f) {
                    c.this.f9971e.postDelayed(this, c.f9967g);
                }
            }
        };
        this.f9968a = new CopyOnWriteArraySet<>();
        this.f9969b = new CopyOnWriteArraySet<>();
        com.bytedance.apm.thread.d dVar = new com.bytedance.apm.thread.d("AsyncEventManager-Thread");
        this.f9971e = dVar;
        dVar.start();
    }

    /* compiled from: AsyncEventManager.java */
    /* loaded from: classes2.dex */
    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        static final c f9978a = new c();

        private a() {
        }
    }

    public static c getInstance() {
        return a.f9978a;
    }

    public static void setPollingIntervalMs(long j2) {
        f9967g = Math.max(j2, 5000L);
    }

    public Looper getLooper() {
        com.bytedance.apm.thread.d dVar = this.f9971e;
        if (dVar != null) {
            return dVar.getLooper();
        }
        return null;
    }

    public void sendMessage(Message message) {
        this.f9971e.sendMessage(message);
    }

    public void post(Runnable runnable) {
        if (runnable == null || !this.f9972f) {
            return;
        }
        this.f9971e.post(runnable);
    }

    public void postDelay(Runnable runnable, long j2) {
        if (runnable == null || !this.f9972f) {
            return;
        }
        this.f9971e.postDelayed(runnable, j2);
    }

    public void removeCallbacks(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.f9971e.removeCallbacks(runnable);
    }

    public void addTimeTask(b bVar) {
        if (bVar != null) {
            try {
                if (!this.f9972f || this.f9968a.contains(bVar)) {
                    return;
                }
                this.f9968a.add(bVar);
                this.f9971e.removeCallbacks(this.f9973h);
                this.f9971e.postDelayed(this.f9973h, WAIT_INTERVAL_MS);
            } catch (Throwable unused) {
            }
        }
    }

    public void removeTimeTask(b bVar) {
        if (bVar != null) {
            try {
                this.f9968a.remove(bVar);
            } catch (Throwable unused) {
            }
        }
    }

    public void addControlledTimeTask(b bVar) {
        if (bVar != null) {
            try {
                if (this.f9972f) {
                    this.f9969b.add(bVar);
                    this.f9971e.removeCallbacks(this.f9974i);
                    this.f9971e.postDelayed(this.f9974i, f9967g);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void removeControlledTimeTask(b bVar) {
        if (bVar != null) {
            try {
                this.f9969b.remove(bVar);
                if (this.f9969b.isEmpty()) {
                    this.f9971e.removeCallbacks(this.f9974i);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void stopTimer() {
        this.f9972f = false;
        com.bytedance.apm.thread.d dVar = this.f9971e;
        if (dVar != null) {
            dVar.removeCallbacks(this.f9973h);
            this.f9971e.removeCallbacks(this.f9974i);
        }
    }

    public void resumeTimer() {
        this.f9972f = true;
        if (this.f9971e != null && !this.f9968a.isEmpty()) {
            this.f9971e.removeCallbacks(this.f9973h);
            this.f9971e.postDelayed(this.f9973h, WAIT_INTERVAL_MS);
        }
        if (this.f9971e == null || this.f9969b.isEmpty()) {
            return;
        }
        this.f9971e.removeCallbacks(this.f9974i);
        this.f9971e.postDelayed(this.f9974i, f9967g);
    }

    public void injectExecutor(ExecutorService executorService) {
        this.f9970d = executorService;
    }

    public void submitTask(Runnable runnable) {
        if (this.f9970d == null) {
            synchronized (this) {
                if (this.f9970d == null) {
                    this.f9970d = Executors.newFixedThreadPool(1, new ThreadFactory() { // from class: com.bytedance.perf.monitor.c.3
                        @Override // java.util.concurrent.ThreadFactory
                        public Thread newThread(Runnable runnable2) {
                            return new Thread(runnable2, "Apm_Normal");
                        }
                    });
                }
            }
        }
        this.f9970d.submit(runnable);
    }
}
