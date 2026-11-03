package com.bytedance.common.wschannel.utils;

import android.os.Process;
import com.bytedance.common.utility.Logger;
import java.util.concurrent.ThreadFactory;
/* compiled from: WsThreadFactory.java */
/* loaded from: classes2.dex */
public class h implements ThreadFactory {

    /* renamed from: b  reason: collision with root package name */
    private static final String f6019b = "h";

    /* renamed from: a  reason: collision with root package name */
    private final String f6020a;

    public h(String str) {
        this.f6020a = "WsChannel-" + str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        if (Logger.debug()) {
            Logger.d(f6019b, "creating newThread " + this.f6020a);
        }
        return new Thread(new Runnable() { // from class: com.bytedance.common.wschannel.utils.h.1
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                try {
                    runnable.run();
                } catch (Throwable th) {
                    Logger.e(h.f6019b, "WsThreadFactory error when running in thread " + h.this.f6020a, th);
                }
            }
        }, this.f6020a);
    }
}
