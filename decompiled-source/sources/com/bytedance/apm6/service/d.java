package com.bytedance.apm6.service;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ServiceManager.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, Object> f2367a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, b<?>> f2368b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, c> f2369c = new ConcurrentHashMap<>();

    public static <T> T getService(Class<T> cls) {
        T t = (T) f2367a.get(cls);
        if (t == null) {
            synchronized (d.class) {
                ConcurrentHashMap<Class, b<?>> concurrentHashMap = f2368b;
                b<?> bVar = concurrentHashMap.get(cls);
                if (bVar != null) {
                    t = (T) bVar.create();
                    concurrentHashMap.remove(cls);
                    if (t != null) {
                        a(cls, t);
                        return t;
                    }
                }
            }
        }
        return t;
    }

    public static <T> void registerService(Class<T> cls, T t) {
        f2367a.put(cls, t);
    }

    public static <T> void registerService(Class<T> cls, b<T> bVar) {
        f2368b.put(cls, bVar);
    }

    public static <T> void unregisterService(Class<T> cls, T t) {
        f2367a.remove(cls, t);
    }

    public static <T> void registerServiceLoadListener(Class<T> cls, c<T> cVar) {
        f2369c.put(cls, cVar);
    }

    private static <T> void a(Class<T> cls, T t) {
        f2367a.put(cls, t);
        ConcurrentHashMap<Class, c> concurrentHashMap = f2369c;
        c cVar = concurrentHashMap.get(cls);
        if (cVar != null) {
            cVar.onServiceLoaded(t);
            concurrentHashMap.remove(cls);
        }
    }
}
