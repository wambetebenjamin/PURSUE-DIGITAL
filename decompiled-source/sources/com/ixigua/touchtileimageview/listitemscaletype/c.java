package com.ixigua.touchtileimageview.listitemscaletype;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
/* compiled from: FitCenterListItemScaleType.java */
/* loaded from: classes9.dex */
public class c implements e {
    public static final c INSTANCE = new c();

    @Override // com.ixigua.touchtileimageview.listitemscaletype.e
    public Matrix getMatrix(RectF rectF, Rect rect) {
        Matrix matrix = new Matrix();
        float max = 1.0f / Math.max(rectF.width() / rect.width(), rectF.height() / rect.height());
        matrix.setScale(max, max, rectF.centerX(), rectF.centerY());
        matrix.postTranslate(rect.centerX() - rectF.centerX(), rect.centerY() - rectF.centerY());
        return matrix;
    }
}
