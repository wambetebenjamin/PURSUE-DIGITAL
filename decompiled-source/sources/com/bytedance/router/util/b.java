package com.bytedance.router.util;

import android.util.Log;
/* compiled from: Logger.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12236a = "SmartRouter";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f12237b = false;

    public static void setDebug(boolean z) {
        f12237b = z;
    }

    public static boolean isDebug() {
        return f12237b;
    }

    public static void v(String str) {
        v(f12236a, str);
    }

    public static void d(String str) {
        d(f12236a, str);
    }

    public static void i(String str) {
        i(f12236a, str);
    }

    public static void w(String str) {
        w(f12236a, str);
    }

    public static void e(String str) {
        e(f12236a, str);
    }

    public static void v(String str, String str2) {
        if (f12237b) {
            Log.v(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (f12237b) {
            Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (f12237b) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }
}
