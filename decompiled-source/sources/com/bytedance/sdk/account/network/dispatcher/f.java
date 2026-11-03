package com.bytedance.sdk.account.network.dispatcher;

import com.bytedance.sdk.account.network.dispatcher.e;
import java.lang.Thread;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: RequestQueue.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: c  reason: collision with root package name */
    private static final int f12669c = 4;

    /* renamed from: d  reason: collision with root package name */
    private static final int f12670d = 4;

    /* renamed from: e  reason: collision with root package name */
    private static volatile f f12671e;

    /* renamed from: b  reason: collision with root package name */
    private volatile boolean f12673b;

    /* renamed from: g  reason: collision with root package name */
    private int f12674g;

    /* renamed from: h  reason: collision with root package name */
    private int f12675h;

    /* renamed from: i  reason: collision with root package name */
    private final PriorityBlockingQueue<e> f12676i;

    /* renamed from: j  reason: collision with root package name */
    private final PriorityBlockingQueue<e> f12677j;
    private final PriorityBlockingQueue<e> k;
    private a[] l;
    private d[] m;
    private b n;
    private volatile long o;
    private volatile long p;
    private volatile long q;
    private volatile long r;

    /* renamed from: a  reason: collision with root package name */
    private static AtomicInteger f12668a = new AtomicInteger();

    /* renamed from: f  reason: collision with root package name */
    private static volatile boolean f12672f = true;

    public static void setDynamicAdjustThreadPoolSizeOpen(boolean z) {
        f12672f = z;
    }

    public static f getDefaultRequestQueue() {
        if (f12671e == null) {
            synchronized (f.class) {
                if (f12671e == null) {
                    f12671e = new f(false);
                }
            }
        }
        return f12671e;
    }

    public static int getSequenceNumber() {
        return f12668a.incrementAndGet();
    }

    public f() {
        this(4, 4, true);
    }

    public f(boolean z) {
        this(4, 0, z);
    }

    public f(int i2, int i3, boolean z) {
        this.f12673b = false;
        this.f12676i = new PriorityBlockingQueue<>();
        this.f12677j = new PriorityBlockingQueue<>();
        this.k = new PriorityBlockingQueue<>();
        this.o = 0L;
        this.p = 0L;
        this.q = 0L;
        this.r = 0L;
        this.f12674g = i2;
        this.l = new a[i2 * 4];
        if (z) {
            this.f12675h = i3;
            this.m = new d[i3 * 4];
        }
    }

    public synchronized void addDownload(c cVar) {
        if (cVar == null) {
            return;
        }
        cVar.setSequence(getSequenceNumber());
        if (!this.f12673b) {
            start();
        }
        if (cVar.getPriority() == e.a.IMMEDIATE) {
            com.bytedance.common.utility.concurrent.e.submitRunnable(cVar);
        } else {
            cVar.sendEnDownloadQueueExpireMsg();
            this.k.add(cVar);
        }
    }

    public synchronized void add(c cVar) {
        if (cVar == null) {
            return;
        }
        cVar.setSequence(getSequenceNumber());
        if (!this.f12673b) {
            start();
        }
        if (cVar.needTryLocal()) {
            this.f12676i.add(cVar);
        } else if (cVar.getPriority() == e.a.IMMEDIATE) {
            com.bytedance.common.utility.concurrent.e.submitRunnable(cVar);
        } else {
            cVar.sendEnQueueExpireMsg();
            this.f12677j.add(cVar);
        }
    }

    public synchronized void start() {
        stop();
        b bVar = new b(this.f12676i, this.f12677j);
        this.n = bVar;
        bVar.start();
        for (int i2 = 0; i2 < this.f12674g; i2++) {
            a aVar = new a(this.f12677j, "Account-ApiDispatcher-Thread", "ApiDispatcher");
            this.l[i2] = aVar;
            aVar.start();
        }
        if (this.m != null) {
            for (int i3 = 0; i3 < this.f12675h; i3++) {
                d dVar = new d(this.k, "Account-DownloadDispatcher-Thread", "DownloadDispatcher");
                this.m[i3] = dVar;
                dVar.start();
            }
        }
        this.f12673b = true;
    }

    public synchronized void stop() {
        int i2 = 0;
        this.f12673b = false;
        b bVar = this.n;
        if (bVar != null) {
            bVar.quit();
        }
        int i3 = 0;
        while (true) {
            a[] aVarArr = this.l;
            if (i3 >= aVarArr.length) {
                break;
            }
            if (aVarArr[i3] != null) {
                aVarArr[i3].quit();
                this.l[i3] = null;
            }
            i3++;
        }
        if (this.m != null) {
            while (true) {
                d[] dVarArr = this.m;
                if (i2 >= dVarArr.length) {
                    break;
                }
                if (dVarArr[i2] != null) {
                    dVarArr[i2].quit();
                    this.m[i2] = null;
                }
                i2++;
            }
        }
    }

    public synchronized void handleExpandRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f12672f) {
            com.ss.android.e.b("RequestQueue", "handleExpandRequestQueueSize");
            long currentTimeMillis = System.currentTimeMillis();
            if (this.o > currentTimeMillis) {
                this.o = currentTimeMillis;
            }
            if (currentTimeMillis - this.o <= 1000) {
                com.ss.android.e.b("RequestQueue", "handleExpandRequestQueueSize (now - mLastExpandRequestQueueTime) <= ApiThread.ENQUEUE_EXPIRE");
                return;
            }
            this.o = currentTimeMillis;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                a[] aVarArr = this.l;
                if (i2 >= aVarArr.length) {
                    break;
                }
                if (aVarArr[i2] == null) {
                    i3++;
                    if (i3 > this.f12674g) {
                        break;
                    }
                    a aVar = new a(this.f12677j, "Account-ApiDispatcher-Thread", "ApiDispatcher");
                    com.ss.android.e.b("RequestQueue", "apiDispatcher : " + aVar.toString() + " create");
                    this.l[i2] = aVar;
                    aVar.start();
                }
                i2++;
            }
        }
    }

    public synchronized void handleExpandDownloadRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f12672f) {
            com.ss.android.e.b("RequestQueue", "handleExpandDownloadRequestQueueSize");
            long currentTimeMillis = System.currentTimeMillis();
            if (this.p > currentTimeMillis) {
                this.p = currentTimeMillis;
            }
            if (currentTimeMillis - this.p <= 1000) {
                com.ss.android.e.b("RequestQueue", "handleExpandDownloadRequestQueueSize (now - mLastExpandDownloadRequestQueueTime) <= ApiThread.ENQUEUE_EXPIRE");
                return;
            }
            this.p = currentTimeMillis;
            if (this.m == null) {
                return;
            }
            int i2 = 0;
            int i3 = 0;
            while (true) {
                d[] dVarArr = this.m;
                if (i2 >= dVarArr.length) {
                    break;
                }
                if (dVarArr[i2] == null) {
                    i3++;
                    if (i3 > this.f12675h) {
                        break;
                    }
                    d dVar = new d(this.k, "Account-DownloadDispatcher-Thread", "DownloadDispatcher");
                    com.ss.android.e.b("RequestQueue", "downloadDispatcher : " + dVar.toString() + " create");
                    this.m[i2] = dVar;
                    dVar.start();
                }
                i2++;
            }
        }
    }

    public synchronized void handleShrinkRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f12672f) {
            com.ss.android.e.b("RequestQueue", "handleShrinkRequestQueueSize");
            long currentTimeMillis = System.currentTimeMillis();
            if (this.q > currentTimeMillis) {
                this.q = currentTimeMillis;
            }
            if (currentTimeMillis - this.q <= 2000) {
                com.ss.android.e.b("RequestQueue", "handleShrinkRequestQueueSize (now - mLastShrinkRequestQueueTime) <= ApiDispatcher.SHRINK_EXPIRE");
                return;
            }
            boolean z = true;
            boolean z2 = true;
            for (int length = this.l.length - 1; length >= this.f12674g; length--) {
                a aVar = this.l[length];
                if (aVar != null && aVar.isRunning()) {
                    z = false;
                }
                if (aVar != null) {
                    z2 = false;
                }
            }
            this.q = currentTimeMillis;
            if (z && !z2) {
                for (int length2 = this.l.length - 1; length2 >= this.f12674g; length2--) {
                    a aVar2 = this.l[length2];
                    if (aVar2 != null && aVar2.getState() != Thread.State.RUNNABLE && !aVar2.isRunning()) {
                        com.ss.android.e.b("RequestQueue", "apiDispatcher : " + aVar2.toString() + " quit");
                        aVar2.quit();
                        this.l[length2] = null;
                    }
                }
                return;
            }
            com.ss.android.e.b("RequestQueue", "handleShrinkRequestQueueSize shouldShrink = " + z + " allNull = " + z2);
        }
    }

    public synchronized void handleShrinkDownloadRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f12672f) {
            com.ss.android.e.b("RequestQueue", "handleShrinkDownloadRequestQueueSize");
            long currentTimeMillis = System.currentTimeMillis();
            if (this.r > currentTimeMillis) {
                this.r = currentTimeMillis;
            }
            if (currentTimeMillis - this.r <= 2000) {
                com.ss.android.e.b("RequestQueue", "handleShrinkDownloadRequestQueueSize (now - mLastShrinkDownloadRequestQueueTime) <= ApiDispatcher.SHRINK_EXPIRE");
                return;
            }
            d[] dVarArr = this.m;
            if (dVarArr == null) {
                return;
            }
            boolean z = true;
            boolean z2 = true;
            for (int length = dVarArr.length - 1; length >= this.f12675h; length--) {
                d dVar = this.m[length];
                if (dVar != null && dVar.isRunning()) {
                    z = false;
                }
                if (dVar != null) {
                    z2 = false;
                }
            }
            this.r = currentTimeMillis;
            if (z && !z2) {
                for (int length2 = this.m.length - 1; length2 >= this.f12675h; length2--) {
                    d dVar2 = this.m[length2];
                    if (dVar2 != null && dVar2.getState() != Thread.State.RUNNABLE && !dVar2.isRunning()) {
                        com.ss.android.e.b("RequestQueue", "apiDispatcher : " + dVar2.toString() + " quit");
                        dVar2.quit();
                        this.m[length2] = null;
                    }
                }
                return;
            }
            com.ss.android.e.b("RequestQueue", "handleShrinkDownloadRequestQueueSize shouldShrink " + z + " allNull = " + z2);
        }
    }
}
