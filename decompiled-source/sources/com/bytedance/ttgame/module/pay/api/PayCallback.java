package com.bytedance.ttgame.module.pay.api;
/* loaded from: classes6.dex */
public interface PayCallback<T> {
    void onFailed(int i2, String str);

    void onSuccess(T t);
}
