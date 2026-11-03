package com.oasis.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class ThreadUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17437a;

    /* renamed from: b  reason: collision with root package name */
    private static final int f17438b;

    /* renamed from: c  reason: collision with root package name */
    private static final ThreadPoolExecutor f17439c;
    public static Handler mMainHandler;

    /* loaded from: classes9.dex */
    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Runnable f17440a;

        a(Runnable runnable) {
            this.f17440a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadUtils.mMainHandler.post(this.f17440a);
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f17437a = availableProcessors;
        int max = Math.max(availableProcessors, 5);
        f17438b = max;
        f17439c = new ThreadPoolExecutor(max, max, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128));
        mMainHandler = new Handler(Looper.getMainLooper());
    }

    public static <T> Future<T> runOnThreadPool(Callable<T> callable) {
        return f17439c.submit(callable);
    }

    public static void runOnThreadPool(Runnable runnable) {
        f17439c.execute(runnable);
    }

    public static void runOnUIThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            mMainHandler.post(runnable);
        }
    }

    public static void runOnUIThreadByThreadPool(Runnable runnable) {
        f17439c.execute(new a(runnable));
    }
}
