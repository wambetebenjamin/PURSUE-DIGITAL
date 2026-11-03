package com.bytedance.ttgame.rocketapi.callback;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface QuerySdkOpenIdCallback {
    void onFailed(GSDKError gSDKError);

    void onSuccess(String str);
}
