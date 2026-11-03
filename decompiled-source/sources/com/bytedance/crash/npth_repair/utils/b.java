package com.bytedance.crash.npth_repair.utils;

import android.util.Log;
/* compiled from: RepairLog.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6961a = "NpthRepair";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f6962b = false;

    public static void a(Object obj) {
        if (f6962b) {
            Log.d(f6961a, String.valueOf(obj));
        }
    }

    public static void b(Object obj) {
        if (f6962b) {
            Log.i(f6961a, String.valueOf(obj));
        }
    }

    public static void a(String str, Object obj) {
        if (f6962b) {
            Log.i(f6961a, str + " " + obj);
        }
    }

    public static void b(String str, Object obj) {
        if (f6962b) {
            Log.i(f6961a, str + " " + obj);
        }
    }

    public static void a(boolean z) {
        f6962b = z;
    }

    public static boolean a() {
        return f6962b;
    }
}
