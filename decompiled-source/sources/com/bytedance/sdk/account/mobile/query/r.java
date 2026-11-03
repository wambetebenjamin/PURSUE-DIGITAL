package com.bytedance.sdk.account.mobile.query;
/* compiled from: QuickLoginOnlyQueryObj.java */
/* loaded from: classes2.dex */
public class r extends m implements j {
    public String mCaptcha;
    public String mCode;
    public String mMobile;
    public String mSmsCodekey;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public r(String str, String str2, String str3) {
        super(24);
        this.mMobile = str;
        this.mCode = str2;
        this.mCaptcha = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
