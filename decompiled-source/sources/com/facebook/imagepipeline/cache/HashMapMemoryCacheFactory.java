package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
/* loaded from: classes8.dex */
public class HashMapMemoryCacheFactory {
    public static HashMapMemoryCache<CacheKey, Bitmap> get() {
        return new HashMapMemoryCache<>();
    }
}
