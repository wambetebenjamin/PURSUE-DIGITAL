package com.bytedance.apm.util;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/* compiled from: TopK.java */
/* loaded from: classes.dex */
public class ak<E extends Comparable> {

    /* renamed from: a  reason: collision with root package name */
    private PriorityQueue<E> f1672a;

    /* renamed from: b  reason: collision with root package name */
    private int f1673b;

    public ak(int i2) {
        if (i2 <= 0) {
            throw new IllegalStateException();
        }
        this.f1673b = i2;
        this.f1672a = new PriorityQueue<>(i2, new Comparator<E>() { // from class: com.bytedance.apm.util.ak.1
            @Override // java.util.Comparator
            public int compare(E e2, E e3) {
                return e2.compareTo(e3);
            }
        });
    }

    public void add(E e2) {
        if (this.f1672a.size() < this.f1673b) {
            this.f1672a.add(e2);
        } else if (e2.compareTo(this.f1672a.peek()) > 0) {
            this.f1672a.poll();
            this.f1672a.add(e2);
        }
    }

    public List<E> toList() {
        return new ArrayList(this.f1672a);
    }

    public List<E> sortedList() {
        ArrayList arrayList = new ArrayList(this.f1672a);
        Collections.sort(arrayList);
        return arrayList;
    }

    public int size() {
        return this.f1672a.size();
    }

    public void clear() {
        this.f1672a.clear();
    }
}
