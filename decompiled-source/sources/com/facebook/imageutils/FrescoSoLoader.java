package com.facebook.imageutils;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.soloader.SoLoader;
/* loaded from: classes8.dex */
public class FrescoSoLoader {
    private static final Class<?> TAG = FrescoSoLoader.class;
    private static volatile boolean sInitialized = false;
    private static SoLoaderHandler sSoLoaderHandler;

    /* loaded from: classes8.dex */
    public interface SoLoaderHandler {
        void loadLibrary(String str);
    }

    public static void setSoLoaderHandler(SoLoaderHandler soLoaderHandler) {
        sSoLoaderHandler = soLoaderHandler;
    }

    public static void initSoLoader(Context context) {
        if (sSoLoaderHandler != null) {
            sInitialized = true;
            return;
        }
        try {
            SoLoader.init(context, 0);
            sInitialized = true;
        } catch (Throwable th) {
            FLog.w(TAG, th, "Could not initialize SoLoader", new Object[0]);
        }
    }

    public static void loadLibrary(String str) {
        if (sInitialized) {
            try {
                SoLoaderHandler soLoaderHandler = sSoLoaderHandler;
                if (soLoaderHandler != null) {
                    soLoaderHandler.loadLibrary(str);
                    return;
                } else {
                    SoLoader.loadLibrary(str);
                    return;
                }
            } catch (Throwable th) {
                FLog.w(TAG, th, "loadLibrary fail", new Object[0]);
            }
        }
        System.loadLibrary(str);
    }
}
