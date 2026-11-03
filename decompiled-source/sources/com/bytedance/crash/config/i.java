package com.bytedance.crash.config;

import com.bytedance.crash.IEncrypt;
import com.bytedance.crash.util.j;
/* compiled from: RuntimeConfig.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final long f6372a = 8000;

    /* renamed from: b  reason: collision with root package name */
    private static final long f6373b = 500;

    /* renamed from: c  reason: collision with root package name */
    private static long f6374c = 8000;

    /* renamed from: d  reason: collision with root package name */
    private static long f6375d = 500;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f6376e = false;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f6378g;

    /* renamed from: h  reason: collision with root package name */
    private static boolean f6379h;

    /* renamed from: f  reason: collision with root package name */
    private static IEncrypt f6377f = new a();

    /* renamed from: i  reason: collision with root package name */
    private static boolean f6380i = true;

    /* renamed from: j  reason: collision with root package name */
    private static boolean f6381j = true;

    public static boolean k() {
        return true;
    }

    public static long a() {
        return f6375d;
    }

    public static void a(long j2) {
        f6375d = j2;
    }

    public static long b() {
        return f6374c;
    }

    public static void b(long j2) {
        f6374c = j2;
    }

    public static IEncrypt c() {
        return f6377f;
    }

    public static void a(IEncrypt iEncrypt) {
        if (iEncrypt != null) {
            f6377f = iEncrypt;
        }
    }

    public static boolean d() {
        return f6376e;
    }

    public static void a(boolean z) {
        f6376e = z;
    }

    public static boolean e() {
        return f6378g;
    }

    public static void f() {
        f6378g = true;
    }

    public static boolean g() {
        return f6379h;
    }

    public static void h() {
        f6379h = true;
    }

    public static boolean i() {
        return d() && j();
    }

    public static boolean j() {
        String g2;
        com.bytedance.crash.monitor.a a2 = com.bytedance.crash.monitor.h.a();
        return (a2 == null || (g2 = a2.g()) == null || !g2.contains("test_crash")) ? false : true;
    }

    /* compiled from: RuntimeConfig.java */
    /* loaded from: classes2.dex */
    static class a implements IEncrypt {
        a() {
        }

        @Override // com.bytedance.crash.IEncrypt
        public byte[] encrypt(byte[] bArr) {
            return j.encrypt(bArr);
        }
    }

    public static void b(boolean z) {
        f6380i = z;
    }

    public static boolean l() {
        return f6380i;
    }

    public static void c(boolean z) {
        f6381j = z;
    }

    public static boolean m() {
        return f6381j;
    }
}
