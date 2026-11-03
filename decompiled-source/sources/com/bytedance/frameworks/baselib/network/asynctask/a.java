package com.bytedance.frameworks.baselib.network.asynctask;

import java.util.concurrent.TimeUnit;
/* compiled from: NetworkAsyncTask.java */
/* loaded from: classes2.dex */
public abstract class a implements Runnable {
    private long mInitialDelay;
    private long mLoopInterval;
    private c mTaskType;
    private TimeUnit mTimeUnit;

    public a() {
        this.mInitialDelay = 0L;
        this.mLoopInterval = 0L;
        this.mTimeUnit = TimeUnit.MILLISECONDS;
    }

    public a(long j2) {
        this.mInitialDelay = 0L;
        this.mLoopInterval = 0L;
        this.mTimeUnit = TimeUnit.MILLISECONDS;
        this.mLoopInterval = j2;
    }

    public a(long j2, long j3) {
        this(j3);
        this.mInitialDelay = j2;
    }

    public a(long j2, long j3, TimeUnit timeUnit) {
        this(j2, j3);
        this.mTimeUnit = timeUnit;
    }

    public boolean isLoop() {
        return this.mLoopInterval > 0;
    }

    public long getInitialDelay() {
        return this.mInitialDelay;
    }

    public long getLoopInterval() {
        return this.mLoopInterval;
    }

    public TimeUnit getTimeUnit() {
        return this.mTimeUnit;
    }

    public void setTaskType(c cVar) {
        this.mTaskType = cVar;
    }

    public c getTaskType() {
        return this.mTaskType;
    }
}
