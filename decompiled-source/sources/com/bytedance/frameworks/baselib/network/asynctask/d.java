package com.bytedance.frameworks.baselib.network.asynctask;

import android.os.Process;
import com.bytedance.common.utility.Logger;
import java.util.concurrent.ThreadFactory;
/* compiled from: NetworkThreadFactory.java */
/* loaded from: classes2.dex */
public class d implements ThreadFactory {

    /* renamed from: b  reason: collision with root package name */
    private static final String f7418b = "d";

    /* renamed from: a  reason: collision with root package name */
    private final String f7419a;

    public d(String str) {
        this.f7419a = "ttnet-" + str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        return new Thread(new Runnable() { // from class: com.bytedance.frameworks.baselib.network.asynctask.d$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                d.this.a(runnable);
            }
        }, this.f7419a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Runnable runnable) {
        Process.setThreadPriority(10);
        try {
            runnable.run();
        } catch (Throwable th) {
            Logger.e(f7418b, "NetworkThreadFactory error when running in thread " + this.f7419a, th);
        }
    }
}
