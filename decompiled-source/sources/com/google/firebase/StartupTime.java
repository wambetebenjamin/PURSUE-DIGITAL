package com.google.firebase;

import android.os.SystemClock;
/* loaded from: classes9.dex */
public abstract class StartupTime {
    public abstract long getElapsedRealtime();

    public abstract long getEpochMillis();

    public abstract long getUptimeMillis();

    public static StartupTime create(long j2, long j3, long j4) {
        return new AutoValue_StartupTime(j2, j3, j4);
    }

    public static StartupTime now() {
        return create(System.currentTimeMillis(), SystemClock.elapsedRealtime(), SystemClock.uptimeMillis());
    }
}
