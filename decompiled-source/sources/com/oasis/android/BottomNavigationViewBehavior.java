package com.oasis.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
/* loaded from: classes9.dex */
public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<View> {

    /* renamed from: a  reason: collision with root package name */
    private float f17359a;

    public BottomNavigationViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17359a = -1.0f;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int[] iArr, int i4) {
        super.onNestedPreScroll(coordinatorLayout, view, view2, i2, i3, iArr, i4);
        view.setTranslationY(view.getHeight() * ((this.f17359a - Math.abs(view2.getY())) / this.f17359a));
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
        if (this.f17359a == -1.0f) {
            this.f17359a = view3.getY();
        }
        return (i2 & 2) != 0;
    }
}
