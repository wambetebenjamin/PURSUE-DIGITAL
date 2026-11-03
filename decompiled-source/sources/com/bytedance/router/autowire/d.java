package com.bytedance.router.autowire;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/* compiled from: TypeWrapper.java */
/* loaded from: classes2.dex */
public class d<T> {

    /* renamed from: a  reason: collision with root package name */
    protected final Type f12156a = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected d() {
    }

    public Type getType() {
        return this.f12156a;
    }
}
