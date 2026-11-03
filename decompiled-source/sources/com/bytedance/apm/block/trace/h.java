package com.bytedance.apm.block.trace;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bytedance.apm.constant.r;
import com.bytedance.apm.core.ActivityLifeObserver;
import java.util.LinkedList;
/* compiled from: MethodCollector.java */
/* loaded from: classes.dex */
public class h {
    private static com.bytedance.monitor.collector.a A = null;
    private static Runnable B = null;
    private static a C = null;
    private static a D = null;
    public static final int DEFAULT_STACK_DEPTH = 5;
    public static final int METHOD_ID_DISPATCH = 1048574;

    /* renamed from: a  reason: collision with root package name */
    private static final String f821a = "MethodCollector";

    /* renamed from: b  reason: collision with root package name */
    private static h f822b = new h();

    /* renamed from: c  reason: collision with root package name */
    private static final int f823c = Integer.MAX_VALUE;

    /* renamed from: d  reason: collision with root package name */
    private static final int f824d = 2;

    /* renamed from: e  reason: collision with root package name */
    private static final int f825e = 1;

    /* renamed from: f  reason: collision with root package name */
    private static final int f826f = -1;

    /* renamed from: g  reason: collision with root package name */
    private static final int f827g = -2;

    /* renamed from: h  reason: collision with root package name */
    private static final int f828h = -3;

    /* renamed from: i  reason: collision with root package name */
    private static final int f829i = -4;

    /* renamed from: j  reason: collision with root package name */
    private static volatile int f830j = 0;
    private static volatile boolean k = false;
    private static final Object l;
    private static long[] m = null;
    private static int n = 0;
    private static int o = 0;
    private static boolean p = false;
    private static volatile long q = 0;
    private static volatile long r = 0;
    private static Thread s = null;
    private static volatile Runnable t = null;
    private static HandlerThread u = null;
    private static Handler v = null;
    private static final int w = 1048575;
    private static Object x;
    private static boolean y;
    private static Runnable z;

    private static long b(long j2) {
        return j2 & 8796093022207L;
    }

    static /* synthetic */ long f() {
        return q();
    }

