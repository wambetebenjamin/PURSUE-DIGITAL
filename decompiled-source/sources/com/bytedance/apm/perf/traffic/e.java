package com.bytedance.apm.perf.traffic;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
/* compiled from: NewTrafficStatisticsImpl.java */
/* loaded from: classes.dex */
public class e implements c {

    /* renamed from: b  reason: collision with root package name */
    private static final long f1385b = 4611686018427387903L;
    private static final long l = 1000;
    private static final String n = "NewTrafficStatisticsImp";

    /* renamed from: i  reason: collision with root package name */
    private NetworkStatsManager f1393i;

    /* renamed from: j  reason: collision with root package name */
    private long[] f1394j;
    private long[] k;

    /* renamed from: a  reason: collision with root package name */
    private boolean f1386a = false;

    /* renamed from: c  reason: collision with root package name */
    private volatile long f1387c = 0;

    /* renamed from: d  reason: collision with root package name */
    private volatile long f1388d = 0;

    /* renamed from: e  reason: collision with root package name */
    private volatile long f1389e = 0;

    /* renamed from: f  reason: collision with root package name */
    private volatile long f1390f = 0;
    private volatile long m = 0;
    private volatile long p = 0;
    private volatile long q = 0;
    private volatile long r = 0;

    /* renamed from: g  reason: collision with root package name */
    private long f1391g = -1;

    /* renamed from: h  reason: collision with root package name */
    private volatile boolean f1392h = true;
    private int o = -1;

    @Override // com.bytedance.apm.perf.traffic.c
    public void init() {
        if (com.bytedance.apm.thread.b.getInstance().inWorkThread()) {
            d();
        } else {
            com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.perf.traffic.e.1
                @Override // java.lang.Runnable
                public void run() {
                    e.this.d();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.f1386a) {
            return;
        }
        this.f1386a = true;
        this.f1391g = SystemClock.elapsedRealtime();
        this.f1394j = a(0L, 4611686018427387903L, 1);
        this.k = a(0L, 4611686018427387903L, 0);
        if (com.bytedance.apm.c.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(n, "initTrafficData: mTotalWifiBytes:" + this.f1394j[0] + " mTotalWifiPackets:" + this.f1394j[1] + " mTotalMobileBytes:" + this.k[0] + " mTotalMobilePackets:" + this.k[1]);
        }
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getMobileBytes() {
        a();
        return this.f1388d + this.f1390f;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getWifiBytes() {
        a();
        return this.f1387c + this.f1389e;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getBackBytes() {
        a();
        return this.f1388d + this.f1387c;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long b() {
        a();
        return this.p + this.m;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getFrontBytes() {
        a();
        return this.f1390f + this.f1389e;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long c() {
        a();
        return this.r + this.q;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getMobileFrontBytes() {
        a();
        return this.f1390f;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getMobileBackBytes() {
        a();
        return this.f1388d;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getWifiFrontBytes() {
        a();
        return this.f1389e;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getWifiBackBytes() {
        a();
        return this.f1387c;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getTotalBytes() {
        return getMobileBytes() + getWifiBytes();
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public void onStatusChange(final boolean z) {
        com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.perf.traffic.e.2
            @Override // java.lang.Runnable
            public void run() {
                e.this.a();
                e.this.f1392h = !z;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        long j2;
        e eVar;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j3 = this.f1391g;
        if (elapsedRealtime - j3 < 1000 || j3 == -1) {
            return;
        }
        long[] a2 = a(0L, 4611686018427387903L, 1);
        long[] a3 = a(0L, 4611686018427387903L, 0);
        long j4 = a3[0];
        long[] jArr = this.k;
        long j5 = j4 - jArr[0];
        long j6 = a3[1] - jArr[1];
        this.k = a3;
        long j7 = a2[0];
        long[] jArr2 = this.f1394j;
        long j8 = j7 - jArr2[0];
        long j9 = a2[1] - jArr2[1];
        this.f1394j = a2;
        if (com.bytedance.apm.c.isDebugMode()) {
            eVar = this;
            j2 = j5;
            com.bytedance.apm6.util.log.b.d(n, "mTotalWifiBytes:" + this.f1394j[0] + " mTotalWifiPackets:" + eVar.f1394j[1] + " mTotalMobileBytes:" + eVar.k[0] + " mTotalMobilePackets:" + eVar.k[1]);
        } else {
            j2 = j5;
            eVar = this;
        }
        if (eVar.f1392h) {
            eVar.f1390f += j2;
            eVar.r += j6;
            eVar.f1389e += j8;
            eVar.q += j9;
        } else {
            eVar.f1388d += j2;
            eVar.p += j6;
            eVar.f1387c += j8;
            eVar.m += j9;
        }
        if (com.bytedance.apm.c.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(n, "periodWifiBytes" + j8 + " periodMobileBytes:" + j2 + " mMobileBackBytes:" + eVar.f1388d + " mWifiBackBytes:" + eVar.f1387c);
        }
        eVar.f1391g = elapsedRealtime;
    }

    private long[] a(long j2, long j3, int i2) {
        Context context = com.bytedance.apm.c.getContext();
        if (this.f1393i == null) {
            this.f1393i = (NetworkStatsManager) context.getApplicationContext().getSystemService("netstats");
        }
        if (this.f1393i == null) {
            return new long[]{0, 0};
        }
        NetworkStats networkStats = null;
        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
        long j4 = 0;
        try {
            networkStats = this.f1393i.querySummary(i2, null, j2, j3);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        while (networkStats != null && networkStats.hasNextBucket()) {
            networkStats.getNextBucket(bucket);
            if (a(context) == bucket.getUid()) {
                j4 += bucket.getRxBytes();
                j5 += bucket.getTxBytes();
                j6 += bucket.getRxPackets();
                j7 += bucket.getTxPackets();
            }
        }
        if (networkStats != null) {
            networkStats.close();
        }
        return new long[]{j4 + j5, j6 + j7};
    }

    private int a(Context context) {
        if (this.o == -1) {
            try {
                PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 128);
                if (packageInfo != null) {
                    this.o = packageInfo.applicationInfo.uid;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return this.o;
    }
}
