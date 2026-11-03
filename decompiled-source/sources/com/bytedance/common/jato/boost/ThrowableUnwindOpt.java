package com.bytedance.common.jato.boost;

import android.os.Build;
/* loaded from: classes.dex */
public class ThrowableUnwindOpt {
    private static native boolean optimizeFileExistUnwind();

    public static void a() {
        if (Build.VERSION.SDK_INT <= 28) {
            return;
        }
        com.bytedance.common.jato.d.loadLibrary();
        try {
            optimizeFileExistUnwind();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
