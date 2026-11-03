package com.bytedance.sdk.account.mobile.query;

import java.util.Map;
/* compiled from: SendCodeQueryObj.java */
/* loaded from: classes2.dex */
public class w extends m {
    public int checkRegister;
    public String mAuthToken;
    public int mAutoread;
    public String mCaptcha;
    public String mConfirmSwitchBindTips;
    public String mConfirmSwitchBindUrl;
    public Map mExtendInfo;
    public String mMobile;
    public String mOldMobile;
    public int mResendTime;
    public String mSharkTicket;
    public String mSmsCodeKey;
    public String mTicket;
    public int mType;
    public int mUnbindExist;
    public String mUnusableMobileTicket;

    public w(String str, String str2, int i2) {
        super(i2);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i2;
        this.mResendTime = 30;
        this.mOldMobile = "";
    }

    public w(String str, String str2, int i2, int i3, int i4) {
        super(i2);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i2;
        this.mResendTime = 30;
        this.mOldMobile = "";
        this.mUnbindExist = i3;
        this.checkRegister = i4;
    }

    public w(String str, String str2, String str3, int i2) {
        super(i2);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str3;
        this.mType = i2;
        this.mResendTime = 30;
        this.mOldMobile = str2;
    }

    public w(String str, String str2, int i2, int i3, String str3, int i4, int i5) {
        super(i2);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i2;
        this.mResendTime = 30;
        this.mOldMobile = "";
        this.mUnbindExist = i3;
        this.mTicket = str3;
        this.mAutoread = i5;
        this.checkRegister = i4;
    }

    public w(String str, String str2, int i2, int i3, String str3, int i4, int i5, String str4, String str5) {
        super(i2);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i2;
        this.mResendTime = 30;
        this.mOldMobile = "";
        this.mUnbindExist = i3;
        this.mTicket = str3;
        this.mAutoread = i5;
        this.mSharkTicket = str4;
        this.mUnusableMobileTicket = str5;
        this.checkRegister = i4;
    }

    public w(String str, String str2, int i2, int i3, String str3, int i4, int i5, String str4, String str5, Map map) {
        this(str, str2, i2, i3, str3, i4, i5, str4, str5);
        this.mExtendInfo = map;
    }

    public w(int i2, String str, String str2, int i3, int i4, String str3, String str4, String str5) {
        super(i2);
        this.checkRegister = -1;
        this.mType = i2;
        this.mMobile = str;
        this.mOldMobile = str2;
        this.checkRegister = i3;
        this.mUnbindExist = i4;
        this.mTicket = str3;
        this.mAuthToken = str4;
        this.mUnusableMobileTicket = str5;
    }

    public w(com.bytedance.sdk.account.param.a aVar) {
        this(aVar.mobile, aVar.captcha, aVar.type);
        this.mOldMobile = aVar.oldMobile;
        this.mUnbindExist = aVar.unbindExist ? 1 : 0;
        this.mTicket = aVar.ticket;
        this.checkRegister = aVar.checkRegister;
        this.mAutoread = aVar.autoRead ? 1 : 0;
        this.mSharkTicket = aVar.sharkTicket;
        this.mAuthToken = aVar.authToken;
        this.mUnusableMobileTicket = aVar.unusableMobileTicket;
        this.mExtendInfo = aVar.extraInfo;
    }
}
