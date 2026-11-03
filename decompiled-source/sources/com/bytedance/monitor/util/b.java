package com.bytedance.monitor.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
/* compiled from: FieldUtils.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Field> f9671a = new HashMap();

    private b() {
    }

    private static String a(Class<?> cls, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString()).append("#").append(str);
        return sb.toString();
    }

    public static Field getField(Class<?> cls, String str) {
        Field field;
        String a2 = a(cls, str);
        Map<String, Field> map = f9671a;
        synchronized (map) {
            field = map.get(a2);
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
                Map<String, Field> map2 = f9671a;
                synchronized (map2) {
                    map2.put(a2, declaredField);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Object readField(Field field, Object obj) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    public static void writeField(Field field, Object obj, Object obj2) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(obj, obj2);
    }

    public static Object readField(Object obj, String str) {
        Field field = getField(obj.getClass(), str);
        if (field != null) {
            return readField(field, obj);
        }
        return null;
    }

    public static void writeField(Object obj, String str, Object obj2) {
        Field field = getField(obj.getClass(), str);
        if (field != null) {
            writeField(field, obj, obj2);
        }
    }

    public static Object readStaticField(Field field) {
        return readField(field, (Object) null);
    }

    public static Object readStaticField(Class<?> cls, String str) {
        Field field = getField(cls, str);
        if (field != null) {
            return readStaticField(field);
        }
        return null;
    }

    public static void writeStaticField(Field field, Object obj) {
        writeField(field, (Object) null, obj);
    }

    public static void writeStaticField(Class<?> cls, String str, Object obj) {
        Field field = getField(cls, str);
        if (field != null) {
            writeStaticField(field, obj);
        }
    }
}
