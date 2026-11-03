package com.bytedance.monitor.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
/* compiled from: MethodUtils.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Method> f9676a = new HashMap();

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (Modifier.isPublic(superclass.getModifiers())) {
                try {
                    return superclass.getMethod(str, clsArr);
                } catch (Exception unused) {
                    return null;
                }
            }
        }
        return null;
    }

    private static String b(Class<?> cls, String str, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString());
        sb.append("#");
        sb.append(str);
        if (clsArr != null && clsArr.length > 0) {
            for (Class<?> cls2 : clsArr) {
                sb.append(cls2.toString()).append("#");
            }
        } else {
            sb.append(Void.class.toString());
        }
        return sb.toString();
    }

    private static Method c(Class<?> cls, String str, Class<?>... clsArr) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int i2 = 0; i2 < interfaces.length; i2++) {
                if (Modifier.isPublic(interfaces[i2].getModifiers())) {
                    try {
                        return interfaces[i2].getDeclaredMethod(str, clsArr);
                    } catch (NoSuchMethodException unused) {
                        Method c2 = c(interfaces[i2], str, clsArr);
                        if (c2 != null) {
                            return c2;
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private static Method a(Method method) {
        if (d.a((Member) method)) {
            Class<?> declaringClass = method.getDeclaringClass();
            if (Modifier.isPublic(declaringClass.getModifiers())) {
                return method;
            }
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Method c2 = c(declaringClass, name, parameterTypes);
            return c2 == null ? a(declaringClass, name, parameterTypes) : c2;
        }
        return null;
    }

    private static Method a(String str) {
        Method method;
        synchronized (f9676a) {
            method = f9676a.get(str);
        }
        if (method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method;
        }
        return null;
    }

    private static Method d(Class<?> cls, String str, Class<?>... clsArr) {
        String b2 = b(cls, str, clsArr);
        Method a2 = a(b2);
        if (a2 != null) {
            return a2;
        }
        try {
            Method method = cls.getMethod(str, clsArr);
            d.a((AccessibleObject) method);
            synchronized (f9676a) {
                f9676a.put(b2, method);
            }
            return method;
        } catch (NoSuchMethodException unused) {
            return a(cls, b2, str, clsArr);
        }
    }

    private static Method a(Class<?> cls, String str, String str2, Class<?>... clsArr) {
        Method[] methods;
        Method a2;
        Method method = null;
        for (Method method2 : cls.getMethods()) {
            if (method2.getName().equals(str2) && d.a(clsArr, method2.getParameterTypes(), true) && (a2 = a(method2)) != null && (method == null || d.a(a2.getParameterTypes(), method.getParameterTypes(), clsArr) < 0)) {
                method = a2;
            }
        }
        if (method != null) {
            d.a((AccessibleObject) method);
        }
        synchronized (f9676a) {
            f9676a.put(str, method);
        }
        return method;
    }

    public static Object invokeStaticMethod(Class cls, String str, Object... objArr) {
        return invokeStaticMethod(cls, str, f.a(objArr), f.b(objArr));
    }

    public static Object invokeStaticMethod(Class cls, String str, Object[] objArr, Class<?>[] clsArr) {
        Method d2 = d(cls, str, clsArr);
        if (d2 == null) {
            throw new NoSuchMethodException("No such accessible method: " + str + "() on object: " + cls.getName());
        }
        return d2.invoke(null, f.a(objArr));
    }
}
