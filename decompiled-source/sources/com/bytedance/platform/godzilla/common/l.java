package com.bytedance.platform.godzilla.common;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: SerialExecutor.java */
/* loaded from: classes2.dex */
public final class l implements Executor {

    /* renamed from: b  reason: collision with root package name */
    private static l f11062b;

    /* renamed from: d  reason: collision with root package name */
    private Runnable f11065d;

    /* renamed from: c  reason: collision with root package name */
    private Executor f11064c = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.platform.godzilla.common.l.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "godzilla_serialExecutor");
        }
    });

    /* renamed from: a  reason: collision with root package name */
    final ArrayDeque<Runnable> f11063a = new ArrayDeque<>();

    private l() {
    }

    public static l getInstance() {
        if (f11062b == null) {
            synchronized (l.class) {
                if (f11062b == null) {
                    f11062b = new l();
                }
            }
        }
        return f11062b;
    }

    public void setExecutor(Executor executor) {
        this.f11064c = executor;
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(final Runnable runnable) {
        this.f11063a.offer(new Runnable() { // from class: com.bytedance.platform.godzilla.common.l.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable unused) {
                }
                l.this.a();
            }
        });
        if (this.f11065d == null) {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        Runnable poll = this.f11063a.poll();
        this.f11065d = poll;
        if (poll != null) {
            this.f11064c.execute(poll);
        }
    }
}
