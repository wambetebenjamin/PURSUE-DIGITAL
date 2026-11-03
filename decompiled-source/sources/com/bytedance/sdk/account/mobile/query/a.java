package com.bytedance.sdk.account.mobile.query;

import java.util.Map;
/* compiled from: BindLoginObj.java */
/* loaded from: classes2.dex */
public class a extends m implements j {
    public String mCaptcha;
    public String mCode;
    public Map mExtendInfo;
    public String mMobile;
    public com.bytedance.sdk.account.user.c mUserInfo;
    public String profile_key;

    public a(String str, String str2, String str3, String str4) {
        super(7);
        this.mMobile = str;
        this.mCode = str2;
        this.mCaptcha = str4;
        this.profile_key = str3;
    }

    public a(String str, String str2, String str3, String str4, Map map) {
        this(str, str2, str3, str4);
        this.mExtendInfo = map;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
