package com.facebook.animated.webp;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;
/* loaded from: classes8.dex */
public class WebPFrame implements AnimatedImageFrame {
    private long mNativeContext;

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDurationMs();

    private native int nativeGetHeight();

    private native int nativeGetWidth();

    private native int nativeGetXOffset();

    private native int nativeGetYOffset();

    private native boolean nativeIsBlendWithPreviousFrame();

    private native void nativeRenderFrame(int i2, int i3, Bitmap bitmap);

    private native boolean nativeShouldDisposeToBackgroundColor();

    WebPFrame(long j2) {
        this.mNativeContext = j2;
    }

    protected void finalize() {
        nativeFinalize();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public void dispose() {
        nativeDispose();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public void renderFrame(int i2, int i3, Bitmap bitmap) {
        nativeRenderFrame(i2, i3, bitmap);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getDurationMs() {
        return nativeGetDurationMs();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getWidth() {
        return nativeGetWidth();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getHeight() {
        return nativeGetHeight();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getXOffset() {
        return nativeGetXOffset();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImageFrame
    public int getYOffset() {
        return nativeGetYOffset();
    }

    public boolean shouldDisposeToBackgroundColor() {
        return nativeShouldDisposeToBackgroundColor();
    }

    public boolean isBlendWithPreviousFrame() {
        return nativeIsBlendWithPreviousFrame();
    }
}
