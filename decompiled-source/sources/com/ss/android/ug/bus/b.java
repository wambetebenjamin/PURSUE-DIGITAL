package com.ss.android.ug.bus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: UgBusFramework.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static Map<Class, a> f18616a = new ConcurrentHashMap();

    public static <T extends a> void registerService(Class<T> cls, a aVar) {
        if (aVar != null) {
            f18616a.put(cls, aVar);
        }
    }

    public static <T extends a> void unregisterService(Class<T> cls) {
        f18616a.remove(cls);
    }

    public static <T extends a> T getService(Class<T> cls) {
        return (T) f18616a.get(cls);
    }
}
