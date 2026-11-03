package com.bytedance.sdk.account.mobile.query;
/* compiled from: BindMobileQueryObj.java */
/* loaded from: classes2.dex */
public class b extends m implements j {
    public String mCaptcha;
    public String mCode;
    public String mMobile;
    public String mPassword;
    public int mUnbindExist;
    public com.bytedance.sdk.account.user.c mUserInfo;
    public String notLoginTicket;
    public String verifyTicket;

    public b(String str, String str2, String str3, String str4) {
        super(10);
        this.mMobile = str;
        this.mCode = str2;
        this.mCaptcha = str4;
        this.mPassword = str3;
    }

    public b(String str, String str2, String str3, String str4, int i2) {
        super(10);
        this.mMobile = str;
        this.mCode = str2;
        this.mCaptcha = str4;
        this.mPassword = str3;
        this.mUnbindExist = i2;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
