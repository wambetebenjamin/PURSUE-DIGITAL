package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool;
/* loaded from: classes8.dex */
public abstract class MemoryChunkPool extends BasePool<MemoryChunk> {
    private final int[] mBucketSizes;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public abstract MemoryChunk alloc(int i2);

    @Override // com.facebook.imagepipeline.memory.BasePool
    protected int getSizeInBytes(int i2) {
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        this.mBucketSizes = new int[sparseIntArray.size()];
        int i2 = 0;
        while (true) {
            int[] iArr = this.mBucketSizes;
            if (i2 < iArr.length) {
                iArr[i2] = sparseIntArray.keyAt(i2);
                i2++;
            } else {
                initialize();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinBufferSize() {
        return this.mBucketSizes[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public void free(MemoryChunk memoryChunk) {
        Preconditions.checkNotNull(memoryChunk);
        memoryChunk.close();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    protected int getBucketedSize(int i2) {
        int[] iArr;
        if (i2 <= 0) {
            throw new BasePool.InvalidSizeException(Integer.valueOf(i2));
        }
        for (int i3 : this.mBucketSizes) {
            if (i3 >= i2) {
                return i3;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getBucketedSizeForValue(MemoryChunk memoryChunk) {
        Preconditions.checkNotNull(memoryChunk);
        return memoryChunk.getSize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public boolean isReusable(MemoryChunk memoryChunk) {
        Preconditions.checkNotNull(memoryChunk);
        return !memoryChunk.isClosed();
    }
}
