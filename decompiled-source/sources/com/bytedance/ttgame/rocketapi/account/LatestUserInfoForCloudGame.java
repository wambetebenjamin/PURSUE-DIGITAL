package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class LatestUserInfoForCloudGame {
    public GSDKError gsdkError;
    public boolean isFromPassport;
    public String token;
    public int userType;

    public String toString() {
        return "LatestUserInfoForCloudGame{gsdkError=" + this.gsdkError + ", userType=" + this.userType + ", token=" + this.token + "}";
    }
}
