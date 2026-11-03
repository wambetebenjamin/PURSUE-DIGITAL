package com.swmansion.gesturehandler;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
/* loaded from: classes9.dex */
public class LongPressGestureHandler extends GestureHandler<LongPressGestureHandler> {
    private static float DEFAULT_MAX_DIST_DP = 10.0f;
    private static final long DEFAULT_MIN_DURATION_MS = 500;
    private Handler mHandler;
    private float mMaxDistSq;
    private long mMinDurationMs = 500;
    private float mStartX;
    private float mStartY;

    public LongPressGestureHandler(Context context) {
        setShouldCancelWhenOutside(true);
        this.mMaxDistSq = DEFAULT_MAX_DIST_DP * context.getResources().getDisplayMetrics().density;
    }

    public void setMinDurationMs(long j2) {
        this.mMinDurationMs = j2;
    }

    public LongPressGestureHandler setMaxDist(float f2) {
        this.mMaxDistSq = f2 * f2;
        return this;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onHandle(MotionEvent motionEvent) {
        if (getState() == 0) {
            begin();
            this.mStartX = motionEvent.getRawX();
            this.mStartY = motionEvent.getRawY();
            Handler handler = new Handler();
            this.mHandler = handler;
            handler.postDelayed(new Runnable() { // from class: com.swmansion.gesturehandler.LongPressGestureHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    LongPressGestureHandler.this.activate();
                }
            }, this.mMinDurationMs);
        }
        if (motionEvent.getActionMasked() == 1) {
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
                this.mHandler = null;
            }
            if (getState() == 4) {
                end();
                return;
            } else {
                fail();
                return;
            }
        }
        float rawX = motionEvent.getRawX() - this.mStartX;
        float rawY = motionEvent.getRawY() - this.mStartY;
        if ((rawX * rawX) + (rawY * rawY) > this.mMaxDistSq) {
            if (getState() == 4) {
                cancel();
            } else {
                fail();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    protected void onStateChange(int i2, int i3) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }
}
