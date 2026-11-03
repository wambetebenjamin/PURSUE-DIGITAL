package com.ixigua.touchtileimageview.ratioconverter;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.ixigua.touchtileimageview.f;
/* compiled from: LongPictureImageRatioToRatioConverter.java */
/* loaded from: classes9.dex */
public class c implements f {
    public static final c INSTANCE = new c();

    @Override // com.ixigua.touchtileimageview.f
    public Matrix getFromMatrix(RectF rectF, RectF rectF2, Matrix matrix) {
        Matrix matrix2 = new Matrix(matrix);
        matrix2.postTranslate(0.0f, -((rectF2.height() - rectF.height()) / 2.0f));
        return matrix2;
    }
}
