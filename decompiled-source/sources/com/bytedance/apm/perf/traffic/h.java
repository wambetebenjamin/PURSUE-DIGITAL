package com.bytedance.apm.perf.traffic;

import android.os.Build;
/* compiled from: TrafficStatisticWrapper.java */
/* loaded from: classes.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private c f1422a;

    private h() {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f1422a = new e();
        } else {
            this.f1422a = new f();
        }
        this.f1422a.init();
    }

    public static h getInstance() {
        return a.f1423a;
    }

    public long getMobileBytes() {
        return this.f1422a.getMobileBytes();
    }

    public long getWifiBytes() {
        return this.f1422a.getWifiBytes();
    }

    public long getBackBytes() {
        return this.f1422a.getBackBytes();
    }

    public long a() {
        return this.f1422a.b();
    }

    public long getFrontBytes() {
        return this.f1422a.getFrontBytes();
    }

    public long b() {
        return this.f1422a.c();
    }

    public long getMobileFrontBytes() {
        return this.f1422a.getMobileFrontBytes();
    }

    public long getMobileBackBytes() {
        return this.f1422a.getMobileBackBytes();
    }

    public long getWifiFrontBytes() {
        return this.f1422a.getWifiFrontBytes();
    }

    public long getWifiBackBytes() {
        return this.f1422a.getWifiBackBytes();
    }

    public long getTotalBytes() {
        return this.f1422a.getTotalBytes();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f1422a.onStatusChange(z);
    }

    /* compiled from: TrafficStatisticWrapper.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static h f1423a = new h();

        private a() {
        }
    }
}
