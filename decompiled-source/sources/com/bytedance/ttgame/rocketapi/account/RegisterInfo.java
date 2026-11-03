package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class RegisterInfo {
    public GSDKError gsdkError;
    public boolean isRegistered;

    public RegisterInfo(boolean z, GSDKError gSDKError) {
        this.isRegistered = z;
        this.gsdkError = gSDKError;
    }
}
