package com.bytedance.frameworks.baselib.network.connectionclass;

import android.net.TrafficStats;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bytedance.common.utility.Logger;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: DeviceBandwidthSampler.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: g  reason: collision with root package name */
    private static long f7492g = -1;

    /* renamed from: a  reason: collision with root package name */
    private final d f7493a;

    /* renamed from: b  reason: collision with root package name */
    private AtomicInteger f7494b;

    /* renamed from: c  reason: collision with root package name */
    private b f7495c;

    /* renamed from: d  reason: collision with root package name */
    private HandlerThread f7496d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f7497e;

    /* renamed from: f  reason: collision with root package name */
    private long f7498f;

    /* compiled from: DeviceBandwidthSampler.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final f f7499a = new f(d.b());

        private a() {
        }
    }

    public static f b() {
        return a.f7499a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(d dVar) {
        this.f7497e = false;
        this.f7493a = dVar;
        try {
            this.f7494b = new AtomicInteger();
            HandlerThread a2 = com.bytedance.frameworks.baselib.network.thread.a.a("ParseThread");
            this.f7496d = a2;
            a2.start();
            this.f7495c = new b(this.f7496d.getLooper());
        } catch (Throwable unused) {
            this.f7497e = true;
        }
    }

    public void c() {
        try {
            if (!this.f7497e && this.f7494b.getAndIncrement() == 0) {
                if (Logger.debug()) {
                    Logger.d("ConnectionClassManager", "startSampling");
                }
                this.f7495c.a();
                this.f7498f = SystemClock.elapsedRealtime();
            }
        } catch (Throwable unused) {
        }
    }

    public void d() {
        try {
            if (!this.f7497e && this.f7494b.decrementAndGet() == 0) {
                if (Logger.debug()) {
                    Logger.d("ConnectionClassManager", "stopSampling");
                }
                this.f7495c.b();
                f();
            }
        } catch (Throwable unused) {
        }
    }

    protected void e() {
        try {
            long totalRxBytes = TrafficStats.getTotalRxBytes();
            long j2 = f7492g;
            long j3 = totalRxBytes - j2;
            if (j2 >= 0) {
                synchronized (this) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    this.f7493a.a(j3, elapsedRealtime - this.f7498f);
                    this.f7498f = elapsedRealtime;
                }
            }
            f7492g = totalRxBytes;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void f() {
        e();
        f7492g = -1L;
    }

    public boolean g() {
        return this.f7494b.get() != 0;
    }

    /* compiled from: DeviceBandwidthSampler.java */
    /* loaded from: classes2.dex */
    private class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        static final long f7500a = 1000;

        /* renamed from: c  reason: collision with root package name */
        private static final int f7501c = 1;

        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                if (message.what != 1) {
                    return;
                }
                f.this.e();
                sendEmptyMessageDelayed(1, 1000L);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public void a() {
            sendEmptyMessage(1);
        }

        public void b() {
            removeMessages(1);
        }
    }
}
