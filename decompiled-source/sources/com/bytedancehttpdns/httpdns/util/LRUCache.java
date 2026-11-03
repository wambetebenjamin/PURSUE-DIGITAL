package com.bytedancehttpdns.httpdns.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class LRUCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private Map<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    protected V create(K k) {
        return null;
    }

    protected void entryEvicted(K k, V v) {
    }

    protected int sizeOf(K k, V v) {
        return 1;
    }

    public LRUCache(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i2;
        this.map = Collections.synchronizedMap(new LinkedHashMap(0, 0.75f, true));
    }

    public final synchronized V get(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V v = this.map.get(k);
        if (v != null) {
            this.hitCount++;
            return v;
        }
        this.missCount++;
        V create = create(k);
        if (create != null) {
            this.createCount++;
            this.size += safeSizeOf(k, create);
            this.map.put(k, create);
            trimToSize(this.maxSize);
        }
        return create;
    }

    public final synchronized V put(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.putCount++;
        this.size += safeSizeOf(k, v);
        put = this.map.put(k, v);
        if (put != null) {
            this.size -= safeSizeOf(k, put);
        }
        trimToSize(this.maxSize);
        return put;
    }

    private void trimToSize(int i2) {
        Map.Entry<K, V> next;
        while (this.size > i2 && !this.map.isEmpty() && (next = this.map.entrySet().iterator().next()) != null) {
            K key = next.getKey();
            V value = next.getValue();
            this.map.remove(key);
            this.size -= safeSizeOf(key, value);
            this.evictionCount++;
            entryEvicted(key, value);
        }
        if (this.size < 0 || (this.map.isEmpty() && this.size != 0)) {
            throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
        }
    }

    public final synchronized V remove(K k) {
        V remove;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        remove = this.map.remove(k);
        if (remove != null) {
            this.size -= safeSizeOf(k, remove);
        }
        return remove;
    }

    private int safeSizeOf(K k, V v) {
        int sizeOf = sizeOf(k, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    public final synchronized void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public synchronized Map<K, V> map() {
        return this.map;
    }

    public final synchronized String toString() {
        int i2;
        int i3;
        i2 = this.hitCount;
        i3 = this.missCount + i2;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i3 != 0 ? (i2 * 100) / i3 : 0));
    }
}
