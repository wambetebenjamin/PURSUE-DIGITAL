package com.ss.android.deviceregister;
/* compiled from: LogUtils.java */
/* loaded from: classes9.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    public static String f18267a = "LogDeviceRegister";

    /* renamed from: b  reason: collision with root package name */
    private static int f18268b;

    /* renamed from: c  reason: collision with root package name */
    private static a f18269c;

    /* compiled from: LogUtils.java */
    /* loaded from: classes9.dex */
    public interface a {
        void a(String str, String str2, Throwable th);

        void b(String str, String str2, Throwable th);

        void c(String str, String str2, Throwable th);

        void d(String str, String str2, Throwable th);

        void e(String str, String str2, Throwable th);
    }

    public static void a(int i2) {
        f18268b = i2;
    }

    public static int a() {
        return f18268b;
    }

    public static boolean b() {
        return f18268b <= 3;
    }

    public static void a(String str, String str2) {
        a(str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        a aVar = f18269c;
        if (aVar == null || f18268b > 2) {
            return;
        }
        aVar.a(str, str2, th);
    }

    public static void b(String str, String str2) {
        b(str, str2, null);
    }

    public static void b(String str, String str2, Throwable th) {
        a aVar = f18269c;
        if (aVar == null || f18268b > 3) {
            return;
        }
        aVar.b(str, str2, th);
    }

    public static void c(String str, String str2) {
        c(str, str2, null);
    }

    public static void c(String str, String str2, Throwable th) {
        a aVar = f18269c;
        if (aVar == null || f18268b > 4) {
            return;
        }
        aVar.c(str, str2, th);
    }

    public static void d(String str, String str2) {
        d(str, str2, null);
    }

    public static void d(String str, String str2, Throwable th) {
        a aVar = f18269c;
        if (aVar == null || f18268b > 6) {
            return;
        }
        aVar.d(str, str2, th);
    }

    public static void e(String str, String str2) {
        e(str, str2, null);
    }

    public static void e(String str, String str2, Throwable th) {
        a aVar = f18269c;
        if (aVar == null || f18268b > 5) {
            return;
        }
        aVar.e(str, str2, th);
    }

    public static void a(a aVar) {
        f18269c = aVar;
    }
}
