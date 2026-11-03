package com.bytedance.frameworks.baselib.network.connectionclass;

import com.bytedance.common.utility.Logger;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: ConnectionClassManager.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    static final double f7475a = 5.0d;

    /* renamed from: b  reason: collision with root package name */
    static final int f7476b = 28;

    /* renamed from: c  reason: collision with root package name */
    static final int f7477c = 112;

    /* renamed from: d  reason: collision with root package name */
    static final int f7478d = 560;

    /* renamed from: e  reason: collision with root package name */
    static final long f7479e = 20;

    /* renamed from: f  reason: collision with root package name */
    static final long f7480f = 3;

    /* renamed from: g  reason: collision with root package name */
    private static final int f7481g = 8;

    /* renamed from: h  reason: collision with root package name */
    private static final double f7482h = 1.25d;

    /* renamed from: i  reason: collision with root package name */
    private static final double f7483i = 0.8d;

    /* renamed from: j  reason: collision with root package name */
    private static final double f7484j = 0.05d;
    private AtomicReference<e> n;
    private int p;
    private g k = new g(f7484j);
    private volatile boolean l = false;
    private AtomicReference<e> m = new AtomicReference<>(e.UNKNOWN);
    private ArrayList<b> o = new ArrayList<>();

    /* compiled from: ConnectionClassManager.java */
    /* loaded from: classes2.dex */
    public interface b {
        void a(e eVar);
    }

    /* compiled from: ConnectionClassManager.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final d f7485a = new d();

        private a() {
        }
    }

    public static d b() {
        return a.f7485a;
    }

    public synchronized void a(long j2, long j3) {
        double d2 = ((j2 * 1.0d) / j3) * 8.0d;
        if (j3 == 0 || d2 < 3.0d) {
            return;
        }
        try {
            if (Logger.debug()) {
                Logger.d("ConnectionClassManager", "bytes = " + j2 + " timeInMs = " + j3 + " bandwidth = " + d2);
            }
            this.k.a(d2);
        } catch (Throwable unused) {
        }
        if (this.l) {
            this.p++;
            if (d() != this.n.get()) {
                this.l = false;
                this.p = 1;
            }
            if (this.p >= f7475a && a()) {
                this.l = false;
                this.p = 1;
                this.m.set(this.n.get());
                f();
            }
            return;
        }
        if (this.m.get() != d()) {
            this.l = true;
            this.n = new AtomicReference<>(d());
        }
    }

    private boolean a() {
        if (this.k == null) {
            return false;
        }
        try {
            e eVar = this.m.get();
            double d2 = 560.0d;
            double d3 = 112.0d;
            if (e.POOR == eVar) {
                d3 = 0.0d;
                d2 = 28.0d;
            } else if (e.MODERATE == eVar) {
                d2 = 112.0d;
                d3 = 28.0d;
            } else if (e.GOOD != eVar) {
                if (e.EXCELLENT == eVar) {
                    d2 = 3.4028234663852886E38d;
                    d3 = 560.0d;
                }
                return true;
            }
            double a2 = this.k.a();
            if (a2 > d2) {
                if (a2 > d2 * f7482h) {
                    return true;
                }
            } else if (a2 < d3 * 0.8d) {
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public void c() {
        try {
            g gVar = this.k;
            if (gVar != null) {
                gVar.b();
            }
            this.m.set(e.UNKNOWN);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized e d() {
        g gVar = this.k;
        if (gVar == null) {
            return e.UNKNOWN;
        }
        return a(gVar.a());
    }

    private e a(double d2) {
        if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return e.UNKNOWN;
        }
        if (d2 < 28.0d) {
            return e.POOR;
        }
        if (d2 < 112.0d) {
            return e.MODERATE;
        }
        if (d2 < 560.0d) {
            return e.GOOD;
        }
        return e.EXCELLENT;
    }

    public synchronized double e() {
        g gVar;
        gVar = this.k;
        return gVar == null ? -1.0d : gVar.a();
    }

    public e a(b bVar) {
        if (bVar != null) {
            this.o.add(bVar);
        }
        return this.m.get();
    }

    public void b(b bVar) {
        if (bVar != null) {
            this.o.remove(bVar);
        }
    }

    private void f() {
        try {
            int size = this.o.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.o.get(i2).a(this.m.get());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
