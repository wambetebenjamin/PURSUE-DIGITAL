package com.bytedance.common.jato.memory;

import android.os.Build;
import android.os.Process;
/* loaded from: classes.dex */
public class MinFreeHeapOpt {
    protected static native void nativeSetMinFreeHeapPecent(double d2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void a(double d2) {
        if (Build.VERSION.SDK_INT > 30 && Process.is64Bit()) {
            nativeSetMinFreeHeapPecent(d2);
        }
    }
}
