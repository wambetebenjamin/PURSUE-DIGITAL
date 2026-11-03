package com.facebook.imagepipeline.animated.base;

import com.facebook.imageformat.ImageFormat;
/* loaded from: classes8.dex */
public interface AnimatedImage {
    public static final int LOOP_COUNT_INFINITE = 0;

    AnimatedImage cloneOrNull();

    void dispose();

    boolean doesRenderSupportScaling();

    int getDecodeStatus();

    int getDuration();

    AnimatedImageFrame getFrame(int i2);

    int getFrameCount();

    int[] getFrameDurations();

    AnimatedDrawableFrameInfo getFrameInfo(int i2);

    int getHeight();

    ImageFormat getImageFormat();

    int getLoopCount();

    int getSizeInBytes();

    int getWidth();
}
