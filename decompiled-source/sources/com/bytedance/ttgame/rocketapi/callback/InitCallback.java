package com.bytedance.ttgame.rocketapi.callback;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface InitCallback {
    void onFailed(GSDKError gSDKError);

    void onSuccess(InitResult initResult);
}
