package com.bytedance.apm.structure;

import java.util.LinkedList;
import java.util.List;
/* compiled from: ListStack.java */
/* loaded from: classes.dex */
public class d<T> implements a<T> {

    /* renamed from: a  reason: collision with root package name */
    private List<T> f1452a = new LinkedList();

    /* renamed from: c  reason: collision with root package name */
    private int f1454c = 0;

    /* renamed from: b  reason: collision with root package name */
    int f1453b = 0;

    @Override // com.bytedance.apm.structure.a
    public void clear() {
    }

    @Override // com.bytedance.apm.structure.a
    public T pop() {
        T t = this.f1452a.get(this.f1454c - 1);
        this.f1452a.remove(this.f1454c - 1);
        this.f1453b--;
        return t;
    }

    @Override // com.bytedance.apm.structure.a
    public void push(T t) {
        this.f1452a.add(t);
        this.f1453b++;
        this.f1454c++;
    }

    @Override // com.bytedance.apm.structure.a
    public T peek() {
        return this.f1452a.get(this.f1454c - 1);
    }

    @Override // com.bytedance.apm.structure.a
    public boolean isEmpty() {
        return this.f1453b == 0;
    }

    public int size() {
        return this.f1452a.size();
    }
}
