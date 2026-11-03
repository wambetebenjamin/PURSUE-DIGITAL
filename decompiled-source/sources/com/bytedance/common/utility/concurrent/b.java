package com.bytedance.common.utility.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: SimpleThreadFactory.java */
/* loaded from: classes2.dex */
public class b implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    private String f5223a;

    /* renamed from: b  reason: collision with root package name */
    private AtomicInteger f5224b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f5225c;

    public b(String str) {
        this(str, false);
    }

    public b(String str, boolean z) {
        this.f5224b = new AtomicInteger();
        this.f5223a = str;
        this.f5225c = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f5223a + "-" + this.f5224b.incrementAndGet());
        if (!this.f5225c) {
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
        }
        return thread;
    }
}
