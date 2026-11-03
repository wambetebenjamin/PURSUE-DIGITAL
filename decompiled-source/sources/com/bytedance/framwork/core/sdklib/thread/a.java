package com.bytedance.framwork.core.sdklib.thread;

import android.os.Message;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: AsyncEventManager.java */
/* loaded from: classes2.dex */
public class a {
    public static final String ASYNC_EVENT_MANAGER_THREAD = "AsyncEventManager-Thread";

    /* renamed from: d  reason: collision with root package name */
    private static long f8728d = 30000;

    /* renamed from: a  reason: collision with root package name */
    CopyOnWriteArraySet<b> f8729a;

    /* renamed from: b  reason: collision with root package name */
    private d f8730b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f8731c;

    /* renamed from: e  reason: collision with root package name */
    private final Runnable f8732e;

    private a() {
        this.f8731c = true;
        this.f8732e = new Runnable() { // from class: com.bytedance.framwork.core.sdklib.thread.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator<b> it = a.this.f8729a.iterator();
                    while (it.hasNext()) {
                        it.next().onTimeEvent(System.currentTimeMillis());
                    }
                    if (a.this.f8731c) {
                        a.this.f8730b.postDelayed(this, a.f8728d);
                    }
                } catch (OutOfMemoryError unused) {
                }
            }
        };
        this.f8729a = new CopyOnWriteArraySet<>();
        d dVar = new d("AsyncEventManager-Thread");
        this.f8730b = dVar;
        dVar.start();
    }

    /* compiled from: AsyncEventManager.java */
    /* renamed from: com.bytedance.framwork.core.sdklib.thread.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static final class C0124a {

        /* renamed from: a  reason: collision with root package name */
        static final a f8734a = new a();

        private C0124a() {
        }
    }

    public static a getInstance() {
        return C0124a.f8734a;
    }

    public void sendMessage(Message message) {
        this.f8730b.sendMessage(message);
    }

    public void post(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.f8730b.post(runnable);
    }

    public void postDelay(Runnable runnable, long j2) {
        if (runnable == null) {
            return;
        }
        this.f8730b.postDelayed(runnable, j2);
    }

    public void removeCallbacks(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        this.f8730b.removeCallbacks(runnable);
    }

    public void addTimeTask(b bVar) {
        if (bVar != null) {
            try {
                this.f8729a.add(bVar);
                if (this.f8731c) {
                    this.f8730b.removeCallbacks(this.f8732e);
                    this.f8730b.postDelayed(this.f8732e, f8728d);
                }
            } catch (Throwable unused) {
            }
        }
    }
}
