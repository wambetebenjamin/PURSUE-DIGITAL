package com.bytedance.frameworks.baselib.network.dispatcher;

import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: IApiTask.java */
/* loaded from: classes2.dex */
public abstract class e implements f, Comparable<f>, Runnable {

    /* renamed from: c  reason: collision with root package name */
    protected final AtomicBoolean f7523c = new AtomicBoolean(false);

    /* renamed from: d  reason: collision with root package name */
    protected final AtomicBoolean f7524d = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    protected int f7525e = 0;

    /* renamed from: f  reason: collision with root package name */
    protected int f7526f;

    @Deprecated
    public boolean f() {
        return false;
    }

    public boolean g() {
        return false;
    }

    public void h() {
        this.f7524d.compareAndSet(false, true);
    }

    public boolean i() {
        return this.f7524d.get();
    }

    public e b(int i2) {
        this.f7526f = i2;
        return this;
    }

    public int j() {
        return this.f7525e;
    }
}
