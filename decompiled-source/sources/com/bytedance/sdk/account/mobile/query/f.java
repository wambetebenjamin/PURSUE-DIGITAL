package com.bytedance.sdk.account.mobile.query;
/* compiled from: EmailLoginQueryObj.java */
/* loaded from: classes2.dex */
public class f extends m implements j {
    public String mCaptcha;
    public String mEmail;
    public String mPassword;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public f(String str, String str2, String str3) {
        this.mEmail = str;
        this.mPassword = str2;
        this.mCaptcha = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
