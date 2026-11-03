package com.bytedance.pipeline;
/* compiled from: Chain.java */
/* loaded from: classes2.dex */
public interface b<IN> {
    Object a();

    <I> I a(Class<? extends d<I, ?>> cls);

    Object a(IN in);

    Object a(String str);

    void a(String str, Object obj);

    Object b();

    <O> O b(Class<? extends d<?, O>> cls);

    <T> T c(Class<? extends d> cls);
}
