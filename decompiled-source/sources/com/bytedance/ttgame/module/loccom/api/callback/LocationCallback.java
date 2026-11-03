package com.bytedance.ttgame.module.loccom.api.callback;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes6.dex */
public interface LocationCallback<T> {
    void onFailed(GSDKError gSDKError);

    void onSuccess(T t);
}
