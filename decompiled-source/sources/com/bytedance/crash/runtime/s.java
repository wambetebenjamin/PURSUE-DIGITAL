package com.bytedance.crash.runtime;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* compiled from: ThreadWithHandler.java */
@Deprecated
/* loaded from: classes2.dex */
public class s {

    /* renamed from: f  reason: collision with root package name */
    private static final b<d, Runnable> f7071f = new b<d, Runnable>() { // from class: com.bytedance.crash.runtime.s.1
        @Override // com.bytedance.crash.runtime.s.b
        public boolean equals(d dVar, Runnable runnable) {
            return runnable == null ? dVar == null || dVar.f7083a == null || dVar.f7083a.getCallback() == null : (dVar == null || dVar.f7083a == null || !runnable.equals(dVar.f7083a.getCallback())) ? false : true;
        }
    };

    /* renamed from: g  reason: collision with root package name */
    private static final b<Message, Runnable> f7072g = new b<Message, Runnable>() { // from class: com.bytedance.crash.runtime.s.2
        @Override // com.bytedance.crash.runtime.s.b
        public boolean equals(Message message, Runnable runnable) {
            return runnable == null ? message == null || message.getCallback() == null : message != null && runnable.equals(message.getCallback());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final HandlerThread f7073a;

    /* renamed from: d  reason: collision with root package name */
    private volatile Handler f7076d;

    /* renamed from: b  reason: collision with root package name */
    private final Queue<d> f7074b = new ConcurrentLinkedQueue();

    /* renamed from: c  reason: collision with root package name */
    private final Queue<Message> f7075c = new ConcurrentLinkedQueue();

    /* renamed from: e  reason: collision with root package name */
    private final Object f7077e = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes2.dex */
    public interface b<A, B> {
        boolean equals(A a2, B b2);
    }

    public s(String str, int i2) {
        this.f7073a = new c(str, i2);
    }

    public s(String str) {
        this.f7073a = new c(str);
    }

    public Handler getHandler() {
        return this.f7076d;
    }

    public void start() {
        this.f7073a.start();
    }

    public HandlerThread getThread() {
        return this.f7073a;
    }

    private Message a(Runnable runnable) {
        return Message.obtain(this.f7076d, runnable);
    }

    private Message a(Runnable runnable, Object obj) {
        Message obtain = Message.obtain(this.f7076d, runnable);
        obtain.obj = obj;
        return obtain;
    }

    public final boolean post(Runnable runnable) {
        return a(a(runnable), 0L);
    }

    public final boolean postAtTime(Runnable runnable, long j2) {
        return b(a(runnable), j2);
    }

    public final boolean postAtTime(Runnable runnable, Object obj, long j2) {
        return b(a(runnable, obj), j2);
    }

    public final boolean postDelayed(Runnable runnable, long j2) {
        return a(a(runnable), j2);
    }

    public final boolean postDelayed(Runnable runnable, Object obj, long j2) {
        return a(a(runnable, obj), j2);
    }

    public final boolean postAtFrontOfQueue(Runnable runnable) {
        return a(a(runnable));
    }

    public final void removeCallbacks(Runnable runnable) {
        if (!this.f7074b.isEmpty() || !this.f7075c.isEmpty()) {
            a(this.f7074b, runnable, f7071f);
            a(this.f7075c, runnable, f7072g);
        }
        if (this.f7076d != null) {
            this.f7076d.removeCallbacks(runnable);
        }
    }

    public final boolean sendMessage(Message message) {
        return a(message, 0L);
    }

    public final boolean sendEmptyMessage(int i2) {
        return a(i2, 0L);
    }

    private boolean a(int i2, long j2) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        return a(obtain, j2);
    }

    public final boolean sendEmptyMessageAtTime(int i2, long j2) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        return b(obtain, j2);
    }

    private boolean a(Message message, long j2) {
        if (j2 < 0) {
            j2 = 0;
        }
        return b(message, SystemClock.uptimeMillis() + j2);
    }

    private boolean b(Message message, long j2) {
        if (this.f7076d == null) {
            synchronized (this.f7077e) {
                if (this.f7076d == null) {
                    this.f7074b.add(new d(message, j2));
                    return true;
                }
            }
        }
        try {
            return this.f7076d.sendMessageAtTime(message, j2);
        } catch (Throwable unused) {
            return true;
        }
    }

    private boolean a(Message message) {
        if (this.f7076d == null) {
            synchronized (this.f7077e) {
                if (this.f7076d == null) {
                    this.f7075c.add(message);
                    return true;
                }
            }
        }
        try {
            return this.f7076d.sendMessageAtFrontOfQueue(message);
        } catch (Throwable unused) {
            return true;
        }
    }

    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes2.dex */
    class c extends HandlerThread {

        /* renamed from: c  reason: collision with root package name */
        static final int f7079c = 5;

        /* renamed from: a  reason: collision with root package name */
        volatile int f7080a;

        /* renamed from: b  reason: collision with root package name */
        volatile boolean f7081b;

        c(String str) {
            super(str);
        }

        c(String str, int i2) {
            super(str, i2);
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            synchronized (s.this.f7077e) {
                s.this.f7076d = new Handler();
            }
            s.this.f7076d.post(new a());
            while (true) {
                try {
                    Looper.loop();
                } catch (Throwable th) {
                    try {
                        if (this.f7080a < 5) {
                            com.bytedance.crash.diagnose.b.a(th);
                        } else if (!this.f7081b) {
                            this.f7081b = true;
                            com.bytedance.crash.diagnose.b.b("NPTH_ERR_MAX", new RuntimeException());
                        }
                        this.f7080a++;
                    } catch (Throwable unused) {
                    }
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
            while (!s.this.f7074b.isEmpty()) {
                d dVar = (d) s.this.f7074b.poll();
                if (s.this.f7076d != null) {
                    try {
                        s.this.f7076d.sendMessageAtTime(dVar.f7083a, dVar.f7084b);
                    } catch (Throwable unused) {
                    }
                }
            }
        }

        void b() {
            while (!s.this.f7075c.isEmpty()) {
                if (s.this.f7076d != null) {
                    try {
                        s.this.f7076d.sendMessageAtFrontOfQueue((Message) s.this.f7075c.poll());
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ThreadWithHandler.java */
    /* loaded from: classes2.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        Message f7083a;

        /* renamed from: b  reason: collision with root package name */
        long f7084b;

        d(Message message, long j2) {
            this.f7083a = message;
            this.f7084b = j2;
        }
    }

    private static <L, O> boolean a(Collection<L> collection, O o, b<? super L, O> bVar) {
        boolean z = false;
        if (collection != null && !collection.isEmpty() && bVar != null) {
            try {
                Iterator<L> it = collection.iterator();
                while (it.hasNext()) {
                    if (bVar.equals(it.next(), o)) {
                        it.remove();
                        z = true;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return z;
    }
}
