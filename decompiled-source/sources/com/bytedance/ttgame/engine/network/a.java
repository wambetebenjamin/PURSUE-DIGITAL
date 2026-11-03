package com.bytedance.ttgame.engine.network;
/* compiled from: Callback.java */
/* loaded from: classes3.dex */
public interface a<T> {
    void onFailure(b<T> bVar, Throwable th);

    void onResponse(b<T> bVar, T t, int i2);
}
