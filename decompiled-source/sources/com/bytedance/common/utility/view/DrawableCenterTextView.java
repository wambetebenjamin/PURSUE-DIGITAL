package com.bytedance.common.utility.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
/* loaded from: classes2.dex */
public class DrawableCenterTextView extends TextView {
    public DrawableCenterTextView(Context context) {
        super(context);
    }

    public DrawableCenterTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DrawableCenterTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public DrawableCenterTextView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (compoundDrawables != null && compoundDrawables.length == 4) {
            Drawable drawable = compoundDrawables[0];
            Drawable drawable2 = compoundDrawables[2];
            if (drawable != null || drawable2 != null) {
                float measureText = getPaint().measureText(getText().toString()) + (drawable != null ? drawable.getIntrinsicWidth() : drawable2.getIntrinsicWidth()) + getCompoundDrawablePadding();
                if (drawable != null) {
                    setGravity(19);
                    canvas.translate((getWidth() - measureText) / 2.0f, 0.0f);
                } else {
                    setGravity(21);
                    canvas.translate((measureText - getWidth()) / 2.0f, 0.0f);
                }
            }
        }
        super.onDraw(canvas);
    }
}
