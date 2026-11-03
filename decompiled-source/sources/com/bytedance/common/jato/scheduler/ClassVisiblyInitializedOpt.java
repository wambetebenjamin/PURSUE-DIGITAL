package com.bytedance.common.jato.scheduler;

import com.bytedance.common.jato.d;
/* loaded from: classes2.dex */
public class ClassVisiblyInitializedOpt {
    public static native void enable();

    public static native void setMax(int i2);

    public static native void setUseMembarrier(boolean z);

    static {
        d.loadLibrary();
    }
}
