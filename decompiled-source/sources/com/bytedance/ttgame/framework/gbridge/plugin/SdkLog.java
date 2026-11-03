package com.bytedance.ttgame.framework.gbridge.plugin;

import android.util.Log;
/* loaded from: classes3.dex */
public class SdkLog {
    public static void v(String str, String str2) {
        Log.v(str, str2);
    }

    public static void v(String str, String str2, Object... objArr) {
        Log.v(str, String.format(str2, objArr));
    }

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }
}
