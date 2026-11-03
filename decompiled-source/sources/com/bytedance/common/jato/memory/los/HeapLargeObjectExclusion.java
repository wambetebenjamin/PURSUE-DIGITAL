package com.bytedance.common.jato.memory.los;

import com.bytedance.common.jato.d;
/* loaded from: classes2.dex */
public class HeapLargeObjectExclusion {
    public static native long getLargeObjectAllocatedBytes();

    public static native boolean nativeExcludeLOS();

    public static native boolean nativeSetLargeObjectThreshold(int i2);

    public static native boolean nativeSetLargeObjectThresholdByByteSize(int i2);

    public static native void nativeUseNewFree();

    public static native void needGcBlock(boolean z);

    static {
        d.loadLibrary();
    }
}
