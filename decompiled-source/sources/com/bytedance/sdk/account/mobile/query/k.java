package com.bytedance.sdk.account.mobile.query;
/* compiled from: LoginQueryObj.java */
/* loaded from: classes2.dex */
public class k extends m implements j {
    public String mCaptcha;
    public String mMobile;
    public String mPassword;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public k(String str, String str2, String str3) {
        super(7);
        this.mMobile = str;
        this.mPassword = str2;
        this.mCaptcha = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
