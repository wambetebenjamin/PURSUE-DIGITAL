package com.facebook.react.bridge;
/* loaded from: classes8.dex */
public abstract class GuardedRunnable implements Runnable {
    private final NativeModuleCallExceptionHandler mExceptionHandler;

    public abstract void runGuarded();

    @Deprecated
    public GuardedRunnable(ReactContext reactContext) {
        this(reactContext.getExceptionHandler());
    }

    public GuardedRunnable(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mExceptionHandler = nativeModuleCallExceptionHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            runGuarded();
        } catch (RuntimeException e2) {
            try {
                this.mExceptionHandler.handleException(e2);
            } catch (Exception unused) {
            }
        }
    }
}
