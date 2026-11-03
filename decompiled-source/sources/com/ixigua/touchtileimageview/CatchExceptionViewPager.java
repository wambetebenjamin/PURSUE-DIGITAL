package com.ixigua.touchtileimageview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
/* loaded from: classes9.dex */
public class CatchExceptionViewPager extends FixScrollJumpViewPager {
    public CatchExceptionViewPager(Context context) {
        super(context);
    }

    public CatchExceptionViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.ixigua.touchtileimageview.FixScrollJumpViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.ixigua.touchtileimageview.FixScrollJumpViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
