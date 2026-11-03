package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool;
/* loaded from: classes8.dex */
public class GenericByteArrayPool extends BasePool<byte[]> implements ByteArrayPool {
    private final int[] mBucketSizes;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getSizeInBytes(int i2) {
        return i2;
    }

    public GenericByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        this.mBucketSizes = new int[sparseIntArray.size()];
        for (int i2 = 0; i2 < sparseIntArray.size(); i2++) {
            this.mBucketSizes[i2] = sparseIntArray.keyAt(i2);
        }
        initialize();
    }

    public int getMinBufferSize() {
        return this.mBucketSizes[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public byte[] alloc(int i2) {
        return new byte[i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public void free(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
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
    public int getBucketedSizeForValue(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        return bArr.length;
    }
}
