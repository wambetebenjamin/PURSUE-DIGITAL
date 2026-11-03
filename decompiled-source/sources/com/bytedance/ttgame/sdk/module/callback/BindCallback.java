package com.bytedance.ttgame.sdk.module.callback;

import com.bytedance.ttgame.sdk.module.entity.BindResult;
/* loaded from: classes8.dex */
public interface BindCallback {
    void onFail(int i2, String str);

    void onSuccess(BindResult bindResult);
}
