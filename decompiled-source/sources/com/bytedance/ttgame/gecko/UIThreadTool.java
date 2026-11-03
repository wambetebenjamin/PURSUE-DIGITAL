package com.bytedance.ttgame.gecko;

import android.os.Handler;
import android.os.Looper;
import java.util.Objects;
/* loaded from: classes3.dex */
public class UIThreadTool {
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void runOnUIThread(Runnable runnable) {
        Objects.requireNonNull(runnable);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            sHandler.post(runnable);
        }
    }
}
