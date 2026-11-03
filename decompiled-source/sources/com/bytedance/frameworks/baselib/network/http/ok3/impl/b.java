package com.bytedance.frameworks.baselib.network.http.ok3.impl;
/* compiled from: OkHttp3RequestLog.java */
/* loaded from: classes2.dex */
enum b {
    CONNECTION_INFO_UNKNOWN(0),
    CONNECTION_INFO_HTTP1_1(1),
    CONNECTION_INFO_DEPRECATED_SPDY3(3),
    CONNECTION_INFO_HTTP2(4),
    CONNECTION_INFO_QUIC_UNKNOWN_VERSION(5),
    CONNECTION_INFO_HTTP1_0(9);
    

    /* renamed from: g  reason: collision with root package name */
    final int f7964g;

    b(int i2) {
        this.f7964g = i2;
    }
}
