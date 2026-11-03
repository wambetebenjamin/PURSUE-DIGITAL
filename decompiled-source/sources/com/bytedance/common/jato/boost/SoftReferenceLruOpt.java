package com.bytedance.common.jato.boost;

import android.os.Build;
import android.util.Log;
/* loaded from: classes.dex */
public class SoftReferenceLruOpt {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4554a = "SoftReferenceLruOpt";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f4555b = false;

    private static native int nEnable(long j2, int i2);

    private static native int nEnableV2(long j2, int i2);

    public static synchronized void a(long j2, int i2) {
        synchronized (SoftReferenceLruOpt.class) {
            if (f4555b) {
                Log.d(f4554a, "already enable");
                return;
            }
            if (com.bytedance.common.jato.d.loadLibrary() && Build.VERSION.SDK_INT >= 21) {
                try {
                    if (nEnable(j2, i2) == 1) {
                        f4555b = true;
                        Log.i(f4554a, "enable success.");
                    } else {
                        Log.e(f4554a, "enable failed.");
                    }
                } catch (Exception e2) {
                    Log.e(f4554a, "Exception occurred:" + e2.getMessage());
                }
            }
        }
    }

    public static synchronized void b(long j2, int i2) {
        synchronized (SoftReferenceLruOpt.class) {
            if (f4555b) {
                Log.d(f4554a, "already enable");
                return;
            }
            if (com.bytedance.common.jato.d.loadLibrary() && Build.VERSION.SDK_INT >= 21) {
                try {
                    if (nEnableV2(j2, i2) == 1) {
                        f4555b = true;
                        Log.i(f4554a, "enable v2 success.");
                    } else {
                        Log.e(f4554a, "enable v2 failed.");
                    }
                } catch (Exception e2) {
                    Log.e(f4554a, "Exception occurred:" + e2.getMessage());
                }
            }
        }
    }
}
