package com.bytedance.frameworks.baselib.network.connectionclass;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: ExponentialGeometricAverage.java */
/* loaded from: classes2.dex */
class g {

    /* renamed from: a  reason: collision with root package name */
    private final double f7503a;

    /* renamed from: b  reason: collision with root package name */
    private final int f7504b;

    /* renamed from: c  reason: collision with root package name */
    private double f7505c = -1.0d;

    /* renamed from: d  reason: collision with root package name */
    private int f7506d;

    public g(double d2) {
        this.f7503a = d2;
        this.f7504b = d2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Integer.MAX_VALUE : (int) Math.ceil(1.0d / d2);
    }

    public void a(double d2) {
        double d3 = 1.0d - this.f7503a;
        int i2 = this.f7506d;
        if (i2 > this.f7504b) {
            this.f7505c = Math.exp((d3 * Math.log(this.f7505c)) + (this.f7503a * Math.log(d2)));
        } else if (i2 > 0) {
            double d4 = (d3 * i2) / (i2 + 1.0d);
            this.f7505c = Math.exp((d4 * Math.log(this.f7505c)) + ((1.0d - d4) * Math.log(d2)));
        } else {
            this.f7505c = d2;
        }
        this.f7506d++;
    }

    public double a() {
        return this.f7505c;
    }

    public void b() {
        this.f7505c = -1.0d;
        this.f7506d = 0;
    }
}
