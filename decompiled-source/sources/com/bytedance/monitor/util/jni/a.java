package com.bytedance.monitor.util.jni;

import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* compiled from: JavaCalls.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9679a = "JavaCalls";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f9680b;

    static {
        HashMap hashMap = new HashMap();
        f9680b = hashMap;
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
    /* renamed from: com.bytedance.monitor.util.jni.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0137a<T> {
        public final Class<? extends T> clazz;
        public final T obj;

        public C0137a(Class<? extends T> cls, T t) {
            this.clazz = cls;
            this.obj = t;
        }
    }

    public static <T> T getField(Object obj, String str) {
        try {
            return (T) getFieldOrThrow(obj, str);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static <T> T getFieldOrThrow(Object obj, String str) {
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

    public static void setField(Object obj, String str, Object obj2) {
        try {
            setFieldOrThrow(obj, str, obj2);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    public static void setFieldOrThrow(Object obj, String str, Object obj2) {
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

    public static <T> T callMethod(Object obj, String str, Object... objArr) {
        try {
            return (T) callMethodOrThrow(obj, str, objArr);
        } catch (Exception e2) {
            Log.w(f9679a, "Meet exception when call Method '" + str + "' in " + obj, e2);
            return null;
        }
    }

    public static <T> T callMethodOrThrow(Object obj, String str, Object... objArr) {
        return (T) a(obj.getClass(), str, a(objArr)).invoke(obj, b(objArr));
    }

    public static <T> T callStaticMethod(String str, String str2, Object... objArr) {
        try {
            return (T) callStaticMethodOrThrow(Class.forName(str), str2, objArr);
        } catch (Exception e2) {
            Log.w(f9679a, "Meet exception when call Method '" + str2 + "' in " + str, e2);
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method a2 = a(cls.getDeclaredMethods(), str, clsArr);
        if (a2 == null) {
            if (cls.getSuperclass() != null) {
                return a(cls.getSuperclass(), str, clsArr);
            }
            throw new NoSuchMethodException();
        }
        a2.setAccessible(true);
        return a2;
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
                    Map<Class<?>, Class<?>> map = f9680b;
                    if (!map.containsKey(clsArr[i2]) || !map.get(clsArr[i2]).equals(map.get(clsArr2[i2]))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static <T> T callStaticMethodOrThrow(String str, String str2, Object... objArr) {
        return (T) a(Class.forName(str), str2, a(objArr)).invoke(null, b(objArr));
    }

    public static <T> T callStaticMethodOrThrow(Class<?> cls, String str, Object... objArr) {
        return (T) a(cls, str, a(objArr)).invoke(null, b(objArr));
    }

    public static <T> T newInstance(Class<?> cls, Object... objArr) {
        try {
            return (T) newInstanceOrThrow(cls, objArr);
        } catch (Exception e2) {
            Log.w(f9679a, "Meet exception when make instance as a " + cls.getSimpleName(), e2);
            return null;
        }
    }

    public static <T> T newEmptyInstance(Class<?> cls) {
        try {
            return (T) newEmptyInstanceOrThrow(cls);
        } catch (Exception e2) {
            Log.w(f9679a, "Meet exception when make instance as a " + cls.getSimpleName(), e2);
            return null;
        }
    }

    public static <T> T newEmptyInstanceOrThrow(Class<?> cls) {
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
            objArr[i2] = a(parameterTypes[i2]);
        }
        return (T) constructor.newInstance(objArr);
    }

    private static Object a(Class<?> cls) {
        if (Integer.TYPE.equals(cls) || Integer.class.equals(cls) || Byte.TYPE.equals(cls) || Byte.class.equals(cls) || Short.TYPE.equals(cls) || Short.class.equals(cls) || Long.TYPE.equals(cls) || Long.class.equals(cls) || Double.TYPE.equals(cls) || Double.class.equals(cls) || Float.TYPE.equals(cls) || Float.class.equals(cls)) {
            return 0;
        }
        if (Boolean.TYPE.equals(cls) || Boolean.class.equals(cls)) {
            return false;
        }
        return (Character.TYPE.equals(cls) || Character.class.equals(cls)) ? (char) 0 : null;
    }

    public static <T> T newInstanceOrThrow(Class<?> cls, Object... objArr) {
        return (T) cls.getConstructor(a(objArr)).newInstance(b(objArr));
    }

    public static Object newInstance(String str, Object... objArr) {
        try {
            return newInstanceOrThrow(str, objArr);
        } catch (Exception e2) {
            Log.w(f9679a, "Meet exception when make instance as a " + str, e2);
            return null;
        }
    }

    public static Object newInstanceOrThrow(String str, Object... objArr) {
        return newInstanceOrThrow(Class.forName(str), b(objArr));
    }

    private static Class<?>[] a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            if (obj != null && (obj instanceof C0137a)) {
                clsArr[i2] = ((C0137a) obj).clazz;
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
            if (obj != null && (obj instanceof C0137a)) {
                objArr2[i2] = ((C0137a) obj).obj;
            } else {
                objArr2[i2] = obj;
            }
        }
        return objArr2;
    }
}
