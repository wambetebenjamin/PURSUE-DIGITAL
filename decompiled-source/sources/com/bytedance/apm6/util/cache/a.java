package com.bytedance.apm6.util.cache;

import java.util.concurrent.ConcurrentLinkedQueue;
/* compiled from: LimitedQueue.java */
/* loaded from: classes.dex */
public class a<T> {

    /* renamed from: a  reason: collision with root package name */
    private final int f2379a;

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentLinkedQueue<T> f2380b = new ConcurrentLinkedQueue<>();

    /* renamed from: c  reason: collision with root package name */
    private InterfaceC0034a<T> f2381c;

    /* compiled from: LimitedQueue.java */
    /* renamed from: com.bytedance.apm6.util.cache.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0034a<T> {
        void onEvict(T t);
    }

    public a(int i2) {
        this.f2379a = i2;
    }

    public void setEvictListener(InterfaceC0034a<T> interfaceC0034a) {
        this.f2381c = interfaceC0034a;
    }

    public void push(T t) {
        this.f2380b.add(t);
        if (this.f2380b.size() > this.f2379a) {
            T poll = this.f2380b.poll();
            InterfaceC0034a<T> interfaceC0034a = this.f2381c;
            if (interfaceC0034a != null) {
                interfaceC0034a.onEvict(poll);
            }
        }
    }

    public T pop() {
        return this.f2380b.poll();
    }

    public T peek() {
        return this.f2380b.peek();
    }

    public boolean isEmpty() {
        return this.f2380b.isEmpty();
    }

    public void clear() {
        this.f2380b.clear();
    }
}
