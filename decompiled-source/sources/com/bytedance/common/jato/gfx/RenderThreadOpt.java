package com.bytedance.common.jato.gfx;

import android.os.Build;
import android.os.Process;
/* loaded from: classes.dex */
public class RenderThreadOpt {
    private static native boolean nativeChangeRenderPipelineType(boolean z);

    private static native boolean nativePinImagesOpt();

    private static native boolean nativeResourceCacheOpt(int i2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void a(boolean z) {
        if (Build.VERSION.SDK_INT >= 26 && Process.is64Bit() && !com.bytedance.common.jato.d.loadLibrary()) {
            nativeChangeRenderPipelineType(z);
        }
    }

    public static void a(int i2) {
        if (Process.is64Bit() && Build.VERSION.SDK_INT >= 29 && Build.VERSION.SDK_INT <= 32) {
            nativeResourceCacheOpt(i2);
        }
    }

    public static void a() {
        if (Build.VERSION.SDK_INT >= 28 && Process.is64Bit()) {
            nativePinImagesOpt();
        }
    }
}
