package com.bytedance.framwork.core.sdklib.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bytedance.framwork.core.sdklib.util.d;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* compiled from: ThreadWithHandler.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    static final d.a<c, Runnable> f8735a = new d.a<c, Runnable>() { // from class: com.bytedance.framwork.core.sdklib.thread.d.1
        @Override // com.bytedance.framwork.core.sdklib.util.d.a
        public boolean equals(c cVar, Runnable runnable) {
            return runnable == null ? cVar == null || cVar.f8744a == null || cVar.f8744a.getCallback() == null : (cVar == null || cVar.f8744a == null || !runnable.equals(cVar.f8744a.getCallback())) ? false : true;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    static final d.a<Message, Runnable> f8736b = new d.a<Message, Runnable>() { // from class: com.bytedance.framwork.core.sdklib.thread.d.2
        @Override // com.bytedance.framwork.core.sdklib.util.d.a
        public boolean equals(Message message, Runnable runnable) {
            return runnable == null ? message == null || message.getCallback() == null : message != null && runnable.equals(message.getCallback());
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private final HandlerThread f8737c;

    /* renamed from: f  reason: collision with root package name */
    private volatile Handler f8740f;

    /* renamed from: d  reason: collision with root package name */
    private final Queue<c> f8738d = new ConcurrentLinkedQueue();

    /* renamed from: e  reason: collision with root package name */
    private final Queue<Message> f8739e = new ConcurrentLinkedQueue();

    /* renamed from: g  reason: collision with root package name */
    private final Object f8741g = new Object();

    public d(String str, int i2) {
        this.f8737c = new b(str, i2);
    }

    public d(String str) {
        this.f8737c = new b(str);
    }

    public void start() {
        this.f8737c.start();
    }

    private Message a(Runnable runnable) {
        return Message.obtain(this.f8740f, runnable);
    }

    private Message a(Runnable runnable, Object obj) {
        Message obtain = Message.obtain(this.f8740f, runnable);
        obtain.obj = obj;
        return obtain;
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
        if (!this.f8738d.isEmpty() || !this.f8739e.isEmpty()) {
            com.bytedance.framwork.core.sdklib.util.d.removeAll(this.f8738d, runnable, f8735a);
            com.bytedance.framwork.core.sdklib.util.d.removeAll(this.f8739e, runnable, f8736b);
        }
        if (this.f8740f != null) {
            this.f8740f.removeCallbacks(runnable);
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
        if (this.f8740f == null) {
            synchronized (this.f8741g) {
                if (this.f8740f == null) {
                    this.f8738d.add(new c(message, j2));
                    return true;
                }
            }
        }
        return this.f8740f.sendMessageAtTime(message, j2);
    }

    public final boolean sendMessageAtFrontOfQueue(Message message) {
        if (this.f8740f == null) {
            synchronized (this.f8741g) {
                if (this.f8740f == null) {
                    this.f8739e.add(message);
                    return true;
                }
            }
        }
        return this.f8740f.sendMessageAtFrontOfQueue(message);
    }

    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes2.dex */
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
            synchronized (d.this.f8741g) {
                d.this.f8740f = new Handler();
            }
            d.this.f8740f.post(new a());
            while (true) {
                try {
                    Looper.loop();
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes2.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b();
            a();
        }

        void a() {
            while (!d.this.f8738d.isEmpty()) {
                c cVar = (c) d.this.f8738d.poll();
                if (d.this.f8740f != null) {
                    d.this.f8740f.sendMessageAtTime(cVar.f8744a, cVar.f8745b);
                }
            }
        }

        void b() {
            while (!d.this.f8739e.isEmpty()) {
                if (d.this.f8740f != null) {
                    d.this.f8740f.sendMessageAtFrontOfQueue((Message) d.this.f8739e.poll());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        Message f8744a;

        /* renamed from: b  reason: collision with root package name */
        long f8745b;

        c(Message message, long j2) {
            this.f8744a = message;
            this.f8745b = j2;
        }
    }
}
