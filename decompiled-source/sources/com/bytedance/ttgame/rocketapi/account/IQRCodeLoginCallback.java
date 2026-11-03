package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IQRCodeLoginCallback {
    void authorizeFailed(GSDKError gSDKError);

    void authorizeSuccess(int i2);
}
