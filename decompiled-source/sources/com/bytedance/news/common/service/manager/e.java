package com.bytedance.news.common.service.manager;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ServiceManager.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, Object> f9728a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, b<?>> f9729b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, d> f9730c = new ConcurrentHashMap<>();

    public static <T> T getService(Class<T> cls) {
        T t = (T) f9728a.get(cls);
        if (t == null) {
            synchronized (e.class) {
                ConcurrentHashMap<Class, b<?>> concurrentHashMap = f9729b;
                b<?> bVar = concurrentHashMap.get(cls);
                if (bVar != null) {
                    T t2 = (T) bVar.create();
                    concurrentHashMap.remove(cls);
                    if (t2 != null) {
                        a(cls, t2);
                        return t2;
                    }
                }
                T t3 = (T) c.a(cls);
                if (t3 != null) {
                    a(cls, t3);
                    return t3;
                }
                synchronized (cls) {
                    t = (T) a(cls);
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
        f9728a.put(cls, t);
    }

    public static <T> void registerService(Class<T> cls, b<T> bVar) {
        f9729b.put(cls, bVar);
    }

    public static <T> void unregisterService(Class<T> cls, T t) {
        f9728a.remove(cls, t);
    }

    public static <T> void registerServiceLoadListener(Class<T> cls, d<T> dVar) {
        f9730c.put(cls, dVar);
    }

    private static <T> void a(Class<T> cls, T t) {
        f9728a.put(cls, t);
        ConcurrentHashMap<Class, d> concurrentHashMap = f9730c;
        d dVar = concurrentHashMap.get(cls);
        if (dVar != null) {
            dVar.onServiceLoaded(t);
            concurrentHashMap.remove(cls);
        }
    }

    private static <T> T a(Class<T> cls) {
        try {
            Object newInstance = Class.forName(String.format("%s__ServiceProxy", cls.getName())).newInstance();
            if (newInstance instanceof IServiceProxy) {
                return (T) ((IServiceProxy) newInstance).newInstance();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
