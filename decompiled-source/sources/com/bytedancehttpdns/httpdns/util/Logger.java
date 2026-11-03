package com.bytedancehttpdns.httpdns.util;

import android.util.Log;
/* loaded from: classes8.dex */
public class Logger {
    private static final String TAG = "veHttpDns";
    static int mLevel = 4;

    public static void setLogLevel(int i2) {
        mLevel = i2;
    }

    public static int getLogLevel() {
        return mLevel;
    }

    public static boolean debug() {
        return mLevel <= 3;
    }

    public static void v(String str) {
        v(TAG, str);
    }

    public static void v(String str, String str2) {
        if (str2 != null && mLevel <= 2) {
            Log.v(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && mLevel <= 2) {
            Log.v(str, str2, th);
        }
    }

    public static void d(String str) {
        d(TAG, str);
    }

    public static void d(String str, String str2) {
        if (str2 != null && mLevel <= 3) {
            Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && mLevel <= 3) {
            Log.d(str, str2, th);
        }
    }

    public static void i(String str) {
        i(TAG, str);
    }

    public static void i(String str, String str2) {
        if (str2 != null && mLevel <= 4) {
            Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && mLevel <= 4) {
            Log.i(str, str2, th);
        }
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void w(String str, String str2) {
        if (str2 != null && mLevel <= 5) {
            Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && mLevel <= 5) {
            Log.w(str, str2, th);
        }
    }

    public static void e(String str) {
        e(TAG, str);
    }

    public static void e(String str, String str2) {
        if (str2 != null && mLevel <= 6) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && mLevel <= 6) {
            Log.e(str, str2, th);
        }
    }
}
