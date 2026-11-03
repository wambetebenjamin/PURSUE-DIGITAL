package com.bytedance.apm6.util;

import android.os.Build;
import android.os.Trace;
/* compiled from: TraceUtils.java */
/* loaded from: classes.dex */
public class l {
    public static void beginSection(String str) {
        if (!a.isSystraceMode() || Build.VERSION.SDK_INT < 18) {
            return;
        }
        Trace.beginSection(str);
    }

    public static void endSection() {
        if (!a.isSystraceMode() || Build.VERSION.SDK_INT < 18) {
            return;
        }
        Trace.endSection();
    }
}
