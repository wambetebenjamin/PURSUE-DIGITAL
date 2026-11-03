package com.bytedance.apm.battery.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: BatteryTypeInf.java */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface c {
    public static final String BATTERY_ALARM = "alarm";
    public static final String BATTERY_CPU_ACTIVE = "cpu_active_time";
    public static final String BATTERY_GROUND_RECORD = "ground_record";
    public static final String BATTERY_LOC_API = "location";
    public static final String BATTERY_NEW_LAUNCH = "new_launch";
    public static final String BATTERY_POWER_LOCK = "power";
    public static final String BATTERY_TRAFFIC = "traffic";

    /* renamed from: a  reason: collision with root package name */
    public static final String f586a = "traffic_packets";

    /* renamed from: b  reason: collision with root package name */
    public static final String f587b = "traffic_all_interface";

    /* renamed from: c  reason: collision with root package name */
    public static final String f588c = "battery_consume";
}
