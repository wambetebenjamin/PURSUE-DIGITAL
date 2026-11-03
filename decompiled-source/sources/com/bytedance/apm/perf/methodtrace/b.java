package com.bytedance.apm.perf.methodtrace;
/* compiled from: ThreadTimeItem.java */
/* loaded from: classes.dex */
public class b {
    public int threadId;
    public String threadName;
    public long threadTime;
    public float threadUsage = 0.0f;

    public b(int i2, String str, long j2) {
        this.threadId = i2;
        this.threadName = str;
        this.threadTime = j2;
    }

    public void a(long j2) {
        this.threadTime = j2;
    }
}
