package com.bytedance.praisedialoglib.ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: TTExecutors.java */
/* loaded from: classes2.dex */
public class a {
    private static final int CORE_POOL_SIZE_NORMAL;
    private static final int CPU_COUNT;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final int MAXIMUM_POOL_SIZE_NORMAL;

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f11101a;

    /* renamed from: b  reason: collision with root package name */
    private static final int f11102b;

    /* renamed from: e  reason: collision with root package name */
    private static final ThreadFactoryC0164a f11103e;

    /* renamed from: i  reason: collision with root package name */
    private static final BlockingQueue<Runnable> f11104i;
    private static final RejectedExecutionHandler l;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f11102b = availableProcessors;
        if (availableProcessors <= 0) {
            availableProcessors = 1;
        }
        CPU_COUNT = availableProcessors;
        int max = Math.max(2, Math.min(availableProcessors - 1, 6)) * 2;
        CORE_POOL_SIZE_NORMAL = max;
        int i2 = (max * 2) + 1;
        MAXIMUM_POOL_SIZE_NORMAL = i2;
        ThreadFactoryC0164a threadFactoryC0164a = new ThreadFactoryC0164a("TTDefaultExecutors");
        f11103e = threadFactoryC0164a;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        f11104i = linkedBlockingQueue;
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() { // from class: com.bytedance.praisedialoglib.ThreadPool.a.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Executors.newCachedThreadPool().execute(runnable);
            }
        };
        l = rejectedExecutionHandler;
        b bVar = new b(max, i2, 30L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactoryC0164a, rejectedExecutionHandler);
        f11101a = bVar;
        bVar.allowCoreThreadTimeOut(true);
    }

    /* compiled from: TTExecutors.java */
    /* renamed from: com.bytedance.praisedialoglib.ThreadPool.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class ThreadFactoryC0164a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private static final AtomicInteger f11105a = new AtomicInteger(1);

        /* renamed from: b  reason: collision with root package name */
        private final ThreadGroup f11106b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicInteger f11107c = new AtomicInteger(1);

        /* renamed from: d  reason: collision with root package name */
        private final String f11108d;

        ThreadFactoryC0164a(String str) {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.f11106b = threadGroup;
            this.f11108d = str + "-" + f11105a.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f11106b, runnable, this.f11108d + this.f11107c.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public static ExecutorService getNormalExecutor() {
        return f11101a;
    }
}
