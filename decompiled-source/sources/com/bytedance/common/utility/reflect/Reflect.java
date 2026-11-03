package com.bytedance.common.utility.reflect;

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
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes2.dex */
public final class Reflect {
    private final boolean isClass = true;
    private final Object object;

    /* loaded from: classes2.dex */
    public static class a {
    }

    public static Reflect on(String str) {
        return on(forName(str));
    }

    public static Reflect on(Class<?> cls) {
        return new Reflect(cls);
    }

    public static Reflect on(Object obj) {
        return new Reflect(obj);
    }

    public static <T extends AccessibleObject> T accessible(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Member) {
            Member member = (Member) t;
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                return t;
            }
        }
        if (!t.isAccessible()) {
            t.setAccessible(true);
        }
        return t;
    }

    private Reflect(Class<?> cls) {
        this.object = cls;
    }

    private Reflect(Object obj) {
        this.object = obj;
    }

    public <T> T get() {
        return (T) this.object;
    }

    public Reflect set(String str, Object obj) {
        try {
            field0(str, new Class[0]).set(this.object, unwrap(obj));
            return this;
        } catch (Exception e2) {
            throw new b(e2);
        }
    }

    public <T> T get(String str, Class<?>... clsArr) {
        return (T) field(str, clsArr).get();
    }

    public Reflect field(String str, Class<?>... clsArr) {
        try {
            return on(field0(str, clsArr).get(this.object));
        } catch (Exception e2) {
            throw new b(e2);
        }
    }

    public Field exactField(String str, Class<?>... clsArr) {
        return field0(str, clsArr);
    }

    private Field field0(String str, Class<?>... clsArr) {
        Field[] declaredFields;
        Class<?> type = type();
        if (clsArr != null && clsArr.length > 0) {
            do {
                try {
                    for (Field field : type.getDeclaredFields()) {
                        Class<?> type2 = field.getType();
                        if (type2 != null && type2.getName().equals(clsArr[0].getName()) && field.getName().equals(str)) {
                            return (Field) accessible(field);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                type = type.getSuperclass();
            } while (type != null);
            throw new b("NoSuchFieldException");
        }
        try {
            return type.getField(str);
        } catch (NoSuchFieldException e3) {
            do {
                try {
                    return (Field) accessible(type.getDeclaredField(str));
                } catch (NoSuchFieldException e4) {
                    e4.printStackTrace();
                    type = type.getSuperclass();
                    if (type != null) {
                        throw new b(e3);
                    }
                }
            } while (type != null);
            throw new b(e3);
        }
    }

    public Map<String, Reflect> fields() {
        Field[] declaredFields;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Class<?> type = type();
        do {
            for (Field field : type.getDeclaredFields()) {
                if ((!this.isClass) ^ Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();
                    if (!linkedHashMap.containsKey(name)) {
                        linkedHashMap.put(name, field(name, new Class[0]));
                    }
                }
            }
            type = type.getSuperclass();
        } while (type != null);
        return linkedHashMap;
    }

    public Reflect call(String str) {
        return call(str, null, new Object[0]);
    }

    public Reflect call(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            try {
                return on(exactMethod(str, clsArr, objArr), this.object, objArr);
            } catch (NoSuchMethodException unused) {
                return on(similarMethod(str, clsArr), this.object, objArr);
            }
        } catch (NoSuchMethodException e2) {
            throw new b(e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.reflect.Method exactMethod(java.lang.String r17, java.lang.Class<?>[] r18, java.lang.Object... r19) {
        /*
            r16 = this;
            r1 = r17
            r2 = r18
            java.lang.Class r0 = r16.type()
            java.lang.reflect.Method r0 = r0.getMethod(r1, r2)     // Catch: java.lang.NoSuchMethodException -> Ld
            return r0
        Ld:
            r3 = r0
        Le:
            java.lang.reflect.Method r0 = r3.getDeclaredMethod(r1, r2)     // Catch: java.lang.NoSuchMethodException -> L13
            return r0
        L13:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()
            java.lang.Class r3 = r3.getSuperclass()
            if (r3 != 0) goto Le
            java.lang.Class r0 = r16.type()
            java.lang.reflect.Method[] r3 = r0.getMethods()
            int r4 = r3.length
            r5 = 0
            r6 = r5
        L29:
            if (r6 >= r4) goto L91
            r7 = r3[r6]
            java.lang.String r8 = r7.getName()
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto L8e
            java.lang.Class[] r8 = r7.getParameterTypes()
            boolean r9 = com.bytedance.common.utility.Logger.debug()
            if (r9 == 0) goto L87
            int r9 = r8.length
            r10 = 1
            r11 = r5
            r12 = r10
        L45:
            if (r11 >= r9) goto L87
            r13 = r8[r11]
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Reflect type = "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r15 = " method = "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r1)
            java.lang.String r15 = " paramTypes "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r12)
            java.lang.String r15 = " = "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r13 = r13.getName()
            java.lang.StringBuilder r13 = r14.append(r13)
            java.lang.String r13 = r13.toString()
            java.lang.String r14 = "Plugin"
            com.bytedance.common.utility.Logger.d(r14, r13)
            int r12 = r12 + r10
            int r11 = r11 + 1
            goto L45
        L87:
            if (r8 == 0) goto L8e
            int r8 = r8.length
            int r9 = r2.length
            if (r8 != r9) goto L8e
            return r7
        L8e:
            int r6 = r6 + 1
            goto L29
        L91:
            java.lang.NoSuchMethodException r0 = new java.lang.NoSuchMethodException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.reflect.Reflect.exactMethod(java.lang.String, java.lang.Class[], java.lang.Object[]):java.lang.reflect.Method");
    }

    private Method similarMethod(String str, Class<?>[] clsArr) {
        Method[] methods;
        Method[] declaredMethods;
        Class<?> type = type();
        for (Method method : type.getMethods()) {
            if (isSimilarSignature(method, str, clsArr)) {
                return method;
            }
        }
        do {
            for (Method method2 : type.getDeclaredMethods()) {
                if (isSimilarSignature(method2, str, clsArr)) {
                    return method2;
                }
            }
            type = type.getSuperclass();
        } while (type != null);
        throw new NoSuchMethodException("No similar method " + str + " with params " + Arrays.toString(clsArr) + " could be found on type " + type() + c.a.DOT);
    }

    private boolean isSimilarSignature(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && match(method.getParameterTypes(), clsArr);
    }

    public Reflect create() {
        return create(new Object[0]);
    }

    public Reflect create(Object... objArr) {
        Constructor<?>[] declaredConstructors;
        Class<?>[] types = types(objArr);
        try {
            return on(type().getDeclaredConstructor(types), objArr);
        } catch (NoSuchMethodException e2) {
            for (Constructor<?> constructor : type().getDeclaredConstructors()) {
                if (match(constructor.getParameterTypes(), types)) {
                    return on(constructor, objArr);
                }
            }
            throw new b(e2);
        }
    }

    public <P> P as(Class<P> cls) {
        final boolean z = this.object instanceof Map;
        return (P) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.bytedance.common.utility.reflect.Reflect.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                String name = method.getName();
                try {
                    return Reflect.on(Reflect.this.object).call(name, Reflect.types(objArr), objArr).get();
                } catch (b e2) {
                    if (z) {
                        Map map = (Map) Reflect.this.object;
                        int length = objArr == null ? 0 : objArr.length;
                        if (length == 0 && name.startsWith("get")) {
                            return map.get(Reflect.property(name.substring(3)));
                        }
                        if (length == 0 && name.startsWith("is")) {
                            return map.get(Reflect.property(name.substring(2)));
                        }
                        if (length == 1 && name.startsWith(dh.z)) {
                            map.put(Reflect.property(name.substring(3)), objArr[0]);
                            return null;
                        }
                    }
                    throw e2;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String property(String str) {
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            return str.toLowerCase(Locale.getDefault());
        }
        return str.substring(0, 1).toLowerCase(Locale.getDefault()) + str.substring(1);
    }

    private boolean match(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length == clsArr2.length) {
            for (int i2 = 0; i2 < clsArr2.length; i2++) {
                if (clsArr2[i2] != a.class && !wrapper(clsArr[i2]).isAssignableFrom(wrapper(clsArr2[i2]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.object.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Reflect) {
            return this.object.equals(((Reflect) obj).get());
        }
        return false;
    }

    public String toString() {
        return this.object.toString();
    }

    private static Reflect on(Constructor<?> constructor, Object... objArr) {
        try {
            return on(((Constructor) accessible(constructor)).newInstance(objArr));
        } catch (Exception e2) {
            throw new b(e2);
        }
    }

    private static Reflect on(Method method, Object obj, Object... objArr) {
        try {
            accessible(method);
            if (method.getReturnType() == Void.TYPE) {
                method.invoke(obj, objArr);
                return on(obj);
            }
            return on(method.invoke(obj, objArr));
        } catch (Exception e2) {
            throw new b(e2);
        }
    }

    private static Object unwrap(Object obj) {
        return obj instanceof Reflect ? ((Reflect) obj).get() : obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Class<?>[] types(Object... objArr) {
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

    private static Class<?> forName(String str) {
        try {
            return Class.forName(str);
        } catch (Exception e2) {
            throw new b(e2);
        }
    }

    public Class<?> type() {
        if (this.isClass) {
            return (Class) this.object;
        }
        return this.object.getClass();
    }

    public static Class<?> wrapper(Class<?> cls) {
        if (cls == null) {
            return null;
        }
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
}
