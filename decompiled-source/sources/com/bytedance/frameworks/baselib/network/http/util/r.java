package com.bytedance.frameworks.baselib.network.http.util;
/* compiled from: AsyncTask.java */
/* loaded from: classes2.dex */
public abstract class r implements Runnable {
    private final long mInitialDelay;
    private boolean mIsLoop;
    private long mLoopInterval;
    private final String mTaskIdentifier;

    public r() {
        this(0L);
    }

    public r(long j2) {
        this(j2, 0L, "");
    }

    public r(long j2, String str) {
        this(j2, 0L, str);
    }

    public r(long j2, long j3, String str) {
        this.mInitialDelay = j2;
        this.mLoopInterval = j3;
        this.mTaskIdentifier = str;
        if (j3 > 0) {
            this.mIsLoop = true;
        }
    }

    public final long getInitialDelay() {
        return this.mInitialDelay;
    }

    public final boolean isLoop() {
        return this.mIsLoop;
    }

    public final long getLoopInterval() {
        return this.mLoopInterval;
    }

    public final String getTaskIdentifier() {
        return this.mTaskIdentifier;
    }
}
