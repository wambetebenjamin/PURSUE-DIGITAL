package com.bytedance.common.jato.scheduler;
/* compiled from: JatoVLinkerOpt.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f5011a = false;

    public static synchronized boolean a() {
        synchronized (b.class) {
            if (com.bytedance.common.jato.util.b.d()) {
                return false;
            }
            if (f5011a) {
                return true;
            }
            compatible.standard.a.b();
            try {
                System.loadLibrary("jatolinker");
                f5011a = true;
            } catch (Throwable unused) {
            }
            return f5011a;
        }
    }
}
