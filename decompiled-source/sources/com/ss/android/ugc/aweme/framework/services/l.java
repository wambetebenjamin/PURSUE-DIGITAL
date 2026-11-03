package com.ss.android.ugc.aweme.framework.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: StaticServiceImplManager.java */
/* loaded from: classes9.dex */
public class l {

    /* renamed from: e  reason: collision with root package name */
    private Map<String, Object> f18665e = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    final Set<String> f18661a = Collections.synchronizedSet(new HashSet());

    /* renamed from: b  reason: collision with root package name */
    final Map<String, Integer> f18662b = new ConcurrentHashMap(50);

    /* renamed from: f  reason: collision with root package name */
    private Map<String, Set<Object>> f18666f = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    final Set<String> f18663c = Collections.synchronizedSet(new HashSet());

    /* renamed from: d  reason: collision with root package name */
    final Map<String, Integer> f18664d = new ConcurrentHashMap(50);

    /* renamed from: g  reason: collision with root package name */
    public final Map<String, com.ss.android.ugc.aweme.framework.services.annotation.c> f18667g = new ConcurrentHashMap(50);

    /* compiled from: StaticServiceImplManager.java */
    /* loaded from: classes9.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final l f18668a = new l();

        private a() {
        }
    }

    public static l a() {
        return a.f18668a;
    }

    public <T> T a(Class<T> cls, String str) {
        T t = (T) this.f18665e.get(str);
        return (t != null || this.f18662b.containsKey(str)) ? t : (T) c(cls);
    }

    public <T> Set<T> b(Class<T> cls) {
        String name = cls.getName();
        Set<T> set = (Set<T>) this.f18666f.get(name);
        return (set != null || this.f18664d.containsKey(name)) ? set : d(cls);
    }

    private <T> T c(Class<T> cls) {
        this.f18662b.put(cls.getName(), 1);
        return null;
    }

    private void a(String str, Object obj) {
        this.f18665e.put(str, obj);
    }

    private <T> Set<T> d(Class<T> cls) {
        this.f18664d.put(cls.getName(), 1);
        return null;
    }

    private void a(String str, Set<Object> set) {
        this.f18666f.put(str, set);
    }

    private void a(String str, com.ss.android.ugc.aweme.framework.services.annotation.c cVar) {
        this.f18667g.put(str, cVar);
    }

    public <T> T a(String str) {
        return (T) b(str, null);
    }

    private Object b(String str, com.ss.android.ugc.aweme.framework.services.annotation.c cVar) {
        if (cVar == null) {
            cVar = this.f18667g.get(str);
        }
        if (cVar != null) {
            return cVar.a();
        }
        return null;
    }

    private List<Object> b(String str) {
        com.ss.android.ugc.aweme.framework.services.annotation.c cVar = this.f18667g.get(str);
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }
}
