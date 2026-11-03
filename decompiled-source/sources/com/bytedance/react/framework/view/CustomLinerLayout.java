package com.bytedance.react.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
/* loaded from: classes2.dex */
public class CustomLinerLayout extends LinearLayout {
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public CustomLinerLayout(Context context) {
        super(context);
    }

    public CustomLinerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomLinerLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public CustomLinerLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }
}
