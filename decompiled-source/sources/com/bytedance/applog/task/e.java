package com.bytedance.applog.task;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.LinkedList;
/* compiled from: TeaThread.java */
/* loaded from: classes.dex */
public class e extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3517a = "TeaThread";

    /* renamed from: b  reason: collision with root package name */
    private static volatile e f3518b = null;

    /* renamed from: g  reason: collision with root package name */
    private static final int f3519g = 1000;

    /* renamed from: c  reason: collision with root package name */
    private volatile Handler f3520c;

    /* renamed from: d  reason: collision with root package name */
    private final Object f3521d;

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f3522e;

    /* renamed from: f  reason: collision with root package name */
    private final LinkedList<Runnable> f3523f;

    private e() {
        super(f3517a);
        this.f3521d = new Object();
        this.f3522e = false;
        this.f3523f = new LinkedList<>();
    }

    private e(String str) {
        super(str);
        this.f3521d = new Object();
        this.f3522e = false;
        this.f3523f = new LinkedList<>();
    }

    public static e a() {
        if (f3518b == null) {
            synchronized (e.class) {
                if (f3518b == null) {
                    f3518b = new e();
                    f3518b.start();
                }
            }
        }
        return f3518b;
    }

    public static e a(String str) {
        return new e(str);
    }

    @Override // android.os.HandlerThread
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        synchronized (this.f3521d) {
            this.f3522e = true;
            ArrayList<Runnable> arrayList = new ArrayList(this.f3523f);
            this.f3523f.clear();
            if (!arrayList.isEmpty()) {
                for (Runnable runnable : arrayList) {
                    b(runnable);
                }
            }
        }
    }

    public void a(Runnable runnable) {
        a(runnable, 0L);
    }

    public void a(Runnable runnable, long j2) {
        if (runnable == null) {
            return;
        }
        if (this.f3522e) {
            c(runnable, j2);
            return;
        }
        synchronized (this.f3521d) {
            if (this.f3522e) {
                c(runnable, j2);
            } else {
                if (this.f3523f.size() > 1000) {
                    this.f3523f.poll();
                }
                this.f3523f.add(runnable);
            }
        }
    }

    public void b(Runnable runnable) {
        if (runnable != null) {
            b().post(runnable);
        }
    }

    public void c(Runnable runnable) {
        if (runnable != null) {
            d(runnable);
            b(runnable);
        }
    }

    public void b(Runnable runnable, long j2) {
        if (runnable != null) {
            d(runnable);
            c(runnable, j2);
        }
    }

    public void c(Runnable runnable, long j2) {
        if (runnable != null) {
            b().postDelayed(runnable, j2);
        }
    }

    public void d(Runnable runnable) {
        b().removeCallbacks(runnable);
    }

    public void e(Runnable runnable) {
        a(runnable);
    }

    public Handler b() {
        if (this.f3520c == null) {
            synchronized (this) {
                if (this.f3520c == null) {
                    this.f3520c = new Handler(getLooper());
                }
            }
        }
        return this.f3520c;
    }

    public Handler c() {
        return new Handler(getLooper());
    }
}
