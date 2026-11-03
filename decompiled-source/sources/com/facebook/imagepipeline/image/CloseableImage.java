package com.facebook.imagepipeline.image;

import android.graphics.Rect;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.ImageFormat;
import java.io.Closeable;
/* loaded from: classes8.dex */
public abstract class CloseableImage implements ImageInfo, MemoryCacheHitTracker, Closeable {
    private static final String TAG = "CloseableImage";
    private boolean isThumbCache;
    protected ImageFormat mImageFormat;
    private boolean isRequestInternet = false;
    private boolean isHitDiskCache = false;
    private boolean isHitMemoryCache = false;

    public CloseableImage cloneOrNull() {
        return null;
    }

    public abstract void close();

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public Rect getRegionToDecode() {
        return null;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getSampleSize() {
        return -1;
    }

    public abstract int getSizeInBytes();

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public Rect getSmartCrop() {
        return null;
    }

    public String getSourceUri() {
        return null;
    }

    public abstract boolean isClosed();

    public boolean isStateful() {
        return false;
    }

    public void setSourceUri(String str) {
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.FULL_QUALITY;
    }

    public boolean isRequestInternet() {
        return this.isRequestInternet;
    }

    public boolean isHitDiskCache() {
        return this.isHitDiskCache;
    }

    public boolean isHitMemoryCache() {
        return this.isHitMemoryCache;
    }

    @Override // com.facebook.imagepipeline.image.MemoryCacheHitTracker
    public void hitMemoryCache() {
        this.isHitMemoryCache = true;
        this.isHitDiskCache = false;
        this.isRequestInternet = false;
    }

    public void copyImageFromTrace(EncodedImage encodedImage) {
        this.isRequestInternet = encodedImage.isRequestNetwork();
        this.isHitDiskCache = encodedImage.isHitDiskCache();
        this.isHitMemoryCache = encodedImage.isHitMemoryCache();
    }

    public void setImageFrom(int i2) {
        if (i2 == 0) {
            this.isHitMemoryCache = false;
            this.isHitDiskCache = false;
            this.isRequestInternet = true;
        } else if (i2 == 1) {
            hitMemoryCache();
        } else if (i2 != 2) {
        } else {
            this.isHitMemoryCache = false;
            this.isHitDiskCache = true;
            this.isRequestInternet = false;
        }
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public ImageFormat getImageFormat() {
        ImageFormat imageFormat = this.mImageFormat;
        return imageFormat == null ? ImageFormat.UNKNOWN : imageFormat;
    }

    protected void finalize() {
        if (isClosed()) {
            return;
        }
        FLog.d(TAG, "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public void setThumbCache(boolean z) {
        this.isThumbCache = z;
    }

    public boolean isThumbCache() {
        return this.isThumbCache;
    }
}
