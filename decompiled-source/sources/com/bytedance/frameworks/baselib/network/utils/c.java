package com.bytedance.frameworks.baselib.network.utils;

import com.meituan.robust.Constants;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: TTReflect.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Field> f8518a = new ConcurrentHashMap();

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, Method> f8519b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    private static final Map<String, Constructor<?>> f8520c = new ConcurrentHashMap();

    /* renamed from: d  reason: collision with root package name */
    private static final String f8521d = "ReflectUtil";

    /* renamed from: e  reason: collision with root package name */
    private final Object f8522e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f8523f = true;

    public static c a(String str) {
        return a(e(str));
    }

    public static c a(Class<?> cls) {
        if (cls == null) {
            throw new d("Class object cannot be null");
        }
        return new c(cls);
    }

    public static c a(Object obj) {
        if (obj == null) {
            throw new d("Instance object cannot be null");
        }
        return new c(obj);
    }

    public static <T extends AccessibleObject> T a(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        try {
            if (!t.isAccessible()) {
                t.setAccessible(true);
            }
            return t;
        } catch (SecurityException e2) {
            throw new d("Failed to set accessible for " + t, e2, t.getClass().getSimpleName());
        }
    }

    private c(Class<?> cls) {
        this.f8522e = cls;
    }

    private c(Object obj) {
        this.f8522e = obj;
    }

    public <T> T a() {
        return (T) this.f8522e;
    }

    public c a(String str, Object obj) {
        return a(str, (Class<?>) null, obj);
    }

    public c a(String str, Class<?> cls, Object obj) {
        try {
            d(str, cls).set(this.f8522e, b(obj));
            return this;
        } catch (d e2) {
            throw e2;
        } catch (Exception e3) {
            throw new d("Failed to set field: " + str, e3, d(), str);
        }
    }

    public <T> T b(String str) {
        return (T) a(str, (Class<?>) null);
    }

    public <T> T a(String str, Class<?> cls) {
        return (T) b(str, cls).a();
    }

    public c c(String str) {
        return b(str, (Class<?>) null);
    }

    public c b(String str, Class<?> cls) {
        try {
            return a(d(str, cls).get(this.f8522e));
        } catch (d e2) {
            throw e2;
        } catch (Exception e3) {
            throw new d("Failed to reflect field: " + str, e3, d(), str);
        }
    }

    public Field c(String str, Class<?> cls) {
        return d(str, cls);
    }

    public c d(String str) {
        return a(str, new Object[0]);
    }

    public c a(String str, Object... objArr) {
        return a(str, b(objArr), objArr);
    }

    public c a(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            try {
                return a(a(str, clsArr), objArr);
            } catch (NoSuchMethodException unused) {
                return a(b(str, clsArr), objArr);
            }
        } catch (NoSuchMethodException e2) {
            throw new d("No method found: " + str, e2, d(), str, Arrays.toString(clsArr));
        }
    }

    public c b() {
        return a(new Object[0]);
    }

    public c a(Object... objArr) {
        Class<?>[] b2 = b(objArr);
        try {
            return a(((Constructor) a(a(b2))).newInstance(objArr));
        } catch (Exception e2) {
            throw new d("Failed to create instance", e2, d(), "constructor", Arrays.toString(b2));
        }
    }

    private Field d(String str, Class<?> cls) {
        if (str == null || str.isEmpty()) {
            throw new d("Field name cannot be empty");
        }
        String a2 = a(d(), str, cls);
        Field field = f8518a.get(a2);
        if (field != null) {
            return field;
        }
        Class<?> c2 = c();
        Field field2 = null;
        while (c2 != null && field2 == null) {
            if (cls != null) {
                try {
                    field2 = a(c2, str, cls);
                } catch (NoSuchFieldException unused) {
                    c2 = c2.getSuperclass();
                }
            } else {
                try {
                    field2 = c2.getField(str);
                } catch (NoSuchFieldException unused2) {
                    field2 = c2.getDeclaredField(str);
                }
            }
            c2 = c2.getSuperclass();
        }
        if (field2 == null) {
            String str2 = "Field not found: " + str;
            String[] strArr = new String[3];
            strArr[0] = d();
            strArr[1] = str;
            strArr[2] = cls != null ? cls.getName() : "unknown";
            throw new d(str2, null, strArr);
        }
        a(field2);
        f8518a.put(a2, field2);
        return field2;
    }

    private Field a(Class<?> cls, String str, Class<?> cls2) {
        Field[] declaredFields;
        for (Field field : cls.getDeclaredFields()) {
            if (field.getName().equals(str) && field.getType().getName().equals(cls2.getName())) {
                return field;
            }
        }
        throw new NoSuchFieldException();
    }

    private Method a(String str, Class<?>[] clsArr) {
        String a2 = a(d(), str, clsArr);
        Method method = f8519b.get(a2);
        if (method != null) {
            return method;
        }
        Class<?> c2 = c();
        Method method2 = null;
        while (c2 != null && method2 == null) {
            try {
                try {
                    method2 = c2.getMethod(str, clsArr);
                } catch (NoSuchMethodException unused) {
                    method2 = c2.getDeclaredMethod(str, clsArr);
                }
            } catch (NoSuchMethodException unused2) {
                c2 = c2.getSuperclass();
            }
        }
        if (method2 == null) {
            throw new NoSuchMethodException();
        }
        a(method2);
        f8519b.put(a2, method2);
        return method2;
    }

    private Method b(String str, Class<?>[] clsArr) {
        Class<?> c2 = c();
        Method method = null;
        while (c2 != null && method == null) {
            Method[] declaredMethods = c2.getDeclaredMethods();
            int length = declaredMethods.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                Method method2 = declaredMethods[i2];
                if (method2.getName().equals(str) && method2.getParameterTypes().length == clsArr.length && a(method2.getParameterTypes(), clsArr)) {
                    method = method2;
                    break;
                }
                i2++;
            }
            if (method == null) {
                c2 = c2.getSuperclass();
            }
        }
        if (method == null) {
            throw new NoSuchMethodException();
        }
        a(method);
        f8519b.put(a(d(), str, clsArr), method);
        return method;
    }

    private Constructor<?> a(Class<?>[] clsArr) {
        String a2 = a(d(), "constructor", clsArr);
        Constructor<?> constructor = f8520c.get(a2);
        if (constructor != null) {
            return constructor;
        }
        Constructor<?> constructor2 = null;
        try {
            constructor2 = c().getDeclaredConstructor(clsArr);
        } catch (NoSuchMethodException unused) {
            Constructor<?>[] declaredConstructors = c().getDeclaredConstructors();
            int length = declaredConstructors.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                Constructor<?> constructor3 = declaredConstructors[i2];
                if (a(constructor3.getParameterTypes(), clsArr)) {
                    constructor2 = constructor3;
                    break;
                }
                i2++;
            }
        }
        if (constructor2 == null) {
            throw new NoSuchMethodException();
        }
        a(constructor2);
        f8520c.put(a2, constructor2);
        return constructor2;
    }

    private c a(Method method, Object... objArr) {
        try {
            a(method);
            return method.getReturnType() == Void.TYPE ? this : a(method.invoke(this.f8522e, objArr));
        } catch (Exception e2) {
            throw new d("Failed to invoke method: " + method.getName(), e2, d(), method.getName(), Arrays.toString(method.getParameterTypes()));
        }
    }

    private String a(String str, String str2, Class<?>... clsArr) {
        StringBuilder append = new StringBuilder(str).append("#").append(str2);
        if (clsArr != null && clsArr.length > 0) {
            append.append(Constants.ARRAY_TYPE);
            for (int i2 = 0; i2 < clsArr.length; i2++) {
                Class<?> cls = clsArr[i2];
                append.append(cls != null ? cls.getName() : "null");
                if (i2 != clsArr.length - 1) {
                    append.append(",");
                }
            }
            append.append("]");
        }
        return append.toString();
    }

    private Object b(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof c) {
            return ((c) obj).a();
        }
        if (obj.getClass().isArray() && (obj instanceof Object[])) {
            Object[] objArr = (Object[]) obj;
            for (int i2 = 0; i2 < objArr.length; i2++) {
                objArr[i2] = b(objArr[i2]);
            }
        }
        return obj;
    }

    private boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            Class<?> b2 = b(clsArr[i2]);
            Class<?> b3 = clsArr2[i2] == a.class ? null : b(clsArr2[i2]);
            if (b3 != null && !b2.isAssignableFrom(b3)) {
                return false;
            }
        }
        return true;
    }

    private Class<?>[] b(Object... objArr) {
        if (objArr == null) {
            return new Class[0];
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            clsArr[i2] = obj == null ? a.class : obj.getClass();
        }
        return clsArr;
    }

    private static Class<?> e(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new d("Class not found: " + str, e2, str);
        } catch (Exception e3) {
            throw new d("Failed to load class: " + str, e3, str);
        }
    }

    public Class<?> c() {
        return this.f8523f ? (Class) this.f8522e : this.f8522e.getClass();
    }

    private String d() {
        return c().getName();
    }

    public static Class<?> b(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        return cls.isPrimitive() ? Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : Void.TYPE == cls ? Void.class : cls : cls;
    }

    public int hashCode() {
        return this.f8522e.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f8522e.equals(((c) obj).f8522e);
    }

    public String toString() {
        return "TTReflect{target=" + this.f8522e + ", isClassTarget=" + this.f8523f + ", targetClass=" + d() + '}';
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TTReflect.java */
    /* loaded from: classes2.dex */
    public static class a {
        private a() {
        }
    }
}
