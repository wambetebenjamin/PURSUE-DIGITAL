package com.bytedance.apm.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bytedance.apm.constant.k;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: AsyncEventManager.java */
/* loaded from: classes.dex */
public class b {
    public static long WAIT_INTERVAL_MS = 30000;

    /* renamed from: e  reason: collision with root package name */
    private static long f1457e = 30000;

    /* renamed from: a  reason: collision with root package name */
    CopyOnWriteArraySet<InterfaceC0021b> f1458a;

    /* renamed from: b  reason: collision with root package name */
    CopyOnWriteArraySet<InterfaceC0021b> f1459b;

    /* renamed from: c  reason: collision with root package name */
    private volatile ExecutorService f1460c;

    /* renamed from: d  reason: collision with root package name */
    private volatile boolean f1461d;

    /* renamed from: f  reason: collision with root package name */
    private com.bytedance.monitor.util.thread.d f1462f;

    /* renamed from: g  reason: collision with root package name */
    private final com.bytedance.monitor.util.thread.e f1463g;

    /* renamed from: h  reason: collision with root package name */
    private final com.bytedance.monitor.util.thread.e f1464h;

    /* compiled from: AsyncEventManager.java */
    /* renamed from: com.bytedance.apm.thread.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0021b {
        void onTimeEvent(long j2);
    }

    private Handler b() {
        return null;
    }

    private b() {
        this.f1461d = true;
        this.f1463g = new com.bytedance.monitor.util.thread.e() { // from class: com.bytedance.apm.thread.b.1
            @Override // com.bytedance.monitor.util.thread.e
            public String getTaskName() {
                return "AsyncEventManager-mTimerRunnable";
            }

            @Override // com.bytedance.monitor.util.thread.e
            public com.bytedance.monitor.util.thread.b getTaskType() {
                return com.bytedance.monitor.util.thread.b.LIGHT_WEIGHT;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<InterfaceC0021b> it = b.this.f1458a.iterator();
                while (it.hasNext()) {
                    it.next().onTimeEvent(System.currentTimeMillis());
                }
                if (b.this.f1461d) {
                    b.this.postDelay((com.bytedance.monitor.util.thread.e) this, b.WAIT_INTERVAL_MS);
                }
            }
        };
        this.f1464h = new com.bytedance.monitor.util.thread.e() { // from class: com.bytedance.apm.thread.b.2
            @Override // com.bytedance.monitor.util.thread.e
            public String getTaskName() {
                return "AsyncEventManager-mControlledTimerRunnable";
            }

            @Override // com.bytedance.monitor.util.thread.e
            public com.bytedance.monitor.util.thread.b getTaskType() {
                return com.bytedance.monitor.util.thread.b.LIGHT_WEIGHT;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<InterfaceC0021b> it = b.this.f1459b.iterator();
                while (it.hasNext()) {
                    it.next().onTimeEvent(System.currentTimeMillis());
                }
                if (b.this.f1461d) {
                    b.this.postDelay((com.bytedance.monitor.util.thread.e) this, b.f1457e);
                }
            }
        };
        this.f1458a = new CopyOnWriteArraySet<>();
        this.f1459b = new CopyOnWriteArraySet<>();
        this.f1462f = com.bytedance.monitor.util.thread.c.getAsyncTaskManagerInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AsyncEventManager.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        static final b f1468a = new b();

        private a() {
        }
    }

    public static b getInstance() {
        return a.f1468a;
    }

    public static void setPollingIntervalMs(long j2) {
        f1457e = Math.max(j2, k.MIN_POLLING_INTERVAL_LIMIT_MS);
    }

    public boolean inWorkThread() {
        return this.f1462f != null && Thread.currentThread().getId() == this.f1462f.getWorkThreadId(com.bytedance.monitor.util.thread.b.LIGHT_WEIGHT);
    }

    public Looper getLooper() {
        if (b() != null) {
            return b().getLooper();
        }
        return null;
    }

    public void sendMessage(Message message) {
        if (b() != null) {
            b().sendMessage(message);
        }
    }

    public void post(Runnable runnable) {
        if (this.f1462f == null || runnable == null || !this.f1461d) {
            return;
        }
        this.f1462f.post(a(runnable, com.bytedance.sdk.account.execute.a.METHOD_POST));
    }

    public void a(Runnable runnable) {
        com.bytedance.monitor.util.thread.d dVar = this.f1462f;
        if (dVar == null || runnable == null) {
            return;
        }
        dVar.post(a(runnable, com.bytedance.sdk.account.execute.a.METHOD_POST));
    }

    public void postDelay(Runnable runnable, long j2) {
        if (this.f1462f == null || runnable == null || !this.f1461d) {
            return;
        }
        this.f1462f.postDelayed(a(runnable, "postDelayed"), j2);
    }

    public void postDelay(com.bytedance.monitor.util.thread.e eVar, long j2) {
        if (this.f1462f == null || eVar == null || !this.f1461d) {
            return;
        }
        this.f1462f.postDelayed(eVar, j2);
    }

    public void a(com.bytedance.monitor.util.thread.e eVar, long j2) {
        com.bytedance.monitor.util.thread.d dVar = this.f1462f;
        if (dVar == null || eVar == null) {
            return;
        }
        dVar.postDelayed(eVar, j2);
    }

    public void removeCallbacks(com.bytedance.monitor.util.thread.e eVar) {
        com.bytedance.monitor.util.thread.d dVar = this.f1462f;
        if (dVar == null || eVar == null) {
            return;
        }
        dVar.removeTask(eVar);
    }

    public void addTimeTask(InterfaceC0021b interfaceC0021b) {
        if (interfaceC0021b != null) {
            try {
                if (!this.f1461d || this.f1458a.contains(interfaceC0021b)) {
                    return;
                }
                this.f1458a.add(interfaceC0021b);
                removeCallbacks(this.f1463g);
                postDelay(this.f1463g, WAIT_INTERVAL_MS);
            } catch (Throwable unused) {
            }
        }
    }

    public void removeTimeTask(InterfaceC0021b interfaceC0021b) {
        if (interfaceC0021b != null) {
            try {
                this.f1458a.remove(interfaceC0021b);
            } catch (Throwable unused) {
            }
        }
    }

    public void addControlledTimeTask(InterfaceC0021b interfaceC0021b) {
        if (interfaceC0021b != null) {
            try {
                if (this.f1461d) {
                    this.f1459b.add(interfaceC0021b);
                    removeCallbacks(this.f1464h);
                    postDelay(this.f1464h, f1457e);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void removeControlledTimeTask(InterfaceC0021b interfaceC0021b) {
        if (interfaceC0021b != null) {
            try {
                this.f1459b.remove(interfaceC0021b);
                if (this.f1459b.isEmpty()) {
                    removeCallbacks(this.f1464h);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void stopTimer() {
        this.f1461d = false;
        removeCallbacks(this.f1463g);
        removeCallbacks(this.f1464h);
    }

    public void resumeTimer() {
        this.f1461d = true;
        if (!this.f1458a.isEmpty()) {
            removeCallbacks(this.f1463g);
            postDelay(this.f1463g, WAIT_INTERVAL_MS);
        }
        if (this.f1459b.isEmpty()) {
            return;
        }
        removeCallbacks(this.f1464h);
        postDelay(this.f1464h, f1457e);
    }

    public void injectExecutor(ExecutorService executorService) {
        this.f1460c = executorService;
        com.bytedance.monitor.util.thread.d dVar = this.f1462f;
        if (dVar != null) {
            dVar.setIOExecutor(executorService);
        }
    }

    public void submitTask(Runnable runnable) {
        if (this.f1460c == null) {
            synchronized (this) {
                if (this.f1460c == null) {
                    com.bytedance.monitor.util.thread.d dVar = this.f1462f;
                    if (dVar != null) {
                        this.f1460c = dVar.getIOExecutor();
                    } else {
                        this.f1460c = Executors.newFixedThreadPool(1, new ThreadFactory() { // from class: com.bytedance.apm.thread.b.3
                            @Override // java.util.concurrent.ThreadFactory
                            public Thread newThread(Runnable runnable2) {
                                return new Thread(runnable2, "Apm_Normal");
                            }
                        });
                    }
                }
            }
        }
        this.f1460c.submit(runnable);
    }

    private com.bytedance.monitor.util.thread.e a(Runnable runnable, String str) {
        return com.bytedance.monitor.util.thread.c.wrapLightWeightTask("AsyncEventManager-" + str, runnable);
    }
}
