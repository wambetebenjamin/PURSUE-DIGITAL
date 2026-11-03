package com.oasis.ui;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;
/* loaded from: classes9.dex */
final class h implements e {

    /* renamed from: a  reason: collision with root package name */
    private Class<?> f17714a;

    private Class c(Context context) {
        Class<?> cls = this.f17714a;
        if (cls != null) {
            return cls;
        }
        try {
            this.f17714a = context.getClassLoader().loadClass("android.util.FtFeature");
        } catch (ClassNotFoundException unused) {
            Log.e("Oasis.UI", "[VivoNotch]FtFeature ClassNotFoundException");
        }
        return this.f17714a;
    }

    @Override // com.oasis.ui.e
    public final boolean a() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Override // com.oasis.ui.e
    public final boolean a(Context context) {
        Class c2 = c(context);
        if (c2 == null) {
            return false;
        }
        try {
            return ((Boolean) c2.getMethod("isFeatureSupport", Integer.TYPE).invoke(c2, 32)).booleanValue();
        } catch (NoSuchMethodException unused) {
            Log.e("Oasis.UI", "[VivoNotch]hasNotchInScreen NoSuchMethodException");
            return false;
        } catch (Exception e2) {
            Log.e("Oasis.UI", "[VivoNotch]Invoke method failed.", e2);
            return false;
        }
    }

    @Override // com.oasis.ui.e
    public final boolean b() {
        try {
            Class<?> cls = Class.forName("android.util.FtDeviceInfo");
            return "foldable".equals(cls.getMethod("getDeviceType", new Class[0]).invoke(cls, new Object[0]));
        } catch (Exception e2) {
            Log.e("Oasis.UI", "[VivoNotch]check foldable failed.", e2);
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.oasis.ui.e
    public final int[] b(Context context) {
        if (((WindowManager) context.getSystemService("window")) == null) {
            return null;
        }
        String str = Build.MODEL;
        if (str.contains("V1813A") || str.contains("Z1") || str.contains("Y85") || str.contains("Y93") || str.contains("V1818CA") || str.contains("V1818CT") || str.contains("V1732A") || str.contains("Y83")) {
            return g.a(g.a(context));
        }
        return null;
    }
}
