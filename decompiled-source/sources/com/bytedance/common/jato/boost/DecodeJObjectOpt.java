package com.bytedance.common.jato.boost;

import android.os.Build;
/* loaded from: classes.dex */
public class DecodeJObjectOpt {
    private static native boolean nOptimizeDecodeJObject();

    public static void a() {
        if (Build.VERSION.SDK_INT > 30 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        com.bytedance.common.jato.d.loadLibrary();
        try {
            nOptimizeDecodeJObject();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
