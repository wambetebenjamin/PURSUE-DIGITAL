package com.bytedance.common.jato.gc;

import android.os.Handler;
import android.os.HandlerThread;
import com.bytedance.common.jato.d;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class GCMonitor {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4694a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4695b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4696c = 4;

    /* renamed from: d  reason: collision with root package name */
    public static final int f4697d = 7;

    /* renamed from: e  reason: collision with root package name */
    private static a f4698e = null;

    /* renamed from: f  reason: collision with root package name */
    private static int f4699f = 7;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f4700g;

    /* renamed from: h  reason: collision with root package name */
    private HandlerThread f4701h;

    /* renamed from: i  reason: collision with root package name */
    private com.bytedance.common.jato.gc.a[] f4702i;

    /* renamed from: j  reason: collision with root package name */
    private final AtomicInteger f4703j;
    private final AtomicInteger k;
    private com.bytedance.common.jato.gc.a[] l;
    private final AtomicInteger m;
    private final AtomicInteger n;

    /* loaded from: classes.dex */
    public interface a {
        HandlerThread a();
    }

    private native boolean init(int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void initNotificationInWorkingThread();

    private native void notifyJavaCallbackCountChange(int i2, int i3, int i4, int i5);

    private native boolean registerCallbackPtr(long j2, long j3, long j4, boolean z);

    private native void setForceClearSoft(boolean z);

    private native boolean unregisterCallbackPtr(long j2, long j3, long j4, boolean z);

    /* loaded from: classes.dex */
    private static final class c {

        /* renamed from: a  reason: collision with root package name */
        static final GCMonitor f4706a = new GCMonitor();

        private c() {
        }
    }

    /* loaded from: classes.dex */
    public static class b implements a {

        /* renamed from: a  reason: collision with root package name */
        private HandlerThread f4705a;

        @Override // com.bytedance.common.jato.gc.GCMonitor.a
        public HandlerThread a() {
            if (this.f4705a == null) {
                HandlerThread handlerThread = new HandlerThread("gc_notification");
                this.f4705a = handlerThread;
                handlerThread.start();
            }
            return this.f4705a;
        }
    }

    public static boolean a(a aVar) {
        if (f4698e != null) {
            return false;
        }
        f4698e = aVar;
        return true;
    }

    public static void a(int i2) {
        f4699f = i2;
    }

    public static GCMonitor a() {
        return c.f4706a;
    }

    private GCMonitor() {
        boolean z = true;
        this.f4702i = new com.bytedance.common.jato.gc.a[1];
        this.f4703j = new AtomicInteger(0);
        this.k = new AtomicInteger(0);
        this.l = new com.bytedance.common.jato.gc.a[1];
        this.m = new AtomicInteger(0);
        this.n = new AtomicInteger(0);
        if (f4698e == null) {
            f4698e = new b();
        }
        this.f4700g = (d.loadLibrary() && init(f4699f)) ? z : false;
    }

    public boolean a(com.bytedance.common.jato.gc.a aVar) {
        return a(aVar, true);
    }

    public boolean b(com.bytedance.common.jato.gc.a aVar) {
        return b(aVar, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0037 A[Catch: all -> 0x0054, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0004, B:10:0x000c, B:12:0x0018, B:14:0x001c, B:15:0x001e, B:23:0x0037, B:16:0x0022, B:18:0x002e, B:20:0x0032), top: B:31:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean a(com.bytedance.common.jato.gc.a r5, boolean r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            if (r5 == 0) goto L57
            boolean r1 = r4.f4700g     // Catch: java.lang.Throwable -> L54
            if (r1 != 0) goto L9
            goto L57
        L9:
            r1 = 1
            if (r6 == 0) goto L22
            com.bytedance.common.jato.gc.a[] r6 = r4.l     // Catch: java.lang.Throwable -> L54
            java.util.concurrent.atomic.AtomicInteger r2 = r4.m     // Catch: java.lang.Throwable -> L54
            java.util.concurrent.atomic.AtomicInteger r3 = r4.n     // Catch: java.lang.Throwable -> L54
            com.bytedance.common.jato.gc.a[] r5 = r4.a(r5, r6, r2, r3)     // Catch: java.lang.Throwable -> L54
            if (r5 == 0) goto L35
            com.bytedance.common.jato.gc.a[] r6 = r4.l     // Catch: java.lang.Throwable -> L54
            if (r5 == r6) goto L1e
            r4.l = r5     // Catch: java.lang.Throwable -> L54
        L1e:
            r4.b()     // Catch: java.lang.Throwable -> L54
            goto L34
        L22:
            com.bytedance.common.jato.gc.a[] r6 = r4.f4702i     // Catch: java.lang.Throwable -> L54
            java.util.concurrent.atomic.AtomicInteger r2 = r4.f4703j     // Catch: java.lang.Throwable -> L54
            java.util.concurrent.atomic.AtomicInteger r3 = r4.k     // Catch: java.lang.Throwable -> L54
            com.bytedance.common.jato.gc.a[] r5 = r4.a(r5, r6, r2, r3)     // Catch: java.lang.Throwable -> L54
            if (r5 == 0) goto L35
            com.bytedance.common.jato.gc.a[] r6 = r4.f4702i     // Catch: java.lang.Throwable -> L54
            if (r5 == r6) goto L34
            r4.f4702i = r5     // Catch: java.lang.Throwable -> L54
        L34:
            r0 = r1
        L35:
            if (r0 == 0) goto L52
            java.util.concurrent.atomic.AtomicInteger r5 = r4.f4703j     // Catch: java.lang.Throwable -> L54
            int r5 = r5.get()     // Catch: java.lang.Throwable -> L54
            java.util.concurrent.atomic.AtomicInteger r6 = r4.m     // Catch: java.lang.Throwable -> L54
            int r6 = r6.get()     // Catch: java.lang.Throwable -> L54
            java.util.concurrent.atomic.AtomicInteger r1 = r4.k     // Catch: java.lang.Throwable -> L54
            int r1 = r1.get()     // Catch: java.lang.Throwable -> L54
            java.util.concurrent.atomic.AtomicInteger r2 = r4.n     // Catch: java.lang.Throwable -> L54
            int r2 = r2.get()     // Catch: java.lang.Throwable -> L54
            r4.notifyJavaCallbackCountChange(r5, r6, r1, r2)     // Catch: java.lang.Throwable -> L54
        L52:
            monitor-exit(r4)
            return r0
        L54:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L57:
            monitor-exit(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.jato.gc.GCMonitor.a(com.bytedance.common.jato.gc.a, boolean):boolean");
    }

    private com.bytedance.common.jato.gc.a[] a(com.bytedance.common.jato.gc.a aVar, com.bytedance.common.jato.gc.a[] aVarArr, AtomicInteger atomicInteger, AtomicInteger atomicInteger2) {
        int i2 = atomicInteger.get();
        if (i2 == aVarArr.length) {
            com.bytedance.common.jato.gc.a[] aVarArr2 = new com.bytedance.common.jato.gc.a[i2 * 2];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
            aVarArr = aVarArr2;
        } else if (i2 > aVarArr.length) {
            return null;
        }
        aVarArr[i2] = aVar;
        if (atomicInteger.compareAndSet(i2, i2 + 1)) {
            if (!aVar.a()) {
                atomicInteger2.incrementAndGet();
            }
            return aVarArr;
        }
        return null;
    }

    public synchronized boolean b(com.bytedance.common.jato.gc.a aVar, boolean z) {
        boolean b2;
        if (aVar != null) {
            if (this.f4700g) {
                if (z) {
                    b2 = b(aVar, this.l, this.m, this.n);
                    if (b2) {
                        b();
                    }
                } else {
                    b2 = b(aVar, this.f4702i, this.f4703j, this.k);
                }
                if (b2) {
                    notifyJavaCallbackCountChange(this.f4703j.get(), this.m.get(), this.k.get(), this.n.get());
                }
                return b2;
            }
        }
        return false;
    }

    private boolean b(com.bytedance.common.jato.gc.a aVar, com.bytedance.common.jato.gc.a[] aVarArr, AtomicInteger atomicInteger, AtomicInteger atomicInteger2) {
        int i2 = atomicInteger.get();
        if (i2 == 0) {
            return false;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                i3 = -1;
                break;
            } else if (aVarArr[i3] == aVar) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            int i4 = i2 - 1;
            if (atomicInteger.compareAndSet(i2, i4)) {
                if (!aVar.a()) {
                    atomicInteger2.decrementAndGet();
                }
                if (i3 < i4) {
                    aVarArr[i3] = aVarArr[i4];
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    public synchronized boolean a(long j2, long j3, long j4, boolean z) {
        if (this.f4700g && (j2 != 0 || j3 != 0 || j4 != 0)) {
            return registerCallbackPtr(j2, j3, j4, z);
        }
        return false;
    }

    public synchronized boolean b(long j2, long j3, long j4, boolean z) {
        if (this.f4700g && (j2 != 0 || j3 != 0 || j4 != 0)) {
            return unregisterCallbackPtr(j2, j3, j4, z);
        }
        return false;
    }

    private void b() {
        if (this.f4701h == null) {
            HandlerThread a2 = f4698e.a();
            new Handler(a2.getLooper()).post(new Runnable() { // from class: com.bytedance.common.jato.gc.GCMonitor.1
                @Override // java.lang.Runnable
                public void run() {
                    GCMonitor.this.initNotificationInWorkingThread();
                }
            });
            this.f4701h = a2;
        }
    }

    public boolean a(boolean z) {
        if (this.f4700g) {
            setForceClearSoft(z);
            return true;
        }
        return false;
    }

    private synchronized void dispatchGCStart(boolean z, int i2, int i3, int i4) {
        com.bytedance.common.jato.gc.a[] aVarArr = z ? this.l : this.f4702i;
        int i5 = (z ? this.m : this.f4703j).get();
        for (int i6 = 0; i6 < i5; i6++) {
            com.bytedance.common.jato.gc.a aVar = aVarArr[i6];
            if (aVar != null) {
                aVar.a(i2, i3, i4);
            }
        }
    }

    private synchronized void dispatchGCEnd(boolean z, int i2, int i3, int i4, long j2, boolean z2, long j3, long j4, long j5, long j6, long j7) {
        com.bytedance.common.jato.gc.a[] aVarArr = z ? this.l : this.f4702i;
        int i5 = (z ? this.m : this.f4703j).get();
        for (int i6 = 0; i6 < i5; i6++) {
            com.bytedance.common.jato.gc.a aVar = aVarArr[i6];
            if (aVar != null) {
                aVar.a(i2, i3, i4, j2, z2, j3, j4, j5, j6, j7);
            }
        }
    }

    private synchronized void dispatchWaitGC(boolean z, boolean z2, int i2, int i3, int i4) {
        com.bytedance.common.jato.gc.a[] aVarArr = z ? this.l : this.f4702i;
        int i5 = (z ? this.m : this.f4703j).get();
        for (int i6 = 0; i6 < i5; i6++) {
            com.bytedance.common.jato.gc.a aVar = aVarArr[i6];
            if (aVar != null && (!aVar.a() || z2)) {
                aVar.b(i2, i3, i4);
            }
        }
    }
}
