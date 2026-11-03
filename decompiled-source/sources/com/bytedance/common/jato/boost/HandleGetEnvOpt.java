package com.bytedance.common.jato.boost;

import android.os.Build;
/* loaded from: classes.dex */
public class HandleGetEnvOpt {
    private static native void nativeInit();

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void init() {
        if (Build.VERSION.SDK_INT < 33 || Build.VERSION.SDK_INT > 34) {
            return;
        }
        try {
            nativeInit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
