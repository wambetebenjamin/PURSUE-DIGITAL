package com.bytedance.bdinstall.service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ServiceManager.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public static final ConcurrentHashMap<String, f> f3973a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private static HashMap<Class, HashMap<String, ?>> f3974b;

    /* renamed from: c  reason: collision with root package name */
    private final ConcurrentHashMap<Class, Object> f3975c = new ConcurrentHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private final ConcurrentHashMap<Class, e> f3976d = new ConcurrentHashMap<>();

    public static <T> T a(Class<T> cls, String str) {
        f fVar = f3973a.get(str);
        if (fVar != null) {
            return (T) fVar.f3975c.get(cls);
        }
        return null;
    }

    public static <T> void a(Class<T> cls, T t, String str) {
        ConcurrentHashMap<String, f> concurrentHashMap = f3973a;
        f fVar = concurrentHashMap.get(str);
        if (fVar == null) {
            fVar = new f();
            concurrentHashMap.put(str, fVar);
        }
        fVar.f3975c.put(cls, t);
    }

    public static synchronized <T> void b(Class<T> cls, T t, String str) {
        synchronized (f.class) {
            HashMap<Class, HashMap<String, ?>> hashMap = f3974b;
            if (hashMap != null && t != null) {
                HashMap<String, ?> hashMap2 = hashMap.get(cls);
                if (hashMap2 != null) {
                    hashMap2.remove(str);
                }
            }
        }
    }
}
