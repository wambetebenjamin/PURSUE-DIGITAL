package com.bytedance.retrofit2;

import com.bytedance.retrofit2.client.Request;
/* loaded from: classes2.dex */
public interface Call<T> extends Cloneable {
    void cancel();

    Call<T> clone();

    void enqueue(Callback<T> callback);

    @Deprecated
    SsResponse<T> execute();

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
