package com.bytedance.geckox.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: IOSerialExecutor.java */
/* loaded from: classes2.dex */
public class i implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private static volatile i f9363a;

    /* renamed from: b  reason: collision with root package name */
    private Executor f9364b = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.geckox.utils.i.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("gecko-io-thread");
            thread.setPriority(3);
            return thread;
        }
    });

    private i() {
    }

    public static i a() {
        if (f9363a == null) {
            synchronized (i.class) {
                if (f9363a == null) {
                    f9363a = new i();
                }
            }
        }
        return f9363a;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f9364b.execute(runnable);
    }
}
