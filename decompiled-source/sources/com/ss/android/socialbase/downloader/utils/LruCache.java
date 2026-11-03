package com.ss.android.socialbase.downloader.utils;

import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class LruCache<K, T> extends LinkedHashMap<K, T> {
    private static final int DEFAULT_SIZE = 4;
    private int mMaxSize;

    public LruCache() {
        this(4, 4);
    }

    public LruCache(int i2, int i3) {
        this(i2, i3, true);
    }

    public LruCache(int i2, int i3, boolean z) {
        super(i2, 0.75f, z);
        setMaxSize(i3);
    }

    public void setMaxSize(int i2) {
        this.mMaxSize = i2;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, T> entry) {
        return size() > this.mMaxSize;
    }
}
