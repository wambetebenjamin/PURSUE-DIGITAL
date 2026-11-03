package com.bytedancehttpdns.httpdns;
/* loaded from: classes8.dex */
public class DnsTaskInfo {
    private final long mDuration;
    private final int mNetError;
    private final TaskInfoSource mType;

    /* loaded from: classes8.dex */
    public enum TaskInfoSource {
        HTTPDNS(0),
        LOCALDNS(1);
        
        final int mValue;

        TaskInfoSource(int i2) {
            this.mValue = i2;
        }
    }

    public DnsTaskInfo(long j2, TaskInfoSource taskInfoSource, int i2) {
        this.mDuration = j2;
        this.mType = taskInfoSource;
        this.mNetError = i2;
    }

    public int getNetError() {
        return this.mNetError;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public int getType() {
        return this.mType.mValue;
    }
}
