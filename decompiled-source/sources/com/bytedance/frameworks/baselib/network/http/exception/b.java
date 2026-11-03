package com.bytedance.frameworks.baselib.network.http.exception;

import com.bytedance.retrofit2.ttnet.TTNetExceptionStorage;
/* compiled from: ClientProtocolException.java */
/* loaded from: classes2.dex */
public class b extends TTNetExceptionStorage {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7830a = -5596590843227115865L;

    public b() {
    }

    public b(String str) {
        super(str);
    }

    public b(Throwable th) {
        initCause(th);
    }

    public b(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
