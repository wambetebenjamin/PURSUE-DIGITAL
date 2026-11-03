package com.ixigua.touchtileimageview.listitemscaletype;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
/* compiled from: CropCenterListItemScaleType.java */
/* loaded from: classes9.dex */
public class b implements e {
    public static final b INSTANCE = new b();

    @Override // com.ixigua.touchtileimageview.listitemscaletype.e
    public Matrix getMatrix(RectF rectF, Rect rect) {
        Matrix matrix = new Matrix();
        float min = 1.0f / Math.min(rectF.width() / rect.width(), rectF.height() / rect.height());
        matrix.setScale(min, min, rectF.centerX(), rectF.centerY());
        matrix.postTranslate(rect.centerX() - rectF.centerX(), rect.centerY() - rectF.centerY());
        return matrix;
    }
}
