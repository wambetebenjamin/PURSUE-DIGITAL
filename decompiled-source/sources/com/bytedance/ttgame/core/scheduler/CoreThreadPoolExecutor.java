package com.bytedance.ttgame.core.scheduler;

import android.os.AsyncTask;
import android.os.Process;
import android.os.SystemClock;
import com.bytedance.crash.NpthBus;
import com.bytedance.ttgame.core.coredata.SdkCoreData;
import com.bytedance.ttgame.core.scheduler.CoreThreadPoolExecutor;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import timber.log.Timber;
/* loaded from: classes3.dex */
class CoreThreadPoolExecutor extends ThreadPoolExecutor {
    private static final long DECREASE_INTERVAL = 5000;
    private final int defaultCoreSize;
    private final boolean elastic;
    private final AtomicInteger intensiveCount;
    private volatile long lastTimeIncrease;
    private final int maxCoreSize;
    private final SchedulerMonitor monitor;
    private final AtomicInteger notIntensiveCount;
    private final AtomicBoolean threadAdjusting;
    private final int type;
    private static final int CPU_SIZE = Runtime.getRuntime().availableProcessors();
    private static final RejectedExecutionHandler defaultHandler = new FallbackRejectHandler();

    private CoreThreadPoolExecutor(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler, int i4) {
        super(i2, i2, j2, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
        this.intensiveCount = new AtomicInteger(0);
        this.notIntensiveCount = new AtomicInteger(0);
        this.monitor = new SchedulerMonitor();
        this.threadAdjusting = new AtomicBoolean(false);
        this.lastTimeIncrease = SystemClock.uptimeMillis();
        this.type = i4;
        this.defaultCoreSize = i2;
        this.maxCoreSize = i3;
        this.elastic = i2 != i3;
        allowCoreThreadTimeOut(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CoreThreadPoolExecutor create(int i2) {
        if (i2 == 0) {
            int i3 = CPU_SIZE;
            return new CoreThreadPoolExecutor(i3, (i3 * 4) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), new CoreThreadFactory("DEFAULT", i2), defaultHandler, i2);
        } else if (i2 == 1) {
            int i4 = CPU_SIZE;
            return new CoreThreadPoolExecutor(i4, (i4 * 2) + 1, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), new CoreThreadFactory("BACKGROUND", i2), defaultHandler, i2);
        } else if (i2 != 2) {
            if (i2 == 3 || i2 == 4) {
                throw new UnsupportedOperationException("can not create ThreadPoolExecutor for type " + i2);
            }
            return null;
        } else {
            return new CoreThreadPoolExecutor(1, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), new CoreThreadFactory("SERIAL", i2), defaultHandler, i2);
        }
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void beforeExecute(Thread thread, Runnable runnable) {
        if (this.elastic) {
            try {
                if (getQueue().size() > getCorePoolSize() * 2 && getSaturation() > 3.0f && SdkCoreData.getInstance().getMemoryLevel() < 15) {
                    if (this.intensiveCount.incrementAndGet() > getCorePoolSize()) {
                        increaseThread();
                        this.intensiveCount.set(0);
                        this.notIntensiveCount.set(0);
                    }
                } else if (getCorePoolSize() > this.defaultCoreSize && SystemClock.uptimeMillis() - this.lastTimeIncrease > 5000 && getSaturation() < 1.0f && this.notIntensiveCount.incrementAndGet() > getCorePoolSize()) {
                    decreaseThread();
                    this.intensiveCount.set(0);
                    this.notIntensiveCount.set(0);
                }
            } catch (Throwable th) {
                Timber.tag("GScheduler").e(th.getMessage(), new Object[0]);
            }
        }
    }

    SchedulerMonitor getMonitor() {
        return this.monitor;
    }

    private float getSaturation() {
        return (getQueue().size() + getActiveCount()) / getPoolSize();
    }

    private void increaseThread() {
        int corePoolSize = getCorePoolSize();
        if (corePoolSize >= this.maxCoreSize || !this.threadAdjusting.compareAndSet(false, true)) {
            return;
        }
        this.lastTimeIncrease = SystemClock.uptimeMillis();
        int i2 = corePoolSize + 1;
        setMaximumPoolSize(i2);
        setCorePoolSize(i2);
        this.threadAdjusting.set(false);
    }

    private void decreaseThread() {
        int corePoolSize = getCorePoolSize();
        if (corePoolSize <= this.defaultCoreSize || !this.threadAdjusting.compareAndSet(false, true)) {
            return;
        }
        int i2 = corePoolSize - 1;
        setCorePoolSize(i2);
        setMaximumPoolSize(i2);
        this.threadAdjusting.set(false);
    }

    /* loaded from: classes3.dex */
    static class FallbackRejectHandler implements RejectedExecutionHandler {
        private FallbackRejectHandler() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Timber.tag("GScheduler").e("Task " + runnable.toString() + " rejected from " + threadPoolExecutor.toString(), new Object[0]);
            AsyncTask.THREAD_POOL_EXECUTOR.execute(runnable);
            CoreThreadPoolExecutor coreThreadPoolExecutor = (CoreThreadPoolExecutor) threadPoolExecutor;
            coreThreadPoolExecutor.getMonitor().commit(1, threadPoolExecutor.getQueue().toString(), coreThreadPoolExecutor.type);
        }
    }

