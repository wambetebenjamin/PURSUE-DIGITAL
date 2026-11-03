package com.bytedance.sdk.account.mobile.query;

import java.util.Map;
/* compiled from: QuickLoginContinueQueryObj.java */
/* loaded from: classes2.dex */
public class q extends m implements j {
    public Map mExtendInfo;
    public String mMobile;
    public String mSmsCodekey;
    public com.bytedance.sdk.account.user.c mUserInfo;
    public int mVcdAuth;

    public q(String str, String str2) {
        super(24);
        this.mMobile = str;
        this.mSmsCodekey = str2;
    }

    public q(String str, String str2, int i2, Map map) {
        super(24);
        this.mMobile = str;
        this.mSmsCodekey = str2;
        this.mVcdAuth = i2;
        this.mExtendInfo = map;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
