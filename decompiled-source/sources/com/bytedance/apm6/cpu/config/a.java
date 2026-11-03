package com.bytedance.apm6.cpu.config;
/* compiled from: CpuConfig.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final long f2022a = 120;

    /* renamed from: b  reason: collision with root package name */
    private static final long f2023b = 600;

    /* renamed from: c  reason: collision with root package name */
    private static final long f2024c = 1200;

    /* renamed from: h  reason: collision with root package name */
    private static final long f2025h = 120;

    /* renamed from: i  reason: collision with root package name */
    private static final long f2026i = 600;

    /* renamed from: g  reason: collision with root package name */
    private boolean f2030g = false;

    /* renamed from: j  reason: collision with root package name */
    private boolean f2031j = false;

    /* renamed from: d  reason: collision with root package name */
    private long f2027d = 120;

    /* renamed from: e  reason: collision with root package name */
    private long f2028e = 600;

    /* renamed from: f  reason: collision with root package name */
    private long f2029f = 1200;
    private long k = 120;
    private long l = 600;

    public long getFrontCollectInterval() {
        return this.f2027d * 1000;
    }

    public void setFrontCollectInterval(long j2) {
        this.f2027d = j2;
    }

    public long getBackCollectInterval() {
        return this.f2028e * 1000;
    }

    public void setBackCollectInterval(long j2) {
        this.f2028e = j2;
    }

    public long getMonitorInterval() {
        return this.f2029f * 1000;
    }

    public void setMonitorInterval(long j2) {
        this.f2029f = j2;
    }

    public boolean isEnableUpload() {
        return this.f2030g;
    }

    public void setEnableUpload(boolean z) {
        this.f2030g = z;
    }

    public long a() {
        return this.l * 1000;
    }

    public void a(long j2) {
        this.l = j2;
    }

    public long b() {
        return this.k * 1000;
    }

    public void b(long j2) {
        this.k = j2;
    }

    public boolean c() {
        return this.f2031j;
    }

    public void a(boolean z) {
        this.f2031j = z;
    }

    public String toString() {
        return "CpuConfig{mEnableUpload=" + this.f2030g + ", mCollectAllProcess=" + this.f2031j + ", mFrontCollectInterval=" + this.f2027d + ", mBackCollectInterval=" + this.f2028e + ", mMonitorInterval=" + this.f2029f + ", mFrontThreadCollectInterval=" + this.k + ", mBackThreadCollectInterval=" + this.l + '}';
    }
}
