package com.bytedance.common.wschannel;
/* compiled from: TransportMode.java */
/* loaded from: classes2.dex */
public enum k {
    TUDP(0),
    TLS(1),
    HTTP2(2),
    TUDP_PROXY(3),
    TLS_PROXY(4);
    

    /* renamed from: f  reason: collision with root package name */
    final int f5761f;

    public static k b() {
        return null;
    }

    k(int i2) {
        this.f5761f = i2;
    }

    public int a() {
        return this.f5761f;
    }
}
