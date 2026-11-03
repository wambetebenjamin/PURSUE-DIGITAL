package com.bytedance.platform.godzilla.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: DoubleReflectTool.java */
/* loaded from: classes2.dex */
public final class h {
    private h() {
    }

    public static Field getField(Class<?> cls, String str) {
        try {
            Field field = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(cls, str);
            field.setAccessible(true);
            return field;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            Method method = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class).invoke(cls, str, clsArr);
            method.setAccessible(true);
            return method;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
