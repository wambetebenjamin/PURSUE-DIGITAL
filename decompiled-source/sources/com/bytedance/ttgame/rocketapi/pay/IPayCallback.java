package com.bytedance.ttgame.rocketapi.pay;
/* loaded from: classes7.dex */
public interface IPayCallback<T> {
    void onFailed(T t);

    void onSuccess(T t);
}
