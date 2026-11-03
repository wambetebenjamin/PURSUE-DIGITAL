package com.bytedance.common.jato.memory;
/* loaded from: classes.dex */
public final class HeapGcParamsOpt {
    private static native int nativeAdjustHeapGcParameters(long j2, long j3, double d2);

    private HeapGcParamsOpt() {
    }

    public static boolean a(long j2, long j3, double d2) {
        return com.bytedance.common.jato.d.loadLibrary() && nativeAdjustHeapGcParameters(j2, j3, d2) != 0;
    }
}
