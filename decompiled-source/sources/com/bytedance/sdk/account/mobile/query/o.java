package com.bytedance.sdk.account.mobile.query;
/* compiled from: OneBindMobileQueryObj.java */
/* loaded from: classes2.dex */
public class o extends m implements j {
    public String mConfirmSwitchBindTips;
    public String mConfirmSwitchBindUrl;
    public String mFrom;
    public String mPassword;
    public String mToken;
    public int mUnbindExist;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public o(String str, String str2, String str3, int i2) {
        super(10);
        this.mToken = str;
        this.mFrom = str2;
        this.mPassword = str3;
        this.mUnbindExist = i2;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
