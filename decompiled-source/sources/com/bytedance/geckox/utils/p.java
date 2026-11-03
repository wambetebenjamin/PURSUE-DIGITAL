package com.bytedance.geckox.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: ThreadPool.java */
/* loaded from: classes2.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private static Executor f9373a;

    /* renamed from: b  reason: collision with root package name */
    private static Executor f9374b;

    public static Executor a() {
        if (f9374b == null) {
            f9374b = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.geckox.utils.p.1
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setName("gecko-update-thread");
                    thread.setPriority(3);
                    return thread;
                }
            });
        }
        return f9374b;
    }

    public static Executor b() {
        if (f9373a == null) {
            f9373a = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.geckox.utils.p.2
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setName("gecko-check-update-thread");
                    thread.setPriority(3);
                    return thread;
                }
            });
        }
        return f9373a;
    }

    public static Executor a(final int i2) {
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.geckox.utils.p.3
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("gecko-update-thread");
                thread.setPriority(i2);
                return thread;
            }
        });
    }

    public static Executor c() {
        return Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.geckox.utils.p.4
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("gecko-check-update-thread");
                thread.setPriority(3);
                return thread;
            }
        });
    }
}
