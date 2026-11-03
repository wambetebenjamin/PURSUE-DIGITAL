package com.bytedance.crash.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: ReflexHelper.java */
/* loaded from: classes2.dex */
public class s {
    public static Field a(String str, String str2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object a(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int b(String str, String str2) {
        return Class.forName(str).getField(str2).getInt(null);
    }

    public static Object c(String str, String str2) {
        return Class.forName(str).getField(str2).get(null);
    }

    public static Method a(String str, String str2, Class<?>... clsArr) {
        return Class.forName(str).getMethod(str2, clsArr);
    }

    public static Method a(Object obj, String str, Class<?>... clsArr) {
        return obj.getClass().getMethod(str, clsArr);
    }
}
