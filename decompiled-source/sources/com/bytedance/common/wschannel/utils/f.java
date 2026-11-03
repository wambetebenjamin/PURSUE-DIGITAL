package com.bytedance.common.wschannel.utils;

import com.bytedance.common.utility.Logger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: WsAsyncIOTaskManager.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: d  reason: collision with root package name */
    private static f f6009d = null;

    /* renamed from: e  reason: collision with root package name */
    private static final String f6010e = "f";

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentHashMap<g, ScheduledFuture> f6012b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private ConcurrentHashMap<g, Runnable> f6013c = new ConcurrentHashMap<>();

    /* renamed from: a  reason: collision with root package name */
    private ScheduledThreadPoolExecutor f6011a = new ScheduledThreadPoolExecutor(1, new h("frontier"));

    private f() {
    }

    public static f a() {
        if (f6009d == null) {
            synchronized (f.class) {
                if (f6009d == null) {
                    f6009d = new f();
                }
            }
        }
        return f6009d;
    }

    public void a(g gVar) {
        ScheduledFuture<?> schedule;
        if (gVar == null) {
            return;
        }
        try {
            a aVar = new a(gVar);
            if (gVar.b()) {
                schedule = this.f6011a.scheduleWithFixedDelay(aVar, gVar.a(), gVar.c(), TimeUnit.MILLISECONDS);
            } else {
                schedule = this.f6011a.schedule(aVar, gVar.a(), TimeUnit.MILLISECONDS);
            }
            this.f6013c.put(gVar, aVar);
            this.f6012b.put(gVar, schedule);
        } catch (Throwable th) {
            Logger.e(f6010e, "sendTask failed.", th);
        }
    }

    public void b(g gVar) {
        try {
            this.f6011a.remove(this.f6013c.remove(gVar));
            ScheduledFuture remove = this.f6012b.remove(gVar);
            if (remove != null) {
                remove.cancel(true);
            }
        } catch (Throwable th) {
            Logger.e(f6010e, "removeTask failed", th);
        }
    }

    /* compiled from: WsAsyncIOTaskManager.java */
    /* loaded from: classes2.dex */
    private class a implements Runnable {

        /* renamed from: b  reason: collision with root package name */
        private g f6015b;

        private a(g gVar) {
            this.f6015b = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.f6015b.run();
                if (Logger.debug()) {
                    Logger.d(f.f6010e, "Task execute time: " + (System.currentTimeMillis() - currentTimeMillis));
                }
            } catch (Throwable th) {
                try {
                    Logger.e(f.f6010e, "thread " + Thread.currentThread().getName() + " exception", th);
                    if (this.f6015b.b()) {
                    }
                } finally {
                    if (!this.f6015b.b()) {
                        f.this.f6012b.remove(this.f6015b);
                        f.this.f6013c.remove(this.f6015b);
                    }
                }
            }
        }
    }
}
