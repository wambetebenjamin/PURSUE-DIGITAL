package com.bytedance.applog.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: AppLogExecutors.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2563a;

    /* renamed from: b  reason: collision with root package name */
    public static final int f2564b;

    /* renamed from: c  reason: collision with root package name */
    public static final int f2565c;

    /* renamed from: d  reason: collision with root package name */
    public static final int f2566d;

    /* renamed from: e  reason: collision with root package name */
    public static final int f2567e = 30;

    /* renamed from: f  reason: collision with root package name */
    private static final BlockingQueue<Runnable> f2568f;

    /* renamed from: g  reason: collision with root package name */
    private static final ThreadFactoryC0037a f2569g;

    /* renamed from: h  reason: collision with root package name */
    private static final RejectedExecutionHandler f2570h;

    /* renamed from: i  reason: collision with root package name */
    private static final com.bytedance.applog.log.a<b> f2571i;

    /* renamed from: j  reason: collision with root package name */
    private static final com.bytedance.applog.log.a<b> f2572j;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f2563a = availableProcessors;
        if (availableProcessors <= 0) {
            availableProcessors = 1;
        }
        f2564b = availableProcessors;
        int max = Math.max(2, Math.min(availableProcessors - 1, 6)) * 2;
        f2565c = max;
        f2566d = (max * 2) + 1;
        f2568f = new LinkedBlockingQueue();
        f2569g = new ThreadFactoryC0037a("AppLogDefaultExecutors");
        f2570h = new RejectedExecutionHandler() { // from class: com.bytedance.applog.concurrent.a.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Executors.newCachedThreadPool().execute(runnable);
            }
        };
        f2571i = new com.bytedance.applog.log.a<b>() { // from class: com.bytedance.applog.concurrent.a.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.bytedance.applog.log.a
            /* renamed from: a */
            public b create(Object... objArr) {
                b bVar = new b(a.f2565c, a.f2566d, 30L, TimeUnit.SECONDS, a.f2568f, a.f2569g, a.f2570h);
                bVar.allowCoreThreadTimeOut(true);
                return bVar;
            }
        };
        f2572j = new com.bytedance.applog.log.a<b>() { // from class: com.bytedance.applog.concurrent.a.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.bytedance.applog.log.a
            /* renamed from: a */
            public b create(Object... objArr) {
                b bVar = new b(1, a.f2566d, 1L, TimeUnit.SECONDS, a.f2568f, a.f2569g, a.f2570h);
                bVar.allowCoreThreadTimeOut(true);
                return bVar;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AppLogExecutors.java */
    /* renamed from: com.bytedance.applog.concurrent.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class ThreadFactoryC0037a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private static final AtomicInteger f2573a = new AtomicInteger(1);

        /* renamed from: b  reason: collision with root package name */
        private final ThreadGroup f2574b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicInteger f2575c = new AtomicInteger(1);

        /* renamed from: d  reason: collision with root package name */
        private final String f2576d;

        ThreadFactoryC0037a(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.f2574b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.f2576d = str + "-" + f2573a.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f2574b, runnable, this.f2576d + this.f2575c.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public static ExecutorService a() {
        return f2571i.get(new Object[0]);
    }

    public static ExecutorService b() {
        return f2572j.get(new Object[0]);
    }
}
