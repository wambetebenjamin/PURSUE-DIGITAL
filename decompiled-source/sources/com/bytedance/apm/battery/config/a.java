package com.bytedance.apm.battery.config;
/* compiled from: BatteryDetectConfig.java */
/* loaded from: classes.dex */
public class a {
    public static final String KEY_MAX_NORMAL_ALARM_INVOKE_COUNT_10_MINS = "max_normal_alarm_invoke_count";
    public static final String KEY_MAX_SINGLE_LOC_REQUEST_TIME = "max_single_loc_request_time_second";
    public static final String KEY_MAX_SINGLE_WAKE_LOCK_HOLD_TIME = "max_single_wake_lock_hold_time_second";
    public static final String KEY_MAX_TOTAL_LOC_REQUEST_COUNT_10_MINS = "max_total_loc_request_count";
    public static final String KEY_MAX_TOTAL_LOC_REQUEST_TIME_10_MINS = "max_total_loc_request_time_second";
    public static final String KEY_MAX_TOTAL_WAKE_LOCK_ACQUIRE_COUNT_10_MINS = "max_total_wake_lock_acquire_count";
    public static final String KEY_MAX_TOTAL_WAKE_LOCK_HOLD_TIME_10_MINS = "max_total_wake_lock_hold_time_second";
    public static final String KEY_MAX_WAKE_UP_ALARM_INVOKE_COUNT_10_MINS = "max_wake_up_alarm_invoke_count";
    public static final int MAX_NORMAL_ALARM_INVOKE_COUNT_10_MINS = 10;
    public static final long MAX_SINGLE_LOC_REQUEST_TIME_SECONDS = 120;
    public static final long MAX_SINGLE_WAKE_LOCK_HOLD_TIME_SECOND = 120;
    public static final int MAX_TOTAL_LOC_REQUEST_COUNT_10_MINS = 5;
    public static final int MAX_TOTAL_LOC_REQUEST_TIME_10_MINS_SECOND = 240;
    public static final int MAX_TOTAL_WAKE_LOCK_ACQUIRE_COUNT_10_MINS = 5;
    public static final long MAX_TOTAL_WAKE_LOCK_HOLD_TIME_10_MINS_SECOND = 240;
    public static final int MAX_WAKE_UP_ALARM_INVOKE_COUNT_10_MINS = 5;

    /* renamed from: a  reason: collision with root package name */
    private static long f577a = 120000;

    /* renamed from: b  reason: collision with root package name */
    private static long f578b = 5;

    /* renamed from: c  reason: collision with root package name */
    private static long f579c = 240000;

    /* renamed from: d  reason: collision with root package name */
    private static int f580d = 5;

    /* renamed from: e  reason: collision with root package name */
    private static int f581e = 10;

    /* renamed from: f  reason: collision with root package name */
    private static long f582f = 120000;

    /* renamed from: g  reason: collision with root package name */
    private static int f583g = 5;

    /* renamed from: h  reason: collision with root package name */
    private static long f584h = 240000;

    public static long getMaxSingleWakeLockHoldTimeMs() {
        return f577a;
    }

    public static void setMaxSingleWakeLockHoldTimeMs(long j2) {
        f577a = j2;
    }

    public static long getMaxTotalWakeLockAcquireCount() {
        return f578b;
    }

    public static void setMaxTotalWakeLockAcquireCount(int i2) {
        f578b = i2;
    }

    public static long getMaxTotalWakeLockHoldTimeMs() {
        return f579c;
    }

    public static void setMaxTotalWakeLockHoldTimeMs(long j2) {
        f579c = j2;
    }

    public static int getMaxWakeUpAlarmInvokeCount() {
        return f580d;
    }

    public static void setMaxWakeUpAlarmInvokeCount(int i2) {
        f580d = i2;
    }

    public static int getMaxNormalAlarmInvokeCount() {
        return f581e;
    }

    public static void setMaxNormalAlarmInvokeCount(int i2) {
        f581e = i2;
    }

    public static long getMaxSingleLocRequestTimeMs() {
        return f582f;
    }

    public static void setMaxSingleLocRequestTimeMs(long j2) {
        f582f = j2;
    }

    public static int getMaxTotalLocRequestCount() {
        return f583g;
    }

    public static void setMaxTotalLocRequestCount(int i2) {
        f583g = i2;
    }

    public static long getMaxTotalLocRequestTimeMs() {
        return f584h;
    }

    public static void setMaxTotalLocRequestTimeMs(long j2) {
        f584h = j2;
    }
}
