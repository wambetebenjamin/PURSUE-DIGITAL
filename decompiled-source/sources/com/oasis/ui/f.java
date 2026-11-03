package com.oasis.ui;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.facebook.internal.security.CertificateUtil;
/* loaded from: classes9.dex */
final class f implements e {
    private static String c() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls.newInstance(), "ro.oppo.screen.heteromorphism");
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.oasis.ui.e
    public final boolean a() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Override // com.oasis.ui.e
    public final boolean a(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    @Override // com.oasis.ui.e
    public final boolean b() {
        try {
            Class<?> cls = Class.forName("com.oplus.content.OplusFeatureConfigManager");
            Object invoke = cls.getDeclaredMethod("hasFeature", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "oplus.hardware.type.fold");
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Exception e2) {
            Log.e("Oasis.UI", "[OppoNotch]get foldable failed.", e2);
            return false;
        }
    }

    @Override // com.oasis.ui.e
    public final int[] b(Context context) {
        String[] split;
        String c2 = c();
        if (c2 == null || (split = c2.split(CertificateUtil.DELIMITER)) == null || split.length != 2) {
            return null;
        }
        String[] split2 = split[0].split(",");
        return g.a(Integer.parseInt(split[1].split(",")[1]) - Integer.parseInt(split2[1]));
    }
}
