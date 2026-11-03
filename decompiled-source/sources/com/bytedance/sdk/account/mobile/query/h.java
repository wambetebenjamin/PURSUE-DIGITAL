package com.bytedance.sdk.account.mobile.query;

import java.util.Map;
/* compiled from: EmailRegisterVerifyQueryObj.java */
/* loaded from: classes2.dex */
public class h extends m implements j {
    public String mCode;
    public String mEmail;
    public Map mExtendInfo;
    public int mType;
    public com.bytedance.sdk.account.user.c mUserInfo;
    public String mVersion;

    public h(String str, String str2, int i2, Map map, String str3) {
        this.mEmail = str;
        this.mCode = str2;
        this.mType = i2;
        this.mExtendInfo = map;
        this.mVersion = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
