package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class AccountCodeResult {
    public String accountCode;
    public GSDKError gsdkError;

    public AccountCodeResult(GSDKError gSDKError) {
        this(gSDKError, "");
    }

    public AccountCodeResult(GSDKError gSDKError, String str) {
        this.gsdkError = gSDKError;
        this.accountCode = str;
    }

    public String toString() {
        return "AccountCodeResult{gsdkError=" + this.gsdkError + ", accountCode='" + this.accountCode + '}';
    }
}
