package com.bytedance.ttgame.core.scheduler;

import com.bytedance.ttgame.core.scheduler.CoreThreadPoolExecutor;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes3.dex */
public class SchedulerService {
    private static final SchedulerService INSTANCE = new SchedulerService();
    static final SchedulerMonitor monitor = new SchedulerMonitor();
    private final ExecutorService backgroundExecutor;
    private final ExecutorService defaultExecutor;
    private final ExecutorService idleExecutor;
    private final ExecutorService mainExecutor;
    private final ScheduledExecutorService scheduledExecutor;
    private final ExecutorService serialExecutor;

    private SchedulerService() {
        CoreThreadPoolExecutor create = CoreThreadPoolExecutor.create(0);
        this.defaultExecutor = new DelegatedThreadPoolExecutorService(create);
        this.backgroundExecutor = new DelegatedThreadPoolExecutorService(CoreThreadPoolExecutor.create(1));
        this.serialExecutor = new DelegatedThreadPoolExecutorService(CoreThreadPoolExecutor.create(2));
        this.mainExecutor = new DelegatedThreadPoolExecutorService(CoreMainExecutor.create(3));
        this.idleExecutor = new DelegatedThreadPoolExecutorService(CoreMainExecutor.create(4));
        this.scheduledExecutor = new DelegatedScheduledExecutorService(CoreThreadPoolExecutor.CoreScheduledThreadPoolExecutor.create(create));
    }

    public static SchedulerService getInstance() {
        return INSTANCE;
    }

    public ExecutorService getExecutor(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            return this.idleExecutor;
                        }
                        return this.defaultExecutor;
                    }
                    return this.mainExecutor;
                }
                return this.serialExecutor;
            }
            return this.backgroundExecutor;
        }
        return this.defaultExecutor;
    }

    public ScheduledExecutorService getScheduledExecutor() {
        return this.scheduledExecutor;
    }

    /* loaded from: classes3.dex */
    static class DelegatedThreadPoolExecutorService extends AbstractExecutorService {

        /* renamed from: e  reason: collision with root package name */
        private final Executor f14105e;

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long j2, TimeUnit timeUnit) throws InterruptedException {
            return false;
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            return false;
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            return false;
        }

        @Override // java.util.concurrent.ExecutorService
        public void shutdown() {
        }

        @Override // java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            return null;
        }

        DelegatedThreadPoolExecutorService(Executor executor) {
            this.f14105e = executor;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f14105e.execute(runnable);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public Future<?> submit(Runnable runnable) {
            return super.submit(runnable);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Callable<T> callable) {
            return super.submit(callable);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> Future<T> submit(Runnable runnable, T t) {
            return super.submit(runnable, t);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return super.invokeAll(collection);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j2, TimeUnit timeUnit) throws InterruptedException {
            return super.invokeAll(collection, j2, timeUnit);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return (T) super.invokeAny(collection);
        }

        @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j2, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (T) super.invokeAny(collection, j2, timeUnit);
        }
    }

    /* loaded from: classes3.dex */
    static class DelegatedScheduledExecutorService extends DelegatedThreadPoolExecutorService implements ScheduledExecutorService {

        /* renamed from: e  reason: collision with root package name */
        private final ScheduledExecutorService f14104e;

        DelegatedScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
            super(scheduledExecutorService);
            this.f14104e = scheduledExecutorService;
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> schedule(Runnable runnable, long j2, TimeUnit timeUnit) {
            return this.f14104e.schedule(runnable, j2, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j2, TimeUnit timeUnit) {
            return this.f14104e.schedule(callable, j2, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
            return this.f14104e.scheduleAtFixedRate(runnable, j2, j3, timeUnit);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
            return this.f14104e.scheduleWithFixedDelay(runnable, j2, j3, timeUnit);
        }
    }
}
