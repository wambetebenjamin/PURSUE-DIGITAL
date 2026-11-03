package com.bytedance.sdk.account.mobile.query;

import android.text.TextUtils;
import org.json.JSONObject;
/* compiled from: MobileQueryObj.java */
/* loaded from: classes2.dex */
public abstract class m {
    public JSONObject jsonResult;
    public long mCancelApplyTime;
    public String mCancelAvatarUrl;
    public String mCancelNickName;
    public long mCancelTime;
    public String mCancelToken;
    public int mDetailErrorCode;
    public int mError;
    public String mErrorAlert;
    public String mErrorCaptcha;
    public String mErrorMsg;
    public int mScenario;

    public m() {
    }

    public m(int i2) {
        this.mScenario = i2;
    }

    public boolean isNeedShowCaptcha() {
        int i2 = this.mError;
        return i2 > 1100 && i2 < 1199;
    }

    public boolean isNeedShowPicCaptcha() {
        int i2 = this.mError;
        return (i2 == 1101 || i2 == 1102 || i2 == 1103) && !TextUtils.isEmpty(this.mErrorCaptcha);
    }

    public boolean isNeedShowSecureCaptcha() {
        int i2 = this.mError;
        return i2 == 1104 || i2 == 1105;
    }
}
