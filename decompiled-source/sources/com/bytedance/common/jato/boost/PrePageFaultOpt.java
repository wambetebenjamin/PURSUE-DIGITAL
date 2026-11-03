package com.bytedance.common.jato.boost;
/* loaded from: classes.dex */
public class PrePageFaultOpt {
    public static native boolean mlockCodeItem(int i2);

    public static native void munlockInBackground();

    private static native boolean prePageFaultInner(int i2, int i3);

    public static native boolean reclaimCodeItem(int i2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void a(int i2, int i3) {
        prePageFaultInner(i2, i3);
    }
}
