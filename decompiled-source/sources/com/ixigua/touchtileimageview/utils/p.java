package com.ixigua.touchtileimageview.utils;

import android.animation.TypeEvaluator;
import android.graphics.Rect;
/* compiled from: RectEvaluator.java */
/* loaded from: classes9.dex */
public class p implements TypeEvaluator<Rect> {

    /* renamed from: a  reason: collision with root package name */
    private Rect f17223a;

    public p() {
    }

    public p(Rect rect) {
        this.f17223a = rect;
    }

    @Override // android.animation.TypeEvaluator
    public Rect evaluate(float f2, Rect rect, Rect rect2) {
        int i2 = rect.left + ((int) ((rect2.left - rect.left) * f2));
        int i3 = rect.top + ((int) ((rect2.top - rect.top) * f2));
        int i4 = rect.right + ((int) ((rect2.right - rect.right) * f2));
        int i5 = rect.bottom + ((int) ((rect2.bottom - rect.bottom) * f2));
        Rect rect3 = this.f17223a;
        if (rect3 == null) {
            return new Rect(i2, i3, i4, i5);
        }
        rect3.set(i2, i3, i4, i5);
        return this.f17223a;
    }
}
