package com.bytedance.geckox.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* compiled from: PiecemealSerialExecutor.java */
/* loaded from: classes2.dex */
public class m implements Executor {

    /* renamed from: a  reason: collision with root package name */
    private static volatile m f9368a;

    /* renamed from: b  reason: collision with root package name */
    private Executor f9369b = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bytedance.geckox.utils.m.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("gecko-piecemeal-thread");
            return thread;
        }
    });

    private m() {
    }

    public static m a() {
        if (f9368a == null) {
            synchronized (m.class) {
                if (f9368a == null) {
                    f9368a = new m();
                }
            }
        }
        return f9368a;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f9369b.execute(runnable);
    }
}
