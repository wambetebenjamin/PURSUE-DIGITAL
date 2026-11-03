package com.facebook.imagepipeline.cache;
/* loaded from: classes8.dex */
public class MemoryCacheParams {
    public final boolean mMemoryCacheOpt;
    public final boolean mOptReuseMemoryCache;
    public final int maxCacheEntries;
    public final int maxCacheEntrySize;
    public final int maxCacheSize;
    public final int maxEvictionQueueEntries;
    public final int maxEvictionQueueSize;
    public final int maxOptEntrySize;

    public MemoryCacheParams(int i2, int i3, int i4, int i5, int i6) {
        this.maxCacheSize = i2;
        this.maxCacheEntries = i3;
        this.maxEvictionQueueSize = i4;
        this.maxEvictionQueueEntries = i5;
        this.maxCacheEntrySize = i6;
        this.mMemoryCacheOpt = false;
        this.maxOptEntrySize = 0;
        this.mOptReuseMemoryCache = false;
    }

    public MemoryCacheParams(int i2, int i3, int i4, int i5, int i6, boolean z, int i7) {
        this.maxCacheSize = i2;
        this.maxCacheEntries = i3;
        this.maxEvictionQueueSize = i4;
        this.maxEvictionQueueEntries = i5;
        this.maxCacheEntrySize = i6;
        this.mMemoryCacheOpt = z;
        this.maxOptEntrySize = i7;
        this.mOptReuseMemoryCache = false;
    }

    public MemoryCacheParams(int i2, int i3, int i4, int i5, int i6, boolean z, int i7, boolean z2) {
        this.maxCacheSize = i2;
        this.maxCacheEntries = i3;
        this.maxEvictionQueueSize = i4;
        this.maxEvictionQueueEntries = i5;
        this.maxCacheEntrySize = i6;
        this.mMemoryCacheOpt = z;
        this.maxOptEntrySize = i7;
        this.mOptReuseMemoryCache = z2;
    }
}
