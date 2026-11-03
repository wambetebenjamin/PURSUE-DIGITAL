package com.bytedance.ttgame.framework.module.callback;
/* loaded from: classes3.dex */
public interface ICallback<T> {
    void onFailed(T t);

    void onSuccess(T t);
}
