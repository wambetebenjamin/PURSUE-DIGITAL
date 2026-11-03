package com.bytedance.ttgame.channel;

import com.bytedance.ttgame.rocketapi.account.PeerNetworkUserInfoResult;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
/* loaded from: classes3.dex */
public class PeerNetworkUserInfoManager {
    public static PeerNetworkUserInfoResult invertToPeerNetworkInfo(String str, UserInfoResult userInfoResult) {
        if (userInfoResult == null) {
            return null;
        }
        PeerNetworkUserInfoResult peerNetworkUserInfoResult = new PeerNetworkUserInfoResult();
        peerNetworkUserInfoResult.data = userInfoResult.data;
        peerNetworkUserInfoResult.sdkOpenId = str;
        peerNetworkUserInfoResult.gsdkError = userInfoResult.gsdkError;
        return peerNetworkUserInfoResult;
    }
}
