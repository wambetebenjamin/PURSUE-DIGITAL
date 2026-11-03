package com.bytedance.push.utils;

import java.util.LinkedList;
/* compiled from: LimitQueue.java */
/* loaded from: classes2.dex */
public class g<E> {

    /* renamed from: a  reason: collision with root package name */
    private int f12032a;

    /* renamed from: b  reason: collision with root package name */
    private LinkedList<E> f12033b = new LinkedList<>();

    public g(int i2) {
        this.f12032a = i2;
    }

    public void offer(E e2) {
        if (this.f12033b.size() >= this.f12032a) {
            this.f12033b.poll();
        }
        this.f12033b.offer(e2);
    }

    public E get(int i2) {
        return this.f12033b.get(i2);
    }

    public E getLast() {
        return this.f12033b.getLast();
    }

    public E getFirst() {
        return this.f12033b.getFirst();
    }

    public int getLimit() {
        return this.f12032a;
    }

    public int size() {
        return this.f12033b.size();
    }
}
