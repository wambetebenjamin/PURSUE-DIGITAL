package com.facebook.common.time;
/* loaded from: classes8.dex */
public class AwakeTimeSinceBootClock implements MonotonicClock {
    private static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    private AwakeTimeSinceBootClock() {
    }

    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // com.facebook.common.time.MonotonicClock
    public long now() {
        return android.os.SystemClock.uptimeMillis();
    }
}
