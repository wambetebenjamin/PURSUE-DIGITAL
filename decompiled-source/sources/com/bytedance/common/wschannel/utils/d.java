package com.bytedance.common.wschannel.utils;

import android.os.Build;
import android.os.SystemClock;
/* compiled from: TimeCompactUtil.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final long f6007a = 0;

    public static long a() {
        if (Build.VERSION.SDK_INT >= 17) {
            return SystemClock.elapsedRealtimeNanos();
        }
        return SystemClock.elapsedRealtime() * 1000;
    }
}
