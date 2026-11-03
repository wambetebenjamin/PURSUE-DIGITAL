package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import java.util.List;
/* loaded from: classes8.dex */
public class AnimatedImageResultBuilder {
    private boolean mAnimHeifIndividualCacheForCurrentRequestEnabled;
    private List<CloseableReference<Bitmap>> mDecodedFrames;
    private int mFrameForPreview;
    private final AnimatedImage mImage;
    private CloseableReference<Bitmap> mPreviewBitmap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimatedImageResultBuilder(AnimatedImage animatedImage) {
        this.mImage = animatedImage;
    }

    public AnimatedImage getImage() {
        return this.mImage;
    }

    public CloseableReference<Bitmap> getPreviewBitmap() {
        return CloseableReference.cloneOrNull(this.mPreviewBitmap);
    }

    public AnimatedImageResultBuilder setPreviewBitmap(CloseableReference<Bitmap> closeableReference) {
        this.mPreviewBitmap = CloseableReference.cloneOrNull(closeableReference);
        return this;
    }

    public int getFrameForPreview() {
        return this.mFrameForPreview;
    }

    public AnimatedImageResultBuilder setFrameForPreview(int i2) {
        this.mFrameForPreview = i2;
        return this;
    }

    public List<CloseableReference<Bitmap>> getDecodedFrames() {
        return CloseableReference.cloneOrNull(this.mDecodedFrames);
    }

    public AnimatedImageResultBuilder setDecodedFrames(List<CloseableReference<Bitmap>> list) {
        this.mDecodedFrames = CloseableReference.cloneOrNull(list);
        return this;
    }

    public AnimatedImageResultBuilder setAnimHeifIndividualCacheForCurrentRequestEnabled(boolean z) {
        this.mAnimHeifIndividualCacheForCurrentRequestEnabled = z;
        return this;
    }

    public boolean isAnimHeifIndividualCacheForCurrentRequestEnabled() {
        return this.mAnimHeifIndividualCacheForCurrentRequestEnabled;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.facebook.common.references.CloseableReference<android.graphics.Bitmap>>, com.facebook.common.references.CloseableReference<android.graphics.Bitmap>] */
    public AnimatedImageResult build() {
        try {
            return new AnimatedImageResult(this);
        } finally {
            CloseableReference.closeSafely(this.mPreviewBitmap);
            this.mPreviewBitmap = null;
            CloseableReference.closeSafely(this.mDecodedFrames);
            this.mDecodedFrames = null;
        }
    }
}
