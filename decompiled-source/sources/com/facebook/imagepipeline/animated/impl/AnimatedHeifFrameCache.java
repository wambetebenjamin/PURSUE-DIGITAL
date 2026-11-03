package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.HashMapMemoryCache;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class AnimatedHeifFrameCache {
    private final HashMapMemoryCache<CacheKey, Bitmap> mAnimatedHeifBackingCache;
    private final CacheKey mImageCacheKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class FrameKey implements CacheKey {
        private final int mFrameIndex;
        private final CacheKey mImageCacheKey;

        @Override // com.facebook.cache.common.CacheKey
        public String getUriString() {
            return null;
        }

        public FrameKey(CacheKey cacheKey, int i2) {
            this.mImageCacheKey = cacheKey;
            this.mFrameIndex = i2;
        }

        @Override // com.facebook.cache.common.CacheKey
        public String toString() {
            return Objects.toStringHelper(this).add("imageCacheKey", this.mImageCacheKey).add("frameIndex", this.mFrameIndex).toString();
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FrameKey) {
                FrameKey frameKey = (FrameKey) obj;
                return this.mFrameIndex == frameKey.mFrameIndex && this.mImageCacheKey.equals(frameKey.mImageCacheKey);
            }
            return false;
        }

        @Override // com.facebook.cache.common.CacheKey
        public int hashCode() {
            return (this.mImageCacheKey.hashCode() * 1013) + this.mFrameIndex;
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean containsUri(Uri uri) {
            return this.mImageCacheKey.containsUri(uri);
        }
    }

    public AnimatedHeifFrameCache(CacheKey cacheKey, HashMapMemoryCache<CacheKey, Bitmap> hashMapMemoryCache) {
        this.mImageCacheKey = cacheKey;
        this.mAnimatedHeifBackingCache = hashMapMemoryCache;
    }

    @Nullable
    public CloseableReference<Bitmap> cache(int i2, CloseableReference<Bitmap> closeableReference) {
        if (CloseableReference.isValid(closeableReference)) {
            CloseableReference<Bitmap> cloneOrNull = CloseableReference.cloneOrNull(closeableReference);
            this.mAnimatedHeifBackingCache.cache(keyFor(i2), cloneOrNull);
            return cloneOrNull;
        }
        return null;
    }

    @Nullable
    public CloseableReference<Bitmap> get(int i2) {
        return this.mAnimatedHeifBackingCache.get(keyFor(i2));
    }

    public boolean contains(int i2) {
        return CloseableReference.isValid(this.mAnimatedHeifBackingCache.get(keyFor(i2)));
    }

    public void clear() {
        this.mAnimatedHeifBackingCache.clearForCacheKey(this.mImageCacheKey);
    }

    private FrameKey keyFor(int i2) {
        return new FrameKey(this.mImageCacheKey, i2);
    }
}
