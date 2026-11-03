package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
/* loaded from: classes8.dex */
public class ArtBitmapFactory extends PlatformBitmapFactory {
    private final BitmapPool mBitmapPool;

    public ArtBitmapFactory(BitmapPool bitmapPool) {
        this.mBitmapPool = bitmapPool;
    }

    public BitmapPool getBitmapPool() {
        return this.mBitmapPool;
    }

    @Override // com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
    public CloseableReference<Bitmap> createBitmapInternal(int i2, int i3, Bitmap.Config config) {
        Bitmap bitmap = this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(i2, i3, config));
        Preconditions.checkArgument(bitmap.getAllocationByteCount() >= (i2 * i3) * BitmapUtil.getPixelSizeForBitmapConfig(config));
        bitmap.reconfigure(i2, i3, config);
        return CloseableReference.of(bitmap, this.mBitmapPool);
    }
}
