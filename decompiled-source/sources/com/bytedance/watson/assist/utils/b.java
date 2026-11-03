package com.bytedance.watson.assist.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
/* compiled from: DebugLog.java */
/* loaded from: classes8.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16285a = "watson_assist";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f16286b = Log.isLoggable(f16285a, 3);

    public static void v(String str) {
        v(f16285a, str);
    }

    public static void d(String str) {
        d(f16285a, str);
    }

    public static void i(String str) {
        i(f16285a, str);
    }

    public static void w(String str) {
        w(f16285a, str);
    }

    public static void e(String str) {
        e(f16285a, str);
    }

    public static void v(String str, String str2) {
        if (f16286b) {
            Log.v(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (f16286b) {
            Log.d(str, str2);
        }
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

    public static void toast(Context context, String str) {
        Toast.makeText(context, str, 1).show();
    }
}
