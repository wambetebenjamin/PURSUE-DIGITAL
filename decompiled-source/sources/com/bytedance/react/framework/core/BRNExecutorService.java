package com.bytedance.react.framework.core;

import android.util.Log;
import com.bytedance.react.framework.core.BRNManager;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class BRNExecutorService {
    /* JADX WARN: Type inference failed for: r0v1, types: [com.bytedance.react.framework.core.BRNExecutorService$1] */
    public static void executeByDefaultExecutor(String str, final Runnable runnable) {
        if (runnable == null) {
            Log.e("BRNExecutorService", "executeByDefaultExecutor task is null");
            return;
        }
        ExecutorService defaultExecutor = getDefaultExecutor();
        if (defaultExecutor != null) {
            defaultExecutor.execute(runnable);
        } else {
            new Thread(str) { // from class: com.bytedance.react.framework.core.BRNExecutorService.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    runnable.run();
                }
            }.start();
        }
    }

    public static void executeByScheduledExecutor(String str, final Runnable runnable, long j2, TimeUnit timeUnit) {
        if (runnable == null) {
            Log.e("BRNExecutorService", "executeByScheduledExecutor task is null");
            return;
        }
        ScheduledExecutorService scheduledExecutor = getScheduledExecutor();
        if (scheduledExecutor != null) {
            scheduledExecutor.schedule(runnable, j2, timeUnit);
            return;
        }
        new Timer().schedule(new TimerTask() { // from class: com.bytedance.react.framework.core.BRNExecutorService.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                runnable.run();
            }
        }, timeUnit.toMillis(j2));
    }

    public static ExecutorService getDefaultExecutor() {
        BRNManager.ExecutorServiceInterface executorServiceInterface = BRNManager.newInstance().getExecutorServiceInterface();
        if (executorServiceInterface != null) {
            return executorServiceInterface.getDefaultExecutor();
        }
        return null;
    }

    public static ExecutorService getBackGroundExecutor() {
        BRNManager.ExecutorServiceInterface executorServiceInterface = BRNManager.newInstance().getExecutorServiceInterface();
        if (executorServiceInterface != null) {
            return executorServiceInterface.getBackGroundExecutor();
        }
        return null;
    }

    public static ExecutorService getMainExecutor() {
        BRNManager.ExecutorServiceInterface executorServiceInterface = BRNManager.newInstance().getExecutorServiceInterface();
        if (executorServiceInterface != null) {
            return executorServiceInterface.getMainExecutor();
        }
        return null;
    }

    public static ExecutorService getIdleExecutor() {
        BRNManager.ExecutorServiceInterface executorServiceInterface = BRNManager.newInstance().getExecutorServiceInterface();
        if (executorServiceInterface != null) {
            return executorServiceInterface.getIdleExecutor();
        }
        return null;
    }

    public static ExecutorService getSerialExecutor() {
        BRNManager.ExecutorServiceInterface executorServiceInterface = BRNManager.newInstance().getExecutorServiceInterface();
        if (executorServiceInterface != null) {
            return executorServiceInterface.getSerialExecutor();
        }
        return null;
    }

    public static ScheduledExecutorService getScheduledExecutor() {
        BRNManager.ExecutorServiceInterface executorServiceInterface = BRNManager.newInstance().getExecutorServiceInterface();
        if (executorServiceInterface != null) {
            return executorServiceInterface.getScheduledExecutor();
        }
        return null;
    }
}
