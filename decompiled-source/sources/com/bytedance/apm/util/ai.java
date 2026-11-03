package com.bytedance.apm.util;

import android.os.Looper;
/* compiled from: ThreadUtils.java */
/* loaded from: classes.dex */
public class ai {
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void sleepSafely(long j2) {
        try {
            Thread.sleep(j2);
        } catch (InterruptedException unused) {
        }
    }
}
