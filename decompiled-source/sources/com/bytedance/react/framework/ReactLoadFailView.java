package com.bytedance.react.framework;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
/* loaded from: classes2.dex */
public class ReactLoadFailView extends LinearLayout {
    public ReactLoadFailView(Context context) {
        super(context);
        initView();
    }

    public ReactLoadFailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public ReactLoadFailView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView();
    }

    private void initView() {
        setOrientation(1);
        setGravity(17);
        setBackgroundColor(-1);
    }

    public void buildView(int i2, int i3) {
        int i4 = i2 < i3 ? i2 >> 1 : i3 >> 1;
        TextView textView = new TextView(getContext());
        textView.setText("load resource error, please try again");
        textView.setTextSize(2, 16.0f);
        textView.setPadding(dip2px(getContext(), 20.0f), 0, dip2px(getContext(), 20.0f), 0);
        textView.setGravity(17);
        textView.setTextColor(getResources().getColor(17170444));
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.sign_paper_error);
        drawable.setBounds(0, 0, i4, i4);
        textView.setCompoundDrawables(null, drawable, null, null);
        textView.setCompoundDrawablePadding(dip2px(getContext(), 20.0f));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        addView(textView, layoutParams);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(new Runnable() { // from class: com.bytedance.react.framework.ReactLoadFailView.1
            @Override // java.lang.Runnable
            public void run() {
                ReactLoadFailView reactLoadFailView = ReactLoadFailView.this;
                reactLoadFailView.measure(View.MeasureSpec.makeMeasureSpec(reactLoadFailView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactLoadFailView.this.getHeight(), 1073741824));
                ReactLoadFailView reactLoadFailView2 = ReactLoadFailView.this;
                reactLoadFailView2.layout(reactLoadFailView2.getLeft(), ReactLoadFailView.this.getTop(), ReactLoadFailView.this.getRight(), ReactLoadFailView.this.getBottom());
            }
        });
    }

    private int dip2px(Context context, float f2) {
        if (context != null) {
            return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        return 0;
    }
}
