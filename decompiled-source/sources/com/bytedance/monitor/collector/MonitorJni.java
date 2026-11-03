package com.bytedance.monitor.collector;

import com.bytedance.apm6.perf.base.model.ThreadStatInfo;
/* loaded from: classes2.dex */
public class MonitorJni {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void disableBinderHook();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void disableLock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doCollect();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doDestroy();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doDisableAtrace();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String doDumpAtrace();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String doDumpAtraceRange(long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doDumpAtraceRangeToALog(long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String doDumpNativeStack(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doEnableAtrace(int i2, long j2);

    public static native long doGetAppCpuTime();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long doGetCpuTime(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String doGetSchedInfo(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doInit();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doSetDebugMode(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doStart();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void doStop();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void dumpProcInfoTimeRangeToALog(long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void enableBinderHook();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void enableLock(long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getProcCGroup(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getProcInfoTimeRange(long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getProcInfos();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native ThreadStatInfo getThreadStatInfo(int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getTotalCPUTimeByTimeInStat(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void keepProcHyperOpen(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean readProcFile(String str, int[] iArr, Object[] objArr, long[] jArr, float[] fArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setAlogInstance(long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setBufferSize(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void startHyperMonitor();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void stopHyperMonitor();
}
