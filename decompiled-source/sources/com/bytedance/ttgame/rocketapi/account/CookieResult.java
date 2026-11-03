package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class CookieResult {
    public GSDKError gsdkError;
    public boolean status;

    public CookieResult() {
    }

    public CookieResult(GSDKError gSDKError) {
        this.gsdkError = gSDKError;
    }

    public CookieResult(GSDKError gSDKError, boolean z) {
        this.gsdkError = gSDKError;
        this.status = z;
    }

    public String toString() {
        return "CookieResult{gsdkError=" + this.gsdkError + ", status=" + this.status + '}';
    }
}
