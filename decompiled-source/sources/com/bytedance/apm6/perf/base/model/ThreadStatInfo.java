package com.bytedance.apm6.perf.base.model;
/* loaded from: classes.dex */
public class ThreadStatInfo {
    public long threadCpuTime;
    public String threadName;

    public ThreadStatInfo(String str, long j2) {
        this.threadName = str;
        this.threadCpuTime = j2;
    }
}
