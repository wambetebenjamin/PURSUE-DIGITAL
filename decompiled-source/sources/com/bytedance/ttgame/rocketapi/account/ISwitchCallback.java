package com.bytedance.ttgame.rocketapi.account;
/* loaded from: classes7.dex */
public interface ISwitchCallback<T> {
    void onFailed(T t);

    @Deprecated
    void onLogout(T t);

    void onSuccess(T t);
}
