package com.bytedance.apm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: LogBypassStoreEventManager.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private volatile ExecutorService f1469a;

    private c() {
        this.f1469a = Executors.newFixedThreadPool(1, new ThreadFactory() { // from class: com.bytedance.apm.thread.c.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "Apm_Log_Bypass_Store");
            }
        });
    }

    public static c getInstance() {
        if (com.bytedance.apm.c.isInternalTest()) {
            return a.f1471a;
        }
        return null;
    }

    public void post(Runnable runnable) {
        this.f1469a.submit(runnable);
    }

    /* compiled from: LogBypassStoreEventManager.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        static final c f1471a = new c();

        private a() {
        }
    }
}
