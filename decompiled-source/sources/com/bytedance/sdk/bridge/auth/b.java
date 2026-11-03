package com.bytedance.sdk.bridge.auth;

import com.bytedance.sdk.bridge.g;
import com.bytedance.sdk.bridge.m;
/* compiled from: AbsBridgeAuthenticator.java */
/* loaded from: classes2.dex */
public abstract class b<T> implements m<T> {

    /* renamed from: a  reason: collision with root package name */
    protected d<T> f13102a = new d<>();

    protected boolean a(T t, g gVar) {
        boolean doAuthFilter = this.f13102a.doAuthFilter(t, gVar);
        this.f13102a.resetIndex();
        return doAuthFilter;
    }

    @Override // com.bytedance.sdk.bridge.m
    public boolean auth(T t, g gVar) {
        return a(t, gVar);
    }

    public b<T> add(c<T> cVar) {
        this.f13102a.addFilter(cVar);
        return this;
    }
}
