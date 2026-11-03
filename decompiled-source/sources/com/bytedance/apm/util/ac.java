package com.bytedance.apm.util;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import gsdk.impl.account.toutiao.dh;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
/* compiled from: ReflectionUtils.java */
/* loaded from: classes.dex */
public class ac {

    /* renamed from: a  reason: collision with root package name */
    private final Object f1653a;

    /* renamed from: b  reason: collision with root package name */
    private final Class<?> f1654b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f1655c;

    private ac(Class<?> cls) {
        this.f1653a = cls;
        this.f1654b = cls;
        this.f1655c = true;
    }

    private ac(Object obj) {
        this.f1653a = obj;
        this.f1654b = obj != null ? obj.getClass() : null;
        this.f1655c = false;
    }

    private ac(Object obj, Class<?> cls) {
        this.f1653a = obj;
        this.f1654b = cls;
        this.f1655c = false;
    }

    public static <T> T getFieldValue(Object obj, String str) {
        return (T) getFieldValue(obj, str, true);
    }

    public static <T> T getFieldValue(Object obj, String str, boolean z) {
        Object[] a2 = a(obj, str, z);
        if (a2 == null) {
            throw new NoSuchFieldException("field:" + str);
        }
        return (T) ((Field) a2[0]).get(a2[1]);
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        setFieldValue(obj, str, obj2, true);
    }

    public static void setFieldValue(Object obj, String str, Object obj2, boolean z) {
        Object[] a2 = a(obj, str, z);
        if (a2 == null) {
            throw new NoSuchFieldException("field:" + str);
        }
        ((Field) a2[0]).set(a2[1], obj2);
    }

