package com.bytedance.monitor.collector;

import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
/* compiled from: Sysconf.java */
/* loaded from: classes2.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    protected static final long f9643a = 100;

    /* renamed from: b  reason: collision with root package name */
    private static long f9644b = -1;

    /* renamed from: c  reason: collision with root package name */
    private static long f9645c = -1;

    public static long getJiffyMills() {
        if (f9645c == -1) {
            f9645c = 1000 / getScClkTck();
        }
        return f9645c;
    }

    public static long getScClkTck() {
        return getScClkTck(100L);
    }

    public static long getScClkTck(long j2) {
        long a2;
        long j3 = f9644b;
        if (j3 > 0) {
            return j3;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            a2 = Os.sysconf(OsConstants._SC_CLK_TCK);
        } else {
            a2 = Build.VERSION.SDK_INT >= 14 ? a("_SC_CLK_TCK", j2) : j2;
        }
        if (a2 > 0) {
            j2 = a2;
        }
        f9644b = j2;
        return j2;
    }

    private static long a(String str, long j2) {
        try {
            int i2 = Class.forName("libcore.io.OsConstants").getField(str).getInt(null);
            Class<?> cls = Class.forName("libcore.io.Libcore");
            Class<?> cls2 = Class.forName("libcore.io.Os");
            return ((Long) cls2.getMethod("sysconf", Integer.TYPE).invoke(cls.getField("os").get(null), Integer.valueOf(i2))).longValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return j2;
        }
    }
}
