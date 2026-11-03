package com.bytedance.apm.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.apm.constant.o;
/* compiled from: FunctionSwitcher.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f1133a = false;

    /* renamed from: b  reason: collision with root package name */
    private static SharedPreferences f1134b;

    /* renamed from: c  reason: collision with root package name */
    private static int f1135c;

    /* renamed from: d  reason: collision with root package name */
    private static int f1136d;

    /* renamed from: e  reason: collision with root package name */
    private static long f1137e;

    public static synchronized void init(Context context) {
        synchronized (a.class) {
            if (f1133a) {
                return;
            }
            f1133a = true;
            if (f1134b == null) {
                f1134b = context.getSharedPreferences(o.MONITOR_SWITCH_CONFIG, 0);
            }
            SharedPreferences sharedPreferences = f1134b;
            if (sharedPreferences != null) {
                int i2 = sharedPreferences.getInt(o.MONITOR_SWITCH_CONFIG_FIRST_FLAG, 0);
                f1135c = i2;
                f1136d = i2;
                f1137e = f1134b.getLong(o.MONITOR_SWITCH_CONFIG_ATRACE_FLAG, 0L);
            }
        }
    }

    public static void updateSwitch(int i2, boolean z) {
        if (z) {
            f1136d = i2 | f1136d;
            return;
        }
        f1136d = (~i2) & f1136d;
    }

    public static void updateGpu(long j2, long j3) {
        SharedPreferences sharedPreferences = f1134b;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(o.MONITOR_SWITCH_GPU_LOAD, j2).putLong(o.MONITOR_SWITCH_GPU_WINDOW, j3).apply();
        }
    }

    public static long getGpuLoadInterval() {
        SharedPreferences sharedPreferences = f1134b;
        if (sharedPreferences == null) {
            return 0L;
        }
        return sharedPreferences.getLong(o.MONITOR_SWITCH_GPU_LOAD, 0L);
    }

    public static long getGpuLoadWindows() {
        SharedPreferences sharedPreferences = f1134b;
        if (sharedPreferences == null) {
            return 0L;
        }
        return sharedPreferences.getLong(o.MONITOR_SWITCH_GPU_WINDOW, 1000L);
    }

    public static void updateSwitch(int i2, int i3) {
        f1136d = (i3 & i2) + ((~i2) & f1136d);
    }

    public static void updateAtraceFlags(long j2) {
        f1137e = j2;
    }

    public static void saveToSp() {
        SharedPreferences sharedPreferences = f1134b;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(o.MONITOR_SWITCH_CONFIG_FIRST_FLAG, f1136d).putLong(o.MONITOR_SWITCH_CONFIG_ATRACE_FLAG, f1137e).apply();
        }
    }

    public static boolean getSwitch(int i2) {
        return (i2 & f1135c) != 0;
    }

    public static long getAtraceFlags() {
        return f1137e;
    }

    public static int getMonitorRunMode() {
        return (f1135c & o.MONITOR_RUN_MODE_MASK) >>> 29;
    }
}
