package com.bytedance.apm.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bytedance.apm.l;
import com.bytedance.apm.util.m;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* compiled from: ThreadWithHandler.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    static final m.a<c, Runnable> f1472a = new m.a<c, Runnable>() { // from class: com.bytedance.apm.thread.d.1
        @Override // com.bytedance.apm.util.m.a
        public boolean equals(c cVar, Runnable runnable) {
            return runnable == null ? cVar == null || cVar.f1484a == null || cVar.f1484a.getCallback() == null : (cVar == null || cVar.f1484a == null || !runnable.equals(cVar.f1484a.getCallback())) ? false : true;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    static final m.a<Message, Runnable> f1473b = new m.a<Message, Runnable>() { // from class: com.bytedance.apm.thread.d.2
        @Override // com.bytedance.apm.util.m.a
        public boolean equals(Message message, Runnable runnable) {
            return runnable == null ? message == null || message.getCallback() == null : message != null && runnable.equals(message.getCallback());
        }
    };

    /* renamed from: i  reason: collision with root package name */
    private static final long f1474i = 1000;

    /* renamed from: c  reason: collision with root package name */
    private final HandlerThread f1475c;

    /* renamed from: f  reason: collision with root package name */
    public volatile Handler f1478f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1479g;

    /* renamed from: d  reason: collision with root package name */
    private final Queue<c> f1476d = new ConcurrentLinkedQueue();

    /* renamed from: e  reason: collision with root package name */
    private final Queue<Message> f1477e = new ConcurrentLinkedQueue();

    /* renamed from: j  reason: collision with root package name */
    private long f1481j = 0;

    /* renamed from: h  reason: collision with root package name */
    private final Object f1480h = new Object();

    public d(String str, int i2) {
        this.f1475c = new b(str, i2);
    }

    public d(String str, int i2, boolean z) {
        this.f1475c = new b(str, i2);
        this.f1479g = z;
    }

    public d(String str) {
        this.f1475c = new b(str);
    }

    public void removeCallbacksAndMessages() {
        if (this.f1478f != null) {
            this.f1478f.removeCallbacksAndMessages(null);
        } else if (this.f1476d.isEmpty() && this.f1477e.isEmpty()) {
        } else {
            synchronized (this.f1480h) {
                try {
                    this.f1476d.clear();
                    this.f1477e.clear();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void quit() {
        this.f1475c.quit();
    }

    public void start() {
        this.f1475c.start();
    }

    public boolean isReady() {
        return this.f1478f != null;
    }

    public Message a(Runnable runnable) {
        return Message.obtain(this.f1478f, runnable);
    }

    private Message a(Runnable runnable, Object obj) {
        Message obtain = Message.obtain(this.f1478f, runnable);
        obtain.obj = obj;
        return obtain;
    }

    public Looper getLooper() {
        HandlerThread handlerThread = this.f1475c;
        if (handlerThread != null) {
            return handlerThread.getLooper();
        }
        return null;
    }

    public final boolean b(Runnable runnable) {
        return a(runnable, SystemClock.uptimeMillis());
    }

    public final boolean a(Runnable runnable, long j2) {
        if (this.f1481j < j2) {
            this.f1481j = j2 + 1000;
        }
        return postAtTime(runnable, this.f1481j);
    }

    public final boolean post(Runnable runnable) {
        return sendMessageDelayed(a(runnable), 0L);
    }

    public final boolean postAtTime(Runnable runnable, long j2) {
        return sendMessageAtTime(a(runnable), j2);
    }

    public final boolean postAtTime(Runnable runnable, Object obj, long j2) {
        return sendMessageAtTime(a(runnable, obj), j2);
    }

    public final boolean postDelayed(Runnable runnable, long j2) {
        return sendMessageDelayed(a(runnable), j2);
    }

    public final boolean postDelayed(Runnable runnable, Object obj, long j2) {
        return sendMessageDelayed(a(runnable, obj), j2);
    }

    public final boolean postAtFrontOfQueue(Runnable runnable) {
        return sendMessageAtFrontOfQueue(a(runnable));
    }

    public final void removeCallbacks(Runnable runnable) {
        if (!this.f1476d.isEmpty() || !this.f1477e.isEmpty()) {
            m.removeAll(this.f1476d, runnable, f1472a);
            m.removeAll(this.f1477e, runnable, f1473b);
        }
        if (this.f1478f != null) {
            this.f1478f.removeCallbacks(runnable);
        }
    }

    public final boolean sendMessage(Message message) {
        return sendMessageDelayed(message, 0L);
    }

    public final boolean sendEmptyMessage(int i2) {
        return sendEmptyMessageDelayed(i2, 0L);
    }

    public final boolean sendEmptyMessageDelayed(int i2, long j2) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        return sendMessageDelayed(obtain, j2);
    }

    public final boolean sendEmptyMessageAtTime(int i2, long j2) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        return sendMessageAtTime(obtain, j2);
    }

    public final boolean sendMessageDelayed(Message message, long j2) {
        if (j2 < 0) {
            j2 = 0;
        }
        return sendMessageAtTime(message, SystemClock.uptimeMillis() + j2);
    }

    public final boolean sendMessageAtTime(Message message, long j2) {
        if (this.f1478f == null) {
            synchronized (this.f1480h) {
                if (this.f1478f == null) {
                    this.f1476d.add(new c(message, j2));
                    return true;
                }
            }
        }
        return this.f1478f.sendMessageAtTime(message, j2);
    }

    public final boolean sendMessageAtFrontOfQueue(Message message) {
        if (this.f1478f == null) {
            synchronized (this.f1480h) {
                if (this.f1478f == null) {
                    this.f1477e.add(message);
                    return true;
                }
            }
        }
        return this.f1478f.sendMessageAtFrontOfQueue(message);
    }

    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes.dex */
    class b extends HandlerThread {
        b(String str) {
            super(str);
        }

        b(String str, int i2) {
            super(str, i2);
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            synchronized (d.this.f1480h) {
                d.this.f1478f = new Handler();
            }
            d.this.f1478f.post(new a());
            while (true) {
                try {
                    Looper.loop();
                } catch (Throwable th) {
                    l.getInstance().directReportError(th, "apm_error");
                }
            }
        }
    }

    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b();
            a();
        }

        void a() {
            while (!d.this.f1476d.isEmpty()) {
                synchronized (d.this.f1480h) {
                    c cVar = (c) d.this.f1476d.poll();
                    if (d.this.f1478f != null) {
                        d.this.f1478f.sendMessageAtTime(cVar.f1484a, cVar.f1485b);
                    }
                }
            }
        }

        void b() {
            while (!d.this.f1477e.isEmpty()) {
                synchronized (d.this.f1480h) {
                    if (d.this.f1478f != null) {
                        d.this.f1478f.sendMessageAtFrontOfQueue((Message) d.this.f1477e.poll());
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        Message f1484a;

        /* renamed from: b  reason: collision with root package name */
        long f1485b;

        c(Message message, long j2) {
            this.f1484a = message;
            this.f1485b = j2;
        }
    }
}
