package com.bytedance.common.wschannel.utils;
/* compiled from: WsAsyncTask.java */
/* loaded from: classes2.dex */
public abstract class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final long f6016a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f6017b;

    /* renamed from: c  reason: collision with root package name */
    private long f6018c;

    public g() {
        this(0L);
    }

    public g(long j2) {
        this(j2, 0L);
    }

    public g(long j2, long j3) {
        this.f6016a = j2;
        this.f6018c = j3;
        if (j3 > 0) {
            this.f6017b = true;
        }
    }

    public final long a() {
        return this.f6016a;
    }

    public final boolean b() {
        return this.f6017b;
    }

    public final long c() {
        return this.f6018c;
    }
}
