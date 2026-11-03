package com.bytedance.ttgame.sdk.module.account.api;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo;
/* loaded from: classes3.dex */
public class TTUserInfoResult {
    public TTUserInfo data;
    public GSDKError gsdkError = new GSDKError(0, "", 0, "");
    public int operationType;
}
