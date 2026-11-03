package com.bytedance.sdk.bridge.auth;

import com.bytedance.sdk.bridge.g;
/* compiled from: AbsBridgeAuthFilter.java */
/* loaded from: classes2.dex */
public abstract class a<T> implements c<T> {
    protected abstract boolean a(T t, g gVar);

    @Override // com.bytedance.sdk.bridge.auth.c
    public boolean doAuthFilter(T t, g gVar, d<T> dVar) {
        if (a(t, gVar)) {
            return true;
        }
        return dVar.doAuthFilter(t, gVar);
    }
}
