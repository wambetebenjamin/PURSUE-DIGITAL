package com.bytedance.common.jato.boost;

import android.os.Build;
import android.os.Process;
/* loaded from: classes.dex */
public class MutexOpt {
    private static native void exclusiveLockSpinOptInternal(int i2);

    public static void a(int i2) {
        if (Build.VERSION.SDK_INT < 30 || Build.VERSION.SDK_INT > 34 || !Process.is64Bit()) {
            return;
        }
        com.bytedance.common.jato.d.loadLibrary();
        exclusiveLockSpinOptInternal(i2);
    }
}
