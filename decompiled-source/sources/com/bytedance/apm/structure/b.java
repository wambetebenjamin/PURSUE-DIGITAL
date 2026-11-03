package com.bytedance.apm.structure;

import java.util.LinkedList;
/* compiled from: LimitQueue.java */
/* loaded from: classes.dex */
public class b<T> {

    /* renamed from: a  reason: collision with root package name */
    private LinkedList<T> f1449a = new LinkedList<>();

    /* renamed from: b  reason: collision with root package name */
    private int f1450b;

    public b(int i2) {
        this.f1450b = i2;
    }

    public void enqueue(T t) {
        if (this.f1449a.size() > this.f1450b) {
            this.f1449a.removeFirst();
        }
        this.f1449a.addLast(t);
    }

    public LinkedList<T> toList() {
        return this.f1449a;
    }

    public boolean isEmpty() {
        return this.f1449a.isEmpty();
    }

    public int size() {
        return this.f1449a.size();
    }

    public void clear() {
        this.f1449a.clear();
    }
}
