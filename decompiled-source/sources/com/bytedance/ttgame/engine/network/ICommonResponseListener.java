package com.bytedance.ttgame.engine.network;
/* loaded from: classes3.dex */
public interface ICommonResponseListener<T> {
    void onFailed(int i2, String str);

    void onSuccess(T t);
}
