package com.bytedance.geckox.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.Objects;
/* compiled from: UIHandler.java */
/* loaded from: classes2.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f9376a = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable) {
        Objects.requireNonNull(runnable);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            f9376a.post(runnable);
        }
    }
}
