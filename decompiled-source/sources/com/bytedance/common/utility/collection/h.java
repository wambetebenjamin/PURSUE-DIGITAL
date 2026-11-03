package com.bytedance.common.utility.collection;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
/* compiled from: WeakValueMap.java */
/* loaded from: classes2.dex */
public class h<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<K, a<K, V>> f5219a = new HashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final ReferenceQueue<V> f5220b = new ReferenceQueue<>();

    /* compiled from: WeakValueMap.java */
    /* loaded from: classes2.dex */
    public static final class a<K, V> extends WeakReference<V> {

        /* renamed from: a  reason: collision with root package name */
        final K f5221a;

        public a(K k, V v, ReferenceQueue<V> referenceQueue) {
            super(v, referenceQueue);
            this.f5221a = k;
        }
    }

    private void a() {
        while (true) {
            a aVar = (a) this.f5220b.poll();
            if (aVar == null) {
                return;
            }
            if (!this.f5219a.isEmpty()) {
                this.f5219a.remove(aVar.f5221a);
            }
        }
    }

    public V get(K k) {
        a<K, V> aVar;
        a();
        if (k == null || (aVar = this.f5219a.get(k)) == null) {
            return null;
        }
        return (V) aVar.get();
    }

    public void put(K k, V v) {
        if (k == null || v == null) {
            return;
        }
        this.f5219a.remove(k);
        a();
        this.f5219a.put(k, new a<>(k, v, this.f5220b));
    }

    public void remove(K k) {
        a();
        if (k != null) {
            this.f5219a.remove(k);
        }
    }

    public void clear() {
        this.f5219a.clear();
        a();
    }

    public boolean isEmpty() {
        a();
        return this.f5219a.isEmpty();
    }

    public int size() {
        a();
        return this.f5219a.size();
    }
}
