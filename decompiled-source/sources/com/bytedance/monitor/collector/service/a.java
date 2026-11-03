package com.bytedance.monitor.collector.service;

import android.os.Build;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: DoubleReflectHelper.java */
/* loaded from: classes2.dex */
public final class a {
    private a() {
    }

    public static boolean isAndroidPHigher() {
        return Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0);
    }

    public static Field getFieldInner(Class<?> cls, String str) {
        try {
            Field field = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(cls, str);
            field.setAccessible(true);
            return field;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Method getMethodInner(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            Method method = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class).invoke(cls, str, clsArr);
            method.setAccessible(true);
            return method;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Object readField(Object obj, String str) {
        try {
            return getField(obj.getClass(), str).get(obj);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Field getField(Class<?> cls, String str) {
        if (isAndroidPHigher()) {
            try {
                return getFieldInner(cls, str);
            } catch (Throwable unused) {
                return null;
            }
        }
        return com.bytedance.monitor.util.b.getField(cls, str);
    }
}
