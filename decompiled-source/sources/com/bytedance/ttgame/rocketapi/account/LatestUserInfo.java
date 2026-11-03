package com.bytedance.ttgame.rocketapi.account;
/* loaded from: classes7.dex */
public class LatestUserInfo {
    public GameUserInfo gameUserInfo;
    public String sdkOpenId;
    public String token;

    public String toString() {
        return "LatestUserInfo{token='" + this.token + "', sdkOpenId='" + this.sdkOpenId + "', gameUserInfo=" + this.gameUserInfo + '}';
    }
}
