package com.bytedance.sdk.account.api.response;

import org.json.JSONObject;
/* compiled from: LoginByTicketResponse.java */
/* loaded from: classes2.dex */
public class ae extends com.bytedance.sdk.account.api.call.b implements com.bytedance.sdk.account.mobile.query.j {
    public long mCancelApplyTime;
    public String mCancelAvatarUrl;
    public String mCancelNickName;
    public long mCancelTime;
    public String mCancelToken;
    public String mErrorCaptcha;
    public String mSmsCodeKey;
    public com.bytedance.sdk.account.user.c mUserInfo;
    public JSONObject rawData;

    public ae(boolean z, int i2) {
        super(z, i2);
    }

    @Override // com.bytedance.sdk.account.mobile.query.j
    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.mUserInfo;
    }
}
