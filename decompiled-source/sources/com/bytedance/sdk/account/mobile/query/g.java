package com.bytedance.sdk.account.mobile.query;
/* compiled from: EmailRegisterQueryObj.java */
/* loaded from: classes2.dex */
public class g extends m implements j {
    public String mCode;
    public String mEmail;
    public String mPassword;
    public String mRecaptchaToken;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public g(String str, String str2, String str3, String str4) {
        this.mEmail = str;
        this.mPassword = str2;
        this.mCode = str3;
        this.mRecaptchaToken = str4;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
