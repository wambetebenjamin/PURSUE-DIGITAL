package com.bytedance.ttgame.rocketapi.callback;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IEmulatorCallback<Boolean> {
    void onFailed(GSDKError gSDKError);

    @Deprecated
    default void onSuccess(Boolean r1) {
    }

    default void onSuccess(Boolean r1, String str) {
        onSuccess(r1);
    }
}
