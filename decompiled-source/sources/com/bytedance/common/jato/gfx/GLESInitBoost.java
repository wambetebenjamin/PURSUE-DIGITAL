package com.bytedance.common.jato.gfx;
/* loaded from: classes.dex */
public class GLESInitBoost {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f4711a = false;

    static native int nativeBoost(boolean z);

    public static synchronized void a(boolean z) {
        synchronized (GLESInitBoost.class) {
            if (f4711a) {
                return;
            }
            f4711a = true;
            if (com.bytedance.common.jato.d.loadLibrary()) {
                compatible.standard.a.a();
                nativeBoost(z);
            }
        }
    }
}
