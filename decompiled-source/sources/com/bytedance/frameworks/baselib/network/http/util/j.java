package com.bytedance.frameworks.baselib.network.http.util;
/* compiled from: LangUtils.java */
/* loaded from: classes2.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8348a = 17;

    /* renamed from: b  reason: collision with root package name */
    public static final int f8349b = 37;

    public static int a(int i2, int i3) {
        return (i2 * 37) + i3;
    }

    private j() {
    }

    public static int a(int i2, boolean z) {
        return a(i2, z ? 1 : 0);
    }

    public static int a(int i2, Object obj) {
        return a(i2, obj != null ? obj.hashCode() : 0);
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static boolean a(Object[] objArr, Object[] objArr2) {
        if (objArr == null) {
            return objArr2 == null;
        } else if (objArr2 == null || objArr.length != objArr2.length) {
            return false;
        } else {
            for (int i2 = 0; i2 < objArr.length; i2++) {
                if (!a(objArr[i2], objArr2[i2])) {
                    return false;
                }
            }
            return true;
        }
    }
}
