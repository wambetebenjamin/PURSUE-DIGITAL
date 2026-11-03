package com.bytedance.ttgame.framework.gbridge.utils;

import android.util.Log;
/* loaded from: classes3.dex */
public class LogUtil {
    public static boolean DEBUG = true;
    private static final String TAG = "GBridge";

    public static void d(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public static void d(String str, Object obj) {
        if (DEBUG) {
            if (obj != null) {
                str = str + " " + obj.toString();
            }
            Log.d(TAG, str);
        }
    }

    public static void e(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    public static void w(String str) {
        Log.w(TAG, str);
    }

    public static void i(String str) {
        Log.i(TAG, str);
    }
}
