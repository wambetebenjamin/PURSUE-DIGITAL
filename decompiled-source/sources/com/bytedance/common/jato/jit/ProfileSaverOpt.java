package com.bytedance.common.jato.jit;

import android.os.Build;
import android.util.Log;
import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class ProfileSaverOpt {
    private static native void disableInlineCache();

    public static native void enableInlineCache();

    private static native void nSaveProfileInBg();

    private static native void profileSaveBackup();

    public static void a() {
        if (Build.VERSION.SDK_INT < 29 || Build.VERSION.SDK_INT > 34) {
            return;
        }
        d.loadLibrary();
        disableInlineCache();
    }

    public static void b() {
        if (Build.VERSION.SDK_INT < 29 || Build.VERSION.SDK_INT > 33) {
            return;
        }
        d.loadLibrary();
        profileSaveBackup();
    }

    public static void c() {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        JitCompiler.hookProfileSaverBug();
        if (JitCompiler.canForceSaveProfile()) {
            Log.d("ProfileSaverOpt", "saveProfileInBg enable");
            nSaveProfileInBg();
        }
    }
}
