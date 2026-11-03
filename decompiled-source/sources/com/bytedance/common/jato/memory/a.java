package com.bytedance.common.jato.memory;

import android.os.SystemClock;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: GCMonitor.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4883a = "GCMonitor";

    /* renamed from: b  reason: collision with root package name */
    private static a f4884b;

    /* renamed from: e  reason: collision with root package name */
    private static long f4887e;

    /* renamed from: c  reason: collision with root package name */
    private static WeakReference<b> f4885c = new WeakReference<>(new b());

    /* renamed from: d  reason: collision with root package name */
    private static final List<InterfaceC0064a> f4886d = new CopyOnWriteArrayList();

    /* renamed from: f  reason: collision with root package name */
    private static long f4888f = 0;

    /* renamed from: g  reason: collision with root package name */
    private static long f4889g = Long.MAX_VALUE;

    /* renamed from: h  reason: collision with root package name */
    private static long f4890h = 0;

    /* compiled from: GCMonitor.java */
    /* renamed from: com.bytedance.common.jato.memory.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0064a {
        void a(long j2, long j3, long j4);
    }

    static /* synthetic */ long i() {
        long j2 = f4888f;
        f4888f = 1 + j2;
        return j2;
    }

    /* compiled from: GCMonitor.java */
    /* loaded from: classes.dex */
    private static final class b {
        private b() {
        }

        protected void finalize() {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long j2 = 0;
                if (a.f4887e > 0) {
                    long j3 = elapsedRealtime - a.f4887e;
                    if (j3 < a.f4889g) {
                        long unused = a.f4889g = j3;
                    }
                    if (j3 > a.f4890h) {
                        long unused2 = a.f4890h = j3;
                    }
                    j2 = j3;
                }
                long unused3 = a.f4887e = elapsedRealtime;
                a.i();
                a.a().a(elapsedRealtime, a.f4888f, j2);
                WeakReference unused4 = a.f4885c = new WeakReference(new b());
            } finally {
                super.finalize();
            }
        }
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (f4884b == null) {
                f4884b = new a();
            }
            aVar = f4884b;
        }
        return aVar;
    }

    private a() {
    }

    public void a(InterfaceC0064a interfaceC0064a) {
        if (interfaceC0064a != null) {
            List<InterfaceC0064a> list = f4886d;
            if (list.contains(interfaceC0064a)) {
                return;
            }
            list.add(interfaceC0064a);
        }
    }

    public void b(InterfaceC0064a interfaceC0064a) {
        if (interfaceC0064a != null) {
            f4886d.remove(interfaceC0064a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j2, long j3, long j4) {
        for (InterfaceC0064a interfaceC0064a : f4886d) {
            try {
                interfaceC0064a.a(j2, j3, j4);
            } catch (Exception e2) {
                Log.e(f4883a, "Error in GC listener", e2);
            }
        }
    }

    public long b() {
        return f4887e;
    }

    public long c() {
        return f4888f;
    }

    public long d() {
        long j2 = f4889g;
        if (j2 == Long.MAX_VALUE) {
            return 0L;
        }
        return j2;
    }

    public long e() {
        return f4890h;
    }
}
