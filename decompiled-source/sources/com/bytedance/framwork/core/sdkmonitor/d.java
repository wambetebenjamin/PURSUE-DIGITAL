package com.bytedance.framwork.core.sdkmonitor;
/* compiled from: HttpResponseException.java */
/* loaded from: classes2.dex */
public class d extends Exception {
    public String message;
    public int statusCode;

    public d(int i2, String str) {
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
