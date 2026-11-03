package com.facebook.imagepipeline.blurhash;
/* loaded from: classes8.dex */
public final class BlurHashConfig {
    private static final int MAX_BLUR_HASH_HEIGHT = 100;
    private static final int MAX_BLUR_HASH_WIDTH = 100;
    final int mHeight;
    final float mPunch;
    final boolean mUseCache;
    final int mWidth;
    final int targetHeight;
    final int targetWidth;

    public BlurHashConfig(int i2, int i3) {
        this(i2, i3, 1.0f, true);
    }

    public BlurHashConfig(int i2, int i3, float f2, boolean z) {
        this(i2, i3, f2, z, 0, 0);
    }

    public BlurHashConfig(int i2, int i3, float f2, boolean z, int i4, int i5) {
        this.mWidth = Math.min(i2, 100);
        this.mHeight = Math.min(i3, 100);
        this.mPunch = f2;
        this.mUseCache = z;
        this.targetWidth = i4;
        this.targetHeight = i5;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getTargetWidth() {
        return this.targetWidth;
    }

    public int getTargetHeight() {
        return this.targetHeight;
    }

    public float getPunch() {
        return this.mPunch;
    }

    public boolean isUseCache() {
        return this.mUseCache;
    }
}
