package com.bytedance.monitor.util.unsafe;

import java.lang.reflect.Method;
/* compiled from: ReflectUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f9717a;

    private a() {
    }

    public static a getInstance() {
        if (f9717a == null) {
            f9717a = new a();
        }
        return f9717a;
    }

    public Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
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

    public Object invokeStaticMethod(Method method, Object... objArr) {
        return invokeMethod(method, null, objArr);
    }

    public Object invokeMethod(Method method, Object obj, Object... objArr) {
        if (method != null) {
            try {
                return method.invoke(obj, objArr);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
        return null;
    }

    public Object getUnsafe(Class<?> cls) {
        return invokeStaticMethod(getMethod(cls, "getUnsafe", new Class[0]), new Object[0]);
    }
}
