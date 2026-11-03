package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
/* loaded from: classes10.dex */
public class OverlayImageView extends ImageView {
    Overlay overlay;

    public OverlayImageView(Context context) {
        super(context);
        this.overlay = new Overlay(null);
    }

    public OverlayImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.overlay = new Overlay(null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.overlay.draw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.overlay.setDrawableState(getDrawableState());
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.overlay.setDrawableBounds(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.overlay.setDrawableBounds(i2, i3);
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.overlay.drawable) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void setOverlayDrawable(Drawable drawable) {
        if (drawable != this.overlay.drawable) {
            this.overlay.cleanupDrawable(this);
            if (drawable != null) {
                drawable.setCallback(this);
            }
            Overlay overlay = new Overlay(drawable);
            this.overlay = overlay;
            overlay.setDrawableState(getDrawableState());
            requestLayout();
        }
    }

    /* loaded from: classes10.dex */
    protected static class Overlay {
        final Drawable drawable;

        Overlay(Drawable drawable) {
            this.drawable = drawable;
        }

        protected void cleanupDrawable(ImageView imageView) {
            Drawable drawable = this.drawable;
            if (drawable != null) {
                drawable.setCallback(null);
                imageView.unscheduleDrawable(this.drawable);
            }
        }

        protected void setDrawableBounds(int i2, int i3) {
            Drawable drawable = this.drawable;
            if (drawable != null) {
                drawable.setBounds(0, 0, i2, i3);
            }
        }

        protected void setDrawableState(int[] iArr) {
            Drawable drawable = this.drawable;
            if (drawable == null || !drawable.isStateful()) {
                return;
            }
            this.drawable.setState(iArr);
        }

        protected void draw(Canvas canvas) {
            Drawable drawable = this.drawable;
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }
}
