package com.bytedance.apm6.util.timetask;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
/* compiled from: SingleThreadFactory.java */
/* loaded from: classes.dex */
public class e implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    private final String f2424a;

    public e(String str) {
        this.f2424a = "APM6-" + str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        if (com.bytedance.apm6.util.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(d.TAG, "creating newThread " + this.f2424a);
        }
        return new Thread(new Runnable() { // from class: com.bytedance.apm6.util.timetask.e.1
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                try {
                    runnable.run();
                } catch (Throwable th) {
                    com.bytedance.apm6.util.log.b.e(d.TAG, "SingleThreadFactory error when running in thread " + e.this.f2424a, th);
                }
            }
        }, this.f2424a);
    }
}
