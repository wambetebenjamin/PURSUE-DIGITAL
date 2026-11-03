package com.ixigua.touchtileimageview.ratioconverter;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.ixigua.touchtileimageview.f;
/* compiled from: CropCenterImageRatioToRatioConverter.java */
/* loaded from: classes9.dex */
public class a implements f {
    public static final a INSTANCE = new a();

    @Override // com.ixigua.touchtileimageview.f
    public Matrix getFromMatrix(RectF rectF, RectF rectF2, Matrix matrix) {
        float centerX = rectF.centerX() - rectF2.centerX();
        float centerY = rectF.centerY() - rectF2.centerY();
        float height = rectF.height() / rectF2.height();
        Matrix matrix2 = new Matrix(matrix);
        matrix2.postScale(height, height, rectF2.centerX(), rectF2.centerY());
        matrix2.postTranslate(centerX, centerY);
        return matrix2;
    }
}
