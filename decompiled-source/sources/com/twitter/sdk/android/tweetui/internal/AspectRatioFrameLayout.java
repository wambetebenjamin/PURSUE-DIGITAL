package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.twitter.sdk.android.tweetui.R;
/* loaded from: classes10.dex */
public class AspectRatioFrameLayout extends FrameLayout {
    static final int ADJUST_DIMENSION_HEIGHT = 0;
    static final int ADJUST_DIMENSION_WIDTH = 1;
    private static final int DEFAULT_ADJUST_DIMENSION = 0;
    private static final float DEFAULT_ASPECT_RATIO = 1.0f;
    protected double aspectRatio;
    private int dimensionToAdjust;

    public AspectRatioFrameLayout(Context context) {
        this(context, null);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initAttributes(i2);
    }

    private void initAttributes(int i2) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(i2, R.styleable.AspectRatioFrameLayout);
        try {
            this.aspectRatio = obtainStyledAttributes.getFloat(R.styleable.AspectRatioFrameLayout_tw__frame_layout_aspect_ratio, 1.0f);
            this.dimensionToAdjust = obtainStyledAttributes.getInt(R.styleable.AspectRatioFrameLayout_tw__frame_layout_dimension_to_adjust, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setAspectRatio(double d2) {
        this.aspectRatio = d2;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int measuredHeight;
        int i4;
        int i5;
        int measuredWidth;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.dimensionToAdjust == 0) {
            if (View.MeasureSpec.getMode(i2) == 1073741824) {
                measuredWidth = View.MeasureSpec.getSize(i2);
            } else {
                super.onMeasure(i2, i3);
                measuredWidth = getMeasuredWidth();
            }
            i5 = measuredWidth - paddingLeft;
            i4 = (int) (i5 / this.aspectRatio);
        } else {
            if (View.MeasureSpec.getMode(i3) == 1073741824) {
                measuredHeight = View.MeasureSpec.getSize(i3);
            } else {
                super.onMeasure(i2, i3);
                measuredHeight = getMeasuredHeight();
            }
            i4 = measuredHeight - paddingBottom;
            i5 = (int) (i4 * this.aspectRatio);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i5 + paddingLeft, 1073741824), View.MeasureSpec.makeMeasureSpec(i4 + paddingBottom, 1073741824));
    }
}
