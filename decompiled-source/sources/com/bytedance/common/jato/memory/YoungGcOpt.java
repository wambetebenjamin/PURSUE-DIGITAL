package com.bytedance.common.jato.memory;
/* loaded from: classes.dex */
public class YoungGcOpt {
    public static native boolean doYoungGcOpt(double d2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }
}
