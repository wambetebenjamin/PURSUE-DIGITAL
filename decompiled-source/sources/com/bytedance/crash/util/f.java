package com.bytedance.crash.util;

import android.os.Build;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: DoubleReflectHelper.java */
/* loaded from: classes2.dex */
public final class f {
    private f() {
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    public static Field a(Class<?> cls, String str) {
        try {
            Field field = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(cls, str);
            field.setAccessible(true);
            return field;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            Method method = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class).invoke(cls, str, clsArr);
            method.setAccessible(true);
            return method;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Object a(Object obj, String str) {
        try {
            return b(obj.getClass(), str).get(obj);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Field b(Class<?> cls, String str) {
        if (a()) {
            try {
                return a(cls, str);
            } catch (Throwable unused) {
                return null;
            }
        }
        return i.a(cls, str);
    }
}
