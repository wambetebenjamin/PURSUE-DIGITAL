package com.bytedance.common.jato.threads;

import com.bytedance.common.jato.d;
/* loaded from: classes2.dex */
public class ThreadSuspendTimeout {
    public static native void setSuspendTimeoutInSeconds(long j2);

    public static native void setSuspendTimeoutInSecondsSync(long j2);

    static {
        d.loadLibrary();
    }
}
