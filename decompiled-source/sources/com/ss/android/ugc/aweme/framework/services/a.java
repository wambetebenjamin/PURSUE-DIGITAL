package com.ss.android.ugc.aweme.framework.services;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
/* compiled from: Binding.java */
/* loaded from: classes9.dex */
public final class a<T> {

    /* renamed from: a  reason: collision with root package name */
    i f18627a;

    /* renamed from: b  reason: collision with root package name */
    Class<T> f18628b;

    /* renamed from: c  reason: collision with root package name */
    j<T> f18629c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(i iVar, Class<T> cls, j<T> jVar) {
        this.f18627a = iVar;
        this.f18628b = cls;
        this.f18629c = jVar;
        Set<j<?>> set = iVar.f18654a.get(cls);
        if (set == null) {
            Set<j<?>> synchronizedSet = Collections.synchronizedSet(new LinkedHashSet());
            synchronizedSet.add(jVar);
            iVar.f18654a.put(cls, synchronizedSet);
            return;
        }
        set.add(jVar);
    }

    public a<T> a() {
        Set<j<?>> set = this.f18627a.f18654a.get(this.f18628b);
        j<T> jVar = this.f18629c;
        if (!(jVar instanceof k)) {
            set.remove(jVar);
            k kVar = new k(this.f18629c);
            this.f18629c = kVar;
            set.add(kVar);
        }
        return this;
    }
}
