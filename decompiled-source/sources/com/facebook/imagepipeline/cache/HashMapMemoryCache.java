package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class HashMapMemoryCache<K, V> implements MemoryCache<K, V> {
    final Map<K, CloseableReference<V>> mCachedEntries = new ConcurrentHashMap();

    /* loaded from: classes8.dex */
    public static class Entry<K, V> {
        public final K key;
        public final CloseableReference<V> valueRef;

        private Entry(K k, CloseableReference<V> closeableReference) {
            this.key = (K) Preconditions.checkNotNull(k);
            this.valueRef = (CloseableReference) Preconditions.checkNotNull(CloseableReference.cloneOrNull(closeableReference));
        }
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> cache(K k, CloseableReference<V> closeableReference) {
        return this.mCachedEntries.put(k, closeableReference);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> get(K k) {
        Preconditions.checkNotNull(k);
        return this.mCachedEntries.get(k);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<K, CloseableReference<V>>> it = this.mCachedEntries.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, CloseableReference<V>> next = it.next();
            if (predicate == null || predicate.apply(next.getKey())) {
                arrayList.add(next.getValue());
                next.getValue().close();
                it.remove();
            }
        }
        return arrayList.size();
    }

    public void clearForCacheKey(final CacheKey cacheKey) {
        if (cacheKey == null || cacheKey.getUriString().isEmpty()) {
            return;
        }
        removeAll(new Predicate<K>() { // from class: com.facebook.imagepipeline.cache.HashMapMemoryCache.1
            @Override // com.facebook.common.internal.Predicate
            public boolean apply(K k) {
                return k.toString().contains(cacheKey.getUriString());
            }
        });
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized boolean contains(Predicate<K> predicate) {
        ArrayList arrayList;
        arrayList = new ArrayList(this.mCachedEntries.entrySet().size());
        for (Map.Entry<K, CloseableReference<V>> entry : this.mCachedEntries.entrySet()) {
            if (predicate == null || predicate.apply(entry.getKey())) {
                arrayList.add(entry);
            }
        }
        return !arrayList.isEmpty();
    }
}
