package com.bytedance.push.settings;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: InstanceCache.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, Object> f11769a = new ConcurrentHashMap<>();

    public static <T> T obtain(Class<T> cls, f fVar) {
        ConcurrentHashMap<Class, Object> concurrentHashMap = f11769a;
        T t = (T) concurrentHashMap.get(cls);
        if (t == null) {
            synchronized (e.class) {
                t = concurrentHashMap.get(cls);
                if (t == null) {
                    Object create = fVar.create(cls);
                    if (create != null) {
                        concurrentHashMap.put(cls, create);
                    }
                    t = (T) create;
                }
            }
        }
        return t;
    }
}
