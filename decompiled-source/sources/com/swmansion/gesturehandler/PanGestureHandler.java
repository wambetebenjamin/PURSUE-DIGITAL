package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
/* loaded from: classes9.dex */
public class PanGestureHandler extends GestureHandler<PanGestureHandler> {
    private static int DEFAULT_MAX_POINTERS = 10;
    private static int DEFAULT_MIN_POINTERS = 1;
    private static float MAX_VALUE_IGNORE = Float.MIN_VALUE;
    private static float MIN_VALUE_IGNORE = Float.MAX_VALUE;
    private float mActiveOffsetXEnd;
    private float mActiveOffsetXStart;
    private float mActiveOffsetYEnd;
    private float mActiveOffsetYStart;
    private boolean mAverageTouches;
    private float mFailOffsetXEnd;
    private float mFailOffsetXStart;
    private float mFailOffsetYEnd;
    private float mFailOffsetYStart;
    private float mLastVelocityX;
    private float mLastVelocityY;
    private float mLastX;
    private float mLastY;
    private int mMaxPointers;
    private float mMinDistSq;
    private int mMinPointers;
    private float mMinVelocitySq;
    private float mMinVelocityX;
    private float mMinVelocityY;
    private float mOffsetX;
    private float mOffsetY;
    private float mStartX;
    private float mStartY;
    private VelocityTracker mVelocityTracker;

    public PanGestureHandler(Context context) {
        float f2 = MAX_VALUE_IGNORE;
        this.mMinDistSq = f2;
        float f3 = MIN_VALUE_IGNORE;
        this.mActiveOffsetXStart = f3;
        this.mActiveOffsetXEnd = f2;
        this.mFailOffsetXStart = f2;
        this.mFailOffsetXEnd = f3;
        this.mActiveOffsetYStart = f3;
        this.mActiveOffsetYEnd = f2;
        this.mFailOffsetYStart = f2;
        this.mFailOffsetYEnd = f3;
        this.mMinVelocityX = f3;
        this.mMinVelocityY = f3;
        this.mMinVelocitySq = f3;
        this.mMinPointers = DEFAULT_MIN_POINTERS;
        this.mMaxPointers = DEFAULT_MAX_POINTERS;
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMinDistSq = scaledTouchSlop * scaledTouchSlop;
    }

    public PanGestureHandler setActiveOffsetXStart(float f2) {
        this.mActiveOffsetXStart = f2;
        return this;
    }

    public PanGestureHandler setActiveOffsetXEnd(float f2) {
        this.mActiveOffsetXEnd = f2;
        return this;
    }

    public PanGestureHandler setFailOffsetXStart(float f2) {
        this.mFailOffsetXStart = f2;
        return this;
    }

    public PanGestureHandler setFailOffsetXEnd(float f2) {
        this.mFailOffsetXEnd = f2;
        return this;
    }

    public PanGestureHandler setActiveOffsetYStart(float f2) {
        this.mActiveOffsetYStart = f2;
        return this;
    }

    public PanGestureHandler setActiveOffsetYEnd(float f2) {
        this.mActiveOffsetYEnd = f2;
        return this;
    }

    public PanGestureHandler setFailOffsetYStart(float f2) {
        this.mFailOffsetYStart = f2;
        return this;
    }

    public PanGestureHandler setFailOffsetYEnd(float f2) {
        this.mFailOffsetYEnd = f2;
        return this;
    }

    public PanGestureHandler setMinDist(float f2) {
        this.mMinDistSq = f2 * f2;
        return this;
    }

    public PanGestureHandler setMinPointers(int i2) {
        this.mMinPointers = i2;
        return this;
    }

    public PanGestureHandler setMaxPointers(int i2) {
        this.mMaxPointers = i2;
        return this;
    }

    public PanGestureHandler setAverageTouches(boolean z) {
        this.mAverageTouches = z;
        return this;
    }

    public PanGestureHandler setMinVelocity(float f2) {
        this.mMinVelocitySq = f2 * f2;
        return this;
    }

    public PanGestureHandler setMinVelocityX(float f2) {
        this.mMinVelocityX = f2;
        return this;
    }

    public PanGestureHandler setMinVelocityY(float f2) {
        this.mMinVelocityY = f2;
        return this;
    }

