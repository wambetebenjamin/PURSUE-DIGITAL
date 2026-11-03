package com.ixigua.touchtileimageview.matrixitem;

import android.graphics.Matrix;
import android.graphics.RectF;
/* compiled from: FullMatrixItem.java */
/* loaded from: classes9.dex */
public class e implements f {

    /* renamed from: a  reason: collision with root package name */
    private final Matrix f17176a;

    public e(RectF rectF, RectF rectF2) {
        float centerY = rectF.centerY() - rectF2.centerY();
        Matrix matrix = new Matrix();
        this.f17176a = matrix;
        matrix.postTranslate(rectF.centerX() - rectF2.centerX(), centerY);
    }

    @Override // com.ixigua.touchtileimageview.matrixitem.f
    public Matrix getMatrix() {
        return this.f17176a;
    }
}
