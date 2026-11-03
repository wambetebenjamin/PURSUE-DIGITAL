package com.bytedance.common.jato.lock;

import android.os.Build;
import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class LockMaxSpinsOpt {
    private static native void nativeSetMaxSpins(int i2);

    static {
        d.loadLibrary();
    }

    public static void setLockMaxSpins(int i2) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        nativeSetMaxSpins(i2);
    }
}
