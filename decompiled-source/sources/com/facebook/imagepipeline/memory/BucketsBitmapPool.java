package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class BucketsBitmapPool extends BasePool<Bitmap> implements BitmapPool {
    @Override // com.facebook.imagepipeline.memory.BasePool
    protected int getBucketedSize(int i2) {
        return i2;
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    protected int getSizeInBytes(int i2) {
        return i2;
    }

    public BucketsBitmapPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public Bitmap alloc(int i2) {
        return Bitmap.createBitmap(1, (int) Math.ceil(i2 / 2.0d), Bitmap.Config.RGB_565);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public void free(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        bitmap.recycle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getBucketedSizeForValue(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        return bitmap.getAllocationByteCount();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.BasePool
    public boolean isReusable(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        return !bitmap.isRecycled() && bitmap.isMutable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.imagepipeline.memory.BasePool
    @Nullable
    public Bitmap getValue(Bucket<Bitmap> bucket) {
        Bitmap bitmap = (Bitmap) super.getValue((Bucket<Object>) bucket);
        if (bitmap != null) {
            bitmap.eraseColor(0);
        }
        return bitmap;
    }
}
