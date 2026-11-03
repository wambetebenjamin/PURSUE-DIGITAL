package com.bytedance.common.jato.gfx;

import android.os.Build;
/* loaded from: classes.dex */
public class HwuiBoost {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f4713a = false;

    private static native void nativeBoost(boolean z, boolean z2);

    public static void a(boolean z, boolean z2, boolean z3) {
        synchronized (HwuiBoost.class) {
            if (f4713a) {
                return;
            }
            f4713a = true;
            if (z && Build.VERSION.SDK_INT >= 29) {
                GLESInitBoost.a(false);
            }
            if (Build.VERSION.SDK_INT < 31) {
                z3 = false;
            }
            if (z2 || z3) {
                a(z2, z3);
            }
        }
    }

    private static void a(boolean z, boolean z2) {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            compatible.standard.a.a();
            nativeBoost(z, z2);
        }
    }
}
