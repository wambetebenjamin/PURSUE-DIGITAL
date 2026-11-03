package com.facebook.imagepipeline.nativecode;

import android.os.Build;
import com.facebook.imageutils.FrescoSoLoader;
/* loaded from: classes8.dex */
public class StaticWebpNativeLoader {
    private static boolean sInitialized;

    public static synchronized void ensure() {
        synchronized (StaticWebpNativeLoader.class) {
            if (!sInitialized) {
                if (Build.VERSION.SDK_INT <= 16) {
                    try {
                        FrescoSoLoader.loadLibrary("fb_jpegturbo");
                    } catch (IllegalStateException | UnsatisfiedLinkError unused) {
                    }
                }
                FrescoSoLoader.loadLibrary("static-webp");
                sInitialized = true;
            }
        }
    }
}
