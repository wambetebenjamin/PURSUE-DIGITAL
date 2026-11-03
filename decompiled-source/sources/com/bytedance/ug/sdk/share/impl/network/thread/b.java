package com.bytedance.ug.sdk.share.impl.network.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: TTThreadPoolExecutor.java */
/* loaded from: classes8.dex */
public class b extends ThreadPoolExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final RejectedExecutionHandler f15887a = new ThreadPoolExecutor.AbortPolicy();

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public void shutdown() {
    }

    public b(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i2, i3, j2, timeUnit, blockingQueue, Executors.defaultThreadFactory(), f15887a);
    }

    public b(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i2, i3, j2, timeUnit, blockingQueue, Executors.defaultThreadFactory(), rejectedExecutionHandler);
    }

    public b(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i2, i3, j2, timeUnit, blockingQueue, threadFactory, f15887a);
    }

    public b(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i2, i3, j2, timeUnit, blockingQueue, Executors.defaultThreadFactory(), rejectedExecutionHandler);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return new ArrayList();
    }
}
