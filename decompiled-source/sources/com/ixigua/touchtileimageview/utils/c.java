package com.ixigua.touchtileimageview.utils;

import android.animation.TypeEvaluator;
/* compiled from: FloatArrayEvaluator.java */
/* loaded from: classes9.dex */
public class c implements TypeEvaluator<float[]> {

    /* renamed from: a  reason: collision with root package name */
    private float[] f17190a;

    public c(float[] fArr) {
        this.f17190a = fArr;
    }

    @Override // android.animation.TypeEvaluator
    public float[] evaluate(float f2, float[] fArr, float[] fArr2) {
        float[] fArr3 = this.f17190a;
        if (fArr3 == null) {
            fArr3 = new float[fArr.length];
        }
        for (int i2 = 0; i2 < fArr3.length; i2++) {
            float f3 = fArr[i2];
            fArr3[i2] = f3 + ((fArr2[i2] - f3) * f2);
        }
        return fArr3;
    }
}
