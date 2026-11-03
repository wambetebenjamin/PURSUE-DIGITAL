package com.bytedance.ttgame.rocketapi.minors;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IMinorsCallback {
    void onFailed(GSDKError gSDKError);

    void onSuccess(String str);
}
