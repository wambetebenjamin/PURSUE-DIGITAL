package com.google.android.gms.internal.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public final class zzj {
    public static Object zza(Class cls, String str, zzi... zziVarArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return zzc(cls, "isIsolated", null, false, zziVarArr);
    }

    public static Object zzb(String str, String str2, ClassLoader classLoader, zzi... zziVarArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return zzc(classLoader.loadClass("com.google.android.gms.common.security.ProviderInstallerImpl"), "reportRequestStats2", null, false, zziVarArr);
    }

    private static Object zzc(Class cls, String str, Object obj, boolean z, zzi... zziVarArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int length = zziVarArr.length;
        Class<?>[] clsArr = new Class[length];
        Object[] objArr = new Object[length];
        for (int i2 = 0; i2 < zziVarArr.length; i2++) {
            zzi zziVar = zziVarArr[i2];
            Objects.requireNonNull(zziVar);
            clsArr[i2] = zziVar.zzc();
            objArr[i2] = zziVarArr[i2].zzd();
        }
        return cls.getDeclaredMethod(str, clsArr).invoke(null, objArr);
    }
}
