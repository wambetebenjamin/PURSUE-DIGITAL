package com.bytedance.common.jato.jit;

import android.os.Build;
import android.util.Log;
import com.bytedance.common.jato.d;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/* loaded from: classes.dex */
public class JitCompiler {
    private static final String TAG = "JitCompiler";
    private static volatile boolean canForceSaveProfile = false;
    private static Field sArtMethodField;

    private static native boolean nativeCompile(int i2, long j2, String str);

    private static native void nativeForceSaveProfile();

    private static native boolean nativeHookProfileSaverBug();

    static {
        if (skip()) {
            return;
        }
        if (d.loadLibrary()) {
            try {
                Field declaredField = Method.class.getSuperclass().getDeclaredField("artMethod");
                sArtMethodField = declaredField;
                declaredField.setAccessible(true);
                return;
            } catch (Throwable th) {
                Log.e(TAG, "get artMethod field failed", th);
                return;
            }
        }
        Log.w(TAG, "load jato so failed");
    }

    public static boolean compile(Member member) {
        if (skip()) {
            return true;
        }
        Field field = sArtMethodField;
        if (field == null) {
            Log.w(TAG, "compile failed: sArtMethodField == null");
            return false;
        }
        try {
            long longValue = ((Long) field.get(member)).longValue();
            if (member instanceof Method) {
                Method method = (Method) member;
                if (Modifier.isAbstract(method.getModifiers()) || Modifier.isNative(method.getModifiers())) {
                    return true;
                }
                if (Modifier.isStatic(method.getModifiers())) {
                    resolveStaticMethod(method);
                }
            }
            return nativeCompile(Build.VERSION.SDK_INT, longValue, member.toString());
        } catch (Throwable th) {
            Log.w(TAG, "compile failed: ", th);
            return false;
        }
    }

    private static boolean resolveStaticMethod(Method method) {
        Class<?>[] parameterTypes;
        try {
            method.setAccessible(true);
            parameterTypes = method.getParameterTypes();
        } catch (IllegalArgumentException | InvocationTargetException unused) {
        } catch (Throwable th) {
            Log.w(TAG, "compile: ", th);
            return false;
        }
        if (parameterTypes != null && parameterTypes.length != 0) {
            method.invoke("", new Object[0]);
            return true;
        }
        method.invoke("", "");
        return true;
    }

    private static boolean skip() {
        return Build.VERSION.SDK_INT < 24 || Build.VERSION.SDK_INT > 33;
    }

    public static void hookProfileSaverBug() {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        if (Build.VERSION.SDK_INT < 31) {
            canForceSaveProfile = nativeHookProfileSaverBug();
        } else {
            canForceSaveProfile = true;
        }
    }

    public static boolean canForceSaveProfile() {
        return canForceSaveProfile;
    }

    public static void forceSaveProfile() {
        if (canForceSaveProfile) {
            nativeForceSaveProfile();
        }
    }
}
