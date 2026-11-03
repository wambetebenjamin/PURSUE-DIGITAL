package com.bytedance.pipeline.exception;
/* compiled from: RequestInterceptException.java */
/* loaded from: classes2.dex */
public class e extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    private int f10013a;

    public e(String str, Throwable th) {
        super(str, th);
    }

    public e(int i2, String str, Throwable th) {
        super(str + ":[code:" + i2 + "]", th);
        this.f10013a = i2;
    }

    public int a() {
        return this.f10013a;
    }
}
