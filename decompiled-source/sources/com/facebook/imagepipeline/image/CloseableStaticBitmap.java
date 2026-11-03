package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class CloseableStaticBitmap extends CloseableBitmap {
    private volatile Bitmap mBitmap;
    private CloseableReference<Bitmap> mBitmapReference;
    private final int mExifOrientation;
    private final QualityInfo mQualityInfo;
    private final Rect mRegionToDecode;
    private final int mRotationAngle;
    private final int mSampleSize;
    private final Rect mSmartCrop;

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getImageCount() {
        return 1;
    }

    public CloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int i2) {
        this(bitmap, resourceReleaser, qualityInfo, i2, 0);
    }

    public CloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int i2, int i3) {
        this(bitmap, resourceReleaser, qualityInfo, i2, i3, (Rect) null, (Rect) null, -1);
    }

    public CloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int i2, int i3, Rect rect, Rect rect2, int i4) {
        this.mBitmap = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.mBitmapReference = CloseableReference.of(this.mBitmap, (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser));
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i2;
        this.mExifOrientation = i3;
        this.mRegionToDecode = rect;
        this.mSmartCrop = rect2;
        this.mSampleSize = i4;
    }

    public CloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int i2, int i3, Rect rect, Rect rect2, int i4, ImageFormat imageFormat) {
        this.mBitmap = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.mBitmapReference = CloseableReference.of(this.mBitmap, (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser));
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i2;
        this.mExifOrientation = i3;
        this.mRegionToDecode = rect;
        this.mSmartCrop = rect2;
        this.mSampleSize = i4;
        this.mImageFormat = imageFormat;
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i2) {
        this(closeableReference, qualityInfo, i2, 0);
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i2, ImageFormat imageFormat) {
        this(closeableReference, qualityInfo, i2, 0, (Rect) null, (Rect) null, -1, imageFormat);
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i2, int i3) {
        this(closeableReference, qualityInfo, i2, i3, null, null, -1);
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i2, int i3, Rect rect, Rect rect2, int i4) {
        CloseableReference<Bitmap> closeableReference2 = (CloseableReference) Preconditions.checkNotNull(closeableReference.cloneOrNull());
        this.mBitmapReference = closeableReference2;
        this.mBitmap = closeableReference2.get();
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i2;
        this.mExifOrientation = i3;
        this.mRegionToDecode = rect;
        this.mSmartCrop = rect2;
        this.mSampleSize = i4;
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i2, int i3, Rect rect, Rect rect2, int i4, ImageFormat imageFormat) {
        CloseableReference<Bitmap> closeableReference2 = (CloseableReference) Preconditions.checkNotNull(closeableReference.cloneOrNull());
        this.mBitmapReference = closeableReference2;
        this.mBitmap = closeableReference2.get();
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i2;
        this.mExifOrientation = i3;
        this.mRegionToDecode = rect;
        this.mSmartCrop = rect2;
        this.mSampleSize = i4;
        this.mImageFormat = imageFormat;
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i2, int i3, Rect rect, Rect rect2, int i4, ImageFormat imageFormat, int i5) {
        CloseableReference<Bitmap> closeableReference2 = (CloseableReference) Preconditions.checkNotNull(closeableReference.cloneOrNull());
        this.mBitmapReference = closeableReference2;
        this.mBitmap = closeableReference2.get();
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i2;
        this.mExifOrientation = i3;
        this.mRegionToDecode = rect;
        this.mSmartCrop = rect2;
        this.mSampleSize = i4;
        this.mImageFormat = imageFormat;
        setImageFrom(i5);
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference<Bitmap> detachBitmapReference = detachBitmapReference();
        if (detachBitmapReference != null) {
            detachBitmapReference.close();
        }
    }

    private synchronized CloseableReference<Bitmap> detachBitmapReference() {
        CloseableReference<Bitmap> closeableReference;
        closeableReference = this.mBitmapReference;
        this.mBitmapReference = null;
        this.mBitmap = null;
        return closeableReference;
    }

    public synchronized CloseableReference<Bitmap> convertToBitmapReference() {
        Preconditions.checkNotNull(this.mBitmapReference, "Cannot convert a closed static bitmap");
        return detachBitmapReference();
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> cloneUnderlyingBitmapReference() {
        return CloseableReference.cloneOrNull(this.mBitmapReference);
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public synchronized boolean isClosed() {
        return this.mBitmapReference == null;
    }

    @Override // com.facebook.imagepipeline.image.CloseableBitmap
    public Bitmap getUnderlyingBitmap() {
        return this.mBitmap;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public int getSizeInBytes() {
        return BitmapUtil.getSizeInBytes(this.mBitmap);
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getWidth() {
        int i2;
        if (this.mRotationAngle % RotationOptions.ROTATE_180 != 0 || (i2 = this.mExifOrientation) == 5 || i2 == 7) {
            return getBitmapHeight(this.mBitmap);
        }
        return getBitmapWidth(this.mBitmap);
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getHeight() {
        int i2;
        if (this.mRotationAngle % RotationOptions.ROTATE_180 != 0 || (i2 = this.mExifOrientation) == 5 || i2 == 7) {
            return getBitmapWidth(this.mBitmap);
        }
        return getBitmapHeight(this.mBitmap);
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public Rect getRegionToDecode() {
        return this.mRegionToDecode;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public Rect getSmartCrop() {
        return this.mSmartCrop;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public int getSampleSize() {
        return this.mSampleSize;
    }

    private static int getBitmapWidth(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getWidth();
    }

    private static int getBitmapHeight(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getHeight();
    }

    public int getRotationAngle() {
        return this.mRotationAngle;
    }

    public int getExifOrientation() {
        return this.mExifOrientation;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public QualityInfo getQualityInfo() {
        return this.mQualityInfo;
    }
}
