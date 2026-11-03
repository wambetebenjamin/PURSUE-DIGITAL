package com.bytedance.apm6.memory.config;
/* compiled from: MemoryConfig.java */
/* loaded from: classes.dex */
public final class a {
    public static final double BENCHMARK_RATE = 0.8d;
    public static final long MEM_MONITOR_INTERVAL_SECONDS = 120;
    public static final double REACH_TOP_MIN_RATE = 0.5d;

    /* renamed from: a  reason: collision with root package name */
    private long f2316a;

    /* renamed from: b  reason: collision with root package name */
    private double f2317b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f2318c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2319d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f2320e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2321f;

    public a(long j2, double d2, boolean z, boolean z2, boolean z3, boolean z4) {
        if (j2 <= 0) {
            this.f2316a = 120L;
        } else {
            this.f2316a = j2;
        }
        this.f2317b = d2;
        this.f2321f = z4;
        this.f2318c = z;
        this.f2319d = z2;
        this.f2320e = z3;
    }

    public boolean isApm6SampleEnable() {
        return this.f2321f;
    }

    public double memoryTopCheckThreshold() {
        double d2 = this.f2317b;
        if (d2 > 0.5d) {
            return d2;
        }
        return 0.8d;
    }

    public long memoryCollectionInterval() {
        return this.f2316a;
    }

    public boolean isStopWhenBackground() {
        return this.f2318c;
    }

    public boolean isRealTimeMemEnable() {
        return this.f2319d;
    }

    public boolean isUploadEnable() {
        return this.f2320e;
    }

    public void setRealTimeMemEnable(boolean z) {
        this.f2319d = z;
    }

    public void setStopWhenBackground(boolean z) {
        this.f2318c = z;
    }

    public String toString() {
        return "MemoryConfig{memoryCollectionInterval=" + this.f2316a + ", memoryTopCheckThreshold=" + this.f2317b + ", isStopWhenBackground=" + this.f2318c + ", isRealTimeMemEnable=" + this.f2319d + ", isUploadEnable=" + this.f2320e + ", isApm6SampleEnable=" + this.f2321f + '}';
    }
}
