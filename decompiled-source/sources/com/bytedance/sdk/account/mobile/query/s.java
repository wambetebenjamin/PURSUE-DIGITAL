package com.bytedance.sdk.account.mobile.query;
/* compiled from: QuickLoginQueryObj.java */
/* loaded from: classes2.dex */
public class s extends m implements j {
    public Integer mAuthOpposite;
    public String mCaptcha;
    public String mCode;
    public String mMobile;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public s(String str, String str2, Integer num, String str3) {
        super(24);
        this.mMobile = str;
        this.mCode = str2;
        this.mAuthOpposite = num;
        this.mCaptcha = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
