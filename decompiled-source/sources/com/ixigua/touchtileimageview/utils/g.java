package com.ixigua.touchtileimageview.utils;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;
/* compiled from: MatrixEvaluator.java */
/* loaded from: classes9.dex */
public class g implements TypeEvaluator<Matrix> {

    /* renamed from: a  reason: collision with root package name */
    private float[] f17210a = new float[9];

    /* renamed from: b  reason: collision with root package name */
    private float[] f17211b = new float[9];

    /* renamed from: c  reason: collision with root package name */
    private Matrix f17212c = new Matrix();

    @Override // android.animation.TypeEvaluator
    public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f17210a);
        matrix2.getValues(this.f17211b);
        for (int i2 = 0; i2 < 9; i2++) {
            float[] fArr = this.f17211b;
            float f3 = fArr[i2];
            float[] fArr2 = this.f17210a;
            fArr[i2] = fArr2[i2] + ((f3 - fArr2[i2]) * f2);
        }
        this.f17212c.setValues(this.f17211b);
        return this.f17212c;
    }
}
