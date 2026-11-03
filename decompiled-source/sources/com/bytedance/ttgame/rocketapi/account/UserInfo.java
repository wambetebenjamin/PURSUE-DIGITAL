package com.bytedance.ttgame.rocketapi.account;
/* loaded from: classes7.dex */
public class UserInfo {
    private String accessToken;
    private ExtraData extraData;

    public String getToken() {
        return this.accessToken;
    }

    public void setToken(String str) {
        this.accessToken = str;
    }

    public ExtraData getExtraData() {
        return this.extraData;
    }

    public void setExtraData(ExtraData extraData) {
        this.extraData = extraData;
    }

    public String toString() {
        return "UserInfo{accessToken='" + this.accessToken + "', extraData=" + this.extraData + '}';
    }
}
