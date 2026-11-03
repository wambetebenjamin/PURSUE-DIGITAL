package com.bytedance.common.utility.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: TTThreadPoolExecutor.java */
/* loaded from: classes2.dex */
public class d extends ThreadPoolExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final RejectedExecutionHandler f5245a = new ThreadPoolExecutor.AbortPolicy();

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public void shutdown() {
    }

    public d(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i2, i3, j2, timeUnit, blockingQueue, Executors.defaultThreadFactory(), f5245a);
    }

    public d(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i2, i3, j2, timeUnit, blockingQueue, Executors.defaultThreadFactory(), rejectedExecutionHandler);
    }

    public d(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i2, i3, j2, timeUnit, blockingQueue, threadFactory, f5245a);
    }

    public d(int i2, int i3, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i2, i3, j2, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return new ArrayList();
    }
}
