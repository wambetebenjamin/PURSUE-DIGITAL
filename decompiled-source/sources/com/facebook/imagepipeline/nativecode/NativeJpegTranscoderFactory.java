package com.facebook.imagepipeline.nativecode;

import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
/* loaded from: classes8.dex */
public class NativeJpegTranscoderFactory implements ImageTranscoderFactory {
    private final int mMaxBitmapSize;
    private final boolean mUseDownSamplingRatio;

    public NativeJpegTranscoderFactory(int i2, boolean z) {
        this.mMaxBitmapSize = i2;
        this.mUseDownSamplingRatio = z;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoderFactory
    public ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean z) {
        if (imageFormat != DefaultImageFormats.JPEG) {
            return null;
        }
        return new NativeJpegTranscoder(z, this.mMaxBitmapSize, this.mUseDownSamplingRatio);
    }
}
