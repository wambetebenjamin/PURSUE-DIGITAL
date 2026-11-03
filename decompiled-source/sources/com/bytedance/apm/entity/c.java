package com.bytedance.apm.entity;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: CpuMonitorItem.java */
/* loaded from: classes.dex */
public class c {
    public long firstMonitorTime;
    public double maxCpuRate;
    public double maxStatSpeed;
    public double totalCpuRate;
    public double totalStatSpeed;
    public long totalTimes = 1;

    public c(long j2, double d2, double d3, double d4, double d5) {
        this.firstMonitorTime = j2;
        this.maxCpuRate = d2;
        this.totalCpuRate = d3;
        this.maxStatSpeed = d4;
        this.totalStatSpeed = d5;
    }

    public void reset() {
        this.firstMonitorTime = 0L;
        this.maxCpuRate = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.totalTimes = 0L;
        this.totalCpuRate = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.maxStatSpeed = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.totalStatSpeed = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }
}
