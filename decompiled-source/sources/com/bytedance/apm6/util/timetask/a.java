package com.bytedance.apm6.util.timetask;
/* compiled from: AsyncTask.java */
/* loaded from: classes.dex */
public abstract class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private long f2413a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f2414b;

    /* renamed from: c  reason: collision with root package name */
    private long f2415c;

    public a() {
        this(0L);
    }

    public a(long j2) {
        this(j2, 0L);
    }

    public a(long j2, long j3) {
        this.f2413a = j2;
        this.f2415c = j3;
        if (j3 > 0) {
            this.f2414b = true;
        }
    }

    public long getInitialDelay() {
        return this.f2413a;
    }

    public final boolean isLoop() {
        return this.f2414b;
    }

    public final long getLoopInterval() {
        return this.f2415c;
    }

    public void a(long j2, long j3) {
        this.f2413a = j2;
        this.f2415c = j3;
        if (j3 > 0) {
            this.f2414b = true;
        }
    }
}
