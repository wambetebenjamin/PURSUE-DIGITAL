package com.bytedance.common.utility.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
/* compiled from: WeakContainer.java */
/* loaded from: classes2.dex */
public class e<E> implements Iterable<E> {

    /* renamed from: a  reason: collision with root package name */
    private final WeakHashMap<E, Object> f5215a = new WeakHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final Object f5216b = new Object();

    public void add(E e2) {
        if (e2 == null) {
            this.f5215a.size();
        } else {
            this.f5215a.put(e2, this.f5216b);
        }
    }

    public void clear() {
        this.f5215a.clear();
    }

    public boolean isEmpty() {
        return this.f5215a.isEmpty();
    }

    public int size() {
        return this.f5215a.size();
    }

    public void remove(E e2) {
        if (e2 == null) {
            this.f5215a.size();
        } else {
            this.f5215a.remove(e2);
        }
    }

    public E peek() {
        E e2 = null;
        if (this.f5215a.size() == 0) {
            return null;
        }
        Iterator<E> it = this.f5215a.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            E next = it.next();
            if (next != null) {
                e2 = next;
                break;
            }
        }
        this.f5215a.remove(e2);
        return e2;
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        ArrayList arrayList = new ArrayList(this.f5215a.size());
        for (E e2 : this.f5215a.keySet()) {
            if (e2 != null) {
                arrayList.add(e2);
            }
        }
        return arrayList.iterator();
    }

    public boolean contains(E e2) {
        return this.f5215a.containsKey(e2);
    }
}
