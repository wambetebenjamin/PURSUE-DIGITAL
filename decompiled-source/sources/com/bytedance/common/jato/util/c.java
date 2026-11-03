package com.bytedance.common.jato.util;

import android.os.Build;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: DoubleReflector.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5052a = "DoubleReflector";

    /* renamed from: b  reason: collision with root package name */
    private static Method f5053b;

    /* renamed from: c  reason: collision with root package name */
    private static Method f5054c;

    /* renamed from: d  reason: collision with root package name */
    private static Method f5055d;

    /* renamed from: e  reason: collision with root package name */
    private static Method f5056e;

    static {
        try {
            f5053b = Class.class.getDeclaredMethod("getDeclaredField", String.class);
            f5054c = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            f5055d = Class.class.getDeclaredMethod("getDeclaredConstructor", Class[].class);
            f5056e = Class.class.getDeclaredMethod("forName", String.class);
        } catch (Throwable unused) {
        }
    }

    public static Field a(Class<?> cls, String str) {
        Method method;
        Field field = (Build.VERSION.SDK_INT < 29 || (method = f5053b) == null) ? null : (Field) method.invoke(cls, str);
        return field == null ? cls.getDeclaredField(str) : field;
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        Method method2 = (Build.VERSION.SDK_INT < 29 || (method = f5054c) == null) ? null : (Method) method.invoke(cls, str, clsArr);
        return method2 == null ? cls.getDeclaredMethod(str, clsArr) : method2;
    }

    public static Constructor a(Class<?> cls, Class<?>... clsArr) {
        Method method;
        Constructor constructor = (Build.VERSION.SDK_INT < 29 || (method = f5055d) == null) ? null : (Constructor) method.invoke(cls, clsArr);
        return constructor == null ? cls.getDeclaredConstructor(clsArr) : constructor;
    }

    public static Class<?> a(String str) {
        Method method;
        Class<?> cls = null;
        if (Build.VERSION.SDK_INT >= 29 && (method = f5056e) != null) {
            cls = (Class) method.invoke(null, str);
        }
        return cls == null ? Class.forName(str) : cls;
    }

    public static Object a(Object obj, String str) {
        try {
            Field a2 = a(obj.getClass(), str);
            a2.setAccessible(true);
            return a2.get(obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
