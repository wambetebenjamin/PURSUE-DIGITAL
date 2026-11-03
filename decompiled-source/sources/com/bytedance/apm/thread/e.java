package com.bytedance.apm.thread;

import android.os.Build;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
/* compiled from: ThreadWithBlockingQueue.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final Thread f1486a;

    /* renamed from: b  reason: collision with root package name */
    private final BlockingQueue<Runnable> f1487b;

    public e(String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f1487b = new LinkedTransferQueue();
        } else {
            this.f1487b = new LinkedBlockingQueue();
        }
        this.f1486a = new Thread(new Runnable() { // from class: com.bytedance.apm.thread.e.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        ((Runnable) e.this.f1487b.take()).run();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }, str);
    }

    public void a() {
        this.f1486a.start();
    }

    public final boolean a(Runnable runnable) {
        return this.f1487b.offer(runnable);
    }
}
