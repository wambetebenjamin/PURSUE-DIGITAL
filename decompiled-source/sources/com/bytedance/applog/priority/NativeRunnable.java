package com.bytedance.applog.priority;

import com.bytedance.applog.log.o;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class NativeRunnable implements Runnable {
    private static final List<String> loggerTags = Collections.singletonList("PriorityNativeRunnable");
    private final long nativeTaskPtr;

    public native void nativeRun(long j2);

    public NativeRunnable(long j2) {
        this.nativeTaskPtr = j2;
    }

    @Override // java.lang.Runnable
    public void run() {
        o.a().b(loggerTags, "run with native ptr: " + this.nativeTaskPtr, new Object[0]);
        nativeRun(this.nativeTaskPtr);
    }
}
