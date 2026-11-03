package com.bytedance.sdk.account.response;

import com.bytedance.sdk.account.mobile.query.w;
/* compiled from: SendCodeResponseData.java */
/* loaded from: classes2.dex */
public class b extends a {
    public String confirmSwitchBindTips;
    public String confirmSwitchBindUrl;
    public int resendTime;

    public b(w wVar) {
        super(wVar);
        this.resendTime = wVar.mResendTime;
        this.confirmSwitchBindTips = wVar.mConfirmSwitchBindTips;
        this.confirmSwitchBindUrl = wVar.mConfirmSwitchBindUrl;
    }

    @Override // com.bytedance.sdk.account.response.a
    public String toString() {
        return super.toString() + "\nSendCodeSdkResponse{resendTime=" + this.resendTime + ", confirmSwitchBindTips='" + this.confirmSwitchBindTips + "', confirmSwitchBindUrl='" + this.confirmSwitchBindUrl + "'}";
    }
}
