package com.bytedance.sdk.account.mobile.query;
/* compiled from: ResetPasswordQueryObj.java */
/* loaded from: classes2.dex */
public class v extends m implements j {
    public String mCaptcha;
    public String mCode;
    public String mMobile;
    public String mPassword;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public v(String str, String str2, String str3, String str4) {
        super(6);
        this.mMobile = str;
        this.mCode = str2;
        this.mPassword = str3;
        this.mCaptcha = str4;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
