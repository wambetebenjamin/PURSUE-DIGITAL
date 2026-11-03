package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.Twitter;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes10.dex */
public final class ExecutorUtils {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final long DEFAULT_TERMINATION_TIMEOUT = 1;
    private static final long KEEP_ALIVE = 1;
    private static final int MAXIMUM_POOL_SIZE;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        CORE_POOL_SIZE = availableProcessors + 1;
        MAXIMUM_POOL_SIZE = (availableProcessors * 2) + 1;
    }

    private ExecutorUtils() {
    }

    public static ExecutorService buildThreadPoolExecutorService(String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), getNamedThreadFactory(str));
        addDelayedShutdownHook(str, threadPoolExecutor);
        return threadPoolExecutor;
    }

    public static ScheduledExecutorService buildSingleThreadScheduledExecutorService(String str) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(getNamedThreadFactory(str));
        addDelayedShutdownHook(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    static ThreadFactory getNamedThreadFactory(final String str) {
        final AtomicLong atomicLong = new AtomicLong(1L);
        return new ThreadFactory() { // from class: com.twitter.sdk.android.core.internal.ExecutorUtils$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return ExecutorUtils.lambda$getNamedThreadFactory$0(str, atomicLong, runnable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Thread lambda$getNamedThreadFactory$0(String str, AtomicLong atomicLong, Runnable runnable) {
        Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
        newThread.setName(str + atomicLong.getAndIncrement());
        return newThread;
    }

    static void addDelayedShutdownHook(String str, ExecutorService executorService) {
        addDelayedShutdownHook(str, executorService, 1L, TimeUnit.SECONDS);
    }

    static void addDelayedShutdownHook(final String str, final ExecutorService executorService, final long j2, final TimeUnit timeUnit) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() { // from class: com.twitter.sdk.android.core.internal.ExecutorUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ExecutorUtils.lambda$addDelayedShutdownHook$1(executorService, j2, timeUnit, str);
            }
        }, "Twitter Shutdown Hook for " + str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$addDelayedShutdownHook$1(ExecutorService executorService, long j2, TimeUnit timeUnit, String str) {
        try {
            executorService.shutdown();
            if (executorService.awaitTermination(j2, timeUnit)) {
                return;
            }
            Twitter.getLogger().d("Twitter", str + " did not shutdown in the allocated time. Requesting immediate shutdown.");
            executorService.shutdownNow();
        } catch (InterruptedException unused) {
            Twitter.getLogger().d("Twitter", String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", str));
            executorService.shutdownNow();
        }
    }
}
