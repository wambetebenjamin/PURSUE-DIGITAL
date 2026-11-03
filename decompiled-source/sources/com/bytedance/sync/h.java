package com.bytedance.sync;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DelayTaskExecutor.java */
/* loaded from: classes3.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    private final Deque<Runnable> f13448a;

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f13449b;

    /* renamed from: c  reason: collision with root package name */
    private final int f13450c;

    public h() {
        this(200);
    }

    public h(int i2) {
        this.f13448a = new LinkedList();
        this.f13449b = new AtomicBoolean(false);
        this.f13450c = i2;
    }

    public void runAfterReady(Runnable runnable) {
        synchronized (this) {
            if (this.f13449b.get()) {
                runnable.run();
            } else if (this.f13448a.size() < this.f13450c) {
                this.f13448a.add(runnable);
            } else {
                com.bytedance.sync.logger.c.e("throw task " + runnable + ", because there are too many tasks in queue");
            }
        }
    }

    public void onReady() {
        synchronized (this) {
            this.f13449b.set(true);
            while (this.f13448a.size() > 0) {
                this.f13448a.pop().run();
            }
        }
    }

    public boolean hasDelayTask() {
        return this.f13448a.size() > 0;
    }
}
