package com.bytedance.applog.util;

import java.lang.reflect.Field;
/* compiled from: ReflectUtils.java */
/* loaded from: classes.dex */
public class l {
    public static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static boolean b(String str) {
        return a(str) != null;
    }

    public static boolean a(Object obj, String... strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                Class<?> a2 = a(str);
                if (a2 != null && a2.isInstance(obj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Class<?> a(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                Class<?> a2 = a(str);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    public static Object a(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(Object obj, String str, Object obj2) {
        if (obj == null) {
            return;
        }
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (Throwable unused) {
        }
    }
}
