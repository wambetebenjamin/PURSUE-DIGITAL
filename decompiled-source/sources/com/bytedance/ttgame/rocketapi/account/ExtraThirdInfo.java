package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class ExtraThirdInfo {
    private String accessToken;
    public GSDKError gsdkError = new GSDKError(0, "success");
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String toString() {
        return "ExtraThirdInfo{userId='" + this.userId + "', accessToken='" + this.accessToken + "', gsdkError=" + this.gsdkError + '}';
    }
}
