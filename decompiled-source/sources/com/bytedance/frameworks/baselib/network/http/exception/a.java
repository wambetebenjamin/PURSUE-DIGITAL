package com.bytedance.frameworks.baselib.network.http.exception;

import java.io.IOException;
/* compiled from: CdnCacheVerifyException.java */
/* loaded from: classes2.dex */
public class a extends IOException {
    public a() {
    }

    public a(String str) {
        super(str);
    }

    public a(Throwable th) {
        initCause(th);
    }

    public a(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
