package com.bytedance.pipo.payment.common.lib.callback;

import com.bytedance.pipo.iap.model.AbsResult;
/* compiled from: ApiCallback.java */
/* loaded from: classes2.dex */
public interface a<T> {
    void onFailed(AbsResult absResult);

    void onSuccess(T t);
}
