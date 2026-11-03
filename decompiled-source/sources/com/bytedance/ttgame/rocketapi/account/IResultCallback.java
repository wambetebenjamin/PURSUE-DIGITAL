package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public interface IResultCallback<T> {
    void onResultFailed(GSDKError gSDKError);

    void onResultSuccess(T t);
}
