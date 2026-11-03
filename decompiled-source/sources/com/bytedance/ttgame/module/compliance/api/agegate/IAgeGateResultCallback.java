package com.bytedance.ttgame.module.compliance.api.agegate;
/* loaded from: classes4.dex */
public interface IAgeGateResultCallback<T> {
    void onFailed(T t);

    void onSuccess(T t);
}
