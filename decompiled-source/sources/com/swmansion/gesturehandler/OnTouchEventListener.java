package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import com.swmansion.gesturehandler.GestureHandler;
/* loaded from: classes9.dex */
public interface OnTouchEventListener<T extends GestureHandler> {
    void onStateChange(T t, int i2, int i3);

    void onTouchEvent(T t, MotionEvent motionEvent);
}