    private boolean shouldActivate() {
        float f2 = (this.mLastX - this.mStartX) + this.mOffsetX;
        float f3 = this.mActiveOffsetXStart;
        float f4 = MIN_VALUE_IGNORE;
        if (f3 == f4 || f2 >= f3) {
            float f5 = this.mActiveOffsetXEnd;
            float f6 = MAX_VALUE_IGNORE;
            if (f5 == f6 || f2 <= f5) {
                float f7 = (this.mLastY - this.mStartY) + this.mOffsetY;
                float f8 = this.mActiveOffsetYStart;
                if (f8 == f4 || f7 >= f8) {
                    float f9 = this.mActiveOffsetYEnd;
                    if (f9 == f6 || f7 <= f9) {
                        float f10 = (f2 * f2) + (f7 * f7);
                        float f11 = this.mMinDistSq;
                        if (f11 == f4 || f10 < f11) {
                            float f12 = this.mLastVelocityX;
                            float f13 = this.mMinVelocityX;
                            if (f13 == f4 || ((f13 >= 0.0f || f12 > f13) && (f13 < 0.0f || f12 < f13))) {
                                float f14 = this.mLastVelocityY;
                                float f15 = this.mMinVelocityY;
                                if (f15 == f4 || ((f15 >= 0.0f || f12 > f15) && (f15 < 0.0f || f12 < f15))) {
                                    float f16 = (f12 * f12) + (f14 * f14);
                                    float f17 = this.mMinVelocitySq;
                                    return f17 != f4 && f16 >= f17;
                                }
                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private boolean shouldFail() {
        float f2 = (this.mLastX - this.mStartX) + this.mOffsetX;
        float f3 = this.mFailOffsetXStart;
        float f4 = MAX_VALUE_IGNORE;
        if (f3 == f4 || f2 >= f3) {
            float f5 = this.mFailOffsetXEnd;
            float f6 = MIN_VALUE_IGNORE;
            if (f5 == f6 || f2 <= f5) {
                float f7 = (this.mLastY - this.mStartY) + this.mOffsetY;
                float f8 = this.mFailOffsetYStart;
                if (f8 == f4 || f7 >= f8) {
                    float f9 = this.mFailOffsetYEnd;
                    return f9 != f6 && f7 > f9;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        int state = getState();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 6 || actionMasked == 5) {
            this.mOffsetX += this.mLastX - this.mStartX;
            this.mOffsetY += this.mLastY - this.mStartY;
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, this.mAverageTouches);
            float lastPointerY = GestureUtils.getLastPointerY(motionEvent, this.mAverageTouches);
            this.mLastY = lastPointerY;
            this.mStartX = this.mLastX;
            this.mStartY = lastPointerY;
        } else {
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, this.mAverageTouches);
            this.mLastY = GestureUtils.getLastPointerY(motionEvent, this.mAverageTouches);
        }
        if (state == 0 && motionEvent.getPointerCount() >= this.mMinPointers) {
            this.mStartX = this.mLastX;
            this.mStartY = this.mLastY;
            this.mOffsetX = 0.0f;
            this.mOffsetY = 0.0f;
            VelocityTracker obtain = VelocityTracker.obtain();
            this.mVelocityTracker = obtain;
            addVelocityMovement(obtain, motionEvent);
            begin();
        } else {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                addVelocityMovement(velocityTracker, motionEvent);
                this.mVelocityTracker.computeCurrentVelocity(1000);
                this.mLastVelocityX = this.mVelocityTracker.getXVelocity();
                this.mLastVelocityY = this.mVelocityTracker.getYVelocity();
            }
        }
        if (actionMasked == 1) {
            if (state == 4 || state == 2) {
                end();
            } else {
                fail();
            }
        } else if (actionMasked == 5 && motionEvent.getPointerCount() > this.mMaxPointers) {
            if (state == 4) {
                cancel();
            } else {
                fail();
            }
        } else if (actionMasked == 6 && state == 4 && motionEvent.getPointerCount() < this.mMinPointers) {
            fail();
        } else if (state == 2) {
            if (shouldFail()) {
                fail();
            } else if (shouldActivate()) {
                this.mStartX = this.mLastX;
                this.mStartY = this.mLastY;
                activate();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public float getTranslationX() {
        return (this.mLastX - this.mStartX) + this.mOffsetX;
    }

    public float getTranslationY() {
        return (this.mLastY - this.mStartY) + this.mOffsetY;
    }

    public float getVelocityX() {
        return this.mLastVelocityX;
    }

    public float getVelocityY() {
        return this.mLastVelocityY;
    }

    private static void addVelocityMovement(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        velocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }
}
