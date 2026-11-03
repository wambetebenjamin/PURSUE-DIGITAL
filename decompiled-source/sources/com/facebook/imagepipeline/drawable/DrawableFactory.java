package com.facebook.imagepipeline.drawable;

import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.image.CloseableImage;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public interface DrawableFactory {
    @Nullable
    Drawable createDrawable(CloseableImage closeableImage);

    @Nullable
    Drawable createDrawable(CloseableImage closeableImage, Drawable drawable);

    boolean supportsImageType(CloseableImage closeableImage);
}
