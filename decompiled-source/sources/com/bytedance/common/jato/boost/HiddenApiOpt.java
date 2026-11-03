package com.bytedance.common.jato.boost;

import android.os.Build;
/* loaded from: classes.dex */
public class HiddenApiOpt {
    private static native boolean nOptimize();

    public static void a() {
        if (Build.VERSION.SDK_INT <= 30) {
            return;
        }
        com.bytedance.common.jato.d.loadLibrary();
        try {
            nOptimize();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
