package com.bytedance.frameworks.baselib.network.http.exception;

import java.io.IOException;
/* compiled from: NotAllowApiHttpException.java */
/* loaded from: classes2.dex */
public class f extends IOException {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7837a = -5588326043064908201L;

    public f() {
    }

    public f(String str) {
        super(str);
    }

    public f(Throwable th) {
        initCause(th);
    }

    public f(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
