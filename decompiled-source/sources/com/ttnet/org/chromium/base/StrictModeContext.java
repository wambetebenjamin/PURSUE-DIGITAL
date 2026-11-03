package com.ttnet.org.chromium.base;

import android.os.Build;
import android.os.StrictMode;
import java.io.Closeable;
/* loaded from: classes9.dex */
public final class StrictModeContext implements Closeable {
    private final StrictMode.ThreadPolicy mThreadPolicy;
    private final StrictMode.VmPolicy mVmPolicy;

    private StrictModeContext(StrictMode.ThreadPolicy threadPolicy, StrictMode.VmPolicy vmPolicy) {
        this.mThreadPolicy = threadPolicy;
        this.mVmPolicy = vmPolicy;
    }

    private StrictModeContext(StrictMode.ThreadPolicy threadPolicy) {
        this(threadPolicy, null);
    }

    private StrictModeContext(StrictMode.VmPolicy vmPolicy) {
        this(null, vmPolicy);
    }

    public static StrictModeContext allowAllVmPolicies() {
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
        return new StrictModeContext(vmPolicy);
    }

    public static StrictModeContext allowAllThreadPolicies() {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        return new StrictModeContext(threadPolicy);
    }

    public static StrictModeContext allowDiskWrites() {
        return new StrictModeContext(StrictMode.allowThreadDiskWrites());
    }

    public static StrictModeContext allowDiskReads() {
        return new StrictModeContext(StrictMode.allowThreadDiskReads());
    }

    public static StrictModeContext allowSlowCalls() {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitCustomSlowCalls().build());
        return new StrictModeContext(threadPolicy);
    }

    public static StrictModeContext allowUnbufferedIo() {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        if (Build.VERSION.SDK_INT >= 26) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        }
        return new StrictModeContext(threadPolicy);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        StrictMode.ThreadPolicy threadPolicy = this.mThreadPolicy;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        StrictMode.VmPolicy vmPolicy = this.mVmPolicy;
        if (vmPolicy != null) {
            StrictMode.setVmPolicy(vmPolicy);
        }
    }
}
