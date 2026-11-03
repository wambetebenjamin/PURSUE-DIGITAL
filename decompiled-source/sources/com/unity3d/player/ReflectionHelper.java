package com.unity3d.player;

import androidx.exifinterface.media.ExifInterface;
import com.meituan.robust.Constants;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes10.dex */
final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;

    /* renamed from: a  reason: collision with root package name */
    private static M[] f18877a = new M[4096];

    /* renamed from: b  reason: collision with root package name */
    private static long f18878b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static long f18879c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static boolean f18880d = false;

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0075, code lost:
        if (r12.asSubclass(r11) != null) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static float a(java.lang.Class r11, java.lang.Class[] r12, java.lang.Class[] r13) {
        /*
            int r0 = r13.length
            r1 = 1036831949(0x3dcccccd, float:0.1)
            if (r0 != 0) goto L7
            return r1
        L7:
            r0 = 0
            if (r12 != 0) goto Lc
            r2 = r0
            goto Ld
        Lc:
            int r2 = r12.length
        Ld:
            int r2 = r2 + 1
            int r3 = r13.length
            r4 = 0
            if (r2 == r3) goto L14
            return r4
        L14:
            r2 = 1056964608(0x3f000000, float:0.5)
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r12 == 0) goto L4f
            int r5 = r12.length
            r6 = r0
            r7 = r3
        L1d:
            if (r0 >= r5) goto L50
            r8 = r12[r0]
            int r9 = r6 + 1
            r6 = r13[r6]
            boolean r10 = r8.equals(r6)
            if (r10 == 0) goto L2d
            r6 = r3
            goto L4a
        L2d:
            boolean r10 = r8.isPrimitive()
            if (r10 != 0) goto L49
            boolean r10 = r6.isPrimitive()
            if (r10 != 0) goto L49
            java.lang.Class r10 = r8.asSubclass(r6)     // Catch: java.lang.ClassCastException -> L41
            if (r10 == 0) goto L41
            r6 = r2
            goto L4a
        L41:
            java.lang.Class r6 = r6.asSubclass(r8)     // Catch: java.lang.ClassCastException -> L49
            if (r6 == 0) goto L49
            r6 = r1
            goto L4a
        L49:
            r6 = r4
        L4a:
            float r7 = r7 * r6
            int r0 = r0 + 1
            r6 = r9
            goto L1d
        L4f:
            r7 = r3
        L50:
            int r12 = r13.length
            int r12 = r12 + (-1)
            r12 = r13[r12]
            boolean r13 = r11.equals(r12)
            if (r13 == 0) goto L5d
            r1 = r3
            goto L79
        L5d:
            boolean r13 = r11.isPrimitive()
            if (r13 != 0) goto L78
            boolean r13 = r12.isPrimitive()
            if (r13 != 0) goto L78
            java.lang.Class r13 = r11.asSubclass(r12)     // Catch: java.lang.ClassCastException -> L71
            if (r13 == 0) goto L71
            r1 = r2
            goto L79
        L71:
            java.lang.Class r11 = r12.asSubclass(r11)     // Catch: java.lang.ClassCastException -> L78
            if (r11 == 0) goto L78
            goto L79
        L78:
            r1 = r4
        L79:
            float r7 = r7 * r1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.a(java.lang.Class, java.lang.Class[], java.lang.Class[]):float");
    }

    private static Class a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i2 = iArr[0];
            iArr[0] = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt != '(' && charAt != ')') {
                if (charAt == 'L') {
                    int indexOf = str.indexOf(59, iArr[0]);
                    if (indexOf == -1) {
                        return null;
                    }
                    String substring = str.substring(iArr[0], indexOf);
                    iArr[0] = indexOf + 1;
                    try {
                        return Class.forName(substring.replace('/', '.'));
                    } catch (ClassNotFoundException unused) {
                        return null;
                    }
                } else if (charAt == 'Z') {
                    return Boolean.TYPE;
                } else {
                    if (charAt == 'I') {
                        return Integer.TYPE;
                    }
                    if (charAt == 'F') {
                        return Float.TYPE;
                    }
                    if (charAt == 'V') {
                        return Void.TYPE;
                    }
                    if (charAt == 'B') {
                        return Byte.TYPE;
                    }
                    if (charAt == 'C') {
                        return Character.TYPE;
                    }
                    if (charAt == 'S') {
                        return Short.TYPE;
                    }
                    if (charAt == 'J') {
                        return Long.TYPE;
                    }
                    if (charAt == 'D') {
                        return Double.TYPE;
                    }
                    if (charAt == '[') {
                        return Array.newInstance(a(str, iArr), 0).getClass();
                    }
                    AbstractC0445z.Log(5, "! parseType; " + charAt + " is not known!");
                    return null;
                }
            }
        }
        return null;
    }

    private static synchronized boolean a(M m) {
        int i2;
        boolean z;
        synchronized (ReflectionHelper.class) {
            M[] mArr = f18877a;
            i2 = m.f18851d;
            M m2 = mArr[i2 & 4095];
            if (m.equals(m2)) {
                m.f18852e = m2.f18852e;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    private static Class[] a(String str) {
        Class a2;
        int i2 = 0;
        int[] iArr = {0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length() && (a2 = a(str, iArr)) != null) {
            arrayList.add(a2);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            clsArr[i2] = (Class) it.next();
            i2++;
        }
        return clsArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static synchronized boolean beginProxyCall(long j2) {
        boolean z;
        synchronized (ReflectionHelper.class) {
            if (j2 == f18878b) {
                f18879c++;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    protected static Object createInvocationError(long j2, boolean z) {
        return new N(j2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static synchronized void endProxyCall() {
        synchronized (ReflectionHelper.class) {
            long j2 = f18879c - 1;
            f18879c = j2;
            if (0 == j2 && f18880d) {
                ReflectionHelper.class.notifyAll();
            }
        }
    }

    protected static synchronized void endUnityLaunch() {
        synchronized (ReflectionHelper.class) {
            try {
                f18878b++;
                f18880d = true;
                while (f18879c > 0) {
                    ReflectionHelper.class.wait();
                }
            } catch (InterruptedException unused) {
                AbstractC0445z.Log(6, "Interrupted while waiting for all proxies to exit.");
            }
            f18880d = false;
        }
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor<?>[] constructors;
        int i2;
        Constructor<?> constructor;
        M m = new M(cls, "", str);
        if (a(m)) {
            constructor = (Constructor) m.f18852e;
        } else {
            Class[] a2 = a(str);
            float f2 = 0.0f;
            Constructor<?> constructor2 = null;
            for (Constructor<?> constructor3 : cls.getConstructors()) {
                float a3 = a(Void.TYPE, constructor3.getParameterTypes(), a2);
                if (a3 > f2) {
                    constructor2 = constructor3;
                    if (a3 == 1.0f) {
                        break;
                    }
                    f2 = a3;
                }
            }
            synchronized (ReflectionHelper.class) {
                m.f18852e = constructor2;
                M[] mArr = f18877a;
                i2 = m.f18851d;
                mArr[i2 & 4095] = m;
            }
            constructor = constructor2;
        }
        if (constructor != null) {
            return constructor;
        }
        throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        int i2;
        Field field;
        Class cls2 = cls;
        M m = new M(cls2, str, str2);
        if (a(m)) {
            field = (Field) m.f18852e;
        } else {
            Class[] a2 = a(str2);
            float f2 = 0.0f;
            Field field2 = null;
            while (cls2 != null) {
                Field[] declaredFields = cls2.getDeclaredFields();
                int length = declaredFields.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    Field field3 = declaredFields[i3];
                    if (z == Modifier.isStatic(field3.getModifiers()) && field3.getName().compareTo(str) == 0) {
                        float a3 = a(field3.getType(), null, a2);
                        if (a3 > f2) {
                            field2 = field3;
                            if (a3 == 1.0f) {
                                f2 = a3;
                                break;
                            }
                            f2 = a3;
                        } else {
                            continue;
                        }
                    }
                    i3++;
                }
                if (f2 == 1.0f || cls2.isPrimitive() || cls2.isInterface() || cls2.equals(Object.class) || cls2.equals(Void.TYPE)) {
                    break;
                }
                cls2 = cls2.getSuperclass();
            }
            synchronized (ReflectionHelper.class) {
                m.f18852e = field2;
                M[] mArr = f18877a;
                i2 = m.f18851d;
                mArr[i2 & 4095] = m;
            }
            field = field2;
        }
        if (field == null) {
            Object[] objArr = new Object[4];
            objArr[0] = z ? "static" : "non-static";
            objArr[1] = str;
            objArr[2] = str2;
            objArr[3] = cls2.getName();
            throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", objArr));
        }
        return field;
    }

    protected static String getFieldSignature(Field field) {
        Class<?> type = field.getType();
        if (!type.isPrimitive()) {
            return type.isArray() ? type.getName().replace('.', '/') : "L" + type.getName().replace('.', '/') + Constants.PACKNAME_END;
        }
        String name = type.getName();
        return "boolean".equals(name) ? "Z" : Constants.BYTE.equals(name) ? "B" : Constants.CHAR.equals(name) ? "C" : Constants.DOUBLE.equals(name) ? "D" : "float".equals(name) ? "F" : Constants.INT.equals(name) ? "I" : "long".equals(name) ? "J" : Constants.SHORT.equals(name) ? ExifInterface.LATITUDE_SOUTH : name;
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        int i2;
        Method method;
        M m = new M(cls, str, str2);
        if (a(m)) {
            method = (Method) m.f18852e;
        } else {
            Class[] a2 = a(str2);
            float f2 = 0.0f;
            Method method2 = null;
            while (cls != null) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    Method method3 = declaredMethods[i3];
                    if (z == Modifier.isStatic(method3.getModifiers()) && method3.getName().compareTo(str) == 0) {
                        float a3 = a(method3.getReturnType(), method3.getParameterTypes(), a2);
                        if (a3 > f2) {
                            method2 = method3;
                            if (a3 == 1.0f) {
                                f2 = a3;
                                break;
                            }
                            f2 = a3;
                        } else {
                            continue;
                        }
                    }
                    i3++;
                }
                if (f2 == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            synchronized (ReflectionHelper.class) {
                m.f18852e = method2;
                M[] mArr = f18877a;
                i2 = m.f18851d;
                mArr[i2 & 4095] = m;
            }
            method = method2;
        }
        if (method == null) {
            Object[] objArr = new Object[4];
            objArr[0] = z ? "static" : "non-static";
            objArr[1] = str;
            objArr[2] = str2;
            objArr[3] = cls.getName();
            throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", objArr));
        }
        return method;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyFinalize(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native Object nativeProxyInvoke(long j2, String str, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyJNIFreeGCHandle(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyLogJNIInvokeException(long j2);

    protected static Object newProxyInstance(UnityPlayer unityPlayer, long j2, Class cls) {
        return newProxyInstance(unityPlayer, j2, new Class[]{cls});
    }

    protected static Object newProxyInstance(UnityPlayer unityPlayer, long j2, Class[] clsArr) {
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new L(unityPlayer, j2));
    }
}
