package com.ixigua.touchtileimageview.matrixitem;

import android.graphics.Matrix;
import android.graphics.RectF;
/* compiled from: FitCenterMatrixItem.java */
/* loaded from: classes9.dex */
public class b implements f {

    /* renamed from: a  reason: collision with root package name */
    private final Matrix f17173a;

    public b(RectF rectF, RectF rectF2) {
        float min = Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        float centerX = rectF.centerX() - rectF2.centerX();
        float centerY = rectF.centerY() - rectF2.centerY();
        Matrix matrix = new Matrix();
        this.f17173a = matrix;
        matrix.setScale(min, min, rectF2.centerX(), rectF2.centerY());
        matrix.postTranslate(centerX, centerY);
    }

    @Override // com.ixigua.touchtileimageview.matrixitem.f
    public Matrix getMatrix() {
        return this.f17173a;
    }
}
