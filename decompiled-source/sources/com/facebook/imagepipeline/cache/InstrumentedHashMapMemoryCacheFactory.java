package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
/* loaded from: classes8.dex */
public class InstrumentedHashMapMemoryCacheFactory {
    public static InstrumentedMemoryCache<CacheKey, Bitmap> get(HashMapMemoryCache<CacheKey, Bitmap> hashMapMemoryCache, final ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerHashMapMemoryCache(hashMapMemoryCache);
        return new InstrumentedMemoryCache<>(hashMapMemoryCache, new MemoryCacheTracker<CacheKey>() { // from class: com.facebook.imagepipeline.cache.InstrumentedHashMapMemoryCacheFactory.1
            @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
            public void onCacheHit(CacheKey cacheKey) {
                ImageCacheStatsTracker.this.onBitmapCacheHit(cacheKey);
            }

            @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
            public void onCacheMiss() {
                ImageCacheStatsTracker.this.onBitmapCacheMiss();
            }

            @Override // com.facebook.imagepipeline.cache.MemoryCacheTracker
            public void onCachePut() {
                ImageCacheStatsTracker.this.onBitmapCachePut();
            }
        });
    }
}
