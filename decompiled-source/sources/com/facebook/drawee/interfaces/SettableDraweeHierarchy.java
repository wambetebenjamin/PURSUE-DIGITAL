package com.facebook.drawee.interfaces;

import android.graphics.drawable.Drawable;
/* loaded from: classes8.dex */
public interface SettableDraweeHierarchy extends DraweeHierarchy {
    void reset();

    void setControllerOverlay(Drawable drawable);

    void setFailure(Throwable th);

    void setImage(Drawable drawable, float f2, boolean z);

    void setProgress(float f2, boolean z);

    void setRetry(Throwable th);
}
