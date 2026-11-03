package com.ttnet.org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/* loaded from: classes9.dex */
public class ThreadUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object sLock = new Object();
    private static boolean sThreadAssertsDisabled;
    private static Handler sUiThreadHandler;
    private static boolean sWillOverride;

    /* loaded from: classes9.dex */
    public static class ThreadChecker {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final long mThreadId = Process.myTid();

        public void assertOnValidThread() {
        }
    }

    public static void assertOnBackgroundThread() {
    }

    public static void assertOnUiThread() {
    }

    public static void setWillOverrideUiThread(boolean z) {
        synchronized (sLock) {
            sWillOverride = z;
        }
    }

    public static void setUiThread(Looper looper) {
        synchronized (sLock) {
            if (looper == null) {
                sUiThreadHandler = null;
                return;
            }
            Handler handler = sUiThreadHandler;
            if (handler != null && handler.getLooper() != looper) {
                throw new RuntimeException("UI thread looper is already set to " + sUiThreadHandler.getLooper() + " (Main thread looper is " + Looper.getMainLooper() + "), cannot set to new looper " + looper);
            }
            sUiThreadHandler = new Handler(looper);
            TraceEvent.onUiThreadReady();
        }
    }

    public static Handler getUiThreadHandler() {
        boolean z;
        synchronized (sLock) {
            if (sUiThreadHandler != null) {
                z = false;
            } else if (sWillOverride) {
                throw new RuntimeException("Did not yet override the UI thread");
            } else {
                sUiThreadHandler = new Handler(Looper.getMainLooper());
                z = true;
            }
        }
        if (z) {
            TraceEvent.onUiThreadReady();
        }
        return sUiThreadHandler;
    }

    @Deprecated
    public static void runOnUiThreadBlocking(Runnable runnable) {
        if (runningOnUiThread()) {
            runnable.run();
            return;
        }
        FutureTask futureTask = new FutureTask(runnable, null);
        postOnUiThread(futureTask);
        try {
            futureTask.get();
        } catch (Exception e2) {
            throw new RuntimeException("Exception occurred while waiting for runnable", e2);
        }
    }

    @Deprecated
    public static <T> T runOnUiThreadBlockingNoException(Callable<T> callable) {
        try {
            return (T) runOnUiThreadBlocking(callable);
        } catch (ExecutionException e2) {
            throw new RuntimeException("Error occurred waiting for callable", e2);
        }
    }

    @Deprecated
    public static <T> T runOnUiThreadBlocking(Callable<T> callable) {
        FutureTask futureTask = new FutureTask(callable);
        runOnUiThread(futureTask);
        try {
            return (T) futureTask.get();
        } catch (InterruptedException e2) {
            throw new RuntimeException("Interrupted waiting for callable", e2);
        }
    }

    @Deprecated
    public static <T> FutureTask<T> runOnUiThread(FutureTask<T> futureTask) {
        if (runningOnUiThread()) {
            futureTask.run();
        } else {
            postOnUiThread((FutureTask) futureTask);
        }
        return futureTask;
    }

    @Deprecated
    public static <T> FutureTask<T> runOnUiThread(Callable<T> callable) {
        return runOnUiThread(new FutureTask(callable));
    }

    @Deprecated
    public static void runOnUiThread(Runnable runnable) {
        if (runningOnUiThread()) {
            runnable.run();
        } else {
            getUiThreadHandler().post(runnable);
        }
    }

    @Deprecated
    public static <T> FutureTask<T> postOnUiThread(FutureTask<T> futureTask) {
        getUiThreadHandler().post(futureTask);
        return futureTask;
    }

    @Deprecated
    public static void postOnUiThread(Runnable runnable) {
        getUiThreadHandler().post(runnable);
    }

    @Deprecated
    public static void postOnUiThreadDelayed(Runnable runnable, long j2) {
        getUiThreadHandler().postDelayed(runnable, j2);
    }

    public static void checkUiThread() {
        if (!sThreadAssertsDisabled && !runningOnUiThread()) {
            throw new IllegalStateException("Must be called on the UI thread.");
        }
    }

    public static void setThreadAssertsDisabledForTesting(boolean z) {
        sThreadAssertsDisabled = z;
    }

    public static boolean runningOnUiThread() {
        return getUiThreadHandler().getLooper() == Looper.myLooper();
    }

    public static Looper getUiThreadLooper() {
        return getUiThreadHandler().getLooper();
    }

    public static void setThreadPriorityAudio(int i2) {
        Process.setThreadPriority(i2, -16);
    }

    private static boolean isThreadPriorityAudio(int i2) {
        return Process.getThreadPriority(i2) == -16;
    }
}
