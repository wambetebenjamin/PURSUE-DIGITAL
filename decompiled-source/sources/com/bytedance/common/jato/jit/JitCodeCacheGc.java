package com.bytedance.common.jato.jit;

import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class JitCodeCacheGc {
    public static native void disable();

    public static native void enable();

    public static native boolean isEnable();

    static {
        d.loadLibrary();
    }
}
