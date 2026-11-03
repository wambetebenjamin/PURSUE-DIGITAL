package com.bytedance.sdk.account.mobile.query;
/* compiled from: UserPasswordRegisterQueryObj.java */
/* loaded from: classes2.dex */
public class z extends m implements j {
    public String mPassword;
    public com.bytedance.sdk.account.user.c mUserInfo;
    public String mUserName;

    public z(String str, String str2) {
        super(3);
        this.mPassword = str2;
        this.mUserName = str;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
