package com.bytedance.applog.priority;
/* loaded from: classes.dex */
public class PriorityHttpResponse {
    private final int code;
    private final String data;
    private final String errMsg;

    public PriorityHttpResponse(int i2, String str, String str2) {
        this.code = i2;
        this.errMsg = str;
        this.data = str2;
    }

    public String getData() {
        return this.data;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        return "code: " + this.code + ", errMsg: " + this.errMsg + ", data: " + this.data;
    }
}
