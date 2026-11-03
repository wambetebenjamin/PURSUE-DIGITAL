package com.ixigua.touchtileimageview;

import android.graphics.Rect;
import android.view.View;
import androidx.core.view.ViewCompat;
/* compiled from: ViewRectCallback.java */
/* loaded from: classes9.dex */
public abstract class m {
    protected abstract View a(Object obj);

    public int[] getMaskInsetPixel(Object obj) {
        return null;
    }

    public float getRoundRectRadius(Object obj) {
        return 0.0f;
    }

    public int getViewOverlayInsetPixel(Object obj) {
        return 0;
    }

    public boolean isCircleView(Object obj) {
        return false;
    }

    public Rect getImageViewRect(Object obj) {
        return a(a(obj));
    }

    public Rect getImageViewVisibleRect(Object obj) {
        View a2 = a(obj);
        if (a2 != null && ViewCompat.isAttachedToWindow(a2)) {
            Rect rect = new Rect();
            a2.getGlobalVisibleRect(rect);
            return rect;
        }
        return null;
    }

    private static Rect a(View view) {
        if (view != null && ViewCompat.isAttachedToWindow(view)) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i2 = iArr[0];
            int i3 = iArr[1];
            return new Rect(i2, i3, (int) (i2 + (view.getWidth() * view.getScaleX())), (int) (i3 + (view.getHeight() * view.getScaleY())));
        }
        return null;
    }
}
