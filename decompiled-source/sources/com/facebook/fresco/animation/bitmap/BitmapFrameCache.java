package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public interface BitmapFrameCache {

    /* loaded from: classes8.dex */
    public interface FrameCacheListener {
        void onFrameCached(BitmapFrameCache bitmapFrameCache, int i2);

        void onFrameEvicted(BitmapFrameCache bitmapFrameCache, int i2);
    }

    void clear(ImageFormat imageFormat, boolean z);

    boolean contains(int i2, ImageFormat imageFormat, boolean z);

    @Nullable
    CloseableReference<Bitmap> getBitmapToReuseForFrame(int i2, int i3, int i4, ImageFormat imageFormat, boolean z);

    @Nullable
    CloseableReference<Bitmap> getCachedFrame(int i2, ImageFormat imageFormat, boolean z);

    @Nullable
    CloseableReference<Bitmap> getFallbackFrame(int i2);

    int getSizeInBytes();

    void onFramePrepared(int i2, CloseableReference<Bitmap> closeableReference, int i3, @Nullable ImageFormat imageFormat, boolean z);

    void onFrameRendered(int i2, CloseableReference<Bitmap> closeableReference, int i3, @Nullable ImageFormat imageFormat, boolean z);

    void setFrameCacheListener(FrameCacheListener frameCacheListener);
}
