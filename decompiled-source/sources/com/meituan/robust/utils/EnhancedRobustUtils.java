package com.meituan.robust.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes9.dex */
public class EnhancedRobustUtils {
    public static boolean isThrowable = true;
    private static ClassLoader mClassLoader;

    public static void setClassLoader(ClassLoader classLoader) {
        mClassLoader = classLoader;
    }

    public static Object invokeReflectConstruct(String str, Object[] objArr, Class[] clsArr) {
        Class<?> cls;
        try {
            ClassLoader classLoader = mClassLoader;
            if (classLoader != null) {
                cls = classLoader.loadClass(str);
            } else {
                cls = Class.forName(str);
            }
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("invokeReflectConstruct error " + str + "   parameter   " + objArr);
            }
            return null;
        }
    }

    public static Object invokeReflectMethod(String str, Object obj, Object[] objArr, Class[] clsArr, Class cls) throws Throwable {
        try {
            return getDeclaredMethod(obj, str, clsArr, cls).invoke(obj, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowableRuntimeException(e2)) {
                throw ((InvocationTargetException) e2).getTargetException();
            }
            if (isSystemRuntimeException(e2)) {
                throw new RuntimeException("invokeReflectMethod error " + str + "   parameter   " + objArr + " targetObject " + obj.toString() + "  args  " + clsArr + " method invoke RuntimeException " + ((InvocationTargetException) e2).getTargetException());
            }
            if (isThrowable) {
                throw new RuntimeException("invokeReflectMethod error " + str + "   parameter   " + objArr + " targetObject " + obj.toString() + "  args  " + clsArr + " , reflection invoke Exception");
            }
            return null;
        }
    }

    public static Object invokeReflectMethod(String str, Object obj, Object[] objArr, Class[] clsArr, Class cls, String[] strArr) throws Throwable {
        try {
            return getDeclaredMethod(obj, str, clsArr, cls).invoke(obj, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (needToThrowException(e2, strArr).isNeedToThrow()) {
                throw ((InvocationTargetException) e2).getTargetException();
            }
            if (isThrowableRuntimeException(e2)) {
                throw ((InvocationTargetException) e2).getTargetException();
            }
            if (isSystemRuntimeException(e2)) {
                throw new RuntimeException("invokeReflectMethod error " + str + "   parameter   " + objArr + " targetObject " + obj.toString() + "  args  " + clsArr + " method invoke RuntimeException " + ((InvocationTargetException) e2).getTargetException());
            }
            if (isThrowable) {
                throw new RuntimeException("invokeReflectMethod error " + str + "   parameter   " + objArr + " targetObject " + obj.toString() + "  args  " + clsArr + " , reflection invoke Exception");
            }
            return null;
        }
    }

    public static Method getDeclaredMethod(Object obj, String str, Class[] clsArr, Class cls) {
        Method declaredMethod;
        if (cls == null || !cls.isInterface()) {
            if (cls != null && cls.isAssignableFrom(obj.getClass()) && (declaredMethod = cls.getDeclaredMethod(str, clsArr)) != null) {
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            }
            for (Class<?> cls2 = obj.getClass(); cls2 != null; cls2 = cls2.getSuperclass()) {
                Method declaredMethod2 = cls2.getDeclaredMethod(str, clsArr);
                if (declaredMethod2 != null) {
                    if (!declaredMethod2.isAccessible()) {
                        declaredMethod2.setAccessible(true);
                    }
                    return declaredMethod2;
                }
            }
            if (isThrowable) {
                throw new RuntimeException("getDeclaredMethod error " + str + "   parameterTypes   " + clsArr + " targetObject " + obj.toString());
            }
            return null;
        }
        return cls.getDeclaredMethod(str, clsArr);
    }

    public static Object invokeReflectStaticMethod(String str, Class cls, Object[] objArr, Class[] clsArr) throws Throwable {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowableRuntimeException(e2)) {
                throw ((InvocationTargetException) e2).getTargetException();
            }
            if (isSystemRuntimeException(e2)) {
                throw new RuntimeException("invokeReflectStaticMethod error " + str + "   class   " + cls + "  args  " + clsArr + " , method invoke RuntimeException " + ((InvocationTargetException) e2).getTargetException());
            }
            if (isThrowable) {
                throw new RuntimeException("invokeReflectStaticMethod error " + str + "   class   " + cls + "  args  " + clsArr + " , reflection invoke Exception");
            }
            return null;
        }
    }

    public static Object invokeReflectStaticMethod(String str, Class cls, Object[] objArr, Class[] clsArr, String[] strArr) throws Throwable {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (needToThrowException(e2, strArr).isNeedToThrow()) {
                throw ((InvocationTargetException) e2).getTargetException();
            }
            if (isThrowableRuntimeException(e2)) {
                throw ((InvocationTargetException) e2).getTargetException();
            }
            if (isSystemRuntimeException(e2)) {
                throw new RuntimeException("invokeReflectStaticMethod error " + str + "   class   " + cls + "  args  " + clsArr + " , method invoke RuntimeException " + ((InvocationTargetException) e2).getTargetException());
            }
            if (isThrowable) {
                throw new RuntimeException("invokeReflectStaticMethod error " + str + "   class   " + cls + "  args  " + clsArr + " , reflection invoke Exception");
            }
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static class CombineNeedThrowException {
        private boolean needToThrow;
        private Class needToThrowException;

        public boolean isNeedToThrow() {
            return this.needToThrow;
        }

        public void setNeedToThrow(boolean z) {
            this.needToThrow = z;
        }

        public Class getNeedToThrowException() {
            return this.needToThrowException;
        }

        public void setNeedToThrowException(Class cls) {
            this.needToThrowException = cls;
        }

        public CombineNeedThrowException(boolean z, Class cls) {
            this.needToThrow = false;
            this.needToThrowException = null;
            this.needToThrow = z;
            this.needToThrowException = cls;
        }
    }

    public static CombineNeedThrowException needToThrowException(Exception exc, String[] strArr) {
        CombineNeedThrowException combineNeedThrowException = new CombineNeedThrowException(false, null);
        if (strArr != null && strArr.length != 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                try {
                    arrayList.add(Class.forName(str));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (arrayList.isEmpty()) {
                return combineNeedThrowException;
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Class cls = (Class) it.next();
                if (exc instanceof InvocationTargetException) {
                    if (((InvocationTargetException) exc).getTargetException().getClass().equals(cls)) {
                        combineNeedThrowException.needToThrow = true;
                        combineNeedThrowException.needToThrowException = cls;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return combineNeedThrowException;
    }

    public static boolean isThrowableRuntimeException(Exception exc) {
        if (exc instanceof InvocationTargetException) {
            InvocationTargetException invocationTargetException = (InvocationTargetException) exc;
            return (invocationTargetException.getTargetException() instanceof RuntimeException) && !invocationTargetException.getTargetException().getClass().getName().startsWith("java.lang");
        }
        return false;
    }

    public static boolean isSystemRuntimeException(Exception exc) {
        if (exc instanceof InvocationTargetException) {
            InvocationTargetException invocationTargetException = (InvocationTargetException) exc;
            if ((invocationTargetException.getTargetException() instanceof RuntimeException) && invocationTargetException.getTargetException().getClass().getName().startsWith("java.lang")) {
                return true;
            }
        }
        return false;
    }

    public static void setFieldValue(String str, Object obj, int i2, Class cls) {
        try {
            getReflectField(str, obj, cls).setInt(obj, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue int error " + str + "   target   " + obj + "  value  " + i2);
            }
        }
    }

    public static void setFieldValue(String str, Object obj, boolean z, Class cls) {
        try {
            getReflectField(str, obj, cls).setBoolean(obj, z);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue boolean error " + str + "   target   " + obj + "  value  " + z);
            }
        }
    }

    public static void setFieldValue(String str, Object obj, byte b2, Class cls) {
        try {
            getReflectField(str, obj, cls).setByte(obj, b2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue byte error " + str + "   target   " + obj + "  value  " + ((int) b2));
            }
        }
    }

    public static void setFieldValue(String str, Object obj, char c2, Class cls) {
        try {
            getReflectField(str, obj, cls).setChar(obj, c2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue char error " + str + "   target   " + obj + "  value  " + c2);
            }
        }
    }

    public static void setFieldValue(String str, Object obj, double d2, Class cls) {
        try {
            getReflectField(str, obj, cls).setDouble(obj, d2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue double error " + str + "   target   " + obj + "  value  " + d2);
            }
        }
    }

    public static void setFieldValue(String str, Object obj, float f2, Class cls) {
        try {
            getReflectField(str, obj, cls).setFloat(obj, f2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue float error " + str + "   target   " + obj + "  value  " + f2);
            }
        }
    }

    public static void setFieldValue(String str, Object obj, long j2, Class cls) {
        try {
            getReflectField(str, obj, cls).setLong(obj, j2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue long error " + str + "   target   " + obj + "  value  " + j2);
            }
        }
    }

    public static void setFieldValue(String str, Object obj, Object obj2, Class cls) {
        try {
            getReflectField(str, obj, cls).set(obj, obj2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue Object error " + str + "   target   " + obj + "  value  " + obj2);
            }
        }
    }

    public static void setFieldValue(String str, Object obj, short s, Class cls) {
        try {
            getReflectField(str, obj, cls).setShort(obj, s);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setFieldValue short error " + str + "   target   " + obj + "  value  " + ((int) s));
            }
        }
    }

    public static void setStaticFieldValue(String str, Class cls, Object obj) {
        try {
            getReflectStaticField(str, cls).set(null, obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setStaticFieldValue Object error " + str + "   Class   " + cls + "  value  " + obj);
            }
        }
    }

    public static void setStaticFieldValue(String str, Class cls, int i2) {
        try {
            getReflectStaticField(str, cls).setInt(null, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setStaticFieldValue int error " + str + "   Class   " + cls + "  value  " + i2);
            }
        }
    }

    public static void setStaticFieldValue(String str, Class cls, boolean z) {
        try {
            getReflectStaticField(str, cls).setBoolean(null, z);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setStaticFieldValue boolean error " + str + "   Class   " + cls + "  value  " + z);
            }
        }
    }

    public static void setStaticFieldValue(String str, Class cls, double d2) {
        try {
            getReflectStaticField(str, cls).setDouble(null, d2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setStaticFieldValue Object error " + str + "   Class   " + cls + "  value  " + d2);
            }
        }
    }

    public static void setStaticFieldValue(String str, Class cls, float f2) {
        try {
            getReflectStaticField(str, cls).setFloat(null, f2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setStaticFieldValue float error " + str + "   Class   " + cls + "  value  " + f2);
            }
        }
    }

    public static void setStaticFieldValue(String str, Class cls, long j2) {
        try {
            getReflectStaticField(str, cls).setLong(null, j2);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("setStaticFieldValue long error " + str + "   Class   " + cls + "  value  " + j2);
            }
        }
    }

    public static Object getFieldValue(String str, Object obj, Class cls) {
        try {
            return getReflectField(str, obj, cls).get(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("getFieldValue  error " + str + "   instance   " + obj);
            }
            return null;
        }
    }

    private static Field getReflectField(String str, Object obj, Class cls) throws NoSuchFieldException {
        if (cls == null) {
            if (isThrowable) {
                throw new RuntimeException("Field " + str + " declaring class is null ");
            }
            return null;
        } else if (!cls.isInterface()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                throw new NoSuchFieldException("Field " + str + " not found in " + obj.getClass());
            }
        } else {
            return cls.getDeclaredField(str);
        }
    }

    public static Object getStaticFieldValue(String str, Class cls) {
        try {
            return getReflectStaticField(str, cls).get(null);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (isThrowable) {
                throw new RuntimeException("getStaticFieldValue  error " + str + "   clazz   " + cls);
            }
            return null;
        }
    }

    private static Field getReflectStaticField(String str, Class cls) throws NoSuchFieldException {
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            throw new NoSuchFieldException("Field " + str + " not found in " + cls);
        }
    }
}
