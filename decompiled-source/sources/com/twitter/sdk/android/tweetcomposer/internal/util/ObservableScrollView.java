package com.twitter.sdk.android.tweetcomposer.internal.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
/* loaded from: classes10.dex */
public class ObservableScrollView extends ScrollView {
    ScrollViewListener scrollViewListener;

    /* loaded from: classes10.dex */
    public interface ScrollViewListener {
        void onScrollChanged(int i2);
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        ScrollViewListener scrollViewListener = this.scrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(i3);
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }
}
