package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.nativecode.NativeJpegTranscoderFactory;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class MultiImageTranscoderFactory implements ImageTranscoderFactory {
    @Nullable
    private final Integer mImageTranscoderType;
    private final int mMaxBitmapSize;
    @Nullable
    private final ImageTranscoderFactory mPrimaryImageTranscoderFactory;
    private final boolean mUseDownSamplingRatio;

    public MultiImageTranscoderFactory(int i2, boolean z, @Nullable ImageTranscoderFactory imageTranscoderFactory, @Nullable Integer num) {
        this.mMaxBitmapSize = i2;
        this.mUseDownSamplingRatio = z;
        this.mPrimaryImageTranscoderFactory = imageTranscoderFactory;
        this.mImageTranscoderType = num;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoderFactory
    public ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean z) {
        ImageTranscoder customImageTranscoder = getCustomImageTranscoder(imageFormat, z);
        if (customImageTranscoder == null) {
            customImageTranscoder = getImageTranscoderWithType(imageFormat, z);
        }
        if (customImageTranscoder == null) {
            customImageTranscoder = getNativeImageTranscoder(imageFormat, z);
        }
        return customImageTranscoder == null ? getSimpleImageTranscoder(imageFormat, z) : customImageTranscoder;
    }

    @Nullable
    private ImageTranscoder getCustomImageTranscoder(ImageFormat imageFormat, boolean z) {
        ImageTranscoderFactory imageTranscoderFactory = this.mPrimaryImageTranscoderFactory;
        if (imageTranscoderFactory == null) {
            return null;
        }
        return imageTranscoderFactory.createImageTranscoder(imageFormat, z);
    }

    @Nullable
    private ImageTranscoder getNativeImageTranscoder(ImageFormat imageFormat, boolean z) {
        return new NativeJpegTranscoderFactory(this.mMaxBitmapSize, this.mUseDownSamplingRatio).createImageTranscoder(imageFormat, z);
    }

    private ImageTranscoder getSimpleImageTranscoder(ImageFormat imageFormat, boolean z) {
        return new SimpleImageTranscoderFactory(this.mMaxBitmapSize).createImageTranscoder(imageFormat, z);
    }

    @Nullable
    private ImageTranscoder getImageTranscoderWithType(ImageFormat imageFormat, boolean z) {
        Integer num = this.mImageTranscoderType;
        if (num == null) {
            return null;
        }
        int intValue = num.intValue();
        if (intValue != 0) {
            if (intValue == 1) {
                return getSimpleImageTranscoder(imageFormat, z);
            }
            throw new IllegalArgumentException("Invalid ImageTranscoderType");
        }
        return getNativeImageTranscoder(imageFormat, z);
    }
}
