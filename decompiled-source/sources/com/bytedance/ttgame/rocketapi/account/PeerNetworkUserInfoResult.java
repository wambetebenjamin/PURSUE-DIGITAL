package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class PeerNetworkUserInfoResult {
    public UserInfo data;
    public GSDKError gsdkError;
    public String sdkOpenId;

    public String toString() {
        return "PeerNetworkUserInfoResult{data=" + this.data + ", sdkOpenId='" + this.sdkOpenId + "', gsdkError=" + this.gsdkError + '}';
    }
}
