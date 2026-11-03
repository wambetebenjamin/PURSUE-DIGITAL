package com.bytedance.ttgame.module.rn.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
/* loaded from: classes6.dex */
public class RNDebugUtil {
    private static final String RN_FLAG_OPEN = "rn_flag_open";
    private static final String SHARED_PREFS_DORAEMON = "shared_prefs_doraemon";

    public static boolean getRNDebug(Context context) {
        if (context == null) {
            return false;
        }
        return getSharedPrefs(context).getBoolean("rn_flag_open", false);
    }

    public static void setRNDebug(Context context, boolean z) {
        if (context == null) {
            return;
        }
        getSharedPrefs(context).edit().putBoolean("rn_flag_open", z).apply();
    }

    private static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_DORAEMON, 0);
    }

    public static boolean isApkDebugable(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean checkDebugFile(Context context) {
        return new File(context.getFilesDir(), "bundles" + File.separator + "rn_debug.txt").exists();
    }
}
