package com.bytedance.ttgame.module.cloud.api;
/* loaded from: classes4.dex */
public interface ICloudServiceCallback<T> {
    void onFailed(T t);

    void onSuccess(T t);
}
