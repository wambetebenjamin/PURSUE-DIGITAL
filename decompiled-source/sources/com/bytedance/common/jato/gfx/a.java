package com.bytedance.common.jato.gfx;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Printer;
import android.view.Choreographer;
import com.bytedance.common.jato.util.f;
import java.util.ArrayList;
/* compiled from: DoFrameController.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4721a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4722b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4723c = 2;

    /* renamed from: d  reason: collision with root package name */
    public static final int f4724d = 3;

    /* renamed from: e  reason: collision with root package name */
    public static final int f4725e = 4;

    /* renamed from: f  reason: collision with root package name */
    public static final int f4726f = 5;

    /* renamed from: g  reason: collision with root package name */
    public static final int f4727g = 6;

    /* renamed from: h  reason: collision with root package name */
    public static final int f4728h = 7;

    /* renamed from: i  reason: collision with root package name */
    static c f4729i = null;

    /* renamed from: j  reason: collision with root package name */
    private static final String f4730j = "DoFrameController";
    private static Handler n;
    private static long[] p;
    private static Printer q;
    private static ArrayList<d> r;
    private static InterfaceC0059a s;
    private static final e k = new e(Looper.getMainLooper());
    private static volatile int l = 0;
    private static volatile boolean m = false;
    private static Object o = null;

    /* compiled from: DoFrameController.java */
    /* renamed from: com.bytedance.common.jato.gfx.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0059a {
        boolean a(Handler handler, Message message, long j2);
    }

    /* compiled from: DoFrameController.java */
    /* loaded from: classes.dex */
    public interface b {
        void a(Throwable th);
    }

    /* compiled from: DoFrameController.java */
    /* loaded from: classes.dex */
    public interface c {
        boolean a(Handler handler, Message message, long j2);
    }

    /* compiled from: DoFrameController.java */
    /* loaded from: classes.dex */
    public interface d {
        void a(Message message, long j2);
    }

    public static synchronized void a(b bVar) {
        synchronized (a.class) {
            a(Choreographer.getInstance(), bVar);
        }
    }

    public static synchronized void a(Choreographer choreographer, b bVar) {
        synchronized (a.class) {
            if (m) {
                return;
            }
            Handler handler = (Handler) com.bytedance.common.jato.util.c.a(choreographer, "mHandler");
            n = handler;
            if (handler == null) {
                return;
            }
            o = com.bytedance.common.jato.util.c.a(choreographer, "mDisplayEventReceiver");
            q = (Printer) com.bytedance.common.jato.util.c.a(Looper.getMainLooper(), "mLogging");
            long a2 = f.a(com.bytedance.common.jato.util.c.a((Class<?>) Choreographer.class, "mHandler"));
            e eVar = k;
            f.a(choreographer, a2, eVar);
            eVar.a(o);
            Object a3 = com.bytedance.common.jato.util.c.a(choreographer, "mFrameInfo");
            if (a3 != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    p = (long[]) com.bytedance.common.jato.util.c.a(a3, "frameInfo");
                } else if (Build.VERSION.SDK_INT >= 23) {
                    p = (long[]) com.bytedance.common.jato.util.c.a(a3, "mFrameInfo");
                }
                if (Build.VERSION.SDK_INT >= 23 && p == null) {
                    bVar.a(new IllegalStateException("frameArray"));
                }
            }
            m = true;
        }
    }

    public static void a(int i2) {
        l = i2;
    }

    public static int a() {
        return l;
    }

    public static c b() {
        return f4729i;
    }

    public static void a(c cVar) {
        f4729i = cVar;
    }

    public static void a(d dVar) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("must call in main thread");
        }
        if (r == null) {
            r = new ArrayList<>();
        }
        r.add(dVar);
    }

    public static void b(d dVar) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("must call in main thread");
        }
        ArrayList<d> arrayList = r;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(dVar);
    }

    public static void a(InterfaceC0059a interfaceC0059a) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("must call in main thread");
        }
        s = interfaceC0059a;
    }

    public static long c() {
        if (p != null) {
            if (Build.VERSION.SDK_INT >= 31) {
                return p[3];
            }
            return p[2];
        }
        return 0L;
    }

    public static Handler d() {
        return n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DoFrameController.java */
    /* loaded from: classes.dex */
    public static class e extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private String f4731a;

        /* renamed from: b  reason: collision with root package name */
        private String f4732b;

        public e(Looper looper) {
            super(looper);
            this.f4731a = "<";
            this.f4732b = ">";
        }

        @Override // android.os.Handler
        public String toString() {
            return com.bytedance.common.jato.boost.handler.d.a() ? "DFC_H_ProxyFrameHandler" : super.toString();
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j2) {
            if (message.getCallback() == a.o) {
                if (a(message, j2)) {
                    return true;
                }
                switch (a.l) {
                    case 1:
                        return super.sendMessageAtTime(message, 0L);
                    case 2:
                        int unused = a.l = 0;
                        return super.sendMessageAtTime(message, 0L);
                    case 3:
                        Message a2 = com.bytedance.common.jato.gfx.c.a(com.bytedance.common.jato.gfx.c.a());
                        if (a2 == null) {
                            return super.sendMessageAtTime(message, 0L);
                        }
                        return super.sendMessageAtTime(message, Math.min(a2.getWhen(), j2));
                    case 4:
                        a(message, false);
                        return true;
                    case 5:
                        a(message, true);
                        return true;
                    case 6:
                        return super.sendMessageAtTime(message, j2 + 500);
                    case 7:
                        InterfaceC0059a interfaceC0059a = a.s;
                        boolean a3 = interfaceC0059a != null ? interfaceC0059a.a(this, message, j2) : false;
                        return !a3 ? super.sendMessageAtTime(message, j2) : a3;
                    default:
                        return super.sendMessageAtTime(message, j2);
                }
            }
            return super.sendMessageAtTime(message, j2);
        }

        private boolean a(Message message, long j2) {
            if (a.r != null) {
                for (int i2 = 0; i2 < a.r.size(); i2++) {
                    ((d) a.r.get(i2)).a(message, j2);
                }
            }
            if (a.f4729i != null) {
                return a.f4729i.a(a.n, message, j2);
            }
            return false;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (a.n != null) {
                a.n.handleMessage(message);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Object obj) {
            this.f4731a = ">>>>> Dispatching to " + toString() + " " + obj + ": 0";
            this.f4732b = "<<<<< Finished to " + toString() + " " + obj;
        }

        private void a(Message message, boolean z) {
            message.setTarget(this);
            if (a.q == null) {
                dispatchMessage(message);
            } else if (z) {
                a.q.println(a.k.f4731a);
                dispatchMessage(message);
                a.q.println(a.k.f4732b);
            } else {
                a.q.println(">>>>> Dispatching to " + message.getTarget() + " " + message.getCallback() + ": " + message.what);
                dispatchMessage(message);
                a.q.println("<<<<< Finished to " + message.getTarget() + " " + message.getCallback());
            }
            message.recycle();
        }
    }
}
