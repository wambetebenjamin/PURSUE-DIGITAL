package com.bytedance.geckox.logger;
/* compiled from: GeckoLogger.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f9215a = false;

    /* renamed from: b  reason: collision with root package name */
    private static c f9216b = new a();

    public static void enable() {
        f9215a = true;
    }

    public static void disable() {
        f9215a = false;
    }

    public static boolean isDebug() {
        return f9215a;
    }

    public static void a(c cVar) {
        f9216b = cVar;
    }

    public static void a(String str, Object... objArr) {
        c cVar;
        if (!f9215a || (cVar = f9216b) == null) {
            return;
        }
        cVar.d(str, objArr);
    }

    public static void w(String str, String str2, Throwable th) {
        c cVar;
        if (!f9215a || (cVar = f9216b) == null) {
            return;
        }
        cVar.w(str, str2, th);
    }

    public static void w(String str, String str2) {
        c cVar;
        if (!f9215a || (cVar = f9216b) == null) {
            return;
        }
        cVar.w(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        c cVar;
        if (!f9215a || (cVar = f9216b) == null) {
            return;
        }
        cVar.e(str, str2, th);
    }
}
