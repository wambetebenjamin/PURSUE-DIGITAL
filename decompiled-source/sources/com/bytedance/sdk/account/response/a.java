package com.bytedance.sdk.account.response;

import com.bytedance.sdk.account.d;
import com.bytedance.sdk.account.mobile.query.m;
import org.json.JSONObject;
/* compiled from: MobileResponseData.java */
/* loaded from: classes2.dex */
public class a extends d {
    public long cancelApplyTime;
    public String cancelAvatarUrl;
    public String cancelNickName;
    public long cancelTime;
    public String cancelToken;
    public String errorAlert;
    public String errorCaptcha;
    public JSONObject jsonResult;

    public a(m mVar) {
        this.errorCaptcha = mVar.mErrorCaptcha;
        this.errorAlert = mVar.mErrorAlert;
        this.jsonResult = mVar.jsonResult;
        this.cancelToken = mVar.mCancelToken;
        this.cancelApplyTime = mVar.mCancelApplyTime;
        this.cancelTime = mVar.mCancelTime;
        this.cancelNickName = mVar.mCancelNickName;
        this.cancelAvatarUrl = mVar.mCancelAvatarUrl;
    }

    public String toString() {
        return "MobileSdkResponse{errorCaptcha='" + this.errorCaptcha + "', errorAlert='" + this.errorAlert + "', jsonResult=" + this.jsonResult + ", cancelToken='" + this.cancelToken + "', cancelApplyTime=" + this.cancelApplyTime + ", cancelTime=" + this.cancelTime + ", cancelNickName='" + this.cancelNickName + "', cancelAvatarUrl='" + this.cancelAvatarUrl + "'}";
    }
}