    private static Object[] a(Object obj, String str, boolean z) {
        if (obj == null) {
            return null;
        }
        String[] split = str.split("[.]");
        Class<?> cls = obj.getClass();
        Object[] objArr = new Object[2];
        int length = split.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str2 = split[i2];
            i3++;
            Field field_ = getField_(cls, str2, z);
            if (field_ != null) {
                field_.setAccessible(true);
                objArr[0] = field_;
                objArr[1] = obj;
                obj = field_.get(obj);
                if (obj == null) {
                    if (i3 < split.length) {
                        throw new IllegalAccessException("can not getFieldValue as field '" + str2 + "' value is null in '" + cls.getName() + "'");
                    }
                } else {
                    cls = obj.getClass();
                }
            }
            i2++;
        }
        return objArr;
    }

    public static Field getField_(Class<?> cls, String str, boolean z) {
        Field field;
        NoSuchFieldException e2 = null;
        try {
            field = cls.getDeclaredField(str);
            if (!z) {
                try {
                    field.setAccessible(true);
                    return field;
                } catch (NoSuchFieldException e3) {
                    e2 = e3;
                }
            }
        } catch (NoSuchFieldException e4) {
            field = null;
            e2 = e4;
        }
        if (e2 != null) {
            if (z) {
                do {
                    cls = cls.getSuperclass();
                    if (cls != null) {
                        try {
                            Field declaredField = cls.getDeclaredField(str);
                            declaredField.setAccessible(true);
                            return declaredField;
                        } catch (NoSuchFieldException e5) {
                            if (cls.getSuperclass() == null) {
                                throw e5;
                            }
                        }
                    }
                } while (cls.getSuperclass() == null);
                throw e5;
            }
            throw e2;
        }
        return field;
    }

    public static ac on(String str) {
        return on(c(str));
    }

    public static ac on(Class<?> cls) {
        return new ac(cls);
    }

    public static ac on(Object obj) {
        return new ac(obj);
    }

    public static ac on(Object obj, Class<?> cls) {
        return new ac(obj, cls);
    }

    private static <T extends AccessibleObject> T a(T t) {
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers())) {
                Modifier.isPublic(member.getDeclaringClass().getModifiers());
            }
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private static ac a(Constructor<?> constructor, Object... objArr) {
        try {
            return on(((Constructor) a(constructor)).newInstance(objArr));
        } catch (Exception e2) {
            throw new ab(e2);
        }
    }

    private static ac a(Method method, Object obj, Object... objArr) {
        try {
            a(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(obj, objArr);
                return on(obj);
            }
            return on(method.invoke(obj, objArr));
        } catch (Exception e2) {
            throw new ab(e2);
        }
    }

    private static Object a(Object obj) {
        return obj instanceof ac ? ((ac) obj).get() : obj;
    }

    private static Class<?>[] a(Object... objArr) {
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

    private static Class<?> c(String str) {
        try {
            return Class.forName(str);
        } catch (Exception e2) {
            throw new ab(e2);
        }
    }

    private static Class<?> a(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (Boolean.TYPE == cls) {
                return Boolean.class;
            }
            if (Integer.TYPE == cls) {
                return Integer.class;
            }
            if (Long.TYPE == cls) {
                return Long.class;
            }
            if (Short.TYPE == cls) {
                return Short.class;
            }
            if (Byte.TYPE == cls) {
                return Byte.class;
            }
            if (Double.TYPE == cls) {
                return Double.class;
            }
            if (Float.TYPE == cls) {
                return Float.class;
            }
            if (Character.TYPE == cls) {
                return Character.class;
            }
            return Void.TYPE == cls ? Void.class : cls;
        }
        return cls;
    }

    public <T> T get() {
        return (T) this.f1653a;
    }

    public ac set(String str, Object obj) {
        try {
            Field e2 = e(str);
            if (e2 != null) {
                e2.set(this.f1653a, a(obj));
            }
            return this;
        } catch (Exception e3) {
            throw new ab(e3);
        }
    }

    public ac setNoException(String str, Object obj) {
        try {
            set(str, obj);
        } catch (ab e2) {
            e2.printStackTrace();
        }
        return this;
    }

    public <T> T get(String str) {
        return (T) d(str).get();
    }

    public <T> T getNoException(String str) {
        try {
            return (T) get(str);
        } catch (ab e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private ac d(String str) {
        try {
            return on(e(str).get(this.f1653a));
        } catch (Exception e2) {
            throw new ab(e2);
        }
    }

    private Field e(String str) {
        Class<?> a2 = a();
        try {
            return (Field) a(a2.getField(str));
        } catch (NoSuchFieldException e2) {
            do {
                try {
                    return (Field) a(a2.getDeclaredField(str));
                } catch (NoSuchFieldException unused) {
                    a2 = a2.getSuperclass();
                    if (a2 != null) {
                        throw new ab(e2);
                    }
                }
            } while (a2 != null);
            throw new ab(e2);
        }
    }

    public Map<String, ac> fields() {
        Field[] declaredFields;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Class<?> a2 = a();
        do {
            for (Field field : a2.getDeclaredFields()) {
                if ((!this.f1655c) ^ Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    if (!linkedHashMap.containsKey(name)) {
                        linkedHashMap.put(name, d(name));
                    }
                }
            }
            a2 = a2.getSuperclass();
        } while (a2 != null);
        return linkedHashMap;
    }

    public ac call(String str) {
        return call(str, new Object[0]);
    }

    public ac call(String str, Object... objArr) {
        return call(str, null, null, objArr);
    }

    public ac call(String str, Map<String, Vector<Method>> map, Class<?>[] clsArr, Object... objArr) {
        Method a2;
        Class<?>[] a3 = a(objArr);
        if (map != null) {
            try {
                try {
                    ac a4 = a(str, map, a3, objArr);
                    if (a4 != null) {
                        return a4;
                    }
                } catch (NoSuchMethodException unused) {
                    Method b2 = b(str, a3);
                    if (map != null && b2 != null) {
                        Vector<Method> vector = map.get(str);
                        if (vector == null) {
                            vector = new Vector<>(4);
                            map.put(str, vector);
                        }
                        vector.add(b2);
                    }
                    return a(b2, this.f1653a, objArr);
                }
            } catch (NoSuchMethodException e2) {
                throw new ab(e2);
            }
        }
        if (clsArr != null) {
            try {
                a2 = a(str, clsArr);
            } catch (NoSuchMethodException unused2) {
                a2 = a(str, a3);
            }
        } else {
            a2 = a(str, a3);
        }
        if (map != null && a2 != null) {
            Vector<Method> vector2 = map.get(str);
            if (vector2 == null) {
                vector2 = new Vector<>(4);
                map.put(str, vector2);
            }
            vector2.add(a2);
        }
        return a(a2, this.f1653a, objArr);
    }

    private ac a(String str, Map<String, Vector<Method>> map, Class<?>[] clsArr, Object... objArr) {
        Vector<Method> vector = map.get(str);
        if (vector != null) {
            Iterator<Method> it = vector.iterator();
            while (it.hasNext()) {
                Method next = it.next();
                if (next != null && next.getDeclaringClass().isAssignableFrom(a()) && a(next, str, clsArr)) {
                    return a(next, this.f1653a, objArr);
                }
            }
            return null;
        }
        return null;
    }

    private Method a(String str, Class<?>[] clsArr) {
        Class<?> a2 = a();
        try {
            return (Method) a(a2.getMethod(str, clsArr));
        } catch (NoSuchMethodException e2) {
            do {
                try {
                    return (Method) a(a2.getDeclaredMethod(str, clsArr));
                } catch (NoSuchMethodException unused) {
                    a2 = a2.getSuperclass();
                    if (a2 != null) {
                        throw new NoSuchMethodException(e2.getMessage());
                    }
                }
            } while (a2 != null);
            throw new NoSuchMethodException(e2.getMessage());
        }
    }

    private Method b(String str, Class<?>[] clsArr) {
        Method[] declaredMethods;
        Method[] methods;
        Class<?> a2 = a();
        try {
            for (Method method : a2.getMethods()) {
                if (a(method, str, clsArr)) {
                    return (Method) a(method);
                }
            }
        } catch (NoClassDefFoundError unused) {
        }
        do {
            try {
                for (Method method2 : a2.getDeclaredMethods()) {
                    if (a(method2, str, clsArr)) {
                        return (Method) a(method2);
                    }
                }
            } catch (NoClassDefFoundError unused2) {
            }
            a2 = a2.getSuperclass();
        } while (a2 != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + a() + c.a.DOT);
    }

    private boolean a(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && a(method.getParameterTypes(), clsArr);
    }

    public ac create() {
        return create(new Object[0]);
    }

    public ac create(Object... objArr) {
        Constructor<?>[] declaredConstructors;
        Class<?>[] a2 = a(objArr);
        try {
            return a(a().getDeclaredConstructor(a2), objArr);
        } catch (NoSuchMethodException e2) {
            for (Constructor<?> constructor : a().getDeclaredConstructors()) {
                if (a(constructor.getParameterTypes(), a2)) {
                    return a(constructor, objArr);
                }
            }
            throw new ab(e2);
        }
    }

    public <P> P as(Class<P> cls) {
        final boolean z = this.f1653a instanceof Map;
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.bytedance.apm.util.ac.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                String name = method.getName();
                try {
                    return ac.on(ac.this.f1653a).call(name, objArr).get();
                } catch (ab e2) {
                    if (z) {
                        Map map = (Map) ac.this.f1653a;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(ac.b(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith("is")) {
                            return map.get(ac.b(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith(dh.z)) {
                            map.put(ac.b(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e2;
                }
            }
        });
    }

    private boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length == clsArr2.length) {
            for (int i2 = 0; i2 < clsArr2.length; i2++) {
                if (clsArr2[i2] != a.class && !a(clsArr[i2]).isAssignableFrom(a(clsArr2[i2]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean b(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length == clsArr2.length) {
            for (int i2 = 0; i2 < clsArr2.length; i2++) {
                if (clsArr2[i2] != a.class && !TextUtils.equals(a(clsArr[i2]).getName(), a(clsArr2[i2]).getName())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f1653a.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ac) {
            return this.f1653a.equals(((ac) obj).get());
        }
        return false;
    }

    public String toString() {
        return this.f1653a.toString();
    }

    private Class<?> a() {
        Class<?> cls = this.f1654b;
        if (cls != null) {
            return cls;
        }
        if (this.f1655c) {
            return (Class) this.f1653a;
        }
        return this.f1653a.getClass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReflectionUtils.java */
    /* loaded from: classes.dex */
    public static class a {
        private a() {
        }
    }
}
