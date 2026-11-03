package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.MemoryCacheHitTracker;
/* loaded from: classes8.dex */
public class InstrumentedMemoryCache<K, V> implements MemoryCache<K, V> {
    private final MemoryCache<K, V> mDelegate;
    private final MemoryCacheTracker mTracker;

    public InstrumentedMemoryCache(MemoryCache<K, V> memoryCache, MemoryCacheTracker memoryCacheTracker) {
        this.mDelegate = memoryCache;
        this.mTracker = memoryCacheTracker;
    }

    public MemoryCache<K, V> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> get(K k) {
        CloseableReference<V> closeableReference = this.mDelegate.get(k);
        if (closeableReference == null) {
            this.mTracker.onCacheMiss();
        } else {
            this.mTracker.onCacheHit(k);
            V v = closeableReference.get();
            if (v instanceof MemoryCacheHitTracker) {
                ((MemoryCacheHitTracker) v).hitMemoryCache();
            }
        }
        return closeableReference;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> cache(K k, CloseableReference<V> closeableReference) {
        this.mTracker.onCachePut();
        return this.mDelegate.cache(k, closeableReference);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        return this.mDelegate.removeAll(predicate);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public boolean contains(Predicate<K> predicate) {
        return this.mDelegate.contains(predicate);
    }
}
