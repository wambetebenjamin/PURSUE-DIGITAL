package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class ImageDecodeOptions {
    private static final ImageDecodeOptions DEFAULTS = newBuilder().build();
    public boolean animatedHeifIndividualCacheEnabled;
    public Bitmap.Config bitmapConfig;
    @Nullable
    public final BitmapTransformation bitmapTransformation;
    @Nullable
    public final ImageDecoder customImageDecoder;
    public final boolean decodeAllFrames;
    @Nullable
    public final Object decodeContext;
    public final boolean decodePreviewFrame;
    public final boolean forceStaticImage;
    public boolean forceUseARGB8888;
    public boolean isSelectBitmapConfig;
    public final int minDecodeIntervalMs;
    public final int preDecodeFrameCount;
    @Nullable
    public final Rect regionToDecode;
    public final boolean transformToSRGB;
    public final boolean useLastFrameForPreview;
    public final boolean useSmartCrop;

    public ImageDecodeOptions(ImageDecodeOptionsBuilder imageDecodeOptionsBuilder) {
        this.minDecodeIntervalMs = imageDecodeOptionsBuilder.getMinDecodeIntervalMs();
        this.decodePreviewFrame = imageDecodeOptionsBuilder.getDecodePreviewFrame();
        this.preDecodeFrameCount = imageDecodeOptionsBuilder.getPreDecodeFrameCount();
        this.useLastFrameForPreview = imageDecodeOptionsBuilder.getUseLastFrameForPreview();
        this.decodeAllFrames = imageDecodeOptionsBuilder.getDecodeAllFrames();
        this.forceStaticImage = imageDecodeOptionsBuilder.getForceStaticImage();
        this.bitmapConfig = imageDecodeOptionsBuilder.getBitmapConfig();
        this.forceUseARGB8888 = imageDecodeOptionsBuilder.isForceUseARGB8888();
        this.customImageDecoder = imageDecodeOptionsBuilder.getCustomImageDecoder();
        this.transformToSRGB = imageDecodeOptionsBuilder.getTransformToSRGB();
        this.bitmapTransformation = imageDecodeOptionsBuilder.getBitmapTransformation();
        this.decodeContext = imageDecodeOptionsBuilder.getDecodeContext();
        this.isSelectBitmapConfig = imageDecodeOptionsBuilder.isSelectBitmapConfig();
        this.useSmartCrop = imageDecodeOptionsBuilder.isUseSmartCrop();
        this.regionToDecode = imageDecodeOptionsBuilder.getRegionToDecode();
        this.animatedHeifIndividualCacheEnabled = imageDecodeOptionsBuilder.isAnimatedHeifIndividualCacheEnabled();
    }

    public static ImageDecodeOptions defaults() {
        return DEFAULTS;
    }

    public static ImageDecodeOptionsBuilder newBuilder() {
        return new ImageDecodeOptionsBuilder();
    }

    public boolean equals(Object obj) {
        Rect rect;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageDecodeOptions imageDecodeOptions = (ImageDecodeOptions) obj;
        if (this.decodePreviewFrame == imageDecodeOptions.decodePreviewFrame && this.useLastFrameForPreview == imageDecodeOptions.useLastFrameForPreview && this.decodeAllFrames == imageDecodeOptions.decodeAllFrames && this.forceStaticImage == imageDecodeOptions.forceStaticImage && this.transformToSRGB == imageDecodeOptions.transformToSRGB && this.bitmapConfig == imageDecodeOptions.bitmapConfig && this.customImageDecoder == imageDecodeOptions.customImageDecoder && this.bitmapTransformation == imageDecodeOptions.bitmapTransformation && this.decodeContext == imageDecodeOptions.decodeContext && this.isSelectBitmapConfig == imageDecodeOptions.isSelectBitmapConfig && this.useSmartCrop == imageDecodeOptions.useSmartCrop && this.animatedHeifIndividualCacheEnabled == imageDecodeOptions.animatedHeifIndividualCacheEnabled) {
            Rect rect2 = this.regionToDecode;
            return (rect2 == null || rect2.equals(imageDecodeOptions.regionToDecode)) && ((rect = imageDecodeOptions.regionToDecode) == null || rect.equals(this.regionToDecode));
        }
        return false;
    }

    public int hashCode() {
        int ordinal = ((((((((((((this.minDecodeIntervalMs * 31) + (this.decodePreviewFrame ? 1 : 0)) * 31) + (this.useLastFrameForPreview ? 1 : 0)) * 31) + (this.decodeAllFrames ? 1 : 0)) * 31) + (this.forceStaticImage ? 1 : 0)) * 31) + (this.transformToSRGB ? 1 : 0)) * 31) + this.bitmapConfig.ordinal()) * 31;
        ImageDecoder imageDecoder = this.customImageDecoder;
        int hashCode = (ordinal + (imageDecoder != null ? imageDecoder.hashCode() : 0)) * 31;
        BitmapTransformation bitmapTransformation = this.bitmapTransformation;
        int hashCode2 = (hashCode + (bitmapTransformation != null ? bitmapTransformation.hashCode() : 0)) * 31;
        Object obj = this.decodeContext;
        int hashCode3 = (((((hashCode2 + (obj != null ? obj.hashCode() : 0)) * 31) + (this.isSelectBitmapConfig ? 1 : 0)) * 31) + (this.useSmartCrop ? 1 : 0)) * 31;
        Rect rect = this.regionToDecode;
        return ((hashCode3 + (rect != null ? rect.hashCode() : 0)) * 31) + (this.animatedHeifIndividualCacheEnabled ? 1 : 0);
    }

    public String toString() {
        return String.format(null, "%d-%b-%b-%b-%b-%b-%s-%s-%s-%s-%b-%b-%s-%b", Integer.valueOf(this.minDecodeIntervalMs), Boolean.valueOf(this.decodePreviewFrame), Boolean.valueOf(this.useLastFrameForPreview), Boolean.valueOf(this.decodeAllFrames), Boolean.valueOf(this.forceStaticImage), Boolean.valueOf(this.transformToSRGB), this.bitmapConfig.name(), this.customImageDecoder, this.bitmapTransformation, this.decodeContext, Boolean.valueOf(this.isSelectBitmapConfig), Boolean.valueOf(this.useSmartCrop), this.regionToDecode, Boolean.valueOf(this.animatedHeifIndividualCacheEnabled));
    }

    public String toOptKeyString() {
        return String.format(null, "%b-%s-%s-%s-%b", Boolean.valueOf(this.forceStaticImage), this.bitmapConfig.name(), this.bitmapTransformation, this.regionToDecode, Boolean.valueOf(this.animatedHeifIndividualCacheEnabled));
    }
}
