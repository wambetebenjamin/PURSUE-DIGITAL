package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.build.BuildConfig;
/* loaded from: classes9.dex */
public class NativeLibraryLoadedStatus {
    private static NativeLibraryLoadedStatusProvider sProvider;

    /* loaded from: classes9.dex */
    public interface NativeLibraryLoadedStatusProvider {
        boolean areMainDexNativeMethodsReady();

        boolean areNativeMethodsReady();
    }

    public static void checkLoaded(boolean z) {
        NativeLibraryLoadedStatusProvider nativeLibraryLoadedStatusProvider;
        boolean areNativeMethodsReady;
        if (BuildConfig.ENABLE_ASSERTS && (nativeLibraryLoadedStatusProvider = sProvider) != null) {
            if (z) {
                areNativeMethodsReady = nativeLibraryLoadedStatusProvider.areMainDexNativeMethodsReady();
            } else {
                areNativeMethodsReady = nativeLibraryLoadedStatusProvider.areNativeMethodsReady();
            }
            if (!areNativeMethodsReady) {
                throw new JniException("Native method called before the native library was ready.");
            }
        }
    }

    public static void setProvider(NativeLibraryLoadedStatusProvider nativeLibraryLoadedStatusProvider) {
        sProvider = nativeLibraryLoadedStatusProvider;
    }

    public static NativeLibraryLoadedStatusProvider getProviderForTesting() {
        return sProvider;
    }
}
