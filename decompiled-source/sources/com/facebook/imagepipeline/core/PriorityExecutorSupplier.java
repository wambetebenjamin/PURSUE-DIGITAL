package com.facebook.imagepipeline.core;

import com.facebook.common.executors.PriorityRunnable;
import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes8.dex */
public class PriorityExecutorSupplier implements ExecutorSupplier {
    private static final int NUM_IO_BOUND_THREADS = 2;
    private static final int NUM_LIGHTWEIGHT_BACKGROUND_THREADS = 1;
    private final Executor mBackgroundExecutor;
    private final Executor mDecodeExecutor;
    Comparator<Runnable> comparator = new Comparator<Runnable>() { // from class: com.facebook.imagepipeline.core.PriorityExecutorSupplier.1
        @Override // java.util.Comparator
        public int compare(Runnable runnable, Runnable runnable2) {
            return getPriority(runnable) - getPriority(runnable2);
        }

        private int getPriority(Runnable runnable) {
            if (runnable instanceof PriorityRunnable) {
                return ((PriorityRunnable) runnable).getPriority();
            }
            return 1;
        }
    };
    private final Executor mIoBoundExecutor = Executors.newFixedThreadPool(2, new PriorityThreadFactory(10, "FrescoIoBoundExecutor", true));
    private final Executor mLightWeightBackgroundExecutor = Executors.newFixedThreadPool(1, new PriorityThreadFactory(10, "FrescoLightWeightBackgroundExecutor", true));

    public PriorityExecutorSupplier(int i2) {
        this.mDecodeExecutor = new ThreadPoolExecutor(i2, i2, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(50, this.comparator), new PriorityThreadFactory(10, "FrescoDecodeExecutor", true));
        this.mBackgroundExecutor = new ThreadPoolExecutor(i2, i2, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(50, this.comparator), new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLocalStorageRead() {
        return this.mIoBoundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLocalStorageWrite() {
        return this.mIoBoundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forDecode() {
        return this.mDecodeExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forBackgroundTasks() {
        return this.mBackgroundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLightweightBackgroundTasks() {
        return this.mLightWeightBackgroundExecutor;
    }
}
