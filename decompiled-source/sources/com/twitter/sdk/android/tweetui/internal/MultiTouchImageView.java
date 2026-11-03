package com.twitter.sdk.android.tweetui.internal;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener;
/* loaded from: classes10.dex */
public class MultiTouchImageView extends ImageView implements SwipeToDismissTouchListener.SwipeableViewProvider {
    private static final float DOUBLE_TAP_SCALE_FACTOR = 2.0f;
    private static final float MINIMUM_SCALE_FACTOR = 1.0f;
    private static final long SCALE_ANIMATION_DURATION = 300;
    boolean allowIntercept;
    final Matrix baseMatrix;
    final Matrix drawMatrix;
    final RectF drawRect;
    final GestureDetector gestureDetector;
    final float[] matrixValues;
    final ScaleGestureDetector scaleGestureDetector;
    final Matrix updateMatrix;
    final RectF viewRect;

    public MultiTouchImageView(Context context) {
        this(context, null);
    }

    public MultiTouchImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiTouchImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.drawMatrix = new Matrix();
        this.baseMatrix = new Matrix();
        this.updateMatrix = new Matrix();
        this.viewRect = new RectF();
        this.drawRect = new RectF();
        this.matrixValues = new float[9];
        this.scaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView.1
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                MultiTouchImageView.this.setScale(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                MultiTouchImageView.this.setImageMatrix();
                return true;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                if (MultiTouchImageView.this.getScale() < 1.0f) {
                    MultiTouchImageView.this.reset();
                    MultiTouchImageView.this.setImageMatrix();
                }
            }
        });
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                MultiTouchImageView.this.setTranslate(-f2, -f3);
                MultiTouchImageView.this.setImageMatrix();
                if (!MultiTouchImageView.this.allowIntercept || MultiTouchImageView.this.scaleGestureDetector.isInProgress()) {
                    return true;
                }
                MultiTouchImageView.this.requestDisallowInterceptTouchEvent(false);
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (MultiTouchImageView.this.getScale() > 1.0f) {
                    MultiTouchImageView multiTouchImageView = MultiTouchImageView.this;
                    multiTouchImageView.animateScale(multiTouchImageView.getScale(), 1.0f, motionEvent.getX(), motionEvent.getY());
                    return true;
                }
                MultiTouchImageView multiTouchImageView2 = MultiTouchImageView.this;
                multiTouchImageView2.animateScale(multiTouchImageView2.getScale(), MultiTouchImageView.DOUBLE_TAP_SCALE_FACTOR, motionEvent.getX(), motionEvent.getY());
                return true;
            }
        });
    }

    boolean isInitializationComplete() {
        Drawable drawable = getDrawable();
        return drawable != null && drawable.getIntrinsicWidth() > 0;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (isInitializationComplete()) {
            initializeViewRect();
            initializeBaseMatrix(getDrawable());
            setImageMatrix();
        }
    }

    void initializeViewRect() {
        this.viewRect.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
    }

    void initializeBaseMatrix(Drawable drawable) {
        RectF rectF = new RectF(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        this.baseMatrix.reset();
        this.baseMatrix.setRectToRect(rectF, this.viewRect, Matrix.ScaleToFit.CENTER);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isInitializationComplete()) {
            requestDisallowInterceptTouchEvent(true);
            return (this.gestureDetector.onTouchEvent(motionEvent) || this.scaleGestureDetector.onTouchEvent(motionEvent)) || super.onTouchEvent(motionEvent);
        }
        return false;
    }

    void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    void setScale(float f2, float f3, float f4) {
        this.updateMatrix.postScale(f2, f2, f3, f4);
    }

    float getScale() {
        this.updateMatrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }

    void setTranslate(float f2, float f3) {
        this.updateMatrix.postTranslate(f2, f3);
    }

    void reset() {
        this.updateMatrix.reset();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void updateMatrixBounds() {
        /*
            r6 = this;
            android.graphics.Matrix r0 = r6.getDrawMatrix()
            android.graphics.RectF r0 = r6.getDrawRect(r0)
            float r1 = r0.height()
            android.graphics.RectF r2 = r6.viewRect
            float r2 = r2.height()
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r2 = 1073741824(0x40000000, float:2.0)
            r3 = 0
            if (r1 > 0) goto L29
            android.graphics.RectF r1 = r6.viewRect
            float r1 = r1.height()
            float r4 = r0.height()
            float r1 = r1 - r4
            float r1 = r1 / r2
            float r4 = r0.top
        L27:
            float r1 = r1 - r4
            goto L49
        L29:
            float r1 = r0.top
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L33
            float r1 = r0.top
            float r1 = -r1
            goto L49
        L33:
            float r1 = r0.bottom
            android.graphics.RectF r4 = r6.viewRect
            float r4 = r4.height()
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 >= 0) goto L48
            android.graphics.RectF r1 = r6.viewRect
            float r1 = r1.height()
            float r4 = r0.bottom
            goto L27
        L48:
            r1 = r3
        L49:
            float r4 = r0.width()
            android.graphics.RectF r5 = r6.viewRect
            float r5 = r5.width()
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            r5 = 1
            if (r4 > 0) goto L6a
            r6.allowIntercept = r5
            android.graphics.RectF r3 = r6.viewRect
            float r3 = r3.width()
            float r4 = r0.width()
            float r3 = r3 - r4
            float r3 = r3 / r2
            float r0 = r0.left
            float r3 = r3 - r0
            goto L92
        L6a:
            float r2 = r0.left
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L76
            r6.allowIntercept = r5
            float r0 = r0.left
            float r3 = -r0
            goto L92
        L76:
            float r2 = r0.right
            android.graphics.RectF r4 = r6.viewRect
            float r4 = r4.width()
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L8f
            r6.allowIntercept = r5
            android.graphics.RectF r2 = r6.viewRect
            float r2 = r2.width()
            float r0 = r0.right
            float r3 = r2 - r0
            goto L92
        L8f:
            r0 = 0
            r6.allowIntercept = r0
        L92:
            r6.setTranslate(r3, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView.updateMatrixBounds():void");
    }

    RectF getDrawRect(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            this.drawRect.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            matrix.mapRect(this.drawRect);
        }
        return this.drawRect;
    }

    Matrix getDrawMatrix() {
        this.drawMatrix.set(this.baseMatrix);
        this.drawMatrix.postConcat(this.updateMatrix);
        return this.drawMatrix;
    }

    void setImageMatrix() {
        updateMatrixBounds();
        setScaleType(ImageView.ScaleType.MATRIX);
        setImageMatrix(getDrawMatrix());
    }

    void animateScale(float f2, float f3, final float f4, final float f5) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        ofFloat.setDuration(300L);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MultiTouchImageView.this.m741x87e09e7b(f4, f5, valueAnimator);
            }
        });
        ofFloat.start();
    }

    /* renamed from: lambda$animateScale$0$com-twitter-sdk-android-tweetui-internal-MultiTouchImageView  reason: not valid java name */
    public /* synthetic */ void m741x87e09e7b(float f2, float f3, ValueAnimator valueAnimator) {
        setScale(((Float) valueAnimator.getAnimatedValue()).floatValue() / getScale(), f2, f3);
        setImageMatrix();
    }

    @Override // com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener.SwipeableViewProvider
    public boolean canBeSwiped() {
        return getScale() == 1.0f;
    }
}
