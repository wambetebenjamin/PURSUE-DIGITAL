package com.bytedance.frameworks.baselib.network.http.ok3.impl;
/* compiled from: OkHttp3RequestLog.java */
/* loaded from: classes2.dex */
enum r {
    NOT_SET(0),
    SERVE_FROM_CACHE(2),
    SERVE_FROM_PREFER_ADDR(5),
    SERVE_FROM_HTTP_DNS_JOB(6),
    SERVE_FROM_PROC_DNS_JOB(8),
    SERVE_FROM_HARDCODE_HOSTS(9),
    SERVE_FROM_BATCH_PRELOAD(19),
    SERVE_FROM_ASYNC_BATCH_PRELOAD(20);
    

    /* renamed from: i  reason: collision with root package name */
    final int f8149i;

    r(int i2) {
        this.f8149i = i2;
    }
}
