package com.bytedance.apm6.fd.config;
/* compiled from: FdConfig.java */
/* loaded from: classes.dex */
public class a {
    public static final long DEFAULT_COLLECT_INTERVAL_MINUTE = 10;
    public static final int DEFAULT_FD_COUNT_THRESHOLD = 800;

    /* renamed from: a  reason: collision with root package name */
    private long f2184a;

    /* renamed from: b  reason: collision with root package name */
    private long f2185b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f2186c;

    public a(long j2, long j3, boolean z) {
        this.f2184a = j2;
        this.f2185b = j3;
        this.f2186c = z;
    }

    public long getFdCountThreshold() {
        return this.f2184a;
    }

    public long getCollectIntervalMs() {
        return this.f2185b;
    }

    public boolean isSampled() {
        return this.f2186c;
    }

    public String toString() {
        return "FdConfig{fdCountThreshold=" + this.f2184a + ", collectIntervalMs=" + this.f2185b + ", isSampled=" + this.f2186c + '}';
    }
}
