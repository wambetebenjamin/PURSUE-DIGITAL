package com.bytedance.ttgame.core.scheduler;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import timber.log.Timber;
/* loaded from: classes3.dex */
class CoreMainExecutor implements Executor {
    private static final long LONG_MAIN_TASK_TIME = 50;
    private final int type;
    private volatile MessageQueue mainQueue = null;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public CoreMainExecutor(int i2) {
        this.type = i2;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        int i2 = this.type;
        if (i2 == 3) {
            this.mainHandler.post(new MainRunnable(runnable, this.type));
        } else if (i2 == 4) {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mainHandler.getLooper().getQueue().addIdleHandler(new CoreIdleHandler(runnable, this.type));
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                Looper.myQueue().addIdleHandler(new CoreIdleHandler(runnable, this.type));
            } else {
                if (this.mainQueue == null) {
                    synchronized (CoreMainExecutor.class) {
                        if (this.mainQueue == null) {
                            this.mainQueue = getMainMessageQueueUnderLowAndroidBuild();
                        }
                    }
                }
                if (this.mainQueue != null) {
                    this.mainQueue.addIdleHandler(new CoreIdleHandler(runnable, this.type));
                }
            }
        }
    }

    private MessageQueue getMainMessageQueueUnderLowAndroidBuild() {
        try {
            Field declaredField = Class.forName("android.os.Looper").getDeclaredField("mQueue");
            declaredField.setAccessible(true);
            return (MessageQueue) declaredField.get(this.mainHandler.getLooper());
        } catch (Throwable th) {
            Timber.tag("GScheduler").e("reflect get main queue failed, ", th.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor create(int i2) {
        if (3 == i2 || 4 == i2) {
            return new CoreMainExecutor(i2);
        }
        throw new UnsupportedOperationException("can not create MainExecutor for type " + i2);
    }

    /* loaded from: classes3.dex */
    static class CoreIdleHandler implements MessageQueue.IdleHandler {
        private final MainRunnable target;

        public CoreIdleHandler(Runnable runnable, int i2) {
            this.target = new MainRunnable(runnable, i2);
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            this.target.run();
            return false;
        }
    }

    /* loaded from: classes3.dex */
    static class MainRunnable implements Runnable {
        private final Runnable target;
        private final int type;

        public MainRunnable(Runnable runnable, int i2) {
            this.target = runnable;
            this.type = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            Runnable runnable = this.target;
            if (runnable != null) {
                runnable.run();
                if (SystemClock.uptimeMillis() - uptimeMillis > CoreMainExecutor.LONG_MAIN_TASK_TIME) {
                    SchedulerService.monitor.commit(0, this.target.toString(), this.type);
                }
            }
        }
    }
}
