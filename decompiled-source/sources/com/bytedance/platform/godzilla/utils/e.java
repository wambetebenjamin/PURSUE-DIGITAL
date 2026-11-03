package com.bytedance.platform.godzilla.utils;

import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/* compiled from: MethodUtils.java */
/* loaded from: classes2.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Method> f11096a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private static final HashMap<Class<?>, Class<?>> f11097b;

    static {
        HashMap<Class<?>, Class<?>> hashMap = new HashMap<>();
        f11097b = hashMap;
        hashMap.put(Boolean.TYPE, Boolean.class);
        hashMap.put(Byte.TYPE, Byte.class);
        hashMap.put(Character.TYPE, Character.class);
        hashMap.put(Short.TYPE, Short.class);
        hashMap.put(Integer.TYPE, Integer.class);
        hashMap.put(Long.TYPE, Long.class);
        hashMap.put(Double.TYPE, Double.class);
        hashMap.put(Float.TYPE, Float.class);
        hashMap.put(Void.TYPE, Void.class);
    }

    private e() {
    }

    private static String a(Class<?> cls, String str, Class... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString()).append("#").append(str);
        if (clsArr != null && clsArr.length > 0) {
            for (Class cls2 : clsArr) {
                sb.append(cls2.toString()).append("#");
            }
        } else {
            sb.append(Void.class.toString());
        }
        return sb.toString();
    }

    public static Method getAccessibleMethod(Class<?> cls, String str, Class... clsArr) {
        Method method;
        Method[] declaredMethods;
        g.checkNotNull(cls, "The class must not be null");
        g.checkTrue(!TextUtils.isEmpty(str), "The method name must not be blank");
        String a2 = a(cls, str, clsArr);
        synchronized (f11096a) {
            method = f11096a.get(a2);
        }
        if (method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method;
        }
        while (cls != null) {
            try {
                if (f.isAndroidPHigher() && d.getInstance() != null) {
                    method = d.getInstance().getMethod(cls, str, clsArr);
                    if (method == null) {
                        method = cls.getDeclaredMethod(str, clsArr);
                    }
                } else {
                    method = cls.getDeclaredMethod(str, clsArr);
                }
            } catch (NoSuchMethodException unused) {
            }
            if (method == null && (declaredMethods = cls.getDeclaredMethods()) != null) {
                for (Method method2 : declaredMethods) {
                    if (method2 != null && TextUtils.equals(method2.getName(), str)) {
                        Class<?>[] parameterTypes = method2.getParameterTypes();
                        if (clsArr != null && clsArr.length == parameterTypes.length) {
                            boolean z = true;
                            for (int i2 = 0; i2 < clsArr.length; i2++) {
                                if (!isAssignableFrom(clsArr[i2], parameterTypes[i2])) {
                                    z = false;
                                }
                            }
                            if (z) {
                                method = method2;
                            }
                        }
                    }
                }
            }
            if (method != null) {
                method.setAccessible(true);
                synchronized (f11096a) {
                    f11096a.put(a2, method);
                }
                return method;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static boolean isAssignableFrom(Class<?> cls, Class<?> cls2) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (cls.isPrimitive() && !cls2.isPrimitive()) {
            cls = f11097b.get(cls);
        }
        if (cls2.isPrimitive() && !cls.isPrimitive()) {
            cls2 = f11097b.get(cls2);
        }
        return cls2.isAssignableFrom(cls);
    }

    private static Object a(Class cls, String str, Object[] objArr, Class<?>[] clsArr) {
        Class<?>[] nullToEmpty = c.nullToEmpty(clsArr);
        Object[] nullToEmpty2 = c.nullToEmpty(objArr);
        Method accessibleMethod = getAccessibleMethod(cls, str, nullToEmpty);
        if (accessibleMethod != null) {
            return accessibleMethod.invoke(null, nullToEmpty2);
        }
        return null;
    }

    public static Object invokeStaticMethod(Class cls, String str, Object... objArr) {
        Object[] nullToEmpty = c.nullToEmpty(objArr);
        return a(cls, str, nullToEmpty, c.toClass(nullToEmpty));
    }
}
