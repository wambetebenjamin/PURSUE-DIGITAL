package com.ss.android.ugc.aweme.framework.services;
/* compiled from: SingletonProvider.java */
/* loaded from: classes9.dex */
public class k<T> implements j<T> {

    /* renamed from: a  reason: collision with root package name */
    j<T> f18659a;

    /* renamed from: b  reason: collision with root package name */
    volatile T f18660b;

    public k(j jVar) {
        this.f18659a = jVar;
    }

    @Override // com.ss.android.ugc.aweme.framework.services.j
    public synchronized T a() {
        if (this.f18660b == null) {
            this.f18660b = this.f18659a.a();
        }
        return this.f18660b;
    }
}
