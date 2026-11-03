package com.bytedance.crash.terminate;

import com.bytedance.crash.util.y;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* compiled from: JavaCalls.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7128a = "JavaCalls";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f7129b;

    static {
        HashMap hashMap = new HashMap();
        f7129b = hashMap;
        hashMap.put(Boolean.class, Boolean.TYPE);
        hashMap.put(Byte.class, Byte.TYPE);
        hashMap.put(Character.class, Character.TYPE);
        hashMap.put(Short.class, Short.TYPE);
        hashMap.put(Integer.class, Integer.TYPE);
        hashMap.put(Float.class, Float.TYPE);
        hashMap.put(Long.class, Long.TYPE);
        hashMap.put(Double.class, Double.TYPE);
        hashMap.put(Boolean.TYPE, Boolean.TYPE);
        hashMap.put(Byte.TYPE, Byte.TYPE);
        hashMap.put(Character.TYPE, Character.TYPE);
        hashMap.put(Short.TYPE, Short.TYPE);
        hashMap.put(Integer.TYPE, Integer.TYPE);
        hashMap.put(Float.TYPE, Float.TYPE);
        hashMap.put(Long.TYPE, Long.TYPE);
        hashMap.put(Double.TYPE, Double.TYPE);
    }

    /* compiled from: JavaCalls.java */
    /* loaded from: classes2.dex */
    public static class a<T> {

        /* renamed from: a  reason: collision with root package name */
        final Class<? extends T> f7130a;

        /* renamed from: b  reason: collision with root package name */
        final T f7131b;

        public a(Class<? extends T> cls, T t) {
            this.f7130a = cls;
            this.f7131b = t;
        }
    }

    public static <T> T a(Object obj, String str) {
        try {
            return (T) b(obj, str);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    private static <T> T b(Object obj, String str) {
        Class<?> cls = obj.getClass();
        Field field = null;
        while (field == null) {
            try {
                field = cls.getDeclaredField(str);
                field.setAccessible(true);
                continue;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
                continue;
            }
            if (cls == null) {
                throw new NoSuchFieldException();
            }
        }
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    public static void a(Object obj, String str, Object obj2) {
        try {
            b(obj, str, obj2);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    private static void b(Object obj, String str, Object obj2) {
        Class<?> cls = obj.getClass();
        Field field = null;
        while (field == null) {
            try {
                field = cls.getDeclaredField(str);
                continue;
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
                continue;
            }
            if (cls == null) {
                throw new NoSuchFieldException();
            }
        }
        field.setAccessible(true);
        field.set(obj, obj2);
    }

    public static <T> T a(Object obj, String str, Object... objArr) {
        try {
            return (T) b(obj, str, objArr);
        } catch (Exception e2) {
            y.a(f7128a, "Meet exception when call Method '" + str + "' in " + obj, e2);
            return null;
        }
    }

    private static <T> T b(Object obj, String str, Object... objArr) {
        return (T) a(obj.getClass(), str, a(objArr)).invoke(obj, b(objArr));
    }

    public static <T> T a(String str, String str2, Object... objArr) {
        try {
            return (T) a(Class.forName(str), str2, objArr);
        } catch (Exception e2) {
            y.a(f7128a, "Meet exception when call Method '" + str2 + "' in " + str, e2);
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            if (declaredMethod == null) {
                throw new NoSuchMethodException();
            }
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException unused) {
            if (cls.getSuperclass() != null) {
                return a((Class<?>) cls.getSuperclass(), str, clsArr);
            }
            throw new NoSuchMethodException();
        }
    }

    private static Method a(Method[] methodArr, String str, Class<?>[] clsArr) {
        Objects.requireNonNull(str, "Method name must not be null.");
        for (Method method : methodArr) {
            if (method.getName().equals(str) && a(method.getParameterTypes(), clsArr)) {
                return method;
            }
        }
        return null;
    }

    private static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr == null) {
            return clsArr2 == null || clsArr2.length == 0;
        } else if (clsArr2 == null) {
            return clsArr.length == 0;
        } else if (clsArr.length != clsArr2.length) {
            return false;
        } else {
            for (int i2 = 0; i2 < clsArr.length; i2++) {
                if (!clsArr[i2].isAssignableFrom(clsArr2[i2])) {
                    Map<Class<?>, Class<?>> map = f7129b;
                    if (!map.containsKey(clsArr[i2]) || !map.get(clsArr[i2]).equals(map.get(clsArr2[i2]))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static <T> T b(String str, String str2, Object... objArr) {
        return (T) a(Class.forName(str), str2, a(objArr)).invoke(null, b(objArr));
    }

    private static <T> T a(Class<?> cls, String str, Object... objArr) {
        return (T) a(cls, str, a(objArr)).invoke(null, b(objArr));
    }

    public static <T> T a(Class<?> cls, Object... objArr) {
        try {
            return (T) b(cls, objArr);
        } catch (Exception e2) {
            y.a(f7128a, "Meet exception when make instance as a " + cls.getSimpleName(), e2);
            return null;
        }
    }

    public static <T> T a(Class<?> cls) {
        try {
            return (T) b(cls);
        } catch (Exception e2) {
            y.a(f7128a, "Meet exception when make instance as a " + cls.getSimpleName(), e2);
            return null;
        }
    }

    private static <T> T b(Class<?> cls) {
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        if (declaredConstructors == null || declaredConstructors.length == 0) {
            throw new IllegalArgumentException("Can't get even one available constructor for " + cls);
        }
        Constructor<?> constructor = declaredConstructors[0];
        constructor.setAccessible(true);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length == 0) {
            return (T) constructor.newInstance(new Object[0]);
        }
        Object[] objArr = new Object[parameterTypes.length];
        for (int i2 = 0; i2 < parameterTypes.length; i2++) {
            objArr[i2] = c(parameterTypes[i2]);
        }
        return (T) constructor.newInstance(objArr);
    }

    private static Object c(Class<?> cls) {
        if (Integer.TYPE.equals(cls) || Integer.class.equals(cls) || Byte.TYPE.equals(cls) || Byte.class.equals(cls) || Short.TYPE.equals(cls) || Short.class.equals(cls) || Long.TYPE.equals(cls) || Long.class.equals(cls) || Double.TYPE.equals(cls) || Double.class.equals(cls) || Float.TYPE.equals(cls) || Float.class.equals(cls)) {
            return 0;
        }
        if (Boolean.TYPE.equals(cls) || Boolean.class.equals(cls)) {
            return false;
        }
        return (Character.TYPE.equals(cls) || Character.class.equals(cls)) ? (char) 0 : null;
    }

    private static <T> T b(Class<?> cls, Object... objArr) {
        return (T) cls.getConstructor(a(objArr)).newInstance(b(objArr));
    }

    public static Object a(String str, Object... objArr) {
        try {
            return b(str, objArr);
        } catch (Exception e2) {
            y.a(f7128a, "Meet exception when make instance as a " + str, e2);
            return null;
        }
    }

    private static Object b(String str, Object... objArr) {
        return b(Class.forName(str), b(objArr));
    }

    private static Class<?>[] a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            if (obj != null && (obj instanceof a)) {
                clsArr[i2] = ((a) obj).f7130a;
            } else {
                clsArr[i2] = obj == null ? null : obj.getClass();
            }
        }
        return clsArr;
    }

    private static Object[] b(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            if (obj != null && (obj instanceof a)) {
                objArr2[i2] = ((a) obj).f7131b;
            } else {
                objArr2[i2] = obj;
            }
        }
        return objArr2;
    }
}
