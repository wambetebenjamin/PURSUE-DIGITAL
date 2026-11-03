package com.bytedance.apm6.consumer.slardar.send;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
/* compiled from: LogReportController.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1861a = 15;

    /* renamed from: b  reason: collision with root package name */
    private static final int f1862b = 1000;

    /* renamed from: c  reason: collision with root package name */
    private static final int f1863c = 60000;

    /* renamed from: d  reason: collision with root package name */
    private static final int f1864d = 300000;

    /* renamed from: e  reason: collision with root package name */
    private static final int f1865e = 900000;

    /* renamed from: f  reason: collision with root package name */
    private static final int f1866f = 1800000;

    /* renamed from: g  reason: collision with root package name */
    private static final int f1867g = 30000;

    /* renamed from: h  reason: collision with root package name */
    private static final int f1868h = 60000;

    /* renamed from: i  reason: collision with root package name */
    private static final int f1869i = 120000;

    /* renamed from: j  reason: collision with root package name */
    private static final int f1870j = 240000;
    private static final int k = 300000;
    private static final long l = 120000;
    private static final long m = 300000;
    private static final long n = 600000;
    private static final long o = 1800000;
    private static final long p = 3600000;
    private static final long[] q = {120000, 300000, 600000, 1800000, 3600000};
    private AtomicLong A;
    private AtomicLong B;
    private com.bytedance.apm6.consumer.slardar.config.a C;
    private volatile boolean D;
    private volatile int r;
    private volatile int s;
    private volatile int t;
    private volatile int u;
    private volatile int v;
    private List<String> w;
    private List<String> x;
    private List<String> y;
    private volatile boolean z;

    private c() {
        this.t = 0;
        this.z = true;
        this.w = new ArrayList();
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.A = new AtomicLong(0L);
        this.B = new AtomicLong();
    }

    public static c getInstance() {
        return a.f1871a;
    }

    public void updateConfig(com.bytedance.apm6.consumer.slardar.config.a aVar) {
        this.C = aVar;
    }

    public void setDefaultReportUrlList(List<String> list) {
        if (com.bytedance.apm6.util.g.isEmpty(list)) {
            return;
        }
        this.w.clear();
        this.w.addAll(list);
    }

    public void setDefaultExceptionUrlList(List<String> list) {
        if (com.bytedance.apm6.util.g.isEmpty(list)) {
            return;
        }
        this.y.clear();
        this.y.addAll(list);
    }

    public void setTraceReportUrlList(List<String> list) {
        if (com.bytedance.apm6.util.g.isEmpty(list)) {
            return;
        }
        this.x.clear();
        this.x.addAll(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (this.r == 0) {
            this.r = 1;
            this.s = 300000;
        } else if (this.r == 1) {
            this.r = 2;
            this.s = f1865e;
        } else if (this.r == 2) {
            this.r = 3;
            this.s = 1800000;
        } else {
            this.r = 4;
            this.s = 1800000;
        }
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "longBackOff:" + this.s + " netFailCount:" + this.r);
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.t == 0) {
            this.t = 1;
            this.u = 30000;
        } else if (this.t == 1) {
            this.t = 2;
            this.u = 60000;
        } else if (this.t == 2) {
            this.t = 3;
            this.u = f1869i;
        } else if (this.t == 3) {
            this.t = 4;
            this.u = f1870j;
        } else {
            this.t = 5;
            this.u = 300000;
        }
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "shortStopInterval:" + this.u + " shortFailCount:" + this.t);
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a(int i2) {
        int i3 = i2 - 1;
        if (i3 < 0) {
            return 0L;
        }
        long[] jArr = q;
        if (i3 >= jArr.length) {
            return jArr[jArr.length - 1];
        }
        return jArr[i3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        this.z = true;
        this.D = false;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.B.set(0L);
        this.A.set(0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.z || System.currentTimeMillis() - this.A.get() > m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        a();
        this.D = true;
        this.B.set(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.D = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        if (this.D) {
            return System.currentTimeMillis() - this.B.get() <= m();
        }
        return this.D;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.v = (int) (j2 * 1000);
        l();
    }

    private void l() {
        this.z = false;
        this.A.set(System.currentTimeMillis());
    }

    private long m() {
        long j2 = this.s > this.u ? this.s : this.u;
        return j2 > ((long) this.v) ? j2 : this.v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> h() {
        com.bytedance.apm6.consumer.slardar.config.a aVar = this.C;
        if (aVar != null && !com.bytedance.apm6.util.g.isEmpty(aVar.getReportUrlList())) {
            return this.C.getReportUrlList();
        }
        return this.w;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> i() {
        com.bytedance.apm6.consumer.slardar.config.a aVar = this.C;
        if (aVar != null && !com.bytedance.apm6.util.g.isEmpty(aVar.getTraceReportUrl())) {
            return this.C.getTraceReportUrl();
        }
        return this.x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> j() {
        com.bytedance.apm6.consumer.slardar.config.a aVar = this.C;
        if (aVar != null && !com.bytedance.apm6.util.g.isEmpty(aVar.getExceptionUrl())) {
            return this.C.getExceptionUrl();
        }
        return this.y;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        com.bytedance.apm6.consumer.slardar.config.a aVar = this.C;
        if (aVar != null) {
            return aVar.isEncrypt();
        }
        return true;
    }

    /* compiled from: LogReportController.java */
    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        static c f1871a = new c();

        a() {
        }
    }
}
