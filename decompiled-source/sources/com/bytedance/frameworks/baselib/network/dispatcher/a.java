package com.bytedance.frameworks.baselib.network.dispatcher;

import android.os.Process;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import com.bytedance.frameworks.baselib.network.dispatcher.f;
import java.util.concurrent.BlockingQueue;
/* compiled from: ApiLocalDispatcher.java */
/* loaded from: classes2.dex */
class a extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7512a = "ApiLocalDispatcher";

    /* renamed from: b  reason: collision with root package name */
    private final BlockingQueue<f> f7513b;

    /* renamed from: c  reason: collision with root package name */
    private final BlockingQueue<f> f7514c;

    /* renamed from: d  reason: collision with root package name */
    private volatile boolean f7515d;

    public a(BlockingQueue<f> blockingQueue, BlockingQueue<f> blockingQueue2) {
        super("ApiLocalDispatcher-Thread");
        this.f7515d = false;
        this.f7513b = blockingQueue;
        this.f7514c = blockingQueue2;
    }

    public void a() {
        this.f7515d = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                f take = this.f7513b.take();
                c cVar = take instanceof c ? (c) take : null;
                if (cVar != null) {
                    String name = Thread.currentThread().getName();
                    String a2 = cVar.a();
                    try {
                    } catch (Throwable th) {
                        Logger.e(f7512a, "Unhandled exception: " + th);
                    }
                    if (!cVar.i()) {
                        if (!o.isEmpty(a2) && !o.isEmpty(name)) {
                            Thread.currentThread().setName("ApiLocalDispatcher-" + a2);
                        }
                        if (Logger.debug()) {
                            Logger.d(f7512a, "run4Local " + a2 + ", queue size: " + this.f7513b.size() + " " + this.f7514c.size());
                        }
                        if (!cVar.f()) {
                            if (cVar.c() == f.a.IMMEDIATE) {
                                com.bytedance.common.utility.concurrent.e.submitRunnable(cVar);
                            } else {
                                this.f7514c.add(cVar);
                            }
                        }
                        if (!o.isEmpty(a2) && !o.isEmpty(name)) {
                            Thread.currentThread().setName(name);
                        }
                    }
                }
            } catch (InterruptedException unused) {
                if (this.f7515d) {
                    return;
                }
            }
        }
    }
}
