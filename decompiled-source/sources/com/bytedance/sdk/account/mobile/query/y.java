package com.bytedance.sdk.account.mobile.query;
/* compiled from: UserPasswordLoginQueryObj.java */
/* loaded from: classes2.dex */
public class y extends m implements j {
    public String mAccount;
    public String mCaptcha;
    public String mEmail;
    public String mMobile;
    public String mPassword;
    public int mScene;
    public String mToken;
    public com.bytedance.sdk.account.user.c mUserInfo;
    public String mUserName;

    public y(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2) {
        super(7);
        this.mEmail = str;
        this.mPassword = str5;
        this.mCaptcha = str7;
        this.mUserName = str2;
        this.mMobile = str3;
        this.mAccount = str4;
        this.mPassword = str5;
        this.mToken = str6;
        this.mScene = i2;
    }

    public y(com.bytedance.sdk.account.param.b bVar) {
        super(7);
        this.mEmail = bVar.f12696a;
        this.mPassword = bVar.f12702g;
        this.mCaptcha = null;
        this.mUserName = bVar.f12698c;
        this.mMobile = bVar.f12697b;
        this.mAccount = bVar.f12699d;
        this.mToken = bVar.f12700e;
        this.mScene = bVar.f12701f;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
