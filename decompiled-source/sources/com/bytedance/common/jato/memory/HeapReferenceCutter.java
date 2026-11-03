package com.bytedance.common.jato.memory;

import android.os.Build;
import java.lang.ref.Reference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
/* loaded from: classes.dex */
public class HeapReferenceCutter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4802a = "HeapReferenceCutter";

    private static native void nCutReference(Object[] objArr);

    private static native void nReplaceReference(Object[] objArr, Object[] objArr2);

    public static void a(Object[] objArr) {
        if (Build.VERSION.SDK_INT >= 23 && com.bytedance.common.jato.d.loadLibrary()) {
            try {
                nCutReference(objArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(Object[] objArr, Object[] objArr2) {
        if (Build.VERSION.SDK_INT < 23 || objArr == null || objArr.length == 0 || objArr2 == null || objArr2.length == 0 || objArr2.length != objArr.length || !com.bytedance.common.jato.d.loadLibrary()) {
            return;
        }
        try {
            nReplaceReference(objArr, objArr2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int ReplaceObjectReference(Object obj, Object obj2, Object obj3) {
        Class<?> cls;
        int i2;
        boolean z;
        char c2;
        char c3;
        if (obj == null || obj2 == null) {
            return 0;
        }
        Class<?> cls2 = obj.getClass();
        char c4 = 3;
        char c5 = 2;
        int i3 = 5;
        if (cls2.isArray()) {
            Class<?> componentType = cls2.getComponentType();
            if (componentType == null || !componentType.isPrimitive()) {
                Object[] objArr = (Object[]) obj;
                try {
                    int length = Array.getLength(objArr);
                    int i4 = 0;
                    for (int i5 = 0; i5 < length; i5++) {
                        try {
                            if (Array.get(objArr, i5) == obj2) {
                                Array.set(objArr, i5, obj3);
                                i4++;
                                System.out.println(String.format("%s, Start replacing array class: %s, index: %d, original object: %s, replaced object: %s", f4802a, obj.getClass().getName(), Integer.valueOf(i5), obj2, obj3));
                            }
                        } catch (Throwable unused) {
                            return i4;
                        }
                    }
                    return i4;
                } catch (Throwable unused2) {
                    return 0;
                }
            }
            return 0;
        } else if (obj instanceof Reference) {
            return 0;
        } else {
            if (obj instanceof Class) {
                cls = (Class) obj;
                i2 = 0;
                z = true;
            } else {
                cls = obj.getClass();
                i2 = 0;
                z = false;
            }
            while (cls != null && cls != Object.class) {
                Field[] declaredFields = cls.getDeclaredFields();
                int length2 = declaredFields.length;
                int i6 = i2;
                int i7 = 0;
                while (i7 < length2) {
                    Field field = declaredFields[i7];
                    if (!field.getType().isPrimitive() && z == Modifier.isStatic(field.getModifiers())) {
                        try {
                            field.setAccessible(true);
                        } catch (Throwable th) {
                            th = th;
                            c2 = c5;
                        }
                        if (field.get(z ? null : obj) == obj2) {
                            field.set(z ? null : obj, obj3);
                            i6++;
                            try {
                                Object[] objArr2 = new Object[i3];
                                objArr2[0] = f4802a;
                                objArr2[1] = obj;
                                c2 = 2;
                                try {
                                    objArr2[2] = field.getName();
                                    c3 = 3;
                                    try {
                                        objArr2[3] = obj2;
                                        try {
                                            objArr2[4] = obj3;
                                            System.out.println(String.format("%s, Start replacing: %s field: %s, original object: %s, replaced object: %s", objArr2));
                                        } catch (Throwable th2) {
                                            th = th2;
                                            System.out.println("HeapReferenceCutter replace object failed, msg: " + th.getMessage());
                                            i7++;
                                            c5 = c2;
                                            c4 = c3;
                                            i3 = 5;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    c3 = 3;
                                    System.out.println("HeapReferenceCutter replace object failed, msg: " + th.getMessage());
                                    i7++;
                                    c5 = c2;
                                    c4 = c3;
                                    i3 = 5;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                c2 = 2;
                                c3 = 3;
                                System.out.println("HeapReferenceCutter replace object failed, msg: " + th.getMessage());
                                i7++;
                                c5 = c2;
                                c4 = c3;
                                i3 = 5;
                            }
                            i7++;
                            c5 = c2;
                            c4 = c3;
                            i3 = 5;
                        }
                    }
                    c2 = c5;
                    c3 = 3;
                    i7++;
                    c5 = c2;
                    c4 = c3;
                    i3 = 5;
                }
                cls = cls.getSuperclass();
                i2 = i6;
                i3 = 5;
            }
            return i2;
        }
    }
}
