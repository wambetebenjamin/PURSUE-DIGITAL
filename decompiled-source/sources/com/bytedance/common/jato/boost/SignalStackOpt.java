package com.bytedance.common.jato.boost;

import android.os.Build;
import android.os.Process;
/* loaded from: classes.dex */
public class SignalStackOpt {
    private static native void signalStackOptInternal(int i2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void a(int i2) {
        if (Build.VERSION.SDK_INT >= 23 && Process.is64Bit() && com.bytedance.common.jato.d.loadLibrary()) {
            signalStackOptInternal(i2);
        }
    }
}
