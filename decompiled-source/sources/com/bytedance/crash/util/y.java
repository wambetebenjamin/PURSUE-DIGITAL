package com.bytedance.crash.util;

import android.util.Log;
/* compiled from: NpthLog.java */
/* loaded from: classes2.dex */
public final class y {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7325a = "NPTH";

    public static void d(Object obj) {
        if (com.bytedance.crash.config.i.i()) {
            Log.d(f7325a, String.valueOf(obj));
        }
    }

    public static void a(String str, Object obj) {
        if (com.bytedance.crash.config.i.i()) {
            Log.i(f7325a, str + " " + obj);
        }
    }

    public static void i(Object obj) {
        if (com.bytedance.crash.config.i.d()) {
            Log.i(f7325a, String.valueOf(obj));
        }
    }

    public static void i(String str, Object obj) {
        if (com.bytedance.crash.config.i.d()) {
            Log.i(f7325a, str + " " + obj);
        }
    }

    public static void w(Throwable th) {
        a(f7325a, th);
    }

    public static void w(String str) {
        a(f7325a, str);
    }

    public static void a(String str, Throwable th) {
        if (com.bytedance.crash.config.i.d()) {
            Log.w(str, th);
        }
    }

    public static void a(String str, String str2) {
        if (com.bytedance.crash.config.i.d()) {
            Log.w(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (com.bytedance.crash.config.i.d()) {
            Log.w(str, str2, th);
        }
    }

    public static void e(Throwable th) {
        e("", th);
    }

    public static void e(String str, Throwable th) {
        Log.e(f7325a, str, th);
    }

    public static void e(String str, Object obj) {
        Log.e(f7325a, str + " " + obj);
    }

    public static void e(String str, Object obj, Throwable th) {
        if (com.bytedance.crash.config.i.d()) {
            Log.e(f7325a, str + " " + obj, th);
        }
    }
}
