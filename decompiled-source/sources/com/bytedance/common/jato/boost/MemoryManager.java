package com.bytedance.common.jato.boost;

import compatible.standard.a;
/* loaded from: classes.dex */
public class MemoryManager {
    private static native void nativePagePreFault(int i2, int i3, boolean z, boolean z2);

    public static void pagePreFault(int i2, int i3, boolean z, boolean z2) {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            a.a();
            nativePagePreFault(i2, i3, z, z2);
        }
    }
}
