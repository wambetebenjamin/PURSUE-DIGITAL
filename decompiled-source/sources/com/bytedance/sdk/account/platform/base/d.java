package com.bytedance.sdk.account.platform.base;

import android.os.Bundle;
/* compiled from: AuthorizeErrorResponse.java */
/* loaded from: classes2.dex */
public class d {
    public Bundle extras;
    public boolean isCancel;
    public String logid = "";
    public String platformErrorCode;
    public String platformErrorDetail;
    public String platformErrorMsg;

    public d(String str, String str2) {
        this.platformErrorMsg = str2;
        this.platformErrorCode = str;
    }

    public d() {
    }

    public d(int i2, String str) {
        this.platformErrorCode = String.valueOf(i2);
        this.platformErrorMsg = str;
    }

    public d(String str) {
        this.platformErrorMsg = str;
    }

    public d(int i2, String str, String str2) {
        this.platformErrorCode = String.valueOf(i2);
        this.platformErrorMsg = str;
        this.platformErrorDetail = str2;
    }

    public d(boolean z) {
        this.isCancel = z;
    }

    public String toString() {
        return "AuthorizeErrorResponse{logid='" + this.logid + "', isCancel=" + this.isCancel + ", platformErrorCode='" + this.platformErrorCode + "', platformErrorMsg='" + this.platformErrorMsg + "', platformErrorDetail='" + this.platformErrorDetail + "', extras=" + this.extras + '}';
    }
}
