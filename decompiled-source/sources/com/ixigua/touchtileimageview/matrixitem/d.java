package com.ixigua.touchtileimageview.matrixitem;

import android.graphics.Matrix;
import android.graphics.RectF;
/* compiled from: FitWidthTopMatrixItem.java */
/* loaded from: classes9.dex */
public class d implements f {

    /* renamed from: a  reason: collision with root package name */
    private final Matrix f17175a;

    public d(RectF rectF, RectF rectF2) {
        Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        rectF.centerX();
        rectF2.centerX();
        rectF.centerY();
        rectF2.centerY();
        Matrix matrix = new Matrix();
        this.f17175a = matrix;
        matrix.setScale(rectF.width() / rectF2.width(), rectF.width() / rectF2.width(), 0.0f, 0.0f);
    }

    @Override // com.ixigua.touchtileimageview.matrixitem.f
    public Matrix getMatrix() {
        return this.f17175a;
    }
}
