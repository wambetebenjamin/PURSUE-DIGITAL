package com.bytedance.apm.entity;
/* compiled from: TraceTimeEntity.java */
/* loaded from: classes.dex */
public class i {
    public long endTimestamp;
    public long startTimestamp;
    public String threadName;

    public i(long j2) {
        this.startTimestamp = j2;
    }

    public void appendEndTimeAndThread(long j2, String str) {
        this.endTimestamp = j2;
        this.threadName = str;
    }
}
