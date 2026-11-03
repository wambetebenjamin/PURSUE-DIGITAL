package com.bytedance.monitor.util;
/* compiled from: InnerUtils.java */
/* loaded from: classes2.dex */
class f {

    /* renamed from: a  reason: collision with root package name */
    static final Class<?>[] f9677a = new Class[0];

    /* renamed from: b  reason: collision with root package name */
    static final Object[] f9678b = new Object[0];

    f() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Object[] objArr, Object[] objArr2) {
        if (objArr != null || objArr2 == null || objArr2.length <= 0) {
            if (objArr2 != null || objArr == null || objArr.length <= 0) {
                return objArr == null || objArr2 == null || objArr.length == objArr2.length;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] a(Object[] objArr) {
        return (objArr == null || objArr.length <= 0) ? f9678b : objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?>[] b(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return f9677a;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            clsArr[i2] = objArr[i2] == null ? null : objArr[i2].getClass();
        }
        return clsArr;
    }
}
