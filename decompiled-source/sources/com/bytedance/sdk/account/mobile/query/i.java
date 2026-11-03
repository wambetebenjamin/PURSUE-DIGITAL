package com.bytedance.sdk.account.mobile.query;

import java.util.Map;
/* compiled from: EmailSendCodeQueryObj.java */
/* loaded from: classes2.dex */
public class i extends m {
    public String mCaptcha;
    public String mEmail;
    public Map mExtendInfo;
    public String mNext;
    public String mPassWord;
    public int mType;
    public String mVersion;

    public i(String str, String str2, String str3, int i2, String str4, Map map, String str5) {
        this.mEmail = str;
        this.mCaptcha = str2;
        this.mPassWord = str3;
        this.mType = i2;
        this.mNext = str4;
        this.mExtendInfo = map;
        this.mVersion = str5;
    }
}
