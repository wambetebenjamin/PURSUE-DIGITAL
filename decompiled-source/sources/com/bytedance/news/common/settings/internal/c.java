package com.bytedance.news.common.settings.internal;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: InstanceCache.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, Object> f9800a = new ConcurrentHashMap<>();

    public static <T> T obtain(Class<T> cls, d dVar) {
        ConcurrentHashMap<Class, Object> concurrentHashMap = f9800a;
        T t = (T) concurrentHashMap.get(cls);
        if (t == null) {
            synchronized (c.class) {
                t = concurrentHashMap.get(cls);
                if (t == null) {
                    Object create = dVar.create(cls);
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
