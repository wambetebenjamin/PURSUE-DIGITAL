package com.bytedance.crash.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
/* compiled from: FieldUtils.java */
/* loaded from: classes2.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Field> f7303a = new HashMap();

    private i() {
    }

    private static String c(Class<?> cls, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString()).append("#").append(str);
        return sb.toString();
    }

    public static Field a(Class<?> cls, String str) {
        Field field;
        String c2 = c(cls, str);
        Map<String, Field> map = f7303a;
        synchronized (map) {
            field = map.get(c2);
        }
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Map<String, Field> map2 = f7303a;
                synchronized (map2) {
                    map2.put(c2, declaredField);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Object a(Field field, Object obj) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    public static void a(Field field, Object obj, Object obj2) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(obj, obj2);
    }

    public static Object a(Object obj, String str) {
        Field a2 = a(obj.getClass(), str);
        if (a2 != null) {
            return a(a2, obj);
        }
        return null;
    }

    public static void a(Object obj, String str, Object obj2) {
        Field a2 = a(obj.getClass(), str);
        if (a2 != null) {
            a(a2, obj, obj2);
        }
    }

    public static Object a(Field field) {
        return a(field, (Object) null);
    }

    public static Object b(Class<?> cls, String str) {
        Field a2 = a(cls, str);
        if (a2 != null) {
            return a(a2);
        }
        return null;
    }

    public static void b(Field field, Object obj) {
        a(field, (Object) null, obj);
    }

    public static void a(Class<?> cls, String str, Object obj) {
        Field a2 = a(cls, str);
        if (a2 != null) {
            b(a2, obj);
        }
    }
}
