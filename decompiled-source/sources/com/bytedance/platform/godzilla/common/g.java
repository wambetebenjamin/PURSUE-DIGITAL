package com.bytedance.platform.godzilla.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: DoubleReflectHelper.java */
/* loaded from: classes2.dex */
public final class g {
    private g() {
    }

    public static Field getField(Class<?> cls, String str) {
        if (com.bytedance.platform.godzilla.utils.f.isAndroidPHigher()) {
            try {
                return h.getField(cls, str);
            } catch (Throwable unused) {
                return null;
            }
        }
        return com.bytedance.platform.godzilla.utils.b.getField(cls, str);
    }

    public static Method getMethod(Class<?> cls, String str, Class... clsArr) {
        if (com.bytedance.platform.godzilla.utils.f.isAndroidPHigher()) {
            try {
                return h.getMethod(cls, str, clsArr);
            } catch (Throwable unused) {
                return null;
            }
        }
        return com.bytedance.platform.godzilla.utils.e.getAccessibleMethod(cls, str, clsArr);
    }
}
