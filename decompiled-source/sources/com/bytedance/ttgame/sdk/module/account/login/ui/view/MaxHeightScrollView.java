package com.bytedance.ttgame.sdk.module.account.login.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.bytedance.ttgame.module.account.impl.R;
/* loaded from: classes3.dex */
public class MaxHeightScrollView extends ScrollView {

    /* renamed from: a  reason: collision with root package name */
    private int f14973a;

    public MaxHeightScrollView(Context context) {
        super(context);
    }

    public MaxHeightScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public MaxHeightScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.gsdk_account_scrollview_max_height);
        this.f14973a = obtainStyledAttributes.getDimensionPixelSize(R.styleable.gsdk_account_scrollview_max_height_max_height, Integer.MAX_VALUE);
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i3), this.f14973a), View.MeasureSpec.getMode(i3)));
    }
}
