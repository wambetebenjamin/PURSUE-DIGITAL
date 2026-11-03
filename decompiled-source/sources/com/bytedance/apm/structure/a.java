package com.bytedance.apm.structure;
/* compiled from: IStack.java */
/* loaded from: classes.dex */
public interface a<T> {
    void clear();

    boolean isEmpty();

    T peek();

    T pop();

    void push(T t);
}
