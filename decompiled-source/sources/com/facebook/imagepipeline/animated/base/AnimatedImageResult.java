package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class AnimatedImageResult {
    private boolean mAnimHeifIndividualCacheForCurrentRequestEnabled;
    @Nullable
    private List<CloseableReference<Bitmap>> mDecodedFrames;
    private final int mFrameForPreview;
    private final AnimatedImage mImage;
    @Nullable
    private CloseableReference<Bitmap> mPreviewBitmap;
    private String mSourceUri;

    public void setSourceUri(String str) {
        if (this.mImage.getImageFormat() == DefaultImageFormats.WEBP_ANIMATED || this.mImage.getImageFormat() == DefaultImageFormats.GIF) {
            this.mSourceUri = str;
        }
    }

    public String getSourceUri() {
        return this.mSourceUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimatedImageResult(AnimatedImageResultBuilder animatedImageResultBuilder) {
        this.mImage = (AnimatedImage) Preconditions.checkNotNull(animatedImageResultBuilder.getImage());
        this.mFrameForPreview = animatedImageResultBuilder.getFrameForPreview();
        this.mPreviewBitmap = animatedImageResultBuilder.getPreviewBitmap();
        this.mDecodedFrames = animatedImageResultBuilder.getDecodedFrames();
        this.mAnimHeifIndividualCacheForCurrentRequestEnabled = animatedImageResultBuilder.isAnimHeifIndividualCacheForCurrentRequestEnabled();
    }

    private AnimatedImageResult(AnimatedImage animatedImage) {
        this.mImage = (AnimatedImage) Preconditions.checkNotNull(animatedImage);
        this.mFrameForPreview = 0;
    }

    public static AnimatedImageResult forAnimatedImage(AnimatedImage animatedImage) {
        return new AnimatedImageResult(animatedImage);
    }

    public static AnimatedImageResultBuilder newBuilder(AnimatedImage animatedImage) {
        return new AnimatedImageResultBuilder(animatedImage);
    }

    public AnimatedImage getImage() {
        return this.mImage;
    }

    public int getFrameForPreview() {
        return this.mFrameForPreview;
    }

    public boolean isUseIndividualCacheForHeif() {
        return this.mAnimHeifIndividualCacheForCurrentRequestEnabled;
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getDecodedFrame(int i2) {
        List<CloseableReference<Bitmap>> list = this.mDecodedFrames;
        if (list != null) {
            return CloseableReference.cloneOrNull(list.get(i2));
        }
        return null;
    }

    public synchronized boolean hasDecodedFrame(int i2) {
        boolean z;
        List<CloseableReference<Bitmap>> list = this.mDecodedFrames;
        if (list != null) {
            z = list.get(i2) != null;
        }
        return z;
    }

    public synchronized CloseableReference<Bitmap> getPreviewBitmap() {
        return CloseableReference.cloneOrNull(this.mPreviewBitmap);
    }

    public synchronized void dispose() {
        CloseableReference.closeSafely(this.mPreviewBitmap);
        this.mPreviewBitmap = null;
        CloseableReference.closeSafely(this.mDecodedFrames);
        this.mDecodedFrames = null;
    }

    public synchronized int getDecodedFrameSize() {
        List<CloseableReference<Bitmap>> list;
        list = this.mDecodedFrames;
        return list != null ? list.size() : 0;
    }
}
