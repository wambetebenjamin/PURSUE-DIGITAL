package com.facebook.cache.disk;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import java.util.Map;
/* loaded from: classes8.dex */
public class DiskWrapperCacheKey implements CacheKey {
    private final CacheKey cacheKey;
    private final Map<String, String> extraInfo;

    public DiskWrapperCacheKey(CacheKey cacheKey, Map<String, String> map) {
        this.cacheKey = cacheKey;
        this.extraInfo = map;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        return this.cacheKey.containsUri(uri);
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.cacheKey.getUriString();
    }

    @Override // com.facebook.cache.common.CacheKey
    public int hashCode() {
        return this.cacheKey.hashCode();
    }

    @Override // com.facebook.cache.common.CacheKey
    public String toString() {
        return this.cacheKey.toString();
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean equals(Object obj) {
        return this.cacheKey.equals(obj);
    }

    public Map<String, String> getExtraInfo() {
        return this.extraInfo;
    }

    public CacheKey getInnerCacheKey() {
        return this.cacheKey;
    }
}
