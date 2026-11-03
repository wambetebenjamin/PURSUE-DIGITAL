package com.reactnativecommunity.viewpager;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
/* loaded from: classes9.dex */
public class VerticalViewPager extends ViewPager {
    private GestureDetector mGestureDetector;
    private boolean mVertical;

    public VerticalViewPager(Context context) {
        super(context);
        this.mVertical = false;
    }

    public void setOrientation(boolean z) {
        this.mVertical = z;
        if (z) {
            setPageTransformer(true, new a());
            this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.reactnativecommunity.viewpager.VerticalViewPager.1
                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                    return Math.abs(f3) > Math.abs(f2);
                }
            });
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean canScrollHorizontally(int i2) {
        return !canScrollVertically(i2);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        return this.mVertical;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(flipXY(motionEvent));
        flipXY(motionEvent);
        if (this.mVertical && this.mGestureDetector.onTouchEvent(motionEvent)) {
            return true;
        }
        return onInterceptTouchEvent;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(flipXY(motionEvent));
        flipXY(motionEvent);
        if (this.mVertical && this.mGestureDetector.onTouchEvent(motionEvent)) {
            return true;
        }
        return onTouchEvent;
    }

    private MotionEvent flipXY(MotionEvent motionEvent) {
        if (this.mVertical) {
            float width = getWidth();
            float height = getHeight();
            motionEvent.setLocation((motionEvent.getY() / height) * width, (motionEvent.getX() / width) * height);
        }
        return motionEvent;
    }

    /* loaded from: classes9.dex */
    private static final class a implements ViewPager.PageTransformer {
        private a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.PageTransformer
        public void transformPage(View view, float f2) {
            int width = view.getWidth();
            int height = view.getHeight();
            if (f2 < -1.0f) {
                view.setAlpha(0.0f);
            } else if (f2 <= 1.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(width * (-f2));
                view.setTranslationY(f2 * height);
            } else {
                view.setAlpha(0.0f);
            }
        }
    }
}
