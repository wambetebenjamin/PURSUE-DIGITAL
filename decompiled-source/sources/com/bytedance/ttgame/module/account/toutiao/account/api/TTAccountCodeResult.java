package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.rocketapi.account.AccountCodeResult;
/* loaded from: classes4.dex */
public class TTAccountCodeResult {
    public String accountCode;
    public GSDKError gsdkError;

    public TTAccountCodeResult(GSDKError gSDKError) {
        this(gSDKError, "");
    }

    public TTAccountCodeResult(GSDKError gSDKError, String str) {
        this.gsdkError = gSDKError;
        this.accountCode = str;
    }

    public AccountCodeResult toAccountCodeResult() {
        return new AccountCodeResult(this.gsdkError, this.accountCode);
    }
}
