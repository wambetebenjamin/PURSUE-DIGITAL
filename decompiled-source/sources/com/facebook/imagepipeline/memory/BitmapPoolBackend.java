package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.facebook.imageutils.BitmapUtil;
/* loaded from: classes8.dex */
public class BitmapPoolBackend extends LruBucketsPoolBackend<Bitmap> {
    private static final String TAG = "BitmapPoolBackend";

    @Override // com.facebook.imagepipeline.memory.LruBucketsPoolBackend, com.facebook.imagepipeline.memory.PoolBackend
    public void put(Bitmap bitmap) {
        if (isReusable(bitmap)) {
            super.put((BitmapPoolBackend) bitmap);
        }
    }

    @Override // com.facebook.imagepipeline.memory.LruBucketsPoolBackend, com.facebook.imagepipeline.memory.PoolBackend
    public Bitmap get(int i2) {
        Bitmap bitmap = (Bitmap) super.get(i2);
        if (bitmap == null || !isReusable(bitmap)) {
            return null;
        }
        bitmap.eraseColor(0);
        return bitmap;
    }

    @Override // com.facebook.imagepipeline.memory.PoolBackend
    public int getSize(Bitmap bitmap) {
        return BitmapUtil.getSizeInBytes(bitmap);
    }

    protected boolean isReusable(Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        if (bitmap.isRecycled()) {
            FLog.wtf(TAG, "Cannot reuse a recycled bitmap: %s", bitmap);
            return false;
        } else if (bitmap.isMutable()) {
            return true;
        } else {
            FLog.wtf(TAG, "Cannot reuse an immutable bitmap: %s", bitmap);
            return false;
        }
    }
}
