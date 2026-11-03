package com.bytedance.bdinstall;
/* compiled from: DrLog.java */
/* loaded from: classes.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    static final String f4012a = "DrLog";

    /* renamed from: b  reason: collision with root package name */
    public static boolean f4013b = false;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4014c = com.bytedance.bdinstall.util.j.b();

    /* renamed from: d  reason: collision with root package name */
    private static final String f4015d = "U SHALL NOT PASS!";

    /* renamed from: e  reason: collision with root package name */
    private static ah f4016e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(ah ahVar) {
        f4016e = ahVar;
    }

    public static void a(String str) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.d(str, null);
        }
    }

    public static void a(String str, Throwable th) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.w(str, th);
        }
    }

    public static void b(String str, Throwable th) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.e(str, th);
        }
    }

    public static void c(String str, Throwable th) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.i(str, th);
        }
    }

    public static void d(String str, Throwable th) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.v(str, th);
        }
    }

    public static void a(Throwable th) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.e(f4015d, th);
        }
    }

    public static boolean a() {
        return f4013b;
    }

    public static void b(String str) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.v(str, null);
        }
    }

    public static void c(String str) {
        ah ahVar = f4016e;
        if (ahVar != null) {
            ahVar.e(str, null);
        }
    }
}
