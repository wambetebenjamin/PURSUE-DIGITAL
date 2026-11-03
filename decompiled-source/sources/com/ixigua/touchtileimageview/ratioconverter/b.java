package com.ixigua.touchtileimageview.ratioconverter;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.ixigua.touchtileimageview.f;
/* compiled from: DefaultToLongPictureRatioConverter.java */
/* loaded from: classes9.dex */
public class b implements f {
    public static final b INSTANCE = new b();

    @Override // com.ixigua.touchtileimageview.f
    public Matrix getFromMatrix(RectF rectF, RectF rectF2, Matrix matrix) {
        float width = rectF.width() / rectF2.width();
        float centerY = rectF.centerY() - rectF2.centerY();
        Matrix matrix2 = new Matrix(matrix);
        matrix2.postScale(width, width, rectF2.centerX(), rectF2.centerY());
        matrix2.postTranslate(0.0f, centerY);
        return matrix2;
    }
}
