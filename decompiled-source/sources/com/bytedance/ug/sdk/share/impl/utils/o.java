package com.bytedance.ug.sdk.share.impl.utils;

import android.os.Handler;
import android.os.Looper;
/* compiled from: UiThreadHelper.java */
/* loaded from: classes8.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private static o f16190a;

    /* renamed from: b  reason: collision with root package name */
    private final Handler f16191b = new Handler(Looper.getMainLooper());

    private o() {
    }

    public static o getInstance() {
        if (f16190a == null) {
            f16190a = new o();
        }
        return f16190a;
    }

    public void execute(Runnable runnable) {
        if (isHandlerThread()) {
            runnable.run();
        } else {
            this.f16191b.post(runnable);
        }
    }

    public boolean isHandlerThread() {
        return Thread.currentThread() == this.f16191b.getLooper().getThread();
    }
}
