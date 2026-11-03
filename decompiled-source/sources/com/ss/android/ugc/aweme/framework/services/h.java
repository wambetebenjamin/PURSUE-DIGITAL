package com.ss.android.ugc.aweme.framework.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: GroupBinding.java */
/* loaded from: classes9.dex */
public final class h<T> {

    /* renamed from: a  reason: collision with root package name */
    Map<Class<?>, Map<String, j<?>>> f18650a;

    /* renamed from: b  reason: collision with root package name */
    String f18651b;

    /* renamed from: c  reason: collision with root package name */
    Class<T> f18652c;

    /* renamed from: d  reason: collision with root package name */
    j<T> f18653d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public h(Map<Class<?>, Map<String, j<?>>> map, Class<T> cls, String str, j<T> jVar) {
        this.f18650a = map;
        this.f18651b = str;
        this.f18652c = cls;
        this.f18653d = jVar;
        Map<String, j<?>> map2 = map.get(cls);
        if (map2 == null) {
            map2 = Collections.synchronizedMap(new HashMap());
            this.f18650a.put(cls, map2);
        }
        map2.put(str, jVar);
    }

    public h<T> a() {
        if (!(this.f18653d instanceof k)) {
            Map<String, j<?>> map = this.f18650a.get(this.f18652c);
            map.remove(this.f18653d);
            k kVar = new k(this.f18653d);
            this.f18653d = kVar;
            map.put(this.f18651b, kVar);
        }
        return this;
    }
}
