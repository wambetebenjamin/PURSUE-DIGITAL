package com.ixigua.touchtileimageview.drawable;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
/* compiled from: SetBlockingQueue.java */
/* loaded from: classes9.dex */
class j<T> extends LinkedBlockingQueue<T> {

    /* renamed from: a  reason: collision with root package name */
    private Set<T> f17121a = Collections.newSetFromMap(new ConcurrentHashMap());

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean add(T t) {
        if (this.f17121a.contains(t)) {
            return false;
        }
        this.f17121a.add(t);
        return super.add(t);
    }

    @Override // java.util.concurrent.LinkedBlockingQueue, java.util.concurrent.BlockingQueue
    public T take() {
        T t = (T) super.take();
        this.f17121a.remove(t);
        return t;
    }
}
