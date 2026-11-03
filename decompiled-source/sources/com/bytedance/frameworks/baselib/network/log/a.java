package com.bytedance.frameworks.baselib.network.log;

import com.bytedance.frameworks.baselib.network.asynctask.b;
import com.bytedance.frameworks.baselib.network.asynctask.c;
import com.bytedance.retrofit2.AsyncLogReporter;
/* compiled from: NetworkLogReporter.java */
/* loaded from: classes2.dex */
public class a implements AsyncLogReporter {
    @Override // com.bytedance.retrofit2.AsyncLogReporter
    public void postDelayed(final Runnable runnable, long j2) {
        b.a(c.NETWORK).a(new com.bytedance.frameworks.baselib.network.asynctask.a(j2, 0L) { // from class: com.bytedance.frameworks.baselib.network.log.a.1
            @Override // java.lang.Runnable
            public void run() {
                runnable.run();
            }
        });
    }
}
