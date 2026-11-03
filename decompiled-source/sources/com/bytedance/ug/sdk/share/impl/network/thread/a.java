package com.bytedance.ug.sdk.share.impl.network.thread;

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
/* loaded from: classes8.dex */
public class a {
    public static final int AVAILABLEPROCESSORS;
    public static final int CORE_POOL_SIZE_CPU;
    public static final int CORE_POOL_SIZE_NORMAL;
    public static final int CORE_POOL_SIZE_SCHEDULED = 3;
    public static final int CPU_COUNT;
    public static final int DOWNLOAD_THREAD_COUNT = 2;
    public static final int KEEP_ALIVE_SECONDS = 30;
    public static final int MAXIMUM_POOL_SIZE_CPU;
    public static final int MAXIMUM_POOL_SIZE_NORMAL;

    /* renamed from: a  reason: collision with root package name */
    private static ExecutorService f15873a;

    /* renamed from: b  reason: collision with root package name */
    private static ExecutorService f15874b;

    /* renamed from: c  reason: collision with root package name */
    private static ExecutorService f15875c;

    /* renamed from: d  reason: collision with root package name */
    private static ScheduledExecutorService f15876d;

    /* renamed from: e  reason: collision with root package name */
    private static final ThreadFactoryC0257a f15877e;

    /* renamed from: f  reason: collision with root package name */
    private static final ThreadFactoryC0257a f15878f;

    /* renamed from: g  reason: collision with root package name */
    private static final ThreadFactoryC0257a f15879g;

    /* renamed from: h  reason: collision with root package name */
    private static final ThreadFactoryC0257a f15880h;

    /* renamed from: i  reason: collision with root package name */
    private static final BlockingQueue<Runnable> f15881i;

    /* renamed from: j  reason: collision with root package name */
    private static final BlockingQueue<Runnable> f15882j;
    private static final BlockingQueue<Runnable> k;
    private static final RejectedExecutionHandler l;

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
        ThreadFactoryC0257a threadFactoryC0257a = new ThreadFactoryC0257a("TTDefaultExecutors");
        f15877e = threadFactoryC0257a;
        ThreadFactoryC0257a threadFactoryC0257a2 = new ThreadFactoryC0257a("TTCpuExecutors");
        f15878f = threadFactoryC0257a2;
        ThreadFactoryC0257a threadFactoryC0257a3 = new ThreadFactoryC0257a("TTScheduledExecutors");
        f15879g = threadFactoryC0257a3;
        ThreadFactoryC0257a threadFactoryC0257a4 = new ThreadFactoryC0257a("TTDownLoadExecutors");
        f15880h = threadFactoryC0257a4;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        f15881i = linkedBlockingQueue;
        LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        f15882j = linkedBlockingQueue2;
        LinkedBlockingQueue linkedBlockingQueue3 = new LinkedBlockingQueue();
        k = linkedBlockingQueue3;
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() { // from class: com.bytedance.ug.sdk.share.impl.network.thread.a.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Executors.newCachedThreadPool().execute(runnable);
            }
        };
        l = rejectedExecutionHandler;
        b bVar = new b(max, i2, 30L, TimeUnit.SECONDS, linkedBlockingQueue, threadFactoryC0257a, rejectedExecutionHandler);
        f15873a = bVar;
        bVar.allowCoreThreadTimeOut(true);
        b bVar2 = new b(max2, i3, 30L, TimeUnit.SECONDS, linkedBlockingQueue2, threadFactoryC0257a2, rejectedExecutionHandler);
        f15874b = bVar2;
        bVar2.allowCoreThreadTimeOut(true);
        f15876d = Executors.newScheduledThreadPool(3, threadFactoryC0257a3);
        b bVar3 = new b(2, 2, 30L, TimeUnit.SECONDS, linkedBlockingQueue3, threadFactoryC0257a4, rejectedExecutionHandler);
        f15875c = bVar3;
        bVar3.allowCoreThreadTimeOut(true);
    }

    /* compiled from: TTExecutors.java */
    /* renamed from: com.bytedance.ug.sdk.share.impl.network.thread.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    private static class ThreadFactoryC0257a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private static final AtomicInteger f15883a = new AtomicInteger(1);

        /* renamed from: b  reason: collision with root package name */
        private final ThreadGroup f15884b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicInteger f15885c = new AtomicInteger(1);

        /* renamed from: d  reason: collision with root package name */
        private final String f15886d;

        ThreadFactoryC0257a(String str) {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.f15884b = threadGroup;
            this.f15886d = str + "-" + f15883a.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f15884b, runnable, this.f15886d + this.f15885c.getAndIncrement(), 0L);
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
        return f15873a;
    }

    public static ExecutorService getDownLoadThreadPool() {
        return f15875c;
    }

    public static ScheduledExecutorService getScheduledThreadPool() {
        return f15876d;
    }

    public static ExecutorService getCPUThreadPool() {
        return f15874b;
    }

    public static void setNormalThreadPool(ExecutorService executorService) {
        f15873a = executorService;
    }
}
