package com.ixigua.touchtileimageview.utils.closegesturepolicy;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.ixigua.touchtileimageview.j;
import com.ixigua.touchtileimageview.utils.h;
/* compiled from: FitCenterScaleGesturePolicy.java */
/* loaded from: classes9.dex */
public class b extends com.ixigua.touchtileimageview.utils.e {

    /* renamed from: a  reason: collision with root package name */
    private static final float f17195a = 0.5f;

    /* renamed from: b  reason: collision with root package name */
    private float f17196b;

    /* renamed from: c  reason: collision with root package name */
    private float f17197c;

    public b(Matrix matrix) {
        super(matrix);
        float scaleValue = h.scaleValue(matrix);
        this.f17196b = scaleValue;
        this.f17197c = scaleValue * 0.5f;
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public float getAlphaValue(float f2, float f3, RectF rectF, RectF rectF2, float f4) {
        float max = Math.max(this.f17197c, f3);
        if (com.ixigua.touchtileimageview.utils.d.isExactlyLessThanEqual(f2, max)) {
            return 0.0f;
        }
        if (com.ixigua.touchtileimageview.utils.d.isExactlyGreaterThan(f2, this.f17196b)) {
            return 1.0f;
        }
        return Math.abs((f2 - max) / max);
    }

    @Override // com.ixigua.touchtileimageview.utils.e
    public boolean canExit(j jVar, float f2, RectF rectF, RectF rectF2, float f3) {
        return com.ixigua.touchtileimageview.utils.d.isExactlyLessThanEqual(f2, h.scaleValue(this.matrix) * 0.8f);
    }
}
