package com.bytedance.common.utility.reflect;

import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: ReflectUtils.java */
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5353a = "ReflectUtils";

    private c() {
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>[] clsArr) {
        if (cls == null || o.isEmpty(str)) {
            return null;
        }
        try {
            Logger.d(f5353a, "thread id : " + Thread.currentThread().getName());
            return cls.getMethod(str, clsArr);
        } catch (Throwable th) {
            Logger.e(f5353a, "exception in getMethod, pkg : " + cls.getName() + ", function : " + str + ", " + th.toString());
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static Object invokeMethod(Class<?> cls, String str, Object... objArr) {
        return invokeMethod(cls, str, null, null, objArr);
    }

    public static Object invokeMethod(Class<?> cls, String str, Class<?>[] clsArr, Object[] objArr, Object... objArr2) {
        if (cls != null && !o.isEmpty(str)) {
            if (clsArr == null) {
                clsArr = new Class[0];
            }
            if (objArr == null) {
                objArr = new Object[0];
            }
            try {
                Method method = getMethod(cls, str, clsArr);
                if (method == null) {
                    return null;
                }
                method.setAccessible(true);
                if (objArr2 != null && objArr2.length > 0) {
                    return method.invoke(objArr2[0], objArr);
                }
                return method.invoke(null, objArr);
            } catch (Throwable th) {
                Logger.e(f5353a, "exception in invokeMethod, pkg : " + cls.getName() + ", function : " + str + ", " + th.toString());
            }
        }
        return null;
    }

    public static Object getFiledValue(Object obj, Class<?> cls) {
        if (obj != null && cls != null) {
            try {
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (int i2 = 0; i2 < declaredFields.length; i2++) {
                    declaredFields[i2].setAccessible(true);
                    if (declaredFields[i2].getType().getName().equals(cls.getName())) {
                        return declaredFields[i2].get(obj);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }
}
