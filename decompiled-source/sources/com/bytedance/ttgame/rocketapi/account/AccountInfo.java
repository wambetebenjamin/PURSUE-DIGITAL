package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class AccountInfo {
    public String account;
    public long changeAccountCodeTime;
    public String email;
    public GSDKError gsdkError;
    public boolean hasPassword;
    public long lastChangeAccountCodeTime;
    public String mobile;

    public AccountInfo(String str, String str2, String str3, boolean z, long j2, long j3, GSDKError gSDKError) {
        this.account = str;
        this.email = str2;
        this.mobile = str3;
        this.hasPassword = z;
        this.gsdkError = gSDKError;
        this.changeAccountCodeTime = j2;
        this.lastChangeAccountCodeTime = j3;
    }

    public AccountInfo(GSDKError gSDKError) {
        this.gsdkError = gSDKError;
    }
}
