package com.ixigua.touchtileimageview.listitemscaletype;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
/* compiled from: FitTopListItemScaleType.java */
/* loaded from: classes9.dex */
public class d implements e {
    public static final d INSTANCE = new d();

    @Override // com.ixigua.touchtileimageview.listitemscaletype.e
    public Matrix getMatrix(RectF rectF, Rect rect) {
        Matrix matrix = new Matrix();
        float width = rect.width() / rectF.width();
        matrix.setScale(width, width, 0.0f, 0.0f);
        matrix.postTranslate(rect.left - rectF.left, rect.top - rectF.top);
        return matrix;
    }
}