    /* loaded from: classes3.dex */
    static class CoreThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(0);
        private final int type;

        CoreThreadFactory(String str, int i2) {
            this.namePrefix = NpthBus.UUID_SUFIX_3_1_3 + str + "-";
            this.type = i2;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread defaultThread;
            String str = this.namePrefix + this.threadNumber.getAndIncrement();
            if (this.type == 1) {
                defaultThread = new BackgroundThread(runnable, str);
            } else {
                defaultThread = new DefaultThread(runnable, str);
            }
            if (defaultThread.isDaemon()) {
                defaultThread.setDaemon(false);
            }
            return defaultThread;
        }
    }

    /* loaded from: classes3.dex */
    static class DefaultThread extends Thread {
        public DefaultThread(Runnable runnable, String str) {
            super(runnable, str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(0);
            super.run();
        }
    }

    /* loaded from: classes3.dex */
    static class BackgroundThread extends Thread {
        public BackgroundThread(Runnable runnable, String str) {
            super(runnable, str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    /* loaded from: classes3.dex */
    static class CoreScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
        private final ThreadPoolExecutor executor;

        private CoreScheduledThreadPoolExecutor(int i2, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler, ThreadPoolExecutor threadPoolExecutor) {
            super(i2, threadFactory, rejectedExecutionHandler);
            this.executor = threadPoolExecutor;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static CoreScheduledThreadPoolExecutor create(ThreadPoolExecutor threadPoolExecutor) {
            return new CoreScheduledThreadPoolExecutor(1, new CoreThreadFactory("SCHEDULED", 0), CoreThreadPoolExecutor.defaultHandler, threadPoolExecutor);
        }

        @Override // java.util.concurrent.ScheduledThreadPoolExecutor
        protected <V> RunnableScheduledFuture<V> decorateTask(Callable<V> callable, RunnableScheduledFuture<V> runnableScheduledFuture) {
            return new TransferFuture(runnableScheduledFuture, this.executor);
        }

        @Override // java.util.concurrent.ScheduledThreadPoolExecutor
        protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> runnableScheduledFuture) {
            return new TransferFuture(runnableScheduledFuture, this.executor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TransferFuture<V> implements RunnableScheduledFuture<V> {
        private final RunnableScheduledFuture<V> outerTask;
        private final WeakReference<ExecutorService> serviceWeakReference;

        public TransferFuture(RunnableScheduledFuture<V> runnableScheduledFuture, ExecutorService executorService) {
            this.outerTask = runnableScheduledFuture;
            this.serviceWeakReference = new WeakReference<>(executorService);
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public boolean isPeriodic() {
            return this.outerTask.isPeriodic();
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit timeUnit) {
            return this.outerTask.getDelay(timeUnit);
        }

        @Override // java.lang.Comparable
        public int compareTo(Delayed delayed) {
            return this.outerTask.compareTo(delayed);
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            if (this.serviceWeakReference.get() != null) {
                this.serviceWeakReference.get().execute(new Runnable() { // from class: com.bytedance.ttgame.core.scheduler.CoreThreadPoolExecutor$TransferFuture$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        CoreThreadPoolExecutor.TransferFuture.this.m145x2a9e8add();
                    }
                });
            }
        }

        /* renamed from: lambda$run$0$com-bytedance-ttgame-core-scheduler-CoreThreadPoolExecutor$TransferFuture  reason: not valid java name */
        public /* synthetic */ void m145x2a9e8add() {
            this.outerTask.run();
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            return this.outerTask.cancel(z);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.outerTask.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.outerTask.isDone();
        }

        @Override // java.util.concurrent.Future
        public V get() throws ExecutionException, InterruptedException {
            return (V) this.outerTask.get();
        }

        @Override // java.util.concurrent.Future
        public V get(long j2, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
            return (V) this.outerTask.get(j2, timeUnit);
        }
    }
}
