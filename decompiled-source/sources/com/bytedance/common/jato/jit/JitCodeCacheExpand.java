package com.bytedance.common.jato.jit;

import android.util.Log;
import com.bytedance.common.jato.d;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class JitCodeCacheExpand {
    private int testField;

    public static native void enableSmallPattern();

    public static native void keepAlive();

    public static native void testSmallPattern();

    public JitCodeCacheExpand testReturnValue() {
        return this;
    }

    public float testReturnValue2() {
        return 0.0f;
    }

    public int testReturnValue3() {
        return 1;
    }

    public boolean testReturnValue4() {
        return false;
    }

    static {
        d.loadLibrary();
    }

    public void stubMethod() {
        System.err.println("stubMethod");
    }

    public static boolean checkSuperClassIsObject(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            if (obj instanceof Method) {
                Method method = (Method) obj;
                return method.getDeclaringClass() != null && method.getDeclaringClass().getSuperclass() == Object.class;
            } else if (obj instanceof Constructor) {
                Constructor constructor = (Constructor) obj;
                return constructor.getDeclaringClass() != null && constructor.getDeclaringClass().getSuperclass() == Object.class;
            } else {
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean checkClassIsObject(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return obj instanceof Method ? ((Method) obj).getDeclaringClass() == Object.class : (obj instanceof Constructor) && ((Constructor) obj).getDeclaringClass() == Object.class;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean checkReturnTypeIsFloat(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            if (obj instanceof Method) {
                Class<?> returnType = ((Method) obj).getReturnType();
                if (returnType != Float.class) {
                    if (returnType != Float.TYPE) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static long getMethodDexFile(Object obj) {
        if (obj == null) {
            return -1L;
        }
        Class<?> cls = null;
        try {
            if (obj instanceof Method) {
                cls = ((Method) obj).getDeclaringClass();
            } else if (obj instanceof Constructor) {
                cls = ((Constructor) obj).getDeclaringClass();
            }
            if (cls == null) {
                return -1L;
            }
            Field declaredField = Class.class.getDeclaredField("dexCache");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(cls);
            Field declaredField2 = obj2.getClass().getDeclaredField("dexFile");
            declaredField2.setAccessible(true);
            return declaredField2.getLong(obj2);
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public JitCodeCacheExpand() {
    }

    public JitCodeCacheExpand(int i2) {
        this.testField = i2;
    }

    public int testGetter() {
        return this.testField;
    }

    public void testSetter(int i2) {
        this.testField = i2;
    }

    public void print() {
        Log.e("jato", "JitCodeCacheExpand testReturnValue:" + testReturnValue() + ",testReturnValue2:" + testReturnValue2() + ",testReturnValue3:" + testReturnValue3() + ",testReturnValue4:" + testReturnValue4() + ",testGetter:" + testGetter());
    }
}
