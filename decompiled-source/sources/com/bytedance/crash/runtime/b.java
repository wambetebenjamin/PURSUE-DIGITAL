package com.bytedance.crash.runtime;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bytedance.crash.constants.c;
import com.bytedance.crash.util.y;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* compiled from: DefaultWorkThread.java */
/* loaded from: classes2.dex */
public class b extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7048a = 5;

    /* renamed from: b  reason: collision with root package name */
    private static volatile b f7049b;

    /* renamed from: c  reason: collision with root package name */
    private final Queue<a> f7050c;

    /* renamed from: d  reason: collision with root package name */
    private Handler f7051d;

    /* renamed from: e  reason: collision with root package name */
    private int f7052e;

    private b() {
        super("npth-worker");
        this.f7050c = new ConcurrentLinkedQueue();
    }

    private static b d() {
        if (f7049b == null) {
            synchronized (b.class) {
                if (f7049b == null) {
                    f7049b = new b();
                    f7049b.start();
                }
            }
        }
        return f7049b;
    }

    private void e() {
        synchronized (this.f7050c) {
            while (!this.f7050c.isEmpty()) {
                a poll = this.f7050c.poll();
                if (poll != null) {
                    try {
                        this.f7051d.sendMessageAtTime(Message.obtain(this.f7051d, poll.f7053a), poll.f7054b);
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    private void a(Throwable th) {
        try {
            int i2 = this.f7052e;
            if (i2 <= 5) {
                com.bytedance.crash.diagnose.b.b(i2 == 5 ? "NPTH_ERR_MAX" : c.A, th);
            }
            this.f7052e++;
        } catch (Throwable unused) {
        }
    }

    @Override // android.os.HandlerThread
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        this.f7051d = new Handler(Looper.myLooper());
        e();
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable th) {
                y.i(th);
                a(th);
            }
        }
    }

    private void b(Runnable runnable, long j2) {
        if (j2 < 0) {
            j2 = 0;
        }
        c(runnable, SystemClock.uptimeMillis() + j2);
    }

    private void c(Runnable runnable, long j2) {
        if (this.f7051d == null) {
            synchronized (this.f7050c) {
                if (this.f7051d == null) {
                    this.f7050c.add(new a(runnable, j2));
                    return;
                }
            }
        }
        this.f7051d.sendMessageAtTime(Message.obtain(this.f7051d, runnable), j2);
    }

    private void d(Runnable runnable) {
        synchronized (this.f7050c) {
            Iterator<a> it = this.f7050c.iterator();
            while (it.hasNext()) {
                if (it.next().f7053a.equals(runnable)) {
                    it.remove();
                }
            }
        }
        Handler handler = this.f7051d;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public static void a(Runnable runnable) {
        d().b(runnable, 0L);
    }

    public static void a(Runnable runnable, long j2) {
        d().b(runnable, j2);
    }

    public static void b(Runnable runnable) {
        d().c(runnable, 0L);
    }

    public static void c(Runnable runnable) {
        d().d(runnable);
    }

    public static Handler a() {
        return d().f7051d;
    }

    public static Thread b() {
        return d();
    }

    public static boolean c() {
        return Thread.currentThread() == b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DefaultWorkThread.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        Runnable f7053a;

        /* renamed from: b  reason: collision with root package name */
        long f7054b;

        a(Runnable runnable, long j2) {
            this.f7053a = runnable;
            this.f7054b = j2;
        }
    }
}
