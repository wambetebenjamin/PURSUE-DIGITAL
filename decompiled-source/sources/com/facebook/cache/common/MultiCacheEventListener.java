package com.facebook.cache.common;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes8.dex */
public class MultiCacheEventListener implements CacheEventListener {
    private List<CacheEventListener> mCacheEventListeners = new CopyOnWriteArrayList();

    public void addCacheEventListener(CacheEventListener cacheEventListener) {
        this.mCacheEventListeners.add(cacheEventListener);
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onHit(CacheEvent cacheEvent) {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onHit(cacheEvent);
        }
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onMiss(CacheEvent cacheEvent) {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onMiss(cacheEvent);
        }
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onWriteAttempt(CacheEvent cacheEvent) {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onWriteAttempt(cacheEvent);
        }
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onWriteSuccess(CacheEvent cacheEvent) {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onWriteSuccess(cacheEvent);
        }
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onReadException(CacheEvent cacheEvent) {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onReadException(cacheEvent);
        }
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onWriteException(CacheEvent cacheEvent) {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onWriteException(cacheEvent);
        }
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onEviction(CacheEvent cacheEvent) {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onEviction(cacheEvent);
        }
    }

    @Override // com.facebook.cache.common.CacheEventListener
    public void onCleared() {
        for (CacheEventListener cacheEventListener : this.mCacheEventListeners) {
            cacheEventListener.onCleared();
        }
    }
}
