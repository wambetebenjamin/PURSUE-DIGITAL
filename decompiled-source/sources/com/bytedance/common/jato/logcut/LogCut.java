package com.bytedance.common.jato.logcut;

import android.os.Build;
import android.util.Log;
import com.bytedance.common.jato.c;
/* loaded from: classes.dex */
public class LogCut {

    /* renamed from: a  reason: collision with root package name */
    public static int f4784a = 20000;

    /* renamed from: b  reason: collision with root package name */
    public static int f4785b = 20001;

    /* renamed from: c  reason: collision with root package name */
    public static int f4786c = 20002;

    /* renamed from: d  reason: collision with root package name */
    private static String f4787d = "LogCut";

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f4788e = false;

    /* renamed from: f  reason: collision with root package name */
    private static volatile boolean f4789f = false;

    /* renamed from: g  reason: collision with root package name */
    private static int f4790g = 20002;

    /* renamed from: h  reason: collision with root package name */
    private static c f4791h;

    private static native int initLogCutInternal(int i2, int i3);

    private static native void logCutStartInternal();

    private static native void logCutStopInternal();

    private static boolean c() {
        return f4788e && !f4789f;
    }

    public static void a(int i2) {
        if (Build.VERSION.SDK_INT > 32) {
            Log.d(f4787d, "logcut support less than android 12");
            return;
        }
        if (f4790g != i2) {
            f4788e = false;
            f4789f = false;
        }
        if (f4788e || f4789f) {
            return;
        }
        try {
            int initLogCutInternal = initLogCutInternal(i2, Build.VERSION.SDK_INT);
            if (initLogCutInternal != 0) {
                c cVar = f4791h;
                if (cVar != null) {
                    cVar.onDebugInfo("logcut init failed:" + initLogCutInternal);
                }
                f4789f = true;
            }
            f4788e = true;
            f4790g = i2;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a() {
        if (c()) {
            logCutStartInternal();
        }
    }

    public static void b() {
        if (c()) {
            logCutStopInternal();
        }
    }

    public static void a(c cVar) {
        f4791h = cVar;
    }
}
