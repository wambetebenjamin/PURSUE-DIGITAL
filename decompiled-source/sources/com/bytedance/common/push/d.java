package com.bytedance.common.push;

import android.os.Looper;
import java.util.concurrent.Future;
/* compiled from: ThreadPlus.java */
/* loaded from: classes2.dex */
public class d {
    public static Future<?> a(Runnable runnable) {
        return com.bytedance.common.utility.concurrent.c.getIOThreadPool().submit(runnable);
    }

    public static void b(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a(runnable);
        } else {
            runnable.run();
        }
    }
}
