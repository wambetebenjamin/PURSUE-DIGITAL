package com.bytedance.common.utility.concurrent;

import com.bytedance.common.utility.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: ThreadPlus.java */
/* loaded from: classes2.dex */
public class e implements Runnable {
    private final boolean mBackground;
    private Runnable runnable;
    private static ExecutorService mCachedExecutor = c.getNormalExecutor();
    private static ExecutorService mFixedExecutor = c.getNormalExecutor();
    protected static final AtomicInteger S_COUNT = new AtomicInteger();

    public static void shutdown() {
    }

    public e(Runnable runnable, String str, boolean z) {
        this.runnable = runnable;
        this.mBackground = z;
    }

    public e(String str) {
        this(false);
    }

    public e() {
        this(false);
    }

    public e(boolean z) {
        this.mBackground = z;
    }

    public static void setExecutorService(ExecutorService executorService) {
        mCachedExecutor = executorService;
        mFixedExecutor = executorService;
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.runnable;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void start() {
        Runnable runnable = Logger.debug() ? new Runnable() { // from class: com.bytedance.common.utility.concurrent.e.1
            @Override // java.lang.Runnable
            public void run() {
                Logger.d("ThreadPlus", "thread count: " + e.S_COUNT.incrementAndGet());
                try {
                    e.this.run();
                } catch (Exception e2) {
                    Logger.w("ThreadPlus", "Thread crashed!", e2);
                }
                Logger.d("ThreadPlus", "thread count: " + e.S_COUNT.decrementAndGet());
            }
        } : this;
        if (this.mBackground) {
            mFixedExecutor.submit(runnable);
        } else {
            mCachedExecutor.submit(runnable);
        }
    }

    public static void submitRunnable(Runnable runnable) {
        if (runnable != null) {
            mCachedExecutor.submit(runnable);
        }
    }
}
