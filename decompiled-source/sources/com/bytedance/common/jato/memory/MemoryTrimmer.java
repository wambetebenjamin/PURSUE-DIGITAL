package com.bytedance.common.jato.memory;

import android.content.Context;
import android.os.Build;
/* loaded from: classes.dex */
public class MemoryTrimmer {
    private static native boolean nTrim(String str, float f2, boolean z, boolean z2, boolean z3, boolean z4);

    public static void a(Context context, float f2, boolean z, boolean z2, boolean z3, boolean z4) {
        if (context == null || Build.VERSION.SDK_INT < 23 || !com.bytedance.common.jato.d.loadLibrary()) {
            return;
        }
        try {
            nTrim(context.getPackageName(), f2, z, z2, z3, z4);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
