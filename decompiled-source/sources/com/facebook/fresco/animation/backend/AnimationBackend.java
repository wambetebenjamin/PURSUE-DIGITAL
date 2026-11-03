package com.facebook.fresco.animation.backend;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.imageformat.ImageFormat;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public interface AnimationBackend extends AnimationInformation, AnimationSourceInfo {
    public static final int INTRINSIC_DIMENSION_UNSET = -1;

    void clear();

    boolean drawFrame(Drawable drawable, Canvas canvas, int i2);

    @Override // com.facebook.fresco.animation.backend.AnimationSourceInfo
    ImageFormat getImageFormat();

    int getIntrinsicHeight();

    int getIntrinsicWidth();

    int getSizeInBytes();

    boolean hasCacheFrame(int i2);

    boolean isAnimatedHeifIndividualCacheEnabled();

    void setAlpha(int i2);

    void setBounds(Rect rect);

    void setColorFilter(@Nullable ColorFilter colorFilter);
}
