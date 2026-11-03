package com.bytedance.framwork.core.sdklib.apm6;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
/* compiled from: LogReportController.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8624a = "/monitor/collect/batch/";

    /* renamed from: b  reason: collision with root package name */
    private static final int f8625b = 15;

    /* renamed from: c  reason: collision with root package name */
    private static final int f8626c = 1000;

    /* renamed from: d  reason: collision with root package name */
    private static final int f8627d = 60000;

    /* renamed from: e  reason: collision with root package name */
    private static final int f8628e = 300000;

    /* renamed from: f  reason: collision with root package name */
    private static final int f8629f = 900000;

    /* renamed from: g  reason: collision with root package name */
    private static final int f8630g = 1800000;

    /* renamed from: h  reason: collision with root package name */
    private static final int f8631h = 30000;

    /* renamed from: i  reason: collision with root package name */
    private static final int f8632i = 60000;

    /* renamed from: j  reason: collision with root package name */
    private static final int f8633j = 120000;
    private static final int k = 240000;
    private static final int l = 300000;
    private static final long m = 120000;
    private static final long n = 300000;
    private static final long o = 600000;
    private static final long p = 1800000;
    private static final long q = 3600000;
    private static final long[] r = {120000, 300000, 600000, 1800000, 3600000};
    private AtomicLong A;
    private final ConcurrentHashMap<String, Boolean> B;
    private volatile boolean C;
    private volatile boolean D;
    private volatile int s;
    private volatile int t;
    private volatile int u;
    private volatile int v;
    private volatile int w;
    private List<String> x;
    private volatile boolean y;
    private AtomicLong z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public long d() {
        return 5L;
    }

    private e() {
        this.u = 0;
        this.y = true;
        this.B = new ConcurrentHashMap<>();
        this.C = true;
        this.z = new AtomicLong(0L);
        this.A = new AtomicLong();
    }

    public static e a() {
        return a.f8634a;
    }

    public void a(String str, List<String> list) {
        if (com.bytedance.apm6.util.g.isEmpty(list) || !com.bytedance.apm6.util.g.isEmpty(this.x)) {
            return;
        }
        this.x = a(list);
    }

    public void a(String str, boolean z) {
        boolean z2;
        this.B.put(str, Boolean.valueOf(z));
        Iterator<Boolean> it = this.B.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            } else if (it.next().booleanValue()) {
                z2 = true;
                break;
            }
        }
        this.C = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.s == 0) {
            this.s = 1;
            this.t = 300000;
        } else if (this.s == 1) {
            this.s = 2;
            this.t = f8629f;
        } else if (this.s == 2) {
            this.s = 3;
            this.t = 1800000;
        } else {
            this.s = 4;
            this.t = 1800000;
        }
        if (l.e()) {
            com.bytedance.framwork.core.sdklib.apm6.safety.d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "longBackOff:" + this.t + " netFailCount:" + this.s);
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        if (this.u == 0) {
            this.u = 1;
            this.v = 30000;
        } else if (this.u == 1) {
            this.u = 2;
            this.v = 60000;
        } else if (this.u == 2) {
            this.u = 3;
            this.v = f8633j;
        } else if (this.u == 3) {
            this.u = 4;
            this.v = k;
        } else {
            this.u = 5;
            this.v = 300000;
        }
        if (l.e()) {
            com.bytedance.framwork.core.sdklib.apm6.safety.d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "shortStopInterval:" + this.v + " shortFailCount:" + this.u);
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a(int i2) {
        int i3 = i2 - 1;
        if (i3 < 0) {
            return 0L;
        }
        long[] jArr = r;
        if (i3 >= jArr.length) {
            return jArr[jArr.length - 1];
        }
        return jArr[i3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.y = true;
        this.D = false;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.A.set(0L);
        this.z.set(0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return this.y || System.currentTimeMillis() - this.z.get() > m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        b();
        this.D = true;
        this.A.set(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        this.D = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i() {
        if (this.D) {
            return System.currentTimeMillis() - this.A.get() <= m();
        }
        return this.D;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.w = (int) (j2 * 1000);
        l();
    }

    private void l() {
        this.y = false;
        this.z.set(System.currentTimeMillis());
    }

    private long m() {
        long j2 = this.t > this.v ? this.t : this.v;
        return j2 > ((long) this.w) ? j2 : this.w;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> j() {
        if (com.bytedance.apm6.util.g.isEmpty(this.x)) {
            return com.bytedance.apm6.util.constant.d.DEFAULT_LOG_REPORT;
        }
        return this.x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return this.C;
    }

    private static List<String> a(List<String> list) {
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            try {
                linkedList.add(("https://" + new URL(it.next()).getHost()) + "/monitor/collect/batch/");
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
        return linkedList;
    }

    /* compiled from: LogReportController.java */
    /* loaded from: classes2.dex */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        static e f8634a = new e();

        a() {
        }
    }
}
