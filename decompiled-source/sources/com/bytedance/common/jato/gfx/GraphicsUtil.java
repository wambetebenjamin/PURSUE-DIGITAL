package com.bytedance.common.jato.gfx;
/* loaded from: classes.dex */
public class GraphicsUtil {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f4712a = false;

    private static native void nativeEnableResourceCleanup();

    public static void a() {
        synchronized (GraphicsUtil.class) {
            if (f4712a) {
                return;
            }
            f4712a = true;
            if (com.bytedance.common.jato.d.loadLibrary()) {
                compatible.standard.a.b();
                nativeEnableResourceCleanup();
            }
        }
    }
}
