package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.facebook.imagepipeline.animated.factory.AnimatedFactoryProvider;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class ImageDecodeOptionsBuilder {
    private boolean isSelectBitmapConfig;
    private boolean mAnimatedHeifIndividualCacheEnabled;
    @Nullable
    private BitmapTransformation mBitmapTransformation;
    @Nullable
    private ImageDecoder mCustomImageDecoder;
    private boolean mDecodeAllFrames;
    @Nullable
    private Object mDecodeContext;
    private boolean mDecodePreviewFrame;
    private boolean mForceStaticImage;
    @Nullable
    private Rect mRegionToDecode;
    private boolean mTransformToSRGB;
    private boolean mUseLastFrameForPreview;
    private boolean mUseSmartCrop;
    private int mMinDecodeIntervalMs = 100;
    private int mPreDecodeFrameCount = AnimatedFactoryProvider.getDefaultPreDecodeCount();
    private Bitmap.Config mBitmapConfig = Bitmap.Config.ARGB_8888;
    private boolean mForceUseARGB8888 = false;

    public ImageDecodeOptionsBuilder setFrom(ImageDecodeOptions imageDecodeOptions) {
        this.mMinDecodeIntervalMs = imageDecodeOptions.minDecodeIntervalMs;
        this.mDecodePreviewFrame = imageDecodeOptions.decodePreviewFrame;
        this.mPreDecodeFrameCount = imageDecodeOptions.preDecodeFrameCount;
        this.mUseLastFrameForPreview = imageDecodeOptions.useLastFrameForPreview;
        this.mDecodeAllFrames = imageDecodeOptions.decodeAllFrames;
        this.mForceStaticImage = imageDecodeOptions.forceStaticImage;
        this.mBitmapConfig = imageDecodeOptions.bitmapConfig;
        this.mForceUseARGB8888 = imageDecodeOptions.forceUseARGB8888;
        this.mCustomImageDecoder = imageDecodeOptions.customImageDecoder;
        this.mTransformToSRGB = imageDecodeOptions.transformToSRGB;
        this.mBitmapTransformation = imageDecodeOptions.bitmapTransformation;
        this.mDecodeContext = imageDecodeOptions.decodeContext;
        this.isSelectBitmapConfig = imageDecodeOptions.isSelectBitmapConfig;
        this.mUseSmartCrop = imageDecodeOptions.useSmartCrop;
        this.mRegionToDecode = imageDecodeOptions.regionToDecode;
        this.mAnimatedHeifIndividualCacheEnabled = imageDecodeOptions.animatedHeifIndividualCacheEnabled;
        return this;
    }

    public ImageDecodeOptionsBuilder setMinDecodeIntervalMs(int i2) {
        this.mMinDecodeIntervalMs = i2;
        return this;
    }

    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }

    public ImageDecodeOptionsBuilder setDecodePreviewFrame(boolean z) {
        this.mDecodePreviewFrame = z;
        return this;
    }

    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }

    public ImageDecodeOptionsBuilder setPreDecodeFrameCount(int i2) {
        this.mPreDecodeFrameCount = i2;
        return this;
    }

    public int getPreDecodeFrameCount() {
        return this.mPreDecodeFrameCount;
    }

    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }

    public ImageDecodeOptionsBuilder setUseLastFrameForPreview(boolean z) {
        this.mUseLastFrameForPreview = z;
        return this;
    }

    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }

    public ImageDecodeOptionsBuilder setDecodeAllFrames(boolean z) {
        this.mDecodeAllFrames = z;
        return this;
    }

    public ImageDecodeOptionsBuilder setForceStaticImage(boolean z) {
        this.mForceStaticImage = z;
        return this;
    }

    public ImageDecodeOptionsBuilder setCustomImageDecoder(@Nullable ImageDecoder imageDecoder) {
        this.mCustomImageDecoder = imageDecoder;
        return this;
    }

    public ImageDecodeOptionsBuilder setAnimatedHeifIndividualCacheEnabled(boolean z) {
        this.mAnimatedHeifIndividualCacheEnabled = z;
        return this;
    }

    @Nullable
    public ImageDecoder getCustomImageDecoder() {
        return this.mCustomImageDecoder;
    }

    public boolean getForceStaticImage() {
        return this.mForceStaticImage;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public boolean isForceUseARGB8888() {
        return this.mForceUseARGB8888;
    }

    public ImageDecodeOptionsBuilder setBitmapConfig(Bitmap.Config config) {
        this.mBitmapConfig = config;
        this.isSelectBitmapConfig = true;
        return this;
    }

    public ImageDecodeOptionsBuilder setForceUseARGB8888(boolean z) {
        this.mForceUseARGB8888 = z;
        return this;
    }

    public boolean getTransformToSRGB() {
        return this.mTransformToSRGB;
    }

    public ImageDecodeOptionsBuilder setTransformToSRGB(boolean z) {
        this.mTransformToSRGB = z;
        return this;
    }

    public ImageDecodeOptionsBuilder setBitmapTransformation(@Nullable BitmapTransformation bitmapTransformation) {
        this.mBitmapTransformation = bitmapTransformation;
        return this;
    }

    @Nullable
    public BitmapTransformation getBitmapTransformation() {
        return this.mBitmapTransformation;
    }

    @Nullable
    public Object getDecodeContext() {
        return this.mDecodeContext;
    }

    public void setDecodeContext(@Nullable Object obj) {
        this.mDecodeContext = obj;
    }

    public boolean isUseSmartCrop() {
        return this.mUseSmartCrop;
    }

    public ImageDecodeOptionsBuilder setUseSmartCrop(boolean z) {
        this.mUseSmartCrop = z;
        return this;
    }

    @Nullable
    public Rect getRegionToDecode() {
        return this.mRegionToDecode;
    }

    public ImageDecodeOptionsBuilder setRegionToDecode(@Nullable Rect rect) {
        this.mRegionToDecode = rect;
        return this;
    }

    public boolean isSelectBitmapConfig() {
        return this.isSelectBitmapConfig;
    }

    public boolean isAnimatedHeifIndividualCacheEnabled() {
        return this.mAnimatedHeifIndividualCacheEnabled;
    }

    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }
}
