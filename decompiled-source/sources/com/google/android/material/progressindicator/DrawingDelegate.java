package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
/* loaded from: classes6.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    protected DrawableWithAnimatedVisibilityChange drawable;
    S spec;

    abstract void adjustCanvas(Canvas canvas, float f2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void fillIndicator(Canvas canvas, Paint paint, float f2, float f3, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void fillTrack(Canvas canvas, Paint paint);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getPreferredHeight();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getPreferredWidth();

    public DrawingDelegate(S s) {
        this.spec = s;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerDrawable(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
        this.drawable = drawableWithAnimatedVisibilityChange;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validateSpecAndAdjustCanvas(Canvas canvas, float f2) {
        this.spec.validateSpec();
        adjustCanvas(canvas, f2);
    }
}
