package com.bytedance.apm.perf.traffic;
/* compiled from: ITrafficStatistics.java */
/* loaded from: classes.dex */
public interface c {
    long b();

    long c();

    long getBackBytes();

    long getFrontBytes();

    long getMobileBackBytes();

    long getMobileBytes();

    long getMobileFrontBytes();

    long getTotalBytes();

    long getWifiBackBytes();

    long getWifiBytes();

    long getWifiFrontBytes();

    void init();

    void onStatusChange(boolean z);
}
