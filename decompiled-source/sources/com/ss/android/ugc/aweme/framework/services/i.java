package com.ss.android.ugc.aweme.framework.services;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ServiceManager.java */
/* loaded from: classes9.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    final ConcurrentHashMap<Class<?>, Set<j<?>>> f18654a;

    /* renamed from: b  reason: collision with root package name */
    final Map<Class<?>, Map<String, j<?>>> f18655b;

    /* renamed from: c  reason: collision with root package name */
    final Set<String> f18656c;

    /* renamed from: d  reason: collision with root package name */
    public volatile boolean f18657d;

    public void a(boolean z) {
        this.f18657d = z;
    }

    public void a(Set<String> set) {
        this.f18656c.addAll(set);
    }

    public void a(String str) {
        this.f18656c.add(str);
    }

    public <T> T a(Class<T> cls, boolean z) {
        return (T) a(cls, z, this.f18657d);
    }

    public <T> T a(Class<T> cls) {
        return (T) a((Class<Object>) cls, false);
    }

    public <T> T a(Class<T> cls, boolean z, boolean z2) {
        return (T) a(cls, z, z2, false);
    }

    public <T> T b(Class<T> cls) {
        return (T) a(cls, false, this.f18657d, true);
    }

    public <T> T a(Class<T> cls, boolean z, boolean z2, boolean z3) {
        Set<j<?>> set = this.f18654a.get(cls);
        T t = (set == null || set.isEmpty() || z) ? null : (T) ((j) set.toArray()[0]).a();
        if (t != null) {
            return t;
        }
        String name = cls.getName();
        T t2 = (T) l.a().a(name);
        if (t2 != null) {
            return t2;
        }
        T t3 = (T) l.a().a(cls, name);
        if (t3 != null) {
            return t3;
        }
        if (z3) {
            return null;
        }
        T t4 = (T) f(cls);
        if (t4 != null) {
            return t4;
        }
        T t5 = (T) e.a().a(cls);
        if (t5 == null && z2) {
            new f();
            return (T) f.a(cls);
        }
        return t5;
    }

    public <T> void a(Class<T> cls, j<T> jVar) {
        a((Class) cls, (j) jVar, true);
    }

    public <T> void a(Class<T> cls, j<T> jVar, boolean z) {
        if (z) {
            jVar = new k(jVar);
        }
        e.a().a(cls, jVar);
    }

    private <T> T f(Class<T> cls) {
        if (this.f18656c.contains(cls.getName())) {
            Iterator a2 = c.a(cls);
            if (a2.hasNext()) {
                return (T) a2.next();
            }
            return null;
        }
        return null;
    }

    public ConcurrentHashMap a() {
        return this.f18654a;
    }

    public Map<Class<?>, Map<String, j<?>>> b() {
        return this.f18655b;
    }

    public <T> Set<T> c(Class<T> cls) {
        return b((Class) cls, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> Set<T> b(Class<T> cls, boolean z) {
        Set<T> b2;
        Set<j<?>> set = this.f18654a.get(cls);
        if (set == null || set.isEmpty()) {
            Set<T> b3 = l.a().b(cls);
            return (b3 == null || b3.isEmpty()) ? d(cls) : b3;
        }
        Set<T> set2 = (Set<T>) Collections.synchronizedSet(new LinkedHashSet());
        for (j<?> jVar : set) {
            set2.add(jVar.a());
        }
        if (z && (b2 = l.a().b(cls)) != null && !b2.isEmpty()) {
            set2.addAll(b2);
        }
        return set2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> Set<T> d(Class<T> cls) {
        Set<T> set = (Set<T>) Collections.synchronizedSet(new LinkedHashSet());
        if (this.f18656c.contains(cls.getName())) {
            Iterator a2 = c.a(cls);
            while (a2.hasNext()) {
                set.add(a2.next());
            }
            return set;
        }
        return set;
    }

    public <T> com.ss.android.ugc.aweme.framework.services.a b(Class<T> cls, j<T> jVar) {
        return new com.ss.android.ugc.aweme.framework.services.a(this, cls, jVar);
    }

    public <T> h a(Class<T> cls, String str, j<T> jVar) {
        return new h(this.f18655b, cls, str, jVar);
    }

    public <T> T a(Class<T> cls, String str) {
        Map<String, j<?>> map = this.f18655b.get(cls);
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return (T) map.get(str).a();
    }

    private com.ss.android.ugc.aweme.framework.services.annotation.c b(String str) {
        return com.ss.android.ugc.aweme.framework.services.utils.a.a(str);
    }

    public static i c() {
        return a.f18658a;
    }

    private i() {
        this.f18654a = new ConcurrentHashMap<>();
        this.f18655b = new ConcurrentHashMap();
        this.f18656c = new ConcurrentHashMap().keySet();
        this.f18657d = true;
    }

    /* compiled from: ServiceManager.java */
    /* loaded from: classes9.dex */
    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final i f18658a = new i();

        private a() {
        }
    }
}
