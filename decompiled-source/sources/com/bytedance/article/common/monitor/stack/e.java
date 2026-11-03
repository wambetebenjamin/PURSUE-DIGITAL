package com.bytedance.article.common.monitor.stack;
/* compiled from: HttpResponseException.java */
/* loaded from: classes.dex */
public class e extends Exception {
    public String message;
    public int statusCode;

    public e(int i2, String str) {
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