    static {
        f830j = Integer.MAX_VALUE;
        k = false;
        Object obj = new Object();
        l = obj;
        m = new long[com.bytedance.apm.block.b.BUFFER_SIZE];
        n = 0;
        o = -1;
        p = false;
        q = q();
        r = q;
        s = Looper.getMainLooper().getThread();
        t = null;
        u = o();
        v = new Handler(u.getLooper());
        x = new Object();
        y = false;
        z = null;
        A = new com.bytedance.monitor.collector.a() { // from class: com.bytedance.apm.block.trace.h.1
            @Override // com.bytedance.monitor.collector.a
            public boolean isValid() {
                if (h.t != null) {
                    h.t.run();
                    Runnable unused = h.t = null;
                }
                return h.f830j >= 1;
            }

            @Override // com.bytedance.monitor.collector.a
            public void dispatchStart(String str) {
                super.dispatchStart(str);
                h.s();
            }

            @Override // com.bytedance.monitor.collector.a
            public void a(String str, Message message) {
                super.a(str, message);
                h.t();
            }
        };
        B = new Runnable() { // from class: com.bytedance.apm.block.trace.h.2
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        if (h.y || h.f830j <= -1) {
                            synchronized (h.x) {
                                h.x.wait();
                            }
                        } else {
                            long unused = h.q = h.f() - h.r;
                            h.q &= 8796093022207L;
                            SystemClock.sleep(5L);
                        }
                    } catch (InterruptedException unused2) {
                        return;
                    }
                }
            }
        };
        if (Build.VERSION.SDK_INT < 24) {
            f830j = -4;
            k = true;
        } else {
            if (f830j == Integer.MAX_VALUE) {
                synchronized (obj) {
                    if (f830j == Integer.MAX_VALUE) {
                        r();
                        f830j = 1;
                    }
                }
            }
            i(METHOD_ID_DISPATCH, 0L);
            maskIndex("EvilMethodTracer#message_0", com.bytedance.monitor.collector.a.uptime);
        }
        v.postDelayed(new Runnable() { // from class: com.bytedance.apm.block.trace.h.3
            @Override // java.lang.Runnable
            public void run() {
                h.p();
            }
        }, r.APP_START_MAX_LIMIT_MS);
    }

    private static HandlerThread o() {
        HandlerThread handlerThread = new HandlerThread("trace_time_update_thread");
        handlerThread.start();
        return handlerThread;
    }

    public static h getInstance() {
        return f822b;
    }

    public void onStart() {
        synchronized (l) {
            if (f830j < 2 && f830j >= -2) {
                v.removeCallbacks(z);
                if (m == null) {
                    throw new RuntimeException("MethodCollector sBuffer == null");
                }
                f830j = 2;
                k = false;
            }
        }
    }

    public void onStop() {
        synchronized (l) {
            if (f830j == 2) {
                f830j = -1;
                k = true;
            }
        }
    }

    public boolean isAlive() {
        return f830j >= 2;
    }

    public static boolean isRealTrace() {
        return f830j >= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void p() {
        synchronized (l) {
            if (f830j == Integer.MAX_VALUE || f830j == -4) {
                f830j = -3;
                k = true;
                t = new Runnable() { // from class: com.bytedance.apm.block.trace.h.4
                    @Override // java.lang.Runnable
                    public void run() {
                        h.v.removeCallbacksAndMessages(null);
                        com.bytedance.monitor.collector.j.unregister(h.A);
                        h.u.quit();
                        long[] unused = h.m = null;
                    }
                };
            }
        }
    }

    private static long q() {
        return SystemClock.uptimeMillis();
    }

    public static long getCurrentDiffTime() {
        return q;
    }

    private static void r() {
        q = q() - r;
        v.removeCallbacksAndMessages(null);
        v.postDelayed(B, 5L);
        Handler handler = v;
        Runnable runnable = new Runnable() { // from class: com.bytedance.apm.block.trace.h.5
            @Override // java.lang.Runnable
            public void run() {
                synchronized (h.l) {
                    if (h.f830j == Integer.MAX_VALUE || h.f830j == 1) {
                        int unused = h.f830j = -2;
                        boolean unused2 = h.k = true;
                    }
                }
            }
        };
        z = runnable;
        handler.postDelayed(runnable, r.APP_START_MAX_LIMIT_MS);
        com.bytedance.monitor.collector.j.register(A);
    }

    public static void u() {
        com.bytedance.monitor.collector.j.unregister(A);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void s() {
        q = com.bytedance.monitor.collector.a.uptime - r;
        y = false;
        synchronized (x) {
            x.notify();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void t() {
        y = true;
    }

    public static void i(int i2, long j2) {
        if (!k && i2 < w && Thread.currentThread() == s && !p) {
            p = true;
            if (j2 != 0) {
                q = j2 - r;
                q &= 8796093022207L;
            }
            long j3 = (i2 << 43) | Long.MIN_VALUE | q;
            long[] jArr = m;
            int i3 = n;
            jArr[i3] = j3;
            int i4 = i3 + 1;
            n = i4;
            if (i4 == 600000) {
                n = 0;
            }
            p = false;
            a aVar = D;
            if (aVar != null && aVar.index == n) {
                a aVar2 = D.f834d;
                D = aVar2;
                if (aVar2 == null) {
                    C = null;
                } else {
                    aVar2.f833c = null;
                }
            }
        }
    }

    public static void o(int i2, long j2) {
        if (!k && i2 < w && Thread.currentThread() == s) {
            if (j2 != 0) {
                q = j2 - r;
                q &= 8796093022207L;
            }
            long j3 = (i2 << 43) | 0 | q;
            long[] jArr = m;
            int i3 = n;
            jArr[i3] = j3;
            int i4 = i3 + 1;
            n = i4;
            if (i4 == 600000) {
                n = 0;
            }
            a aVar = D;
            if (aVar == null || aVar.index != n) {
                return;
            }
            a aVar2 = D.f834d;
            D = aVar2;
            if (aVar2 == null) {
                C = null;
            } else {
                aVar2.f833c = null;
            }
        }
    }

    public static String getVisibleScene() {
        return ActivityLifeObserver.getInstance().getTopActivityClassName();
    }

    public static a maskIndex(String str, long j2) {
        if (C == null) {
            a aVar = new a(n);
            C = aVar;
            if (j2 <= 0) {
                j2 = SystemClock.uptimeMillis();
            }
            aVar.f835e = j2;
            C.source = str;
            a aVar2 = C;
            D = aVar2;
            return aVar2;
        }
        a aVar3 = new a(n);
        if (j2 <= 0) {
            j2 = SystemClock.uptimeMillis();
        }
        aVar3.f835e = j2;
        aVar3.source = str;
        aVar3.f833c = C;
        C.f831a = aVar3.index - 1;
        C.f834d = aVar3;
        C = aVar3;
        return aVar3;
    }

    public a getCurrentMethodIndex(String str) {
        int i2 = n - 1;
        a aVar = new a(i2);
        aVar.source = str;
        if (i2 >= 0) {
            long[] jArr = m;
            if (i2 < jArr.length) {
                aVar.f835e = b(jArr[i2]);
            }
        }
        return aVar;
    }

    public static int getLastMethodIndex() {
        return n - 1;
    }

    /* compiled from: MethodCollector.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        int f831a;

        /* renamed from: b  reason: collision with root package name */
        boolean f832b = true;

        /* renamed from: c  reason: collision with root package name */
        private a f833c;

        /* renamed from: d  reason: collision with root package name */
        private a f834d;

        /* renamed from: e  reason: collision with root package name */
        private long f835e;
        public int index;
        public String source;

        public a(int i2) {
            this.index = i2;
        }

        public String toString() {
            return "index:" + this.index + ",\tisValid:" + this.f832b + " source:" + this.source;
        }
    }

    public long[] copyData(a aVar) {
        return copyData(aVar, new a(n - 1));
    }

    public long[] copyData(a aVar, a aVar2) {
        if (f830j == -3) {
            return null;
        }
        long[] jArr = new long[0];
        try {
            if (aVar.f832b && aVar2.f832b) {
                return copyData(aVar.index - 1, aVar2.index);
            }
        } catch (OutOfMemoryError unused) {
        }
        return jArr;
    }

    public long[] copyAllData() {
        a aVar;
        try {
            a aVar2 = C;
            if (aVar2 != null && (aVar = D) != null && aVar2 != aVar) {
                return copyData(D.index, aVar2.f831a == 0 ? n - 1 : C.f831a);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private long[] a(long j2, long j3) {
        a aVar;
        a aVar2 = C;
        if (aVar2 == null || (aVar = D) == null) {
            return null;
        }
        while (aVar2 != aVar) {
            if (aVar2.f833c.f835e > j3) {
                aVar2 = aVar2.f833c;
            } else if (aVar.f834d.f835e >= j2) {
                break;
            } else {
                aVar = aVar.f834d;
            }
        }
        return copyData(aVar.index, aVar2.f831a == 0 ? n - 1 : aVar2.f831a);
    }

    public long[] copyData(int i2, int i3) {
        long[] jArr = new long[0];
        int max = Math.max(0, i2);
        int max2 = Math.max(0, i3);
        if (max2 > max) {
            int i4 = (max2 - max) + 1;
            long[] jArr2 = new long[i4];
            System.arraycopy(m, max, jArr2, 0, i4);
            return jArr2;
        } else if (max2 < max) {
            int i5 = max2 + 1;
            long[] jArr3 = m;
            long[] jArr4 = new long[(jArr3.length - max) + i5];
            System.arraycopy(jArr3, max, jArr4, 0, jArr3.length - max);
            long[] jArr5 = m;
            System.arraycopy(jArr5, 0, jArr4, jArr5.length - max, i5);
            return jArr4;
        } else {
            return jArr;
        }
    }

    public static long getDiffTime() {
        return r;
    }

    public static long getLastMethodTime() {
        return b(m[n - 1]);
    }

    public String getEvilMethod(long j2, long j3) {
        return evilMethodString(a(j2, j3), SystemClock.uptimeMillis());
    }

    public String evilMethodString(long[] jArr, long j2) {
        if (jArr == null) {
            return "";
        }
        LinkedList linkedList = new LinkedList();
        com.bytedance.perf.collector.a.structuredDataToStack(jArr, (LinkedList<i>) linkedList, true, j2);
        com.bytedance.perf.collector.a.trimStack(linkedList, 1);
        StringBuilder sb = new StringBuilder();
        com.bytedance.perf.collector.a.stackToString(linkedList, sb);
        return sb.toString();
    }
}
