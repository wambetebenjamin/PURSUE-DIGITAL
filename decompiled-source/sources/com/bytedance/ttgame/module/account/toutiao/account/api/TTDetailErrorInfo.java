package com.bytedance.ttgame.module.account.toutiao.account.api;
/* loaded from: classes4.dex */
public class TTDetailErrorInfo {
    public int detailErrorCode;
    public String detailErrorMsg;
    public int errorCode;
    public String errorMsg;

    public TTDetailErrorInfo(int i2, String str, int i3, String str2) {
        this.errorCode = i2;
        this.errorMsg = str;
        this.detailErrorCode = i3;
        this.detailErrorMsg = str2;
    }

    public String toString() {
        return "DetailErrorInfo{thirdErrorCode=" + this.errorCode + ", thirdErrorMsg='" + this.errorMsg + "', thirdDetailErrorCode=" + this.detailErrorCode + ", thirdDetailErrorMsg='" + this.detailErrorMsg + "'}";
    }
}
