package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public class BlackDenseRegionThresholdOpt {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4532a = "BlackDenseThreshold";

    private static native void nResetBlackDenseRegionThreshold();

    private static native int nSetBlackDenseRegionThreshold(int i2, int i3);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static int a(int i2, int i3) {
        int i4 = -1;
        if (Build.VERSION.SDK_INT < 26) {
            return -1;
        }
        try {
            i4 = nSetBlackDenseRegionThreshold(i2, i3);
            Log.d(f4532a, "setBlackDenseRegionThreshold ret = " + i4);
            return i4;
        } catch (NoSuchMethodError e2) {
            Log.e(f4532a, "NoSuchMethodError", e2);
            e2.printStackTrace();
            return i4;
        } catch (UnsatisfiedLinkError e3) {
            Log.e(f4532a, "UnsatisfiedLinkError", e3);
            e3.printStackTrace();
            return i4;
        }
    }

    public static void a() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        try {
            nResetBlackDenseRegionThreshold();
            Log.d(f4532a, "resetBlackDenseRegionThreshold");
        } catch (NoSuchMethodError e2) {
            Log.e(f4532a, "NoSuchMethodError", e2);
            e2.printStackTrace();
        } catch (UnsatisfiedLinkError e3) {
            Log.e(f4532a, "UnsatisfiedLinkError", e3);
            e3.printStackTrace();
        }
    }
}
