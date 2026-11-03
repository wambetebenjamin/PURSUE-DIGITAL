package com.ss.android.deviceregister.service;

import android.util.Log;
import com.ss.android.deviceregister.s;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ServiceManager.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18264a = "ServiceManager ";

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, Object> f18265b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private static final ConcurrentHashMap<Class, a> f18266c = new ConcurrentHashMap<>();

    public static <T> void a(Class<T> cls, T t) {
        ConcurrentHashMap<Class, Object> concurrentHashMap = f18265b;
        if (concurrentHashMap.containsKey(s.class)) {
            return;
        }
        concurrentHashMap.put(cls, t);
        Log.d(f18264a, "register service: " + cls.getName());
    }

    public static <T> T a(Class<T> cls) {
        ConcurrentHashMap<Class, a> concurrentHashMap;
        a aVar;
        ConcurrentHashMap<Class, Object> concurrentHashMap2 = f18265b;
        T t = (T) concurrentHashMap2.get(cls);
        if (t == null) {
            synchronized (concurrentHashMap2) {
                t = concurrentHashMap2.get(cls);
                if (t == null && (aVar = (concurrentHashMap = f18266c).get(cls)) != null) {
                    t = (T) aVar.a(cls);
                    if (t != null) {
                        concurrentHashMap2.put(cls, t);
                    }
                    concurrentHashMap.remove(cls);
                    Log.d(f18264a, "create service: " + cls.getName());
                }
            }
        }
        return t;
    }

    public static <T> void b(Class<T> cls, T t) {
        f18265b.remove(cls, t);
    }
}
