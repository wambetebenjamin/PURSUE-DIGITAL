package com.bytedance.sdk.account.mobile.query;
/* compiled from: OneForceBindMobileQueryObj.java */
/* loaded from: classes2.dex */
public class p extends m implements j {
    public int mBindLogicType;
    public String mFrom;
    public String mProfileKey;
    public String mResultCode;
    public String mToken;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public p(String str, String str2, String str3, int i2) {
        super(10);
        this.mToken = str;
        this.mFrom = str2;
        this.mProfileKey = str3;
        this.mBindLogicType = i2;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
