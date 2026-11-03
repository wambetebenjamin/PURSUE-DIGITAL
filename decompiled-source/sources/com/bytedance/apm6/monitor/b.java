package com.bytedance.apm6.monitor;

import android.os.Build;
import com.bytedance.apm6.util.l;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
/* compiled from: Monitor.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2338a = "APM-Monitor";

    /* renamed from: b  reason: collision with root package name */
    private static final String f2339b = "APM-Monitor";

    /* renamed from: c  reason: collision with root package name */
    private static List<d> f2340c = new CopyOnWriteArrayList();

    /* renamed from: d  reason: collision with root package name */
    private static final BlockingQueue<c> f2341d;

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f2342e;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            f2341d = new LinkedTransferQueue();
        } else {
            f2341d = new LinkedBlockingQueue();
        }
        f2342e = false;
    }

    public static void registerHandler(d dVar) {
        if (f2340c.contains(dVar)) {
            return;
        }
        f2340c.add(dVar);
    }

    public static void record(c cVar) {
        if (cVar == null) {
            return;
        }
        f2341d.offer(cVar);
        if (f2342e) {
            return;
        }
        c();
    }

    private static synchronized void c() {
        synchronized (b.class) {
            if (f2342e) {
                return;
            }
            f2342e = true;
            new Thread(new Runnable() { // from class: com.bytedance.apm6.monitor.b.1
                @Override // java.lang.Runnable
                public void run() {
                    while (true) {
                        l.beginSection("APM-Consumers");
                        try {
                            c cVar = (c) b.f2341d.take();
                            for (d dVar : b.f2340c) {
                                l.beginSection("APM-Handler");
                                if (cVar.isValid()) {
                                    dVar.handle(cVar);
                                } else if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                                    com.bytedance.apm6.util.log.b.w("APM-Monitor", "monitorable invalid. ignored. " + cVar);
                                }
                                l.endSection();
                            }
                        } catch (Throwable th) {
                            com.bytedance.apm6.util.log.b.e("APM", "Oh, Damn it!!!", th);
                        }
                        l.endSection();
                    }
                }
            }, "APM-Monitor").start();
        }
    }
}
