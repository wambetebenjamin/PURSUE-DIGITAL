package com.google.android.gms.common.util;

import android.os.SystemClock;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public interface Clock {
    default long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    long currentTimeMillis();

    long elapsedRealtime();

    long nanoTime();
}
