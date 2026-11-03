package com.bytedance.frameworks.baselib.network.queryfilter;
/* compiled from: QueryActionInfo.java */
/* loaded from: classes2.dex */
final class a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f8387a = false;

    /* renamed from: b  reason: collision with root package name */
    private int f8388b = -1;

    /* renamed from: c  reason: collision with root package name */
    private long f8389c = -1;

    /* renamed from: d  reason: collision with root package name */
    private long f8390d = -1;

    public long a() {
        long j2 = this.f8389c;
        if (j2 >= 0) {
            long j3 = this.f8390d;
            if (j3 < 0 || j2 > j3) {
                return -1L;
            }
            return j3 - j2;
        }
        return -1L;
    }

    public boolean b() {
        return this.f8387a;
    }

    public void a(boolean z) {
        this.f8387a = z;
    }

    public int c() {
        return this.f8388b;
    }

    public void a(int i2) {
        this.f8388b = i2;
    }

    public void a(long j2) {
        this.f8389c = j2;
    }

    public void b(long j2) {
        this.f8390d = j2;
    }
}
