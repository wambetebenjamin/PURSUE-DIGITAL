package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.memory.MemoryTrimType;
/* loaded from: classes8.dex */
public class LruBitmapPool implements BitmapPool {
    private int mCurrentSize;
    private int mMaxBitmapSize;
    private final int mMaxPoolSize;
    private final PoolStatsTracker mPoolStatsTracker;
    protected final PoolBackend<Bitmap> mStrategy = new BitmapPoolBackend();

    public LruBitmapPool(int i2, int i3, PoolStatsTracker poolStatsTracker) {
        this.mMaxPoolSize = i2;
        this.mMaxBitmapSize = i3;
        this.mPoolStatsTracker = poolStatsTracker;
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType memoryTrimType) {
        trimTo((int) (this.mMaxPoolSize * (1.0d - memoryTrimType.getSuggestedTrimRatio())));
    }

    private synchronized void trimTo(int i2) {
        Bitmap pop;
        while (this.mCurrentSize > i2 && (pop = this.mStrategy.pop()) != null) {
            int size = this.mStrategy.getSize(pop);
            this.mCurrentSize -= size;
            this.mPoolStatsTracker.onFree(size);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.common.memory.Pool
    public synchronized Bitmap get(int i2) {
        int i3 = this.mCurrentSize;
        int i4 = this.mMaxPoolSize;
        if (i3 > i4) {
            trimTo(i4);
        }
        Bitmap bitmap = this.mStrategy.get(i2);
        if (bitmap != null) {
            int size = this.mStrategy.getSize(bitmap);
            this.mCurrentSize -= size;
            this.mPoolStatsTracker.onValueReuse(size);
            return bitmap;
        }
        return alloc(i2);
    }

    private Bitmap alloc(int i2) {
        this.mPoolStatsTracker.onAlloc(i2);
        return Bitmap.createBitmap(1, i2, Bitmap.Config.ALPHA_8);
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public synchronized void release(Bitmap bitmap) {
        int size = this.mStrategy.getSize(bitmap);
        if (size <= this.mMaxBitmapSize) {
            this.mPoolStatsTracker.onValueRelease(size);
            this.mStrategy.put(bitmap);
            this.mCurrentSize += size;
        }
    }
}
