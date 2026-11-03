package com.bytedance.push.utils;

import com.bytedance.common.utility.Logger;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DoubleReflectUtils.java */
/* loaded from: classes2.dex */
public class t {

    /* renamed from: b  reason: collision with root package name */
    private static final String f12073b = "DoubleReflector";

    /* renamed from: c  reason: collision with root package name */
    private static Method f12074c;

    /* renamed from: d  reason: collision with root package name */
    private static Method f12075d;

    /* renamed from: e  reason: collision with root package name */
    private static Method f12076e;

    /* renamed from: f  reason: collision with root package name */
    private static Method f12077f;

    /* renamed from: g  reason: collision with root package name */
    private static Method f12078g;

    /* renamed from: i  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f12080i;

    /* renamed from: j  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f12081j;
    private static Class<?>[] k;

    /* renamed from: h  reason: collision with root package name */
    private static final HashMap<String, Method> f12079h = new HashMap<>();

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?>[] f12072a = new Class[0];

    static {
        HashMap hashMap = new HashMap();
        f12080i = hashMap;
        f12081j = new HashMap();
        k = new Class[]{Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
        try {
            f12074c = Class.class.getDeclaredMethod("getDeclaredField", String.class);
            f12075d = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            f12076e = Class.class.getDeclaredMethod("getDeclaredMethods", new Class[0]);
            f12077f = Class.class.getDeclaredMethod("getDeclaredConstructor", Class[].class);
            f12078g = Class.class.getDeclaredMethod("forName", String.class);
            hashMap.put(Boolean.TYPE, Boolean.class);
            hashMap.put(Byte.TYPE, Byte.class);
            hashMap.put(Character.TYPE, Character.class);
            hashMap.put(Short.TYPE, Short.class);
            hashMap.put(Integer.TYPE, Integer.class);
            hashMap.put(Long.TYPE, Long.class);
            hashMap.put(Double.TYPE, Double.class);
            hashMap.put(Float.TYPE, Float.class);
            hashMap.put(Void.TYPE, Void.TYPE);
            for (Map.Entry entry : hashMap.entrySet()) {
                Class<?> cls = (Class) entry.getKey();
                Class<?> cls2 = (Class) entry.getValue();
                if (!cls.equals(cls2)) {
                    f12081j.put(cls2, cls);
                }
            }
        } catch (Throwable th) {
            Logger.e(f12073b, "DoubleReflectorinit failed", th);
        }
    }

    public static Field a(Class<?> cls, String str) {
        return (Field) a(b(cls, str));
    }

    public static Field b(Class<?> cls, String str) {
        a(cls, "The class must not be null !!!");
        Method method = f12074c;
        if (method != null) {
            try {
                return (Field) method.invoke(cls, str);
            } catch (Throwable th) {
                Logger.e(f12073b, f12073b + String.format("getField %s#%s failed !!!", cls.getName(), str), th);
            }
        }
        return null;
    }

    public static Field c(Class<?> cls, String str) {
        a(cls, "The class must not be null !!!");
        while (cls != null) {
            try {
                return (Field) f12074c.invoke(cls, str);
            } catch (Throwable th) {
                Logger.e(f12073b, "DoubleReflectorgetFieldAll failed", th);
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static boolean a(Object obj, String str, Object obj2) {
        try {
            Field b2 = b(obj.getClass(), str);
            b2.setAccessible(true);
            b2.set(obj, obj2);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static <T extends AccessibleObject> T a(T t) {
        if (t != null && !t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        return (Method) a(b(cls, str, clsArr));
    }

    public static Method b(Class<?> cls, String str, Class<?>... clsArr) {
        a(cls, "The class must not be null !!!");
        Method method = f12075d;
        if (method != null) {
            try {
                return (Method) method.invoke(cls, str, clsArr);
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static Method[] a(Class<?> cls) {
        a(cls, "The class must not be null !!!");
        Method method = f12076e;
        if (method != null) {
            try {
                return (Method[]) method.invoke(cls, new Object[0]);
            } catch (Throwable th) {
                Logger.e(f12073b, f12073b + String.format("getMethods %s#%s failed !!!", cls.getName()), th);
            }
        }
        return null;
    }

    public static Constructor a(Class<?> cls, Class<?>... clsArr) {
        a(cls, "The class must not be null !!!");
        Method method = f12077f;
        if (method != null) {
            try {
                return (Constructor) method.invoke(cls, clsArr);
            } catch (Throwable th) {
                StringBuilder append = new StringBuilder().append(f12073b);
                Object[] objArr = new Object[2];
                objArr[0] = cls.getName();
                objArr[1] = Integer.valueOf(clsArr != null ? clsArr.length : 0);
                Logger.e(f12073b, append.append(String.format("getConstructor %s<init>%s failed !!!", objArr)).toString(), th);
            }
        }
        return null;
    }

    public static Class<?> a(String str) {
        a(str, "The class name can not be null !!!");
        Method method = f12078g;
        if (method != null) {
            try {
                return (Class) method.invoke(null, str);
            } catch (Throwable th) {
                Logger.e(f12073b, f12073b + String.format("getClass %s failed !!!", str), th);
                return null;
            }
        }
        return null;
    }

    private static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str);
    }

    public static Object a(Object obj, String str, Object... objArr) {
        try {
            return a(obj.getClass(), str, objArr).invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (InvocationTargetException e4) {
            throw new IllegalStateException(e4.getCause());
        }
    }

    private static Method b(String str) {
        HashMap<String, Method> hashMap = f12079h;
        if (hashMap.containsKey(str)) {
            Method method = hashMap.get(str);
            if (method != null) {
                return method;
            }
            throw new NoSuchMethodError(str);
        }
        return null;
    }

    private static Method a(String str, Class<?> cls, String str2, Class<?>... clsArr) {
        try {
            Method e2 = e(cls, str2, clsArr);
            f12079h.put(str, e2);
            return e2;
        } catch (NoSuchMethodError unused) {
            return null;
        }
    }

    private static Method d(Class<?> cls, String str, Class<?>... clsArr) {
        Method[] a2;
        boolean z = true;
        Method method = null;
        while (true) {
            for (Method method2 : a(cls)) {
                if ((z || !Modifier.isPrivate(method2.getModifiers())) && method2.getName().equals(str) && a(clsArr, method2.getParameterTypes()) && (method == null || a(method2.getParameterTypes(), method.getParameterTypes(), clsArr) < 0)) {
                    method = method2;
                }
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                return method;
            }
            z = false;
        }
    }

    public static Method c(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + a(clsArr) + "#best";
        Method b2 = b(str2);
        if (b2 != null) {
            return b2;
        }
        Method a2 = a(str2, cls, str, clsArr);
        if (a2 != null) {
            return a2;
        }
        Method d2 = d(cls, str, clsArr);
        if (d2 != null) {
            d2.setAccessible(true);
            f12079h.put(str2, d2);
            return d2;
        }
        NoSuchMethodError noSuchMethodError = new NoSuchMethodError(str2);
        f12079h.put(str2, null);
        throw noSuchMethodError;
    }

    private static Method a(Class<?> cls, String str, Object... objArr) {
        return c(cls, str, a(objArr));
    }

    private static Class<?>[] a(Object... objArr) {
        if (objArr == null) {
            return f12072a;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            clsArr[i2] = objArr[i2] != null ? objArr[i2].getClass() : null;
        }
        return clsArr;
    }

    private static Method e(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + '#' + str + a(clsArr) + "#exact";
        HashMap<String, Method> hashMap = f12079h;
        if (hashMap.containsKey(str2)) {
            Method method = hashMap.get(str2);
            if (method != null) {
                return method;
            }
            throw new NoSuchMethodError(str2);
        }
        Method b2 = b(cls, str, clsArr);
        b2.setAccessible(true);
        hashMap.put(str2, b2);
        return b2;
    }

    private static String a(Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder("(");
        boolean z = true;
        for (Class<?> cls : clsArr) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            if (cls != null) {
                sb.append(cls.getCanonicalName());
            } else {
                sb.append("null");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static boolean a(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = b(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = c(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (cls2.isPrimitive()) {
                if (Integer.TYPE.equals(cls)) {
                    return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (Long.TYPE.equals(cls)) {
                    return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                    return false;
                } else {
                    if (Float.TYPE.equals(cls)) {
                        return Double.TYPE.equals(cls2);
                    }
                    if (Character.TYPE.equals(cls)) {
                        return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else if (Short.TYPE.equals(cls)) {
                        return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else if (Byte.TYPE.equals(cls)) {
                        return Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
        return cls2.isAssignableFrom(cls);
    }

    public static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (a((Object[]) clsArr, (Object[]) clsArr2)) {
            if (clsArr == null) {
                clsArr = f12072a;
            }
            if (clsArr2 == null) {
                clsArr2 = f12072a;
            }
            for (int i2 = 0; i2 < clsArr.length; i2++) {
                if (!a(clsArr[i2], clsArr2[i2], true)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static Class<?> b(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : f12080i.get(cls);
    }

    public static Class<?> c(Class<?> cls) {
        return f12081j.get(cls);
    }

    public static boolean a(Object[] objArr, Object[] objArr2) {
        return (objArr != null || objArr2 == null || objArr2.length <= 0) && (objArr2 != null || objArr == null || objArr.length <= 0) && (objArr == null || objArr2 == null || objArr.length == objArr2.length);
    }

    private static int a(Class<?>[] clsArr, Class<?>[] clsArr2, Class<?>[] clsArr3) {
        return Float.compare(b(clsArr3, clsArr), b(clsArr3, clsArr2));
    }

    private static float b(Class<?>[] clsArr, Class<?>[] clsArr2) {
        int length = clsArr.length;
        float f2 = 0.0f;
        for (int i2 = 0; i2 < length; i2++) {
            f2 += a(clsArr[i2], clsArr2[i2]);
        }
        return f2;
    }

    private static float a(Class<?> cls, Class<?> cls2) {
        if (cls2.isPrimitive()) {
            return b(cls, cls2);
        }
        float f2 = 0.0f;
        while (true) {
            if (cls != null && !cls2.equals(cls)) {
                if (cls2.isInterface() && a(cls, cls2, true)) {
                    f2 += 0.25f;
                    break;
                }
                f2 += 1.0f;
                cls = cls.getSuperclass();
            } else {
                break;
            }
        }
        return cls == null ? f2 + 1.5f : f2;
    }

    private static float b(Class<?> cls, Class<?> cls2) {
        float f2;
        if (cls.isPrimitive()) {
            f2 = 0.0f;
        } else {
            cls = c(cls);
            f2 = 0.1f;
        }
        int i2 = 0;
        while (cls != cls2) {
            Class<?>[] clsArr = k;
            if (i2 >= clsArr.length) {
                break;
            }
            if (cls == clsArr[i2]) {
                f2 += 0.1f;
                if (i2 < clsArr.length - 1) {
                    cls = clsArr[i2 + 1];
                }
            }
            i2++;
        }
        return f2;
    }
}
