package com.bytedance.apm.perf.traffic;

import android.os.Process;
import android.text.TextUtils;
import com.bytedance.apm.util.m;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: OldTrafficStatisticsImpl.java */
/* loaded from: classes.dex */
public class f implements c {

    /* renamed from: a  reason: collision with root package name */
    private File f1398a;

    /* renamed from: b  reason: collision with root package name */
    private List<String> f1399b = new CopyOnWriteArrayList();

    /* renamed from: c  reason: collision with root package name */
    private long f1400c = -1;

    /* renamed from: d  reason: collision with root package name */
    private volatile long f1401d = 0;

    /* renamed from: e  reason: collision with root package name */
    private volatile long f1402e = 0;

    /* renamed from: f  reason: collision with root package name */
    private volatile long f1403f = 0;

    /* renamed from: g  reason: collision with root package name */
    private volatile long f1404g = 0;

    /* renamed from: h  reason: collision with root package name */
    private volatile long f1405h = 0;

    /* renamed from: i  reason: collision with root package name */
    private volatile long f1406i = 0;

    /* renamed from: j  reason: collision with root package name */
    private volatile long f1407j = 0;
    private volatile long k = 0;

    @Override // com.bytedance.apm.perf.traffic.c
    public void onStatusChange(boolean z) {
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public void init() {
        this.f1398a = new File("/proc/net/xt_qtaguid/stats");
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getMobileBytes() {
        return getMobileBackBytes() + getMobileFrontBytes();
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getWifiBytes() {
        return getWifiBackBytes() + getWifiFrontBytes();
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getBackBytes() {
        return getMobileBackBytes() + getWifiBackBytes();
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long b() {
        d();
        return this.f1406i + this.f1405h;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getFrontBytes() {
        return getMobileFrontBytes() + getWifiFrontBytes();
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long c() {
        d();
        return this.k + this.f1407j;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getMobileFrontBytes() {
        d();
        return this.f1404g;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getMobileBackBytes() {
        d();
        return this.f1402e;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getWifiFrontBytes() {
        d();
        return this.f1403f;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getWifiBackBytes() {
        d();
        return this.f1401d;
    }

    @Override // com.bytedance.apm.perf.traffic.c
    public long getTotalBytes() {
        return getMobileBytes() + getWifiBytes();
    }

    private void d() {
        int i2;
        long j2;
        long j3;
        f fVar = this;
        if (System.currentTimeMillis() - fVar.f1400c < 1000) {
            return;
        }
        fVar.f1400c = System.currentTimeMillis();
        int myUid = Process.myUid();
        a();
        if (m.isEmpty(fVar.f1399b)) {
            return;
        }
        Iterator<String> it = fVar.f1399b.iterator();
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        long j10 = 0;
        long j11 = 0;
        while (it.hasNext()) {
            Iterator<String> it2 = it;
            long j12 = j10;
            String[] split = it.next().split(" ");
            try {
            } catch (Exception unused) {
                i2 = myUid;
                j2 = j5;
            }
            if (TextUtils.equals(split[3], "uid_tag_int")) {
                fVar = this;
                it = it2;
                j10 = j12;
            } else {
                if (myUid == Integer.parseInt(split[3])) {
                    long parseLong = Long.parseLong(split[5]);
                    long parseLong2 = Long.parseLong(split[6]);
                    long parseLong3 = Long.parseLong(split[7]);
                    long parseLong4 = Long.parseLong(split[8]);
                    i2 = myUid;
                    if (Long.valueOf(split[4]).longValue() == 1) {
                        j2 = j5;
                        try {
                            if (split[1].startsWith("rmnet_data")) {
                                j8 += parseLong3 + parseLong;
                                j9 += parseLong4 + parseLong2;
                            } else if (split[1].startsWith("wlan")) {
                                j7 += parseLong3 + parseLong;
                                j4 += parseLong4 + parseLong2;
                            }
                        } catch (Exception unused2) {
                        }
                    } else {
                        j2 = j5;
                    }
                    if (Long.valueOf(split[4]).longValue() == 0) {
                        if (split[1].startsWith("rmnet_data")) {
                            j6 += parseLong3 + parseLong;
                            j10 = j12 + parseLong4 + parseLong2;
                            j5 = j2;
                            fVar = this;
                            it = it2;
                            myUid = i2;
                        } else {
                            j3 = j12;
                            if (split[1].startsWith("wlan")) {
                                j11 += parseLong4 + parseLong2;
                                j10 = j3;
                                j5 = j2 + parseLong3 + parseLong;
                                fVar = this;
                                it = it2;
                                myUid = i2;
                            }
                            j10 = j3;
                            j5 = j2;
                            fVar = this;
                            it = it2;
                            myUid = i2;
                        }
                    }
                    j3 = j12;
                    j10 = j3;
                    j5 = j2;
                    fVar = this;
                    it = it2;
                    myUid = i2;
                } else {
                    i2 = myUid;
                    j2 = j5;
                }
                j3 = j12;
                j10 = j3;
                j5 = j2;
                fVar = this;
                it = it2;
                myUid = i2;
            }
        }
        fVar.f1401d = j5;
        fVar.f1402e = j6;
        fVar.f1403f = j7;
        fVar.f1404g = j8;
        fVar.f1405h = j11;
        fVar.f1406i = j10;
        fVar.f1407j = j4;
        fVar.k = j9;
    }

    private void a() {
        BufferedReader bufferedReader;
        Throwable th;
        if (this.f1398a == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.f1398a), com.bytedance.platform.godzilla.common.c.CHARSET_UTF_8));
            int i2 = 1;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    if (i2 >= 0) {
                        arrayList.add(readLine);
                    }
                    i2++;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        th.printStackTrace();
                    } finally {
                        com.bytedance.monitor.util.c.safeClose(bufferedReader);
                    }
                }
            }
            this.f1399b.clear();
            this.f1399b.addAll(arrayList);
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }
}
