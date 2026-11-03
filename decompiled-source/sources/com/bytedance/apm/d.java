package com.bytedance.apm;

import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: ApmDebugger.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static AtomicInteger f968a = new AtomicInteger(0);

    /* renamed from: b  reason: collision with root package name */
    private static volatile int f969b = 120;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f970c = false;

    /* renamed from: d  reason: collision with root package name */
    private static volatile long f971d = 100000;

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f972e = false;

    /* renamed from: f  reason: collision with root package name */
    private static volatile long f973f = 100000;

    /* renamed from: g  reason: collision with root package name */
    private static volatile boolean f974g = false;

    /* renamed from: h  reason: collision with root package name */
    private static volatile int f975h = 1000;

    /* renamed from: i  reason: collision with root package name */
    private static volatile boolean f976i = false;

    public static void forceReportBattery() {
    }

    public static void setAsyncTimer(long j2) {
    }

    public static void setBatteryTimer(long j2) {
    }

    public static boolean shouldForceReportBattery() {
        return false;
    }

    public static void endForceReportBattery() {
        f968a.decrementAndGet();
    }

    public static void setReportInterval(int i2) {
        f969b = i2;
        f970c = true;
    }

    public static boolean hasReportInterval() {
        return f970c;
    }

    public static int getReportInterval() {
        return f969b;
    }

    public static void setCpuInterval(long j2) {
        f971d = j2;
        f972e = true;
    }

    public static boolean hasCpuInterval() {
        return f972e;
    }

    public static long getCpuInterval() {
        return f971d;
    }

    public static void setMemInterval(long j2) {
        f973f = j2;
        f974g = true;
    }

    public static boolean hasMemInterval() {
        return f974g;
    }

    public static long getMemInterval() {
        return f973f;
    }
}
