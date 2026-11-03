package com.bytedance.frameworks.baselib.network.http.exception;

import java.io.IOException;
/* compiled from: NotAllowUseNetworkException.java */
/* loaded from: classes2.dex */
public class g extends IOException {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7838a = -210264743726619965L;

    public g() {
    }

    public g(String str) {
        super(str);
    }

    public g(Throwable th) {
        initCause(th);
    }

    public g(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
