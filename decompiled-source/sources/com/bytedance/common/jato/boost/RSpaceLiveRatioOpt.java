package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public class RSpaceLiveRatioOpt {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4551a = "RSpaceLiveRatio";

    private static native int nRegionSpaceLiveRatioOpt(int i2, int i3);

    private static native void nResetRegionSpaceLiveRatioOpt();

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static int a(int i2, int i3) {
        int i4 = -1;
        if (Build.VERSION.SDK_INT < 26) {
            return -1;
        }
        try {
            i4 = nRegionSpaceLiveRatioOpt(i2, i3);
            Log.d(f4551a, "regionSpaceLiveRatioOpt ret = " + i4);
            return i4;
        } catch (NoSuchMethodError e2) {
            Log.e(f4551a, "NoSuchMethodError", e2);
            e2.printStackTrace();
            return i4;
        } catch (UnsatisfiedLinkError e3) {
            Log.e(f4551a, "UnsatisfiedLinkError", e3);
            e3.printStackTrace();
            return i4;
        }
    }

    public static void a() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        try {
            nResetRegionSpaceLiveRatioOpt();
            Log.d(f4551a, "resetRegionSpaceLiveRatioOpt");
        } catch (NoSuchMethodError e2) {
            Log.e(f4551a, "NoSuchMethodError", e2);
            e2.printStackTrace();
        } catch (UnsatisfiedLinkError e3) {
            Log.e(f4551a, "UnsatisfiedLinkError", e3);
            e3.printStackTrace();
        }
    }
}
