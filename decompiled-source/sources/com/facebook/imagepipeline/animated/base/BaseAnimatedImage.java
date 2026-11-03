package com.facebook.imagepipeline.animated.base;
/* loaded from: classes8.dex */
public abstract class BaseAnimatedImage implements AnimatedImage {
    protected int mDecodeStatus = 0;

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public AnimatedImage cloneOrNull() {
        return null;
    }

    public void setDecodeStatus(int i2) {
        this.mDecodeStatus = i2;
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getDecodeStatus() {
        return this.mDecodeStatus;
    }
}
