package com.bytedance.ttgame.module.cloudgame.api;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes4.dex */
public interface ICloudGameMessageListener {
    void onReceive(CloudGameMessage cloudGameMessage);

    void onSendResult(GSDKError gSDKError, String str);
}
