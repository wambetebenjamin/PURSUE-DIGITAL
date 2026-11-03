package com.bytedance.applog.util;

import android.os.SystemClock;
/* loaded from: classes.dex */
public class BlockHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final long f3544a = 10000;

    /* renamed from: b  reason: collision with root package name */
    private static long f3545b = 100;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f3546c = false;

    /* renamed from: d  reason: collision with root package name */
    private static volatile long f3547d = -1;

    public static void setBlockInterval(long j2) {
        f3545b = Math.max(j2, 1L);
    }

    public static long getBlockInterval() {
        return f3545b;
    }

    public static void beginBlock() {
        f3546c = true;
        f3547d = SystemClock.elapsedRealtime();
    }

    public static void endBlock() {
        f3546c = false;
        f3547d = -1L;
    }

    public static void tryBlock() {
        while (f3546c) {
            a();
        }
    }

    private static void a() {
        if (f3546c) {
            try {
                if (Math.abs(SystemClock.elapsedRealtime() - f3547d) < f3544a) {
                    Thread.sleep(f3545b);
                } else {
                    endBlock();
                }
            } catch (InterruptedException e2) {
                com.bytedance.applog.log.o.a().a("BlockHelper: block interrupted!", e2, new Object[0]);
            }
        }
    }
}
