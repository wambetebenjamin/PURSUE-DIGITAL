package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public class PosionDeadObjectOpt {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f4549a = false;

    /* renamed from: b  reason: collision with root package name */
    private static final String f4550b = "PosionDeadObjectOpt";

    private static native int nBypassPoisonDeadObjects();

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static int a() {
        int i2 = -1;
        if (Build.VERSION.SDK_INT < 29 || Build.VERSION.SDK_INT > 33 || f4549a) {
            return -1;
        }
        try {
            i2 = nBypassPoisonDeadObjects();
            f4549a = true;
            return i2;
        } catch (NoSuchMethodError e2) {
            Log.e(f4550b, "NoSuchMethodError", e2);
            e2.printStackTrace();
            return i2;
        } catch (UnsatisfiedLinkError e3) {
            Log.e(f4550b, "UnsatisfiedLinkError", e3);
            e3.printStackTrace();
            return i2;
        }
    }
}
