package com.bytedance.common.utility;

import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;
import android.util.DisplayMetrics;
import java.io.File;
import java.lang.reflect.Method;
/* compiled from: SignUtils.java */
/* loaded from: classes2.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5304a = 0;

    /* renamed from: b  reason: collision with root package name */
    private static final int f5305b = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5306c = 2;

    /* renamed from: d  reason: collision with root package name */
    private static final int f5307d = 3;

    /* renamed from: e  reason: collision with root package name */
    private static final int f5308e = 4;

    private m() {
    }

    public static boolean checkSignature(Context context, String str) {
        Signature[] appSignatures;
        Signature[] apkSignatures = getApkSignatures(context, str);
        if (apkSignatures == null || (appSignatures = getAppSignatures(context)) == null) {
            return true;
        }
        for (Signature signature : appSignatures) {
            for (Signature signature2 : apkSignatures) {
                if (signature.equals(signature2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Signature[] getAppSignatures(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
        } catch (Exception e2) {
            Logger.w("SignUtils", "failed to get package signatures: " + e2);
            return null;
        }
    }

    public static Signature[] getApkSignatures(Context context, String str) {
        Signature[] signatureArr;
        try {
            signatureArr = context.getPackageManager().getPackageArchiveInfo(str, 64).signatures;
        } catch (Exception e2) {
            Logger.w("SignUtils", "failed to get package signatures: " + e2);
            signatureArr = null;
        }
        return signatureArr == null ? a(str) : signatureArr;
    }

    public static byte[] getApkSignature(String str) {
        try {
            Signature[] a2 = a(str);
            if (a2 == null) {
                return null;
            }
            return a2[0].toByteArray();
        } catch (Exception e2) {
            Logger.w("SignUtils", "failed to get package signatures: " + e2);
            return null;
        }
    }

    private static Signature[] a(String str) {
        Object newInstance;
        Object invoke;
        try {
            Class<?> cls = Class.forName("android.content.pm.PackageParser");
            Class<?>[] clsArr = {String.class};
            Object[] objArr = {str};
            if (Build.VERSION.SDK_INT > 19) {
                newInstance = cls.newInstance();
            } else {
                newInstance = cls.getConstructor(clsArr).newInstance(objArr);
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            if (Build.VERSION.SDK_INT > 19) {
                Method declaredMethod = cls.getDeclaredMethod("parsePackage", File.class, Integer.TYPE);
                declaredMethod.setAccessible(true);
                invoke = declaredMethod.invoke(newInstance, new File(str), 64);
            } else {
                Method declaredMethod2 = cls.getDeclaredMethod("parsePackage", File.class, String.class, DisplayMetrics.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                invoke = declaredMethod2.invoke(newInstance, new File(str), str, displayMetrics, 64);
            }
            cls.getDeclaredMethod("collectCertificates", invoke.getClass(), Integer.TYPE).invoke(newInstance, invoke, 64);
            return (Signature[]) invoke.getClass().getDeclaredField("mSignatures").get(invoke);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
