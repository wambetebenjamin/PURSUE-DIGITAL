package com.bytedance.praisedialoglib.ThreadPool;

import java.util.concurrent.ExecutorService;
/* compiled from: ThreadPlus.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: b  reason: collision with root package name */
    private static ExecutorService f11109b = a.getNormalExecutor();

    public static void submitRunnable(Runnable runnable) {
        if (runnable != null) {
            f11109b.submit(runnable);
        }
    }
}
