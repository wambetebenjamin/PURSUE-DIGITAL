package com.bytedance.platform.godzilla.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
/* compiled from: ProxyHelper.java */
/* loaded from: classes2.dex */
public final class i {
    private i() {
    }

    public static Object createProxy(Object obj, InvocationHandler invocationHandler) {
        List<Class<?>> a2 = a(obj.getClass());
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), (a2 == null || a2.size() <= 0) ? new Class[0] : (Class[]) a2.toArray(new Class[a2.size()]), invocationHandler);
    }

    private static List<Class<?>> a(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        a(cls, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    private static void a(Class<?> cls, HashSet<Class<?>> hashSet) {
        Class<?>[] interfaces;
        while (cls != null) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                if (hashSet.add(cls2)) {
                    a(cls2, hashSet);
                }
            }
            cls = cls.getSuperclass();
        }
    }
}
