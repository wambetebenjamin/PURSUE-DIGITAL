package com.bytedance.pipo.iap.model;
/* loaded from: classes2.dex */
public class AbsResult {
    public static final int CODE_FAILED = 1;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_UNKNOWN = -1000;
    protected int mCode = -1000;
    protected String mDetailCode = String.valueOf(-1000);
    protected String mMessage = "";

    public AbsResult withErrorCode(int i2) {
        this.mCode = i2;
        return this;
    }

    public AbsResult withDetailCode(int i2) {
        return withDetailCode(String.valueOf(i2));
    }

    public AbsResult withDetailCode(String str) {
        if (str != null) {
            this.mDetailCode = str;
        }
        return this;
    }

    public AbsResult withMessage(String str) {
        if (str != null) {
            this.mMessage = str;
        }
        return this;
    }

    public int getCode() {
        return this.mCode;
    }

    public String getDetailCode() {
        return this.mDetailCode;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public boolean isSuccess() {
        return this.mCode == 0;
    }

    public String toString() {
        return "AbsResult{mCode=" + this.mCode + ", mDetailCode=" + this.mDetailCode + ", mMessage='" + this.mMessage + "'}";
    }
}
