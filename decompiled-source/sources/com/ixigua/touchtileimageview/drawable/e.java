package com.ixigua.touchtileimageview.drawable;

import androidx.core.util.Pools;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: CountSynchronizedPool.java */
/* loaded from: classes9.dex */
class e<T> extends Pools.SynchronizedPool<T> {

    /* renamed from: a  reason: collision with root package name */
    private AtomicInteger f17112a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(int i2) {
        super(i2);
        this.f17112a = new AtomicInteger();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f17112a.get();
    }

    @Override // androidx.core.util.Pools.SynchronizedPool, androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
    public T acquire() {
        T t = (T) super.acquire();
        if (t != null) {
            this.f17112a.getAndDecrement();
        }
        return t;
    }

    @Override // androidx.core.util.Pools.SynchronizedPool, androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
    public boolean release(T t) {
        boolean release = super.release(t);
        if (release) {
            this.f17112a.getAndIncrement();
        }
        return release;
    }
}
