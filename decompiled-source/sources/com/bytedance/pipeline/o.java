package com.bytedance.pipeline;

import java.io.Serializable;
/* compiled from: UnProceedChain.java */
/* loaded from: classes2.dex */
final class o<IN> implements b<IN>, Serializable {

    /* renamed from: a  reason: collision with root package name */
    private b<IN> f10039a;

    public o(b<IN> bVar) {
        this.f10039a = bVar;
    }

    @Override // com.bytedance.pipeline.b
    public Object a(IN in) {
        return this.f10039a.a((b<IN>) in);
    }

    @Override // com.bytedance.pipeline.b
    public Object a() {
        throw new UnsupportedOperationException();
    }

    @Override // com.bytedance.pipeline.b
    public Object b() {
        return this.f10039a.b();
    }

    @Override // com.bytedance.pipeline.b
    public <I> I a(Class<? extends d<I, ?>> cls) {
        return (I) this.f10039a.a((Class<? extends d<Object, ?>>) cls);
    }

    @Override // com.bytedance.pipeline.b
    public <O> O b(Class<? extends d<?, O>> cls) {
        return (O) this.f10039a.b(cls);
    }

    @Override // com.bytedance.pipeline.b
    public <T> T c(Class<? extends d> cls) {
        return (T) this.f10039a.c(cls);
    }

    @Override // com.bytedance.pipeline.b
    public void a(String str, Object obj) {
        this.f10039a.a(str, obj);
    }

    @Override // com.bytedance.pipeline.b
    public Object a(String str) {
        return this.f10039a.a(str);
    }
}
