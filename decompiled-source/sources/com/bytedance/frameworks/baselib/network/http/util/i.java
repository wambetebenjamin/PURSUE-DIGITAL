package com.bytedance.frameworks.baselib.network.http.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
/* compiled from: LRUCache.java */
/* loaded from: classes2.dex */
public class i<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private Map<K, V> f8340a;

    /* renamed from: b  reason: collision with root package name */
    private int f8341b;

    /* renamed from: c  reason: collision with root package name */
    private int f8342c;

    /* renamed from: d  reason: collision with root package name */
    private int f8343d;

    /* renamed from: e  reason: collision with root package name */
    private int f8344e;

    /* renamed from: f  reason: collision with root package name */
    private int f8345f;

    /* renamed from: g  reason: collision with root package name */
    private int f8346g;

    /* renamed from: h  reason: collision with root package name */
    private int f8347h;

    protected void b(K k, V v) {
    }

    protected int c(K k, V v) {
        return 1;
    }

    protected V c(K k) {
        return null;
    }

    public i(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f8342c = i2;
        this.f8340a = Collections.synchronizedMap(new LinkedHashMap(0, 0.75f, true));
    }

    public final synchronized V a(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V v = this.f8340a.get(k);
        if (v != null) {
            this.f8346g++;
            return v;
        }
        this.f8347h++;
        V c2 = c(k);
        if (c2 != null) {
            this.f8344e++;
            this.f8341b += d(k, c2);
            this.f8340a.put(k, c2);
            a(this.f8342c);
        }
        return c2;
    }

    public final synchronized V a(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.f8343d++;
        this.f8341b += d(k, v);
        put = this.f8340a.put(k, v);
        if (put != null) {
            this.f8341b -= d(k, put);
        }
        a(this.f8342c);
        return put;
    }

    private void a(int i2) {
        Map.Entry<K, V> next;
        while (this.f8341b > i2 && !this.f8340a.isEmpty() && (next = this.f8340a.entrySet().iterator().next()) != null) {
            K key = next.getKey();
            V value = next.getValue();
            this.f8340a.remove(key);
            this.f8341b -= d(key, value);
            this.f8345f++;
            b(key, value);
        }
        if (this.f8341b < 0 || (this.f8340a.isEmpty() && this.f8341b != 0)) {
            throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
        }
    }

    public final synchronized V b(K k) {
        V remove;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        remove = this.f8340a.remove(k);
        if (remove != null) {
            this.f8341b -= d(k, remove);
        }
        return remove;
    }

    private int d(K k, V v) {
        int c2 = c(k, v);
        if (c2 >= 0) {
            return c2;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    public final synchronized void a() {
        a(-1);
    }

    public final synchronized int b() {
        return this.f8341b;
    }

    public final synchronized int c() {
        return this.f8342c;
    }

    public final synchronized int d() {
        return this.f8346g;
    }

    public final synchronized int e() {
        return this.f8347h;
    }

    public final synchronized int f() {
        return this.f8344e;
    }

    public final synchronized int g() {
        return this.f8343d;
    }

    public final synchronized int h() {
        return this.f8345f;
    }

    public final synchronized Map<K, V> i() {
        return new LinkedHashMap(this.f8340a);
    }

    public synchronized Map<K, V> j() {
        return this.f8340a;
    }

    public final synchronized String toString() {
        int i2;
        int i3;
        i2 = this.f8346g;
        i3 = this.f8347h + i2;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f8342c), Integer.valueOf(this.f8346g), Integer.valueOf(this.f8347h), Integer.valueOf(i3 != 0 ? (i2 * 100) / i3 : 0));
    }
}
