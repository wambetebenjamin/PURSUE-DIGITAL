package com.bytedance.ttgame.module.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
/* loaded from: classes7.dex */
public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<View> {

    /* renamed from: a  reason: collision with root package name */
    private int f14658a;

    /* renamed from: b  reason: collision with root package name */
    private a f14659b;

    /* loaded from: classes7.dex */
    public interface a {
        void onNsetScroll(View view, View view2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i2, int i3) {
        return true;
    }

    public BottomNavigationViewBehavior() {
        this.f14658a = 0;
    }

    public BottomNavigationViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14658a = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i2) {
        ((CoordinatorLayout.LayoutParams) view.getLayoutParams()).topMargin = coordinatorLayout.getMeasuredHeight() - view.getMeasuredHeight();
        return super.onLayoutChild(coordinatorLayout, view, i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i2, int i3, int[] iArr, int i4) {
        super.onNestedPreScroll(coordinatorLayout, view, view2, i2, i3, iArr, i4);
        boolean z = true;
        boolean z2 = i3 > 0 && view.getTranslationY() >= ((float) view.getHeight());
        if (i3 >= 0 || view.getTranslationY() > 0.0f) {
            z = false;
        }
        if (z || z2) {
            return;
        }
        int i5 = this.f14658a + i3;
        this.f14658a = i5;
        int i6 = i5 >= 0 ? i5 : 0;
        this.f14658a = i6;
        int height = i6 > view.getHeight() ? view.getHeight() : this.f14658a;
        this.f14658a = height;
        view.setTranslationY(height);
        a aVar = this.f14659b;
        if (aVar != null) {
            aVar.onNsetScroll(view, view2);
        }
    }

    public void a(a aVar) {
        this.f14659b = aVar;
    }

    public void a() {
        this.f14658a = 0;
    }
}
