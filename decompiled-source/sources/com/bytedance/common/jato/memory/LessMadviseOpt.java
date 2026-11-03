package com.bytedance.common.jato.memory;
/* loaded from: classes.dex */
public class LessMadviseOpt {
    private static native void nAddLessMadviseFileName(String str);

    private static native void nSetLessMadviseEnabled(boolean z);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void a(boolean z) {
        if (com.bytedance.common.jato.d.a()) {
            nSetLessMadviseEnabled(z);
        }
    }

    public static void a(String str) {
        if (com.bytedance.common.jato.d.a()) {
            nAddLessMadviseFileName(str);
        }
    }
}
