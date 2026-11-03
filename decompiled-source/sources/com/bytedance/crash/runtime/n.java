package com.bytedance.crash.runtime;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
/* compiled from: NpthHandlerThread.java */
@Deprecated
/* loaded from: classes2.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7066a = "default_npth_thread";

    /* renamed from: b  reason: collision with root package name */
    private static volatile s f7067b;

    /* renamed from: c  reason: collision with root package name */
    private static volatile Handler f7068c;

    public static void stopOtherTask() {
    }

    public static HandlerThread getDefaultHandlerThread() {
        if (f7067b == null) {
            synchronized (n.class) {
                if (f7067b == null) {
                    f7067b = new s(f7066a);
                    f7067b.start();
                }
            }
        }
        return f7067b.getThread();
    }

    public static s getDefaultHandler() {
        if (f7067b == null) {
            getDefaultHandlerThread();
        }
        return f7067b;
    }

    public static Handler getMainThreadHandler() {
        if (f7068c == null) {
            f7068c = new Handler(Looper.getMainLooper());
        }
        return f7068c;
    }
}
