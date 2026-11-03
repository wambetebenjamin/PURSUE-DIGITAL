package com.bytedance.common.jato.boost;
/* loaded from: classes.dex */
public class PrePageFaultOpt4 {
    public static native boolean mlockCodeOdex(int i2, int i3);

    public static native void munlockCodeOdex();

    public static native void munlockInBackground();

    private static native boolean prePageFaultInner(int i2, int i3);

    public static native boolean reclaimCodeOdex(int i2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void prePageFault(int i2, int i3) {
        prePageFaultInner(i2, i3);
    }
}
