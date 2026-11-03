package com.bytedance.common.jato.memory;
/* loaded from: classes.dex */
public class MadviseFreeOpt {
    public static native boolean replaceDontNeedToFree();

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }
}
