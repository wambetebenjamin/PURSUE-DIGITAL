package com.just.agentweb;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
/* loaded from: classes9.dex */
public class NestedScrollAgentWebView extends AgentWebView implements NestedScrollingChild {
    private NestedScrollingChildHelper mChildHelper;
    private int mLastMotionY;
    private int mNestedYOffset;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;

    public NestedScrollAgentWebView(Context context) {
        super(context);
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        init();
    }

    public NestedScrollAgentWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        init();
    }

    private void init() {
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int[] iArr;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.mNestedYOffset = 0;
        }
        int y = (int) motionEvent.getY();
        motionEvent.offsetLocation(0.0f, this.mNestedYOffset);
        if (actionMasked == 0) {
            this.mLastMotionY = y;
            startNestedScroll(2);
            return super.onTouchEvent(motionEvent);
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                int i2 = this.mLastMotionY - y;
                if (dispatchNestedPreScroll(0, i2, this.mScrollConsumed, this.mScrollOffset)) {
                    i2 -= this.mScrollConsumed[1];
                    obtain.offsetLocation(0.0f, this.mScrollOffset[1]);
                    this.mNestedYOffset += this.mScrollOffset[1];
                }
                this.mLastMotionY = y - this.mScrollOffset[1];
                int scrollY = getScrollY();
                int max = Math.max(0, scrollY + i2) - scrollY;
                if (dispatchNestedScroll(0, max, 0, i2 - max, this.mScrollOffset)) {
                    this.mLastMotionY = this.mLastMotionY - this.mScrollOffset[1];
                    obtain.offsetLocation(0.0f, iArr[1]);
                    this.mNestedYOffset += this.mScrollOffset[1];
                }
                boolean onTouchEvent = super.onTouchEvent(obtain);
                obtain.recycle();
                return onTouchEvent;
            } else if (actionMasked != 3 && actionMasked != 5) {
                return false;
            }
        }
        stopNestedScroll();
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i2) {
        return this.mChildHelper.startNestedScroll(i2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.mChildHelper.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.mChildHelper.dispatchNestedPreFling(f2, f3);
    }
}
