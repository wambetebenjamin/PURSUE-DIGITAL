package com.bytedance.frameworks.baselib.network.http.util;

import android.os.Process;
import com.bytedance.common.utility.Logger;
import java.util.concurrent.ThreadFactory;
/* compiled from: ThreadFactory.java */
/* loaded from: classes2.dex */
public class s implements ThreadFactory {

    /* renamed from: b  reason: collision with root package name */
    private static final String f8376b = "s";

    /* renamed from: a  reason: collision with root package name */
    private final String f8377a;

    public s(String str) {
        this.f8377a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        if (Logger.debug()) {
            Logger.d(f8376b, "creating newThread " + this.f8377a);
        }
        return new Thread(new Runnable() { // from class: com.bytedance.frameworks.baselib.network.http.util.s.1
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                try {
                    runnable.run();
                } catch (Throwable th) {
                    Logger.e(s.f8376b, "WsThreadFactory error when running in thread " + s.this.f8377a, th);
                }
            }
        }, this.f8377a);
    }
}
