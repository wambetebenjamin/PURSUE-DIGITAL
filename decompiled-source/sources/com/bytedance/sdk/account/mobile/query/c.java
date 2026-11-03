package com.bytedance.sdk.account.mobile.query;

import java.util.Map;
/* compiled from: ChangeMobileNumQueryObj.java */
/* loaded from: classes2.dex */
public class c extends m implements j {
    public String mCaptcha;
    public String mCode;
    public Map mExtendInfo;
    public String mMobile;
    public String mTicket;
    public com.bytedance.sdk.account.user.c mUserInfo;

    public c(String str, String str2, String str3) {
        super(20);
        this.mMobile = str;
        this.mCaptcha = str3;
        this.mCode = str2;
    }

    public c(String str, String str2, String str3, String str4) {
        super(20);
        this.mMobile = str;
        this.mCaptcha = str3;
        this.mCode = str2;
        this.mTicket = str4;
    }

    public c(String str, String str2, String str3, String str4, Map map) {
        this(str, str2, str3, str4);
        this.mExtendInfo = map;
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
