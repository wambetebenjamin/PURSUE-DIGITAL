package com.bytedance.crash.repair;

import android.content.Context;
import android.util.Log;
import com.bytedance.crash.Global;
import com.bytedance.crash.monitor.h;
import com.bytedance.crash.npth_repair.NpthRepair;
import com.bytedance.crash.npth_repair.monitor.SignalMonitor;
import com.bytedance.crash.npth_repair.nativeCrash.ArtDumpNativeStackFixer;
import com.bytedance.crash.npth_repair.nativeCrash.DvmSuspendTimeOpt;
import com.bytedance.crash.npth_repair.nativeCrash.MTKBufferOpt;
import com.bytedance.crash.npth_repair.nativeCrash.ScudoVmaLeakFixer;
import com.bytedance.crash.util.y;
import java.util.ArrayList;
/* compiled from: RepairAdapter.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7016a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f7017b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f7018c = 2;

    /* renamed from: d  reason: collision with root package name */
    public static final int f7019d = 3;

    /* renamed from: e  reason: collision with root package name */
    private static final String f7020e = "NpthRepairAdapter";

    /* renamed from: f  reason: collision with root package name */
    private static boolean f7021f = false;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f7022g = true;

    /* renamed from: h  reason: collision with root package name */
    private static com.bytedance.crash.repair.b f7023h;
    private static final b o = new b() { // from class: com.bytedance.crash.repair.a.1
        @Override // com.bytedance.crash.repair.a.b
        public int a(int i2) {
            if (a.f7021f || (a.f7022g && a.b(i2, 0))) {
                return ArtDumpNativeStackFixer.a();
            }
            return -1;
        }
    };
    private static final b p = new b() { // from class: com.bytedance.crash.repair.a.2
        @Override // com.bytedance.crash.repair.a.b
        public int a(int i2) {
            if (a.b(i2, 1)) {
                return DvmSuspendTimeOpt.a();
            }
            return -1;
        }
    };
    private static final b q = new b() { // from class: com.bytedance.crash.repair.a.3
        @Override // com.bytedance.crash.repair.a.b
        public int a(int i2) {
            if (a.b(i2, 2)) {
                return SignalMonitor.a().a(a.f7023h.d());
            }
            return -1;
        }
    };

    /* renamed from: i  reason: collision with root package name */
    private boolean f7024i;

    /* renamed from: j  reason: collision with root package name */
    private int f7025j;
    private ArrayList<b> k;
    private int l;
    private int m;
    private boolean n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RepairAdapter.java */
    /* loaded from: classes2.dex */
    public interface b {
        int a(int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(int i2, int i3) {
        return (i2 & (1 << i3)) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RepairAdapter.java */
    /* renamed from: com.bytedance.crash.repair.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0101a {

        /* renamed from: a  reason: collision with root package name */
        static final a f7027a = new a();

        private C0101a() {
        }
    }

    private a() {
        this.f7024i = false;
        this.f7025j = -1;
    }

    private static a f() {
        return C0101a.f7027a;
    }

    private void g() {
        this.l = f7023h.b();
        this.m = f7023h.a();
        this.n = f7023h.c();
        y.i(f7020e, "type: " + this.l + " debug: " + this.n + " delayedTime: " + this.m);
        if (h()) {
            com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.repair.a.4
                @Override // java.lang.Runnable
                public void run() {
                    for (int i2 = 0; i2 < 3; i2++) {
                        try {
                            if (i2 >= a.this.k.size()) {
                                return;
                            }
                            Log.d(a.f7020e, "fix ret: " + ((b) a.this.k.get(i2)).a(a.this.l));
                        } catch (Throwable unused) {
                            return;
                        }
                    }
                }
            }, this.m);
        }
    }

    private boolean h() {
        if (this.f7024i) {
            return this.f7025j == 0;
        }
        this.f7024i = true;
        int b2 = b(this.n);
        this.f7025j = b2;
        if (b2 != 0) {
            return false;
        }
        ArrayList<b> arrayList = new ArrayList<>();
        this.k = arrayList;
        arrayList.add(o);
        this.k.add(p);
        this.k.add(q);
        return true;
    }

    private void a(b bVar) {
        ArrayList<b> arrayList = this.k;
        if (arrayList != null) {
            arrayList.add(bVar);
        }
    }

    public int a(int i2, String str) {
        if (b(this.l, i2)) {
            if (i2 == 2) {
                return SignalMonitor.a().a(str);
            }
            return -2;
        }
        return -1;
    }

    public static synchronized void a(com.bytedance.crash.repair.b bVar) {
        synchronized (a.class) {
            if (bVar == null) {
                return;
            }
            f7023h = bVar;
            f().g();
        }
    }

    public static synchronized int b(int i2, String str) {
        int a2;
        synchronized (a.class) {
            a2 = f().a(i2, str);
        }
        return a2;
    }

    private static int b(boolean z) {
        Context c2 = Global.c();
        if (c2 != null) {
            return NpthRepair.a(c2, z);
        }
        return -7;
    }

    public static void a(boolean z) {
        f7021f = z;
        f7022g = false;
    }

    public static synchronized int a(int i2) {
        synchronized (a.class) {
            int b2 = b(false);
            if (b2 == 0) {
                return MTKBufferOpt.a(i2);
            }
            return b2;
        }
    }

    public static int a() {
        int b2 = b(false);
        if (b2 == 0) {
            b2 = ScudoVmaLeakFixer.a();
            try {
                com.bytedance.crash.monitor.a a2 = h.a();
                if (a2 != null) {
                    a2.b("fix_scudo", String.valueOf(b2));
                }
            } catch (Throwable unused) {
            }
        }
        return b2;
    }

    public static void b() {
        com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.repair.a.5
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }
}
