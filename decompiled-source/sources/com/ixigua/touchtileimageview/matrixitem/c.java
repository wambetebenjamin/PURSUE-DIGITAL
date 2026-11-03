package com.ixigua.touchtileimageview.matrixitem;

import android.graphics.Matrix;
import android.graphics.RectF;
/* compiled from: FitWidthBottomMatrixItem.java */
/* loaded from: classes9.dex */
public class c implements f {

    /* renamed from: a  reason: collision with root package name */
    private final Matrix f17174a;

    public c(RectF rectF, RectF rectF2) {
        Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        rectF.centerX();
        rectF2.centerX();
        rectF.centerY();
        rectF2.centerY();
        Matrix matrix = new Matrix();
        this.f17174a = matrix;
        matrix.setScale(rectF.width() / rectF2.width(), rectF.width() / rectF2.width(), 0.0f, 0.0f);
        RectF rectF3 = new RectF();
        matrix.mapRect(rectF3, rectF2);
        matrix.postTranslate(0.0f, rectF.bottom - rectF3.bottom);
    }

    @Override // com.ixigua.touchtileimageview.matrixitem.f
    public Matrix getMatrix() {
        return this.f17174a;
    }
}
