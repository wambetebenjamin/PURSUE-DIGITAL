package com.swmansion.gesturehandler;

import android.os.Handler;
import android.view.MotionEvent;
/* loaded from: classes9.dex */
public class TapGestureHandler extends GestureHandler<TapGestureHandler> {
    private static final long DEFAULT_MAX_DELAY_MS = 500;
    private static final long DEFAULT_MAX_DURATION_MS = 500;
    private static final int DEFAULT_MIN_NUMBER_OF_POINTERS = 1;
    private static final int DEFAULT_NUMBER_OF_TAPS = 1;
    private static float MAX_VALUE_IGNORE = Float.MIN_VALUE;
    private final Runnable mFailDelayed;
    private Handler mHandler;
    private float mLastX;
    private float mLastY;
    private long mMaxDelayMs;
    private float mMaxDeltaX;
    private float mMaxDeltaY;
    private float mMaxDistSq;
    private long mMaxDurationMs;
    private int mMinNumberOfPointers;
    private int mNumberOfPointers;
    private int mNumberOfTaps;
    private float mOffsetX;
    private float mOffsetY;
    private float mStartX;
    private float mStartY;
    private int mTapsSoFar;

    public TapGestureHandler setNumberOfTaps(int i2) {
        this.mNumberOfTaps = i2;
        return this;
    }

    public TapGestureHandler setMaxDelayMs(long j2) {
        this.mMaxDelayMs = j2;
        return this;
    }

    public TapGestureHandler setMaxDurationMs(long j2) {
        this.mMaxDurationMs = j2;
        return this;
    }

    public TapGestureHandler setMaxDx(float f2) {
        this.mMaxDeltaX = f2;
        return this;
    }

    public TapGestureHandler setMaxDy(float f2) {
        this.mMaxDeltaY = f2;
        return this;
    }

    public TapGestureHandler setMaxDist(float f2) {
        this.mMaxDistSq = f2 * f2;
        return this;
    }

    public TapGestureHandler setMinNumberOfPointers(int i2) {
        this.mMinNumberOfPointers = i2;
        return this;
    }

    public TapGestureHandler() {
        float f2 = MAX_VALUE_IGNORE;
        this.mMaxDeltaX = f2;
        this.mMaxDeltaY = f2;
        this.mMaxDistSq = f2;
        this.mMaxDurationMs = 500L;
        this.mMaxDelayMs = 500L;
        this.mNumberOfTaps = 1;
        this.mMinNumberOfPointers = 1;
        this.mNumberOfPointers = 1;
        this.mFailDelayed = new Runnable() { // from class: com.swmansion.gesturehandler.TapGestureHandler.1
            @Override // java.lang.Runnable
            public void run() {
                TapGestureHandler.this.fail();
            }
        };
        setShouldCancelWhenOutside(true);
    }

    private void startTap() {
        Handler handler = this.mHandler;
        if (handler == null) {
            this.mHandler = new Handler();
        } else {
            handler.removeCallbacksAndMessages(null);
        }
        this.mHandler.postDelayed(this.mFailDelayed, this.mMaxDurationMs);
    }

    private void endTap() {
        Handler handler = this.mHandler;
        if (handler == null) {
            this.mHandler = new Handler();
        } else {
            handler.removeCallbacksAndMessages(null);
        }
        int i2 = this.mTapsSoFar + 1;
        this.mTapsSoFar = i2;
        if (i2 == this.mNumberOfTaps && this.mNumberOfPointers >= this.mMinNumberOfPointers) {
            activate();
            end();
            return;
        }
        this.mHandler.postDelayed(this.mFailDelayed, this.mMaxDelayMs);
    }

    private boolean shouldFail() {
        float f2 = (this.mLastX - this.mStartX) + this.mOffsetX;
        if (this.mMaxDeltaX == MAX_VALUE_IGNORE || Math.abs(f2) <= this.mMaxDeltaX) {
            float f3 = (this.mLastY - this.mStartY) + this.mOffsetY;
            if (this.mMaxDeltaY == MAX_VALUE_IGNORE || Math.abs(f3) <= this.mMaxDeltaY) {
                float f4 = (f3 * f3) + (f2 * f2);
                float f5 = this.mMaxDistSq;
                return f5 != MAX_VALUE_IGNORE && f4 > f5;
            }
            return true;
        }
        return true;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        int state = getState();
        int actionMasked = motionEvent.getActionMasked();
        if (state == 0) {
            this.mOffsetX = 0.0f;
            this.mOffsetY = 0.0f;
            this.mStartX = motionEvent.getRawX();
            this.mStartY = motionEvent.getRawY();
        }
        if (actionMasked == 6 || actionMasked == 5) {
            this.mOffsetX += this.mLastX - this.mStartX;
            this.mOffsetY += this.mLastY - this.mStartY;
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, true);
            float lastPointerY = GestureUtils.getLastPointerY(motionEvent, true);
            this.mLastY = lastPointerY;
            this.mStartX = this.mLastX;
            this.mStartY = lastPointerY;
        } else {
            this.mLastX = GestureUtils.getLastPointerX(motionEvent, true);
            this.mLastY = GestureUtils.getLastPointerY(motionEvent, true);
        }
        if (this.mNumberOfPointers < motionEvent.getPointerCount()) {
            this.mNumberOfPointers = motionEvent.getPointerCount();
        }
        if (shouldFail()) {
            fail();
        } else if (state == 0) {
            if (actionMasked == 0) {
                begin();
            }
            startTap();
        } else if (state == 2) {
            if (actionMasked == 1) {
                endTap();
            } else if (actionMasked == 0) {
                startTap();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onCancel() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onReset() {
        this.mTapsSoFar = 0;
        this.mNumberOfPointers = 0;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
