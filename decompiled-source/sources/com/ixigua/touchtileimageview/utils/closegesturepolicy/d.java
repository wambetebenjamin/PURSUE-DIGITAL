package com.ixigua.touchtileimageview.utils.closegesturepolicy;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.ixigua.touchtileimageview.j;
import com.ixigua.touchtileimageview.utils.h;
/* compiled from: FitWidthBottomScrollGesturePolicy.java */
/* loaded from: classes9.dex */
public class d extends com.ixigua.touchtileimageview.utils.e {

    /* renamed from: a  reason: collision with root package name */
    private static final float f17202a = 1.0f;

    /* renamed from: b  reason: collision with root package name */
    private static final float f17203b = 1.0f;

    /* renamed from: c  reason: collision with root package name */
    private static final float f17204c = 0.1f;

    /* renamed from: d  reason: collision with root package name */
    private float f17205d;

    public d(Matrix matrix) {
        super(matrix);
        this.f17205d = h.scaleValue(matrix);
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public float getScaleValue(float f2, float f3, RectF rectF, RectF rectF2, float f4) {
        if (f4 >= 0.0f) {
            return this.f17205d;
        }
        float min = Math.min(1.0f, Math.abs(f4) / (rectF2.height() * 1.0f));
        float f5 = this.f17205d;
        return f5 + ((f3 - f5) * min);
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public float getAlphaValue(float f2, float f3, RectF rectF, RectF rectF2, float f4) {
        if (f4 >= 0.0f) {
            return 1.0f;
        }
        return 1.0f - Math.min(1.0f, Math.abs(f4) / (rectF2.height() * 1.0f));
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public float getMinScaleValue(RectF rectF, RectF rectF2) {
        return this.f17205d / 2.0f;
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public boolean canExit(j jVar, float f2, RectF rectF, RectF rectF2, float f3) {
        return com.ixigua.touchtileimageview.utils.d.isGreaterThanEqual(-f3, rectF2.height() * 0.1f);
    }
}
