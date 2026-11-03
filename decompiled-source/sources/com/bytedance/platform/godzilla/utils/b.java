package com.bytedance.platform.godzilla.utils;

import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
/* compiled from: FieldUtils.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Field> f11093a = new HashMap();

    private b() {
    }

    private static String a(Class<?> cls, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString()).append("#").append(str);
        return sb.toString();
    }

    public static Field getField(Class<?> cls, String str) {
        Field field;
        Field declaredField;
        Class<? super Object> cls2 = (Class) g.checkNotNull(cls, "The class must not be null");
        g.checkTrue(!TextUtils.isEmpty(str), "The field name must not be blank");
        String a2 = a(cls2, str);
        Map<String, Field> map = f11093a;
        synchronized (map) {
            field = map.get(a2);
        }
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }
        while (cls2 != null) {
            try {
                if (f.isAndroidPHigher() && d.getInstance() != null) {
                    declaredField = d.getInstance().getField(cls2, str);
                    if (declaredField == null) {
                        declaredField = cls2.getDeclaredField(str);
                    }
                } else {
                    declaredField = cls2.getDeclaredField(str);
                }
                if (declaredField != null) {
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    Map<String, Field> map2 = f11093a;
                    synchronized (map2) {
                        map2.put(a2, declaredField);
                    }
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                cls2 = cls2.getSuperclass();
            }
        }
        return null;
    }

    public static Object readField(Field field, Object obj) {
        g.checkArgument(field != null, "The field must not be null");
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    public static void writeField(Field field, Object obj, Object obj2) {
        Field field2 = (Field) g.checkNotNull(field, "The field must not be null");
        if (!field2.isAccessible()) {
            field2.setAccessible(true);
        }
        field2.set(obj, obj2);
    }

    public static Object readField(Object obj, String str) {
        Object checkNotNull = g.checkNotNull(obj, "target object must not be null");
        Field field = getField(checkNotNull.getClass(), str);
        if (field != null) {
            return readField(field, checkNotNull);
        }
        return null;
    }

    public static void writeField(Object obj, String str, Object obj2) {
        Object checkNotNull = g.checkNotNull(obj, "target object must not be null");
        Field field = getField(checkNotNull.getClass(), str);
        if (field != null) {
            writeField(field, checkNotNull, obj2);
        }
    }

    public static Object readStaticField(Field field) {
        return readField((Field) g.checkNotNull(field, "The field must not be null"), (Object) null);
    }

    public static Object readStaticField(Class<?> cls, String str) {
        Field field = getField(cls, str);
        if (field != null) {
            return readStaticField(field);
        }
        return null;
    }

    public static void writeStaticField(Field field, Object obj) {
        writeField((Field) g.checkNotNull(field, "The field must not be null"), (Object) null, obj);
    }

    public static void writeStaticField(Class<?> cls, String str, Object obj) {
        Field field = getField(cls, str);
        if (field != null) {
            writeStaticField(field, obj);
        }
    }
}
