package com.bytedance.common.jato.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: UnsafeProxy.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static Class f5058a;

    /* renamed from: b  reason: collision with root package name */
    private static Object f5059b;

    static {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            f5058a = cls;
            f5059b = a(cls);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public static Object a(Class<?> cls) {
        return a(a(cls, "getUnsafe", new Class[0]), new Object[0]);
    }

    public static long a(Field field) {
        Object a2 = a(a(f5058a, "objectFieldOffset", Field.class), f5059b, field);
        if (a2 == null) {
            return 0L;
        }
        return ((Long) a2).longValue();
    }

    public static long a(Object obj, long j2, Object obj2) {
        Object a2 = a(a(f5058a, "putObject", Object.class, Long.TYPE, Object.class), f5059b, obj, Long.valueOf(j2), obj2);
        if (a2 == null) {
            return 0L;
        }
        return ((Long) a2).longValue();
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method declaredMethod;
        try {
            if (clsArr != null) {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            } else {
                declaredMethod = cls.getDeclaredMethod(str, new Class[0]);
            }
            return declaredMethod;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Object a(Method method, Object... objArr) {
        return a(method, (Object) null, objArr);
    }

    public static Object a(Method method, Object obj, Object... objArr) {
        if (method != null) {
            try {
                return method.invoke(obj, objArr);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
        return null;
    }
}
