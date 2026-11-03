package com.bytedance.monitor.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
/* compiled from: MemberUtils.java */
/* loaded from: classes2.dex */
class d {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?>[] f9672a = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

    /* renamed from: b  reason: collision with root package name */
    private static final int f9673b = 7;

    /* renamed from: c  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f9674c;

    /* renamed from: d  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f9675d;

    private static boolean a(int i2) {
        return (i2 & 7) == 0;
    }

    d() {
    }

    static {
        HashMap hashMap = new HashMap();
        f9674c = hashMap;
        f9675d = new HashMap();
        hashMap.put(Byte.TYPE, Byte.class);
        hashMap.put(Boolean.TYPE, Boolean.class);
        hashMap.put(Short.TYPE, Short.class);
        hashMap.put(Character.TYPE, Character.class);
        hashMap.put(Long.TYPE, Long.class);
        hashMap.put(Integer.TYPE, Integer.class);
        hashMap.put(Float.TYPE, Float.class);
        hashMap.put(Double.TYPE, Double.class);
        hashMap.put(Void.TYPE, Void.TYPE);
        for (Class<?> cls : hashMap.keySet()) {
            Class<?> cls2 = f9674c.get(cls);
            if (!cls.equals(cls2)) {
                f9675d.put(cls2, cls);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Member member) {
        return (member == null || !Modifier.isPublic(member.getModifiers()) || member.isSynthetic()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(AccessibleObject accessibleObject) {
        if (accessibleObject != null && !accessibleObject.isAccessible()) {
            Member member = (Member) accessibleObject;
            if (!accessibleObject.isAccessible() && Modifier.isPublic(member.getModifiers()) && a(member.getDeclaringClass().getModifiers())) {
                try {
                    accessibleObject.setAccessible(true);
                    return true;
                } catch (Exception unused) {
                }
            }
        }
        return false;
    }

    static boolean a(Class<?> cls, Class<?> cls2) {
        return a(cls, cls2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z) {
        if (f.a(clsArr, clsArr2)) {
            if (clsArr == null) {
                clsArr = f.f9677a;
            }
            if (clsArr2 == null) {
                clsArr2 = f.f9677a;
            }
            for (int i2 = 0; i2 < clsArr.length; i2++) {
                if (!a(clsArr[i2], clsArr2[i2], z)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static boolean a(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = a(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = b(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            return b(cls, cls2);
        }
        return cls2.isAssignableFrom(cls);
    }

    private static boolean b(Class<?> cls, Class<?> cls2) {
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
                boolean z = Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                if (Character.TYPE.equals(cls) || Short.TYPE.equals(cls)) {
                    return z;
                }
                if (Byte.TYPE.equals(cls)) {
                    return Short.TYPE.equals(cls2) || z;
                }
                return false;
            }
        }
        return false;
    }

    static Class<?> a(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : f9674c.get(cls);
    }

    private static float c(Class<?> cls, Class<?> cls2) {
        float f2;
        if (cls.isPrimitive()) {
            f2 = 0.0f;
        } else {
            cls = b(cls);
            f2 = 0.1f;
        }
        int i2 = 0;
        while (cls != cls2) {
            Class<?>[] clsArr = f9672a;
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

    static Class<?> b(Class<?> cls) {
        return f9675d.get(cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Class<?>[] clsArr, Class<?>[] clsArr2, Class<?>[] clsArr3) {
        float a2 = a(clsArr3, clsArr);
        float a3 = a(clsArr3, clsArr2);
        if (a2 < a3) {
            return -1;
        }
        return a3 < a2 ? 1 : 0;
    }

    private static float d(Class<?> cls, Class<?> cls2) {
        if (cls2.isPrimitive()) {
            return c(cls, cls2);
        }
        float f2 = 0.0f;
        while (true) {
            if (cls != null && !cls2.equals(cls)) {
                if (cls2.isInterface() && a(cls, cls2)) {
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

    private static float a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        float f2 = 0.0f;
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            f2 += d(clsArr[i2], clsArr2[i2]);
        }
        return f2;
    }
}
