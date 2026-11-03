package com.bytedance.apm6.memory;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.apm.constant.i;
import java.math.BigDecimal;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MemoryStatistics.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private long f2330a;

    /* renamed from: b  reason: collision with root package name */
    private long f2331b;

    /* renamed from: c  reason: collision with root package name */
    private long f2332c;

    /* renamed from: d  reason: collision with root package name */
    private long f2333d;

    e() {
    }

    public static e getInstance() {
        return a.f2334a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        b();
        c();
        d();
        e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.bytedance.apm6.memory.monitorable.a a(com.bytedance.apm6.memory.config.a aVar) {
        Debug.MemoryInfo memoryInfo = getMemoryInfo();
        if (memoryInfo == null) {
            return null;
        }
        long a2 = a(memoryInfo);
        if (a2 < 0) {
            return null;
        }
        long freeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        double a3 = a(freeMemory);
        return new com.bytedance.apm6.memory.monitorable.a(b(), c(), d(), e(), !((com.bytedance.apm6.service.lifecycle.a) com.bytedance.apm6.service.d.getService(com.bytedance.apm6.service.lifecycle.a.class)).isForeground(), memoryInfo.nativePss * 1024, 1024 * memoryInfo.getTotalPss(), freeMemory, a2, b(memoryInfo), f(), a3, a3 > aVar.memoryTopCheckThreshold(), aVar.isRealTimeMemEnable());
    }

    private long a(Debug.MemoryInfo memoryInfo) {
        return memoryInfo.dalvikPss * 1024;
    }

    private long b() {
        if (Build.VERSION.SDK_INT < 23) {
            return -1L;
        }
        long parseLong = Long.parseLong(Debug.getRuntimeStat(i.ART_GC_GC_COUNT));
        long j2 = parseLong - this.f2330a;
        this.f2330a = parseLong;
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", "gcCount:" + parseLong);
        }
        return j2;
    }

    private long c() {
        if (Build.VERSION.SDK_INT < 23) {
            return -1L;
        }
        long parseLong = Long.parseLong(Debug.getRuntimeStat(i.ART_GC_GC_TIME));
        long j2 = parseLong - this.f2331b;
        this.f2331b = parseLong;
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", "gcTime:" + this.f2331b);
        }
        return j2;
    }

    private long d() {
        if (Build.VERSION.SDK_INT < 23) {
            return -1L;
        }
        long parseLong = Long.parseLong(Debug.getRuntimeStat(i.ART_GC_BLOCKING_GC_COUNT));
        long j2 = parseLong - this.f2332c;
        this.f2332c = parseLong;
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", "blockingGcCount:" + this.f2332c);
        }
        return j2;
    }

    private long e() {
        if (Build.VERSION.SDK_INT < 23) {
            return -1L;
        }
        long parseLong = Long.parseLong(Debug.getRuntimeStat(i.ART_GC_BLOCKING_GC_TIME));
        long j2 = parseLong - this.f2333d;
        this.f2333d = parseLong;
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d("APM-Memory", "blockingGcTime:" + this.f2333d);
        }
        return j2;
    }

    private double a(long j2) {
        if (j2 > 0) {
            return new BigDecimal(j2).divide(new BigDecimal(Runtime.getRuntime().maxMemory()), 4, 4).doubleValue();
        }
        return -1.0d;
    }

    private long b(Debug.MemoryInfo memoryInfo) {
        if (Build.VERSION.SDK_INT < 23) {
            return -1L;
        }
        String memoryStat = memoryInfo.getMemoryStat(i.GRAPHICS_MEM);
        if (!TextUtils.isEmpty(memoryStat)) {
            try {
                return Integer.parseInt(memoryStat) * 1024;
            } catch (Exception e2) {
                com.bytedance.apm6.util.log.b.e("APM-Memory", "getGraphics", e2);
                e2.printStackTrace();
            }
        }
        return -1L;
    }

    private long f() {
        long a2 = f.a() * 1024;
        if (a2 > 0) {
            return a2;
        }
        return -1L;
    }

    public static Debug.MemoryInfo getMemoryInfo() {
        if (com.bytedance.apm6.foundation.context.a.getContext() == null) {
            return null;
        }
        try {
            ActivityManager activityManager = (ActivityManager) com.bytedance.apm6.foundation.context.a.getContext().getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            return activityManager.getProcessMemoryInfo(new int[]{Process.myPid()})[0];
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MemoryStatistics.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final e f2334a = new e();

        private a() {
        }
    }
}
