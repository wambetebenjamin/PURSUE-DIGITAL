package com.bytedance.framwork.core.monitor.internal;
/* compiled from: HttpResponseException.java */
@Deprecated
/* loaded from: classes2.dex */
public class a extends Exception {
    public String message;
    public int statusCode;

    public a(int i2, String str) {
        this.statusCode = i2;
        this.message = str;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMsg() {
        return this.message;
    }
}
