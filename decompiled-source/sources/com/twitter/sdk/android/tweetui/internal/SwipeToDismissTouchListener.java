package com.twitter.sdk.android.tweetui.internal;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
/* loaded from: classes10.dex */
public class SwipeToDismissTouchListener implements View.OnTouchListener {
    private Callback callback;
    private final float closeThreshold;
    private float initialY;
    private boolean isMoving;
    private float lastX;
    private float lastY;
    private final float maxTranslate;
    private int pointerIndex;
    private int touchSlop;

    /* loaded from: classes10.dex */
    public interface Callback {
        void onDismiss();

        void onMove(float f2);
    }

    /* loaded from: classes10.dex */
    public interface SwipeableViewProvider {
        boolean canBeSwiped();
    }

    public static SwipeToDismissTouchListener createFromView(View view, Callback callback) {
        return new SwipeToDismissTouchListener(callback, ViewConfiguration.get(view.getContext()).getScaledTouchSlop(), view.getContext().getResources().getDisplayMetrics().heightPixels * 0.5f);
    }

    SwipeToDismissTouchListener(Callback callback, int i2, float f2) {
        this(callback, i2, f2, 0.2f * f2);
    }

    SwipeToDismissTouchListener(Callback callback, int i2, float f2, float f3) {
        setCallback(callback);
        this.touchSlop = i2;
        this.maxTranslate = f2;
        this.closeThreshold = f3;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return ((!(view instanceof SwipeableViewProvider) || ((SwipeableViewProvider) view).canBeSwiped() || isMoving()) ? handleTouchEvent(view, motionEvent) : false) || view.onTouchEvent(motionEvent);
    }

    boolean handleTouchEvent(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.lastX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            this.lastY = rawY;
            this.initialY = rawY;
            this.isMoving = false;
            this.pointerIndex = motionEvent.getPointerId(motionEvent.getPointerCount() - 1);
            return false;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                float rawX = motionEvent.getRawX();
                float rawY2 = motionEvent.getRawY();
                float f2 = rawY2 - this.initialY;
                float f3 = rawX - this.lastX;
                float f4 = rawY2 - this.lastY;
                this.lastX = rawX;
                this.lastY = rawY2;
                if (isValidPointer(motionEvent)) {
                    if (this.isMoving || (hasMovedEnoughInProperYDirection(f2) && hasMovedMoreInYDirectionThanX(f3, f4))) {
                        this.isMoving = true;
                        moveView(view, f4);
                        return false;
                    }
                    return false;
                }
                return false;
            } else if (actionMasked != 3) {
                if (actionMasked != 5) {
                    return false;
                }
                settleView(view);
                this.isMoving = false;
                this.pointerIndex = -1;
                return false;
            }
        }
        boolean z = (isValidPointer(motionEvent) && this.isMoving) ? settleOrCloseView(view) : false;
        this.isMoving = false;
        return z;
    }

    boolean hasMovedEnoughInProperYDirection(float f2) {
        return Math.abs(f2) > ((float) this.touchSlop);
    }

    boolean hasMovedMoreInYDirectionThanX(float f2, float f3) {
        return Math.abs(f3) > Math.abs(f2);
    }

    boolean isMoving() {
        return this.isMoving;
    }

    boolean isValidPointer(MotionEvent motionEvent) {
        return this.pointerIndex >= 0 && motionEvent.getPointerCount() == 1;
    }

    boolean settleOrCloseView(View view) {
        float translationY = view.getTranslationY();
        float f2 = this.closeThreshold;
        if (translationY > f2 || translationY < (-f2)) {
            Callback callback = this.callback;
            if (callback != null) {
                callback.onDismiss();
                return true;
            }
            return true;
        }
        settleView(view);
        return false;
    }

    void settleView(View view) {
        if (view.getTranslationY() != 0.0f) {
            ObjectAnimator duration = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0.0f).setDuration(100L);
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SwipeToDismissTouchListener.this.m742x7dc0e98(valueAnimator);
                }
            });
            duration.start();
        }
    }

    /* renamed from: lambda$settleView$0$com-twitter-sdk-android-tweetui-internal-SwipeToDismissTouchListener  reason: not valid java name */
    public /* synthetic */ void m742x7dc0e98(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        Callback callback = this.callback;
        if (callback != null) {
            callback.onMove(floatValue);
        }
    }

    void moveView(View view, float f2) {
        float translationY = view.getTranslationY();
        float bound = bound(translationY + ((float) (f2 * calculateTension(translationY))));
        view.setTranslationY(bound);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onMove(bound);
        }
    }

    double calculateTension(float f2) {
        return 1.0d - (Math.pow(Math.abs(f2), 2.0d) / Math.pow(this.closeThreshold * 2.0f, 2.0d));
    }

    float bound(float f2) {
        float f3 = this.maxTranslate;
        return f2 < (-f3) ? -f3 : f2 > f3 ? f3 : f2;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
