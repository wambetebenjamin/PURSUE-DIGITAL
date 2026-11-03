package com.bytedance.common.utility.concurrent;

import android.os.Process;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: TTExecutors.java */
/* loaded from: classes2.dex */
public class c {
    public static final int AVAILABLEPROCESSORS;
    public static final int CORE_POOL_SIZE_CPU;
    public static final int CORE_POOL_SIZE_NORMAL;
    public static final int CORE_POOL_SIZE_SCHEDULED = 3;
    public static final int CPU_COUNT;
    public static final int DOWNLOAD_THREAD_COUNT = 2;
    public static final int KEEP_ALIVE_SECONDS = 30;
    public static final int MAXIMUM_POOL_SIZE_CPU;
    public static final int MAXIMUM_POOL_SIZE_NORMAL;
    public static final int MAX_POOL_SIZE = 3;

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f5226a;

    /* renamed from: b  reason: collision with root package name */
    private static ExecutorService f5227b;

    /* renamed from: c  reason: collision with root package name */
    private static ExecutorService f5228c;

    /* renamed from: d  reason: collision with root package name */
    private static ScheduledExecutorService f5229d;

    /* renamed from: e  reason: collision with root package name */
    private static ExecutorService f5230e;

    /* renamed from: f  reason: collision with root package name */
    private static ExecutorService f5231f;

    /* renamed from: g  reason: collision with root package name */
    private static final b f5232g;

    /* renamed from: h  reason: collision with root package name */
    private static final b f5233h;

    /* renamed from: i  reason: collision with root package name */
    private static final b f5234i;

    /* renamed from: j  reason: collision with root package name */
    private static final b f5235j;
    private static final b k;
    private static final a l;
    private static final BlockingQueue<Runnable> m;
    private static final BlockingQueue<Runnable> n;
    private static final BlockingQueue<Runnable> o;
    private static final RejectedExecutionHandler p;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        AVAILABLEPROCESSORS = availableProcessors;
        if (availableProcessors <= 0) {
            availableProcessors = 1;
        }
        CPU_COUNT = availableProcessors;
        int max = Math.max(2, Math.min(availableProcessors - 1, 6)) * 2;
        CORE_POOL_SIZE_NORMAL = max;
        int i2 = (max * 2) + 1;
        MAXIMUM_POOL_SIZE_NORMAL = i2;
        int max2 = Math.max(2, Math.min(availableProcessors - 1, 3));
        CORE_POOL_SIZE_CPU = max2;
        int i3 = (availableProcessors * 2) + 1;
        MAXIMUM_POOL_SIZE_CPU = i3;
        b bVar = new b("TTDefaultExecutors");
        f5232g = bVar;
        b bVar2 = new b("TTCpuExecutors");
        f5233h = bVar2;
        b bVar3 = new b("TTScheduledExecutors");
        f5234i = bVar3;
        b bVar4 = new b("TTDownLoadExecutors");
        f5235j = bVar4;
        b bVar5 = new b("TTSerialExecutors");
        k = bVar5;
        a aVar = new a("TTBackgroundExecutors");
        l = aVar;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        m = linkedBlockingQueue;
        LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        n = linkedBlockingQueue2;
        LinkedBlockingQueue linkedBlockingQueue3 = new LinkedBlockingQueue();
        o = linkedBlockingQueue3;
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() { // from class: com.bytedance.common.utility.concurrent.c.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Executors.newCachedThreadPool().execute(runnable);
            }
        };
        p = rejectedExecutionHandler;
        d dVar = new d(max, i2, 30L, TimeUnit.SECONDS, linkedBlockingQueue, bVar, rejectedExecutionHandler);
        f5226a = dVar;
        dVar.allowCoreThreadTimeOut(true);
        d dVar2 = new d(max2, i3, 30L, TimeUnit.SECONDS, linkedBlockingQueue2, bVar2, rejectedExecutionHandler);
        f5227b = dVar2;
        dVar2.allowCoreThreadTimeOut(true);
        f5229d = Executors.newScheduledThreadPool(3, bVar3);
        d dVar3 = new d(2, 2, 30L, TimeUnit.SECONDS, linkedBlockingQueue3, bVar4, rejectedExecutionHandler);
        f5228c = dVar3;
        dVar3.allowCoreThreadTimeOut(true);
        d dVar4 = new d(1, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), bVar5);
        f5230e = dVar4;
        dVar4.allowCoreThreadTimeOut(true);
        d dVar5 = new d(0, 3, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), aVar);
        f5231f = dVar5;
        dVar5.allowCoreThreadTimeOut(true);
    }

    /* compiled from: TTExecutors.java */
    /* loaded from: classes2.dex */
    private static class b implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private static final AtomicInteger f5241a = new AtomicInteger(1);

        /* renamed from: b  reason: collision with root package name */
        private final ThreadGroup f5242b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicInteger f5243c = new AtomicInteger(1);

        /* renamed from: d  reason: collision with root package name */
        private final String f5244d;

        b(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                this.f5242b = securityManager.getThreadGroup();
            } else {
                this.f5242b = Thread.currentThread().getThreadGroup();
            }
            this.f5244d = str + "-" + f5241a.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f5242b, runnable, this.f5244d + this.f5243c.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    /* compiled from: TTExecutors.java */
    /* loaded from: classes2.dex */
    private static class a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private static final AtomicInteger f5236a = new AtomicInteger(1);

        /* renamed from: b  reason: collision with root package name */
        private final ThreadGroup f5237b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicInteger f5238c = new AtomicInteger(1);

        /* renamed from: d  reason: collision with root package name */
        private final String f5239d;

        a(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.f5237b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.f5239d = str + "-" + f5236a.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f5237b, runnable, this.f5239d + this.f5238c.getAndIncrement(), 0L) { // from class: com.bytedance.common.utility.concurrent.c.a.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    public static ExecutorService getNormalExecutor() {
        return f5226a;
    }

    @Deprecated
    public static ExecutorService getDownLoadThreadPool() {
        return f5228c;
    }

    public static ScheduledExecutorService getScheduledThreadPool() {
        return f5229d;
    }

    public static ExecutorService getCPUThreadPool() {
        return f5227b;
    }

    public static ExecutorService getIOThreadPool() {
        return f5228c;
    }

    public static ExecutorService getSerialThreadPool() {
        return f5230e;
    }

    public static ExecutorService getBackgroundThreadPool() {
        return f5231f;
    }

    public static void setNormalThreadPool(ExecutorService executorService) {
        f5226a = executorService;
    }

    public static void setCPUThreadPool(ExecutorService executorService) {
        f5227b = executorService;
    }

    public static void setIOThreadPool(ExecutorService executorService) {
        f5228c = executorService;
    }

    public static void setScheduledThreadPool(ScheduledExecutorService scheduledExecutorService) {
        f5229d = scheduledExecutorService;
    }

    public static void setSerialThreadPool(ExecutorService executorService) {
        f5230e = executorService;
    }

    public static void setBackgroundThreadPool(ExecutorService executorService) {
        f5231f = executorService;
    }
}
