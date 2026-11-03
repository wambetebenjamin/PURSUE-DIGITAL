package com.ixigua.touchtileimageview.gesture;

import android.view.MotionEvent;
/* compiled from: ScaleGestureDetectorCompat.java */
/* loaded from: classes9.dex */
public abstract class c {

    /* compiled from: ScaleGestureDetectorCompat.java */
    /* loaded from: classes9.dex */
    public interface a {
        boolean onScale(c cVar);

        boolean onScaleBegin(c cVar);

        void onScaleEnd(c cVar);
    }

    public abstract float getFocusX();

    public abstract float getFocusY();

    public abstract float getScaleFactor();

    public abstract boolean onTouchEvent(MotionEvent motionEvent);
}
