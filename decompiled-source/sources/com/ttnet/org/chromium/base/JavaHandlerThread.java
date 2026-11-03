package com.ttnet.org.chromium.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
import java.lang.Thread;
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
public class JavaHandlerThread {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final HandlerThread mThread;
    private Throwable mUnhandledException;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void initializeThread(long j2, long j3);

        void onLooperStopped(long j2);
    }

    public JavaHandlerThread(String str, int i2) {
        this.mThread = new HandlerThread(str, i2);
    }

    private static JavaHandlerThread create(String str, int i2) {
        return new JavaHandlerThread(str, i2);
    }

    public Looper getLooper() {
        return this.mThread.getLooper();
    }

    public void maybeStart() {
        if (hasStarted()) {
            return;
        }
        this.mThread.start();
    }

    private void startAndInitialize(final long j2, final long j3) {
        maybeStart();
        new Handler(this.mThread.getLooper()).post(new Runnable() { // from class: com.ttnet.org.chromium.base.JavaHandlerThread.1
            @Override // java.lang.Runnable
            public void run() {
                JavaHandlerThreadJni.get().initializeThread(j2, j3);
            }
        });
    }

    private void quitThreadSafely(final long j2) {
        new Handler(this.mThread.getLooper()).post(new Runnable() { // from class: com.ttnet.org.chromium.base.JavaHandlerThread.2
            @Override // java.lang.Runnable
            public void run() {
                JavaHandlerThread.this.mThread.quit();
                JavaHandlerThreadJni.get().onLooperStopped(j2);
            }
        });
        this.mThread.getLooper().quitSafely();
    }

    private void joinThread() {
        boolean z = false;
        while (!z) {
            try {
                this.mThread.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        }
    }

    private boolean hasStarted() {
        return this.mThread.getState() != Thread.State.NEW;
    }

    private boolean isAlive() {
        return this.mThread.isAlive();
    }

    private void listenForUncaughtExceptionsForTesting() {
        this.mThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.ttnet.org.chromium.base.JavaHandlerThread.3
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                JavaHandlerThread.this.mUnhandledException = th;
            }
        });
    }

    private Throwable getUncaughtExceptionIfAny() {
        return this.mUnhandledException;
    }
}
