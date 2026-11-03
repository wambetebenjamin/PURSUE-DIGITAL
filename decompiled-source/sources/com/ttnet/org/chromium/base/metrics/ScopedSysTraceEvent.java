package com.ttnet.org.chromium.base.metrics;

import android.os.Trace;
/* loaded from: classes9.dex */
public class ScopedSysTraceEvent implements AutoCloseable {
    public static ScopedSysTraceEvent scoped(String str) {
        return new ScopedSysTraceEvent(str);
    }

    private ScopedSysTraceEvent(String str) {
        Trace.beginSection(str);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Trace.endSection();
    }
}
