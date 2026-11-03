package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class UserInfoResult {
    public UserInfo data;
    public int operationType = -1;
    public GSDKError gsdkError = new GSDKError(0, "success");

    public String toString() {
        return "UserInfoResult{, data=" + this.data + ", operationType=" + this.operationType + ", gsdkError=" + this.gsdkError + '}';
    }
}
