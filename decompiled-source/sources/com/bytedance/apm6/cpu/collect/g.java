package com.bytedance.apm6.cpu.collect;

import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.bytedance.librarian.c;
import com.bytedance.monitor.collector.o;
import java.io.File;
/* compiled from: ProcessCpuTracer.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2002a = 255;

    /* renamed from: b  reason: collision with root package name */
    public static final int f2003b = 0;

    /* renamed from: c  reason: collision with root package name */
    public static final int f2004c = 32;

    /* renamed from: d  reason: collision with root package name */
    public static final int f2005d = 9;

    /* renamed from: e  reason: collision with root package name */
    public static final int f2006e = 10;

    /* renamed from: f  reason: collision with root package name */
    public static final int f2007f = 256;

    /* renamed from: g  reason: collision with root package name */
    public static final int f2008g = 512;

    /* renamed from: h  reason: collision with root package name */
    public static final int f2009h = 1024;

    /* renamed from: i  reason: collision with root package name */
    public static final int f2010i = 2048;

    /* renamed from: j  reason: collision with root package name */
    public static final int f2011j = 4096;
    public static final int k = 8192;
    public static final int l = 16384;
    static final int m = 0;
    static final int n = 1;
    static final int o = 2;
    static final int p = 3;
    static final int q = 1;
    static final int r = 2;
    static final int s = 3;
    static final int t = 4;
    static final int u = 5;
    private static final String v = "ProcessCpuTracker";
    private static final boolean w = true;
    private static final boolean x = true;
    private final long E;
    private a F;
    private int H;
    private static final int[] y = {32, 544, 32, 32, 32, 32, 32, 32, 32, 8224, 32, 8224, 32, 8224, 8224};
    private static final int[] B = {32, 4640, 32, 32, 32, 32, 32, 32, 32, 8224, 32, 8224, 32, 8224, 8224, 32, 32, 32, 32, 32, 32, 32, 8224};
    private final long[] z = new long[4];
    private final long[] A = new long[4];
    private final String[] C = new String[6];
    private final long[] D = new long[6];
    private boolean G = true;

    /* compiled from: ProcessCpuTracer.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final int f2012a;

        /* renamed from: b  reason: collision with root package name */
        public final int f2013b;

        /* renamed from: c  reason: collision with root package name */
        final String f2014c;

        /* renamed from: d  reason: collision with root package name */
        public boolean f2015d;

        /* renamed from: e  reason: collision with root package name */
        public String f2016e;

        /* renamed from: f  reason: collision with root package name */
        public String f2017f;

        /* renamed from: g  reason: collision with root package name */
        public long f2018g;

        /* renamed from: h  reason: collision with root package name */
        public long f2019h;

        /* renamed from: i  reason: collision with root package name */
        public long f2020i;

        /* renamed from: j  reason: collision with root package name */
        public long f2021j;
        public long k;
        public int l;
        public int m;
        public long n;
        public long o;
        public int p;
        public int q;
        public boolean r;
        public boolean s;
        public boolean t;
        public boolean u;

        a(int i2) {
            this.f2012a = i2;
            File file = new File("/proc", Integer.toString(i2));
            this.f2013b = a(file.toString());
            this.f2014c = new File(file, "stat").toString();
        }

        private static int a(String str) {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    return Os.stat(str).st_uid;
                } catch (ErrnoException e2) {
                    Log.w(g.v, "Failed to stat(" + str + "): " + e2);
                }
            }
            return -1;
        }
    }

    public g() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.E = 1000 / Os.sysconf(OsConstants._SC_CLK_TCK);
        } else {
            this.E = 0L;
        }
    }

    public void a() {
        Log.v(v, "Init: " + this);
        this.G = true;
        this.H = Runtime.getRuntime().availableProcessors();
        b();
    }

    public void b() {
        Log.v(v, "Update: " + this);
        if (o.isSoLoaded()) {
            try {
                a(this.G);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.G = false;
        }
    }

    private void a(boolean z) {
        int myPid = Process.myPid();
        a aVar = this.F;
        if (aVar != null && aVar.f2012a == myPid) {
            this.F.t = false;
            this.F.s = false;
            Log.v(v, "Existing  pid " + myPid + ": " + this.F);
            if (this.F.f2015d) {
                long uptimeMillis = SystemClock.uptimeMillis();
                long[] jArr = this.z;
                if (o.getInstance().a(this.F.f2014c.toString(), y, null, jArr, null)) {
                    long j2 = jArr[0];
                    long j3 = jArr[1];
                    long j4 = jArr[2];
                    long j5 = this.E;
                    long j6 = j4 * j5;
                    long j7 = jArr[3] * j5;
                    if (j6 == this.F.f2021j && j7 == this.F.k) {
                        this.F.l = 0;
                        this.F.m = 0;
                        this.F.p = 0;
                        this.F.q = 0;
                        if (this.F.r) {
                            this.F.r = false;
                            return;
                        }
                        return;
                    }
                    if (!this.F.r) {
                        this.F.r = true;
                    }
                    Log.v("Load", "Stats changed " + this.F.f2017f + " pid=" + this.F.f2012a + " utime=" + j6 + "-" + this.F.f2021j + " stime=" + j7 + "-" + this.F.k + " minfaults=" + j2 + "-" + this.F.n + " majfaults=" + j3 + "-" + this.F.o);
                    a aVar2 = this.F;
                    aVar2.f2020i = uptimeMillis - aVar2.f2019h;
                    this.F.f2019h = uptimeMillis;
                    a aVar3 = this.F;
                    aVar3.l = (int) (j6 - aVar3.f2021j);
                    a aVar4 = this.F;
                    aVar4.m = (int) (j7 - aVar4.k);
                    this.F.f2021j = j6;
                    this.F.k = j7;
                    a aVar5 = this.F;
                    aVar5.p = (int) (j2 - aVar5.n);
                    a aVar6 = this.F;
                    aVar6.q = (int) (j3 - aVar6.o);
                    this.F.n = j2;
                    this.F.o = j3;
                    this.F.s = true;
                    return;
                }
                return;
            }
            return;
        }
        a aVar7 = this.F;
        if (aVar7 == null || aVar7.f2012a > myPid) {
            this.F = new a(myPid);
            Log.v(v, "New  pid " + myPid + ": " + this.F);
            String[] strArr = this.C;
            long[] jArr2 = this.D;
            this.F.f2019h = SystemClock.uptimeMillis();
            if (o.getInstance().a(this.F.f2014c.toString(), B, strArr, jArr2, null)) {
                this.F.f2018g = jArr2[5];
                this.F.f2015d = true;
                this.F.f2016e = strArr[0];
                this.F.n = jArr2[1];
                this.F.o = jArr2[2];
                this.F.f2021j = jArr2[3] * this.E;
                this.F.k = jArr2[4] * this.E;
            } else {
                Log.w(v, "Skipping unknown process pid " + myPid);
                this.F.f2016e = "<unknown>";
                a aVar8 = this.F;
                aVar8.k = 0L;
                aVar8.f2021j = 0L;
                a aVar9 = this.F;
                aVar9.o = 0L;
                aVar9.n = 0L;
            }
            Log.v("Load", "Stats added " + this.F.f2017f + " pid=" + this.F.f2012a + " utime=" + this.F.f2021j + " stime=" + this.F.k + " minfaults=" + this.F.n + " majfaults=" + this.F.o);
            this.F.l = 0;
            this.F.m = 0;
            this.F.p = 0;
            this.F.q = 0;
            this.F.t = true;
            if (z || !this.F.f2015d) {
                return;
            }
            this.F.s = true;
            return;
        }
        this.F.l = 0;
        this.F.m = 0;
        this.F.p = 0;
        this.F.q = 0;
        this.F.u = true;
        this.F.s = true;
        Log.v(v, "Removed  pid " + myPid + ": " + this.F);
        this.F.l = 0;
        this.F.m = 0;
        this.F.p = 0;
        this.F.q = 0;
        this.F.u = true;
        this.F.s = true;
    }

    public String c() {
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer, this.F.t ? " +" : this.F.u ? " -" : "  ", this.F.f2012a, this.F.f2016e, (int) this.F.f2020i, this.F.l, this.F.m, 0, 0, 0, this.F.p, this.F.q);
        return stringBuffer.toString();
    }

    public float d() {
        long j2;
        a aVar = this.F;
        if (aVar == null) {
            return 0.0f;
        }
        long j3 = aVar.l + this.F.m;
        if (((int) this.F.f2020i) <= 0) {
            return 0.0f;
        }
        return (float) (((j3 * 1000) / j2) / 10.0d);
    }

    public float e() {
        if (this.H <= 0) {
            return 0.0f;
        }
        return d() / this.H;
    }

    private void a(StringBuffer stringBuffer, long j2, long j3) {
        long j4 = (j2 * 1000) / j3;
        long j5 = j4 / 10;
        stringBuffer.append(j5);
        if (j5 < 10) {
            long j6 = j4 - (j5 * 10);
            if (j6 != 0) {
                stringBuffer.append('.');
                stringBuffer.append(j6);
            }
        }
    }

    private void a(StringBuffer stringBuffer, String str, int i2, String str2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        stringBuffer.append(str);
        long j2 = i3 == 0 ? 1 : i3;
        a(stringBuffer, i4 + i5 + i6 + i7 + i8, j2);
        stringBuffer.append("% ");
        if (i2 >= 0) {
            stringBuffer.append(i2);
            stringBuffer.append(c.a.SEPARATOR);
        }
        stringBuffer.append(str2);
        stringBuffer.append(": ");
        a(stringBuffer, i4, j2);
        stringBuffer.append("% user + ");
        a(stringBuffer, i5, j2);
        stringBuffer.append("% kernel");
        if (i6 > 0) {
            stringBuffer.append(" + ");
            a(stringBuffer, i6, j2);
            stringBuffer.append("% iowait");
        }
        if (i7 > 0) {
            stringBuffer.append(" + ");
            a(stringBuffer, i7, j2);
            stringBuffer.append("% irq");
        }
        if (i8 > 0) {
            stringBuffer.append(" + ");
            a(stringBuffer, i8, j2);
            stringBuffer.append("% softirq");
        }
        if (i9 > 0 || i10 > 0) {
            stringBuffer.append(" / faults:");
            if (i9 > 0) {
                stringBuffer.append(" ");
                stringBuffer.append(i9);
                stringBuffer.append(" minor");
            }
            if (i10 > 0) {
                stringBuffer.append(" ");
                stringBuffer.append(i10);
                stringBuffer.append(" major");
            }
        }
    }
}
