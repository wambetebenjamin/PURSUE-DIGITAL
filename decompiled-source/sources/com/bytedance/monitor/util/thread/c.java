package com.bytedance.monitor.util.thread;

import java.util.Locale;
/* compiled from: AsyncTaskUtil.java */
/* loaded from: classes2.dex */
public class c {
    public static d getAsyncTaskManagerInstance() {
        return a.getInstance();
    }

    public static String getTaskInfo(e eVar) {
        return eVar == null ? "null" : eVar.getTaskName() + ", " + eVar.getTaskType();
    }

    public static e wrapLightWeightTask(String str, Runnable runnable) {
        return wrapRunnable(b.LIGHT_WEIGHT, str, runnable);
    }

    public static e wrapLightWeightTaskNoName(Runnable runnable) {
        return wrapRunnable(b.LIGHT_WEIGHT, "no-name", runnable);
    }

    public static e wrapIOTask(String str, Runnable runnable) {
        return wrapRunnable(b.IO, str, runnable);
    }

    public static e wrapTimeSensitiveTask(String str, Runnable runnable) {
        return wrapRunnable(b.TIME_SENSITIVE, str, runnable);
    }

    public static e wrapRunnable(final b bVar, final String str, final Runnable runnable) {
        return new e() { // from class: com.bytedance.monitor.util.thread.c.1
            @Override // com.bytedance.monitor.util.thread.e
            public String getTaskName() {
                return str;
            }

            @Override // com.bytedance.monitor.util.thread.e
            public b getTaskType() {
                return bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                        d asyncTaskManagerInstance = c.getAsyncTaskManagerInstance();
                        if (asyncTaskManagerInstance == null || asyncTaskManagerInstance.getThreadLogListener() == null || !asyncTaskManagerInstance.getThreadLogListener().isDebug()) {
                            return;
                        }
                        c.logd(asyncTaskManagerInstance.getThreadLogListener(), "AsyncTaskUtil", "task execute: " + bVar + "  /  " + str);
                    }
                } catch (Throwable th) {
                    c.getAsyncTaskManagerInstance().directReportError(th, "APM_INNER_ERROR_async_task");
                }
            }
        };
    }

    public static void logd(f fVar, String str, String str2) {
        if (fVar == null || !fVar.isDebug()) {
            return;
        }
        fVar.onLog(str, String.format(Locale.CHINA, "[callerThread: %s] \n %s", Thread.currentThread().getName(), str2));
    }

    public static void loge(f fVar, String str, String str2) {
        if (fVar != null) {
            fVar.onError(str, String.format(Locale.CHINA, "[callerThread: %s] \n %s", Thread.currentThread().getName(), str2));
        }
    }
}
