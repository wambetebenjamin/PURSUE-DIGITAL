package com.ixigua.touchtileimageview.matrixitem;

import android.graphics.Matrix;
import android.graphics.RectF;
/* compiled from: CropCenterMatrixItem.java */
/* loaded from: classes9.dex */
public class a implements f {

    /* renamed from: a  reason: collision with root package name */
    private final Matrix f17172a;

    public a(RectF rectF, RectF rectF2) {
        Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        float centerX = rectF.centerX() - rectF2.centerX();
        float centerY = rectF.centerY() - rectF2.centerY();
        Matrix matrix = new Matrix();
        this.f17172a = matrix;
        float max = Math.max(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        matrix.setScale(max, max, rectF2.centerX(), rectF2.centerY());
        matrix.postTranslate(centerX, centerY);
    }

    @Override // com.ixigua.touchtileimageview.matrixitem.f
    public Matrix getMatrix() {
        return this.f17172a;
    }
}
