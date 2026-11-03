package com.bytedancehttpdns.httpdns;
/* loaded from: classes8.dex */
public class HttpDnsResponse {
    private String mResponse = null;
    private int mNetErrorCode = 0;
    private int mResponseCode = 0;

    public void setResponse(int i2, String str) {
        this.mResponseCode = i2;
        this.mResponse = str;
    }

    public void setNetErrorCode(int i2) {
        this.mNetErrorCode = i2;
    }

    public String getResponse() {
        return this.mResponse;
    }

    public int getNetErrorCode() {
        return this.mNetErrorCode;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }
}
