package com.facebook.imagepipeline.memory;
/* loaded from: classes8.dex */
interface PoolBackend<T> {
    T get(int i2);

    int getSize(T t);

    T pop();

    void put(T t);
}
