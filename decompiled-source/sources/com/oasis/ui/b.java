package com.oasis.ui;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;
/* loaded from: classes9.dex */
final class b implements e {

    /* renamed from: a  reason: collision with root package name */
    private Class<?> f17708a;

    /* renamed from: b  reason: collision with root package name */
    private Method f17709b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f17710c = false;

    private Method a(Class<?> cls) {
        Method method = this.f17709b;
        if (method != null) {
            return method;
        }
        try {
            this.f17709b = cls.getMethod("getNotchSize", new Class[0]);
        } catch (NoSuchMethodException unused) {
            Log.e("Oasis.UI", "[HuaweiNotch]getNotchSize NoSuchMethodException");
        }
        return this.f17709b;
    }

    private Class c(Context context) {
        Class<?> cls = this.f17708a;
        if (cls != null) {
            return cls;
        }
        if (this.f17710c) {
            return null;
        }
        try {
            this.f17708a = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
        } catch (ClassNotFoundException unused) {
            this.f17710c = true;
            Log.e("Oasis.UI", "[HuaweiNotch]HwNotchSizeUtil ClassNotFoundException");
        }
        return this.f17708a;
    }

    @Override // com.oasis.ui.e
    public final boolean a() {
        return Build.VERSION.SDK_INT >= 28 && !Build.MODEL.contains("nova 4");
    }

    @Override // com.oasis.ui.e
    public final boolean a(Context context) {
        Class c2;
        if (Build.VERSION.SDK_INT > 25 && (c2 = c(context)) != null) {
            try {
                return ((Boolean) c2.getMethod("hasNotchInScreen", new Class[0]).invoke(c2, new Object[0])).booleanValue();
            } catch (NoSuchMethodException unused) {
                Log.e("Oasis.UI", "[HuaweiNotch]hasNotchInScreen NoSuchMethodException");
                return false;
            } catch (Exception e2) {
                Log.e("Oasis.UI", "[HuaweiNotch]Invoke method failed.", e2);
                return false;
            }
        }
        return false;
    }

    @Override // com.oasis.ui.e
    public final boolean b() {
        String a2 = g.a("ro.config.hw_fold_disp");
        return (a2 == null || a2.isEmpty()) ? false : true;
    }

    @Override // com.oasis.ui.e
    public final int[] b(Context context) {
        Class c2;
        Method a2;
        if (Build.VERSION.SDK_INT <= 25 || (c2 = c(context)) == null || (a2 = a(c2)) == null) {
            return null;
        }
        try {
            int[] iArr = (int[]) a2.invoke(c2, new Object[0]);
            if (iArr == null) {
                return null;
            }
            return g.a(iArr[1]);
        } catch (Exception e2) {
            Log.e("Oasis.UI", "[HuaweiNotch]Invoke method failed.", e2);
            return null;
        }
    }
}
