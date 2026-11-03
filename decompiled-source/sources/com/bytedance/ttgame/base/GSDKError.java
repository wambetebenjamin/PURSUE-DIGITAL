package com.bytedance.ttgame.base;
/* loaded from: classes3.dex */
public class GSDKError {
    private String additionalInfo;
    private int code;
    private int extraErrorCode;
    private String extraErrorMessage;
    private String message;

    public boolean isSuccess() {
        return this.code == 0;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public int getExtraErrorCode() {
        return this.extraErrorCode;
    }

    public String getExtraErrorMessage() {
        return this.extraErrorMessage;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public GSDKError(int i2, String str) {
        this.code = i2;
        this.message = str;
        this.extraErrorMessage = "";
        this.additionalInfo = "";
    }

    public GSDKError(int i2, String str, int i3, String str2) {
        this(i2, str);
        this.extraErrorCode = i3;
        this.extraErrorMessage = str2;
    }

    public GSDKError(int i2, String str, int i3, String str2, String str3) {
        this.code = i2;
        this.message = str;
        this.extraErrorCode = i3;
        this.extraErrorMessage = str2;
        this.additionalInfo = str3;
    }

    public String toString() {
        return "GSDKError{errorCode=" + this.code + ", errorMessage='" + this.message + "', extraError=" + this.extraErrorCode + ", extraMessage='" + this.extraErrorMessage + "', additionalInfo='" + this.additionalInfo + "'}";
    }
}
