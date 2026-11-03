package com.facebook.imagepipeline.blurhash2;
/* loaded from: classes8.dex */
public final class PreviewHashConfig {
    public static final int DEFAULT_BLUR_RADIUS = 1;
    public static final int DEFAULT_HEIGHT = 0;
    public static final int DEFAULT_ITERATIONS = 1;
    public static final int DEFAULT_WIDTH = 0;
    final int mBlurRadius;
    final int mHeight;
    final int mIterations;
    final int mWidth;

    public PreviewHashConfig(int i2, int i3) {
        this(i2, i3, 0, 0);
    }

    public PreviewHashConfig(int i2, int i3, int i4, int i5) {
        this.mIterations = i2;
        this.mBlurRadius = i3;
        this.mWidth = i4;
        this.mHeight = i5;
    }

    public int getIterations() {
        return this.mIterations;
    }

    public int getBlurRadius() {
        return this.mBlurRadius;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }
}
