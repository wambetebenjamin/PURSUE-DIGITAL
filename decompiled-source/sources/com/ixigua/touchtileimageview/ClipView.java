package com.ixigua.touchtileimageview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.ixigua.touchtileimageview.utils.p;
/* loaded from: classes9.dex */
abstract class ClipView extends View {
    private final Path mClipPath;
    private final RectF mClipRect;
    private final p mFromEvaluator;
    private Rect mFromRect;
    private float mRadius;
    private boolean mUseClipPath;
    private boolean mUseClipRect;
    private boolean mUseVisibleClipRect;
    private final Rect mViewRect;
    private final RectF mVisibleClipRect;
    private final p mVisibleEvaluator;
    private Rect mVisibleFromRect;

    protected abstract void drawContent(Canvas canvas);

    public ClipView(Context context) {
        super(context);
        this.mClipRect = new RectF();
        this.mClipPath = new Path();
        this.mVisibleClipRect = new RectF();
        this.mUseClipRect = false;
        this.mUseClipPath = false;
        this.mUseVisibleClipRect = false;
        this.mViewRect = new Rect();
        this.mFromEvaluator = new p(new Rect());
        this.mVisibleEvaluator = new p(new Rect());
    }

    public ClipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClipRect = new RectF();
        this.mClipPath = new Path();
        this.mVisibleClipRect = new RectF();
        this.mUseClipRect = false;
        this.mUseClipPath = false;
        this.mUseVisibleClipRect = false;
        this.mViewRect = new Rect();
        this.mFromEvaluator = new p(new Rect());
        this.mVisibleEvaluator = new p(new Rect());
    }

    public ClipView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mClipRect = new RectF();
        this.mClipPath = new Path();
        this.mVisibleClipRect = new RectF();
        this.mUseClipRect = false;
        this.mUseClipPath = false;
        this.mUseVisibleClipRect = false;
        this.mViewRect = new Rect();
        this.mFromEvaluator = new p(new Rect());
        this.mVisibleEvaluator = new p(new Rect());
    }

    public ClipView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mClipRect = new RectF();
        this.mClipPath = new Path();
        this.mVisibleClipRect = new RectF();
        this.mUseClipRect = false;
        this.mUseClipPath = false;
        this.mUseVisibleClipRect = false;
        this.mViewRect = new Rect();
        this.mFromEvaluator = new p(new Rect());
        this.mVisibleEvaluator = new p(new Rect());
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        if (this.mUseVisibleClipRect) {
            canvas.save();
            canvas.clipRect(this.mVisibleClipRect);
            if (this.mUseClipPath) {
                canvas.save();
                canvas.clipPath(this.mClipPath);
                drawContent(canvas);
                canvas.restore();
            } else if (this.mUseClipRect) {
                canvas.save();
                canvas.clipRect(this.mClipRect);
                drawContent(canvas);
                canvas.restore();
            } else {
                drawContent(canvas);
            }
            canvas.restore();
        } else if (this.mUseClipPath) {
            canvas.save();
            canvas.clipPath(this.mClipPath);
            drawContent(canvas);
            canvas.restore();
        } else if (this.mUseClipRect) {
            canvas.save();
            canvas.clipRect(this.mClipRect);
            drawContent(canvas);
            canvas.restore();
        } else {
            drawContent(canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mViewRect.set(0, 0, getWidth(), getHeight());
    }

    private void setClipRect(Rect rect, Rect rect2, float f2) {
        if (rect.equals(this.mViewRect) && rect2.equals(this.mViewRect) && f2 == 0.0f) {
            this.mUseClipRect = false;
            this.mUseClipPath = false;
            this.mUseVisibleClipRect = false;
            return;
        }
        this.mClipRect.set(rect);
        if (Build.VERSION.SDK_INT >= 18 && f2 > 0.0f) {
            this.mClipPath.rewind();
            this.mClipPath.addRoundRect(this.mClipRect, f2, f2, Path.Direction.CCW);
            this.mUseClipPath = true;
        } else {
            this.mUseClipRect = true;
        }
        this.mVisibleClipRect.set(rect2);
        this.mUseVisibleClipRect = !this.mVisibleClipRect.equals(this.mClipRect);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void computeAnimation(float f2) {
        if (this.mFromRect == null || this.mVisibleFromRect == null || this.mViewRect.isEmpty()) {
            return;
        }
        setClipRect(this.mFromEvaluator.evaluate(f2, this.mFromRect, this.mViewRect), this.mVisibleEvaluator.evaluate(f2, this.mVisibleFromRect, this.mViewRect), this.mRadius * (1.0f - f2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Animator getClipAnimator(Rect rect, Rect rect2, float f2, final boolean z) {
        this.mFromRect = new Rect(rect);
        this.mVisibleFromRect = new Rect(rect2);
        this.mRadius = f2;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ixigua.touchtileimageview.ClipView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ClipView clipView = ClipView.this;
                if (z) {
                    floatValue = 1.0f - floatValue;
                }
                clipView.computeAnimation(floatValue);
            }
        });
        return ofFloat;
    }
}
