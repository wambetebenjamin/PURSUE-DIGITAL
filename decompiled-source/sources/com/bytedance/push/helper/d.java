package com.bytedance.push.helper;
/* compiled from: EnsureExceptionHelper.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f11366a;

    public static void a(Throwable th, String str) {
        Boolean bool = f11366a;
        if (bool == null || bool.booleanValue()) {
            try {
                com.bytedance.services.apm.api.b.ensureNotReachHere(th, str);
                f11366a = true;
            } catch (Throwable unused) {
                f11366a = false;
            }
        }
    }

    public static void a(String str) {
        Boolean bool = f11366a;
        if (bool == null || bool.booleanValue()) {
            try {
                com.bytedance.services.apm.api.b.ensureNotReachHere(str);
                f11366a = true;
            } catch (Throwable unused) {
                f11366a = false;
            }
        }
    }
}
